package erebus.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import erebus.core.helper.Utils;

public class ComposterRegistry {

	private static List<ItemStack> registry = new ArrayList<ItemStack>();

	public static void init() {
		// TODO register(ModItems.butts);
		// TODO if you register a itemstack with metadata = OreDictionary.WILDCARD_VALUE it will ignore the metadata when checking the registry
	}

	private static void register(Item item) {
		register(new ItemStack(item));
	}

	private static void register(Block block) {
		register(new ItemStack(block));
	}

	private static void register(ItemStack stack) {
		registry.add(stack);
	}

	public static boolean isCompostable(ItemStack stack) {
		for (ItemStack reg : registry)
			if (Utils.areStacksTheSame(stack, reg, false))
				return true;

		return false;
	}
}