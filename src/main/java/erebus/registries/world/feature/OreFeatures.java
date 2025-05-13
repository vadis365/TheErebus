package erebus.registries.world.feature;

import erebus.registries.blocks.providers.OreBlocks;
import erebus.registries.data.ModTags;
import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.ore.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OreFeatures extends ModFeatureHelpers {

    private static final RuleTest umberstoneReplaceables = new TagMatchTest(ModTags.UMBERSTONE_ORE_REPLACEABLES);
    public static IronOre IRON_ORE = new IronOre();
    public static GoldOre GOLD_ORE = new GoldOre();
    public static CoalOre COAL_ORE = new CoalOre();
    public static DiamondOre DIAMOND_ORE = new DiamondOre();
    public static EmeraldOre EMERALD_ORE = new EmeraldOre();
    public static LapisOre LAPIS_ORE = new LapisOre();
    public static QuartzOre QUARTZ_ORE = new QuartzOre();
    public static PetrifiedQuartzOre PETRIFIED_QUARTZ_ORE = new PetrifiedQuartzOre();
    public static CopperOre COPPER_ORE = new CopperOre();
    public static SilverOre SILVER_ORE = new SilverOre();
    public static TinOre TIN_ORE = new TinOre();
    public static LeadOre LEAD_ORE = new LeadOre();
    public static AluminumOre ALUMINUM_ORE = new AluminumOre();
    public static JadeOre JADE_ORE = new JadeOre();
    public static EncrustedDiamondOre ENCRUSTED_DIAMOND_ORE = new EncrustedDiamondOre();
    public static FossilOre FOSSIL_ORE = new FossilOre();
    public static GneissOre GNEISS_ORE = new GneissOre();
    public static PetrifiedWoodOre PETRIFIED_WOOD_ORE = new PetrifiedWoodOre();
    public static TempleOre TEMPLE_ORE = new TempleOre();

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        registerConfiguredOre(IRON_ORE, umberstoneReplaceables, OreBlocks.ORE_IRON, 9);
        registerConfiguredOre(GOLD_ORE, umberstoneReplaceables, OreBlocks.ORE_GOLD, 9);
        registerConfiguredOre(COAL_ORE, umberstoneReplaceables, OreBlocks.ORE_COAL, 9);
        registerConfiguredOre(DIAMOND_ORE, umberstoneReplaceables, OreBlocks.ORE_DIAMOND, 9);
        registerConfiguredOre(EMERALD_ORE, umberstoneReplaceables, OreBlocks.ORE_EMERALD, 9);
        registerConfiguredOre(LAPIS_ORE, umberstoneReplaceables, OreBlocks.ORE_LAPIS, 9);
        registerConfiguredOre(QUARTZ_ORE, umberstoneReplaceables, OreBlocks.ORE_QUARTZ, 9);
        registerConfiguredOre(PETRIFIED_QUARTZ_ORE, umberstoneReplaceables, OreBlocks.ORE_PETRIFIED_QUARTZ, 9);
        registerConfiguredOre(COPPER_ORE, umberstoneReplaceables, OreBlocks.ORE_COPPER, 9);
        registerConfiguredOre(SILVER_ORE, umberstoneReplaceables, OreBlocks.ORE_SILVER, 9);
        registerConfiguredOre(TIN_ORE, umberstoneReplaceables, OreBlocks.ORE_TIN, 9);
        registerConfiguredOre(LEAD_ORE, umberstoneReplaceables, OreBlocks.ORE_LEAD, 9);
        registerConfiguredOre(ALUMINUM_ORE, umberstoneReplaceables, OreBlocks.ORE_ALUMINUM, 9);
        registerConfiguredOre(JADE_ORE, umberstoneReplaceables, OreBlocks.ORE_JADE, 9);
        registerConfiguredOre(ENCRUSTED_DIAMOND_ORE, umberstoneReplaceables, OreBlocks.ORE_ENCRUSTED_DIAMOND, 9);
        registerConfiguredOre(FOSSIL_ORE, umberstoneReplaceables, OreBlocks.ORE_FOSSIL, 9);
        registerConfiguredOre(GNEISS_ORE, umberstoneReplaceables, OreBlocks.ORE_GNEISS, 9);
        registerConfiguredOre(PETRIFIED_WOOD_ORE, umberstoneReplaceables, OreBlocks.ORE_PETRIFIED_WOOD, 9);
        registerConfiguredOre(TEMPLE_ORE, umberstoneReplaceables, OreBlocks.ORE_TEMPLE, 9);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(IRON_ORE);
        registerPlacedFeature(GOLD_ORE);
        registerPlacedFeature(COAL_ORE);
        registerPlacedFeature(DIAMOND_ORE);
        registerPlacedFeature(EMERALD_ORE);
        registerPlacedFeature(LAPIS_ORE);
        registerPlacedFeature(QUARTZ_ORE);
        registerPlacedFeature(PETRIFIED_QUARTZ_ORE);
        registerPlacedFeature(COPPER_ORE);
        registerPlacedFeature(SILVER_ORE);
        registerPlacedFeature(TIN_ORE);
        registerPlacedFeature(LEAD_ORE);
        registerPlacedFeature(ALUMINUM_ORE);
        registerPlacedFeature(JADE_ORE);
        registerPlacedFeature(ENCRUSTED_DIAMOND_ORE);
        registerPlacedFeature(FOSSIL_ORE);
        registerPlacedFeature(GNEISS_ORE);
        registerPlacedFeature(PETRIFIED_WOOD_ORE);
        registerPlacedFeature(TEMPLE_ORE);
    }
}
