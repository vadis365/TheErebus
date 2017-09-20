package erebus.tileentity;

import erebus.entity.EntityTitanBeetle;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class TileEntityTitanChest extends TileEntityBasicInventory {

	protected EntityTitanBeetle titan;

	public TileEntityTitanChest(EntityTitanBeetle chest) {
		super(chest.inventory.size(), "Titan Beetle");
		setInventory(chest.inventory);
		titan = chest;
	}

	@Override
	public Block getBlockType() {
		return Blocks.CHEST;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		if(!titan.getOpen())
			titan.setOpen(true);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		if(titan.getOpen())
			titan.setOpen(false);
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