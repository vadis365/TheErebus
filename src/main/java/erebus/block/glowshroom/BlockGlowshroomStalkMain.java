package erebus.block.glowshroom;

import java.util.Random;

import erebus.ModBlocks;
import erebus.core.helper.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockGlowshroomStalkMain extends Block {

	public BlockGlowshroomStalkMain() {
		super(Material.wood);
		setTickRandomly(true);
		setHardness(0.2F);
		setBlockName("erebus.glowshroomStalk");
		setStepSound(Block.soundTypeWood);
		setBlockTextureName("erebus:glowshroomStalk");
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;

		int offset = 1;

		switch (rand.nextInt(5)) {
			case 0:
				if (world.isAirBlock(x, y - offset, z))
					world.setBlock(x, y - offset, z, ModBlocks.glowshroomStalkDown1);
				break;
			case 1:
				if (world.isAirBlock(x, y, z - offset) && world.getBlock(x, y + 1, z) == ModBlocks.glowshroomStalkMain)
					world.setBlock(x, y, z - offset, ModBlocks.glowshroomStalkN1);
				break;
			case 2:
				if (world.isAirBlock(x, y, z + offset) && world.getBlock(x, y + 1, z) == ModBlocks.glowshroomStalkMain)
					world.setBlock(x, y, z + offset, ModBlocks.glowshroomStalkS1);
				break;
			case 3:
				if (world.isAirBlock(x - offset, y, z) && world.getBlock(x, y + 1, z) == ModBlocks.glowshroomStalkMain)
					world.setBlock(x - offset, y, z, ModBlocks.glowshroomStalkW1);
				break;
			case 4:
				if (world.isAirBlock(x + offset, y, z) && world.getBlock(x, y + 1, z) == ModBlocks.glowshroomStalkMain)
					world.setBlock(x + offset, y, z, ModBlocks.glowshroomStalkE1);
				break;
		}

		if (rand.nextInt(10) == 0)
			if (world.isAirBlock(x, y + 1, z))
				world.setBlock(x, y + 1, z, ModBlocks.glowshroom);
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
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y - 1, z)) || isValidBlock(world.getBlock(x, y + 1, z)) || isValidBlock(world.getBlock(x - 1, y, z)) || isValidBlock(world.getBlock(x + 1, y, z)) || isValidBlock(world.getBlock(x, y, z - 1)) || isValidBlock(world.getBlock(x, y, z + 1));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isRemote)
			return;

		if (!canPlaceBlockAt(world, x, y, z))
			Utils.breakBlockWithParticles(world, x, y, z);
	}

	private boolean isValidBlock(Block block) {
		return block.getMaterial().blocksMovement() || block == this;
	}
}