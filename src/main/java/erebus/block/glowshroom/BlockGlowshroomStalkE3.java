package erebus.block.glowshroom;

import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGlowshroomStalkE3 extends Block {

	public BlockGlowshroomStalkE3() {
		super(Material.wood);
		setHardness(0.2F);
		setStepSound(Block.soundTypeWood);
		setUnlocalizedName("erebus.glowshroomStalkE3");
		setBlockTextureName("erebus:glowshroomStalk");
		setBlockBounds(0F, 0.3125F, 0.3125F, 0.6875F, 1F, 0.6875F);
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.GLOWSHROOM_STALK.id();
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
	public int quantityDropped(int meta, int fortune, Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(int id, Random random, int fortune) {
		return null;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x - 1, y, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x - 1, y, z));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isRemote)
			return;

		if (world.getBlock(x, y + 1, z) != ModBlocks.glowshroom)
			world.setBlock(x, y, z, ModBlocks.glowshroomStalkE1);

		if (!isValidBlock(world.getBlock(x - 1, y, z)))
			Utils.breakBlockWithParticles(world, x, y, z);
	}

	private boolean isValidBlock(Block block) {
		return block == ModBlocks.glowshroomStalkWE2;
	}
}