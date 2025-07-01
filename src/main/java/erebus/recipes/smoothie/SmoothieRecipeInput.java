package erebus.recipes.smoothie;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;

public record SmoothieRecipeInput(
        SizedFluidIngredient fluid,
        SizedFluidIngredient fluid2,
        SizedFluidIngredient fluid3,
        SizedFluidIngredient fluid4,
        Ingredient stack,
        Ingredient stack2,
        Ingredient stack3,
        Ingredient stack4
) implements RecipeInput {

    @Override
    public @NotNull ItemStack getItem(int slot) {
        if(slot != 0) throw new IllegalArgumentException("No item for index %d".formatted(slot));
        return stack.getItems()[slot];
    }

    @Override
    public int size() {
        return 8;
    }
}
