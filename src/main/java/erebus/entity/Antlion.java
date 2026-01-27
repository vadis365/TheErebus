package erebus.entity;

import erebus.network.client.AntlionParticlePacket;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
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
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class Antlion extends Monster {
	public static final EntityDataAccessor<Boolean> IS_ACTIVE = SynchedEntityData.defineId(Antlion.class, EntityDataSerializers.BOOLEAN);

	public Antlion(EntityType<? extends Antlion> type, Level level) {
		super(type, level);
		xpReward = 17;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6D, false));
		goalSelector.addGoal(2, new AIWander(this, 0.5D));
		goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, false));
		// targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFireAnt.class, false));
		// targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFireAntSoldier.class, false));
		// targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityBlackAnt.class, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 35D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.6D)
				.add(Attributes.ATTACK_DAMAGE, 4D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.ARMOR, 8D);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(IS_ACTIVE, true);
	}

	public boolean isActive() {
		return entityData.get(IS_ACTIVE);
	}

	@Override
	public double getEyeY() {
		return isActive() ? this.position().y + getBbHeight() * 0.3F : this.position().y + 1F;
    }

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.ANTLION_GROWL.get();
	}

	@Override
	protected @NonNull SoundEvent getHurtSound(@NonNull DamageSource source) {
		return ModSounds.ANTLION_GROWL.get();
	}

	@Override
	protected @NonNull SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

	@Override
	protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState block) {
		this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
	}

	//TODO Loot tables
	/*
	 * @Override protected Item getDropItem() { return
	 * Item.getItemFromBlock(Blocks.SAND); }
	 * 
	 * @Override protected void dropFewItems(boolean recentlyHit, int looting) { if
	 * (recentlyHit) { int chance = rand.nextInt(4) + rand.nextInt(1 + looting); int
	 * amount; for (amount = 0; amount < chance; ++amount) entityDropItem(new
	 * ItemStack(ModItems.MATERIALS, 1,
	 * EnumErebusMaterialsType.PLATE_EXO.ordinal()), 0.0F); } }
	 */

	public static boolean canSpawnHere(EntityType<Antlion> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnType, @Nullable SpawnGroupData spawnGroupData) {
		if (spawnType == EntitySpawnReason.COMMAND || spawnType== EntitySpawnReason.SPAWN_ITEM_USE || spawnType == EntitySpawnReason.SPAWNER || spawnType == EntitySpawnReason.DISPENSER)
			setActive(true);
		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	public void tick() {
		super.tick();
		if (!level().isClientSide()) {
			if (getTarget() != null) {
				if (!isActive()) {
					setActive(true);
					if (isHiding()) {
				      Vec3 vec3 = getDeltaMovement();
				      setDeltaMovement(vec3.x, getJumpPower(), vec3.z);
				      PacketDistributor.sendToPlayersNear((ServerLevel) level(), null, getX(), getY() + 1D, getZ(), 30, new AntlionParticlePacket(Block.getId(level().getBlockState(blockPosition())), getX(), getY() + 1D, getZ(), 0.75D, false));
					}
				}
			}

			if (isActive()) {
				if (getTarget() == null && !isInWater() && !isHiding() && canHideIn(level())) {
					setActive(false);
				    setPos(getX(), getY() -1D, getZ());
				    PacketDistributor.sendToPlayersNear((ServerLevel) level(), null, getX(), getY(), getZ(), 30, new AntlionParticlePacket(Block.getId(level().getBlockState(getOnPos())), getX(), getY() + 1D, getZ(), 1.25D, true));
				}
			}
		}
	}

	private boolean isHiding() {
		return getInBlockState().is(Tags.Blocks.SANDS);
	}

	public void setActive(boolean active) {
		entityData.set(IS_ACTIVE, active);
	}

	protected boolean canHideIn(Level level) {
		if (!level.isClientSide()) {
			int minX = (int) Math.floor(getBoundingBox().minX);
			int minY = (int) Math.floor(getBoundingBox().minY);
			int minZ = (int) Math.floor(getBoundingBox().minZ);
			int maxX = (int) Math.floor(getBoundingBox().maxX);
			int maxY = (int) Math.floor(getBoundingBox().maxY);
			int maxZ = (int) Math.floor(getBoundingBox().maxZ);

			for (int k1 = minX; k1 <= maxX; ++k1)
				for (int l1 = minY; l1 <= maxY; ++l1)
					for (int i2 = minZ; i2 <= maxZ; ++i2) {
						BlockState blockStateBelow = level().getBlockState(new BlockPos(k1, l1, i2).below());
						if (!blockStateBelow.is(Tags.Blocks.SANDS))
							return false;
					}
		}
		return true;
	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel level, DamageSource source, float damage) {
		if (source.is(DamageTypes.IN_WALL) || source.is(DamageTypes.DROWN)) {
			return false;
		}
		return super.hurtServer(level, source, damage);
	}

	public static class AIWander extends WaterAvoidingRandomStrollGoal {

		private final Antlion antlion;

		public AIWander(Antlion antlion, double speedIn) {
			super(antlion, speedIn);
			this.antlion = antlion;
		}

		@Override
		public boolean canUse() {
			return !antlion.getInBlockState().is(Tags.Blocks.SANDS) && super.canUse();
		}

		@Override
		public boolean canContinueToUse() {
			return !antlion.getInBlockState().is(Tags.Blocks.SANDS) && !antlion.getNavigation().isDone();
		}

	}
}
