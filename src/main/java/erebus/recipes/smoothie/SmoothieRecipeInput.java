package erebus.recipes.smoothie;

import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public class SmoothieRecipeInput implements RecipeInput {

    private final List<SizedFluidIngredient> fluidIngredients;
    private final List<ItemStack> itemIngredients;
    private final StackedContents<Item> stackedContents = new StackedContents<>();
    private final int ingredientCount;

    public SmoothieRecipeInput(List<SizedFluidIngredient> fluidIngredients, List<ItemStack> items) {
        this.fluidIngredients = fluidIngredients;
        this.itemIngredients = items;
        ingredientCount = fluidIngredients.size() + items.size();
        items.forEach((item) -> stackedContents.account(item.getItem(), item.count()));
    }

    @Override
    @NotNull
    public ItemStack getItem(int i) {
        return itemIngredients.get(i);
    }

    @Override
    public int size() {
        return itemIngredients.size();
    }

    @Override
    public boolean isEmpty() {
        return ingredientCount == 0;
    }

    public List<ItemStack> getItemIngredients() {
        return itemIngredients;
    }

    public List<SizedFluidIngredient> getFluidIngredients() {
        return fluidIngredients;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }

    public StackedContents<Item> getStackedContents() {
        return stackedContents;
    }
}
