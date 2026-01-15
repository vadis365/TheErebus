package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.BambooCrateMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class BambooCrateScreen extends ErebusScreen<BambooCrateMenu> {

	public BambooCrateScreen(BambooCrateMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/bamboo_crate.png"));
		imageHeight = 168;
		imageWidth = 176;
	}

	@Override
	protected void renderLabels(@Nonnull GuiGraphics gg, int mouseX, int mouseY) {
		gg.drawString(font, Component.translatable("erebus.container.bamboo_crate"), 8, 6, 4210752, false);
		gg.drawString(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 4210752, false);
	}

	@Override
	protected void renderBg(GuiGraphics gg, float partialTicks, int mouseX, int mouseY) {
        gg.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
	}
}
