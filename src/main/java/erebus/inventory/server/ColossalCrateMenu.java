package erebus.inventory.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import erebus.block.entity.BambooCrateBlockEntity;
import erebus.registries.client.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ColossalCrateMenu extends AbstractContainerMenu {

	private final Inventory playerInventory;
	public BambooCrateBlockEntity crate1;
	public BambooCrateBlockEntity crate2;
	public BambooCrateBlockEntity crate3;
	public BambooCrateBlockEntity crate4;
	public BambooCrateBlockEntity crate5;
	public BambooCrateBlockEntity crate6;
	public BambooCrateBlockEntity crate7;
	public BambooCrateBlockEntity crate8;
	public List<BambooCrateBlockEntity> crateList;
	public final int[][] places = new int[][] { { 1, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 0 } };

	public int page = 1;

	public ColossalCrateMenu(final int windowId, final Inventory playerInventory, FriendlyByteBuf extra) {
		super(ModMenuTypes.COLOSSAL_CRATE.get(), windowId);
		BlockPos pos = extra.readBlockPos();
		Level level = playerInventory.player.getCommandSenderWorld();

		List<BambooCrateBlockEntity> list = new ArrayList<BambooCrateBlockEntity>();
		for (int[] place : places) {
			BlockEntity te = level.getBlockEntity(pos.offset(place[0], place[1], place[2]));
			if (te != null && te instanceof BambooCrateBlockEntity)
				list.add((BambooCrateBlockEntity) te);
		}
		this.playerInventory = playerInventory;
		crate1 = list.get(0);
		crate2 = list.get(1);
		crate3 = list.get(2);
		crate4 = list.get(3);
		crate5 = list.get(4);
		crate6 = list.get(5);
		crate7 = list.get(6);
		crate8 = list.get(7);
		crateList = list;
		addSlots();
	}

	public void addSlots() {
		int j;
		int k;
		int slotNo = page * 72 - 72;
		for (j = slotNo; j < slotNo + 72; ++j) {
			int crateNo = getCrateNumberFromSlotNo(j);
			addSlot(new Slot(crateList.get(crateNo), getSlotIDFromSlotNo(j), getSlotXFromSlotNo(j), getSlotYFromSlotNo(j)));
		}

		for (j = 0; j < 3; ++j)
			for (k = 0; k < 9; ++k)
				addSlot(new Slot(playerInventory, k + j * 9 + 9, 35 + k * 18, 138 + j * 18));

		for (j = 0; j < 9; ++j)
			addSlot(new Slot(playerInventory, j, 35 + j * 18, 196));
	}

	public void changePage(int i) {
		while (i < 1)
			i += 3;
		while (i > 3)
			i -= 3;
		page = i;
		slots.clear();
		addSlots();
	}

	@Override
	public boolean stillValid(@Nonnull Player player) {
		return crate1.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(@Nonnull Player player, int slotIndex) {
		ItemStack is = ItemStack.EMPTY;
		Slot slot = (Slot) slots.get(slotIndex);

		if (slot != null && slot.hasItem()) {
			ItemStack is1 = slot.getItem();
			is = is1.copy();

			if (slotIndex < 72) {
				if (!moveItemStackTo(is1, 72, slots.size(), true))
					return ItemStack.EMPTY;
			} else if (!moveItemStackTo(is1, 0, 72, false))
				return ItemStack.EMPTY;

			if (is1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
		}

		return is;
	}

	public int getCrateNumberFromSlotNo(int slot) {
		int no = slot % 27;
		int roundedDown = slot - no;
		return roundedDown / 27;
	}

	public int getSlotIDFromSlotNo(int slot) {
		return slot % 27;
	}

	public int getSlotXFromSlotNo(int slot) {
		int coloumOn = slot;
		while (coloumOn >= 12)
			coloumOn -= 12;
		return 8 + coloumOn * 18;
	}

	public int getSlotYFromSlotNo(int slot) {
		while (slot >= 72)
			slot -= 72;
		int no = slot % 6;
		int roundedDown = slot - no;
		int rowOn = roundedDown / 12;
		return 18 + rowOn * 18;
	}
}