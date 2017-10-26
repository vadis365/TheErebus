package erebus.entity;

import javax.annotation.Nullable;

import erebus.Erebus;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketParticle.ParticleType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityBombardierBeetleLarva extends EntityBeetleLarva implements IMob {
	private static final DataParameter<Integer> INFLATE_SIZE = EntityDataManager.<Integer>createKey(EntityBombardierBeetleLarva.class, DataSerializers.VARINT);

	public EntityBombardierBeetleLarva(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(INFLATE_SIZE, new Integer(0));
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.5D, false));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getEntityWorld().isRemote) {
			if (getInflateSize() <= 0)
				setInflateSize(0);
			if (getInflateSize() >= 100)
				explode();
			if (getAttackTarget() == null)
				setInflateSize(getInflateSize() - 2);
			if (getAttackTarget() != null) {
				float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
				if (getInflateSize() < 100 && distance <= 4)
					setInflateSize(getInflateSize() + 2);
				if (getInflateSize() < 100 && distance > 4)
					setInflateSize(getInflateSize() - 2);
			}
		}
	}

	private void explode() {
		Explosion explosion = new Explosion(getEntityWorld(), this, posX, posY, posZ, 3F, true, getEntityWorld().getGameRules().getBoolean("mobGriefing"));
		explosion.doExplosionA();
		Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.BEETLE_LARVA_SQUISH, (float) posX, (float)posY, (float)posZ));
		getEntityWorld().playSound((EntityPlayer)null, getPosition(), getJumpedOnSound(), SoundCategory.NEUTRAL, 1.0F, 0.5F);
		getEntityWorld().playSound((EntityPlayer)null, getPosition(), getDeathSound(), SoundCategory.NEUTRAL, 1.0F, 0.7F);
		setDead();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (entity != null && getDistance(entity) <= 1.5F && entity.getEntityBoundingBox().maxY > getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < getEntityBoundingBox().maxY)
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
		return super.attackEntityAsMob(entity);
	}

	public void setInflateSize(int size) {
		dataManager.set(INFLATE_SIZE, size);
	}

	public int getInflateSize() {
		return dataManager.get(INFLATE_SIZE);
	}

    @Nullable
	@Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		setLarvaType((byte) 4);
        return super.onInitialSpawn(difficulty, livingdata);
    }
}
