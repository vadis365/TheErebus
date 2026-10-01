package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModParticles;
import erebus.utils.AnimationMathHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.Nullable;

public class Cicada extends Monster {
    private static final EntityDataAccessor<Boolean> FLIGHT_ACTIVE = SynchedEntityData.defineId(Cicada.class, EntityDataSerializers.BOOLEAN);
    private final AnimationMathHelper mathWings = new AnimationMathHelper();
    public float wingFloat;
    public float wingFloatO;
    private int sonics;
    private @Nullable BlockPos flightTarget;

    public Cicada(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.MOVEMENT_SPEED, 0).add(Attributes.FOLLOW_RANGE, 8).add(Attributes.STEP_HEIGHT, 0);
    }

    public static boolean hasCypressNearby(LevelAccessor level, BlockPos pos) {
        for (var candidate : BlockPos.betweenClosed(pos.offset(-5, -5, -5), pos.offset(5, 4, 5)))
            if (!level.isOutsideBuildHeight(candidate) && level.hasChunkAt(candidate) && level.getBlockState(candidate).is(ModBlocks.LOG_CYPRESS)) return true;
        return false;
    }

    public static boolean checkCicadaSpawnRules(EntityType<Cicada> type, ServerLevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && hasCypressNearby(level, pos) && Mob.checkMobSpawnRules(type, level, reason, pos, random);
    }

    @Override
    protected void registerGoals() {
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder data) {
        super.defineSynchedData(data);
        data.define(FLIGHT_ACTIVE, false);
    }

    public boolean isFlying() {
        return !onGround();
    }

    public boolean isFlightActive() {
        return entityData.get(FLIGHT_ACTIVE);
    }

    public void setFlightActive(boolean active) {
        entityData.set(FLIGHT_ACTIVE, active);
        if (!active) flightTarget = null;
    }

    @Override
    public void tick() {
        super.tick();
        if (!isAlive()) return;
        wingFloatO = wingFloat;
        wingFloat = isFlying() ? mathWings.swing(4, 0.1F) : 0;
        if (getDeltaMovement().y < 0) setDeltaMovement(getDeltaMovement().multiply(1, 0.6, 1));
        if (level() instanceof ServerLevel server && !isNoAi()) {
            if (sonics == 20) sonicPulse(server);
            sonics = (sonics + 1) % 21;
            if (isFlightActive() && random.nextInt(100) == 0) setFlightActive(false);
            if (isFlightActive()) flyAbout(server);
        }
    }

    private void sonicPulse(ServerLevel level) {
        var center = position().add(0.5, 0.5, 0.5);
        var area = new AABB(center, center).inflate(4, 0.5, 4);
        for (var player : level.getEntitiesOfClass(Player.class, area,
                p -> p.isAlive() && !p.isCreative() && !p.isSpectator())) {
            if (!hasLineOfSight(player)) continue;
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 160), this);
            player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 160), this);
            float yaw = getYRot() * Mth.DEG_TO_RAD;
            player.push(-Mth.sin(yaw) * 2, 0, Mth.cos(yaw) * 2);
            player.hurtMarked = true;
            level.playSound(null, blockPosition(), ModSounds.LOCUST_SPAWN.get(), SoundSource.NEUTRAL, 1, 6);
            level.broadcastEntityEvent(this, (byte) 61);
            setFlightActive(true);
            break;
        }
    }

    private void flyAbout(ServerLevel level) {
        if (flightTarget != null && (!level.isInWorldBounds(flightTarget) || !level.hasChunkAt(flightTarget)
                || !level.isEmptyBlock(flightTarget) || flightTarget.getY() <= level.getMinY())) flightTarget = null;
        if (flightTarget == null || random.nextInt(30) == 0 || flightTarget.distSqr(blockPosition()) < 10)
            flightTarget = blockPosition().offset(random.nextInt(7) - random.nextInt(7), random.nextInt(6) - 2,
                    random.nextInt(7) - random.nextInt(7));
        if (!level.isInWorldBounds(flightTarget) || !level.getWorldBorder().isWithinBounds(flightTarget) || !level.hasChunkAt(flightTarget)) {
            flightTarget = null;
            return;
        }
        var motion = getDeltaMovement();
        var next = motion.add((Math.signum(flightTarget.getX() + 0.5 - getX()) * 0.5 - motion.x) * 0.1,
                (Math.signum(flightTarget.getY() + 1 - getY()) * 0.7 - motion.y) * 0.1,
                (Math.signum(flightTarget.getZ() + 0.5 - getZ()) * 0.5 - motion.z) * 0.1);
        setDeltaMovement(next);
        zza = 0.5F;
        setYRot((float) (Mth.atan2(next.z, next.x) * Mth.RAD_TO_DEG) - 90);
        yBodyRot = getYRot();
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("Flying", isFlightActive());
        output.putInt("Sonics", sonics);
        if (flightTarget != null) output.store("FlightTarget", BlockPos.CODEC, flightTarget);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        setFlightActive(input.getBooleanOr("Flying", false));
        sonics = Mth.clamp(input.getIntOr("Sonics", 0), 0, 20);
        flightTarget = isFlightActive() ? input.read("FlightTarget", BlockPos.CODEC).orElse(null) : null;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
        return level.getDifficulty() != Difficulty.PEACEFUL && hasCypressNearby(level, blockPosition());
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 10;
    }

    @Override
    public boolean onClimbable() {
        return horizontalCollision;
    }

    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    public boolean causeFallDamage(double distance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    public void handleEntityEvent(byte event) {
        if (event == 61) {
            if (level().isClientSide()) spawnSonicParticles();
        } else super.handleEntityEvent(event);
    }

    private void spawnSonicParticles() {
        for (int angle = 0; angle < 360; angle += 6) {
            float radians = angle * Mth.DEG_TO_RAD;
            level().addParticle(ModParticles.REPELLENT.get(), getX() - Mth.sin(radians), getY() + 0.5, getZ() + Mth.cos(radians), -Mth.sin(radians) * 0.3, 0, Mth.cos(radians) * 0.3);
        }
        for (int angle = 0; angle < 360; angle += 4) {
            float radians = angle * Mth.DEG_TO_RAD;
            ClientParticles.spawnParticles(ClientParticles.ParticleType.SONIC, getX() - Mth.sin(radians), getY() + 0.5, getZ() + Mth.cos(radians), -Mth.sin(radians) * 0.3, 0, Mth.cos(radians) * 0.3);
        }
    }
}
