package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDesertRockGneiss extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		for (int xx = pos.getX() - 3; xx <= pos.getX() + 3; xx++)
			for (int zz = pos.getZ() - 3; zz <= pos.getZ() + 3; zz++)
				if (world.getBlockState(new BlockPos(xx, pos.getY(), zz)) != Blocks.SAND.getDefaultState()) {
					return false;
				}

		float radX, radY, radZ;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (rand.nextBoolean()) {
			radX = rand.nextFloat() * 0.3F + 1.85F;
			radZ = rand.nextFloat() * 0.2F + 1.5F;
		} else {
			radX = rand.nextFloat() * 0.2F + 1.5F;
			radZ = rand.nextFloat() * 0.3F + 1.85F;
		}
		radY = rand.nextFloat() * 0.7F + 2F;

		y += (int) Math.floor(radY);

		for (int a = 0; a < 2; a++) {
			generateEllipsoidAt(world, rand, x, y, z, radX, radY, radZ);

			++y;

			if (radX > radZ)
				x += rand.nextInt(2) * 2 - 1;
			else
				z += rand.nextInt(2) * 2 - 1;
		}

		if (rand.nextInt(5) == 0) {
			IBlockState block;
			for (int attempt = 0, diamonds = 0, diamondAmount = rand.nextInt(2) + 1, iradX = (int) Math.ceil(radX), iradY = (int) Math.ceil(radY), iradZ = (int) Math.ceil(radZ); attempt < 10 && diamonds < diamondAmount; attempt++) {
				int xx = x + rand.nextInt(iradX * 2) - iradX, yy = y + rand.nextInt(iradY * 2) - iradY, zz = z + rand.nextInt(iradZ * 2) - iradZ;
				block = world.getBlockState(new BlockPos(xx, yy, zz));

				if (block == ModBlocks.GNEISS.getDefaultState() || block == ModBlocks.GNEISS_VENT.getDefaultState()) {
					world.setBlockState(new BlockPos(xx, yy, zz), ModBlocks.ORE_ENCRUSTED_DIAMOND.getDefaultState());
					++diamonds;
				}
			}
		}
		return true;
	}

	private void generateEllipsoidAt(World world, Random rand, int x, int y, int z, float radX, float radY, float radZ) {
		for (float xf = x - radX; xf <= x + radX; xf++)
			for (float zf = z - radZ; zf <= z + radZ; zf++)
				for (float yf = y - radY; yf <= y + radY; yf++)
					if (Math.pow(xf - x, 2) / (radX * radX) + Math.pow(yf - y, 2) / (radY * radY) + Math.pow(zf - z, 2) / (radZ * radZ) <= 1.1)
						world.setBlockState(new BlockPos((int) Math.floor(xf), (int) Math.floor(yf), (int) Math.floor(zf)), rand.nextInt(6) == 0 ? ModBlocks.GNEISS_VENT.getDefaultState() : ModBlocks.GNEISS.getDefaultState());
	}
}