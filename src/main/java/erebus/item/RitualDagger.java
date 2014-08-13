package erebus.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.oredict.OreDictionary;
import erebus.ModMaterials;
import erebus.ModTabs;

public class RitualDagger extends ItemSword
{

	public RitualDagger()
	{
		super(ModMaterials.ritualDagger);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:ritualDagger");
		setUnlocalizedName("erebus.ritualDagger");
	}

	@Override
	public boolean getIsRepairable(ItemStack item, ItemStack material)
	{
		for (int id : OreDictionary.getOreIDs(material))
		{
			if ("ingotGold".equals(OreDictionary.getOreName(id)))
			{
				return true;
			}
		}
		return false;
	}
}