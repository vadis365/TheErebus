package erebus.recipes;

import erebus.ModItems;
import erebus.items.ItemLeggingsSprint;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RecipeSprintLeggingsUpgrades extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting craftMatrix, World world) {
		int size = craftMatrix.getSizeInventory();
		ItemStack is;
		// Shapeless supernatural velocity
		boolean hasVelocity = false;
		int cnt = 0;
		ItemStack leggings = ItemStack.EMPTY;

		for (int a = 0; a < size; a++) {
			is = craftMatrix.getStackInSlot(a);
			if (is.isEmpty())
				continue;
			++cnt;

			if (is.getItem() == ModItems.MATERIALS && is.getItemDamage() == EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.ordinal())
				hasVelocity = true;
			else if (is.getItem() == ModItems.SPRINT_LEGGINGS)
				leggings = is;
		}

		if (cnt == 2 && hasVelocity && !leggings.isEmpty())
			return leggings.getTagCompound() == null || leggings.getTagCompound().getByte("upgradeTier") < ItemLeggingsSprint.maxTier;

		// Biovelocity around leggings
		if (size < 9)
			return false;

		for (int a = 0; a < size; a++) {
			if ((is = craftMatrix.getStackInSlot(a)) == ItemStack.EMPTY)
				return false;

			if (a == 4) {
				if (!(is.getItem() == ModItems.SPRINT_LEGGINGS && (is.getTagCompound() == null || is.getTagCompound().getByte("upgradeTier") < ItemLeggingsSprint.maxTier)))
					return false;
			} else if (!(is.getItem() == ModItems.MATERIALS && is.getItemDamage() == EnumErebusMaterialsType.BIO_VELOCITY.ordinal()))
				return false;
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {
		ItemStack is = ItemStack.EMPTY;

		for (int a = 0; a < craftMatrix.getSizeInventory(); a++) {
			is = craftMatrix.getStackInSlot(a);
			if (!is.isEmpty() && is.getItem() == ModItems.SPRINT_LEGGINGS)
				break;
		}

		if (is.isEmpty())
			return ItemStack.EMPTY;
		is = is.copy();

		if (!is.hasTagCompound())
			is.setTagCompound(new NBTTagCompound());
		is.getTagCompound().setByte("upgradeTier", (byte) (is.getTagCompound().getByte("upgradeTier") + 1));
		return is;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ModItems.SPRINT_LEGGINGS);
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height >= 2;
	}

}
