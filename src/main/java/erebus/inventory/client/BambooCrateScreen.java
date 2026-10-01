package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.BambooCrateMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class BambooCrateScreen extends ErebusScreen<BambooCrateMenu> {

    public BambooCrateScreen(BambooCrateMenu container, Inventory playerInventory, Component name) {
        super(container, playerInventory, name, Erebus.prefix("textures/gui/container/bamboo_crate.png"));
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        graphics.text(font, title, 8, 6, 0xFF404040);
        graphics.text(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 0xFF404040);
    }

}
