package erebus.block.glowshroom;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import erebus.ModBlocks;

public class BlockGlowshroomStalkWE2 extends Block {

	public BlockGlowshroomStalkWE2() {
		super(Material.wood);
		setTickRandomly(true);
		setBlockBounds(0F, 0.1875F, 0.1875F, 1F, 0.8125F, 0.8125F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkMain) {
			if (rand.nextInt(2) == 0 && world.isAirBlock(x - 1, y, z))
				world.setBlock(x - 1, y, z, ModBlocks.glowshroomStalkW1, 0, 2);
			else if (world.getBlock(x - 1, y, z) == ModBlocks.glowshroomStalkW1)
				return;
			else if (world.getBlock(x - 1, y, z) == ModBlocks.glowshroomStalkW3)
				return;
			else
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain, 0, 2);
		}

		else if (world.getBlock(x - 1, y, z) == ModBlocks.glowshroomStalkMain)
			if (rand.nextInt(2) == 0 && world.isAirBlock(x + 1, y, z))
				world.setBlock(x + 1, y, z, ModBlocks.glowshroomStalkE1, 0, 2);
			else if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkE1)
				return;
			else if (world.getBlock(x + 1, y, z) == ModBlocks.glowshroomStalkE3)
				return;
			else
				world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain, 0, 2);
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
		return isValidBlock(world.getBlock(x + 1, y, z)) || isValidBlock(world.getBlock(x - 1, y, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x + 1, y, z)) || isValidBlock(world.getBlock(x - 1, y, z));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isRemote)
			return;

		int meta = world.getBlockMetadata(x, y, z);
		boolean flag = false;
		if (isValidBlock(world.getBlock(x + 1, y, z)))
			flag = true;
		if (isValidBlock(world.getBlock(x - 1, y, z)))
			flag = true;
		if (!flag) {
			breakBlock(world, x, y, z, neighbour, meta);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	private boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain;
	}
}