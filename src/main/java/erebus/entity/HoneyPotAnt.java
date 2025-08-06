package erebus.entity;

import org.jetbrains.annotations.NotNull;

import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class HoneyPotAnt extends TamableAnimal {

	private static final EntityDataAccessor<Float> HONEY_BELLY = SynchedEntityData.defineId(HoneyPotAnt.class, EntityDataSerializers.FLOAT);

	public HoneyPotAnt(EntityType<? extends HoneyPotAnt> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(HONEY_BELLY, 0F);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5D));
		goalSelector.addGoal(3, new PanicGoal(this, 0.7D));
		goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		goalSelector.addGoal(5, new TemptGoal(this, 0.5D, item -> item.is(ModItems.ANT_TAMING_AMULET.get()), false));
		goalSelector.addGoal(6, new TemptGoal(this, 0.5D, item -> item.is(Items.SUGAR), false));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 15D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D);
	}

	public static boolean canSpawnHere(EntityType<HoneyPotAnt> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
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
	public boolean isPersistenceRequired() {
        return !isTame();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.ANT_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.ANT_HURT.get();
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

		if (!stack.isEmpty() && stack.is(Items.SUGAR)) {
			if (isTame() && getHoneyBelly() < 0.8F) {
				if (!level().isClientSide()) {
					setHoneyBelly(getHoneyBelly() + 0.1F);
					if (!player.isCreative())
						stack.shrink(1);
				}
				return InteractionResult.SUCCESS;
			}
		} else if (!stack.isEmpty() && stack.is(ModItems.NECTAR_COLLECTOR.get())) {
			if (getHoneyBelly() > 0 && isTame()) {
				if (!level().isClientSide()) {
					spawnAtLocation(new ItemStack(ModItems.NECTAR.get(), (int) (getHoneyBelly() * 10)));
					stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
					setHoneyBelly(0);
				}
				return InteractionResult.SUCCESS;
			}
		} else if (!stack.isEmpty() && stack.is(ModItems.ANT_TAMING_AMULET.get())) {
			if (!level().isClientSide()) {
				if (!isTame())
					setTame(true, false);
			}
			level().broadcastEntityEvent(this, (byte) 18);
			player.swing(hand);
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}


/* TODO LOOT TABLES
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		if (isTamed()) {
			if (getHoneyBelly() > 0)
				entityDropItem(new ItemStack(ModItems.MATERIALS, (int) (getHoneyBelly() * 10), ItemMaterials.EnumErebusMaterialsType.NECTAR.ordinal()), 0.0F);
		} else
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, ItemMaterials.EnumErebusMaterialsType.NECTAR.ordinal()), 0.0F);
	}
*/

	@Override
	public boolean isFood(ItemStack stack) {
		return false;
	}

	@Override
	public void spawnChildFromBreeding(ServerLevel level, Animal mate) {
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return null;
	}
	
	@Override
	public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
		if (HONEY_BELLY.equals(key)) {
			refreshDimensions();
			setYRot(this.yHeadRot);
			yBodyRot = this.yHeadRot;
		}
		super.onSyncedDataUpdated(key);
	}

	@Override
	public void refreshDimensions() {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		super.refreshDimensions();
		this.setPos(x, y, z);
	}

	@Override
	public @NotNull EntityDimensions getDefaultDimensions(@NotNull Pose pose) {
		return super.getDefaultDimensions(pose).scale(1F + getHoneyBelly() * 0.1F, 1F);
	}

	public void setHoneyBelly(float scaledSize) {
		entityData.set(HONEY_BELLY, scaledSize);
		reapplyPosition();
		refreshDimensions();
	}

	public float getHoneyBelly() {
		return entityData.get(HONEY_BELLY);
	}

	@Override
	  public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putFloat("size", getHoneyBelly());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		setHoneyBelly(nbt.getFloat("size"));
	}

}
