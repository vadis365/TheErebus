package erebus.item;

import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemJadeSword extends ItemSword {

	public ItemJadeSword() {
		super(ModMaterials.toolJADE);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:swordJade");
		setUnlocalizedName("erebus.swordJade");
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return Utils.isItemOre(material, "gemJade");
	}
}