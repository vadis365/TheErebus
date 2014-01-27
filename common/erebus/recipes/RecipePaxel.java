package erebus.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.ModItems;

public class RecipePaxel implements IRecipe {
	@Override
	public boolean matches(InventoryCrafting matrix, World world) {
		return checkItemInSlot(matrix, 0, ModItems.jadeAxe.itemID) && checkItemInSlot(matrix, 1, ModItems.jadeShovel.itemID) && checkItemInSlot(matrix, 2, ModItems.jadePickaxe.itemID) && checkItemInSlot(matrix, 4, Item.stick.itemID) && checkItemInSlot(matrix, 7, Item.stick.itemID);
	}

	private boolean checkItemInSlot(InventoryCrafting matrix, int slot, int itemID) {
		ItemStack is = matrix.getStackInSlot(slot);
		return is != null && is.itemID == itemID;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting matrix) {
		int currentDurability = 0, totalDurability = 0;

		for (int a = 0; a < 3; a++) {
			ItemStack is = matrix.getStackInSlot(a);
			totalDurability += is.getMaxDamage();
			currentDurability += is.getMaxDamage() - is.getItemDamage();
		}

		return new ItemStack(ModItems.jadePaxel.itemID, 1, ErebusMod.toolJADEPAXEL.getMaxUses() - (int) Math.floor((float) currentDurability * ErebusMod.toolJADEPAXEL.getMaxUses() / totalDurability));
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
