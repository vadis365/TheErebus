package erebus.world.feature.util;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenRotatedStructurePart extends WorldGenerator {
	protected World world;
	protected Random rand;

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		this.world = world;
		this.rand = rand;
		return generate(pos);
	}

	protected abstract boolean generate(BlockPos pos);

	public void rotatedCubeVolume(World world, BlockPos pos, IBlockState state, int updateFlag, int sizeWidth, int sizeHeight, int sizeDepth, int offsetA, int offsetB, int offsetC, EnumFacing facing) {

		switch (facing) {
		case NORTH:
			for (int yy = offsetB; yy < offsetB + sizeHeight; yy++)
				for (int xx = offsetA; xx < offsetA + sizeWidth; xx++)
					for (int zz = offsetC; zz < offsetC + sizeDepth; zz++) {
						world.setBlockState(pos.add(xx, yy, zz), state, updateFlag);
					}
			break;
		case EAST:
			for (int yy = offsetB; yy < offsetB + sizeHeight; yy++)
				for (int zz = offsetA - 1; zz > offsetA - sizeWidth - 1; zz--)
					for (int xx = offsetC; xx < offsetC + sizeDepth; xx++) {
						world.setBlockState(pos.add(xx, yy, zz), state, updateFlag);
					}
			break;
		case SOUTH:
			for (int yy = offsetB; yy < offsetB + sizeHeight; yy++)
				for (int xx = offsetA - 1; xx > offsetA - sizeWidth - 1; xx--)
					for (int zz = offsetC - 1; zz > offsetC - sizeDepth - 1; zz--) {
						world.setBlockState(pos.add(xx, yy, zz), state, updateFlag);
					}
			break;
		case WEST:
			for (int yy = offsetB; yy < offsetB + sizeHeight; yy++)
				for (int zz = offsetA; zz < offsetA + sizeWidth; zz++)
					for (int xx = offsetC - 1; xx > offsetC - sizeDepth - 1; xx--) {
						world.setBlockState(pos.add(xx, yy, zz), state, updateFlag);
					}
			break;
		case DOWN:
			break;
		case UP:
			break;
		default:
			break;
		}
	}

}
