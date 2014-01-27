package erebus.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBlackWidow extends EntityMob {

	private int shouldDo;
	Class[] preys = { EntityFly.class, EntityBotFly.class };

	public EntityBlackWidow(World world) {
		super(world);
		int i = 1 << rand.nextInt(3);
		setWidowSize(i);
		isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 1));
	}

	protected void setWidowSize(int par1) {
		dataWatcher.updateObject(16, new Byte((byte) par1));
		setSize(0.9F * par1, 0.4F * par1);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1;
	}

	protected Entity findEnemyToAttack() {
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(8D, 10D, 8D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity != null) {
				if (!(entity instanceof EntityLivingBase))
					continue;
				for (int j = 0; j < preys.length; j++)
					if (entity.getClass() == preys[j])
						return entity;
			}
		}
		return null;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		int i;
		if (worldObj.isRemote) {
			i = getWidowSize();
			setSize(0.9F * i, 0.4F * i);
		}
		if (findPlayerToAttack() != null)
			entityToAttack = findPlayerToAttack();
		else if (findEnemyToAttack() != null)
			entityToAttack = findEnemyToAttack();
		else
			entityToAttack = null;

		if (!worldObj.isRemote && getWidowSize() == 1) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(15.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D); // atkDmg
		}
		if (!worldObj.isRemote && getWidowSize() == 2) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.5D); // atkDmg
		}
		if (!worldObj.isRemote && getWidowSize() == 4) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float distance) {
	}

	@Override
	public void setInWeb() {
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potionEffect) {
		return potionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(potionEffect);
	}

	@Override
	protected String getLivingSound() {
		return "erebus:blackwidowsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:blackwidowhurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected String getWebSlingThrowSound() {
		return "erebus:webslingthrow";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int blockID) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected int getDropItemId() {
		return Item.silk.itemID;
	}

	@Override
	protected void dropFewItems(boolean attackedByPlayer, int looting) {
		super.dropFewItems(attackedByPlayer, looting);
		if (attackedByPlayer && (rand.nextInt(3) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Item.spiderEye.itemID, 1);
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		int i;
		i = getWidowSize();
		if (distance < 0.9F * i) {
			super.attackEntity(entity, distance);
			attackEntityAsMob(entity);
		}

		if (distance > 2.0F && distance < 6.0F && rand.nextInt(10) == 0)
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
				motionY = 0.4000000059604645D;
			}

		if (distance >= 5 & distance < 8.0F)
			if (attackTime == 0) {
				++shouldDo;
				if (shouldDo == 1)
					attackTime = 60;
				else if (shouldDo <= 4)
					attackTime = 6;
				else {
					attackTime = 20;
					shouldDo = 0;
				}
				if (shouldDo > 1 && getWidowSize() > 1 && entity instanceof EntityPlayer) {
					worldObj.playSoundAtEntity(this, getWebSlingThrowSound(), 1.0F, 1.0F);
					for (int var10 = 0; var10 < 1; ++var10) {
						EntityWebSling var11 = new EntityWebSling(worldObj, this);
						var11.posY = posY + height / 2.0F + 0.5D;
						var11.setType((byte) 1);
						worldObj.spawnEntityInWorld(var11);
					}
				}
			}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte var2 = 0;
				if (worldObj.difficultySetting > 1)
					if (worldObj.difficultySetting == 2)
						var2 = 7;
					else if (worldObj.difficultySetting == 3)
						var2 = 15;
				if (var2 > 0) {
					int chanceFiftyFifty = rand.nextInt(2);
					if (chanceFiftyFifty == 1)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, var2 * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Size", getWidowSize() - 1);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setWidowSize(nbt.getInteger("Size") + 1);
	}

	public int getWidowSize() {
		return dataWatcher.getWatchableObjectByte(16);
	}
}