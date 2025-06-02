package erebus.entity.projectile;

import erebus.block.entity.PreservedBlockEntity;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.AmberBlocks;
import erebus.registries.data.ModTags;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class AmberStar extends ThrowableProjectile implements ItemSupplier {

    public AmberStar(EntityType<? extends AmberStar> type, Level level) {
        super(type, level);
    }

    public AmberStar(Level level, LivingEntity shooter) {
        super(ModEntities.AMBER_STAR.get(), shooter, level);
    }

    public AmberStar(Level level, double x, double y, double z) {
        super(ModEntities.AMBER_STAR.get(), x, y, z, level);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        level().setBlock(pos, AmberBlocks.AMBER.get().defaultBlockState(), 2);
        remove(RemovalReason.KILLED);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        Entity entity = result.getEntity();
        Level level = entity.level();
        if(!level.isClientSide) return;

        BlockPos pos = entity.blockPosition();

        if (!(entity instanceof Player)) {
            if(canTrap(entity)) {
                level.setBlock(pos, AmberBlocks.PRESERVED_AMBER_GLASS.get().defaultBlockState(), 3);
                PreservedBlockEntity blockEntity = (PreservedBlockEntity) level.getBlockEntity(pos);
                if(blockEntity != null)
                    blockEntity.setEntity(trapEntity(entity));
                entity.remove(RemovalReason.KILLED);
            }
        }

        remove(RemovalReason. KILLED);
    }

    private boolean canTrap(Entity entity) {
        return entity.getType().is(ModTags.CAN_BE_PRESERVED);
    }

    private CompoundTag trapEntity(Entity entity) {
        return entity.saveWithoutId(new CompoundTag());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {}

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(ModItems.AMBER_STAR.get());
    }
}
