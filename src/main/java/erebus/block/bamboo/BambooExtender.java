package erebus.block.bamboo;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.BambooExtenderBlockEntity;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BambooExtender extends DirectionalBlock implements EntityBlock {

    public static final MapCodec<BambooExtender> CODEC = simpleCodec(BambooExtender::new);
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public BambooExtender(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    @Override
    protected @NotNull MapCodec<BambooExtender> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? null : BambooExtenderBlockEntity::serverTick;
    }

    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new BambooExtenderBlockEntity(pos, state);
    }

    @Override
    public @NonNull InteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (stack.is(ModItems.BAMBOO_PIPE_WRENCH)) return InteractionResult.PASS;
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else if (blockEntity instanceof BambooExtenderBlockEntity extender) {
            player.openMenu(extender);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getNearestLookingDirection();
        if (direction == Direction.UP || direction == Direction.DOWN)
            return this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
        return this.defaultBlockState().setValue(FACING, direction).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    protected void neighborChanged(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!level.isClientSide()) {
            var entity = level.getBlockEntity(pos);
            BambooExtenderBlockEntity tile = entity instanceof BambooExtenderBlockEntity extender ? extender : null;
            boolean flag = level.hasNeighborSignal(pos);
            if (flag != state.getValue(POWERED)) {
                level.setBlock(pos, state.setValue(POWERED, flag), 3);
                if (tile != null)
                    tile.setExtending(flag);
            }
        }
    }


}
