package erebus.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.item.ErebusMaterial.DATA;
import erebus.item.Whetstone;

public class RecipeWhetstoneUpgrades implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting craftMatrix, World world)
	{
		int size = craftMatrix.getSizeInventory();
		ItemStack is;
		if (size < 9)
		{
			return false;
		}

		for (int a = 0; a < size; a++)
		{
			if ((is = craftMatrix.getStackInSlot(a)) == null)
			{
				return false;
			}

			if (a == 4)
			{
				if (!(is.getItem() == ModItems.whetstone && is.getItemDamage() < Whetstone.maxTier))
				{
					return false;
				}
			} else if (!(is.getItem() == ModItems.erebusMaterials && is.getItemDamage() == DATA.whetstonePowder.ordinal()))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craftMatrix)
	{
		ItemStack is = null;

		for (int a = 0; a < craftMatrix.getSizeInventory(); a++)
		{
			is = craftMatrix.getStackInSlot(a);
			if (is != null && is.getItem() == ModItems.whetstone)
			{
				break;
			}
		}

		if (is == null)
		{
			return null;
		}

		return new ItemStack(ModItems.whetstone, 1, is.getItemDamage() + 1);
	}

	@Override
	public int getRecipeSize()
	{
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return new ItemStack(ModItems.whetstone);
	}
}
