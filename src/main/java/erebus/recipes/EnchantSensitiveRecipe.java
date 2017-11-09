package erebus.recipes;

import java.util.ArrayList;
import java.util.Iterator;

import erebus.core.helper.Utils;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class EnchantSensitiveRecipe extends ShapedOreRecipe {

	private int width, height;

	public EnchantSensitiveRecipe(ItemStack output, Object... inputs) {
		super(output, inputs);
	}

	@Override
	public boolean matches(InventoryCrafting grid, World world) {
		for (int i = 0; i <= 3 - width; i++)
			for (int j = 0; j <= 3 - height; j++) {
				if (checkMatch(grid, i, j, true))
					return true;
				if (checkMatch(grid, i, j, false))
					return true;
			}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror) {
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++) {
				int subX = x - startX;
				int subY = y - startY;
				Object target = null;

				if (subX >= 0 && subY >= 0 && subX < width && subY < height)
					if (mirror)
						target = getInput()[width - subX - 1 + subY * width];
					else
						target = getInput()[subX + subY * width];

				ItemStack slot = inv.getStackInRowAndColumn(x, y);

				if (target instanceof ItemStack) {
					if (!OreDictionary.itemMatches((ItemStack) target, slot, false) || !checkEnchants((ItemStack) target, slot))
						return false;
				} else if (target instanceof ArrayList) {
					boolean matched = false;

					Iterator<ItemStack> itr = ((ArrayList<ItemStack>) target).iterator();
					while (itr.hasNext() && !matched)
						matched = OreDictionary.itemMatches(itr.next(), slot, false);

					if (!matched)
						return false;
				} else if (target == null && slot != null)
					return false;
			}

		return true;
	}

	private boolean checkEnchants(ItemStack stack1, ItemStack stack2) {
		if (stack1.hasTagCompound() && stack2.hasTagCompound())
			return Utils.getEnchantments(stack1).equals(Utils.getEnchantments(stack2));
		return stack1.hasTagCompound() == stack2.hasTagCompound();
	}

	public static EnchantSensitiveRecipe makeRecipe(ItemStack output, Object... inputs) {
		EnchantSensitiveRecipe recipe = new EnchantSensitiveRecipe(output, inputs);
		recipe.width = 0;
		recipe.height = 0;

		int i = 0;
		if (inputs[i] instanceof String[]) {
			String[] str = (String[]) inputs[i++];
			for (int j = 0; j < str.length; j++) {
				String s1 = str[j];
				recipe.height++;
				recipe.width = s1.length();
			}
		} else
			while (inputs[i] instanceof String) {
				String str = (String) inputs[i++];
				recipe.height++;
				recipe.width = str.length();
			}

		return recipe;
	}
}