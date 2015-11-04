package erebus.tileentity;

import erebus.network.AbstractPacket;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketBones;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityBones extends TileEntityBasicInventory {

	private String owner = ""; // could be expanded to contain random names

	public TileEntityBones() {
		super(86, "container.bones");
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[0];
	}

	public void setOwner(String name) {
		owner = name;
		if (!worldObj.isRemote)
			PacketPipeline.sendToAll(getPacket());
	}

	public String getOwnerName() {
		return owner;
	}

	public AbstractPacket getPacket() {
		return new PacketBones(xCoord, yCoord, zCoord, owner);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		NBTTagCompound nbt = packet.func_148857_g();
		if (packet.func_148853_f() == 0)
			readFromNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		owner = data.getString("owner");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setString("owner", owner);
	}
}