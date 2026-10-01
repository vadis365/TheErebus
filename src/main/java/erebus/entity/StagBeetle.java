package erebus.entity;

import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class StagBeetle extends TamableAnimal implements PlayerRideable {
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(StagBeetle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> HEAD_POSITION = SynchedEntityData.defineId(StagBeetle.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> JAW_TICKS = SynchedEntityData.defineId(StagBeetle.class, EntityDataSerializers.INT);
    private float previousJawTicks;

    public StagBeetle(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 60).add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 4).add(Attributes.FOLLOW_RANGE, 16).add(Attributes.TEMPT_RANGE, 10)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75).add(Attributes.STEP_HEIGHT, 2);
    }

    public static boolean canSpawnHere(EntityType<StagBeetle> type, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && Mob.checkMobSpawnRules(type, level, reason, pos, random);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SADDLED, false);
        builder.define(HEAD_POSITION, 1);
        builder.define(JAW_TICKS, 0);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5, true));
        goalSelector.addGoal(2, new BreedGoal(this, 0.5));
        goalSelector.addGoal(3, new TemptGoal(this, 0.5, stack -> stack.is(ModItems.TURNIP), false));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.5));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true) {
            @Override
            public boolean canUse() {
                return !isTame() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !isTame() && super.canContinueToUse();
            }
        });
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(isTame() && target instanceof Player ? null : target);
    }

    @Override
    protected void applyTamingSideEffects() {
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(isTame() ? 80 : 60);
    }

    public boolean hasSaddle() {
        return entityData.get(SADDLED);
    }

    public int getJawTicks() {
        return entityData.get(JAW_TICKS);
    }

    public float getJawTicks(float partialTick) {
        return Mth.lerp(partialTick, previousJawTicks, getJawTicks());
    }

    public int getHeadPosition() {
        return entityData.get(HEAD_POSITION);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return isTame() && stack.is(ModItems.TURNIP);
    }

    @Override
    public boolean canMate(Animal partner) {
        return isTame() && partner instanceof StagBeetle beetle && beetle.isTame() && super.canMate(partner);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        var larva = ModEntities.BEETLE_LARVA.get().create(level, EntitySpawnReason.BREEDING);
        if (larva != null) {
            larva.setLarvaType((byte) 5);
            larva.setFutureOwner(getOwnerReference());
        }
        return larva;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        if (!isTame() && stack.is(ModItems.BEETLE_TAMING_AMULET)) {
            if (!level().isClientSide()) {
                tame(player);
                setTarget(null);
                getNavigation().stop();
                heal(20);
                stack.consume(1, player);
                level().broadcastEntityEvent(this, (byte) 7);
            }
            return InteractionResult.SUCCESS;
        }
        if (isTame() && stack.is(ModItems.BEETLE_RIDING_KIT) && !hasSaddle()) {
            if (!level().isClientSide()) {
                entityData.set(SADDLED, true);
                stack.consume(1, player);
            }
            return InteractionResult.SUCCESS;
        }
        if (isTame() && stack.is(ModItems.BAMBOO)) {
            if (getHealth() < getMaxHealth() && !level().isClientSide()) {
                heal(5);
                stack.consume(1, player);
                level().broadcastEntityEvent(this, (byte) 7);
                if (getHealth() == getMaxHealth()) playEatingSound();
            }
            return InteractionResult.SUCCESS;
        }
        if (stack.isEmpty() && hasSaddle() && isTame() && !isVehicle() && !isBaby()) {
            if (!level().isClientSide()) {
                setTarget(null);
                getNavigation().stop();
                player.startRiding(this);
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected void playEatingSound() {
        playSound(ModSounds.BEETLE_LARVA_MUNCH.get(), 1, 0.75F);
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        return isTame() && hasSaddle() && getFirstPassenger() instanceof Player player ? player : null;
    }

    @Override
    protected Vec3 getRiddenInput(Player player, Vec3 input) {
        float forward = player.zza * 0.3F;
        if (forward <= 0) forward *= 0.25F;
        return new Vec3(player.xxa * 0.3F, 0, forward);
    }

    @Override
    protected float getRiddenSpeed(Player player) {
        return (float) getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    @Override
    protected void tickRidden(Player player, Vec3 input) {
        setYRot(player.getYRot());
        yRotO = getYRot();
        setXRot(player.getXRot() * 0.5F);
        yBodyRot = getYRot();
        yHeadRot = yBodyRot;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float scale) {
        double yaw = Math.toRadians(yBodyRot);
        return new Vec3(Math.sin(yaw) * 0.75, 0.8, -Math.cos(yaw) * 0.75);
    }

    @Override
    public void tick() {
        previousJawTicks = getJawTicks();
        super.tick();
        if (!level().isClientSide() && getJawTicks() > 0) {
            int next = getJawTicks() + 1;
            entityData.set(JAW_TICKS, next >= 6 ? 0 : next);
            if (next >= 6) entityData.set(HEAD_POSITION, 1);
        }
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        return !(isTame() && target instanceof Player) && super.doHurtTarget(level, target);
    }

    @Override
    public boolean removeWhenFarAway(double distance) {
        return !isTame();
    }

    @Override
    public boolean canBeLeashed() {
        return isTame() && super.canBeLeashed();
    }

    public void startDigAnimation(double hitY, double riderY) {
        entityData.set(HEAD_POSITION, hitY > riderY + 2 ? 2 : hitY < riderY ? 0 : 1);
        entityData.set(JAW_TICKS, 1);
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this) && level.isUnobstructed(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 2;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.BOMBARDIER_BEETLE_SOUND.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.BOMBARDIER_BEETLE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1);
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean killedByPlayer) {
        super.dropCustomDeathLoot(level, source, killedByPlayer);
        if (hasSaddle()) spawnAtLocation(level, ModItems.BEETLE_RIDING_KIT.get());
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("HasSaddle", hasSaddle());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        entityData.set(SADDLED, input.getBooleanOr("HasSaddle", false));
        applyTamingSideEffects();
    }
}
