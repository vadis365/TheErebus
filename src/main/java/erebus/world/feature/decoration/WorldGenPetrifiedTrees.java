package erebus.world.feature.decoration;

import java.util.Random;

import erebus.blocks.BlockPetrifiedWoodRock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPetrifiedTrees extends WorldGenerator {

	private int height = -1;
	private int baseRadius = -1;
	protected IBlockState bark;
	protected IBlockState core;
	protected IBlockState ore;
	private boolean genOres;
	
	public WorldGenPetrifiedTrees(int height, int baseRadius, IBlockState barkBlock, IBlockState fillerBlock, boolean genOres, IBlockState oreBlock) {
		this.height = height;
		this.baseRadius = baseRadius;
		this.bark = barkBlock;
		this.core = fillerBlock;
		this.genOres = genOres;
		this.ore = oreBlock;
	}

	public WorldGenPetrifiedTrees(int height, int baseRadius, IBlockState barkBlock) {
		this.baseRadius = baseRadius;
		this.height = height;
		this.bark = this.core = this.ore = barkBlock;
		this.genOres = false;
	}


	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		boolean alternate = rand.nextBoolean();
		for (int x1 = x - baseRadius; x1 <= x + baseRadius; x1++)
			for (int z1 = z - baseRadius; z1 <= z + baseRadius; z1++)
				for (int y1 = y + 1; y1 < y + height; y1++)
					if (!world.isAirBlock(new BlockPos(x1, y1, z1)))
						return false;



		// Trunk
		for (int yy = y; height + y >= yy; yy++) {
			for (int i = -baseRadius; i <= baseRadius; ++i)
				for (int j = -baseRadius; j <= baseRadius; ++j) {
					double dSq = i * i + j * j;
					if (Math.round(Math.sqrt(dSq)) == baseRadius) {
						if (rand.nextInt(4) == 0 && height > 10) {
							world.setBlockState(new BlockPos(x + i, yy, z + j), bark);
						}
					} //else
						//world.setBlockState(new BlockPos(x + i, yy, z + j), Blocks.AIR.getDefaultState());
					if (Math.round(Math.sqrt(dSq)) == baseRadius -1)
						world.setBlockState(new BlockPos(x + i, yy, z + j), bark);
				}
		
			if (yy == y + height - height / 3 || yy == y + height - height / 4) {
				if (alternate) {
					createBranch(world, rand, x + baseRadius, yy - rand.nextInt(2), z, EnumFacing.NORTH, height / 6);
					createBranch(world, rand, x - baseRadius, yy - rand.nextInt(2), z, EnumFacing.SOUTH, height / 6);
					alternate = false;
				} else {
					createBranch(world, rand, x, yy - rand.nextInt(2), z + baseRadius, EnumFacing.EAST, height / 6);
					createBranch(world, rand, x, yy - rand.nextInt(2), z - baseRadius, EnumFacing.WEST, height / 6);
					alternate = true;
				}
			}

			if (yy == y + height - height / 2) {
				if (alternate) {
					createBranch(world, rand, x + baseRadius, yy - rand.nextInt(2), z, EnumFacing.NORTH, height / 8);
					createBranch(world, rand, x - baseRadius, yy - rand.nextInt(2), z, EnumFacing.SOUTH, height / 8);
					alternate = false;

				} else {
					createBranch(world, rand, x, yy - rand.nextInt(2), z + baseRadius, EnumFacing.EAST, height / 8);
					createBranch(world, rand, x, yy - rand.nextInt(2), z - baseRadius, EnumFacing.WEST, height / 8);
					alternate = true;
				}
			}
		}
		return true;
	}
	
	
	private void createBranch(World world, Random rand, int x, int y, int z, EnumFacing dir, int branchLength) {

		for (int i = 0; i <= branchLength; ++i) {

			if (i >= height / 8)
				y++;

			if (dir == EnumFacing.NORTH)
				world.setBlockState(new BlockPos(x + i, y, z), bark.withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.X));

			if (dir == EnumFacing.SOUTH)
				world.setBlockState(new BlockPos(x - i, y, z), bark.withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.X));

			if (dir == EnumFacing.EAST)
				world.setBlockState(new BlockPos(x, y, z + i), bark.withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Z));

			if (dir == EnumFacing.WEST)
				world.setBlockState(new BlockPos(x, y, z - i), bark.withProperty(BlockPetrifiedWoodRock.AXIS, EnumFacing.Axis.Z));
			}
	}

}
