package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.client.elements.GuiInvisibleButton;
import erebus.inventory.server.ColossalCrateMenu;
import erebus.network.server.ColossalCratePage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class ColossalCrateScreen extends ErebusScreen<ColossalCrateMenu> {

    private static final Identifier GUI_BAMBOO_CRATE = Erebus.prefix("textures/gui/container/bamboo_collosal_crate.png");

    public ColossalCrateScreen(ColossalCrateMenu handler, Inventory playerInventory, Component text) {
        super(handler, playerInventory, text, GUI_BAMBOO_CRATE, 230, 220);
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new GuiInvisibleButton(leftPos + 7, topPos + 4, 17, 11, Component.literal(""), (button) -> {
            Minecraft.getInstance().getConnection().send(new ColossalCratePage(Math.floorMod(getPageNumber() - 2, 3) + 1));
        }));

        addRenderableWidget(new GuiInvisibleButton(leftPos + 205, topPos + 4, 17, 11, Component.literal(""), (button) -> {
            Minecraft.getInstance().getConnection().send(new ColossalCratePage(getPageNumber() % 3 + 1));
        }));
    }

    public int getPageNumber() {
        return getMenu().page;
    }

    @Override
    protected void extractLabels(@Nonnull GuiGraphicsExtractor graphics, int x, int y) {
        graphics.text(font, Component.translatable("erebus.container.colossal_crate"), 28, 6, 0xFF404040);
        String str = getPageNumber() + "/3";
        graphics.text(font, str, imageWidth / 2 - font.width(str) / 2, 6, 0xFF404040);
        graphics.text(font, Component.translatable("container.inventory"), 32, imageHeight - 96 + 3, 0xFF404040);
    }

}
