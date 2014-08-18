package erebus.item.hearts;

import net.minecraft.item.Item;
import erebus.ModTabs;

public class SoulCrystal extends Item
{
	public SoulCrystal()
	{
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:soulCrystal");
		setUnlocalizedName("erebus.soulCrystal");
	}
}