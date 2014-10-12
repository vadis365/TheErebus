package erebus.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import erebus.ModFluids;


public class TileEntityKitchenCounter extends TileEntityBasicInventory implements IFluidHandler{
	
	protected final FluidTank fluidTank;
	public TileEntityKitchenCounter instance = this;
	
	public TileEntityKitchenCounter(){
		super(7, "kitchenCounter");
		this.fluidTank = new FluidTank(16000);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		this.fluidTank.readFromNBT(nbt.getCompoundTag("fluidTank"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		NBTTagCompound fluidTag = new NBTTagCompound();
		this.fluidTank.writeToNBT(fluidTag);
		nbt.setTag("fluidTank", fluidTag);
	}
	
	public FluidTank getFluidTank(){
		return this.fluidTank;
	}
	
	public FluidStack getFluidStackFromTank(){
		return getFluidTank().getFluid();
	}
	
	public Fluid getFluidFromTank(){
		return getFluidStackFromTank().getFluid();
	}
	
	public int getTankAmount(){
		return getFluidTank().getFluidAmount();
	}
	
	public int gaugeLiquidScaled(int par1){
		if(getFluidTank().getFluidAmount() <= 0){
			return 0;
		}
		
		return getFluidTank().getFluidAmount() * par1 / getFluidTank().getCapacity();
	}
	
	public boolean needsFluid(){
		return getFluidTank().getFluidAmount() <= getFluidTank().getCapacity();
	}
	
	@Override
	public boolean canUpdate(){
		return true;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(canFill(from, resource.getFluid())){
			return getFluidTank().fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if((resource == null) || (!resource.isFluidEqual(getFluidTank().getFluid()))){
			return null;
		}
		
		if(!canDrain(from, resource.getFluid())){
			return null;
		}
		
		return getFluidTank().drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return getFluidTank().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(fluid == ModFluids.honey || fluid ==  ModFluids.beetleJuice || fluid == ModFluids.antiVenom || fluid.getBlock() == Blocks.water){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {getFluidTank().getInfo()};
	}
	
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager network, S35PacketUpdateTileEntity packet){
		readFromNBT(packet.func_148857_g());
	}
}
