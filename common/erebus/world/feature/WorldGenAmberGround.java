package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenAmberGround extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		boolean plausible = false;

		for (int a = 0; a < 10; a++) {
			if (world.isAirBlock(x, y, z) && world.getBlockId(x, y - 1, z) == Block.grass.blockID) {
				plausible = true;
				break;
			}

			if (--y <= 1)
				return false;
		}

		if (!plausible)
			return false;

		float rad = random.nextFloat() + 2.6F;
		int ceilRad = 1 + (int) Math.ceil(rad);

		for (int xx = -ceilRad; xx <= ceilRad; xx++)
			for (int yy = -ceilRad; yy <= ceilRad; yy++)
				for (int zz = -ceilRad; zz <= ceilRad; zz++)
					if (Math.sqrt(xx * xx + yy * yy + zz * zz) <= rad + random.nextFloat() * 0.4F)
						world.setBlock(x + xx, y + yy, z + zz, ModBlocks.blockAmber.blockID);

		return true;
	}
}
