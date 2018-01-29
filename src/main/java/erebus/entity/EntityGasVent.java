package erebus.entity;

import erebus.Erebus;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityGasVent extends EntityLiving {

	private static final DataParameter<Byte> TYPE = EntityDataManager.<Byte>createKey(EntityGasVent.class, DataSerializers.BYTE);

	public EntityGasVent(World world) {
		super(world);
		setSize(0.5F, 2.5F);
		setEntityInvulnerable(true);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(TYPE, (byte)0);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote) {
			if (ticksExisted > 20)
				setDead();
			if (ticksExisted == 1) {
				if (getFlameType() == 0)
					Erebus.NETWORK_WRAPPER.sendToAllAround((new PacketParticle(ParticleType.GAS_VENT_SWAMP, (float) posX, (float)posY, (float)posZ)), new TargetPoint(this.dimension, (double) posX, (double)posY, (double)posZ, 16D));
				if (getFlameType() == 1)
					Erebus.NETWORK_WRAPPER.sendToAllAround((new PacketParticle(ParticleType.GAS_VENT_VOLCANIC, (float) posX, (float)posY, (float)posZ)), new TargetPoint(this.dimension, (double) posX, (double)posY, (double)posZ, 16D));
			}
		}
	}

	@Override
	protected void collideWithEntity(Entity entity) {
		if (!getEntityWorld().isRemote)
			if (entity instanceof EntityLivingBase && !(entity instanceof EntityGasVent))
				entity.setFire(5);
		setDead();
		super.collideWithEntity(entity);
	}

	public void setFlameType(byte type) {
		dataManager.set(TYPE, type);
	}

	public byte getFlameType() {
		return dataManager.get(TYPE).byteValue();
	}

}