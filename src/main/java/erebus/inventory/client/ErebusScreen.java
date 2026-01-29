package erebus.inventory.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

import javax.annotation.Nonnull;

public class ErebusScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    protected final Identifier TEXTURE;
    public ErebusScreen(T container, Inventory inventory, Component title, Identifier texture) {
        super(container, inventory, title);
        TEXTURE = texture;
    }

    @Override
    public void render(@Nonnull GuiGraphics gg, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(gg, mouseX, mouseY, partialTicks);
        super.render(gg, mouseX, mouseY, partialTicks);
        renderTooltip(gg, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics gg, float partialTicks, int mX, int mY) {
        gg.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics gg, int mouseX, int mouseY) {
        String title = getTitle().getString();
        gg.drawString(font, title, imageWidth / 2.0f - font.width(title) / 2.0f, 6, 4210752, false);
    }

    protected void drawCenteredString(GuiGraphics gg, Component text, int x, int y, int color) {
        gg.drawString(font, text.getString(), x - font.width(text.getString()) / 2.0f, y, color, false);
    }
}
