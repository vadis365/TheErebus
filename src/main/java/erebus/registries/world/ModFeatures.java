package erebus.registries.world;

import erebus.registries.blocks.providers.OreBlocks;
import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.data.ModTags;
import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.bush.HeartBerryBushFeature;
import erebus.world.feature.bush.JadeBerryBushFeature;
import erebus.world.feature.bush.SwampBerryBushFeature;
import erebus.world.feature.ore.*;
import erebus.world.feature.plant.ErebusPlantFeature;
import erebus.world.feature.tree.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class ModFeatures extends ModFeatureHelpers {

    // MARK: Trees
    public static AsperTree ASPER_TREE = new AsperTree();
    public static BalsamTree BALSAM_TREE = new BalsamTree();
    public static BambooTree BAMBOO_TREE = new BambooTree();
    public static BaobabTree BAOBAB_TREE = new BaobabTree();
    public static CypressTree CYPRESS_TREE = new CypressTree();
    public static EucalyptusTree EUCALYPTUS_TREE = new EucalyptusTree();
    public static GiantEucalyptusTree GIANT_EUCALYPTUS_TREE = new GiantEucalyptusTree();
    public static MahoganyTree MAHOGANY_TREE = new MahoganyTree();
    public static GiantMahoganyTree GIANT_MAHOGANY_TREE = new GiantMahoganyTree();
    public static MarshwoodTree MARSHWOOD_TREE = new MarshwoodTree();
    public static MossbarkTree MOSSBARK_TREE = new MossbarkTree();

    // MARK: Bushes
    public static SwampBerryBushFeature SWAMP_BERRY_BUSH;
    public static HeartBerryBushFeature HEART_BERRY_BUSH;
    public static JadeBerryBushFeature JADE_BERRY_BUSH;

    // MARK: Ores
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

    // MARK: Plants
    public static ErebusPlantFeature NETTLE = new ErebusPlantFeature("nettle");
    public static ErebusPlantFeature SWAMP_PLANT = new ErebusPlantFeature("swamp_plant");
    public static ErebusPlantFeature FIRE_BLOOM = new ErebusPlantFeature("fire_bloom");
    public static ErebusPlantFeature FIDDLE_HEAD = new ErebusPlantFeature("fiddle_head");
    public static ErebusPlantFeature BULLRUSH = new ErebusPlantFeature("bullrush");
    public static ErebusPlantFeature WEEPING_BLUEBELL = new ErebusPlantFeature("weeping_bluebell");
    public static ErebusPlantFeature SUNDEW = new ErebusPlantFeature("sundew");
    public static ErebusPlantFeature TALL_BLOOM = new ErebusPlantFeature("tall_bloom");
    public static ErebusPlantFeature TANGLED_STALK = new ErebusPlantFeature("tangled_stalk");
    public static ErebusPlantFeature HIGH_CAPPED_MUSHROOM = new ErebusPlantFeature("high_capped_mushroom");
    public static ErebusPlantFeature FERN = new ErebusPlantFeature("fern");
    public static ErebusPlantFeature TALL_FERN = new ErebusPlantFeature("tall_fern");

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        SWAMP_BERRY_BUSH = new SwampBerryBushFeature();
        HEART_BERRY_BUSH = new HeartBerryBushFeature();
        JADE_BERRY_BUSH = new JadeBerryBushFeature();

        RuleTest umberstoneReplaceables = new TagMatchTest(ModTags.UMBERSTONE_ORE_REPLACEABLES);

        registerConfiguredTree(context, ASPER_TREE);
        registerConfiguredTree(context, BALSAM_TREE);
        registerConfiguredTree(context, BAMBOO_TREE);
        registerConfiguredTree(context, BAOBAB_TREE);
        registerConfiguredTree(context, CYPRESS_TREE);
        registerConfiguredTree(context, EUCALYPTUS_TREE);
        registerConfiguredTree(context, GIANT_EUCALYPTUS_TREE);
        registerConfiguredTree(context, MAHOGANY_TREE);
        registerConfiguredTree(context, GIANT_MAHOGANY_TREE);
        registerConfiguredTree(context, MARSHWOOD_TREE);
        registerConfiguredTree(context, MOSSBARK_TREE);

        registerConfiguredBush(context, SWAMP_BERRY_BUSH);
        registerConfiguredBush(context, HEART_BERRY_BUSH);
        registerConfiguredBush(context, JADE_BERRY_BUSH);

        registerConfiguredOre(context, IRON_ORE, umberstoneReplaceables, OreBlocks.ORE_IRON, 9);
        registerConfiguredOre(context, GOLD_ORE, umberstoneReplaceables, OreBlocks.ORE_GOLD, 9);
        registerConfiguredOre(context, COAL_ORE, umberstoneReplaceables, OreBlocks.ORE_COAL, 9);
        registerConfiguredOre(context, DIAMOND_ORE, umberstoneReplaceables, OreBlocks.ORE_DIAMOND, 9);
        registerConfiguredOre(context, EMERALD_ORE, umberstoneReplaceables, OreBlocks.ORE_EMERALD, 9);
        registerConfiguredOre(context, LAPIS_ORE, umberstoneReplaceables, OreBlocks.ORE_LAPIS, 9);
        registerConfiguredOre(context, QUARTZ_ORE, umberstoneReplaceables, OreBlocks.ORE_QUARTZ, 9);
        registerConfiguredOre(context, PETRIFIED_QUARTZ_ORE, umberstoneReplaceables, OreBlocks.ORE_PETRIFIED_QUARTZ, 9);
        registerConfiguredOre(context, COPPER_ORE, umberstoneReplaceables, OreBlocks.ORE_COPPER, 9);
        registerConfiguredOre(context, SILVER_ORE, umberstoneReplaceables, OreBlocks.ORE_SILVER, 9);
        registerConfiguredOre(context, TIN_ORE, umberstoneReplaceables, OreBlocks.ORE_TIN, 9);
        registerConfiguredOre(context, LEAD_ORE, umberstoneReplaceables, OreBlocks.ORE_LEAD, 9);
        registerConfiguredOre(context, ALUMINUM_ORE, umberstoneReplaceables, OreBlocks.ORE_ALUMINUM, 9);
        registerConfiguredOre(context, JADE_ORE, umberstoneReplaceables, OreBlocks.ORE_JADE, 9);
        registerConfiguredOre(context, ENCRUSTED_DIAMOND_ORE, umberstoneReplaceables, OreBlocks.ORE_ENCRUSTED_DIAMOND, 9);
        registerConfiguredOre(context, FOSSIL_ORE, umberstoneReplaceables, OreBlocks.ORE_FOSSIL, 9);
        registerConfiguredOre(context, GNEISS_ORE, umberstoneReplaceables, OreBlocks.ORE_GNEISS, 9);
        registerConfiguredOre(context, PETRIFIED_WOOD_ORE, umberstoneReplaceables, OreBlocks.ORE_PETRIFIED_WOOD, 9);
        registerConfiguredOre(context, TEMPLE_ORE, umberstoneReplaceables, OreBlocks.ORE_TEMPLE, 9);

        registerConfiguredFeature(
                context,
                NETTLE.getConfiguredResourceKey(),
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,
                        2,
                        0,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new NoiseThresholdProvider(
                                                2345L,
                                                new NormalNoise.NoiseParameters(0, 1.0),
                                                0.005F,
                                                -0.8F,
                                                0.33333334F,
                                                PlantBlocks.NETTLE.get().defaultBlockState(),
                                                List.of(PlantBlocks.NETTLE.get().defaultBlockState()),
                                                List.of(PlantBlocks.NETTLE_FLOWERED.get().defaultBlockState())
                                        )
                                )
                        )
                )
        );

        registerSimpleConfiguredPlant(context, SWAMP_PLANT, PlantBlocks.SWAMP_PLANT, 64);
        registerSimpleConfiguredPlant(context, FIRE_BLOOM, PlantBlocks.FIRE_BLOOM, 64);
        registerSimpleConfiguredPlant(context, FIDDLE_HEAD, PlantBlocks.FIDDLE_HEAD, 64);
        registerSimpleConfiguredPlant(context, BULLRUSH, PlantBlocks.BULLRUSH, 64);
        registerSimpleConfiguredPlant(context, WEEPING_BLUEBELL, PlantBlocks.WEEPING_BLUEBELL, 64);
        registerSimpleConfiguredPlant(context, SUNDEW, PlantBlocks.SUNDEW, 64);
        registerSimpleConfiguredPlant(context, TALL_BLOOM, PlantBlocks.TALL_BLOOM, 64);
        registerSimpleConfiguredPlant(context, TANGLED_STALK, PlantBlocks.TANGLED_STALK, 64);
        registerSimpleConfiguredPlant(context, HIGH_CAPPED_MUSHROOM, PlantBlocks.HIGH_CAPPED_MUSHROOM, 64);
        registerSimpleConfiguredPlant(context, FERN, PlantBlocks.FERN, 32);
        registerSimpleConfiguredPlant(context, TALL_FERN, PlantBlocks.TALL_FERN, 32);
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        registerPlacedFeature(context, ASPER_TREE);
        registerPlacedFeature(context, BALSAM_TREE);
        registerPlacedFeature(context, BAMBOO_TREE);
        registerPlacedFeature(context, BAOBAB_TREE);
        registerPlacedFeature(context, CYPRESS_TREE);
        registerPlacedFeature(context, EUCALYPTUS_TREE);
        registerPlacedFeature(context, GIANT_EUCALYPTUS_TREE);
        registerPlacedFeature(context, MAHOGANY_TREE);
        registerPlacedFeature(context, GIANT_MAHOGANY_TREE);
        registerPlacedFeature(context, MARSHWOOD_TREE);
        registerPlacedFeature(context, MOSSBARK_TREE);

        registerPlacedFeature(context, SWAMP_BERRY_BUSH);
        registerPlacedFeature(context, HEART_BERRY_BUSH);
        registerPlacedFeature(context, JADE_BERRY_BUSH);

        registerPlacedFeature(context, IRON_ORE);
        registerPlacedFeature(context, GOLD_ORE);
        registerPlacedFeature(context, COAL_ORE);
        registerPlacedFeature(context, DIAMOND_ORE);
        registerPlacedFeature(context, EMERALD_ORE);
        registerPlacedFeature(context, LAPIS_ORE);
        registerPlacedFeature(context, QUARTZ_ORE);
        registerPlacedFeature(context, PETRIFIED_QUARTZ_ORE);
        registerPlacedFeature(context, COPPER_ORE);
        registerPlacedFeature(context, SILVER_ORE);
        registerPlacedFeature(context, TIN_ORE);
        registerPlacedFeature(context, LEAD_ORE);
        registerPlacedFeature(context, ALUMINUM_ORE);
        registerPlacedFeature(context, JADE_ORE);
        registerPlacedFeature(context, ENCRUSTED_DIAMOND_ORE);
        registerPlacedFeature(context, FOSSIL_ORE);
        registerPlacedFeature(context, GNEISS_ORE);
        registerPlacedFeature(context, PETRIFIED_WOOD_ORE);
        registerPlacedFeature(context, TEMPLE_ORE);

        registerPlacedFeature(context, NETTLE);
        registerPlacedFeature(context, SWAMP_PLANT);
        registerPlacedFeature(context, FIRE_BLOOM);
        registerPlacedFeature(context, FIDDLE_HEAD);
        registerPlacedFeature(context, BULLRUSH);
        registerPlacedFeature(context, WEEPING_BLUEBELL);
        registerPlacedFeature(context, SUNDEW);
        registerPlacedFeature(context, TALL_BLOOM);
        registerPlacedFeature(context, TANGLED_STALK);
        registerPlacedFeature(context, HIGH_CAPPED_MUSHROOM);
        registerPlacedFeature(context, FERN);
        registerPlacedFeature(context, TALL_FERN);
    }
}
