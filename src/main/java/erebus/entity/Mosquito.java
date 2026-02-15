package erebus.entity;

import erebus.entity.ai.FlyingWanderGoal;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import erebus.utils.AnimationMathHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class Mosquito extends Monster {

    private static final EntityDataAccessor<Byte> BLOOD = SynchedEntityData.defineId(Mosquito.class, EntityDataSerializers.BYTE);
    private static final int MAX_BLOOD_LEVEL = 5;

    private BlockPos currentFlightTarget;
    private final float heightOffset = 1.5F;
    private int drainage;
    private short consumptionTimer = 0;
    private LivingEntity target;
    private final AnimationMathHelper mathWings = new AnimationMathHelper();
    private final AnimationMathHelper mathSucking = new AnimationMathHelper();
    public float wingFloat;
    public float suckFloat;
    public boolean firstTickCheck;
    public int hitInterval = 30;
    private final List<EntityType<?>> prey = List.of(
            EntityType.PIG,
            EntityType.COW,
            ModEntities.BEETLE_LARVA.get()
    );

    public Mosquito(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        wingFloat = 0.0F;
        suckFloat = 1.0F;
        firstTickCheck = false;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FlyingWanderGoal(this, 0.8F, 0.02F));
    }

    @Override
    public void tick() {
        if(!level().isClientSide()) {
            if(getVehicle() != null) {
                suckFloat = 1.0F + mathSucking.swing(1.0F, 0.15F);
                if(random.nextInt(10) == 0) {
                    for(int c = 0; c < 8; c++) {
                        level().addParticle(DustParticleOptions.REDSTONE, getX() + (random.nextFloat() - random.nextFloat()), getY() + random.nextFloat() + 1D, getZ() + (random.nextFloat() - random.nextFloat()), 0, 0, 0);
                    }
                }

                wingFloat = 0.0F;
            } else {
                suckFloat = 1.0F;
                wingFloat = mathWings.swing(1.0F, 0.15F);
            }
        } else {
            if(getDeltaMovement().y < 0.0D) {
                setDeltaMovement(getDeltaMovement().multiply(1, 0.8, 1));
            }

            if(!firstTickCheck) {
                firstTickCheck = true;
            }

            if(consumptionTimer > 0) {
                if(--consumptionTimer == 0) {
                    setBloodConsumed(0);
                }
            }

            if(findPlayerToAttack() != null && getBloodConsumed() < MAX_BLOOD_LEVEL) {
                target = findPlayerToAttack();
            } else if(findEnemyToAttack() != null && getBloodConsumed() < MAX_BLOOD_LEVEL) {
                target = findEnemyToAttack();
            } else {
                target = null;
            }

            if(target != null && getVehicle() != null && distanceTo(target) <= 1.2D && getBloodConsumed() < MAX_BLOOD_LEVEL) {
                startRiding(target);
            }

            if (getVehicle() instanceof LivingEntity && getBloodConsumed() >= MAX_BLOOD_LEVEL && consumptionTimer > 0) {
                stopRiding();
                target = null;
            }

            if(isInWater()) {
                setDeltaMovement(getDeltaMovement().multiply(1, 0.08, 1));
            }
        }

        super.tick();
    }

    @Override
    public boolean hurtServer(@NonNull ServerLevel level, @NonNull DamageSource source, float damage) {
        if (isInvulnerable())
            return false;
        else if (super.hurtServer(level, source, damage)) {
            Entity attacker = source.getDirectEntity();
            if (level().isClientSide() && attacker instanceof LivingEntity && getVehicle() != attacker) {
                if (attacker != this)
                    target = (LivingEntity) attacker;
                return true;
            } else if (level().isClientSide() && attacker instanceof LivingEntity && getVehicle() == attacker) {
                stopRiding();
                setDeltaMovement(getDeltaMovement().add(0, 0.5, 0));
                return true;
            } else
                return true;
        } else
            return false;
    }

    protected Player findPlayerToAttack() {
        Player player = level().getNearestPlayer(this, 10.0D);
        return player != null && getSensing().hasLineOfSight(player) && !player.isCreative() ? player : null;
    }

    protected LivingEntity findEnemyToAttack() {
        final LivingEntity[] entity = {null};
        level().getEntities(this, getBoundingBox().inflate(10, 10, 10), (e) -> prey.contains(e.getType()))
                .forEach(e -> {
                    if (!hasExactlyOnePlayerPassenger() && getSensing().hasLineOfSight(e)) {
                        entity[0] = (LivingEntity) e;
                    }
                });

        return entity[0];
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 15F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(BLOOD, (byte) 0);
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putByte("BloodLevel", (byte) getBloodConsumed());
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        setBloodConsumed(input.getByteOr("BloodLevel", (byte) 0));
    }

    public int getBloodConsumed() {
        return entityData.get(BLOOD);
    }

    public void setBloodConsumed(int blood) {
        entityData.set(BLOOD, (byte) blood);
        consumptionTimer = 1200;
    }

    @Override
    public float getVoicePitch() {
        return 1.0F;
    }

    @Override
    protected float getSoundVolume() {
        return 0.1F;
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return ModSounds.MOSQUITO_DEATH.get();
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.MOSQUITO_HIT.get();
    }

    @Override
    public void playAmbientSound() {
        playSound(getVehicle() != null ? ModSounds.MOSQUITO_SUCKING.get() : ModSounds.MOSQUITO_FLYING.get());
    }
}
