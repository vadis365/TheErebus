package erebus.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class HangingWebBlock extends WebBlock {
    public static final MapCodec<WebBlock> CODEC = simpleCodec(HangingWebBlock::new);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty PART = IntegerProperty.create("part", 0, 9);

    public HangingWebBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PART, 9));
    }

    @Override
    public @NonNull MapCodec<WebBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected boolean canSurvive(BlockState state, @NonNull LevelReader level, @NonNull BlockPos pos) {
        int part = state.getValue(PART);
        Direction facing = state.getValue(FACING);
        if (part < 9 && part % 3 != 1) {
            int column = part % 3 - 1;
            var center = level.getBlockState(pos.relative(facing.getClockWise(), -column));
            return center.is(this) && center.getValue(FACING) == facing && center.getValue(PART) == part / 3 * 3 + 1;
        }
        var above = level.getBlockState(pos.above());
        return above.isFaceSturdy(level, pos.above(), Direction.DOWN)
                || (above.is(this) && above.getValue(FACING) == facing
                && (above.getValue(PART) == 9 || above.getValue(PART) % 3 == 1));
    }

    @Override
    protected @NonNull BlockState updateShape(BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction direction, @NonNull BlockPos neighbor, @NonNull BlockState neighborState, @NonNull RandomSource random) {
        return state.canSurvive(level, pos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public @NonNull List<ItemStack> onSheared(Player player, @NonNull ItemStack item, @NonNull Level level, @NonNull BlockPos pos) {
        return List.of(new ItemStack(Items.STRING));
    }
}
