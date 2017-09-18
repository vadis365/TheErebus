package erebus.tileentity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public abstract class TileEntityBasicInventory extends TileEntity implements ISidedInventory {

	private NonNullList<ItemStack> inventory;
	private final String name;

	public TileEntityBasicInventory(int invtSize, String name) {
		setInventory(NonNullList.<ItemStack>withSize(invtSize, ItemStack.EMPTY));
		this.name = name;
	}

	@Override
	public int getSizeInventory() {
		return getInventory().size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return getInventory().get(slot);
	}

    protected NonNullList<ItemStack> getItems() {
        return getInventory();
    }

	@Override
    public ItemStack decrStackSize(int index, int count) {
		ItemStack itemstack = ItemStackHelper.getAndSplit(getInventory(), index, count);
		if (!itemstack.isEmpty())
			this.markDirty();
		return itemstack;
	}

	@Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        getInventory().set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());
        this.markDirty();
    }

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : getInventory()) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
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
		setInventory(NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY));
		if (compound.hasKey("Items", 9))
			ItemStackHelper.loadAllItems(compound, getInventory());
	}

	public NBTTagCompound saveToNbt(NBTTagCompound compound) {
		ItemStackHelper.saveAllItems(compound, getInventory(), false);
		return compound;
	}

	@Override
	public void openInventory(EntityPlayer playerIn) {
	}

	@Override
	public void closeInventory(EntityPlayer playerIn) {
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
		getInventory().clear();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	public boolean canInsertItem() {
		return false;
	}

	public NonNullList<ItemStack> getInventory() {
		return inventory;
	}

	public void setInventory(NonNullList<ItemStack> inventory) {
		this.inventory = inventory;
	}
}