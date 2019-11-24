package erebus.blocks;

import javax.annotation.Nullable;

import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockWall;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWoodFenceGate extends BlockHorizontal {
	public static final PropertyBool OPEN = PropertyBool.create("open");
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyBool IN_WALL = PropertyBool.create("in_wall");
	protected static final AxisAlignedBB AABB_HITBOX_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
	protected static final AxisAlignedBB AABB_HITBOX_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_HITBOX_ZAXIS_INWALL = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 0.8125D, 0.625D);
	protected static final AxisAlignedBB AABB_HITBOX_XAXIS_INWALL = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.8125D, 1.0D);
	protected static final AxisAlignedBB AABB_COLLISION_BOX_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
	protected static final AxisAlignedBB AABB_COLLISION_BOX_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 1.0D);

	public BlockWoodFenceGate(IBlockState state) {
		super(state.getMaterial(), state.getMaterial().getMaterialMapColor());
		setSoundType(state.getBlock().getSoundType());
		setHardness(2.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, Boolean.valueOf(false)).withProperty(POWERED, Boolean.valueOf(false)).withProperty(IN_WALL, Boolean.valueOf(false)));
		this.setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = this.getActualState(state, source, pos);

		if (((Boolean) state.getValue(IN_WALL)).booleanValue())
			return ((EnumFacing) state.getValue(FACING)).getAxis() == EnumFacing.Axis.X ? AABB_HITBOX_XAXIS_INWALL : AABB_HITBOX_ZAXIS_INWALL;
		else
			return ((EnumFacing) state.getValue(FACING)).getAxis() == EnumFacing.Axis.X ? AABB_HITBOX_XAXIS : AABB_HITBOX_ZAXIS;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumFacing.Axis enumfacing$axis = ((EnumFacing) state.getValue(FACING)).getAxis();

		if (enumfacing$axis == EnumFacing.Axis.Z && (canFenceGateConnectTo(world, pos, EnumFacing.WEST) || canFenceGateConnectTo(world, pos, EnumFacing.EAST)) || enumfacing$axis == EnumFacing.Axis.X && (canFenceGateConnectTo(world, pos, EnumFacing.NORTH) || canFenceGateConnectTo(world, pos, EnumFacing.SOUTH)))
			state = state.withProperty(IN_WALL, Boolean.valueOf(true));

		return state;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirror) {
		return state.withRotation(mirror.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return world.getBlockState(pos.down()).getMaterial().isSolid() ? super.canPlaceBlockAt(world, pos) : false;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess world, BlockPos pos) {
		if (((Boolean) blockState.getValue(OPEN)).booleanValue())
			return NULL_AABB;
		else
			return ((EnumFacing) blockState.getValue(FACING)).getAxis() == EnumFacing.Axis.Z ? AABB_COLLISION_BOX_ZAXIS : AABB_COLLISION_BOX_XAXIS;
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
	public boolean isPassable(IBlockAccess world, BlockPos pos) {
		return ((Boolean) world.getBlockState(pos).getValue(OPEN)).booleanValue();
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		boolean flag = world.isBlockPowered(pos);
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(OPEN, Boolean.valueOf(flag)).withProperty(POWERED, Boolean.valueOf(flag)) .withProperty(IN_WALL, Boolean.valueOf(false));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (((Boolean) state.getValue(OPEN)).booleanValue()) {
			state = state.withProperty(OPEN, Boolean.valueOf(false));
			world.setBlockState(pos, state, 10);
		} else {
			EnumFacing enumfacing = EnumFacing.fromAngle((double) player.rotationYaw);
			if (state.getValue(FACING) == enumfacing.getOpposite())
				state = state.withProperty(FACING, enumfacing);
			state = state.withProperty(OPEN, Boolean.valueOf(true));
			world.setBlockState(pos, state, 10);
		}
		world.playEvent(player, ((Boolean) state.getValue(OPEN)).booleanValue() ? 1008 : 1014, pos, 0);
		return true;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (!world.isRemote) {
			boolean flag = world.isBlockPowered(pos);
			if (((Boolean) state.getValue(POWERED)).booleanValue() != flag) {
				world.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(flag)).withProperty(OPEN, Boolean.valueOf(flag)), 2);
				if (((Boolean) state.getValue(OPEN)).booleanValue() != flag)
					world.playEvent((EntityPlayer) null, flag ? 1008 : 1014, pos, 0);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(OPEN, Boolean.valueOf((meta & 4) != 0)).withProperty(POWERED, Boolean.valueOf((meta & 8) != 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();

		if (((Boolean) state.getValue(POWERED)).booleanValue()) {
			i |= 8;
		}

		if (((Boolean) state.getValue(OPEN)).booleanValue()) {
			i |= 4;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, OPEN, POWERED, IN_WALL });
	}

	@Override
	public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		Block connector = world.getBlockState(pos.offset(facing)).getBlock();
		return connector instanceof BlockFence || connector instanceof BlockWall;
	}

	private boolean canFenceGateConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		Block block = world.getBlockState(pos.offset(facing)).getBlock();
		return block.canBeConnectedTo(world, pos.offset(facing), facing.getOpposite());
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing) {
		if (facing != EnumFacing.UP && facing != EnumFacing.DOWN)
			return ((EnumFacing) state.getValue(FACING)).getAxis() == facing.rotateY().getAxis() ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.UNDEFINED;
		else
			return BlockFaceShape.UNDEFINED;
	}

}