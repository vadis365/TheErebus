package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import erebus.entity.EntityTitanBeetle;

public class TileEntityTitanChest extends TileEntityBasicInventory {

	protected EntityTitanBeetle titan;

	public TileEntityTitanChest(EntityTitanBeetle chest) {
		super(chest.inventory.length, "Titan Beetle");
		inventory = chest.inventory;
		titan = chest;
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
		titan.setOpen(true);
	}

	@Override
	public void closeInventory() {
		titan.setOpen(false);
	}
}