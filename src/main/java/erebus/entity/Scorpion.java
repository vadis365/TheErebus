package erebus.entity;

import erebus.Config;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

public class Scorpion extends Monster {
    private static final EntityDataAccessor<Boolean> CLIMBING = SynchedEntityData.defineId(Scorpion.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> STING_TICKS = SynchedEntityData.defineId(Scorpion.class, EntityDataSerializers.INT);
    private float stingAngle;
    private float previousStingAngle;

    public Scorpion(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        setPathfindingMalus(PathType.WATER, -8);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 1)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.STEP_HEIGHT, 1);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CLIMBING, false);
        builder.define(STING_TICKS, 0);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.3, false) {
            @Override
            protected boolean canPerformAttack(LivingEntity target) {
                return isTimeToAttack() && mob.distanceToSqr(target) <= 4 + target.getBbWidth() && mob.getSensing().hasLineOfSight(target);
            }
        });
        goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6));
        goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.3));
        goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8));
        goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) {
            entityData.set(CLIMBING, horizontalCollision);
            tickSting();
        }
        previousStingAngle = stingAngle;
        int ticks = entityData.get(STING_TICKS);
        stingAngle = isVehicle() ? (ticks >= 4 ? (8 - ticks) : ticks) * 0.16F : 0;

    }

    @Override
    public boolean onClimbable() {
        return entityData.get(CLIMBING);
    }

    @Override
    public boolean causeFallDamage(double distance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 multiplier) {
        if (!state.is(Blocks.COBWEB)) super.makeStuckInBlock(state, multiplier);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 4;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
        return true;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1);
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);
        if (!level().isClientSide() && Config.scorpionGrab && !isVehicle() && !isPassenger()
                && isAlive() && player.isAlive() && !player.isCreative() && !player.isSpectator()
                && !player.isPassenger() && hasLineOfSight(player)) {
            if (player.startRiding(this, true, true)) player.setShiftKeyDown(false);
        }
    }

    @Override
    protected void positionRider(Entity passenger, Entity.MoveFunction move) {
        if (hasPassenger(passenger)) {
            double yaw = Math.toRadians(yBodyRot);
            var attachment = passenger.getVehicleAttachmentPoint(this);
            move.accept(passenger, getX() - Math.sin(yaw) * 0.75 - attachment.x,
                    getY() + 0.75 - attachment.y, getZ() + Math.cos(yaw) * 0.75 - attachment.z);
            if (Config.scorpionGrab && passenger instanceof Player player && !player.isCreative() && !player.isSpectator())
                passenger.setShiftKeyDown(false);
        }
    }

    public float getStingAngle(float partialTick) {
        return Mth.lerp(partialTick, previousStingAngle, stingAngle);
    }

    private void tickSting() {
        int ticks = entityData.get(STING_TICKS);
        if (ticks > 0) entityData.set(STING_TICKS, ticks - 1);
        if (!Config.scorpionGrab) return;
        if (getFirstPassenger() instanceof Player player && player.isAlive() && !player.isCreative() && !player.isSpectator()) {
            int duration = switch (level().getDifficulty()) {
                case NORMAL -> 100;
                case HARD -> 200;
                default -> 0;
            };
            if (duration > 0 && random.nextInt(50) == 0) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, duration, 0), this);
                entityData.set(STING_TICKS, 8);
                level().playSound(null, blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 0.5F, 2);
            }
        }
    }
}
