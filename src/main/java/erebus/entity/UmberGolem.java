package erebus.entity;

import erebus.block.UmberGolemStatueBlock;
import erebus.entity.ai.AnimatedBlockFollowOwnerGoal;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class UmberGolem extends AnimatedBlock {
    private static final EntityDataAccessor<Boolean> CLIMBING = SynchedEntityData.defineId(UmberGolem.class, EntityDataSerializers.BOOLEAN);

    public UmberGolem(EntityType<? extends UmberGolem> type, Level level) {
        super(type, level);
        setPersistenceRequired();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AnimatedBlock.createAttributes().add(Attributes.MAX_HEALTH, 75).add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 5).add(Attributes.TEMPT_RANGE, 10);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CLIMBING, false);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new AnimatedBlockFollowOwnerGoal(this));
        goalSelector.addGoal(2, new TemptGoal(this, 0.5, stack -> stack.is(ModItems.WAND_OF_ANIMATION), false));
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.5, false));
        goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, true));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    public boolean onClimbable() {
        return entityData.get(CLIMBING);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) entityData.set(CLIMBING, horizontalCollision);
    }

    @Override
    public boolean removeWhenFarAway(double distance) {
        return false;
    }

    @Override
    public boolean canBeLeashed() {
        return true;
    }

    @Override
    public BlockState getBlockType() {
        return ModBlocks.UMBER_GOLEM_STATUE.get().defaultBlockState().setValue(UmberGolemStatueBlock.FACING, getDirection());
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState state) {
        playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1);
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!(target instanceof LivingEntity living)) return false;
        if (onGround()) {
            var horizontal = new Vec3(target.getX() - getX(), 0, target.getZ() - getZ()).normalize().scale(0.4);
            setDeltaMovement(horizontal.x + getDeltaMovement().x * 0.2, 0.4, horizontal.z + getDeltaMovement().z * 0.2);
        }
        if (!living.hurtServer(level, damageSources().mobAttack(this), (float) getAttributeValue(Attributes.ATTACK_DAMAGE))) return false;
        double yaw = Math.toRadians(getYRot());
        living.push(-Math.sin(yaw) * 0.5, 0.4, Math.cos(yaw) * 0.5);
        living.hurtMarked = true;
        living.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, level.getDifficulty().getId() * 50, 0), this);
        playSound(SoundEvents.PLAYER_BIG_FALL, 1, 1);
        return true;
    }
}
