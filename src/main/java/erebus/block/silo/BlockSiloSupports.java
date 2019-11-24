package erebus.block.silo;

import javax.annotation.Nullable;

import erebus.ModTabs;
import erebus.blocks.BlockSimple;
import erebus.entity.EntityBlackAnt;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSiloSupports extends BlockSimple {

	public BlockSiloSupports(Material material) {
		super(material);
		setCreativeTab(ModTabs.BLOCKS);
		setHardness(2F);
		setSoundType(SoundType.WOOD);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)  {
		return !world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).getMaterial().blocksMovement();
	}

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (!canPlaceBlockAt(world, pos)) {
			world.setBlockToAir(pos);
			dropBlockAsItem(world, pos, state, 0);
		}
	}

	@Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity != null && entity instanceof EntityBlackAnt) {
			// this may get used - dunno yet
		}
		super.onEntityCollision(world, pos, state, entity);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
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

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
}