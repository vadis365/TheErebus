package erebus.datagen.providers.recipes;

import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

/**
 * Provider for cooking recipes (smoking, campfire cooking).
 */
public class CookingRecipeProvider extends ErebusRecipeProvider {

    public CookingRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    public void buildRecipes() {
        cook(ModItems.BEETLE_LARVA_RAW, ModItems.BEETLE_LARVA_COOKED);
        cook(ModItems.GRASSHOPPER_LEG_RAW, ModItems.GRASSHOPPER_LEG_COOKED);
        cook(ModItems.TARANTULA_LEG_RAW, ModItems.TARANTULA_LEG_COOKED);
        cook(ModItems.TITAN_CHOP_RAW, ModItems.TITAN_CHOP_COOKED);
        cook(ModItems.PRICKLY_PEAR_RAW, ModItems.PRICKLY_PEAR_COOKED);
        cook(ModItems.TITAN_STEW, ModItems.TITAN_STEW_COOKED);
        cook(ModItems.STAG_HEART_RAW, ModItems.STAG_HEART_COOKED);
    }
}