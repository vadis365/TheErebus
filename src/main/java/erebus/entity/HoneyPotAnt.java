package erebus.entity;

import erebus.registries.ModSounds;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class HoneyPotAnt extends Animal {

	private static final EntityDataAccessor<Float> HONEY_BELLY = SynchedEntityData.defineId(HoneyPotAnt.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Boolean> IS_TAME = SynchedEntityData.defineId(HoneyPotAnt.class, EntityDataSerializers.BOOLEAN);

	public HoneyPotAnt(EntityType<? extends HoneyPotAnt> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(HONEY_BELLY, 0F);
		builder.define(IS_TAME, false);
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
				.add(Attributes.TEMPT_RANGE, 16.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D);
	}

	public static boolean canSpawnHere(EntityType<HoneyPotAnt> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
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
        return isTamedAnt();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.ANT_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(@NonNull DamageSource source) {
		return ModSounds.ANT_HURT.get();
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
	public @NonNull InteractionResult mobInteract(Player player, @NonNull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!stack.isEmpty() && stack.is(Items.SUGAR)) {
			if (isTamedAnt() && getHoneyBelly() < 0.8F) {
				if (!level().isClientSide()) {
					setHoneyBelly(getHoneyBelly() + 0.1F);
					if (!player.isCreative())
						stack.shrink(1);
				}
				return InteractionResult.SUCCESS;
			}
		} else if (!stack.isEmpty() && stack.is(ModItems.NECTAR_COLLECTOR.get())) {
			if (getHoneyBelly() > 0 && isTamedAnt()) {
				if (!level().isClientSide()) {
					spawnAtLocation((ServerLevel) level(), new ItemStack(ModItems.NECTAR.get(), (int) (getHoneyBelly() * 10)));
					stack.hurtAndBreak(1, player, hand.asEquipmentSlot());
					setHoneyBelly(0);
				}
				return InteractionResult.SUCCESS;
			}
		} else if (!stack.isEmpty() && stack.is(ModItems.ANT_TAMING_AMULET.get())) {
			if (!level().isClientSide()) {
				if (!isTamedAnt())
					setTamedAnt(true);
			}
			level().broadcastEntityEvent(this, (byte) 18);
			player.swing(hand);
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}

	@Override
	public boolean isFood(@NonNull ItemStack stack) {
		return false;
	}

	@Override
	public AgeableMob getBreedOffspring(@NonNull ServerLevel level, @NonNull AgeableMob otherParent) {
		return null;
	}

	@Override
	public void onSyncedDataUpdated(@NonNull EntityDataAccessor<?> key) {
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
	public @NonNull EntityDimensions getDefaultDimensions(@NonNull Pose pose) {
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

    public boolean isTamedAnt() {
        return entityData.get(IS_TAME);
    }

    public void setTamedAnt(boolean tame) {
		entityData.set(IS_TAME, tame);
    }

	@Override
	  public void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putFloat("size", getHoneyBelly());
		output.putBoolean("is_tame", isTamedAnt());
	}

	@Override
	public void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		setHoneyBelly(input.getFloatOr("size", 0));
		setTamedAnt(input.getBooleanOr("is_tame", false));
	}

}
