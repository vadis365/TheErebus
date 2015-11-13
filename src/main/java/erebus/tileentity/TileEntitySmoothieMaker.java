package erebus.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.recipes.SmoothieMakerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntitySmoothieMaker extends TileEntityBasicInventory implements IFluidHandler {

	private static final int MAX_TIME = 432;

	private final FluidTank[] tanks = new FluidTank[4];
	private int progress = 0, prevProgress = 0;

	public TileEntitySmoothieMaker() {
		super(5, "container.kitchenCounter");
		for (int i = 0; i < tanks.length; i++)
			tanks[i] = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
	}

	public FluidTank[] getTanks() {
		return tanks;
	}

	public float getBlendProgress() {
		return progress / 12F;
	}

	public float getPrevBlendProgress() {
		return prevProgress / 12F;
	}

	public boolean isBlending() {
		return progress > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		progress = nbt.getInteger("progress");
		if (nbt.hasKey("honeyTank")) { // for backards compat
			tanks[0].readFromNBT(nbt.getCompoundTag("honeyTank"));
			tanks[1].readFromNBT(nbt.getCompoundTag("milkTank"));
			tanks[2].readFromNBT(nbt.getCompoundTag("beetleTank"));
			tanks[3].readFromNBT(nbt.getCompoundTag("antiVenomTank"));
		} else
			readTanksFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("progress", progress);
		writeTanksToNBT(nbt);
	}

	private void writeTanksToNBT(NBTTagCompound nbt) {
		for (int i = 0; i < tanks.length; i++)
			nbt.setTag("tank_" + i, tanks[i].writeToNBT(new NBTTagCompound()));
	}

	private void readTanksFromNBT(NBTTagCompound nbt) {
		for (int i = 0; i < tanks.length; i++)
			tanks[i].readFromNBT(nbt.getCompoundTag("tank_" + i));
	}

	public void writeGUIData(NBTTagCompound nbt) {
		nbt.setInteger("progress", progress);
		writeTanksToNBT(nbt);
	}

	public void readGUIData(NBTTagCompound nbt) {
		progress = nbt.getInteger("progress");
		readTanksFromNBT(nbt);
	}

	public ItemStack fillTankWithBucket(ItemStack bucket) {
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(bucket);
		if (fluid != null && canFill(ForgeDirection.UNKNOWN, fluid.getFluid())) {
			int amountFilled = fill(ForgeDirection.UNKNOWN, fluid, false);
			if (amountFilled == fluid.amount) {
				fill(ForgeDirection.UNKNOWN, fluid, true);
				return FluidContainerRegistry.drainFluidContainer(bucket);
			}
		}

		return bucket;
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			// Interpolation stuff
			prevProgress = progress;
			return;
		}

		ItemStack[] inputs = new ItemStack[4];
		for (int i = 0; i < 4; i++)
			inputs[i] = inventory[i];
		SmoothieMakerRecipe recipe = SmoothieMakerRecipe.getRecipe(getStackInSlot(4), tanks[0], tanks[1], tanks[2], tanks[3], inputs);
		if (recipe != null) {
			progress++;

			if (progress >= MAX_TIME) {
				for (int i = 0; i < 5; i++)
					if (inventory[i] != null)
						if (--inventory[i].stackSize <= 0)
							inventory[i] = null;
				extractFluids(recipe);
				inventory[4] = ItemStack.copyItemStack(recipe.getOutput());
				progress = 0;
				markDirty();
			}
		}
		if (recipe == null || getStackInSlot(4) == null) {
			progress = 0;
			markDirty();
		}
	}

	private void extractFluids(SmoothieMakerRecipe recipe) {
		label: for (FluidStack fluid : recipe.getFluids())
			for (FluidTank tank : tanks)
				if (tank.getFluid() != null && tank.getFluid().isFluidEqual(fluid)) {
					tank.drain(fluid.amount, true);
					continue label;
				}
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
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource == null)
			return 0;
		resource = resource.copy();

		int amountFilled = 0;
		boolean foundTankWithSameFluid = false;
		for (FluidTank tank : tanks)
			if (resource.isFluidEqual(tank.getFluid())) {
				foundTankWithSameFluid = true;
				amountFilled = tank.fill(resource, doFill);
				break;
			}

		resource.amount -= amountFilled;
		if (!foundTankWithSameFluid && amountFilled < resource.amount)
			for (FluidTank tank : tanks)
				if (tank.getFluid() == null) {
					amountFilled += tank.fill(resource, doFill);
					break;
				}

		return amountFilled;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		for (SmoothieMakerRecipe recipe : SmoothieMakerRecipe.getRecipeList())
			for (FluidStack recipeFluid : recipe.getFluids())
				if (recipeFluid.getFluid() == fluid)
					return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidTankInfo[] infos = new FluidTankInfo[tanks.length];
		for (int i = 0; i < infos.length; i++)
			infos[i] = new FluidTankInfo(tanks[i].getFluid(), tanks[i].getCapacity());
		return infos;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 4;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 2, zCoord + 1);
	}
}