package erebus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;

public class ContainerPetrifiedWoodChest extends ContainerChest {

	public ContainerPetrifiedWoodChest(IInventory player, IInventory chest) {
		super(player, chest);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}