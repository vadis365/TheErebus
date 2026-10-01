package erebus.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ChameleonTick extends Monster {
    private static final EntityDataAccessor<BlockState> CAMOUFLAGE = SynchedEntityData.defineId(ChameleonTick.class, EntityDataSerializers.BLOCK_STATE);
    private static final EntityDataAccessor<Byte> UNFOLD = SynchedEntityData.defineId(ChameleonTick.class, EntityDataSerializers.BYTE);
    private int previousUnfold;
    private int clientUnfold;

    public ChameleonTick(EntityType<? extends ChameleonTick> type, Level level) {
        super(type, level);
        setPathfindingMalus(PathType.WATER, -8);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.5).add(Attributes.ATTACK_DAMAGE, 2).add(Attributes.FOLLOW_RANGE, 16);
    }

    public static boolean checkChameleonSpawnRules(EntityType<ChameleonTick> type, ServerLevelAccessor level,
                                                   EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, level, reason, pos, random);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CAMOUFLAGE, Blocks.GRASS_BLOCK.defaultBlockState());
        builder.define(UNFOLD, (byte) 0);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.65, false) {
            @Override
            public boolean canUse() {
                return getUnfold() >= 9 && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return getUnfold() >= 9 && super.canContinueToUse();
            }
        });
    }

    public BlockState getCamouflage() {
        return entityData.get(CAMOUFLAGE);
    }

    public int getUnfold() {
        return entityData.get(UNFOLD);
    }

    public float getUnfold(float partialTick) {
        return Mth.lerp(partialTick, previousUnfold, level().isClientSide() ? clientUnfold : getUnfold());
    }

    @Override
    public void tick() {
        previousUnfold = level().isClientSide() ? clientUnfold : getUnfold();
        super.tick();
        if (level().isClientSide()) {
            clientUnfold = getUnfold();
            return;
        }
        if (!isAlive()) return;
        var supportPos = blockPosition().below();
        var support = level().getBlockState(supportPos);
        if (onGround() && support.isRedstoneConductor(level(), supportPos)) entityData.set(CAMOUFLAGE, support);
        if (isNoAi()) return;
        var player = level().getNearestPlayer(getX(), getY(), getZ(), 8,
                entity -> entity instanceof Player candidate && candidate.isAlive() && !candidate.isCreative() && !candidate.isSpectator());
        setTarget(player);
        boolean active = player != null;
        entityData.set(UNFOLD, (byte) Math.clamp(getUnfold() + (active ? 1 : -1), 0, 10));
        getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(active && getUnfold() >= 9 ? 0.65 : 0);
        if (!active) {
            getNavigation().stop();
            setDeltaMovement(getDeltaMovement().multiply(0, 1, 0));
            if (onGround()) setPos(Mth.floor(getX()) + 0.5, getY(), Mth.floor(getZ()) + 0.5);
            setYRot(0);
            yRotO = 0;
            yBodyRot = 0;
            yBodyRotO = 0;
            yHeadRot = 0;
        }
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity target) {
        return distanceToSqr(target) < 4 + target.getBbWidth();
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        return getUnfold() >= 9 && getSensing().hasLineOfSight(target) && super.doHurtTarget(level, target);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
        return true;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return !level.containsAnyLiquid(getBoundingBox()) && level.noCollision(this);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 2;
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("Camouflage", BlockState.CODEC, getCamouflage());
        output.putByte("Unfold", (byte) getUnfold());
    }

    @Override
    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        var camouflage = input.read("Camouflage", BlockState.CODEC).orElse(Blocks.GRASS_BLOCK.defaultBlockState());
        entityData.set(CAMOUFLAGE, camouflage.isAir() ? Blocks.GRASS_BLOCK.defaultBlockState() : camouflage);
        entityData.set(UNFOLD, (byte) Math.clamp(input.getByteOr("Unfold", (byte) 0), 0, 10));
        previousUnfold = getUnfold();
        clientUnfold = getUnfold();
    }
}
