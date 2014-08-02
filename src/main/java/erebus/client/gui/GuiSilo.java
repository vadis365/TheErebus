package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.block.silo.TileEntitySiloTank;
import erebus.inventory.ContainerSilo;

@SideOnly(Side.CLIENT)
public class GuiSilo extends GuiContainer {

	private static final ResourceLocation GUI_HONEY_COMB = new ResourceLocation("erebus:textures/gui/container/honeyCombGui.png");
	private final TileEntitySiloTank siloTank;

	public GuiSilo(InventoryPlayer playerInventory, TileEntitySiloTank tile) {
		super(new ContainerSilo(playerInventory, tile));
		siloTank = tile;
		allowUserInput = false;
		ySize = 168;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRendererObj.drawString(StatCollector.translateToLocal(siloTank.getInventoryName()), 8, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_HONEY_COMB); //temp texture 
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}