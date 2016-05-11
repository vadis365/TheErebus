package erebus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;
import erebus.ModBlocks;
import erebus.entity.EntityAnimatedBambooCrate;

public class TileEntityAnimatedBambooCrate extends TileEntityBasicInventory {

	protected EntityAnimatedBambooCrate bamber;

	public TileEntityAnimatedBambooCrate(EntityAnimatedBambooCrate crate) {
		super(crate.inventory.length, StatCollector.translateToLocal("container.animatedBambooCrate"));
		inventory = crate.inventory;
		bamber = crate;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public Block getBlockType() {
		return ModBlocks.bambooCrate;
	}
}