package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import erebus.ModBlocks;

public class TileEntityGlowGem extends TileEntity {

	private ForgeDirection dir = null;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		if (dir == null)
			dir = getDirectionFromMetadata(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));

	}

		private int getMetaFromDirection(ForgeDirection dir) {
		switch (dir) {
			case UP:
				return 1;
			case DOWN:
				return 1;
			case EAST:
			case WEST:
				return 4;
			case NORTH:
			case SOUTH:
				return 3;
			default:
				return 0;
		}
	}

	private ForgeDirection getDirectionFromMetadata(int meta) {
		switch (meta) {
			case 0:
				return ForgeDirection.DOWN;
			case 1:
				return ForgeDirection.UP;
			case 2:
				return ForgeDirection.NORTH;
			case 3:
				return ForgeDirection.SOUTH;
			case 4:
				return ForgeDirection.WEST;
			case 5:
				return ForgeDirection.EAST;
		}
		return null;
	}
}