package erebus.block.entity;

import erebus.inventory.server.BlenderMenu;
import erebus.registries.blocks.ModBlockEntities;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlenderBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

    public FluidTank[] tanks = new FluidTank[4];
    private static final int MAX_TIME = 432;
    private final int progress = 0;
    private final int prevProgress = 0;

    public BlenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLENDER.get(), 5, pos, state);

        for(int c = 0; c < tanks.length; c++) {
            tanks[c] = new FluidTank(FluidType.BUCKET_VOLUME * 8);
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return null;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("erebus.container.blender");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new BlenderMenu(containerId, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
    }

    public FluidTank[] getTanks() {
        return tanks;
    }

    public float getBlendProgress() {
        return progress / 12F;
    }

    public float getPrevBlendProgress() {
        return prevProgress / 12F;
    }

    public boolean isBlending() {
        return progress > 0;
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        for (FluidTank tank : tanks) {
            tank.writeToNBT(registries, tag);
        }
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        for (FluidTank tank : tanks) {
            tank.readFromNBT(registries, tag);
        }
    }
}
