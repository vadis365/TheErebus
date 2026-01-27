package erebus.entity.projectile;

import erebus.block.entity.PreservedBlockEntity;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModEntityTypeTags;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class AmberStar extends ThrowableProjectile implements ItemSupplier {

    private Entity trappedEntity;

    public AmberStar(EntityType<? extends AmberStar> type, Level level) {
        super(type, level);
    }

    public AmberStar(Level level) {
        super(ModEntities.AMBER_STAR.get(), level);
    }

    public AmberStar(Level level, double x, double y, double z) {
        super(ModEntities.AMBER_STAR.get(), x, y, z, level);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        level().setBlock(pos.relative(result.getDirection()), ModBlocks.AMBER.get().defaultBlockState(), 2);
        remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        Entity entity = result.getEntity();
        Level level = entity.level();
        if(!level.isClientSide()) return;

        BlockPos pos = entity.blockPosition();

        if (!(entity instanceof Player)) {
            if(canTrap(entity)) {
                level.setBlock(pos, ModBlocks.PRESERVED_AMBER_GLASS.get().defaultBlockState(), Block.UPDATE_ALL);
                PreservedBlockEntity blockEntity = (PreservedBlockEntity) level.getBlockEntity(pos);
                if(blockEntity != null)
                    blockEntity.setTrappedEntity(entity);
                entity.remove(RemovalReason.DISCARDED);
            }
        }

        remove(RemovalReason.DISCARDED);
    }

    private boolean canTrap(Entity entity) {
        return entity.is(ModEntityTypeTags.CAN_BE_PRESERVED);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {}

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(ModItems.AMBER_STAR.get());
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        trappedEntity.save(output);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        trappedEntity.load(input);
    }
}
