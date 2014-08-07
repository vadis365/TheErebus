package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import erebus.ModTabs;
import erebus.core.helper.Utils;

public class Planticide extends Item {

	public Planticide() {
		setCreativeTab(ModTabs.items);
		setUnlocalizedName("planticide");
		setTextureName("erebus:planticide");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode)
			stack.stackSize--;

		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY);
		int z = MathHelper.floor_double(player.posZ);

		if (!world.isRemote)
			for (int i = -3; i <= 3; i++)
				for (int j = -3; j <= 3; j++)
					for (int k = -3; k <= 3; k++) {
						Block block = world.getBlock(x + i, y + j, z + k);
						if (block.isLeaves(world, x + i, y + j, z + k))
							Utils.breakBlockWithParticles(world, x + i, y + j, z + k);
						else if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium) {
							Utils.playBreakParticles(world, x + i, y + j, z + k);
							world.setBlock(x + i, y + j, z + k, Blocks.dirt, 1, 3);
						} else if (block instanceof IPlantable)
							Utils.breakBlockWithParticles(world, x + i, y + j, z + k);
					}
		else
			player.swingItem();

		return stack;
	}
}