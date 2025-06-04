package erebus.block.plants;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.IShearable;

public abstract class WalPlantsAbstract extends DirectionalBlock implements IShearable {
	protected static final VoxelShape UP_AABB = Block.box(0D, 0D, 0D, 16D, 3D, 16D);
	protected static final VoxelShape DOWN_AABB = Block.box(0D, 13D, 0D, 16D, 16D, 16D);
	protected static final VoxelShape WEST_AABB = Block.box(13D, 0D, 0D, 16D, 1D, 16D);
	protected static final VoxelShape EAST_AABB = Block.box(0D, 0D, 0D, 3D, 16D, 16D);
	protected static final VoxelShape SOUTH_AABB = Block.box(0D, 0D, 0D, 16D, 16D, 3D);
	protected static final VoxelShape NORTH_AABB = Block.box(0D, 0D, 13D, 16D, 16D, 16D);
	public int tickRate = 10; // just a default

	protected WalPlantsAbstract(Properties properties) {
		super(properties);
	}

	//TODO override this, return an int, and make it simple.
	public int getScheduledTickRate() {
		return tickRate;
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return switch (state.getValue(FACING)) {
		case EAST -> EAST_AABB;
		case WEST -> WEST_AABB;
		case SOUTH -> SOUTH_AABB;
		case NORTH -> NORTH_AABB;
		case UP -> UP_AABB;
		case DOWN -> DOWN_AABB;
		};
	}

	@Nonnull
	@Override
	public VoxelShape getInteractionShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		return switch (state.getValue(FACING)) {
		case EAST -> EAST_AABB;
		case WEST -> WEST_AABB;
		case SOUTH -> SOUTH_AABB;
		case NORTH -> NORTH_AABB;
		case UP -> UP_AABB;
		case DOWN -> DOWN_AABB;
		};
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Override
	 public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getClickedFace();
		return this.defaultBlockState().setValue(FACING, direction);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!state.is(oldState.getBlock())) {
            if (!level.isClientSide() && !level.getBlockTicks().hasScheduledTick(pos, this)) {
            	level.scheduleTick(pos, this, getScheduledTickRate());
            }
        }
    }

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).isFaceSturdy(level, pos.relative(state.getValue(FACING).getOpposite()), state.getValue(FACING)) && isValidBlock(level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())));
	}

	// TODO override this and remove umberstone for cultivated moss and mould or implement a tag.
	private boolean isValidBlock(BlockState state) {
		return state.is(WoodBlocks.LOG_ROTTEN.get()) || state.is(UmberstoneBlocks.UMBERSTONE.get());
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		return state.canSurvive(level, pos) ? super.updateShape(state, direction, neighborState, level, pos, neighborPos) : Blocks.AIR.defaultBlockState();
	}

	@Override
    public boolean isShearable(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		Direction type = level.getBlockState(pos).getValue(FACING);
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		switch (type) {
		case EAST:
		case WEST:
		case SOUTH:
		case NORTH:
		case UP:
		case DOWN:
			ret.add(new ItemStack(this, 1));
			break;
		}
		return ret;
	}

	@Override
	 protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

		if (random.nextInt(2) == 0) {
			byte radius = 4;
			int distance = 5;
			for (int xx = pos.getX() - radius; xx <= pos.getX() + radius; ++xx)
				for (int zz = pos.getZ() - radius; zz <= pos.getZ() + radius; ++zz)
					for (int yy = pos.getY() - radius; yy <= pos.getY() + radius; ++yy)
						if (level.isLoaded(checkPos.set(xx, yy, zz)) && level.getBlockState(checkPos.set(xx, yy, zz)).getBlock() == this) {
							--distance;
							if (distance <= 0)
								return;
						}
			for (int attempt = 0; attempt < 6; attempt++) {
				int xx = pos.getX() + random.nextInt(3) - 1;
				int yy = pos.getY() + random.nextInt(3) - 1;
				int zz = pos.getZ() + random.nextInt(3) - 1;
				int offsetDir = 0;
				if (xx != pos.getX())
					offsetDir++;
				if (yy != pos.getY())
					offsetDir++;
				if (zz != pos.getZ())
					offsetDir++;
				if (offsetDir > 1)
					continue;
				BlockPos offsetPos = new BlockPos(xx, yy, zz);
				if (level.isEmptyBlock(offsetPos)) {
					Direction facing = Direction.getRandom(random);
					Direction.Axis axis = facing.getAxis();
					Direction oppositeFacing = facing.getOpposite();
					boolean isInvalid = false;
					if (axis.isHorizontal() && !level.getBlockState(offsetPos.relative(oppositeFacing)).isFaceSturdy(level, offsetPos.relative(oppositeFacing), facing)) {
						isInvalid = true;
					} else if (axis.isVertical() && !state.canSurvive(level, offsetPos.relative(oppositeFacing))) {
						isInvalid = true;
					}
					if (!isInvalid) {
						level.setBlockAndUpdate(offsetPos, this.defaultBlockState().setValue(FACING, facing));
						break;
					}
				}
			}

		} else if (random.nextInt(25) == 0) {
			level.removeBlock(pos, false);
		}
		level.scheduleTick(pos, this, getScheduledTickRate());
	}
}
