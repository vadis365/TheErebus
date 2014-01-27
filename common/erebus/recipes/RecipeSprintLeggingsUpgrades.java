package erebus.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;
import erebus.item.ItemSprintLeggings;

public class RecipeSprintLeggingsUpgrades implements IRecipe {
	@Override
	public boolean matches(InventoryCrafting craftMatrix, World world) {
		int size = craftMatrix.getSizeInventory();
		ItemStack is;

		// Shapeless supernatural velocity
		boolean hasVelocity = false;
		int cnt = 0;
		ItemStack leggings = null;

		for (int a = 0; a < size; a++) {
			if ((is = craftMatrix.getStackInSlot(a)) == null)
				continue;
			++cnt;

			if (is.itemID == ModItems.erebusMaterials.itemID && is.getItemDamage() == ItemErebusMaterial.dataSupernaturalVelocity)
				hasVelocity = true;
			else if (is.itemID == ModItems.sprintLeggings.itemID)
				leggings = is;
		}

		if (cnt == 2 && hasVelocity && leggings != null)
			return leggings.stackTagCompound == null || leggings.stackTagCompound.getByte("upgradeTier") < ItemSprintLeggings.maxTier;

		// Biovelocity around leggings
		if (size < 9)
			return false;

		for (int a = 0; a < size; a++) {
			if ((is = craftMatrix.getStackInSlot(a)) == null)
				return false;

			if (a == 4) {
				if (!(is.itemID == ModItems.sprintLeggings.itemID && (is.stackTagCompound == null || is.stackTagCompound.getByte("upgradeTier") < ItemSprintLeggings.maxTier)))
					return false;
			} else if (!(is.itemID == ModItems.erebusMaterials.itemID && is.getItemDamage() == ItemErebusMaterial.dataBioVelocity))
				return false;
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
		ItemStack is = null;

		for (int a = 0; a < craftMatrix.getSizeInventory(); a++) {
			is = craftMatrix.getStackInSlot(a);
			if (is != null && is.itemID == ModItems.sprintLeggings.itemID)
				break;
		}

		if (is == null)
			return null;
		is = is.copy();

		if (is.stackTagCompound == null)
			is.stackTagCompound = new NBTTagCompound();
		is.stackTagCompound.setByte("upgradeTier", (byte) (is.stackTagCompound.getByte("upgradeTier") + 1));
		return is;
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ModItems.sprintLeggings);
	}
}
