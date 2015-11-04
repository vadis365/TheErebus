package erebus.inventory;

import java.util.List;

import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerColossalCrate extends Container {

	private final InventoryPlayer playerInventory;
	public TileEntityBambooCrate crate1;
	public TileEntityBambooCrate crate2;
	public TileEntityBambooCrate crate3;
	public TileEntityBambooCrate crate4;
	public TileEntityBambooCrate crate5;
	public TileEntityBambooCrate crate6;
	public TileEntityBambooCrate crate7;
	public TileEntityBambooCrate crate8;
	public List<TileEntityBambooCrate> crateList;

	public int page = 1;

	public ContainerColossalCrate(InventoryPlayer playerInventory, List<TileEntityBambooCrate> list) {
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
			addSlotToContainer(new Slot(crateList.get(crateNo), getSlotIDFromSlotNo(j), getSlotXFromSlotNo(j), getSlotYFromSlotNo(j)));
		}

		for (j = 0; j < 3; ++j)
			for (k = 0; k < 9; ++k)
				addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 35 + k * 18, 138 + j * 18));

		for (j = 0; j < 9; ++j)
			addSlotToContainer(new Slot(playerInventory, j, 35 + j * 18, 196));
	}

	public void changePage(int i) {
		while (i < 1)
			i += 3;
		while (i > 3)
			i -= 3;
		page = i;
		inventorySlots.clear();
		inventoryItemStacks.clear();
		addSlots();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return crate1.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
		ItemStack is = null;
		Slot slot = (Slot) inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack is1 = slot.getStack();
			is = is1.copy();

			if (par2 < 72) {
				if (!mergeItemStack(is1, 72, inventorySlots.size(), true))
					return null;
			} else if (!mergeItemStack(is1, 0, 72, false))
				return null;

			if (is1.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();
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