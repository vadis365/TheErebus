package erebus.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class BogMaw extends Monster {

    private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(BogMaw.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> JAW_ANGLE = SynchedEntityData.defineId(BogMaw.class, EntityDataSerializers.FLOAT);
    private float previousJaw, clientJaw;

    public BogMaw(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25F)
                .add(Attributes.MOVEMENT_SPEED, 0.0F)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.STEP_HEIGHT, 0);
    }

    public static boolean checkBogMawSpawnRules(EntityType<BogMaw> type, ServerLevelAccessor level,
                                                EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, level, reason, pos, random);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new LeapAtTargetGoal(this, 0.5F));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0, true));
        targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(ROTATION, 0.0F);
        entityData.define(JAW_ANGLE, 0.0F);
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!(target instanceof LivingEntity living) || !getSensing().hasLineOfSight(living)
                || !living.hurtServer(level, damageSources().cactus(), 4)) return false;
        living.addEffect(new MobEffectInstance(MobEffects.POISON, 50, 0), this);
        living.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 50, 0), this);
        return true;
    }

    @Override
    public void tick() {
        previousJaw = level().isClientSide() ? clientJaw : getJawAngle();
        super.tick();
        setYRot(0);
        yRotO = 0;
        yBodyRot = 0;
        yBodyRotO = 0;
        if (level().isClientSide()) {
            clientJaw = getJawAngle();
            return;
        }
        var target = getTarget();
        if (target != null && target.isAlive()) setRotation(target.getYRot());
        boolean nearby = target != null && target.isAlive() && distanceToSqr(target) <= 16;
        setJawAngle(getJawAngle() + (nearby ? 0.1F : -0.1F));
    }

    public float getJawAngle(float partialTick) {
        return Mth.lerp(partialTick, previousJaw, level().isClientSide() ? clientJaw : getJawAngle());
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
    public int getMaxSpawnClusterSize() {
        return 6;
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putFloat("JawAngle", getJawAngle());
        output.putFloat("LeapRotation", getRotation());
    }

    @Override
    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        setJawAngle(input.getFloatOr("JawAngle", 0));
        setRotation(input.getFloatOr("LeapRotation", 0));
        previousJaw = getJawAngle();
        clientJaw = getJawAngle();
    }

    public float getRotation() {
        return entityData.get(ROTATION);
    }

    public void setRotation(float rot) {
        entityData.set(ROTATION, Float.isFinite(rot) ? Mth.wrapDegrees(rot) : 0);
    }

    public float getJawAngle() {
        return entityData.get(JAW_ANGLE);
    }

    public void setJawAngle(float angle) {
        entityData.set(JAW_ANGLE, Float.isFinite(angle) ? Mth.clamp(angle, 0, 1) : 0);
    }
}
