package erebus.tileentity;

import erebus.block.bamboo.BlockBambooPipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityBambooPipe extends TileEntity implements ITickable {
    public FluidTankTile tank;

	public TileEntityBambooPipe() {
        this.tank = new FluidTankTile(null, 100);
        this.tank.setTileEntity(this);
	}

	@Override
	public void update() {
		if (getWorld().isRemote)
			return;

		if (getWorld().getBlockState(getPos()) == null || !(getWorld().getBlockState(getPos()).getBlock() instanceof BlockBambooPipe))
			return;
		
		IBlockState state = getWorld().getBlockState(getPos());
		BlockBambooPipe block = (BlockBambooPipe) state.getBlock();
		EnumFacing pipeFacing = state.getValue(block.FACING);

		for (EnumFacing facing : EnumFacing.VALUES) {
			TileEntity tileToFill = getWorld().getTileEntity(pos.offset(facing));
			if (tileToFill != null && facing != pipeFacing && tileToFill.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing.getOpposite())) {
				IFluidHandler recepticle = tileToFill.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing);
				IFluidTankProperties[] tankProperties = recepticle.getTankProperties();
				if (tankProperties != null) {
					for (IFluidTankProperties properties : tankProperties) {
						if (properties.canFill() && properties.getCapacity() > 0) {
							FluidStack contents = properties.getContents();
							if (tank.getFluid() != null) {
								if (contents == null || contents.amount <= properties.getCapacity() - tank.getFluid().amount && contents.containsFluid(new FluidStack(tank.getFluid(), 0))) {
									recepticle.fill(tank.drain(new FluidStack(tank.getFluid(), tank.getFluid().amount), true), true);
									markDirty();
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readFromNBT(packet.getNbtCompound());
		tank.onContentsChanged();
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
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		tank.readFromNBT(tagCompound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tank.writeToNBT(tagCompound);
		return tagCompound;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) tank;
		return super.getCapability(capability, facing);
	}

}

