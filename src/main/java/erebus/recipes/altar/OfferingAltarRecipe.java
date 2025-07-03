package erebus.recipes.altar;

import erebus.recipes.util.MultiStackInput;
import erebus.registries.ModCustomRecipes;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface OfferingAltarRecipe extends Recipe<MultiStackInput> {

	@Override
	default boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	default RecipeType<?> getType() {
		return ModCustomRecipes.OFFERING_ALTAR_RECIPE.get();
	}

	@Override
	default boolean isIncomplete() {
		return true;
	}
}
