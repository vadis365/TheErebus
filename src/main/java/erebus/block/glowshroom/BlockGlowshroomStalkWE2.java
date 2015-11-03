package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import erebus.core.helper.Utils;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkWE2 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkWE2() {
		super("StalkWE2", ForgeDirection.UNKNOWN);
		setBlockBounds(0F, 0.1875F, 0.1875F, 1F, 0.8125F, 0.8125F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;

		if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkMain) {
			if (rand.nextInt(2) == 0 && world.isAirBlock(x - 1, y, z))
				world.setBlock(x - 1, y, z, ModBlocks.glowshroomStalkW1);
			else if (world.getBlock(x - 1, y, z) == ModBlocks.glowshroomStalkW1)
				return;
			else if (world.getBlock(x - 1, y, z) == ModBlocks.glowshroomStalkW3)
				return;
			else
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain);
		}

		else if (world.getBlock(x - 1, y, z) == ModBlocks.glowshroomStalkMain)
			if (rand.nextInt(2) == 0 && world.isAirBlock(x + 1, y, z))
				world.setBlock(x + 1, y, z, ModBlocks.glowshroomStalkE1);
			else if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkE1)
				return;
			else if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkE3)
				return;
			else
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x + 1, y, z)) || isValidBlock(world.getBlock(x - 1, y, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x + 1, y, z)) || isValidBlock(world.getBlock(x - 1, y, z));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isRemote)
			return;

		if (!isValidBlock(world.getBlock(x + 1, y, z)) && !isValidBlock(world.getBlock(x - 1, y, z)))
			Utils.breakBlockWithParticles(world, x, y, z);
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain;
	}
}