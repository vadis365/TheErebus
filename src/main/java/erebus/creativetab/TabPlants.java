package erebus.creativetab;

import erebus.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabPlants extends CreativeTabs {

	public TabPlants() {
		super("erebus.plants");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ModBlocks.dutchCap);
	}
}