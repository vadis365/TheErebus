package erebus.block.glowshroom;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.core.helper.Utils;

public class BlockGlowshroomStalkDown3 extends Block {

	public BlockGlowshroomStalkDown3() {
		super(Material.wood);
		setTickRandomly(true);
		setHardness(0.2F);
		setStepSound(Block.soundTypeWood);
		setBlockName("erebus.glowshroomStalkDown3");
		setBlockTextureName("erebus:glowshroomStalk");
		setBlockBounds(0.1875F, 0F, 0.1875F, 0.8125F, 1F, 0.8125F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		world.setBlock(x, y, z, ModBlocks.glowshroomStalkMain);
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
	public boolean canBlockStay(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y + 1, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x, y + 1, z));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isRemote)
			return;

		if (!isValidBlock(world.getBlock(x, y + 1, z)))
			Utils.breakBlockWithParticles(world, x, y, z);
	}

	private boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkMain;
	}
}