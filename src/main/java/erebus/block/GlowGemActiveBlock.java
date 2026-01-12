package erebus.block;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Locale;

public class GlowGemActiveBlock extends Block {

	public static final EnumProperty<EnumGemDirection> TYPE = EnumProperty.create("type", EnumGemDirection.class);
	public static final VoxelShape DOWN_NORTH_AABB = Block.box(2D, 0D, 3D, 14D, 3D, 13D);
	public static final VoxelShape DOWN_SOUTH_AABB = Block.box(2D, 0D, 3D, 14D, 3D, 13D);
	public static final VoxelShape DOWN_WEST_AABB = Block.box(3D, 0D, 2D, 13D, 3D, 14D);
	public static final VoxelShape DOWN_EAST_AABB = Block.box(3D, 0D, 2D, 13D, 3D, 14D);
	public static final VoxelShape UP_NORTH_AABB = Block.box(2D, 13D, 3D, 14D, 16D, 13D);
	public static final VoxelShape UP_SOUTH_AABB = Block.box(2D, 13D, 3D, 14D, 16D, 13D);
	public static final VoxelShape UP_WEST_AABB = Block.box(3D, 13D, 2D, 13D, 16D, 14D);
	public static final VoxelShape UP_EAST_AABB = Block.box(3D, 13D, 2D, 13D, 16D, 14D);
	public static final VoxelShape NORTH_AABB = Block.box(2D, 3D, 0D, 14D, 13D, 3D);
	public static final VoxelShape SOUTH_AABB = Block.box(2D, 3D, 13D, 14D, 13D, 16D);
	public static final VoxelShape WEST_AABB = Block.box(0D, 3D, 2D, 3D, 13D, 14D);
	public static final VoxelShape EAST_AABB = Block.box(13D, 3D, 2D, 16D, 13D, 14D);

	public GlowGemActiveBlock(Block.Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(TYPE, EnumGemDirection.DOWN_NORTH));
	}

	/*@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
		tooltipComponents.add(Component.translatable("tooltip.erebus.glow_gem").withStyle(ChatFormatting.YELLOW));
	}*/

	@Nonnull
	@Override
	public RenderShape getRenderShape(@NonNull BlockState state) {
		return RenderShape.MODEL;
	}

	@Nonnull
	@Override
	public VoxelShape getInteractionShape(@NonNull BlockState state, @NonNull BlockGetter worldIn, @NonNull BlockPos pos) {
		return getVoxelShape(state);
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter worldIn, @NonNull BlockPos pos, @NonNull CollisionContext context) {
		return getVoxelShape(state);
	}

	@NonNull
	private VoxelShape getVoxelShape(BlockState state) {
		return switch (state.getValue(TYPE)) {
			case SOUTH -> SOUTH_AABB;
			case NORTH -> NORTH_AABB;
			case EAST -> EAST_AABB;
			case WEST -> WEST_AABB;
			case UP_NORTH -> UP_NORTH_AABB;
			case UP_EAST -> UP_EAST_AABB;
			case UP_SOUTH -> UP_SOUTH_AABB;
			case UP_WEST -> UP_WEST_AABB;
			case DOWN_NORTH -> DOWN_NORTH_AABB;
			case DOWN_EAST -> DOWN_EAST_AABB;
			case DOWN_SOUTH -> DOWN_SOUTH_AABB;
			case DOWN_WEST -> DOWN_WEST_AABB;
		};
	}

	@Override
	public void neighborChanged(BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Block block, @Nullable Orientation orientation, boolean movedByPiston) {
		EnumGemDirection newFacing = state.getValue(TYPE);
		boolean flag = false;

		if (newFacing == EnumGemDirection.UP_NORTH || newFacing == EnumGemDirection.UP_EAST || newFacing == EnumGemDirection.UP_SOUTH || newFacing == EnumGemDirection.UP_WEST)
			if (level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN, SupportType.RIGID))
				flag = true;

		if (newFacing == EnumGemDirection.DOWN_NORTH || newFacing == EnumGemDirection.DOWN_EAST || newFacing == EnumGemDirection.DOWN_SOUTH || newFacing == EnumGemDirection.DOWN_WEST)
			if (level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP, SupportType.RIGID))
				flag = true;

		if (newFacing == EnumGemDirection.NORTH && level.getBlockState(pos.relative(Direction.NORTH)).isFaceSturdy(level, pos.relative(Direction.NORTH), Direction.NORTH, SupportType.RIGID))
			flag = true;

		if (newFacing == EnumGemDirection.SOUTH && level.getBlockState(pos.relative(Direction.SOUTH)).isFaceSturdy(level, pos.relative(Direction.SOUTH), Direction.SOUTH, SupportType.RIGID))
			flag = true;

		if (newFacing == EnumGemDirection.WEST && level.getBlockState(pos.relative(Direction.WEST)).isFaceSturdy(level, pos.relative(Direction.WEST), Direction.WEST, SupportType.RIGID))
			flag = true;

		if (newFacing == EnumGemDirection.EAST && level.getBlockState(pos.relative(Direction.EAST)).isFaceSturdy(level, pos.relative(Direction.EAST), Direction.EAST, SupportType.RIGID))
			flag = true;

		if (!flag) {
			popResource(level, pos, new ItemStack(ModBlocks.GLOW_GEM_INACTIVE.asItem(), 1));
			level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
	}

	@Nonnull
	@Override
	public InteractionResult useWithoutItem(@NonNull BlockState state, Level world, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
		if (!world.isClientSide()) {
			BlockState activeState = ModBlocks.GLOW_GEM_INACTIVE.get().defaultBlockState().setValue(GlowGemInactiveBlock.TYPE, state.getValue(TYPE));
			world.setBlock(pos, activeState, 3);
			world.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.6F);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public boolean canSurvive(@NonNull BlockState state, @NonNull LevelReader world, @NonNull BlockPos pos) {
		for (Direction enumfacing : Direction.values())
			if (canSupportCenter(world, pos.relative(enumfacing.getOpposite()), enumfacing))
				return true;
		return false;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction facing = context.getClickedFace();
		Direction direction = Direction.NORTH;
		if (context.getPlayer() != null)
			direction = context.getPlayer().getDirection();

		EnumGemDirection newFacing = EnumGemDirection.DOWN_NORTH;
		if (facing == Direction.UP) {
			newFacing = switch (direction) {
				case SOUTH -> EnumGemDirection.DOWN_SOUTH;
				case EAST -> EnumGemDirection.DOWN_WEST;
				case NORTH -> EnumGemDirection.DOWN_NORTH;
				case WEST -> EnumGemDirection.DOWN_EAST;
				default -> newFacing;
			};
		} else if (facing == Direction.DOWN) {
			newFacing = switch (direction) {
				case SOUTH -> EnumGemDirection.UP_SOUTH;
				case EAST -> EnumGemDirection.UP_WEST;
				case NORTH -> EnumGemDirection.UP_NORTH;
				case WEST -> EnumGemDirection.UP_EAST;
				default -> newFacing;
			};
		} else {
			newFacing = switch (facing) {
				case SOUTH -> EnumGemDirection.NORTH;
				case EAST -> EnumGemDirection.WEST;
				case NORTH -> EnumGemDirection.SOUTH;
				case WEST -> EnumGemDirection.EAST;
				default -> newFacing;
			};
		}

		return defaultBlockState().setValue(TYPE, newFacing);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE);
	}

	public enum EnumGemDirection implements StringRepresentable {
		DOWN_NORTH, DOWN_SOUTH, DOWN_WEST, DOWN_EAST, UP_NORTH, UP_SOUTH, UP_WEST, UP_EAST, NORTH, SOUTH, WEST, EAST;

		@Nonnull
		@Override
		public String getSerializedName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}
