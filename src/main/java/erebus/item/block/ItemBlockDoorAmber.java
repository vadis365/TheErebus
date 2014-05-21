package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBlockDoorAmber extends Item {

	public Block doorBlock;

	public ItemBlockDoorAmber(Block block) {
		doorBlock = block;
		maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

		if (side != 1)
			return false;
		else {
			++y;
			Block block = doorBlock;
			if (player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is)) {
				if (!block.canPlaceBlockAt(world, x, y, z))
					return false;
				else {
					int direction = MathHelper.floor_double((player.rotationYaw + 180.0F) * 4.0F / 360.0F - 0.5D) & 3;
					ItemDoor.placeDoorBlock(world, x, y, z, direction, block);
					--is.stackSize;
					return true;
				}
			} else
				return false;
		}
	}

	@Override
	public void registerIcons(IconRegister icon) {
		itemIcon = icon.registerIcon("erebus:doorAmber");
	}
}