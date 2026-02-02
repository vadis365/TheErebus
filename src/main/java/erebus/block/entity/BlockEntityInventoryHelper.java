package erebus.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public abstract class BlockEntityInventoryHelper extends BlockEntity implements WorldlyContainer {

	private final NonNullList<ItemStack> inventory;

	public BlockEntityInventoryHelper(BlockEntityType<?> tileEntityTypeIn, int invSize, BlockPos pos, BlockState state) {
		super(tileEntityTypeIn, pos, state);
		inventory = NonNullList.withSize(invSize, ItemStack.EMPTY);
	}

	@Override
	public int @NotNull [] getSlotsForFace(@NotNull Direction side) {
		int[] SLOTS = new int[getContainerSize()];
		for (int index = 0; index < SLOTS.length; index++)
			SLOTS[index] = index;
		return SLOTS;
	}

	@Override
	public int getContainerSize() {
		return inventory.size();
	}

	@Override
	public @NotNull ItemStack getItem(int slot) {
		return inventory.get(slot);
	}

    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

	@Override
    public @NotNull ItemStack removeItem(int index, int count) {
		ItemStack itemstack = ContainerHelper.removeItem(inventory, index, count);
		if (!itemstack.isEmpty())
			this.setChanged();
		return itemstack;
	}

	@Override
    public void setItem(int index, @Nullable ItemStack stack) {
        inventory.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize())
            stack.setCount(this.getMaxStackSize());
        this.setChanged();
    }

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return true;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : inventory) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	protected void saveAdditional(@NonNull ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, inventory, false);
	}

	@Override
	protected void loadAdditional(@NonNull ValueInput input) {
		super.loadAdditional(input);
		ContainerHelper.loadAllItems(input, inventory);
	}

	@Override
	public void clearContent() {
		inventory.clear();
	}

	public boolean canInsertItem() {
		return false;
	}
}
