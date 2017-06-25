package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVinesErebus extends WorldGenerator {
	int maxVineHeight;
	int variation;

	public WorldGenVinesErebus(int height, int heightVariation) {
		maxVineHeight = height;
		variation = heightVariation;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		for (; pos.getY() < maxVineHeight + rand.nextInt(variation) - rand.nextInt(variation); pos = pos.up()) {
			if (world.isBlockLoaded(pos) && world.isAirBlock(pos))
				for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL.facings())
					if (world.isBlockLoaded(pos.offset(facing))) {
						if (Blocks.VINE.canPlaceBlockOnSide(world, pos, facing)) {
							IBlockState state = Blocks.VINE.getDefaultState()
									.withProperty(BlockVine.NORTH, Boolean.valueOf(facing == EnumFacing.SOUTH))
									.withProperty(BlockVine.EAST, Boolean.valueOf(facing == EnumFacing.WEST))
									.withProperty(BlockVine.SOUTH, Boolean.valueOf(facing == EnumFacing.NORTH))
									.withProperty(BlockVine.WEST, Boolean.valueOf(facing == EnumFacing.EAST));
							world.setBlockState(pos, state, 2);
							break;
						}
					} else
						pos = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
		}
		return true;
	}
}