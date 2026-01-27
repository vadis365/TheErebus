package erebus.entity;

import erebus.client.particle.ClientParticleTypes.ParticleType;
import erebus.entity.ai.LarvaEatWoodenBlocksGoal;
import erebus.network.client.ParticlePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class BombardierBeetleLarva extends BeetleLarva implements Enemy {
	private static final EntityDataAccessor<Integer> INFLATE_SIZE = SynchedEntityData.defineId(BombardierBeetleLarva.class, EntityDataSerializers.INT);

	public BombardierBeetleLarva(EntityType<? extends BombardierBeetleLarva> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(INFLATE_SIZE, 0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, true));
		goalSelector.addGoal(2, new LarvaEatWoodenBlocksGoal(this, 0.48D, 10));
		goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.48D));
		goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, true, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 8D)
				.add(Attributes.ATTACK_DAMAGE, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 16D)
				.add(Attributes.MOVEMENT_SPEED, 0.35D)
				.add(Attributes.STEP_HEIGHT, 1D);
	}

	@Override
	public void tick() {
		super.tick();
		if (!level().isClientSide()) {
			if (getInflateSize() <= 0)
				setInflateSize(0);
			if (getInflateSize() >= 100)
				explode();
			if (getTarget() == null)
				setInflateSize(getInflateSize() - 2);
			if (getTarget() != null) {
				float distance = distanceTo(getTarget());
				if (getInflateSize() < 100 && distance <= 4)
					setInflateSize(getInflateSize() + 2);
				if (getInflateSize() < 100 && distance > 4)
					setInflateSize(getInflateSize() - 2);
			}
		}
	}

	private void explode() {
		if (!level().isClientSide()) {
			level().explode(this, getX(), getY(), getZ(), 1.5F, Level.ExplosionInteraction.NONE);
			PacketDistributor.sendToPlayersNear((ServerLevel) level(), null, blockPosition().getX(),
					blockPosition().getY(), blockPosition().getZ(), 30,
					new ParticlePacket((byte) ParticleType.BEETLE_LARVA_SQUISH.ordinal(), blockPosition().getX() + 0.5D,
							blockPosition().getY() + 0.5D, blockPosition().getZ() + 0.5D));
			level().playSound(null, blockPosition(), getJumpedOnSound(), SoundSource.NEUTRAL, 1.0F, 0.5F);
			level().playSound(null, blockPosition(), getDeathSound(), SoundSource.NEUTRAL, 1.0F, 0.7F);
			remove(RemovalReason.DISCARDED);
		}
	}

	public void setInflateSize(int size) {
		entityData.set(INFLATE_SIZE, size);
	}

	public int getInflateSize() {
		return entityData.get(INFLATE_SIZE);
	}

	public static boolean canSpawnHereAlt(EntityType<BombardierBeetleLarva> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		float light = level.getLightLevelDependentMagicValue(pos);
		return light >= 0F;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnType, @Nullable SpawnGroupData spawnGroupData) {
		spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
		setLarvaType((byte) 4);
		return spawnGroupData;
	}
}
