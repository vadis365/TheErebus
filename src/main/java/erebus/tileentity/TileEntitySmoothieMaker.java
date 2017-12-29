package erebus.tileentity;

import erebus.recipes.SmoothieMakerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySmoothieMaker extends TileEntityBasicInventory implements ITickable {

	private static final int MAX_TIME = 432;

	private final FluidTank[] tanks = new FluidTank[4];
	private int progress = 0, prevProgress = 0;

	public TileEntitySmoothieMaker() {
		super(5, "container.kitchenCounter");
		for (int i = 0; i < tanks.length; i++)
			tanks[i] = new FluidTank(Fluid.BUCKET_VOLUME * 16);
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
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("progress", progress);
		writeTanksToNBT(nbt);
		return nbt;
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

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readFromNBT(packet.getNbtCompound());
		return;
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new SPacketUpdateTileEntity(getPos(), 0, tag);
	}

	@Override
    public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
        return writeToNBT(tag);
    }

	@Override
	public void update() {
		if (getWorld().isRemote) {
			// Interpolation stuff
			prevProgress = progress;
			return;
		}

		ItemStack[] inputs = new ItemStack[4];
		for (int i = 0; i < 4; i++)
			inputs[i] = getInventory().get(i);
		SmoothieMakerRecipe recipe = SmoothieMakerRecipe.getRecipe(getStackInSlot(4), tanks[0], tanks[1], tanks[2], tanks[3], inputs);
		if (recipe != null) {
			progress++;

			if (progress >= MAX_TIME) {
				for (int i = 0; i < 5; i++)
					if (!getInventory().get(i).isEmpty())
						getInventory().get(i).shrink(1);

				extractFluids(recipe);
				getInventory().set(4, recipe.getOutput().copy());
				progress = 0;
				markDirty();
			}
		}
		if (recipe == null || getStackInSlot(4).isEmpty()) {
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
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {

				if (facing == EnumFacing.NORTH)
					return (T) tanks[0];
				else if (facing == EnumFacing.EAST)
					return (T) tanks[1];
				else if (facing == EnumFacing.SOUTH)
					return (T) tanks[2];
				else if (facing == EnumFacing.WEST)
					return (T) tanks[3];

		}
		return super.getCapability(capability, facing);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 4;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

}