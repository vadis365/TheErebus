package erebus.registries.world.feature;

import erebus.Config;
import erebus.Erebus;
import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.ConfigurableOreFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures extends ModFeatureHelpers {
    public static final DeferredRegister<Feature<?>> CONFIGS = DeferredRegister.create(BuiltInRegistries.FEATURE, Erebus.MODID);

    public static final DeferredHolder<Feature<?>, ConfigurableOreFeature> COPPER_ORE = CONFIGS.register("copper_ore", () -> new ConfigurableOreFeature(() -> Config.generateCopperOre));
    public static final DeferredHolder<Feature<?>, ConfigurableOreFeature> TIN_ORE = CONFIGS.register("tin_ore", () -> new ConfigurableOreFeature(() -> Config.generateTinOre));
    public static final DeferredHolder<Feature<?>, ConfigurableOreFeature> SILVER_ORE = CONFIGS.register("silver_ore", () -> new ConfigurableOreFeature(() -> Config.generateSilverOre));
    public static final DeferredHolder<Feature<?>, ConfigurableOreFeature> LEAD_ORE = CONFIGS.register("lead_ore", () -> new ConfigurableOreFeature(() -> Config.generateLeadOre));
    public static final DeferredHolder<Feature<?>, ConfigurableOreFeature> ALUMINUM_ORE = CONFIGS.register("aluminum_ore", () -> new ConfigurableOreFeature(() -> Config.generateAluminumOre));

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        DecorationFeatures.initConfiguredFeatures(context);
        MiscFeatures.initConfiguredFeatures(context);
        OreFeatures.initConfiguredFeatures(context);
        PlantFeatures.initConfiguredFeatures(context);
        TreeFeatures.initConfiguredFeatures(context);
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        DecorationFeatures.initPlacedFeatures(context);
        MiscFeatures.initPlacedFeatures(context);
        OreFeatures.initPlacedFeatures(context);
        PlantFeatures.initPlacedFeatures(context);
        TreeFeatures.initPlacedFeatures(context);
    }
}
