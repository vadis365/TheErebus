package erebus.creativetab;

import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class CreativeTabErebusSpecialItem extends CreativeTabErebus
{

	public CreativeTabErebusSpecialItem()
	{
		super("erebus.special");
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.erebusSpecialItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int func_151243_f()
	{
		return 1;
	}
}