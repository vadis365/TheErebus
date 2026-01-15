/*
package erebus.compat.emi;

import erebus.registries.blocks.ModBlocks;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import erebus.Erebus;
import erebus.compat.emi.recipes.SmoothieEmiRecipe;
import erebus.registries.ModCustomRecipes;
import net.minecraft.world.item.crafting.*;

import java.util.function.Function;

@EmiEntrypoint
public class ModEmiPlugin implements EmiPlugin {
    private static final EmiStack BLENDER = EmiStack.of(ModBlocks.BLENDER);
    public static final EmiRecipeCategory BLENDER_CATEGORY = new EmiRecipeCategory(Erebus.prefix("blender"), BLENDER);

    private static final EmiStack OFFERING_ALTAR = EmiStack.of(ModBlocks.OFFERING_ALTAR);
    public static final EmiRecipeCategory OFFERING_ALTAR_CATEGORY = new EmiRecipeCategory(Erebus.prefix("offering_altar"), OFFERING_ALTAR);

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(BLENDER_CATEGORY);
        registry.addCategory(OFFERING_ALTAR_CATEGORY);

        registry.addWorkstation(BLENDER_CATEGORY, BLENDER);
        registry.addWorkstation(OFFERING_ALTAR_CATEGORY, OFFERING_ALTAR);

        registerRecipe(registry, ModCustomRecipes.SMOOTHIE_RECIPE.get(), SmoothieEmiRecipe::new);
        //registerRecipe(registry, ModCustomRecipes.OFFERING_ALTAR_RECIPE.get(), OfferingAltarEmiRecipe::new);
    }

    private <C extends RecipeInput, T extends Recipe<C>> void registerRecipe(EmiRegistry registry, RecipeType<T> recipeType, Function<RecipeHolder<T>, EmiRecipe> func) {
        RecipeManager manager = registry.getRecipeManager();
        manager.getAllRecipesFor(recipeType).forEach(recipe -> {
            registry.addRecipe(func.apply(recipe));
        });
    }
}
*/
