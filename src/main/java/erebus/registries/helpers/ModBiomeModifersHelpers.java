package erebus.registries.helpers;

import erebus.Erebus;
import erebus.world.feature.ErebusFeature;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifersHelpers {

    protected static HolderGetter<PlacedFeature> placedFeatures;
    protected static HolderGetter<Biome> biomes;
    protected static BootstrapContext<BiomeModifier> bootstrapContext;

    protected static <F extends ErebusFeature> void registerVegetalDecoration(ResourceKey<BiomeModifier> key, F feature) {
        register(key, feature, GenerationStep.Decoration.VEGETAL_DECORATION);
    }

    protected static <F extends ErebusFeature> void registerOre(ResourceKey<BiomeModifier> key, F feature) {
        register(key, feature, GenerationStep.Decoration.UNDERGROUND_ORES);
    }

    private static <F extends ErebusFeature> void register(ResourceKey<BiomeModifier> key, F feature, GenerationStep.Decoration decoration) {
        bootstrapContext.register(key, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(feature.getPlacedResourceKey())),
                decoration
        ));
    }

    protected static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Erebus.prefix(name));
    }
}
