package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkS1 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkS1() {
		super("StalkS1", ForgeDirection.NORTH);
		setBlockBounds(0.3125F, 0.3125F, 0F, 0.6875F, 0.6875F, 0.6875F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (world.getBlock(x, y, z - 1) == ModBlocks.glowshroomStalkMain)
			world.setBlock(x, y, z, ModBlocks.glowshroomStalkNS2);
		if (rand.nextInt(2) == 0)
			if (world.getBlock(x, y, z - 1) == ModBlocks.glowshroomStalkNS2 && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkS3);
				world.setBlock(x, y + 1, z, ModBlocks.glowshroom);
			} else {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkNS2);
				world.setBlock(x, y, z - 1, ModBlocks.glowshroomStalkMain);
			}
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain || block == ModBlocks.glowshroomStalkNS2;
	}
}