package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GrassPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public GrassPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        boolean jungle = biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY);
        boolean savannah = biome.is(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY);
        boolean fields = biome.is(ModBiomes.ELYSIAN_FIELDS_KEY);
        if (!jungle && !savannah && !fields && !biome.is(ModBiomes.ELYSIAN_FOREST_KEY)) return false;
        var flower = (jungle ? Blocks.BLUE_ORCHID : savannah ? Blocks.DANDELION
                : fields ? Blocks.OXEYE_DAISY : Blocks.AZURE_BLUET).defaultBlockState();
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < (jungle ? 300 : savannah ? 42 : 84); attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            if (!savannah) random.nextInt(80);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = random.nextInt(3) == 0 ? 40 + random.nextInt(35) : 22;
                 y < 100; y += random.nextBoolean() ? 2 : 1) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(soil)) continue;
                var surface = level.getBlockState(soil);
                if (surface.is(Blocks.GRASS_BLOCK) || surface.is(Blocks.MYCELIUM)) {
                    placed |= placeGroundPlant(level, soil.above(), random.nextInt(10) == 0, flower, random);
                    if (!savannah) break;
                }
            }
        }
        return placed;
    }

    private boolean placeGroundPlant(WorldGenLevel level, BlockPos pos, boolean tall, BlockState flower, RandomSource random) {
        if (level.isOutsideBuildHeight(pos) || level.isOutsideBuildHeight(pos.below())
                || !level.isEmptyBlock(pos) || !(level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || level.getBlockState(pos.below()).is(Blocks.MYCELIUM))) return false;
        if (tall && !level.isOutsideBuildHeight(pos.above()) && level.isEmptyBlock(pos.above())) {
            DoublePlantBlock.placeAt(level, Blocks.TALL_GRASS.defaultBlockState(), pos, 2);
            return true;
        }
        var plant = !tall && random.nextInt(16) == 0 && flower.canSurvive(level, pos)
                ? flower : Blocks.SHORT_GRASS.defaultBlockState();
        return level.setBlock(pos, plant, 2);
    }
}
