package erebus.registries.world.feature;

import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.tree.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TreeFeatures extends ModFeatureHelpers {
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

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
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
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
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
    }
}
