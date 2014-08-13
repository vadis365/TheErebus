package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModTabs;

public class Compost extends Item
{

	public Compost()
	{
		setCreativeTab(ModTabs.items);
		setUnlocalizedName("compost");
		setTextureName("erebus:compost");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (ItemDye.applyBonemeal(stack, world, x, y, z, player))
		{
			if (!world.isRemote)
			{
				world.playAuxSFX(2005, x, y, z, 0);
			}
			return true;
		}
		return false;
	}
}