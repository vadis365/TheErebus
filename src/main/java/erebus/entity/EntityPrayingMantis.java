package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPrayingMantis extends EntityMob {
	private static final DataParameter<Float> ALPHA = EntityDataManager.<Float>createKey(EntityPrayingMantis.class, DataSerializers.FLOAT);
	private static final DataParameter<Byte> ANIMATION_BYTE = EntityDataManager.<Byte>createKey(EntityPrayingMantis.class, DataSerializers.BYTE);
	private int attackAnimation;

	public EntityPrayingMantis(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(2.0F, 2.5F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIErebusAttackMelee(this, 0.6D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.4D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ALPHA, 0F);
		dataManager.register(ANIMATION_BYTE, (byte) 0);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.MANTIS_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.MANTIS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	public boolean isOnLadder() {
		return false;//isCollidedHorizontally;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		for (int amount = 0; amount < chance; ++amount)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.CAMO_POWDER.ordinal()), 0.0F);
	}

	@Override
	public void onUpdate() {
		if (!getEntityWorld().isRemote && getAttackTarget() != null) {
			double distanceToTarget = getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
			if (distanceToTarget >= 4.0D)
				setAlpha((24.0F - (float) distanceToTarget) * 0.0208333F);
			if (distanceToTarget < 4.0D)
				setAlpha((24.0F - (float) distanceToTarget) * 0.041666F);
		}
		if (!getEntityWorld().isRemote && getAttackTarget() == null || getAlpha() > 0.8F)
			setAlpha(1.0F);
		if (!getEntityWorld().isRemote) {
			if (attackAnimation < 5 && getAnimationByte() == 0)
				setAttackAnimation(attackAnimation + 1, (byte) 0);
			if (attackAnimation == 5)
				setAttackAnimation(0, (byte) 1);
		}
		super.onUpdate();
	}

	public void setAttackAnimation(int count, byte action) {
		attackAnimation = count;
		dataManager.set(ANIMATION_BYTE, action);
	}

	public byte getAnimationByte() {
		return dataManager.get(ANIMATION_BYTE);
	}

	public void setAlpha(float alpha) {
		dataManager.set(ALPHA, alpha);
	}

	public float getAlpha() {
		return dataManager.get(ALPHA);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setAlpha(nbt.getFloat("mantis_alpha"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setFloat("mantis_alpha", getAlpha());
	}
}