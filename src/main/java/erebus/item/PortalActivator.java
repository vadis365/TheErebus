package erebus.item;

import erebus.ModBlocks;
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
        //System.out.println(world.getBlockMetadata(x, y, z));
		if (ModBlocks.portalErebus.makePortal(world, x, y + 1, z))
        {
			world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			is.damageItem(1, player);
			return true;
		}
        else return false;
	}
}