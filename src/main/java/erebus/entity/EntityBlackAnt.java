package erebus.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.block.silo.TileEntitySiloTank;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIAntBonemealCrops;
import erebus.entity.ai.EntityAIAntHarvestCrops;
import erebus.entity.ai.EntityAIAntPlantCrops;
import erebus.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class EntityBlackAnt extends EntityTameable implements IInventory {
	private static final DataParameter<Integer> DROP_POINT_X = EntityDataManager.<Integer>createKey(EntityBlackAnt.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> DROP_POINT_Y = EntityDataManager.<Integer>createKey(EntityBlackAnt.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> DROP_POINT_Z = EntityDataManager.<Integer>createKey(EntityBlackAnt.class, DataSerializers.VARINT);
	private static final DataParameter<Byte> TAME_TYPE = EntityDataManager.<Byte>createKey(EntityBlackAnt.class, DataSerializers.BYTE);
	public EntityAIPanic aiPanic;
	public EntityAIAntHarvestCrops aiHarvestCrops;
	public EntityAIAntPlantCrops aiPlantCrops;
	public EntityAIAntBonemealCrops aiBonemealCrops;
	public EntityAIWander aiWander;

	public boolean setAttributes;
	public boolean canPickupItems;
	public boolean canCollectFromSilo;
	public boolean canAddToSilo;

	public NonNullList<ItemStack> inventory;
	public static final int TOOL_SLOT = 0;
	public static final int CROP_ID_SLOT = 1;
	public static final int INVENTORY_SLOT = 2;

	public EntityBlackAnt(World world) {
		super(world);
		setPathPriority(PathNodeType.WATER, -8F);
		stepHeight = 1.0F;
		setAttributes = false;
		canPickupItems = false;
		canAddToSilo = false;
		canCollectFromSilo = false;
		setSize(0.9F, 0.4F);
		inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(DROP_POINT_X, 0);
		dataManager.register(DROP_POINT_Y, 0);
		dataManager.register(DROP_POINT_Z, 0);
		dataManager.register(TAME_TYPE, (byte) 0);
	}
	
	@Override
	protected void initEntityAI() {
		aiPanic = new EntityAIPanic(this, 0.8D);
		aiHarvestCrops = new EntityAIAntHarvestCrops(this, 0.6D, 1);
		aiPlantCrops = new EntityAIAntPlantCrops(this, 0.6D, 4);
		aiBonemealCrops = new EntityAIAntBonemealCrops(this, 0.6D, 4);
		aiWander = new EntityAIWander(this, 0.6D);
		
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiWander);
		tasks.addTask(2, aiPanic);
		tasks.addTask(3, new EntityAILookIdle(this));
		tasks.addTask(4, new EntityAITempt(this, 0.6D, ModItems.ANT_TAMING_AMULET, false));
		tasks.addTask(5, new EntityAITempt(this, 0.6D, Items.SUGAR, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 15D : 15D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.FIRE_ANT_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.FIRE_ANT_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	@Override
	protected boolean canDespawn() {
		return !isTamed();
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public boolean isTamed() {
		return dataManager.get(TAME_TYPE) != 0;
	}

	private static final String[] names = { "Antwan", "George", "Geoff", "Alberto", "Jose", "Linda", "Chantelle", "Dave", "Basil", "Gertrude", "Herbert", "Russel", "Adam", "Gwen", "Billy Bob Joe Bob Joe Harrison Jr.", "Sid", "Dylan", "Jade" };

	@Override
	public void setTamed(boolean tamed) {
		if (tamed) {
			dataManager.set(TAME_TYPE, (byte) 1);
			if (!hasCustomName())
				setCustomNameTag(names[getEntityWorld().rand.nextInt(names.length)]);
		} else
			dataManager.set(TAME_TYPE, (byte) 0);
	}

	public void openGUI(EntityPlayer player) {
		player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.ANT_INVENTORY.ordinal(), player.getEntityWorld(), getEntityId(), 0, 0);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack is = player.inventory.getCurrentItem();

		if (!is.isEmpty() && is.getItem() == ModItems.ANT_TAMING_AMULET && is.hasTagCompound() && is.getTagCompound().hasKey("homeX")) {
			setDropPoint(is.getTagCompound().getInteger("homeX"), is.getTagCompound().getInteger("homeY"), is.getTagCompound().getInteger("homeZ"));
			player.swingArm(hand);
			setTamed(true);
			playTameEffect(true);
			return true;
		}
		if (isTamed()) {
			openInventory(player);
			openGUI(player);
		}
		return super.processInteract(player, hand);
	}

	public void setBlockHarvested(Block block, int meta) {
		Random rand = new Random();
		if (block != null) {
			Utils.dropStack(getEntityWorld(), getPosition(), new ItemStack(block.getItemDropped(block.getStateFromMeta(meta), rand, 0), rand.nextInt(2) + 1));
			Utils.dropStack(getEntityWorld(), getPosition(), new ItemStack(block.getItemDropped(block.getDefaultState(), rand, 0), rand.nextInt(2) + 1));
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!getEntityWorld().isRemote && !setAttributes) {
			if (isTamed()) {
				openInventory(null);
				closeInventory(null);
			}
			setAttributes = true;
		}
	}

	@Override
	protected void dropEquipment(boolean rencentHit, int fortune) {
		for (ItemStack stack : inventory)
			if (!stack.isEmpty())
				entityDropItem(stack, 0.0F);
	}

	private void addToInventory(ItemStack stack) {
		if (stack.isEmpty())
			return;
		if (inventory.get(INVENTORY_SLOT).isEmpty()) {
			inventory.set(INVENTORY_SLOT, stack.copy());
			stack.setCount(0);
		} else if (Utils.areStacksTheSame(stack, inventory.get(INVENTORY_SLOT), false)) {
			int old = inventory.get(INVENTORY_SLOT).getCount();
			inventory.get(INVENTORY_SLOT).setCount(stack.getCount() + old);
			if (inventory.get(INVENTORY_SLOT).getCount() > inventory.get(INVENTORY_SLOT).getMaxStackSize())
				inventory.get(INVENTORY_SLOT).setCount(inventory.get(INVENTORY_SLOT).getMaxStackSize());

			int added = inventory.get(INVENTORY_SLOT).getCount() - old;
			stack.shrink(added);
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (getEntityWorld().isRemote)
			return;
		// Don't pick up items unless the filter is defined and the inventory is
		// not full
		if (isTamed()) {
			if (canPickupItems && !isFilterSlotEmpty() && (getAntInvSlotStack().isEmpty() || getAntInvSlotStack().getCount() < getAntInvSlotStack().getMaxStackSize())) {
				EntityItem entityitem = getClosestEntityItem(this, 16.0D, getFilterSlotStack());
				if (entityitem != null) {
					float distance = entityitem.getDistance(this);
					if (distance >= 2F && !entityitem.isDead) {
						double x = entityitem.posX;
						double y = entityitem.posY;
						double z = entityitem.posZ;
						getLookHelper().setLookPosition(x, y, z, 20.0F, 8.0F);
						moveToItem(entityitem);
						return;
					}
					if (distance < 2F) {
						getMoveHelper().setMoveTo(entityitem.posX, entityitem.posY, entityitem.posZ, 0.5D);
						addToInventory(entityitem.getItem());
						if (entityitem.getItem().getCount() <= 0)
							entityitem.setDead();
						return;
					}
				}
			}

			if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof ItemBucket)
				if (!isAntInvSlotEmpty() && getAntInvSlotStack().getCount() > 15) {
					canAddToSilo = true;
					canPickupItems = false;
				}

			if (!canPickupItems && canAddToSilo) {
				moveToSilo();
				BlockPos pos = new BlockPos(getDropPointX(), getDropPointY(), getDropPointZ());
				Block block = getEntityWorld().getBlockState(pos).getBlock();
				if (block == ModBlocks.SILO_TANK)
					if (getDistance(getDropPointX() + 0.5D, getDropPointY() - 1D, getDropPointZ() + 0.5D) < 2D) {
						addDropToInventory(pos);
						if(isAntInvSlotEmpty()) {
							canAddToSilo = false;
							canPickupItems = true;
						}
					}
			}

			if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof ItemHoe || !isTaskSlotEmpty() && getTaskSlotStack().getItem() == Items.BONE)
				if (isAntInvSlotEmpty() && !isFilterSlotEmpty())
					canCollectFromSilo = true; // this stops the planting or
			// bonemealing AIs and makes the ant
			// go to the silo

			if (canCollectFromSilo) {
				moveToSilo();
				BlockPos pos = new BlockPos(getDropPointX(), getDropPointY(), getDropPointZ());
				Block block = getEntityWorld().getBlockState(pos).getBlock();
				if (block == ModBlocks.SILO_TANK)
					if (getDistance(getDropPointX() + 0.5D, getDropPointY() - 1D, getDropPointZ() + 0.5D) < 2D) {
						getStackFromSilo();
						canCollectFromSilo = false;
					}
			}
		}
	}

	private void getStackFromSilo() {
		if (getEntityWorld().isRemote)
			return;
		BlockPos pos = new BlockPos(getDropPointX(), getDropPointY(), getDropPointZ());
		TileEntitySiloTank siloTile = (TileEntitySiloTank) getEntityWorld().getTileEntity(pos);

		if (siloTile != null && siloTile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IItemHandler handler = siloTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
			int sizeInventory = handler.getSlots();
		for (int i = 0; i < sizeInventory; i++)
			if (!siloTile.getStackInSlot(i).isEmpty())
				if (siloTile.getStackInSlot(i).getItem() == getStackInSlot(CROP_ID_SLOT).getItem() && siloTile.getStackInSlot(i).getItemDamage() == getStackInSlot(CROP_ID_SLOT).getItemDamage())
					if (isAntInvSlotEmpty()) {
						int collectStackSize = siloTile.getStackInSlot(i).getCount();
						setInventorySlotContents(INVENTORY_SLOT, new ItemStack(siloTile.getStackInSlot(i).getItem(), collectStackSize, siloTile.getStackInSlot(i).getItemDamage()));
						siloTile.decrStackSize(i, collectStackSize);
						return;
					}
		}
	}

	private void addDropToInventory(BlockPos pos) {
		ItemStack stack = getAntInvSlotStack();
		TileEntitySiloTank siloTile = (TileEntitySiloTank) getEntityWorld().getTileEntity(pos);
		if (!isAntInvSlotEmpty()) {
			if (siloTile != null && siloTile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
				IItemHandler handler = siloTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
				if (!stack.isEmpty()) {
					ItemStack stackNew = stack.copy();
					stackNew.setCount(1);
					ItemStack stack1 = ItemHandlerHelper.insertItem(handler, stackNew, true);
					if (stack1.isEmpty()) {
						ItemHandlerHelper.insertItem(handler, decrStackSize(INVENTORY_SLOT, 1), false);
						markDirty();
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public EntityItem getClosestEntityItem(final Entity entity, double d, ItemStack filter) {
		List<EntityItem> list = getEntityWorld().getEntitiesWithinAABB(EntityItem.class, getEntityBoundingBox().grow(d, d, d));
		if (list.isEmpty())
			return null;

		for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext();) {
			EntityItem item = iterator.next();
			if (!Utils.areStacksTheSame(filter, item.getItem(), false))
				iterator.remove();
		}

		if (list.isEmpty())
			return null;

		Collections.sort(list, new Comparator<EntityItem>() {

			@Override
			public int compare(EntityItem e1, EntityItem e2) {
				return Double.compare(e1.getDistanceSq(entity.posX, entity.posY, entity.posZ), e2.getDistanceSq(entity.posX, entity.posY, entity.posZ));
			}

		});

		return list.get(0);
	}

	public void moveToItem(Entity entity) {
		Path pathentity = getNavigator().getPathToEntityLiving(entity);
		if (pathentity != null) {
			getNavigator().setPath(pathentity, 0.5D);
		}
	}

	public void moveToSilo() {
		Path pathentity = getNavigator().getPathToXYZ(getDropPointX(), getDropPointY() - 1, getDropPointZ());
		if (pathentity != null) {
			getNavigator().setPath(pathentity, 0.5D);
		}
	}

	@Override
	public int getSizeInventory() {
		return inventory.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.get(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (!inventory.get(slot).isEmpty()) {
			ItemStack itemstack;
			if (inventory.get(slot).getCount() <= size) {
				itemstack = inventory.get(slot);
				inventory.set(slot, ItemStack.EMPTY);
				return itemstack;
			} else {
				itemstack = inventory.get(slot).splitStack(size);
				if (inventory.get(slot).getCount() == 0)
					inventory.set(slot, ItemStack.EMPTY);
				return itemstack;
			}
		} else
			return ItemStack.EMPTY;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory.set(slot, stack);

		if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit())
			stack.setCount(getInventoryStackLimit());
	}

	@Override
	public String getName() {
		return hasCustomName() ? getCustomNameTag() : "container.antInventory";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (slot == TOOL_SLOT)
			return stack.getItem() == Items.SHEARS || stack.getItem() == Items.BUCKET || stack.getItem() instanceof ItemHoe || stack.getItem() == Items.BONE;

		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.getByte("tamed") == 1)
			setTamed(true);
		else
			setTamed(false);
		setDropPoint(compound.getInteger("dropPointX"), compound.getInteger("dropPointY"), compound.getInteger("dropPointZ"));
		loadFromNbt(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if (isTamed())
			compound.setByte("tamed", Byte.valueOf((byte) 1));
		else
			compound.setByte("tamed", Byte.valueOf((byte) 0));
		compound.setInteger("dropPointX", getDropPointX());
		compound.setInteger("dropPointY", getDropPointY());
		compound.setInteger("dropPointZ", getDropPointZ());
		return saveToNbt(compound);
	}

	public void loadFromNbt(NBTTagCompound compound) {
		inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (compound.hasKey("Items", 9))
			ItemStackHelper.loadAllItems(compound, inventory);
	}

	public NBTTagCompound saveToNbt(NBTTagCompound compound) {
		ItemStackHelper.saveAllItems(compound, inventory, false);
		return compound;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		if (getEntityWorld().isRemote)
			return;
		canPickupItems = false;
		canAddToSilo = false;
		canCollectFromSilo = false;
		tasks.removeTask(aiWander);
		tasks.removeTask(aiPlantCrops);
		tasks.removeTask(aiHarvestCrops);
		tasks.removeTask(aiBonemealCrops);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		if (getEntityWorld().isRemote)
			return;

		if (isTaskSlotEmpty() && isTamed()) {
			tasks.addTask(1, aiWander);
			dataManager.set(TAME_TYPE, (byte) 1);
		}

		if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof ItemHoe && !isFilterSlotEmpty()) {
			tasks.addTask(1, aiPlantCrops);
			dataManager.set(TAME_TYPE, (byte) 2);
		}

		if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof ItemBucket && !isFilterSlotEmpty()) {
			canPickupItems = true;
			dataManager.set(TAME_TYPE, (byte) 3);
		}

		if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() instanceof ItemShears) {
			tasks.addTask(1, aiHarvestCrops);
			dataManager.set(TAME_TYPE, (byte) 4);
		}

		if (!isTaskSlotEmpty() && getTaskSlotStack().getItem() == Items.BONE && !isFilterSlotEmpty()) {
			tasks.addTask(1, aiBonemealCrops);
			dataManager.set(TAME_TYPE, (byte) 5);
		}
	}

	public boolean isTaskSlotEmpty() {
		return getTaskSlotStack().isEmpty();
	}

	public ItemStack getTaskSlotStack() {
		return getStackInSlot(TOOL_SLOT);
	}

	public boolean isFilterSlotEmpty() {
		return getFilterSlotStack().isEmpty();
	}

	public ItemStack getFilterSlotStack() {
		return getStackInSlot(CROP_ID_SLOT);
	}

	public boolean isAntInvSlotEmpty() {
		return getAntInvSlotStack().isEmpty();
	}

	public ItemStack getAntInvSlotStack() {
		return getStackInSlot(INVENTORY_SLOT);
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.IN_WALL) || source.equals(DamageSource.DROWN))
			return false;
		return super.attackEntityFrom(source, damage);
	}

	public void setDropPoint(int x, int y, int z) {
		dataManager.set(DROP_POINT_X, x);
		dataManager.set(DROP_POINT_Y, y);
		dataManager.set(DROP_POINT_Z, z);
	}

	public int getDropPointX() {
		return dataManager.get(DROP_POINT_X);
	}

	public int getDropPointY() {
		return dataManager.get(DROP_POINT_Y);
	}

	public int getDropPointZ() {
		return dataManager.get(DROP_POINT_Z);
	}
	
	public byte getTamedType() {
		return dataManager.get(TAME_TYPE);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable baby) {
		return null;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {	
	}
}