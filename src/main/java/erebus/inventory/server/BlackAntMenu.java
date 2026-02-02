package erebus.inventory.server;

import erebus.entity.BlackAnt;
import erebus.inventory.slot.BlackAntSlot;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class BlackAntMenu extends AbstractContainerMenu {

	private BlackAntSimpleContainer container;
	private int entityId;
	private BlackAnt entity;

	public BlackAntMenu(final int windowId, final Inventory playerInventory, FriendlyByteBuf buf) {
		this(windowId, playerInventory, (BlackAnt) Minecraft.getInstance().level.getEntity(buf.readInt()));
		this.entityId = buf.readInt();
		this.container = new BlackAntSimpleContainer(3);
		this.entity = (BlackAnt) playerInventory.player.level().getEntity(entityId);
		this.container.startOpen(playerInventory.player);
	}

	public BlackAntMenu(int windowId, Inventory playerInventory, BlackAnt entity) {
		super(ModMenuTypes.BLACK_ANT.get(), windowId);
		BlackAntSimpleContainer entityInventory = entity.getInventory();
		checkContainerSize(entityInventory, entity.getInventorySize());
		this.container = entityInventory;

		int i = -54;

		for (int k = 0; k < 3; k++)
			addSlot(new BlackAntSlot(entityInventory, k, 26 + k * 54, 18/*, k == 2*/)); //TODO add back slot restriction once done testing

		for (int j = 0; j < 3; j++)
			for (int k = 0; k < 9; k++)
				addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
		for (int j = 0; j < 9; j++)
			addSlot(new Slot(playerInventory, j, 8 + j * 18, 161 + i));
	}

	@Override
	public boolean stillValid(@Nonnull Player player) {
		return this.container.stillValid(player);
	}

	@Override
	public @NonNull ItemStack quickMoveStack(@NonNull Player player, int slotIndex) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(slotIndex);

		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();

			if (slotIndex == BlackAnt.CROP_ID_SLOT) {
				slot.set(ItemStack.EMPTY);
				return stack;
			} else if (slotIndex < 3) {
				if (!moveItemStackTo(stack1, 3, container.getContainerSize(), true))
					return ItemStack.EMPTY;
			} else if (container.canPlaceItem(0, stack1)) {
				if (!moveItemStackTo(stack1, 0, 1, false))
					return ItemStack.EMPTY;
			} else
				return ItemStack.EMPTY;

			if (stack1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
		}
		return stack;
	}

	@Override
	public void removed(@NonNull Player player) {
		super.removed(player);
		this.container.stopOpen(player);
	}

	@Override
	public void clicked(int slotIndex, int buttonNum, @NonNull ContainerInput containerInput, @NonNull Player player) {
		if (slotIndex == BlackAnt.CROP_ID_SLOT) {
			Slot slot = slots.get(slotIndex);
			ItemStack slotStack = slot.getItem();
			ItemStack heldStack = player.containerMenu.getCarried();

			if (slotStack.isEmpty() && !heldStack.isEmpty() && slots.get(BlackAnt.TOOL_SLOT).hasItem() && !slots.get(BlackAnt.TOOL_SLOT).getItem().is(Items.SHEARS)) {
				ItemStack copy = heldStack.copy();
				copy.setCount(1);
				slot.set(copy);
			} else if (!slotStack.isEmpty())
				slot.set(ItemStack.EMPTY);
		} else super.clicked(slotIndex, buttonNum, containerInput, player);
	}
}
