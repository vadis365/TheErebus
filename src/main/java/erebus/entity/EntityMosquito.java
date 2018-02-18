package erebus.entity;

import java.util.List;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.client.render.entity.AnimationMathHelper;
import erebus.core.handler.configs.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMosquito extends EntityMob {
	private static final DataParameter<Byte> BLOOD = EntityDataManager.<Byte>createKey(EntityMosquito.class, DataSerializers.BYTE);
	private final static int maxBloodLevel = 5;

	private BlockPos currentFlightTarget;
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
		dataManager.register(BLOOD, Byte.valueOf((byte) 0));
	}

	@Override
    public boolean isAIDisabled() {
        return false;
    }

	@Override
	public void onUpdate() {
		if (getEntityWorld().isRemote) {
			if (getRidingEntity() != null) {
				suckFloat = 1.0F + mathSucking.swing(1.0F, 0.15F);
				if (rand.nextInt(10) == 0)
					for (int i = 0; i < 8; i++)
						getEntityWorld().spawnParticle(EnumParticleTypes.REDSTONE, posX + (rand.nextFloat() - rand.nextFloat()), posY + rand.nextFloat() + 1D, posZ + (rand.nextFloat() - rand.nextFloat()), 0, 0, 0);
				wingFloat = 0.0F;
			} else {
				suckFloat = 1.0F;
				wingFloat = mathWings.swing(4.0F, 0.1F);
			}
		}

		if (!getEntityWorld().isRemote) {
			if (motionY < 0.0D)
				motionY *= 0.8D;

			if (!firstTickCheck)
				firstTickCheck = true;

			if (consumptionTimer > 0)
				if (--consumptionTimer == 0)
					setBloodConsumed(0);

			if (findPlayerToAttack() != null && getBloodConsumed() < maxBloodLevel)
				entityToAttack = (EntityLivingBase) findPlayerToAttack();
			else if (findEnemyToAttack() != null && getBloodConsumed() < maxBloodLevel)
				entityToAttack = (EntityLivingBase) findEnemyToAttack();
			else
				entityToAttack = null;
			if (entityToAttack != null && getRidingEntity() == null && getDistance(entityToAttack) <= 1.2D && !getEntityWorld().isRemote && !entityToAttack.isBeingRidden() && getBloodConsumed() < maxBloodLevel)
				startRiding(entityToAttack);
			if (getRidingEntity() instanceof EntityLivingBase && getBloodConsumed() >= maxBloodLevel && consumptionTimer > 0) {
				dismountRidingEntity();
				entityToAttack = null;
			}
			
			if(isInWater())
				motionY += 0.08D;
		}
		super.onUpdate();
	}

	@Override
	public double getYOffset() {
		if (getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer)
			return -2D;
		else if (getRidingEntity() != null)
			return getRidingEntity().height * 0.75D - 1.0D;
		else
			return 0.5F;
	}

	protected void flyAbout() {
		if (!getEntityWorld().isRemote) {
			if (currentFlightTarget != null && (!getEntityWorld().isAirBlock(currentFlightTarget) || currentFlightTarget.getY() < 1))
				currentFlightTarget = null;
			if (currentFlightTarget == null || rand.nextInt(30) == 0 || currentFlightTarget.getDistance((int) posX, (int) posY, (int) posZ) < 4.0F)
				currentFlightTarget = new BlockPos((int) posX + rand.nextInt(7) - rand.nextInt(7), (int) posY + rand.nextInt(6) - 2, (int) posZ + rand.nextInt(7) - rand.nextInt(7));
			double var1 = currentFlightTarget.getX() + 0.5D - posX;
			double var3 = currentFlightTarget.getY() + 1.D - posY;
			double var5 = currentFlightTarget.getZ() + 0.5D - posZ;
			motionX += (Math.signum(var1) * 0.5D - motionX) * 0.10000000149011612D;
			motionY += (Math.signum(var3) * 0.699999988079071D - motionY) * 0.10000000149011612D;
			motionZ += (Math.signum(var5) * 0.5D - motionZ) * 0.10000000149011612D;
			float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapDegrees(var7 - rotationYaw);
			moveForward = 0.5F;
			rotationYaw += var8;
		}
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		int j = MathHelper.floor(posX);
		int k = MathHelper.floor(posY);
		int l = MathHelper.floor(posZ);
		Block m = getEntityWorld().getBlockState(new BlockPos(j, k - 1, l)).getBlock();
		if (getRidingEntity() != null && getRidingEntity() instanceof EntityLivingBase && getBloodConsumed() < maxBloodLevel) {
			drainage++;
			if (drainage >= hitInterval) {
				getRidingEntity().attackEntityFrom(DamageSource.causeMobDamage(this), getDamage());
				drainage = 0;
				setBloodConsumed(getBloodConsumed() + 1);
				//System.out.println("Blood Consumed: " + getBloodConsumed());
			}
		}

		if (m == Blocks.WATER)
			motionY = 0.25D;

	}

	@Override
	public void onLivingUpdate() {
		if (!getEntityWorld().isRemote) {
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
				float var8 = MathHelper.wrapDegrees(var7 - rotationYaw);
				moveForward = 0.5F;
				rotationYaw += var8;
			}
			if (entityToAttack == null)
				flyAbout();
		}
		super.onLivingUpdate();
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			if (getIsInvulnerable())
				return false;
			else if (super.attackEntityAsMob(entity)) {
				Entity target = entity;
				if (getRidingEntity() != target) {
					if (target != this)
						if (!getEntityWorld().isRemote && getBloodConsumed() < maxBloodLevel) {
							startRiding(entity);
							motionY += 0.5F;
							return true;
						}
				}
				return true;
			} else
				return true;
		}
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (getIsInvulnerable())
			return false;
		else if (super.attackEntityFrom(source, amount)) {
			Entity attacker = source.getTrueSource();
			if (!getEntityWorld().isRemote && attacker instanceof EntityLivingBase && getRidingEntity() != attacker) {
				if (attacker != this)
					entityToAttack = (EntityLivingBase) attacker;
				return true;
			} else if (!getEntityWorld().isRemote && attacker instanceof EntityLivingBase && getRidingEntity() == attacker) {
				dismountEntity(getRidingEntity());
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
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer player = getEntityWorld().getClosestPlayerToEntity(this, 10.0D);
		return player != null && canEntityBeSeen(player)&& !player.isCreative() ? player  : null;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int amount) {
		if (recentlyHit) {
			int count = 1 + getBloodConsumed();
			dropItem(ModItems.LIFE_BLOOD, count);
		}
	}

	@SuppressWarnings("unchecked")
	protected Entity findEnemyToAttack() {
		List<Entity> list = getEntityWorld().getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(10D, 10D, 10D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
			if (entity != null) {
				if (!(entity instanceof EntityCreature))
					continue;
				for (int j = 0; j < preys.length; j++)
					if (entity.getClass() == preys[j] && !entity.isBeingRidden())
						return canEntityBeSeen(entity) ? entity : null;
			}
		}
		return null;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 10 + maxBloodLevel : (10 + maxBloodLevel) * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public int getDamage() {
		int var2 = 2;
		if (this.isPotionActive(MobEffects.STRENGTH))
			var2 += 3 << getActivePotionEffect(MobEffects.STRENGTH).getAmplifier();
		if (this.isPotionActive(MobEffects.WEAKNESS))
			var2 -= 2 << getActivePotionEffect(MobEffects.WEAKNESS).getAmplifier();
		return var2;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		if (getRidingEntity() != null)
			return ModSounds.MOSQUITO_SUCKING;
		else
			return ModSounds.MOSQUITO_FLYING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.MOSQUITO_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.MOSQUITO_DEATH;
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
		return dataManager.get(BLOOD);
	}

	public void setBloodConsumed(int amount) {
		consumptionTimer = 1200;
		dataManager.set(BLOOD, (byte) amount);
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
		AxisAlignedBB axisalignedbb = getEntityBoundingBox().grow(5.0D, 5.0D, 5.0D);
		int n = MathHelper.floor(axisalignedbb.minX);
		int o = MathHelper.floor(axisalignedbb.maxX);
		int p = MathHelper.floor(axisalignedbb.minY);
		int q = MathHelper.floor(axisalignedbb.maxY);
		int n1 = MathHelper.floor(axisalignedbb.minZ);
		int o1 = MathHelper.floor(axisalignedbb.maxZ);
		for (int p1 = n; p1 < o; p1++)
			for (int q1 = p; q1 < q; q1++)
				for (int n2 = n1; n2 < o1; n2++) {
					IBlockState o2 = getEntityWorld().getBlockState(new BlockPos(p1, q1, n2));
					if (o2.getMaterial() == Material.AIR)
						if (getEntityWorld().getBlockState(new BlockPos(p1, q1, n2).down()).getMaterial() == Material.WATER)
							return true;
				}
		return false;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}
}
