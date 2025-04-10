package erebus.world.feature;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

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
}
