package erebus.recipes.smoothie;

import erebus.recipes.util.SmoothieIngredientCounts;
import erebus.registries.ModCustomRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class SmoothieRecipe implements Recipe<SmoothieRecipeInput> {

    private final NonNullList<SizedFluidIngredient> fluids;
    private final NonNullList<Ingredient> items;
    private final ItemStack result;
    private final SmoothieIngredientCounts counts;

    public SmoothieRecipe(NonNullList<SizedFluidIngredient> fluids, NonNullList<Ingredient> items, ItemStack result) {
        this(fluids, items, new SmoothieIngredientCounts(fluids.size(), items.size()), result);
    }

    public SmoothieRecipe(NonNullList<SizedFluidIngredient> fluids, NonNullList<Ingredient> items, SmoothieIngredientCounts smoothieIngredientCounts, ItemStack result) {
        this.fluids = fluids;
        this.items = items;
        this.counts = smoothieIngredientCounts;
        this.result = result;
    }

    @Override
    public @NonNull RecipeType<? extends Recipe<SmoothieRecipeInput>> getType() {
        return ModCustomRecipes.SMOOTHIE_RECIPE.get();
    }

    @Override
    public @NonNull PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public @NonNull RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    @Override
    public boolean matches(@NotNull SmoothieRecipeInput input, @NotNull Level level) {
        return true;
    }

    @Override
    public @NonNull ItemStack assemble(SmoothieRecipeInput input) {
        return result.copy();
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    public @NonNull RecipeSerializer<? extends Recipe<SmoothieRecipeInput>> getSerializer() {
        return ModCustomRecipes.SMOOTHIE_RECIPE_SERIALIZER.get();
    }

    public NonNullList<SizedFluidIngredient> getFluidIngredients() {
        return fluids;
    }

    public NonNullList<Ingredient> getItemIngredients() {
        return items;
    }

    public SmoothieIngredientCounts getCounts() {
        return counts;
    }
}
