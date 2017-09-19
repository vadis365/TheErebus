package erebus.tileentity;

import erebus.ModBlocks;
import erebus.entity.EntityAnimatedBambooCrate;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class TileEntityAnimatedBambooCrate extends TileEntityBasicInventory {

	protected EntityAnimatedBambooCrate bamber;

	public TileEntityAnimatedBambooCrate(EntityAnimatedBambooCrate crate) {
		super(crate.inventory.size(), "container.animatedBambooCrate");
		setInventory(crate.inventory);
		bamber = crate;
	}

	@Override
	public Block getBlockType() {
		return ModBlocks.BAMBOO_CRATE;
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