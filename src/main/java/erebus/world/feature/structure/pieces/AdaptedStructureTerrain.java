package erebus.world.feature.structure.pieces;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.function.Function;
import java.util.function.Predicate;

/** Bounded soil caps supplement the native beard without bridging deep caves. */
public final class AdaptedStructureTerrain {
    private static final int MAX_FILL = 8;
    private static final int MAX_CUT = 4;

    private AdaptedStructureTerrain() {
    }

    public static boolean ground(BlockState state) {
        return !state.hasBlockEntity() && state.getFluidState().isEmpty()
                && (state.is(ModBlocks.UMBERSTONE) || state.is(BlockTags.DIRT) || state.is(BlockTags.GRASS_BLOCKS)
                || state.is(BlockTags.SAND) || state.is(BlockTags.MUD) || state.is(BlockTags.MOSS_BLOCKS)
                || state.is(ModBlocks.MUD) || state.is(Blocks.CLAY) || state.is(Blocks.GRAVEL)
                || state.is(Blocks.SANDSTONE) || state.is(Blocks.RED_SANDSTONE));
    }

    public static boolean clearable(BlockState state, int aboveFloor) {
        return !state.hasBlockEntity() && (state.isAir()
                || (state.canBeReplaced() || state.getBlock() instanceof VegetationBlock
                || state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS))
                && state.getFluidState().isEmpty()
                || aboveFloor <= MAX_CUT && (ground(state) || state.getFluidState().is(FluidTags.WATER)));
    }

    public static boolean supported(BlockPos floor, Function<BlockPos, BlockState> blocks, Predicate<BlockPos> inBounds) {
        for (int depth = 0; depth <= MAX_FILL; depth++) {
            var pos = floor.below(depth);
            if (!inBounds.test(pos)) return false;
            var state = blocks.apply(pos);
            if (ground(state) && state.isCollisionShapeFullBlock(EmptyBlockGetter.INSTANCE, pos)) return true;
            if (!clearable(state, 0)) return false;
        }
        return false;
    }

    public static void prepareColumn(WorldGenLevel level, BoundingBox clip, BlockPos floor, int clearance,
                                     BlockState surface, BlockState fill) {
        if (!clip.isInside(floor)) return;
        for (int depth = 0; depth <= MAX_FILL; depth++) {
            var target = floor.below(depth);
            if (level.isOutsideBuildHeight(target)) break;
            var current = level.getBlockState(target);
            if (depth >= 3 && ground(current) && current.isCollisionShapeFullBlock(level, target)) break;
            if (!clearable(current, 0)) break;
            level.setBlock(target, depth == 0 ? surface : fill, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
        }
        for (int height = 1; height <= clearance; height++) {
            var target = floor.above(height);
            var current = level.getBlockState(target);
            if (!current.isAir() && clearable(current, height))
                level.setBlock(target, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
        }
    }
}
