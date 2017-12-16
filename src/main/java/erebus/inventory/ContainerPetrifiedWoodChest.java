package erebus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;

public class ContainerPetrifiedWoodChest extends ContainerChest {

	public ContainerPetrifiedWoodChest(IInventory playerInventory, IInventory chestInventory, EntityPlayer player) {
		super(playerInventory, chestInventory, player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}