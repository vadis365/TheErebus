package erebus.block.plants;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.IShearable;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class WallPlantsAbstract extends DirectionalBlock implements IShearable {
	protected static final VoxelShape UP_AABB = Block.box(0D, 0D, 0D, 16D, 3D, 16D);
	protected static final VoxelShape DOWN_AABB = Block.box(0D, 13D, 0D, 16D, 16D, 16D);
	protected static final VoxelShape WEST_AABB = Block.box(13D, 0D, 0D, 16D, 16D, 16D);
	protected static final VoxelShape EAST_AABB = Block.box(0D, 0D, 0D, 3D, 16D, 16D);
	protected static final VoxelShape SOUTH_AABB = Block.box(0D, 0D, 0D, 16D, 16D, 3D);
	protected static final VoxelShape NORTH_AABB = Block.box(0D, 0D, 13D, 16D, 16D, 16D);
	public int tickRate = 100; // just a default

	protected WallPlantsAbstract(Properties properties) {
		super(properties);
	}

	//TODO override this, return an int, and make it simple.
	public int getScheduledTickRate() {
		return tickRate;
	}

	public boolean shouldScheduleTick() {
		return true;
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return getVoxelShape(state);
	}

	@Nonnull
	@Override
	public VoxelShape getInteractionShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		return getVoxelShape(state);
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
    protected void onPlace(BlockState state, @NonNull Level level, @NonNull BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!state.is(oldState.getBlock())) {
            if (!level.isClientSide() && shouldScheduleTick() && !level.getBlockTicks().hasScheduledTick(pos, this)) {
            	level.scheduleTick(pos, this, getScheduledTickRate());
            }
        }
    }

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).isFaceSturdy(level, pos.relative(state.getValue(FACING).getOpposite()), state.getValue(FACING)) && isValidBlock(level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())));
	}

	// TODO override this and remove umberstone for cultivated moss and mould or implement a tag.
	public boolean isValidBlock(BlockState state) {
		return state.is(ModBlocks.LOG_ROTTEN.get()) || state.is(ModBlocks.UMBERSTONE.get());
	}

	@Override
	protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
		if (shouldScheduleTick())
			if(!((ServerLevel)level).getBlockTicks().hasScheduledTick(pos, this))
				((ServerLevel)level).scheduleTick(pos, this, getScheduledTickRate());
		return canSurvive(state, level, pos) ? super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random) : Blocks.AIR.defaultBlockState();
	}

	@Override
    public boolean isShearable(@Nullable Player player, @NonNull ItemStack item, @NonNull Level level, @NonNull BlockPos pos) {
		return true;
	}

	@Override
	public @NonNull List<ItemStack> onSheared(@Nullable Player player, @NonNull ItemStack item, Level level, @NonNull BlockPos pos) {
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
	 protected void randomTick(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull RandomSource random) {
		this.tick(state, level, pos, random);
	}

	@Override
	protected void tick(@NonNull BlockState state, @NonNull ServerLevel level, @NonNull BlockPos pos, @NonNull RandomSource random) {
		if (shouldScheduleTick())
			if(!level.getBlockTicks().hasScheduledTick(pos, this))
				level.scheduleTick(pos, this, getScheduledTickRate());
		
		if (random.nextInt(25) == 0 && !shouldScheduleTick())
			level.removeBlock(pos, false);

		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
			byte radius = 2;
			int maxNearby = 6;
			for (int xx = pos.getX() - radius; xx <= pos.getX() + radius; ++xx)
				for (int zz = pos.getZ() - radius; zz <= pos.getZ() + radius; ++zz)
					for (int yy = pos.getY() - radius; yy <= pos.getY() + radius; ++yy)
						if (level.isLoaded(checkPos.set(xx, yy, zz)) && level.getBlockState(checkPos.set(xx, yy, zz)).getBlock() == this) {
							--maxNearby;
							if (maxNearby <= 0)
								return;
						}
			
			BlockPos growingOnBlock = pos.relative(state.getValue(FACING).getOpposite());
			Direction randomiseDirection = Direction.getRandom(random);
			BlockPos blockToGrowOnPos = growingOnBlock.relative(randomiseDirection);

			if(level.isEmptyBlock(blockToGrowOnPos)) //allows for floating blocks to grow on all sides
				blockToGrowOnPos = growingOnBlock;

			if (isValidBlock(level.getBlockState(blockToGrowOnPos))) {
				for (int attempt = 0; attempt < 6; attempt++) {
					Direction randomiseSide = Direction.getRandom(random);
					if (level.isEmptyBlock(blockToGrowOnPos.relative(randomiseSide)) && level.getBlockState(blockToGrowOnPos).isFaceSturdy(level, blockToGrowOnPos, randomiseSide))
						level.setBlockAndUpdate(blockToGrowOnPos.relative(randomiseSide), this.defaultBlockState().setValue(FACING, randomiseSide));
				}
			}
	}

	@NonNull
	private VoxelShape getVoxelShape(@Nonnull BlockState state) {
		return switch (state.getValue(FACING)) {
			case EAST -> EAST_AABB;
			case WEST -> WEST_AABB;
			case SOUTH -> SOUTH_AABB;
			case NORTH -> NORTH_AABB;
			case UP -> UP_AABB;
			case DOWN -> DOWN_AABB;
		};
	}
}
