package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.PetrifiedChestBlockEntity;
import erebus.inventory.server.PetrifiedChestMenu;
import erebus.registries.blocks.ModBlockEntities;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class PetrifiedChestBlock extends AbstractChestBlock<PetrifiedChestBlockEntity> implements SimpleWaterloggedBlock {
    public static final MapCodec<PetrifiedChestBlock> CODEC = simpleCodec(PetrifiedChestBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final int EVENT_SET_OPEN_COUNT = 1;
    protected static final int AABB_OFFSET = 1;
    protected static final int AABB_HEIGHT = 14;
    protected static final VoxelShape NORTH_AABB = Block.box(1.0, 0.0, 0.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 16.0);
    protected static final VoxelShape WEST_AABB = Block.box(0.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape EAST_AABB = Block.box(1.0, 0.0, 1.0, 16.0, 14.0, 15.0);
    protected static final VoxelShape AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    private static final Component CONTAINER_TITLE = Component.translatable("erebus.container.petrified_wood_chest");

    private static final DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<Container>> CHEST_COMBINER = new DoubleBlockCombiner.Combiner<>() {

        @Override
        public @NotNull Optional<Container> acceptDouble(@NotNull ChestBlockEntity first, @NotNull ChestBlockEntity second) {
            return Optional.of(new CompoundContainer(first, second));
        }

        @Override
        public @NotNull Optional<Container> acceptSingle(@NotNull ChestBlockEntity single) {
            return Optional.of(single);
        }

        @Override
        public @NotNull Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };

    private static final DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {

        @Override
        public @NotNull Optional<MenuProvider> acceptDouble(@NotNull ChestBlockEntity first, @NotNull ChestBlockEntity second) {
            Container container = new CompoundContainer(first, second);
            return Optional.of(new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                    if (first.hasCustomName()) {
                        assert first.getCustomName() != null;
                        return first.getCustomName();
                    } else {
                        if (second.hasCustomName()) {
                            assert second.getCustomName() != null;
                            return second.getCustomName();
                        } else {
                            return CONTAINER_TITLE;
                        }
                    }
                }

                @Override
                public @Nullable AbstractContainerMenu createMenu(int id, @NotNull Inventory inv, @NotNull Player player) {
                    if(first.canOpen(player) && second.canOpen(player)) {
                        first.unpackLootTable(player);
                        second.unpackLootTable(player);
                        return PetrifiedChestMenu.eightRows(id, inv, container);
                    } else {
                        return null;
                    }
                }
            });
        }

        @Override
        public @NotNull Optional<MenuProvider> acceptSingle(@NotNull ChestBlockEntity single) {
            return Optional.of(single);
        }

        @Override
        public @NotNull Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };

    @Override
    public @NotNull MapCodec<? extends AbstractChestBlock<PetrifiedChestBlockEntity>> codec() {
        return CODEC;
    }

    public PetrifiedChestBlock(Properties properties) {
        super(properties, ModBlockEntities.PETRIFIED_CHEST::get);
        registerDefaultState(
                defaultBlockState()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(TYPE, ChestType.SINGLE)
                        .setValue(WATERLOGGED, false)
        );
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
        ChestType type = state.getValue(TYPE);
        if(type == ChestType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        } else {
            return type == ChestType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
        }
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if(neighborState.is(this) && direction.getAxis().isHorizontal()) {
            ChestType type = neighborState.getValue(TYPE);

            if(state.getValue(TYPE) == ChestType.SINGLE) {
                if(type != ChestType.SINGLE) {
                    if(state.getValue(FACING) == neighborState.getValue(FACING)) {
                        if(getConnectedDirection(neighborState) == direction.getOpposite()) {
                            return state.setValue(TYPE, type.getOpposite());
                        }
                    }
                }
            } else if(getConnectedDirection(state) == direction) {
                return state.setValue(TYPE, ChestType.SINGLE);
            }
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if(state.getValue(TYPE) == ChestType.SINGLE) {
            return AABB;
        } else {
            return switch (getConnectedDirection(state)) {
                case SOUTH -> SOUTH_AABB;
                case WEST -> WEST_AABB;
                case EAST -> EAST_AABB;
                default -> NORTH_AABB;
            };
        }
    }

    public static Direction getConnectedDirection(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        ChestType type = ChestType.SINGLE;
        Direction direction = context.getHorizontalDirection().getOpposite();
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        boolean isSecondaryUseActive = context.isSecondaryUseActive();
        Direction clickedFace = context.getClickedFace();

        if(clickedFace.getAxis().isHorizontal() && isSecondaryUseActive) {
            Direction candidate = candidatePartnerFacing(context, clickedFace.getOpposite());
            if(candidate != null && candidate.getAxis() != clickedFace.getAxis()) {
                direction = candidate;
                type = direction.getCounterClockWise() == clickedFace.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
            }
        }

        if(type == ChestType.SINGLE && !isSecondaryUseActive) {
            if (direction == this.candidatePartnerFacing(context, direction.getClockWise())) {
                type = ChestType.LEFT;
            } else if (direction == this.candidatePartnerFacing(context, direction.getCounterClockWise())) {
                type = ChestType.RIGHT;
            }
        }

        return defaultBlockState()
                .setValue(FACING, direction)
                .setValue(TYPE, type)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    protected @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    private Direction candidatePartnerFacing(BlockPlaceContext context, Direction direction) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return state.is(this) && state.getValue(TYPE) == ChestType.SINGLE ? state.getValue(FACING) : null;
    }

    @Override
    protected void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean movedByPiston) {
        Containers.dropContentsOnDestroy(state, newState, level, pos);
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if(level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider provider = getMenuProvider(state, level, pos);
            if(provider != null) {
                player.openMenu(provider);
                player.awardStat(getOpenChestStat());
                PiglinAi.angerNearbyPiglins(player, true);
            }
            return InteractionResult.CONSUME;
        }
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    @NotNull
    public BlockEntityType<? extends ChestBlockEntity> blockEntityType() {
        return ModBlockEntities.PETRIFIED_CHEST.get();
    }

    public static Container getContainer(PetrifiedChestBlock chest, BlockState state, Level level, BlockPos pos, boolean override) {
        return chest.combine(state, level, pos, override).apply(CHEST_COMBINER).orElse(null);
    }

    @Override
    public DoubleBlockCombiner.@NotNull NeighborCombineResult<? extends ChestBlockEntity> combine(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, boolean override) {
        BiPredicate<LevelAccessor, BlockPos> biPredicate;
        if(override) {
            biPredicate = (accessor, blockPos) -> false;
        } else {
            biPredicate = PetrifiedChestBlock::isChestBlockedAt;
        }

        return DoubleBlockCombiner.combineWithNeigbour(blockEntityType.get(), PetrifiedChestBlock::getBlockType, PetrifiedChestBlock::getConnectedDirection, FACING, state, level, pos, biPredicate);
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return combine(state, level, pos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    public static DoubleBlockCombiner.Combiner<PetrifiedChestBlockEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lid) {
        return new DoubleBlockCombiner.Combiner<>() {
            public @NotNull Float2FloatFunction acceptDouble(@NotNull PetrifiedChestBlockEntity left, @NotNull PetrifiedChestBlockEntity right) {
                return angle -> Math.max(left.getOpenNess(angle), right.getOpenNess(angle));
            }

            public @NotNull Float2FloatFunction acceptSingle(@NotNull PetrifiedChestBlockEntity chest) {
                return chest::getOpenNess;
            }

            public @NotNull Float2FloatFunction acceptNone() {
                return lid::getOpenNess;
            }
        };
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new PetrifiedChestBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return level.isClientSide ? createTickerHelper(type, blockEntityType(), PetrifiedChestBlockEntity::lidAnimateTick) : null;
    }

    public static boolean isChestBlockedAt(LevelAccessor level, BlockPos pos) {
        return isBlockedChestByBlock(level, pos) || isCatSittingOnChest(level, pos);
    }

    private static boolean isBlockedChestByBlock(BlockGetter level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return level.getBlockState(blockpos).isRedstoneConductor(level, blockpos);
    }

    private static boolean isCatSittingOnChest(LevelAccessor level, BlockPos pos) {
        List<Cat> list = level.getEntitiesOfClass(
                Cat.class,
                new AABB(
                        pos.getX(),
                        pos.getY() + 1,
                        pos.getZ(),
                        pos.getX() + 1,
                        pos.getY() + 2,
                        pos.getZ() + 1
                )
        );

        if (!list.isEmpty()) {
            for (Cat cat : list) {
                if (cat.isInSittingPose()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, state, level, pos, false));
    }

    @Override
    protected @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        BlockState rotated = state.rotate(mirror.getRotation(state.getValue(FACING)));
        return mirror == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    protected boolean isPathfindable(@NotNull BlockState state, @NotNull PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected void tick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof PetrifiedChestBlockEntity chest) {
            chest.recheckOpen();
        }
    }
}
