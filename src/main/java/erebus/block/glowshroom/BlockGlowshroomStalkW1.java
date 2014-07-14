package erebus.block.glowshroom;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import erebus.ModBlocks;

public class BlockGlowshroomStalkW1 extends Block {

	public BlockGlowshroomStalkW1() {
		super(Material.wood);
		setTickRandomly(true);
		setBlockBounds(0.3125F, 0.3125F, 0.3125F, 1F, 0.6875F, 0.6875F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkMain)
			world.setBlock(x, y, z, ModBlocks.glowshroomStalkWE2);
		if (rand.nextInt(2) == 0)
			if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkWE2 && world.isAirBlock(x, y + 1, z)) {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkW3);
				world.setBlock(x, y + 1, z, ModBlocks.glowshroom);
			} else {
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkWE2);
				world.setBlock(x + 1, y, z, ModBlocks.glowshroomStalkMain);
			}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(int id, Random random, int fortune) {
		return null;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x + 1, y, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x + 1, y, z));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isRemote)
			return;

		if (!isValidBlock(world.getBlock(x + 1, y, z)))
			world.setBlockToAir(x, y, z);
	}

	private boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain || block == ModBlocks.glowshroomStalkWE2;
	}
}