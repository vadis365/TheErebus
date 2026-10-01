package erebus.entity;

import erebus.entity.ai.AboveWaterNavigation;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
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

public class PondSkater extends Monster {
    public PondSkater(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        setPathfindingMalus(PathType.WALKABLE, -8);
        setPathfindingMalus(PathType.BLOCKED, -8);
        setPathfindingMalus(PathType.LAVA, -8);
        setPathfindingMalus(PathType.WATER, 16);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.MOVEMENT_SPEED, 1.2)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.STEP_HEIGHT, 0);
    }

    public static boolean checkSkaterSpawnRules(EntityType<PondSkater> type, ServerLevelAccessor level,
                                                EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && validHabitat(level, pos);
    }

    public static boolean validHabitat(LevelAccessor level, BlockPos pos) {
        return pos.getY() <= level.getMinY() + 24 && level.getFluidState(pos).is(FluidTags.WATER);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5, true) {
            @Override
            protected boolean canPerformAttack(LivingEntity target) {
                return isTimeToAttack() && mob.distanceToSqr(target) <= 4 + target.getBbWidth()
                        && mob.getSensing().hasLineOfSight(target);
            }
        });
        goalSelector.addGoal(2, new RandomStrollGoal(this, 0.5, 10) {
            @Override
            protected Vec3 getPosition() {
                return DefaultRandomPos.getPos(mob, 16, 0);
            }
        });
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new AboveWaterNavigation(this, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().getFluidState(blockPosition()).is(FluidTags.WATER)) {
            if (getDeltaMovement().y < 0) setDeltaMovement(getDeltaMovement().multiply(1, 0, 1));
            resetFallDistance();
        }
        if (!level().isClientSide() && getTarget() != null && !getTarget().isInWater()) setTarget(null);
        if (isInWater()) moveControl.setWantedPosition(getX(), getY() + 1, getZ(), 0.32);
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!target.isInWater() || !hasLineOfSight(target) || !super.doHurtTarget(level, target)) return false;
        int duration = switch (level.getDifficulty()) {
            case NORMAL -> 140;
            case HARD -> 300;
            default -> 0;
        };
        if (duration > 0 && target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.POISON, duration, 0), this);
            living.addEffect(new MobEffectInstance(MobEffects.NAUSEA, duration, 0), this);
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
        return validHabitat(level, blockPosition());
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return level.isUnobstructed(this) && level.noCollision(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 6;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.GENERIC_SWIM, 0.125F, 0.125F);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }
}
