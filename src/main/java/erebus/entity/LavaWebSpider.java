package erebus.entity;

import erebus.entity.ai.ThrowWebAttackGoal;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class LavaWebSpider extends Monster {

	private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(LavaWebSpider.class, EntityDataSerializers.BYTE);

	public LavaWebSpider(EntityType<? extends LavaWebSpider> type, Level level) { 
		super(type, level);
		setPathfindingMalus(PathType.LAVA, 0.0F);
		setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
		xpReward = 10;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(CLIMBING, (byte)0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new ThrowWebAttackGoal(this, 0.8D, OtherBlocks.LAVA_WEB.get().defaultBlockState()));
		goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4F));
		goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.6D, true));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6,  new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.ATTACK_DAMAGE, 4D);
	}

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

	public static boolean canSpawnHere(EntityType<LavaWebSpider> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		BlockPos.MutableBlockPos blockPosMutable = pos.mutable();
		do {
			blockPosMutable.move(Direction.UP);
		} while (level.getFluidState(blockPosMutable).is(FluidTags.LAVA));
		return level.getBlockState(blockPosMutable).isAir();
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		if (level.getBlockState(pos).getFluidState().is(FluidTags.LAVA)) {
			return 10.0F;
		} else {
			return this.isInLava() ? Float.NEGATIVE_INFINITY : 0.0F;
		}
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader level) {
		return level.isUnobstructed(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide())
            setClimbing(horizontalCollision);

		if (level().isClientSide() && level().getGameTime() % 40 == 0)
			lavaParticles(level(), getX(), getY() + 1.3D, getZ(), random);
    }

	@OnlyIn(Dist.CLIENT)
	public void lavaParticles(Level level, double x, double y, double z, RandomSource random) {
		level.addParticle(ParticleTypes.LAVA, false, x, y, z, 0F, 0F, 0F);
	}

	@Override
	  public void aiStep() {
		super.aiStep();
		
		if (random.nextInt(50) == 0) {
			int i = Mth.floor(getX());
			int j = Mth.floor(getY());
			int k = Mth.floor(getZ());
			for (int l = 0; l < 4; ++l) {
				i = Mth.floor(getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
				j = Mth.floor(getY());
				k = Mth.floor(getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
				BlockPos blockpos = new BlockPos(i, j, k);
				BlockState blockstate = BaseFireBlock.getState(level(), blockpos);
				if (level().getBlockState(blockpos).isAir() && BaseFireBlock.canBePlacedAt(level(), blockpos, Direction.DOWN))
					level().setBlock(blockpos, blockstate, 11);
			}
		}
	}

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 motionMultiplier) {
		if (!state.is(Blocks.COBWEB) && !state.is(OtherBlocks.LAVA_WEB.get()))
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
    public boolean canBeAffected(MobEffectInstance potioneffect) {
		 return (!potioneffect.is(MobEffects.POISON) && !potioneffect.is(MobEffects.WITHER) && super.canBeAffected(potioneffect));
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		if (source.is(DamageTypes.IN_WALL)) {
			return false;
		}
		return super.hurt(source, damage);
	}

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
		RandomSource randomsource = level.getRandom();

		if (randomsource.nextInt(100) == 0) {
			MoneySpider moneyspider = ModEntities.MONEY_SPIDER.get().create(this.level());
			if (moneyspider != null) {
				moneyspider.moveTo(getX(), getY(), getZ(), getYRot(), 0.0F);
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
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		super.positionRider(entity, moveFunction);
		if (entity instanceof MoneySpider) {
			double a = Math.toRadians(yBodyRot);
			double offSetX = -Math.sin(a);
			double offSetZ = Math.cos(a);
			entity.setPos(getX() - offSetX, getY() + getBbHeight() + 0.0625F, getZ() - offSetZ);
		}
	}

}