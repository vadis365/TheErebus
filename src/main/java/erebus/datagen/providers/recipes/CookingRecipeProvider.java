package erebus.datagen.providers.recipes;

import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;

/**
 * Provider for food cooking recipes (furnace, smoker and campfire).
 */
public class CookingRecipeProvider extends ErebusRecipeProvider {

    public CookingRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        cook(ModItems.BEETLE_LARVA_RAW, ModItems.BEETLE_LARVA_COOKED, 0.2F);
        cook(ModItems.GRASSHOPPER_LEG_RAW, ModItems.GRASSHOPPER_LEG_COOKED, 0.2F);
        cook(ModItems.TARANTULA_LEG_RAW, ModItems.TARANTULA_LEG_COOKED, 0.2F);
        cook(ModItems.TITAN_CHOP_RAW, ModItems.TITAN_CHOP_COOKED, 0.2F);
        cook(ModItems.PRICKLY_PEAR_RAW, ModItems.PRICKLY_PEAR_COOKED, 0.2F);
        cook(ModItems.TITAN_STEW, ModItems.TITAN_STEW_COOKED, 1.0F);
        cook(ModItems.STAG_HEART_RAW, ModItems.STAG_HEART_COOKED, 1.0F);
    }
}
