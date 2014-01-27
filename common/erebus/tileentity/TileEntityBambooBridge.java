package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBambooBridge extends TileEntity {
	public byte renderSide1;
	public byte renderSide2;

	public void setRenderSide1(byte side1) {
		renderSide1 = side1;
	}

	public void setRenderSide2(byte side2) {
		renderSide2 = side2;
	}

	public byte getRenderSide1() {
		return renderSide1;
	}

	public byte getRenderSide2() {
		return renderSide2;
	}
	
	@Override
	public boolean canUpdate(){
		return false;
	}

	protected void writeTileToNBT(NBTTagCompound nbt) {
		nbt.setByte("renderSide1", renderSide1);
		nbt.setByte("renderSide2", renderSide2);
	}

	protected void readTileFromNBT(NBTTagCompound nbt) {
		renderSide1 = nbt.getByte("renderSide1");
		renderSide2 = nbt.getByte("renderSide2");
	}

	@Override
	public final void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeTileToNBT(nbt);
	}

	@Override
	public final void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readTileFromNBT(nbt);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound data = new NBTTagCompound();
		writeTileToNBT(data);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, data);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
		readTileFromNBT(packet.data);
		worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
}