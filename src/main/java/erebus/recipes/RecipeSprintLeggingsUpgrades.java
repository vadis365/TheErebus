package erebus.recipes;

import erebus.ModItems;
import erebus.item.ItemLeggingsSprint;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

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

			if (is.getItem() == ModItems.materials && is.getItemDamage() == DATA.supernaturalvelocity.ordinal())
				hasVelocity = true;
			else if (is.getItem() == ModItems.sprintLeggings)
				leggings = is;
		}

		if (cnt == 2 && hasVelocity && leggings != null)
			return leggings.stackTagCompound == null || leggings.stackTagCompound.getByte("upgradeTier") < ItemLeggingsSprint.maxTier;

		// Biovelocity around leggings
		if (size < 9)
			return false;

		for (int a = 0; a < size; a++) {
			if ((is = craftMatrix.getStackInSlot(a)) == null)
				return false;

			if (a == 4) {
				if (!(is.getItem() == ModItems.sprintLeggings && (is.stackTagCompound == null || is.stackTagCompound.getByte("upgradeTier") < ItemLeggingsSprint.maxTier)))
					return false;
			} else if (!(is.getItem() == ModItems.materials && is.getItemDamage() == DATA.bioVelocity.ordinal()))
				return false;
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
		ItemStack is = null;

		for (int a = 0; a < craftMatrix.getSizeInventory(); a++) {
			is = craftMatrix.getStackInSlot(a);
			if (is != null && is.getItem() == ModItems.sprintLeggings)
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
