package erebus.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.block.BlockUmberFurnace;
import erebus.inventory.ContainerUmberFurnace;

public class TileEntityUmberFurnace extends TileEntity implements IFluidHandler, ISidedInventory {

	private ItemStack[] inventory = new ItemStack[4];
	private final FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
	private final int BUCKET_SLOT = 0;
	private final int SMELT_SLOT = 1;
	private final int FUEL_SLOT = 2;
	private final int RESULT_SLOT = 3;

	private final int COOK_TIME_BASE = 200;
	private int cookTime = 200;
	private int furnaceBurnTime;
	private int currentItemBurnTime;
	private int furnaceCookTime;

	public TileEntityUmberFurnace() {
		tank.setFluid(new FluidStack(FluidRegistry.LAVA, 0));
	}

	public ItemStack fillTankWithBucket(ItemStack bucket) {
		if (tank.getFluidAmount() <= tank.getCapacity() - FluidContainerRegistry.BUCKET_VOLUME)
			if (FluidContainerRegistry.isFilledContainer(bucket) && FluidContainerRegistry.getFluidForFilledItem(bucket).getFluid() == FluidRegistry.LAVA)
				if (bucket.stackSize == 1) {
					tank.fill(new FluidStack(FluidRegistry.LAVA, FluidContainerRegistry.BUCKET_VOLUME), true);
					for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
						if (data.filledContainer.itemID == bucket.itemID && data.filledContainer.getItemDamage() == bucket.getItemDamage())
							return data.emptyContainer.copy();
				}
		return bucket;
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		// Draining buckets
		inventory[BUCKET_SLOT] = fillTankWithBucket(inventory[BUCKET_SLOT]);

		cookTime = COOK_TIME_BASE - (int) (COOK_TIME_BASE * 0.8F * ((float) tank.getFluidAmount() / (float) tank.getCapacity()));

		// Smelting items
		// Stolen from vanilla furnace.
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;
		if (furnaceBurnTime > 0)
			furnaceBurnTime--;
		if (!worldObj.isRemote) {
			if (furnaceBurnTime == 0 && canSmelt()) {
				currentItemBurnTime = furnaceBurnTime = TileEntityFurnace.getItemBurnTime(inventory[FUEL_SLOT]);
				if (furnaceBurnTime > 0) {
					flag1 = true;
					if (inventory[FUEL_SLOT] != null) {
						inventory[FUEL_SLOT].stackSize--;
						if (inventory[FUEL_SLOT].stackSize == 0)
							inventory[FUEL_SLOT] = inventory[FUEL_SLOT].getItem().getContainerItemStack(inventory[FUEL_SLOT]);
					}
				}
			}
			if (isBurning() && canSmelt()) {
				furnaceCookTime++;
				if (furnaceCookTime >= cookTime) {
					furnaceCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			} else
				furnaceCookTime = 0;
			if (flag != furnaceBurnTime > 0) {
				flag1 = true;
				BlockUmberFurnace.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}
		if (flag1)
			onInventoryChanged();
	}

	private boolean canSmelt() {
		if (inventory[SMELT_SLOT] == null)
			return false;
		else {
			ItemStack is = FurnaceRecipes.smelting().getSmeltingResult(inventory[SMELT_SLOT]);
			if (is == null)
				return false;
			if (inventory[RESULT_SLOT] == null)
				return true;
			if (!inventory[RESULT_SLOT].isItemEqual(is))
				return false;
			int result = inventory[RESULT_SLOT].stackSize + is.stackSize;
			return result <= getInventoryStackLimit() && result <= is.getMaxStackSize();
		}
	}

	private void smeltItem() {
		if (canSmelt()) {
			ItemStack is = FurnaceRecipes.smelting().getSmeltingResult(inventory[SMELT_SLOT]);

			if (inventory[RESULT_SLOT] == null)
				inventory[RESULT_SLOT] = is.copy();
			else if (inventory[RESULT_SLOT].isItemEqual(is))
				inventory[RESULT_SLOT].stackSize += is.stackSize;

			tank.drain(FluidContainerRegistry.BUCKET_VOLUME / 10, true);
			--inventory[SMELT_SLOT].stackSize;

			if (inventory[SMELT_SLOT].stackSize <= 0)
				inventory[SMELT_SLOT] = null;
		}
	}

	public boolean isBurning() {
		return furnaceBurnTime > 0;
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
	public ItemStack decrStackSize(int slot, int size) {
		if (inventory[slot] != null) {
			ItemStack is;
			if (inventory[slot].stackSize <= size) {
				is = inventory[slot];
				inventory[slot] = null;
				return is;
			} else {
				is = inventory[slot].splitStack(size);
				if (inventory[slot].stackSize == 0)
					inventory[slot] = null;
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
	}

	@Override
	public String getInvName() {
		return "container.umberFurnace";
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
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return slot == BUCKET_SLOT ? FluidContainerRegistry.isContainer(is) : slot == FUEL_SLOT ? TileEntityFurnace.isItemFuel(is) : slot == SMELT_SLOT ? FurnaceRecipes.smelting().getSmeltingResult(is) != null : false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? new int[] { RESULT_SLOT, BUCKET_SLOT } : new int[] { BUCKET_SLOT, FUEL_SLOT, SMELT_SLOT };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return isItemValidForSlot(slot, is);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return slot == BUCKET_SLOT ? FluidContainerRegistry.isEmptyContainer(is) : true;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource == null || resource.getFluid() != FluidRegistry.LAVA)
			return 0;

		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

	public int getScaledFluidAmount(int scale) {
		return tank.getFluid() != null ? (int) ((float) tank.getFluid().amount / (float) tank.getCapacity() * scale) : 0;
	}

	public String getFluidAmount() {
		return tank.getFluidAmount() + " mB";
	}

	public void getGUIData(int id, int value) {
		switch (id) {
			case 1:
				if (tank.getFluid() == null)
					tank.setFluid(new FluidStack(FluidRegistry.LAVA, value));
				else
					tank.getFluid().amount = value;
				break;
			case 2:
				furnaceBurnTime = value;
				break;
			case 3:
				furnaceCookTime = value;
				break;
			case 4:
				cookTime = value;
				break;
			case 5:
				currentItemBurnTime = value;
				break;
		}
	}

	public void sendGUIData(ContainerUmberFurnace furnace, ICrafting craft) {
		craft.sendProgressBarUpdate(furnace, 1, tank.getFluid() != null ? tank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(furnace, 2, furnaceBurnTime);
		craft.sendProgressBarUpdate(furnace, 3, furnaceCookTime);
		craft.sendProgressBarUpdate(furnace, 4, cookTime);
		craft.sendProgressBarUpdate(furnace, 5, currentItemBurnTime);
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int scale) {
		if (currentItemBurnTime == 0)
			currentItemBurnTime = cookTime;

		return furnaceBurnTime * scale / currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale) {
		return furnaceCookTime * scale / cookTime;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tank.readFromNBT(data);
		NBTTagList nbttaglist = data.getTagList("Items");
		inventory = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < inventory.length)
				inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}

		currentItemBurnTime = data.getInteger("currentItemBurnTime");
		furnaceBurnTime = data.getInteger("furnaceBurnTime");
		furnaceCookTime = data.getInteger("furnaceCookTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tank.writeToNBT(data);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inventory.length; ++i)
			if (inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		data.setTag("Items", nbttaglist);

		data.setInteger("currentItemBurnTime", currentItemBurnTime);
		data.setInteger("furnaceBurnTime", furnaceBurnTime);
		data.setInteger("furnaceCookTime", furnaceCookTime);
	}
}
