package erebus.entity;

import erebus.client.particle.ClientParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class Punchroom extends Monster {
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	private boolean wasOnGround;

	public Punchroom(EntityType<? extends Punchroom> type, Level level) { 
		super(type, level);
		this.moveControl = new PunchroomMoveHelper(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new AIPunchroomFloat(this));
		goalSelector.addGoal(1, new AIPunchroomAttack(this));
		goalSelector.addGoal(2, new AIPunchroomFaceRandom(this));
		goalSelector.addGoal(3, new AIPunchroomHop(this));
		targetSelector.addGoal(0, new NearestAttackableTargetGoal<Player>(this, Player.class, true, false));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MOVEMENT_SPEED,0.5D)
				.add(Attributes.MAX_HEALTH, 20D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
				.add(Attributes.FOLLOW_RANGE, 16.0D);
	}
/*
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (rand.nextInt(5) == 0)
			entityDropItem(ItemMaterials.EnumErebusMaterialsType.ELASTIC_FIBRE.createStack(1 + looting), 0.0F);
	}
*/
	public static boolean canSpawnHere(EntityType<Punchroom> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 2;
	}

	@Override
	  public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("wasOnGround", wasOnGround);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		wasOnGround = nbt.getBoolean("wasOnGround");
	}

    @Override
    public void tick() {
		squishFactor += (squishAmount - squishFactor) * 0.5F;
		prevSquishFactor = squishFactor;
		super.tick();
		if (onGround() && !wasOnGround) {
			for (int j = 0; j < 8; ++j) {
				float f = random.nextFloat() * Mth.PI * 2.0F;
				float f1 = random.nextFloat() * 0.5F + 0.5F;
				float f2 = Mth.sin(f) * 0.5F * f1;
				float f3 = Mth.cos(f) * 0.5F * f1;
				if (level().isClientSide())
					ClientParticles.spawnCustomParticle("spores", getX() + f2, getBoundingBox().minY, getZ() + f3, 0.0D, 0.0D, 0.0D);
			}
			playSound(getSquishSound(), getSoundVolume(), ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			squishAmount = -1.5F;
		} else if (!onGround() && wasOnGround) {
			squishAmount = 2.0F;
		}
		wasOnGround = onGround();
		alterSquishAmount();
	}

	protected void alterSquishAmount() {
		squishAmount *= 0.6F;
	}

	protected int getJumpDelay() {
		return random.nextInt(20) + 10;
	}

	@Override
	public void knockback(double strength, double xRatio, double zRatio) {
		float knockback = 0.4F;
		if (!level().isClientSide()) {
				if (level().getDifficulty().ordinal() > Difficulty.PEACEFUL.ordinal())
					if (level().getDifficulty() == Difficulty.NORMAL)
						knockback = 0.6F;
					else if (level().getDifficulty() == Difficulty.HARD)
						knockback = 0.8F;
			}
			super.knockback(knockback, xRatio, zRatio);
	}

	@Override
	public void playerTouch(Player player) {
		super.playerTouch(player);
		if (!level().isClientSide() && !player.isCreative()) {
			if (player.getBoundingBox().maxY >= getBoundingBox().minY && player.getBoundingBox().minY <= getBoundingBox().maxY)
				player.hurt(this.damageSources().mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
		}
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() * 3.95F;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.SLIME_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SLIME_DEATH;
	}

	protected SoundEvent getSquishSound() {
		return SoundEvents.SLIME_SQUISH;
	}

	@Override
	public void jumpFromGround() {
		this.setDeltaMovement(this.getDeltaMovement().x(), 0.5D, this.getDeltaMovement().z());
		this.hasImpulse = true;
	}

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

	protected SoundEvent getJumpSound() {
		return SoundEvents.SLIME_JUMP;
	}
	

	static class AIPunchroomAttack extends Goal {
		private final Punchroom punchroom;
		private int growTiredTimer;

		public AIPunchroomAttack(Punchroom punchroomIn) {
			punchroom = punchroomIn;
			setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			LivingEntity entitylivingbase = punchroom.getTarget();
			return entitylivingbase != null && entitylivingbase.isAlive() && punchroom.canAttack(entitylivingbase);
		}

		@Override
		public void start() {
			growTiredTimer = 300;
			super.start();
		}

		@Override
		public boolean canContinueToUse() {
			LivingEntity entitylivingbase = punchroom.getTarget();
			return entitylivingbase != null && entitylivingbase.isAlive() && punchroom.canAttack(entitylivingbase) && --growTiredTimer > 0;
		}
		
		@Override
		public boolean requiresUpdateEveryTick() {
			return true;
		}

		@Override
		public void tick() {
			if (punchroom.getTarget() != null)
				punchroom.lookAt(punchroom.getTarget(), 10.0F, 10.0F);

			if (punchroom.getMoveControl() instanceof PunchroomMoveHelper control)
				control.setDirection(punchroom.getYRot(), true);

		}
	}

	static class AIPunchroomFaceRandom extends Goal {
		private final Punchroom punchroom;
		private float chosenDegrees;
		private int nextRandomizeTime;

		public AIPunchroomFaceRandom(Punchroom punchroomIn) {
			punchroom = punchroomIn;
			setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			return punchroom.getTarget() == null && (punchroom.onGround() || punchroom.isInWater() || punchroom.isInLava() || punchroom.hasEffect(MobEffects.LEVITATION));
		}

		@Override
		public void tick() {
			if (--nextRandomizeTime <= 0) {
				nextRandomizeTime = 40 + punchroom.getRandom().nextInt(60);
				chosenDegrees = (float) punchroom.getRandom().nextInt(360);
			}
			
			if (punchroom.getMoveControl() instanceof PunchroomMoveHelper control)
				control.setDirection(chosenDegrees, false);
		}
	}

	static class AIPunchroomFloat extends Goal {
		private final Punchroom punchroom;

		public AIPunchroomFloat(Punchroom punchroomIn) {
			punchroom = punchroomIn;
			setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
			punchroomIn.getNavigation().setCanFloat(true);
		}

		@Override
		public boolean canUse() {
			return punchroom.isInWater() || punchroom.isInLava();
		}

		@Override
		public boolean requiresUpdateEveryTick() {
			return true;
		}

		@Override
		public void tick() {
			if (punchroom.getRandom().nextFloat() < 0.8F)
				punchroom.getJumpControl().jump();
			
			if (punchroom.getMoveControl() instanceof PunchroomMoveHelper control)
				control.setSpeed(1.2D);
		}
	}

	static class AIPunchroomHop extends Goal {
		private final Punchroom punchroom;

		public AIPunchroomHop(Punchroom punchroomIn) {
			punchroom = punchroomIn;
			setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse() {
			return true;
		}

		@Override
		public void tick() {
			if (punchroom.getMoveControl() instanceof PunchroomMoveHelper control)
				control.setSpeed(1.0D);
		}
	}

	static class PunchroomMoveHelper extends MoveControl {
		private float yRot;
		private int jumpDelay;
		private final Punchroom punchroom;
		private boolean isAggressive;

		public PunchroomMoveHelper(Punchroom punchroomIn) {
			super(punchroomIn);
			punchroom = punchroomIn;
			yRot = 180.0F * punchroomIn.getYRot() / Mth.PI;
		}

		public void setDirection(float rotationY, boolean aggressive) {
			yRot = rotationY;
			isAggressive = aggressive;
		}

		public void setSpeed(double speedIn) {
			speedModifier = speedIn;
			operation = MoveControl.Operation.MOVE_TO;
		}

		@Override
		public void tick() {
			mob.setYRot(rotlerp(mob.getYRot(), yRot, 90.0F));
			mob.yHeadRot = this.mob.getYRot();
			mob.yBodyRot = this.mob.getYRot();

			if (operation != MoveControl.Operation.MOVE_TO) {
				mob.setZza(0.0F);
			} else {
				operation = MoveControl.Operation.WAIT;

				if (mob.onGround()) {
					mob.setSpeed((float) (speedModifier * mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));

					if (jumpDelay-- <= 0) {
						jumpDelay = punchroom.getJumpDelay();

						if (isAggressive) {
							jumpDelay /= 3;
						}

						punchroom.getJumpControl().jump();
						punchroom.playSound(punchroom.getJumpSound(), 1F, ((punchroom.getRandom().nextFloat() - punchroom.getRandom().nextFloat()) * 0.2F + 1.0F) * 2.8F);

					} else {
						punchroom.xxa = 0.0F;
						punchroom.zza = 0.0F;
						mob.setSpeed(0.0F);
					}
				} else {
					mob.setSpeed((float) (speedModifier * mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
				}
			}
		}
	}
}
