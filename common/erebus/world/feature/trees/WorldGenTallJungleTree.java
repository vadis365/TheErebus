package erebus.world.feature.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTallJungleTree extends WorldGenerator {

	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int baseHeight = 3 + random.nextInt(3), branchAmount = 2 + random.nextInt(4);

		// check for space

		int treeHeight = baseHeight + branchAmount * 3 + 1;

		if (y <= 0 || y + treeHeight > 255)
			return false;
		int testY;

		for (testY = y + 1; testY < y + baseHeight; ++testY)
			if (!world.isAirBlock(x, testY, z))
				return false;

		for (testY = y + baseHeight; testY <= y + treeHeight; ++testY)
			for (int testX = x - 2; testX <= x + 2; ++testX)
				for (int testZ = z - 2; testZ <= z + 2; ++testZ)
					if (!world.isAirBlock(testX, testY, testZ))
						return false;

		if (world.getBlockId(x, y - 1, z) != Block.dirt.blockID && world.getBlockId(x, y - 1, z) != Block.grass.blockID)
			return false;

		// generate tree

		for (int yy = 0; yy < baseHeight; yy++)
			world.setBlock(x, y + yy, z, Block.wood.blockID, 3, 3);

		y += baseHeight;
		for (int branch = 0; branch < branchAmount; branch++) {
			// wood in the center
			for (int a = 0; a < 3; a++)
				world.setBlock(x, y + branch * 3 + a, z, Block.wood.blockID, 3, 3);
			// + shaped leaves
			for (int a = 0; a < 2; a++)
				for (int b = 0; b < 4; b++)
					world.setBlock(x + offsetX[b], y + branch * 3 + a * 2, z + offsetZ[b], Block.leaves.blockID, 3, 3);
			// circular leaves mid-branch
			for (int a = 0; a < 2; a++)
				for (int b = 0; b < 2; b++)
					world.setBlock(x - 1 + a * 2, y + branch * 3 + 1, z - 1 + b * 2, Block.leaves.blockID, 3, 3);
			for (int a = 0; a < 4; a++)
				world.setBlock(x + offsetX[a] * 2, y + branch * 3 + 1, z + offsetZ[a] * 2, Block.leaves.blockID, 3, 3);
		}

		y += branchAmount * 3;
		world.setBlock(x, y, z, Block.leaves.blockID, 3, 3);

		return true;
	}
}
