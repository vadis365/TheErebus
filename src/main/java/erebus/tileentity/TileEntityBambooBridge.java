package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBambooBridge extends TileEntity
{

	public boolean renderSide1, renderSide2;

	public void setRenderSide1(boolean side1)
	{
		renderSide1 = side1;
	}

	public void setRenderSide2(boolean side2)
	{
		renderSide2 = side2;
	}

	public boolean getRenderSide1()
	{
		return renderSide1;
	}

	public boolean getRenderSide2()
	{
		return renderSide2;
	}

	@Override
	public boolean canUpdate()
	{
		return false;
	}

	protected void writeTileToNBT(NBTTagCompound nbt)
	{
		nbt.setBoolean("renderSide1", renderSide1);
		nbt.setBoolean("renderSide2", renderSide2);
	}

	protected void readTileFromNBT(NBTTagCompound nbt)
	{
		renderSide1 = nbt.getBoolean("renderSide1");
		renderSide2 = nbt.getBoolean("renderSide2");
	}

	@Override
	public final void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		writeTileToNBT(nbt);
	}

	@Override
	public final void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		readTileFromNBT(nbt);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound data = new NBTTagCompound();
		writeTileToNBT(data);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, data);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		readTileFromNBT(packet.func_148857_g());
		worldObj.func_147479_m(xCoord, yCoord, zCoord);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
}