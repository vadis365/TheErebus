package erebus.item;

import erebus.ModMaterials;
import erebus.core.helper.Utils;
import net.minecraft.item.ItemStack;

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