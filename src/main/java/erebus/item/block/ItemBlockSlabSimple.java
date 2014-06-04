package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemBlockSlabSimple extends ItemBlockLocalised {

	public ItemBlockSlabSimple(Block block) {
		super(block);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 0)
			y--;
		if (side == 1)
			y++;
		if (side == 2)
			z--;
		if (side == 3)
			z++;
		if (side == 4)
			x--;
		if (side == 5)
			x++;

		if (!player.canPlayerEdit(x, y, z, side, stack))
			return false;

		Block i1 = world.getBlock(x, y, z);
		int j1 = world.getBlockMetadata(x, y, z);
		int k1 = j1 & 7;
		boolean flag = (j1 & 8) != 0;

		if ((side == 1 && !flag || side == 0 && flag) && i1 == getBlock() && k1 == stack.getItemDamage()) {
			if (world.checkNoEntityCollision(getBlock().getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, getBlock(), k1, 3)) {
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, getBlock().stepSound.getBreakSound(), (getBlock().stepSound.getVolume() + 1.0F) / 2.0F, getBlock().stepSound.getPitch() * 0.8F);
				stack.stackSize--;
			}

			return true;
		}
	}
}