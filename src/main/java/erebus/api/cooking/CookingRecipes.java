package erebus.api.cooking;

import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;

public class CookingRecipes {

	public static void addStoveRecipe(ItemStack output, Object... input) {
		try {
			Class<?> StoveRecipe = Class.forName("erebus.recipes.KitchenCounterRecipe");
			Method addRecipe = StoveRecipe.getMethod("addRecipe", ItemStack.class, Object[].class);
			addRecipe.invoke(null, output, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
