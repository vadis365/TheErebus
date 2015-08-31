package erebus.client.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerComposter;
import erebus.tileentity.TileEntityComposter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiComposter extends GuiContainer {
	private static final ResourceLocation composterGuiTextures = new ResourceLocation("erebus:textures/gui/container/composter.png");
	private TileEntityComposter tileComposter;

	public GuiComposter(InventoryPlayer player, TileEntityComposter composter) {
		super(new ContainerComposter(player, composter));
		tileComposter = composter;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int colour = Utils.getColour(20, 0, 0);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.composter"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("container.composter")) / 2, 6, colour);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), xSize - 170, ySize - 93, colour);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(composterGuiTextures);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

		if (tileComposter.isBurning()) {
			int i1 = tileComposter.getBurnTimeRemainingScaled(13);
			drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
			i1 = tileComposter.getCookProgressScaled(32);
			drawTexturedModalRect(k + 76, l + 28, 176, 14, i1 + 1, 32);
		}
	}
}
