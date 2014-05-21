package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemBlockSlabGeneric extends ItemBlockGeneric {
	private final Block[] slabArray;
	private final int groupId;

	public ItemBlockSlabGeneric(int id, String unlocalizedName, Block[] slabArray, int groupId) {
		super(id, unlocalizedName + groupId);
		this.slabArray = slabArray;
		this.groupId = groupId;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (getBlockID() == slabArray[1 + groupId * 2].blockID)
			return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
		else if (itemStack.stackSize == 0)
			return false;
		else if (!player.canPlayerEdit(x, y, z, side, itemStack))
			return false;
		else {
			int i1 = world.getBlockId(x, y, z);
			int j1 = world.getBlockMetadata(x, y, z);
			int k1 = j1 & 7;
			boolean flag = (j1 & 8) != 0;

			if ((side == 1 && !flag || side == 0 && flag) && i1 == slabArray[0 + groupId * 2].blockID && k1 == itemStack.getItemDamage()) {
				if (world.checkNoEntityCollision(slabArray[1 + groupId * 2].getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, slabArray[1 + groupId * 2].blockID, k1, 3)) {
					world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, slabArray[1 + groupId * 2].stepSound.getPlaceSound(), (slabArray[1 + groupId * 2].stepSound.getVolume() + 1.0F) / 2.0F, slabArray[1 + groupId * 2].stepSound.getPitch() * 0.8F);
					--itemStack.stackSize;
				}

				return true;
			} else
				return func_77888_a(itemStack, player, world, x, y, z, side) ? true : super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean canPlaceItemBlockOnSide(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack itemStack) {
		int i1 = x;
		int j1 = y;
		int k1 = z;
		int l1 = world.getBlockId(x, y, z);
		int i2 = world.getBlockMetadata(x, y, z);
		int j2 = i2 & 7;
		boolean flag = (i2 & 8) != 0;

		if ((side == 1 && !flag || side == 0 && flag) && l1 == slabArray[0 + groupId * 2].blockID && j2 == itemStack.getItemDamage())
			return true;
		else {
			if (side == 0)
				--y;

			if (side == 1)
				++y;

			if (side == 2)
				--z;

			if (side == 3)
				++z;

			if (side == 4)
				--x;

			if (side == 5)
				++x;

			l1 = world.getBlockId(x, y, z);
			i2 = world.getBlockMetadata(x, y, z);
			j2 = i2 & 7;
			flag = (i2 & 8) != 0;
			return l1 == slabArray[0 + groupId * 2].blockID && j2 == itemStack.getItemDamage() ? true : super.canPlaceItemBlockOnSide(world, i1, j1, k1, side, player, itemStack);
		}
	}

	private boolean func_77888_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if (side == 0)
			--y;

		if (side == 1)
			++y;

		if (side == 2)
			--z;

		if (side == 3)
			++z;

		if (side == 4)
			--x;

		if (side == 5)
			++x;

		int i1 = world.getBlockId(x, y, z);
		int j1 = world.getBlockMetadata(x, y, z);
		int k1 = j1 & 7;

		if (i1 == slabArray[0 + groupId * 2].blockID && k1 == itemStack.getItemDamage()) {
			if (world.checkNoEntityCollision(slabArray[1 + groupId * 2].getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, slabArray[1 + groupId * 2].blockID, k1, 3)) {
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, slabArray[1 + groupId * 2].stepSound.getPlaceSound(), (slabArray[1 + groupId * 2].stepSound.getVolume() + 1.0F) / 2.0F, slabArray[1 + groupId * 2].stepSound.getPitch() * 0.8F);
				--itemStack.stackSize;
			}

			return true;
		} else
			return false;
	}
}
