package erebus.item;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PortalActivator extends Item
{
    public PortalActivator()
    {
		setMaxStackSize(1);
		setMaxDamage(64);
		setFull3D();
	}

	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ)
    {
		if (meta == 0) --y;
		if (meta == 1) ++y;
		if (meta == 2) --z;
		if (meta == 3) ++z;
		if (meta == 4) --x;
		if (meta == 5) ++x;

		if (!player.canPlayerEdit(x, y, z, meta, is)) return false;
		else
        {
			Block block = world.getBlock(x, y, z);

			if (block.isAir(world, x, y, z) && ModBlocks.portalErebus.makePortal(world, x, y, z))
            {
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				is.damageItem(1, player);
				return true;
			}
		}
		
		return false;
	}
}