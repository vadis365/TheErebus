package erebus.api.altars;

import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;

public class RecipeHelper {
	public static void addOfferingAltarRecipe(ItemStack output, Object... inputs) {
		try {
			Class<?> OfferingAltarRecipe = Class.forName("erebus.recipes.OfferingAltarRecipe");
			Method addRecipe = OfferingAltarRecipe.getMethod("addRecipe", ItemStack.class, Object[].class);
			addRecipe.invoke(null, output, inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addCraftingAltarRecipe(ItemStack output, Object focusItem, Object... inputs) {
		try {
			Class<?> CraftingAltarRecipe = Class.forName("erebus.recipes.CraftingAltarRecipe");
			Method addRecipe = CraftingAltarRecipe.getMethod("addRecipe", ItemStack.class, Object.class, Object[].class);
			addRecipe.invoke(null, output, focusItem, inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}