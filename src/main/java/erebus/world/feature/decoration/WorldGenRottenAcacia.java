package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRottenAcacia extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int len = rand.nextInt(3) + 3;
		int offsetX = rand.nextInt(2), offsetZ = 1 - offsetX;

		for (int a = 0; a < len; a++)
			if (!world.isAirBlock(x + offsetX * a, y, z + offsetZ * a) || world.isAirBlock(x + offsetX * a, y - 1, z + offsetZ * a))
				return false;

		for (int a = 0; a < len; a++)
			world.setBlock(x + offsetX * a, y, z + offsetZ * a, ModBlocks.hollowLogAcacia, offsetX == 0 ? 0 : 1, 2);

		return true;
	}
}