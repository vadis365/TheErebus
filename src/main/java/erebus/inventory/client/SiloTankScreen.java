package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.SiloTankMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SiloTankScreen extends ErebusScreen<SiloTankMenu> {

	public SiloTankScreen(SiloTankMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/silo_gui.png"));
	}
	
	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
		super.extractLabels(graphics, xm, ym);
		graphics.text(font, title, 8, 6, 16777215);
		graphics.text(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 16777215);
	}
	
	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
		super.extractBackground(graphics, mouseX, mouseY, a);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
	}
}
