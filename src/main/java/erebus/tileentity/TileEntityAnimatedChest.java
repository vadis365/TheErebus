package erebus.tileentity;

import erebus.entity.EntityAnimatedChest;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class TileEntityAnimatedChest extends TileEntityBasicInventory {

	protected EntityAnimatedChest chester;

	public TileEntityAnimatedChest(EntityAnimatedChest chest) {
		super(chest.inventory.size(), "container.animatedChest");
		setInventory(chest.inventory);
		chester = chest;
	}

	@Override
	public Block getBlockType() {
		return Blocks.CHEST;
	}

	@Override
	public void openInventory(EntityPlayer playerIn) {
		chester.setOpen(true);
	}

	@Override
	public void closeInventory(EntityPlayer playerIn) {
		chester.setOpen(false);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}
}