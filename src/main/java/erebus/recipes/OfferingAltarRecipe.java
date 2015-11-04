package erebus.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import erebus.core.helper.Utils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OfferingAltarRecipe {
	private static final List<OfferingAltarRecipe> list = new ArrayList<OfferingAltarRecipe>();

	public static List<OfferingAltarRecipe> getRecipeList() {
		return Collections.unmodifiableList(list);
	}

	public static void addRecipe(ItemStack output, Object... inputs) {
		list.add(new OfferingAltarRecipe(output, inputs));
	}

	public static ItemStack getOutput(ItemStack input1, ItemStack input2, ItemStack input3) {
		for (OfferingAltarRecipe recipe : list)
			if (recipe.matches(input1, input2, input3))
				return recipe.getOutput();
		return null;
	}

	private final ItemStack output;
	private final Object[] inputs;

	private OfferingAltarRecipe(ItemStack output, Object... inputs) {
		this.output = output;
		this.inputs = inputs;
		if (inputs.length > 3)
			throw new IllegalArgumentException("Must not have more than 3 inputs.");

		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i] == null)
				throw new IllegalArgumentException("Input must not be null.");
			boolean isStack = inputs[i] instanceof ItemStack;
			boolean isString = inputs[i] instanceof String;
			if (isString)
				inputs[i] = OreDictionary.getOres((String) inputs[i]);
			else if (!isStack)
				throw new IllegalArgumentException("Input must not be an ItemStack or a String.");
		}
	}

	@SuppressWarnings("unchecked")
	public boolean matches(ItemStack input1, ItemStack input2, ItemStack input3) {
		ItemStack[] stacks = { input1, input2, input3 };
		label: for (Object input : inputs) {
			for (int i = 0; i < stacks.length; i++)
				if (stacks[i] != null)
					if (input instanceof ItemStack) {
						if (Utils.areStacksTheSame((ItemStack) input, stacks[i], false)) {
							stacks[i] = null;
							continue label;
						}
					} else
						for (ItemStack s : (ArrayList<ItemStack>) input)
							if (Utils.areStacksTheSame(s, stacks[i], false)) {
								stacks[i] = null;
								continue label;
							}

			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean isPartOfInput(ItemStack stack) {
		if (stack == null)
			return false;
		for (Object input : inputs)
			if (input instanceof ItemStack) {
				if (Utils.areStacksTheSame((ItemStack) input, stack, false))
					return true;
			} else
				for (ItemStack s : (ArrayList<ItemStack>) input)
					if (Utils.areStacksTheSame(s, stack, false))
						return true;
		return false;
	}

	public Object[] getInputs() {
		return inputs;
	}

	public ItemStack getOutput() {
		return ItemStack.copyItemStack(output);
	}
}