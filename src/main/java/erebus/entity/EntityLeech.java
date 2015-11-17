package erebus.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;

public class EntityLeech extends EntityMob {

	private final static int maxBloodLevel = 5;
	public int attackCountDown = 60;
	public int hungerCoolDown = 0;
	private int drainage;
	public float moveProgress;
	public boolean firstTickCheck;
	AnimationMathHelper mathSucking = new AnimationMathHelper();

	public EntityLeech(World world) {
		super(world);
		setSize(0.5F, 0.25F);
		stepHeight = 0.0F;
		moveProgress = 0.0F;
		firstTickCheck = false;
		drainage = 0;
		setBloodConsumed(0);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(15, Byte.valueOf((byte) 0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D); // followRange
	}

	@Override
	public boolean isAIEnabled() {
		return false;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:snailliving";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:snailhurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:snaildeath";
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (!worldObj.isRemote && !player.capabilities.isCreativeMode)
			if (player.riddenByEntity == null && getBloodConsumed() <= 0)
				mountEntity(player);
	}

	@Override
	public void onUpdate() {
		if (!worldObj.isRemote)
			if (ridingEntity != null && ridingEntity instanceof EntityPlayer)
				setRotation(ridingEntity.rotationYaw, ridingEntity.rotationPitch);

		if (!firstTickCheck) {
			mountEntity(null);
			ridingEntity = null;
			firstTickCheck = true;
		}
		if (hungerCoolDown > 0)
			if (--hungerCoolDown == 0)
				setBloodConsumed(0);
		if (ridingEntity != null) {
			moveProgress = 1.0F + mathSucking.swing(1.F, 0.15F);
			if (rand.nextInt(10) == 0)
				for (int i = 0; i < 8; i++)
					worldObj.spawnParticle("reddust", posX + (rand.nextFloat() - rand.nextFloat()), posY + rand.nextFloat(), posZ + (rand.nextFloat() - rand.nextFloat()), 0, 0, 0);
		} else
			moveProgress = 0F;

		if (ridingEntity != null && ridingEntity instanceof EntityLivingBase && getBloodConsumed() < maxBloodLevel) {
			drainage++;
			if (drainage >= attackCountDown) {
				ridingEntity.attackEntityFrom(DamageSource.causeMobDamage(this), 1F);
				drainage = 0;
				setBloodConsumed(getBloodConsumed() + 1);
			}
		}

		if (getBloodConsumed() == 5 && ridingEntity != null)
			mountEntity(null);

		super.onUpdate();
	}

	@Override
	public double getYOffset() {
		if (ridingEntity != null && ridingEntity instanceof EntityPlayer)
			return -2D;
		else
			return yOffset;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall) || source.equals(DamageSource.drown))
			return false;
		return super.attackEntityFrom(source, damage);
	}

	public int getBloodConsumed() {
		return dataWatcher.getWatchableObjectByte(15);
	}

	public void setBloodConsumed(int amount) {
		hungerCoolDown = 1000;
		dataWatcher.updateObject(15, Byte.valueOf((byte) amount));
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int amount) {
		if (recentlyHit) {
			int count = 1 + getBloodConsumed();
			dropItem(ModItems.lifeBlood, count);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("bloodLevel", getBloodConsumed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setBloodConsumed(nbttagcompound.getInteger("bloodLevel"));
	}
}
