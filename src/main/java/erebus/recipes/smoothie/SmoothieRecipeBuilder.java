package erebus.recipes.smoothie;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SmoothieRecipeBuilder implements RecipeBuilder {

    private final HolderGetter<Item> items;
    private final ItemStackTemplate result;
    private final List<SizedFluidIngredient> fluidIngredients = new ArrayList<>();
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private String group;

    private SmoothieRecipeBuilder(HolderGetter<Item> items, ItemStackTemplate result) {
        this.items = items;
        this.result = result;
    }

    public static SmoothieRecipeBuilder smoothieRecipe(HolderGetter<Item> items, ItemStackTemplate result) {
        return new SmoothieRecipeBuilder(items, result);
    }

    public static SmoothieRecipeBuilder smoothieRecipe(HolderGetter<Item> items, ItemLike result) {
        return smoothieRecipe(items, result, 1);
    }

    public static SmoothieRecipeBuilder smoothieRecipe(HolderGetter<Item> items, ItemLike result, int count) {
        return new SmoothieRecipeBuilder(items, new ItemStackTemplate(result.asItem(), count));
    }

    public SmoothieRecipeBuilder requires(TagKey<Item> tag) {
        return requires(Ingredient.of(items.getOrThrow(tag)));
    }

    public SmoothieRecipeBuilder requires(ItemLike item) {
        return requires(item, 1);
    }

    public SmoothieRecipeBuilder requires(ItemLike item, int count) {
        for (int i = 0; i < count; i++) {
            requires(Ingredient.of(item));
        }

        return this;
    }

    public SmoothieRecipeBuilder requires(Ingredient ingredient) {
        return requires(ingredient, 1);
    }

    public SmoothieRecipeBuilder requires(Ingredient ingredient, int count) {
        for (int i = 0; i < count; i++) {
            ingredients.add(ingredient);
        }

        return this;
    }

    public SmoothieRecipeBuilder requires(Fluid fluid) {
        return requires(SizedFluidIngredient.of(fluid, FluidType.BUCKET_VOLUME));
    }

    public SmoothieRecipeBuilder requires(SizedFluidIngredient ingredient) {
        fluidIngredients.add(ingredient);
        return this;
    }

    public @NonNull SmoothieRecipeBuilder unlockedBy(@NonNull String name, @NonNull Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    public @NonNull SmoothieRecipeBuilder group(String group) {
        this.group = group;
        return this;
    }

    @Override
    public @NonNull ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(result);
    }

    @Override
    public void save(@NotNull RecipeOutput output, @NonNull ResourceKey<Recipe<?>> id) {
        SmoothieRecipe recipe = new SmoothieRecipe(
                RecipeBuilder.createCraftingCommonInfo(true),
                RecipeBuilder.createCraftingBookInfo(RecipeCategory.MISC, group),
                result,
                ingredients,
                fluidIngredients
        );

        output.accept(id, recipe, advancementBuilder.build(output, id, RecipeCategory.MISC));
    }
}
