package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenErebus extends WorldGenerator {
	private World world;
	protected Random rand;

	@Override
	public final boolean generate(World world, Random rand, int x, int y, int z) {
		this.world = world;
		this.rand = rand;
		boolean res = generate(x, y, z);
		this.world = null;
		this.rand = null;
		return res;
	}

	protected abstract boolean generate(int x, int y, int z);

	/*
	 * PLACING BLOCKS
	 */

	protected final boolean setBlock(int x, int y, int z, Block block) {
		return world.setBlock(x, y, z, block);
	}

	protected final boolean setBlock(int x, int y, int z, Block block, int metadata) {
		return world.setBlock(x, y, z, block, metadata, 2);
	}

	protected final boolean setBlock(int x, int y, int z, Block block, int metadata, boolean update) {
		return world.setBlock(x, y, z, block, metadata, update ? 3 : 2);
	}

	protected final void setBlockRect(int x1, int z1, int x2, int z2, int y, Block block) {
		setBlockRect(x1, z1, x2, z2, y, block, 0);
	}

	protected final void setBlockRect(int x1, int z1, int x2, int z2, int y, Block block, int metadata) {
		for (int xx = x1; xx <= x2; xx++)
			for (int zz = z1; zz <= z2; zz++)
				world.setBlock(xx, y, zz, block, metadata, 2);
	}

	protected final void setBlockPillar(int x, int z, int y1, int y2, Block block) {
		setBlockPillar(x, z, y1, y2, block, 0);
	}

	protected final void setBlockPillar(int x, int z, int y1, int y2, Block block, int metadata) {
		for (int yy = y1; yy <= y2; yy++)
			world.setBlock(x, yy, z, block, metadata, 2);
	}

	protected final void setBlockCube(int x1, int y1, int z1, int x2, int y2, int z2, Block block) {
		setBlockCube(x1, y1, z1, x2, y2, z2, block, 0);
	}

	protected final void setBlockCube(int x1, int y1, int z1, int x2, int y2, int z2, Block block, int metadata) {
		for (int yy = y1; yy <= y2; yy++)
			for (int xx = x1; xx <= x2; xx++)
				for (int zz = z1; zz <= z2; zz++)
					world.setBlock(xx, yy, zz, block, metadata, 2);
	}

	protected final boolean setMetadata(int x, int y, int z, int metadata) {
		return world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
	}

	protected final boolean setMetadata(int x, int y, int z, int metadata, boolean update) {
		return world.setBlockMetadataWithNotify(x, y, z, metadata, update ? 3 : 2);
	}

	/*
	 * RETRIEVING BLOCK INFORMATION
	 */

	protected final Block getBlock(int x, int y, int z) {
		return world.getBlock(x, y, z);
	}

	protected final int getMetadata(int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	protected final Material getMaterial(int x, int y, int z) {
		return world.getBlock(x, y, z).getMaterial();
	}

	protected final boolean isAir(int x, int y, int z) {
		return world.isAirBlock(x, y, z);
	}

	protected final boolean checkAirCube(int x1, int y1, int z1, int x2, int y2, int z2) {
		for (int yy = y1; yy <= y2; yy++)
			for (int xx = x1; xx <= x2; xx++)
				for (int zz = z1; zz <= z2; zz++)
					if (!isAir(xx, yy, zz))
						return false;

		return true;
	}

	protected final boolean checkSolidCube(int x1, int y1, int z1, int x2, int y2, int z2) {
		for (int yy = y1; yy <= y2; yy++)
			for (int xx = x1; xx <= x2; xx++)
				for (int zz = z1; zz <= z2; zz++)
					if (!world.getBlock(xx, yy, zz).isOpaqueCube())
						return false;

		return true;
	}
}
