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
 * Provider for shapeless crafting recipes.
 */
public class ShapelessCraftingRecipeProvider extends ErebusRecipeProvider {

    public ShapelessCraftingRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        addPlanksRecipes();
        addMiscShapelessRecipes();
        addFoodRecipes();
        addSpecialRecipes();
    }

    private void addPlanksRecipes() {
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_ASPER, ModBlocks.PLANKS_ASPER, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_BAOBAB, ModBlocks.PLANKS_BAOBAB, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_EUCALYPTUS, ModBlocks.PLANKS_EUCALYPTUS, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MAHOGANY, ModBlocks.PLANKS_MAHOGANY, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MOSSBARK, ModBlocks.PLANKS_MOSSBARK, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_CYPRESS, ModBlocks.PLANKS_CYPRESS, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_BALSAM, ModBlocks.PLANKS_BALSAM, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.PLANKS_BALSAM, 4)
                .requires(ModBlocks.LOG_BALSAM_RESINLESS)
                .unlockedBy("has_resinless_balsam", has(ModBlocks.LOG_BALSAM_RESINLESS))
                .save(output, "balsam_planks_from_resinless_log");
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_ROTTEN, ModBlocks.PLANKS_ROTTEN, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MARSHWOOD, ModBlocks.PLANKS_MARSHWOOD, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_SCORCHED, ModBlocks.PLANKS_SCORCHED, 4);

        shapeless(BUILDING_BLOCKS, ModBlocks.PLANKS_WHITE)
                .requires(ItemTags.PLANKS)
                .requires(Tags.Items.DYES_WHITE)
                .unlockedBy("has_white_dye", has(Tags.Items.DYES_WHITE))
                .save(output);

        shapeless(BUILDING_BLOCKS, ModBlocks.PLANKS_VARNISHED, 4)
                .requires(ItemTags.PLANKS)
                .requires(ItemTags.PLANKS)
                .requires(ItemTags.PLANKS)
                .requires(ItemTags.PLANKS)
                .requires(ModItems.RESIN, 2)
                .requires(ModItems.REPELLENT)
                .requires(ModItems.CAMO_POWDER)
                .unlockedBy("has_repellent", has(ModItems.REPELLENT))
                .save(output);
    }

    private void addMiscShapelessRecipes() {
        shapeless(MISC, Items.BONE_MEAL)
                .requires(ModItems.DEATH_COMPASS)
                .unlockedBy("has_death_compass", has(ModItems.DEATH_COMPASS))
                .save(output, "erebus:bone_meal_from_death_compass");

        shapeless(MISC, Items.PINK_DYE, 2)
                .requires(ModBlocks.TALL_BLOOM)
                .unlockedBy("has_tall_bloom", has(ModBlocks.TALL_BLOOM))
                .save(output, "erebus:pink_dye_from_tall_bloom");

        shapeless(BUILDING_BLOCKS, ModBlocks.GLOWSHROOM_BLOCK)
                .requires(ModItems.GLOWSHROOM)
                .requires(Items.TORCH)
                .unlockedBy("has_glowshroom", has(ModItems.GLOWSHROOM))
                .save(output, "erebus:glowshroom_from_torch");

        shapeless(BUILDING_BLOCKS, ModBlocks.GLOWSHROOM_BLOCK)
                .requires(ModItems.GLOWSHROOM)
                .requires(ModItems.BIO_LUMINESCENCE)
                .unlockedBy("has_glowshroom", has(ModItems.GLOWSHROOM))
                .save(output, "erebus:glowshroom_from_bioluminescence");

        shapeless(BUILDING_BLOCKS, ModItems.RED_GEM, Items.REDSTONE, 2);
        shapeless(MISC, ModBlocks.UMBERCOBBLE, ModBlocks.UMBERSTONE_BUTTON, 1);

        shapeless(BUILDING_BLOCKS, ModBlocks.SILK)
                .requires(Items.STRING, 9)
                .unlockedBy("has_string", has(Items.STRING))
                .save(output);

        shapeless(MISC, Items.STRING, 9)
                .requires(ModBlocks.SILK)
                .unlockedBy("has_silk", has(ModBlocks.SILK))
                .save(output, "erebus:string_from_silk");

        shapeless(MISC, Items.BONE_MEAL)
                .requires(ModItems.SHARD_BONE)
                .unlockedBy("has_shard_bone", has(ModItems.SHARD_BONE))
                .save(output, "erebus:bone_meal_from_bone_shard");

        shapeless(BUILDING_BLOCKS, ModBlocks.REIN_EXO)
                .requires(ModItems.REINFORCED_PLATE_EXO, 4)
                .unlockedBy("has_reinforced_plate_exo", has(ModItems.REINFORCED_PLATE_EXO))
                .save(output);

        shapeless(MISC, Items.BOOK)
                .requires(ModItems.PLATE_EXO)
                .requires(Items.PAPER, 3)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output, "erebus:book_from_exoskeleton_plate");

        shapeless(MISC, Items.PAPER, 4)
                .requires(ModItems.PAPYRUS, 2)
                .unlockedBy("has_papyrus", has(ModItems.PAPYRUS))
                .save(output, "erebus:paper_from_papyrus");

        shaped(BUILDING_BLOCKS, ModBlocks.JADE_BLOCK).pattern("###").pattern("###").pattern("###")
                .define('#', ModItemTags.GEMS_JADE).unlockedBy("has_jade", has(ModItemTags.GEMS_JADE))
                .save(output, "jade_block");
        shapeless(MISC, ModItems.JADE, 9).requires(ModItemTags.STORAGE_BLOCKS_JADE)
                .unlockedBy("has_jade_block", has(ModItemTags.STORAGE_BLOCKS_JADE)).save(output, "jade");

        shapeless(MISC, ModItems.PLANTICIDE, 2)
                .requires(ModItems.POISON_GLAND)
                .requires(ModItems.REPELLENT)
                .requires(Items.BONE_MEAL)
                .unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
                .save(output);

        shapeless(MISC, ModItems.SMOOTHIE_GLASS)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
                .save(output);

        shapeless(COMBAT, ModItems.WEB_SLINGER_WITHER)
                .requires(ModItems.WEB_SLINGER)
                .requires(Blocks.SOUL_SAND)
                .requires(ModItems.POISON_GLAND)
                .requires(ModBlocks.WITHER_WEB, 3)
                .unlockedBy("has_web_slinger", has(ModItems.WEB_SLINGER))
                .save(output);

        shapeless(MISC, ModBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED)
                .requires(ModBlocks.VELOCITY_BLOCK)
                .requires(ModItems.SUPERNATURAL_VELOCITY, 8)
                .unlockedBy("has_velocity_block", has(ModBlocks.VELOCITY_BLOCK))
                .save(output);

        shapeless(MISC, ModItems.REINFORCED_PLATE_EXO)
                .requires(ModItems.PLATE_EXO, 9)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output);

        shapeless(COMBAT, ModItems.WASP_DAGGER)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(ModItems.WASP_STING)
                .unlockedBy("has_wasp_sting", has(ModItems.WASP_STING))
                .save(output);

        shapeless(MISC, ModBlocks.BAMBOO_PIPE_EXTRACT)
                .requires(Items.LEVER)
                .requires(ModBlocks.BAMBOO_PIPE)
                .unlockedBy("has_bamboo_pipe", has(ModBlocks.BAMBOO_PIPE))
                .save(output);
    }

    private void addFoodRecipes() {
        shapeless(FOOD, ModItems.BAMBOO_SOUP)
                .requires(Items.BOWL)
                .requires(ModItems.BAMBOO)
                .requires(ModBlocks.SAPLING_BAMBOO)
                .unlockedBy("has_bamboo_sapling", has(ModBlocks.SAPLING_BAMBOO))
                .save(output, "erebus:bamboo_soup_from_sapling");

        shapeless(FOOD, ModItems.BAMBOO_SOUP)
                .requires(Items.BOWL)
                .requires(ModItems.BAMBOO_SHOOT)
                .requires(ModItems.BAMBOO)
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .unlockedBy("has_bamboo_shoot", has(ModItems.BAMBOO_SHOOT))
                .save(output);

        shapeless(FOOD, ModItems.LARVAE_ON_STICK)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(ModItems.BEETLE_LARVA_COOKED, 3)
                .unlockedBy("has_cooked_beetle_larvae", has(ModItems.BEETLE_LARVA_COOKED))
                .save(output);
    }

    private void addSpecialRecipes() {
        shapeless(MISC, ModItems.TITAN_STEW)
                .requires(ModItems.STEW_POT)
                .requires(ModItems.TITAN_CHOP_RAW)
                .requires(ModItems.MANDRAKE_ROOT)
                .requires(ModItems.TURNIP)
                .requires(ModItems.CABBAGE)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_stew_pot", has(ModItems.STEW_POT))
                .save(output);

        shapeless(MISC, ModItems.TITAN_STEW, 1)
                .requires(ModItems.STEW_POT)
                .requires(Items.BEEF, 2)
                .requires(Items.POTATO)
                .requires(Items.CARROT)
                .requires(ModItems.CABBAGE)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_stew_pot", has(ModItems.STEW_POT))
                .save(output, "stew_pot_no_titan_chop");
    }
}
