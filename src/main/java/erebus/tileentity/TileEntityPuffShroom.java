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
import erebus.entity.EntitySporeJet;

public class TileEntityPuffShroom extends TileEntity {

	public int animationTicks, prevAnimationTicks;
	public boolean active;

	@Override
	public void updateEntity() {
		prevAnimationTicks = animationTicks;

		if (!worldObj.isRemote) {
			findEnemyToAttack();
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
			if (!active)
				if (animationTicks >= 1)
					animationTicks--;

			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	@SuppressWarnings("unchecked")
	protected Entity findEnemyToAttack() {
		List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D).expand(3D, 2D, 3D));
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (entity != null)
					if (entity instanceof EntityPlayer)
						if (!active && animationTicks == 0)
							setActive(true);
			}
		return null;
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