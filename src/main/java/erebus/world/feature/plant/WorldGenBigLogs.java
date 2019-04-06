package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBigLogs extends WorldGenerator {

	private int length = -1;
	private int baseRadius = -1;
	private EnumFacing facing;
	protected IBlockState log;
	protected IBlockState core;
	protected IBlockState ore;
	private boolean genOres;
	
	public WorldGenBigLogs(int length, int baseRadius, EnumFacing facing, IBlockState outerLayer, IBlockState innerLayer, boolean generateOres, IBlockState innerOre) {
		this.length = length;
		this.baseRadius = baseRadius;
		this.facing = facing;
		this.log = outerLayer;
		this.core = innerLayer;
		this.ore = innerOre;
		this.genOres = generateOres;
	}

	public WorldGenBigLogs(int length, int baseRadius, EnumFacing facing, IBlockState outerLayer) {
		this.length = length;
		this.baseRadius = baseRadius;
		this.facing = facing;
		this.log = core = ore = outerLayer; // safety 
		this.genOres = false;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		// Trunk N/S
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
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
							world.setBlockState(new BlockPos(x + i, y + j + baseRadius, zz), log);
							if (rand.nextInt(12) == 0)
								world.setBlockToAir(new BlockPos(x + i, y + j + baseRadius, zz));
							if (zz == z - length && rand.nextInt(2) == 0 || zz == z + length - 1 && rand.nextInt(2) == 0)
								world.setBlockToAir(new BlockPos(x + i, y + j + baseRadius, zz));
						} 
						else if(Math.round(Math.sqrt(dSq)) < baseRadius && genOres) {
							world.setBlockState(new BlockPos(x + i, y + j + baseRadius, zz), rand.nextInt(6) == 0 ? ore : core);
							if (zz == z - length && rand.nextInt(2) == 0 || zz == z + length - 1 && rand.nextInt(2) == 0)
								world.setBlockToAir(new BlockPos(x + i, y + j + baseRadius, zz));
						}
						else
							world.setBlockToAir(new BlockPos(x + i, y + j + baseRadius, zz));
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
							world.setBlockState(new BlockPos(xx, y + j + baseRadius, z + i), log);
							if (rand.nextInt(12) == 0)
								world.setBlockToAir(new BlockPos(xx, y + j + baseRadius, z + i));
							if (xx == x - length && rand.nextInt(2) == 0 || xx == x + length - 1 && rand.nextInt(2) == 0)
								world.setBlockToAir(new BlockPos(xx, y + j + baseRadius, z + i));
						}
						else if(Math.round(Math.sqrt(dSq)) < baseRadius && genOres) {
							world.setBlockState(new BlockPos(xx, y + j + baseRadius, z + i), rand.nextInt(6) == 0 ? ore : core);
							if (xx == x - length && rand.nextInt(2) == 0 || xx == x + length - 1 && rand.nextInt(2) == 0)
								world.setBlockToAir(new BlockPos(xx, y + j + baseRadius, z + i));
						}
						else
							world.setBlockToAir(new BlockPos(xx, y + j + baseRadius, z + i));
					}
		}
		return true;
	}
}
