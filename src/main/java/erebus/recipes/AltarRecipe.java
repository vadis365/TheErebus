package erebus.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AltarRecipe
{

	private static final ArrayList<AltarRecipe> recipeRegistry = new ArrayList<AltarRecipe>();

	public static void init()
	{
		// example recipe, delete when real ones are added
		addRecipe(new ItemStack(Items.arrow), new ItemStack(Blocks.anvil), new ItemStack(Items.apple));
	}

	/**
	 *
	 * @param output
	 *            what will be produced by the recipe
	 * @param focusItem
	 *            item that will go in the centre
	 * @param inputs
	 *            must not be null and must contain less than 8 items
	 */
	public static void addRecipe(ItemStack output, ItemStack focusItem, ItemStack... inputs)
	{
		if (output == null || focusItem == null || inputs == null || inputs.length >= 8)
		{
			return;
		}
		AltarRecipe recipe = new AltarRecipe(output, focusItem, inputs);
		if (!recipeRegistry.contains(recipe))
		{
			recipeRegistry.add(recipe);
		}
	}

	public static ItemStack getOutput(ItemStack focusItem, ItemStack... inputs)
	{
		AltarRecipe recipe = new AltarRecipe(null, focusItem, inputs);
		if (recipeRegistry.contains(recipe))
		{
			ItemStack output = recipeRegistry.get(recipeRegistry.indexOf(recipe)).output;
			return output != null ? output.copy() : null;
		}
		return null;
	}

	private final ItemStack output;
	private final UnsizedStack focusItem;
	private final UnsizedStack[] inputs;

	public AltarRecipe(ItemStack output, ItemStack focusItem, ItemStack... inputs)
	{
		this.output = output == null ? null : output.copy();
		this.focusItem = new UnsizedStack(focusItem);
		UnsizedStack[] temp = new UnsizedStack[inputs.length];
		for (int i = 0; i < inputs.length; i++)
		{
			temp[i] = new UnsizedStack(inputs[i]);
		}

		List<UnsizedStack> list = Arrays.asList(temp);
		Collections.sort(list);
		this.inputs = list.toArray(new UnsizedStack[0]);
	}

	@Override
	public int hashCode()
	{
		return new HashCodeBuilder().append(focusItem).append(inputs).hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof AltarRecipe))
		{
			return false;
		}

		AltarRecipe recipe = (AltarRecipe) obj;
		return new EqualsBuilder().append(focusItem, recipe.focusItem).append(inputs, recipe.inputs).build();
	}
}