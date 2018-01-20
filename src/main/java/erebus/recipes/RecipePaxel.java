package erebus.recipes;

import erebus.ModItems;
import erebus.ModMaterials;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipePaxel extends ShapedOreRecipe {

	private static final int wildcard = OreDictionary.WILDCARD_VALUE;

	public RecipePaxel() {
		super(new ResourceLocation ("recipe_jade_paxel"), new ItemStack(ModItems.JADE_PAXEL), "xyz", " w ", " w ", 'x', new ItemStack(ModItems.JADE_AXE, 1, wildcard), 'y', new ItemStack(ModItems.JADE_SHOVEL, 1, wildcard), 'z', new ItemStack(ModItems.JADE_PICKAXE, 1, wildcard), 'w', "stickWood");
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting matrix) {
		int currentDurability = 0, totalDurability = 0;

		for (int a = 0; a < 3; a++) {
			ItemStack is = matrix.getStackInSlot(a);
			totalDurability += is.getMaxDamage();
			currentDurability += is.getMaxDamage() - is.getItemDamage();
		}

		return new ItemStack(ModItems.JADE_PAXEL, 1, ModMaterials.TOOL_JADE_PAXEL.getMaxUses() - (int) Math.floor((float) currentDurability * ModMaterials.TOOL_JADE_PAXEL.getMaxUses() / totalDurability));
	}
}