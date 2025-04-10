package erebus.registries.world;

import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.bush.HeartBerryBushFeature;
import erebus.world.feature.bush.JadeBerryBushFeature;
import erebus.world.feature.bush.SwampBerryBushFeature;
import erebus.world.feature.tree.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModFeatures extends ModFeatureHelpers {

    // Trees
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

    // Bushes
    public static SwampBerryBushFeature SWAMP_BERRY_BUSH;
    public static HeartBerryBushFeature HEART_BERRY_BUSH;
    public static JadeBerryBushFeature JADE_BERRY_BUSH;

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        SWAMP_BERRY_BUSH = new SwampBerryBushFeature();
        HEART_BERRY_BUSH = new HeartBerryBushFeature();
        JADE_BERRY_BUSH = new JadeBerryBushFeature();

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
    }
}
