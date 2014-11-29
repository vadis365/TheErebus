package erebus.creativetab;

import net.minecraft.item.Item;
import erebus.ModBlocks;

public class TabPlants extends CreativeTabErebus {

	public TabPlants() {
		super("erebus.plants");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ModBlocks.dutchCap);
	}
}