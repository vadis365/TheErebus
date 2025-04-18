package erebus.entity;

import javax.annotation.Nullable;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Locust extends Monster {
	public boolean canJump = true;

	public Locust(EntityType<? extends Locust> type, Level level) {
		super(type, level);
		moveControl = new FlyingMoveControl(this, 10, false);
	/*	jumpMovementFactor = 0.05F;
		setPathPriority(PathNodeType.WATER, -8F);
		setPathPriority(PathNodeType.BLOCKED, -8.0F);
		setPathPriority(PathNodeType.OPEN, 8.0F);
		*/
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, true));
		goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		goalSelector.addGoal(4, new Locust.AIRandomJumpWhenIdle(this));
		goalSelector.addGoal(5, new Locust.AIFlyingWander(this, 0.75D, 10));
		targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers(Locust.class));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, false));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 100D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 1D)
				.add(Attributes.FLYING_SPEED, 1D)
				.add(Attributes.ATTACK_DAMAGE, 4D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.STEP_HEIGHT, 1D);
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
	}

	@Override
    protected PathNavigation createNavigation(Level level){
		return new FlyingPathNavigation(this, level);
	}
	
	public static boolean canSpawnHere(EntityType<Locust> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		return level.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }


	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.LOCUST_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.LOCUST_HURT.get();
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
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(new ItemStack(ModItems.MATERIALS, amount, ItemMaterials.EnumErebusMaterialsType.ELASTIC_FIBRE.ordinal()), 0.0F);
	}
*/
	public boolean randJump() {
		return random.nextInt(50) == 0;
	}
	
	// PROBABLY DONT NEED THS ANYMORE
	public void jump() {
		setDeltaMovement(getDeltaMovement().add(0.0, 0.3, 0.0));
		setCanJump(false);
	}

	public void setCanJump(boolean ableToJump) {
		canJump = ableToJump;
	}

	@Override
	public void tick() {
		super.tick();
		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround() && vec3.y < 0.0D) {
			if (getTarget() == null)
				this.setDeltaMovement(vec3.multiply(1.0D, 0.2D, 1.0D));
			else
				this.setDeltaMovement(vec3.multiply(1.0D, 0.75D, 1.0D));
		}

		if(isInWater())
			getNavigation().moveTo(getX(), getY() + 1D, getZ(), 0.5D);

	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		if (hasLineOfSight(entity)) {
			if (super.doHurtTarget(entity)) {
				if (entity instanceof LivingEntity) {
					byte duration = 0;

					if (level().getDifficulty().ordinal() > Difficulty.EASY.ordinal())
						if (level().getDifficulty() == Difficulty.NORMAL)
							duration = 8;
						else if (level().getDifficulty() == Difficulty.HARD)
							duration = 15;

					if (duration > 0)
						((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.HUNGER, duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}
	

	public class AIFlyingWander extends WaterAvoidingRandomStrollGoal {

		private final Locust locust;

		public AIFlyingWander(Locust locustIn, double speedIn, int chance) {
			super(locustIn, speedIn, chance);
			this.locust = locustIn;
		}

		@Nullable
		@Override
		protected Vec3 getPosition() {
			Vec3 vec3 = this.mob.getViewVector(0.0F);
			return AirAndWaterRandomPos.getPos(this.mob, 32, 4, -2, vec3.x, vec3.z, (double) ((float) Math.PI / 2F));
		}
	}

	public class AIRandomJumpWhenIdle extends Goal {

		private final Locust locust;
		private int idleTime;

		public AIRandomJumpWhenIdle(Locust locustIn) {;
			this.locust = locustIn;
		}

		@Override
		public boolean canUse() {
			return locust.random.nextFloat() < 0.02F && locust.onGround() && locust.canJump;
		}

		@Override
		public boolean canContinueToUse() {
			return idleTime >= 0;
		}

	    public void start() {
			locust.jumpFromGround();
			idleTime = 20 + locust.random.nextInt(20);
		}

		@Override
		public void tick() {
			if (locust.onGround() && !locust.canJump)
				locust.setCanJump(true);
			--this.idleTime;
		}
	}
}