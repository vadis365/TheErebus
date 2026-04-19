package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.client.elements.UmberFurnaceRecipeBookComponent;
import erebus.inventory.server.UmberFurnaceMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class UmberFurnaceScreen extends AbstractRecipeBookScreen<UmberFurnaceMenu> {
    private static final Identifier LIT_PROGRESS_SPRITE = Erebus.prefix("umberfurnace/lit_progress");
    private static final Identifier BURN_PROGRESS_SPRITE = Erebus.prefix("umberfurnace/burn_progress");
    private static final Identifier TANK_LEVEL_SPRITE = Erebus.prefix("umberfurnace/tank");
    private static final Identifier TEXTURE = Erebus.prefix("textures/gui/container/umber_furnace.png");
    private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.smeltable");

    private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
            new RecipeBookComponent.TabInfo(SearchRecipeBookCategory.FURNACE),
            new RecipeBookComponent.TabInfo(Items.PORKCHOP, RecipeBookCategories.FURNACE_FOOD),
            new RecipeBookComponent.TabInfo(Items.STONE, RecipeBookCategories.FURNACE_BLOCKS),
            new RecipeBookComponent.TabInfo(Items.LAVA_BUCKET, Items.EMERALD, RecipeBookCategories.FURNACE_MISC)
    );

    public UmberFurnaceScreen(UmberFurnaceMenu menu, Inventory inventory, Component title) {
        super(menu, new UmberFurnaceRecipeBookComponent(menu, FILTER_NAME, TABS), inventory, title);
    }

    @Override
    protected @NonNull ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(this.leftPos + 20, this.height / 2 - 49);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int xm, int ym, float a) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        if (menu.isLit()) {
            int litSpriteHeight = 14;
            int litProgressHeight = Mth.ceil(menu.getLitProgress() * 13.0F) + 1;
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - litProgressHeight, leftPos + 56, topPos + 36 + 14 - litProgressHeight, 14, litProgressHeight);
        }

        int burnSpriteWidth = 24;
        int burnProgressWidth = Mth.ceil(menu.getBurnProgress() * 24.0F);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BURN_PROGRESS_SPRITE, 24, 16, 0, 0, leftPos + 79, topPos + 34, burnProgressWidth, 16);

        int tankAmount = menu.getTankAmount();
        if (tankAmount > 0) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, TANK_LEVEL_SPRITE, 10, 65, 0, 65 - tankAmount, leftPos + 31, topPos + 10 + 65 - tankAmount, 10, tankAmount);
        }
    }
}
