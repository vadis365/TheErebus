package erebus.block.entity;

import erebus.block.PetrifiedChestBlock;
import erebus.inventory.server.PetrifiedChestMenu;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

public class PetrifiedChestBlockEntity extends ChestBlockEntity {

    private static final int EVENT_SET_OPEN_COUNT = 1;
    private NonNullList<ItemStack> items = NonNullList.withSize(36, ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            playSound(level, pos, state, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            playSound(level, pos, state, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int count, int openCount) {
            signalOpenCount(level, pos, state, count, openCount);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if(!(player.containerMenu instanceof PetrifiedChestMenu)) {
                return false;
            } else {
                Container container = ((PetrifiedChestMenu) player.containerMenu).getContainer();
                return container instanceof PetrifiedChestBlockEntity
                        || container instanceof CompoundContainer && ((CompoundContainer)container).contains(PetrifiedChestBlockEntity.this);
            }
        }
    };
    private final ChestLidController lidController = new ChestLidController();

    public PetrifiedChestBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.PETRIFIED_CHEST.get(), pos, blockState);
    }

    private static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent sound) {
        ChestType type = state.getValue(ChestBlock.TYPE);
        if(type != ChestType.LEFT) {
            double x = pos.getX() + 0.5D;
            double y = pos.getY() + 0.5D;
            double z = pos.getZ() + 0.5D;

            if(type == ChestType.RIGHT) {
                Direction direction = PetrifiedChestBlock.getConnectedDirection(state);
                x += direction.getStepX() * 0.5D;
                z += direction.getStepZ() * 0.5D;
            }

            level.playSound(null, x, y, z, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    public int getContainerSize() {
        return 36;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("erebus.container.petrified_wood_chest");
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if(!tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, items, registries);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if(!trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, items, registries);
        }
    }

    public static void lidAnimateTick(PetrifiedChestBlockEntity block) {
        block.lidController.tickLid();
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return PetrifiedChestMenu.fourRows(containerId, inventory);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if(id == 1) {
            lidController.shouldBeOpen(type > 0);
            return true;
        }

        return super.triggerEvent(id, type);
    }

    @Override
    public void startOpen(Player player) {
        if(!remove && !player.isSpectator()) {
            openersCounter.incrementOpeners(player, getLevel(), getBlockPos(), getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if(!remove && !player.isSpectator()) {
            openersCounter.decrementOpeners(player, getLevel(), getBlockPos(), getBlockState());
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return lidController.getOpenness(partialTicks);
    }

    public static int getOpenCount(BlockGetter level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if(state.hasBlockEntity()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof PetrifiedChestBlockEntity chest) {
                return chest.openersCounter.getOpenerCount();
            }
        }

        return 0;
    }

    public static void swapContents(PetrifiedChestBlockEntity from, PetrifiedChestBlockEntity to) {
        NonNullList<ItemStack> fromItems = from.items;
        from.setItems(to.items);
        to.setItems(fromItems);
    }

    @Override
    public void setBlockState(BlockState state) {
        var oldState = getBlockState();
        super.setBlockState(state);
        if(oldState.getValue(ChestBlock.FACING) != state.getValue(ChestBlock.FACING) || oldState.getValue(ChestBlock.TYPE) != state.getValue(ChestBlock.TYPE)) {
            invalidateCapabilities();
        }
    }

    public void recheckOpen() {
        if(!remove) {
            openersCounter.recheckOpeners(getLevel(), getBlockPos(), getBlockState());
        }
    }

    public void signalOpenCount(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
        Block block = state.getBlock();
        level.blockEvent(pos, block, 1, eventParam);
    }
}
