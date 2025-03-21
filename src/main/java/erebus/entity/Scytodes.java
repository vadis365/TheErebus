package erebus.entity;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Scytodes  extends Monster {

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
		//tasks.addTask(1, new Scytodes.AIWebSlingAttack(this));
		goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4F));
		goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.5D, true));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6,  new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, true));
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
/*
	@Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

		if (getEntityWorld().rand.nextInt(100) == 0) {
			EntityMoneySpider moneyspider = new EntityMoneySpider(getEntityWorld());
			moneyspider.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			moneyspider.onInitialSpawn(difficulty, (IEntityLivingData) null);
			getEntityWorld().spawnEntity(moneyspider);
			moneyspider.startRiding(this);
		}
		if (livingdata == null) {
			livingdata = new EntitySpider.GroupData();
            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty())
                ((EntitySpider.GroupData)livingdata).setRandomEffect(this.world.rand);

            if (livingdata instanceof EntitySpider.GroupData) {
                Potion potion = ((EntitySpider.GroupData)livingdata).effect;
                if (potion != null)
                    this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
            }
		}
		return livingdata;
	}
*/
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
/*
	static class AIWebSlingAttack extends EntityAIBase {
		private final Scytodes scytodes;
		private int attackStep;
		private int attackTime;

		public AIWebSlingAttack(Scytodes scytodesIn) {
			scytodes = scytodesIn;
			setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = scytodes.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		@Override
		public void startExecuting() {
			attackStep = 0;
		}

		@Override
		public void updateTask() {
			--attackTime;
			EntityLivingBase entitylivingbase = scytodes.getAttackTarget();
			double distance = scytodes.getDistanceSq(entitylivingbase);

			if (distance < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					scytodes.attackEntityAsMob(entitylivingbase);
				}

				scytodes.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, scytodes.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

			} else if (distance < 256.0D) {
				double targetX = entitylivingbase.posX - scytodes.posX;
				double targetY = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (scytodes.posY + (double) (scytodes.height / 2.0F));
				double targetZ = entitylivingbase.posZ - scytodes.posZ;

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

					if (attackStep > 1 && entitylivingbase instanceof EntityPlayer) {
						scytodes.getEntityWorld().playSound((EntityPlayer) null, scytodes.getPosition(), scytodes.getWebSlingThrowSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
						for (int count = 0; count < 1; ++count) {
							EntityWebSling webSling = new EntityWebSling(scytodes.getEntityWorld(), scytodes);
							webSling.posY = scytodes.posY + (double) (scytodes.height / 2.0F) + 0.5D;
							webSling.setType((byte) 0);
							webSling.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
							scytodes.getEntityWorld().spawnEntity(webSling);
						}
					}
				}
				scytodes.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
				scytodes.getNavigator().clearPath();
				scytodes.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, scytodes.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
			}
			super.updateTask();
		}
	}
	*/
}