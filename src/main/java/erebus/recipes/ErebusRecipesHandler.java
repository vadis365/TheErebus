package erebus.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.ErebusMaterial;

public class ErebusRecipesHandler
{
	public static void init()
	{
		altar();
		offeringAltar();
	}

	private static void offeringAltar()
	{
		OfferingAltarRecipe.addRecipe(ErebusMaterial.createStack(ErebusMaterial.DATA.gaeanGem), new ItemStack(Items.diamond), new ItemStack(Items.emerald), new ItemStack(Blocks.obsidian));
	}

	private static void altar()
	{
		AltarRecipe.addRecipe(new ItemStack(ModItems.jadeHeart), new ItemStack(ModBlocks.jadeBlock), ErebusMaterial.createStack(ErebusMaterial.DATA.crimsonHeart), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot));
	}
}