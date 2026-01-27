package erebus.entity;

import erebus.client.particle.ClientParticleTypes.ParticleType;
import erebus.entity.ai.LarvaEatWoodenBlocksGoal;
import erebus.network.client.ParticlePacket;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jspecify.annotations.NonNull;

public class BeetleLarva extends PathfinderMob {
	public static final EntityDataAccessor<Byte> LARVA_TYPE = SynchedEntityData.defineId(BeetleLarva.class, EntityDataSerializers.BYTE);
	public static final EntityDataAccessor<Float> LARVA_SIZE = SynchedEntityData.defineId(BeetleLarva.class, EntityDataSerializers.FLOAT);
	public static final EntityDataAccessor<Boolean> IS_SQUASHED = SynchedEntityData.defineId(BeetleLarva.class, EntityDataSerializers.BOOLEAN);
	public boolean isEating;

	public BeetleLarva(EntityType<? extends BeetleLarva> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(LARVA_SIZE, 1F);
		builder.define(LARVA_TYPE, (byte) 0);
		builder.define(IS_SQUASHED, false);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new LarvaEatWoodenBlocksGoal(this, 0.48D, 10));
		goalSelector.addGoal(2, new TemptGoal(this, 0.48D, item -> item.is(Items.STICK), false));
		goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.48D));
		goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		goalSelector.addGoal(5, new PanicGoal(this, 0.48D));
	}

	@Override
	public boolean isBaby() {
		return false;
	}

	@Override
	public boolean isPersistenceRequired() {
		return getLarvaType() == 0 || getLarvaType() == 4;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 8D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.35D)
				.add(Attributes.STEP_HEIGHT, 1D);
	}

	public static boolean canSpawnHere(EntityType<BeetleLarva> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public void playerTouch(@NonNull Player player) {
		super.playerTouch(player);
		byte duration = 0;
		if (!level().isClientSide() && player.getBoundingBox().maxY >= getBoundingBox().minY && player.getBoundingBox().minY <= getBoundingBox().maxY && player.getBoundingBox().maxX >= getBoundingBox().minX && player.getBoundingBox().minX <= getBoundingBox().maxX && player.getBoundingBox().maxZ >= getBoundingBox().minZ && player.getBoundingBox().minZ <= getBoundingBox().maxZ && !player.onGround() && player.getDeltaMovement().y() < 0) {
			if (level().getDifficulty() == Difficulty.NORMAL)
				duration = 7;
			else if (level().getDifficulty() == Difficulty.HARD)
				duration = 15;
			if (duration > 0)
				player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, duration * 20, 0));
			setIsSquashed(true);
			kill((ServerLevel) level());
			deathTime = 19;
			tickDeath();
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BEETLE_LARVA_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(@NonNull DamageSource source) {
		return ModSounds.BEETLE_LARVA_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

	protected SoundEvent getJumpedOnSound() {
		return ModSounds.BEETLE_LARVA_SPLAT.get();
	}

	protected SoundEvent getHasMunched() {
		return ModSounds.BEETLE_LARVA_MUNCH.get();
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	public void tick() {
		super.tick();
		if (!level().isClientSide())
			if (getLarvaSize() > 1.8F) {
				remove(RemovalReason.DISCARDED);
				spawnBeetle();
			}
	}

	private void spawnBeetle() {
		if (getLarvaType() == 0) {
			Beetle entityBeetle = ModEntities.BEETLE.get().create(level(), EntitySpawnReason.CONVERSION);
			if (entityBeetle != null) {
				entityBeetle.finalizeSpawn((ServerLevel) level(), ((ServerLevel) level()).getCurrentDifficultyAt(blockPosition()), EntitySpawnReason.CONVERSION, null);
				level().addFreshEntity(entityBeetle);
				entityBeetle.copyPosition(this);
			}
		}
		if (getLarvaType() == 1) {
			Beetle entityBeetle = ModEntities.BEETLE.get().create(level(), EntitySpawnReason.CONVERSION);
			if (entityBeetle != null) {
				entityBeetle.setTame(true);
				entityBeetle.finalizeSpawn((ServerLevel) level(), ((ServerLevel) level()).getCurrentDifficultyAt(blockPosition()), EntitySpawnReason.CONVERSION, null);
				level().addFreshEntity(entityBeetle);
				entityBeetle.copyPosition(this);
			}
		} 
		/*	else if (getLarvaType() == 2) {
			EntityRhinoBeetle entityRhinoBeetle = new EntityRhinoBeetle(level());
			entityRhinoBeetle.setPosition(posX, posY, posZ);
			entityRhinoBeetle.setTameState((byte) 1);
			level().spawnEntity(entityRhinoBeetle);
		} else if (getLarvaType() == 3) {
			EntityTitanBeetle entityTitanBeetle = new EntityTitanBeetle(level());
			entityTitanBeetle.setPosition(posX, posY, posZ);
			entityTitanBeetle.setTameState((byte) 1);
			level().spawnEntity(entityTitanBeetle);
		}*/
		else if (getLarvaType() == 4) {
			BombardierBeetle entityBombardierBeetle = ModEntities.BOMBARDIER_BEETLE.get().create(level(), EntitySpawnReason.CONVERSION);
			//entityBombardierBeetle.finalizeSpawn((ServerLevel)level(), level().getCurrentDifficultyAt(blockPosition()), EntitySpawnReason.CONVERSION, null);
			level().addFreshEntity(entityBombardierBeetle);
			entityBombardierBeetle.copyPosition(this);
		} /*else if (getLarvaType() == 5) {
			EntityStagBeetle entityStagBeetle = new EntityStagBeetle(level());
			entityStagBeetle.setPosition(posX, posY, posZ);
			entityStagBeetle.setTameState((byte) 1);
			level().spawnEntity(entityStagBeetle);
		}
	*/
	}

	@Override
	protected void tickDeath() {
		super.tickDeath();
		if (getIsSquashed()) {
			if (!level().isClientSide())
				PacketDistributor.sendToPlayersNear((ServerLevel) level(), null, blockPosition().getX(),
						blockPosition().getY(), blockPosition().getZ(), 30,
						new ParticlePacket((byte) ParticleType.BEETLE_LARVA_SQUISH.ordinal(), blockPosition().getX() + 0.5D, blockPosition().getY() + 0.5D, blockPosition().getZ() + 0.5D));
			level().playSound(null, blockPosition(), getJumpedOnSound(), SoundSource.NEUTRAL, 1.0F, 0.5F);
			level().playSound(null, blockPosition(), getDeathSound(), SoundSource.NEUTRAL, 1.0F, 0.7F);
			if (!level().isClientSide()) {
				if (random.nextInt(200) == 0) {
					//		entityDropItem(new ItemStack(Items.DIAMOND), 0.0F);
				}
				//	entityDropItem(new ItemStack(Items.SLIME_BALL), 0.0F);
			}
		}
	}

	@Override
	public @NonNull InteractionResult mobInteract(Player player, @NonNull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!level().isClientSide() && stack.is(Items.STICK) && getLarvaType() != 4) {
			setLarvaSize(getLarvaSize() + 0.1F);
			stack.shrink(1);
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}

	public void setIsEating(boolean eating) {
		isEating = eating;
		this.goalSelector.setControlFlag(Goal.Flag.LOOK, !eating);
		this.goalSelector.setControlFlag(Goal.Flag.MOVE, !eating);
	}

	@Override
	public void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putFloat("larvaSize", getLarvaSize());
		output.putByte("larvaType", getLarvaType());
	}

	@Override
	public void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		setLarvaSize(input.getFloatOr("larvaSize", 0));
		setLarvaType(input.getByteOr("larvaType", (byte) 0));
	}

	@Override
	public void onSyncedDataUpdated(@NonNull EntityDataAccessor<?> key) {
		if (LARVA_SIZE.equals(key)) {
			refreshDimensions();
			setYRot(this.yHeadRot);
			yBodyRot = this.yHeadRot;
		}
		if (IS_SQUASHED.equals(key)) {
			setIsSquashed(getIsSquashed());
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
		return super.getDefaultDimensions(pose).scale(0.9F * getLarvaSize(), 0.5F * getLarvaSize());
	}

	public void setLarvaSize(float size) {
		entityData.set(LARVA_SIZE, size);
		reapplyPosition();
		refreshDimensions();
	}

	public void setLarvaType(byte isTamed) {
		entityData.set(LARVA_TYPE, isTamed);
	}

	public void setIsSquashed(boolean squashed) {
		entityData.set(IS_SQUASHED, squashed);
	}

	private boolean getIsSquashed() {
		return entityData.get(IS_SQUASHED);
	}

	public float getLarvaSize() {
		return entityData.get(LARVA_SIZE);
	}

	public byte getLarvaType() {
		return entityData.get(LARVA_TYPE);
	}

}
