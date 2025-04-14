package erebus.entity;

import javax.annotation.Nullable;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.entity.PartEntity;

public class VelvetWorm extends Monster {
	private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(VelvetWorm.class, EntityDataSerializers.INT);
	public VelvetWormMultipart[] parts;

	private int wallInvulnerabilityTicks = 40;
	private boolean doSpawningAnimation = false;

	public VelvetWorm(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.setPathfindingMalus(PathType.WATER, -1.0F);
		this.parts = new VelvetWormMultipart[]{
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F),
			new VelvetWormMultipart(this, 0.3125F, 0.3125F)
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
		//builder.define(INFLATE_SIZE, 0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8D, 1));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4.0F));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
		
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 25D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.ATTACK_DAMAGE, 2D);
	}

	public static boolean canSpawnHere(EntityType<VelvetWorm> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
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
	public void aiStep() {
		super.aiStep();
		setHitBoxes();
	}

	protected float getHeadMotionYMultiplier() {
		return this.doSpawningAnimation && this.tickCount < 20 ? 0.65F : 1.0F;
	}

	protected float getTailMotionYMultiplier() {
		return this.doSpawningAnimation && this.tickCount < 20 ? 0.0F : 1.0F;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.level().isClientSide() && this.tickCount % 10 == 0) {
			this.spawnParticles(this.level(), this.xo, this.yo, this.zo, this.getRandom());
		}

		if (this.wallInvulnerabilityTicks > 0) {
			this.wallInvulnerabilityTicks--;
		}

		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.multiply(1.0D, this.getHeadMotionYMultiplier(), 1.0D));
	}

	@OnlyIn(Dist.CLIENT)
	public void spawnParticles(Level level, double x, double y, double z, RandomSource rand) {
		for (int count = 0; count < 1 + level.getRandom().nextInt(4); ++count) {
			double a = Math.toRadians(this.yBodyRot);
			double offSetX = -Math.sin(a) * 0D + rand.nextDouble() * 0.3D - rand.nextDouble() * 0.3D;
			double offSetZ = Math.cos(a) * 0D + rand.nextDouble() * 0.3D - rand.nextDouble() * 0.3D;
			level.addParticle(ParticleTypes.ITEM_SLIME, false, x + offSetX, y, z + offSetZ, 0, 0, 0);
		}
	}
	
	@Override
	public void makeStuckInBlock(BlockState state, Vec3 motionMultiplier) {
		if (!state.is(Blocks.COBWEB))
			super.makeStuckInBlock(state, motionMultiplier);
	}

	protected SoundEvent getWebSlingThrowSound() {
		return ModSounds.WEBSLING_THROW.get();
	}

	public double getAttackStrength() {
		switch (level().getDifficulty()) {
			default:
				return 4.0D;
			case EASY:
				return 4.0D;
			case NORMAL:
				return 5.0D;
			case HARD:
				return 6.0D;
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.IN_WALL) && this.wallInvulnerabilityTicks > 0)
			return false;
		return super.hurt(source, amount);
	}

	private void setHitBoxes() {
		for (VelvetWormMultipart part : this.parts) {
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

	public void movePiecePos(VelvetWorm sludgeWorm, VelvetWormMultipart targetPart, Entity destinationPart, float speed, float yawSpeed) {
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

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType type, @Nullable SpawnGroupData data) {
		setSkin(level.getRandom().nextInt(2));
		for (VelvetWormMultipart part : this.parts) {
			part.setPos(this.xo, this.yo, this.zo);
			part.setYRot(this.getYRot());
		}
		return data;
	}
	
	public void setSkin(int skinType) {
		entityData.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return entityData.get(SKIN_TYPE);
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
