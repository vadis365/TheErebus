package erebus.registries.world.feature;

import erebus.Erebus;
import erebus.registries.helpers.ModFeatureHelpers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures extends ModFeatureHelpers {
    public static final DeferredRegister<Feature<?>> CONFIGS = DeferredRegister.create(BuiltInRegistries.FEATURE, Erebus.MODID);

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        DecorationFeatures.initConfiguredFeatures(context);
        MiscFeatures.initConfiguredFeatures(context);
        OreFeatures.initConfiguredFeatures(context);
        PlantFeatures.initConfiguredFeatures(context);
        StructureFeatures.initConfiguredFeatures(context);
        TreeFeatures.initConfiguredFeatures(context);
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        DecorationFeatures.initPlacedFeatures(context);
        MiscFeatures.initPlacedFeatures(context);
        OreFeatures.initPlacedFeatures(context);
        PlantFeatures.initPlacedFeatures(context);
        StructureFeatures.initPlacedFeatures(context);
        TreeFeatures.initPlacedFeatures(context);
    }
}
