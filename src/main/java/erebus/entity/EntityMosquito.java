package erebus.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;

public class EntityMosquito extends EntityMob {
	private final static int maxBloodLevel = 5;

	private ChunkCoordinates currentFlightTarget;
	private float heightOffset = 1.5F;
	private int drainage;
	private short consumptionTimer = 0;
	EntityLivingBase entityToAttack;
	AnimationMathHelper mathWings = new AnimationMathHelper();
	AnimationMathHelper mathSucking = new AnimationMathHelper();
	public float wingFloat;
	public float suckFloat;
	public boolean firstTickCheck;
	public int hitInterval = 30;
	Class<?>[] preys = { EntityPig.class, EntityCow.class, EntityBeetleLarva.class };

	public EntityMosquito(World world) {
		super(world);
		yOffset = 0.5F;
		drainage = 0;
		entityToAttack = null;
		setSize(1.0F, 1.8F);
		wingFloat = 0.0F;
		suckFloat = 1.0F;
		firstTickCheck = false;
		setBloodConsumed(0);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(15, Byte.valueOf((byte) 0));
	}

	@Override
	public void onUpdate() {
		if (!firstTickCheck) {
			mountEntity(null);
			ridingEntity = null;
			firstTickCheck = true;
		}
		if (consumptionTimer > 0)
			if (--consumptionTimer == 0)
				setBloodConsumed(0);
		if (ridingEntity != null) {
			suckFloat = 1.0F + mathSucking.swing(1.0F, 0.15F);
			if (rand.nextInt(10) == 0)
				for (int i = 0; i < 8; i++)
					worldObj.spawnParticle("reddust", posX + (rand.nextFloat() - rand.nextFloat()), posY + rand.nextFloat() + 1D, posZ + (rand.nextFloat() - rand.nextFloat()), 0, 0, 0);
			wingFloat = 0.0F;
		} else {
			suckFloat = 1.0F;
			wingFloat = mathWings.swing(4.0F, 0.1F);
		}
		if (!worldObj.isRemote) {
			if (findPlayerToAttack() != null && getBloodConsumed() < maxBloodLevel)
				entityToAttack = (EntityLivingBase) findPlayerToAttack();
			else if (findEnemyToAttack() != null && getBloodConsumed() < maxBloodLevel)
				entityToAttack = (EntityLivingBase) findEnemyToAttack();
			else
				entityToAttack = null;
			if (entityToAttack != null && ridingEntity == null && getDistanceToEntity(entityToAttack) <= 1.2D && !worldObj.isRemote && entityToAttack.riddenByEntity == null && getBloodConsumed() < maxBloodLevel)
				mountEntity(entityToAttack);
			if (ridingEntity != null && ridingEntity instanceof EntityLivingBase && getBloodConsumed() == maxBloodLevel) {
				entityToAttack = null;
				mountEntity(null);
			}
		}
		super.onUpdate();
	}

	@Override
	public double getYOffset() {
		if (ridingEntity != null && ridingEntity instanceof EntityPlayer)
			return -2D;
		else if (ridingEntity != null)
			return ridingEntity.height * 0.75D - 1.0D;
		else
			return yOffset;
	}

	protected void flyAbout() {
		if (!worldObj.isRemote) {
			if (currentFlightTarget != null && (!worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1))
				currentFlightTarget = null;
			if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistanceSquared((int) posX, (int) posY, (int) posZ) < 4.0F)
				currentFlightTarget = new ChunkCoordinates((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));
			double var1 = currentFlightTarget.posX + 0.5D - posX;
			double var3 = currentFlightTarget.posY + 1.D - posY;
			double var5 = currentFlightTarget.posZ + 0.5D - posZ;
			motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
			motionY += (Math.signum(var3) * 0.699999988079071D - motionY) * 0.10000000149011612D;
			motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
			float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
			moveForward = 0.5F;
			rotationYaw += var8;
		}
	}

	@Override
	protected void updateEntityActionState() {
		super.updateEntityActionState();
		int j = MathHelper.floor_double(posX);
		int k = MathHelper.floor_double(posY);
		int l = MathHelper.floor_double(posZ);
		Block m = worldObj.getBlock(j, k - 1, l);
		if (ridingEntity != null && ridingEntity instanceof EntityLivingBase && getBloodConsumed() < maxBloodLevel) {
			drainage++;
			if (drainage >= hitInterval) {
				ridingEntity.attackEntityFrom(DamageSource.causeMobDamage(this), getDamage());
				drainage = 0;
				setBloodConsumed(getBloodConsumed() + 1);
			}
		}
		if (m == Blocks.water && rand.nextInt(10) == 0 && (motionX > 0.05D || motionZ > 0.05D || motionX < -0.05D || motionZ < -0.05D))
			motionY = 0.25D;
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote) {
			heightOffset = 1.0F + (float) rand.nextGaussian() * 5.0F;
			if (entityToAttack != null && entityToAttack.posY + entityToAttack.getEyeHeight() > posY + getEyeHeight() + heightOffset) {
				double var1 = entityToAttack.posX + 0.5D - posX;
				double var3 = entityToAttack.posY + 1.D - posY;
				double var5 = entityToAttack.posZ + 0.5D - posZ;
				motionY += (0.350000011920929D - motionY) * 0.350000011920929D;
				motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
				motionY += (Math.signum(var3) * 0.699999988079071D - motionY) * 0.10000000149011612D;
				motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
				float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
				moveForward = 0.5F;
				rotationYaw += var8;
			}
			if (entityToAttack == null)
				flyAbout();
		}
		super.onLivingUpdate();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float par2) {
		if (isEntityInvulnerable())
			return false;
		else if (super.attackEntityFrom(source, par2)) {
			Entity var3 = source.getEntity();
			if (riddenByEntity != var3 && ridingEntity != var3) {
				if (var3 != this)
					entityToAttack = (EntityLivingBase) var3;
				return true;
			} else if (ridingEntity == var3 && !worldObj.isRemote) {
				mountEntity(ridingEntity);
				motionY += 0.5F;
				return true;
			} else
				return true;
		} else
			return false;
	}

	@Override
	public boolean canDespawn() {
		return true;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 10.0D);
		return var1 != null && canEntityBeSeen(var1) ? var1 : null;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int amount) {
		if (recentlyHit) {
			int count = 1 + getBloodConsumed();
			dropItem(ModItems.lifeBlood, count);
		}
	}

	@SuppressWarnings("unchecked")
	protected Entity findEnemyToAttack() {
		List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(10D, 10D, 10D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
			if (entity != null) {
				if (!(entity instanceof EntityCreature))
					continue;
				for (int j = 0; j < preys.length; j++)
					if (entity.getClass() == preys[j] && entity.riddenByEntity == null)
						return canEntityBeSeen(entity) ? entity : null;
			}
		}
		return null;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10 + maxBloodLevel);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public int getDamage() {
		int var2 = 2;
		if (this.isPotionActive(Potion.damageBoost))
			var2 += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();
		if (this.isPotionActive(Potion.weakness))
			var2 -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();
		return var2;
	}

	@Override
	protected String getLivingSound() {
		if (ridingEntity != null)
			return "erebus:mosquitosucking";
		else
			return "erebus:mosquitoflying";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:mosquitohit";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:mosquitodeath";
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	protected float getSoundPitch() {
		return 1.0F;
	}

	public int getBloodConsumed() {
		return dataWatcher.getWatchableObjectByte(15);
	}

	public void setBloodConsumed(int amount) {
		consumptionTimer = 2400;
		dataWatcher.updateObject(15, Byte.valueOf((byte) amount));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("BloodLevel", getBloodConsumed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setBloodConsumed(nbttagcompound.getInteger("BloodLevel"));
	}

	@Override
	public boolean getCanSpawnHere() {
		AxisAlignedBB axisalignedbb = boundingBox.expand(5D, 5D, 5D);
		int n = MathHelper.floor_double(axisalignedbb.minX);
		int o = MathHelper.floor_double(axisalignedbb.maxX + 1.0D);
		int p = MathHelper.floor_double(axisalignedbb.minY);
		int q = MathHelper.floor_double(axisalignedbb.maxY + 1.0D);
		int n1 = MathHelper.floor_double(axisalignedbb.minZ);
		int o1 = MathHelper.floor_double(axisalignedbb.maxZ + 1.0D);
		for (int p1 = n; p1 < o; p1++)
			for (int q1 = p; q1 < q; q1++)
				for (int n2 = n1; n2 < o1; n2++) {
					if (!worldObj.blockExists(p1, q1, n2))
						continue;
					Block o2 = worldObj.getBlock(p1, q1, n2);
					if (o2.isAir(worldObj, p1, q1, n2))
						continue;
					if (o2 == Blocks.water)
						return true;
				}
		return false;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}
}
