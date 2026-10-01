package erebus.world.feature.misc.config;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;

public class QuickSandFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    // Coordinates and edge-roll order match WorldGenQuickSand in mc1.12.
    private static final int[][] CORE = {
            {0, 0, 0}, {0, -1, 0}, {0, -2, 0},
            {0, 0, -1}, {0, -1, -1}, {0, 0, 1}, {0, -1, 1},
            {-1, 0, 0}, {-1, -1, 0}, {1, 0, 0}, {1, -1, 0},
            {-2, 0, 0}, {-1, 0, -1}, {2, 0, 0}, {1, 0, 1},
            {0, 0, -2}, {-1, 0, 1}, {0, 0, 2}, {1, 0, -1}
    };
    private static final int[][] EDGES = {
            {1, -2, 0}, {0, -2, 1}, {0, -2, -1}, {-1, -2, 0},
            {2, -1, 0}, {1, -1, 1}, {0, -1, 2}, {-1, -1, 1},
            {-2, -1, 0}, {1, -1, -1}, {0, -1, -2}, {-1, -1, -1},
            {3, 0, 0}, {2, 0, 1}, {1, 0, 2}, {-3, 0, 0}, {-2, 0, 1}, {-1, 0, 2},
            {0, 0, -3}, {2, 0, -1}, {1, 0, -2}, {0, 0, 3}, {-2, 0, -1}, {-1, 0, -2}
    };

    public QuickSandFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    private static boolean isSoil(BlockState state) {
        return !state.hasBlockEntity() && state.getFluidState().isEmpty()
                && (state.is(BlockTags.DIRT) || state.is(BlockTags.SAND) || state.is(BlockTags.GRASS_BLOCKS)
                || state.is(Blocks.MUD) || state.is(Blocks.PACKED_MUD) || state.is(Blocks.FARMLAND) || state.is(Blocks.DIRT_PATH)
                || state.is(ModBlocks.MUD));
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var origin = context.origin();
        if (level.isOutsideBuildHeight(origin) || !level.getBlockState(origin).is(Blocks.GRASS_BLOCK)) return false;
        var positions = new ArrayList<BlockPos>(CORE.length + EDGES.length);
        for (var offset : CORE) positions.add(origin.offset(offset[0], offset[1], offset[2]));
        for (var offset : EDGES) if (context.random().nextBoolean()) positions.add(origin.offset(offset[0], offset[1], offset[2]));
        // Validate the entire selected shape before mutating any terrain.
        for (var pos : positions) {
            if (level.isOutsideBuildHeight(pos) || !isSoil(level.getBlockState(pos))) return false;
        }
        boolean placed = false;
        for (var pos : positions) placed |= level.setBlock(pos, ModBlocks.QUICK_SAND.get().defaultBlockState(), 2);
        return placed;
    }
}
