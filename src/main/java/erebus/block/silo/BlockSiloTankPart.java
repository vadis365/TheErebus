package erebus.block.silo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.ModBlocks;

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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileEntitySiloTankPart) {
			ItemStack current = player.inventory.getCurrentItem();
			if (current != null && current.getItem() == Item.getItemFromBlock(this) || current != null && current.getItem() == Item.getItemFromBlock(ModBlocks.siloIntake))
				return false;
        	TileEntitySiloTankPart multiBlock = (TileEntitySiloTankPart) tile;
        	if (multiBlock.hasMaster()) {
        		System.out.println("Valid Structure Part");
        		if (multiBlock.isMaster()) {
        			System.out.println(" Also the Master Block");
        			return true;
        			}
        		}
        	}
        return true;
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