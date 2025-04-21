package erebus.entity;

import erebus.entity.ai.GrasshopperEatPlantsGoal;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class Grasshopper extends PathfinderMob {

	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int currentMoveTypeDuration;
	public boolean isEating;
	public int animationTicks, prevAnimationTicks;

	public Grasshopper(EntityType<? extends Grasshopper> type, Level level ) {
		super(type, level);
		jumpControl = new Grasshopper.GrasshopperJumpControl(this);
		moveControl = new Grasshopper.GrasshopperMoveControl(this);
		setSpeedModifier(0.5D);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.48D));
		goalSelector.addGoal(1, new GrasshopperEatPlantsGoal(this, 0.6D, 20, false));
		goalSelector.addGoal(2, new PanicGoal(this, 0.8D));
		goalSelector.addGoal(4, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 25.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.8D)
			.add(Attributes.STEP_HEIGHT, 1.0D);
	}

	public static boolean canSpawnHere(EntityType<Grasshopper> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}
	
	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.GRASSHOPPER_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.GRASSHOPPER_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }
/*
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if(recentlyHit) {
			int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
			int amount;
			for (amount = 0; amount < chance; ++amount)
				if (isBurning())
					entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.GRASSHOPPER_LEG_COOKED.ordinal()), 0.0F);
				else
					entityDropItem(new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.GRASSHOPPER_LEG_RAW.ordinal()), 0.0F);
		}
	}
*/
	public void setIsEating(boolean isEating) {
		this.isEating = isEating;
	}

    @Override
    protected float getJumpPower() {
		if (!horizontalCollision || moveControl.hasWanted() && this.moveControl.getWantedY() > this.getY() + 0.5) {
			Path path = navigation.getPath();
			if (path != null && !path.isDone()) {
				Vec3 vec3 = path.getNextEntityPos(this);
				if (vec3.y > getY() + 0.5D)
					return 0.5F;
			}
			return moveControl.getSpeedModifier() <= 0.6D ? 0.5F : 0.5F;
		} else {
			return 0.5F;
		}
	}

	@Override
	public void jumpFromGround() {
		super.jumpFromGround();
		double moveSpeed = moveControl.getSpeedModifier();
		if (moveSpeed > 0.0D) {
			double moveSpeedSq = getDeltaMovement().horizontalDistanceSqr();
			if (moveSpeedSq < 0.01D)
				moveRelative(0.1F, new Vec3(0.0, 0.0, 1.0));
		}
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)1);
        }
	}

	@OnlyIn(Dist.CLIENT)
	 public float getJumpCompletion(float partialTick) {
		return jumpDuration == 0 ? 0.0F : ((float) jumpTicks + partialTick) / (float) jumpDuration;
	}

	public void setSpeedModifier(double newSpeed) {
		getNavigation().setSpeedModifier(newSpeed);
		moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), newSpeed);
	}

	public void setJumping(boolean jumping) {
		super.setJumping(jumping);
	}

	public void startJumping() {
		if(isEating)
			return;
		setJumping(true);
		jumpDuration = 10;
		jumpTicks = 0;
	}

    @Override
    public void customServerAiStep() {
		if (currentMoveTypeDuration > 0) {
			--currentMoveTypeDuration;
		}

		if (onGround()) {
			if (!wasOnGround) {
				setJumping(false);
				checkLandingDelay();
			}
			Grasshopper.GrasshopperJumpControl grasshopperjumphelper = (Grasshopper.GrasshopperJumpControl) jumpControl;
			if (!grasshopperjumphelper.getIsJumping()) {
				if (moveControl.hasWanted() && currentMoveTypeDuration == 0) {
					Path path = navigation.getPath();
					Vec3 vec3 = new Vec3(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
					if (path != null && !path.isDone())
						vec3 = path.getNextEntityPos(this);
					calculateRotationYaw(vec3.x, vec3.z);
					startJumping();
				}
			} else if (!grasshopperjumphelper.canJump())
				enableJumpControl();
		}
		wasOnGround = onGround();
	}

	public void spawnRunningParticles() {
	}

	private void calculateRotationYaw(double x, double z) {
		yBodyRot = (float) (Math.atan2(z - getZ(), x - getX()) * (180D / Math.PI)) - 90.0F;
	}

	private void enableJumpControl() {
		((Grasshopper.GrasshopperJumpControl) jumpControl).setCanJump(true);
	}

	private void disableJumpControl() {
		((Grasshopper.GrasshopperJumpControl) jumpControl).setCanJump(false);
	}

	private void updateMoveTypeDuration() {
		if (moveControl.getSpeedModifier() < 2.2D)
			currentMoveTypeDuration = 10;
		else
			currentMoveTypeDuration = 1;
	}

	private void checkLandingDelay() {
		updateMoveTypeDuration();
		disableJumpControl();
	}

	@Override
	public void tick() {
		super.tick();

		if (level().isClientSide()) {
			prevAnimationTicks = animationTicks;
			if (animationTicks < 720)
				animationTicks += 1;
			if (animationTicks >= 720) {
				animationTicks -= 720;
				prevAnimationTicks -= 720;
			}
		}
	}

    @Override
    public void aiStep() {
        super.aiStep();

		if (jumpTicks != jumpDuration) {
			++jumpTicks;
		} else if (jumpDuration != 0) {
			jumpTicks = 0;
			jumpDuration = 0;
			setJumping(false);
		}
	}
    
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 1) {
          //  this.spawnSprintParticle();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else {
            super.handleEntityEvent(id);
        }
    }

	public class GrasshopperJumpControl extends JumpControl {
		private final Grasshopper grasshopper;
		private boolean canJump;

		public GrasshopperJumpControl(Grasshopper grasshopper) {
			super(grasshopper);
			this.grasshopper = grasshopper;
		}

		public boolean getIsJumping() {
			return jump;
		}

		public boolean canJump() {
			return canJump;
		}

		public void setCanJump(boolean canJumpIn) {
			canJump = canJumpIn;
		}

	       @Override
	        public void tick() {
			if (jump) {
				grasshopper.startJumping();
				jump = false;
			}
		}
	}

	static class GrasshopperMoveControl extends MoveControl {
		private final Grasshopper grasshopper;
		private double nextJumpSpeed;

		public GrasshopperMoveControl(Grasshopper grasshopper) {
			super(grasshopper);
			this.grasshopper = grasshopper;
		}

        @Override
        public void tick() {
			if (grasshopper.onGround() && !grasshopper.jumping && !((Grasshopper.GrasshopperJumpControl) grasshopper.jumpControl).getIsJumping()) {
				grasshopper.setSpeedModifier(0.0D);
			} else if (hasWanted())
				grasshopper.setSpeedModifier(nextJumpSpeed);
			 super.tick();
		}

        @Override
        public void setWantedPosition(double x, double y, double z, double speedIn) {
			if (grasshopper.isInWater())
				speedIn = 1.5D;
			super.setWantedPosition(x, y, z, speedIn);
			if (speedIn > 0.0D)
				nextJumpSpeed = speedIn;
		}
	}
}