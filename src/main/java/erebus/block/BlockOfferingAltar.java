package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.tileentity.TileEntityOfferingAltar;

public class BlockOfferingAltar extends BlockContainer {
	public BlockOfferingAltar() {
		super(Material.rock);
		setBlockName("offeringAltar");
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int m) {
		return new TileEntityOfferingAltar();
	}
}
