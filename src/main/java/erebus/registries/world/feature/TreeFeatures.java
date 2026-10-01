package erebus.registries.world.feature;

import erebus.registries.helpers.ModFeatureHelpers;
import erebus.registries.world.feature.config.PlantFeatureConfigs;
import erebus.world.feature.tree.*;
import erebus.world.feature.tree.population.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static erebus.registries.world.feature.config.PlantFeatureConfigs.*;

public class TreeFeatures extends ModFeatureHelpers {
    public static JungleTree JUNGLE_TREE = new JungleTree();
    public static JungleTreePopulationFeature JUNGLE_TREE_POPULATION = new JungleTreePopulationFeature();
    public static TallJungleTree TALL_JUNGLE_TREE = new TallJungleTree();
    public static OutbackTreePopulationFeature OUTBACK_ACACIA = new OutbackTreePopulationFeature("outback_acacia_population");
    public static OutbackTreePopulationFeature OUTBACK_BALSAM = new OutbackTreePopulationFeature("outback_balsam_population");
    public static OutbackTreePopulationFeature OUTBACK_EUCALYPTUS = new OutbackTreePopulationFeature("outback_eucalyptus_population");

    public static SavannahTreePopulationFeature SAVANNAH_ACACIA = new SavannahTreePopulationFeature("savannah_acacia_population");
    public static SavannahTreePopulationFeature SAVANNAH_ASPER = new SavannahTreePopulationFeature("savannah_asper_population");
    public static SavannahTreePopulationFeature SAVANNAH_BAOBAB = new SavannahTreePopulationFeature("savannah_baobab_population");

    public static SwampTreePopulationFeature SWAMP_TREE_POPULATION = new SwampTreePopulationFeature();
    public static ElysianForestPopulationFeature ELYSIAN_FOREST_POPULATION = new ElysianForestPopulationFeature();
    public static CypressPopulationFeature CYPRESS_POPULATION = new CypressPopulationFeature();
    public static AsperTree ASPER_TREE = new AsperTree();
    public static BalsamTree BALSAM_TREE = new BalsamTree();
    public static BambooTree BAMBOO_TREE = new BambooTree();
    public static BaobabTree BAOBAB_TREE = new BaobabTree();
    public static CypressTree CYPRESS_TREE = new CypressTree();
    public static EucalyptusTree EUCALYPTUS_TREE = new EucalyptusTree();
    public static MahoganyTree MAHOGANY_TREE = new MahoganyTree();
    public static GiantMahoganyTree GIANT_MAHOGANY_TREE = new GiantMahoganyTree();
    public static MarshwoodTree MARSHWOOD_TREE = new MarshwoodTree();
    public static MossbarkTree MOSSBARK_TREE = new MossbarkTree();

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        registerConfiguredTree(JUNGLE_TREE);
        registerConfiguredFeatureWithConfig(JUNGLE_TREE_POPULATION, PlantFeatureConfigs.JUNGLE_TREE_POPULATION_CONFIG);
        registerConfiguredTree(TALL_JUNGLE_TREE);
        registerConfiguredFeatureWithConfig(OUTBACK_ACACIA, PlantFeatureConfigs.OUTBACK_ACACIA_CONFIG);
        registerConfiguredFeatureWithConfig(OUTBACK_BALSAM, PlantFeatureConfigs.OUTBACK_BALSAM_CONFIG);
        registerConfiguredFeatureWithConfig(OUTBACK_EUCALYPTUS, PlantFeatureConfigs.OUTBACK_EUCALYPTUS_CONFIG);

        registerConfiguredFeatureWithConfig(SAVANNAH_ACACIA, PlantFeatureConfigs.SAVANNAH_ACACIA_CONFIG);
        registerConfiguredFeatureWithConfig(SAVANNAH_ASPER, PlantFeatureConfigs.SAVANNAH_ASPER_CONFIG);
        registerConfiguredFeatureWithConfig(SAVANNAH_BAOBAB, PlantFeatureConfigs.SAVANNAH_BAOBAB_CONFIG);

        registerConfiguredFeatureWithConfig(SWAMP_TREE_POPULATION, SWAMP_TREE_POPULATION_CONFIG);
        registerConfiguredFeatureWithConfig(ELYSIAN_FOREST_POPULATION, ELYSIAN_FOREST_POPULATION_CONFIG);
        registerConfiguredFeatureWithConfig(CYPRESS_POPULATION, CYPRESS_POPULATION_CONFIG);
        registerConfiguredTree(ASPER_TREE);
        registerConfiguredTree(BALSAM_TREE);
        registerConfiguredTree(BAMBOO_TREE);
        registerConfiguredTree(BAOBAB_TREE);
        registerConfiguredTree(CYPRESS_TREE);
        registerConfiguredTree(EUCALYPTUS_TREE);
        registerConfiguredTree(MAHOGANY_TREE);
        registerConfiguredTree(GIANT_MAHOGANY_TREE);
        registerConfiguredTree(MARSHWOOD_TREE);
        registerConfiguredTree(MOSSBARK_TREE);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(JUNGLE_TREE_POPULATION);
        registerPlacedFeature(OUTBACK_ACACIA);
        registerPlacedFeature(OUTBACK_BALSAM);
        registerPlacedFeature(OUTBACK_EUCALYPTUS);

        registerPlacedFeature(SAVANNAH_ACACIA);
        registerPlacedFeature(SAVANNAH_ASPER);
        registerPlacedFeature(SAVANNAH_BAOBAB);

        registerPlacedFeature(SWAMP_TREE_POPULATION);
        registerPlacedFeature(ELYSIAN_FOREST_POPULATION);
        registerPlacedFeature(CYPRESS_POPULATION);
        registerPlacedFeature(ASPER_TREE);
        registerPlacedFeature(BALSAM_TREE);
        registerPlacedFeature(BAMBOO_TREE);
        registerPlacedFeature(BAOBAB_TREE);
        registerPlacedFeature(CYPRESS_TREE);
        registerPlacedFeature(EUCALYPTUS_TREE);
        registerPlacedFeature(MAHOGANY_TREE);
        registerPlacedFeature(GIANT_MAHOGANY_TREE);
        registerPlacedFeature(MARSHWOOD_TREE);
        registerPlacedFeature(MOSSBARK_TREE);
    }
}
