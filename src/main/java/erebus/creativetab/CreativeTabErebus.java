package erebus.creativetab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public abstract class CreativeTabErebus extends CreativeTabs
{

	public CreativeTabErebus(String lable)
	{
		super(lable);
	}

	public void setTab(Block... blocks)
	{
		for (Block block : blocks)
		{
			if (block != null)
			{
				block.setCreativeTab(this);
			}
		}
	}

	public void setTab(Item... items)
	{
		for (Item item : items)
		{
			if (item != null)
			{
				item.setCreativeTab(this);
			}
		}
	}
}