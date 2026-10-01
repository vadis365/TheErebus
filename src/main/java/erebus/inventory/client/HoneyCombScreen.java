package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.HoneyCombMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class HoneyCombScreen extends ErebusScreen<HoneyCombMenu> {

    public HoneyCombScreen(HoneyCombMenu container, Inventory playerInventory, Component name) {
        super(container, playerInventory, name, Erebus.prefix("textures/gui/container/honey_comb_gui.png"));
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        super.extractLabels(graphics, xm, ym);
        graphics.text(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 0xFF404040);
    }

}
