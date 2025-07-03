package erebus.recipes.smoothie;

import erebus.registries.ModCustomRecipes;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;

public record SmoothieRecipe(NonNullList<SizedFluidIngredient> fluids, NonNullList<Ingredient> items, ItemStack result) implements Recipe<SmoothieRecipeInput> {

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

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModCustomRecipes.SMOOTHIE_RECIPE_SERIALIZER.get();
    }

    public NonNullList<SizedFluidIngredient> getFluidIngredients() {
        return fluids;
    }

    public NonNullList<Ingredient> getItemIngredients() {
        return items;
    }
}
