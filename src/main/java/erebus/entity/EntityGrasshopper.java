package erebus.entity;

import erebus.ModItems;
import erebus.ModSounds;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.ai.EntityAIEatCrops;
import erebus.items.ItemErebusFood.EnumFoodType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGrasshopper extends EntityCreature {

	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int currentMoveTypeDuration;
	public boolean isEating;

	public EntityGrasshopper(World world) {
		super(world);
		setSize(1.3F, 0.5F);
		stepHeight = 1.0F;
		setPathPriority(PathNodeType.WATER, -8F);
		jumpHelper = new EntityGrasshopper.GrasshopperJumpHelper(this);
		moveHelper = new EntityGrasshopper.GrasshopperMoveHelper(this);
		setMovementSpeed(0.5D);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWanderAvoidWater(this, 0.48D));
		tasks.addTask(2, new EntityAIEatCrops(this, 0.6D, 20));
		tasks.addTask(3, new EntityAIPanic(this, 0.8D));
		///tasks.addTask(4, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ConfigHandler.INSTANCE.mobHealthMultipier < 2 ? 25D : 25D * ConfigHandler.INSTANCE.mobHealthMultipier);
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
		return 3;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.GRASSHOPPER_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.GRASSHOPPER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			if (isBurning())
				entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.GRASSHOPPER_LEG_COOKED.ordinal()), 0.0F);
			else
				entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.GRASSHOPPER_LEG_RAW.ordinal()), 0.0F);
	}

	public void setIsEating(boolean isEating) {
		this.isEating = isEating;
	}

	protected float getJumpUpwardsMotion() {
		if (!collidedHorizontally && (!moveHelper.isUpdating() || moveHelper.getY() <= posY + 0.5D)) {
			Path path = navigator.getPath();
			if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
				Vec3d vec3d = path.getPosition(this);
				if (vec3d.y > posY + 0.5D)
					return 0.5F;
			}
			return moveHelper.getSpeed() <= 0.6D ? 0.5F : 0.5F;
		} else {
			return 0.5F;
		}
	}

	@Override
	protected void jump() {
		super.jump();
		double moveSpeed = moveHelper.getSpeed();
		if (moveSpeed > 0.0D) {
			double moveSpeedSq = motionX * motionX + motionZ * motionZ;
			if (moveSpeedSq < 0.010000000000000002D)
				moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
		}
		if (!world.isRemote)
			world.setEntityState(this, (byte) 1);
	}

	@SideOnly(Side.CLIENT)
	public float setJumpCompletion(float partialTicks) {
		return jumpDuration == 0 ? 0.0F : ((float) jumpTicks + partialTicks) / (float) jumpDuration;
	}

	public void setMovementSpeed(double newSpeed) {
		getNavigator().setSpeed(newSpeed);
		moveHelper.setMoveTo(moveHelper.getX(), moveHelper.getY(), moveHelper.getZ(), newSpeed);
	}

	public void setJumping(boolean jumping) {
		super.setJumping(jumping);

		// if (jumping) {
		// playSound(getJumpSound(), getSoundVolume(), ((rand.nextFloat() -
		// rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
		// }
	}

	public void startJumping() {
		setJumping(true);
		jumpDuration = 10;
		jumpTicks = 0;
	}

	@Override
	public void updateAITasks() {
		if (currentMoveTypeDuration > 0) {
			--currentMoveTypeDuration;
		}

		if (onGround) {
			if (!wasOnGround) {
				setJumping(false);
				checkLandingDelay();
			}
			EntityGrasshopper.GrasshopperJumpHelper grasshopperjumphelper = (EntityGrasshopper.GrasshopperJumpHelper) jumpHelper;
			if (!grasshopperjumphelper.getIsJumping()) {
				if (moveHelper.isUpdating() && currentMoveTypeDuration == 0) {
					Path path = navigator.getPath();
					Vec3d vec3d = new Vec3d(moveHelper.getX(), moveHelper.getY(), moveHelper.getZ());
					if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
						vec3d = path.getPosition(this);
					calculateRotationYaw(vec3d.x, vec3d.z);
					startJumping();
				}
			} else if (!grasshopperjumphelper.canJump())
				enableJumpControl();
		}
		wasOnGround = onGround;
	}

	public void spawnRunningParticles() {
	}

	private void calculateRotationYaw(double x, double z) {
		rotationYaw = (float) (MathHelper.atan2(z - posZ, x - posX) * (180D / Math.PI)) - 90.0F;
	}

	private void enableJumpControl() {
		((EntityGrasshopper.GrasshopperJumpHelper) jumpHelper).setCanJump(true);
	}

	private void disableJumpControl() {
		((EntityGrasshopper.GrasshopperJumpHelper) jumpHelper).setCanJump(false);
	}

	private void updateMoveTypeDuration() {
		if (moveHelper.getSpeed() < 2.2D)
			currentMoveTypeDuration = 10;
		else
			currentMoveTypeDuration = 1;
	}

	private void checkLandingDelay() {
		updateMoveTypeDuration();
		disableJumpControl();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (jumpTicks != jumpDuration) {
			++jumpTicks;
		} else if (jumpDuration != 0) {
			jumpTicks = 0;
			jumpDuration = 0;
			setJumping(false);
		}
	}

	public class GrasshopperJumpHelper extends EntityJumpHelper {
		private final EntityGrasshopper grasshopper;
		private boolean canJump;

		public GrasshopperJumpHelper(EntityGrasshopper grasshopper) {
			super(grasshopper);
			this.grasshopper = grasshopper;
		}

		public boolean getIsJumping() {
			return isJumping;
		}

		public boolean canJump() {
			return canJump;
		}

		public void setCanJump(boolean canJumpIn) {
			canJump = canJumpIn;
		}

		@Override
		public void doJump() {
			if (isJumping) {
				grasshopper.startJumping();
				isJumping = false;
			}
		}
	}

	static class GrasshopperMoveHelper extends EntityMoveHelper {
		private final EntityGrasshopper grasshopper;
		private double nextJumpSpeed;

		public GrasshopperMoveHelper(EntityGrasshopper grasshopper) {
			super(grasshopper);
			this.grasshopper = grasshopper;
		}
		@Override
		public void onUpdateMoveHelper() {
			if (grasshopper.onGround && !grasshopper.isJumping && !((EntityGrasshopper.GrasshopperJumpHelper) grasshopper.jumpHelper).getIsJumping()) {
				grasshopper.setMovementSpeed(0.0D);
			} else if (isUpdating())
				grasshopper.setMovementSpeed(nextJumpSpeed);
			super.onUpdateMoveHelper();
		}

		@Override
		public void setMoveTo(double x, double y, double z, double speedIn) {
			if (grasshopper.isInWater())
				speedIn = 1.5D;
			super.setMoveTo(x, y, z, speedIn);
			if (speedIn > 0.0D)
				nextJumpSpeed = speedIn;
		}
	}
}