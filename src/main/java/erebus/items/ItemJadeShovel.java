package erebus.items;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumType;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemJadeShovel extends ItemSpade {

	public ItemJadeShovel() {
		super(ModMaterials.TOOL_JADE);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumType.JADE.ordinal();
	}
}