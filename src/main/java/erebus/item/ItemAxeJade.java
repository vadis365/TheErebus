package erebus.item;

import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemAxeJade extends ItemAxe {

	public ItemAxeJade() {
		super(ModMaterials.toolJADE);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:axeJade");
		setUnlocalizedName("erebus.axeJade");
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return Utils.isItemOre(material, "gemJade");
	}
}