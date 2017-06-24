package erebus.items;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.item.ItemStack;

public class ItemJadePaxel extends ItemPaxel {

	public ItemJadePaxel() {
		super(ModMaterials.TOOL_JADE_PAXEL);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.JADE.ordinal();
	}
}