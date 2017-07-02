package erebus.world.feature.plant;

import java.util.Random;

import erebus.ModBlocks;
import erebus.blocks.BlockWallPlants;
import erebus.blocks.EnumWood;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMossPatch extends WorldGenerator {
	boolean blockPlaced = false;
	private int mossType = -1;

	public WorldGenMossPatch(int type) {
		super(true);
		mossType = type;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		placeBlockAt(world, rand, pos);
		if (blockPlaced)
			createPatch(world, rand, pos);
		return true;
	}

	private void placeBlockAt(World world, Random rand, BlockPos pos) {
		int offset = 1;
		IBlockState state = ModBlocks.WALL_PLANTS.getDefaultState();
		int randomiseSide = rand.nextInt(6);
		switch (randomiseSide) {
			case 0:
				if (world.isSideSolid(pos.up(), EnumFacing.DOWN) && isValidBlock(world, pos.up())) {
					world.setBlockState(pos, mossType == 0 ? state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOSS_DOWN) : state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOULD_DOWN), 2);
					blockPlaced = true;
				}
				break;
			case 1:
				if (world.isSideSolid(pos.down(), EnumFacing.UP) && isValidBlock(world, pos.down())) {
					world.setBlockState(pos, mossType == 0 ? state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOSS_UP) : state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOULD_UP), 2);
					blockPlaced = true;
				}
				break;
			case 2:
				if (world.isSideSolid(pos.south(), EnumFacing.NORTH) && isValidBlock(world, pos.south())) {
					world.setBlockState(pos, mossType == 0 ? state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOSS_NORTH) : state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOULD_NORTH), 2);
					blockPlaced = true;
				}
				break;
			case 3:
				if (world.isSideSolid(pos.north(), EnumFacing.SOUTH) && isValidBlock(world, pos.north())) {
					world.setBlockState(pos, mossType == 0 ? state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOSS_SOUTH) : state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOULD_SOUTH), 2);
					blockPlaced = true;
				}
				break;
			case 4:
				if (world.isSideSolid(pos.east(), EnumFacing.WEST) && isValidBlock(world, pos.east())) {
					world.setBlockState(pos, mossType == 0 ? state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOSS_WEST) : state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOULD_WEST), 2);
					blockPlaced = true;
				}
				break;
			case 5:
				if (world.isSideSolid(pos.west(), EnumFacing.EAST) && isValidBlock(world, pos.west())) {
					world.setBlockState(pos, mossType == 0 ? state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOSS_EAST) : state.withProperty(BlockWallPlants.TYPE, BlockWallPlants.EnumWallPlantType.MOULD_EAST), 2);
					blockPlaced = true;
				}
				break;
			default:
				blockPlaced = false;
				break;
		}
	}

	private void createPatch(World world, Random rand, BlockPos pos) {
		byte radius = 2;
		for (int xx = - radius; xx <= radius; ++xx)
			for (int zz = - radius; zz <= radius; ++zz)
				for (int yy = - radius; yy <= radius; ++yy)
					if (world.isAirBlock(pos.add(xx, yy, zz)))
						for (int attempt = 0; attempt < 3; attempt++)
							placeBlockAt(world, rand, pos.add(xx, yy, zz));
	}

	private boolean isValidBlock(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock() == EnumWood.ROTTEN.getLog() || world.getBlockState(pos).getBlock() == ModBlocks.UMBERSTONE;
	}

}