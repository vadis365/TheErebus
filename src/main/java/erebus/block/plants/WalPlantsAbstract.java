package erebus.block.plants;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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

	protected WalPlantsAbstract(Properties properties) {
		super(properties);
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
}
