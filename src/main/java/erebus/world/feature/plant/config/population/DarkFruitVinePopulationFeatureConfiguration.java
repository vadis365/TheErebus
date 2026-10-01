package erebus.world.feature.plant.config.population;

import erebus.block.plants.DarkFruitVineBlock;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DarkFruitVinePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public DarkFruitVinePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        if (!biome.is(ModBiomes.ELYSIAN_FIELDS_KEY) && !biome.is(ModBiomes.ELYSIAN_FOREST_KEY) && !biome.is(ModBiomes.UNDERGROUND_JUNGLE_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 10; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 30 + random.nextInt(90);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var ceiling = new BlockPos(x, y, z);
            if (!level.isOutsideBuildHeight(ceiling) && level.getBlockState(ceiling).isSolidRender()) {
                placed |= placeHanger(level, ceiling, random.nextInt(20));
            }
        }
        return placed;
    }

    public boolean placeHanger(WorldGenLevel level, BlockPos ceiling, int length) {
        if (level.isOutsideBuildHeight(ceiling) || !level.getBlockState(ceiling).isSolidRender()) return false;
        var vine = ModBlocks.DARK_FRUIT_VINE.get().defaultBlockState().setValue(DarkFruitVineBlock.AGE, 4);
        boolean placed = false;
        // Build down from the ceiling, never creating isolated fragments below an obstruction.
        for (int distance = 1; distance <= length; distance++) {
            var target = ceiling.below(distance);
            if (level.isOutsideBuildHeight(target) || !level.isEmptyBlock(target) || !vine.canSurvive(level, target)) break;
            if (!level.setBlock(target, vine, 2)) break;
            placed = true;
        }
        return placed;
    }
}
