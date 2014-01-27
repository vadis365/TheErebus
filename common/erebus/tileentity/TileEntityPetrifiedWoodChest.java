package erebus.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import erebus.block.BlockPetrifiedChest;
import erebus.inventory.ContainerPetrifiedWoodChest;

public class TileEntityPetrifiedWoodChest extends TileEntity implements IInventory {
	private ItemStack[] chestContents = new ItemStack[36];

	public boolean adjacentChestChecked;
	public TileEntityPetrifiedWoodChest adjacentChestZNeg, adjacentChestXPos, adjacentChestXNeg, adjacentChestZPosition;

	public float lidAngle;
	public float prevLidAngle;

	public int numUsingPlayers;

	private int ticksSinceSync;
	private String customName;

	@Override
	public int getSizeInventory() {
		return chestContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return chestContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (chestContents[slot] != null) {
			ItemStack is;

			if (chestContents[slot].stackSize <= amount) {
				is = chestContents[slot];
				chestContents[slot] = null;
				onInventoryChanged();
				return is;
			} else {
				is = chestContents[slot].splitStack(amount);

				if (chestContents[slot].stackSize == 0)
					chestContents[slot] = null;

				onInventoryChanged();
				return is;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
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

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return isInvNameLocalized() ? customName : "container.petrifiedWoodChest";
	}

	@Override
	public boolean isInvNameLocalized() {
		return customName != null && customName.length() > 0;
	}

	public void setChestGuiName(String name) {
		customName = name;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items");
		chestContents = new ItemStack[getSizeInventory()];

		if (nbt.hasKey("CustomName"))
			customName = nbt.getString("CustomName");

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < chestContents.length)
				chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < chestContents.length; ++i)
			if (chestContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		nbt.setTag("Items", nbttaglist);

		if (isInvNameLocalized())
			nbt.setString("CustomName", customName);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		adjacentChestChecked = false;
	}

	private void func_90009_a(TileEntityPetrifiedWoodChest chest, int par2) {
		if (chest.isInvalid())
			adjacentChestChecked = false;
		else if (adjacentChestChecked)
			switch (par2) {
				case 0:
					if (adjacentChestZPosition != chest)
						adjacentChestChecked = false;

					break;
				case 1:
					if (adjacentChestXNeg != chest)
						adjacentChestChecked = false;

					break;
				case 2:
					if (adjacentChestZNeg != chest)
						adjacentChestChecked = false;

					break;
				case 3:
					if (adjacentChestXPos != chest)
						adjacentChestChecked = false;
			}
	}

	public void checkForAdjacentChests() {
		if (!adjacentChestChecked) {
			adjacentChestChecked = true;
			adjacentChestZNeg = null;
			adjacentChestXPos = null;
			adjacentChestXNeg = null;
			adjacentChestZPosition = null;

			if (func_94044_a(xCoord - 1, yCoord, zCoord))
				adjacentChestXNeg = (TileEntityPetrifiedWoodChest) worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord);
			if (func_94044_a(xCoord + 1, yCoord, zCoord))
				adjacentChestXPos = (TileEntityPetrifiedWoodChest) worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
			if (func_94044_a(xCoord, yCoord, zCoord - 1))
				adjacentChestZNeg = (TileEntityPetrifiedWoodChest) worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1);
			if (func_94044_a(xCoord, yCoord, zCoord + 1))
				adjacentChestZPosition = (TileEntityPetrifiedWoodChest) worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1);
			if (adjacentChestZNeg != null)
				adjacentChestZNeg.func_90009_a(this, 0);
			if (adjacentChestZPosition != null)
				adjacentChestZPosition.func_90009_a(this, 2);
			if (adjacentChestXPos != null)
				adjacentChestXPos.func_90009_a(this, 1);
			if (adjacentChestXNeg != null)
				adjacentChestXNeg.func_90009_a(this, 3);
		}
	}

	private boolean func_94044_a(int x, int y, int z) {
		Block block = Block.blocksList[worldObj.getBlockId(x, y, z)];
		return block != null && block instanceof BlockPetrifiedChest;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		checkForAdjacentChests();
		++ticksSinceSync;
		float f;

		if (!worldObj.isRemote && numUsingPlayers != 0 && (ticksSinceSync + xCoord + yCoord + zCoord) % 200 == 0) {
			numUsingPlayers = 0;
			f = 5.0F;
			List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(xCoord - f, yCoord - f, zCoord - f, xCoord + 1 + f, yCoord + 1 + f, zCoord + 1 + f));
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityPlayer entityplayer = (EntityPlayer) iterator.next();

				if (entityplayer.openContainer instanceof ContainerPetrifiedWoodChest) {
					IInventory iinventory = ((ContainerPetrifiedWoodChest) entityplayer.openContainer).getLowerChestInventory();

					if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest) iinventory).isPartOfLargeChest(this))
						++numUsingPlayers;
				}
			}
		}

		prevLidAngle = lidAngle;
		f = 0.1F;
		double d0;

		if (numUsingPlayers > 0 && lidAngle == 0.0F && adjacentChestZNeg == null && adjacentChestXNeg == null) {
			double d1 = xCoord + 0.5D;
			d0 = zCoord + 0.5D;

			if (adjacentChestZPosition != null)
				d0 += 0.5D;

			if (adjacentChestXPos != null)
				d1 += 0.5D;

			worldObj.playSoundEffect(d1, yCoord + 0.5D, d0, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
			float f1 = lidAngle;

			if (numUsingPlayers > 0)
				lidAngle += f;
			else
				lidAngle -= f;

			if (lidAngle > 1.0F)
				lidAngle = 1.0F;

			float f2 = 0.5F;

			if (lidAngle < f2 && f1 >= f2 && adjacentChestZNeg == null && adjacentChestXNeg == null) {
				d0 = xCoord + 0.5D;
				double d2 = zCoord + 0.5D;

				if (adjacentChestZPosition != null)
					d2 += 0.5D;

				if (adjacentChestXPos != null)
					d0 += 0.5D;

				worldObj.playSoundEffect(d0, yCoord + 0.5D, d2, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (lidAngle < 0.0F)
				lidAngle = 0.0F;
		}
	}

	@Override
	public boolean receiveClientEvent(int eventId, int data) {
		if (eventId == 1) {
			numUsingPlayers = data;
			return true;
		} else
			return super.receiveClientEvent(eventId, data);
	}

	@Override
	public void openChest() {
		if (numUsingPlayers < 0)
			numUsingPlayers = 0;

		++numUsingPlayers;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, numUsingPlayers);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType().blockID);
	}

	@Override
	public void closeChest() {
		if (getBlockType() != null && getBlockType() instanceof BlockPetrifiedChest) {
			--numUsingPlayers;
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, numUsingPlayers);
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType().blockID);
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return true;
	}

	@Override
	public void invalidate() {
		super.invalidate();
		updateContainingBlockInfo();
		checkForAdjacentChests();
	}
}