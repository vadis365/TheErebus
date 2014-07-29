package erebus.block.silo;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.tileentity.TileEntitySiloIntake;

public class BlockSiloIntake extends BlockContainer {

	public BlockSiloIntake() {
		super(Material.iron);
		setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
	}

	@Override
	public int getRenderType() {
		return -1;
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
		return new TileEntitySiloIntake();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y +1, z);
		if (block == null)
			return false;
		if (block == ModBlocks.reinExo)
			return true;
		if (block.isLeaves(world, x, y - 1, z) && !block.isOpaqueCube())
			return false;
		return block.getMaterial().blocksMovement();
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

}