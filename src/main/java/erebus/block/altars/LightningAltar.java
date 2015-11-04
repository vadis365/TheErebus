package erebus.block.altars;

import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltarLightning;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LightningAltar extends AltarAbstract {

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarLightning();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityErebusAltarLightning te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarLightning.class);
		te.setActive(false);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarLightning te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarLightning.class);
		if (player.getCurrentEquippedItem() != null) {
			if (player.getCurrentEquippedItem().getItem() == ModItems.wandOfAnimation && !te.active) {
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setActive(true);
				te.setSpawnTicks(12000);
				return true;
			}
			if (player.getCurrentEquippedItem().getItem() == ModItems.wandOfAnimation && te.active) {
				player.getCurrentEquippedItem().damageItem(1, player);
				te.setActive(false);
				return true;
			}
		}

		return false;
	}
}