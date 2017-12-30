package erebus.client.gui;

import erebus.core.helper.Utils;
import erebus.inventory.ContainerComposter;
import erebus.tileentity.TileEntityComposter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiComposter extends GuiErebus {

	private static final ResourceLocation composterGuiTextures = new ResourceLocation("erebus:textures/gui/container/composter.png");
	private TileEntityComposter tileComposter;

	public GuiComposter(InventoryPlayer player, TileEntityComposter composter) {
		super(new ContainerComposter(player, composter));
		tileComposter = composter;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int colour = Utils.getColour(0, 0, 0);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.composter").getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation("container.composter").getFormattedText())) / 2, 6, colour);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), xSize - 167, ySize - 93, colour);
	
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.composter").getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation("container.composter").getFormattedText())) / 2 - 1, 5, Utils.getColour(255, 255, 255));
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), xSize - 168, ySize - 94, Utils.getColour(255, 255, 255));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(composterGuiTextures);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (tileComposter.isBurning()) {
			int i1 = tileComposter.getBurnTimeRemainingScaled(13);
			drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - i1, 176, 12 - i1, 16, i1 + 2);
			i1 = tileComposter.getCookProgressScaled(32);
			drawTexturedModalRect(guiLeft + 76, guiTop + 28, 176, 14, i1 + 1, 32);
		}
	}
}
