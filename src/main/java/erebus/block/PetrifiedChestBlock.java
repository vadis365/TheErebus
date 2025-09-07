package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.PetrifiedChestBlockEntity;
import erebus.inventory.server.PetrifiedChestMenu;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PetrifiedChestBlock extends ChestBlock {
    public static final MapCodec<PetrifiedChestBlock> CODEC = simpleCodec(PetrifiedChestBlock::new);
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
    
    public PetrifiedChestBlock(Properties properties) {
        super(properties, ModBlockEntities.PETRIFIED_CHEST::get);
    }

    @Override
    public @NotNull MapCodec<? extends ChestBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getAnalogOutputSignal(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, state, level, pos, false));
    }

    public static Container getContainer(PetrifiedChestBlock chest, BlockState state, Level level, BlockPos pos, boolean override) {
        return chest.combine(state, level, pos, override).apply(CHEST_COMBINER).orElse(null);
    }

    @Override
    public @NotNull BlockEntityType<? extends ChestBlockEntity> blockEntityType() {
        return ModBlockEntities.PETRIFIED_CHEST.get();
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new PetrifiedChestBlockEntity(pos, state);
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return combine(state, level, pos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }
}
