package erebus.recipes.altar;

import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MultiStackInput implements RecipeInput {

	private final List<ItemStack> items;
	private final StackedItemContents stackedContents = new StackedItemContents();
	private final int ingredientCount;

	public MultiStackInput(List<ItemStack> item) {
		items = item;
		int i = 0;

		for (ItemStack stack : item) {
			if (!stack.isEmpty()) {
				i++;
				stackedContents.accountStack(stack, stack.getCount());
			}
		}

		ingredientCount = i;
	}

	@Override
	public @NotNull ItemStack getItem(int index) {
		return items.get(index);
	}

	@Override
	public int size() {
		return items.size();
	}

	@Override
	public boolean isEmpty() {
		return this.ingredientCount == 0;
	}

	public StackedItemContents stackedContents() {
		return stackedContents;
	}

	public List<ItemStack> items() {
		return items;
	}

	public int ingredientCount() {
		return ingredientCount;
	}
}
