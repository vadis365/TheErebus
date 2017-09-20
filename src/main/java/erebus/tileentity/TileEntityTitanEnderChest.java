package erebus.tileentity;

import erebus.entity.EntityTitanBeetle;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ITickable;

public class TileEntityTitanEnderChest extends TileEntityEnderChest implements ITickable {

	protected EntityTitanBeetle titan;

	public TileEntityTitanEnderChest(EntityTitanBeetle entityTitanBeetle) {
		titan = entityTitanBeetle;
	}

	@Override
	public Block getBlockType() {
		return Blocks.ENDER_CHEST;
	}

	@Override
	public void update() {
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		return false;
	}

	@Override
	public void invalidate() {
		updateContainingBlockInfo();
		super.invalidate();
	}

	@Override
	public void openChest() {
		if (!titan.getOpen())
			titan.setOpen(true);
	}

	@Override
	public void closeChest() {
		if (titan.getOpen())
			titan.setOpen(false);
	}

	@Override
	public boolean canBeUsed(EntityPlayer player) {
		return true;
	}
}