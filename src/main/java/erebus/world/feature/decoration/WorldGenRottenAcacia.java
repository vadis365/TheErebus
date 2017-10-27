package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import erebus.blocks.BlockHollowLog;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRottenAcacia extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int len = rand.nextInt(3) + 3;
		int offsetX = rand.nextInt(2), offsetZ = 1 - offsetX;

		for (int a = 0; a < len; a++)
			if (!world.isAirBlock(pos.add(offsetX * a, 0, offsetZ * a)) || world.isAirBlock(pos.add(offsetX * a, - 1, offsetZ * a)))
				return false;

		for (int a = 0; a < len; a++)
			world.setBlockState(pos.add(offsetX * a, 0, offsetZ * a), ModBlocks.HOLLOW_LOG.getDefaultState().withProperty(BlockHollowLog.FACING, offsetX == 0 ? EnumFacing.SOUTH : EnumFacing.EAST), 2);

		return true;
	}
}