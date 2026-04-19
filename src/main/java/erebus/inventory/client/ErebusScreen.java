package erebus.inventory.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ErebusScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    protected final Identifier TEXTURE;
    public ErebusScreen(T container, Inventory inventory, Component title, Identifier texture) {
        super(container, inventory, title);
        TEXTURE = texture;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        String title = getTitle().getString();
        graphics.text(font, title, (int) (imageWidth / 2.0f - font.width(title) / 2.0f), 6, 4210752);
    }
}
