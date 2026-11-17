package erebus.datagen.providers.recipes;

import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import static net.minecraft.data.recipes.RecipeCategory.*;

/**
 * Provider for miscellaneous shaped crafting recipes.
 */
public class ShapedMiscRecipeProvider extends ErebusRecipeProvider {

    public ShapedMiscRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        addGliderWingRecipes();
        addBambooMiscRecipes();
        addFoodRecipes();
        addBlockRecipes();
        addGolemRecipes();
        addMiscItemRecipes();
        addPlantDyeRecipes();
    }

    private void addPlantDyeRecipes() {
        shaped(MISC, Items.GREEN_DYE)
                .pattern("SS")
                .pattern("SS")
                .define('S', ModBlocks.STEM)
                .unlockedBy("has_stem", has(ModBlocks.STEM))
                .save(output, "erebus:green_dye_from_stems");

        shaped(MISC, Items.GREEN_DYE)
                .pattern("LL")
                .pattern("LL")
                .define('L', ModBlocks.GIANT_LILY_PAD)
                .unlockedBy("has_giant_lily_pad", has(ModBlocks.GIANT_LILY_PAD))
                .save(output, "erebus:green_dye_from_giant_lily_pads");
    }

    private void addGliderWingRecipes() {
        shaped(MISC, ModItems.GLIDER_WING)
                .pattern("SSS")
                .pattern("WWW")
                .pattern("WWW")
                .define('S', Tags.Items.RODS_WOODEN)
                .define('W', ModItems.FLY_WING)
                .unlockedBy("has_fly_wing", has(ModItems.FLY_WING))
                .save(output);

        shaped(MISC, ModItems.ENHANCED_GLIDER_WING)
                .pattern("BBB")
                .pattern("WWW")
                .pattern("WWW")
                .define('B', ModItems.BAMBOO)
                .define('W', ModItems.DRAGONFLY_WING)
                .unlockedBy("has_dragonfly_wing", has(ModItems.DRAGONFLY_WING))
                .save(output);
    }

    private void addBambooMiscRecipes() {
        shaped(MISC, ModItems.BAMBUCKET)
                .pattern(" S ")
                .pattern("B B")
                .pattern(" B ")
                .define('S', Items.STRING)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        shaped(MISC, ModBlocks.BAMBOO_TORCH, 4)
                .pattern("C")
                .pattern("B")
                .pattern("B")
                .define('C', ItemTags.COALS)
                .define('B', ModItems.BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        shaped(MISC, ModBlocks.BAMBOO_CRATE)
                .pattern("BPB")
                .pattern("P P")
                .pattern("BPB")
                .define('B', ModItems.BAMBOO)
                .define('P', ModBlocks.PLANKS_BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        shaped(MISC, ModBlocks.BAMBOO_PIPE, 3)
                .pattern("  B")
                .pattern("HBS")
                .pattern("B  ")
                .define('B', ModItems.BAMBOO)
                .define('H', ModItems.HYDROFUGE)
                .define('S', Items.STRING)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);

        shaped(MISC, ModItems.BAMBOO_PIPE_WRENCH)
                .pattern("B B")
                .pattern(" P ")
                .pattern(" B ")
                .define('B', ModItems.BAMBOO)
                .define('P', ModBlocks.PLANKS_BAMBOO)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .save(output);
    }

    private void addFoodRecipes() {
        shaped(FOOD, ModItems.HONEY_SANDWICH, 2)
                .pattern(" B ")
                .pattern("HHH")
                .pattern(" B ")
                .define('B', Items.BREAD)
                .define('H', ModItems.HONEY_DRIP)
                .unlockedBy("has_honey_drip", has(ModItems.HONEY_DRIP))
                .save(output);

        shaped(FOOD, ModBlocks.HONEY_TREAT)
                .pattern("SHS")
                .pattern("HBH")
                .pattern("SHS")
                .define('S', Items.SUGAR)
                .define('H', ModItems.HONEY_DRIP)
                .define('B', Items.BREAD)
                .unlockedBy("has_honey_drip", has(ModItems.HONEY_DRIP))
                .save(output);
    }

    private void addBlockRecipes() {
        shaped(MISC, ModBlocks.GLOW_GEM_ACTIVE, 3)
                .pattern("BBB").pattern("BGB").pattern("BBB")
                .define('B', ModItems.BIO_LUMINESCENCE)
                .define('G', ModItems.RED_GEM)
                .unlockedBy("has_bio_luminescence", has(ModItems.BIO_LUMINESCENCE))
                .save(output);

        shaped(MISC, ModBlocks.BAMBOO_EXTENDER)
                .pattern("BSB").pattern("PDP").pattern("BRB")
                .define('B', ModItems.BAMBOO).define('S', Items.STRING).define('P', ModBlocks.PLANKS_BAMBOO)
                .define('D', Blocks.DISPENSER).define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO)).save(output);

        shaped(MISC, ModBlocks.LIQUIFIER)
                .pattern("RBR").pattern("SGS").pattern("RJR")
                .define('R', Tags.Items.DUSTS_REDSTONE).define('B', ModBlocks.PLANKS_BAMBOO)
                .define('S', Tags.Items.RODS_WOODEN).define('G', ModBlocks.AMBER_GLASS).define('J', ModBlocks.FLUID_JAR)
                .unlockedBy("has_fluid_jar", has(ModBlocks.FLUID_JAR)).save(output);

        shaped(MISC, ModBlocks.BLENDER)
                .pattern("GBG").pattern("GUG").pattern("UUU")
                .define('G', ModItems.SMOOTHIE_GLASS).define('B', Items.BUCKET).define('U', ModBlocks.UMBERSTONE)
                .unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS)).save(output);

        shaped(MISC, ModBlocks.GLOWING_JAR)
                .pattern("III")
                .pattern("GBG")
                .pattern("GGG")
                .define('B', ModItems.BIO_LUMINESCENCE)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', ModBlocks.AMBER_GLASS)
                .unlockedBy("has_bio_luminescence", has(ModItems.BIO_LUMINESCENCE))
                .save(output);

        threeByThree(ModItems.BIO_VELOCITY, ModBlocks.VELOCITY_BLOCK);

        shaped(MISC, ModBlocks.FLUID_JAR)
                .pattern("PPP")
                .pattern("GBG")
                .pattern("GGG")
                .define('P', ModBlocks.PLANKS_VARNISHED)
                .define('B', Items.BUCKET)
                .define('G', ModBlocks.AMBER_GLASS)
                .unlockedBy("has_amber_glass", has(ModBlocks.AMBER_GLASS))
                .save(output);

        shaped(MISC, ModBlocks.MUCUS_BOMB)
                .pattern("MMM").pattern("MTM").pattern("MMM")
                .define('M', ModItems.MUCUS_CHARGE).define('T', Blocks.TNT)
                .unlockedBy("has_mucus_charge", has(ModItems.MUCUS_CHARGE)).save(output);

        shaped(MISC, ModItems.MUCUS_CHARGE)
                .pattern("SSS")
                .pattern("SRS")
                .pattern("SSS")
                .define('S', Tags.Items.SLIME_BALLS)
                .define('R', ModItems.REPELLENT)
                .unlockedBy("has_repellent", has(ModItems.REPELLENT))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.HONEY_COMB)
                .pattern("NPN")
                .pattern("PCP")
                .pattern("NPN")
                .define('N', ModItems.NECTAR)
                .define('P', ModItems.PAPYRUS)
                .define('C', Blocks.CHEST)
                .unlockedBy("has_nectar", has(ModItems.NECTAR))
                .save(output);

        threeByThree(ModBlocks.FIRE_BLOOM, Items.BLAZE_POWDER);
        threeByThree(ModBlocks.MOSS, ModItems.MOSS_BALL);

        shaped(MISC, ModBlocks.GAEAN_KEYSTONE)
                .pattern("V V")
                .pattern("SOS")
                .pattern("SSS")
                .define('V', Blocks.VINE)
                .define('S', ItemTags.STONE_BRICKS)
                .define('O', Blocks.OBSIDIAN)
                .unlockedBy("has_vine", has(Blocks.VINE))
                .save(output);

        shaped(MISC, ModBlocks.COMPOSTER)
                .pattern("PSP")
                .pattern("PGP")
                .pattern("PSP")
                .define('P', ModBlocks.PLANKS_VARNISHED)
                .define('S', ModBlocks.SLAB_PLANKS_VARNISHED)
                .define('G', Tags.Items.DYES_GREEN)
                .unlockedBy("has_planks_varnished", has(ModBlocks.PLANKS_VARNISHED))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.SILO_TANK)
                .pattern("IPI")
                .pattern("BCB")
                .pattern("IPI")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('P', ModBlocks.PLANKS_VARNISHED)
                .define('B', Tags.Items.STORAGE_BLOCKS_IRON)
                .define('C', ModBlocks.CHEST_PETRIFIED)
                .unlockedBy("has_petrified_wood_chest", has(ModBlocks.CHEST_PETRIFIED))
                .save(output);

        shaped(MISC, ModBlocks.OFFERING_ALTAR)
                .pattern("SGS")
                .pattern("BOB")
                .pattern("SBS")
                .define('S', Tags.Items.STONES)
                .define('G', Tags.Items.INGOTS_GOLD)
                .define('O', Tags.Items.OBSIDIANS)
                .define('B', ItemTags.STONE_BRICKS)
                .unlockedBy("has_obsidian", has(Tags.Items.OBSIDIANS))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.MOSS_CULTIVATED)
                .pattern("GSG")
                .pattern("SMS")
                .pattern("GSG")
                .define('S', ModItems.SUPERNATURAL_VELOCITY)
                .define('M', ModBlocks.MOSS)
                .define('G', Tags.Items.DYES_GREEN)
                .unlockedBy("has_supernatural_velocity", has(ModItems.SUPERNATURAL_VELOCITY))
                .save(output);

        shaped(BUILDING_BLOCKS, ModBlocks.MOULD_CULTIVATED)
                .pattern("LSL")
                .pattern("SMS")
                .pattern("LSL")
                .define('S', ModItems.SUPERNATURAL_VELOCITY)
                .define('M', ModBlocks.MOULD)
                .define('L', Items.LAPIS_LAZULI)
                .unlockedBy("has_supernatural_velocity", has(ModItems.SUPERNATURAL_VELOCITY))
                .save(output);

        surround(ModItems.HYDROFUGE, ModItems.REPELLENT, ModItems.WATER_REPELLENT);
        surround(ModItems.ALTAR_FRAGMENT, Blocks.OBSIDIAN, ModBlocks.ALTAR_BASE);
    }

    private void addGolemRecipes() {
        shaped(MISC, ModItems.UMBERGOLEM_HEAD)
                .pattern("SSS")
                .pattern("SHS")
                .pattern("SMS")
                .define('S', Tags.Items.STONES)
                .define('H', ModItems.REIN_COMPOUND_GOGGLES)
                .define('M', ModItems.STAG_BEETLE_MANDIBLES)
                .unlockedBy("has_rein_compound_goggles", has(ModItems.REIN_COMPOUND_GOGGLES))
                .save(output);

        surround(ModItems.ALTAR_FRAGMENT, ModItems.RED_GEM, ModItems.UMBERGOLEM_CORE);

        shaped(MISC, ModItems.UMBERGOLEM_LEGS)
                .pattern("SSS")
                .pattern("S S")
                .pattern("P P")
                .define('S', Tags.Items.STONES)
                .define('P', ModItems.REINFORCED_PLATE_EXO)
                .unlockedBy("has_reinforced_place_exo", has(ModItems.REINFORCED_PLATE_EXO))
                .save(output);

        shaped(MISC, ModItems.UMBERGOLEM_CLAW)
                .pattern("SSP")
                .pattern("S  ")
                .define('S', Tags.Items.STONES)
                .define('P', ModItems.SCORPION_PINCER)
                .unlockedBy("has_scorpion_pincer", has(ModItems.SCORPION_PINCER))
                .save(output);

        shaped(MISC, ModItems.UMBERGOLEM_CLAW)
                .pattern("P  ")
                .pattern("S  ")
                .pattern("SS ")
                .define('S', Tags.Items.STONES)
                .define('P', ModItems.SCORPION_PINCER)
                .unlockedBy("has_scorpion_pincer", has(ModItems.SCORPION_PINCER))
                .save(output, "umbergolem_claw_vertical");

        shaped(BUILDING_BLOCKS, ModBlocks.UMBER_GOLEM_STATUE)
                .pattern(" H ")
                .pattern("CTC")
                .pattern(" L ")
                .define('H', ModItems.UMBERGOLEM_HEAD)
                .define('C', ModItems.UMBERGOLEM_CLAW)
                .define('T', ModItems.UMBERGOLEM_CORE)
                .define('L', ModItems.UMBERGOLEM_LEGS)
                .unlockedBy("has_umbergolem_head", has(ModItems.UMBERGOLEM_HEAD))
                .unlockedBy("has_umbergolem_claw", has(ModItems.UMBERGOLEM_CLAW))
                .unlockedBy("has_umbergolem_core", has(ModItems.UMBERGOLEM_CORE))
                .unlockedBy("has_umbergolem_legs", has(ModItems.UMBERGOLEM_LEGS))
                .save(output);

        surround(ModBlocks.MUD_BRICKS, ModBlocks.UMBER_GOLEM_STATUE, ModItems.MUD_UMBERGOLEM);
        surround(Tags.Items.STORAGE_BLOCKS_IRON, ModItems.IRON_UMBERGOLEM);
        surround(Tags.Items.STORAGE_BLOCKS_GOLD, ModItems.GOLD_UMBERGOLEM);
        surround(ModItemTags.STORAGE_BLOCKS_JADE, ModItems.JADE_UMBERGOLEM);
    }

    private void addMiscItemRecipes() {
        shaped(MISC, ModItems.JADE)
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.JADE_BERRIES)
                .unlockedBy("has_jade_berries", has(ModItems.JADE_BERRIES))
                .save(output, "erebus:jade_from_jadeberries");

        shaped(MISC, ModItems.STEW_POT)
                .pattern(" N ")
                .pattern("N N")
                .pattern(" B ")
                .define('N', Items.IRON_NUGGET)
                .define('B', Items.BUCKET)
                .unlockedBy("has_bucket", has(Items.BUCKET))
                .save(output);

        shaped(MISC, ModItems.SPRAY_CAN, 9)
                .pattern(" B ")
                .pattern("IRI")
                .pattern("III")
                .define('B', ItemTags.WOODEN_BUTTONS)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('R', ModItems.REPELLENT)
                .unlockedBy("has_repellent", has(ModItems.REPELLENT))
                .save(output);

        twoByTwo(ModItems.HIDE_SHROOM, Items.LEATHER, 8);
        twoByTwo(ModItems.PLATE_ZOMBIE_ANT, Items.ROTTEN_FLESH);

        shaped(MISC, ModItems.AMBER_STAR)
                .pattern(" R ")
                .pattern("RGR")
                .pattern(" R ")
                .define('R', ModItems.RESIN)
                .define('G', ModBlocks.AMBER_GLASS)
                .unlockedBy("has_resin", has(ModItems.RESIN))
                .save(output);

        shaped(MISC, ModItems.BEETLE_RIDING_KIT)
                .pattern(" SP")
                .pattern("CCC")
                .pattern("LLL")
                .define('S', Items.STRING)
                .define('P', ModItems.PLATE_EXO)
                .define('C', ItemTags.WOOL_CARPETS)
                .define('L', Items.LAPIS_LAZULI)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output);

        shaped(MISC, ModItems.BEETLE_TAMING_AMULET)
                .pattern(" N ")
                .pattern("NJN")
                .pattern(" A ")
                .define('N', Tags.Items.NUGGETS_GOLD)
                .define('J', ModItemTags.GEMS_JADE)
                .define('A', ModItems.ALTAR_FRAGMENT)
                .unlockedBy("has_jade", has(ModItemTags.GEMS_JADE))
                .save(output);
    }
}
