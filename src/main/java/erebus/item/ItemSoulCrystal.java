package erebus.item;

import erebus.ModTabs;
import net.minecraft.item.Item;

public class ItemSoulCrystal extends Item {
	public ItemSoulCrystal() {
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:soulCrystal");
		setUnlocalizedName("erebus.soulCrystal");
	}
}