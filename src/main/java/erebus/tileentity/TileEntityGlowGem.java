package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGlowGem extends TileEntity {

	public boolean lightOn = true;

	@Override
	public boolean canUpdate() {
		return false;
	}

	public void toggleLight() {
		setIlluminated(!lightOn);
	}

	public void setIlluminated(boolean state) {
		lightOn = state;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, lightOn ? 1 : 0);
	}

	@Override
	public boolean receiveClientEvent(int eventId, int eventData) {
		switch (eventId) {
			case 0:
				lightOn = eventData == 1;
				worldObj.func_147451_t(xCoord, yCoord, zCoord);
				return true;
			default:
				return false;
		}
	}

	@Override
	public final void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("state", lightOn);
	}

	@Override
	public final void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		lightOn = nbt.getBoolean("state");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("state", lightOn);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		lightOn = packet.func_148857_g().getBoolean("state");
	}
}