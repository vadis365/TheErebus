package erebus.entity;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import erebus.block.entity.SiloTankBlockEntity;
import erebus.entity.ai.BlackAntPlantCrops;
import erebus.inventory.server.BlackAntMenu;
import erebus.inventory.server.BlackAntSimpleContainer;
import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.data.ModDataComponents;
import erebus.utils.CapHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
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
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

public class BlackAnt extends Animal implements ContainerListener, HasCustomInventoryScreen, MenuProvider {
	private static final EntityDataAccessor<BlockPos> DROP_POINT= SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Boolean> TAME_STATE = SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> ANT_ROLE = SynchedEntityData.defineId(BlackAnt.class, EntityDataSerializers.BYTE);
	private UUID playerOwner = null;
	private WeakReference<FakePlayer> fakePlayer = new WeakReference<>(null);
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
		//setPathPriority(PathNodeType.WATER, -8F);
		//stepHeight = 1.0F;
		//setAttributes = false;
		canPickupItems = false;
		canAddToSilo = false;
		canCollectFromSilo = false;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DROP_POINT, this.blockPosition());
		builder.define(TAME_STATE, false);
		builder.define(ANT_ROLE, NONE);
	}
	
	@Override
	protected void registerGoals() {
	//	aiPanic = new EntityAIPanic(this, 0.8D);
	//	aiHarvestCrops = new EntityAIAntHarvestCrops(this, 0.6D, 1);
	//	aiBonemealCrops = new EntityAIAntBonemealCrops(this, 0.6D, 4);
	//	aiWander = new EntityAIWander(this, 0.6D);
		
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new BlackAntPlantCrops(this, 0.6D, 4, false));
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
		return ModSounds.FIRE_ANT_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.FIRE_ANT_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
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

	public static boolean canSpawnHere(EntityType<BlackAnt> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
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

	private static final String[] names = { "Antwan", "George", "Geoff", "Alberto", "Jose", "Linda", "Chantelle", "Dave", "Basil", "Gertrude", "Herbert", "Russel", "Adam", "Gwen", "Billy Bob Joe Bob Joe Harrison Jr.", "Sid", "Dylan", "Jade" };

	public void setTameState(boolean state) {
		entityData.set(TAME_STATE, state);
		if (!hasCustomName())
			setCustomName(Component.literal(names[random.nextInt(names.length)]));
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack is = player.getItemInHand(hand);

		if (!is.isEmpty() && is.getItem() == ModItems.ANT_TAMING_AMULET.get() && is.has(ModDataComponents.ANT_TAMING_AMULET)) {
			if (!level().isClientSide()) {
				BlockPos dataBlockPos = is.getComponents().get(ModDataComponents.ANT_TAMING_AMULET.get());
				setDropPoint(dataBlockPos);
				setTameState(true);
				setPlayerOwner(player);
			}
			level().broadcastEntityEvent(this, (byte)18);
			player.swing(hand);
			return InteractionResult.SUCCESS;
		}
		if (isTamedAnt()) {
			openCustomInventoryScreen(player);
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
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
	public boolean isFood(ItemStack stack) {
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return null;
	}
	
	@Override
    protected void customServerAiStep() {
        super.customServerAiStep();

		// Don't pick up items unless the filter is defined and the inventory is
		// not full
		if (isTamedAnt()) {
			if (canPickupItems && !isFilterSlotEmpty() && (getAntInvSlotStack().isEmpty() || getAntInvSlotStack().getCount() < getAntInvSlotStack().getMaxStackSize())) {
				ItemEntity entityitem = getClosestEntityItem(this, 16.0D, getFilterSlotStack());
				if (entityitem != null) {
					float distance = entityitem.distanceTo(this);
					if (distance >= 2F && entityitem.isAlive()) {
						double x = entityitem.getX();
						double y = entityitem.getY();
						double z = entityitem.getZ();
						getLookControl().setLookAt(x, y, z, 20.0F, 8.0F);
						moveToItem(entityitem);
						return;
					}
					if (distance < 2F) {
						getMoveControl().setWantedPosition(entityitem.getX(), entityitem.getY(), entityitem.getZ(), 0.5D);
						addToInventory(entityitem.getItem());
						if (entityitem.getItem().getCount() <= 0)
							entityitem.remove(RemovalReason.DISCARDED);
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
				if (block == OtherBlocks.SILO_TANK.get())
					if (getDistance(getDropPoint().getX() + 0.5D, getDropPoint().getY() - 1D, getDropPoint().getZ() + 0.5D) < 2D) {
						addDropToInventory(getDropPoint());
						if(isAntInvSlotEmpty()) {
							canAddToSilo = false;
							canPickupItems = true;
						}
					}
			}

			if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof HoeItem || !isTaskSlotEmpty() && getTaskSlotStack().getItem() == Items.BONE)
				if (isAntInvSlotEmpty() && !isFilterSlotEmpty())
					canCollectFromSilo = true; // this stops the planting or
			// bonemealing AIs and makes the ant
			// go to the silo

			if (canCollectFromSilo) {
				moveToSilo();
				Block block = level().getBlockState(getDropPoint()).getBlock();
				if (block == OtherBlocks.SILO_TANK.get())
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
	
    public double getDistance(double x, double y, double z)
    {
        double d0 = this.getX() - x;
        double d1 = this.getY() - y;
        double d2 = this.getZ() - z;
        return (double)Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }
	
	public ItemEntity getClosestEntityItem(final Entity entity, double d, ItemStack filter) {
		List<ItemEntity> list = level().<ItemEntity>getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(d, d, d), EntitySelector.ENTITY_STILL_ALIVE);
		if (list.isEmpty())
			return null;

		for (Iterator<ItemEntity> iterator = list.iterator(); iterator.hasNext();) {
			ItemEntity item = iterator.next();
			if (!ItemStack.isSameItem(filter, item.getItem()))
				iterator.remove();
		}

		if (list.isEmpty())
			return null;

		Collections.sort(list, new Comparator<ItemEntity>() {

			@Override
			public int compare(ItemEntity e1, ItemEntity e2) {
				return Double.compare(e1.distanceToSqr(entity), e2.distanceToSqr(entity));
			}

		});

		return list.get(0);
	}

	@Override
	  public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("tameState", isTamedAnt());
		nbt.putByte("antRole", getAntRole());
		nbt.put("dropPoint", NbtUtils.writeBlockPos(getDropPoint()));

		// TODO handling individual slots rather than iterating here, may change later
		if (!this.inventory.getItem(TOOL_SLOT).isEmpty())
        	nbt.put("toolSlot", this.inventory.getItem(TOOL_SLOT).save(registryAccess()));
		if (!this.inventory.getItem(CROP_ID_SLOT).isEmpty())
        	nbt.put("cropIdSlot", this.inventory.getItem(CROP_ID_SLOT).save(registryAccess()));
		if (!this.inventory.getItem(INVENTORY_SLOT).isEmpty())
        	nbt.put("inventorySlot", this.inventory.getItem(INVENTORY_SLOT).save(registryAccess()));

		if (playerOwner != null) nbt.putUUID("playerOwner", playerOwner);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		setTameState(nbt.getBoolean("tameState"));
		setAntRole(nbt.getByte("antRole"));
		Optional<BlockPos> optional = NbtUtils.readBlockPos(nbt, "dropPoint");

		if(!optional.isEmpty())
			setDropPoint(optional.get());

		if (nbt.contains("toolSlot", 10)) {
			ItemStack stack1 = ItemStack.parse(this.registryAccess(), nbt.getCompound("toolSlot")).orElse(ItemStack.EMPTY);
			 if (!stack1.isEmpty())
				 this.inventory.setItem(TOOL_SLOT, stack1);
		}

		if (nbt.contains("cropIdSlot", 10)) {
			ItemStack stack2 = ItemStack.parse(this.registryAccess(), nbt.getCompound("cropIdSlot")).orElse(ItemStack.EMPTY);
			if (!stack2.isEmpty())
				this.inventory.setItem(CROP_ID_SLOT, stack2);
		}

		if (nbt.contains("inventorySlot", 10)) {
			ItemStack stack3 = ItemStack.parse(this.registryAccess(), nbt.getCompound("inventorySlot")).orElse(ItemStack.EMPTY);
			if (!stack3.isEmpty())
				this.inventory.setItem(INVENTORY_SLOT, stack3);
		}

		playerOwner = nbt.hasUUID("playerOwner") ? nbt.getUUID("playerOwner") : null;
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
		if (!isAntInvSlotEmpty()) {
		if (tile instanceof SiloTankBlockEntity silo) {
			Optional<IItemHandler> handlerOptional = CapHelper.getItemHandler(level(), pos, null);
			if (handlerOptional.isPresent()) {
				handlerOptional.ifPresent((handler) -> {
					ItemStack stack1 = ItemHandlerHelper.insertItem(handler, stack, true);
					if (stack1.isEmpty()) {
						ItemHandlerHelper.insertItem(handler, stack, false);
						silo.setChanged();
						inventory.setItem(INVENTORY_SLOT, ItemStack.EMPTY); // not nice atm but stops insane behaviours
					} else {
						spawnAtLocation(stack, 0.0F);
						inventory.setItem(INVENTORY_SLOT, ItemStack.EMPTY); // not nice atm but stops insane behaviours
					}
				});
			} else {
				spawnAtLocation(stack, 0.0F); // just in case
				inventory.setItem(INVENTORY_SLOT, ItemStack.EMPTY); // not nice atm but stops insane behaviours
			}
		}
		}
	}

	private void getStackFromSilo() {
		BlockEntity tile = level().getBlockEntity(getDropPoint());
		if (tile instanceof SiloTankBlockEntity silo) {
			Optional<IItemHandler> handlerOptional = CapHelper.getItemHandler(level(), getDropPoint(), null);
			if (handlerOptional.isPresent()) {
				handlerOptional.ifPresent((handler) -> {
					int sizeInventory = handler.getSlots();
					for (int i = 0; i < sizeInventory; i++)
						if (!handler.getStackInSlot(i).isEmpty())
							if (ItemStack.isSameItem(handler.getStackInSlot(i), inventory.getItem(CROP_ID_SLOT)))
								if (isAntInvSlotEmpty()) {
									int collectStackSize = handler.getStackInSlot(i).getCount();
									inventory.setItem(INVENTORY_SLOT, new ItemStack(handler.getStackInSlot(i).getItem(), collectStackSize));
									handler.getStackInSlot(i).shrink(collectStackSize);
									return;
								}
				});
			}
		}
	}

	protected void updateInventory() {
        BlackAntSimpleContainer previousInventory = this.inventory;
        this.inventory = new BlackAntSimpleContainer(this.getInventorySize());
        if (previousInventory != null) {
            previousInventory.removeListener(this);
            int maxSize = Math.min(previousInventory.getContainerSize(), this.inventory.getContainerSize());

            for (int slot = 0; slot < maxSize; ++slot) {
                ItemStack stack = previousInventory.getItem(slot);
                if (!stack.isEmpty()) {
                    this.inventory.setItem(slot, stack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.syncInventoryToFlags();
    }

    public void syncInventoryToFlags() {
        if (!this.level().isClientSide()) {
        	canPickupItems = false;
    		canAddToSilo = false;
    		canCollectFromSilo = false;
        	
        	if (isTaskSlotEmpty() && isTamedAnt()) {
    			//tasks.addTask(1, aiWander);
    			entityData.set(ANT_ROLE, NONE);
    		}

    		if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof HoeItem) {
    			entityData.set(ANT_ROLE, PLANTER);
    		}

    		if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof BucketItem) {
    			canPickupItems = true;
    			entityData.set(ANT_ROLE, COLLECTOR);
    		}

    		if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof ShearsItem) {
    			//tasks.addTask(1, aiHarvestCrops);
    			entityData.set(ANT_ROLE, HARVESTER);
    		}

    		if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() == Items.BONE) {
    			//tasks.addTask(1, aiBonemealCrops);
    			entityData.set(ANT_ROLE, FERTILIZER);
    		}
        }
    }

    @Override
    public void containerChanged(Container Container) {
        this.syncInventoryToFlags();
    }
	
    public int getInventorySize() {
        return 3;
    }
	
    public BlackAntSimpleContainer getInventory() {
        if(this.inventory == null){
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
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new BlackAntMenu(containerId, playerInventory, this);
	}

	@Override
	public void openCustomInventoryScreen(Player player) {
		if (!level().isClientSide()) {
			((ServerPlayer) player).openMenu(this, buf -> {
			buf.writeInt(this.getId());
			buf.writeInt(this.getId());
		});
		}
	}
}