package erebus.entity;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;

import javax.annotation.Nullable;

public class Centipede extends Monster {
	private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(Centipede.class, EntityDataSerializers.INT);
	public CentipedeMultipart[] parts;
	public Centipede(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.setPathfindingMalus(PathType.WATER, -8.0F);
		this.parts = new CentipedeMultipart[]{
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F),
				new CentipedeMultipart(this, 0.3125F, 0.3125F)
				};
			setId(ENTITY_COUNTER.getAndAdd(this.parts.length + 1) + 1);
		}

		@Override
		public void setId(int id) {
			super.setId(id);
			for (int i = 0; i < this.parts.length; i++)
				this.parts[i].setId(id + i + 1);
		}

		@Override
		public PartEntity<?>[] getParts() {
			return parts;
		}

		@Override
		public boolean isMultipartEntity() {
			return true;
		}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SKIN_TYPE, 0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.3D, false));
		goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(3, new RandomStrollGoal(this, 0.3D, 1));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 25D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 1D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
				.add(Attributes.ARMOR, 8D);
	}
	
	@Override
	public void aiStep() {
		super.aiStep();
		setHitBoxes();
	}
	
	private void setHitBoxes() {
		for (CentipedeMultipart part : this.parts) {
			part.yRotO = part.getYRot();
			part.xRotO = part.getXRot();
			part.xOld = part.getX();
			part.yOld = part.getY();
			part.zOld = part.getZ();
		}

		for (int i = 0; i < this.parts.length; i++) {
			this.movePiecePos(this, this.parts[i], i > 0 ? this.parts[i - 1] : this, 4.5F, 2F);
		}
	}

	protected double getMaxPieceDistance() {
		return 0.3125F;
	}

	public void movePiecePos(Centipede centipede, CentipedeMultipart targetPart, Entity destinationPart, float speed, float yawSpeed) {
		//TODO make this better and use the parent entities motionY

		if (destinationPart.yo - targetPart.yo < -0.5D)
			speed = 1.5F;

		double movementTolerance = 0.05D;
		double maxDist = this.getMaxPieceDistance();
		boolean correctY = false;

		for (int i = 0; i < 5; i++) {
			Vec3 diff = destinationPart.position().subtract(targetPart.position());
			double len = diff.length();

			if (len > maxDist) {
				Vec3 correction = diff.scale(1.0D / len * (len - maxDist));
				targetPart.xo += correction.x;
				if(tickCount <= 1)
					targetPart.yo = destinationPart.yo;
				else
					targetPart.yo += correction.y; // this?
				targetPart.zo += correction.z;
				targetPart.setPos(targetPart.xo, targetPart.yo, targetPart.zo);

				double cy = targetPart.getY();
				Vec3 vec3 = targetPart.getDeltaMovement();
				targetPart.setDeltaMovement(vec3.add(0D, correction.y, 0D));

				if (Math.abs((targetPart.yo - cy) - correction.y) <= movementTolerance) {
					correctY = true;
					break;
				}
			}
		}

		//Welp, failed to move smoothly along Y, just clip
		if (!correctY) {
			Vec3 diff = destinationPart.position().subtract(targetPart.position());
			double len = diff.lengthSqr();

			if (len > maxDist) {
				Vec3 correction = diff.scale(1.0D / len * (len - maxDist));
				targetPart.xo += correction.x;
				targetPart.yo += correction.y;
				targetPart.zo += correction.z;
			}
		}

		Vec3 diff = new Vec3(destinationPart.xo, 0, destinationPart.zo).subtract(new Vec3(targetPart.xo, 0, targetPart.zo));
		float destYaw = (float) Math.toDegrees(Math.atan2(diff.z, diff.x)) - 90;
		double yawDiff = (destYaw - targetPart.getYRot()) % 360.0F;
		double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
		double rotationYaw = targetPart.getYRot();
		rotationYaw += yawInterpolant / yawSpeed;
		targetPart.setYRot((float) rotationYaw);
		targetPart.setXRot(0F);
		if ((yo < targetPart.yo) && level().collidesWithSuffocatingBlock(targetPart, targetPart.getBoundingBox()))
			targetPart.yo += 0.02D;
		targetPart.setPos(targetPart.xo, targetPart.yo, targetPart.zo);
	}
	
    @Override
    public AABB getBoundingBoxForCulling() {
    	AABB newBox = getBoundingBox();
		for(CentipedeMultipart part : this.parts)
			newBox = getBoundingBox().minmax(part.getBoundingBox());
		return newBox;
    }

	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		double aabbSize = this.getBoundingBox().getSize() * 10.0;
		if (Double.isNaN(aabbSize))
			aabbSize = 1.0;
		aabbSize *= 64.0 * getViewScale();
		return distance < aabbSize * aabbSize;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 2;
	}

	public static boolean canSpawnHere(EntityType<Centipede> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	public double getAttackStrength() {
		switch (level().getDifficulty()) {
			default:
				return 2.0D;
			case EASY:
				return 2.0D;
			case NORMAL:
				return 2.0D;
			case HARD:
				return 4.0D;
		}
	}

	@Override
    public SoundEvent getAmbientSound() {
		return ModSounds.CENTIPEDE_SOUND.get();
	}

	@Override
    protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.CENTIPEDE_HURT.get();
	}

	@Override
    protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(ModSounds.CENTIPEDE_WALK.get(), 0.5F, 1.0F);
    }

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}
/* TODO 
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount) {
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.BIO_VELOCITY.ordinal()), 0.0F);
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.POISON_GLAND.ordinal()), 0.0F);
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (this.world.getGameRules().getBoolean("doMobLoot") && rand.nextInt(50) == 0)
			if (cause.getTrueSource() instanceof EntityLivingBase)
				entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.ordinal()), 0.0F);
	}
*/
	@Override
	public boolean doHurtTarget(Entity entity) {
		if (hasLineOfSight(entity)) {
			if (super.doHurtTarget(entity)) {
				if (entity instanceof LivingEntity) {
					byte duration = 0;

					if (level().getDifficulty().ordinal() > Difficulty.EASY.ordinal())
						if (level().getDifficulty() == Difficulty.NORMAL)
							duration = 7;
						else if (level().getDifficulty() == Difficulty.HARD)
							duration = 15;

					if (duration > 0)
						((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON, duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	public void setSkin(int skinType) {
		entityData.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return entityData.get(SKIN_TYPE);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType type, @Nullable SpawnGroupData data) {
		setSkin(level.getRandom().nextInt(3));
		for (CentipedeMultipart part : this.parts) {
			part.setPos(this.xo, this.yo, this.zo);
			part.setYRot(this.getYRot());
		}
		return data;
	}

	@Override
	  public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putInt("skin", getSkin());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		setSkin(nbt.getInt("skin"));
	}
}
