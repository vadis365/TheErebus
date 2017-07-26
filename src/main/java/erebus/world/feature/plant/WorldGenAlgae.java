package erebus.world.feature.plant;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAlgae extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		for (int attempt = 0; attempt < 300; ++attempt) {
			int xx = rand.nextInt(8) - rand.nextInt(8);
			int yy = rand.nextInt(4) - rand.nextInt(4);
			int zz = rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(pos.add(xx, yy, zz)) && ModBlocks.ALGAE.canPlaceBlockAt(world, pos.add(xx, yy, zz)))
				world.setBlockState(pos.add(xx, yy, zz), ModBlocks.ALGAE.getDefaultState(), 2);
		}

		return true;
	}
}
