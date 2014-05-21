package erebus.creativetab;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabErebus extends CreativeTabs {

	private final List<ItemStack> blockList = new ArrayList<ItemStack>();

	public CreativeTabErebus(String name) {
		super(name);
	}

	public void add(Block... blocks) {
		for (Block block : blocks) {
			blockList.add(new ItemStack(block));
			block.setCreativeTab(this);
		}
	}

	public void add(Item... items) {
		for (Item item : items) {
			blockList.add(new ItemStack(item));
			item.setCreativeTab(this);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void displayAllReleventItems(List list) {
		for (ItemStack s : blockList)
			if (s != null)
				s.getItem().getSubItems(s.getItem(), this, list);
	}

	@Override
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}
}