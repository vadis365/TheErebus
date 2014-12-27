package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.inventory.ContainerKitchenCounter;
import erebus.tileentity.TileEntityKitchenCounter;

public class GuiKitchenCounter extends GuiContainer {
	private TileEntityKitchenCounter kitchen;
	private static final ResourceLocation gui = new ResourceLocation("erebus:textures/gui/container/kitchenCounter.png");

	public GuiKitchenCounter(InventoryPlayer inv, TileEntityKitchenCounter tile) {
		super(new ContainerKitchenCounter(inv, tile));
		kitchen = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(gui);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		int honey = kitchen.getScaledHoneyAmount(80);
		drawTexturedModalRect(x + 8, y + 80 - honey, 8, 248 - honey, 9, honey);
		int beetleJuice = kitchen.getScaledBeetleJuiceAmount(80);
		drawTexturedModalRect(x + 25, y + 80 - beetleJuice, 25, 248 - beetleJuice, 9, beetleJuice);
		int antiVenom = kitchen.getScaledAntiVenomAmount(80);
		drawTexturedModalRect(x + 142, y + 80 - antiVenom, 142, 248 - antiVenom, 9, antiVenom);
		int milk = kitchen.getScaledMilkAmount(80);
		drawTexturedModalRect(x + 159, y + 80 - milk, 159, 248 - milk, 9, milk);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
	}
}
