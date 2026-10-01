package erebus.entity;

import erebus.entity.ai.LocustMoveControl;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class Locust extends Monster {
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(Locust.class, EntityDataSerializers.BOOLEAN);
    public int animationTicks, prevAnimationTicks;
    public boolean canJump = true;
    public int flyingTicks;
    public int prevflyingTicks;
    private int jumpTicks;
    private int jumpDuration;

    public Locust(EntityType<? extends Locust> type, Level level) {
        super(type, level);
        moveControl = new LocustMoveControl(this);
        setPathfindingMalus(PathType.WATER, -8F);
        setPathfindingMalus(PathType.BLOCKED, -8F);
        setPathfindingMalus(PathType.OPEN, 8F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100D)
                .add(Attributes.FOLLOW_RANGE, 16D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D)
                .add(Attributes.FLYING_SPEED, 0.75D)
                .add(Attributes.ATTACK_DAMAGE, 4D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.STEP_HEIGHT, 1D);
    }

    public static boolean canSpawnHere(EntityType<Locust> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
        float light = level.getLightLevelDependentMagicValue(pos);
        return light >= 0F;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, true));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        goalSelector.addGoal(4, new AIRandomJumpWhenIdle(this));
        goalSelector.addGoal(5, new AIFlyingWander(this, 0.75D, 10));
        targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers(Locust.class));
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
    public boolean checkSpawnObstruction(LevelReader world) {
        return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, @NonNull BlockState state, @NonNull BlockPos pos) {
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.LOCUST_SOUND.get();
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.LOCUST_HURT.get();
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    public void setCanJump(boolean ableToJump) {
        canJump = ableToJump;
    }

    public boolean isFlying() {
        return entityData.get(FLYING);
    }

    public void setIsFlying(boolean flying) {
        entityData.set(FLYING, flying);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder data) {
        super.defineSynchedData(data);
        data.define(FLYING, false);
    }

    @Override
    public void jumpFromGround() {
        setDeltaMovement(getDeltaMovement().x, 0.75D, getDeltaMovement().z);
        setCanJump(false);
        needsSync = true;
        CommonHooks.onLivingJump(this);
        jumpDuration = 20;
        jumpTicks = 0;
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte) 1);
        }
    }

    public void startFlying() {
        setIsFlying(true);
    }

    public void stopFlying() {
        setIsFlying(false);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 1) {
            jumpDuration = 20;
            jumpTicks = 0;
        } else super.handleEntityEvent(id);
    }

    @Override
    protected float getFlyingSpeed() {
        return 0.05F; // Legacy jumpMovementFactor.
    }

    public float getJumpPose(float partialTick) {
        return jumpDuration == 0 ? 0.0F : ((float) jumpTicks + partialTick) / (float) jumpDuration;
    }

    public float getFlyingPose(float partialTick) {
        return Mth.lerp(partialTick, prevflyingTicks, flyingTicks);
    }

    @Override
    public void aiStep() {
        if (jumpTicks != jumpDuration && !isFlying()) {
            ++jumpTicks;
        } else if (jumpDuration != 0) {
            jumpTicks = 0;
            jumpDuration = 0;
        }

        var motion = getDeltaMovement();
        setDeltaMovement(motion.multiply(1, getMoveControl().hasWanted() && motion.y < 0 ? 0.2 : 0.75, 1));
        if (isInWater()) getMoveControl().setWantedPosition(getX(), getY() + 1, getZ(), 0.32D);
        if (!level().isClientSide()) {
            if (getMoveControl().hasWanted() && !onGround()) startFlying();
            else stopFlying();
        }
        super.aiStep();
    }

    @Override
    public void tick() {
        if (level().isClientSide()) {
            prevAnimationTicks = animationTicks;
            prevflyingTicks = flyingTicks;
            if (animationTicks < 720)
                animationTicks += 1;
            if (animationTicks >= 720) {
                animationTicks -= 720;
                prevAnimationTicks -= 720;
            }
            if (flyingTicks < 18 && isFlying())
                flyingTicks++;
            else if (flyingTicks > 0 && !isFlying())
                flyingTicks = Math.max(0, flyingTicks - 2);
        }

        super.tick();

    }

    @Override
    public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity entity) {
        if (hasLineOfSight(entity)) {
            if (super.doHurtTarget(level, entity)) {
                if (entity instanceof LivingEntity) {
                    byte duration = 0;

                    if (level().getDifficulty().ordinal() > Difficulty.EASY.ordinal())
                        if (level().getDifficulty() == Difficulty.NORMAL)
                            duration = 8;
                        else if (level().getDifficulty() == Difficulty.HARD)
                            duration = 15;

                    if (duration > 0)
                        ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.HUNGER, duration * 20, 0));
                }
            }
            return true;
        } else
            return false;
    }

    public static class AIFlyingWander extends RandomStrollGoal {

        private final Locust locust;

        public AIFlyingWander(Locust locustIn, double speedIn, int chance) {
            super(locustIn, speedIn, chance);
            this.locust = locustIn;
        }

        @Nullable
        @Override
        protected Vec3 getPosition() {
            return DefaultRandomPos.getPos(this.mob, 32, 32);
        }
    }

    public static class AIRandomJumpWhenIdle extends Goal {

        private final Locust locust;
        private int idleTime;

        public AIRandomJumpWhenIdle(Locust locustIn) {
            this.locust = locustIn;
            setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return locust.random.nextFloat() < 0.02F && locust.onGround() && locust.canJump;
        }

        @Override
        public boolean canContinueToUse() {
            return idleTime >= 0;
        }

        public void start() {
            locust.jumpFromGround();
            idleTime = 20 + locust.random.nextInt(20);
        }

        @Override
        public void tick() {
            if (locust.onGround() && !locust.canJump)
                locust.setCanJump(true);
            --this.idleTime;
        }
    }
}
