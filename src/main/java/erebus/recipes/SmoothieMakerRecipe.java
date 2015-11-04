package erebus.recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import erebus.core.helper.Utils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.oredict.OreDictionary;

public class SmoothieMakerRecipe {

	private static final List<SmoothieMakerRecipe> recipes = new ArrayList<SmoothieMakerRecipe>();

	public static void addRecipe(ItemStack output, ItemStack container, FluidStack fluid, Object... input) {
		addRecipe(output, container, new FluidStack[] { fluid }, input);
	}

	public static void addRecipe(ItemStack output, ItemStack container, FluidStack[] fluids, Object... input) {
		recipes.add(new SmoothieMakerRecipe(output, container, fluids, input));
	}

	public static void addRecipe(ItemStack output, ItemStack container, Fluid fluid, Object... input) {
		addRecipe(output, container, new Fluid[] { fluid }, input);
	}

	public static void addRecipe(ItemStack output, ItemStack container, Fluid[] fluids, Object... input) {
		FluidStack[] stacks = new FluidStack[fluids.length];
		for (int i = 0; i < stacks.length; i++)
			stacks[i] = new FluidStack(fluids[i], FluidContainerRegistry.BUCKET_VOLUME);
		addRecipe(output, container, stacks, input);
	}

	public static ItemStack getOutput(ItemStack container, IFluidTank tank0, IFluidTank tank1, IFluidTank tank2, IFluidTank tank3, ItemStack... input) {
		SmoothieMakerRecipe recipe = getRecipe(container, tank0, tank1, tank2, tank3, input);
		return recipe != null ? recipe.getOutput() : null;
	}

	public static SmoothieMakerRecipe getRecipe(ItemStack container, IFluidTank tank0, IFluidTank tank1, IFluidTank tank2, IFluidTank tank3, ItemStack... input) {
		for (SmoothieMakerRecipe recipe : recipes)
			if (recipe.matches(container, tank0, tank1, tank2, tank3, input))
				return recipe;

		return null;
	}

	public static List<SmoothieMakerRecipe> getRecipeList() {
		return Collections.unmodifiableList(recipes);
	}

	private final ItemStack output;
	private final ItemStack container;
	private final FluidStack[] fluids;
	private final Object[] input;

	private SmoothieMakerRecipe(ItemStack output, ItemStack container, FluidStack[] fluids, Object... input) {
		this.output = ItemStack.copyItemStack(output);
		this.container = container;
		this.fluids = fluids;
		this.input = new Object[input.length];

		if (input.length > 4)
			throw new IllegalArgumentException("Input must be 1 to 4.");
		if (fluids.length > 4)
			throw new IllegalArgumentException("Fluids must be 1 to 4.");

		for (int c = 0; c < input.length; c++)
			if (input[c] instanceof ItemStack)
				this.input[c] = ItemStack.copyItemStack((ItemStack) input[c]);
			else if (input[c] instanceof String)
				this.input[c] = OreDictionary.getOres((String) input[c]);
			else
				throw new IllegalArgumentException("Input must be an ItemStack or an OreDictionary name");
	}

	public Object[] getInputs() {
		return input;
	}

	public ItemStack getOutput() {
		return ItemStack.copyItemStack(output);
	}

	public ItemStack getContainer() {
		return ItemStack.copyItemStack(container);
	}

	public boolean matches(ItemStack container, IFluidTank tank0, IFluidTank tank1, IFluidTank tank2, IFluidTank tank3, ItemStack... stacks) {
		if (container != null && !areStacksTheSame(container, this.container) && container.stackSize == this.container.stackSize)
			return false;

		label: for (Object input : this.input) {
			for (int i = 0; i < stacks.length; i++)
				if (stacks[i] != null)
					if (areStacksTheSame(input, stacks[i])) {
						stacks[i] = null;
						continue label;
					}

			return false;
		}

		label: for (FluidStack fluid : fluids) {
			for (IFluidTank tank : new IFluidTank[] { tank0, tank1, tank2, tank3 })
				if (tank.getFluidAmount() >= fluid.amount && tank.getFluid().isFluidEqual(fluid))
					continue label;
			return false;
		}

		return true;
	}

	public boolean isPartOfInput(ItemStack ingredient) {
		for (Object i : input)
			if (areStacksTheSame(i, ingredient))
				return true;

		return areStacksTheSame(container, ingredient);
	}

	public FluidStack[] getFluids() {
		FluidStack[] fluids = new FluidStack[this.fluids.length];
		for (int i = 0; i < fluids.length; i++)
			fluids[i] = this.fluids[i].copy();
		return fluids;
	}

	@SuppressWarnings("unchecked")
	public boolean areStacksTheSame(Object obj, ItemStack target) {
		if (obj instanceof ItemStack)
			return Utils.areStacksTheSame((ItemStack) obj, target, false);
		else if (obj instanceof List) {
			List<ItemStack> list = (List<ItemStack>) obj;

			for (ItemStack stack : list)
				if (Utils.areStacksTheSame(stack, target, false))
					return true;
		}

		return false;
	}
}