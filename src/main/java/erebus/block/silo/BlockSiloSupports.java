package erebus.block.silo;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import erebus.block.BlockSimple;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import erebus.entity.EntityBlackAnt;

public class BlockSiloSupports extends BlockSimple {

	public BlockSiloSupports(Material material) {
		super(material);
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
	public boolean renderAsNormalBlock() {
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity != null && entity instanceof EntityBlackAnt) {
			//this may get used - dunno yet
		}
		super.onEntityCollidedWithBlock(world , x, y, z, entity);
	}
}