package erebus.entity;

import erebus.entity.ai.ThrowWebAttackGoal;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class BlackWidow extends Monster {
	private static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(BlackWidow.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(BlackWidow.class, EntityDataSerializers.BYTE);

	public BlackWidow(EntityType<? extends BlackWidow> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SIZE, 1);
		builder.define(CLIMBING, (byte) 0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4F));
		goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.6D, true));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, true));
		// targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFly.class, true));
		// targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityBotFly.class, true));
		// targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMidgeSwarm.class, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 15D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.ATTACK_DAMAGE, 2D);
	}

	@Override
	protected @NonNull PathNavigation createNavigation(@NonNull Level level) {
		return new WallClimberNavigation(this, level);
	}

	@Override
	public void tick() {
		super.tick();
		if (!level().isClientSide())
			setClimbing(horizontalCollision);
	}

	public static boolean canSpawnHere(EntityType<BlackWidow> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 2;
	}

	@Override
	public boolean causeFallDamage(double fallDistance, float damageModifier, @NonNull DamageSource damageSource) {
		return false;
	}

	@Override
	public void makeStuckInBlock(BlockState state, @NonNull Vec3 motionMultiplier) {
		if (!state.is(Blocks.COBWEB) && !state.is(ModBlocks.WITHER_WEB.get()) && !state.is(ModBlocks.LAVA_WEB.get()))
			super.makeStuckInBlock(state, motionMultiplier);
	}

	@Override
	public boolean onClimbable() {
		return isClimbing();
	}

	public boolean isClimbing() {
		return (entityData.get(CLIMBING) & 1) != 0;
	}

	public void setClimbing(boolean climbing) {
		byte climingState = entityData.get(CLIMBING);
		if (climbing)
			climingState = (byte) (climingState | 1);
		else
			climingState = (byte) (climingState & -2);
		entityData.set(CLIMBING, climingState);
	}

	@Override
	public boolean canBeAffected(MobEffectInstance potionEffect) {
		return (!potionEffect.is(MobEffects.POISON) && !potionEffect.is(MobEffects.WITHER) && super.canBeAffected(potionEffect));
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel level, DamageSource source, float damage) {
		if (source.is(DamageTypes.IN_WALL)) {
			return false;
		}
		return super.hurtServer(level, source, damage);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BLACK_WIDOW_SOUND.get();
	}

	@Override
	protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
		return ModSounds.BLACK_WIDOW_HURT.get();
	}

	@Override
	protected @NonNull SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

	@Override
	protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
		playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity entity) {
		if (super.doHurtTarget(level, entity)) {
			if (entity instanceof LivingEntity) {
				byte duration = 0;
				if (level().getDifficulty() == Difficulty.NORMAL)
					duration = 7;
				else if (level().getDifficulty() == Difficulty.HARD)
					duration = 15;
				if (duration > 0)
					if (random.nextBoolean())
						((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.WITHER, duration * 20, 0));
			}
			return true;
		} else
			return false;
	}

	@Override
	public void onSyncedDataUpdated(@NonNull EntityDataAccessor<?> key) {
		if (SIZE.equals(key)) {
			refreshDimensions();
			setYRot(this.yHeadRot);
			yBodyRot = this.yHeadRot;
		}
		super.onSyncedDataUpdated(key);
	}

	@Override
	public void refreshDimensions() {
		double d0 = this.getX();
		double d1 = this.getY();
		double d2 = this.getZ();
		super.refreshDimensions();
		this.setPos(d0, d1, d2);
	}

	@Override
	public @NonNull EntityDimensions getDefaultDimensions(@NonNull Pose pose) {
		return super.getDefaultDimensions(pose).scale((float) this.getWidowSize(), (float) this.getWidowSize());
	}

	public void setWidowSize(int size, boolean resetHealth) {
		entityData.set(SIZE, size);
		reapplyPosition();
		refreshDimensions();

		if (size == 1) {
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(15D);
			getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(1D);
		}
		if (size == 2) {
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(20D);
			getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(1.5D);
			goalSelector.addGoal(1, new ThrowWebAttackGoal(this, 0.9D, ModBlocks.WITHER_WEB.get().defaultBlockState()));
		}
		if (size == 4) {
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(25D);
			getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2D);
			goalSelector.addGoal(1, new ThrowWebAttackGoal(this, 0.9D, ModBlocks.WITHER_WEB.get().defaultBlockState()));
		}

		if (resetHealth)
			setHealth(getMaxHealth());
		
	}

	public int getWidowSize() {
		return entityData.get(SIZE);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnType, SpawnGroupData spawnGroupData) {
		RandomSource randomsource = level.getRandom();
		int randomSize = randomsource.nextInt(3);

		if (randomSize < 2 && randomsource.nextFloat() < 0.5F * difficulty.getSpecialMultiplier())
			randomSize++;

		int size = 1 << randomSize;
		setWidowSize(size, true);
		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}

	@Override
	public void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putInt("widowSize", getWidowSize() - 1);
	}

	@Override
	public void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		int size = input.getIntOr("widowSize", 0);
		if (size < 0)
			size = 0;
		setWidowSize(size + 1, false);
	}
}
