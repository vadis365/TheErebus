package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModBlocks;

public class ItemPortalActivator extends Item {

	public ItemPortalActivator(int i) {
		super(i);
		maxStackSize = 1;
		setMaxDamage(64);
		setFull3D();
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if (meta == 0)
			--y;

		if (meta == 1)
			++y;

		if (meta == 2)
			--z;

		if (meta == 3)
			++z;

		if (meta == 4)
			--x;

		if (meta == 5)
			++x;

		if (!player.canPlayerEdit(x, y, z, meta, is))
			return false;
		else {
			int var11 = world.getBlockId(x, y, z);

			if (var11 == 0) {
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				onBlockAdded(world, x, y, z);
			}

			is.damageItem(1, player);
			return true;
		}
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		ModBlocks.portalErebus.tryToCreatePortal(world, x, y, z);
	}
}
