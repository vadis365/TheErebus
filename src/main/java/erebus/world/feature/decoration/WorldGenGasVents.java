package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGasVents extends WorldGenerator {
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		float ang, len;
		for (int attempt = 0, placed = 0, xx, yy, zz; attempt < 10 && placed < 5; ++attempt) {
			ang = (float) (rand.nextDouble() * Math.PI * 2D);
			len = rand.nextFloat() * (0.3F + rand.nextFloat() * 0.7F) * 7F;

			xx = (int) (x + 0.5F + MathHelper.cos(ang) * len);
			yy = y + rand.nextInt(3) - rand.nextInt(3);
			zz = (int) (z + 0.5F + MathHelper.sin(ang) * len);
			BlockPos posNew = new BlockPos(xx, yy, zz);

			if (world.isAirBlock(posNew.up()) && world.getBlockState(posNew) == Blocks.GRASS.getDefaultState()) {
				world.setBlockState(posNew, ModBlocks.SWAMP_VENT.getDefaultState(), 2);
				++placed;
			}
		}

		return true;
	}
}