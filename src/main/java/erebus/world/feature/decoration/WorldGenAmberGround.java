package erebus.world.feature.decoration;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenAmberGround extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		boolean plausible = false;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		for (int a = 0; a < 10; a++) {
			if (world.isAirBlock(pos) && world.getBlockState(pos.down()) == Blocks.GRASS.getDefaultState()) {
				plausible = true;
				break;
			}

			if (--y <= 1)
				return false;
		}

		if (!plausible)
			return false;

		float rad = rand.nextFloat() + 2.6F;
		int ceilRad = 1 + (int) Math.ceil(rad);

		for (int xx = -ceilRad; xx <= ceilRad; xx++)
			for (int yy = -ceilRad; yy <= ceilRad; yy++)
				for (int zz = -ceilRad; zz <= ceilRad; zz++)
					if (Math.sqrt(xx * xx + yy * yy + zz * zz) <= rad + rand.nextFloat() * 0.4F)
						world.setBlockState(new BlockPos(x + xx, y + yy, z + zz), ModBlocks.AMBER.getDefaultState(), 2); //ModBlocks.amber

		return true;
	}
}