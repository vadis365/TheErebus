package erebus.item.hearts;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.api.animationmagic.EnergyType;
import erebus.api.animationmagic.IEnergyStorage;

public abstract class BaseHeart extends Item implements IEnergyStorage
{
	public BaseHeart(String name)
	{
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:heart" + name.substring(0, 1).toUpperCase() + name.substring(1));
		setUnlocalizedName("erebus." + name.toLowerCase() + "Heart");
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
				tooltip.add(type.toString() + " - " + getCurrentStorage(stack, type) + "/" + getMaxStorage(stack, type));
			}
		}
	}

	@Override
	public int getCurrentStorage(ItemStack stack, EnergyType type)
	{
		NBTTagCompound nbt = getNBT(stack);
		if (nbt.hasKey(type.toString()))
		{
			return nbt.getInteger(type.toString());
		}
		return 0;
	}

	@Override
	public int addEnergy(ItemStack stack, EnergyType type, int amount)
	{
		NBTTagCompound nbt = getNBT(stack);
		int current = Math.max(0, nbt.getInteger(type.toString()));
		int newValue = Math.min(getMaxStorage(stack, type), current + amount);
		nbt.setInteger(type.toString(), newValue);

		return newValue - current;
	}

	@Override
	public int extractEnergy(ItemStack stack, EnergyType type, int amount)
	{
		NBTTagCompound nbt = getNBT(stack);
		int current = Math.max(0, nbt.getInteger(type.toString()));
		int newValue = Math.max(0, current - amount);
		nbt.setInteger(type.toString(), newValue);

		return current - newValue;
	}

	@Override
	public boolean canStore(ItemStack stack, EnergyType type)
	{
		return true;
	}

	private NBTTagCompound getNBT(ItemStack stack)
	{
		if (!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		return stack.getTagCompound();
	}
}