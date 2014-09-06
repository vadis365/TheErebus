package erebus.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import erebus.core.helper.Utils;

public class CraftingAltarRecipe
{

	private static final List<CraftingAltarRecipe> recipes = new ArrayList<CraftingAltarRecipe>();

	/**
	 *
	 * @param output
	 *            what will be produced by the recipe
	 * @param focusItem
	 *            item that will go in the centre
	 * @param inputs
	 *            must contain less than 8 items
	 */
	public static void addRecipe(ItemStack output, Object focusItem, Object... inputs)
	{
		recipes.add(new CraftingAltarRecipe(output, focusItem, inputs));
	}

	public static ItemStack getOutput(ItemStack focusItem, ItemStack... inputs)
	{
		for (CraftingAltarRecipe recipe : recipes)
		{
			if (recipe.matches(focusItem, inputs))
			{
				return recipe.getOutput();
			}
		}

		return null;
	}

	public static List<CraftingAltarRecipe> getRecipeList()
	{
		return Collections.unmodifiableList(recipes);
	}

	private final ItemStack output;
	private final Object focusItem;
	private final Object[] inputs;

	private CraftingAltarRecipe(ItemStack output, Object focusItem, Object... inputs)
	{
		this.output = ItemStack.copyItemStack(output);
		this.inputs = new Object[inputs.length];

		if (inputs.length > 8)
		{
			throw new IllegalArgumentException("Max input size is 8.");
		}

		for (int i = 0; i < inputs.length; i++)
		{
			if (inputs[i] instanceof ItemStack)
			{
				this.inputs[i] = ItemStack.copyItemStack((ItemStack) inputs[i]);
			} else if (inputs[i] instanceof String)
			{
				this.inputs[i] = OreDictionary.getOres((String) inputs[i]);
			} else
			{
				throw new IllegalArgumentException("Input must be an ItemStack or a String.");
			}
		}

		if (focusItem instanceof ItemStack)
		{
			this.focusItem = focusItem;
		} else if (focusItem instanceof String)
		{
			this.focusItem = OreDictionary.getOres((String) focusItem);
		} else
		{
			throw new IllegalArgumentException("Focus item must be an ItemStack or a String.");
		}
	}

	public Object[] getInputs()
	{
		return inputs;
	}

	public Object getFocusItem()
	{
		return focusItem;
	}

	public ItemStack getOutput()
	{
		return ItemStack.copyItemStack(output);
	}

	public boolean isPartOfInput(ItemStack ingredient)
	{
		for (Object input : inputs)
		{
			if (areStacksTheSame(input, ingredient))
			{
				return true;
			}
		}

		return areStacksTheSame(focusItem, ingredient);
	}

	public boolean matches(ItemStack focusItem, ItemStack... stacks)
	{
		if (!areStacksTheSame(this.focusItem, focusItem))
		{
			return false;
		}

		label: for (Object input : inputs)
		{
			for (int i = 0; i < stacks.length; i++)
			{
				if (stacks[i] != null)
				{
					if (areStacksTheSame(input, stacks[i]))
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

	@SuppressWarnings("unchecked")
	private boolean areStacksTheSame(Object obj, ItemStack target)
	{
		if (obj instanceof ItemStack)
		{
			return Utils.areStacksTheSame((ItemStack) obj, target, false);
		} else if (obj instanceof List)
		{
			List<ItemStack> list = (List<ItemStack>) obj;
			for (ItemStack stack : list)
			{
				if (Utils.areStacksTheSame(stack, target, false))
				{
					return true;
				}
			}
		}

		return false;
	}
}