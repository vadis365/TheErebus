package erebus.entity;

import java.util.Random;

import javax.annotation.Nullable;

import erebus.entity.ai.FlyingMoveControlLessSpin;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class Dragonfly extends Monster {

	public double pickupHeight;
	private boolean dropped;
	private int droptime = 0;// cool-down for picking up
	private int countDown;// makes sure player is always dropped
	public int animationTicks, prevAnimationTicks;
	private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(Dragonfly.class, EntityDataSerializers.INT);

	public Dragonfly(EntityType<? extends Dragonfly> type, Level level) {
		super(type, level);
		this.moveControl = new FlyingMoveControlLessSpin(this, 10, false);
		//setPathfindingMalus(PathType.BLOCKED, -8.0F);
		//setPathfindingMalus(PathType.OPEN, 8.0F);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SKIN_TYPE, 1);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.75D, true));
		//goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
		//goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		goalSelector.addGoal(4, new AIFlyingWander(this, 1D, 0.01F));
		targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers(Dragonfly.class));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, false));
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 15D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.75D)
				.add(Attributes.FLYING_SPEED, 1D)
				.add(Attributes.ATTACK_DAMAGE, 1D);
	}

	@Override
    protected PathNavigation createNavigation(Level level){
		return new FlyingPathNavigation(this, level);
	}

	@Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {
    }

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

	public boolean captured() {
		return isBeingRidden();
	}

	private void setCountdown(int count) {
		countDown = count;
	}

	private void setDropped(boolean dropstate) {
		dropped = dropstate;
	}

	public boolean getDropped() {
		return dropped;
	}

	@Override
	protected float getSoundVolume() {
		return 0.3F;
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() * 0.5F;
	}

	@Override
    public SoundEvent getAmbientSound() {
		return ModSounds.FLY_SOUND.get();
	}

	@Override
    protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.FLY_HURT.get();
	}

	@Override
    protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
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
				this.setDeltaMovement(vec3.multiply(1.0D, 0.3D, 1.0D));
			else
				this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
			this.yBodyRot = yBodyRotO;
		}
		
		if (isBeingRidden()){
			if (getTarget() != null && !level().isEmptyBlock(blockPosition().below(3)) || !getDropped() && getY() < pickupHeight + 10D) {
				getNavigation().stop();
				getNavigation().moveTo(getX(), getY() + 10D, getZ(), 1D);
				this.setDeltaMovement(vec3.multiply(1.0D, 1D, 1.0D));
			}
			
			if (!level().isClientSide() && captured() && (getY() > pickupHeight + 10D || countDown <= 0 || !level().isClientSide() && captured() && level().getBlockState(blockPosition().above()).isRedstoneConductor(level(), blockPosition()))) {
				setDropped(true);
				ejectPassengers();
			}
		}

		if (dropped) {
			droptime++;
			if (droptime >= 20) {
				setDropped(false);
				droptime = 0;
			}
		}

		if (countDown >= 0)
			countDown--;

		if (level().isClientSide())
			if (getSkin() == 0) {
				//spawnParticles(level(), getX(), getY(), getZ(), random);
				//if (!hasCustomName())
				//	setCustomName(Component "Ender Dragonfly");
			}

		if(isInWater())
			getNavigation().moveTo(getX(), getY() + 1D, getZ(), 0.5D);
	}
	
    @Nullable
    public boolean isBeingRidden()  {
        return !this.getPassengers().isEmpty() && (Entity)this.getPassengers().get(0) != null;
    }

    @OnlyIn(Dist.CLIENT)
	public void spawnParticles(Level level, double x, double y, double z, Random rand) {
		for (int count = 0; count < 20; ++count) {
			double velX = 0.0D;
			double velY = 0.0D;
			double velZ = 0.0D;
			int motionX = rand.nextInt(2) * 2 - 1;
			int motionZ = rand.nextInt(2) * 2 - 1;
			velY = (rand.nextFloat() - 0.5D) * 0.125D;
			velZ = rand.nextFloat() * 1.0F * motionZ;
			velX = rand.nextFloat() * 1.0F * motionX;
		//	Erebus.PROXY.spawnCustomParticle("portal", level(), x, y, z, velX, velY, velZ);
		//	TODO Particles
		}
	}

	@Override
	public void playerTouch(Player player) {
		super.playerTouch(player);
		if (!level().isClientSide() && !player.isCreative() && !captured() && random.nextInt(20) == 0 && !getDropped()) {
			pickupHeight = getY();
			setPos(getX(), player.getY() + player.getBbHeight() + getBbHeight() * 0.5F, getZ());
			player.startRiding(this, true);
			setCountdown(60);
		}
		if (player.isCrouching())
			player.setPose(Pose.STANDING);
	}

	public double getCapturedOffset() {
		if (getCapturedPlayer() != null)
			return getCapturedPlayer().getBbHeight();
		else
			return 0;
	}

	public Player getCapturedPlayer() {
		for (Entity entity : this.getPassengers())
			if (entity instanceof Player)
				return (Player) entity;
		return null;
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		if (entity instanceof LivingEntity || entity instanceof Player) {
			double a = Math.toRadians(yBodyRot);
			double offSetX = -Math.sin(a) * -0.6D;
			double offSetZ = Math.cos(a) * -0.6D;
			entity.setPos(getX() - offSetX, getY() - getCapturedOffset(), getZ() - offSetZ);
			if (entity.isCrouching())
				entity.setPose(Pose.STANDING);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		if (isInvulnerableTo(source))
			return false;
		else if (super.hurt(source, damage)) {
			if (isBeingRidden() && getCapturedPlayer() != null) {
				setDropped(true);
				ejectPassengers();
				return true;
			} else
				return true;
		} else
			return false;
	}

	public static boolean canSpawnHere(EntityType<Dragonfly> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		if (pos.getY() > 100)
			return false;
		else {
			int light = level.getMaxLocalRawBrightness(pos);
			return light > random.nextInt(7) ? false : checkMobSpawnRules(entity, level, spawn, pos, random);
		}
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 6;
	}
/* TODO
	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.DRAGONFLY_WING.ordinal()), 0.0F);
		if (random.nextInt(5) == 0)
			entityDropItem(new ItemStack(ModItems.MATERIALS, random.nextInt(1) + 1 + looting, EnumErebusMaterialsType.COMPOUND_EYES.ordinal()), 0.0F);
		if (getSkin() == 0)
			entityDropItem(new ItemStack(Items.ENDER_PEARL, random.nextInt(1) + 1 + looting), 0.0F);
	}
*/

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		setSkin(level.getRandom().nextInt(51));
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
		setSkin(nbt.getInt("skin"));
	}

	class AIFlyingWander extends WaterAvoidingRandomStrollGoal {
		public AIFlyingWander(Dragonfly creatureIn, double speedIn, float chance) {
			super(creatureIn, speedIn, chance);
		}
	
		@Nullable
		protected Vec3 getPosition() {
			Vec3 vec3 = this.mob.getViewVector(0.0F);
			Vec3 vec31 = HoverRandomPos.getPos(this.mob, 16, 2, vec3.x, vec3.z, ((float) Math.PI / 2F), 2, 1);
			return vec31 != null ? vec31 : AirAndWaterRandomPos.getPos(this.mob, 16, 2, -2, vec3.x, vec3.z, (double) ((float) Math.PI / 2F));
		}
	}
}
