package erebus.registries.world.feature;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.ErebusOre;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static erebus.world.ModOrePlacement.orePlacement;
import static erebus.world.ModOrePlacement.rareOrePlacement;

public class OreFeatures extends ModFeatureHelpers {

    private static final int COAL_VEIN_SIZE = 10;
    private static final int IRON_VEIN_SIZE = 7;
    private static final int GOLD_VEIN_SIZE = 6;
    private static final int LAPIS_VEIN_SIZE = 5;
    private static final int EMERALD_VEIN_SIZE = 3;
    private static final int DIAMOND_VEIN_SIZE = 1;
    private static final int JADE_VEIN_SIZE = 4;
    private static final int PETRIFIED_WOOD_VEIN_SIZE = 8;
    private static final int FOSSIL_VEIN_SIZE = 10;
    private static final int ALUMINUM_VEIN_SIZE = 4;
    private static final int COPPER_VEIN_SIZE = 6;
    private static final int LEAD_VEIN_SIZE = 3;
    private static final int SILVER_VEIN_SIZE = 3;
    private static final int TIN_VEIN_SIZE = 3;
    private static final int QUARTZ_VEIN_SIZE = 8;
    private static final int GNEISS_VEIN_SIZE = 3;
    private static final int TEMPLE_VEIN_SIZE = 8;

    public static final ErebusOre COAL_ORE;
    public static final ErebusOre IRON_ORE;
    public static final ErebusOre GOLD_ORE;
    public static final ErebusOre LAPIS_ORE;
    public static final ErebusOre EMERALD_ORE;
    public static final ErebusOre DIAMOND_ORE;
    public static final ErebusOre JADE_ORE;
    public static final ErebusOre PETRIFIED_WOOD_ORE;
    public static final ErebusOre FOSSIL_ORE;
    public static final ErebusOre ALUMINUM_ORE;
    public static final ErebusOre COPPER_ORE;
    public static final ErebusOre LEAD_ORE;
    public static final ErebusOre SILVER_ORE;
    public static final ErebusOre TIN_ORE;
    public static final ErebusOre QUARTZ_ORE;
    public static final ErebusOre GNEISS_ORE;
    public static final ErebusOre TEMPLE_ORE;

    public static final ErebusOre COAL_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre IRON_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre GOLD_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre EMERALD_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre JADE_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre FOSSIL_ORE_ELYSIAN_FIELDS;

    public static final ErebusOre COAL_ORE_FUNGAL_FOREST;
    public static final ErebusOre IRON_ORE_FUNGAL_FOREST;
    public static final ErebusOre GOLD_ORE_FUNGAL_FOREST;
    public static final ErebusOre EMERALD_ORE_FUNGAL_FOREST;
    public static final ErebusOre JADE_ORE_FUNGAL_FOREST;
    public static final ErebusOre FOSSIL_ORE_FUNGAL_FOREST;

    public static final ErebusOre GOLD_ORE_PETRIFIED_FOREST;
    public static final ErebusOre ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST;
    public static final ErebusOre JADE_ORE_PETRIFIED_FOREST;
    public static final ErebusOre FOSSIL_ORE_PETRIFIED_FOREST;

    public static final ErebusOre COAL_ORE_SUBMERGED_SWAMP;
    public static final ErebusOre EMERALD_ORE_SUBMERGED_SWAMP;
    public static final ErebusOre DIAMOND_ORE_SUBMERGED_SWAMP;
    public static final ErebusOre PETRIFIED_WOOD_ORE_SUBMERGED_SWAMP;
    public static final ErebusOre FOSSIL_ORE_SUBMERGED_SWAMP;

    public static final ErebusOre GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH;
    public static final ErebusOre GOLD_ORE_SUBTERRANEAN_SAVANNAH;
    public static final ErebusOre GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW;
    public static final ErebusOre EMERALD_ORE_SUBTERRANEAN_SAVANNAH;
    public static final ErebusOre DIAMOND_ORE_SUBTERRANEAN_SAVANNAH;
    public static final ErebusOre JADE_ORE_SUBTERRANEAN_SAVANNAH;
    public static final ErebusOre PETRIFIED_WOOD_ORE_SUBTERRANEAN_SAVANNAH;

    public static final ErebusOre COAL_ORE_ULTERIOR_OUTBACK;
    public static final ErebusOre EMERALD_ORE_ULTERIOR_OUTBACK;
    public static final ErebusOre DIAMOND_ORE_ULTERIOR_OUTBACK;
    public static final ErebusOre PETRIFIED_WOOD_ORE_ULTERIOR_OUTBACK;
    public static final ErebusOre FOSSIL_ORE_ULTERIOR_OUTBACK;

    public static final ErebusOre COAL_ORE_UNDERGROUND_JUNGLE;
    public static final ErebusOre COAL_ORE_UNDERGROUND_JUNGLE_LARGE;
    public static final ErebusOre DIAMOND_ORE_UNDERGROUND_JUNGLE;
    public static final ErebusOre JADE_ORE_UNDERGROUND_JUNGLE;
    public static final ErebusOre PETRIFIED_WOOD_ORE_UNDERGROUND_JUNGLE;
    public static final ErebusOre FOSSIL_ORE_UNDERGROUND_JUNGLE;

    public static final ErebusOre GOLD_ORE_VOLCANIC_DESERT;
    public static final ErebusOre ENCRUSTED_DIAMOND_ORE_VOLCANIC_DESERT;
    public static final ErebusOre JADE_ORE_VOLCANIC_DESERT;
    public static final ErebusOre FOSSIL_ORE_VOLCANIC_DESERT;

    static {
        COAL_ORE = new ErebusOre("coal_ore", orePlacement(6));
        IRON_ORE = new ErebusOre("iron_ore", orePlacement(7));
        GOLD_ORE = new ErebusOre("gold_ore", orePlacement(4));
        LAPIS_ORE = new ErebusOre("lapis_ore", orePlacement(3));
        EMERALD_ORE =  new ErebusOre("emerald_ore", orePlacement(3));
        DIAMOND_ORE = new ErebusOre("diamond_ore", rareOrePlacement(2));
        JADE_ORE = new ErebusOre("jade_ore",  rareOrePlacement(2));
        PETRIFIED_WOOD_ORE = new ErebusOre("petrified_wood_ore", orePlacement(3));
        FOSSIL_ORE = new ErebusOre("fossil_ore", rareOrePlacement(4));
        ALUMINUM_ORE = new ErebusOre("aluminum_ore", orePlacement(2));
        COPPER_ORE = new ErebusOre("copper_ore", orePlacement(2));
        LEAD_ORE = new ErebusOre("lead_ore", orePlacement(2));
        SILVER_ORE = new ErebusOre("silver_ore", orePlacement(2));
        TIN_ORE = new ErebusOre("tin_ore", orePlacement(2));
        QUARTZ_ORE = new ErebusOre("quartz_ore", orePlacement(3));
        GNEISS_ORE = new ErebusOre("gneiss_ore", orePlacement(3));
        TEMPLE_ORE = new ErebusOre("temple_ore", orePlacement(3));

        COAL_ORE_ELYSIAN_FIELDS = new ErebusOre("coal_ore_elysian_fields", orePlacement(3, 5, 48));
        IRON_ORE_ELYSIAN_FIELDS = new ErebusOre("iron_ore_elysian_fields", orePlacement(4, 5, 42));
        GOLD_ORE_ELYSIAN_FIELDS = new ErebusOre("gold_ore_elysian_fields", orePlacement(2, 5, 48));
        EMERALD_ORE_ELYSIAN_FIELDS = new ErebusOre("emerald_ore_elysian_fields", orePlacement(3));
        JADE_ORE_ELYSIAN_FIELDS = new ErebusOre("jade_ore_elysian_fields", orePlacement(3));
        FOSSIL_ORE_ELYSIAN_FIELDS = new ErebusOre("fossil_ore_elysian_fields", rareOrePlacement(4));

        COAL_ORE_FUNGAL_FOREST = new ErebusOre("coal_ore_fungal_forest", orePlacement(3, 5, 48));
        IRON_ORE_FUNGAL_FOREST = new ErebusOre("iron_ore_fungal_forest", orePlacement(3, 5, 42));
        GOLD_ORE_FUNGAL_FOREST = new ErebusOre("gold_ore_fungal_forest", orePlacement(2, 5, 48));
        EMERALD_ORE_FUNGAL_FOREST = new ErebusOre("emerald_ore_fungal_forest", orePlacement(3));
        JADE_ORE_FUNGAL_FOREST = new ErebusOre("jade_ore_fungal_forest", orePlacement(3));
        FOSSIL_ORE_FUNGAL_FOREST = new ErebusOre("fossil_ore_fungal_forest", rareOrePlacement(4));

        GOLD_ORE_PETRIFIED_FOREST = new ErebusOre("gold_ore_petrified_forest", orePlacement(2));
        ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST = new ErebusOre("encrusted_diamond_ore_petrified_forest", rareOrePlacement(2, 5, 16));
        JADE_ORE_PETRIFIED_FOREST = new ErebusOre("jade_ore_petrified_forest", orePlacement(1));
        FOSSIL_ORE_PETRIFIED_FOREST = new ErebusOre("fossil_ore_petrified_forest", rareOrePlacement(4));

        COAL_ORE_SUBMERGED_SWAMP = new ErebusOre("coal_ore_submerged_swamp", orePlacement(3, 5, 56));
        EMERALD_ORE_SUBMERGED_SWAMP = new ErebusOre("emerald_ore_submerged_swamp", orePlacement(2));
        DIAMOND_ORE_SUBMERGED_SWAMP = new ErebusOre("diamond_ore_submerged_swamp", orePlacement(3));
        PETRIFIED_WOOD_ORE_SUBMERGED_SWAMP = new ErebusOre("petrified_wood_ore_submerged_swamp", orePlacement(2, 20, 64));
        FOSSIL_ORE_SUBMERGED_SWAMP = new ErebusOre("fossil_ore_submerged_swamp", rareOrePlacement(4));

        GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH = new ErebusOre("gold_ore_subterranean_savannah_high", rareOrePlacement(4, 50, 126));
        GOLD_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("gold_ore_subterranean_savannah", rareOrePlacement(2, 25, 50));
        GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW = new ErebusOre("gold_ore_subterranean_savannah_low", orePlacement(3, 5, 25));
        EMERALD_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("emerald_ore_subterranean_savannah", rareOrePlacement(4));
        DIAMOND_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("diamond_ore_subterranean_savannah", rareOrePlacement(2, 5, 16));
        JADE_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("jade_ore_subterranean_savannah", rareOrePlacement(3));
        PETRIFIED_WOOD_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("petrified_wood_ore_subterranean_savannah", orePlacement(2, 5, 64));

        COAL_ORE_ULTERIOR_OUTBACK = new ErebusOre("coal_ore_ulterior_outback", orePlacement(3, 5, 56));
        EMERALD_ORE_ULTERIOR_OUTBACK = new ErebusOre("emerald_ore_ulterior_outback", orePlacement(4));
        DIAMOND_ORE_ULTERIOR_OUTBACK = new ErebusOre("diamond_ore_ulterior_outback", orePlacement(3));
        PETRIFIED_WOOD_ORE_ULTERIOR_OUTBACK = new ErebusOre("petrified_wood_ore_ulterior_outback", orePlacement(5, 20, 64));
        FOSSIL_ORE_ULTERIOR_OUTBACK = new ErebusOre("fossil_ore_ulterior_outback", rareOrePlacement(4));

        COAL_ORE_UNDERGROUND_JUNGLE = new ErebusOre("coal_ore_underground_jungle", orePlacement(4, 27, 48));
        COAL_ORE_UNDERGROUND_JUNGLE_LARGE = new ErebusOre("coal_ore_underground_jungle_large", orePlacement(6, 6, 24));
        DIAMOND_ORE_UNDERGROUND_JUNGLE = new ErebusOre("diamond_ore_underground_jungle", rareOrePlacement(10, 6, 16));
        JADE_ORE_UNDERGROUND_JUNGLE = new ErebusOre("jade_ore_underground_jungle", orePlacement(2));
        PETRIFIED_WOOD_ORE_UNDERGROUND_JUNGLE = new ErebusOre("petrified_wood_ore_underground_jungle", rareOrePlacement(4, 6, 64));
        FOSSIL_ORE_UNDERGROUND_JUNGLE = new ErebusOre("fossil_ore_underground_jungle", rareOrePlacement(4));

        GOLD_ORE_VOLCANIC_DESERT = new ErebusOre("gold_ore_volcanic_desert", orePlacement(3));
        ENCRUSTED_DIAMOND_ORE_VOLCANIC_DESERT = new ErebusOre("encrusted_diamond_ore_volcanic_desert", orePlacement(2, 5, 16));
        JADE_ORE_VOLCANIC_DESERT = new ErebusOre("jade_ore_volcanic_desert", orePlacement(1));
        FOSSIL_ORE_VOLCANIC_DESERT = new ErebusOre("fossil_ore_volcanic_desert", orePlacement(1));
    }

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        registerConfiguredOre(COAL_ORE, ModBlocks.ORE_COAL, COAL_VEIN_SIZE);
        registerConfiguredOre(IRON_ORE, ModBlocks.ORE_IRON, IRON_VEIN_SIZE);
        registerConfiguredOre(GOLD_ORE, ModBlocks.ORE_GOLD, GOLD_VEIN_SIZE);
        registerConfiguredOre(LAPIS_ORE, ModBlocks.ORE_LAPIS, LAPIS_VEIN_SIZE);
        registerConfiguredOre(EMERALD_ORE, ModBlocks.ORE_EMERALD, EMERALD_VEIN_SIZE);
        registerConfiguredOre(DIAMOND_ORE, ModBlocks.ORE_DIAMOND, DIAMOND_VEIN_SIZE);
        registerConfiguredOre(JADE_ORE, ModBlocks.ORE_JADE, JADE_VEIN_SIZE);
        registerConfiguredOre(PETRIFIED_WOOD_ORE, ModBlocks.ORE_PETRIFIED_WOOD, PETRIFIED_WOOD_VEIN_SIZE);
        registerConfiguredOre(FOSSIL_ORE, ModBlocks.ORE_FOSSIL, FOSSIL_VEIN_SIZE);
        registerConfiguredOre(ALUMINUM_ORE, ModBlocks.ORE_ALUMINUM, ALUMINUM_VEIN_SIZE);
        registerConfiguredOre(COPPER_ORE, ModBlocks.ORE_COPPER, COPPER_VEIN_SIZE);
        registerConfiguredOre(LEAD_ORE, ModBlocks.ORE_LEAD, LEAD_VEIN_SIZE);
        registerConfiguredOre(SILVER_ORE, ModBlocks.ORE_SILVER, SILVER_VEIN_SIZE);
        registerConfiguredOre(TIN_ORE, ModBlocks.ORE_TIN, TIN_VEIN_SIZE);
        registerConfiguredOre(QUARTZ_ORE, ModBlocks.ORE_QUARTZ, QUARTZ_VEIN_SIZE);
        registerConfiguredOre(GNEISS_ORE, ModBlocks.ORE_GNEISS, GNEISS_VEIN_SIZE);
        registerConfiguredOre(TEMPLE_ORE, ModBlocks.ORE_TEMPLE, TEMPLE_VEIN_SIZE);

        registerConfiguredOre(COAL_ORE_ELYSIAN_FIELDS, ModBlocks.ORE_COAL, COAL_VEIN_SIZE);
        registerConfiguredOre(IRON_ORE_ELYSIAN_FIELDS, ModBlocks.ORE_IRON, IRON_VEIN_SIZE);
        registerConfiguredOre(GOLD_ORE_ELYSIAN_FIELDS, ModBlocks.ORE_GOLD, GOLD_VEIN_SIZE);
        registerConfiguredOre(EMERALD_ORE_ELYSIAN_FIELDS, ModBlocks.ORE_EMERALD, EMERALD_VEIN_SIZE);
        registerConfiguredOre(JADE_ORE_ELYSIAN_FIELDS, ModBlocks.ORE_JADE, JADE_VEIN_SIZE);
        registerConfiguredOre(FOSSIL_ORE_ELYSIAN_FIELDS, ModBlocks.ORE_FOSSIL, 6);

        registerConfiguredOre(COAL_ORE_FUNGAL_FOREST, ModBlocks.ORE_COAL, COAL_VEIN_SIZE);
        registerConfiguredOre(IRON_ORE_FUNGAL_FOREST, ModBlocks.ORE_IRON, IRON_VEIN_SIZE);
        registerConfiguredOre(GOLD_ORE_FUNGAL_FOREST, ModBlocks.ORE_GOLD, GOLD_VEIN_SIZE);
        registerConfiguredOre(EMERALD_ORE_FUNGAL_FOREST, ModBlocks.ORE_EMERALD, EMERALD_VEIN_SIZE);
        registerConfiguredOre(JADE_ORE_FUNGAL_FOREST, ModBlocks.ORE_JADE, JADE_VEIN_SIZE);
        registerConfiguredOre(FOSSIL_ORE_FUNGAL_FOREST, ModBlocks.ORE_FOSSIL, 6);

        registerConfiguredOre(GOLD_ORE_PETRIFIED_FOREST, ModBlocks.ORE_GOLD, GOLD_VEIN_SIZE);
        registerConfiguredOre(ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST, ModBlocks.ORE_DIAMOND, 2);
        registerConfiguredOre(JADE_ORE_PETRIFIED_FOREST, ModBlocks.ORE_JADE, JADE_VEIN_SIZE);
        registerConfiguredOre(FOSSIL_ORE_PETRIFIED_FOREST, ModBlocks.ORE_FOSSIL, FOSSIL_VEIN_SIZE);

        registerConfiguredOre(COAL_ORE_SUBMERGED_SWAMP, ModBlocks.ORE_COAL, 8);
        registerConfiguredOre(EMERALD_ORE_SUBMERGED_SWAMP, ModBlocks.ORE_EMERALD, EMERALD_VEIN_SIZE);
        registerConfiguredOre(DIAMOND_ORE_SUBMERGED_SWAMP, ModBlocks.ORE_DIAMOND, DIAMOND_VEIN_SIZE);
        registerConfiguredOre(PETRIFIED_WOOD_ORE_SUBMERGED_SWAMP, ModBlocks.ORE_PETRIFIED_WOOD, PETRIFIED_WOOD_VEIN_SIZE);
        registerConfiguredOre(FOSSIL_ORE_SUBMERGED_SWAMP, ModBlocks.ORE_FOSSIL, FOSSIL_VEIN_SIZE);

        registerConfiguredOre(GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH, ModBlocks.ORE_GOLD, GOLD_VEIN_SIZE);
        registerConfiguredOre(GOLD_ORE_SUBTERRANEAN_SAVANNAH, ModBlocks.ORE_GOLD, GOLD_VEIN_SIZE);
        registerConfiguredOre(GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW, ModBlocks.ORE_GOLD, GOLD_VEIN_SIZE);
        registerConfiguredOre(EMERALD_ORE_SUBTERRANEAN_SAVANNAH, ModBlocks.ORE_EMERALD, EMERALD_VEIN_SIZE);
        registerConfiguredOre(DIAMOND_ORE_SUBTERRANEAN_SAVANNAH, ModBlocks.ORE_DIAMOND, 2);
        registerConfiguredOre(JADE_ORE_SUBTERRANEAN_SAVANNAH, ModBlocks.ORE_JADE, JADE_VEIN_SIZE);
        registerConfiguredOre(PETRIFIED_WOOD_ORE_SUBTERRANEAN_SAVANNAH, ModBlocks.ORE_PETRIFIED_WOOD, PETRIFIED_WOOD_VEIN_SIZE);

        registerConfiguredOre(COAL_ORE_ULTERIOR_OUTBACK, ModBlocks.ORE_COAL, 8);
        registerConfiguredOre(EMERALD_ORE_ULTERIOR_OUTBACK, ModBlocks.ORE_EMERALD, EMERALD_VEIN_SIZE);
        registerConfiguredOre(DIAMOND_ORE_ULTERIOR_OUTBACK, ModBlocks.ORE_DIAMOND, DIAMOND_VEIN_SIZE);
        registerConfiguredOre(PETRIFIED_WOOD_ORE_ULTERIOR_OUTBACK, ModBlocks.ORE_PETRIFIED_WOOD, PETRIFIED_WOOD_VEIN_SIZE);
        registerConfiguredOre(FOSSIL_ORE_ULTERIOR_OUTBACK, ModBlocks.ORE_FOSSIL, FOSSIL_VEIN_SIZE);

        registerConfiguredOre(COAL_ORE_UNDERGROUND_JUNGLE, ModBlocks.ORE_COAL, COAL_VEIN_SIZE);
        registerConfiguredOre(COAL_ORE_UNDERGROUND_JUNGLE_LARGE, ModBlocks.ORE_COAL, 13);
        registerConfiguredOre(DIAMOND_ORE_UNDERGROUND_JUNGLE, ModBlocks.ORE_DIAMOND, DIAMOND_VEIN_SIZE);
        registerConfiguredOre(JADE_ORE_UNDERGROUND_JUNGLE, ModBlocks.ORE_JADE, 5);
        registerConfiguredOre(PETRIFIED_WOOD_ORE_UNDERGROUND_JUNGLE, ModBlocks.ORE_PETRIFIED_WOOD, PETRIFIED_WOOD_VEIN_SIZE);
        registerConfiguredOre(FOSSIL_ORE_UNDERGROUND_JUNGLE, ModBlocks.ORE_FOSSIL, FOSSIL_VEIN_SIZE);

        registerConfiguredOre(GOLD_ORE_VOLCANIC_DESERT, ModBlocks.ORE_GOLD, GOLD_VEIN_SIZE);
        registerConfiguredOre(ENCRUSTED_DIAMOND_ORE_VOLCANIC_DESERT, ModBlocks.ORE_DIAMOND, 2);
        registerConfiguredOre(JADE_ORE_VOLCANIC_DESERT, ModBlocks.ORE_JADE, JADE_VEIN_SIZE);
        registerConfiguredOre(FOSSIL_ORE_VOLCANIC_DESERT, ModBlocks.ORE_FOSSIL, FOSSIL_VEIN_SIZE);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(COAL_ORE);
        registerPlacedFeature(IRON_ORE);
        registerPlacedFeature(GOLD_ORE);
        registerPlacedFeature(LAPIS_ORE);
        registerPlacedFeature(EMERALD_ORE);
        registerPlacedFeature(DIAMOND_ORE);
        registerPlacedFeature(JADE_ORE);
        registerPlacedFeature(PETRIFIED_WOOD_ORE);
        registerPlacedFeature(FOSSIL_ORE);
        registerPlacedFeature(ALUMINUM_ORE);
        registerPlacedFeature(COPPER_ORE);
        registerPlacedFeature(LEAD_ORE);
        registerPlacedFeature(SILVER_ORE);
        registerPlacedFeature(TIN_ORE);
        registerPlacedFeature(QUARTZ_ORE);
        registerPlacedFeature(GNEISS_ORE);
        registerPlacedFeature(TEMPLE_ORE);

        registerPlacedFeature(COAL_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(IRON_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(GOLD_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(EMERALD_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(JADE_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(FOSSIL_ORE_ELYSIAN_FIELDS);

        registerPlacedFeature(COAL_ORE_FUNGAL_FOREST);
        registerPlacedFeature(IRON_ORE_FUNGAL_FOREST);
        registerPlacedFeature(GOLD_ORE_FUNGAL_FOREST);
        registerPlacedFeature(EMERALD_ORE_FUNGAL_FOREST);
        registerPlacedFeature(JADE_ORE_FUNGAL_FOREST);
        registerPlacedFeature(FOSSIL_ORE_FUNGAL_FOREST);

        registerPlacedFeature(GOLD_ORE_PETRIFIED_FOREST);
        registerPlacedFeature(ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST);
        registerPlacedFeature(JADE_ORE_PETRIFIED_FOREST);
        registerPlacedFeature(FOSSIL_ORE_PETRIFIED_FOREST);

        registerPlacedFeature(COAL_ORE_SUBMERGED_SWAMP);
        registerPlacedFeature(EMERALD_ORE_SUBMERGED_SWAMP);
        registerPlacedFeature(DIAMOND_ORE_SUBMERGED_SWAMP);
        registerPlacedFeature(PETRIFIED_WOOD_ORE_SUBMERGED_SWAMP);
        registerPlacedFeature(FOSSIL_ORE_SUBMERGED_SWAMP);

        registerPlacedFeature(GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH);
        registerPlacedFeature(GOLD_ORE_SUBTERRANEAN_SAVANNAH);
        registerPlacedFeature(GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW);
        registerPlacedFeature(EMERALD_ORE_SUBTERRANEAN_SAVANNAH);
        registerPlacedFeature(DIAMOND_ORE_SUBTERRANEAN_SAVANNAH);
        registerPlacedFeature(JADE_ORE_SUBTERRANEAN_SAVANNAH);
        registerPlacedFeature(PETRIFIED_WOOD_ORE_SUBTERRANEAN_SAVANNAH);

        registerPlacedFeature(COAL_ORE_ULTERIOR_OUTBACK);
        registerPlacedFeature(EMERALD_ORE_ULTERIOR_OUTBACK);
        registerPlacedFeature(DIAMOND_ORE_ULTERIOR_OUTBACK);
        registerPlacedFeature(PETRIFIED_WOOD_ORE_ULTERIOR_OUTBACK);
        registerPlacedFeature(FOSSIL_ORE_ULTERIOR_OUTBACK);

        registerPlacedFeature(COAL_ORE_UNDERGROUND_JUNGLE);
        registerPlacedFeature(COAL_ORE_UNDERGROUND_JUNGLE_LARGE);
        registerPlacedFeature(DIAMOND_ORE_UNDERGROUND_JUNGLE);
        registerPlacedFeature(JADE_ORE_UNDERGROUND_JUNGLE);
        registerPlacedFeature(PETRIFIED_WOOD_ORE_UNDERGROUND_JUNGLE);
        registerPlacedFeature(FOSSIL_ORE_UNDERGROUND_JUNGLE);

        registerPlacedFeature(GOLD_ORE_VOLCANIC_DESERT);
        registerPlacedFeature(ENCRUSTED_DIAMOND_ORE_VOLCANIC_DESERT);
        registerPlacedFeature(JADE_ORE_VOLCANIC_DESERT);
        registerPlacedFeature(FOSSIL_ORE_VOLCANIC_DESERT);
    }
}
