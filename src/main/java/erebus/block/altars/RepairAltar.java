package erebus.block.altars;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.RepairAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RepairAltar extends AltarAbstract {

    public static final MapCodec<RepairAltar> CODEC = simpleCodec(RepairAltar::new);

    public RepairAltar(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<RepairAltar> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new RepairAltarBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
        return RepairAltarBlockEntity::tick;
    }

    @Override
    protected void onPlace(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull BlockState oldState, boolean movedByPiston) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof RepairAltarBlockEntity altar) {
            altar.setActive(false);
            altar.setcanBeUsed(true);
        }
    }

    @Override
    public void stepOn(Level level, @NonNull BlockPos pos, @NonNull BlockState state, @NonNull Entity entity) {
        if (!level.isClientSide() && entity instanceof ItemEntity item && item.getY() >= pos.getY() + 0.9
                && level.getBlockEntity(pos) instanceof RepairAltarBlockEntity altar)
            altar.beginRepair(item);
    }

    @Override
    public @NonNull InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else if (blockEntity instanceof RepairAltarBlockEntity altar) {
            return activateAltar(level, stack, player, altar, pos, hand);
        }
        return InteractionResult.FAIL;
    }
}
