package erebus.block.entity;

import erebus.block.bamboo.BambooBridge;
import erebus.block.bamboo.BambooExtender;
import erebus.inventory.server.BambooExtenderMenu;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class BambooExtenderBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {
    private boolean extending;

    public BambooExtenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BAMBOO_EXTENDER.get(), 6, pos, state);
    }

    public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T entity) {
        if (!(entity instanceof BambooExtenderBlockEntity tile)) return;
        Direction direction = state.getValue(BambooExtender.FACING);
        tile.setExtending(state.getValue(BambooExtender.POWERED));
        BlockState extension = direction.getAxis().isVertical() ? ModBlocks.BAMBOO_NERD_POLE.get().defaultBlockState()
                : ModBlocks.BAMBOO_BRIDGE.get().defaultBlockState().setValue(BambooBridge.FACING, direction);
        BlockPos target = pos.relative(direction), last = pos;
        while (available(level, target) && level.getBlockState(target).is(extension.getBlock())) {
            last = target;
            target = target.relative(direction);
        }
        if (tile.extending) {
            if (!available(level, target) || !level.getBlockState(target).canBeReplaced() || level.getBlockEntity(target) != null) return;
            for (int slot = 0; slot < tile.getContainerSize(); slot++) {
                ItemStack stack = tile.getItem(slot);
                if (stack.is(extension.getBlock().asItem())) {
                    if (level.setBlock(target, extension, 3)) {
                        stack.shrink(1);
                        tile.setChanged();
                        var sound = extension.getSoundType();
                        level.playSound(null, target, sound.getBreakSound(), SoundSource.BLOCKS, (sound.getVolume() + 1F) / 2F, sound.getPitch() * 0.8F);
                    }
                    return;
                }
            }
        } else {
            if (last.equals(pos) || !available(level, target) && !level.isOutsideBuildHeight(target)) return;
            ItemStack recovered = new ItemStack(extension.getBlock());
            for (int slot = 0; slot < tile.getContainerSize(); slot++) {
                ItemStack stack = tile.getItem(slot);
                if (stack.isEmpty() || ItemStack.isSameItemSameComponents(stack, recovered) && stack.getCount() < stack.getMaxStackSize()) {
                    if (level.setBlock(last, Blocks.AIR.defaultBlockState(), 3)) {
                        if (stack.isEmpty()) tile.setItem(slot, recovered);
                        else {
                            stack.grow(1);
                            tile.setChanged();
                        }
                        level.levelEvent(null, 2001, last, Block.getId(extension));
                    }
                    return;
                }
            }
        }
    }

    private static boolean available(Level level, BlockPos pos) {
        return !level.isOutsideBuildHeight(pos) && level.getWorldBorder().isWithinBounds(pos) && level.hasChunkAt(pos);
    }

    public void setExtending(boolean value) {
        if (extending != value) {
            extending = value;
            setChanged();
        }
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putBoolean("extending", extending);
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        extending = input.getBooleanOr("extending", false);
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return stack.is(ModBlocks.BAMBOO_NERD_POLE.asItem()) || stack.is(ModBlocks.BAMBOO_BRIDGE.asItem());
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction side) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        return true;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getItems(), slot);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("erebus.container.bamboo_extender");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new BambooExtenderMenu(id, inventory, this);
    }
}
