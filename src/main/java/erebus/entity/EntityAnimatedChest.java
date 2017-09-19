package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIBlockFollowOwner;
import erebus.tileentity.TileEntityAnimatedChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntityAnimatedChest extends EntityAnimatedBlock {
	private static final DataParameter<Float> OPENTICKS = EntityDataManager.<Float>createKey(EntityAnimatedChest.class, DataSerializers.FLOAT);
	public NonNullList<ItemStack> inventory;
	boolean isOpen;
	boolean canClose;

	public EntityAnimatedChest(World world) {
		super(world);
		inventory = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
		isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(OPENTICKS, 0.0F);
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		tasks.removeTask(aiWander);
		tasks.removeTask(aiAttackOnCollide);
		tasks.removeTask(aiAttackNearestTarget);
		tasks.addTask(1, new EntityAIBlockFollowOwner(this, 1.0D, 10.0F, 2.0F));
	}

	@Override
	public boolean getIsInvulnerable() {
		return true;
	}

	public EntityAnimatedChest setContents(IInventory chest) {
		if (chest == null)
			return this;

		inventory = NonNullList.<ItemStack>withSize(chest.getSizeInventory(), ItemStack.EMPTY);
		for (int i = 0; i < chest.getSizeInventory(); i++) {
			if (chest.getStackInSlot(i).isEmpty())
				continue;
			inventory.set(i, chest.getStackInSlot(i).copy());
			chest.setInventorySlotContents(i, ItemStack.EMPTY);
		}
		return this;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote && isDead)
			for (ItemStack is : inventory)
				if (!is.isEmpty())
					Utils.dropStack(getEntityWorld(), getPosition(), is);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (isOpen)
			if (getOpenTicks() >= -1.570F) {
				setOpenTicks(getOpenTicks() - 0.19625F);
			}
		if (!isOpen) {
			if (getOpenTicks() < 0F) {
				setOpenTicks(getOpenTicks() + 0.19625F);
			}
			if (getOpenTicks() == -1.5699999F)
				getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, 0.9F);
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (getEntityWorld().isRemote)
			return true;
		ItemStack is = player.inventory.getCurrentItem();
		if (!is.isEmpty() && is.getItem() == ModItems.WAND_OF_ANIMATION) {
			setDead();
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), ModSounds.ALTAR_OFFERING, SoundCategory.NEUTRAL, 0.2F, 1.0F);
			getEntityWorld().setBlockState(getPosition(), blockID.getStateFromMeta(blockMeta), 3);
			TileEntityChest chest = Utils.getTileEntity(getEntityWorld(), getPosition(), TileEntityChest.class);
			for (int i = 0; i < chest.getSizeInventory(); i++)
				chest.setInventorySlotContents(i, inventory.get(i));
			return true;
		} else if (is.isEmpty()) {
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, 0.9F);
			player.displayGUIChest(new TileEntityAnimatedChest(this));
			return true;
		} else
			return false;
	}

	public void setOpen(boolean open) {
		isOpen = open;
	}

	public void setOpenTicks(float ticks) {
		dataManager.set(OPENTICKS, ticks);
	}

	public float getOpenTicks() {
		return dataManager.get(OPENTICKS);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.loadFromNbt(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		return this.saveToNbt(compound);
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

	public int getSizeInventory() {
		return inventory.size();
	}
}