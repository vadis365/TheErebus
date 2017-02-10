package erebus.world.feature.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

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

	protected void rect(Block block, int x1, int z1, int x2, int z2, int y) {
		rect(block, 0, x1, z1, x2, z2, y);
	}

	protected void rect(Block block, int metadata, int x1, int z1, int x2, int z2, int y) {
		for (int x = x1; x <= x2; x++)
			for (int z = z1; z <= z2; z++)
				world.setBlockState(new BlockPos(x, y, z), block == null ? Blocks.AIR.getDefaultState() : block.getStateFromMeta(metadata), 2);
	}

	protected void linex(Block block, int x1, int x2, int z, int y) {
		linex(block, 0, x1, x2, z, y);
	}

	protected void linex(Block block, int metadata, int x1, int x2, int z, int y) {
		for (int x = x1; x <= x2; x++)
			world.setBlockState(new BlockPos(x, y, z), block == null ? Blocks.AIR.getDefaultState() : block.getStateFromMeta(metadata), 2);
	}

	protected void linez(Block block, int z1, int z2, int x, int y) {
		linez(block, 0, z1, z2, x, y);
	}

	protected void linez(Block block, int metadata, int z1, int z2, int x, int y) {
		for (int z = z1; z <= z2; z++)
			world.setBlockState(new BlockPos(x, y, z), block == null ? Blocks.AIR.getDefaultState() : block.getStateFromMeta(metadata), 2);
	}

	protected void block(Block block, int x, int z, int y) {
		block(block, 0, x, z, y);
	}

	protected void block(Block block, int metadata, int x, int z, int y) {
		world.setBlockState(new BlockPos(x, y, z), block == null ? Blocks.AIR.getDefaultState() : block.getStateFromMeta(metadata), 2);
	}
}
