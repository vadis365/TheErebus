package erebus.inventory.server;

import erebus.block.entity.BambooExtenderBlockEntity;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nonnull;

public class BambooExtenderMenu extends AbstractContainerMenu {
	public BambooExtenderBlockEntity extender;
	
	public BambooExtenderMenu(final int windowId, final Inventory playerInventory, FriendlyByteBuf extra) {
		super(ModMenuTypes.BAMBOO_EXTENDER.get(), windowId);

		BlockPos tilePos = extra.readBlockPos();
		BlockEntity tile = playerInventory.player.level().getBlockEntity(tilePos);

		if (!(tile instanceof BambooExtenderBlockEntity))
			return;

		extender = (BambooExtenderBlockEntity) tile;
		
		for (int i = 0; i < extender.getItems().size(); i++)
			addSlot(new Slot(extender, i, 35 + 18 * i, 18));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 54 + i * 18));
		for (int i = 0; i < 9; i++)
			addSlot(new Slot(playerInventory, i, 8 + i * 18, 112));
	}

	@Override
	public boolean stillValid(@Nonnull Player player) {
		return true;
	}

	@Override
	public ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = slots.get(slotIndex);

		if (slot != null && slot.hasItem()) {
			ItemStack slotItemStack = slot.getItem();
			itemStack = slotItemStack.copy();

			if (slotIndex < 6) {
				if (!moveItemStackTo(itemStack, 6, slots.size(), true))
					return ItemStack.EMPTY;
			} else if (!moveItemStackTo(itemStack, 0, 6, false))
				return ItemStack.EMPTY;

			if (itemStack.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
		}
			return itemStack;
	}
}
