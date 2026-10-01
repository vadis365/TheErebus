package erebus.world.feature.mushroom.config.population;

import erebus.registries.world.ModBiomes;
import erebus.registries.world.feature.PlantFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GiantMushroomPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public GiantMushroomPopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.FUNGAL_FOREST_KEY)) return false;
        var random = context.random();
        for (int attempt = 0; attempt < 400; attempt++) {
            int roll = random.nextInt(100);
            int species = roll < 16 ? 0 : roll < 25 ? 1 : roll < 80 ? 2 : roll < 96 ? 3 : 4;
            int x = context.origin().getX() + random.nextInt(16) + 8;
            int y = level.getMinY() + 25 + random.nextInt(50 + random.nextInt(40));
            int z = context.origin().getZ() + random.nextInt(16) + 8;
            var soil = new BlockPos(x, y, z);

            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if ((state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM))
                    && placeMushroom(context, species, soil.above())) return true;
        }
        return false;
    }

    protected boolean placeMushroom(FeaturePlaceContext<NoneFeatureConfiguration> context, int species, BlockPos origin) {
        var mushroom = switch (species) {
            case 0 -> PlantFeatures.DUTCH_CAP_MUSHROOM;
            case 1 -> PlantFeatures.SARCASTIC_CZECH_MUSHROOM;
            case 2 -> PlantFeatures.KAIZERS_FINGERS_MUSHROOM;
            case 3 -> PlantFeatures.GRANDMAS_SHOES_MUSHROOM;
            default -> PlantFeatures.DARK_CAPPED_MUSHROOM;
        };
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE)
                .getOrThrow(mushroom.getConfiguredResourceKey()).value()
                .place(context.level(), context.chunkGenerator(), context.random(), origin);
    }
}
