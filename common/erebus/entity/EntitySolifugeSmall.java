package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySolifugeSmall extends EntityMob implements IEntityAdditionalSpawnData {

	public final String[] potionName = new String[] { "Move Slowdown", "Dig Slowdown", "Harm", "Confusion", "Blindness", "Hunger", "Weakness", "Poison", "Wither" };
	public final byte[] potionIds = new byte[] { 2, 4, 7, 9, 15, 17, 18, 19, 20 };
	
	public EntitySolifugeSmall(World world) {
		super(world);
		setSize(1.0F, 0.5F);
		isImmuneToFire = true;
		experienceValue = 3;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(25, new Byte((byte) 0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
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
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}
	
	@Override
	public void onUpdate() {
		setCustomNameTag(""+potionName[getPotionEffect()]);
		super.onUpdate();
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
			if (entity instanceof EntityLivingBase) {
				byte duration = 0;
				if (worldObj.difficultySetting > 1)
					if (worldObj.difficultySetting == 2)
						duration = 5;
					else if (worldObj.difficultySetting == 3)
						duration = 10;
				if (duration > 0)
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.potionTypes[potionIds[getPotionEffect()]].id, duration * 20, 0));
			}
			return true;
		} else
			return false;
	}
	
	public byte getPotionEffect() {
		return dataWatcher.getWatchableObjectByte(25);
	}

	public void setPotionEffect(byte type) {
		dataWatcher.updateObject(25, Byte.valueOf(type));
		worldObj.setEntityState(this, (byte) 25);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("effect", getPotionEffect());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setPotionEffect(nbt.getByte("effect"));
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeByte(getPotionEffect());
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		setPotionEffect(data.readByte());
	}
}
