package erebus.client.gui;

import erebus.core.helper.Utils;
import erebus.inventory.ContainerUmberFurnace;
import erebus.tileentity.TileEntityUmberFurnace;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiUmberFurnace extends GuiErebus {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/gui/container/umber_furnace.png");
	private final TileEntityUmberFurnace furnace;
	private int tankXMin, tankYMin, tankXMax, tankYMax;

	public GuiUmberFurnace(InventoryPlayer inventory, TileEntityUmberFurnace tile) {
		super(new ContainerUmberFurnace(inventory, tile));
		furnace = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int color = Utils.getColour(255, 255, 255);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation(furnace.getName()).getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation(furnace.getName()).getFormattedText())) / 2 + 1, 7, Utils.getColour(0, 0, 0));
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText())) / 2 + 56, ySize - 96 + 3, Utils.getColour(0, 0, 0));
		fontRenderer.drawString(I18n.format(new TextComponentTranslation(furnace.getName()).getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation(furnace.getName()).getFormattedText())) / 2, 6, color);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText())) / 2 + 55, ySize - 96 + 2, color);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (furnace.isBurning()) {
			float burnTime = furnace.getBurnTimeRemainingScaled(14);
			drawTexturedModalRectFloat(guiLeft + 58, guiTop + 51 - burnTime, 177, 14 - burnTime, 14, burnTime);
		}
		float currentCookProgress = furnace.getCookProgressScaled(24);
		float prevCookProgress = furnace.getPrevCookProgressScaled(24);
		float cookProcess = currentCookProgress + (currentCookProgress - prevCookProgress) * partialTickTime;
		drawTexturedModalRectFloat(guiLeft + 79, guiTop + 35, 176, 14, cookProcess + 1, 16);

		int size = furnace.getScaledFluidAmount(65);
		drawTexturedModalRect(guiLeft + 10, guiTop + 75 - size, 176, 96 - size, 18, size);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTickTime) {
		super.drawScreen(mouseX, mouseY, partialTickTime);
		tankXMin = (width - xSize) / 2 + 11;
		tankYMin = (height - ySize) / 2 + 11;
		tankXMax = tankXMin + 16;
		tankYMax = tankYMin + 65;
		if (mouseX >= tankXMin && mouseX <= tankXMax)
			if (mouseY >= tankYMin && mouseY <= tankYMax)
				drawToolTip(mouseX, mouseY, furnace.getFluidAmount());
	}
}