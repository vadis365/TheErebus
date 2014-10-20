package erebus.tileentity;

import net.minecraft.item.ItemStack;
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
import erebus.network.PacketPipeline;
import erebus.network.client.PacketKitchenCounter;
import erebus.network.client.PacketKitchenCounterTimer;
import erebus.recipes.KitchenCounterRecipe;


public class TileEntityKitchenCounter extends TileEntityBasicInventory implements IFluidHandler{
	
	protected final FluidTank honeyTank;
	protected final FluidTank milkTank;
	protected final FluidTank beetleTank;
	protected final FluidTank antiVenomTank;
	
	protected ItemStack output = null;
	public int time = 0;
	
	private static final int MAX_TIME = 450;
	
	public TileEntityKitchenCounter instance = this;
	
	public TileEntityKitchenCounter(){
		super(5, "container.kitchenCounter");
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
	
	public int getHoneyAmount(){
		return honeyTank.getFluidAmount();
	}
	
	public int getMilkAmount(){
		return milkTank.getFluidAmount();
	}
	
	public int getBeetleJuiceAmount(){
		return beetleTank.getFluidAmount();
	}
	
	public int getAntiVenomAmount(){
		return antiVenomTank.getFluidAmount();
	}
	
	public int getTanksFullValue(){
		return honeyTank.getCapacity();
	}
	
	public int getScaledHoneyAmount(int scale){
		return honeyTank.getFluid() != null ? (int) ((float) honeyTank.getFluid().amount / (float) honeyTank.getCapacity() * scale) : 0;
	}
	
	public int getScaledMilkAmount(int scale){
		return milkTank.getFluid() != null ? (int) ((float) milkTank.getFluid().amount / (float) milkTank.getCapacity() * scale) : 0;
	}
	
	public int getScaledBeetleJuiceAmount(int scale){
		return beetleTank.getFluid() != null ? (int) ((float) beetleTank.getFluid().amount / (float) beetleTank.getCapacity() * scale) : 0;
	}
	
	public int getScaledAntiVenomAmount(int scale){
		return antiVenomTank.getFluid() != null ? (int) ((float) antiVenomTank.getFluid().amount / (float) antiVenomTank.getCapacity() * scale) : 0;
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
		if(packet.func_148853_f() == 0){
			readFromNBT(packet.func_148857_g());
		}
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
	public void markDirty(){
		super.markDirty();
		
		output = KitchenCounterRecipe.getOutput(inventory[0], inventory[1], inventory[2], inventory[4]);
		
		if(worldObj != null && !worldObj.isRemote){
			NBTTagCompound nbt = new NBTTagCompound();
			writeToNBT(nbt);
			PacketPipeline.sendToAll(new PacketKitchenCounter(xCoord, yCoord, zCoord, nbt));
		}
	}
	
	@Override
	public void updateEntity(){
		if(worldObj.isRemote){
			return;
		}
		
		if(output == null){
			time = 0;
		} else {
			time++;
			PacketPipeline.sendToAll(new PacketKitchenCounterTimer(xCoord, yCoord, zCoord, time));
			
			if(time == 90 || time == 270 || time == 450){
				worldObj.playAuxSFX(2005, xCoord, yCoord + 1, zCoord, 4);
				
				if(time >= MAX_TIME){
					worldObj.playAuxSFX(2005, xCoord, yCoord + 1, zCoord, 0);
				}
			}
			
			if(time >= MAX_TIME){
				inventory[4] = ItemStack.copyItemStack(output);
				
				for(int c = 0; c < 4; c++){
					if(inventory[c] != null){
						if(--inventory[c].stackSize <= 0){
							inventory[c] = null;
						}
					}
				}
				
				time = 0;
				markDirty();
			}
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack){
		return slot != 3;
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side){
		return slot == 3;
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
