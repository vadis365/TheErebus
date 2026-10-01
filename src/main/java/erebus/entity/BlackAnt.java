package erebus.entity;

import erebus.block.entity.SiloTankBlockEntity;
import erebus.entity.ai.BlackAntBonemealCrops;
import erebus.entity.ai.BlackAntHarvestCrops;
import erebus.entity.ai.BlackAntPlantCrops;
import erebus.inventory.container.BlackAntSimpleContainer;
import erebus.inventory.server.BlackAntMenu;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.RangedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.VanillaContainerWrapper;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BlackAnt extends Animal implements HasCustomInventoryScreen, MenuProvider {
    public static final int TOOL_SLOT = 0;
    public static final int CROP_ID_SLOT = 1;
    public static final int INVENTORY_SLOT = 2;
    private static final EntityDataAccessor<BlockPos> DROP_POINT = SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> TAME_STATE = SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> ANT_ROLE = SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BYTE);
    private static final String[] names = {"Antwan", "George", "Geoff", "Alberto", "Jose", "Linda", "Chantelle", "Dave", "Basil", "Gertrude", "Herbert", "Russel", "Adam", "Gwen", "Billy Bob Joe Bob Joe Harrison Jr.", "Sid", "Dylan", "Jade"};
    public final BlackAntSimpleContainer inventory;
    private final ResourceHandler<ItemResource> cargoHandler;
    public boolean canPickupItems;
    public boolean canCollectFromSilo;
    public boolean canAddToSilo;
    public byte NONE = 0;
    public byte PLANTER = 1;
    public byte HARVESTER = 2;
    public byte COLLECTOR = 3;
    public byte FERTILIZER = 4;
    private UUID playerOwner = null;

    public BlackAnt(EntityType<? extends BlackAnt> type, Level level) {
        super(type, level);
        this.inventory = new BlackAntSimpleContainer(3) {
            @Override
            public void setChanged() {
                super.setChanged();
                if (BlackAnt.this.inventory != null) syncInventoryToFlags();
            }
        };
        cargoHandler = RangedResourceHandler.ofSingleIndex(VanillaContainerWrapper.of(inventory), INVENTORY_SLOT);
        canPickupItems = false;
        canAddToSilo = false;
        canCollectFromSilo = false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.TEMPT_RANGE, 16.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }

    public static boolean canSpawnHere(EntityType<BlackAnt> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
        float light = level.getLightLevelDependentMagicValue(pos);
        return light >= 0F;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DROP_POINT, this.blockPosition());
        builder.define(TAME_STATE, false);
        builder.define(ANT_ROLE, NONE);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new BlackAntPlantCrops(this, 0.6D, 4, false));
        goalSelector.addGoal(1, new BlackAntBonemealCrops(this, 0.6D, 4, false));
        goalSelector.addGoal(1, new BlackAntHarvestCrops(this, 0.6D, 4, true));
        //goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        goalSelector.addGoal(3, new PanicGoal(this, 0.6D));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        goalSelector.addGoal(4, new TemptGoal(this, 0.5D, item -> item.is(ModItems.ANT_TAMING_AMULET.get()), false));
        goalSelector.addGoal(5, new TemptGoal(this, 0.5D, item -> item.is(Items.SUGAR), false));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ANT_SOUND.get();
    }

    @Override
    protected SoundEvent getHurtSound(@NonNull DamageSource source) {
        return ModSounds.ANT_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 5;
    }

    @Override
    public boolean isPersistenceRequired() {
        return isTamedAnt() || super.isPersistenceRequired();
    }

    @Override
    public boolean removeWhenFarAway(double distance) {
        return !isTamedAnt();
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
    }

    public boolean isTamedAnt() {
        return entityData.get(TAME_STATE);
    }

    public void setTameState(boolean state) {
        entityData.set(TAME_STATE, state);
        if (inventory != null) syncInventoryToFlags();
        if (state && !hasCustomName())
            setCustomName(Component.literal(names[random.nextInt(names.length)]));
    }

    @Override
    public @NonNull InteractionResult mobInteract(Player player, @NonNull InteractionHand hand) {
        ItemStack is = player.getItemInHand(hand);

        if (!is.isEmpty() && is.getItem() == ModItems.ANT_TAMING_AMULET.get() && is.has(ModDataComponents.ANT_TAMING_AMULET)) {
            if (!level().isClientSide()) {
                BlockPos dataBlockPos = is.getComponents().get(ModDataComponents.ANT_TAMING_AMULET.get());
                setDropPoint(dataBlockPos);
                setTameState(true);
                setPlayerOwner(player);
            }
            level().broadcastEntityEvent(this, (byte) 18);
            player.swing(hand);
            return InteractionResult.SUCCESS;
        }
        if (isTamedAnt()) {
            openCustomInventoryScreen(player);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public boolean isPushable() {
        return getAntRole() != PLANTER && getAntRole() != FERTILIZER;
    }

    public BlockPos getDropPoint() {
        return entityData.get(DROP_POINT);
    }

    public void setDropPoint(BlockPos pos) {
        entityData.set(DROP_POINT, pos.immutable());
    }

    public byte getAntRole() {
        return entityData.get(ANT_ROLE);
    }

    private void setAntRole(byte role) {
        entityData.set(ANT_ROLE, role);
    }

    public UUID getPlayerOwner() {
        return playerOwner;
    }

    public void setPlayerOwner(Player player) {
        playerOwner = player.getUUID();
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    public boolean isFood(@NonNull ItemStack stack) {
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(@NonNull ServerLevel level, @NonNull AgeableMob otherParent) {
        return null;
    }

    @Override
    protected void customServerAiStep(@NonNull ServerLevel level) {
        super.customServerAiStep(level);

        // Don't pick up items unless the filter is defined and the inventory is not full
        if (isTamedAnt()) {
            if (canPickupItems && !isFilterSlotEmpty() && (getAntInvSlotStack().isEmpty() || getAntInvSlotStack().getCount() < getAntInvSlotStack().getMaxStackSize())) {
                ItemEntity entityItem = getClosestEntityItem(this, 16.0D, getFilterSlotStack());
                if (entityItem != null) {
                    float distance = entityItem.distanceTo(this);
                    if (distance >= 2F && entityItem.isAlive()) {
                        double x = entityItem.getX();
                        double y = entityItem.getY();
                        double z = entityItem.getZ();
                        getLookControl().setLookAt(x, y, z, 20.0F, 8.0F);
                        moveToItem(entityItem);
                        return;
                    }
                    if (distance < 2F) {
                        getMoveControl().setWantedPosition(entityItem.getX(), entityItem.getY(), entityItem.getZ(), 0.5D);
                        addToInventory(entityItem.getItem());
                        if (entityItem.getItem().getCount() <= 0)
                            entityItem.remove(RemovalReason.DISCARDED);
                        return;
                    }
                }
            }

            if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof BucketItem)
                if (!isAntInvSlotEmpty() && getAntInvSlotStack().getCount() > 15) {
                    canAddToSilo = true;
                    canPickupItems = false;
                }

            if (!canPickupItems && canAddToSilo) {
                moveToSilo();

                Block block = level().getBlockState(getDropPoint()).getBlock();
                if (block == ModBlocks.SILO_TANK.get())
                    if (getDistance(getDropPoint().getX() + 0.5D, getDropPoint().getY() - 1D, getDropPoint().getZ() + 0.5D) < 2D) {
                        addDropToInventory(getDropPoint());
                        if (isAntInvSlotEmpty()) {
                            canAddToSilo = false;
                            canPickupItems = true;
                        }
                    }
            }

            if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof HoeItem || !isTaskSlotEmpty() && getTaskSlotStack().getItem() == Items.BONE)
                if (isAntInvSlotEmpty() && !isFilterSlotEmpty())
                    canCollectFromSilo = true; // this stops the planting or bonemealing AIs and makes the ant go to the silo

            if (canCollectFromSilo) {
                moveToSilo();
                Block block = level().getBlockState(getDropPoint()).getBlock();
                if (block == ModBlocks.SILO_TANK.get())
                    if (getDistance(getDropPoint().getX() + 0.5D, getDropPoint().getY() - 1D, getDropPoint().getZ() + 0.5D) < 2D) {
                        getStackFromSilo();
                        canCollectFromSilo = false;
                    }
            }
        }
    }

    public void moveToItem(Entity entity) {
        getNavigation().moveTo(entity, 0.5D);
    }

    public void moveToSilo() {
        getNavigation().moveTo(getDropPoint().getX() + 0.5D, getDropPoint().getY() - 1D, getDropPoint().getZ() + 0.5D, 0.5D);
    }

    public double getDistance(double x, double y, double z) {
        double d0 = this.getX() - x;
        double d1 = this.getY() - y;
        double d2 = this.getZ() - z;
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    public ItemEntity getClosestEntityItem(final Entity entity, double d, ItemStack filter) {
        List<ItemEntity> list = level().getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(d, d, d), EntitySelector.ENTITY_STILL_ALIVE);
        if (list.isEmpty())
            return null;

        list.removeIf(item -> !ItemStack.isSameItem(filter, item.getItem()));
        if (!isAntInvSlotEmpty()) list.removeIf(item -> !ItemStack.isSameItemSameComponents(getAntInvSlotStack(), item.getItem()));

        if (list.isEmpty())
            return null;

        list.sort(Comparator.comparingDouble(e -> e.distanceToSqr(entity)));

        return list.getFirst();
    }

    @Override
    public void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("tameState", isTamedAnt());
        output.putByte("antRole", getAntRole());
        output.store("dropPoint", BlockPos.CODEC, getDropPoint());
        output.putBoolean("returningToSilo", canAddToSilo);

        if (!this.inventory.getItem(TOOL_SLOT).isEmpty())
            output.store("toolSlot", ItemStack.CODEC, this.inventory.getItem(TOOL_SLOT));
        if (!this.inventory.getItem(CROP_ID_SLOT).isEmpty())
            output.store("cropIdSlot", ItemStack.CODEC, this.inventory.getItem(CROP_ID_SLOT));
        if (!this.inventory.getItem(INVENTORY_SLOT).isEmpty())
            output.store("inventorySlot", ItemStack.CODEC, this.inventory.getItem(INVENTORY_SLOT));

        if (playerOwner != null) output.putString("playerOwner", playerOwner.toString());
    }

    @Override
    public void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        setTameState(input.getBooleanOr("tameState", false));
        setAntRole(NONE);
        Optional<BlockPos> dropPoint = input.read("dropPoint", BlockPos.CODEC);
        setDropPoint(dropPoint.orElse(BlockPos.ZERO));

        ItemStack toolSlot = input.read("toolSlot", ItemStack.CODEC).orElse(ItemStack.EMPTY);
        ItemStack cropIdSlot = input.read("cropIdSlot", ItemStack.CODEC).orElse(ItemStack.EMPTY);
        ItemStack inventorySlot = input.read("inventorySlot", ItemStack.CODEC).orElse(ItemStack.EMPTY);

        inventory.setItem(TOOL_SLOT, toolSlot);
        inventory.setItem(CROP_ID_SLOT, cropIdSlot.copyWithCount(1));
        inventory.setItem(INVENTORY_SLOT, inventorySlot);

        playerOwner = input.getString("playerOwner").map(UUID::fromString).orElse(null);
        syncInventoryToFlags();
        // A partial deposit can leave fewer than the normal sixteen-item collection threshold.
        // Preserve that delivery rather than waiting for more loose items after a reload.
        if (getAntRole() == COLLECTOR && !isAntInvSlotEmpty() && input.getBooleanOr("returningToSilo", false)) {
            canAddToSilo = true;
            canPickupItems = false;
        }
    }

    public ResourceHandler<ItemResource> getCargoHandler() {
        return cargoHandler;
    }

    private void addToInventory(ItemStack stack) {
        if (stack.isEmpty()) return;
        try (var transaction = Transaction.openRoot()) {
            int inserted = cargoHandler.insert(ItemResource.of(stack), stack.getCount(), transaction);
            transaction.commit();
            stack.shrink(inserted);
        }
    }

    private void addDropToInventory(BlockPos pos) {
        if (isAntInvSlotEmpty()) return;
        var silo = level().getCapability(Capabilities.Item.BLOCK, pos, Direction.UP);
        if (silo == null) return;
        try (var transaction = Transaction.openRoot()) {
            ResourceHandlerUtil.moveStacking(cargoHandler, silo, resource -> true, getAntInvSlotStack().getCount(), transaction);
            transaction.commit();
        }
    }

    private void getStackFromSilo() {
        if (!isAntInvSlotEmpty() || isFilterSlotEmpty() || !(level().getBlockEntity(getDropPoint()) instanceof SiloTankBlockEntity)) return;
        var silo = level().getCapability(Capabilities.Item.BLOCK, getDropPoint(), Direction.UP);
        if (silo == null) return;
        var item = getFilterSlotStack().getItem();
        try (var transaction = Transaction.openRoot()) {
            ResourceHandlerUtil.moveStacking(silo, cargoHandler, resource -> resource.is(item), 64, transaction);
            transaction.commit();
        }
    }

    public void syncInventoryToFlags() {
        if (level().isClientSide()) return;
        var tool = getTaskSlotStack().getItem();
        byte role = !isTamedAnt() ? NONE : tool instanceof HoeItem ? PLANTER : tool instanceof BucketItem ? COLLECTOR
                : tool instanceof ShearsItem ? HARVESTER : tool == Items.BONE ? FERTILIZER : NONE;
        if (role != getAntRole()) {
            setAntRole(role);
            canPickupItems = role == COLLECTOR;
            canAddToSilo = false;
            canCollectFromSilo = false;
        }
    }

    @Override
    protected void dropEquipment(ServerLevel level) {
        super.dropEquipment(level);
        for (var player : level.players()) if (player.containerMenu instanceof BlackAntMenu menu && menu.isFor(this)) player.closeContainer();
        for (int slot : new int[]{TOOL_SLOT, INVENTORY_SLOT}) {
            var stack = inventory.removeItemNoUpdate(slot);
            if (!stack.isEmpty()) spawnAtLocation(level, stack);
        }
        inventory.clearContent();
    }

    public int getInventorySize() {
        return 3;
    }

    public BlackAntSimpleContainer getInventory() {
        return this.inventory;
    }

    public boolean isTaskSlotEmpty() {
        return getTaskSlotStack().isEmpty();
    }

    public ItemStack getTaskSlotStack() {
        return inventory.getItem(TOOL_SLOT);
    }

    public boolean isFilterSlotEmpty() {
        return getFilterSlotStack().isEmpty();
    }

    public ItemStack getFilterSlotStack() {
        return inventory.getItem(CROP_ID_SLOT);
    }

    public boolean isAntInvSlotEmpty() {
        return getAntInvSlotStack().isEmpty();
    }

    public ItemStack getAntInvSlotStack() {
        return inventory.getItem(INVENTORY_SLOT);
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, @NonNull Inventory playerInventory, @NonNull Player player) {
        return new BlackAntMenu(containerId, playerInventory, this);
    }

    @Override
    public void openCustomInventoryScreen(@NonNull Player player) {
        if (!level().isClientSide()) {
            player.openMenu(this);
        }
    }
}
