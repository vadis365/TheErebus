package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkE1 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkE1() {
		super("StalkE1", ForgeDirection.WEST);
		setBlockBounds(0F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (world.getBlock(x - 1, y, z) == ModBlocks.glowshroomStalkMain)
			world.setBlock(x, y, z, ModBlocks.glowshroomStalkWE2);
		if (rand.nextInt(2) == 0)
			if (world.getBlock(x - 1, y, z) == ModBlocks.glowshroomStalkWE2 && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkE3);
				world.setBlock(x, y + 1, z, ModBlocks.glowshroom);
			} else {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkWE2);
				world.setBlock(x - 1, y, z, ModBlocks.glowshroomStalkMain);
			}
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain || block == ModBlocks.glowshroomStalkWE2;
	}
}