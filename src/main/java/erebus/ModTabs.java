package erebus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import erebus.lib.Reference;

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
			return ModItems.EREBUS_FOOD;
		}
	};

	public static final CreativeTabs GEAR = new CreativeTabs(Reference.MOD_ID + ".gear") {
		@Override
		public Item getTabIconItem() {
			return ModItems.JADE_PICKAXE;
		}
	};
}