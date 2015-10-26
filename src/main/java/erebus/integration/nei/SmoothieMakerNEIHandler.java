package erebus.integration.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.client.gui.GuiSmoothieMaker;
import erebus.core.helper.Utils;
import erebus.item.ItemMaterials;
import erebus.recipes.SmoothieMakerRecipe;

public class SmoothieMakerNEIHandler extends TemplateRecipeHandler {

	@Override
	public void drawBackground(int index) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(0, 0, 5, 3, 167, 80);

		int x = -5;
		int y = -3;
		int amount = 80;
		CachedSmoothieMakerRecipe recipe = (CachedSmoothieMakerRecipe) arecipes.get(index);
		FluidStack[] fluids = recipe.getFluids();
		for (FluidStack fluid : fluids) {
			if (fluid.getFluid() == ModFluids.honey)
				GuiDraw.drawTexturedModalRect(x + 8, y + 80 - amount, 8, 248 - amount, 9, amount);
			if (fluid.getFluid() == ModFluids.beetleJuice)
				GuiDraw.drawTexturedModalRect(x + 25, y + 80 - amount, 25, 248 - amount, 9, amount);
			if (fluid.getFluid() == ModFluids.antiVenom)
				GuiDraw.drawTexturedModalRect(x + 142, y + 80 - amount, 142, 248 - amount, 9, amount);
			if (fluid.getFluid() == ModFluids.milk)
				GuiDraw.drawTexturedModalRect(x + 159, y + 80 - amount, 159, 248 - amount, 9, amount);
		}
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiSmoothieMaker.class;
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("tile.erebus.smoothieMaker.name");
	}

	public String getRecipeId() {
		return "erebus.smoothieMaker";
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	@Override
	public String getGuiTexture() {
		return "erebus:textures/gui/container/smoothieMaker.png";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(67, 45, 32, 16), getRecipeId()));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(getRecipeId()))
			for (SmoothieMakerRecipe recipe : SmoothieMakerRecipe.getRecipeList())
				arecipes.add(new CachedSmoothieMakerRecipe(recipe));
		else
			super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (SmoothieMakerRecipe recipe : SmoothieMakerRecipe.getRecipeList())
			if (Utils.areStacksTheSame(result, recipe.getOutput(), false))
				arecipes.add(new CachedSmoothieMakerRecipe(recipe));
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (SmoothieMakerRecipe recipe : SmoothieMakerRecipe.getRecipeList())
			if (recipe.isPartOfInput(ingredient) || ingredient.getItem() == ModItems.materials && ingredient.getItemDamage() == ItemMaterials.DATA.smoothieGlass.ordinal())
				arecipes.add(new CachedSmoothieMakerRecipe(recipe));
	}

	private class CachedSmoothieMakerRecipe extends CachedRecipe {

		private final List<PositionedStack> inputs;
		private final FluidStack[] fluids;
		private final PositionedStack result;

		private CachedSmoothieMakerRecipe(SmoothieMakerRecipe recipe) {
			result = new PositionedStack(recipe.getOutput(), 75, 60);
			fluids = recipe.getFluids();

			int y = 16;
			int x = 16;

			List<?> input = Arrays.asList(recipe.getInputs());
			Collections.shuffle(input);

			inputs = new ArrayList<PositionedStack>();
			if (input.size() >= 1 && input.get(0) != null)
				inputs.add(new PositionedStack(input.get(0), x + 26, y - 10));
			if (input.size() >= 2 && input.get(1) != null)
				inputs.add(new PositionedStack(input.get(1), x + 47, y + 11));
			if (input.size() >= 3 && input.get(2) != null)
				inputs.add(new PositionedStack(input.get(2), x + 53 + 18, y + 11));
			if (input.size() >= 4 && input.get(3) != null)
				inputs.add(new PositionedStack(input.get(3), x + 53 + 36 + 3, y - 10));
		}

		public FluidStack[] getFluids() {
			return fluids;
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