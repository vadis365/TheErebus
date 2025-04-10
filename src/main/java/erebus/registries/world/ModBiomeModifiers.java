package erebus.registries.world;

import erebus.registries.helpers.ModBiomeModifersHelpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.world.BiomeModifier;

public class ModBiomeModifiers extends ModBiomeModifersHelpers {

    // Trees
    public static final ResourceKey<BiomeModifier> ADD_TREE_ASPER = registerKey("add_tree_asper");
    public static final ResourceKey<BiomeModifier> ADD_TREE_BALSAM = registerKey("add_tree_balsam");
    public static final ResourceKey<BiomeModifier> ADD_TREE_BAMBOO = registerKey("add_tree_bamboo");
    public static final ResourceKey<BiomeModifier> ADD_TREE_BAOBAB = registerKey("add_tree_baobab");
    public static final ResourceKey<BiomeModifier> ADD_TREE_CYPRESS = registerKey("add_tree_cypress");
    public static final ResourceKey<BiomeModifier> ADD_TREE_EUCALYPTUS = registerKey("add_tree_eucalyptus");
    public static final ResourceKey<BiomeModifier> ADD_TREE_GIANT_EUCALYPTUS = registerKey("add_tree_giant_eucalyptus");
    public static final ResourceKey<BiomeModifier> ADD_TREE_MAHOGANY = registerKey("add_tree_mahogany");
    public static final ResourceKey<BiomeModifier> ADD_TREE_GIANT_MAHOGANY = registerKey("add_tree_giant_mahogany");
    public static final ResourceKey<BiomeModifier> ADD_TREE_MARSHWOOD = registerKey("add_tree_marshwood");
    public static final ResourceKey<BiomeModifier> ADD_TREE_MOSSBARK = registerKey("add_tree_mossbark");

    // Bushes
    public static final ResourceKey<BiomeModifier> ADD_BUSH_SWAMP_BERRY = registerKey("add_bush_swamp_berry");
    public static final ResourceKey<BiomeModifier> ADD_BUSH_HEART_BERRY = registerKey("add_bush_heart_berry");
    public static final ResourceKey<BiomeModifier> ADD_BUSH_JADE_BERRY = registerKey("add_bush_jade_berry");

    public static final ResourceKey<BiomeModifier> ADD_ORE_IRON = registerKey("add_ore_iron");
    public static final ResourceKey<BiomeModifier> ADD_ORE_GOLD = registerKey("add_ore_gold");
    public static final ResourceKey<BiomeModifier> ADD_ORE_COAL = registerKey("add_ore_coal");
    public static final ResourceKey<BiomeModifier> ADD_ORE_DIAMOND = registerKey("add_ore_diamond");
    public static final ResourceKey<BiomeModifier> ADD_ORE_EMERALD = registerKey("add_ore_emerald");
    public static final ResourceKey<BiomeModifier> ADD_ORE_LAPIS = registerKey("add_ore_lapis");
    public static final ResourceKey<BiomeModifier> ADD_ORE_QUARTZ = registerKey("add_ore_quartz");
    public static final ResourceKey<BiomeModifier> ADD_ORE_PETRIFIED_QUARTZ = registerKey("add_ore_petrified_quartz");
    public static final ResourceKey<BiomeModifier> ADD_ORE_COPPER = registerKey("add_ore_copper");
    public static final ResourceKey<BiomeModifier> ADD_ORE_SILVER = registerKey("add_ore_silver");
    public static final ResourceKey<BiomeModifier> ADD_ORE_TIN = registerKey("add_ore_tin");
    public static final ResourceKey<BiomeModifier> ADD_ORE_LEAD = registerKey("add_ore_lead");
    public static final ResourceKey<BiomeModifier> ADD_ORE_ALUMINUM = registerKey("add_ore_aluminum");
    public static final ResourceKey<BiomeModifier> ADD_ORE_JADE = registerKey("add_ore_jade");
    public static final ResourceKey<BiomeModifier> ADD_ORE_ENCRUSTED_DIAMOND = registerKey("add_ore_encrusted_diamond");
    public static final ResourceKey<BiomeModifier> ADD_ORE_FOSSIL = registerKey("add_ore_fossil");
    public static final ResourceKey<BiomeModifier> ADD_ORE_GNEISS = registerKey("add_ore_gneiss");
    public static final ResourceKey<BiomeModifier> ADD_ORE_PETRIFIED_WOOD = registerKey("add_ore_petrified_wood");
    public static final ResourceKey<BiomeModifier> ADD_ORE_TEMPLE = registerKey("add_ore_temple");

    public static final ResourceKey<BiomeModifier> ADD_PLANT_NETTLE = registerKey("add_plant_nettle");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_SWAMP_PLANT = registerKey("add_plant_swamp_plant");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_FIRE_BLOOM = registerKey("add_plant_fire_bloom");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_FIDDLE_HEAD = registerKey("add_plant_fiddle_head");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_BULL_RUSH = registerKey("add_plant_bull_rush");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_WEEPING_BLUEBELL = registerKey("add_plant_weeping_bluebell");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_SUNDEW = registerKey("add_plant_sundew");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_TALL_BLOOM = registerKey("add_plant_tall_bloom");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_TANGLED_STALK = registerKey("add_plant_tangled_stalk");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_HIGH_CAPPED_MUSHROOM = registerKey("add_plant_high_capped_mushroom");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_FERN = registerKey("add_plant_fern");
    public static final ResourceKey<BiomeModifier> ADD_PLANT_TALL_FERN = registerKey("add_plant_tall_fern");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        biomes = context.lookup(Registries.BIOME);
        bootstrapContext = context;

        // MARK: Trees
        registerVegetalDecoration(ADD_TREE_ASPER, ModFeatures.ASPER_TREE);
        registerVegetalDecoration(ADD_TREE_BALSAM, ModFeatures.BALSAM_TREE);
        registerVegetalDecoration(ADD_TREE_BAMBOO, ModFeatures.BAMBOO_TREE);
        registerVegetalDecoration(ADD_TREE_BAOBAB, ModFeatures.BAOBAB_TREE);
        registerVegetalDecoration(ADD_TREE_CYPRESS, ModFeatures.CYPRESS_TREE);
        registerVegetalDecoration(ADD_TREE_EUCALYPTUS, ModFeatures.EUCALYPTUS_TREE);
        registerVegetalDecoration(ADD_TREE_GIANT_EUCALYPTUS, ModFeatures.GIANT_EUCALYPTUS_TREE);
        registerVegetalDecoration(ADD_TREE_MAHOGANY, ModFeatures.MAHOGANY_TREE);
        registerVegetalDecoration(ADD_TREE_GIANT_MAHOGANY, ModFeatures.GIANT_MAHOGANY_TREE);
        registerVegetalDecoration(ADD_TREE_MARSHWOOD, ModFeatures.MARSHWOOD_TREE);
        registerVegetalDecoration(ADD_TREE_MOSSBARK, ModFeatures.MOSSBARK_TREE);

        // MARK: Bushes
        registerVegetalDecoration(ADD_BUSH_SWAMP_BERRY, ModFeatures.SWAMP_BERRY_BUSH);
        registerVegetalDecoration(ADD_BUSH_HEART_BERRY, ModFeatures.HEART_BERRY_BUSH);
        registerVegetalDecoration(ADD_BUSH_JADE_BERRY, ModFeatures.JADE_BERRY_BUSH);

        // MARK: Ores
        registerOre(ADD_ORE_IRON, ModFeatures.IRON_ORE);
        registerOre(ADD_ORE_GOLD, ModFeatures.GOLD_ORE);
        registerOre(ADD_ORE_COAL, ModFeatures.COAL_ORE);
        registerOre(ADD_ORE_DIAMOND, ModFeatures.DIAMOND_ORE);
        registerOre(ADD_ORE_EMERALD, ModFeatures.EMERALD_ORE);
        registerOre(ADD_ORE_LAPIS, ModFeatures.LAPIS_ORE);
        registerOre(ADD_ORE_QUARTZ, ModFeatures.QUARTZ_ORE);
        registerOre(ADD_ORE_PETRIFIED_QUARTZ, ModFeatures.PETRIFIED_QUARTZ_ORE);
        registerOre(ADD_ORE_COPPER, ModFeatures.COPPER_ORE);
        registerOre(ADD_ORE_SILVER, ModFeatures.SILVER_ORE);
        registerOre(ADD_ORE_TIN, ModFeatures.TIN_ORE);
        registerOre(ADD_ORE_LEAD, ModFeatures.LEAD_ORE);
        registerOre(ADD_ORE_ALUMINUM, ModFeatures.ALUMINUM_ORE);
        registerOre(ADD_ORE_JADE, ModFeatures.JADE_ORE);
        registerOre(ADD_ORE_ENCRUSTED_DIAMOND, ModFeatures.ENCRUSTED_DIAMOND_ORE);
        registerOre(ADD_ORE_FOSSIL, ModFeatures.FOSSIL_ORE);
        registerOre(ADD_ORE_GNEISS, ModFeatures.GNEISS_ORE);
        registerOre(ADD_ORE_PETRIFIED_WOOD, ModFeatures.PETRIFIED_WOOD_ORE);
        registerOre(ADD_ORE_TEMPLE, ModFeatures.TEMPLE_ORE);

        // MARK: Plants
        registerVegetalDecoration(ADD_PLANT_NETTLE, ModFeatures.NETTLE);
        registerVegetalDecoration(ADD_PLANT_SWAMP_PLANT, ModFeatures.SWAMP_PLANT);
        registerVegetalDecoration(ADD_PLANT_FIRE_BLOOM, ModFeatures.FIRE_BLOOM);
        registerVegetalDecoration(ADD_PLANT_FIDDLE_HEAD, ModFeatures.FIDDLE_HEAD);
        registerVegetalDecoration(ADD_PLANT_BULL_RUSH, ModFeatures.BULLRUSH);
        registerVegetalDecoration(ADD_PLANT_WEEPING_BLUEBELL, ModFeatures.WEEPING_BLUEBELL);
        registerVegetalDecoration(ADD_PLANT_SUNDEW, ModFeatures.SUNDEW);
        registerVegetalDecoration(ADD_PLANT_TALL_BLOOM, ModFeatures.TALL_BLOOM);
        registerVegetalDecoration(ADD_PLANT_TANGLED_STALK, ModFeatures.TANGLED_STALK);
        registerVegetalDecoration(ADD_PLANT_HIGH_CAPPED_MUSHROOM, ModFeatures.HIGH_CAPPED_MUSHROOM);
        registerVegetalDecoration(ADD_PLANT_FERN, ModFeatures.FERN);
        registerVegetalDecoration(ADD_PLANT_TALL_FERN, ModFeatures.TALL_FERN);
    }
}
