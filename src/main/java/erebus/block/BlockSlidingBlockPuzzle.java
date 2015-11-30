package erebus.block;

import erebus.core.helper.Utils;
import erebus.tileentity.TileEntitySlidingBlockPuzzle;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSlidingBlockPuzzle extends BlockContainer {

	public BlockSlidingBlockPuzzle() {
		super(Material.rock);
		setBlockUnbreakable();
		setBlockName("erebus.sldingBlockPuzzle");
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntitySlidingBlockPuzzle tile = Utils.getTileEntity(world, x, y, z, TileEntitySlidingBlockPuzzle.class);
		if (tile != null) {
			// TODO implement the game logic
		}

		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySlidingBlockPuzzle();
	}
}