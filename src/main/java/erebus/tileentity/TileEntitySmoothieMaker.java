package erebus.tileentity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.inventory.ContainerSmoothieMaker;
import erebus.item.ItemMaterials;
import erebus.recipes.SmoothieMakerRecipe;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

public class TileEntitySmoothieMaker extends TileEntityBasicInventory implements IFluidHandler {

	protected final FluidTank honeyTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
	protected final FluidTank milkTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
	protected final FluidTank beetleTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
	protected final FluidTank antiVenomTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);

	public int time = 0;
	private static final int MAX_TIME = 432;

	public TileEntitySmoothieMaker() {
		super(5, "container.kitchenCounter");
		honeyTank.setFluid(new FluidStack(ModFluids.honey, 0));
		milkTank.setFluid(new FluidStack(ModFluids.milk, 0));
		beetleTank.setFluid(new FluidStack(ModFluids.beetleJuice, 0));
		antiVenomTank.setFluid(new FluidStack(ModFluids.antiVenom, 0));
	}

	public int getBlendProgress() {
		return time / 12;
	}

	public boolean isBlending() {
		return time > 0;
	}

	public int getHoneyAmount() {
		return honeyTank.getFluidAmount();
	}

	public int getMilkAmount() {
		return milkTank.getFluidAmount();
	}

	public int getBeetleJuiceAmount() {
		return beetleTank.getFluidAmount();
	}

	public int getAntiVenomAmount() {
		return antiVenomTank.getFluidAmount();
	}

	public int getTanksFullValue() {
		return honeyTank.getCapacity();
	}

	public int getScaledHoneyAmount(int scale) {
		return honeyTank.getFluid() != null ? (int) ((float) honeyTank.getFluid().amount / (float) honeyTank.getCapacity() * scale) : 0;
	}

	public int getScaledMilkAmount(int scale) {
		return milkTank.getFluid() != null ? (int) ((float) milkTank.getFluid().amount / (float) milkTank.getCapacity() * scale) : 0;
	}

	public int getScaledBeetleJuiceAmount(int scale) {
		return beetleTank.getFluid() != null ? (int) ((float) beetleTank.getFluid().amount / (float) beetleTank.getCapacity() * scale) : 0;
	}

	public int getScaledAntiVenomAmount(int scale) {
		return antiVenomTank.getFluid() != null ? (int) ((float) antiVenomTank.getFluid().amount / (float) antiVenomTank.getCapacity() * scale) : 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		honeyTank.readFromNBT(nbt.getCompoundTag("honeyTank"));
		milkTank.readFromNBT(nbt.getCompoundTag("milkTank"));
		beetleTank.readFromNBT(nbt.getCompoundTag("beetleTank"));
		antiVenomTank.readFromNBT(nbt.getCompoundTag("antiVenomTank"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setTag("honeyTank", honeyTank.writeToNBT(new NBTTagCompound()));
		nbt.setTag("milkTank", milkTank.writeToNBT(new NBTTagCompound()));
		nbt.setTag("beetleTank", beetleTank.writeToNBT(new NBTTagCompound()));
		nbt.setTag("antiVenomTank", antiVenomTank.writeToNBT(new NBTTagCompound()));
	}

	public void getGUIData(int id, int value) {
		switch (id) {
			case 0:
				time = value;
				break;
			case 1:
				if (honeyTank.getFluid() == null)
					honeyTank.setFluid(new FluidStack(ModFluids.honey, value));
				else
					honeyTank.getFluid().amount = value;
				break;
			case 2:
				if (beetleTank.getFluid() == null)
					beetleTank.setFluid(new FluidStack(ModFluids.beetleJuice, value));
				else
					beetleTank.getFluid().amount = value;
				break;
			case 3:
				if (antiVenomTank.getFluid() == null)
					antiVenomTank.setFluid(new FluidStack(ModFluids.antiVenom, value));
				else
					antiVenomTank.getFluid().amount = value;
				break;
			case 4:
				if (milkTank.getFluid() == null)
					milkTank.setFluid(new FluidStack(ModFluids.milk, value));
				else
					milkTank.getFluid().amount = value;
				break;
		}
	}

	public void sendGUIData(ContainerSmoothieMaker counter, ICrafting craft) {
		craft.sendProgressBarUpdate(counter, 0, time);
		craft.sendProgressBarUpdate(counter, 1, honeyTank.getFluid() != null ? honeyTank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(counter, 2, beetleTank.getFluid() != null ? beetleTank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(counter, 3, antiVenomTank.getFluid() != null ? antiVenomTank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(counter, 4, milkTank.getFluid() != null ? milkTank.getFluid().amount : 0);
	}

	public ItemStack fillTankWithBucket(ItemStack bucket) {
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(bucket);
		if (fluid != null) {
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
		if (worldObj.isRemote)
			return;

		ItemStack[] inputs = new ItemStack[4];
		for (int i = 0; i < 4; i++)
			inputs[i] = inventory[i];
		SmoothieMakerRecipe recipe = SmoothieMakerRecipe.getRecipe(milkTank, honeyTank, beetleTank, antiVenomTank, inputs);
		if (recipe != null)
			if (getStackInSlot(4) != null && getStackInSlot(4).getItem() == ModItems.materials && getStackInSlot(4).getItemDamage() == ItemMaterials.DATA.smoothieGlass.ordinal() && getStackInSlot(4).stackSize == 1) {
				time++;

				if (time >= MAX_TIME) {
					for (int i = 0; i < 5; i++)
						if (inventory[i] != null)
							if (--inventory[i].stackSize <= 0)
								inventory[i] = null;
					extractFluids(recipe);
					inventory[4] = ItemStack.copyItemStack(recipe.getOutput());
					time = 0;
					markDirty();
				}
			}
		if (recipe == null || getStackInSlot(4) == null) {
			time = 0;
			markDirty();
		}
	}

	private void extractFluids(SmoothieMakerRecipe recipe) {
		for (FluidStack fluid : recipe.getFluids())
			if (fluid.isFluidEqual(beetleTank.getFluid()))
				beetleTank.drain(fluid.amount, true);
			else if (fluid.isFluidEqual(honeyTank.getFluid()))
				honeyTank.drain(fluid.amount, true);
			else if (fluid.isFluidEqual(milkTank.getFluid()))
				milkTank.drain(fluid.amount, true);
			else if (fluid.isFluidEqual(antiVenomTank.getFluid()))
				antiVenomTank.drain(fluid.amount, true);
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
		else if (resource.getFluid() == ModFluids.honey)
			return honeyTank.fill(resource, doFill);
		else if (resource.getFluid() == ModFluids.milk)
			return milkTank.fill(resource, doFill);
		else if (resource.getFluid() == ModFluids.beetleJuice)
			return beetleTank.fill(resource, doFill);
		else if (resource.getFluid() == ModFluids.antiVenom)
			return antiVenomTank.fill(resource, doFill);
		else
			return 0;
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
		FluidTankInfo[] infos = new FluidTankInfo[4];
		infos[0] = new FluidTankInfo(honeyTank.getFluid(), honeyTank.getCapacity());
		infos[1] = new FluidTankInfo(milkTank.getFluid(), milkTank.getCapacity());
		infos[2] = new FluidTankInfo(beetleTank.getFluid(), beetleTank.getCapacity());
		infos[3] = new FluidTankInfo(antiVenomTank.getFluid(), antiVenomTank.getCapacity());
		return infos;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 2, zCoord + 1);
	}
}