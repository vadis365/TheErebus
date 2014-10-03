package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import erebus.inventory.ContainerKitchenCounter;
import erebus.lib.Reference;
import erebus.tileentity.TileEntityKitchenCounter;

public class GuiKitchenCounter extends GuiContainer{

	public GuiKitchenCounter(InventoryPlayer inv, TileEntityKitchenCounter tile) {
		super(new ContainerKitchenCounter(inv, tile));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "/textures/gui/container/kitchenCounterGUI.png"));
		int x = (width - xSize) / 2;
		int y = (width - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		fontRendererObj.drawString("Kitchen Counter", 8, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

}
