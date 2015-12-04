package erebus.tileentity;

import erebus.entity.EntitySporeJet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPuffShroom extends TileEntity {

	public int animationTicks, prevAnimationTicks;
	public boolean active;

	@Override
	public void updateEntity() {
		prevAnimationTicks = animationTicks;

		if (!worldObj.isRemote) {
			if (active) {
				if (animationTicks == 12)
					if (worldObj.isAirBlock(xCoord, yCoord + 1, zCoord)) {
						EntitySporeJet jet = new EntitySporeJet(worldObj);
						jet.setPosition(xCoord + 0.5D, yCoord + 1.5D, zCoord + 0.5D);
						worldObj.spawnEntityInWorld(jet);
						worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "erebus:spraycansound", 0.5F, 1F);
					}
				if (animationTicks <= 16)
					animationTicks++;
				if (animationTicks == 16)
					setActive(false);
			}
			if (!active) {
				if (animationTicks >= 1)
					animationTicks--;
				if (animationTicks == 0)
					if (worldObj.rand.nextInt(140) == 0)
						setActive(true);
			}
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	public void setActive(boolean isActive) {
		active = isActive;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("animationTicks", animationTicks);
		nbt.setBoolean("active", active);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		animationTicks = nbt.getInteger("animationTicks");
		active = nbt.getBoolean("active");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("animationTicks", animationTicks);
		nbt.setBoolean("active", active);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		animationTicks = packet.func_148857_g().getInteger("animationTicks");
		active = packet.func_148857_g().getBoolean("active");
	}
}