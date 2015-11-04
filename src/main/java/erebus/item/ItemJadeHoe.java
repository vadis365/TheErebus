package erebus.item;

import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemJadeHoe extends ItemHoe {

	public ItemJadeHoe() {
		super(ModMaterials.toolJADE);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:hoeJade");
		setUnlocalizedName("erebus.hoeJade");
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return Utils.isItemOre(material, "gemJade");
	}
}