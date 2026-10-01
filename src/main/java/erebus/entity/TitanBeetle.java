package erebus.entity;

import erebus.client.particle.ClientParticles;
import erebus.inventory.container.TitanBeetleContainer;
import erebus.inventory.server.TitanBeetleMenu;
import erebus.registries.ModSounds;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TitanBeetle extends TamableAnimal implements PlayerRideable {
    private static final EntityDataAccessor<Boolean> OPEN = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_ENDER_CHEST = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_SADDLE = SynchedEntityData.defineId(TitanBeetle.class, EntityDataSerializers.BOOLEAN);
    private final TitanBeetleContainer inventory = new TitanBeetleContainer(this);
    private final Set<UUID> viewers = new HashSet<>();
    private ItemStack chest = ItemStack.EMPTY;
    private float openTicks, previousOpenTicks;

    public TitanBeetle(EntityType<? extends TitanBeetle> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 60).add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 4).add(Attributes.FOLLOW_RANGE, 16).add(Attributes.TEMPT_RANGE, 6)
                .add(Attributes.STEP_HEIGHT, 2).add(Attributes.KNOCKBACK_RESISTANCE, 0.75);
    }

    public static boolean canSpawnHere(EntityType<TitanBeetle> type, LevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, level, reason, pos, random);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder data) {
        super.defineSynchedData(data);
        data.define(OPEN, false);
        data.define(HAS_CHEST, false);
        data.define(HAS_ENDER_CHEST, false);
        data.define(HAS_SADDLE, false);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5, true));
        goalSelector.addGoal(2, new BreedGoal(this, 0.5));
        goalSelector.addGoal(3, new TemptGoal(this, 0.5, stack -> stack.is(ModItemTags.TITAN_BEETLE_FOOD), false));
        goalSelector.addGoal(4, new RandomStrollGoal(this, 0.5));
        goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6));
        goalSelector.addGoal(6, new RandomLookAroundGoal(this));
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
    protected void applyTamingSideEffects() {
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(isTame() ? 80 : 60);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(isTame() && target instanceof Player ? null : target);
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        return !(isTame() && target instanceof Player) && getSensing().hasLineOfSight(target) && super.doHurtTarget(level, target);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return isTame() && stack.is(ModItems.TURNIP);
    }

    @Override
    public boolean canMate(Animal partner) {
        return isTame() && partner instanceof TitanBeetle beetle && beetle.isTame() && super.canMate(partner);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        var larva = ModEntities.BEETLE_LARVA.get().create(level, EntitySpawnReason.BREEDING);
        if (larva != null) {
            larva.setLarvaType((byte) 3);
            larva.setFutureOwner(getOwnerReference());
        }
        return larva;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        if (player.isSecondaryUseActive() && (hasChest() || hasEnderChest())) {
            boolean ender = hasEnderChest();
            if (!canUseCargo(player, ender)) return InteractionResult.PASS;
            if (!level().isClientSide()) player.openMenu(new SimpleMenuProvider(
                    (id, playerInventory, user) -> new TitanBeetleMenu(id, playerInventory, this, ender),
                    Component.translatable(ender ? "container.enderchest" : "erebus.container.titan_beetle_chest")));
            return InteractionResult.SUCCESS;
        }
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
        if (isTame() && !hasSaddle() && stack.is(ModItems.BEETLE_RIDING_KIT)) {
            if (!level().isClientSide()) {
                setHasSaddle(true);
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
        boolean enderChest = stack.is(Blocks.ENDER_CHEST.asItem());
        if (isTame() && hasSaddle() && !hasChest() && !hasEnderChest() && (enderChest || stack.is(ModItemTags.TITAN_BEETLE_CHESTS))) {
            if (!level().isClientSide()) {
                chest = stack.copyWithCount(1);
                if (enderChest) setHasEnderChest(true);
                else setHasChest(true);
                stack.consume(1, player);
                playSound(SoundEvents.CHICKEN_EGG, 1, (random.nextFloat() - random.nextFloat()) * 0.2F + 1);
            }
            return InteractionResult.SUCCESS;
        }
        if (player.getMainHandItem().isEmpty() && player.getOffhandItem().isEmpty()
                && isTame() && hasSaddle() && isOwnedBy(player) && !isVehicle() && !isBaby()) {
            if (!level().isClientSide()) {
                closeMenus();
                setTarget(null);
                getNavigation().stop();
                player.startRiding(this);
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public TitanBeetleContainer getInventory() {
        return inventory;
    }

    public boolean canUseCargo(Player player, boolean ender) {
        return isAlive() && isTame() && !isVehicle() && player.level() == level() && distanceToSqr(player) <= 64
                && (ender ? hasEnderChest() : hasChest());
    }

    public void startViewing(Player player) {
        if (!level().isClientSide()) {
            viewers.add(player.getUUID());
            setOpen(true);
        }
    }

    public void stopViewing(Player player) {
        if (!level().isClientSide()) {
            viewers.remove(player.getUUID());
            setOpen(!viewers.isEmpty());
        }
    }

    public boolean isOpen() {
        return entityData.get(OPEN);
    }

    private void setOpen(boolean open) {
        if (isOpen() == open) return;
        entityData.set(OPEN, open);
        playSound(hasEnderChest() ? (open ? SoundEvents.ENDER_CHEST_OPEN : SoundEvents.ENDER_CHEST_CLOSE)
                : (open ? SoundEvents.CHEST_OPEN : SoundEvents.CHEST_CLOSE), 0.5F, 0.9F);
    }

    public float getOpenTicks() {
        return openTicks;
    }

    public float getPrevOpenTicks() {
        return previousOpenTicks;
    }

    public boolean hasChest() {
        return entityData.get(HAS_CHEST);
    }

    public boolean hasEnderChest() {
        return entityData.get(HAS_ENDER_CHEST);
    }

    public boolean hasSaddle() {
        return entityData.get(HAS_SADDLE);
    }

    public void setHasChest(boolean value) {
        entityData.set(HAS_CHEST, value);
        if (value) entityData.set(HAS_ENDER_CHEST, false);
    }

    public void setHasEnderChest(boolean value) {
        entityData.set(HAS_ENDER_CHEST, value);
        if (value) entityData.set(HAS_CHEST, false);
    }

    public void setHasSaddle(boolean value) {
        entityData.set(HAS_SADDLE, value);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            previousOpenTicks = openTicks;
            openTicks = Mth.clamp(openTicks + (isOpen() ? 0.1F : -0.1F), 0, 1);
            if (hasEnderChest()) {
                double yaw = Math.toRadians(yBodyRot);
                ClientParticles.enderChestParticles(level(), getX() + Math.sin(yaw) * 1.2, getY() + 1.2, getZ() - Math.cos(yaw) * 1.2);
            }
        } else if (!viewers.isEmpty() && level() instanceof ServerLevel level) {
            viewers.removeIf(id -> {
                var player = level.getServer().getPlayerList().getPlayer(id);
                return player == null || !(player.containerMenu instanceof TitanBeetleMenu menu) || !menu.isFor(this);
            });
            setOpen(!viewers.isEmpty());
        }
    }

    private void closeMenus() {
        if (level() instanceof ServerLevel level) for (var player : level.players())
            if (player.containerMenu instanceof TitanBeetleMenu menu && menu.isFor(this)) player.closeContainer();
        viewers.clear();
        if (!level().isClientSide()) setOpen(false);
    }

    @Override
    public void remove(RemovalReason reason) {
        closeMenus();
        super.remove(reason);
    }

    @Override
    protected void dropEquipment(ServerLevel level) {
        super.dropEquipment(level);
        closeMenus();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            var stack = inventory.removeItemNoUpdate(slot);
            if (!stack.isEmpty()) spawnAtLocation(level, stack);
        }
        if (hasSaddle()) spawnAtLocation(level, ModItems.BEETLE_RIDING_KIT.get());
        if (hasChest() || hasEnderChest()) spawnAtLocation(level, chest.isEmpty()
                ? new ItemStack(hasEnderChest() ? Blocks.ENDER_CHEST : Blocks.CHEST) : chest.copyWithCount(1));
        chest = ItemStack.EMPTY;
        setHasChest(false);
        setHasEnderChest(false);
        setHasSaddle(false);
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        return isTame() && hasSaddle() && getFirstPassenger() instanceof Player player && isOwnedBy(player) ? player : null;
    }

    @Override
    protected Vec3 getRiddenInput(Player player, Vec3 input) {
        float forward = player.zza * 0.4F;
        if (forward <= 0) forward *= 0.25F;
        return new Vec3(player.xxa * 0.4F, 0, forward);
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
        return new Vec3(Math.sin(yaw) * 0.1, 1.1, -Math.cos(yaw) * 0.1);
    }

    @Override
    public boolean canBeLeashed() {
        return true;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 2;
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
    protected void playEatingSound() {
        playSound(ModSounds.BEETLE_LARVA_MUNCH.get(), 1, 0.75F);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("HasChest", hasChest());
        output.putBoolean("HasEnderChest", hasEnderChest());
        output.putBoolean("HasSaddle", hasSaddle());
        if (!chest.isEmpty()) output.store("ChestItem", ItemStack.CODEC, chest.copyWithCount(1));
        inventory.storeSlots(output.list("inventory", ItemStackWithSlot.CODEC));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        setHasSaddle(input.getBooleanOr("HasSaddle", false));
        boolean normal = input.getBooleanOr("HasChest", false);
        setHasEnderChest(!normal && input.getBooleanOr("HasEnderChest", false));
        setHasChest(normal);
        chest = input.read("ChestItem", ItemStack.CODEC).orElse(ItemStack.EMPTY).copyWithCount(1);
        inventory.fromSlots(input.listOrEmpty("inventory", ItemStackWithSlot.CODEC));
        viewers.clear();
        entityData.set(OPEN, false);
        openTicks = 0;
        previousOpenTicks = 0;
        applyTamingSideEffects();
    }
}
