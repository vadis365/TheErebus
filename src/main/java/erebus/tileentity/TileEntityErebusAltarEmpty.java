package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityErebusAltarEmpty extends TileEntityErebusAltar {

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	protected void writeTileToNBT(NBTTagCompound nbt) {
	}

	@Override
	protected void readTileFromNBT(NBTTagCompound nbt) {
	}

	@Override
	public Packet getDescriptionPacket() {
		return null;
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
	}
}