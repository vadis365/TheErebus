package erebus.entity;

import erebus.registries.ModSounds;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class Leech extends Monster {

    public static final EntityDataAccessor<Integer> BLOOD_CONSUMED = SynchedEntityData.defineId(Leech.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> PLAYER_HOST = SynchedEntityData.defineId(Leech.class, EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
    private final static int MAX_BLOOD_LEVEL = 5;
    public int attackCooldown = 60;
    public int hungerCooldown = 0;
    private int drainage;

    public Leech(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        drainage = 0;
        clearCrop();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.STEP_HEIGHT, 0.0F);
    }

    public static boolean canSpawnHere(EntityType<Leech> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
        float light = level.getLightLevelDependentMagicValue(pos);
        return light >= 0F;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 0.6D));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        goalSelector.addGoal(5, new MeleeAttackGoal(this, 0.5D, false));
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.SNAIL_LIVING.get();
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.SNAIL_HURT.get();
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return ModSounds.SNAIL_DEATH.get();
    }

    @Override
    public void playerTouch(@NonNull Player player) {
        super.playerTouch(player);
        if (!level().isClientSide() && isAlive() && player.isAlive() && !player.isCreative() && !player.isSpectator()
                && !isFeeding() && getBloodConsumed() == 0) {
            entityData.set(PLAYER_HOST, Optional.of(EntityReference.of(player)));
            getNavigation().stop();
            drainage = 0;
        }
    }

    @Override
    public void tick() {
        super.tick();
        Entity host = getFeedingHost();
        if (host instanceof Player player && player.isAlive() && player.level() == level()) {
            getNavigation().stop();
            setDeltaMovement(Vec3.ZERO);
            setPos(player.getX(), player.getY() + player.getBbHeight() * 0.45, player.getZ());
            setXRot(player.getXRot());
            setYRot(player.getYRot());
            yBodyRot = player.getYRot();
        }
        if (level().isClientSide()) {
            if (isFeeding() && random.nextInt(10) == 0) {
                for (int c = 0; c < 8; c++) {
                    level().addParticle(DustParticleOptions.REDSTONE, getX() + (random.nextFloat() - random.nextFloat()),
                            getY() + random.nextFloat(), getZ() + (random.nextFloat() - random.nextFloat()), 0, 0, 0);
                }
            }
            return;
        }

        if (hungerCooldown > 0 && --hungerCooldown == 0) clearCrop();
        if (host == null) {
            detachHost();
            drainage = 0;
            return;
        }
        if (!host.isAlive() || host.level() != level() || host instanceof Player player && (player.isCreative() || player.isSpectator())) {
            detachHost();
            drainage = 0;
            return;
        }
        if (getBloodConsumed() < MAX_BLOOD_LEVEL && ++drainage >= Math.max(1, attackCooldown)) {
            drainage = 0;
            if (host.hurtServer((ServerLevel) level(), damageSources().mobAttack(this), 1.0F)) consumeBlood();
        }
        if (getBloodConsumed() >= MAX_BLOOD_LEVEL) detachHost();
    }

    @Override
    public void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("blood_consumed", getBloodConsumed());
        output.putInt("hunger_cooldown", hungerCooldown);
        output.putInt("drainage", drainage);
        EntityReference.store(entityData.get(PLAYER_HOST).orElse(null), output, "PlayerHost");
    }

    @Override
    public void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        entityData.set(PLAYER_HOST, Optional.ofNullable(EntityReference.readWithOldOwnerConversion(input, "PlayerHost", level())));
        entityData.set(BLOOD_CONSUMED, Math.clamp(input.getIntOr("blood_consumed", 0), 0, MAX_BLOOD_LEVEL));
        hungerCooldown = Math.clamp(input.getIntOr("hunger_cooldown", 1000), 1, 1000);
        drainage = Math.clamp(input.getIntOr("drainage", 0), 0, Math.max(1, attackCooldown) - 1);
    }

    @Override
    public double getEyeY() {
        if (isFeeding() && getFeedingHost() instanceof Player player) {
            return player.getEyeY();
        }
        return super.getEyeY();
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, DamageSource source, float damage) {
        if (source.is(DamageTypes.IN_WALL)) {
            return false;
        }
        return super.hurtServer(level, source, damage);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BLOOD_CONSUMED, 0);
        builder.define(PLAYER_HOST, Optional.empty());
    }

    @Override
    protected void dropFromLootTable(@NonNull ServerLevel level, @NonNull DamageSource source, boolean playerKilled) {
        if (playerKilled) {
            drop(new ItemStack(ModItems.LIFE_BLOOD.get(), getBloodConsumed() + 1), true, false);
        }
        super.dropFromLootTable(level, source, playerKilled);
    }

    public void clearCrop() {
        resetHunger();
        entityData.set(BLOOD_CONSUMED, 0);
    }

    public void consumeBlood() {
        resetHunger();
        entityData.set(BLOOD_CONSUMED, Math.min(MAX_BLOOD_LEVEL, getBloodConsumed() + 1));
    }

    public int getBloodConsumed() {
        return entityData.get(BLOOD_CONSUMED);
    }

    private void resetHunger() {
        hungerCooldown = 1000;
    }

    public @Nullable Entity getFeedingHost() {
        return entityData.get(PLAYER_HOST).map(reference -> reference.getEntity(level(), LivingEntity.class)).orElse(null) instanceof Player player
                ? player : getVehicle();
    }

    public void detachHost() {
        entityData.set(PLAYER_HOST, Optional.empty());
        stopRiding();
    }

    public boolean isFeeding() {
        return getFeedingHost() != null;
    }
}
