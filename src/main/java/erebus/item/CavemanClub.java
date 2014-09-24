package erebus.item;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import erebus.ModMaterials;

public class CavemanClub extends ItemSword
{

	public CavemanClub()
	{
		super(ModMaterials.toolCAVEMANCLUB);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack1, ItemStack itemStack2)
	{
		return Items.bone == itemStack2.getItem() ? true : super.getIsRepairable(itemStack1, itemStack2);
	}
}
