package erebus.datagen.providers;

import erebus.Erebus;
import erebus.datagen.providers.recipes.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

/**
 * Main recipe provider that delegates to specialized recipe providers.
 * This class was refactored to reduce its size and improve maintainability.
 */
public class ModRecipeProvider extends RecipeProvider {

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        // Create instances of specialized recipe providers
        CookingRecipeProvider cookingProvider = new CookingRecipeProvider(registries, output);
        SmeltingRecipeProvider smeltingProvider = new SmeltingRecipeProvider(registries, output);
        ShapedBuildingRecipeProvider buildingProvider = new ShapedBuildingRecipeProvider(registries, output);
        ShapedToolsAndWeaponsRecipeProvider toolsProvider = new ShapedToolsAndWeaponsRecipeProvider(registries, output);
        ShapedArmorRecipeProvider armorProvider = new ShapedArmorRecipeProvider(registries, output);
        ShapedMiscRecipeProvider miscProvider = new ShapedMiscRecipeProvider(registries, output);
        ShapelessCraftingRecipeProvider shapelessProvider = new ShapelessCraftingRecipeProvider(registries, output);
        CustomRecipeProvider customProvider = new CustomRecipeProvider(registries, output);

        // Call buildRecipes on each provider
        cookingProvider.buildRecipes();
        smeltingProvider.buildRecipes();
        buildingProvider.buildRecipes();
        toolsProvider.buildRecipes();
        armorProvider.buildRecipes();
        miscProvider.buildRecipes();
        shapelessProvider.buildRecipes();
        customProvider.buildRecipes();
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NonNull String getName() {
            return Erebus.MODID;
        }
    }
}
