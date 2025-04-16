package erebus.world.feature;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;
import java.util.function.Supplier;

public class ErebusFeature {

    private final ResourceKey<PlacedFeature> PLACED_KEY;
    private final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_KEY;

    public ErebusFeature(String name) {
        this.PLACED_KEY = registerPlacedKey(name);
        this.CONFIGURED_KEY = registerConfiguredKey(name);
    }

    private ResourceKey<PlacedFeature> registerPlacedKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Erebus.prefix(name));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Erebus.MODID, name));
    }

    public ResourceKey<PlacedFeature> getPlacedResourceKey() {
        return PLACED_KEY;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredResourceKey() {
        return CONFIGURED_KEY;
    }

    public List<PlacementModifier> getPlacementModifiers() {
        return null;
    }

    /**
     * Placement Utils
     */

    protected List<PlacementModifier> tree(int baseValue, float chance, int addedAmount, Supplier<? extends Block> sapling) {
        return VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 2), sapling.get());
    }

    protected List<PlacementModifier> patch(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }

    protected List<PlacementModifier> patchWithFilter(int count, BlockPredicate filter) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BlockPredicateFilter.forPredicate(filter), BiomeFilter.biome());
    }

    protected List<PlacementModifier> noise(int noiseToCountRatio, double factor, double offset) {
        return List.of(NoiseBasedCountPlacement.of(noiseToCountRatio, factor, offset), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    }

    protected List<PlacementModifier> noiseWithFilter(int noiseToCountRatio, double factor, double offset, BlockPredicate filter) {
        return List.of(NoiseBasedCountPlacement.of(noiseToCountRatio, factor, offset), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BlockPredicateFilter.forPredicate(filter), BiomeFilter.biome());
    }
}
