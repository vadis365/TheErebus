package erebus.inventory;

import erebus.ModItems;
import erebus.item.ItemMaterials;
import erebus.network.PacketPipeline;
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
		ItemStack stack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if (slotIndex > 4) {
				if (stack1.getItem() == ModItems.materials && stack1.getItemDamage() != ItemMaterials.DATA.smoothieGlass.ordinal() || stack1.getItem() != ModItems.materials) {
					if (!mergeItemStack(stack1, 0, 4, false))
						return null;
				} else if (stack1.getItem() == ModItems.materials && stack1.getItemDamage() == ItemMaterials.DATA.smoothieGlass.ordinal())
					if (!mergeItemStack(stack1, 4, 5, false))
						return null;
			} else if (!mergeItemStack(stack1, 5, inventorySlots.size(), false))
				return null;
			if (stack1.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();
			if (stack1.stackSize != stack.stackSize)
				slot.onPickupFromSlot(player, stack1);
			else
				return null;
		}
		return stack;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		if (tile != null) {
			NBTTagCompound nbt = new NBTTagCompound();
			tile.writeGUIData(nbt);
			for (Object crafter : crafters)
				if (crafter instanceof EntityPlayerMP)
					PacketPipeline.sendToPlayer((EntityPlayerMP) crafter, new PacketSmoothieMakerGUI(nbt));
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