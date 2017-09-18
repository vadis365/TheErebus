package erebus.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketOfferingAltar;
import erebus.network.client.PacketOfferingAltarTimer;
import erebus.recipes.OfferingAltarRecipe;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityOfferingAltar extends TileEntityBasicInventory {
	@SideOnly(Side.CLIENT)
	protected EntityItem ghostItem;
	public int time = 0;
	protected ItemStack output = null;

	private static final int MAX_TIME = 450;

	public TileEntityOfferingAltar() {
		this(4, "offeringAltar");
	}

	protected TileEntityOfferingAltar(int size, String name) {
		super(size, name);
	}

	@SideOnly(Side.CLIENT)
	public EntityItem getItemForRendering(int slot) {
		if (ghostItem == null) {
			ghostItem = new EntityItem(worldObj);
			ghostItem.hoverStart = 0.0F;
		}

		if (getInventory()[slot] == null)
			return null;
		else {
			ghostItem.setEntityItemStack(getInventory()[slot]);
			return ghostItem;
		}
	}

	public void popStack() {
		if (!worldObj.isRemote)
			for (int i = getSizeInventory() - 1; i >= 0; i--)
				if (getInventory()[i] != null) {
					Utils.dropStackNoRandom(worldObj, xCoord, yCoord + 1, zCoord, getInventory()[i].copy());
					getInventory()[i] = null;
					markDirty();
					return;
				}
	}

	public void addStack(ItemStack stack) {
		if (stack == null || stack.stackSize <= 0)
			return;
		if (getInventory()[getSizeInventory() - 1] == null)
			for (int i = 0; i < getSizeInventory() - 1; i++)
				if (getInventory()[i] == null) {
					addStack(i, stack);
					return;
				}
	}

	private void addStack(int slot, ItemStack stack) {
		if (!worldObj.isRemote) {
			getInventory()[slot] = ItemStack.copyItemStack(stack);
			getInventory()[slot].stackSize = 1;
			stack.stackSize--;
			markDirty();
		}
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		if (output == null)
			time = 0;
		else {
			time++;
			PacketPipeline.sendToAll(new PacketOfferingAltarTimer(xCoord, yCoord, zCoord, time));

			if (time == 90 || time == 270 || time == 450) {
				worldObj.playAuxSFX(2005, xCoord, yCoord + 1, zCoord, 4);
				if (time >= MAX_TIME)
					worldObj.playAuxSFX(2004, xCoord, yCoord + 1, zCoord, 0);
			}
			if (time >= MAX_TIME) {
				getInventory()[3] = ItemStack.copyItemStack(output);
				for (int i = 0; i < 3; i++)
					if (getInventory()[i] != null)
						if (--getInventory()[i].stackSize <= 0)
							getInventory()[i] = null;
				time = 0;
				markDirty();
			}
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		if (packet.func_148853_f() == 0)
			readFromNBT(packet.func_148857_g());
	}

	@Override
	public void markDirty() {
		super.markDirty();

		output = OfferingAltarRecipe.getOutput(getInventory()[0], getInventory()[1], getInventory()[2]);

		if (worldObj != null && !worldObj.isRemote) {
			NBTTagCompound nbt = new NBTTagCompound();
			writeToNBT(nbt);
			PacketPipeline.sendToAll(new PacketOfferingAltar(xCoord, yCoord, zCoord, nbt));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 2, yCoord + 3, zCoord + 2);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		time = nbt.getInteger("time");

		markDirty();
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("time", time);
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot != 3;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 3;
	}
}