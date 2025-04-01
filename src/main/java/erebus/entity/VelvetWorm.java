package erebus.entity;

import javax.annotation.Nullable;

import erebus.entity.projectile.GooBall;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

public class VelvetWorm extends Monster {
	private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(VelvetWorm.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> INFLATE_SIZE = SynchedEntityData.defineId(VelvetWorm.class, EntityDataSerializers.INT);

	public VelvetWorm(EntityType<? extends VelvetWorm> type, Level level) { 
		super(type, level);
		setPathfindingMalus(PathType.WATER, -8F);
		xpReward = 15;
		//stepHeight = 1;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SKIN_TYPE, 0);
		builder.define(INFLATE_SIZE, 0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.7D, false));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4.0F));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5D));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 25D)
				.add(Attributes.FOLLOW_RANGE, 32D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.ATTACK_DAMAGE, 2D);
	}

	public static boolean canSpawnHere(EntityType<VelvetWorm> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
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
    public void tick() {
        super.tick();
		if (getTarget() != null) {
			getLookControl().setLookAt(getTarget(), 10.0F, 10.0F);
			float distance = (float) distanceTo(getTarget());
			if (getInflateSize() < 100 && distance > 3)
				setInflateSize(getInflateSize() + 2);
			if (getInflateSize() >= 100 && distance > 3)
				shootGooBall(getTarget(), distance);
			if (getInflateSize() == 0)
				;
			if (distance > 1.0F && distance < 6.0F)
				if (onGround())
					getMoveControl().setWantedPosition(getTarget().getX(), getTarget().getY(), getTarget().getZ(), 0.6D);
		}
    }

	@Override
	public void makeStuckInBlock(BlockState state, Vec3 motionMultiplier) {
		if (!state.is(Blocks.COBWEB))
			super.makeStuckInBlock(state, motionMultiplier);
	}

	protected SoundEvent getWebSlingThrowSound() {
		return ModSounds.WEBSLING_THROW.get();
	}

	public double getAttackStrength() {
		switch (level().getDifficulty()) {
			default:
				return 4.0D;
			case EASY:
				return 4.0D;
			case NORMAL:
				return 5.0D;
			case HARD:
				return 6.0D;
		}
	}

/* TODO
	@Override
	protected void dropFewItems(boolean hit, int looting) {
		int chanceFiftyFifty = rand.nextInt(2) + 1;

		dropItem(Items.SLIME_BALL, chanceFiftyFifty + looting);
	}
*/

	protected void shootGooBall(Entity entity, float distance) {
		if (distance < 16.0F)
			if (entity instanceof Player) {
				double targetX = entity.getX() - getX();
				double targetY = entity.getBoundingBox().minY + (double) (entity.getBbHeight()) - (getY() + (double) (getBbHeight()));
				double targetZ = entity.getZ() - getZ();
				level().playSound(null, blockPosition(), getWebSlingThrowSound(), SoundSource.HOSTILE, 1.0F, 1.0F);
				setInflateSize(0);
				GooBall gooBall = new GooBall(level(), this, 0F);
				gooBall.setPos(getX(), getY() + (double) (getBbHeight()) + 0.3D, getZ());
				gooBall.shoot(targetX, targetY, targetZ, 1.0F, 0.0F);
				level().addFreshEntity(gooBall);
			}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		setSkin(level.getRandom().nextInt(2));
		return spawnGroupData;
	}

	public void setInflateSize(int size) {
		entityData.set(INFLATE_SIZE, size);
	}

	public int getInflateSize() {
		return entityData.get(INFLATE_SIZE);
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
}
