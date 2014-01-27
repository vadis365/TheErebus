package erebus.creativetab;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabErebus extends CreativeTabs {

	private final List<Short> blockList = new ArrayList<Short>();

	public CreativeTabErebus(String name) {
		super(name);
	}

	public void add(Block... blocks) {
		for (Block block : blocks) {
			blockList.add((short) block.blockID);
			block.setCreativeTab(this);
		}
	}

	public void add(Item... items) {
		for (Item item : items) {
			blockList.add((short) item.itemID);
			item.setCreativeTab(this);
		}
	}

	@Override
	public void displayAllReleventItems(List list) {
		for (short s : blockList) {
			Item item = Item.itemsList[s];
			if (item != null)
				item.getSubItems(item.itemID, this, list);
		}
	}
}
