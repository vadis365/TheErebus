package erebus.tileentity;

import erebus.ModItems;
import erebus.blocks.BlockLiquifier;
import erebus.items.ItemMaterials;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TileEntityLiquifier extends TileEntityBasicInventory implements ITickable {
	private IItemHandler itemHandler;
    public FluidTankTile tank;
	public boolean active;
	public int operatingTime;
	public int animationTicks, prevAnimationTicks;
    private static final int[] SLOTS = new int[] {0};

	public TileEntityLiquifier() {
		super(1, "Liquifier");
        this.tank = new FluidTankTile(null, Fluid.BUCKET_VOLUME * 8);
        this.tank.setTileEntity(this);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public void update() {
		if (getWorld().isRemote && active) {
			prevAnimationTicks = animationTicks;
			if (animationTicks < 360)
				animationTicks += 18;
			if (animationTicks >= 360) {
				animationTicks -= 360;
				prevAnimationTicks -= 360;
			}
		}

		if (!getWorld().isRemote && getWorld().getBlockState(pos).getBlock() != null) {
			if (getWorld().getBlockState(pos).getValue(BlockLiquifier.POWERED)) {
				boolean isDirty = false;

				if (!getInventory().get(0).isEmpty()) {
					if (canOperate()) {
						++operatingTime;

						if (operatingTime >= 180) {
							operatingTime = 0;
							liquifyItem();
							isDirty = true;
						}
					} else
						operatingTime = 0;
				}

				if (isDirty)
					markDirty();
			}
			else if(operatingTime != 0)
				operatingTime = 0;
		}
	}

	private boolean canOperate() {
		if (getInventory().get(0).isEmpty())
			return false;
		else {
			ItemStack itemstack = getInventory().get(0);
			if (isItemValidForSlot(0, itemstack) && tank.getFluidAmount() <= tank.getCapacity() - 50)
				if(tank.getFluid() == null || tank.getFluid().containsFluid(new FluidStack(FluidRegistry.getFluid("honey"), 0)))
					return true;
			return false;
		}
	}
	
	public void liquifyItem() {
		if (canOperate()) {
			if (tank.getFluid() == null || tank.getFluid().containsFluid(new FluidStack(FluidRegistry.getFluid("honey"), 0))) {
				if (tank.getFluidAmount() <= tank.getCapacity() - 50) {
					tank.fill(new FluidStack(FluidRegistry.getFluid("honey"), 50), true);
					getInventory().get(0).shrink(1);
					if (getInventory().get(0).getCount() <= 0)
						getInventory().set(0, ItemStack.EMPTY);
				}
			}
		}
	}

	public void setActive(boolean isActive) {
		active = isActive;
		getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 3);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("active", active);
		tank.writeToNBT(nbt);
		nbt.setShort("operatingTime", (short) operatingTime);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		active = nbt.getBoolean("active");
		tank.readFromNBT(nbt);
		operatingTime = nbt.getShort("operatingTime");
	}

	@Override
    public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
        return writeToNBT(tag);
    }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new SPacketUpdateTileEntity(getPos(), 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readFromNBT(packet.getNbtCompound());
		tank.onContentsChanged();
		return;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.HONEY_DRIP.ordinal();
	}

	@Override
	public ItemStack removeStackFromSlot(int slot) {
		return null;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, EnumFacing direction) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing direction) {
		return true;
	}

	protected IItemHandler createUnSidedHandler() {
		return new InvWrapper(this);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) (itemHandler == null ? (itemHandler = createUnSidedHandler()) : itemHandler);
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) tank;
		return super.getCapability(capability, facing);
	}

	public int getScaledFluid(int scale) {
		return tank.getFluid() != null ? (int) ((float) tank.getFluidAmount() / (float) tank.getCapacity() * scale) : 0;
	}

	@SideOnly(Side.CLIENT)
	public int getOperationProgressScaled(int time) {
		return operatingTime * time / 180;
	}
}
