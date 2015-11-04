package erebus.block;

import erebus.ModTabs;
import erebus.tileentity.TileEntityGlowingJar;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGlowingJar extends BlockContainer {

	public BlockGlowingJar() {
		super(Material.glass);
		setLightLevel(1.0F);
		setStepSound(soundTypeGlass);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.glowingJar");
		setBlockTextureName("erebus:glassAmber");
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
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
		return new TileEntityGlowingJar();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || BlockFence.func_149825_a(world.getBlock(x, y - 1, z)) || world.getBlock(x, y - 1, z) instanceof BlockGlowingJar;
	}
}