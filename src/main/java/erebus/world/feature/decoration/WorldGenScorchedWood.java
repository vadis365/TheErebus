package erebus.world.feature.decoration;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.blocks.EnumWood;

public class WorldGenScorchedWood extends WorldGenerator {

	private static final int[] offsetX = new int[] { -1, 1, 0, 0 };
	private static final int[] offsetZ = new int[] { 0, 0, -1, 1 };

	@Override
	public boolean generate(World world, Random random, BlockPos pos) {
		int partAmount = random.nextInt(6) + 2;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		// check for space

		int treeHeight = partAmount * 2;

		if (y <= 0 || y + treeHeight > 255)
			return false;

		for (int testY = y + 1; testY <= y + treeHeight; ++testY)
			for (int testX = x - 1; testX <= x + 1; ++testX)
				for (int testZ = z - 1; testZ <= z + 1; ++testZ)
					if (!world.isAirBlock(new BlockPos(testX, testY, testZ)))
						return false;

		// generate tree

		for (int part = 0; part < partAmount; part++) {
			for (int a = 0; a < 2; a++)
			world.setBlockState(new BlockPos(x, y + part * 2 + a, z), EnumWood.SCORCHED.getLog().getStateFromMeta(0), 3);

			for (int a = 0; a < 4; a++)
				world.setBlockState(new BlockPos(x + offsetX[a], y + part * 2, z + offsetZ[a]), EnumWood.SCORCHED.getLog().getStateFromMeta(a < 2 ? 4 : 8), 3);
		}

		return true;
	}

}