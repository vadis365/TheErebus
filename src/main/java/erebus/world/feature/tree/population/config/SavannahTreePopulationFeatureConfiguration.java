package erebus.world.feature.tree.population.config;

import erebus.registries.world.ModBiomes;
import erebus.registries.world.feature.TreeFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import static net.minecraft.data.worldgen.features.TreeFeatures.ACACIA;

public class SavannahTreePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final Kind kind;

    public SavannahTreePopulationFeatureConfiguration(Kind kind) {
        super(NoneFeatureConfiguration.CODEC);
        this.kind = kind;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> treeKey() {
        return switch (kind) {
            case ACACIA -> ACACIA;
            case ASPER -> TreeFeatures.ASPER_TREE.getConfiguredResourceKey();
            case BAOBAB -> TreeFeatures.BAOBAB_TREE.getConfiguredResourceKey();
        };
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY)) return false;
        var random = context.random();
        int attempts = switch (kind) {
            case ACACIA -> 65;
            case ASPER -> 10;
            case BAOBAB -> 20;
        };
        boolean placed = false;
        for (int attempt = 0; attempt < attempts; attempt++) {
            int x = context.origin().getX() + (kind == Kind.BAOBAB ? 12 + random.nextInt(5) : 8 + random.nextInt(16));
            int y = level.getMinY() + (kind == Kind.ACACIA ? 15 + random.nextInt(90) : random.nextInt(120));
            int z = context.origin().getZ() + (kind == Kind.BAOBAB ? 12 + random.nextInt(5) : 8 + random.nextInt(16));
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (state != Blocks.GRASS_BLOCK.defaultBlockState() && state != Blocks.MYCELIUM.defaultBlockState()) continue;
            placed |= placeTree(context, soil.above());
        }
        return placed;
    }

    protected boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(treeKey()).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }

    public enum Kind {ACACIA, ASPER, BAOBAB}
}
