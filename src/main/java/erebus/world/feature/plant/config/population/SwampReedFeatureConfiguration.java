package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SwampReedFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private static final Direction[] WATER_ORDER = {Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH};

    public SwampReedFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.SUBMERGED_SWAMP_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int pass = 0; pass < 2; pass++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var origin = new BlockPos(x, level.getMinY() + 24, z);
            for (int attempt = 0; attempt < 20; attempt++) {
                var base = origin.offset(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
                if (level.isOutsideBuildHeight(base) || !level.isEmptyBlock(base)) continue;
                boolean water = false;
                for (var direction : WATER_ORDER)
                    if (level.getBlockState(base.below().relative(direction)).is(Blocks.WATER)) {
                        water = true;
                        break;
                    }
                if (water) placed |= placeColumn(level, base, 2 + random.nextInt(random.nextInt(3) + 1));
            }
        }
        return placed;
    }

    public boolean placeColumn(WorldGenLevel level, BlockPos base, int height) {
        if (height < 2 || height > 4 || level.isOutsideBuildHeight(base.below())) return false;
        var cane = Blocks.SUGAR_CANE.defaultBlockState();
        if (!cane.canSurvive(level, base)) return false;
        for (int y = 0; y < height; y++) {
            var pos = base.above(y);
            if (level.isOutsideBuildHeight(pos) || !level.isEmptyBlock(pos)) return false;
        }
        for (int y = 0; y < height; y++) if (!level.setBlock(base.above(y), cane, 2)) return y > 0;
        return true;
    }
}
