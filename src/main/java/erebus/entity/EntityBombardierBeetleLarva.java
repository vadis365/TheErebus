package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;

public class EntityBombardierBeetleLarva extends EntityBeetleLarva {

	public EntityBombardierBeetleLarva(World world) {
		super(world);
		setTame((byte) 4);
		tasks.addTask(6, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.5D, false));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, new Integer(0));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (getInflateSize() <= 0)
				setInflateSize(0);
			if (getInflateSize() >= 100)
				explode();
			if (getAttackTarget() == null)
				setInflateSize(getInflateSize() - 2);
			if (getAttackTarget() != null) {
				float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
				if (getInflateSize() < 100 && distance <= 4)
					setInflateSize(getInflateSize() + 2);
				if (getInflateSize() < 100 && distance > 4)
					setInflateSize(getInflateSize() - 2);
			}
		}
	}

	private void explode() {
		Explosion explosion = new Explosion(worldObj, this, posX, posY, posZ, 3F);
		explosion.doExplosionA();
		PacketPipeline.sendToAllAround(this, 64D, new PacketParticle(this, ParticleType.BEETLE_LARVA_SQUISH));
		worldObj.playSoundEffect(posX, posY, posZ, getJumpedOnSound(), 1.0F, 0.5F);
		worldObj.playSoundEffect(posX, posY, posZ, getDeathSound(), 1.0F, 0.7F);
		setDead();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (entity != null && getDistanceToEntity(entity) <= 1.5F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1);
		return super.attackEntityAsMob(entity);
	}

	public void setInflateSize(int size) {
		dataWatcher.updateObject(20, Integer.valueOf(size));
	}

	public int getInflateSize() {
		return dataWatcher.getWatchableObjectInt(20);
	}
}
