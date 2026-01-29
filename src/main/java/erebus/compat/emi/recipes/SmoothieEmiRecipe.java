/*
package erebus.compat.emi.recipes;

import dev.emi.emi.api.neoforge.NeoForgeEmiIngredient;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import erebus.compat.emi.ModEmiPlugin;
import erebus.recipes.smoothie.SmoothieRecipe;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SmoothieEmiRecipe implements EmiRecipe {

    private final Identifier id;
    private final List<EmiIngredient> inputs = new ArrayList<>();
    private final List<EmiStack> output = new ArrayList<>();
    private final SmoothieRecipe recipe;

    private final int[] fluidX = {2, 20, 2, 20};
    private final int[] fluidY = {3, 3, 21, 21};

    private final int[] itemX = {64, 82, 64, 82};
    private final int[] itemY = {3, 3, 21, 21};

    public SmoothieEmiRecipe(RecipeHolder<SmoothieRecipe> recipeHolder) {
        id = recipeHolder.id();
        recipe = recipeHolder.value();

        recipe.getFluidIngredients().stream().map(NeoForgeEmiIngredient::of).forEach(inputs::add);
        recipe.getIngredients().stream().map(EmiIngredient::of).forEach(inputs::add);
        output.add(EmiStack.of(recipe.getResult()));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ModEmiPlugin.BLENDER_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return 154;
    }

    @Override
    public int getDisplayHeight() {
        return 62;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        int c = 0;
        for (SizedFluidIngredient fluid : recipe.getFluidIngredients()) {
            widgets.addSlot(NeoForgeEmiIngredient.of(fluid), fluidX[c], fluidY[c]);
            if(c != 3) c++;
        }

        widgets.addTexture(EmiTexture.PLUS, fluidX[c] + 23, 15);
        
        c = 0;
        for (Ingredient ingredient : recipe.getItemIngredients()) {
            widgets.addSlot(EmiIngredient.of(ingredient), itemX[c], itemY[c]);
            if(c != 3) c++;
        }

        if(recipe.getCounts().itemCount() == 5) {
            widgets.addSlot(EmiIngredient.of(recipe.getItemIngredients().getLast()), 73, 39);
        }

        widgets.addFillingArrow(105, 15, 50 * 200).tooltip((mx, my) -> List.of(ClientTooltipComponent.create(Component.translatable("emi.cooking.time", 200 / 20f).getVisualOrderText())));

        widgets.addSlot(EmiStack.of(recipe.getResult()),134, 15);
    }
}
*/
