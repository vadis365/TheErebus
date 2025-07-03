package erebus.datagen.providers;

import erebus.datagen.providers.recipes.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Main recipe provider that delegates to specialized recipe providers.
 * This class was refactored to reduce its size and improve maintainability.
 */
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private final PackOutput packOutput;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public ModRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
        this.packOutput = packOutput;
        this.registries = registries;
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        // Create instances of specialized recipe providers
        CookingRecipeProvider cookingProvider = new CookingRecipeProvider(packOutput, registries);
        SmeltingRecipeProvider smeltingProvider = new SmeltingRecipeProvider(packOutput, registries);
        ShapedBuildingRecipeProvider buildingProvider = new ShapedBuildingRecipeProvider(packOutput, registries);
        ShapedToolsAndWeaponsRecipeProvider toolsProvider = new ShapedToolsAndWeaponsRecipeProvider(packOutput, registries);
        ShapedArmorRecipeProvider armorProvider = new ShapedArmorRecipeProvider(packOutput, registries);
        ShapedMiscRecipeProvider miscProvider = new ShapedMiscRecipeProvider(packOutput, registries);
        ShapelessCraftingRecipeProvider shapelessProvider = new ShapelessCraftingRecipeProvider(packOutput, registries);
        CustomRecipeProvider customProvider = new CustomRecipeProvider(packOutput, registries);

        // Call buildRecipes on each provider
        cookingProvider.buildRecipes(output);
        smeltingProvider.buildRecipes(output);
        buildingProvider.buildRecipes(output);
        toolsProvider.buildRecipes(output);
        armorProvider.buildRecipes(output);
        miscProvider.buildRecipes(output);
        shapelessProvider.buildRecipes(output);
        customProvider.buildRecipes(output);
    }
}
