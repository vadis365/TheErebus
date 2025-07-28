package erebus.entity;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Wasp extends Monster {
	private static final EntityDataAccessor<Boolean> IS_BOSS = SynchedEntityData.defineId(Wasp.class, EntityDataSerializers.BOOLEAN);
	public int animationTicks, prevAnimationTicks;

	public Wasp(EntityType<? extends Wasp> type, Level level) {
		super(type, level);
		this.moveControl = new FlyingMoveControl(this, 10, false);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1D, true));
		goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		goalSelector.addGoal(4, new Wasp.EntityAIFlyingWander(this, 0.75D, 0.01F));
		targetSelector.addGoal(0, new NearestAttackableTargetGoal<Player>(this, Player.class, true, false));
//		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Monster>(this, Monster.class, 0, true, false, p -> Config.HORNET_ATTACK_MOBS.get()));
//		targetSelector.addGoal(2, new NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity.class, 0, true, false, p -> Config.HORNET_ATTACK_CREATURES.get()));
		targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers(Wasp.class));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(IS_BOSS, false);
	}

	@Override
	public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
		if (IS_BOSS.equals(key)) {
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
		return getIsBoss() ? super.getDefaultDimensions(pose).scale(3F, 2.5F) : super.getDefaultDimensions(pose).scale(1F, 1F);
	}

	public Boolean getIsBoss() {
		return entityData.get(IS_BOSS);
	}

	public void setIsBoss(boolean boss, boolean resetHealth) {
		entityData.set(IS_BOSS, boss);
		reapplyPosition();
		refreshDimensions();

		if (boss) {
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(60D);
			getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8D);
			getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.9D);
			getAttribute(Attributes.FLYING_SPEED).setBaseValue(1.25D);
		}
		else
		{
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(25D);
			getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4D);
			getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.75D);
			getAttribute(Attributes.FLYING_SPEED).setBaseValue(1D);
		}

		if (resetHealth)
			setHealth(getMaxHealth());

		if (!hasCustomName())
			if (random.nextBoolean())
				if (random.nextBoolean())
					setCustomName(Component.literal("Livid's Bane"));
				else
					setCustomName(Component.literal("Nano's Nemesis"));
			else
				setCustomName(Component.literal("Hornet of Despair"));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		RandomSource randomsource = level.getRandom();
		int isBoss = randomsource.nextInt(32);
		if(isBoss == 0)
			setIsBoss(true, true);
		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}

	@Override
	  public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("mobType", getIsBoss());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		setIsBoss(nbt.getBoolean("mobType"), false);
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
	}

	public static AttributeSupplier.Builder createAttributes() {
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 25D)
					.add(Attributes.FOLLOW_RANGE, 32D)
					.add(Attributes.MOVEMENT_SPEED, 0.75D)
					.add(Attributes.FLYING_SPEED, 1D)
					.add(Attributes.ATTACK_DAMAGE, 4D);
	}

	public static boolean canSpawnHere(EntityType<Wasp> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
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
	protected SoundEvent getAmbientSound() {
		return ModSounds.WASP_SOUND.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.WASP_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

	@Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

/* TODO - make loot tables and stuff for mob drops
@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int chance = rand.nextInt(4) + rand.nextInt(1 + looting);
		int amount;
		for (amount = 0; amount < chance; ++amount)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.WASP_STING.ordinal()), 0.0F);
		if (getIsBoss() == 1)
			entityDropItem(new ItemStack(ModItems.ANTI_VENOM_BOTTLE), 0.0F);
	}

*/
	public boolean isFlying() {
		return !onGround();
	}

	@Override
	public void tick() {
		super.tick();

		if (level().isClientSide()) {
			prevAnimationTicks = animationTicks;
			if (animationTicks < 720)
				animationTicks += 1;
			if (animationTicks >= 720) {
				animationTicks -= 720;
				prevAnimationTicks -= 720;
			}
		}

		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround() && vec3.y < 0.0D) {
			if (getTarget() == null)
				this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
			else
				this.setDeltaMovement(vec3.multiply(1.0D, 0.75D, 1.0D));
		}

		if(isInWater())
			getNavigation().moveTo(getX(), getY() + 1D, getZ(), 0.5D);
	}

	@Override
    protected PathNavigation createNavigation(Level level){
		return new FlyingPathNavigation(this, level);
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

	@Override
	public boolean canAttackType(EntityType<?> typeIn) {
		return typeIn != ModEntities.WASP.get();
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		if (hasLineOfSight(entity)) {
			if (super.doHurtTarget(entity)) {
				if (entity instanceof LivingEntity) {
					byte duration = 0;

					if (level().getDifficulty().ordinal() > Difficulty.EASY.ordinal())
						if (level().getDifficulty() == Difficulty.NORMAL)
							duration = 3;
						else if (level().getDifficulty() == Difficulty.HARD)
							duration = 5;

					if (duration > 0)
						((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON, duration * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	class EntityAIFlyingWander extends WaterAvoidingRandomStrollGoal {
		public EntityAIFlyingWander(Wasp creatureIn, double speedIn, float chance) {
			super(creatureIn, speedIn, chance);
		}

		@Nullable
		protected Vec3 getPosition() {
			Vec3 vec3 = this.mob.getViewVector(0.0F);
			Vec3 vec31 = HoverRandomPos.getPos(this.mob, 8, 7, vec3.x, vec3.z, ((float) Math.PI / 2F), 2, 1);
			return vec31 != null ? vec31 : AirAndWaterRandomPos.getPos(this.mob, 8, 4, -2, vec3.x, vec3.z, (float) Math.PI / 2F);
		}
	}

}