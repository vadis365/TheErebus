package erebus.world.feature.plant;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAlgae extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int attempt = 0; attempt < 300; ++attempt) {
			int xx = x + rand.nextInt(8) - rand.nextInt(8);
			int yy = y + rand.nextInt(4) - rand.nextInt(4);
			int zz = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(xx, yy, zz) && ModBlocks.algae.canPlaceBlockAt(world, xx, yy, zz))
				world.setBlock(xx, yy, zz, ModBlocks.algae, 0, 2);
		}

		return true;
	}
}
