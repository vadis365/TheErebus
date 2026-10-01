package erebus.entity.projectile;

import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public class ThrownBlockAsItem extends ThrowableProjectile implements ItemSupplier {
    private static final EntityDataAccessor<BlockState> TYPE = SynchedEntityData.defineId(ThrownBlockAsItem.class, EntityDataSerializers.BLOCK_STATE);
    private SoundEvent placedSound;

    public ThrownBlockAsItem(Level level) {
        super(ModEntities.THROWN_BLOCK_AS_ITEM.get(), level);
    }

    public ThrownBlockAsItem(EntityType<ThrownBlockAsItem> type, Level level) {
        super(type, level);
    }

    public ThrownBlockAsItem(Level level, Entity owner, BlockState state, float damageCaused, SoundEvent placedSoundIn) {
        super(ModEntities.THROWN_BLOCK_AS_ITEM.get(), level);
        this.setOwner(owner);
        setXRot(owner.getXRot());
        setYRot(owner.getYRot());
        setBlockType(state);
        placedSound = placedSoundIn;
    }

    public ThrownBlockAsItem(double x, double y, double z, Level level) {
        super(ModEntities.THROWN_BLOCK_AS_ITEM.get(), x, y, z, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(TYPE, Blocks.STONE.defaultBlockState());
    }

    protected SoundEvent getPlacedSound() {
        return placedSound != null ? placedSound : getBlockType().getSoundType(level(), blockPosition(), null).getPlaceSound();
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("BlockState", BlockState.CODEC, getBlockType());
        if (placedSound != null) output.store("PlacedSound", SoundEvent.DIRECT_CODEC, placedSound);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        setBlockType(input.read("BlockState", BlockState.CODEC).orElse(Blocks.STONE.defaultBlockState()));
        placedSound = input.read("PlacedSound", SoundEvent.DIRECT_CODEC).orElse(null);
    }

    @Override
    protected void onHit(HitResult result) {
        if (!(level() instanceof ServerLevel level) || isRemoved() || result.getType() == HitResult.Type.MISS) return;
        super.onHit(result);
        BlockPos pos = result instanceof EntityHitResult hit ? hit.getEntity().blockPosition() : BlockPos.containing(result.getLocation());
        if (result instanceof BlockHitResult hit) {
            pos = hit.getBlockPos();
            if (!level.getBlockState(pos).canBeReplaced()) pos = pos.relative(hit.getDirection());
        }
        var state = getBlockType();
        if (level.isInWorldBounds(pos) && mayInteract(level, pos) && level.getBlockState(pos).isAir() && state.canSurvive(level, pos))
            level.setBlockAndUpdate(pos, state);
        else level.levelEvent(null, 2001, blockPosition(), Block.getId(state));
        level.playSound(null, blockPosition(), getPlacedSound(), SoundSource.BLOCKS, 1, 1);
        discard();
    }

    @Override
    public boolean canBeCollidedWith(Entity other) {
        return false;
    }

    public boolean attackEntityFrom(DamageSource source, int par2) {
        return false;
    }

    public BlockState getBlockType() {
        return entityData.get(TYPE);
    }

    public void setBlockType(BlockState state) {
        entityData.set(TYPE, state);
    }

    @Override
    public @NonNull ItemStack getItem() {
        return new ItemStack(getBlockType().getBlock());
    }
}
