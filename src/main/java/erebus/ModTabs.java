package erebus;

import erebus.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTabs {

	public static final CreativeTabs BLOCKS = new CreativeTabs(Reference.MOD_ID + ".blocks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.UMBERSTONE);
		}
	};

	public static final CreativeTabs ITEMS = new CreativeTabs(Reference.MOD_ID + ".items") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.EREBUS_FOOD);
		}
	};

	public static final CreativeTabs GEAR = new CreativeTabs(Reference.MOD_ID + ".gear") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.JADE_PICKAXE);
		}
	};
}