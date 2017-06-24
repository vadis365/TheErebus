package erebus.items;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemJadeHoe extends ItemHoe {

	public ItemJadeHoe() {
		super(ModMaterials.TOOL_JADE);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.JADE.ordinal();
	}
}