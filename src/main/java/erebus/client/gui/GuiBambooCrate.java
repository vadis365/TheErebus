package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.inventory.ContainerBambooCrate;
import erebus.tileentity.TileEntityBambooCrate;

@SideOnly(Side.CLIENT)
public class GuiBambooCrate extends GuiContainer
{

	private static final ResourceLocation GUI_BAMBOO_CRATE = new ResourceLocation("erebus:textures/gui/container/bambooCrate.png");
	private final TileEntityBambooCrate bambooCrateInventory;

	public GuiBambooCrate(InventoryPlayer playerInventory, TileEntityBambooCrate tile)
	{
		super(new ContainerBambooCrate(playerInventory, tile));
		bambooCrateInventory = tile;
		allowUserInput = false;
		ySize = 168;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		fontRendererObj.drawString(StatCollector.translateToLocal(bambooCrateInventory.getInventoryName()), 8, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_BAMBOO_CRATE);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}
