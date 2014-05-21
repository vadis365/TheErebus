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

public class TileEntityExtenderThingy extends TileEntity implements IInventory {

	private boolean extending;
	private ForgeDirection dir = null;
	private ItemStack[] inventory = new ItemStack[6];

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		if (dir == null)
			dir = getDirectionFromMetadata(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));

		int blockID;
		Block extension = getExtension(dir);
		int index = getIndex(extension);

		if (extending)
			blockID = extension.blockID;
		else {
			blockID = 0;
			index--;
		}

		int x = xCoord + index * dir.offsetX;
		int y = yCoord + index * dir.offsetY;
		int z = zCoord + index * dir.offsetZ;
		if (x == xCoord && y == yCoord && z == zCoord)
			return;

		Block block = Block.blocksList[worldObj.getBlockId(x, y, z)];
		if (block == null || block.isBlockReplaceable(worldObj, x, y, z) || !extending)
			if (decreaseInventory(blockID))
				if (addToInventory(x, y, z)) {
					worldObj.setBlock(x, y, z, blockID, getMetaFromDirection(dir), 3);
					if (extending)
						worldObj.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, extension.stepSound.getPlaceSound(), (extension.stepSound.getVolume() + 1.0F) / 2.0F, extension.stepSound.getPitch() * 0.8F);
					else
						worldObj.playAuxSFXAtEntity(null, 2001, x, y, z, extension.blockID + (worldObj.getBlockMetadata(x, y, z) << 12));
				}
	}

	private int getIndex(Block extension) {
		int index = 1;

		int x = xCoord + index * dir.offsetX;
		int y = yCoord + index * dir.offsetY;
		int z = zCoord + index * dir.offsetZ;

		while (worldObj.getBlockId(x, y, z) == extension.blockID) {
			index++;
			x = xCoord + index * dir.offsetX;
			y = yCoord + index * dir.offsetY;
			z = zCoord + index * dir.offsetZ;
		}
		Block block = Block.blocksList[worldObj.getBlockId(x, y, z)];
		if (block == null || block.isBlockReplaceable(worldObj, x, y, z) || !extending)
			return index;

		return index - 1;
	}

	private boolean addToInventory(int x, int y, int z) {
		int blockID = worldObj.getBlockId(x, y, z);
		Block block = Block.blocksList[blockID];

		if (worldObj.isAirBlock(x, y, z) || block.isBlockReplaceable(worldObj, x, y, z))
			return true;
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] == null) {
				inventory[i] = new ItemStack(blockID, 1, 0);
				return true;
			} else if (inventory[i].itemID == blockID && inventory[i].stackSize < inventory[i].getMaxStackSize() && inventory[i].stackSize < getInventoryStackLimit()) {
				inventory[i].stackSize++;
				return true;
			}
		return false;
	}

	private boolean decreaseInventory(int blockID) {
		if (blockID == 0)
			return true;
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] != null && inventory[i].itemID == blockID) {
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
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (inventory[slot] != null) {
			ItemStack is;

			if (inventory[slot].stackSize <= amount) {
				is = inventory[slot];
				inventory[slot] = null;
				onInventoryChanged();
				return is;
			} else {
				is = inventory[slot].splitStack(amount);

				if (inventory[slot].stackSize == 0)
					inventory[slot] = null;

				onInventoryChanged();
				return is;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
			ItemStack is = inventory[slot];
			inventory[slot] = null;
			return is;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is) {
		inventory[slot] = is;

		if (is != null && is.stackSize > getInventoryStackLimit())
			is.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "container.extenderThingy";
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
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return stack != null && (stack.itemID == ModBlocks.bambooPole.blockID || stack.itemID == ModBlocks.bambooBridge.blockID);
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setBoolean("extending", extending);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inventory.length; ++i)
			if (inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		data.setTag("Items", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);

		extending = data.getBoolean("extending");

		NBTTagList nbttaglist = data.getTagList("Items");
		inventory = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < inventory.length)
				inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}
}