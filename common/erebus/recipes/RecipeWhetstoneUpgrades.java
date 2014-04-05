package erebus.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial.DATA;
import erebus.item.ItemWhetstone;

public class RecipeWhetstoneUpgrades implements IRecipe {
	@Override
	public boolean matches(InventoryCrafting craftMatrix, World world) {
		int size = craftMatrix.getSizeInventory();
		ItemStack is;
		if (size < 9)
			return false;

		for (int a = 0; a < size; a++) {
			if ((is = craftMatrix.getStackInSlot(a)) == null)
				return false;

			if (a == 4) {
				if (!(is.itemID == ModItems.whetstone.itemID && is.getItemDamage() < ItemWhetstone.maxTier))
					return false;
			} else if (!(is.itemID == ModItems.erebusMaterials.itemID && is.getItemDamage() == DATA.whetstonePowder.ordinal()))
				return false;
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
		ItemStack is = null;

		for (int a = 0; a < craftMatrix.getSizeInventory(); a++) {
			is = craftMatrix.getStackInSlot(a);
			if (is != null && is.itemID == ModItems.whetstone.itemID)
				break;
		}

		if (is == null)
			return null;
		
		return new ItemStack(ModItems.whetstone, 1, is.getItemDamage() + 1);
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ModItems.whetstone);
	}
}
