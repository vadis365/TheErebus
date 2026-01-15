package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.BambooExtenderMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class BambooExtenderScreen extends ErebusScreen<BambooExtenderMenu> {

	public BambooExtenderScreen(BambooExtenderMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/bamboo_extender_gui.png"));
		imageHeight = 136;
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
	}
}
