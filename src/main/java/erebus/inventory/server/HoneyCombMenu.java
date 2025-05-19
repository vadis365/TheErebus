package erebus.inventory.server;

import javax.annotation.Nonnull;

import erebus.block.entity.HoneyCombBlockEntity;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HoneyCombMenu extends AbstractContainerMenu {
	public HoneyCombBlockEntity honey_comb;
	public int numRows = 3;

	public HoneyCombMenu(final int windowId, final Inventory playerInventory, FriendlyByteBuf extra) {
		super(ModMenuTypes.HONEY_COMB.get(), windowId);
		BlockPos tilePos = extra.readBlockPos();
		BlockEntity tile = playerInventory.player.getCommandSenderWorld().getBlockEntity(tilePos);

		if (!(tile instanceof HoneyCombBlockEntity))
			return;

		honey_comb = (HoneyCombBlockEntity) tile;
		
		int i = (numRows - 4) * 18;
		int j;
		int k;

		for (j = 0; j < numRows; ++j)
			for (k = 0; k < 9; ++k)
				addSlot(new Slot((Container) honey_comb, k + j * 9, 8 + k * 18, 18 + j * 18));

		for (j = 0; j < 3; ++j)
			for (k = 0; k < 9; ++k)
				addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + i));

		for (j = 0; j < 9; ++j)
			addSlot(new Slot(playerInventory, j, 8 + j * 18, 160 + i));
	}

	@Override
	public boolean stillValid(@Nonnull Player player) {
		return true;
	}

	@Override
	public ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
		ItemStack is = ItemStack.EMPTY;
		Slot slot = slots.get(slotIndex);

		if (slot != null && slot.hasItem()) {
			ItemStack is1 = slot.getItem();
			is = is1.copy();

			if (slotIndex < numRows * 9) {
				if (!moveItemStackTo(is1, numRows * 9, slots.size(), true))
					return ItemStack.EMPTY;
			} else if (!moveItemStackTo(is1, 0, numRows * 9, false))
				return ItemStack.EMPTY;

			if (is1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
		}

		return is;
	}

}
