package erebus.block.bamboo;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import erebus.block.entity.BambooPipeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class BambooPipe extends DirectionalBlock implements EntityBlock {
	public static final MapCodec<BambooPipe> CODEC = simpleCodec(BambooPipe::new);
    public static final BooleanProperty CONNECTED_DOWN = BooleanProperty.create("connected_down");
    public static final BooleanProperty CONNECTED_UP = BooleanProperty.create("connected_up");
    public static final BooleanProperty CONNECTED_NORTH = BooleanProperty.create("connected_north");
    public static final BooleanProperty CONNECTED_SOUTH = BooleanProperty.create("connected_south");
    public static final BooleanProperty CONNECTED_WEST = BooleanProperty.create("connected_west");
    public static final BooleanProperty CONNECTED_EAST = BooleanProperty.create("connected_east");
    
	//public static final EnumProperty<Direction> TYPE = EnumProperty.create("type", Direction.class);
//public static final VoxelShape PIPE_MIDDLE = Block.box(5D, 5D, 5D, 11D, 11D, 11D);
	//public static final VoxelShape CONNECTED_DOWN = Block.box(5D, 0D, 5D, 11D, 11D, 11D);
	//public static final VoxelShape CONNECTED_SOUTH = Block.box(5D, 5D, 5D, 11D, 11D, 16D);
	//public static final VoxelShape CONNECTED_NORTH = Block.box(5D, 5D, 0D, 11D, 11D, 11D);
	//public static final VoxelShape CONNECTED_WEST = Block.box(0D, 5D, 5D, 11D, 11D, 11D);
	//public static final VoxelShape CONNECTED_EAST = Block.box(5D, 5D, 5D, 16D, 11D, 11D);


	public BambooPipe(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(CONNECTED_DOWN, Boolean.FALSE).setValue(CONNECTED_EAST, Boolean.FALSE).setValue(CONNECTED_NORTH, Boolean.FALSE).setValue(CONNECTED_SOUTH, Boolean.FALSE).setValue(CONNECTED_UP, Boolean.FALSE).setValue(CONNECTED_WEST, Boolean.FALSE));
	}
	
	@Override
	protected MapCodec<BambooPipe> codec() {
		return CODEC;
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide ? null : BambooPipeBlockEntity::serverTick;
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return adjacentState.is(this) || super.skipRendering(state, adjacentState, direction);
    }

	@Nonnull
	@Override
	public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		float minX = 5F, minY = 5F, minZ = 5F;
		float maxX = 11F, maxY = 11F, maxZ = 11F;
		if (state.getValue(FACING) == Direction.UP) maxY = 16.0F;
		if (state.getValue(FACING) == Direction.DOWN) minY = 0.0F;
		if (state.getValue(FACING) == Direction.SOUTH) maxZ = 16.0F;
		if (state.getValue(FACING) == Direction.NORTH) minZ = 0.0F;
		if (state.getValue(FACING) == Direction.WEST) minX = 0.0F;
		if (state.getValue(FACING) == Direction.EAST) maxX = 16.0F;

		if (isSideConnectable (worldIn, pos, Direction.UP)) maxY = 16.0F;
		if (isSideConnectable (worldIn, pos, Direction.DOWN)) minY = 0.0F;
		if (isSideConnectable (worldIn, pos, Direction.SOUTH)) maxZ = 16.0F;
		if (isSideConnectable (worldIn, pos, Direction.NORTH)) minZ = 0.0F;
		if (isSideConnectable (worldIn, pos, Direction.WEST)) minX = 0.0F;
		if (isSideConnectable (worldIn, pos, Direction.EAST)) maxX = 16.0F;
		
		VoxelShape voxelshape = Block.box(minX, minY, minZ, maxX, maxY, maxZ);
		return voxelshape;
	}

	@Nonnull
	@Override
	public VoxelShape getInteractionShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		float minX = 5F, minY = 5F, minZ = 5F;
		float maxX = 11F, maxY = 11F, maxZ = 11F;
		if (state.getValue(FACING) == Direction.UP) maxY = 16.0F;
		if (state.getValue(FACING) == Direction.DOWN) minY = 0.0F;
		if (state.getValue(FACING) == Direction.SOUTH) maxZ = 16.0F;
		if (state.getValue(FACING) == Direction.NORTH) minZ = 0.0F;
		if (state.getValue(FACING) == Direction.WEST) minX = 0.0F;
		if (state.getValue(FACING) == Direction.EAST) maxX = 16.0F;

		if (isSideConnectable (worldIn, pos, Direction.UP)) maxY = 16.0F;
		if (isSideConnectable (worldIn, pos, Direction.DOWN)) minY = 0.0F;
		if (isSideConnectable (worldIn, pos, Direction.SOUTH)) maxZ = 16.0F;
		if (isSideConnectable (worldIn, pos, Direction.NORTH)) minZ = 0.0F;
		if (isSideConnectable (worldIn, pos, Direction.WEST)) minX = 0.0F;
		if (isSideConnectable (worldIn, pos, Direction.EAST)) maxX = 16.0F;
		VoxelShape voxelshape = Block.box(minX, minY, minZ, maxX, maxY, maxZ);
		return voxelshape;
	}

//	@Override
//	public BlockState getStateForPlacement(BlockPlaceContext context) {
//		Direction direction = context.getClickedFace().getOpposite();
//		return this.defaultBlockState().setValue(FACING, direction);
//	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, CONNECTED_DOWN, CONNECTED_UP, CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_WEST, CONNECTED_EAST);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockGetter level = context.getLevel();
		Direction direction = context.getClickedFace().getOpposite();
		BlockPos position = context.getClickedPos();
		BlockState state = defaultBlockState();
		if (direction == Direction.UP)
			return state.setValue(FACING, direction).setValue(CONNECTED_DOWN, this.isSideConnectable(level, position, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, position, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, position, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, position, Direction.SOUTH)).setValue(CONNECTED_UP, false).setValue(CONNECTED_WEST, this.isSideConnectable(level, position, Direction.WEST));
		if (direction == Direction.DOWN)
			return state.setValue(FACING, direction).setValue(CONNECTED_DOWN, false).setValue(CONNECTED_EAST, this.isSideConnectable(level, position, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, position, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, position, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, position, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, position, Direction.WEST));
		if (direction == Direction.SOUTH)
			return state.setValue(FACING, direction).setValue(CONNECTED_DOWN, this.isSideConnectable(level, position, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, position, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, position, Direction.NORTH)).setValue(CONNECTED_SOUTH, false).setValue(CONNECTED_UP, this.isSideConnectable(level, position, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, position, Direction.WEST));
		if (direction == Direction.NORTH)
			return state.setValue(FACING, direction).setValue(CONNECTED_DOWN, this.isSideConnectable(level, position, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, position, Direction.EAST)).setValue(CONNECTED_NORTH, false).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, position, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, position, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, position, Direction.WEST));
		if (direction == Direction.WEST)
			return state.setValue(FACING, direction).setValue(CONNECTED_DOWN, this.isSideConnectable(level, position, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, position, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, position, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, position, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, position, Direction.UP)).setValue(CONNECTED_WEST, false);
		if (direction == Direction.EAST)
			return state.setValue(CONNECTED_DOWN, this.isSideConnectable(level, position, Direction.DOWN)).setValue(CONNECTED_EAST, false).setValue(CONNECTED_NORTH, this.isSideConnectable(level, position, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, position, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, position, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, position, Direction.WEST));

        return state.setValue(FACING, direction).setValue(CONNECTED_DOWN, this.isSideConnectable(level, position, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, position, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, position, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, position, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, position, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, position, Direction.WEST));
    }
	
    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
    	if (facing == Direction.UP)
			return state.setValue(FACING, facing).setValue(CONNECTED_DOWN, this.isSideConnectable(level, currentPos, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, currentPos, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, currentPos, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, currentPos, Direction.SOUTH)).setValue(CONNECTED_UP, false).setValue(CONNECTED_WEST, this.isSideConnectable(level, currentPos, Direction.WEST));
		if (facing == Direction.DOWN)
			return state.setValue(FACING, facing).setValue(CONNECTED_DOWN, false).setValue(CONNECTED_EAST, this.isSideConnectable(level, currentPos, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, currentPos, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, currentPos, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, currentPos, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, currentPos, Direction.WEST));
		if (facing == Direction.SOUTH)
			return state.setValue(FACING, facing).setValue(CONNECTED_DOWN, this.isSideConnectable(level, currentPos, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, currentPos, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, currentPos, Direction.NORTH)).setValue(CONNECTED_SOUTH, false).setValue(CONNECTED_UP, this.isSideConnectable(level, currentPos, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, currentPos, Direction.WEST));
		if (facing == Direction.NORTH)
			return state.setValue(FACING, facing).setValue(CONNECTED_DOWN, this.isSideConnectable(level, currentPos, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, currentPos, Direction.EAST)).setValue(CONNECTED_NORTH, false).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, currentPos, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, currentPos, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, currentPos, Direction.WEST));
		if (facing == Direction.WEST)
			return state.setValue(FACING, facing).setValue(CONNECTED_DOWN, this.isSideConnectable(level, currentPos, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, currentPos, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, currentPos, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, currentPos, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, currentPos, Direction.UP)).setValue(CONNECTED_WEST, false);
		if (facing == Direction.EAST)
			return state.setValue(CONNECTED_DOWN, this.isSideConnectable(level, currentPos, Direction.DOWN)).setValue(CONNECTED_EAST, false).setValue(CONNECTED_NORTH, this.isSideConnectable(level, currentPos, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, currentPos, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, currentPos, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, currentPos, Direction.WEST));

        return state.setValue(FACING, facing).setValue(CONNECTED_DOWN, this.isSideConnectable(level, currentPos, Direction.DOWN)).setValue(CONNECTED_EAST, this.isSideConnectable(level, currentPos, Direction.EAST)).setValue(CONNECTED_NORTH, this.isSideConnectable(level, currentPos, Direction.NORTH)).setValue(CONNECTED_SOUTH, this.isSideConnectable(level, currentPos, Direction.SOUTH)).setValue(CONNECTED_UP, this.isSideConnectable(level, currentPos, Direction.UP)).setValue(CONNECTED_WEST, this.isSideConnectable(level, currentPos, Direction.WEST));
    }

    private boolean isSideConnectable (BlockGetter world, BlockPos pos, Direction side) {
    	final BlockState stateConnection = world.getBlockState(pos.relative(side));
        return world.getBlockState(pos.relative(side)).getBlock() != Blocks.AIR;// stateConnection != null;? false : getFluidHandler(world, pos.relative(side, 1), side) != null;
    }

    @Nonnull
    public static Optional<IFluidHandler> getFluidHandler(@Nonnull Level level, @Nonnull BlockPos pos, @Nullable Direction side) {
    	BlockEntity blockEntity = level.getBlockEntity(pos);
    	if(blockEntity!= null)
    		Optional.ofNullable(level.getCapability(Capabilities.FluidHandler.BLOCK, pos, level.getBlockState(pos), blockEntity, side));
    	return Optional.empty();
    }
    
    @Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new BambooPipeBlockEntity(pos, state);
	}
/*
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, Direction side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			ItemStack stack = player.getHeldItem(hand);
			if (!stack.isEmpty() && stack.getItem() == ModItems.MATERIALS && stack.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.BAMBOO_PIPE_WRENCH.ordinal()) {
				if (!player.isSneaking()) {
					state = state.cycleProperty(FACING);
					state.cycleProperty(FACING);
					world.setBlockState(pos, state, 3);
					return true;
				}
				else {
					breakBlock(world, pos, state);
					dropBlockAsItem(world, pos, state, 0);
					world.setBlockToAir(pos);
					return true;
				}
			}
			return false;
		}
	}
	*/
}
