package erebus.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import erebus.core.helper.Utils;

public class SmoothieMakerRecipe {
	private static final List<SmoothieMakerRecipe> recipes = new ArrayList<SmoothieMakerRecipe>();

	public static void addRecipe(ItemStack output, Object... input) {
		recipes.add(new SmoothieMakerRecipe(output, input));
	}

	public static ItemStack getOutput(ItemStack... input) {
		for (SmoothieMakerRecipe recipe : recipes)
			if (recipe.matches(input))
				return recipe.getOutput();

		return null;
	}

	public static List<SmoothieMakerRecipe> getRecipeList() {
		return Collections.unmodifiableList(recipes);
	}

	private final ItemStack output;
	private final Object[] input;

	private SmoothieMakerRecipe(ItemStack output, Object... input) {
		this.output = ItemStack.copyItemStack(output);
		this.input = new Object[input.length];

		if (input.length > 4)
			throw new IllegalArgumentException("Input must be 1 to 4.");

		for (int c = 0; c < input.length; c++)
			if (input[c] instanceof ItemStack)
				this.input[c] = ItemStack.copyItemStack((ItemStack) input[c]);
			else if (input[c] instanceof String)
				this.input[c] = OreDictionary.getOres((String) input[c]);
			else
				throw new IllegalArgumentException("Input must be an ItemStack or an OreDictionary name");
	}

	public Object[] getInputs() {
		return input;
	}

	public ItemStack getOutput() {
		return ItemStack.copyItemStack(output);
	}

	public boolean matches(ItemStack... stacks) {
		label: for (Object input : this.input) {
			for (int c = 0; c < stacks.length; c++)
				if (stacks[c] != null)
					if (areStacksTheSame(input, stacks[c])) {
						stacks[c] = null;
						continue label;
					}

			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean areStacksTheSame(Object obj, ItemStack target) {
		if (obj instanceof ItemStack)
			return Utils.areStacksTheSame((ItemStack) obj, target, false);
		else if (obj instanceof List) {
			List<ItemStack> list = (List<ItemStack>) obj;

			for (ItemStack stack : list)
				if (Utils.areStacksTheSame(stack, target, false))
					return true;
		}

		return false;
	}
}
