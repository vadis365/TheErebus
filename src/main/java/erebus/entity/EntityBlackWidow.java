package erebus.entity;

import java.util.List;

import erebus.item.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityBlackWidow extends EntityMob {

	private int shouldDo;
	Class<?>[] preys = { EntityFly.class, EntityBotFly.class, EntityMidgeSwarm.class };

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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.75D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D); // followRange
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1;
	}

	@SuppressWarnings("unchecked")
	protected Entity findEnemyToAttack() {
		List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(8D, 10D, 8D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
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
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D); // atkDmg
		}
		if (!worldObj.isRemote && getWidowSize() == 2) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.5D); // atkDmg
		}
		if (!worldObj.isRemote && getWidowSize() == 4) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D); // atkDmg
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
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
	protected void func_145780_a(int x, int y, int z, Block block) { // playStepSound
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem() {
		return Items.string;
	}

	@Override
	protected void dropFewItems(boolean attackedByPlayer, int looting) {
		super.dropFewItems(attackedByPlayer, looting);
		if (attackedByPlayer && (rand.nextInt(3) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Items.spider_eye, 1);
		entityDropItem(ItemMaterials.DATA.poisonGland.makeStack(1 + rand.nextInt(2)), 0.0F);
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
					for (int count = 0; count < 1; ++count) {
						EntityWebSling webSling = new EntityWebSling(worldObj, this);
						webSling.posY = posY + height / 2.0F + 0.5D;
						webSling.setType((byte) 1);
						worldObj.spawnEntityInWorld(webSling);
					}
				}
			}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte duration = 0;
				if (worldObj.difficultySetting == EnumDifficulty.NORMAL)
					duration = 7;
				else if (worldObj.difficultySetting == EnumDifficulty.HARD)
					duration = 15;
				if (duration > 0) {
					int chanceFiftyFifty = rand.nextInt(2);
					if (chanceFiftyFifty == 1)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, duration * 20, 0));
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
