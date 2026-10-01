package erebus.entity.projectile;

import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public class WebSling extends ThrowableProjectile {
    private static final EntityDataAccessor<Boolean> WITHER = SynchedEntityData.defineId(WebSling.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> INCENDIARY = SynchedEntityData.defineId(WebSling.class, EntityDataSerializers.BOOLEAN);

    public WebSling(EntityType<? extends WebSling> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(WITHER, false);
        builder.define(INCENDIARY, false);
    }

    public boolean isWither() {
        return entityData.get(WITHER);
    }

    public void setWither(boolean wither) {
        entityData.set(WITHER, wither);
        if (wither) entityData.set(INCENDIARY, false);
    }

    public boolean isIncendiary() {
        return entityData.get(INCENDIARY);
    }

    public void setIncendiary(boolean incendiary) {
        entityData.set(INCENDIARY, incendiary);
        if (incendiary) entityData.set(WITHER, false);
    }

    public BlockState getWebState() {
        return (isIncendiary() ? Blocks.FIRE : isWither() ? ModBlocks.WITHER_WEB.get() : Blocks.COBWEB).defaultBlockState();
    }

    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("Wither", isWither());
        output.putBoolean("Incendiary", isIncendiary());
    }

    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        setWither(input.getBooleanOr("Wither", false));
        setIncendiary(input.getBooleanOr("Incendiary", false));
    }

    @Override
    protected void onHit(@NonNull HitResult result) {
        if (!(level() instanceof ServerLevel serverLevel) || isRemoved() || result.getType() == HitResult.Type.MISS) return;
        super.onHit(result);
        BlockPos pos = BlockPos.containing(result.getLocation());
        if (result instanceof BlockHitResult blockHit) {
            pos = blockHit.getBlockPos();
            if (!serverLevel.getBlockState(pos).canBeReplaced()) pos = pos.relative(blockHit.getDirection());
        }
        var web = getWebState();
        if (isIncendiary() && result instanceof EntityHitResult entityHit) {
            entityHit.getEntity().igniteForSeconds(10);
        } else if (serverLevel.isInWorldBounds(pos) && mayInteract(serverLevel, pos)
                && serverLevel.getBlockState(pos).canBeReplaced() && web.canSurvive(serverLevel, pos)) {
            serverLevel.setBlockAndUpdate(pos, web);
        }
        if (!isIncendiary()) serverLevel.playSound(null, blockPosition(), ModSounds.WEBSLING_SPLAT.get(), SoundSource.HOSTILE, 1, 1);
        discard();
    }
}
