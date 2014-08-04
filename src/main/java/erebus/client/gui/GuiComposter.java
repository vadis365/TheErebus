package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerComposter;
import erebus.tileentity.TileEntityComposter;

@SideOnly(Side.CLIENT)
public class GuiComposter extends GuiContainer {
	private static final ResourceLocation composterGuiTextures = new ResourceLocation("erebus:textures/gui/container/composter.png");
	private TileEntityComposter tileComposter;

	public GuiComposter(InventoryPlayer player, TileEntityComposter composter) {
		super(new ContainerComposter(player, composter));
		this.tileComposter = composter;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int color = Utils.getColour(0, 0, 0);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.composter"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("container.composter")) / 2, 6, color);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), xSize - 170, ySize - 93, color);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(composterGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		if (this.tileComposter.isBurning()) {
			int i1 = this.tileComposter.getBurnTimeRemainingScaled(13);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
			i1 = this.tileComposter.getCookProgressScaled(24);
			drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
		}
	}
}
