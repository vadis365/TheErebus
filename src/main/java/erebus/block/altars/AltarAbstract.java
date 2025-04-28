package erebus.block.altars;

import javax.annotation.Nonnull;

import erebus.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AltarAbstract extends HorizontalDirectionalBlock implements EntityBlock {
	public static final VoxelShape ALTAR_AABB = Block.box(0D, 0D, 0D, 16D, 15D, 16D);

	public AltarAbstract(Properties properties) {
		super(properties);
		//super(Material.ROCK);
	//	setHardness(20.0F);
	//	setCreativeTab(ModTabs.BLOCKS);
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return ALTAR_AABB;
	}

	@Nonnull
	@Override
	public VoxelShape getInteractionShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		return Shapes.block();
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public void onRemove(BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
		level.levelEvent(2001, pos, Block.getId(ModBlocks.TEMPLE_BRICK.get().defaultBlockState()));
		super.onRemove(state, level, pos, newState, isMoving);
	}

	@Override
	 public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getHorizontalDirection().getOpposite();
		return this.defaultBlockState().setValue(FACING, direction);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

}