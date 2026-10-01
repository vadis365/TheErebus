package erebus.world.feature.misc.config;

import erebus.block.HangingWebBlock;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HangingWebFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private static final Direction[] OFFSETS = {Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH};

    public HangingWebFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public static boolean placeStrand(WorldGenLevel level, BlockPos anchor, Direction facing, int length) {
        var strand = ModBlocks.HANGING_WEB.get().defaultBlockState().setValue(HangingWebBlock.FACING, facing);
        boolean placed = false;
        for (int depth = 1; depth <= length; depth++) {
            var pos = anchor.below(depth);
            if (level.isOutsideBuildHeight(pos) || !level.isEmptyBlock(pos) || !strand.canSurvive(level, pos)) break;
            if (depth + 2 <= length && panelFits(level, pos, facing)) {
                for (int row = 0; row < 3; row++) {
                    var center = pos.below(row);
                    var state = strand.setValue(HangingWebBlock.PART, row * 3 + 1);
                    if (!level.setBlock(center, state, 2)) return placed;
                    placed = true;
                    for (int side : new int[]{-1, 1}) {
                        var target = center.relative(facing.getClockWise(), side);
                        level.setBlock(target, strand.setValue(HangingWebBlock.PART, row * 3 + side + 1), 2);
                    }
                }
                depth += 2;
            } else {
                if (!level.setBlock(pos, strand, 2)) break;
                placed = true;
            }
        }
        return placed;
    }

    private static boolean panelFits(WorldGenLevel level, BlockPos top, Direction facing) {
        for (int row = 0; row < 3; row++)
            for (int column = -1; column <= 1; column++) {
                var pos = top.below(row).relative(facing.getClockWise(), column);
                if (level.isOutsideBuildHeight(pos) || !level.isEmptyBlock(pos)) return false;
            }
        return true;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.PETRIFIED_FOREST_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 800; attempt++) {
            var probe = new BlockPos(context.origin().getX() + 8 + random.nextInt(16), level.getMinY() + 30 + random.nextInt(80), context.origin().getZ() + 8 + random.nextInt(16));
            if (level.isOutsideBuildHeight(probe) || !level.isEmptyBlock(probe)) continue;
            Direction direction = OFFSETS[random.nextInt(4)];
            var anchor = probe.relative(direction);
            if (!level.getBlockState(anchor).isRedstoneConductor(level, anchor)) continue;
            placed |= placeStrand(level, anchor, direction.getOpposite(), random.nextInt(30));
        }
        return placed;
    }
}
