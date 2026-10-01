package erebus.world.feature.plant.config.population;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Map;
import java.util.function.Supplier;

public class GroundPlantPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final Supplier<? extends Block> plant;
    private final Map<ResourceKey<Biome>, Integer> counts;

    public GroundPlantPopulationFeatureConfiguration(Supplier<? extends Block> plant, Map<ResourceKey<Biome>, Integer> counts) {
        super(NoneFeatureConfiguration.CODEC);
        this.plant = plant;
        this.counts = Map.copyOf(counts);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        int attempts = counts.entrySet().stream().filter(e -> biome.is(e.getKey())).mapToInt(Map.Entry::getValue).findFirst().orElse(0);
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < attempts; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            for (int y = 20; y < 100; y += random.nextBoolean() ? 2 : 1) {
                var soil = new BlockPos(x, level.getMinY() + y, z);
                if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
                var state = level.getBlockState(soil);
                if ((state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM)) && level.isEmptyBlock(soil.above())) {
                    placed |= level.setBlock(soil.above(), plant.get().defaultBlockState(), 2);
                    break;
                }
            }
        }
        return placed;
    }
}
