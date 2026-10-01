package erebus.world.feature.tree.population.config;

import erebus.registries.world.ModBiomes;
import erebus.registries.world.feature.TreeFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import static net.minecraft.data.worldgen.features.TreeFeatures.MEGA_JUNGLE_TREE;

public class JungleTreePopulationFeatureConfiguration extends Feature<NoneFeatureConfiguration> {
    public JungleTreePopulationFeatureConfiguration() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public static Kind select(int roll) {
        return roll <= 6 ? Kind.GIANT_JUNGLE : roll <= 11 ? Kind.MAHOGANY : roll <= 16 ? Kind.GIANT_MAHOGANY : roll <= 20 ? Kind.ASPER :
                roll <= 23 ? Kind.JUNGLE : roll <= 26 ? Kind.MOSSBARK : roll <= 28 ? Kind.TALL_JUNGLE : Kind.EUCALYPTUS;
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> treeKey(Kind kind) {
        return switch (kind) {
            case GIANT_JUNGLE -> MEGA_JUNGLE_TREE;
            case MAHOGANY -> TreeFeatures.MAHOGANY_TREE.getConfiguredResourceKey();
            case GIANT_MAHOGANY -> TreeFeatures.GIANT_MAHOGANY_TREE.getConfiguredResourceKey();
            case ASPER -> TreeFeatures.ASPER_TREE.getConfiguredResourceKey();
            case JUNGLE -> TreeFeatures.JUNGLE_TREE.getConfiguredResourceKey();
            case MOSSBARK -> TreeFeatures.MOSSBARK_TREE.getConfiguredResourceKey();
            case TALL_JUNGLE -> TreeFeatures.TALL_JUNGLE_TREE.getConfiguredResourceKey();
            case EUCALYPTUS -> TreeFeatures.EUCALYPTUS_TREE.getConfiguredResourceKey();
        };
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        if (!level.getBiome(context.origin()).is(ModBiomes.UNDERGROUND_JUNGLE_KEY)) return false;
        var random = context.random();
        boolean placed = false;
        for (int attempt = 0; attempt < 2200; attempt++) {
            int x = context.origin().getX() + 8 + random.nextInt(16);
            int y = level.getMinY() + 15 + random.nextInt(90);
            int z = context.origin().getZ() + 8 + random.nextInt(16);
            var soil = new BlockPos(x, y, z);
            if (level.isOutsideBuildHeight(soil) || level.isOutsideBuildHeight(soil.above())) continue;
            var state = level.getBlockState(soil);
            if (state != Blocks.GRASS_BLOCK.defaultBlockState() && state != Blocks.MYCELIUM.defaultBlockState()) continue;
            var kind = select(random.nextInt(31));
            if (kind == Kind.GIANT_JUNGLE || kind == Kind.GIANT_MAHOGANY) {
                x = context.origin().getX() + 9 + random.nextInt(14);
                z = context.origin().getZ() + 9 + random.nextInt(14);
            }
            placed |= placeTree(context, new BlockPos(x, y + 1, z), kind);
        }
        return placed;
    }

    protected boolean placeTree(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos, Kind kind) {
        if (kind == Kind.GIANT_JUNGLE) {
            for (int x = 0; x < 2; x++)
                for (int z = 0; z < 2; z++) {
                    var below = pos.offset(x, -1, z);
                    if (context.level().isOutsideBuildHeight(below)) return false;
                    var soil = context.level().getBlockState(below);
                    if (!(soil.is(BlockTags.DIRT) || soil.is(BlockTags.GRASS_BLOCKS)) || soil.hasBlockEntity()
                            || !soil.getFluidState().isEmpty() || !soil.isFaceSturdy(context.level(), below, Direction.UP)) return false;
                }
        }
        return context.level().registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(treeKey(kind)).value()
                .place(context.level(), context.chunkGenerator(), context.random(), pos);
    }

    public enum Kind {GIANT_JUNGLE, MAHOGANY, GIANT_MAHOGANY, ASPER, JUNGLE, MOSSBARK, TALL_JUNGLE, EUCALYPTUS}
}
