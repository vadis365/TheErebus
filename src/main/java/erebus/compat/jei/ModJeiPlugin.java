package erebus.compat.jei;

import erebus.Erebus;
import erebus.recipes.altar.OfferingAltarRecipe;
import erebus.recipes.smoothie.SmoothieRecipe;
import erebus.registries.ModCustomRecipes;
import erebus.registries.blocks.ModBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;

import java.util.List;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
    static final IRecipeHolderType<SmoothieRecipe> BLENDING = IRecipeHolderType.create(Erebus.prefix("smoothie"));
    static final IRecipeHolderType<OfferingAltarRecipe> OFFERING_ALTAR = IRecipeHolderType.create(Erebus.prefix("offering_altar"));

    @Override
    public Identifier getPluginUid() {
        return Erebus.prefix("jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        var gui = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new BlendingCategory(gui), new OfferingAltarCategory(gui));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var recipes = ClientRecipeSync.recipes();
        registration.addRecipes(BLENDING, List.copyOf(recipes.byType(ModCustomRecipes.SMOOTHIE_RECIPE.get())));
        registration.addRecipes(OFFERING_ALTAR, List.copyOf(recipes.byType(ModCustomRecipes.OFFERING_ALTAR_RECIPE.get())));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(BLENDING, ModBlocks.BLENDER.get());
        registration.addCraftingStation(OFFERING_ALTAR, ModBlocks.OFFERING_ALTAR.get());
    }
}
