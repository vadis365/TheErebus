package erebus.recipes.smoothie;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;

public class SmoothieRecipe implements Recipe<SmoothieRecipeInput> {

    private final SizedFluidIngredient fluid;
    private final SizedFluidIngredient fluid2;
    private final SizedFluidIngredient fluid3;
    private final SizedFluidIngredient fluid4;
    private final ItemStack stack;
    private final ItemStack stack2;
    private final ItemStack stack3;
    private final ItemStack stack4;
    private final ItemStack result;

    public SmoothieRecipe(SizedFluidIngredient fluid, SizedFluidIngredient fluid2, SizedFluidIngredient fluid3, SizedFluidIngredient fluid4, ItemStack stack, ItemStack stack2, ItemStack stack3, ItemStack stack4, ItemStack result) {
        this.fluid = fluid;
        this.fluid2 = fluid2;
        this.fluid3 = fluid3;
        this.fluid4 = fluid4;
        this.stack = stack;
        this.stack2 = stack2;
        this.stack3 = stack3;
        this.stack4 = stack4;
        this.result = result;
    }

    @Override
    public boolean matches(@NotNull SmoothieRecipeInput smoothieRecipeInput, @NotNull Level level) {
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SmoothieRecipeInput input, HolderLookup.@NotNull Provider registries) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return null;
    }
}
