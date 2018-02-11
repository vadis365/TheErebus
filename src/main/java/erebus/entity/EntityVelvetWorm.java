package erebus.entity;

import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityVelvetWorm extends EntityMob {
	private static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntityVelvetWorm.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> INFLATE_SIZE = EntityDataManager.<Integer>createKey(EntityVelvetWorm.class, DataSerializers.VARINT);

	public EntityVelvetWorm(World world) {
		super(world);
		setSize(2F, 0.7F);
		setPathPriority(PathNodeType.WATER, -8F);
		experienceValue = 15;
		stepHeight = 1;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(INFLATE_SIZE, new Integer(0));
		dataManager.register(TYPE, new Integer(rand.nextInt(2)));
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.7D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 4.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? getAttackStrength() : getAttackStrength() * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getAttackTarget() != null) {
			faceEntity(getAttackTarget(), 90F, 90F);
			float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
			if (getInflateSize() < 100 && distance > 3)
				setInflateSize(getInflateSize() + 2);
			if (getInflateSize() >= 100 && distance > 3)
				shootGooBall(getAttackTarget(), distance);
			if (getInflateSize() == 0)
				;
			forceCollideWithPlayer(getAttackTarget(), distance);
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public void setInWeb() {
	}

	protected SoundEvent getWebSlingThrowSound() {
		return ModSounds.WEBSLING_THROW;
	}

	public double getAttackStrength() {
		switch (getEntityWorld().getDifficulty()) {
			default:
				return 4.0D;
			case EASY:
				return 4.0D;
			case NORMAL:
				return 5.0D;
			case HARD:
				return 6.0D;
		}
	}

	@Override
	protected void dropFewItems(boolean hit, int looting) {
		int chanceFiftyFifty = rand.nextInt(2) + 1;

		dropItem(Items.SLIME_BALL, chanceFiftyFifty + looting);
	}

	protected void shootGooBall(Entity entity, float distance) {
		if (distance < 16.0F)
			if (entity instanceof EntityPlayer) {
				double targetX = entity.posX - posX;
				double targetY = entity.getEntityBoundingBox().minY + (double) (entity.height) - (posY + (double) (height));
				double targetZ = entity.posZ - posZ;
				getEntityWorld().playSound((EntityPlayer)null, getPosition(), getWebSlingThrowSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
				setInflateSize(0);
				EntityGooBall gooBall = new EntityGooBall(getEntityWorld(), this);
				gooBall.posY = posY + height + 0.3D;
				gooBall.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
				getEntityWorld().spawnEntity(gooBall);
			}
	}

	public void forceCollideWithPlayer(EntityLivingBase entity, float distance) {
		if (distance > 1.0F && distance < 6.0F)
			if (onGround) {
				double distanceX = entity.posX - posX;
				double distanceZ = entity.posZ - posZ;
				float squareRoot = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ);
				motionX = distanceX / squareRoot * 0.5D * 0.300000011920929D + motionX * 0.10000000298023224D;
				motionZ = distanceZ / squareRoot * 0.5D * 0.300000011920929D + motionZ * 0.10000000298023224D;
				motionY = 0D;
			}
	}

	public void setInflateSize(int size) {
		dataManager.set(INFLATE_SIZE, size);
	}

	public int getInflateSize() {
		return dataManager.get(INFLATE_SIZE);
	}

	public void setSkin(int skinType) {
		dataManager.set(TYPE, skinType);
	}

	public int getSkin() {
		return dataManager.get(TYPE);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("skin", getSkin());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("skin"))
			setSkin(nbt.getInteger("skin"));
		else
			setSkin(rand.nextInt(2));
	}
}
