package erebus.datagen.providers;

import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
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
