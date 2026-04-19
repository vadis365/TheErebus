package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.BambooExtenderMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class BambooExtenderScreen extends ErebusScreen<BambooExtenderMenu> {

	public BambooExtenderScreen(BambooExtenderMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/bamboo_extender_gui.png"));
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
		super.extractLabels(graphics, xm, ym);
		graphics.text(font, Component.translatable("erebus.container.bamboo_extender"), 8, 6, 4210752);
		graphics.text(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 4210752);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
		super.extractBackground(graphics, mouseX, mouseY, a);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
	}
}
