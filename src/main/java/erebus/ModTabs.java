package erebus;

import erebus.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ModTabs {

	public static final CreativeTabs BLOCKS = new CreativeTabs(Reference.MOD_ID + ".block") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.STONE);
		}
	};

	public static final CreativeTabs ITEMS = new CreativeTabs(Reference.MOD_ID + ".item") {
		@Override
		public Item getTabIconItem() {
			return ModItems.MATERIALS;
		}
	};
}