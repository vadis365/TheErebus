package erebus.item.hearts;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.api.animationmagic.EnergyType;
import erebus.api.animationmagic.IEnergyStorage;

public class TerraHeart extends Item implements IEnergyStorage
{
	public TerraHeart()
	{
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:heartTerra");
		setUnlocalizedName("erebus.terraHeart");
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean isComplex)
	{
		for (EnergyType type : EnergyType.values())
		{
			if (canStore(stack, type))
			{
				tooltip.add(type.toString() + " - " + getCurrentStorage(stack, type));
			}
		}
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type)
	{
		return 25000;
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