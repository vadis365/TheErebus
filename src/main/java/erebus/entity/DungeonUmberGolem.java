package erebus.entity;

import erebus.entity.projectile.GooBall;
import erebus.entity.projectile.PoisonJet;
import erebus.entity.projectile.WebSling;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModBlockTags;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class DungeonUmberGolem extends Monster {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(DungeonUmberGolem.class, EntityDataSerializers.INT);
    private final ServerBossEvent bossEvent = new ServerBossEvent(Mth.createInsecureUUID(random), getDisplayName(),
            ServerBossEvent.BossBarColor.RED, ServerBossEvent.BossBarOverlay.PROGRESS);
    private int rangedTicks;
    private @Nullable BlockPos breakingPos;
    private @Nullable BlockState breakingState;
    private int breakingTicks;

    public DungeonUmberGolem(EntityType<? extends DungeonUmberGolem> type, Level level) {
        super(type, level);
        xpReward = 120;
        setPersistenceRequired();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 100).add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.MOVEMENT_SPEED, 0.7).add(Attributes.FOLLOW_RANGE, 32);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5, true));
        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    public int getVariant() {
        return entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        applyVariant(variant);
        setHealth(getMaxHealth());
    }

    private void applyVariant(int variant) {
        int value = Mth.clamp(variant, 0, 3);
        entityData.set(VARIANT, value);
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(100 + value * 50);
        getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4 + value);
        bossEvent.setColor(switch (value) {
            case 1 -> ServerBossEvent.BossBarColor.WHITE;
            case 2 -> ServerBossEvent.BossBarColor.YELLOW;
            case 3 -> ServerBossEvent.BossBarColor.GREEN;
            default -> ServerBossEvent.BossBarColor.RED;
        });
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("GuardianVariant", getVariant());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        int variant = input.getIntOr("GuardianVariant", 0);
        applyVariant(variant);
        super.readAdditionalSaveData(input);
        applyVariant(variant);
        bossEvent.setName(getDisplayName());
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        bossEvent.setName(getDisplayName());
    }

    @Override
    public void startSeenByPlayer(@NonNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NonNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player);
    }

    @Override
    public boolean removeWhenFarAway(double distance) {
        return false;
    }

    @Override
    protected @NonNull SoundEvent getDeathSound() {
        return ModSounds.SQUISH.get();
    }

    @Override
    protected void playStepSound(@NonNull BlockPos pos, @NonNull BlockState state) {
        playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1);
    }

    @Override
    public void makeStuckInBlock(BlockState state, @NonNull Vec3 multiplier) {
        if (!state.is(Blocks.COBWEB) && !state.is(ModBlocks.WITHER_WEB)) super.makeStuckInBlock(state, multiplier);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!(level() instanceof ServerLevel server)) return;
        bossEvent.setProgress(getHealth() / getMaxHealth());
        var target = getTarget();
        if (!isAlive() || isNoAi() || target == null || !target.isAlive()) {
            rangedTicks = 0;
            clearBreaking();
            return;
        }
        if (horizontalCollision && EventHooks.canEntityGrief(server, this)) {
            double yaw = Math.toRadians(yBodyRot);
            double reach = getBbWidth() / 2 + 0.1;
            var front = BlockPos.containing(getX() - Math.sin(yaw) * reach, getY(), getZ() + Math.cos(yaw) * reach);
            var candidate = canBreak(server, front) ? front : front.above();
            if (canBreak(server, candidate)) breakObstruction(server, candidate);
            else clearBreaking();
        } else clearBreaking();
        if (breakingPos == null && distanceToSqr(target) > 9 && ++rangedTicks >= 50) {
            rangedTicks = 0;
            shootMissile(target);
        }
        if (getNavigation().isDone() && !getNavigation().moveTo(target, 0.5)) {
            getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 0.5);
        }
    }

    private boolean canBreak(ServerLevel level, BlockPos pos) {
        if (!level.isInWorldBounds(pos) || !level.getWorldBorder().isWithinBounds(pos) || !level.hasChunkAt(pos)) return false;
        var state = level.getBlockState(pos);
        return !state.isAir() && !state.is(ModBlockTags.DUNGEON_GUARDIAN_IMMUNE) && !state.hasBlockEntity()
                && state.getDestroySpeed(level, pos) > 0 && state.canEntityDestroy(level, pos, this);
    }

    private void breakObstruction(ServerLevel level, BlockPos pos) {
        var state = level.getBlockState(pos);
        if (!pos.equals(breakingPos) || state != breakingState) {
            clearBreaking();
            breakingPos = pos.immutable();
            breakingState = state;
        }
        int duration = Math.max(1, Mth.ceil(state.getDestroySpeed(level, pos) * 160));
        ++breakingTicks;
        level.destroyBlockProgress(getId(), pos, Math.min(9, breakingTicks * 10 / duration));
        if (breakingTicks >= duration) {
            if (EventHooks.onEntityDestroyBlock(this, pos, state)) level.destroyBlock(pos, true, this);
            clearBreaking();
        }
    }

    private void clearBreaking() {
        if (breakingPos != null && level() instanceof ServerLevel server) server.destroyBlockProgress(getId(), breakingPos, -1);
        breakingPos = null;
        breakingState = null;
        breakingTicks = 0;
    }

    @Override
    public void remove(@NonNull RemovalReason reason) {
        clearBreaking();
        super.remove(reason);
    }

    public void shootMissile(LivingEntity target) {
        if (!(level() instanceof ServerLevel server) || !hasLineOfSight(target)) return;
        ThrowableProjectile missile = switch (getVariant()) {
            case 1, 2 -> {
                var web = new WebSling(ModEntities.WEB_SLING.get(), server);
                web.setIncendiary(getVariant() == 2);
                yield web;
            }
            case 3 -> new PoisonJet(ModEntities.POISON_JET.get(), server);
            default -> new GooBall(ModEntities.GOO_BALL.get(), server);
        };
        missile.setOwner(this);
        missile.setPos(getX(), getEyeY() - 0.1, getZ());
        double dx = target.getX() + target.getDeltaMovement().x - getX();
        double dz = target.getZ() + target.getDeltaMovement().z - getZ();
        double dy = target.getEyeY() - 1.100000023841858 - getY();
        missile.shoot(dx, dy + Math.sqrt(dx * dx + dz * dz) * 0.1, dz, 0.75F, 8);
        server.addFreshEntity(missile);
    }

    @Override
    public boolean doHurtTarget(@NonNull ServerLevel level, @NonNull Entity target) {
        if (!(target instanceof LivingEntity living) || !hasLineOfSight(target)) return false;
        if (onGround()) {
            var leap = new Vec3(target.getX() - getX(), 0, target.getZ() - getZ()).normalize().scale(0.4);
            setDeltaMovement(leap.x + getDeltaMovement().x * 0.2, 0.4, leap.z + getDeltaMovement().z * 0.2);
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
