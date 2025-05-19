package erebus.registries.world.feature;

import erebus.registries.blocks.providers.OreBlocks;
import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.ErebusOre;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static erebus.world.ModOrePlacement.orePlacement;
import static erebus.world.ModOrePlacement.rareOrePlacement;

public class OreFeatures extends ModFeatureHelpers {

    public static final ErebusOre COAL_ORE;
    public static final ErebusOre IRON_ORE;
    public static final ErebusOre GOLD_ORE;
    public static final ErebusOre LAPIS_ORE;
    public static final ErebusOre EMERALD_ORE;
    public static final ErebusOre DIAMOND_ORE;
    public static final ErebusOre ENCRUSTED_DIAMOND_ORE;
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
    public static final ErebusOre PETRIFIED_QUARTZ_ORE;
    public static final ErebusOre TEMPLE_ORE;

    public static final ErebusOre COAL_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre IRON_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre GOLD_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre EMERALD_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre PETRIFIED_WOOD_ORE_ELYSIAN_FIELDS;
    public static final ErebusOre FOSSIL_ORE_ELYSIAN_FIELDS;

    public static final ErebusOre COAL_ORE_FUNGAL_FOREST;
    public static final ErebusOre IRON_ORE_FUNGAL_FOREST;
    public static final ErebusOre GOLD_ORE_FUNGAL_FOREST;
    public static final ErebusOre EMERALD_ORE_FUNGAL_FOREST;
    public static final ErebusOre JADE_ORE_FUNGAL_FOREST;
    public static final ErebusOre PETRIFIED_WOOD_ORE_FUNGAL_FOREST;
    public static final ErebusOre FOSSIL_ORE_FUNGAL_FOREST;

    public static final ErebusOre GOLD_ORE_PETRIFIED_FOREST;
    public static final ErebusOre ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST;
    public static final ErebusOre JADE_ORE_PETRIFIED_FOREST;
    public static final ErebusOre FOSSIL_ORE_PETRIFIED_FOREST;

    public static final ErebusOre COAL_ORE_SUBMERGED_SWAMP;
    public static final ErebusOre EMERALD_ORE_SUBMERGED_SWAMP;
    public static final ErebusOre DIAMOND_ORE_SUBMERGED_SWAMP;
    public static final ErebusOre PETRIFIED_WOOD_SUBMERGED_SWAMP;
    public static final ErebusOre FOSSIL_ORE_SUBMERGED_SWAMP;

    public static final ErebusOre GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH;
    public static final ErebusOre GOLD_ORE_SUBTERRANEAN_SAVANNAH;
    public static final ErebusOre GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW;
    public static final ErebusOre EMERALD_ORE_SUBTERRANEAN_SAVANNAH;
    public static final ErebusOre DIAMOND_ORE_SUBTERRANEAN_SAVANNAH;
    public static final ErebusOre JADE_ORE_SUBTERRANEAN_SAVANNAH;
    public static final ErebusOre PETRIFIED_WOOD_SUBTERRANEAN_SAVANNAH;

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
        COAL_ORE = new ErebusOre("coal_ore", orePlacement(6, 5, 112));
        IRON_ORE = new ErebusOre("iron_ore", orePlacement(7, 5, 112));
        GOLD_ORE = new ErebusOre("gold_ore", orePlacement(4, 5, 112));
        LAPIS_ORE = new ErebusOre("lapis_ore", orePlacement(3, 5, 112));
        EMERALD_ORE =  new ErebusOre("emerald_ore", orePlacement(3, 5, 112));
        DIAMOND_ORE = new ErebusOre("diamond_ore", rareOrePlacement(2, 5, 112));
        ENCRUSTED_DIAMOND_ORE = new ErebusOre("encrusted_diamond_ore", orePlacement(0, 5, 112));
        JADE_ORE = new ErebusOre("jade_ore",  rareOrePlacement(2, 5, 112));
        PETRIFIED_WOOD_ORE = new ErebusOre("petrified_wood_ore", orePlacement(3, 5, 112));
        FOSSIL_ORE = new ErebusOre("fossil_ore", rareOrePlacement(4, 5, 112));
        ALUMINUM_ORE = new ErebusOre("aluminum_ore", orePlacement(2, 5, 112));
        COPPER_ORE = new ErebusOre("copper_ore", orePlacement(2, 5, 112));
        LEAD_ORE = new ErebusOre("lead_ore", orePlacement(2, 5, 112));
        SILVER_ORE = new ErebusOre("silver_ore", orePlacement(2, 5, 112));
        TIN_ORE = new ErebusOre("tin_ore", orePlacement(2, 5, 112));
        QUARTZ_ORE = new ErebusOre("quartz_ore", orePlacement(3, 5, 112));
        GNEISS_ORE = new ErebusOre("gneiss_ore", orePlacement(3, 5, 112));
        PETRIFIED_QUARTZ_ORE = new ErebusOre("petrified_quartz_ore", orePlacement(0, 5, 112));
        TEMPLE_ORE = new ErebusOre("temple_ore", orePlacement(3, 5, 112));

        COAL_ORE_ELYSIAN_FIELDS = new ErebusOre("coal_ore_elysian_fields", orePlacement(2, 5, 48));
        IRON_ORE_ELYSIAN_FIELDS = new ErebusOre("iron_ore_elysian_fields", orePlacement(2, 5, 48));
        GOLD_ORE_ELYSIAN_FIELDS = new ErebusOre("gold_ore_elysian_fields", orePlacement(2, 5, 48));
        EMERALD_ORE_ELYSIAN_FIELDS = new ErebusOre("emerald_ore_elysian_fields", orePlacement(2, 5, 48));
        PETRIFIED_WOOD_ORE_ELYSIAN_FIELDS = new ErebusOre("petrified_wood_ore_elysian_fields", orePlacement(2, 5, 48));
        FOSSIL_ORE_ELYSIAN_FIELDS = new ErebusOre("fossil_ore_elysian_fields", orePlacement(2, 5, 48));

        COAL_ORE_FUNGAL_FOREST = new ErebusOre("coal_ore_fungal_forest", orePlacement(2, 5, 48));
        IRON_ORE_FUNGAL_FOREST = new ErebusOre("iron_ore_fungal_forest", orePlacement(2, 5, 48));
        GOLD_ORE_FUNGAL_FOREST = new ErebusOre("gold_ore_fungal_forest", orePlacement(2, 5, 48));
        EMERALD_ORE_FUNGAL_FOREST = new ErebusOre("emerald_ore_fungal_forest", orePlacement(2, 5, 48));
        JADE_ORE_FUNGAL_FOREST = new ErebusOre("jade_ore_fungal_forest", orePlacement(2, 5, 48));
        PETRIFIED_WOOD_ORE_FUNGAL_FOREST = new ErebusOre("petrified_wood_ore_fungal_forest", orePlacement(2, 5, 48));
        FOSSIL_ORE_FUNGAL_FOREST = new ErebusOre("fossil_ore_fungal_forest", orePlacement(2, 5, 48));

        GOLD_ORE_PETRIFIED_FOREST = new ErebusOre("gold_ore_petrified_forest", orePlacement(2, 5, 48));
        ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST = new ErebusOre("encrusted_diamond_ore_petrified_forest", orePlacement(2, 5, 48));
        JADE_ORE_PETRIFIED_FOREST = new ErebusOre("jade_ore_petrified_forest", orePlacement(2, 5, 48));
        FOSSIL_ORE_PETRIFIED_FOREST = new ErebusOre("fossil_ore_petrified_forest", orePlacement(2, 5, 48));

        COAL_ORE_SUBMERGED_SWAMP = new ErebusOre("coal_ore_submerged_swamp", orePlacement(2, 5, 48));
        EMERALD_ORE_SUBMERGED_SWAMP = new ErebusOre("emerald_ore_submerged_swamp", orePlacement(2, 5, 48));
        DIAMOND_ORE_SUBMERGED_SWAMP = new ErebusOre("diamond_ore_submerged_swamp", orePlacement(2, 5, 48));
        PETRIFIED_WOOD_SUBMERGED_SWAMP = new ErebusOre("petrified_wood_ore_submerged_swamp", orePlacement(2, 5, 48));
        FOSSIL_ORE_SUBMERGED_SWAMP = new ErebusOre("fossil_ore_submerged_swamp", orePlacement(2, 5, 48));

        GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH = new ErebusOre("gold_ore_subterranean_savannah_high", orePlacement(2, 5, 48));
        GOLD_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("gold_ore_subterranean_savannah", orePlacement(2, 5, 48));
        GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW = new ErebusOre("gold_ore_subterranean_savannah_low", orePlacement(2, 5, 48));
        EMERALD_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("emerald_ore_subterranean_savannah", orePlacement(2, 5, 48));
        DIAMOND_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("diamond_ore_subterranean_savannah", orePlacement(2, 5, 48));
        JADE_ORE_SUBTERRANEAN_SAVANNAH = new ErebusOre("jade_ore_subterranean_savannah", orePlacement(2, 5, 48));
        PETRIFIED_WOOD_SUBTERRANEAN_SAVANNAH = new ErebusOre("petrified_wood_ore_subterranean_savannah", orePlacement(2, 5, 48));

        COAL_ORE_ULTERIOR_OUTBACK = new ErebusOre("coal_ore_ulterior_outback", orePlacement(2, 5, 48));
        EMERALD_ORE_ULTERIOR_OUTBACK = new ErebusOre("emerald_ore_ulterior_outback", orePlacement(2, 5, 48));
        DIAMOND_ORE_ULTERIOR_OUTBACK = new ErebusOre("diamond_ore_ulterior_outback", orePlacement(2, 5, 48));
        PETRIFIED_WOOD_ORE_ULTERIOR_OUTBACK = new ErebusOre("petrified_wood_ore_ulterior_outback", orePlacement(2, 5, 48));
        FOSSIL_ORE_ULTERIOR_OUTBACK = new ErebusOre("fossil_ore_ulterior_outback", orePlacement(2, 5, 48));

        COAL_ORE_UNDERGROUND_JUNGLE = new ErebusOre("coal_ore_underground_jungle", orePlacement(2, 5, 48));
        COAL_ORE_UNDERGROUND_JUNGLE_LARGE = new ErebusOre("coal_ore_underground_jungle_large", orePlacement(2, 5, 48));
        DIAMOND_ORE_UNDERGROUND_JUNGLE = new ErebusOre("diamond_ore_underground_jungle", orePlacement(2, 5, 48));
        JADE_ORE_UNDERGROUND_JUNGLE = new ErebusOre("jade_ore_underground_jungle", orePlacement(2, 5, 48));
        PETRIFIED_WOOD_ORE_UNDERGROUND_JUNGLE = new ErebusOre("petrified_wood_ore_underground_jungle", orePlacement(2, 5, 48));
        FOSSIL_ORE_UNDERGROUND_JUNGLE = new ErebusOre("fossil_ore_underground_jungle", orePlacement(2, 5, 48));

        GOLD_ORE_VOLCANIC_DESERT = new ErebusOre("gold_ore_volcanic_desert", orePlacement(2, 5, 48));
        ENCRUSTED_DIAMOND_ORE_VOLCANIC_DESERT = new ErebusOre("encrusted_diamond_ore_volcanic_desert", orePlacement(2, 5, 48));
        JADE_ORE_VOLCANIC_DESERT = new ErebusOre("jade_ore_volcanic_desert", orePlacement(2, 5, 48));
        FOSSIL_ORE_VOLCANIC_DESERT = new ErebusOre("fossil_ore_volcanic_desert", orePlacement(2, 5, 48));
    }

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        registerConfiguredOre(COAL_ORE, OreBlocks.ORE_COAL, 10);
        registerConfiguredOre(IRON_ORE, OreBlocks.ORE_IRON, 7);
        registerConfiguredOre(GOLD_ORE, OreBlocks.ORE_GOLD, 6);
        registerConfiguredOre(LAPIS_ORE, OreBlocks.ORE_LAPIS, 5);
        registerConfiguredOre(EMERALD_ORE, OreBlocks.ORE_EMERALD, 3);
        registerConfiguredOre(DIAMOND_ORE, OreBlocks.ORE_DIAMOND, 1);
        registerConfiguredOre(ENCRUSTED_DIAMOND_ORE, OreBlocks.ORE_DIAMOND, 1);
        registerConfiguredOre(JADE_ORE, OreBlocks.ORE_JADE, 4);
        registerConfiguredOre(PETRIFIED_WOOD_ORE, OreBlocks.ORE_PETRIFIED_WOOD, 8);
        registerConfiguredOre(FOSSIL_ORE, OreBlocks.ORE_FOSSIL, 10);
        registerConfiguredOre(ALUMINUM_ORE, OreBlocks.ORE_ALUMINUM, 4);
        registerConfiguredOre(COPPER_ORE, OreBlocks.ORE_COPPER, 6);
        registerConfiguredOre(LEAD_ORE, OreBlocks.ORE_LEAD, 3);
        registerConfiguredOre(SILVER_ORE, OreBlocks.ORE_SILVER, 3);
        registerConfiguredOre(TIN_ORE, OreBlocks.ORE_TIN, 3);
        registerConfiguredOre(QUARTZ_ORE, OreBlocks.ORE_QUARTZ, 8);
        registerConfiguredOre(PETRIFIED_QUARTZ_ORE, OreBlocks.ORE_QUARTZ, 1);
        registerConfiguredOre(GNEISS_ORE, OreBlocks.ORE_GNEISS, 3);
        registerConfiguredOre(TEMPLE_ORE, OreBlocks.ORE_TEMPLE, 8);

        registerConfiguredOre(COAL_ORE_ELYSIAN_FIELDS, OreBlocks.ORE_COAL, 4);
        registerConfiguredOre(IRON_ORE_ELYSIAN_FIELDS, OreBlocks.ORE_IRON, 4);
        registerConfiguredOre(GOLD_ORE_ELYSIAN_FIELDS, OreBlocks.ORE_GOLD, 4);
        registerConfiguredOre(EMERALD_ORE_ELYSIAN_FIELDS, OreBlocks.ORE_EMERALD, 2);
        registerConfiguredOre(PETRIFIED_WOOD_ORE_ELYSIAN_FIELDS, OreBlocks.ORE_PETRIFIED_WOOD, 4);
        registerConfiguredOre(FOSSIL_ORE_ELYSIAN_FIELDS, OreBlocks.ORE_FOSSIL, 4);

        registerConfiguredOre(COAL_ORE_FUNGAL_FOREST, OreBlocks.ORE_COAL, 4);
        registerConfiguredOre(IRON_ORE_FUNGAL_FOREST, OreBlocks.ORE_IRON, 4);
        registerConfiguredOre(GOLD_ORE_FUNGAL_FOREST, OreBlocks.ORE_GOLD, 4);
        registerConfiguredOre(EMERALD_ORE_FUNGAL_FOREST, OreBlocks.ORE_EMERALD, 2);
        registerConfiguredOre(JADE_ORE_FUNGAL_FOREST, OreBlocks.ORE_JADE, 2);
        registerConfiguredOre(PETRIFIED_WOOD_ORE_FUNGAL_FOREST, OreBlocks.ORE_PETRIFIED_WOOD, 4);
        registerConfiguredOre(FOSSIL_ORE_FUNGAL_FOREST, OreBlocks.ORE_FOSSIL, 4);

        registerConfiguredOre(GOLD_ORE_PETRIFIED_FOREST, OreBlocks.ORE_GOLD, 4);
        registerConfiguredOre(ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST, OreBlocks.ORE_DIAMOND, 1);
        registerConfiguredOre(JADE_ORE_PETRIFIED_FOREST, OreBlocks.ORE_JADE, 2);
        registerConfiguredOre(FOSSIL_ORE_PETRIFIED_FOREST, OreBlocks.ORE_FOSSIL, 4);

        registerConfiguredOre(COAL_ORE_SUBMERGED_SWAMP, OreBlocks.ORE_COAL, 4);
        registerConfiguredOre(EMERALD_ORE_SUBMERGED_SWAMP, OreBlocks.ORE_EMERALD, 2);
        registerConfiguredOre(DIAMOND_ORE_SUBMERGED_SWAMP, OreBlocks.ORE_DIAMOND, 1);
        registerConfiguredOre(PETRIFIED_WOOD_SUBMERGED_SWAMP, OreBlocks.ORE_PETRIFIED_WOOD, 4);
        registerConfiguredOre(FOSSIL_ORE_SUBMERGED_SWAMP, OreBlocks.ORE_FOSSIL, 4);

        registerConfiguredOre(GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH, OreBlocks.ORE_GOLD, 4);
        registerConfiguredOre(GOLD_ORE_SUBTERRANEAN_SAVANNAH, OreBlocks.ORE_GOLD, 4);
        registerConfiguredOre(GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW, OreBlocks.ORE_GOLD, 4);
        registerConfiguredOre(EMERALD_ORE_SUBTERRANEAN_SAVANNAH, OreBlocks.ORE_EMERALD, 2);
        registerConfiguredOre(DIAMOND_ORE_SUBTERRANEAN_SAVANNAH, OreBlocks.ORE_DIAMOND, 1);
        registerConfiguredOre(JADE_ORE_SUBTERRANEAN_SAVANNAH, OreBlocks.ORE_JADE, 2);
        registerConfiguredOre(PETRIFIED_WOOD_SUBTERRANEAN_SAVANNAH, OreBlocks.ORE_PETRIFIED_WOOD, 4);

        registerConfiguredOre(COAL_ORE_ULTERIOR_OUTBACK, OreBlocks.ORE_COAL, 4);
        registerConfiguredOre(EMERALD_ORE_ULTERIOR_OUTBACK, OreBlocks.ORE_EMERALD, 2);
        registerConfiguredOre(DIAMOND_ORE_ULTERIOR_OUTBACK, OreBlocks.ORE_DIAMOND, 1);
        registerConfiguredOre(PETRIFIED_WOOD_ORE_ULTERIOR_OUTBACK, OreBlocks.ORE_PETRIFIED_WOOD, 4);
        registerConfiguredOre(FOSSIL_ORE_ULTERIOR_OUTBACK, OreBlocks.ORE_FOSSIL, 4);

        registerConfiguredOre(COAL_ORE_UNDERGROUND_JUNGLE, OreBlocks.ORE_COAL, 4);
        registerConfiguredOre(COAL_ORE_UNDERGROUND_JUNGLE_LARGE, OreBlocks.ORE_COAL, 8);
        registerConfiguredOre(DIAMOND_ORE_UNDERGROUND_JUNGLE, OreBlocks.ORE_DIAMOND, 1);
        registerConfiguredOre(JADE_ORE_UNDERGROUND_JUNGLE, OreBlocks.ORE_JADE, 2);
        registerConfiguredOre(PETRIFIED_WOOD_ORE_UNDERGROUND_JUNGLE, OreBlocks.ORE_PETRIFIED_WOOD, 4);
        registerConfiguredOre(FOSSIL_ORE_UNDERGROUND_JUNGLE, OreBlocks.ORE_FOSSIL, 4);

        registerConfiguredOre(GOLD_ORE_VOLCANIC_DESERT, OreBlocks.ORE_GOLD, 4);
        registerConfiguredOre(ENCRUSTED_DIAMOND_ORE_VOLCANIC_DESERT, OreBlocks.ORE_DIAMOND, 1);
        registerConfiguredOre(JADE_ORE_VOLCANIC_DESERT, OreBlocks.ORE_JADE, 2);
        registerConfiguredOre(FOSSIL_ORE_VOLCANIC_DESERT, OreBlocks.ORE_FOSSIL, 4);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(COAL_ORE);
        registerPlacedFeature(IRON_ORE);
        registerPlacedFeature(GOLD_ORE);
        registerPlacedFeature(LAPIS_ORE);
        registerPlacedFeature(EMERALD_ORE);
        registerPlacedFeature(DIAMOND_ORE);
        registerPlacedFeature(ENCRUSTED_DIAMOND_ORE);
        registerPlacedFeature(JADE_ORE);
        registerPlacedFeature(PETRIFIED_WOOD_ORE);
        registerPlacedFeature(FOSSIL_ORE);
        registerPlacedFeature(ALUMINUM_ORE);
        registerPlacedFeature(COPPER_ORE);
        registerPlacedFeature(LEAD_ORE);
        registerPlacedFeature(SILVER_ORE);
        registerPlacedFeature(TIN_ORE);
        registerPlacedFeature(QUARTZ_ORE);
        registerPlacedFeature(PETRIFIED_QUARTZ_ORE);
        registerPlacedFeature(GNEISS_ORE);
        registerPlacedFeature(TEMPLE_ORE);

        registerPlacedFeature(COAL_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(IRON_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(GOLD_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(EMERALD_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(PETRIFIED_WOOD_ORE_ELYSIAN_FIELDS);
        registerPlacedFeature(FOSSIL_ORE_ELYSIAN_FIELDS);

        registerPlacedFeature(COAL_ORE_FUNGAL_FOREST);
        registerPlacedFeature(IRON_ORE_FUNGAL_FOREST);
        registerPlacedFeature(GOLD_ORE_FUNGAL_FOREST);
        registerPlacedFeature(EMERALD_ORE_FUNGAL_FOREST);
        registerPlacedFeature(JADE_ORE_FUNGAL_FOREST);
        registerPlacedFeature(PETRIFIED_WOOD_ORE_FUNGAL_FOREST);
        registerPlacedFeature(FOSSIL_ORE_FUNGAL_FOREST);

        registerPlacedFeature(GOLD_ORE_PETRIFIED_FOREST);
        registerPlacedFeature(ENCRUSTED_DIAMOND_ORE_PETRIFIED_FOREST);
        registerPlacedFeature(JADE_ORE_PETRIFIED_FOREST);
        registerPlacedFeature(FOSSIL_ORE_PETRIFIED_FOREST);

        registerPlacedFeature(COAL_ORE_SUBMERGED_SWAMP);
        registerPlacedFeature(EMERALD_ORE_SUBMERGED_SWAMP);
        registerPlacedFeature(DIAMOND_ORE_SUBMERGED_SWAMP);
        registerPlacedFeature(PETRIFIED_WOOD_SUBMERGED_SWAMP);
        registerPlacedFeature(FOSSIL_ORE_SUBMERGED_SWAMP);

        registerPlacedFeature(GOLD_ORE_SUBTERRANEAN_SAVANNAH_HIGH);
        registerPlacedFeature(GOLD_ORE_SUBTERRANEAN_SAVANNAH);
        registerPlacedFeature(GOLD_ORE_SUBTERRANEAN_SAVANNAH_LOW);
        registerPlacedFeature(EMERALD_ORE_SUBTERRANEAN_SAVANNAH);
        registerPlacedFeature(DIAMOND_ORE_SUBTERRANEAN_SAVANNAH);
        registerPlacedFeature(JADE_ORE_SUBTERRANEAN_SAVANNAH);
        registerPlacedFeature(PETRIFIED_WOOD_SUBTERRANEAN_SAVANNAH);

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
