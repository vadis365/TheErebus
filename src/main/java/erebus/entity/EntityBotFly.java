package erebus.entity;

import java.util.Calendar;

import erebus.client.render.entity.AnimationMathHelper;
import erebus.item.ItemMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBotFly extends EntityMob {
	// AKA ButtFly
	private ChunkCoordinates currentFlightTarget;
	private float heightOffset = 0.5F;
	public float wingFloat;
	private final AnimationMathHelper mathWings = new AnimationMathHelper();

	public EntityBotFly(World world) {
		super(world);
		setSize(0.9F, 0.75F);
		setIsFlyHanging(false);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(8.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}

	@Override
	protected String getLivingSound() {
		return getIsFlyHanging() && rand.nextInt(4) != 0 ? null : "erebus:flysound";
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
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity entity) {
	}

	public boolean getIsFlyHanging() {
		return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setIsFlyHanging(boolean par1) {
		byte duration = dataWatcher.getWatchableObjectByte(16);

		if (par1)
			dataWatcher.updateObject(16, Byte.valueOf((byte) (duration | 1)));
		else
			dataWatcher.updateObject(16, Byte.valueOf((byte) (duration & -2)));
	}

	@Override
	protected boolean isAIEnabled() {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (getIsFlyHanging()) {
			wingFloat = 0.0F;
			motionX = motionY = motionZ = 0.0D;
			posY = MathHelper.floor_double(posY) + 1.0D - height;
		} else {
			wingFloat = mathWings.swing(4.0F, 0.1F);
			motionY *= 0.6000000238418579D;
		}
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote) {
			heightOffset = 0.5F + (float) rand.nextGaussian() * 5.0F;
			if (getEntityToAttack() != null && getEntityToAttack().posY + getEntityToAttack().getEyeHeight() > posY + getEyeHeight() + heightOffset) {
				double var1 = getEntityToAttack().posX + 0.5D - posX;
				double var3 = getEntityToAttack().posY + 1.D - posY;
				double var5 = getEntityToAttack().posZ + 0.5D - posZ;
				motionY += (0.350000011920929D - motionY) * 0.350000011920929D;
				motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
				motionY += (Math.signum(var3) * 0.699999988079071D - motionY) * 0.10000000149011612D;
				motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
				float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
				moveForward = 0.5F;
				rotationYaw += var8;
			}

			if (getEntityToAttack() == null)
				flyAbout();
		}
		super.onLivingUpdate();
	}

	protected void flyAbout() {

		if (getIsFlyHanging()) {
			if (!worldObj.getBlock(MathHelper.floor_double(posX), (int) posY + 1, MathHelper.floor_double(posZ)).isNormalCube())
				setIsFlyHanging(false);
			else {
				if (rand.nextInt(200) == 0)
					rotationYawHead = rand.nextInt(360);

				if (worldObj.getClosestPlayerToEntity(this, 4.0D) != null)
					setIsFlyHanging(false);
			}
		} else {
			if (currentFlightTarget != null && (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1))
				currentFlightTarget = null;

			if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 4.0F)
				currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));

			double var1 = currentFlightTarget.posX + 0.5D - posX;
			double var3 = currentFlightTarget.posY + 0.1D - posY;
			double var5 = currentFlightTarget.posZ + 0.5D - posZ;
			motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
			motionY += (Math.signum(var3) * 0.699999988079071D - motionY) * 0.10000000149011612D;
			motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
			float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
			moveForward = 0.5F;
			rotationYaw += var8;

			if (rand.nextInt(100) == 0 && worldObj.getBlock(MathHelper.floor_double(posX), (int) posY + 1, MathHelper.floor_double(posZ)).isNormalCube())
				setIsFlyHanging(false);
		}
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

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable())
			return false;
		else {
			if (!worldObj.isRemote && getIsFlyHanging())
				setIsFlyHanging(false);

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(16, Byte.valueOf(nbt.getByte("BotFly")));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setByte("BotFly", dataWatcher.getWatchableObjectByte(16));
	}

	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(boundingBox.minY);

		if (var1 >= 63)
			return false;
		else {
			int duration = MathHelper.floor_double(posX);
			int var3 = MathHelper.floor_double(posZ);
			int var4 = worldObj.getBlockLightValue(duration, var1, var3);
			byte var5 = 4;
			Calendar var6 = worldObj.getCurrentDate();

			if ((var6.get(2) + 1 != 10 || var6.get(5) < 20) && (var6.get(2) + 1 != 11 || var6.get(5) > 3)) {
				if (rand.nextBoolean())
					return false;
			} else
				var5 = 7;

			return var4 > rand.nextInt(var5) ? false : super.getCanSpawnHere();
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount) {
			entityDropItem(ItemMaterials.DATA.flyWing.makeStack(), 0.0F);
			if (rand.nextInt(5) == 0)
				entityDropItem(ItemMaterials.DATA.compoundEyes.makeStack(), 0.0F);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityPlayer)
				if (rand.nextInt(20) == 0 && entity.riddenByEntity == null) {
					EntityBotFlyLarva entityBotFlyLarva = new EntityBotFlyLarva(worldObj);
					entityBotFlyLarva.setPosition(entity.posX, entity.posY + 1, entity.posZ);
					entityBotFlyLarva.setParasiteCount((byte) 1);
					worldObj.spawnEntityInWorld(entityBotFlyLarva);
				} else if (rand.nextInt(20) == 0 && entity.riddenByEntity != null && entity.riddenByEntity instanceof EntityBotFlyLarva)
					if (((EntityBotFlyLarva) entity.riddenByEntity).getParasiteCount() < 3)
						((EntityBotFlyLarva) entity.riddenByEntity).setParasiteCount((byte) (((EntityBotFlyLarva) entity.riddenByEntity).getParasiteCount() + 1));
			return true;
		}
		return false;
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (par2 < 1.2F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			attackEntityAsMob(entity);
	}
}
