package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import erebus.ModFluids;


public class TileEntityKitchenCounter extends TileEntityBasicInventory implements IFluidHandler{
	
	protected final FluidTank honeyTank;
	protected final FluidTank milkTank;
	protected final FluidTank beetleTank;
	protected final FluidTank antiVenomTank;
	
	public TileEntityKitchenCounter instance = this;
	
	public TileEntityKitchenCounter(){
		super(4, "container.kitchenCounter");
		this.honeyTank = new FluidTank(16000);
		this.milkTank = new FluidTank(16000);
		this.beetleTank = new FluidTank(16000);
		this.antiVenomTank = new FluidTank(16000);
	}
	
	public int addHoney(int amount){
		int result = honeyTank.fill(FluidRegistry.getFluidStack("honey", amount), true);
		sendUpdatesToClients();
		return result;
	}
	
	public int addMilk(int amount){
		int result = milkTank.fill(FluidRegistry.getFluidStack("milk", amount), true);
		sendUpdatesToClients();
		return result;
	}
	
	//Don't say his name 3 times!
	public int addBeetleJuice(int amount){
		int result = beetleTank.fill(FluidRegistry.getFluidStack("beetlejuice", amount), true);
		sendUpdatesToClients();
		return result;
	}
	
	public int addAntiVenom(int amount){
		int result = antiVenomTank.fill(FluidRegistry.getFluidStack("antivenom", amount), true);
		sendUpdatesToClients();
		return result;
	}
	
	public int drainHoney(int amount){
		FluidStack fluid = honeyTank.drain(amount, true);
		sendUpdatesToClients();
		return fluid == null ? 0 : fluid.amount;
	}
	
	public int drainMilk(int amount){
		FluidStack fluid = milkTank.drain(amount, true);
		sendUpdatesToClients();
		return fluid == null ? 0 : fluid.amount;
	}
	
	public int drainBeetleJuice(int amount){
		FluidStack fluid = beetleTank.drain(amount, true);
		sendUpdatesToClients();
		return fluid == null ? 0 : fluid.amount;
	}
	
	public int drainAntiVenom(int amount){
		FluidStack fluid = antiVenomTank.drain(amount, true);
		sendUpdatesToClients();
		return fluid == null ? 0 : fluid.amount;
	}
	
	public FluidStack getHoney(){
		if(honeyTank.getFluid() == null){
			honeyTank.setFluid(new FluidStack(ModFluids.honey, 0));
		}
		
		return honeyTank.getFluid();
	}
	
	public FluidStack getMilk(){
		if(milkTank.getFluid() == null){
			milkTank.setFluid(new FluidStack(ModFluids.milk, 0));
		}
		
		return milkTank.getFluid();
	}
	
	public FluidStack getBeetleJuice(){
		if(beetleTank.getFluid() == null){
			beetleTank.setFluid(new FluidStack(ModFluids.beetleJuice, 0));
		}
		
		return beetleTank.getFluid();
	}
	
	public FluidStack getAntiVenom(){
		if(antiVenomTank.getFluid() == null){
			antiVenomTank.setFluid(new FluidStack(ModFluids.antiVenom, 0));
		}
		
		return antiVenomTank.getFluid();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		
		this.honeyTank.readFromNBT(nbt.getCompoundTag("honeyTank"));
		this.milkTank.readFromNBT(nbt.getCompoundTag("milkTank"));
		this.beetleTank.readFromNBT(nbt.getCompoundTag("beetleTank"));
		this.antiVenomTank.readFromNBT(nbt.getCompoundTag("antiVenomTank"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		
		NBTTagCompound honeyTag = new NBTTagCompound();
		NBTTagCompound milkTag = new NBTTagCompound();
		NBTTagCompound beetleTag = new NBTTagCompound();
		NBTTagCompound antiVenomTag = new NBTTagCompound();
		
		this.honeyTank.writeToNBT(honeyTag);
		this.milkTank.writeToNBT(milkTag);
		this.beetleTank.writeToNBT(beetleTag);
		this.antiVenomTank.writeToNBT(antiVenomTag);
		
		nbt.setTag("honeyTank", honeyTag);
		nbt.setTag("milkTank", milkTag);
		nbt.setTag("beetleTank", beetleTag);
		nbt.setTag("antiVenomTank", antiVenomTag);
	}
	
	@Override
	public boolean canUpdate(){
		return true;
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
	
	private void sendUpdatesToClients(){
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null || resource.getFluid() != ModFluids.honey || resource.getFluid() != ModFluids.milk || resource.getFluid() != ModFluids.antiVenom || resource.getFluid() != ModFluids.beetleJuice){
			return 0;
		} else if(resource.getFluid() == ModFluids.honey){
			return honeyTank.fill(resource, doFill);
		} else if(resource.getFluid() == ModFluids.milk){
			return milkTank.fill(resource, doFill);
		} else if(resource.getFluid() == ModFluids.beetleJuice){
			return beetleTank.fill(resource, doFill);
		} else if(resource.getFluid() == ModFluids.antiVenom){
			return antiVenomTank.fill(resource, doFill);
		} else {
			return 0;
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
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		//if(from.)
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return null;
	}
}
