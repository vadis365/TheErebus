package erebus.inventory;

import erebus.Erebus;
import erebus.ModItems;
import erebus.items.ItemMaterials;
import erebus.network.client.PacketSmoothieMakerGUI;
import erebus.tileentity.TileEntitySmoothieMaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerSmoothieMaker extends Container {

	protected TileEntitySmoothieMaker tile;

	public ContainerSmoothieMaker(InventoryPlayer inventory, TileEntitySmoothieMaker tileentity) {
		tile = tileentity;

		addSlotToContainer(new Slot(tileentity, 0, 47, 9));
		addSlotToContainer(new Slot(tileentity, 1, 113, 9));

		addSlotToContainer(new Slot(tileentity, 2, 68, 30));
		addSlotToContainer(new Slot(tileentity, 3, 92, 30));

		addSlotToContainer(new Slot(tileentity, 4, 80, 63));

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		for (int i = 0; i < 9; ++i)
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(slotIndex);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if (slotIndex > 4) {
				if (stack1.getItem() == ModItems.MATERIALS && stack1.getItemDamage() != ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.ordinal() || stack1.getItem() != ModItems.MATERIALS) {
					if (!mergeItemStack(stack1, 0, 4, false))
						return ItemStack.EMPTY;
				} else if (stack1.getItem() == ModItems.MATERIALS && stack1.getItemDamage() == ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.ordinal())
					if (!mergeItemStack(stack1, 4, 5, false))
						return ItemStack.EMPTY;
			} else if (!mergeItemStack(stack1, 5, inventorySlots.size(), false))
				return ItemStack.EMPTY;
			if (stack1.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();
			if (stack1.getCount() != stack.getCount())
				slot.onTake(player, stack1);
			else
				return ItemStack.EMPTY;
		}
		return stack;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		if (tile != null) {
			NBTTagCompound nbt = new NBTTagCompound();
			tile.writeGUIData(nbt);
			for (Object obj : listeners)  
				if (obj instanceof EntityPlayerMP)  
					Erebus.NETWORK_WRAPPER.sendTo(new PacketSmoothieMakerGUI(nbt), (EntityPlayerMP) obj);
		}
	}

	public void readPacketData(NBTTagCompound nbt) {
		if (tile != null && nbt != null)
			tile.readGUIData(nbt);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}