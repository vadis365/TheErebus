package erebus.item.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockSlabSimple extends ItemBlockLocalised {

	public ItemBlockSlabSimple(Block block) {
		super(block);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (stack.stackSize == 0)
			return false;
		else if (!player.canPlayerEdit(x, y, z, side, stack))
			return false;
		else {
			Block block = world.getBlock(x, y, z);
			int meta = world.getBlockMetadata(x, y, z);
			boolean flag = meta == 1;

			if (block == field_150939_a && (side == 1 && !flag || side == 0 && flag)) {
				if (world.checkNoEntityCollision(field_150939_a.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, field_150939_a, 2, 3)) {
					world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, field_150939_a.stepSound.func_150496_b(), (field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, field_150939_a.stepSound.getPitch() * 0.8F);
					stack.stackSize--;
				}

				return true;
			} else
				return func_150946_a(stack, player, world, x, y, z, side) ? true : super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack stack) {
		int i1 = x;
		int j1 = y;
		int k1 = z;
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if (block == field_150939_a && meta == 2)
			return true;
		else {
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

			Block block1 = world.getBlock(x, y, z);
			return block1 == field_150939_a ? true : super.func_150936_a(world, i1, j1, k1, side, player, stack);
		}
	}

	private boolean func_150946_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
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

		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if (block == field_150939_a && meta != 2) {
			if (world.checkNoEntityCollision(field_150939_a.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlockMetadataWithNotify(x, y, z, 2, 3)) {
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, field_150939_a.stepSound.func_150496_b(), (field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, field_150939_a.stepSound.getPitch() * 0.8F);
				stack.stackSize--;
			}

			return true;
		} else
			return false;
	}
}