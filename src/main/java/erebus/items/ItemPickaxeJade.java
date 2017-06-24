package erebus.items;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemPickaxeJade extends ItemPickaxe {

	public ItemPickaxeJade() {
		super(ModMaterials.TOOL_JADE);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.JADE.ordinal();
	}
}