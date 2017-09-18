package erebus.block.altars;

import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltarRepair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class RepairAltar extends AltarAbstract {

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarRepair();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityErebusAltarRepair te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarRepair.class);
		te.setActive(false);
		te.setcanBeUsed(true);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		TileEntityErebusAltarRepair te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarRepair.class);
		double offsetY = 0.9D;
		if (entity instanceof EntityItem && entity.boundingBox.minY >= y + offsetY && te.active) {
			te.setCollisions(te.getCollisions() + 1);
			ItemStack is = ((EntityItem) entity).getEntityItem();
			entity.posY = y + 1.6D;
			int repairDamage = is.getItemDamage();
			if (is.isItemStackDamageable() && repairDamage > 0) {
				if (te.notUsed)
					te.setSpawnTicks(160);
				if (te.getSpawnTicks() == 60 && te.getCollisions() == 101) {
					world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "random.anvil_use", 0.2F, 1.0F);
					is.getItem().setDamage(is, -repairDamage);
				}
				if (te.getSpawnTicks() % 2 == 0 && te.getCollisions() < 101)
					if (world.isRemote)
						te.sparky(world, x, y, z);
			}
			if (te.getCollisions() > 101)
				te.setSpawnTicks(0);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarRepair te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarRepair.class);
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