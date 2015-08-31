package erebus.item;

import erebus.ModMaterials;
import erebus.ModTabs;
import net.minecraft.item.ItemAxe;

public class ItemAxeJade extends ItemAxe {

	public ItemAxeJade() {
		super(ModMaterials.toolJADE);
		setCreativeTab(ModTabs.gears);
	}
}