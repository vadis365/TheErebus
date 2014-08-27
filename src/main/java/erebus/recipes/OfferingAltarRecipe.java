package erebus.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import erebus.core.helper.Utils;

public class OfferingAltarRecipe
{
	private static final List<OfferingAltarRecipe> list = new ArrayList<OfferingAltarRecipe>();

	public static List<OfferingAltarRecipe> getRecipeList()
	{
		return Collections.unmodifiableList(list);
	}

	public static void addRecipe(ItemStack output, ItemStack... inputs)
	{
		list.add(new OfferingAltarRecipe(output, inputs));
	}

	public static ItemStack getOutput(ItemStack input1, ItemStack input2, ItemStack input3)
	{
		for (OfferingAltarRecipe recipe : list)
		{
			if (recipe.matches(input1, input2, input3))
			{
				return recipe.getOutput();
			}
		}
		return null;
	}

	private final ItemStack output;
	private final ItemStack[] inputs;

	private OfferingAltarRecipe(ItemStack output, ItemStack... inputs)
	{
		this.output = output;
		this.inputs = inputs;

		for (int i = 0; i < inputs.length; i++)
		{
			if (inputs[i] == null || inputs[i].stackSize != 1)
			{
				throw new IllegalArgumentException("Input must not be null and be of size 1");
			}
		}
	}

	public boolean matches(ItemStack input1, ItemStack input2, ItemStack input3)
	{
		ItemStack[] stacks = { input1, input2, input3 };
		label: for (ItemStack input : inputs)
		{
			for (int i = 0; i < stacks.length; i++)
			{
				if (stacks[i] != null)
				{
					if (Utils.areStacksTheSame(input, stacks[i], false) && stacks[i].stackSize >= input.stackSize)
					{
						stacks[i] = null;
						continue label;
					}
				}
			}

			return false;
		}

		return true;
	}

	public boolean isPartOfInput(ItemStack stack)
	{
		if (stack == null)
		{
			return false;
		}
		for (ItemStack input : inputs)
		{
			if (Utils.areStacksTheSame(input, stack, false) && stack.stackSize >= input.stackSize)
			{
				return true;
			}
		}
		return false;
	}

	public ItemStack[] getInputs()
	{
		return inputs;
	}

	public ItemStack getOutput()
	{
		return ItemStack.copyItemStack(output);
	}
}