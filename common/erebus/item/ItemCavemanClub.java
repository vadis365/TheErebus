package erebus.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import erebus.ErebusMod;

public class ItemCavemanClub extends ItemSword {

	public ItemCavemanClub(int id) {
		super(id, ErebusMod.toolCAVEMANCLUB);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack1, ItemStack itemStack2) {
		return Item.bone.itemID == itemStack2.itemID ? true : super.getIsRepairable(itemStack1, itemStack2);
	}

}
