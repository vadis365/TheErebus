package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.PetrifiedChestMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PetrifiedChestScreen extends AbstractContainerScreen<PetrifiedChestMenu> {

    public static final ResourceLocation GUI = Erebus.prefix("textures/gui/container/petrified_container_big.png");
    private final int rows;

    public PetrifiedChestScreen(PetrifiedChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        rows = menu.getContainer().getContainerSize() / 9;
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
        gui.blit(GUI, leftPos, topPos, 0, 0, imageWidth, rows * 18 + 17);
        gui.blit(GUI, leftPos, topPos + rows * 18 + 17, 0, 124 + 36, imageWidth, 96);
    }
}
