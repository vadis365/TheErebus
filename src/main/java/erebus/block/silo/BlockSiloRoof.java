package erebus.block.silo;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.block.BlockSimple;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;

public class BlockSiloRoof extends BlockSimple {

	public BlockSiloRoof(Material material) {
		super(material);
        setBlockBounds(0F, 0F, 0F, 1F, 0.75F, 1F);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if(world.getBlock(x, y - 1, z) == ModBlocks.siloTank)
			return true;
		return false;
	}
	
	@Override
	public int getRenderType() {
		return BlockRenderIDs.SILO_ROOF.id();
	}
}