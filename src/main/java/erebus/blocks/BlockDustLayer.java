package erebus.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDustLayer extends Block {
	public static final PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);
	protected static final AxisAlignedBB[] DUST_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };

	public BlockDustLayer() {
		super(Material.GROUND);
		setSoundType(SoundType.SNOW);
		setDefaultState(blockState.getBaseState().withProperty(LAYERS, Integer.valueOf(1)));
		setCreativeTab(ModTabs.BLOCKS);
		setHarvestLevel("shovel", 0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return DUST_AABB[((Integer) state.getValue(LAYERS)).intValue()];
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return ((Integer) worldIn.getBlockState(pos).getValue(LAYERS)).intValue() < 5;
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return ((Integer) state.getValue(LAYERS)).intValue() == 8;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		int i = ((Integer) blockState.getValue(LAYERS)).intValue() - 1;
		float f = 0.125F;
		AxisAlignedBB axisalignedbb = blockState.getBoundingBox(worldIn, pos);
		return new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, (double) ((float) i * 0.125F), axisalignedbb.maxZ);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos.down());
		Block block = iblockstate.getBlock();

		if (block != Blocks.ICE && block != Blocks.PACKED_ICE && block != Blocks.BARRIER) {
			BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP);
			return blockfaceshape == BlockFaceShape.SOLID || iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.down()) || block == this && ((Integer) iblockstate.getValue(LAYERS)).intValue() == 8;
		} else {
			return false;
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		checkAndDropBlock(worldIn, pos, state);
	}

	private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!canPlaceBlockAt(worldIn, pos)) {
			worldIn.setBlockToAir(pos);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, state, te, stack);
		worldIn.setBlockToAir(pos);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;//ModItems.DUSTBALL;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		if (side == EnumFacing.UP) {
			return true;
		} else {
			IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
			return iblockstate.getBlock() == this && ((Integer) iblockstate.getValue(LAYERS)).intValue() >= ((Integer) blockState.getValue(LAYERS)).intValue() ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LAYERS, Integer.valueOf((meta & 7) + 1));
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return ((Integer) worldIn.getBlockState(pos).getValue(LAYERS)).intValue() == 1;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(LAYERS)).intValue() - 1;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return ((Integer) state.getValue(LAYERS)) + 1;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LAYERS });
	}
}