package erebus.integration.nei;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import erebus.core.helper.Utils;
import erebus.recipes.OfferingAltarRecipe;

public class OfferingAltarNEIHandler extends TemplateRecipeHandler
{
	@Override
	public String getRecipeName()
	{
		return StatCollector.translateToLocal("tile.erebus.offeringAltar.name");
	}

	public String getRecipeId()
	{
		return "erebus.offeringAltar";
	}

	@Override
	public String getGuiTexture()
	{
		return "erebus:textures/gui/nei/offeringAltar.png";
	}

	@Override
	public void drawBackground(int recipe)
	{
		GL11.glColor4f(1, 1, 1, 1);
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(25, 15, 0, 0, 137, 65);
	}

	@Override
	public void loadTransferRects()
	{
		transferRects.add(new RecipeTransferRect(new Rectangle(87, 25, 25, 16), getRecipeId()));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if (outputId.equals(getRecipeId()))
		{
			for (OfferingAltarRecipe recipe : OfferingAltarRecipe.getRecipeList())
			{
				arecipes.add(new CachedEnderFurnaceRecipe(recipe));
			}
		} else
		{
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for (OfferingAltarRecipe recipe : OfferingAltarRecipe.getRecipeList())
		{
			if (Utils.areStacksTheSame(result, recipe.getOutput(), false))
			{
				arecipes.add(new CachedEnderFurnaceRecipe(recipe));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		for (OfferingAltarRecipe recipe : OfferingAltarRecipe.getRecipeList())
		{
			if (recipe.isPartOfInput(ingredient))
			{
				arecipes.add(new CachedEnderFurnaceRecipe(recipe));
			}
		}
	}

	class CachedEnderFurnaceRecipe extends CachedRecipe
	{

		private final PositionedStack[] inputs;
		private final PositionedStack result;

		CachedEnderFurnaceRecipe(OfferingAltarRecipe recipe)
		{
			result = new PositionedStack(recipe.getOutput(), 127, 24);

			int y = 16;
			int x = 16;

			ItemStack[] input = recipe.getInputs();
			inputs = new PositionedStack[input.length];
			if (input[0] != null)
			{
				inputs[0] = new PositionedStack(input[0], x + 28, y);
			}
			if (input[1] != null)
			{
				inputs[1] = new PositionedStack(input[1], x + 28 - 18, y + 18);
			}
			if (input[2] != null)
			{
				inputs[2] = new PositionedStack(input[2], x + 28 + 18, y + 18);
			}
		}

		@Override
		public List<PositionedStack> getIngredients()
		{
			return getCycledIngredients(cycleticks / 20, Arrays.asList(inputs));
		}

		@Override
		public PositionedStack getResult()
		{
			return result;
		}
	}
}