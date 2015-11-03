package erebus.block.glowshroom;

import java.util.Random;

import erebus.core.helper.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class BlockGlowshroomPart extends Block {

	private final ForgeDirection dir;

	public BlockGlowshroomPart(String partName, ForgeDirection dir) {
		super(Material.wood);
		this.dir = dir;
		setHardness(0.2F);
		setTickRandomly(true);
		setStepSound(Block.soundTypeWood);
		setBlockName("erebus.glowshroom" + partName);
		setBlockTextureName("erebus:glowshroomStalk");
		setBlockBounds(0.1875F, 0.875F, 0.1875F, 0.8125F, 1F, 0.8125F);
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
		return isValidBlock(world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isValidBlock(world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.isRemote)
			return;

		if (!isValidBlock(world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)))
			Utils.breakBlockWithParticles(world, x, y, z);
	}

	protected abstract boolean isValidBlock(Block block);
}