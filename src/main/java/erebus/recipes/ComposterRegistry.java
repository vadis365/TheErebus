package erebus.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.items.ItemMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ComposterRegistry {

	private static List<Material> compostableMaterials = Arrays.asList(Material.CACTUS, Material.CAKE, Material.CORAL, Material.GOURD, Material.GRASS, Material.LEAVES, Material.PLANTS, Material.SPONGE, Material.VINE, Material.WEB, Material.WOOD);
	private static List<ItemStack> registry = new ArrayList<ItemStack>();
	private static List<ItemStack> blacklist = new ArrayList<ItemStack>();

	public static void init() {
		register(Items.STICK);
		register(Items.WOODEN_AXE);
		register(Items.WOODEN_HOE);
		register(Items.WOODEN_PICKAXE);
		register(Items.WOODEN_SHOVEL);
		register(Items.WOODEN_SWORD);
		register(Items.WHEAT);
		register(Items.POISONOUS_POTATO);
		register(Items.ACACIA_DOOR);
		register(Items.BIRCH_DOOR);
		register(Items.DARK_OAK_DOOR);
		register(Items.JUNGLE_DOOR);
		register(Items.OAK_DOOR);
		register(Items.SPRUCE_DOOR);
		register(ItemMaterials.EnumErebusMaterialsType.DARK_FRUIT_SEEDS.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.BLUEBELL_PETAL.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.PAPYRUS.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.NETTLE_FLOWERS.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.MOSS_BALL.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.GLOWSHROOM.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.JADE_BERRIES.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.BOGMAW_ROOT.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.BAMBOO.createStack());
		register(ItemMaterials.EnumErebusMaterialsType.BAMBOO.createStack());

		blacklist.add(new ItemStack(ModBlocks.WALL_PLANTS, 1, 1));
		blacklist.add(new ItemStack(ModBlocks.WALL_PLANTS_CULTIVATED, 1, 1));
	}

	private static void register(Item item) {
		register(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
	}

	private static void register(ItemStack stack) {
		registry.add(stack);
	}

	public static ItemStack isCompostable(ItemStack stack) {
		if (stack.isEmpty())
			return ItemStack.EMPTY;
		for (ItemStack s : blacklist)
			if (Utils.areStacksTheSame(s, stack, false))
				return ItemStack.EMPTY;

		if (stack.getItem() instanceof ItemFood || stack.getItem() instanceof ItemSeeds)
			return new ItemStack(ModItems.COMPOST);
		else {
			Block block = Block.getBlockFromItem(stack.getItem());
			if (block != null && block != Blocks.AIR && compostableMaterials.contains(block.getDefaultState().getMaterial()))
				return new ItemStack(ModItems.COMPOST);
		}

		for (ItemStack reg : registry)
			if (Utils.areStacksTheSame(stack, reg, false))
				return new ItemStack(ModItems.COMPOST);

		return ItemStack.EMPTY;
	}
}