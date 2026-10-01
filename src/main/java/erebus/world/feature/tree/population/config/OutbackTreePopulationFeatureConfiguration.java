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

public class OutbackTreePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    private final Kind kind;

    public OutbackTreePopulationFeatureConfiguration(Kind kind) {
        super(NoneFeatureConfiguration.CODEC);
        this.kind = kind;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> treeKey() {
        return switch (kind) {
            case ACACIA -> ACACIA;
            case BALSAM -> TreeFeatures.BALSAM_TREE.getConfiguredResourceKey();
            case EUCALYPTUS -> TreeFeatures.EUCALYPTUS_TREE.getConfiguredResourceKey();
        };
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.ULTERIOR_OUTBACK_KEY)) return false;
        var random = context.random();
        if (!random.nextBoolean()) return false;
        int attempts = kind == Kind.EUCALYPTUS ? 180 : 30;
        boolean placed = false;
        for (int attempt = 0; attempt < attempts; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 20 + random.nextInt(80);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (state != Blocks.GRASS_BLOCK.defaultBlockState() && state != Blocks.MYCELIUM.defaultBlockState()) continue;
            boolean success = placeTree(context, soil.above());
            placed |= success;
            if ((kind != Kind.EUCALYPTUS || success) && random.nextBoolean()) break;
        }
        return placed;
    }

    protected boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos) {
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(treeKey()).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }

    public enum Kind {ACACIA, BALSAM, EUCALYPTUS}
}
