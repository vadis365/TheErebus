package erebus.block.plants;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class AlgaeBlock extends VegetationBlock {
    public static final MapCodec<AlgaeBlock> CODEC = simpleCodec(AlgaeBlock::new);
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 1, 16);

    public AlgaeBlock(Properties properties) {
        super(properties);
    }

    public static boolean canPlaceOnWater(LevelReader level, BlockPos pos) {
        if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.below())) return false;
        var below = level.getBlockState(pos.below());
        return below.is(Blocks.WATER) && below.getFluidState().isSource();
    }

    @Override
    protected @NonNull MapCodec<AlgaeBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canSurvive(@NonNull BlockState state, LevelReader level, @NonNull BlockPos pos) {
        if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.below())) return false;
        var below = level.getBlockState(pos.below());
        return canPlaceOnWater(level, pos) || below.is(Blocks.ICE) || below.is(Blocks.FROSTED_ICE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return canPlaceOnWater(context.getLevel(), context.getClickedPos()) ? defaultBlockState() : null;
    }
}
