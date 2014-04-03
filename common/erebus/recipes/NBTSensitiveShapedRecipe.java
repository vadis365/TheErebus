package erebus.recipes;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class NBTSensitiveShapedRecipe extends ShapedRecipes {

	public NBTSensitiveShapedRecipe(int width, int height, ItemStack[] items, ItemStack result) {
		super(width, height, items, result);
	}

	@Override
	public boolean matches(InventoryCrafting grid, World world) {
		for (int i = 0; i <= 3 - recipeWidth; i++)
			for (int j = 0; j <= 3 - recipeHeight; j++) {
				if (checkMatch(grid, i, j, true))
					return true;

				if (checkMatch(grid, i, j, false))
					return true;
			}
		return false;
	}

	private boolean checkMatch(InventoryCrafting grid, int x, int y, boolean inverted) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				int i1 = i - x;
				int j1 = j - y;
				ItemStack stack = null;

				if (i1 >= 0 && j1 >= 0 && i1 < recipeWidth && j1 < recipeHeight)
					if (inverted)
						stack = recipeItems[recipeWidth - i1 - 1 + j1 * recipeWidth];
					else
						stack = recipeItems[i1 + j1 * recipeWidth];

				ItemStack stack2 = grid.getStackInRowAndColumn(i, j);

				if (stack2 != null || stack != null) {
					if (stack2 == null && stack != null || stack2 != null && stack == null)
						return false;

					if (stack.itemID != stack2.itemID)
						return false;

					if (stack.getItemDamage() != OreDictionary.WILDCARD_VALUE && stack.getItemDamage() != stack2.getItemDamage())
						return false;

					if (!checkNBTs(stack, stack2))
						return false;
				}
			}

		return true;
	}

	private boolean checkNBTs(ItemStack stack1, ItemStack stack2) {
		if (stack1.hasTagCompound() && stack2.hasTagCompound())
			return stack1.stackTagCompound.equals(stack2.stackTagCompound);
		return true;
	}

	public static NBTSensitiveShapedRecipe makeRecipe(ItemStack result, Object... recipe) {
		String s = "";
		int i = 0;
		int width = 0;
		int height = 0;

		if (recipe[i] instanceof String[]) {
			String[] astring = (String[]) recipe[i++];

			for (int l = 0; l < astring.length; l++) {
				String s1 = astring[l];
				height++;
				width = s1.length();
				s = s + s1;
			}
		} else
			while (recipe[i] instanceof String) {
				String s2 = (String) recipe[i++];
				height++;
				width = s2.length();
				s = s + s2;
			}

		HashMap hashmap;

		for (hashmap = new HashMap(); i < recipe.length; i += 2) {
			Character character = (Character) recipe[i];
			ItemStack itemstack1 = null;

			if (recipe[i + 1] instanceof Item)
				itemstack1 = new ItemStack((Item) recipe[i + 1]);
			else if (recipe[i + 1] instanceof Block)
				itemstack1 = new ItemStack((Block) recipe[i + 1], 1, 32767);
			else if (recipe[i + 1] instanceof ItemStack)
				itemstack1 = (ItemStack) recipe[i + 1];

			hashmap.put(character, itemstack1);
		}

		ItemStack[] aitemstack = new ItemStack[width * height];

		for (int i1 = 0; i1 < width * height; i1++) {
			char c0 = s.charAt(i1);

			if (hashmap.containsKey(Character.valueOf(c0)))
				aitemstack[i1] = ((ItemStack) hashmap.get(Character.valueOf(c0))).copy();
			else
				aitemstack[i1] = null;
		}

		return new NBTSensitiveShapedRecipe(width, height, aitemstack, result);
	}
}