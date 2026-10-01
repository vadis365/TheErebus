package erebus.block.entity;

import erebus.network.client.OfferingAltarTimerPacket;
import erebus.recipes.altar.MultiStackInput;
import erebus.recipes.altar.OfferingAltarRecipe;
import erebus.registries.ModCustomRecipes;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class OfferingAltarBlockEntity extends BlockEntityInventoryHelper {
    private static final int MAX_TIME = 450;
    public final RecipeManager.CachedCheck<MultiStackInput, OfferingAltarRecipe> quickCheck = RecipeManager.createCheck(ModCustomRecipes.OFFERING_ALTAR_RECIPE.get());
    public int time = 0;
    public int prevTime;
    public int rotation;
    public int prevRotation;
    public boolean isCrafting = false;
    protected ItemStack output;

    public OfferingAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OFFERING_ALTAR.get(), 4, pos, state);
        output = ItemStack.EMPTY;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
        if (blockEntity instanceof OfferingAltarBlockEntity altar) {
            if (level.isClientSide()) {
                altar.prevRotation = altar.rotation;
                altar.prevTime = altar.time;
                altar.rotation += 2;
                if (altar.rotation >= 360) {
                    altar.rotation -= 360;
                    altar.prevRotation -= 360;
                }
                if (altar.isCrafting)
                    altar.time++;
                else
                    altar.time = 0;
            } else {
                MultiStackInput input = new MultiStackInput(altar.getItems().subList(0, 3));
                RecipeHolder<OfferingAltarRecipe> recipe = altar.getItem(3).isEmpty()
                        ? altar.quickCheck.getRecipeFor(input, (ServerLevel) level).orElse(null) : null;
                // Rebuild the derived result after loading, and never replace an uncollected reward.
                altar.output = recipe == null ? ItemStack.EMPTY : recipe.value().assemble(input);
                if (altar.output.isEmpty()) {
                    if (altar.isCrafting || altar.time != 0) {
                        altar.time = 0;
                        altar.isCrafting = false;
                        altar.updateBlockWhenChanged();
                    }
                } else {
                    boolean starting = !altar.isCrafting;
                    altar.isCrafting = true;
                    altar.time = Math.min(MAX_TIME, altar.time + 1);
                    altar.setChanged();
                    if (starting) altar.updateBlockWhenChanged();
                    PacketDistributor.sendToPlayersTrackingChunk((ServerLevel) level,
                            net.minecraft.world.level.ChunkPos.containing(pos),
                            new OfferingAltarTimerPacket(pos.getX(), pos.getY(), pos.getZ(), altar.time, true));
                    if (altar.time == 90 || altar.time == 270 || altar.time == 450)
                        level.levelEvent(2011, pos.above(), 15);
                    if (altar.time >= MAX_TIME) {
                        level.levelEvent(2004, pos.above(), 0);
                        altar.getItems().set(3, altar.output.copy());
                        for (int i = 0; i < 3; i++) {
                            var stack = altar.getItem(i);
                            stack.shrink(1);
                            if (stack.isEmpty()) altar.getItems().set(i, ItemStack.EMPTY);
                        }
                        altar.output = ItemStack.EMPTY;
                        altar.isCrafting = false;
                        altar.time = 0;
                        altar.updateBlockWhenChanged();
                    }
                }
            }
        }
    }

    public ItemStack getItemForRendering(int slot) {
        if (getItems().get(slot).isEmpty())
            return ItemStack.EMPTY;
        else {
            return getItems().get(slot);
        }
    }

    public void popStack() {
        if (!getLevel().isClientSide())
            for (int i = getItems().size() - 1; i >= 0; i--)
                if (!getItems().get(i).isEmpty()) {
                    Block.popResource(getLevel(), getBlockPos().above(), getItems().get(i).copy());
                    getItems().set(i, ItemStack.EMPTY);
                    updateBlockWhenChanged();
                    return;
                }
    }

    public void addStack(ItemStack stack) {
        if (stack.isEmpty() || stack.getCount() <= 0)
            return;
        if (getItems().get(getItems().size() - 1).isEmpty())
            for (int i = 0; i < getItems().size() - 1; i++)
                if (getItems().get(i).isEmpty()) {
                    addStack(i, stack);
                    return;
                }
    }

    private void addStack(int slot, ItemStack stack) {
        if (!getLevel().isClientSide()) {
            getItems().set(slot, stack.copy());
            getItems().get(slot).setCount(1);
            stack.shrink(1);
            updateBlockWhenChanged();
        }
    }

    @Override
    public void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        time = Math.clamp(input.getIntOr("time", 0), 0, MAX_TIME);
        prevTime = time;
        output = ItemStack.EMPTY;
        isCrafting = input.getBooleanOr("isCrafting", false);
    }

    public void loadCustomOnly(CompoundTag nbt) {
        time = Math.clamp(nbt.getIntOr("time", 0), 0, MAX_TIME);
        prevTime = time;
        isCrafting = nbt.getBooleanOr("isCrafting", false);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider provider) {
        return saveWithoutMetadata(provider);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("time", time);
        output.putBoolean("isCrafting", isCrafting);
    }

    public void updateBlockWhenChanged() {
        if (level != null && !level.isClientSide()) {
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        super.setItem(slot, stack);
        updateBlockWhenChanged();
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        var removed = super.removeItem(slot, count);
        if (!removed.isEmpty()) updateBlockWhenChanged();
        return removed;
    }

    @Override
    public void clearContent() {
        super.clearContent();
        updateBlockWhenChanged();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean canPlaceItem(int slot, @NonNull ItemStack stack) {
        return slot >= 0 && slot < 3;
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction side) {
        return new int[]{0, 1, 2, 3};
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, @Nonnull ItemStack itemStackIn, Direction direction) {
        return canPlaceItem(index, itemStackIn);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, @Nonnull ItemStack stack, @Nonnull Direction direction) {
        return index == 3;
    }

    @Nonnull
    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getItems(), slot);
    }
}
