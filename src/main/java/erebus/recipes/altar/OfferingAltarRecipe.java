package erebus.recipes.altar;

import erebus.recipes.util.MultiStackInput;
import erebus.registries.ModCustomRecipes;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jspecify.annotations.NonNull;

public interface OfferingAltarRecipe extends Recipe<MultiStackInput> {

	@Override
	default @NonNull RecipeType<? extends Recipe<MultiStackInput>> getType() {
		return ModCustomRecipes.OFFERING_ALTAR_RECIPE.get();
	}
}
