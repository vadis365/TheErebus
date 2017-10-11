package erebus.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import erebus.ModTabs;
import erebus.entity.EntityWoodlouse;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHollowLog extends Block {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public BlockHollowLog() {
		super(Material.WOOD);
		setHardness(0.7F);
		setSoundType(SoundType.WOOD);
		setCreativeTab(ModTabs.BLOCKS);
	}
	
	public static double PIXEL = 0.0625F;
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(1.0D - PIXEL, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, PIXEL, 1.0D, 1.0D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 1.0D - PIXEL, 1.0D, 1.0D, 1.0D);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, PIXEL);
    public static final AxisAlignedBB BASE = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, PIXEL, 1.0D);
    public static final AxisAlignedBB TOP = new AxisAlignedBB(0.0D, 1.0D - PIXEL, 0.0D, 1.0D, 1.0D, 1.0D);
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
		if (!p_185477_7_)
			state = state.getActualState(world, pos);

		addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, TOP);

		if (state.getValue(FACING).equals(EnumFacing.NORTH) || state.getValue(FACING).equals(EnumFacing.SOUTH)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
		}

		if (state.getValue(FACING).equals(EnumFacing.EAST) || state.getValue(FACING).equals(EnumFacing.WEST)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getFront(meta);
		if (facing.getAxis() == EnumFacing.Axis.Y)
			facing = EnumFacing.NORTH;
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		meta = meta | ((EnumFacing) state.getValue(FACING)).getIndex();
		return meta;
	}

	@Override
	 public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote)
			if (world.rand.nextInt(5) == 0) {
				EntityWoodlouse entity = new EntityWoodlouse(world);
				entity.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
				world.spawnEntity(entity);
			}
		super.onBlockDestroyedByPlayer(world, pos, state);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.STICK;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1 + rand.nextInt(4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}