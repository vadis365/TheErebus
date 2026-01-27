package erebus.entity;

import erebus.entity.ai.ThrowWebAttackGoal;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class Scytodes extends Monster {

	private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(Scytodes.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(Scytodes.class, EntityDataSerializers.BYTE);

	public Scytodes(EntityType<? extends Scytodes> type, Level level) { 
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SKIN_TYPE, 0);
		builder.define(CLIMBING, (byte)0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new ThrowWebAttackGoal(this, 0.9D, Blocks.COBWEB.defaultBlockState()));
		goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4F));
		goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.6D, true));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6,  new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Villager.class, true, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 25D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.ATTACK_DAMAGE, 4D);
	}

    @Override
    protected @NonNull PathNavigation createNavigation(@NonNull Level level) {
        return new WallClimberNavigation(this, level);
    }

	public static boolean canSpawnHere(EntityType<Scytodes> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
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
    public void tick() {
        super.tick();
        if (!level().isClientSide())
            setClimbing(horizontalCollision);
    }

	@Override
	public boolean causeFallDamage(double fallDistance, float damageModifier, @NonNull DamageSource damageSource) {
		return false;
	}

    @Override
    public void makeStuckInBlock(BlockState state, @NonNull Vec3 motionMultiplier) {
        if (!state.is(Blocks.COBWEB))
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
            climingState = (byte)(climingState | 1);
        else 
        	climingState = (byte)(climingState & -2);
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
        return SoundEvents.SPIDER_AMBIENT;
    }

    @Override
    protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource damageSource) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnType, @Nullable SpawnGroupData spawnGroupData) {
		spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
		RandomSource randomsource = level.getRandom();
		setSkin(level.getRandom().nextInt(4));

		if (randomsource.nextInt(100) == 0) {
			MoneySpider moneyspider = ModEntities.MONEY_SPIDER.get().create((Level) level, EntitySpawnReason.NATURAL);
			if (moneyspider != null) {
				moneyspider.moveTowardsClosestSpace(getX(), getY(), getZ());
				moneyspider.finalizeSpawn(level, difficulty, spawnType, null);
				moneyspider.startRiding(this);
			}
		}

		if (spawnGroupData == null)
			spawnGroupData = new Spider.SpiderEffectsGroupData();
			if (level.getDifficulty() == Difficulty.HARD && randomsource.nextFloat() < 0.1F * difficulty.getSpecialMultiplier())
				((Spider.SpiderEffectsGroupData) spawnGroupData).setRandomEffect(randomsource);

		if (spawnGroupData instanceof Spider.SpiderEffectsGroupData spider$spidereffectsgroupdata) {
			Holder<MobEffect> holder = spider$spidereffectsgroupdata.effect;
			if (holder != null)
				this.addEffect(new MobEffectInstance(holder, -1));
		}

		return spawnGroupData;
	}

	@Override
	public void positionRider(@NonNull Entity entity, Entity.@NonNull MoveFunction moveFunction) {
		super.positionRider(entity, moveFunction);
		if (entity instanceof MoneySpider) {
			double a = Math.toRadians(yBodyRot);
			double offSetX = -Math.sin(a) * 0.35D;
			double offSetZ = Math.cos(a) * 0.35D;
			entity.setPos(getX() - offSetX, getY() + getBbHeight() - 0.2F, getZ() - offSetZ);
		}
	}

	public void setSkin(int skinType) {
		entityData.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return entityData.get(SKIN_TYPE);
	}

	@Override
	  public void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putInt("skin", getSkin());
	}

	@Override
	public void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		setSkin(input.getIntOr("skin", 0));
	}
}
