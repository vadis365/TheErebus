package erebus.inventory.client.elements;

import erebus.inventory.server.UmberFurnaceMenu;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class UmberFurnaceRecipeBookComponent extends RecipeBookComponent<UmberFurnaceMenu> {
    private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(Identifier.withDefaultNamespace("recipe_book/furnace_filter_enabled"), Identifier.withDefaultNamespace("recipe_book/furnace_filter_disabled"), Identifier.withDefaultNamespace("recipe_book/furnace_filter_enabled_highlighted"), Identifier.withDefaultNamespace("recipe_book/furnace_filter_disabled_highlighted"));
    private final Component recipeFilterName;

    public UmberFurnaceRecipeBookComponent(UmberFurnaceMenu menu, Component recipeFilterName, List<TabInfo> tabInfos) {
        super(menu, tabInfos);
        this.recipeFilterName = recipeFilterName;
    }

    protected @NonNull WidgetSprites getFilterButtonTextures() {
        return FILTER_SPRITES;
    }

    protected boolean isCraftingSlot(Slot slot) {
        return switch (slot.index) {
            case 0, 1, 2 -> true;
            default -> false;
        };
    }

    protected void fillGhostRecipe(GhostSlots ghostSlots, RecipeDisplay recipe, @NonNull ContextMap context) {
        ghostSlots.setResult(menu.getResultSlot(), context, recipe.result());
        if (recipe instanceof FurnaceRecipeDisplay furnaceRecipe) {
            ghostSlots.setInput(menu.slots.get(UmberFurnaceMenu.INGREDIENT_SLOT), context, furnaceRecipe.ingredient());
            Slot fuelSlot = menu.slots.get(UmberFurnaceMenu.FUEL_SLOT);
            if (fuelSlot.getItem().isEmpty()) {
                ghostSlots.setInput(fuelSlot, context, furnaceRecipe.fuel());
            }
        }

    }

    protected @NonNull Component getRecipeFilterName() {
        return this.recipeFilterName;
    }

    protected void selectMatchingRecipes(RecipeCollection collection, @NonNull StackedItemContents stackedContents) {
        collection.selectRecipes(stackedContents, (display) -> display instanceof FurnaceRecipeDisplay);
    }
}
