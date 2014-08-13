package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.HomingBeeconTextureHandler;

public class HomingBeeconAdvanced extends Item
{

	public HomingBeeconAdvanced()
	{
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return HomingBeeconTextureHandler.beecon;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag)
	{
		if (hasTag(is))
		{
			if (is.stackTagCompound != null && is.stackTagCompound.hasKey("homeX"))
			{
				list.add("Target X: " + is.getTagCompound().getInteger("homeX"));
				list.add("Target Y: " + is.getTagCompound().getInteger("homeY"));
				list.add("Target Z: " + is.getTagCompound().getInteger("homeZ"));
			} else
			{
				list.add("Right click on a Block");
				list.add("to set as target.");
				list.add("Sneak + Right click");
				list.add("to teleport.");
			}
		}
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && hasTag(is))
		{
			Block block = world.getBlock(x, y, z);
			if (!world.isRemote && block != null && !player.isSneaking())
			{
				is.getTagCompound().setInteger("homeX", x);
				is.getTagCompound().setInteger("homeY", y);
				is.getTagCompound().setInteger("homeZ", z);
				player.swingItem();
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
	{
		if (!world.isRemote && player.isSneaking() && is.stackTagCompound.hasKey("homeX"))
		{
			int x = is.getTagCompound().getInteger("homeX");
			int y = is.getTagCompound().getInteger("homeY");
			int z = is.getTagCompound().getInteger("homeZ");
			if (player.worldObj.isAirBlock(x, y + 1, z) && player.worldObj.isAirBlock(x, y + 2, z))
			{
				player.swingItem();
				player.setPositionAndUpdate(x + 0.5D, y + 1D, z + 0.5D);
				player.worldObj.playSoundEffect(x, y, z, "mob.endermen.portal", 1.0F, 1.0F);
			}
		}
		return is;
	}

	private boolean hasTag(ItemStack stack)
	{
		if (!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is, int pass)
	{
		return true;
	}
}