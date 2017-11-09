package erebus.recipes;

import erebus.ModItems;
import erebus.ModMaterials;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipePaxel extends ShapedOreRecipe {

	private static final int wildcard = OreDictionary.WILDCARD_VALUE;

	public RecipePaxel() {
		super(ModItems.jadePaxel, "xyz", " w ", " w ", 'x', new ItemStack(ModItems.jadeAxe, 1, wildcard), 'y', new ItemStack(ModItems.jadeShovel, 1, wildcard), 'z', new ItemStack(ModItems.jadePickaxe, 1, wildcard), 'w', "stickWood");
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
}