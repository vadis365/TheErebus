package erebus.tileentity;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityExtenderThingy extends TileEntityBasicInventory {

	private boolean extending;
	private ForgeDirection dir = null;

	public TileEntityExtenderThingy() {
		super(6, "container.extenderThingy");
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		if (dir == null)
			dir = getDirectionFromMetadata(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));

		Block blockID;
		Block extension = getExtension(dir);
		int index = getIndex(extension);

		if (extending)
			blockID = extension;
		else {
			blockID = null;
			index--;
		}

		int x = xCoord + index * dir.offsetX;
		int y = yCoord + index * dir.offsetY;
		int z = zCoord + index * dir.offsetZ;
		if (x == xCoord && y == yCoord && z == zCoord)
			return;

		Block block = worldObj.getBlock(x, y, z);
		if (block == null || block.isReplaceable(worldObj, x, y, z) || !extending)
			if (decreaseInventory(blockID))
				if (addToInventory(x, y, z))
					if (extending) {
						worldObj.setBlock(x, y, z, blockID, getMetaFromDirection(dir), 3);
						worldObj.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, extension.stepSound.getBreakSound(), (extension.stepSound.getVolume() + 1.0F) / 2.0F, extension.stepSound.getPitch() * 0.8F);
					} else {
						worldObj.setBlock(x, y, z, Blocks.air, getMetaFromDirection(dir), 3);
						worldObj.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(extension) + (worldObj.getBlockMetadata(x, y, z) << 12));
					}
	}

	private int getIndex(Block extension) {
		int index = 1;

		int x = xCoord + index * dir.offsetX;
		int y = yCoord + index * dir.offsetY;
		int z = zCoord + index * dir.offsetZ;

		while (worldObj.getBlock(x, y, z) == extension) {
			index++;
			x = xCoord + index * dir.offsetX;
			y = yCoord + index * dir.offsetY;
			z = zCoord + index * dir.offsetZ;
		}
		Block block = worldObj.getBlock(x, y, z);
		if (block == null || block.isReplaceable(worldObj, x, y, z) || !extending)
			return index;

		return index - 1;
	}

	private boolean addToInventory(int x, int y, int z) {
		Block block = worldObj.getBlock(x, y, z);

		if (worldObj.isAirBlock(x, y, z) || block.isReplaceable(worldObj, x, y, z))
			return true;
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] == null) {
				inventory[i] = new ItemStack(block, 1, 0);
				return true;
			} else if (inventory[i].getItem() == Item.getItemFromBlock(block) && inventory[i].stackSize < inventory[i].getMaxStackSize() && inventory[i].stackSize < getInventoryStackLimit()) {
				inventory[i].stackSize++;
				return true;
			}
		return false;
	}

	private boolean decreaseInventory(Block blockID) {
		if (blockID == null)
			return true;
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] != null && inventory[i].getItem() == Item.getItemFromBlock(blockID)) {
				inventory[i].stackSize--;
				if (inventory[i].stackSize <= 0)
					inventory[i] = null;
				return true;
			}
		return false;
	}

	private Block getExtension(ForgeDirection dir) {
		return dir == ForgeDirection.UP || dir == ForgeDirection.DOWN ? ModBlocks.bambooPole : ModBlocks.bambooBridge;
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

	public void setExtending(boolean extending) {
		this.extending = extending;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return stack != null && (stack.getItem() == Item.getItemFromBlock(ModBlocks.bambooPole) || stack.getItem() == Item.getItemFromBlock(ModBlocks.bambooBridge));
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setBoolean("extending", extending);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		extending = data.getBoolean("extending");
	}
}