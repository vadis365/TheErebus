package erebus.registries.world.feature;

import erebus.registries.blocks.providers.OreBlocks;
import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.data.ModTags;
import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.bush.HeartBerryBushFeature;
import erebus.world.feature.bush.JadeBerryBushFeature;
import erebus.world.feature.bush.SwampBerryBushFeature;
import erebus.world.feature.misc.*;
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

    // MARK: MISC
    public static AmberGroundFeature AMBER_GROUND = new AmberGroundFeature();
    public static AmberUmberstoneFeature AMBER_UMBERSTONE = new AmberUmberstoneFeature();
    public static DesertRockGneissFeature DESERT_ROCK_GNEISS = new DesertRockGneissFeature();
    public static GasVentFeature GAS_VENT = new GasVentFeature();
    public static LakeWithEdgeFeature LAKE_WITH_EDGE = new LakeWithEdgeFeature();
    public static PondFeature POND = new PondFeature();
    public static QuickSandFeature QUICK_SAND = new QuickSandFeature();
    public static RedGemFeature RED_GEM = new RedGemFeature();
    public static RockSpikeFeature ROCK_SPIKE = new RockSpikeFeature();
    public static RottenAcaciaFeature ROTTEN_ACACIA = new RottenAcaciaFeature();
    public static SavannahRockFeature SAVANNAH_ROCK = new SavannahRockFeature();
    public static ScorchedWoodFeature SCORCHED_WOOD = new ScorchedWoodFeature();

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        SWAMP_BERRY_BUSH = new SwampBerryBushFeature();
        HEART_BERRY_BUSH = new HeartBerryBushFeature();
        JADE_BERRY_BUSH = new JadeBerryBushFeature();

        RuleTest umberstoneReplaceables = new TagMatchTest(ModTags.UMBERSTONE_ORE_REPLACEABLES);

        registerConfiguredTree(ASPER_TREE);
        registerConfiguredTree(BALSAM_TREE);
        registerConfiguredTree(BAMBOO_TREE);
        registerConfiguredTree(BAOBAB_TREE);
        registerConfiguredTree(CYPRESS_TREE);
        registerConfiguredTree(EUCALYPTUS_TREE);
        registerConfiguredTree(GIANT_EUCALYPTUS_TREE);
        registerConfiguredTree(MAHOGANY_TREE);
        registerConfiguredTree(GIANT_MAHOGANY_TREE);
        registerConfiguredTree(MARSHWOOD_TREE);
        registerConfiguredTree(MOSSBARK_TREE);

        registerConfiguredBush(SWAMP_BERRY_BUSH);
        registerConfiguredBush(HEART_BERRY_BUSH);
        registerConfiguredBush(JADE_BERRY_BUSH);

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

        registerConfiguredFeature(
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

        registerConfiguredFeatureWithConfig(AMBER_GROUND, ModFeatureConfigurations.AMBER_GROUND_CONFIG);
        registerConfiguredFeatureWithConfig(AMBER_UMBERSTONE, ModFeatureConfigurations.AMBER_UMBERSTONE_CONFIG);
        registerConfiguredFeatureWithConfig(DESERT_ROCK_GNEISS, ModFeatureConfigurations.DESERT_ROCK_GNEISS_CONFIG);
        registerConfiguredFeatureWithConfig(GAS_VENT, ModFeatureConfigurations.GAS_VENT_CONFIG);
        registerConfiguredFeatureWithConfig(LAKE_WITH_EDGE, ModFeatureConfigurations.LAKE_WITH_EDGE_CONFIG);
        registerConfiguredFeatureWithConfig(POND, ModFeatureConfigurations.POND_CONFIG);
        registerConfiguredFeatureWithConfig(QUICK_SAND, ModFeatureConfigurations.QUICK_SAND_CONFIG);
        registerConfiguredFeatureWithConfig(RED_GEM, ModFeatureConfigurations.RED_GEM_FEATURE_CONFIG);
        registerConfiguredFeatureWithConfig(ROCK_SPIKE, ModFeatureConfigurations.ROCK_SPIKE_CONFIG);
        registerConfiguredFeatureWithConfig(ROTTEN_ACACIA, ModFeatureConfigurations.ROTTEN_ACACIA_CONFIG);
        registerConfiguredFeatureWithConfig(SAVANNAH_ROCK, ModFeatureConfigurations.SAVANNAH_ROCK_CONFIG);
        registerConfiguredFeatureWithConfig(SCORCHED_WOOD, ModFeatureConfigurations.SCORCHED_WOOD_CONFIG);
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(ASPER_TREE);
        registerPlacedFeature(BALSAM_TREE);
        registerPlacedFeature(BAMBOO_TREE);
        registerPlacedFeature(BAOBAB_TREE);
        registerPlacedFeature(CYPRESS_TREE);
        registerPlacedFeature(EUCALYPTUS_TREE);
        registerPlacedFeature(GIANT_EUCALYPTUS_TREE);
        registerPlacedFeature(MAHOGANY_TREE);
        registerPlacedFeature(GIANT_MAHOGANY_TREE);
        registerPlacedFeature(MARSHWOOD_TREE);
        registerPlacedFeature(MOSSBARK_TREE);

        registerPlacedFeature(SWAMP_BERRY_BUSH);
        registerPlacedFeature(HEART_BERRY_BUSH);
        registerPlacedFeature(JADE_BERRY_BUSH);

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

        registerPlacedFeature(NETTLE);
        registerPlacedFeature(SWAMP_PLANT);
        registerPlacedFeature(FIRE_BLOOM);
        registerPlacedFeature(FIDDLE_HEAD);
        registerPlacedFeature(BULLRUSH);
        registerPlacedFeature(WEEPING_BLUEBELL);
        registerPlacedFeature(SUNDEW);
        registerPlacedFeature(TALL_BLOOM);
        registerPlacedFeature(TANGLED_STALK);
        registerPlacedFeature(HIGH_CAPPED_MUSHROOM);
        registerPlacedFeature(FERN);
        registerPlacedFeature(TALL_FERN);

        registerPlacedFeature(RED_GEM);
    }
}
