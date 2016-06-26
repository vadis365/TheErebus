package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.blocks.BlockPricklyPear;

public class WorldGenPricklyPairPatch extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		float ang, len;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		for (int attempt = 0, placed = 0, xx, yy, zz; attempt < 48 && placed < 15; ++attempt) {
			ang = (float) (rand.nextDouble() * Math.PI * 2D);
			len = rand.nextFloat() * (0.3F + rand.nextFloat() * 0.7F) * 7F;

			xx = (int) (x + 0.5F + net.minecraft.util.math.MathHelper.cos(ang) * len);
			yy = y + rand.nextInt(3) - rand.nextInt(3);
			zz = (int) (z + 0.5F + net.minecraft.util.math.MathHelper.sin(ang) * len);

			if (world.isAirBlock(new BlockPos(xx, yy, zz)) && world.getBlockState(new BlockPos(xx, yy - 1, zz)).getBlock() == Blocks.SAND) {
				for (int height = 0; height < 1 + rand.nextInt(3); height++) {
					if (world.isAirBlock(new BlockPos(xx, yy + height, zz)))
						world.setBlockState(new BlockPos(xx, yy + height, zz), ModBlocks.PRICKLY_PEAR.getDefaultState());
					if(height == 2)
						world.setBlockState(new BlockPos(xx, yy + height, zz), ModBlocks.PRICKLY_PEAR.getDefaultState().withProperty(BlockPricklyPear.AGE, Integer.valueOf(11)), 4);
				}
				++placed;
			}
		}

		return true;
	}
}
