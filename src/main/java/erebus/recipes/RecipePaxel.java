package erebus.recipes;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.ModMaterials;

public class RecipePaxel implements IRecipe {
	@Override
	public boolean matches(InventoryCrafting matrix, World world) {
		return checkItemInSlot(matrix, 0, ModItems.jadeAxe) && checkItemInSlot(matrix, 1, ModItems.jadeShovel) && checkItemInSlot(matrix, 2, ModItems.jadePickaxe) && checkItemInSlot(matrix, 4, Items.stick) && checkItemInSlot(matrix, 7, Items.stick);
	}

	private boolean checkItemInSlot(InventoryCrafting matrix, int slot, Item itemID) {
		ItemStack is = matrix.getStackInSlot(slot);
		return is != null && is.getItem() == itemID;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting matrix) {
		int currentDurability = 0, totalDurability = 0;

		for (int a = 0; a < 3; a++) {
			ItemStack is = matrix.getStackInSlot(a);
			totalDurability += is.getMaxDamage();
			currentDurability += is.getMaxDamage() - is.getItemDamage();
		}

		return new ItemStack(ModItems.jadePaxel, 1, ModMaterials.toolJADEPAXEL.getMaxUses() - (int) Math.floor((float) currentDurability * ModMaterials.toolJADEPAXEL.getMaxUses() / totalDurability));
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ModItems.jadePaxel);
	}
}