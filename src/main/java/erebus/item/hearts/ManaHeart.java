package erebus.item.hearts;

import net.minecraft.item.ItemStack;
import erebus.api.animationmagic.EnergyType;

public class ManaHeart extends BaseHeart
{
	public ManaHeart()
	{
		super("mana");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type)
	{
		return 10000;
	}
}