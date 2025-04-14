package erebus.datagen.providers;

import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeCategory.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private RecipeOutput output;

    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        this.output = output;

        addCookingRecipes();
        addOreSmeltingRecipes();
        addGenericSmelting();

        addShapedCraftingRecipes();
        addShapelessCraftingRecipes();
    }

    private void addCookingRecipes() {
        cook(ModItems.BEETLE_LARVA_RAW, ModItems.BEETLE_LARVA_COOKED);
        cook(ModItems.GRASSHOPPER_LEG_RAW, ModItems.GRASSHOPPER_LEG_COOKED);
        cook(ModItems.TARANTULA_LEG_RAW, ModItems.TARANTULA_LEG_COOKED);
        cook(ModItems.TITAN_CHOP_RAW, ModItems.TITAN_CHOP_COOKED);
        cook(ModItems.PRICKLY_PEAR_RAW, ModItems.PRICKLY_PEAR_COOKED);
        cook(ModItems.TITAN_STEW, ModItems.TITAN_STEW_COOKED);
        cook(ModItems.STAG_HEART_RAW, ModItems.STAG_HEART_COOKED);
    }

    private void addOreSmeltingRecipes() {
        ore(ModBlocks.ORE_COAL, Items.COAL, "coal");
        ore(ModBlocks.ORE_IRON, Items.IRON_INGOT, "iron");
        ore(ModBlocks.ORE_GOLD, Items.GOLD_INGOT, "gold");
        ore(ModBlocks.ORE_LAPIS, Items.LAPIS_LAZULI, "lapis");
        ore(ModBlocks.ORE_DIAMOND, Items.DIAMOND, "diamond");
        ore(ModBlocks.ORE_EMERALD, Items.EMERALD, "emerald");
        ore(ModBlocks.ORE_QUARTZ, Items.QUARTZ, "quartz");
        ore(ModBlocks.ORE_PETRIFIED_QUARTZ, Items.QUARTZ, "quartz");
        ore(ModBlocks.ORE_JADE, ModItems.JADE, "jade");
        ore(ModBlocks.ORE_FOSSIL, ModItems.SHARD_BONE, "shard_bone");
        ore(ModBlocks.ORE_GNEISS, ModItems.GNEISS_ROCK, "gneiss");
        ore(ModBlocks.ORE_PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD, "petrified_wood");
        ore(ModBlocks.ORE_ENCRUSTED_DIAMOND, Items.DIAMOND, "diamond");
    }

    private void addGenericSmelting() {
        smelting(ModBlocks.AMBER, ModBlocks.AMBER_GLASS);
        smelting(ModBlocks.UMBERSTONE, ModBlocks.UMBERPAVER);
        smelting(ModBlocks.MUD, ModBlocks.MUD_BRICKS);
        smelting(ModItems.NECTAR, ModItems.HONEY_DRIP);
        smelting(ModBlocks.LOG_ASPER, Items.CHARCOAL);
        smelting(ModBlocks.LOG_BALSAM, Items.CHARCOAL);
        smelting(ModBlocks.LOG_BAOBAB, Items.CHARCOAL);
        smelting(ModBlocks.LOG_CYPRESS, Items.CHARCOAL);
        smelting(ModBlocks.LOG_EUCALYPTUS, Items.CHARCOAL);
        smelting(ModBlocks.LOG_MARSHWOOD, Items.CHARCOAL);
        smelting(ModBlocks.LOG_SCORCHED, Items.CHARCOAL);
        smelting(ModBlocks.LOG_MOSSBARK, Items.CHARCOAL);
        smelting(ModBlocks.LOG_MAHOGANY, Items.CHARCOAL);
    }

    private void addShapedCraftingRecipes() {
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ModBlocks.UMBERPAVER, 4)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.UMBERSTONE)
                .unlockedBy("has_umberstone", has(ModBlocks.UMBERSTONE))
                .save(output);

        ShapedRecipeBuilder.shaped(DECORATIONS, ModBlocks.UMBER_FURNACE, 1)
                .pattern("SSS")
                .pattern("SBS")
                .pattern("SSS")
                .define('S', ModBlocks.UMBERCOBBLE)
                .define('B', Items.BUCKET)
                .unlockedBy("has_umbercobble", has(ModBlocks.UMBERCOBBLE))
                .save(output);
    }

    private void addShapelessCraftingRecipes() {
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_ASPER, ModBlocks.PLANKS_ASPER, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_BAOBAB, ModBlocks.PLANKS_BAOBAB, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_EUCALYPTUS, ModBlocks.PLANKS_EUCALYPTUS, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MAHOGANY, ModBlocks.PLANKS_MAHOGANY, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MOSSBARK, ModBlocks.PLANKS_MOSSBARK, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_CYPRESS, ModBlocks.PLANKS_CYPRESS, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_BALSAM, ModBlocks.PLANKS_BALSAM, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_BAMBOO, ModBlocks.PLANKS_BAMBOO, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_ROTTEN, ModBlocks.PLANKS_ROTTEN, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_MARSHWOOD, ModBlocks.PLANKS_MARSHWOOD, 4);
        shapeless(BUILDING_BLOCKS, ModBlocks.LOG_SCORCHED, ModBlocks.PLANKS_SCORCHED, 4);
        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, ModBlocks.PLANKS_VARNISHED, 1)
                .requires(ItemTags.PLANKS)
                .requires(Tags.Items.SLIME_BALLS)
                .requires(ModItems.REPELLENT)
                .unlockedBy("has_repellent", has(ModItems.REPELLENT))
                .save(output);

        shapeless(BUILDING_BLOCKS, ModItems.RED_GEM, Items.REDSTONE, 2);

        ShapelessRecipeBuilder.shapeless(FOOD, ModItems.BAMBOO_SOUP, 1)
                .requires(Items.BOWL)
                .requires(ModItems.BAMBOO_SHOOT)
                .requires(ModItems.BAMBOO)
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_bamboo", has(ModItems.BAMBOO))
                .unlockedBy("has_bamboo_shoot", has(ModItems.BAMBOO_SHOOT))
                .save(output);

        ShapelessRecipeBuilder.shapeless(FOOD, ModItems.LARVAE_ON_STICK, 1)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(ModItems.BEETLE_LARVA_COOKED, 3)
                .unlockedBy("has_cooked_beetle_larvae", has(ModItems.BEETLE_LARVA_COOKED))
                .save(output);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, ModBlocks.SILK, 1)
                .requires(Items.STRING, 9)
                .unlockedBy("has_string", has(Items.STRING))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.BONE_MEAL, 1)
                .requires(ModItems.SHARD_BONE)
                .unlockedBy("has_shard_bone", has(ModItems.SHARD_BONE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, ModBlocks.REIN_EXO, 1)
                .requires(ModItems.REINFORCED_PLATE_EXO, 4)
                .unlockedBy("has_reinforced_plate_exo", has(ModItems.REINFORCED_PLATE_EXO))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.BOOK, 1)
                .requires(ModItems.PLATE_EXO)
                .requires(Items.PAPER, 3)
                .unlockedBy("has_plate_exo", has(ModItems.PLATE_EXO))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, Items.PAPER, 4)
                .requires(ModItems.PAPYRUS, 2)
                .unlockedBy("has_papyrus", has(ModItems.PAPYRUS))
                .save(output);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, ModBlocks.JADE_BLOCK, 1)
                .requires(ModItems.JADE, 9)
                .unlockedBy("has_jade", has(ModItems.JADE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.PLANTICIDE, 2)
                .requires(ModItems.POISON_GLAND)
                .requires(Tags.Items.SLIME_BALLS)
                .requires(Tags.Items.DYES_WHITE)
                .unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.STEW_POT, 1)
                .requires(Items.CAULDRON)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_cauldron", has(Items.CAULDRON))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.TITAN_STEW, 1)
                .requires(ModItems.STEW_POT)
                .requires(ModItems.TITAN_CHOP_RAW)
                .requires(Items.POTATO)
                .requires(Items.CARROT)
                .requires(ModItems.CABBAGE)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_stew_pot", has(ModItems.STEW_POT))
                .save(output);

        /*ShapelessRecipeBuilder.shapeless(MISC, ModItems.TITAN_STEW, 1)
                .requires(ModItems.STEW_POT)
                .requires(Items.BEEF, 2)
                .requires(Items.POTATO)
                .requires(Items.CARROT)
                .requires(ModItems.CABBAGE)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_stew_pot", has(ModItems.STEW_POT))
                .save(output);*/

        ShapelessRecipeBuilder.shapeless(MISC, ModItems.SMOOTHIE_GLASS, 1)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(COMBAT, ModItems.WEB_SLINGER_WITHER, 1)
                .requires(ModItems.WEB_SLINGER)
                .requires(Blocks.SOUL_SAND)
                .requires(ModItems.POISON_GLAND)
                .requires(ModBlocks.WITHER_WEB, 3)
                .unlockedBy("has_web_slinger", has(ModItems.WEB_SLINGER))
                .save(output);

        ShapelessRecipeBuilder.shapeless(MISC, ModBlocks.LIGHTNING_SPEED, 1)
                .requires(ModBlocks.VELOCITY)
                .requires(ModItems.SUPERNATURAL_VELOCITY, 8)
                .unlockedBy("has_velocity_block", has(ModBlocks.VELOCITY))
                .save(output);
    }

    private void smelting(ItemLike ingredient, ItemLike result) {
        smeltingResultFromBase(output, ingredient, result);
    }

    private void cook(ItemLike ingredient, ItemLike result) {
        simpleCookingRecipe(output, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ingredient, result, 0.35F);
        simpleCookingRecipe(output, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ingredient, result, 0.35F);
    }

    private void ore(ItemLike ore, ItemLike ingot, String group) {
        oreSmelting(output, List.of(ore), MISC, ingot, 0.25F, 200, group);
        oreBlasting(output, List.of(ore), MISC, ingot, 0.25F, 100, group);
    }

    private void shapeless(RecipeCategory category, ItemLike ingredient, ItemLike result, int amount) {
        ShapelessRecipeBuilder.shapeless(category, result, amount)
                .requires(ingredient)
                .unlockedBy("has_%s".formatted(ingredient.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(ingredient))
                .save(output);
    }
}
