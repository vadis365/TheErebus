package erebus.entity;

import erebus.block.entity.SiloTankBlockEntity;
import erebus.entity.ai.BlackAntBonemealCrops;
import erebus.entity.ai.BlackAntHarvestCrops;
import erebus.entity.ai.BlackAntPlantCrops;
import erebus.inventory.server.BlackAntMenu;
import erebus.inventory.server.BlackAntSimpleContainer;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BlackAnt extends Animal implements HasCustomInventoryScreen, MenuProvider {
	private static final EntityDataAccessor<BlockPos> DROP_POINT = SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Boolean> TAME_STATE = SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> ANT_ROLE = SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BYTE);
	private UUID playerOwner = null;
//	public EntityAIPanic aiPanic;
//	public EntityAIAntHarvestCrops aiHarvestCrops;
//	public EntityAIAntBonemealCrops aiBonemealCrops;
//	public EntityAIWander aiWander;

	//	public boolean setAttributes;
	public boolean canPickupItems;
	public boolean canCollectFromSilo;
	public boolean canAddToSilo;
	public BlackAntSimpleContainer inventory;
	public static final int TOOL_SLOT = 0;
	public static final int CROP_ID_SLOT = 1;
	public static final int INVENTORY_SLOT = 2;

	public byte NONE = 0;
	public byte PLANTER = 1;
	public byte HARVESTER = 2;
	public byte COLLECTOR = 3;
	public byte FERTILIZER = 4;

	public BlackAnt(EntityType<? extends BlackAnt> type, Level level) {
		super(type, level);
		this.inventory = new BlackAntSimpleContainer(3);
		updateInventory();
		canPickupItems = false;
		canAddToSilo = false;
		canCollectFromSilo = false;
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

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 15D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.FOLLOW_RANGE, 16.0D);
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
		return isTamedAnt();
	}

	public static boolean canSpawnHere(EntityType<BlackAnt> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	public boolean isTamedAnt() {
		return entityData.get(TAME_STATE);
	}

	private static final String[] names = {"Antwan", "George", "Geoff", "Alberto", "Jose", "Linda", "Chantelle", "Dave", "Basil", "Gertrude", "Herbert", "Russel", "Adam", "Gwen", "Billy Bob Joe Bob Joe Harrison Jr.", "Sid", "Dylan", "Jade"};

	public void setTameState(boolean state) {
		entityData.set(TAME_STATE, state);
		if (!hasCustomName())
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

	public void setDropPoint(BlockPos pos) {
		entityData.set(DROP_POINT, pos);
	}

	public BlockPos getDropPoint() {
		return entityData.get(DROP_POINT);
	}

	private void setAntRole(byte role) {
		entityData.set(ANT_ROLE, role);
	}

	public byte getAntRole() {
		return entityData.get(ANT_ROLE);
	}

	public void setPlayerOwner(Player player) {
		playerOwner = player.getUUID();
	}

	public UUID getPlayerOwner() {
		return playerOwner;
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
		getMoveControl().setWantedPosition(entity.getX(), entity.getY(), entity.getZ(), 0.5D);
	}

	public void moveToSilo() {
		getMoveControl().setWantedPosition(getDropPoint().getX() + 0.5D, getDropPoint().getY() - 1D, getDropPoint().getZ() + 0.5D, 0.5D);
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

		// TODO handling individual slots rather than iterating here, may change later
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
		setAntRole(input.getByteOr("antRole", (byte) 0));
		Optional<BlockPos> dropPoint = input.read("dropPoint", BlockPos.CODEC);
		setDropPoint(dropPoint.orElse(BlockPos.ZERO));

		ItemStack toolSlot = input.read("toolSlot", ItemStack.CODEC).orElse(ItemStack.EMPTY);
		ItemStack cropIdSlot = input.read("cropIdSlot", ItemStack.CODEC).orElse(ItemStack.EMPTY);
		ItemStack inventorySlot = input.read("inventorySlot", ItemStack.CODEC).orElse(ItemStack.EMPTY);

		inventory.setItem(TOOL_SLOT, toolSlot);
		inventory.setItem(CROP_ID_SLOT, cropIdSlot);
		inventory.setItem(INVENTORY_SLOT, inventorySlot);

		playerOwner = input.getString("playerOwner").map(UUID::fromString).orElse(null);
	}

	// INVENTORY SHIT

	private void addToInventory(ItemStack stack) {
		if (stack.isEmpty())
			return;
		if (inventory.getItem(INVENTORY_SLOT).isEmpty()) {
			inventory.setItem(INVENTORY_SLOT, stack.copy());
			stack.setCount(0);
		} else if (ItemStack.isSameItem(stack, inventory.getItem(INVENTORY_SLOT))) {
			int old = inventory.getItem(INVENTORY_SLOT).getCount();
			inventory.getItem(INVENTORY_SLOT).setCount(stack.getCount() + old);
			if (inventory.getItem(INVENTORY_SLOT).getCount() > inventory.getItem(INVENTORY_SLOT).getMaxStackSize())
				inventory.getItem(INVENTORY_SLOT).setCount(inventory.getItem(INVENTORY_SLOT).getMaxStackSize());

			int added = inventory.getItem(INVENTORY_SLOT).getCount() - old;
			stack.shrink(added);
		}
	}

	private void addDropToInventory(BlockPos pos) {
		BlockEntity tile = level().getBlockEntity(pos);
		ItemStack stack = getAntInvSlotStack();
		ResourceHandler<ItemResource> silo = level().getCapability(Capabilities.Item.BLOCK, getDropPoint(), Direction.UP);
		ResourceHandler<ItemResource> ant = getCapability(Capabilities.Item.ENTITY_AUTOMATION, Direction.DOWN);
		if (!isAntInvSlotEmpty()) {
			try(Transaction transaction = Transaction.openRoot()) {
				int amountInserted = ResourceHandlerUtil.moveStacking(ant, silo, filter -> filter.is(stack.getItem()), stack.getCount(), transaction);
				if(amountInserted > 0) {
					inventory.setItem(INVENTORY_SLOT, new ItemStack(stack.getItem(), stack.getCount() - amountInserted));
					transaction.commit();
				} else {
					transaction.close();
				}
			}
		}
	}

	private void getStackFromSilo() {
		BlockEntity tile = level().getBlockEntity(getDropPoint());
		ResourceHandler<ItemResource> silo = level().getCapability(Capabilities.Item.BLOCK, getDropPoint(), Direction.UP);
		ResourceHandler<ItemResource> ant = getCapability(Capabilities.Item.ENTITY_AUTOMATION, Direction.DOWN);

		if(tile instanceof SiloTankBlockEntity) {
			try(Transaction transaction = Transaction.openRoot()) {
				int cropCollected = ResourceHandlerUtil.moveStacking(silo, ant, filter -> filter.is(inventory.getItem(CROP_ID_SLOT).getItem()), 64, transaction);
				if(isAntInvSlotEmpty()) {
					inventory.setItem(INVENTORY_SLOT, new ItemStack(inventory.getItem(CROP_ID_SLOT).getItem(), cropCollected));
					transaction.commit();
				} else {
					transaction.close();
				}
			}
		}
	}

	protected void updateInventory() {
		BlackAntSimpleContainer previousInventory = this.inventory;
		this.inventory = new BlackAntSimpleContainer(this.getInventorySize());
		if (previousInventory != null) {
			int maxSize = Math.min(previousInventory.getContainerSize(), this.inventory.getContainerSize());

			for (int slot = 0; slot < maxSize; ++slot) {
				ItemStack stack = previousInventory.getItem(slot);
				if (!stack.isEmpty()) {
					this.inventory.setItem(slot, stack.copy());
				}
			}
		}

		this.syncInventoryToFlags();
	}

	public void syncInventoryToFlags() {
		if (!this.level().isClientSide()) {
			canPickupItems = false;
			canAddToSilo = false;
			canCollectFromSilo = false;

			if (isTaskSlotEmpty() && isTamedAnt())
				entityData.set(ANT_ROLE, NONE);

			if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof HoeItem)
				entityData.set(ANT_ROLE, PLANTER);

			if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof BucketItem) {
				canPickupItems = true;
				entityData.set(ANT_ROLE, COLLECTOR);
			}

			if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof ShearsItem)
				entityData.set(ANT_ROLE, HARVESTER);

			if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() == Items.BONE)
				entityData.set(ANT_ROLE, FERTILIZER);
		}
	}

	public int getInventorySize() {
		return 3;
	}

	public BlackAntSimpleContainer getInventory() {
		if (this.inventory == null) {
			return new BlackAntSimpleContainer(getInventorySize());
		}
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
			player.openMenu(this, buf -> {
				buf.writeInt(this.getId());
				buf.writeInt(this.getId());
			});
		}
	}
}
