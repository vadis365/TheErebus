package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockGhostSand extends Block {

	public BlockGhostSand(int id) {
		super(id, Material.sand);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int side) {
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return Block.sand.blockID;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
}