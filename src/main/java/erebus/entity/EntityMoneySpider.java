package erebus.entity;

import javax.annotation.Nullable;

import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIErebusAttackMelee;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityMoneySpider extends EntityScytodes {
	private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityMoneySpider.class, DataSerializers.VARINT);

	public EntityMoneySpider(World world) {
		super(world);
		setSize(0.6F, 0.4F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SKIN_TYPE, rand.nextInt(3));
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(2, new EntityAIErebusAttackMelee(this, 0.6D, true));
		tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.5D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 8D : 8D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 1D : 1D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
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
	protected float getSoundPitch() {
		return super.getSoundPitch() * 2F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.IN_WALL))
			return false;
		return super.attackEntityFrom(source, damage);
	}

	@Override
	protected Item getDropItem() {
		return Items.GOLD_NUGGET;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (recentlyHit && (rand.nextInt(10) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Items.GOLD_INGOT, 1);
	}

	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		return livingdata;
	}

	public void setSkin(int skinType) {
		dataManager.set(SKIN_TYPE, skinType);
	}
	
	public int getSkin() {
		return dataManager.get(SKIN_TYPE);
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
			setSkin(rand.nextInt(3));
	}
}