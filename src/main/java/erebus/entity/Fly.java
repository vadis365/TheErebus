package erebus.entity;

import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class Fly extends AmbientCreature {
	@Nullable
    private BlockPos targetPosition;
	private static final EntityDataAccessor<Byte> HANGING = SynchedEntityData.defineId(Fly.class, EntityDataSerializers.BYTE);
	private static final TargetingConditions FLY_RESTING_TARGETING = TargetingConditions.forNonCombat().range(4.0);
	public int animationTicks, prevAnimationTicks;

	public Fly(EntityType<? extends Fly> type, Level level) { 
		super(type, level);
		setIsFlyHanging(false);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(HANGING, (byte)0);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 2D)
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
    public SoundEvent getAmbientSound() {
		return getIsFlyHanging() && random.nextInt(4) != 0 ? null : ModSounds.FLY_SOUND.get();
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
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {
    }

    @Override
    protected void pushEntities() {
    }

	public boolean getIsFlyHanging() {
		return (entityData.get(HANGING).byteValue() & 1) != 0;
	}

	public void setIsFlyHanging(boolean isHanging) {
		byte b0 = entityData.get(HANGING).byteValue();
		if (isHanging)
			entityData.set(HANGING, Byte.valueOf((byte) (b0 | 1)));
		else
			entityData.set(HANGING, Byte.valueOf((byte) (b0 & -2)));
	}

	@Override
	public void tick() {
		super.tick();

		if (level().isClientSide()) {
			prevAnimationTicks = animationTicks;
			if (animationTicks < 360)
				animationTicks += 1;
			if (animationTicks >= 360) {
				animationTicks -= 360;
				prevAnimationTicks -= 360;
			}
		}

		if (getIsFlyHanging()) {
            this.setDeltaMovement(Vec3.ZERO);
            this.setPosRaw(this.getX(), (double)Mth.floor(this.getY()) + 1.0 - (double)this.getBbHeight(), this.getZ());
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
        }
	}

	@Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        BlockPos blockpos = this.blockPosition();
        BlockPos blockpos1 = blockpos.above();
        if (this.getIsFlyHanging()) {
            boolean flag = this.isSilent();
            if (this.level().getBlockState(blockpos1).isRedstoneConductor(this.level(), blockpos)) {
                if (this.random.nextInt(200) == 0) {
                    this.yHeadRot = (float)this.random.nextInt(360);
                }

                if (this.level().getNearestPlayer(FLY_RESTING_TARGETING, this) != null) {
                    this.setIsFlyHanging(false);
                    if (!flag) {
                        this.level().levelEvent(null, 1025, blockpos, 0);
                    }
                }
            } else {
                this.setIsFlyHanging(false);
                if (!flag) {
                    this.level().levelEvent(null, 1025, blockpos, 0);
                }
            }
        } else {
            if (this.targetPosition != null
                && (!this.level().isEmptyBlock(this.targetPosition) || this.targetPosition.getY() <= this.level().getMinBuildHeight())) {
                this.targetPosition = null;
            }

            if (this.targetPosition == null || this.random.nextInt(30) == 0 || this.targetPosition.closerToCenterThan(this.position(), 2.0)) {
                this.targetPosition = BlockPos.containing(
                    this.getX() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7),
                    this.getY() + (double)this.random.nextInt(6) - 2.0,
                    this.getZ() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7)
                );
            }

            double d2 = (double)this.targetPosition.getX() + 0.5 - this.getX();
            double d0 = (double)this.targetPosition.getY() + 0.1 - this.getY();
            double d1 = (double)this.targetPosition.getZ() + 0.5 - this.getZ();
            Vec3 vec3 = this.getDeltaMovement();
            Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5 - vec3.x) * 0.1F, (Math.signum(d0) * 0.7F - vec3.y) * 0.1F, (Math.signum(d1) * 0.5 - vec3.z) * 0.1F);
            this.setDeltaMovement(vec31);
            float f = (float)(Mth.atan2(vec31.z, vec31.x) * 180.0F / (float)Math.PI) - 90.0F;
            float f1 = Mth.wrapDegrees(f - this.getYRot());
            this.zza = 0.5F;
            this.setYRot(this.getYRot() + f1);
            if (this.random.nextInt(100) == 0 && this.level().getBlockState(blockpos1).isRedstoneConductor(this.level(), blockpos1)) {
                this.setIsFlyHanging(true);
            }
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (isInvulnerableTo(source))
			return false;
		else if (!level().isClientSide() && getIsFlyHanging())
			setIsFlyHanging(false);
		return super.hurt(source, amount);
	}

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
		entityData.set(HANGING, compound.getByte("fly_hanging"));
	}

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
		compound.putByte("fly_hanging", (entityData.get(HANGING)));
	}

	public static boolean canSpawnHere(EntityType<Fly> entity, LevelAccessor level, MobSpawnType spawn, BlockPos pos, RandomSource random) {
		if (pos.getY() >= 120 || pos.getY() <= 0)
			return false;
		else {
			int light = level.getMaxLocalRawBrightness(pos);
			if (random.nextBoolean())
				return false;
			return light <= random.nextInt(7) && checkMobSpawnRules(entity, level, spawn, pos, random);
		}
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 5;
	}

	/* TODO loot Tables
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if (random.nextInt(10) == 0)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.FLY_WING.ordinal()), 0.0F);
		if (random.nextInt(20) == 0)
			entityDropItem(new ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.COMPOUND_EYES.ordinal()), 0.0F);
	}
	*/
}
