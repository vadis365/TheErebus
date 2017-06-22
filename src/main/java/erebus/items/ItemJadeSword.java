package erebus.items;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemJadeSword extends ItemSword {

	public ItemJadeSword() {
		super(ModMaterials.TOOL_JADE);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumType.JADE.ordinal();
	}

}

