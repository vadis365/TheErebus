package erebus.compat.jei;

import erebus.recipes.altar.OfferingAltarRecipe;
import erebus.registries.blocks.ModBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.world.item.crafting.RecipeHolder;

final class OfferingAltarCategory extends AbstractRecipeCategory<RecipeHolder<OfferingAltarRecipe>> {
    OfferingAltarCategory(IGuiHelper gui) {
        super(ModJeiPlugin.OFFERING_ALTAR, ModBlocks.OFFERING_ALTAR.get().getName(),
                gui.createDrawableItemLike(ModBlocks.OFFERING_ALTAR.get()), 115, 28);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<OfferingAltarRecipe> holder, IFocusGroup focuses) {
        var recipe = holder.value();
        var ingredients = recipe.getIngredients();
        for (int index = 0; index < ingredients.size(); index++) {
            builder.addInputSlot(2 + index * 18, 6).setStandardSlotBackground().add(ingredients.get(index));
        }
        builder.addOutputSlot(92, 6).setOutputSlotBackground().add(recipe.getResult());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<OfferingAltarRecipe> holder, IFocusGroup focuses) {
        builder.addRecipeArrowWidget().setPosition(61, 6);
    }
}
