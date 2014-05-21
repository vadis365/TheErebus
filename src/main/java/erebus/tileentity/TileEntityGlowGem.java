package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
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
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 0, lightOn ? 1 : 0);
	}

	@Override
	public boolean receiveClientEvent(int eventId, int eventData) {
		switch (eventId) {
			case 0:
				lightOn = eventData == 1;
				worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
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
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
		lightOn = packet.data.getBoolean("state");
	}
}