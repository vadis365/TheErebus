package erebus.block.altars;

import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltarHealing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class HealingAltar extends AltarAbstract {

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarHealing();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityErebusAltarHealing te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarHealing.class);
		te.setActive(false);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarHealing te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarHealing.class);
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().getItem() == ModItems.wandOfAnimation && !te.active) {
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setActive(true);
				te.setSpawnTicks(12000);
				return true;
			}
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().getItem() == ModItems.wandOfAnimation && te.active) {
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setActive(false);
				return true;
			}
		return false;
	}
}