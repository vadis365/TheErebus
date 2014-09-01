package erebus.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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
	}
}