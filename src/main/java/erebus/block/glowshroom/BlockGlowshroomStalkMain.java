package erebus.block.glowshroom;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import erebus.ModBlocks;

public class BlockGlowshroomStalkMain extends Block {

	public BlockGlowshroomStalkMain() {
		super(Material.wood);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;

		int offset = 1;

		switch (rand.nextInt(5)) {
			case 0:
				if (world.isAirBlock(x, y - offset, z))
					world.setBlock(x, y - offset, z, ModBlocks.glowshroomStalkDown1, 0, 2);
				break;
			case 1:
				if (world.isAirBlock(x, y, z - offset) && world.getBlock(x, y + 1, z) != ModBlocks.umberstone)
					world.setBlock(x, y, z - offset, ModBlocks.glowshroomStalkN1, 0, 2);
				break;
			case 2:
				if (world.isAirBlock(x, y, z + offset) && world.getBlock(x, y + 1, z) != ModBlocks.umberstone)
					world.setBlock(x, y, z + offset, ModBlocks.glowshroomStalkS1, 0, 2);
				break;
			case 3:
				if (world.isAirBlock(x - offset, y, z) && world.getBlock(x, y + 1, z) != ModBlocks.umberstone)
					world.setBlock(x - offset, y, z, ModBlocks.glowshroomStalkW1, 0, 2);
				break;
			case 4:
				if (world.isAirBlock(x + offset, y, z) && world.getBlock(x, y + 1, z) != ModBlocks.umberstone)
					world.setBlock(x + offset, y, z, ModBlocks.glowshroomStalkE1, 0, 2);
				break;
		}

		if (rand.nextInt(10) == 0)
			if (world.isAirBlock(x, y + 1, z))
				world.setBlock(x, y + 1, z, ModBlocks.glowshroom, 0, 2);
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
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y - 1, z)) || isValidBlock(world.getBlock(x, y + 1, z)) || isValidBlock(world.getBlock(x - 1, y, z)) || isValidBlock(world.getBlock(x + 1, y, z)) || isValidBlock(world.getBlock(x, y, z - 1)) || isValidBlock(world.getBlock(x, y, z + 1));
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return 0;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		boolean flag = false;

		if (isValidBlock(world.getBlock(x, y - 1, z)))
			flag = true;

		if (isValidBlock(world.getBlock(x, y + 1, z)))
			flag = true;

		if (isValidBlock(world.getBlock(x, y, z + 1)))
			flag = true;

		if (isValidBlock(world.getBlock(x, y, z - 1)))
			flag = true;

		if (isValidBlock(world.getBlock(x + 1, y, z)))
			flag = true;

		if (isValidBlock(world.getBlock(x - 1, y, z)))
			flag = true;

		if (!flag) {
			breakBlock(world, x, y, z, neighbour, 0);
			world.setBlockToAir(x, y, z);
		}

		super.onNeighborBlockChange(world, x, y, z, neighbour);
	}

	private boolean isValidBlock(Block block) {
		return block.getMaterial().blocksMovement() || block == this;
	}
}