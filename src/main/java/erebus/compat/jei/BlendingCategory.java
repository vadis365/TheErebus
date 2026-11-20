package erebus.compat.jei;

import erebus.block.entity.BlenderBlockEntity;
import erebus.recipes.smoothie.SmoothieRecipe;
import erebus.registries.blocks.ModBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.display.ForFluidStacks;

final class BlendingCategory extends AbstractRecipeCategory<RecipeHolder<SmoothieRecipe>> {
    BlendingCategory(IGuiHelper gui) {
        super(ModJeiPlugin.BLENDING, Component.translatable("jei.category.erebus.blender"),
                gui.createDrawableItemLike(ModBlocks.BLENDER.get()), 173, 62);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<SmoothieRecipe> holder, IFocusGroup focuses) {
        var recipe = holder.value();
        var fluids = recipe.getFluidIngredients();
        for (int index = 0; index < fluids.size(); index++) {
            var ingredient = fluids.get(index);
            int amount = ingredient.amount();
            ForFluidStacks<FluidStack> display = stack -> stack.copyWithAmount(amount);
            var slot = builder.addInputSlot(2 + index % 2 * 18, 4 + index / 2 * 18);
            slot.setStandardSlotBackground().setFluidRenderer(amount, true, 16, 16)
                    .addIngredients(NeoForgeTypes.FLUID_STACK, ingredient.ingredient().display().resolve(slot.getContextMap(), display).toList());
        }

        var ingredients = recipe.getItemIngredients();
        for (int index = 0; index < ingredients.size(); index++) {
            builder.addInputSlot(60 + index % 3 * 18, 4 + index / 3 * 18)
                    .setStandardSlotBackground().add(ingredients.get(index));
        }
        builder.addInputSlot(78, 44).setStandardSlotBackground().add(recipe.getContainerIngredient());
        builder.addOutputSlot(150, 15).setOutputSlotBackground().add(recipe.getResult());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<SmoothieRecipe> holder, IFocusGroup focuses) {
        builder.addRecipePlusSignWidget().setPosition(43, 16);
        builder.addAnimatedRecipeArrowWidget(BlenderBlockEntity.BLEND_TICKS).setPosition(118, 15)
                .setTooltip(Component.translatable("gui.jei.category.smelting.time.seconds", BlenderBlockEntity.BLEND_TICKS / 20.0F));
    }
}
