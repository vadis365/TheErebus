package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.item.DungeonIdols.IDOL;

public class EntityUmberGolemDungeonTypes extends EntityMob{
    
	public EntityUmberGolemDungeonTypes(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1F, 2.0F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.5D, false));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		experienceValue = 120;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, new Integer(0));
		dataWatcher.addObject(29, new Byte((byte) rand.nextInt(4))); //just for testing random spawns	
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(setGolemHealth());
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(getAttackStrength());
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
	}

	private double getAttackStrength() {
		switch(getType()) {
		case 0:
			return 2.0D;
		case 1:
			return 3.0D;
		case 2:
			return 4.0D;
		case 3:
			return 5.0D;	
		default:
			return 2.0D;
		}
	}

	private double setGolemHealth() {
		switch(getType()) {
		case 0:
			return 100.0D;
		case 1:
			return 150.0D;
		case 2:
			return 200.0D;
		case 3:
			return 250.0D;	
		default:
			return 100.0D;
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
	public boolean allowLeashing() {
		return !canDespawn() && super.allowLeashing();
	}

	/*
	 * protected String getLivingSound() { return "erebus:umbergolemsound"; }
	 *
	 * protected String getHurtSound() { return "erebus:umbergolemhurt"; }
	 */

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		playSound("mob.zombie.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int looting) {
		switch(getType()) {
		case 0:
			entityDropItem(new ItemStack(ModItems.idols, 1, IDOL.idolBronze.ordinal()), 0.0F);
			break;
		case 1:
			entityDropItem(new ItemStack(ModItems.idols, 1, IDOL.idolSilver.ordinal()), 0.0F);
			break;
		case 2:
			entityDropItem(new ItemStack(ModItems.idols, 1, IDOL.idolGold.ordinal()), 0.0F);
			break;
		case 3:
			entityDropItem(new ItemStack(ModItems.idols, 1, IDOL.idolJade.ordinal()), 0.0F);
			break;
		default:
			entityDropItem(new ItemStack(ModItems.idols, 1, IDOL.idolBronze.ordinal()), 0.0F);
			break;
		}

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getAttackTarget() != null) {
			float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().boundingBox.minY, getAttackTarget().posZ);
			if (getRangeAttackTimer() < 100 && distance > 3)
				setRangeAttackTimer(getRangeAttackTimer() + 2);
			if (getRangeAttackTimer() == 100 && distance > 3)
				shootMissile(getAttackTarget(), distance);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		// TODO Add a different attack style for each type
		return Attack(entity);
	}

	protected boolean Attack(Entity entity) {
		if (!worldObj.isRemote) {
			if (onGround) {
				double d0 = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
				motionY = 0.4000000059604645D;
			}
			int Knockback = 1;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getAttackStrength());
			entity.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F, 0.4D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * Knockback * 0.5F);
			worldObj.playSoundAtEntity(entity, "damage.fallbig", 1.0F, 1.0F);
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, worldObj.difficultySetting.ordinal() * 50, 0));
			return true;
		}
		return true;
	}
	
	public EntityThrowable getMissileType() {
		switch(getType()) {
			case 0:
				return new EntityGooBall(worldObj, this);
			case 1:
			case 2:
				return new EntityWebSling(worldObj, this);
			case 3:
				return new EntityPoisonJet(worldObj, this);
			default:
				return new EntityGooBall(worldObj, this);
		}
	}
	
    public void shootMissile(EntityLivingBase EntityLivingBase, float distance) {
    	setRangeAttackTimer(0);
    	EntityThrowable missile = getMissileType();
    	if(getType() == 1)
    		((EntityWebSling) missile).setType((byte) 0);
    	if(getType() == 2)
    		((EntityWebSling) missile).setType((byte) 2);
    	missile.rotationPitch -= -20.0F;
    	double targetX = EntityLivingBase.posX + EntityLivingBase.motionX - this.posX;
    	double targetY = EntityLivingBase.posY + (double)EntityLivingBase.getEyeHeight() - 1.100000023841858D - this.posY;
    	double targetZ = EntityLivingBase.posZ + EntityLivingBase.motionZ - this.posZ;
    	float target = MathHelper.sqrt_double(targetX * targetX + targetZ * targetZ);
    	missile.setThrowableHeading(targetX, targetY + (double)(target * 0.1F), targetZ, 0.75F, 8.0F);
    	this.worldObj.spawnEntityInWorld(missile);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("type", getType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setType(nbt.getByte("type"));
	}
	
	public void setType(byte isType) {
		dataWatcher.updateObject(29, Byte.valueOf(isType));
	}

	public byte getType() {
		return dataWatcher.getWatchableObjectByte(29);
	}
	
	public void setRangeAttackTimer(int size) {
		dataWatcher.updateObject(20, Integer.valueOf(size));
	}

	public int getRangeAttackTimer() {
		return dataWatcher.getWatchableObjectInt(20);
	}
}
