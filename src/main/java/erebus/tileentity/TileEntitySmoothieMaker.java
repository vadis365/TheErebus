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
import net.minecraftforge.fluids.capability.FluidTankPropertiesWrapper;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TileEntitySmoothieMaker extends TileEntityBasicInventory implements ITickable {

	private static final int MAX_TIME = 432;
	private IItemHandler itemHandler;
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
		if (recipe == null || getStackInSlot(4).isEmpty() || getStackInSlot(4).getCount() > 1) {
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

	protected IItemHandler createUnSidedHandler() {
		return new InvWrapper(this);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY  || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) (itemHandler == null ? (itemHandler = createUnSidedHandler()) : itemHandler);
		
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            IFluidHandler[] myTanks = getTanks();
           
            return (T) new IFluidHandler() {
                @Override
                public IFluidTankProperties[] getTankProperties() {
                	IFluidTankProperties[] infos = new IFluidTankProperties[tanks.length];
            		for (int i = 0; i < tanks.length; i++)
            			infos[i] = new FluidTankPropertiesWrapper(tanks[i]);
            		return infos;
                }
 
                @Override
                public int fill(FluidStack resource, boolean doFill) {
                    for(IFluidHandler tank : myTanks) {
                        int filled = tank.fill(resource, doFill);
                        if(filled != 0) {
                            return filled;
                        }
                    }
                    return 0;
                }
 
                @Override
                public FluidStack drain(FluidStack resource, boolean doDrain) {
                    for(IFluidHandler tank : myTanks) {
                        FluidStack ret = tank.drain(resource, doDrain);
                        if(ret != null && ret.amount != 0) {
                            return ret;
                        }
                    }
                    return null;
                }
 
                @Override
                public FluidStack drain(int maxDrain, boolean doDrain) {
                    for(IFluidHandler tank : myTanks) {
                        FluidStack ret = tank.drain(maxDrain, doDrain);
                        if(ret != null && ret.amount != 0) {
                            return ret;
                        }
                    }
                    return null;
                }
            };
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
		int[] SLOTS = new int[getSizeInventory()];
		for (int index = 0; index < SLOTS.length; index++)
			SLOTS[index] = index;
		return SLOTS;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 4;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return true;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

}