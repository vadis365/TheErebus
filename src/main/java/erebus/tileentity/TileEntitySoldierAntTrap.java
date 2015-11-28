package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySoldierAntTrap extends TileEntity {
	
	public int animationTicks;
	public boolean active;

	@Override
    public boolean canUpdate() {
        return true;
    }

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (active) {
				if (animationTicks == 0)
					worldObj.playSoundEffect(xCoord, yCoord, zCoord, "mob.zombie.say", 1.0F, 0.5F);
				if (animationTicks == 11)
					worldObj.playSoundEffect(xCoord, yCoord, zCoord, "mob.sheep.shear", 2.0F, 0.5F);
				if (animationTicks <= 16)
					animationTicks++;
				if (animationTicks == 16)
					setActive(false);
			}
			if (!active) {
				if (animationTicks >= 1)
					animationTicks--;
				if (animationTicks == 0)
					if (worldObj.rand.nextInt(50) == 0)
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