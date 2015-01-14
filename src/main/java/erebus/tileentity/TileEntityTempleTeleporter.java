package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTempleTeleporter extends TileEntity {
	private int targetX, targetY, targetZ;

	@Override
	public boolean canUpdate() {
		return false;
	}

	public void setTargetDestination(int x, int y, int z) {
		targetX = x;
		targetY = y;
		targetZ = z;
		System.out.println("X: "+targetX +" Y: "+targetY+" Z: "+targetZ);
	}
	
	public int getTargetX() {
		return targetX;	
	}
	public int getTargetY() {
		return targetY;	
	}
	public int getTargetZ() {
		return targetZ;	
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setTargetDestination(nbt.getInteger("targetX"), nbt.getInteger("targetY"), nbt.getInteger("targetZ"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("targetX", getTargetX());
		nbt.setInteger("targetY", getTargetY());
		nbt.setInteger("targetZ", getTargetZ());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}
}