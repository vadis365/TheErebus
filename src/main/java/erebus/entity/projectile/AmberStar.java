package erebus.entity.projectile;

import erebus.block.entity.PreservedBlockEntity;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModEntityTypeTags;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class AmberStar extends ThrowableProjectile implements ItemSupplier {

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
        if (level().isClientSide()) return;
        BlockPos pos = result.getBlockPos().relative(result.getDirection());
        if (canPlaceAt(pos)) level().setBlock(pos, ModBlocks.AMBER.get().defaultBlockState(), Block.UPDATE_ALL);
        discard();
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        Level level = level();
        if (level.isClientSide()) return;
        Entity entity = result.getEntity();
        BlockPos pos = entity.blockPosition();
        if (!(entity instanceof Player) && entity.isAlive() && !entity.isPassenger() && !entity.isVehicle() && canTrap(entity) && canPlaceAt(pos)) {
            var previousState = level.getBlockState(pos);
            if (level.setBlock(pos, ModBlocks.PRESERVED_AMBER_GLASS.get().defaultBlockState(), Block.UPDATE_ALL)) {
                if (level.getBlockEntity(pos) instanceof PreservedBlockEntity preserved && preserved.setTrappedEntity(entity)) {
                    entity.discard();
                } else {
                    level.setBlock(pos, previousState, Block.UPDATE_ALL);
                }
            }
        }
        discard();
    }

    private boolean canPlaceAt(BlockPos pos) {
        if (!level().getWorldBorder().isWithinBounds(pos) || !level().getBlockState(pos).canBeReplaced() || level().getBlockState(pos).hasBlockEntity()) return false;
        if (getOwner() instanceof Player player) {
            return level().mayInteract(player, pos) && player.mayUseItemAt(pos, Direction.UP, player.getMainHandItem());
        }
        return true;
    }

    private boolean canTrap(Entity entity) {
        return entity.is(ModEntityTypeTags.CAN_BE_PRESERVED);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(ModItems.AMBER_STAR.get());
    }

}
