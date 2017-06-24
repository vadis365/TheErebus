package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.blocks.BlockBerryBush;

public class WorldGenSwampBush extends WorldGenerator {
	private IBlockState[] bushType = {
			ModBlocks.HEART_BERRY_BUSH.getDefaultState().withProperty(BlockBerryBush.BUSH_AGE, Integer.valueOf(2)),
			ModBlocks.SWAMP_BERRY_BUSH.getDefaultState().withProperty(BlockBerryBush.BUSH_AGE, Integer.valueOf(2)), 
			ModBlocks.JADE_BERRY_BUSH.getDefaultState().withProperty(BlockBerryBush.BUSH_AGE, Integer.valueOf(2))
			};

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		float ang, len;
		int randomBush = rand.nextInt(2);
		for (int attempt = 0, placed = 0, xx, yy, zz; attempt < 10 && placed < 5; ++attempt) {
			ang = (float) (rand.nextDouble() * Math.PI * 2D);
			len = rand.nextFloat() * (0.3F + rand.nextFloat() * 0.7F) * 7F;

			xx = (int) (x + 0.5F + MathHelper.cos(ang) * len);
			yy = y + rand.nextInt(3) - rand.nextInt(3);
			zz = (int) (z + 0.5F + MathHelper.sin(ang) * len);
			BlockPos posBush = new BlockPos(xx, yy, zz);

			if (world.isAirBlock(posBush) && world.getBlockState(posBush.down()) == ModBlocks.UMBERSTONE.getDefaultState()) {
				world.setBlockState(posBush.down(), Blocks.DIRT.getDefaultState(), 2);
				world.setBlockState(posBush, bushType[2], 2);
				++placed;
			} else if (world.isAirBlock(posBush) && world.getBlockState(posBush.down()) == Blocks.GRASS.getDefaultState()) {
				world.setBlockState(posBush, bushType[randomBush], 2);
				++placed;
			}
		}

		return true;
	}
}