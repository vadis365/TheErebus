package erebus.item;

import net.minecraft.item.ItemStack;
import erebus.ModMaterials;
import erebus.core.helper.Utils;

public class ItemJadePaxel extends ItemPaxel {

	public ItemJadePaxel() {
		super(ModMaterials.toolJADEPAXEL);
		setTextureName("erebus:paxelJade");
		setUnlocalizedName("erebus.paxelJade");
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return Utils.isItemOre(material, "gemJade");
	}
}