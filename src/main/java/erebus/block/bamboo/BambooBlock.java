package erebus.block.bamboo;

import erebus.registries.blocks.providers.WoodBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TriState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BambooBlock extends Block {

	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 15);

	public BambooBlock(Properties properties) {
		super(properties);
		registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
		return defaultBlockState().setValue(AGE, 0);
	}

	@Override
	protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return Block.box(3D, 0D, 3D, 13D, 16D, 13D);
	}
	/*
	 * @Override public ArrayList<ItemStack> getDrops(IBlockAccess level, BlockPos
	 * pos, IBlockState state, int fortune) { ArrayList<ItemStack> ret = new
	 * ArrayList<ItemStack>(); if (this.getMetaFromState(state) == 0 &&
	 * RANDOM.nextInt(getMetaFromState(state) >= 8 ? 35 : 20) == 0) ret.add(new
	 * ItemStack(Item.getItemFromBlock(EnumWood.BAMBOO.getSapling()))); ret.add(new
	 * ItemStack(ModItems.MATERIALS, 1, EnumErebusMaterialsType.BAMBOO.ordinal()));
	 * return ret; }
	 */

	@Override
	protected boolean isRandomlyTicking(BlockState state) {
		return state.getValue(AGE) < 15;
	}

	@Override
	protected void tick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (!state.canSurvive(level, pos))
			level.destroyBlock(pos, true);
	}

	@Override
	protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (level.isEmptyBlock(pos.above())) {
			int growthHeight;

			for (growthHeight = 1; level.getBlockState(pos.below(growthHeight)).is(this); ++growthHeight);

			if (growthHeight < 8) {
				int stage = state.getValue(AGE);

				if (stage == 14) {
					if (growthHeight == 7) // 4 leafy blocks possible but at least 1 guaranteed
						level.setBlock(pos.above(), state.setValue(AGE, 15), 2);
					else
						level.setBlockAndUpdate(pos.above(), defaultBlockState());

					if (random.nextBoolean() && growthHeight > 4)
						level.setBlock(pos, state.setValue(AGE, 15), 2);
					else
						level.setBlock(pos, state.setValue(AGE, 0), 4);
				} else {
					level.setBlock(pos, state.setValue(AGE, stage + 1), 4);
				}

			}

		}
	}

	@Nonnull
	@Override
	public InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		if (level.isClientSide())
			return InteractionResult.SUCCESS;

		if (stack.is(Items.SHEARS)) {
			if (state.getValue(AGE) == 15) {
				level.setBlock(pos, state.setValue(AGE, 0), 4);
				popResource(level, pos, new ItemStack(WoodBlocks.SAPLING_BAMBOO.asItem()));
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.TRY_WITH_EMPTY_HAND;
	}

	@Override
	protected @NonNull BlockState updateShape(BlockState state, @NonNull LevelReader level, @NonNull ScheduledTickAccess ticks, @NonNull BlockPos pos, @NonNull Direction directionToNeighbour, @NonNull BlockPos neighbourPos, @NonNull BlockState neighbourState, @NonNull RandomSource random) {
		if (!state.canSurvive(level, pos))
			ticks.scheduleTick(pos, this, 1);
		return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
	}

	@Override
	protected boolean canSurvive(@NonNull BlockState state, LevelReader level, BlockPos pos) {
		BlockState stateBelow = level.getBlockState(pos.below());
		if (stateBelow.is(this)) {
			return true;
		} else {
			TriState soilDecision = stateBelow.canSustainPlant(level, pos.below(), Direction.UP, state);
			if (!soilDecision.isDefault())
				return soilDecision.isTrue();
            return stateBelow.is(BlockTags.DIRT) || stateBelow.is(BlockTags.SAND);
		}
    }
}