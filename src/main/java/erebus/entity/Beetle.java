package erebus.entity;

import javax.annotation.Nullable;

import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class Beetle extends Animal {

	private static final EntityDataAccessor<Integer>  SKIN_TYPE = SynchedEntityData.defineId(Beetle.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> TAMED = SynchedEntityData.defineId(Beetle.class, EntityDataSerializers.BOOLEAN);

	public Beetle(EntityType<? extends Beetle> type, Level level) {
		super(type, level);
	//	setSize(1.6F, 0.9F);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
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
				.add(Attributes.STEP_HEIGHT, 1D);
	}

	public static boolean canSpawnHere(EntityType<BeetleLarva> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
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
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.BEETLE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	public void tick() {
		super.tick();
	}

	@Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
	/*	TODO - WE NEED SOME FLUID STUFF FIRST
	if (FluidUtil.getFluidHandler(stack) != null) {
			FluidStack fluidStack = getFluid(stack);
			if (fluidStack != null)
				return InteractionResult.FAIL;
			if (!stack.isEmpty() && stack.getItem() == Items.BUCKET && !player.isCreative()) {
				stack.shrink(1);
				ItemStack newStack = FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid("beetle_juice"), Fluid.BUCKET_VOLUME));
				player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
				if (!player.getInventory().addItemStackToInventory(newStack))
					player.dropItem(newStack, false);
				return InteractionResult.SUCCESS;
			}
		}
	*/	
		if (!stack.isEmpty() && stack.getItem() == ModItems.TURNIP.get() && !isInLove()) {
			stack.shrink(1);
			if(!getIsTame())
				setTame(true);
			if(getHealth() < getMaxHealth())
				heal(1);
			setInLoveTime(600);
			level().playSound(null, blockPosition(), ModSounds.BEETLE_LARVA_MUNCH.get(), SoundSource.NEUTRAL, 1.0F, 0.75F);
			return InteractionResult.SUCCESS;
		}
		if (!stack.isEmpty() && stack.getItem() == ModItems.BEETLE_TAMING_AMULET.get()) {
			stack.shrink(1);
			if(!getIsTame())
				setTame(true);
			return InteractionResult.SUCCESS;
		}

		return super.mobInteract(player, hand);
	}

/*	@Nullable
	public FluidStack getFluid(final ItemStack container) {
		return FluidUtil.getFluidContained(container);
	}
*/

/* TODO LOOT TABLES
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (recentlyHit) {
			int chance = rand.nextInt(3) + rand.nextInt(1 + looting);
			int amount;
			for (amount = 0; amount < chance; ++amount)
				entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.PLATE_EXO.ordinal()), 0.0F);
		}
	}
*/	
	@Override
	public boolean isFood(ItemStack stack) {
		return !stack.isEmpty() && stack.getItem() == ModItems.TURNIP.get();
	}

	@Override
	public void spawnChildFromBreeding(ServerLevel level, Animal mate) {
		BeetleLarva entityBeetleLarva = new BeetleLarva(ModEntities.BEETLE_LARVA.get(), level());
		if (entityBeetleLarva != null) {
			entityBeetleLarva.setLarvaType((byte) 1);
			entityBeetleLarva.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
			level.addFreshEntityWithPassengers(entityBeetleLarva);
		}
	}

	@Override
	public boolean isPersistenceRequired() {
		if (getIsTame())
			return false;
		else
			return true;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return null;
	}

	public void setSkin(int skinType) {
		entityData.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return entityData.get(SKIN_TYPE).intValue();
	}

	public void setTame(boolean hasMated) {
		entityData.set(TAMED, hasMated);
	}

	public boolean getIsTame() {
		return entityData.get(TAMED);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
		RandomSource randomsource = level.getRandom();
		setSkin(randomsource.nextInt(51));
		return spawnGroupData;
	}

	@Override
	  public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putInt("beetleSkin", getSkin());
		nbt.putBoolean("isTamed", getIsTame());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		setSkin(nbt.getInt("beetleSkin"));
		setTame(nbt.getBoolean("isTamed"));
	}

}
