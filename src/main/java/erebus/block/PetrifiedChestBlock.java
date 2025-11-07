package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.PetrifiedChestBlockEntity;
import erebus.inventory.server.PetrifiedChestMenu;
import erebus.registries.blocks.ModBlockEntities;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;

public class PetrifiedChestBlock extends AbstractChestBlock<PetrifiedChestBlockEntity> {

    public static final MapCodec<PetrifiedChestBlock> CODEC = simpleCodec(PetrifiedChestBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public PetrifiedChestBlock(Properties properties) {
        super(properties, ModBlockEntities.PETRIFIED_CHEST::get);
        registerDefaultState(
                defaultBlockState()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(TYPE, ChestType.SINGLE)
                        .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    private static final DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {

        @Override
        public @NotNull Optional<MenuProvider> acceptDouble(@NotNull ChestBlockEntity first, @NotNull ChestBlockEntity second) {
            final Container container = new CompoundContainer(first, second);
            return Optional.of(new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                    if(first.hasCustomName()) {
                        return first.getDisplayName();
                    } else {
                        if(second.hasCustomName()) {
                            return second.getDisplayName();
                        } else {
                            return Component.translatable("petrified_wood_double_chest");
                        }
                    }
                }

                @Override
                public @Nullable AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInventory, @NotNull Player player) {
                    if(first.canOpen(player) && second.canOpen(player)) {
                        first.unpackLootTable(player);
                        second.unpackLootTable(player);
                        return PetrifiedChestMenu.eightRows(containerId, playerInventory, container);
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

    public static DoubleBlockCombiner.Combiner<PetrifiedChestBlockEntity, Float2FloatFunction> getOpennessCombiner(final LidBlockEntity lid) {
        return new DoubleBlockCombiner.Combiner<>() {
            public @NotNull Float2FloatFunction acceptDouble(@NotNull PetrifiedChestBlockEntity first, @NotNull PetrifiedChestBlockEntity second) {
                return partialTick -> Math.max(first.getOpenNess(partialTick), second.getOpenNess(partialTick));
            }

            public @NotNull Float2FloatFunction acceptSingle(@NotNull PetrifiedChestBlockEntity entity) {
                return entity::getOpenNess;
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
    protected @Nullable MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return this.combine(state, level, pos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? createTickerHelper(blockEntityType, ModBlockEntities.PETRIFIED_CHEST.get(), PetrifiedChestBlockEntity::lidAnimateTick) : null;
    }

    @Override
    protected MapCodec<? extends AbstractChestBlock<PetrifiedChestBlockEntity>> codec() {
        return CODEC;
    }

    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combine(BlockState state, Level level, BlockPos pos, boolean override) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;
        if (override) {
            bipredicate = (p_51578_, p_51579_) -> false;
        } else {
            bipredicate = ChestBlock::isChestBlockedAt;
        }

        return DoubleBlockCombiner.combineWithNeigbour(
                this.blockEntityType.get(), ChestBlock::getBlockType, ChestBlock::getConnectedDirection, FACING, state, level, pos, bipredicate
        );
    }
}