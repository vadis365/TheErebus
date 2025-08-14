package erebus.datagen.providers.recipes;

import erebus.registries.ModItems;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeCategory.*;

/**
 * Provider for shapeless crafting recipes.
 */
public class ShapelessCraftingRecipeProvider extends ErebusRecipeProvider {

    public ShapelessCraftingRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput output) {
        this.output = output;
        addPlanksRecipes();
        addMiscShapelessRecipes();
        addFoodRecipes();
        addSpecialRecipes();
    }

    private void addPlanksRecipes() {
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_ASPER, WoodBlocks.PLANKS_ASPER, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_BAOBAB, WoodBlocks.PLANKS_BAOBAB, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_EUCALYPTUS, WoodBlocks.PLANKS_EUCALYPTUS, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_MAHOGANY, WoodBlocks.PLANKS_MAHOGANY, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_MOSSBARK, WoodBlocks.PLANKS_MOSSBARK, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_CYPRESS, WoodBlocks.PLANKS_CYPRESS, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_BALSAM, WoodBlocks.PLANKS_BALSAM, 4);
        shapeless(BUILDING_BLOCKS, ModItems.BAMBOO, WoodBlocks.PLANKS_BAMBOO, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_ROTTEN, WoodBlocks.PLANKS_ROTTEN, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_MARSHWOOD, WoodBlocks.PLANKS_MARSHWOOD, 4);
        shapeless(BUILDING_BLOCKS, WoodBlocks.LOG_SCORCHED, WoodBlocks.PLANKS_SCORCHED, 4);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, WoodBlocks.PLANKS_VARNISHED)
                .requires(ItemTags.PLANKS)
                .requires(Tags.Items.SLIME_BALLS)
                .requires(ModItems.REPELLENT)
                .unlockedBy("has_repellent", has(ModItems.REPELLENT))
                .save(output);
    }

    private void addMiscShapelessRecipes() {
        shapeless(BUILDING_BLOCKS, ModItems.RED_GEM, Items.REDSTONE, 2);
        shapeless(MISC, UmberstoneBlocks.UMBERSTONE, OtherBlocks.UMBERSTONE_BUTTON, 1);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, OtherBlocks.SILK)
                .requires(Items.STRING, 9)
                .unlockedBy("has_string", has(Items.STRING))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.BONE_MEAL)
                .requires(ModItems.SHARD_BONE)
                .unlockedBy("has_shard_bone", has(ModItems.SHARD_BONE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, OtherBlocks.REIN_EXO)
                .requires(ModItems.REINFORCED_PLATE_EXO, 4)
                .unlockedBy("has_reinforced_plate_exo", has(ModItems.REINFORCED_PLATE_EXO))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.BOOK)
                .requires(ModItems.PLATE_EXO)
                .requires(Items.PAPER, 3)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.PAPER, 4)
                .requires(ModItems.PAPYRUS, 2)
                .unlockedBy("has_papyrus", has(ModItems.PAPYRUS))
                .save(output);

        nineBlockStorageRecipes(output, MISC, ModItems.JADE, BUILDING_BLOCKS, OtherBlocks.JADE_BLOCK);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.PLANTICIDE, 2)
                .requires(ModItems.POISON_GLAND)
                .requires(Tags.Items.SLIME_BALLS)
                .requires(Tags.Items.DYES_WHITE)
                .unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.STEW_POT)
                .requires(Items.CAULDRON)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_cauldron", has(Items.CAULDRON))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.SMOOTHIE_GLASS)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(COMBAT, ModItems.WEB_SLINGER_WITHER)
                .requires(ModItems.WEB_SLINGER)
                .requires(Blocks.SOUL_SAND)
                .requires(ModItems.POISON_GLAND)
                .requires(OtherBlocks.WITHER_WEB, 3)
                .unlockedBy("has_web_slinger", has(ModItems.WEB_SLINGER))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, OtherBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED)
                .requires(OtherBlocks.VELOCITY_BLOCK)
                .requires(ModItems.SUPERNATURAL_VELOCITY, 8)
                .unlockedBy("has_velocity_block", has(OtherBlocks.VELOCITY_BLOCK))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.REINFORCED_PLATE_EXO)
                .requires(ModItems.PLATE_EXO, 9)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output);

        ShapelessRecipeBuilder.shapeless(COMBAT, ModItems.WASP_DAGGER)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(ModItems.WASP_STING)
                .unlockedBy("has_wasp_sting", has(ModItems.WASP_STING))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, OtherBlocks.BAMBOO_PIPE_EXTRACT)
                .requires(Items.LEVER)
                .requires(OtherBlocks.BAMBOO_PIPE)
                .unlockedBy("has_bamboo_pipe", has(OtherBlocks.BAMBOO_PIPE))
                .save(output);
    }

    private void addFoodRecipes() {
        ShapelessRecipeBuilder.shapeless(FOOD, ModItems.BAMBOO_SOUP)
                .requires(Items.BOWL)
                .requires(ModItems.BAMBOO_SHOOT)
                .requires(ModItems.BAMBOO)
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .unlockedBy("has_bamboo_shoot", has(ModItems.BAMBOO_SHOOT))
                .save(output);

        ShapelessRecipeBuilder.shapeless(FOOD, ModItems.LARVAE_ON_STICK)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(ModItems.BEETLE_LARVA_COOKED, 3)
                .unlockedBy("has_cooked_beetle_larvae", has(ModItems.BEETLE_LARVA_COOKED))
                .save(output);
    }

    private void addSpecialRecipes() {
        ShapelessRecipeBuilder.shapeless(MISC, ModItems.TITAN_STEW)
                .requires(ModItems.STEW_POT)
                .requires(ModItems.TITAN_CHOP_RAW)
                .requires(Items.POTATO)
                .requires(Items.CARROT)
                .requires(ModItems.CABBAGE)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_stew_pot", has(ModItems.STEW_POT))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.TITAN_STEW, 1)
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