package erebus.entity;

import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityGasVent extends EntityLiving {

	public EntityGasVent(World world) {
		super(world);
		setSize(0.5F, 2.5F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (ticksExisted > 20)
				setDead();
			if (ticksExisted == 1) {
				if (getFlameType() == 0)
					PacketPipeline.sendToAllAround(this, 16D, new PacketParticle(this, ParticleType.GAS_VENT_SWAMP));
				if (getFlameType() == 1)
					PacketPipeline.sendToAllAround(this, 16D, new PacketParticle(this, ParticleType.GAS_VENT_VOLCANIC));
			}
		}
	}

	@Override
	public boolean isEntityInvulnerable() {
		return true;
	}

	@Override
	protected void collideWithEntity(Entity entity) {
		if (!worldObj.isRemote)
			if (entity instanceof EntityLivingBase && !(entity instanceof EntityGasVent))
				entity.setFire(5);
		setDead();
		super.collideWithEntity(entity);
	}

	public void setFlameType(byte type) {
		dataWatcher.updateObject(16, Byte.valueOf(type));
	}

	public byte getFlameType() {
		return dataWatcher.getWatchableObjectByte(16);
	}

}