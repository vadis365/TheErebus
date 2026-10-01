package erebus.entity;

import erebus.entity.ai.LocustMoveControl;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

public class MidgeSwarm extends Monster {
    public MidgeSwarm(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        // Midge swarms use the same legacy flying steering as locusts.
        moveControl = new LocustMoveControl(this);
        setPathfindingMalus(PathType.WATER, -8);
        setPathfindingMalus(PathType.BLOCKED, -8);
        setPathfindingMalus(PathType.OPEN, 8);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 1)
                .add(Attributes.FOLLOW_RANGE, 8);
    }

    public static boolean checkMidgeSpawnRules(EntityType<MidgeSwarm> type, ServerLevelAccessor level,
                                               EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && validHabitat(level, pos, random)
                && Mob.checkMobSpawnRules(type, level, reason, pos, random);
    }

    public static boolean validHabitat(LevelAccessor level, BlockPos pos, RandomSource random) {
        return pos.getY() <= level.getMinY() + 100 && level.getMaxLocalRawBrightness(pos) <= random.nextInt(7);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5, true) {
            @Override
            protected boolean canPerformAttack(LivingEntity target) {
                return isTimeToAttack() && mob.distanceToSqr(target) <= 4 + target.getBbWidth() && mob.getSensing().hasLineOfSight(target);
            }
        });
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        goalSelector.addGoal(5, new RandomStrollGoal(this, 0.75, 1) {
            @Override
            protected Vec3 getPosition() {
                return DefaultRandomPos.getPos(mob, 32, 32);
            }
        });
        targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new FlyingPathNavigation(this, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (getDeltaMovement().y < 0) setDeltaMovement(getDeltaMovement().multiply(1, 0.2, 1));
        if (isInWater()) moveControl.setWantedPosition(getX(), getY() + 1, getZ(), 0.32);
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!hasLineOfSight(target) || !super.doHurtTarget(level, target)) return false;
        int duration = switch (level.getDifficulty()) {
            case NORMAL -> 160;
            case HARD -> 300;
            default -> 0;
        };
        if (duration > 0 && target instanceof LivingEntity living)
            living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration, 0), this);
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
        return validHabitat(level, blockPosition(), random);
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 2;
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
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Override
    public boolean causeFallDamage(double distance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public float getVoicePitch() {
        return super.getVoicePitch() * 2;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.MOSQUITO_FLYING.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.FLY_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }
}
