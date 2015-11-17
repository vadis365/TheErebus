package erebus.item;

import erebus.ModMaterials;
import erebus.ModTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemCavemanClub extends ItemSword {

	public ItemCavemanClub() {
		super(ModMaterials.toolCAVEMANCLUB);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	public boolean getIsRepairable(ItemStack tool, ItemStack material) {
		return Items.bone == material.getItem() ? true : super.getIsRepairable(tool, material);
	}
}
