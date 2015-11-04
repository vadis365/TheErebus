package erebus.world.feature.plant;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTurnips extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int attempt = 0; attempt < 64; ++attempt) {
			int xx = x + rand.nextInt(8) - rand.nextInt(8);
			int yy = y + rand.nextInt(4) - rand.nextInt(4);
			int zz = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(xx, yy, zz) && world.getBlock(xx, yy - 1, zz) == Blocks.grass)
				world.setBlock(xx, yy, zz, ModBlocks.blockTurnip, 10, 3);
		}

		return true;
	}
}