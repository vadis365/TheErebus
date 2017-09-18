package erebus.block.silo;

import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.block.BlockSimple;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockSiloRoof extends BlockSimple {

	public BlockSiloRoof(Material material) {
		super(material);
		setCreativeTab(ModTabs.blocks);
		setBlockBounds(0F, 0F, 0F, 1F, 0.75F, 1F);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if (world.getBlock(x, y - 1, z) == ModBlocks.siloTank)
			return true;
		return false;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (!canBlockStay(world, x, y, z)) {
			world.setBlockToAir(x, y, z);
			dropBlockAsItem(world, x, y, z, 0, 0);
		}
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.SILO_ROOF.id();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}