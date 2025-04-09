package erebus.registries.world;

import erebus.registries.helpers.ModConfiguredFeatureHelpers;
import erebus.world.tree.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModConfiguredFeatures extends ModConfiguredFeatureHelpers {

    // Trees
    public static final AsperTree ASPER_TREE = new AsperTree();
    public static final BalsamTree BALSAM_TREE = new BalsamTree();
    public static final BambooTree BAMBOO_TREE = new BambooTree();
    public static final BaobabTree BAOBAB_TREE = new BaobabTree();
    public static final CypressTree CYPRESS_TREE = new CypressTree();
    public static final EucalyptusTree EUCALYPTUS_TREE = new EucalyptusTree();
    public static final GiantEucalyptusTree GIANT_EUCALYPTUS_TREE = new GiantEucalyptusTree();
    public static final MahoganyTree MAHOGANY_TREE = new MahoganyTree();
    public static final GiantMahoganyTree GIANT_MAHOGANY_TREE = new GiantMahoganyTree();
    public static final MarshwoodTree MARSHWOOD_TREE = new MarshwoodTree();
    public static final MossbarkTree MOSSBARK_TREE = new MossbarkTree();

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
        registerTree(context, ASPER_TREE);
        registerTree(context, BALSAM_TREE);
        registerTree(context, BAMBOO_TREE);
        registerTree(context, BAOBAB_TREE);
        registerTree(context, CYPRESS_TREE);
        registerTree(context, EUCALYPTUS_TREE);
        registerTree(context, GIANT_EUCALYPTUS_TREE);
        registerTree(context, MAHOGANY_TREE);
        registerTree(context, GIANT_MAHOGANY_TREE);
        registerTree(context, MARSHWOOD_TREE);
        registerTree(context, MOSSBARK_TREE);
    }
}
