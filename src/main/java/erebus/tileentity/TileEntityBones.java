package erebus.tileentity;

import erebus.Erebus;
import erebus.network.client.PacketBones;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityBones extends TileEntityBasicInventory {

	public static boolean allowInsertion = false;

	private String owner = "";

	public TileEntityBones() {
		super(86, "container.bones");
	}

	public void setOwner(String name) {
		owner = name;
		if (!getWorld().isRemote)
			Erebus.NETWORK_WRAPPER.sendToAll(new PacketBones(getPos().getX(), getPos().getY(), getPos().getZ(), name));
	}

	public String getOwnerName() {
		return owner;
	}

    public void markForUpdate() {
    	if (this != null && !getWorld().isRemote) {
			final IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
			markDirty();
    	}
    }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readFromNBT(packet.getNbtCompound());
		markForUpdate();
		return;
	}

	@Override
    public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
        return writeToNBT(nbt);
    }

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		owner = data.getString("owner");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setString("owner", owner);
		return data;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if (allowInsertion) {
			int[] SLOTS = new int[getSizeInventory()];
			for (int index = 0; index < SLOTS.length; index++)
				SLOTS[index] = index;
			return SLOTS;
		}
		return new int[0];
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return true;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
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