package erebus.datagen.providers.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Locale;

import static net.minecraft.data.recipes.RecipeCategory.*;

/**
 * Base class for all Erebus recipe providers.
 * Contains common utility methods used by multiple recipe providers.
 */
public abstract class ErebusRecipeProvider extends RecipeProvider {
    protected RecipeOutput output;

    protected ErebusRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.output = output;
    }

    /**
     * Generate recipes for this provider.
     */

    // Utility methods shared across recipe providers

    protected void smelting(ItemLike ingredient, ItemLike result) {
        smeltingResultFromBase(result, ingredient);
    }

    protected void cook(ItemLike ingredient, ItemLike result) {
        simpleCookingRecipe("smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ingredient, result, 0.35F);
        simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ingredient, result, 0.35F);
    }

    protected void ore(ItemLike ore, ItemLike ingot, String group) {
        oreSmelting(List.of(ore), MISC, ingot, 0.25F, 200, group);
        oreBlasting(List.of(ore), MISC, ingot, 0.25F, 100, group);
    }

    protected void shapeless(RecipeCategory category, ItemLike ingredient, ItemLike result, int amount) {
        shapeless(category, result.asItem().getDefaultInstance().copyWithCount(amount))
                .requires(ingredient)
                .unlockedBy("has_%s".formatted(ingredient.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(ingredient))
                .save(output);
    }

    protected void stairs(ItemLike material, ItemLike result) {
        stairBuilder(result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    protected void slab(ItemLike material, ItemLike result) {
        slabBuilder(BUILDING_BLOCKS, result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    protected void door(ItemLike material, ItemLike result) {
        doorBuilder(result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    protected void fence(ItemLike material, ItemLike result) {
        fenceBuilder(result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    protected void fenceGate(ItemLike material, ItemLike result) {
        fenceGateBuilder(result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    protected void wall(ItemLike material, ItemLike result) {
        wallBuilder(BUILDING_BLOCKS, result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    protected void twoByTwo(ItemLike material, ItemLike result) {
        twoByTwo(material, result, 1);
    }

    protected void twoByTwo(ItemLike material, ItemLike result, int amount) {
        shaped(BUILDING_BLOCKS, result, amount)
                .pattern("##")
                .pattern("##")
                .define('#', material)
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    protected void threeByThree(ItemLike material, ItemLike result) {
        threeByThree(material, result, 1);
    }

    @SuppressWarnings("SameParameterValue")
    protected void threeByThree(ItemLike material, ItemLike result, int amount) {
        shaped(BUILDING_BLOCKS, result, amount)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', material)
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output);
    }

    protected void helmet(ItemLike material, ItemLike result) {
        shaped(COMBAT, result)
                .pattern("MMM")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    protected void chestplate(ItemLike material, ItemLike result) {
        shaped(COMBAT, result)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', material)
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    protected void leggings(ItemLike material, ItemLike result) {
        shaped(COMBAT, result)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    protected void boots(ItemLike material, ItemLike result) {
        shaped(COMBAT, result)
                .pattern("M M")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    protected void surround(ItemLike outer, ItemLike inner, ItemLike result) {
        shaped(MISC, result)
                .pattern("OOO")
                .pattern("OIO")
                .pattern("OOO")
                .define('O', outer)
                .define('I', inner)
                .unlockedBy("has_outer", has(outer))
                .unlockedBy("has_inner", has(inner))
                .save(output);
    }
}
