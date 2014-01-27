package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.inventory.ContainerUmberFurnace;
import erebus.tileentity.TileEntityUmberFurnace;
import erebus.utils.Utils;

@SideOnly(Side.CLIENT)
public class GuiUmberFurnace extends GuiContainer {

	private static final ResourceLocation GUI_UMBER_FURNACE = new ResourceLocation("erebus:textures/gui/container/umberfurnace.png");
	TileEntityUmberFurnace furnace;
	private int tankXMin, tankYMin, tankXMax, tankYMax;

	public GuiUmberFurnace(InventoryPlayer inventory, TileEntityUmberFurnace tile) {
		super(new ContainerUmberFurnace(inventory, tile));
		furnace = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int color = Utils.getColour(255, 255, 255);
		fontRenderer.drawString(I18n.getString(furnace.getInvName()), xSize / 2 - fontRenderer.getStringWidth(I18n.getString(furnace.getInvName())) / 2, 6, color);
		fontRenderer.drawString(I18n.getString("container.inventory"), xSize - 60, ySize - 96 + 2, color, true);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_UMBER_FURNACE);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

		int i1;
		if (furnace.isBurning()) {
			i1 = furnace.getBurnTimeRemainingScaled(14);
			drawTexturedModalRect(k + 58, l + 51 - i1, 177, 14 - i1, 14, i1);
		}
		i1 = furnace.getCookProgressScaled(24);
		drawTexturedModalRect(k + 79, l + 35, 176, 14, i1 + 1, 16);

		int size = furnace.getScaledFluidAmount(65);
		drawTexturedModalRect(k + 10, l + 75 - size, 176, 96 - size, 18, size);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		tankXMin = (width - xSize) / 2 + 11;
		tankYMin = (height - ySize) / 2 + 11;
		tankXMax = tankXMin + 16;
		tankYMax = tankYMin + 65;
		if (mouseX >= tankXMin && mouseX <= tankXMax)
			if (mouseY >= tankYMin && mouseY <= tankYMax)
				drawToolTip(mouseX, mouseY, furnace.getFluidAmount());
	}

	private void drawToolTip(int mouseX, int mouseY, String text) {
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		int k = 0;
		int l = fontRenderer.getStringWidth(text);

		if (l > k)
			k = l;

		int i1 = mouseX + 12;
		int j1 = mouseY - 12;
		int k1 = 8;

		if (i1 + k > width)
			i1 -= 28 + k;

		if (j1 + k1 + 6 > height)
			j1 = height - k1 - 6;

		zLevel = 300.0F;
		itemRenderer.zLevel = 300.0F;
		int l1 = -267386864;
		drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
		drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
		drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
		drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
		drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
		int i2 = 1347420415;
		int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
		drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
		drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
		drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
		drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

		fontRenderer.drawStringWithShadow(text, i1, j1, -1);

		zLevel = 0.0F;
		itemRenderer.zLevel = 0.0F;
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		RenderHelper.enableStandardItemLighting();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}
}
