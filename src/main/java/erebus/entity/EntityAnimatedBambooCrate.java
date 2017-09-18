package erebus.entity;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.helper.Utils;
import erebus.entity.ai.EntityAIBlockFollowOwner;
import erebus.proxy.CommonProxy;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntityAnimatedBambooCrate extends EntityAnimatedBlock implements IInventory {

	public NonNullList<ItemStack> inventory;

	public EntityAnimatedBambooCrate(World world) {
		super(world);
		inventory = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
		isImmuneToFire = true;
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

	public EntityAnimatedBambooCrate setContents(IInventory chest) {
		if (chest == null)
			return this;

		inventory = NonNullList.<ItemStack>withSize(chest.getSizeInventory(), ItemStack.EMPTY);
		for (int i = 0; i < chest.getSizeInventory(); i++) {
			if (chest.getStackInSlot(i).isEmpty())
				continue;
			inventory.set(i, chest.getStackInSlot(i).copy());
			chest.setInventorySlotContents(i, null);
		}
		return this;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote && isDead)
			for (ItemStack is : inventory)
				if (is != null)
					Utils.dropStack(getEntityWorld(), getPosition(), is);
	}

	public TileEntityBambooCrate getTile() {
		TileEntityBambooCrate crate = new TileEntityBambooCrate();
		for (int i = 0; i < inventory.size(); i++)
			crate.setInventorySlotContents(i, inventory.get(i));
		return crate;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (getEntityWorld().isRemote)
			return true;
		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.getItem() == ModItems.wandOfAnimation) {
			setDead();
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), ModSounds.ALTAR_OFFERING, SoundCategory.NEUTRAL, 0.2F, 1.0F);
			getEntityWorld().setBlockState(getPosition(), blockID.getStateFromMeta(blockMeta), 3);
			TileEntityBambooCrate chest = Utils.getTileEntity(getEntityWorld(), getPosition(), TileEntityBambooCrate.class);
			for (int i = 0; i < chest.getSizeInventory(); i++)
				chest.setInventorySlotContents(i, inventory.get(i));
			IBlockState state = this.getEntityWorld().getBlockState(this.getPosition());
	        getEntityWorld().notifyBlockUpdate(this.getPosition(), state, state, 2);
			return true;
		} else {
			player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.ANIMATED_BAMBOO_CRATE.ordinal(), player.getEntityWorld(), getEntityId(), 0, 0);
			return true;
		}
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

	@Override
	public int getSizeInventory() {
		return inventory.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.get(slot);
	}

	@Override
    public ItemStack decrStackSize(int index, int count) {
		ItemStack itemstack = ItemStackHelper.getAndSplit(inventory, index, count);
		if (!itemstack.isEmpty())
			this.markDirty();
		return itemstack;
	}

	@Override
	public String getName() {
		return "container.animatedBambooCrate";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        inventory.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());
        this.markDirty();
    }

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : inventory) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public void markDirty() {	
	}

	@Override
	public void openInventory(EntityPlayer player) {	
	}

	@Override
	public void closeInventory(EntityPlayer player) {
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