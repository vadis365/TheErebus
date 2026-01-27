package erebus.entity;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class Moth extends AmbientCreature {
	private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(Moth.class, EntityDataSerializers.INT);
	@Nullable
    private BlockPos targetPosition;

	public Moth(EntityType<? extends Moth> type, Level level) { 
		super(type, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(SKIN_TYPE, 0);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 4D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() * 0.95F;
	}

	@Override
    protected SoundEvent getDeathSound() {
		return ModSounds.SQUISH.get();
	}

    @Override
    public void tick() {
        super.tick();
		setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
	}

	@Override
    protected void customServerAiStep(@NonNull ServerLevel level) {
        super.customServerAiStep(level);

		if (this.targetPosition != null && (!this.level().isEmptyBlock(this.targetPosition) || this.targetPosition.getY() <= this.level().getMinY())) {
			this.targetPosition = null;
		}

		if (this.targetPosition == null || this.random.nextInt(30) == 0 || this.targetPosition.closerToCenterThan(this.position(), 2.0)) {
			this.targetPosition = BlockPos.containing(
					this.getX() + (double) this.random.nextInt(7) - (double) this.random.nextInt(7),
					this.getY() + (double) this.random.nextInt(6) - 2.0,
					this.getZ() + (double) this.random.nextInt(7) - (double) this.random.nextInt(7));
		}

		double d2 = (double) this.targetPosition.getX() + 0.5 - this.getX();
		double d0 = (double) this.targetPosition.getY() + 0.1 - this.getY();
		double d1 = (double) this.targetPosition.getZ() + 0.5 - this.getZ();
		Vec3 vec3 = this.getDeltaMovement();
		Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5 - vec3.x) * 0.1F, (Math.signum(d0) * 0.7F - vec3.y) * 0.1F, (Math.signum(d1) * 0.5 - vec3.z) * 0.1F);
		this.setDeltaMovement(vec31);
		float f = (float) (Mth.atan2(vec31.z, vec31.x) * 180.0F / (float) Math.PI) - 90.0F;
		float f1 = Mth.wrapDegrees(f - this.getYRot());
		this.zza = 0.5F;
		this.setYRot(this.getYRot() + f1);
	}

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(@NonNull Entity entity) {
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, @NonNull BlockState state, @NonNull BlockPos pos) {
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnType, @Nullable SpawnGroupData spawnGroupData) {
		setSkin(level.getRandom().nextInt(3));
		return spawnGroupData;
	}

	public void setSkin(int skinType) {
		entityData.set(SKIN_TYPE, skinType);
	}

	public int getSkin() {
		return entityData.get(SKIN_TYPE);
	}

	@Override
	public void addAdditionalSaveData(@NonNull ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putInt("skin", getSkin());
	}

	@Override
	public void readAdditionalSaveData(@NonNull ValueInput input) {
		super.readAdditionalSaveData(input);
		setSkin(input.getIntOr("skin", 0));
	}

	public static boolean canSpawnHere(EntityType<Moth> entity, LevelAccessor level, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
		if (pos.getY() >= 120 || pos.getY() <= 0)
			return false;
		else {
			int light = level.getMaxLocalRawBrightness(pos);
			if (random.nextBoolean())
				return false;
			return light <= random.nextInt(4) && checkMobSpawnRules(entity, level, spawn, pos, random);
		}
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return !world.containsAnyLiquid(getBoundingBox()) && world.noCollision(this);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 5;
	}

/* TODO
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		if (rand.nextInt(5) == 0)
			entityDropItem(new ItemStack(Items.GLOWSTONE_DUST, 1, 0), 0.0F);
			
	}
*/
}
