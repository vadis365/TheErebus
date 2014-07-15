package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerAntInventory;

@SideOnly(Side.CLIENT)
public class GuiAntInventory extends GuiContainer {

	private static final ResourceLocation GUI_ANT_INVENTORY = new ResourceLocation("erebus:textures/gui/container/antGuiTest.png");

	public GuiAntInventory(InventoryPlayer inventory, Entity entityInventory) {
		super(new ContainerAntInventory(inventory, (IInventory) entityInventory));
		xSize = 176;
		ySize = 131;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int color = Utils.getColour(0, 0, 0);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.antInventory"), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal("container.extenderThingy")) / 2 -10, 6, color);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), xSize - 170, ySize - 93, color);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_ANT_INVENTORY);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}