package erebus.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

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
			findEnemyToAttack();
			if (active) {
				if (animationTicks == 0)
					worldObj.playSoundEffect(xCoord, yCoord, zCoord, "mob.zombie.say", 0.25F, 0.5F);
				if (animationTicks == 11)
					worldObj.playSoundEffect(xCoord, yCoord, zCoord, "mob.sheep.shear", 0.5F, 0.5F);
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

	@SuppressWarnings("unchecked")
	protected Entity findEnemyToAttack() {
		int meta = getBlockMetadata();
		float x = 0, z = 0;
		if(meta == 4)
			x = -1F/16 * animationTicks;
		if(meta == 5)
			x = 1F/16 * animationTicks;
		if(meta == 2)
			z = -1F/16 * animationTicks;
		if(meta == 3)
			z = 1F/16 * animationTicks;
		List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord + x, yCoord, zCoord + z, xCoord + 1D + x, yCoord + 1D, zCoord + 1D + z));
		if (animationTicks >= 1)
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (entity != null)
					if (entity instanceof EntityPlayer)
						((EntityLivingBase) entity).attackEntityFrom(DamageSource.generic, 2);
			}
		return null;
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