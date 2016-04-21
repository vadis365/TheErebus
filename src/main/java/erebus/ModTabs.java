package erebus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModTabs {

	public static CreativeTabs blocks = new CreativeTabs("erebus.block") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.umberstone);
		}
	};
/*
	public static CreativeTabs items = new CreativeTabs("erebus.item") {
		@Override
		public Item getTabIconItem() {
			return ModItems.food;
		}
	};

	public static CreativeTabs gears = new CreativeTabs("erebus.gear") {
		@Override
		public Item getTabIconItem() {
			return ModItems.jadePickaxe;
		}
	};

	public static CreativeTabs specials = new CreativeTabs("erebus.special") {
		@Override
		public Item getTabIconItem() {
			return ModItems.portalActivator;
		}
	};

	public static CreativeTabs plants = new CreativeTabs("erebus.plant") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.dutchCap);
		}
	};*/
}