package erebus.entity;

import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class Beetle extends Animal {

	private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(Beetle.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> TAMED = SynchedEntityData.defineId(Beetle.class, EntityDataSerializers.BOOLEAN);

	public Beetle(EntityType<? extends Beetle> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SKIN_TYPE, 0);
		builder.define(TAMED, false);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 0.6D));
		goalSelector.addGoal(2, new BreedGoal(this, 0.5D));
		goalSelector.addGoal(3, new TemptGoal(this, 0.5D, item -> item.is(ModItems.TURNIP.get()), false));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 15D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.ARMOR, 4D)
				.add(Attributes.TEMPT_RANGE, 16.0D)
				.add(Attributes.STEP_HEIGHT, 1D);
	}

	public static boolean canSpawnHere(EntityType<Beetle> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
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
		return ModSounds.BEETLE_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(@NonNull DamageSource source) {
		return ModSounds.BEETLE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

	@Override
	protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
		this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public @NonNull InteractionResult mobInteract(Player player, @NonNull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		FluidUtil.interactWithFluidHandler(player, hand, getOnPos(), getCapability(Capabilities.Fluid.ENTITY, Direction.DOWN));

		if (!stack.isEmpty() && stack.is(ModItems.TURNIP.get()) && !isInLove()) {
			stack.shrink(1);
			if (!getIsTame())
				setTame(true);
			if (getHealth() < getMaxHealth())
				heal(1);
			setInLoveTime(600);
			level().playSound(null, blockPosition(), ModSounds.BEETLE_LARVA_MUNCH.get(), SoundSource.NEUTRAL, 1.0F, 0.75F);
			return InteractionResult.SUCCESS;
		}
		if (!stack.isEmpty() && stack.is(ModItems.BEETLE_TAMING_AMULET.get())) {
			stack.shrink(1);
			if (!getIsTame())
				setTame(true);
			return InteractionResult.SUCCESS;
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return !stack.isEmpty() && stack.is(ModItems.TURNIP.get());
	}

	@Override
	public void spawnChildFromBreeding(@NonNull ServerLevel level, @NonNull Animal mate) {
		BeetleLarva entityBeetleLarva = ModEntities.BEETLE_LARVA.get().create(level(), EntitySpawnReason.BREEDING);
		if (entityBeetleLarva != null) {
			entityBeetleLarva.setLarvaType((byte) 1);
			entityBeetleLarva.setPos(getX(), getY(), getZ());
			level.addFreshEntity(entityBeetleLarva);
		}
		resetLove();
		mate.resetLove();
		setAge(6000);
		mate.setAge(6000);
	}

	@Override
	public boolean isPersistenceRequired() {
		return !getIsTame();
	}

	@Override
	public AgeableMob getBreedOffspring(@NonNull ServerLevel level, @NonNull AgeableMob otherParent) {
		return null;
	}

	public void setSkin(int skinType) {
		entityData.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return entityData.get(SKIN_TYPE);
	}

	public void setTame(boolean hasMated) {
		entityData.set(TAMED, hasMated);
	}

	public boolean getIsTame() {
		return entityData.get(TAMED);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnType, @Nullable SpawnGroupData spawnGroupData) {
		spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
		RandomSource randomsource = level.getRandom();
		setSkin(randomsource.nextInt(51));
		return spawnGroupData;
	}

	@Override
	public void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putInt("beetleSkin", getSkin());
		output.putBoolean("isTamed", getIsTame());
	}

	@Override
	public void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		setSkin(input.getIntOr("beetleSkin", 0));
		setTame(input.getBooleanOr("isTamed", false));
	}
}
