package erebus.entity;

import erebus.registries.ModSounds;
import erebus.utils.AnimationMathHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.Optional;

public class Mosquito extends Monster {
    private static final EntityDataAccessor<Byte> BLOOD = SynchedEntityData.defineId(Mosquito.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> PLAYER_HOST = SynchedEntityData.defineId(Mosquito.class, EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
    private static final int MAX_BLOOD_LEVEL = 5;
    private final AnimationMathHelper mathWings = new AnimationMathHelper();
    private final AnimationMathHelper mathSucking = new AnimationMathHelper();
    public float wingFloat, wingFloatO;
    public float suckFloat = 1, suckFloatO = 1;
    public int hitInterval = 30;
    private BlockPos flightTarget;
    private int drainage;
    private int consumptionTimer;

    public Mosquito(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.ATTACK_DAMAGE, 2);
    }

    public static boolean hasWaterSurface(LevelAccessor level, AABB bounds) {
        var area = bounds.inflate(5);
        for (int x = Mth.floor(area.minX); x < Mth.floor(area.maxX); x++)
            for (int y = Math.max(level.getMinY() + 1, Mth.floor(area.minY)); y < Math.min(level.getMaxY(), Mth.floor(area.maxY)); y++)
                for (int z = Mth.floor(area.minZ); z < Mth.floor(area.maxZ); z++) {
                    var pos = new BlockPos(x, y, z);
                    if (level.hasChunkAt(pos) && level.isEmptyBlock(pos) && level.getFluidState(pos.below()).is(FluidTags.WATER)) return true;
                }
        return false;
    }

    public static boolean checkMosquitoSpawnRules(EntityType<Mosquito> type, ServerLevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && hasWaterSurface(level, type.getDimensions().makeBoundingBox(Vec3.atBottomCenterOf(pos)));
    }

    @Override
    protected void registerGoals() {
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder data) {
        super.defineSynchedData(data);
        data.define(BLOOD, (byte) 0);
        data.define(PLAYER_HOST, Optional.empty());
    }

    @Override
    public void tick() {
        super.tick();
        if (!isAlive()) return;
        if (getFeedingHost() instanceof Player player && player.isAlive() && player.level() == level()) {
            setDeltaMovement(Vec3.ZERO);
            setPos(player.getX(), player.getY() + player.getBbHeight() * 0.65, player.getZ());
            setYRot(player.getYRot());
            yBodyRot = player.getYRot();
        }
        if (level().isClientSide()) {
            wingFloatO = wingFloat;
            suckFloatO = suckFloat;
            wingFloat = isFeeding() ? 0 : mathWings.swing(4, 0.1F);
            suckFloat = isFeeding() ? 1 + mathSucking.swing(1, 0.15F) : 1;
            if (isFeeding() && random.nextInt(10) == 0)
                for (int i = 0; i < 8; i++)
                    level().addParticle(DustParticleOptions.REDSTONE,
                            getX() + random.nextFloat() - random.nextFloat(), getY() + random.nextFloat() + 1,
                            getZ() + random.nextFloat() - random.nextFloat(), 0, 0, 0);
            return;
        }
        if (consumptionTimer > 0 && --consumptionTimer == 0) setBloodConsumed(0);
        if (isNoAi()) return;
        var server = (ServerLevel) level();
        if (getFeedingHost() instanceof LivingEntity host) {
            if (!host.isAlive() || host.level() != level() || getBloodConsumed() >= MAX_BLOOD_LEVEL
                    || host instanceof Player player && (player.isCreative() || player.isSpectator())) {
                detachHost();
                drainage = 0;
                setTarget(null);
            } else if (++drainage >= Math.max(1, hitInterval)) {
                drainage = 0;
                if (host.hurtServer(server, damageSources().mobAttack(this), getFeedingDamage()))
                    setBloodConsumed(getBloodConsumed() + 1);
                if (getBloodConsumed() >= MAX_BLOOD_LEVEL || !host.isAlive()) {
                    detachHost();
                    setTarget(null);
                }
            }
            return;
        }
        entityData.set(PLAYER_HOST, Optional.empty());
        drainage = 0;
        LivingEntity host = getBloodConsumed() < MAX_BLOOD_LEVEL ? findHost() : null;
        setTarget(host);
        if (host != null && distanceToSqr(host) <= 1.44 && !host.isVehicle() && hasLineOfSight(host)) {
            if (host instanceof Player player) {
                entityData.set(PLAYER_HOST, Optional.of(EntityReference.of(player)));
                flightTarget = null;
                return;
            }
            if (startRiding(host)) {
                flightTarget = null;
                return;
            }
        }
        if (getDeltaMovement().y < 0) setDeltaMovement(getDeltaMovement().multiply(1, 0.8, 1));
        if (isInWater()) setDeltaMovement(getDeltaMovement().add(0, 0.08, 0));
        if (level().getFluidState(blockPosition().below()).is(FluidTags.WATER))
            setDeltaMovement(getDeltaMovement().x, 0.25, getDeltaMovement().z);
        fly(server, host);
    }

    private LivingEntity findHost() {
        var player = level().getNearestPlayer(this, 10);
        if (player != null && player.isAlive() && !player.isCreative() && !player.isSpectator() && hasLineOfSight(player)) return player;
        var retaliation = getLastHurtByMob();
        if (retaliation != null && retaliation.isAlive() && !(retaliation instanceof Player) && !retaliation.isVehicle()
                && distanceToSqr(retaliation) <= 100 && hasLineOfSight(retaliation)) return retaliation;
        return level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(10),
                        entity -> entity.isAlive() && !entity.isVehicle() && (entity instanceof Pig || entity instanceof Cow || entity instanceof BeetleLarva)
                                && hasLineOfSight(entity))
                .stream().min(Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
    }

    private void fly(ServerLevel level, LivingEntity host) {
        Vec3 destination;
        if (host != null) destination = host.position().add(0.5, 1, 0.5);
        else {
            if (flightTarget != null && (!level.isInWorldBounds(flightTarget) || !level.hasChunkAt(flightTarget)
                    || !level.isEmptyBlock(flightTarget))) flightTarget = null;
            if (flightTarget == null || random.nextInt(30) == 0 || flightTarget.distSqr(blockPosition()) < 4)
                flightTarget = blockPosition().offset(random.nextInt(7) - random.nextInt(7), random.nextInt(6) - 2,
                        random.nextInt(7) - random.nextInt(7));
            if (!level.isInWorldBounds(flightTarget) || !level.getWorldBorder().isWithinBounds(flightTarget) || !level.hasChunkAt(flightTarget)) {
                flightTarget = null;
                return;
            }
            destination = Vec3.atBottomCenterOf(flightTarget).add(0, 1, 0);
        }
        var motion = getDeltaMovement();
        var delta = destination.subtract(position());
        var next = motion.add((Math.signum(delta.x) * 0.5 - motion.x) * 0.1,
                (Math.signum(delta.y) * 0.7 - motion.y) * 0.1, (Math.signum(delta.z) * 0.5 - motion.z) * 0.1);
        setDeltaMovement(next);
        zza = 0.5F;
        setYRot((float) (Mth.atan2(next.z, next.x) * Mth.RAD_TO_DEG) - 90);
        yBodyRot = getYRot();
    }

    public int getFeedingDamage() {
        int damage = 2;
        if (hasEffect(MobEffects.STRENGTH)) damage += 3 << Math.min(10, getEffect(MobEffects.STRENGTH).getAmplifier());
        if (hasEffect(MobEffects.WEAKNESS)) damage -= 2 << Math.min(10, getEffect(MobEffects.WEAKNESS).getAmplifier());
        return Math.max(0, damage);
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (!super.hurtServer(level, source, damage)) return false;
        if (source.getEntity() instanceof LivingEntity attacker && attacker != this) {
            if (getFeedingHost() == attacker) {
                detachHost();
                drainage = 0;
                setDeltaMovement(getDeltaMovement().add(0, 0.5, 0));
            } else setTarget(attacker);
        }
        return true;
    }

    public Entity getFeedingHost() {
        return entityData.get(PLAYER_HOST).map(reference -> reference.getEntity(level(), LivingEntity.class)).orElse(null) instanceof Player player
                ? player : getVehicle();
    }

    public boolean isFeeding() {
        return getFeedingHost() != null;
    }

    public void detachHost() {
        entityData.set(PLAYER_HOST, Optional.empty());
        stopRiding();
    }

    public int getBloodConsumed() {
        return entityData.get(BLOOD);
    }

    public void setBloodConsumed(int blood) {
        int bounded = Math.clamp(blood, 0, MAX_BLOOD_LEVEL);
        entityData.set(BLOOD, (byte) bounded);
        consumptionTimer = bounded > 0 ? 1200 : 0;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putByte("BloodLevel", (byte) getBloodConsumed());
        EntityReference.store(entityData.get(PLAYER_HOST).orElse(null), output, "PlayerHost");
        output.putInt("ConsumptionTimer", consumptionTimer);
        output.putInt("Drainage", drainage);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        setBloodConsumed(input.getByteOr("BloodLevel", (byte) 0));
        entityData.set(PLAYER_HOST, Optional.ofNullable(EntityReference.readWithOldOwnerConversion(input, "PlayerHost", level())));
        consumptionTimer = getBloodConsumed() == 0 ? 0 : Math.clamp(input.getIntOr("ConsumptionTimer", 1200), 1, 1200);
        drainage = Math.clamp(input.getIntOr("Drainage", 0), 0, 29);
        flightTarget = null;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
        return hasWaterSurface(level, getBoundingBox());
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this);
    }

    @Override
    public boolean causeFallDamage(double distance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return isFeeding() ? ModSounds.MOSQUITO_SUCKING.get() : ModSounds.MOSQUITO_FLYING.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MOSQUITO_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.MOSQUITO_HIT.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.1F;
    }

    @Override
    public float getVoicePitch() {
        return 1;
    }
}
