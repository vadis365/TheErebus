package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.blocks.BlockLogErebus;
import erebus.blocks.EnumWood;

public class WorldGenRottenLogs extends WorldGenerator {

	private int length = -1;
	private int baseRadius = -1;
	private byte direction;
	protected Block log;

	public WorldGenRottenLogs(int length, int baseRadius, byte direction) {
		this.length = length;
		this.baseRadius = baseRadius;
		this.direction = direction;
		log = EnumWood.ROTTEN.getLog();
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		// Trunk N/S
		if (direction == 1) {
			for (int xx = x - baseRadius; baseRadius + x >= xx; xx++)
				for (int zz = z - length; length + z - 1 >= zz; zz++)
					for (int yy = y + 1; yy <= y + baseRadius * 2; yy++)
						if (!world.isAirBlock(new BlockPos(xx, yy, zz)))
							return false;

			for (int zz = z - length; length + z - 1 >= zz; zz++)
				for (int i = baseRadius * -1; i <= baseRadius; ++i)
					for (int j = baseRadius * -1; j <= baseRadius; ++j) {
						double dSq = i * i + j * j;
						if (Math.round(Math.sqrt(dSq)) == baseRadius) {
							world.setBlockState(new BlockPos(x + i, y + j + baseRadius, zz), log.getDefaultState().withProperty(BlockLogErebus.LOG_AXIS, BlockLogErebus.EnumAxis.X), 2);
							if (rand.nextInt(12) == 0)
								world.setBlockState(new BlockPos(x + i, y + j + baseRadius, zz), Blocks.AIR.getDefaultState());
							if (zz == z - length && rand.nextInt(2) == 0 || zz == z + length - 1 && rand.nextInt(2) == 0)
								world.setBlockState(new BlockPos(x + i, y + j + baseRadius, zz), Blocks.AIR.getDefaultState());
						} else
							world.setBlockState(new BlockPos(x + i, y + j + baseRadius, zz), Blocks.AIR.getDefaultState());
					}

		} else {

			// Trunk E/W
			for (int xx = x - length; length + x - 1 >= xx; xx++)
				for (int zz = z - baseRadius; baseRadius + z >= zz; zz++)
					for (int yy = y + 1; yy <= y + baseRadius * 2; yy++)
						if (!world.isAirBlock(new BlockPos(xx, yy, zz)))
							return false;

			for (int xx = x - length; length + x - 1 >= xx; xx++)
				for (int i = baseRadius * -1; i <= baseRadius; ++i)
					for (int j = baseRadius * -1; j <= baseRadius; ++j) {
						double dSq = i * i + j * j;
						if (Math.round(Math.sqrt(dSq)) == baseRadius) {
							world.setBlockState(new BlockPos(xx, y + j + baseRadius, z + i), log.getDefaultState().withProperty(BlockLogErebus.LOG_AXIS, BlockLogErebus.EnumAxis.Z), 2);
							if (rand.nextInt(12) == 0)
								world.setBlockState(new BlockPos(xx, y + j + baseRadius, z + i), Blocks.AIR.getDefaultState());
							if (xx == x - length && rand.nextInt(2) == 0 || xx == x + length - 1 && rand.nextInt(2) == 0)
								world.setBlockState(new BlockPos(xx, y + j + baseRadius, z + i), Blocks.AIR.getDefaultState());
						} else
							world.setBlockState(new BlockPos(xx, y + j + baseRadius, z + i), Blocks.AIR.getDefaultState());
					}
		}
		return true;
	}
}
