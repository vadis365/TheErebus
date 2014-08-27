package erebus.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.Materials;

public class ErebusRecipesHandler
{
	public static void init()
	{
		altar();
		offeringAltar();
	}

	private static void offeringAltar()
	{
		OfferingAltarRecipe.addRecipe(Materials.createStack(Materials.DATA.gaeanGem), "gemDiamond", "gemEmerald", new ItemStack(Blocks.obsidian));
	}

	private static void altar()
	{
		AltarRecipe.addRecipe(new ItemStack(ModItems.jadeHeart), new ItemStack(ModBlocks.jadeBlock), Materials.createStack(Materials.DATA.crimsonHeart), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot));
	}
}