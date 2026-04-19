package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.PetrifiedCraftingMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jspecify.annotations.NonNull;

public class PetrifiedCraftingTableScreen extends AbstractRecipeBookScreen<PetrifiedCraftingMenu> {
    private static final Identifier GUI = Identifier.fromNamespaceAndPath(Erebus.MODID, "textures/gui/container/petrified_crafting.png");

    public PetrifiedCraftingTableScreen(PetrifiedCraftingMenu menu, Inventory inventory, Component title) {
        super(menu, new CraftingRecipeBookComponent(menu), inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = 29;
    }

    @Override
    public void extractBackground(@NonNull GuiGraphicsExtractor graphics, int xm, int ym, float a) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI, leftPos, (height - imageHeight) / 2, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    @Override
    protected @NonNull ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(leftPos + 5, height / 2 - 49);
    }
}
