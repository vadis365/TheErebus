package erebus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;

public class ContainerTitanBeetle extends ContainerChest {

	public ContainerTitanBeetle(IInventory player, IInventory beetle) {
		super(player, beetle);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		// Do your stuff here Dave
		// Remember that this is server side only
	}
}