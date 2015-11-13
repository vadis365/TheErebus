package erebus.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.block.silo.TileEntitySiloTank;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerSilo;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiSilo extends GuiErebus {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/gui/container/siloGui.png");
	private final TileEntitySiloTank siloTank;

	public GuiSilo(InventoryPlayer playerInventory, TileEntitySiloTank tile) {
		super(new ContainerSilo(playerInventory, tile));
		siloTank = tile;
		allowUserInput = false;
		xSize = 256;
		ySize = 256;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int colour = Utils.getColour(192, 192, 192);
		fontRendererObj.drawString(StatCollector.translateToLocal(siloTank.getInventoryName()), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal(siloTank.getInventoryName())) / 2, 6, colour);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, colour);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}