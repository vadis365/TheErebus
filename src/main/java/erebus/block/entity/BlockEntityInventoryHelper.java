package erebus.block.entity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityInventoryHelper extends BlockEntity implements WorldlyContainer {

	private NonNullList<ItemStack> inventory;

	public BlockEntityInventoryHelper(BlockEntityType<?> tileEntityTypeIn, int invtSize, BlockPos pos, BlockState state) {
		super(tileEntityTypeIn, pos, state);
		inventory = NonNullList.<ItemStack>withSize(invtSize, ItemStack.EMPTY);
	}

	@Override
	public int getContainerSize() {
		return inventory.size();
	}

	@Override
	public ItemStack getItem(int slot) {
		return inventory.get(slot);
	}

    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

	@Override
    public ItemStack removeItem(int index, int count) {
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
	public boolean stillValid(Player player) {
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
	public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
		super.saveAdditional(compound, registries);
		ContainerHelper.saveAllItems(compound, inventory, false, registries);
	}

	@Override
	public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
		super.loadAdditional(compound, registries);
		inventory = NonNullList.<ItemStack>withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (compound.contains("Items", 9))
			ContainerHelper.loadAllItems(compound, inventory, registries);
	}

	@Override
	public void startOpen(Player playerIn) {
	}

	@Override
	public void stopOpen(Player playerIn) {
	}

	@Override
	public void clearContent() {
		inventory.clear();
	}

	public boolean canInsertItem() {
		return false;
	}
}