package erebus.item;

import net.minecraft.item.Item;
import erebus.ModTabs;

public class SoulCrystal extends Item
{
	public SoulCrystal()
	{
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:soulCrystal");
		setUnlocalizedName("erebus.soulCrystal");
	}
}