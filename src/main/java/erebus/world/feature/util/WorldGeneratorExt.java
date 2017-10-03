package erebus.world.feature.util;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGeneratorExt extends WorldGenerator {
	protected World world;
	protected Random rand;

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		this.world = world;
		this.rand = rand;
		return generate(pos);
	}

	protected abstract boolean generate(BlockPos pos);

	// Utilities

	protected void rect(IBlockState state, int x1, int z1, int x2, int z2, int y) {
		for (int x = x1; x <= x2; x++)
			for (int z = z1; z <= z2; z++)
				world.setBlockState(new BlockPos(x, y, z), state, 2);
	}

	protected void linex(IBlockState state, int x1, int x2, int z, int y) {
		for (int x = x1; x <= x2; x++)
			world.setBlockState(new BlockPos(x, y, z), state, 2);
	}

	protected void linez(IBlockState state, int z1, int z2, int x, int y) {
		for (int z = z1; z <= z2; z++)
			world.setBlockState(new BlockPos(x, y, z), state, 2);
	}

	protected void block(IBlockState state, int x, int z, int y) {
		world.setBlockState(new BlockPos(x, y, z), state, 2);
	}
}
