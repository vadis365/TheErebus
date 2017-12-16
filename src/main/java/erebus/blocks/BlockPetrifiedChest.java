package erebus.blocks;

import erebus.Erebus;
import erebus.ModTabs;
import erebus.proxy.CommonProxy;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPetrifiedChest extends BlockContainer {
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected static final AxisAlignedBB NORTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D);
	protected static final AxisAlignedBB SOUTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D);
	protected static final AxisAlignedBB WEST_CHEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
	protected static final AxisAlignedBB EAST_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
	protected static final AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
	/** 0 : Normal chest, 1 : Trapped chest */
	public final BlockPetrifiedChest.Type chestType;

	public BlockPetrifiedChest(BlockPetrifiedChest.Type chestTypeIn) {
		super(Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.chestType = chestTypeIn;
		this.setCreativeTab(ModTabs.BLOCKS);
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
	@SideOnly(Side.CLIENT)
	public boolean hasCustomBreakingProgress(IBlockState state) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (source.getBlockState(pos.north()).getBlock() == this) {
			return NORTH_CHEST_AABB;
		} else if (source.getBlockState(pos.south()).getBlock() == this) {
			return SOUTH_CHEST_AABB;
		} else if (source.getBlockState(pos.west()).getBlock() == this) {
			return WEST_CHEST_AABB;
		} else {
			return source.getBlockState(pos.east()).getBlock() == this ? EAST_CHEST_AABB : NOT_CONNECTED_AABB;
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.checkForSurroundingChests(worldIn, pos, state);

		for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
			BlockPos blockpos = pos.offset(enumfacing);
			IBlockState iblockstate = worldIn.getBlockState(blockpos);

			if (iblockstate.getBlock() == this) {
				this.checkForSurroundingChests(worldIn, blockpos, iblockstate);
			}
		}
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
		state = state.withProperty(FACING, enumfacing);
		BlockPos blockpos = pos.north();
		BlockPos blockpos1 = pos.south();
		BlockPos blockpos2 = pos.west();
		BlockPos blockpos3 = pos.east();
		boolean flag = this == worldIn.getBlockState(blockpos).getBlock();
		boolean flag1 = this == worldIn.getBlockState(blockpos1).getBlock();
		boolean flag2 = this == worldIn.getBlockState(blockpos2).getBlock();
		boolean flag3 = this == worldIn.getBlockState(blockpos3).getBlock();

		if (!flag && !flag1 && !flag2 && !flag3) {
			worldIn.setBlockState(pos, state, 3);
		} else if (enumfacing.getAxis() != EnumFacing.Axis.X || !flag && !flag1) {
			if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3)) {
				if (flag2) {
					worldIn.setBlockState(blockpos2, state, 3);
				} else {
					worldIn.setBlockState(blockpos3, state, 3);
				}

				worldIn.setBlockState(pos, state, 3);
			}
		} else {
			if (flag) {
				worldIn.setBlockState(blockpos, state, 3);
			} else {
				worldIn.setBlockState(blockpos1, state, 3);
			}

			worldIn.setBlockState(pos, state, 3);
		}

		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityPetrifiedWoodChest) {
				((TileEntityPetrifiedWoodChest) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

	public IBlockState checkForSurroundingChests(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isRemote) {
			return state;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

			if (iblockstate.getBlock() != this && iblockstate1.getBlock() != this) {
				boolean flag = iblockstate.isFullBlock();
				boolean flag1 = iblockstate1.isFullBlock();

				if (iblockstate2.getBlock() == this || iblockstate3.getBlock() == this) {
					BlockPos blockpos1 = iblockstate2.getBlock() == this ? pos.west() : pos.east();
					IBlockState iblockstate7 = worldIn.getBlockState(blockpos1.north());
					IBlockState iblockstate6 = worldIn.getBlockState(blockpos1.south());
					enumfacing = EnumFacing.SOUTH;
					EnumFacing enumfacing2;

					if (iblockstate2.getBlock() == this) {
						enumfacing2 = (EnumFacing) iblockstate2.getValue(FACING);
					} else {
						enumfacing2 = (EnumFacing) iblockstate3.getValue(FACING);
					}

					if (enumfacing2 == EnumFacing.NORTH) {
						enumfacing = EnumFacing.NORTH;
					}

					if ((flag || iblockstate7.isFullBlock()) && !flag1 && !iblockstate6.isFullBlock()) {
						enumfacing = EnumFacing.SOUTH;
					}

					if ((flag1 || iblockstate6.isFullBlock()) && !flag && !iblockstate7.isFullBlock()) {
						enumfacing = EnumFacing.NORTH;
					}
				}
			} else {
				BlockPos blockpos = iblockstate.getBlock() == this ? pos.north() : pos.south();
				IBlockState iblockstate4 = worldIn.getBlockState(blockpos.west());
				IBlockState iblockstate5 = worldIn.getBlockState(blockpos.east());
				enumfacing = EnumFacing.EAST;
				EnumFacing enumfacing1;

				if (iblockstate.getBlock() == this) {
					enumfacing1 = (EnumFacing) iblockstate.getValue(FACING);
				} else {
					enumfacing1 = (EnumFacing) iblockstate1.getValue(FACING);
				}

				if (enumfacing1 == EnumFacing.WEST) {
					enumfacing = EnumFacing.WEST;
				}

				if ((iblockstate2.isFullBlock() || iblockstate4.isFullBlock()) && !iblockstate3.isFullBlock()
						&& !iblockstate5.isFullBlock()) {
					enumfacing = EnumFacing.EAST;
				}

				if ((iblockstate3.isFullBlock() || iblockstate5.isFullBlock()) && !iblockstate2.isFullBlock()
						&& !iblockstate4.isFullBlock()) {
					enumfacing = EnumFacing.WEST;
				}
			}

			state = state.withProperty(FACING, enumfacing);
			worldIn.setBlockState(pos, state, 3);
			return state;
		}
	}

	public IBlockState correctFacing(World worldIn, BlockPos pos, IBlockState state) {
		EnumFacing enumfacing = null;

		for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
			IBlockState iblockstate = worldIn.getBlockState(pos.offset(enumfacing1));

			if (iblockstate.getBlock() == this) {
				return state;
			}

			if (iblockstate.isFullBlock()) {
				if (enumfacing != null) {
					enumfacing = null;
					break;
				}

				enumfacing = enumfacing1;
			}
		}

		if (enumfacing != null) {
			return state.withProperty(FACING, enumfacing.getOpposite());
		} else {
			EnumFacing enumfacing2 = (EnumFacing) state.getValue(FACING);

			if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock()) {
				enumfacing2 = enumfacing2.getOpposite();
			}

			if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock()) {
				enumfacing2 = enumfacing2.rotateY();
			}

			if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock()) {
				enumfacing2 = enumfacing2.getOpposite();
			}

			return state.withProperty(FACING, enumfacing2);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		int i = 0;
		BlockPos blockpos = pos.west();
		BlockPos blockpos1 = pos.east();
		BlockPos blockpos2 = pos.north();
		BlockPos blockpos3 = pos.south();

		if (worldIn.getBlockState(blockpos).getBlock() == this) {
			if (this.isDoubleChest(worldIn, blockpos)) {
				return false;
			}

			++i;
		}

		if (worldIn.getBlockState(blockpos1).getBlock() == this) {
			if (this.isDoubleChest(worldIn, blockpos1)) {
				return false;
			}

			++i;
		}

		if (worldIn.getBlockState(blockpos2).getBlock() == this) {
			if (this.isDoubleChest(worldIn, blockpos2)) {
				return false;
			}

			++i;
		}

		if (worldIn.getBlockState(blockpos3).getBlock() == this) {
			if (this.isDoubleChest(worldIn, blockpos3)) {
				return false;
			}

			++i;
		}

		return i <= 1;
	}

	private boolean isDoubleChest(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).getBlock() != this) {
			return false;
		} else {
			for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
				if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() == this) {
					return true;
				}
			}

			return false;
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityPetrifiedWoodChest) {
			((TileEntityPetrifiedWoodChest)tileentity).updateContainingBlockInfo();
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {

				player.openGui(Erebus.INSTANCE, CommonProxy.GuiID.PETRIFIED_CHEST.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());

				if (this.chestType == BlockPetrifiedChest.Type.BASIC) {
					player.addStat(StatList.CHEST_OPENED);
				} else if (this.chestType == BlockPetrifiedChest.Type.TRAP) {
					player.addStat(StatList.TRAPPED_CHEST_TRIGGERED);
				}
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPetrifiedWoodChest();
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return this.chestType == BlockPetrifiedChest.Type.TRAP;
	}

	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if (!blockState.canProvidePower()) {
			return 0;
		} else {
			int i = 0;
			TileEntity tileentity = blockAccess.getTileEntity(pos);

			if (tileentity instanceof TileEntityPetrifiedWoodChest) {
				i = ((TileEntityPetrifiedWoodChest) tileentity).numPlayersUsing;
			}

			return MathHelper.clamp(i, 0, 15);
		}
	}

	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.UP ? blockState.getWeakPower(blockAccess, pos, side) : 0;
	}

	public boolean isBlocked(World worldIn, BlockPos pos) {
		return this.isBelowSolidBlock(worldIn, pos) || this.isOcelotSittingOnChest(worldIn, pos);
	}

	private boolean isBelowSolidBlock(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.up()).isSideSolid(worldIn, pos.up(), EnumFacing.DOWN);
	}

	private boolean isOcelotSittingOnChest(World worldIn, BlockPos pos) {
		for (Entity entity : worldIn.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB((double) pos.getX(), (double) (pos.getY() + 1), (double) pos.getZ(), (double) (pos.getX() + 1), (double) (pos.getY() + 2), (double) (pos.getZ() + 1)))) {
			EntityOcelot entityocelot = (EntityOcelot) entity;

			if (entityocelot.isSitting()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	public static enum Type {
		BASIC, TRAP;
	}

	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		return !isDoubleChest(world, pos) && super.rotateBlock(world, pos, axis);
	}
}
