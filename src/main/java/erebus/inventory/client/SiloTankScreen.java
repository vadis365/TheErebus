package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.SiloTankMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class SiloTankScreen extends ErebusScreen<SiloTankMenu> {

	public SiloTankScreen(SiloTankMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/silo_gui.png"));
		imageHeight = 256;
		imageWidth = 256;
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
