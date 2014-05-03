package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGlowGem extends TileEntity {
	public boolean lightOn = true;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			if (lightOn)
				lightUp();
			else
				switchOff();
	}

	@SideOnly(Side.CLIENT)
	public void lightUp() {
		worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord, 9);
		worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
	}

	@SideOnly(Side.CLIENT)
	public void switchOff() {
		worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
		worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
	}

	public void toggleLight() {
		if (!lightOn) {
			setIlluminated(true);
			lightUp();
		} else {
			setIlluminated(false);
			switchOff();
		}
	}

	public void setIlluminated(boolean state) {
		lightOn = state;
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
	}

	protected void writeTileToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("state", lightOn);
	}

	protected void readTileFromNBT(NBTTagCompound nbt) {
		lightOn = nbt.getBoolean("state");
	}

}