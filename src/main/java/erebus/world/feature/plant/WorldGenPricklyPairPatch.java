package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenPricklyPairPatch extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		float ang, len;

		for (int attempt = 0, placed = 0, xx, yy, zz; attempt < 48 && placed < 15; ++attempt) {
			ang = (float) (rand.nextDouble() * Math.PI * 2D);
			len = rand.nextFloat() * (0.3F + rand.nextFloat() * 0.7F) * 7F;

			xx = (int) (x + 0.5F + MathHelper.cos(ang) * len);
			yy = y + rand.nextInt(3) - rand.nextInt(3);
			zz = (int) (z + 0.5F + MathHelper.sin(ang) * len);

			if (world.isAirBlock(xx, yy, zz) && world.getBlock(xx, yy - 1, zz) == Blocks.sand) {
				for (int height = 0; height < 1 + rand.nextInt(3); height++) {
					if (world.isAirBlock(xx, yy + height, zz))
						world.setBlock(xx, yy + height, zz, ModBlocks.pricklyPair);
					if(height == 2)
						world.setBlockMetadataWithNotify(xx, yy + height, zz, 11, 4);
				}
				++placed;
			}
		}

		return true;
	}
}
