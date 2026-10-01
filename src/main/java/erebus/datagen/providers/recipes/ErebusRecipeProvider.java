package erebus.datagen.providers.recipes;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.Locale;

import static net.minecraft.data.recipes.RecipeCategory.*;

public abstract class ErebusRecipeProvider extends RecipeProvider {
    protected RecipeOutput output;

    protected ErebusRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.output = output;
    }

    protected void smelting(ItemLike ingredient, ItemLike result) {
        smeltingResultFromBase(result, ingredient);
    }

    protected void cook(ItemLike ingredient, ItemLike result, float furnaceExperience) {
        simpleCookingRecipe("smelting", SmeltingRecipe::new, 200, ingredient, result, furnaceExperience);
        simpleCookingRecipe("smoking", SmokingRecipe::new, 100, ingredient, result, 0.35F);
        simpleCookingRecipe("campfire_cooking", CampfireCookingRecipe::new, 600, ingredient, result, 0.35F);
    }

    protected void ore(ItemLike ore, ItemLike result, String group, float furnaceExperience) {
        ore(ore, result, group, furnaceExperience, 1);
    }

    protected void ore(ItemLike ore, ItemLike result, String group, float furnaceExperience, int count) {
        var outputStack = new ItemStackTemplate(result.asItem(), count);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ore), MISC, CookingBookCategory.BLOCKS,
                        outputStack, furnaceExperience, 200)
                .group(group).unlockedBy(getHasName(ore), has(ore))
                .save(output, getItemName(result) + "_from_smelting_" + getItemName(ore));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ore), MISC, CookingBookCategory.BLOCKS,
                        outputStack, 0.25F, 100)
                .group(group).unlockedBy(getHasName(ore), has(ore))
                .save(output, getItemName(result) + "_from_blasting_" + getItemName(ore));
    }

    protected void shapeless(RecipeCategory category, ItemLike ingredient, ItemLike result, int amount) {
        shapeless(category, result, amount)
                .requires(ingredient)
                .unlockedBy("has_%s".formatted(ingredient.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(ingredient))
                .save(output, "shapeless_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void stairs(ItemLike material, ItemLike result) {
        stairBuilder(result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output, "stairs_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void slab(ItemLike material, ItemLike result) {
        slabBuilder(BUILDING_BLOCKS, result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output, "slab_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void door(ItemLike material, ItemLike result) {
        doorBuilder(result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output, "door_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void fence(ItemLike material, ItemLike result) {
        fenceBuilder(result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output, "fence_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void fenceGate(ItemLike material, ItemLike result) {
        fenceGateBuilder(result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output, "fence_gate_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void wall(ItemLike material, ItemLike result) {
        wallBuilder(BUILDING_BLOCKS, result, Ingredient.of(material))
                .unlockedBy("has_%s".formatted(material.asItem().getDescriptionId().toLowerCase(Locale.ROOT)), has(material))
                .save(output, "wall_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
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
                .save(output, "two_by_two_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
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
                .save(output, "three_by_three_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void helmet(ItemLike material, ItemLike result) {
        shaped(COMBAT, result)
                .pattern("MMM")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_material", has(material))
                .save(output, "helmet_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void chestplate(ItemLike material, ItemLike result) {
        shaped(COMBAT, result)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', material)
                .unlockedBy("has_material", has(material))
                .save(output, "chestplate_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void leggings(ItemLike material, ItemLike result) {
        shaped(COMBAT, result)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_material", has(material))
                .save(output, "leggings_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void boots(ItemLike material, ItemLike result) {
        shaped(COMBAT, result)
                .pattern("M M")
                .pattern("M M")
                .define('M', material)
                .unlockedBy("has_material", has(material))
                .save(output, "boots_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
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
                .save(output, "surround_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void surround(TagKey<Item> outer, ItemLike result) {
        shaped(MISC, result).pattern("OOO").pattern("OIO").pattern("OOO")
                .define('O', outer).define('I', ModBlocks.UMBER_GOLEM_STATUE)
                .unlockedBy("has_outer", has(outer)).unlockedBy("has_inner", has(ModBlocks.UMBER_GOLEM_STATUE))
                .save(output, "surround_%s".formatted(result.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }

    protected void surround() {
        shaped(MISC, ModBlocks.CHEST_PETRIFIED).pattern("OOO").pattern("OIO").pattern("OOO")
                .define('O', ModBlocks.PLANKS_PETRIFIED).define('I', net.neoforged.neoforge.common.Tags.Items.INGOTS_GOLD)
                .unlockedBy("has_outer", has(ModBlocks.PLANKS_PETRIFIED)).unlockedBy("has_inner", has(net.neoforged.neoforge.common.Tags.Items.INGOTS_GOLD))
                .save(output, "surround_%s".formatted(ModBlocks.CHEST_PETRIFIED.asItem().getDescriptionId().toLowerCase(Locale.ROOT)));
    }
}
