package erebus.world.feature.tree;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTallJungleTree extends WorldGenerator {

	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int baseHeight = 3 + rand.nextInt(3), branchAmount = 2 + rand.nextInt(4);

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		// check for space

		int treeHeight = baseHeight + branchAmount * 3 + 1;

		if (y <= 0 || y + treeHeight > 255)
			return false;
		int testY;

		for (testY = y + 1; testY < y + baseHeight; ++testY)
			if (!world.isAirBlock(new BlockPos(x, testY, z)))
				return false;

		for (testY = y + baseHeight; testY <= y + treeHeight; ++testY)
			for (int testX = x - 2; testX <= x + 2; ++testX)
				for (int testZ = z - 2; testZ <= z + 2; ++testZ)
					if (!world.isAirBlock(new BlockPos(testX, testY, testZ)))
						return false;

		if (world.getBlockState(new BlockPos(x, y - 1, z)) != Blocks.DIRT.getDefaultState() && world.getBlockState(new BlockPos(x, y - 1, z)) != Blocks.GRASS.getDefaultState())
			return false;

		// generate tree

		for (int yy = 0; yy < baseHeight; yy++)
			world.setBlockState(new BlockPos(x, y + yy, z), Blocks.LOG.getDefaultState(), 3);

		y += baseHeight;
		for (int branch = 0; branch < branchAmount; branch++) {
			// wood in the center
			for (int a = 0; a < 3; a++)
				world.setBlockState(new BlockPos(x, y + branch * 3 + a, z), Blocks.LOG.getDefaultState(), 3);
			// + shaped leaves
			for (int a = 0; a < 2; a++)
				for (int b = 0; b < 4; b++)
					world.setBlockState(new BlockPos(x + offsetX[b], y + branch * 3 + a * 2, z + offsetZ[b]), Blocks.LEAVES.getDefaultState(), 3);
			// circular leaves mid-branch
			for (int a = 0; a < 2; a++)
				for (int b = 0; b < 2; b++)
					world.setBlockState(new BlockPos(x - 1 + a * 2, y + branch * 3 + 1, z - 1 + b * 2), Blocks.LEAVES.getDefaultState(), 3);
			for (int a = 0; a < 4; a++)
				world.setBlockState(new BlockPos(x + offsetX[a] * 2, y + branch * 3 + 1, z + offsetZ[a] * 2), Blocks.LEAVES.getDefaultState(), 3);
		}

		y += branchAmount * 3;
		world.setBlockState(new BlockPos(x, y, z), Blocks.LEAVES.getDefaultState(), 3);

		return true;
	}
}