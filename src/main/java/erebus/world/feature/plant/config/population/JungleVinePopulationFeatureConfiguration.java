package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class JungleVinePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private static final Direction[] WALLS = {Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH};

    public JungleVinePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.UNDERGROUND_JUNGLE_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 800; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 30 + random.nextInt(80);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var start = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(start) || !level.isEmptyBlock(start)) continue;
            var wall = WALLS[random.nextInt(4)];
            if (!level.getBlockState(start.relative(wall)).isSolidRender()) continue;
            placed |= placeStrand(level, start, wall, random.nextInt(30));
        }
        return placed;
    }

    public boolean placeStrand(WorldGenLevel level, BlockPos start, Direction wall, int length) {
        if (!wall.getAxis().isHorizontal() || level.isOutsideBuildHeight(start)
                || !VineBlock.isAcceptableNeighbour(level, start.relative(wall), wall.getOpposite())) return false;
        var vine = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(wall), true);
        boolean placed = false;
        for (int distance = 0; distance < length; distance++) {
            var target = start.below(distance);
            if (level.isOutsideBuildHeight(target) || !level.isEmptyBlock(target) || !vine.canSurvive(level, target)) break;
            if (!level.setBlock(target, vine, 2)) break;
            placed = true;
        }
        return placed;
    }
}
