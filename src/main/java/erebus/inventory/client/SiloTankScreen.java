package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.SiloTankMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SiloTankScreen extends ErebusScreen<SiloTankMenu> {

    public SiloTankScreen(SiloTankMenu container, Inventory playerInventory, Component name) {
        super(container, playerInventory, name, Erebus.prefix("textures/gui/container/silo_gui.png"), 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        super.extractLabels(graphics, xm, ym);
        graphics.text(font, Component.translatable("container.inventory"), 48, this.imageHeight - 94, 0xFF404040);
    }

}
