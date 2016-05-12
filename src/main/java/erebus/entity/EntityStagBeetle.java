package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemMaterials;

public class EntityStagBeetle extends EntityTameable {

	private final EntityAINearestAttackableTarget aiNearestAttackableTarget = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	int shagCount;
	public int prevAnimation;
	public EntityStagBeetle(World world) {
		super(world);
		stepHeight = 2.0F;
		setSize(2.5F, 1.2F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, 0.5D, true));
		tasks.addTask(2, new EntityAIMate(this, 0.5D));
		tasks.addTask(3, new EntityAITempt(this, 0.5D, ModItems.turnip, false));
		tasks.addTask(5, new EntityAIWander(this, 0.5D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, aiNearestAttackableTarget);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(28, new Byte((byte) 1));
		dataWatcher.addObject(29, new Byte((byte) 0));
		dataWatcher.addObject(30, new Integer(0));
		dataWatcher.addObject(31, new Byte((byte) 0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		prevAnimation = dataWatcher.getWatchableObjectInt(30);
		if (shagCount > 0)
			shagCount--;
		if (!worldObj.isRemote) {
			if (dataWatcher.getWatchableObjectByte(29) == 1)
				dataWatcher.updateObject(30, dataWatcher.getWatchableObjectInt(30) + 1);
			if (dataWatcher.getWatchableObjectInt(30) >= 6) {
				dataWatcher.updateObject(28, (byte) 1);
				dataWatcher.updateObject(29, (byte) 0);
				dataWatcher.updateObject(30, 0);
			}
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 60D : 60D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.75D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:bombardierbeetlesound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:bombardierbeetlehurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness(1.0F);
		if (light >= 0F)
			return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	protected boolean canDespawn() {
		if (getTameState() != 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean allowLeashing() {
		return !canDespawn() && super.allowLeashing();
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (getTameState() >= 2)
			entityDropItem(ItemMaterials.DATA.RHINO_RIDING_KIT.makeStack(), 0.0F);
		int var3 = 1 + rand.nextInt(3) + rand.nextInt(1 + looting);
		for (int a = 0; a < var3; ++a) 
			entityDropItem(ItemMaterials.DATA.PLATE_EXO.makeStack(), 0.0F);
		int rareDropChance = rand.nextInt(30);
		if (rareDropChance == 0)
			entityDropItem(ItemMaterials.DATA.STAG_BEETLE_MANDIBLES.makeStack(), 0.0F);
		if (rareDropChance >= 1 && rareDropChance <= 4) {
			if(isBurning())
				entityDropItem(new ItemStack(ModItems.stagHeartCooked), 1);
			else
				entityDropItem(new ItemStack(ModItems.stagHeartRaw), 1);
		}
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		float healingBuff = 0.0F;
		if (is != null && is.getItem() == ModItems.materials && is.getItemDamage() == ItemMaterials.DATA.BEETLE_TAMING_AMULET.ordinal() && getTameState() == 0) {
			healingBuff = (float) (ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 20D : 20D * ConfigHandler.INSTANCE.mobHealthMultipier);
			is.stackSize--;
			setTameState((byte) 1);
			playTameEffect(true);
			player.swingItem();
			tasks.removeTask(aiNearestAttackableTarget);
			setAttackTarget((EntityLivingBase) null);
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 80D : 80D * ConfigHandler.INSTANCE.mobHealthMultipier);
			heal(healingBuff);
			return true;
		}
		if (is != null && is.getItem() == ModItems.materials && is.getItemDamage() == ItemMaterials.DATA.RHINO_RIDING_KIT.ordinal() && getTameState() == 1) {
			is.stackSize--;
			player.swingItem();
			setTameState((byte) 2);
			return true;
		}
		if (is != null && is.getItem() == ModItems.turnip && !shagging() && getTameState() != 0) {
			is.stackSize--;
			shagCount = 600;
			worldObj.playSoundEffect(posX, posY, posZ, "erebus:beetlelarvamunch", 1.0F, 0.75F);
			return true;
		}
		if (is == null && getTameState() >= 2) {
			if (!worldObj.isRemote)
				player.mountEntity(this);
			return true;
		}
		if (is != null && is.getItem() == ModItems.materials && is.getItemDamage() == ItemMaterials.DATA.BAMBOO_SHOOT.ordinal() && getTameState() != 0) {
			healingBuff = (float) (ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 5D : 5D * ConfigHandler.INSTANCE.mobHealthMultipier);
			if (getHealth() < getMaxHealth()) {
				heal(healingBuff);
				playTameEffect(true);
				player.swingItem();
				is.stackSize--;
				if (getHealth() == getMaxHealth())
					worldObj.playSoundEffect(posX, posY, posZ, "erebus:beetlelarvamunch", 1.0F, 0.75F);
			}
			return true;
		}
		return super.interact(player);
	}

	public boolean shagging() {
		return shagCount > 0;
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward) {
		if (riddenByEntity != null) {
			prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;
			rotationPitch = riddenByEntity.rotationPitch * 0.5F;
			setRotation(rotationYaw, rotationPitch);
			rotationYawHead = renderYawOffset = rotationYaw;
			strafe = ((EntityLivingBase) riddenByEntity).moveStrafing * 0.3F;
			forward = ((EntityLivingBase) riddenByEntity).moveForward * 0.3F;
			if (forward <= 0.0F)
				forward *= 0.25F;
			stepHeight = 2.0F;
			jumpMovementFactor = getAIMoveSpeed() * 0.1F;
			if (!worldObj.isRemote) {
				setAIMoveSpeed((float) getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
				super.moveEntityWithHeading(strafe, forward);
			}
			prevLimbSwingAmount = limbSwingAmount;
			double d0 = posX - prevPosX;
			double d1 = posZ - prevPosZ;
			float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;
			if (f4 > 1.0F)
				f4 = 1.0F;
			limbSwingAmount += (f4 - limbSwingAmount) * 0.4F;
			limbSwing += limbSwingAmount;
		} else {
			stepHeight = 0.5F;
			jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	@Override
	public void updateRiderPosition() {
		super.updateRiderPosition();
		if (riddenByEntity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * 0.75D;
			double offSetZ = Math.cos(a) * 0.75D;
			riddenByEntity.setPosition(posX - offSetX, posY + 0.8D + riddenByEntity.getYOffset(), posZ - offSetZ);
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return spawnBabyAnimal(entityageable);
	}

	@Override
	public boolean isBreedingItem(ItemStack is) {
		if (getTameState() != 0)
			return is != null && is.getItem() == ModItems.turnip;
		else
			return false;
	}

	public EntityBeetleLarva spawnBabyAnimal(EntityAgeable entityageable) {
		EntityBeetleLarva entityBeetleLarva = new EntityBeetleLarva(worldObj);
		entityBeetleLarva.setTame((byte) 5);
		return entityBeetleLarva;
	}

	@Override
	public void setAttackTarget(EntityLivingBase entity) {
		if (getTameState() != 0) {
			if (entity instanceof EntityPlayer)
				super.setAttackTarget((EntityLivingBase) null);
		} else
			super.setAttackTarget(entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (getTameState() != 0)
			if (entity instanceof EntityPlayer) {
				setAttackTarget((EntityLivingBase) null);
				return false;
			}
		if (entity != null && getDistanceToEntity(entity) <= 2.5F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 4D : 4D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
		return super.attackEntityAsMob(entity);
	}

	public void setTameState(byte tameState) {
		dataWatcher.updateObject(31, Byte.valueOf(tameState));
	}

	public byte getTameState() {
		return dataWatcher.getWatchableObjectByte(31);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
		data.setByte("tameState", getTameState());
		dataWatcher.updateObject(30, data.getInteger("jawMotion"));
		dataWatcher.updateObject(28, data.getByte("headPos"));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		setTameState(data.getByte("tameState"));
		data.setInteger("jawMotion", dataWatcher.getWatchableObjectInt(30));
		data.setByte("headPos", dataWatcher.getWatchableObjectByte(28));
	}
}