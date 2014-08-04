package erebus.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import erebus.ModItems;
import erebus.core.helper.Utils;

public class ComposterRegistry {

	private static List<Material> compostableMaterials = Arrays.asList(Material.cactus, Material.cake, Material.coral, Material.gourd, Material.grass, Material.leaves, Material.plants, Material.sponge, Material.vine, Material.web, Material.wood);
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

	public static ItemStack isCompostable(ItemStack stack) {
		if (stack == null)
			return null;

		if (stack.getItem() instanceof ItemFood)
			return new ItemStack(ModItems.compost);
		else {
			Block block = Block.getBlockFromItem(stack.getItem());
			if (block != null && block != Blocks.air && compostableMaterials.contains(block.getMaterial()))
				return new ItemStack(ModItems.compost);
		}

		for (ItemStack reg : registry)
			if (Utils.areStacksTheSame(stack, reg, false))
				return new ItemStack(ModItems.compost);

		return null;
	}
}