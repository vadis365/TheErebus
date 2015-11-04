package erebus.block.altars;

import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityErebusAltarXP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class XPAltar extends AltarAbstract {

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarXP();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityErebusAltarXP te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarXP.class);
		te.setActive(false);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		TileEntityErebusAltarXP te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarXP.class);
		double offsetY = 0.9D;
		if (entity instanceof EntityItem && entity.boundingBox.minY >= y + offsetY && te.active) {
			ItemStack stack = ((EntityItem) entity).getEntityItem();

			if (stack.getItem() == ModItems.materials) {
				te.setUses(te.getUses() + stack.stackSize);
				entity.setDead();
				if (!world.isRemote)
					world.spawnEntityInWorld(new EntityXPOrb(world, x + 0.5D, y + 1.8D, z + 0.5D, stack.stackSize * 5));
				if (te.getUses() > 165)
					te.setSpawnTicks(0);
				if (te.getExcess() > 0)
					Utils.dropStackNoRandom(world, (int) (x + 0.5D), (int) (y + 1.0D), (int) (z + 0.5D), stack.copy());
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityErebusAltarXP te = Utils.getTileEntity(world, x, y, z, TileEntityErebusAltarXP.class);
		if (player.getCurrentEquippedItem() != null)
			if (player.getCurrentEquippedItem().getItem() == ModItems.wandOfAnimation) {
				if (!te.active) {
					player.getCurrentEquippedItem().damageItem(1, player);
					te.setSpawnTicks(12000);
					te.setActive(true);
				} else {
					player.getCurrentEquippedItem().damageItem(1, player);
					te.setActive(false);
				}
				return true;
			}
		return false;
	}
}