package erebus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class ContainerPetrifiedCraftingTable extends ContainerWorkbench {

	public ContainerPetrifiedCraftingTable(InventoryPlayer player, World world, int x, int y, int z) {
		super(player, world, x, y, z);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
