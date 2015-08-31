package erebus.tileentity;

import erebus.entity.EntityAnimatedChest;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class TileEntityAnimatedChest extends TileEntityBasicInventory {

	protected EntityAnimatedChest chester;

	public TileEntityAnimatedChest(EntityAnimatedChest chest) {
		super(chest.inventory.length, "Chester");
		inventory = chest.inventory;
		chester = chest;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public Block getBlockType() {
		return Blocks.chest;
	}

	@Override
	public void openInventory() {
		chester.setOpen(true);
	}

	@Override
	public void closeInventory() {
		chester.setOpen(false);
	}
}