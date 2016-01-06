package erebus.integration.nei;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import erebus.ModItems;
import erebus.client.gui.GuiSmoothieMaker;
import erebus.core.helper.Utils;
import erebus.item.ItemMaterials;
import erebus.recipes.SmoothieMakerRecipe;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

public class SmoothieMakerNEIHandler extends TemplateRecipeHandler {

	@Override
	public final void drawForeground(int recipe) {
		super.drawForeground(recipe);

		CachedSmoothieMakerRecipe rec = (CachedSmoothieMakerRecipe) arecipes.get(recipe);
		FluidStack[] fluids = rec.getFluids();
		for (int i = 0; i < fluids.length; i++)
			draw(GuiSmoothieMaker.tankPositions[i], fluids[i], 16000);
	}

	void draw(Rectangle rectangle, FluidStack fluid, int capacity) {
		if (fluid == null || fluid.getFluid() == null || fluid.amount <= 0)
			return;
		IIcon fluidIcon = fluid.getFluid().getStillIcon();
		if (fluidIcon == null)
			return;

		GuiDraw.changeTexture(TextureMap.locationBlocksTexture);
		int colour = fluid.getFluid().getColor(fluid);
		float r = (colour >> 16 & 255) / 255F;
		float g = (colour >> 8 & 255) / 255F;
		float b = (colour & 255) / 255F;
		int a = colour >> 24 & 255;
		if (a <= 0)
			a = 255;

		GL11.glColor4f(r, g, b, a / 255F);
		GL11.glEnable(GL11.GL_BLEND);

		int amount = Math.max(Math.min(rectangle.height, fluid.amount * rectangle.height / capacity), 1);
		int posY = rectangle.y + rectangle.height - amount;

		for (int i = 0; i < rectangle.width; i += 16)
			for (int j = 0; j < amount; j += 16) {
				int drawWidth = Math.min(rectangle.width - i, 16);
				int drawHeight = Math.min(amount - j, 16);

				int drawX = rectangle.x + i - 5;
				int drawY = posY + j - 3;

				double minU = fluidIcon.getMinU();
				double maxU = fluidIcon.getMaxU();
				double minV = fluidIcon.getMinV();
				double maxV = fluidIcon.getMaxV();

				Tessellator tessellator = Tessellator.instance;
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(drawX, drawY + drawHeight, 0, minU, minV + (maxV - minV) * drawHeight / 16F);
				tessellator.addVertexWithUV(drawX + drawWidth, drawY + drawHeight, 0, minU + (maxU - minU) * drawWidth / 16F, minV + (maxV - minV) * drawHeight / 16F);
				tessellator.addVertexWithUV(drawX + drawWidth, drawY, 0, minU + (maxU - minU) * drawWidth / 16F, minV);
				tessellator.addVertexWithUV(drawX, drawY, 0, minU, minV);
				tessellator.draw();
			}

		GuiDraw.changeTexture(getGuiTexture());
		GL11.glColor3f(1, 1, 1);
		GuiDraw.drawTexturedModalRect(rectangle.x - 5, rectangle.y, 176, 41, rectangle.width, rectangle.height);
	}

	@Override
	public void drawBackground(int index) {
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(0, 0, 5, 3, 167, 80);
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
		transferRects.add(new RecipeTransferRect(new Rectangle(67, 38, 32, 12), getRecipeId()));
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
			if (recipe.isPartOfInput(ingredient) || ingredient.getItem() == ModItems.materials && ingredient.getItemDamage() == ItemMaterials.DATA.SMOOTHIE_GLASS.ordinal())
				arecipes.add(new CachedSmoothieMakerRecipe(recipe));
	}

	@Override
	public final List<String> handleTooltip(GuiRecipe guiRecipe, List<String> currenttip, int recipe) {
		super.handleTooltip(guiRecipe, currenttip, recipe);
		CachedSmoothieMakerRecipe crecipe = (CachedSmoothieMakerRecipe) arecipes.get(recipe);
		if (GuiContainerManager.shouldShowTooltip(guiRecipe)) {
			Point mouse = GuiDraw.getMousePosition();
			Point offset = guiRecipe.getRecipePosition(recipe);
			Point relMouse = new Point(mouse.x - (guiRecipe.width - 176) / 2 - offset.x + 5, mouse.y - (guiRecipe.height - 166) / 2 - offset.y);

			FluidStack[] fluids = crecipe.getFluids();
			for (int i = 0; i < fluids.length; i++)
				if (GuiSmoothieMaker.tankPositions[i].contains(relMouse)) {
					currenttip.add(fluids[i].getLocalizedName());
					currenttip.add(fluids[i].amount + "mB");
				}
		}
		return currenttip;
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