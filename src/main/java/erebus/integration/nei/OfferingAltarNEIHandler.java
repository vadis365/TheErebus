package erebus.integration.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import erebus.core.helper.Utils;
import erebus.recipes.OfferingAltarRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class OfferingAltarNEIHandler extends TemplateRecipeHandler {
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("tile.erebus.offeringAltar.name");
	}

	public String getRecipeId() {
		return "erebus.offeringAltar";
	}

	@Override
	public String getGuiTexture() {
		return "erebus:textures/gui/nei/offeringAltar.png";
	}

	@Override
	public void drawBackground(int recipe) {
		GL11.glColor4f(1, 1, 1, 1);
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(25, 15, 0, 0, 137, 65);
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(87, 25, 25, 16), getRecipeId()));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(getRecipeId()))
			for (OfferingAltarRecipe recipe : OfferingAltarRecipe.getRecipeList())
				arecipes.add(new CachedOfferingAltarRecipe(recipe));
		else
			super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (OfferingAltarRecipe recipe : OfferingAltarRecipe.getRecipeList())
			if (Utils.areStacksTheSame(result, recipe.getOutput(), false))
				arecipes.add(new CachedOfferingAltarRecipe(recipe));
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (OfferingAltarRecipe recipe : OfferingAltarRecipe.getRecipeList())
			if (recipe.isPartOfInput(ingredient))
				arecipes.add(new CachedOfferingAltarRecipe(recipe));
	}

	private class CachedOfferingAltarRecipe extends CachedRecipe {

		private final List<PositionedStack> inputs;
		private final PositionedStack result;

		private CachedOfferingAltarRecipe(OfferingAltarRecipe recipe) {
			result = new PositionedStack(recipe.getOutput(), 127, 24);

			int y = 16;
			int x = 16;

			List<?> input = Arrays.asList(recipe.getInputs());
			Collections.shuffle(input);

			inputs = new ArrayList<PositionedStack>();
			if (input.size() >= 1 && input.get(0) != null)
				inputs.add(new PositionedStack(input.get(0), x + 28, y));
			if (input.size() >= 2 && input.get(1) != null)
				inputs.add(new PositionedStack(input.get(1), x + 28 - 18, y + 18));
			if (input.size() >= 3 && input.get(2) != null)
				inputs.add(new PositionedStack(input.get(2), x + 28 + 18, y + 18));
		}

		@Override
		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, inputs);
		}

		@Override
		public PositionedStack getResult() {
			return result;
		}
	}
}