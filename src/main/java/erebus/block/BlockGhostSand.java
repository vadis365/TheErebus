package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockGhostSand extends Block {

	public BlockGhostSand() {
		super(Material.sand);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block side) {
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.sand);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
}