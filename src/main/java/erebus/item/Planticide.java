package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import erebus.ModTabs;
import erebus.core.helper.Utils;

public class Planticide extends Item {

	public Planticide() {
		setCreativeTab(ModTabs.items);
		setUnlocalizedName("erebus.planticide");
		setTextureName("erebus:planticide");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote)
			for (int i = -2; i <= 2; i++)
				for (int j = -2; j <= 2; j++)
					for (int k = -2; k <= 2; k++) {
						Block block = world.getBlock(x + i, y + j, z + k);
						if (block.isLeaves(world, x + i, y + j, z + k))
							Utils.breakBlockWithParticles(world, x + i, y + j, z + k);
						else if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == Blocks.farmland) {
							Utils.playBreakParticles(world, x + i, y + j, z + k);
							world.setBlock(x + i, y + j, z + k, Blocks.dirt, 1, 3);
						} else if (block instanceof IPlantable || block instanceof IGrowable)
							Utils.breakBlockWithParticles(world, x + i, y + j, z + k);
					}
		else
			player.swingItem();

		if (!player.capabilities.isCreativeMode)
			stack.stackSize--;

		return true;
	}
}