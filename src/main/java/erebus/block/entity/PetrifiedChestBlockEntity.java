package erebus.block.entity;

import erebus.inventory.server.PetrifiedChestMenu;
import erebus.network.server.ErebusChestOpennessPayload;
import erebus.registries.blocks.ModBlockEntities;
import erebus.utils.ErebusOpenersCounter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class PetrifiedChestBlockEntity extends ChestBlockEntity {

    @Nullable
    private BlockPos doubleMainPos;
    private final ChestLidController lidController = new ChestLidController();
    private NonNullList<ItemStack> items;

    private final ErebusOpenersCounter openersCounter = new ErebusOpenersCounter() {
        @Override
        protected void onOpen(@NotNull Level level, @NotNull BlockPos pos, BlockState state) {
            if(state.getValue(ChestBlock.TYPE) != ChestType.LEFT) {
                playSound(level, pos, state, SoundEvents.CHEST_OPEN);
            }
        }

        @Override
        protected void onClose(@NotNull Level level, @NotNull BlockPos pos, BlockState state) {
            if(state.getValue(ChestBlock.TYPE) != ChestType.LEFT) {
                playSound(level, pos, state, SoundEvents.CHEST_CLOSE);
            }
        }

        @Override
        protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int count, int openCount) {
            lidController.shouldBeOpen(openCount > 0);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            return player.containerMenu instanceof PetrifiedChestMenu;
        }
    };

    public PetrifiedChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PETRIFIED_CHEST.get(), pos, state);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
    }

    public ChestLidController getLidController() {
        return lidController;
    }

    public ErebusOpenersCounter getOpenersCounter() {
        return openersCounter;
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items, registries);
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items, registries);
        }
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    public static void lidAnimateTick(PetrifiedChestBlockEntity entity) {
        entity.lidController.tickLid();
    }

    @Override
    public void startOpen(@NotNull Player player) {
        if(level == null || level.isClientSide() || remove || player.isSpectator()) {
            return;
        }

        getOpenersCounter().incrementOpeners(player, level, getBlockPos(), getBlockState());
        sendOpenness();
    }

    @Override
    public void stopOpen(@NotNull Player player) {
        if(level == null || level.isClientSide() || remove || player.isSpectator()) {
            return;
        }

        getOpenersCounter().decrementOpeners(player, level, getBlockPos(), getBlockState());
        sendOpenness();
    }

    @Override
    public void recheckOpen() {
        if(!remove && level != null) {
            int countBeforeCheck = getOpenersCounter().getOpenerCount();
            getOpenersCounter().recheckOpeners(level, getBlockPos(), getBlockState());
            int countAfterCheck = getOpenersCounter().getOpenerCount();
            if (countBeforeCheck != countAfterCheck && (countBeforeCheck == 0 || countAfterCheck == 0)) {
                sendOpenness();
            }
        }
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return lidController.getOpenness(partialTicks);
    }

    private void sendOpenness() {
        if(level instanceof ServerLevel server) {
            ChunkPos chunkPos = level.getChunkAt(getBlockPos()).getPos();
            PacketDistributor.sendToPlayersTrackingChunk(server, chunkPos, new ErebusChestOpennessPayload(getBlockPos(), getOpenersCounter().getOpenerCount() > 0));
        }
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if(id == 1) {
            this.lidController.shouldBeOpen(type > 0);
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.petrified_chest");
    }

    @Override
    public int getContainerSize() {
        return 36;
    }

    @Override
    @NotNull
    protected AbstractContainerMenu createMenu(int id, @NotNull Inventory player) {
        return PetrifiedChestMenu.fourRows(id, player, this);
    }

    static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent sound) {
        ChestType chestType = state.getValue(ChestBlock.TYPE);
        if (chestType != ChestType.LEFT) {
            double xPos = (double) pos.getX() + 0.5;
            double yPos = (double) pos.getY() + 0.5;
            double zPos = (double) pos.getZ() + 0.5;
            if (chestType == ChestType.RIGHT) {
                Direction direction = ChestBlock.getConnectedDirection(state);
                xPos += (double) direction.getStepX() * 0.5;
                zPos += (double) direction.getStepZ() * 0.5;
            }

            level.playSound(null, xPos, yPos, zPos, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    public void setShouldBeOpen(boolean shouldBeOpen) {
        lidController.shouldBeOpen(shouldBeOpen);
        if(level != null) {
            applyEffectToPartner(level, getBlockPos(), (entity, pos) -> entity.lidController.shouldBeOpen(shouldBeOpen));
        }
    }

    public boolean isMainChest() {
        return doubleMainPos == null;
    }

    private void applyEffectToPartner(Level level, BlockPos pos, BiConsumer<PetrifiedChestBlockEntity, BlockPos> execute) {
        ChestType chestType = getBlockState().getValue(ChestBlock.TYPE);
        if (chestType == ChestType.SINGLE) {
            return;
        }
        Direction facing = getBlockState().getValue(ChestBlock.FACING);
        BlockPos neighborPos = isMainChest() ? pos.relative(facing.getCounterClockWise()) : pos.relative(facing.getClockWise());
        level.getBlockEntity(neighborPos, ModBlockEntities.PETRIFIED_CHEST.get())
                .ifPresent(chestBlockEntity -> execute.accept(chestBlockEntity, neighborPos));
    }
}
