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
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/composter.png"));
		this.container = container;
		imageHeight = 168;
		imageWidth = 176;
	}

	@Override
	protected void renderLabels(@Nonnull GuiGraphics gg, int mouseX, int mouseY) {
		gg.drawString(font, title, 8, 6, 16777215, true);
		gg.drawString(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 16777215, true);	
	}

	@Override
	protected void renderBg(GuiGraphics gg, float partialTicks, int mouseX, int mouseY) {
        gg.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        if (container.getMouldProgress() > 0) {
			int operationProgress = container.getMouldProgress();
			gg.blit(TEXTURE, leftPos + 56, topPos + 36 + 12 - operationProgress, 176, 12 - operationProgress, 16, operationProgress + 2);
			operationProgress = container.getCompostingProgress();
			gg.blit(TEXTURE, leftPos + 76, topPos + 28, 176, 14, operationProgress + 1, 32);
        }
	}
}
