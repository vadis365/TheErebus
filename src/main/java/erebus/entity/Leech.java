package erebus.entity;

import erebus.registries.ModSounds;
import erebus.registries.item.ModItems;
import erebus.utils.AnimationMathHelper;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class Leech extends Monster {

    public static final EntityDataAccessor<Integer> BLOOD_CONSUMED = SynchedEntityData.defineId(Leech.class, EntityDataSerializers.INT);
    private final static int MAX_BLOOD_LEVEL = 5;
    public int attackCooldown = 60;
    public int hungerCooldown = 0;
    private int drainage;
    AnimationMathHelper mathSucking = new AnimationMathHelper();

    public Leech(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        drainage = 0;
        clearCrop();
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
        if(level().isClientSide() && !player.isCreative()) {
            if(!player.hasPassenger(this) && getBloodConsumed() <= 0) {
                startRiding(player, true, true);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(level().isClientSide()) {
            if(isFeeding() && getVehicle() instanceof Player player) {
                setXRot(player.getXRot());
                setYRot(player.getYRot());
            }
        }

        if(hungerCooldown > 0) {
            if(--hungerCooldown == 0) {
                clearCrop();
            }
        }

        if(isFeeding()) {
            if(random.nextInt(10) == 0) {
                for(int c = 0; c < 8; c++) {
                    level().addParticle(DustParticleOptions.REDSTONE, getX() + (random.nextFloat() - random.nextFloat()), getY() + random.nextFloat(), getZ() + (random.nextFloat() - random.nextFloat()), 0, 0, 0);
                }
            }
        }

        if(isFeeding() && getVehicle() instanceof Entity entity && getBloodConsumed() < MAX_BLOOD_LEVEL) {
            drainage++;
            if(drainage >= attackCooldown) {
                if(level() instanceof ServerLevel serverLevel) {
                    entity.hurtServer(serverLevel, damageSources().mobAttack(this), 1.0F);
                    drainage = 0;
                    consumeBlood();
                }
            }
        }

        if(getBloodConsumed() == MAX_BLOOD_LEVEL && isFeeding()) {
            dismountTo(getX(), getY(), getZ());
        }
    }

    @Override
    public double getEyeY() {
        if(isFeeding() && getVehicle() instanceof Player player) {
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
    }

    @Override
    protected void dropFromLootTable(@NonNull ServerLevel level, @NonNull DamageSource source, boolean playerKilled) {
        if(wasRecentlyStabbed(getLastAttacker(), 100)) {
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
        entityData.set(BLOOD_CONSUMED, getBloodConsumed() + 1);
    }

    public int getBloodConsumed() {
        return entityData.get(BLOOD_CONSUMED);
    }

    private void resetHunger() {
        hungerCooldown = 1000;
    }

    public boolean isFeeding() {
        return getVehicle() != null;
    }
}
