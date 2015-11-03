package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkW1 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkW1() {
		super("StalkW1", ForgeDirection.EAST);
		setBlockBounds(0.3125F, 0.3125F, 0.3125F, 1F, 0.6875F, 0.6875F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkMain)
			world.setBlock(x, y, z, ModBlocks.glowshroomStalkWE2);
		if (rand.nextInt(2) == 0)
			if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkWE2 && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkW3);
				world.setBlock(x, y + 1, z, ModBlocks.glowshroom);
			} else {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkWE2);
				world.setBlock(x + 1, y, z, ModBlocks.glowshroomStalkMain);
			}
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain || block == ModBlocks.glowshroomStalkWE2;
	}
}