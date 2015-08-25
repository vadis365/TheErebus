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
}