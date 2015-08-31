package erebus.client.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerExtenderThingy;
import erebus.tileentity.TileEntityExtenderThingy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiExtenderThingy extends GuiContainer {

	private static final ResourceLocation GUI_UMBER_FURNACE = new ResourceLocation("erebus:textures/gui/container/extenderThingy.png");

	public GuiExtenderThingy(InventoryPlayer inventory, TileEntityExtenderThingy tile) {
		super(new ContainerExtenderThingy(inventory, tile));
		xSize = 176;
		ySize = 136;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int color = Utils.getColour(0, 0, 0);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.extenderThingy"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("container.extenderThingy")) / 2, 6, color);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), xSize - 170, ySize - 96 + 2, color);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_UMBER_FURNACE);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}