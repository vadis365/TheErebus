package erebus.tileentity;

import net.minecraft.init.Items;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import erebus.ModFluids;
import erebus.inventory.ContainerKitchenCounter;
import erebus.item.Smoothie.SmoothieType;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketKitchenCounter;
import erebus.network.client.PacketKitchenCounterTimer;
import erebus.recipes.KitchenCounterRecipe;

public class TileEntityKitchenCounter extends TileEntityBasicInventory implements IFluidHandler {

	protected final FluidTank honeyTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
	protected final FluidTank milkTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);;
	protected final FluidTank beetleTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);;
	protected final FluidTank antiVenomTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);;

	public int time = 0;
	private static final int MAX_TIME = 432;

	public TileEntityKitchenCounter instance = this;

	public TileEntityKitchenCounter() {
		super(5, "container.kitchenCounter");
		honeyTank.setFluid(new FluidStack(ModFluids.honey, 0));
		milkTank.setFluid(new FluidStack(ModFluids.milk, 0));
		beetleTank.setFluid(new FluidStack(ModFluids.beetleJuice, 0));
		antiVenomTank.setFluid(new FluidStack(ModFluids.antiVenom, 0));
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	public int getBlendProgress() {
		return time/12;
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

		NBTTagCompound honeyTag = new NBTTagCompound();
		NBTTagCompound milkTag = new NBTTagCompound();
		NBTTagCompound beetleTag = new NBTTagCompound();
		NBTTagCompound antiVenomTag = new NBTTagCompound();

		honeyTank.writeToNBT(honeyTag);
		milkTank.writeToNBT(milkTag);
		beetleTank.writeToNBT(beetleTag);
		antiVenomTank.writeToNBT(antiVenomTag);

		nbt.setTag("honeyTank", honeyTag);
		nbt.setTag("milkTank", milkTag);
		nbt.setTag("beetleTank", beetleTag);
		nbt.setTag("antiVenomTank", antiVenomTag);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager network, S35PacketUpdateTileEntity packet) {
		if (packet.func_148853_f() == 0)
			readFromNBT(packet.func_148857_g());
	}

	private void sendUpdatesToClients() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	public void getGUIData(int id, int value) {
		switch (id) {
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
	
	public void sendGUIData(ContainerKitchenCounter counter, ICrafting craft) {
		craft.sendProgressBarUpdate(counter, 1, honeyTank.getFluid() != null ? honeyTank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(counter, 2, beetleTank.getFluid() != null ? beetleTank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(counter, 3, antiVenomTank.getFluid() != null ? antiVenomTank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(counter, 4, milkTank.getFluid() != null ? milkTank.getFluid().amount : 0);
	}
	
	public ItemStack fillTankWithBucket(ItemStack bucket) {
		if (honeyTank.getFluidAmount() <= honeyTank.getCapacity() - FluidContainerRegistry.BUCKET_VOLUME)
			if (FluidContainerRegistry.isFilledContainer(bucket) && FluidContainerRegistry.getFluidForFilledItem(bucket).getFluid() == ModFluids.honey)
				if (bucket.stackSize == 1) {
					honeyTank.fill(new FluidStack(ModFluids.honey, FluidContainerRegistry.BUCKET_VOLUME), true);
					for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
						if (data.filledContainer.getItem() == bucket.getItem() && data.filledContainer.getItemDamage() == bucket.getItemDamage())
							return data.emptyContainer.copy();
				}
		if (beetleTank.getFluidAmount() <= beetleTank.getCapacity() - FluidContainerRegistry.BUCKET_VOLUME)
			if (FluidContainerRegistry.isFilledContainer(bucket) && FluidContainerRegistry.getFluidForFilledItem(bucket).getFluid() == ModFluids.beetleJuice)
				if (bucket.stackSize == 1) {
					beetleTank.fill(new FluidStack(ModFluids.beetleJuice, FluidContainerRegistry.BUCKET_VOLUME), true);
					for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
						if (data.filledContainer.getItem() == bucket.getItem() && data.filledContainer.getItemDamage() == bucket.getItemDamage())
							return data.emptyContainer.copy();
				}
		if (antiVenomTank.getFluidAmount() <= antiVenomTank.getCapacity() - FluidContainerRegistry.BUCKET_VOLUME)
			if (FluidContainerRegistry.isFilledContainer(bucket) && FluidContainerRegistry.getFluidForFilledItem(bucket).getFluid() == ModFluids.antiVenom)
				if (bucket.stackSize == 1) {
					antiVenomTank.fill(new FluidStack(ModFluids.antiVenom, FluidContainerRegistry.BUCKET_VOLUME), true);
					for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
						if (data.filledContainer.getItem() == bucket.getItem() && data.filledContainer.getItemDamage() == bucket.getItemDamage())
							return data.emptyContainer.copy();
				}
		if (milkTank.getFluidAmount() <= milkTank.getCapacity() - FluidContainerRegistry.BUCKET_VOLUME)
			if (FluidContainerRegistry.isFilledContainer(bucket) && FluidContainerRegistry.getFluidForFilledItem(bucket).getFluid() == ModFluids.milk)
				if (bucket.stackSize == 1) {
					milkTank.fill(new FluidStack(ModFluids.milk, FluidContainerRegistry.BUCKET_VOLUME), true);
					for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
						if (data.filledContainer.getItem() == bucket.getItem() && data.filledContainer.getItemDamage() == bucket.getItemDamage())
							return data.emptyContainer.copy();
				}
		return bucket;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource == null || resource.getFluid() != ModFluids.honey || resource.getFluid() != ModFluids.milk || resource.getFluid() != ModFluids.antiVenom || resource.getFluid() != ModFluids.beetleJuice)
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
	public void markDirty() {
		super.markDirty();
		if (worldObj != null && !worldObj.isRemote) {
			NBTTagCompound nbt = new NBTTagCompound();
			writeToNBT(nbt);
			PacketPipeline.sendToAll(new PacketKitchenCounter(xCoord, yCoord, zCoord, nbt));
			PacketPipeline.sendToAll(new PacketKitchenCounterTimer(xCoord, yCoord, zCoord, time));
		}
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		ItemStack[] inputs = new ItemStack[4];
		for (int i = 0; i < 4; i++)
			inputs[i] = inventory[i];
		ItemStack output = KitchenCounterRecipe.getOutput(inputs);
		if (output != null && canExtractFluid(output))
			if (getStackInSlot(4) != null && getStackInSlot(4).getItem() == Items.glass_bottle) {
				time++;
				PacketPipeline.sendToAll(new PacketKitchenCounterTimer(xCoord, yCoord, zCoord, time));

				if (time == 90 || time == 270 || time == 432)
					worldObj.playAuxSFX(2005, xCoord, yCoord + 1, zCoord, 4);

				if (time >= MAX_TIME) {
					worldObj.playAuxSFX(2005, xCoord, yCoord + 1, zCoord, 0);
					for (int i = 0; i < 5; i++)
						if (inventory[i] != null)
							if (--inventory[i].stackSize <= 0)
								inventory[i] = null;
					extractFluid(output);
					inventory[4] = ItemStack.copyItemStack(output);
					time = 0;
					markDirty();
				}
			}
		if (output == null || getStackInSlot(4) == null ) {
			time = 0;
			markDirty();
		}
	}

	private void extractFluid(ItemStack output) {
		switch(SmoothieType.values()[output.getItemDamage()]) {
		//Tanks below can be any of the 4.
		case greenTeaGrasshopper:
			beetleTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		case moneyHoney:
			honeyTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		case nothingInTheMiddle:
			honeyTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		case greenGiant:
			honeyTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		case seedyGoodness:
			honeyTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		case givinMeTheBlues:
			honeyTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		case hotHotBaby:
			honeyTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		case dontMettleWithTheNettle:
			honeyTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		case liquidGold:
			honeyTank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
			break;
		}	
	}

	private boolean canExtractFluid(ItemStack output) {
		switch(SmoothieType.values()[output.getItemDamage()]) {
		//These tanks have to match the extract tanks.
		case greenTeaGrasshopper:
			if(getBeetleJuiceAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		case moneyHoney:
			if(getHoneyAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		case nothingInTheMiddle:
			if(getHoneyAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		case greenGiant:
			if(getHoneyAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		case seedyGoodness:
			if(getHoneyAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		case givinMeTheBlues:
			if(getHoneyAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		case hotHotBaby:
			if(getHoneyAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		case dontMettleWithTheNettle:
			if(getHoneyAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		case liquidGold:
			if(getHoneyAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
				return true;
			break;
		}
		return false;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot != 3;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
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
