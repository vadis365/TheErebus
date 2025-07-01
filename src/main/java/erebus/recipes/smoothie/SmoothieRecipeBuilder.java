package erebus.recipes.smoothie;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class SmoothieRecipeBuilder {
    private SizedFluidIngredient fluid;
    private SizedFluidIngredient fluid2;
    private SizedFluidIngredient fluid3;
    private SizedFluidIngredient fluid4;
    private ItemStack stack;
    private ItemStack stack2;
    private ItemStack stack3;
    private ItemStack stack4;
    private ItemStack result;

    public SmoothieRecipeBuilder addFluidInput(SizedFluidIngredient fluid) {
        this.fluid = fluid;
        return this;
    }

    public SmoothieRecipeBuilder addFluidInputs(SizedFluidIngredient... fluids) {
        this.fluid = fluids.length > 0 && fluids[0] != null ? fluids[0] : SizedFluidIngredient.of(FluidStack.EMPTY);
        this.fluid2 = fluids.length > 1 && fluids[1] != null ? fluids[1] : SizedFluidIngredient.of(FluidStack.EMPTY);
        this.fluid3 = fluids.length > 2 && fluids[2] != null ? fluids[2] : SizedFluidIngredient.of(FluidStack.EMPTY);
        this.fluid4 = fluids.length > 3 && fluids[3] != null ? fluids[3] : SizedFluidIngredient.of(FluidStack.EMPTY);
        return this;
    }

    public SmoothieRecipeBuilder addIngredient(ItemStack stack) {
        this.stack = stack;
        return this;
    }

    public SmoothieRecipeBuilder addIngredients(ItemStack... stacks) {
        this.stack = stacks.length > 0 && stacks[0] != null ? stacks[0] : ItemStack.EMPTY;
        this.stack2 = stacks.length > 1 && stacks[1] != null ? stacks[1] : ItemStack.EMPTY;
        this.stack3 = stacks.length > 2 && stacks[2] != null ? stacks[2] : ItemStack.EMPTY;
        this.stack4 = stacks.length > 3 && stacks[3] != null ? stacks[3] : ItemStack.EMPTY;
        return this;
    }

    public SmoothieRecipeBuilder setOutput(ItemStack result) {
        this.result = result;
        return this;
    }

    public SmoothieRecipe build() {
        return new SmoothieRecipe(fluid, fluid2, fluid3, fluid4, stack, stack2, stack3, stack4, result);
    }
}
