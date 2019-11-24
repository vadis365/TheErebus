package erebus;

import erebus.items.ItemMaterials;
import erebus.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModTabs {

	public static final CreativeTabs BLOCKS = new CreativeTabs(Reference.MOD_ID + ".blocks") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Item.getItemFromBlock(ModBlocks.UMBERSTONE));
		}
	};

	public static final CreativeTabs ITEMS = new CreativeTabs(Reference.MOD_ID + ".items") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.EREBUS_FOOD);
		}
	};

	public static final CreativeTabs GEAR = new CreativeTabs(Reference.MOD_ID + ".gear") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.JADE_PICKAXE);
		}
	};

	public static final CreativeTabs PLANTS = new CreativeTabs(Reference.MOD_ID + ".plants") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.MATERIALS,1, ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.ordinal());
		}
	};
}