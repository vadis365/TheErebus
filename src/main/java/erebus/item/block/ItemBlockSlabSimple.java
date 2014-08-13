package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemBlockSlabSimple extends ItemBlockLocalised
{

	public ItemBlockSlabSimple(Block block)
	{
		super(block);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (world.getBlock(x, y, z) == getBlock() && meta != 2)
		{
			if (!player.canPlayerEdit(x, y, z, side, stack))
			{
				return false;
			}

			if (side == 1)
			{
				if (meta == 0 && world.checkNoEntityCollision(AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1)))
				{
					playPlaceSound(world, x, y, z);
					world.setBlockMetadataWithNotify(x, y, z, 2, 3);
					stack.stackSize--;
					return true;
				}
			} else if (side == 0)
			{
				if (meta == 1 && world.checkNoEntityCollision(AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1)))
				{
					playPlaceSound(world, x, y, z);
					world.setBlockMetadataWithNotify(x, y, z, 2, 3);
					stack.stackSize--;
					return true;
				}
			}
			return false;
		} else
		{
			int X = x;
			int Y = y;
			int Z = z;
			if (side == 0)
			{
				Y--;
			}
			if (side == 1)
			{
				Y++;
			}
			if (side == 2)
			{
				Z--;
			}
			if (side == 3)
			{
				Z++;
			}
			if (side == 4)
			{
				X--;
			}
			if (side == 5)
			{
				X++;
			}

			if (!player.canPlayerEdit(X, Y, Z, side, stack))
			{
				return false;
			}

			Block block = world.getBlock(X, Y, Z);
			if (block != getBlock())
			{
				return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
			}

			meta = world.getBlockMetadata(X, Y, Z);
			boolean flag = meta != 0;

			if ((side == 1 && !flag || side == 0 && flag) && block == getBlock())
			{
				if (world.checkNoEntityCollision(getBlock().getCollisionBoundingBoxFromPool(world, X, Y, Z)) && world.setBlock(X, Y, Z, getBlock(), meta, 3))
				{
					playPlaceSound(world, X, Y, Z);
					stack.stackSize--;
				}
				return true;
			}
		}
		return false;
	}

	private void playPlaceSound(World world, int x, int y, int z)
	{
		world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, getBlock().stepSound.getBreakSound(), (getBlock().stepSound.getVolume() + 1.0F) / 2.0F, getBlock().stepSound.getPitch() * 0.8F);
	}
}