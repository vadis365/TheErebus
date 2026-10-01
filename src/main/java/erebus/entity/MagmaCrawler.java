package erebus.entity;

import erebus.entity.ai.MagmaCrawlerFireballGoal;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class MagmaCrawler extends Monster {
    private static final EntityDataAccessor<Boolean> CEILING_ENABLED = SynchedEntityData.defineId(MagmaCrawler.class, EntityDataSerializers.BOOLEAN);

    public MagmaCrawler(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CEILING_ENABLED, true);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6, false));
        goalSelector.addGoal(2, new MagmaCrawlerFireballGoal(this));
        goalSelector.addGoal(3, new RandomStrollGoal(this, 0.5));
        goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8));
        goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    public boolean getOnCeiling() {
        return entityData.get(CEILING_ENABLED) && level().getBlockState(blockPosition().above()).is(ModBlocks.GNEISS)
                && level().isEmptyBlock(blockPosition().below());
    }

    public void setOnCeiling(boolean enabled) {
        entityData.set(CEILING_ENABLED, enabled);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) {
            if (getOnCeiling() && (lastHurtByPlayerMemoryTime == 60 || (getTarget() != null && distanceToSqr(getTarget()) < 9)))
                setOnCeiling(false);
        }
        if (getOnCeiling()) setDeltaMovement(getDeltaMovement().add(0, 0.1, 0));
    }

    @Override
    public void aiStep() {
        if (!level().isClientSide() && getOnCeiling() && getTarget() != null) {
            var motion = getDeltaMovement();
            double x = motion.x + (Math.signum(getTarget().getX() + 0.5 - getX()) * 0.5 - motion.x) * 0.050000000149011612;
            double z = motion.z + (Math.signum(getTarget().getZ() + 0.5 - getZ()) * 0.5 - motion.z) * 0.050000000149011612;
            setDeltaMovement(x, motion.y, z);
            float yaw = (float) (Math.atan2(z, x) * 180 / Math.PI) - 90;
            setYRot(getYRot() + Mth.wrapDegrees(yaw - getYRot()));
            zza = 0.1F;
        }
        super.aiStep();
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        return hasLineOfSight(target) && super.doHurtTarget(level, target);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
        return level.isEmptyBlock(blockPosition()) && level.getBlockState(blockPosition().above()).is(ModBlocks.GNEISS);
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 5;
    }

    @Override
    public boolean causeFallDamage(double distance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.MAGMACRAWLER.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.MAGMACRAWLER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MAGMACRAWLER_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("CeilingEnabled", entityData.get(CEILING_ENABLED));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        setOnCeiling(input.getBooleanOr("CeilingEnabled", true));
    }
}
