package erebus.entity;

import erebus.Erebus;
import erebus.core.handler.configs.ConfigHandler;
import erebus.items.ItemMaterials;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPunchroom extends EntityMob {
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	private boolean wasOnGround;

	public EntityPunchroom(World world) {
		super(world);
		isImmuneToFire = true;
		setSize(1.0F, 1.0F);
		moveHelper = new EntityPunchroom.PunchroomMoveHelper(this);
	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityPunchroom.AIPunchroomFloat(this));
		tasks.addTask(1, new EntityPunchroom.AIPunchroomAttack(this));
		tasks.addTask(2, new EntityPunchroom.AIPunchroomFaceRandom(this));
		tasks.addTask(3, new EntityPunchroom.AIPunchroomHop(this));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 20D : 20D * ConfigHandler.INSTANCE.mobHealthMultipier);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? 2D : 2D * ConfigHandler.INSTANCE.mobAttackDamageMultiplier);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (rand.nextInt(5) == 0)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.ELASTIC_FIBRE.createStack(1 + looting), 0.0F);
	}

	@Override
	public boolean getCanSpawnHere() {
		float light = getBrightness();
		if (light >= 0F)
			return isNotColliding();
		return super.getCanSpawnHere();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("wasOnGround", wasOnGround);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		wasOnGround = compound.getBoolean("wasOnGround");
	}

	@Override
	public void onUpdate() {
		squishFactor += (squishAmount - squishFactor) * 0.5F;
		prevSquishFactor = squishFactor;
		super.onUpdate();
		if (onGround && !wasOnGround) {
			for (int j = 0; j < 8; ++j) {
				float f = rand.nextFloat() * (float) Math.PI * 2.0F;
				float f1 = rand.nextFloat() * 0.5F + 0.5F;
				float f2 = MathHelper.sin(f) * 0.5F * f1;
				float f3 = MathHelper.cos(f) * 0.5F * f1;
				if (getEntityWorld().isRemote)
					Erebus.PROXY.spawnCustomParticle("spores", getEntityWorld(), posX + f2, getEntityBoundingBox().minY, posZ + f3, 0.0D, 0.0D, 0.0D);
			}
			playSound(getSquishSound(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			squishAmount = -1.5F;
		} else if (!onGround && wasOnGround) {
			squishAmount = 2.0F;
		}
		wasOnGround = onGround;
		alterSquishAmount();
	}

	protected void alterSquishAmount() {
		squishAmount *= 0.6F;
	}

	protected int getJumpDelay() {
		return rand.nextInt(20) + 10;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		float knockback = 0.2F;
		if (!player.capabilities.isCreativeMode && !getEntityWorld().isRemote) {
			if (player.getEntityBoundingBox().maxY >= getEntityBoundingBox().minY && player.getEntityBoundingBox().minY <= getEntityBoundingBox().maxY)
				if (getEntityWorld().getDifficulty().ordinal() > 1)
					if (getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL)
						knockback = 0.4F;
					else if (getEntityWorld().getDifficulty() == EnumDifficulty.HARD)
						knockback = 0.6F;
			player.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (ConfigHandler.INSTANCE.mobAttackDamageMultiplier < 2 ? getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() : getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() * ConfigHandler.INSTANCE.mobAttackDamageMultiplier));
			player.addVelocity(-MathHelper.sin(rotationYaw * 3.141593F / 180.0F) * knockback, 0.3D, MathHelper.cos(rotationYaw * 3.141593F / 180.0F) * knockback);
		}
	}

	@Override
	public float getEyeHeight() {
		return 0.625F * height;
	}

	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 3.95F;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SLIME_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SLIME_DEATH;
	}

	protected SoundEvent getSquishSound() {
		return SoundEvents.ENTITY_SLIME_SQUISH;
	}

	@Override
	protected void jump() {
		motionY = 0.5D;
		isAirBorne = true;
	}

	protected SoundEvent getJumpSound() {
		return SoundEvents.ENTITY_SLIME_JUMP;
	}

	static class AIPunchroomAttack extends EntityAIBase {
		private final EntityPunchroom punchroom;
		private int growTieredTimer;

		public AIPunchroomAttack(EntityPunchroom punchroomIn) {
			punchroom = punchroomIn;
			setMutexBits(2);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = punchroom.getAttackTarget();
			if (entitylivingbase == null)
				return false;
			else if (!entitylivingbase.isEntityAlive())
				return false;
			else 
				return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).capabilities.disableDamage;
		}

		@Override
		public void startExecuting() {
			growTieredTimer = 300;
			super.startExecuting();
		}

		@Override
		public boolean shouldContinueExecuting() {
			EntityLivingBase entitylivingbase = punchroom.getAttackTarget();
			if (entitylivingbase == null)
				return false;
			else if (!entitylivingbase.isEntityAlive())
				return false;
			else if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer) entitylivingbase).capabilities.disableDamage)
				return false;
			else
				return --growTieredTimer > 0;
		}

		@Override
		public void updateTask() {
			punchroom.faceEntity(punchroom.getAttackTarget(), 10.0F, 10.0F);
			((EntityPunchroom.PunchroomMoveHelper) punchroom.getMoveHelper()).setDirection(punchroom.rotationYaw, true);
		}
	}

	static class AIPunchroomFaceRandom extends EntityAIBase {
		private final EntityPunchroom punchroom;
		private float chosenDegrees;
		private int nextRandomizeTime;

		public AIPunchroomFaceRandom(EntityPunchroom punchroomIn) {
			punchroom = punchroomIn;
			setMutexBits(2);
		}

		@Override
		public boolean shouldExecute() {
			return punchroom.getAttackTarget() == null && (punchroom.onGround || punchroom.isInWater() || punchroom.isInLava() || punchroom.isPotionActive(MobEffects.LEVITATION));
		}

		@Override
		public void updateTask() {
			if (--nextRandomizeTime <= 0) {
				nextRandomizeTime = 40 + punchroom.getRNG().nextInt(60);
				chosenDegrees = (float) punchroom.getRNG().nextInt(360);
			}
			((EntityPunchroom.PunchroomMoveHelper) punchroom.getMoveHelper()).setDirection(chosenDegrees, false);
		}
	}

	static class AIPunchroomFloat extends EntityAIBase {
		private final EntityPunchroom punchroom;

		public AIPunchroomFloat(EntityPunchroom punchroomIn) {
			punchroom = punchroomIn;
			setMutexBits(5);
			((PathNavigateGround) punchroomIn.getNavigator()).setCanSwim(true);
		}

		@Override
		public boolean shouldExecute() {
			return punchroom.isInWater() || punchroom.isInLava();
		}

		@Override
		public void updateTask() {
			if (punchroom.getRNG().nextFloat() < 0.8F)
				punchroom.getJumpHelper().setJumping();
			((EntityPunchroom.PunchroomMoveHelper) punchroom.getMoveHelper()).setSpeed(1.2D);
		}
	}

	static class AIPunchroomHop extends EntityAIBase {
		private final EntityPunchroom punchroom;

		public AIPunchroomHop(EntityPunchroom punchroomIn) {
			punchroom = punchroomIn;
			setMutexBits(5);
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void updateTask() {
			((EntityPunchroom.PunchroomMoveHelper) punchroom.getMoveHelper()).setSpeed(1.0D);
		}
	}

	static class PunchroomMoveHelper extends EntityMoveHelper {
		private float yRot;
		private int jumpDelay;
		private final EntityPunchroom punchroom;
		private boolean isAggressive;

		public PunchroomMoveHelper(EntityPunchroom punchroomIn) {
			super(punchroomIn);
			punchroom = punchroomIn;
			yRot = 180.0F * punchroomIn.rotationYaw / (float) Math.PI;
		}

		public void setDirection(float rotationY, boolean aggressive) {
			yRot = rotationY;
			isAggressive = aggressive;
		}

		public void setSpeed(double speedIn) {
			speed = speedIn;
			action = EntityMoveHelper.Action.MOVE_TO;
		}

		public void onUpdateMoveHelper() {
			entity.rotationYaw = limitAngle(entity.rotationYaw, yRot, 90.0F);
			entity.rotationYawHead = entity.rotationYaw;
			entity.renderYawOffset = entity.rotationYaw;

			if (action != EntityMoveHelper.Action.MOVE_TO) {
				entity.setMoveForward(0.0F);
			} else {
				action = EntityMoveHelper.Action.WAIT;

				if (entity.onGround) {
					entity.setAIMoveSpeed((float) (speed * entity .getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));

					if (jumpDelay-- <= 0) {
						jumpDelay = punchroom.getJumpDelay();

						if (isAggressive) {
							jumpDelay /= 3;
						}

						punchroom.getJumpHelper().setJumping();
						punchroom.playSound(punchroom.getJumpSound(), punchroom.getSoundVolume(), ((punchroom.getRNG().nextFloat() - punchroom.getRNG().nextFloat()) * 0.2F + 1.0F) * 2.8F);

					} else {
						punchroom.moveStrafing = 0.0F;
						punchroom.moveForward = 0.0F;
						entity.setAIMoveSpeed(0.0F);
					}
				} else {
					entity.setAIMoveSpeed((float) (speed * entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
				}
			}
		}
	}
}
