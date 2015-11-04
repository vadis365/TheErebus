package erebus.item;

import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemPickaxeJade extends ItemPickaxe {

	public ItemPickaxeJade() {
		super(ModMaterials.toolJADE);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:pickaxeJade");
		setUnlocalizedName("erebus.pickaxeJade");
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack material) {
		return Utils.isItemOre(material, "gemJade");
	}
}