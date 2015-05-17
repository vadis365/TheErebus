package erebus.item;

import net.minecraft.item.ItemAxe;
import erebus.ModMaterials;
import erebus.ModTabs;

public class ItemAxeJade extends ItemAxe {

	public ItemAxeJade() {
		super(ModMaterials.toolJADE);
		setCreativeTab(ModTabs.gears);
	}
}