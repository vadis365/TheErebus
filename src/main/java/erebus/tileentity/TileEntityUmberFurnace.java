package erebus.tileentity;

import erebus.blocks.BlockUmberFurnace;
import erebus.inventory.ContainerUmberFurnace;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileEntityUmberFurnace extends TileEntityBasicInventory implements ITickable {

	private final FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * 16);
	private final int BUCKET_SLOT = 0;
	private final int SMELT_SLOT = 1;
	private final int FUEL_SLOT = 2;
	private final int RESULT_SLOT = 3;

	private final int COOK_TIME_BASE = 200;
	private int cookTime = 200;
	private int furnaceBurnTime;
	private int currentItemBurnTime;
	private int furnaceCookTime, prevFurnaceCookTime;

	public TileEntityUmberFurnace() {
		super(4, "container.umberFurnace");
		tank.setFluid(new FluidStack(FluidRegistry.LAVA, 0));
	}

	public ItemStack fillTankWithBucket(ItemStack bucket) {
		if (tank.getFluidAmount() <= tank.getCapacity() - Fluid.BUCKET_VOLUME)
			if (FluidUtil.getFluidHandler(bucket) != null && FluidUtil.getFluidContained(bucket) != null && FluidUtil.getFluidContained(bucket).getFluid() == FluidRegistry.LAVA)
				if (bucket.getCount() == 1) {
					tank.fill(new FluidStack(FluidRegistry.LAVA, Fluid.BUCKET_VOLUME), true);
					IFluidHandlerItem cap = bucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
					cap.drain(new FluidStack(FluidUtil.getFluidContained(bucket), Fluid.BUCKET_VOLUME), true);
					return FluidUtil.getFluidHandler(bucket).getContainer().copy();
				}
		return bucket;
	}

	@Override
	public void update() {
		if (getWorld().isRemote) {
			// Interpolation stuff
			prevFurnaceCookTime = furnaceCookTime;
			return;
		}

		// Draining buckets
		getInventory().set(BUCKET_SLOT, fillTankWithBucket(getInventory().get(BUCKET_SLOT)));

		cookTime = COOK_TIME_BASE - (int) (COOK_TIME_BASE * 0.8F * ((float) tank.getFluidAmount() / (float) tank.getCapacity()));

		// Smelting items
		// Stolen from vanilla furnace.
		boolean isBurning = furnaceBurnTime > 0;
		boolean isDirty = false;
		if (furnaceBurnTime > 0)
			furnaceBurnTime--;
		if (!getWorld().isRemote) {
			if (furnaceBurnTime == 0 && canSmelt()) {
				currentItemBurnTime = furnaceBurnTime = TileEntityFurnace.getItemBurnTime(getInventory().get(FUEL_SLOT));
				if (furnaceBurnTime > 0) {
					isDirty = true;
					if (!getInventory().get(FUEL_SLOT).isEmpty()) {
						getInventory().get(FUEL_SLOT).shrink(1);
						if (getInventory().get(FUEL_SLOT).getCount() == 0)
							getInventory().set(FUEL_SLOT, getInventory().get(FUEL_SLOT).getItem().getContainerItem(getInventory().get(FUEL_SLOT)));
					}
				}
			}
			if (isBurning() && canSmelt()) {
				furnaceCookTime++;
				if (furnaceCookTime >= cookTime) {
					furnaceCookTime = 0;
					smeltItem();
					isDirty = true;
				}
			} else
				furnaceCookTime = 0;
			if (isBurning != furnaceBurnTime > 0) {
				isDirty = true;
				BlockUmberFurnace.setState(furnaceBurnTime > 0, world, pos);
			}
		}
		if (isDirty)
			markDirty();
	}

	private boolean canSmelt() {
		if (getInventory().get(SMELT_SLOT).isEmpty())
			return false;
		else {
			ItemStack is = FurnaceRecipes.instance().getSmeltingResult(getInventory().get(SMELT_SLOT));
			if (is.isEmpty())
				return false;
			if (getInventory().get(RESULT_SLOT).isEmpty())
				return true;
			if (!getInventory().get(RESULT_SLOT).isItemEqual(is))
				return false;
			int result = getInventory().get(RESULT_SLOT).getCount() + is.getCount();
			return result <= getInventoryStackLimit() && result <= is.getMaxStackSize();
		}
	}

	private void smeltItem() {
		if (canSmelt()) {
			ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(getInventory().get(SMELT_SLOT));

			if (getInventory().get(RESULT_SLOT).isEmpty())
				getInventory().set(RESULT_SLOT, stack.copy());
			else if (getInventory().get(RESULT_SLOT).isItemEqual(stack))
				getInventory().get(RESULT_SLOT).grow(stack.getCount());

			tank.drain(Fluid.BUCKET_VOLUME / 10, true);
			getInventory().get(SMELT_SLOT).shrink(1);

			if (getInventory().get(SMELT_SLOT).getCount() <= 0)
				getInventory().set(SMELT_SLOT, ItemStack.EMPTY);
		}
	}

	public boolean isBurning() {
		return furnaceBurnTime > 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return slot == BUCKET_SLOT ? FluidUtil.getFluidHandler(is) != null : slot == FUEL_SLOT ? TileEntityFurnace.isItemFuel(is) : slot == SMELT_SLOT ? !FurnaceRecipes.instance().getSmeltingResult(is).isEmpty() : false;
	}

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? new int[] { RESULT_SLOT, BUCKET_SLOT } : new int[] { BUCKET_SLOT, FUEL_SLOT, SMELT_SLOT };
	}

    @Override
    public boolean canExtractItem(int slot, ItemStack is, EnumFacing direction) {
		return slot == BUCKET_SLOT ? FluidUtil.getFluidHandler(is) != null : true;
	}

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, EnumFacing direction) {
        return isItemValidForSlot(slot, itemstack);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return  ItemStack.EMPTY;
    }

	protected IItemHandler createUnSidedHandler() {
		return new InvWrapper(this);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY  || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	IItemHandler handlerTop = new SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
	IItemHandler handlerBottom = new SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
	IItemHandler handlerSide = new SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

	@Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		  if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
	            if (facing == EnumFacing.DOWN)
	                return (T) handlerBottom;
	            else if (facing == EnumFacing.UP)
	                return (T) handlerTop;
	            else
	                return (T) handlerSide;
		
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            IFluidHandler myTanks = tank;
           
            return (T) new IFluidHandler() {
                @Override
                public IFluidTankProperties[] getTankProperties() {
                    return null; //don't think that's needed
                }
 
                @Override
                public int fill(FluidStack resource, boolean doFill) {
            		if (resource == null || resource.getFluid() != FluidRegistry.LAVA)
            			return 0;

            		return tank.fill(resource, doFill);
                }
 
                @Override
                public FluidStack drain(FluidStack resource, boolean doDrain) {
                        FluidStack ret = tank.drain(resource, doDrain);
                        if(ret != null && ret.amount != 0) {
                            return ret;
                        }

                    return null;
                }
 
                @Override
                public FluidStack drain(int maxDrain, boolean doDrain) {
                        FluidStack ret = tank.drain(maxDrain, doDrain);
                        if(ret != null && ret.amount != 0) {
                            return ret;
                        }
                    return null;
                }
            };
        }
        return super.getCapability(capability, facing);
    }

	public int getScaledFluidAmount(float scale) {
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

	public void sendGUIData(ContainerUmberFurnace furnace, IContainerListener listener) {
		listener.sendWindowProperty(furnace, 1, tank.getFluid() != null ? tank.getFluid().amount : 0);
		listener.sendWindowProperty(furnace, 2, furnaceBurnTime);
		listener.sendWindowProperty(furnace, 3, furnaceCookTime);
		listener.sendWindowProperty(furnace, 4, cookTime);
		listener.sendWindowProperty(furnace, 5, currentItemBurnTime);
	}

	@SideOnly(Side.CLIENT)
	public float getBurnTimeRemainingScaled(int scale) {
		if (currentItemBurnTime == 0)
			currentItemBurnTime = cookTime;

		return furnaceBurnTime * scale / (float) currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public float getCookProgressScaled(int scale) {
		return furnaceCookTime * scale / (float) cookTime;
	}

	@SideOnly(Side.CLIENT)
	public float getPrevCookProgressScaled(int scale) {
		return prevFurnaceCookTime * scale / (float) cookTime;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tank.readFromNBT(data);

		currentItemBurnTime = data.getInteger("currentItemBurnTime");
		furnaceBurnTime = data.getInteger("furnaceBurnTime");
		furnaceCookTime = data.getInteger("furnaceCookTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tank.writeToNBT(data);

		data.setInteger("currentItemBurnTime", currentItemBurnTime);
		data.setInteger("furnaceBurnTime", furnaceBurnTime);
		data.setInteger("furnaceCookTime", furnaceCookTime);
		return data;
	}

}