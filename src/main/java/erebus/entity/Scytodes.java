package erebus.entity;

import java.util.EnumSet;

import javax.annotation.Nullable;

import erebus.entity.projectile.WebSling;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Scytodes extends Monster {

	private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(Scytodes.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(Scytodes.class, EntityDataSerializers.BYTE);

	public Scytodes(EntityType<? extends Scytodes> type, Level level) { 
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SKIN_TYPE, random.nextInt(4));
		builder.define(CLIMBING, (byte)0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new Scytodes.AIWebSlingAttack(this));
		goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4F));
		goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.5D, true));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6,  new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, true));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 25D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.ATTACK_DAMAGE, 4D);
	}

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

	public static boolean canSpawnHere(EntityType<Scytodes> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
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
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 motionMultiplier) {
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
    public boolean canBeAffected(MobEffectInstance potioneffect) {
		 return (potioneffect.is(MobEffects.POISON) || potioneffect.is(MobEffects.WITHER) ? false : super.canBeAffected(potioneffect));
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

	protected SoundEvent getWebSlingThrowSound() {
		return ModSounds.WEBSLING_THROW.get();
	}

	 @Nullable
	    @Override
	    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		 spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	        RandomSource randomsource = level.getRandom();

		if (randomsource.nextInt(1) == 0) {
			MoneySpider moneyspider = ModEntities.MONEY_SPIDER.get().create(this.level());
			moneyspider.setPos(getX(), getY(), getZ());
			moneyspider.setYRot(getYRot());
			//moneyspider.finalizeSpawn(level, difficulty, spawnType, null);
			moneyspider.startRiding(this);
		}
	
        if (spawnGroupData == null) {
            spawnGroupData = new Spider.SpiderEffectsGroupData();
            if (level.getDifficulty() == Difficulty.HARD && randomsource.nextFloat() < 0.1F * difficulty.getSpecialMultiplier()) {
                ((Spider.SpiderEffectsGroupData)spawnGroupData).setRandomEffect(randomsource);
            }
        }

        if (spawnGroupData instanceof Spider.SpiderEffectsGroupData spider$spidereffectsgroupdata) {
            Holder<MobEffect> holder = spider$spidereffectsgroupdata.effect;
            if (holder != null) {
                this.addEffect(new MobEffectInstance(holder, -1));
            }
        }

        return spawnGroupData;
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
		if (nbt.contains("skin"))
			setSkin(nbt.getInt("skin"));
		else
			setSkin(random.nextInt(4));
	}

	static class AIWebSlingAttack extends Goal {
		private final Scytodes scytodes;
		private int attackStep;
		private int attackTime;

		public AIWebSlingAttack(Scytodes scytodesIn) {
			scytodes = scytodesIn;
			setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			LivingEntity livingentity = scytodes.getTarget();
			return livingentity != null && livingentity.isAlive();
		}

		@Override
		public void start() {
			attackStep = 0;
		}

		@Override
		 public void tick() {
			--attackTime;
			LivingEntity livingentity = scytodes.getTarget();
			double distance = scytodes.distanceToSqr(livingentity);

			if (distance < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					scytodes.doHurtTarget(livingentity);
				}

				scytodes.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), scytodes.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));

			} else if (distance < 256.0D) {
				double targetX = livingentity.getX() - scytodes.getX();
				double targetY = livingentity.getBoundingBox().minY + (double) (livingentity.getBbHeight() / 2.0F) - (scytodes.getY() + (double) (scytodes.getBbHeight() / 2.0F));
				double targetZ = livingentity.getZ() - scytodes.getZ();

				if (attackTime <= 0) {
					++attackStep;
					if (attackStep == 1)
						attackTime = 60;
					else if (attackStep <= 4)
						attackTime = 6;
					else {
						attackTime = 100;
						attackStep = 0;
					}

					if (attackStep > 1 && livingentity instanceof Player) {
						scytodes.level().playSound( null, scytodes.blockPosition(), scytodes.getWebSlingThrowSound(), SoundSource.HOSTILE, 1.0F, 1.0F);
						for (int count = 0; count < 1; ++count) {
							WebSling webSling = new WebSling(scytodes.level(), scytodes, 0);
							webSling.setPos(scytodes.getX(), scytodes.getY() + (double) (scytodes.getBbHeight() / 2.0F) + 0.5D, scytodes.getZ());
							webSling.setWebType((byte) 0);
							webSling.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
							scytodes.level().addFreshEntity(webSling);
						}
					}
				}
				scytodes.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
				scytodes.getNavigation().isDone();
				scytodes.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), scytodes.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
			}
			super.tick();
		}
	}
}