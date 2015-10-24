package erebus.item;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.core.helper.Utils;

public class ItemJadeShovel extends ItemSpade {

	public ItemJadeShovel() {
		super(ModMaterials.toolJADE);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:shovelJade");
		setUnlocalizedName("erebus.shovelJade");
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return Utils.isItemOre(material, "gemJade");
	}
}