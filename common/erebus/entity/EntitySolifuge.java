package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ModItems;
import erebus.entity.ai.EntityErebusAIAttackOnCollide;
import erebus.item.ItemErebusMaterial.DATA;

public class EntitySolifuge extends EntityMob implements IEntityAdditionalSpawnData {

	protected EntityLiving theEntity;
	private boolean areAttributesSetup = false;
	public final String[] potionName = new String[] { "Move Slowdown", "Dig Slowdown", "Harm", "Confusion", "Blindness", "Hunger", "Weakness", "Poison", "Wither" };
	public final byte[] potionIds = new byte[] { 2, 4, 7, 9, 15, 17, 18, 19, 20 };
	
	public EntitySolifuge(World world) {
		super(world);
		isImmuneToFire = true;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityErebusAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.3D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(25, new Byte((byte) 0));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		areAttributesSetup = true;
		updateAttributes();
	}
	
	protected void updateAttributes() {
		if (worldObj != null && !worldObj.isRemote) {
			if (getIsAdult() == 0) {
				setSize(2.0F, 1.0F);
				experienceValue = 10;
				getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.5D);
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D);
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
				getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
			} else {
				setSize(1.0F, 0.5F);
				experienceValue = 3;
				getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.5D);
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D);
				getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
			}
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	public void setInWeb() {
	}

	@Override
	protected String getLivingSound() {
		return "mob.spider.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.spider.say";
	}

	@Override
	protected String getDeathSound() {
		return "mob.spider.death";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if(getIsAdult() == 0)
			entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1 + looting, DATA.bioVelocity.ordinal()), 0.0F);
	}

	@Override
	protected void dropRareDrop(int looting) {
		if(getIsAdult() == 0)
			entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, DATA.supernaturalvelocity.ordinal()), 0.0F);
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}
	
	@Override
	public void onUpdate() {
		if (worldObj.isRemote) {
			if (getIsAdult() == 0)
				setSize(2.0F, 1.0F);
			else {
				setCustomNameTag(""+potionName[getIsAdult()-1]);
				setSize(1.0F, 0.5F);
				}
		}
		super.onUpdate();
	}
	
	@Override
	public void setDead() {
		super.setDead();
		if (getIsAdult() == 0) {
			if (!worldObj.isRemote) {
				for (int a = 0; a < 4; a++) {
					EntitySolifuge entitySolifuge = new EntitySolifuge(worldObj);
					entitySolifuge.setPosition(posX + (rand.nextFloat()*0.03D -rand.nextFloat()*0.03D), posY + 1, posZ +(rand.nextFloat()*0.03D-rand.nextFloat()*0.03D));
					entitySolifuge.setIsAdult(Byte.valueOf((byte) ((byte) 1 + rand.nextInt(9))));
					entitySolifuge.updateAttributes();
					worldObj.spawnEntityInWorld(entitySolifuge);
				}
			}
		}
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance < 2.0F) {
			super.attackEntity(entity, distance);
			attackEntityAsMob(entity);
			}
			if (distance > 2.0F && distance < 6.0F && rand.nextInt(10) == 0)
				if (onGround) {
					double d0 = entity.posX - posX;
					double d1 = entity.posZ - posZ;
					float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
					motionX = d0 / f2 * 0.5D * 1.900000011920929D + motionX * 0.70000000298023224D;
					motionZ = d1 / f2 * 0.5D * 1.900000011920929D + motionZ * 0.70000000298023224D;
					motionY = 0.5000000059604645D;
				}
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase && getIsAdult() !=0 ) {
				byte duration = 0;
				if (worldObj.difficultySetting > 1)
					if (worldObj.difficultySetting == 2)
						duration = 5;
					else if (worldObj.difficultySetting == 3)
						duration = 10;
				if (duration > 0)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.potionTypes[potionIds[getIsAdult()-1]].id, duration * 20, 0));
			}
			return true;
		} else
			return false;
	}
	
	public byte getIsAdult() {
		return dataWatcher.getWatchableObjectByte(25);
	}

	public void setIsAdult(byte type) {
		dataWatcher.updateObject(25, Byte.valueOf(type));
		worldObj.setEntityState(this, (byte) 25);
		if (areAttributesSetup)
			updateAttributes();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("mobType", getIsAdult());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setIsAdult(nbt.getByte("mobType"));
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeByte(getIsAdult());
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		setIsAdult(data.readByte());
	}
}
