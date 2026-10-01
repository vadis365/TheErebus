package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
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
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class Crushroom extends Monster {

    private static final byte SMASH_EVENT = 61;
    private static final EntityDataAccessor<Integer> SMASH_COUNT = SynchedEntityData.defineId(Crushroom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> STANDING = SynchedEntityData.defineId(Crushroom.class, EntityDataSerializers.BYTE);
    private float previousSmash, clientSmash;
    private int sporeCooldown = 40;

    public Crushroom(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 200F)
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
    }

    public static boolean checkCrushroomSpawnRules(EntityType<Crushroom> type, ServerLevelAccessor level,
                                                   EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, level, reason, pos, random);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(SMASH_COUNT, 0);
        entityData.define(STANDING, (byte) 1);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.CRUSHLING_LIVING.get();
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.CRUSHLING_HURT.get();
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return ModSounds.CRUSHLING_DEATH.get();
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState blockState) {
        playSound(SoundEvents.IRON_GOLEM_STEP, 0.15F, 1.0F);
    }

    public int getSmashCount() {
        return entityData.get(SMASH_COUNT);
    }

    public void setSmashCount(int count) {
        entityData.set(SMASH_COUNT, Mth.clamp(count, 0, 20));
    }

    public byte isStanding() {
        return entityData.get(STANDING);
    }

    public void setStanding(byte standing) {
        entityData.set(STANDING, (byte) Mth.clamp(standing, 0, 3));
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        var type = target.getType();
        return type != ModEntities.CRUSHROOM.get()
                && type != ModEntities.FUNGAL_WEEVIL.get()
                && type != ModEntities.ZOMBIE_ANT.get()
                && type != ModEntities.ZOMBIE_ANT_SOLDIER.get()
                && type != ModEntities.PUNCHROOM.get() && super.canAttack(target);
    }

    @Override
    public void tick() {
        previousSmash = level().isClientSide() ? clientSmash : getSmashCount();
        super.tick();
        if (level().isClientSide()) {
            clientSmash = getSmashCount();
            ClientParticles.spawnParticles(ClientParticles.ParticleType.SPORES, getRandomX(1), getRandomY() - 0.25,
                    getRandomZ(1), 1 + random.nextDouble(), 1 + random.nextDouble(), 1 + random.nextDouble());
            return;
        }
        if (isNoAi()) return;
        var target = getTarget();
        if (target == null || !target.isAlive()) {
            setSmashCount(0);
            setStanding((byte) 1);
            return;
        }
        lookAt(target, 10, 20);
        double distance = distanceToSqr(target);
        if (distance <= 25) {
            getNavigation().stop();
            if (getSmashCount() < 20 && isStanding() != 3) {
                setSmashCount(getSmashCount() + 1);
                setStanding((byte) 2);
            }
            if (getSmashCount() == 20 && isStanding() == 2) {
                setStanding((byte) 3);
                if (target.getBoundingBox().maxY >= getBoundingBox().minY - 1
                        && target.getBoundingBox().minY <= getBoundingBox().maxY && getSensing().hasLineOfSight(target)) {
                    playSound(ModSounds.BLAM_SOUND.get(), 0.5F, 1);
                    level().broadcastEntityEvent(this, SMASH_EVENT);
                    if (target.hurtServer((ServerLevel) level(), damageSources().mobAttack(this), (float) getAttributeValue(Attributes.ATTACK_DAMAGE)))
                        target.push(-Mth.sin(getYRot() * Mth.DEG_TO_RAD) * 0.5, 0.2, Mth.cos(getYRot() * Mth.DEG_TO_RAD) * 0.5);
                }
            }
            if (isStanding() == 3 && getSmashCount() > 0) {
                setSmashCount(getSmashCount() - 1);
                if (getSmashCount() == 0) setStanding((byte) 2);
            }
        } else {
            if (distance <= 144 && getSensing().hasLineOfSight(target) && --sporeCooldown <= 0) {
                shootSpores(target);
                sporeCooldown = 40;
            }
            setSmashCount(getSmashCount() - 1);
            setStanding((byte) (getSmashCount() == 0 ? 0 : 3));
        }
    }

    public void shootSpores(LivingEntity target) {
        if (!(level() instanceof ServerLevel server)) return;
        var spores = ModEntities.SPORE_BALL.get().create(server, EntitySpawnReason.TRIGGERED);
        if (spores == null) return;
        spores.setOwner(this);
        spores.setPos(getX(), getEyeY() - 0.1, getZ());
        double dx = target.getX() - getX(), dz = target.getZ() - getZ();
        spores.shoot(dx, target.getY() + target.getBbHeight() * 0.5 - spores.getY() + Math.sqrt(dx * dx + dz * dz) * 0.2, dz, 0.8F, 0);
        if (server.addFreshEntity(spores)) playSound(ModSounds.SPRAY_CAN_SOUND.get(), 0.5F, 0.1F / (random.nextFloat() * 0.4F + 0.8F));
    }

    public float getSmashCount(float partialTick) {
        return Mth.lerp(partialTick, previousSmash, level().isClientSide() ? clientSmash : getSmashCount());
    }

    @Override
    public void handleEntityEvent(byte event) {
        if (event == SMASH_EVENT) ClientParticles.spawnParticles(ClientParticles.ParticleType.CRUSHROOM_BLAM, getX(), getY(), getZ(), 0, 0, 0);
        else super.handleEntityEvent(event);
    }

    @Override
    public float getVoicePitch() {
        return 0.1F;
    }

    @Override
    public boolean checkSpawnRules(@NonNull LevelAccessor level, @NonNull EntitySpawnReason reason) {
        return true;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 3;
    }

    @Override
    public void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("SporeCooldown", sporeCooldown);
        output.putInt("SmashCount", getSmashCount());
        output.putByte("Standing", isStanding());
    }

    @Override
    public void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        sporeCooldown = Math.clamp(input.getIntOr("SporeCooldown", 40), 0, 40);
        setSmashCount(input.getIntOr("SmashCount", 0));
        setStanding(input.getByteOr("Standing", (byte) 1));
        previousSmash = clientSmash = getSmashCount();
    }
}
