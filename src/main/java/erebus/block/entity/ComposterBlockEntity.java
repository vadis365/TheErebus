package erebus.block.entity;

import erebus.inventory.server.ComposterMenu;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class ComposterBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {
	public static final int DATA_MOULD_PROGRESS = 0;
	public static final int DATA_COMPOSTING_PROGRESS = 1;
	public static final int DATA_MOULD_MAX_TIME = 2;
	public int mouldDurationTicks;
	public int mouldMaxTime;
	public int compostingProgressTicks;
	private final int SMELT_SLOT = 0;
	private final int FUEL_SLOT = 1;
	private final int RESULT_SLOT = 2;
	protected final ContainerData dataAccess;

	public ComposterBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.COMPOSTER.get(), 3, pos, state);

		this.dataAccess = new ContainerData() {
			@Override
			public int get(int i) {
				switch (i) {
					case DATA_MOULD_PROGRESS:
						return getMouldProgressScaled(13);
					case DATA_COMPOSTING_PROGRESS:
						return getCompostingProgressScaled(32);
					case DATA_MOULD_MAX_TIME:
						return mouldMaxTime;
					default:
						return 0;
				}
			}

			@Override
			public void set(int key, int value) {
				switch (key) {
					case DATA_MOULD_PROGRESS -> mouldDurationTicks = value;
					case DATA_COMPOSTING_PROGRESS -> compostingProgressTicks = value;
					case DATA_MOULD_MAX_TIME -> mouldMaxTime = value;
				}
			}

			@Override
			public int getCount() {
				return 3;
			}
		};
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		mouldDurationTicks = input.getIntOr("MouldDuration", 0);
		compostingProgressTicks = input.getIntOr("CompostProgress", 0);
		mouldMaxTime = getMouldUseTime(getItems().get(1));
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putInt("MouldDuration", mouldDurationTicks);
		output.putInt("CompostProgress", compostingProgressTicks);
	}

	public int getCompostingProgressScaled(int cookTime) {
		return compostingProgressTicks * cookTime / 200;
	}

	public int getMouldProgressScaled(int burnTime) {
		if (mouldMaxTime == 0)
			mouldMaxTime = 200;

		return mouldDurationTicks * burnTime / mouldMaxTime;
	}

	public boolean isComposting() {
		return mouldDurationTicks > 0;
	}

	public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T t) {
		if (t instanceof ComposterBlockEntity tile) {
			boolean shouldUpdate = tile.mouldDurationTicks > 0;
			boolean isDirty = false;

			if (tile.mouldDurationTicks > 0)
				tile.mouldDurationTicks--;

			if (tile.mouldDurationTicks != 0 || !tile.getItems().get(1).isEmpty() && !tile.getItems().get(0).isEmpty()) {
				if (tile.mouldDurationTicks == 0 && tile.canCompost()) {
					tile.mouldMaxTime = tile.mouldDurationTicks = getMouldUseTime(tile.getItems().get(1));

					if (tile.mouldDurationTicks > 0) {
						isDirty = true;

						if (!tile.getItems().get(1).isEmpty()) {
							tile.getItems().get(1).shrink(1);

							if (tile.getItems().get(1).getCount() == 0)
								tile.getItems().set(1, tile.getItems().get(1).getItem().getCraftingRemainder(tile.getItems().get(1)).create());
						}
					}
				}

				if (tile.isComposting() && tile.canCompost()) {
					++tile.compostingProgressTicks;

					if (tile.compostingProgressTicks == 200) {
						tile.compostingProgressTicks = 0;
						tile.compostItem();
						isDirty = true;
					}
				} else
					tile.compostingProgressTicks = 0;
			}

			if (shouldUpdate != tile.mouldDurationTicks > 0) {
				isDirty = true;
			}

			if (isDirty)
				tile.updateBlock();
		}
	}

	public void updateBlock() {
		getLevel().sendBlockUpdated(worldPosition, getLevel().getBlockState(worldPosition), getLevel().getBlockState(worldPosition), 3);
	}

	private boolean canCompost() {
		if (getItems().get(0).isEmpty())
			return false;
		else {
			ItemStack itemstack = isCompostable(getItems().get(0));
			if (itemstack.isEmpty())
				return false;
			if (getItems().get(2).isEmpty())
				return true;
			if (!ItemStack.isSameItem(getItems().get(2), itemstack))
				return false;
			int result = getItems().get(2).getCount() + itemstack.getCount();
			return result <= getItems().get(2).getMaxStackSize();
		}
	}

	public void compostItem() {
		if (canCompost()) {
			ItemStack itemstack = isCompostable(getItems().get(0));
			if (getItems().get(2).isEmpty())
				getItems().set(2, itemstack.copy());
			else if (getItems().get(2).getItem() == itemstack.getItem())
				getItems().get(2).grow(itemstack.getCount());
			getItems().get(0).shrink(1);
			if (getItems().get(0).getCount() <= 0)
				getItems().set(0, ItemStack.EMPTY);
		}
	}

	public ItemStack isCompostable(ItemStack itemStack) {
		return itemStack.is(ModItemTags.COMPOSTABLE) ? new ItemStack(ModItems.COMPOST.get()) : ItemStack.EMPTY;
	}

	public static int getMouldUseTime(ItemStack itemStack) {
		if (itemStack.isEmpty())
			return 0;
		else {
			if (itemStack.is(ModBlocks.MOULD.asItem()))
				return 800;
			if (itemStack.is(ModBlocks.MOULD_CULTIVATED.asItem()))
				return 400;
		}
		return 0;
	}

	public boolean isItemMould(ItemStack is) {
		return getMouldUseTime(is) > 0;
	}

	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return slot != RESULT_SLOT && (slot == FUEL_SLOT ? isItemMould(is) : slot == SMELT_SLOT && is.is(ModItemTags.COMPOSTABLE));
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, @NonNull Inventory playerInventory, @NonNull Player player) {
		return new ComposterMenu(containerId, playerInventory, this, this.dataAccess);
	}

	@Override
	public int @NotNull [] getSlotsForFace(@NotNull Direction side) {
		return side == Direction.DOWN ? new int[]{RESULT_SLOT} : new int[]{FUEL_SLOT, SMELT_SLOT};
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, @NonNull ItemStack itemStack, Direction direction) {
		return isItemValidForSlot(index, itemStack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, @NonNull ItemStack stack, @NonNull Direction direction) {
		return direction == Direction.DOWN && index == RESULT_SLOT && stack.is(ModItems.COMPOST.get());
	}

	@Override
	public boolean canPlaceItem(int slot, @NonNull ItemStack stack) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canTakeItem(@NonNull Container target, int slot, @NonNull ItemStack stack) {
		return slot == RESULT_SLOT && stack.is(ModItems.COMPOST.get());
	}

	@Override
	public @NonNull ItemStack removeItemNoUpdate(int slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public @NonNull Component getDisplayName() {
		return Component.translatable("erebus.container.composter");
	}
}
