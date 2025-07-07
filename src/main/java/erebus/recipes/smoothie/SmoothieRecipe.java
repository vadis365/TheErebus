package erebus.recipes.smoothie;

import erebus.recipes.util.SmoothieIngredientCounts;
import erebus.registries.ModCustomRecipes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import org.jetbrains.annotations.NotNull;

public class SmoothieRecipe implements Recipe<SmoothieRecipeInput> {

    private final NonNullList<FluidIngredient> fluids;
    private final NonNullList<Ingredient> items;
    private final ItemStack result;
    private final SmoothieIngredientCounts counts;

    public SmoothieRecipe(NonNullList<FluidIngredient> fluids, NonNullList<Ingredient> items, ItemStack result) {
        this(fluids, items, new SmoothieIngredientCounts(fluids.size(), items.size()), result);
    }

    public SmoothieRecipe(NonNullList<FluidIngredient> fluids, NonNullList<Ingredient> items, SmoothieIngredientCounts smoothieIngredientCounts, ItemStack result) {
        this.fluids = fluids;
        this.items = items;
        this.counts = smoothieIngredientCounts;
        this.result = result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModCustomRecipes.SMOOTHIE_RECIPE.get();
    }

    @Override
    public boolean isIncomplete() {
        return true;
    }

    @Override
    public boolean matches(@NotNull SmoothieRecipeInput input, @NotNull Level level) {
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SmoothieRecipeInput input, @NotNull Provider provider) {
        return result.copy();
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull Provider provider) {
        return result;
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModCustomRecipes.SMOOTHIE_RECIPE_SERIALIZER.get();
    }

    public NonNullList<FluidIngredient> getFluidIngredients() {
        return fluids;
    }

    public NonNullList<Ingredient> getItemIngredients() {
        return items;
    }

    public SmoothieIngredientCounts getCounts() {
        return counts;
    }
}
