package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import erebus.entity.EntityAnimatedChest;

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
}