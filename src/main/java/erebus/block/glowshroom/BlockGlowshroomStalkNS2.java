package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import erebus.core.helper.Utils;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGlowshroomStalkNS2 extends BlockGlowshroomPart {

	public BlockGlowshroomStalkNS2() {
		super("StalkNS2", ForgeDirection.UNKNOWN);
		setBlockBounds(0.1875F, 0.1875F, 0F, 0.8125F, 0.8125F, 1F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (world.getBlock(x, y, z + 1) == ModBlocks.glowshroomStalkMain) {
			if (rand.nextInt(2) == 0 && world.isAirBlock(x, y, z - 1))
				world.setBlock(x, y, z - 1, ModBlocks.glowshroomStalkN1);
			else if (world.getBlock(x, y, z - 1) == ModBlocks.glowshroomStalkN1)
				return;
			else if (world.getBlock(x, y, z - 1) == ModBlocks.glowshroomStalkN3)
				return;
			else
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain);
		}

		else if (world.getBlock(x, y, z - 1) == ModBlocks.glowshroomStalkMain)
			if (rand.nextInt(2) == 0 && world.isAirBlock(x, y, z + 1))
				world.setBlock(x, y, z + 1, ModBlocks.glowshroomStalkS1);
			else if (world.getBlock(x, y, z + 1) == ModBlocks.glowshroomStalkS1)
				return;
			else if (world.getBlock(x, y, z + 1) == ModBlocks.glowshroomStalkS3)
				return;
			else
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y, z + 1)) || isValidBlock(world.getBlock(x, y, z - 1));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y, z + 1)) || isValidBlock(world.getBlock(x, y, z - 1));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isRemote)
			return;

		if (!isValidBlock(world.getBlock(x, y, z + 1)) && !isValidBlock(world.getBlock(x, y, z - 1)))
			Utils.breakBlockWithParticles(world, x, y, z);
	}

	@Override
	protected boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain;
	}
}