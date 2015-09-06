package erebus.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerPetrifiedCraftingTable extends ContainerWorkbench {

	public ContainerPetrifiedCraftingTable(InventoryPlayer player, World world, BlockPos pos) {
		super(player, world, pos);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
