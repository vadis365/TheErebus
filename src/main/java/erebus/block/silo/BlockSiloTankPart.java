package erebus.block.silo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSiloTankPart extends BlockContainer {
    public BlockSiloTankPart(Material material) {
        super(material);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileEntitySiloTankPart) {
            TileEntitySiloTankPart multiBlock = (TileEntitySiloTankPart) tile;
            if (multiBlock.hasMaster()) {
                if (multiBlock.isMaster()) {
                    if (!multiBlock.checkMultiBlockForm())
                        multiBlock.resetStructure();
                } else {
                    if (!multiBlock.checkForMaster()) {
                        multiBlock.reset();
                        world.markBlockForUpdate(x, y, z);
                    }
                }
            }
        }
        super.onNeighborBlockChange(world, x, y, z, block);
    }
    
	@Override
	public int getRenderType() {
		return 0;
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
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySiloTankPart();
    }
}