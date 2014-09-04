package erebus.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.Materials;

public class ErebusRecipesHandler
{
	public static void init()
	{
		craftingAltar();
		offeringAltar();
	}

	private static void offeringAltar()
	{
		OfferingAltarRecipe.addRecipe(Materials.createStack(Materials.DATA.gaeanGem), "gemDiamond", "gemEmerald", new ItemStack(Blocks.obsidian));
	}

	private static void craftingAltar()
	{
		CraftingAltarRecipe.addRecipe(new ItemStack(ModItems.jadeHeart), "blockJade", Materials.createStack(Materials.DATA.crimsonHeart), "ingotGold", "ingotGold", "ingotGold", "ingotGold", "ingotGold");
		CraftingAltarRecipe.addRecipe(new ItemStack(ModBlocks.lightningSpeedBlock), new ItemStack(ModBlocks.velocityBlock), getArray(Materials.createStack(Materials.DATA.supernaturalvelocity), 8));
		CraftingAltarRecipe.addRecipe(new ItemStack(ModItems.witherWebSlinger), new ItemStack(ModItems.webSlinger), new ItemStack(Blocks.soul_sand), Materials.createStack(Materials.DATA.poisonGland), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb));
		CraftingAltarRecipe.addRecipe(new ItemStack(ModBlocks.umberGolemStatue), Materials.createStack(Materials.DATA.crimsonHeart), Materials.createStack(Materials.DATA.umberGolemCore), Materials.createStack(Materials.DATA.umberGolemClaw), Materials.createStack(Materials.DATA.umberGolemClaw), Materials.createStack(Materials.DATA.umberGolemHead), Materials.createStack(Materials.DATA.umberGolemLegs));
	}

	private static Object[] getArray(Object base, int size)
	{
		Object[] array = new Object[size];
		for (int i = 0; i < array.length; i++)
		{
			array[i] = base;
		}
		return array;
	}
}