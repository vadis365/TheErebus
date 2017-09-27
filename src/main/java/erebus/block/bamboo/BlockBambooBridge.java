package erebus.block.bamboo;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModTabs;
import erebus.blocks.EnumWood;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityBambooBridge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBambooBridge extends Block implements ITileEntityProvider {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockBambooBridge() {
		super(Material.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(ModTabs.BLOCKS);
		setHardness(0.4F);
		setSoundType(SoundType.LADDER);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBambooBridge();
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
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

	@Override
	   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()), 3);
		onBlockAdded(world, pos, state);
	}

    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, BlockPos pos, net.minecraft.client.particle.ParticleManager manager) {
        return true;
    }

	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
		world.playEvent(2001, pos, Block.getStateId(EnumWood.BAMBOO.getLog().getDefaultState()));
		super.breakBlock(world, pos, state);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		TileEntityBambooBridge te = Utils.getTileEntity(world, pos, TileEntityBambooBridge.class);
		boolean front = canConnectBridgeTo(world, pos.add(0, 0, - 1));
		boolean back = canConnectBridgeTo(world, pos.add(0, 0, 1));
		boolean left = canConnectBridgeTo(world, pos.add(- 1, 0, 0));
		boolean right = canConnectBridgeTo(world, pos.add(1, 0, 0));

		switch (state.getValue(FACING)) {
			case NORTH: //North
				if (!right)
					te.setRenderSide1(true);
				if (!left)
					te.setRenderSide2(true);
				if (right)
					te.setRenderSide1(false);
				if (left)
					te.setRenderSide2(false);
				break;
			case SOUTH: //SOUTH
				if (!right)
					te.setRenderSide2(true);
				if (!left)
					te.setRenderSide1(true);
				if (right)
					te.setRenderSide2(false);
				if (left)
					te.setRenderSide1(false);
				break;
			case EAST: // WEST
				if (!back)
					te.setRenderSide1(true);
				if (!front)
					te.setRenderSide2(true);
				if (back)
					te.setRenderSide1(false);
				if (front)
					te.setRenderSide2(false);
				break;
			case WEST: //EAST
				if (!back)
					te.setRenderSide2(true);
				if (!front)
					te.setRenderSide1(true);
				if (back)
					te.setRenderSide2(false);
				if (front)
					te.setRenderSide1(false);
				break;
		}
		world.notifyBlockUpdate(pos, state, state, 2);
	}

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		onBlockAdded(world, pos, state);
	}

	public static double PIXEL = 0.0625F;
    public static final AxisAlignedBB  RIGHT_AABB = new AxisAlignedBB(1.0D - PIXEL * 2D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
    public static final AxisAlignedBB LEFT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D + PIXEL * 2D, 0.875D, 1.0D);
    public static final AxisAlignedBB BACK_AABB = new AxisAlignedBB(0.0D, 0.0D, 1.0D - PIXEL * 2D, 1.0D, 0.875D, 1.0D);
    public static final AxisAlignedBB FRONT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 0.0D + PIXEL * 2D);
    public static final AxisAlignedBB BASE = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, PIXEL * 2D, 1.0D);

    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        if (!p_185477_7_)
            state = state.getActualState(world, pos);

		boolean front = canConnectBridgeTo(world, pos.add(0, 0, - 1));
		boolean back = canConnectBridgeTo(world, pos.add(0, 0, 1));
		boolean left = canConnectBridgeTo(world, pos.add(- 1, 0, 0));
		boolean right = canConnectBridgeTo(world, pos.add(1, 0, 0));

        addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE);

		if (state.getValue(FACING).equals(EnumFacing.NORTH) || state.getValue(FACING).equals(EnumFacing.SOUTH)) {
			if (!right)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, RIGHT_AABB);
			if (!left)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, LEFT_AABB);
		}

		if (state.getValue(FACING).equals(EnumFacing.EAST) || state.getValue(FACING).equals(EnumFacing.WEST)) {
			if (!back)
				addCollisionBoxToList(pos, entityBox, collidingBoxes, BACK_AABB);
			if (!front) 
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FRONT_AABB);
		}
    }

	public boolean canConnectBridgeTo(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (block != this)
			return !world.isAirBlock(pos) && state.isNormalCube() ? state.getMaterial() != Material.GOURD : false;
		else
			return true;
	}
}