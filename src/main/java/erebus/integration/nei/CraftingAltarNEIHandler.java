package erebus.integration.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import erebus.core.helper.Utils;
import erebus.recipes.CraftingAltarRecipe;

public class CraftingAltarNEIHandler extends TemplateRecipeHandler {
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("tile.erebus.craftingAltar.name");
	}

	public String getRecipeId() {
		return "erebus.craftingAltar";
	}

	@Override
	public String getGuiTexture() {
		return "erebus:textures/gui/nei/craftingAltar.png";
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	@Override
	public void drawBackground(int recipe) {
		GL11.glColor4f(1, 1, 1, 1);
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(0, 15, 0, 0, 200, 100);
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(107, 57, 25, 16), getRecipeId()));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(getRecipeId()))
			for (CraftingAltarRecipe recipe : CraftingAltarRecipe.getRecipeList())
				arecipes.add(new CachedCraftingAltarRecipe(recipe));
		else
			super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (CraftingAltarRecipe recipe : CraftingAltarRecipe.getRecipeList())
			if (Utils.areStacksTheSame(result, recipe.getOutput(), false))
				arecipes.add(new CachedCraftingAltarRecipe(recipe));
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (CraftingAltarRecipe recipe : CraftingAltarRecipe.getRecipeList())
			if (recipe.isPartOfInput(ingredient))
				arecipes.add(new CachedCraftingAltarRecipe(recipe));
	}

	private class CachedCraftingAltarRecipe extends CachedRecipe {

		private final List<PositionedStack> inputs;
		private final PositionedStack result, focus;

		private CachedCraftingAltarRecipe(CraftingAltarRecipe recipe) {
			result = new PositionedStack(recipe.getOutput(), 145, 16 + 18 + 23);
			focus = new PositionedStack(recipe.getFocusItem(), 42, 16 + 18 + 23);

			int y = 16;
			int x = 16;

			List<?> input = Arrays.asList(recipe.getInputs());
			Collections.shuffle(input);

			inputs = new ArrayList<PositionedStack>();
			if (input.size() >= 1 && input.get(0) != null)
				inputs.add(new PositionedStack(input.get(0), x + 26, y));
			if (input.size() >= 2 && input.get(1) != null)
				inputs.add(new PositionedStack(input.get(1), x + 26 + 23, y + 18));
			if (input.size() >= 3 && input.get(2) != null)
				inputs.add(new PositionedStack(input.get(2), x + 26 + 23 + 18, y + 18 + 23));
			if (input.size() >= 4 && input.get(3) != null)
				inputs.add(new PositionedStack(input.get(3), x + 26 + 23, y + 18 + 23 + 23));
			if (input.size() >= 5 && input.get(4) != null)
				inputs.add(new PositionedStack(input.get(4), x + 26, y + 18 + 23 + 23 + 18));
			if (input.size() >= 6 && input.get(5) != null)
				inputs.add(new PositionedStack(input.get(5), x + 26 - 23, y + 18 + 23 + 23));
			if (input.size() >= 7 && input.get(6) != null)
				inputs.add(new PositionedStack(input.get(6), x + 26 - 23 - 18, y + 18 + 23));
			if (input.size() >= 8 && input.get(7) != null)
				inputs.add(new PositionedStack(input.get(7), x + 26 - 23, y + 18));
		}

		@Override
		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, inputs);
		}

		@Override
		public PositionedStack getResult() {
			return result;
		}

		@Override
		public PositionedStack getOtherStack() {
			return focus;
		}
	}
}