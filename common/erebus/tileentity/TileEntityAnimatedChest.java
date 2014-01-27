package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import erebus.entity.EntityAnimatedChest;

public class TileEntityAnimatedChest extends TileEntity implements IInventory {

	private final ItemStack[] chestContents;
	protected EntityAnimatedChest chester;

	public TileEntityAnimatedChest(EntityAnimatedChest chest) {
		chestContents = chest.inventory;
		chester = chest;
	}
	
	@Override
	public boolean canUpdate(){
		return false;
	}

	@Override
	public Block getBlockType() {
		return Block.chest;
	}

	@Override
	public void openChest() {
		chester.setOpen(true);
	}

	@Override
	public void closeChest() {
		chester.setOpen(false);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return chestContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return chestContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (chestContents[slot] != null) {
			ItemStack is;
			if (chestContents[slot].stackSize <= size) {
				is = chestContents[slot];
				chestContents[slot] = null;
				return is;
			} else {
				is = chestContents[slot].splitStack(size);
				if (chestContents[slot].stackSize == 0)
					chestContents[slot] = null;
				return is;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		// closeChest();
		if (chestContents[slot] != null) {
			ItemStack is = chestContents[slot];
			chestContents[slot] = null;
			return is;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is) {
		chestContents[slot] = is;

		if (is != null && is.stackSize > getInventoryStackLimit())
			is.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInvName() {
		return "Chester";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return true;
	}
}