package erebus.world.feature.plant.config.population;

import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;
import java.util.function.Supplier;

public class TallFlowerPopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final Supplier<? extends Block> flower;
    private final int attempts;
    private final List<ResourceKey<Biome>> biomes;

    public TallFlowerPopulationFeatureConfiguration(Supplier<? extends Block> flower, int attempts) {
        this(flower, attempts, List.of(ModBiomes.ELYSIAN_FIELDS_KEY, ModBiomes.ELYSIAN_FOREST_KEY));
    }

    public TallFlowerPopulationFeatureConfiguration(Supplier<? extends Block> flower, int attempts, List<ResourceKey<Biome>> biomes) {
        super(NoneFeatureConfiguration.CODEC);
        this.flower = flower;
        this.attempts = attempts;
        this.biomes = List.copyOf(biomes);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var biome = level.getBiome(context.origin());
        if (biomes.stream().noneMatch(biome::is)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < attempts; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 20 + random.nextInt(80);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            placed |= placeFlower(level, new BlockPos(x, y + 1, z));
        }
        return placed;
    }

    public boolean placeFlower(WorldGenLevel level, BlockPos lower) {
        if (level.isOutsideBuildHeight(lower.below()) || level.isOutsideBuildHeight(lower.above())
                || !(level.getBlockState(lower.below()).is(Blocks.GRASS_BLOCK) || level.getBlockState(lower.below()).is(Blocks.MYCELIUM))
                || !level.isEmptyBlock(lower) || !level.isEmptyBlock(lower.above())) return false;
        DoublePlantBlock.placeAt(level, flower.get().defaultBlockState(), lower, 2);
        return true;
    }
}
