package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.server.UmberFurnaceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

public class UmberFurnaceScreen extends AbstractContainerScreen<UmberFurnaceMenu> implements RecipeUpdateListener {
    public final AbstractFurnaceRecipeBookComponent recipeBookComponent = new SmeltingRecipeBookComponent();
    private boolean widthTooNarrow;
    private final UmberFurnaceMenu menu;

    private static final Identifier LIT_PROGRESS_SPRITE = Erebus.prefix("umberfurnace/lit_progress");
    private static final Identifier BURN_PROGRESS_SPRITE = Erebus.prefix("umberfurnace/burn_progress");
    private static final Identifier TANK_LEVEL_SPRITE = Erebus.prefix("umberfurnace/tank");
    private static final Identifier TEXTURE = Erebus.prefix("textures/gui/container/umber_furnace.png");

    public UmberFurnaceScreen(UmberFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.menu = menu;
    }

    @Override
    protected void init() {
        super.init();
        widthTooNarrow = width < 379;
        recipeBookComponent.init(width, height, minecraft, widthTooNarrow, menu);
        leftPos = recipeBookComponent.updateScreenPosition(width, imageWidth);
        addRenderableWidget(new ImageButton(leftPos - 20, height / 2 - 49, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, button -> {
            recipeBookComponent.toggleVisibility();
            leftPos = recipeBookComponent.updateScreenPosition(width, imageWidth);
            button.setPosition(leftPos -20, height / 2 - 49);
        }));
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    public void containerTick() {
        super.containerTick();
        recipeBookComponent.tick();
    }

    @Override
    public void render(@NotNull GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        if(recipeBookComponent.isVisible() && widthTooNarrow) {
            renderBackground(gui, mouseX, mouseY, partialTick);
            recipeBookComponent.render(gui, mouseX, mouseY, partialTick);
        } else {
            super.render(gui, mouseX, mouseY, partialTick);
            recipeBookComponent.render(gui, mouseX, mouseY, partialTick);
            recipeBookComponent.renderGhostRecipe(gui, leftPos, topPos, true, partialTick);
        }

        renderTooltip(gui, mouseX, mouseY);
        recipeBookComponent.renderTooltip(gui, leftPos, topPos, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
        gui.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        if(menu.isLit()) {
            int offset = Mth.ceil(menu.getLitProgress() * 13.0F) + 1;
            gui.blitSprite(LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - offset, leftPos + 56, topPos + 36 + 14 - offset, 14, offset);
        }

        int offset = Mth.ceil(menu.getBurnProgress() * 24.0F);
        gui.blitSprite(BURN_PROGRESS_SPRITE, 22, 15, 0, 0, leftPos + 80, topPos + 35, offset, 15);

        int tankSize = menu.getScaledFluidAmount();
        gui.blitSprite(TANK_LEVEL_SPRITE, 18, 65, 0, 0, leftPos + 10, topPos + 75 - tankSize, 18, tankSize);

        /*tankXMin = (width - getXSize()) / 2 + 11;
        tankYMin = (height - getYSize()) / 2 + 11;
        tankXMax = tankXMin + 16;
        tankYMax = tankYMin + 65;

        if()*/
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(recipeBookComponent.mouseClicked(mouseX, mouseY, button)) return true;
        return widthTooNarrow && recipeBookComponent.isVisible() || super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void slotClicked(@NotNull Slot slot, int slotId, int mouseButton, @NotNull ClickType type) {
        super.slotClicked(slot, slotId, mouseButton, type);
        recipeBookComponent.slotClicked(slot);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return recipeBookComponent.keyPressed(keyCode, scanCode, modifiers) || super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
        boolean flag = mouseX < guiLeft || mouseY < guiTop || mouseX >= (guiLeft + width) || mouseY >= (guiTop + imageHeight);
        return recipeBookComponent.hasClickedOutside(mouseX, mouseY, leftPos, topPos, imageWidth, imageHeight, mouseButton) && flag;
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        return recipeBookComponent.charTyped(codePoint, modifiers) || super.charTyped(codePoint, modifiers);
    }

    @Override
    public void recipesUpdated() {
        recipeBookComponent.recipesUpdated();
    }

    @Override
    public @NotNull RecipeBookComponent getRecipeBookComponent() {
        return recipeBookComponent;
    }


    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0xFFFFFF, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY + 3, 0xFFFFFF, false);
    }
}
