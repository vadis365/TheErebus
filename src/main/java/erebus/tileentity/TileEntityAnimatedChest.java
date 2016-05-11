package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import erebus.entity.EntityAnimatedChest;

public class TileEntityAnimatedChest extends TileEntityBasicInventory {

	protected EntityAnimatedChest chester;

	public TileEntityAnimatedChest(EntityAnimatedChest chest) {
		super(chest.inventory.length, StatCollector.translateToLocal("container.animatedChest"));
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