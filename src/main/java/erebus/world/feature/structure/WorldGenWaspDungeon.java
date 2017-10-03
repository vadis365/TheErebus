package erebus.world.feature.structure;

import erebus.ModBlocks;
import erebus.world.feature.util.WorldGeneratorExt;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class WorldGenWaspDungeon extends WorldGeneratorExt {
	private IBlockState WASP_NEST_BLOCK = ModBlocks.WASP_NEST.getDefaultState();
	private IBlockState WASP_NEST_STAIRS = ModBlocks.STAIRS_WASP_NEST.getDefaultState();
	private IBlockState WASP_SPAWNER = ModBlocks.WASP_SPAWNER.getDefaultState();
	
	@Override
	protected boolean generate(BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		y -= 12 + rand.nextInt(14);
		int testY = y;

		for (; testY > 60; testY--)
			if (world.isAirBlock(pos.add(0, testY, 0)))
				break;
		if (y - 4 - testY > 4 || world.getBlockState(new BlockPos(x, y - 2, z)) != ModBlocks.UMBERSTONE.getDefaultState() || !world.isAirBlock((new BlockPos(x, y - 16, z))))
			return false;

		// Layer 0 (starting from top)

		rect(WASP_NEST_BLOCK, x - 1, z - 1, x + 1, z + 1, y);
		--y;

		// Layer 1

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 1 - a, x + 1 + a, z - 3 + a, y);
			linex(WASP_NEST_BLOCK, x - 1 - a, x + 1 + a, z + 3 - a, y);
		}
		rect(WASP_NEST_BLOCK, x - 3, z - 1, x - 1, z + 1, y);
		rect(WASP_NEST_BLOCK, x + 1, z - 1, x + 3, z + 1, y);
		for (int a = 0; a < 2; a++) {
			linex(getStairRotations(WASP_NEST_STAIRS, 4 + (a == 0 ? 3 : 2)), x - 1, x + 1, z - 1 + 2 * a, y);
			block(getStairRotations(WASP_NEST_STAIRS, 4 + (a == 0 ? 1 : 0)), x - 1 + 2 * a, z, y);
		}
		block(Blocks.AIR.getDefaultState(), x, z, y);
		--y;

		// Layer 2

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 1, x + 1, z - 5 + 10 * a, y);
			linex(WASP_NEST_BLOCK, x - 3, x + 3, z - 4 + 8 * a, y);
			linex(WASP_NEST_BLOCK, x - 4, x - 2, z - 3 + 6 * a, y);
			linex(WASP_NEST_BLOCK, x + 2, x + 4, z - 3 + 6 * a, y);
			linex(WASP_NEST_BLOCK, x - 4, x - 3, z - 2 + 4 * a, y);
			linex(WASP_NEST_BLOCK, x + 3, x + 4, z - 2 + 4 * a, y);
			linez(Blocks.AIR.getDefaultState(), z - 1, z + 1, x - 3 + 6 * a, y);
			linez(Blocks.AIR.getDefaultState(), z - 2, z + 2, x - 2 + 4 * a, y);
		}
		rect(WASP_NEST_BLOCK, x - 5, z - 1, x - 4, z + 1, y);
		rect(WASP_NEST_BLOCK, x + 4, z - 1, x + 5, z + 1, y);
		rect(Blocks.AIR.getDefaultState(), x - 1, z - 3, x + 1, z + 3, y);
		--y;

		// Layer 3

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 3, x + 3, z - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 3, z + 3, x - 5 + 10 * a, y);
			for (int b = 0; b < 2; b++)
				block(WASP_NEST_BLOCK, x - 4 + 8 * a, z - 4 + 8 * b, y);
			linez(Blocks.AIR.getDefaultState(), z - 3, z + 3, x - 4 + 8 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 3, z - 4, x + 3, z + 4, y);
		--y;

		// Layer 4

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 1, x + 1, z - 6 + 12 * a, y);
			linex(WASP_NEST_BLOCK, x - 4, x - 2, z - 5 + 10 * a, y);
			linex(WASP_NEST_BLOCK, x + 2, x + 4, z - 5 + 10 * a, y);
			linex(WASP_NEST_BLOCK, x - 5, x - 4, z - 4 + 8 * a, y);
			linex(WASP_NEST_BLOCK, x + 4, x + 5, z - 4 + 8 * a, y);
			linez(WASP_NEST_BLOCK, z - 3, z - 1, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z + 1, z + 3, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 1, z + 1, x - 6 + 12 * a, y);
			linez(Blocks.AIR.getDefaultState(), z - 1, z + 1, x - 5 + 10 * a, y);
			linez(Blocks.AIR.getDefaultState(), z - 3, z + 3, x - 4 + 8 * a, y);
			linex(Blocks.AIR.getDefaultState(), x - 1, x + 1, z - 5 + 10 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 3, z - 4, x + 3, z + 4, y);
		--y;

		// Layer 5

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 2, x + 2, z - 6 + 12 * a, y);
			linex(WASP_NEST_BLOCK, x - 4, x - 3, z - 5 + 10 * a, y);
			linex(WASP_NEST_BLOCK, x + 3, x + 4, z - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 4, z - 3, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z + 3, z + 4, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 2, z + 2, x - 6 + 12 * a, y);
			linex(Blocks.AIR.getDefaultState(), x - 2, x + 2, z - 5 + 10 * a, y);
			linez(Blocks.AIR.getDefaultState(), z - 2, z + 2, x - 5 + 10 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 4, z - 4, x + 4, z + 4, y);
		--y;

		// Layer 6,7,8

		for (int layer = 0; layer < 3; layer++) {
			for (int a = 0; a < 2; a++) {
				linex(Blocks.AIR.getDefaultState(), x - 3, x + 3, z - 5 + 10 * a, y);
				linez(Blocks.AIR.getDefaultState(), z - 3, z + 3, x - 5 + 10 * a, y);
				linex(WASP_NEST_BLOCK, x - 1, x + 1, z - 7 + 14 * a, y);
				linex(WASP_NEST_BLOCK, x - 3, x - 2, z - 6 + 12 * a, y);
				linex(WASP_NEST_BLOCK, x - 1, x + 1, z - 6 + 12 * a, y);
				linex(WASP_NEST_BLOCK, x + 2, x + 3, z - 6 + 12 * a, y);
				linex(WASP_NEST_BLOCK, x - 5, x - 4, z - 5 + 10 * a, y);
				linex(WASP_NEST_BLOCK, x + 4, x + 5, z - 5 + 10 * a, y);
				block(WASP_NEST_BLOCK, x - 5, z - 4 + 8 * a, y);
				block(WASP_NEST_BLOCK, x + 5, z - 4 + 8 * a, y);
				linez(WASP_NEST_BLOCK, z - 3, z - 2, x - 6 + 12 * a, y);
				linez(WASP_NEST_BLOCK, z - 1, z + 1, x - 6 + 12 * a, y);
				linez(WASP_NEST_BLOCK, z + 2, z + 3, x - 6 + 12 * a, y);
				linez(WASP_NEST_BLOCK, z - 1, z + 1, x - 7 + 14 * a, y);
			}
			rect(Blocks.AIR.getDefaultState(), x - 4, z - 4, x + 4, z + 4, y);
			--y;
		}

		block(WASP_SPAWNER, x, z, y + 3);

		// Layer 9 (copied 5)

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 2, x + 2, z - 6 + 12 * a, y);
			linex(WASP_NEST_BLOCK, x - 4, x - 3, z - 5 + 10 * a, y);
			linex(WASP_NEST_BLOCK, x + 3, x + 4, z - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 4, z - 3, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z + 3, z + 4, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 2, z + 2, x - 6 + 12 * a, y);
			linex(Blocks.AIR.getDefaultState(), x - 2, x + 2, z - 5 + 10 * a, y);
			linez(Blocks.AIR.getDefaultState(), z - 2, z + 2, x - 5 + 10 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 4, z - 4, x + 4, z + 4, y);
		--y;

		// Layer 10 (copied 4)

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 1, x + 1, z - 6 + 12 * a, y);
			linex(WASP_NEST_BLOCK, x - 4, x - 2, z - 5 + 10 * a, y);
			linex(WASP_NEST_BLOCK, x + 2, x + 4, z - 5 + 10 * a, y);
			linex(WASP_NEST_BLOCK, x - 5, x - 4, z - 4 + 8 * a, y);
			linex(WASP_NEST_BLOCK, x + 4, x + 5, z - 4 + 8 * a, y);
			linez(WASP_NEST_BLOCK, z - 3, z - 1, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z + 1, z + 3, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 1, z + 1, x - 6 + 12 * a, y);
			linez(Blocks.AIR.getDefaultState(), z - 1, z + 1, x - 5 + 10 * a, y);
			linez(Blocks.AIR.getDefaultState(), z - 3, z + 3, x - 4 + 8 * a, y);
			linex(Blocks.AIR.getDefaultState(), x - 1, x + 1, z - 5 + 10 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 3, z - 4, x + 3, z + 4, y);
		--y;

		// Layer 11 (copied 3)

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 3, x + 3, z - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 3, z + 3, x - 5 + 10 * a, y);
			for (int b = 0; b < 2; b++)
				block(WASP_NEST_BLOCK, x - 4 + 8 * a, z - 4 + 8 * b, y);
			linez(Blocks.AIR.getDefaultState(), z - 3, z + 3, x - 4 + 8 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 3, z - 4, x + 3, z + 4, y);
		--y;

		// Layer 12

		for (int a = 0; a < 2; a++) {
			linex(WASP_NEST_BLOCK, x - 2, x + 2, z - 5 + 10 * a, y);
			linex(WASP_NEST_BLOCK, x - 3, x + 3, z - 4 + 8 * a, y);
			linez(WASP_NEST_BLOCK, z - 2, z + 2, x - 5 + 10 * a, y);
			linez(WASP_NEST_BLOCK, z - 3, z + 3, x - 4 + 8 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 3, z - 3, x + 3, z + 3, y);
		--y;

		// Layer 13

		for (int a = 0; a < 2; a++) {
			linex(getStairRotations(WASP_NEST_STAIRS, 4 + (a == 0 ? 2 : 3)), x - 3, x + 3, z - 4 + 8 * a, y);
			linez(getStairRotations(WASP_NEST_STAIRS, 4 + (a == 0 ? 0 : 1)), z - 3, z + 3, x - 4 + 8 * a, y);
			linex(WASP_NEST_BLOCK, x - 3, x + 3, z - 3 + 6 * a, y);
			linez(WASP_NEST_BLOCK, z - 2, z + 2, x - 3 + 6 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 2, z - 2, x + 2, z + 2, y);
		--y;

		// Layer 14

		for (int a = 0; a < 2; a++) {
			linex(getStairRotations(WASP_NEST_STAIRS, 4 + (a == 0 ? 3 : 2)), x - 3, x + 3, z - 3 + 6 * a, y);
			linez(getStairRotations(WASP_NEST_STAIRS, 4 + (a == 0 ? 1 : 0)), z - 2, z + 2, x - 3 + 6 * a, y);
		}
		rect(Blocks.AIR.getDefaultState(), x - 2, z - 2, x + 2, z + 2, y);

		return true;
	}

	public IBlockState getStairRotations(IBlockState state, int blockMeta) {
		int direction = blockMeta;
		switch (direction) {
		case 0:
			return state.withProperty(BlockStairs.FACING, EnumFacing.EAST);
		case 1:
			return state.withProperty(BlockStairs.FACING, EnumFacing.WEST);
		case 2:
			return state.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
		case 3:
			return state.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
		case 4:
			return state.withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, EnumHalf.TOP);
		case 5:
			return state.withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, EnumHalf.TOP);
		case 6:
			return state.withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.HALF, EnumHalf.TOP);
		case 7:
			return state.withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, EnumHalf.TOP);
		}
		return state;
	}

}