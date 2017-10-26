package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

public class TileEntityErebusAltarEmpty extends TileEntityErebusAltar {

	@Override
	protected void writeTileToNBT(NBTTagCompound nbt) {
	}

	@Override
	protected void readTileFromNBT(NBTTagCompound nbt) {
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return null;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
	}
}