package erebus.entity;

import erebus.entity.ai.FlyingWanderGoal;
import erebus.events.BotFlyInfestationHandler;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class BotFly extends Monster {
    // AKA ButtFly
    public int animationTicks, prevAnimationTicks;

    public BotFly(EntityType<? extends BotFly> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        setPathfindingMalus(PathType.WATER, -8F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 15D)
                .add(Attributes.FOLLOW_RANGE, 16D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FLYING_SPEED, 1D)
                .add(Attributes.ATTACK_DAMAGE, 1D);
    }

    public static boolean canSpawnHere(EntityType<BotFly> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
        if (pos.getY() > 100)
            return false;
        else {
            int light = level.getMaxLocalRawBrightness(pos);
            return light <= random.nextInt(7) && checkMobSpawnRules(entity, level, spawn, pos, random);
        }
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, true));
        goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        goalSelector.addGoal(4, new FlyingWanderGoal(this, 0.75D, 0.01F));
        targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers(BotFly.class));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, false));
    }

    @Override
    public float getWalkTargetValue(@NonNull BlockPos pos, LevelReader level) {
        return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    @Override
    protected @NonNull PathNavigation createNavigation(@NonNull Level level) {
        return new FlyingPathNavigation(this, level);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.FLY_SOUND.get();
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.FLY_HURT.get();
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, @NonNull BlockState state, @NonNull BlockPos pos) {
    }

    @Override
    public boolean causeFallDamage(double distance, float multiplier, @NonNull DamageSource source) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide()) {
            prevAnimationTicks = animationTicks;
            if (animationTicks < 360)
                animationTicks += 1;
            if (animationTicks >= 360) {
                animationTicks -= 360;
                prevAnimationTicks -= 360;
            }
        }

        Vec3 vec3 = this.getDeltaMovement();
        if (vec3.y < 0.0D)
            this.setDeltaMovement(vec3.multiply(1.0D, 0.4D, 1.0D));

        if (!level().isClientSide() && isInWater())
            getMoveControl().setWantedPosition(getX(), getY() + 1D, getZ(), 0.32D);
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 3;
    }

    @Override
    public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity entity) {
        if (hasLineOfSight(entity)) {
            if (super.doHurtTarget(level, entity)) {
                if (entity instanceof Player player && random.nextInt(20) == 0)
                    BotFlyInfestationHandler.infect(player);
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Entity getParasite(Player player) {
        return BotFlyInfestationHandler.getLarva(player);
    }
}
