package erebus.entity;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.item.ItemMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDragonfly extends EntityMob {

	private ChunkCoordinates currentFlightTarget;
	EntityLivingBase entityToAttack;
	AnimationMathHelper mathWings = new AnimationMathHelper();
	public float wingFloat;

	public double pickupHeight;
	private boolean dropped;
	private int droptime = 0;// cool-down for picking up
	private int countDown;// makes sure player is always dropped

	public EntityDragonfly(World world) {
		super(world);
		setSize(2.5F, 1.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(30, new Integer(rand.nextInt(51)));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(128.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity entity) {
	}

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	@Override
	protected boolean isAIEnabled() {
		return false;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected void updateFallState(double par1, boolean par3) {
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public boolean captured() {
		return riddenByEntity != null;
	}

	private void setCountdown(int count) {
		countDown = count;
	}

	private void setDropped(boolean dropstate) {
		dropped = dropstate;
	}

	public boolean getDropped() {
		return dropped;
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.5F;
	}

	@Override
	protected String getLivingSound() {
		return rand.nextInt(4) != 0 ? null : "erebus:flysound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:flyhurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		wingFloat = mathWings.swing(4.0F, 0.1F);
		motionY *= 0.6000000238418579D;
		if (getEntityToAttack() == null)
			flyAbout();
		if (riddenByEntity != null)
			if (!worldObj.isRemote && captured() && (posY > pickupHeight + 10D || countDown <= 0)) {
				setDropped(true);
				riddenByEntity.mountEntity(null);
			}
		if (dropped) {
			droptime++;
			if (droptime >= 20) {
				setDropped(false);
				droptime = 0;
			}
		}
		if (countDown >= 0)
			countDown--;
		if (getEntityToAttack() != null) {
			currentFlightTarget = new ChunkCoordinates((int) getEntityToAttack().posX, (int) ((int) getEntityToAttack().posY + getEntityToAttack().getEyeHeight()), (int) getEntityToAttack().posZ);
			flyToTarget();
		}
		if (worldObj.isRemote)
			if (getSkin() == 0) {
				spawnParticles(worldObj, posX - 0.5D, posY, posZ - 0.5D, rand);
				if (!hasCustomNameTag())
					setCustomNameTag("Ender Dragonfly");
			}
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticles(World world, double x, double y, double z, Random rand) {
		for (int count = 0; count < 20; ++count) {
			double velX = 0.0D;
			double velY = 0.0D;
			double velZ = 0.0D;
			int motionX = rand.nextInt(2) * 2 - 1;
			int motionZ = rand.nextInt(2) * 2 - 1;
			velY = (rand.nextFloat() - 0.5D) * 0.125D;
			velZ = rand.nextFloat() * 1.0F * motionZ;
			velX = rand.nextFloat() * 1.0F * motionX;
			Erebus.proxy.spawnCustomParticle("portal", worldObj, x, y, z, velX, velY, velZ);
		}
	}

	public void flyAbout() {
		if (rand.nextInt(200) == 0)
			rotationYawHead = rand.nextInt(360);
		if (currentFlightTarget != null && !worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) && (worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY - 3, currentFlightTarget.posZ) || currentFlightTarget.posY < 1))
			currentFlightTarget = null;
		if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 4.0F)
			currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(10) - rand.nextInt(10), (int) posY + rand.nextInt(4) - rand.nextInt(4), (int) posZ + rand.nextInt(10) - rand.nextInt(10));
		if (currentFlightTarget != null && getEntityToAttack() != null && !worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY - 3, currentFlightTarget.posZ) || currentFlightTarget != null && !getDropped() && currentFlightTarget.posY < pickupHeight + 10D)
			currentFlightTarget.posY++;
		flyToTarget();
	}

	public void flyToTarget() {
		double targetX = currentFlightTarget.posX + 0.5D - posX;
		double targetY = currentFlightTarget.posY + 1D - posY;
		double targetZ = currentFlightTarget.posZ + 0.5D - posZ;
		motionX += (Math.signum(targetX) * 0.5D - motionX) * 0.20000000149011612D;
		motionY += (Math.signum(targetY) * 0.699999988079071D - motionY) * 0.30000000149011612D;
		motionZ += (Math.signum(targetZ) * 0.5D - motionZ) * 0.20000000149011612D;
		float angle = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float rotation = MathHelper.wrapAngleTo180_float(angle - rotationYaw);
		moveForward = 0.5F;
		rotationYaw += rotation;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.isSneaking())
			player.setSneaking(false);
		if (!worldObj.isRemote && !player.capabilities.isCreativeMode && !captured() && rand.nextInt(20) == 0 && !getDropped()) {
			player.mountEntity(this);
			pickupHeight = posY;
			setCountdown(60);
		}
	}

	@Override
	public void updateRiderPosition() {
		super.updateRiderPosition();
		if (riddenByEntity instanceof EntityLivingBase) {
			double a = Math.toRadians(renderYawOffset);
			double offSetX = -Math.sin(a) * -0.6D;
			double offSetZ = Math.cos(a) * -0.6D;
			riddenByEntity.setPosition(posX - offSetX, posY, posZ - offSetZ);
		}
	}

	@Override
	protected Entity findPlayerToAttack() {
		return worldObj.getClosestVulnerablePlayerToEntity(this, 16D);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amp) {
		if (isEntityInvulnerable())
			return false;
		else if (super.attackEntityFrom(source, amp)) {
			Entity entity = source.getEntity();
			if (riddenByEntity != entity && ridingEntity != entity) {
				if (entity != this)
					entityToAttack = (EntityLivingBase) entity;
				return true;
			} else
				return true;
		} else
			return false;
	}

	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(boundingBox.minY);
		if (var1 >= 100)
			return false;
		else {
			int var2 = MathHelper.floor_double(posX);
			int var3 = MathHelper.floor_double(posZ);
			int var4 = worldObj.getBlockLightValue(var2, var1, var3);
			byte var5 = 7;
			return var4 > rand.nextInt(var5) ? false : super.getCanSpawnHere();
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(ItemMaterials.DATA.dragonflyWing.makeStack(), 0.0F);
		if (rand.nextInt(5) == 0)
			entityDropItem(ItemMaterials.DATA.compoundEyes.makeStack(rand.nextInt(1) + 1 + looting), 0.0F);
		if (getSkin() == 0)
			entityDropItem(new ItemStack(Items.ender_pearl, rand.nextInt(1) + 1 + looting), 0.0F);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return super.attackEntityAsMob(entity);
		// I know this does nothing! - But it may soon!
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			super.attackEntity(entity, distance);
	}

	public void setSkin(int skinType) {
		dataWatcher.updateObject(30, new Integer(skinType));
	}

	public int getSkin() {
		return dataWatcher.getWatchableObjectInt(30);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("dragonflySkin", getSkin());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setSkin(nbt.getInteger("dragonflySkin"));
	}
}
