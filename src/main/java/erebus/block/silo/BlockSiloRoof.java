package erebus.block.silo;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.blocks.BlockSimple;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSiloRoof extends BlockSimple {
	protected static final AxisAlignedBB SILO_ROOF_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
	public BlockSiloRoof(Material material) {
		super(material);
		setCreativeTab(ModTabs.BLOCKS);
		setHardness(3F);
		setSoundType(SoundType.METAL);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)  {
		if (world.getBlockState(pos.down()).getBlock() == ModBlocks.SILO_TANK)
			return true;
		return false;
	}

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (!canPlaceBlockAt(world, pos)) {
			world.setBlockToAir(pos);
			dropBlockAsItem(world, pos, state, 0);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SILO_ROOF_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return SILO_ROOF_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}