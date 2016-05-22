package erebus;

import erebus.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModTabs {

	public static final CreativeTabs BLOCKS = new CreativeTabs(Reference.MOD_ID + ".blocks") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.UMBERSTONE);
		}
	};

	public static final CreativeTabs ITEMS = new CreativeTabs(Reference.MOD_ID + ".items") {
		@Override
		public Item getTabIconItem() {
			return ModItems.MATERIALS;
		}
	};
}