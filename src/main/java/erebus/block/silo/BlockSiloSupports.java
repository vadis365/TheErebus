package erebus.block.silo;

import erebus.ModTabs;
import erebus.block.BlockSimple;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.entity.EntityBlackAnt;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSiloSupports extends BlockSimple {

	public BlockSiloSupports(Material material) {
		super(material);
		setCreativeTab(ModTabs.blocks);
	}

	@Override
	public int getRenderType() {
		return BlockRenderIDs.SILO_SUPPORTS.id();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (!canBlockStay(world, x, y, z)) {
			world.setBlockToAir(x, y, z);
			dropBlockAsItem(world, x, y, z, 0, 0);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return !world.isAirBlock(x, y - 1, z) && world.getBlock(x, y - 1, z).getMaterial().blocksMovement();
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity != null && entity instanceof EntityBlackAnt) {
			// this may get used - dunno yet
		}
		super.onEntityCollidedWithBlock(world, x, y, z, entity);
	}
}