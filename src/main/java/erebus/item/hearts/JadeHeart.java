package erebus.item.hearts;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import erebus.ModTabs;
import erebus.api.animationmagic.EnergyType;
import erebus.api.animationmagic.IEnergyStorage;

public class JadeHeart extends Item implements IEnergyStorage
{
	public JadeHeart()
	{
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:heartJade");
		setUnlocalizedName("erebus.jadeHeart");
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type)
	{
		return 10000;
	}

	@Override
	public int getCurrentStorage(ItemStack stack, EnergyType type)
	{
		return 0;
	}

	@Override
	public int addEnergy(ItemStack stack, EnergyType type, int amount)
	{
		return 0;
	}

	@Override
	public int extractEnergy(ItemStack stack, EnergyType type, int amount)
	{
		return 0;
	}

	@Override
	public boolean canStore(ItemStack stack, EnergyType type)
	{
		return true;
	}
}