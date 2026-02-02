package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.ComposterMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class ComposterScreen extends ErebusScreen<ComposterMenu> {

	protected final ComposterMenu container;

	public ComposterScreen(ComposterMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/composter.png"), 176, 168);
		this.container = container;
	}

	@Override
	protected void renderLabels(@Nonnull GuiGraphics gg, int mouseX, int mouseY) {
		gg.drawString(font, title, 8, 6, 16777215);
		gg.drawString(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 16777215);	
	}

	@Override
	protected void renderBg(GuiGraphics gg, float partialTicks, int mouseX, int mouseY) {
		gg.blit(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
        if (container.getMouldProgress() > 0) {
			int operationProgress = container.getMouldProgress();
			gg.blit(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 56, topPos + 36 + 12 - operationProgress, 176, 12 - operationProgress, 16, operationProgress + 2, 256, 256);
			operationProgress = container.getCompostingProgress();
			gg.blit(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 76, topPos + 28, 176, 14, operationProgress + 1, 32, 256, 256);
        }
	}
}
