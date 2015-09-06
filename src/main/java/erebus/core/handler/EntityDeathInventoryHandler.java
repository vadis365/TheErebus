package erebus.core.handler;

import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityBones;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EntityDeathInventoryHandler {

	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void playerDeath(LivingDeathEvent event) {
		World world = event.entityLiving.worldObj;
		if (world.isRemote)
			return;

		if (event.entityLiving instanceof EntityPlayer && !world.getGameRules().getGameRuleBooleanValue("keepInventory")) {
			final EntityPlayer player = (EntityPlayer) event.entityLiving;

			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY - 1);
			int z = MathHelper.floor_double(player.posZ);
			int playerFacing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			byte directionMeta = 0;
			BlockPos pos = new BlockPos(x, y, z);

			if (!world.isAirBlock(pos))
				y++;
			if (playerFacing == 0)
				directionMeta = 2;
			if (playerFacing == 1)
				directionMeta = 5;
			if (playerFacing == 2)
				directionMeta = 3;
			if (playerFacing == 3)
				directionMeta = 4;
			world.setBlockState(pos, ModBlocks.bones.getStateFromMeta(directionMeta), 3);
			TileEntityBones tile = Utils.getTileEntity(world, pos, TileEntityBones.class);
			if (tile != null) {
				for (int i = 0; i < player.inventory.mainInventory.length; i++) {
					ItemStack cont = player.inventory.mainInventory[i];
					if (cont != null) {
						tile.setInventorySlotContents(i + 4, cont.copy());
						player.inventory.mainInventory[i] = null;
					}
				}
				for (int i = 0; i < player.inventory.armorInventory.length; i++) {
					ItemStack cont = player.inventory.armorInventory[i];
					if (cont != null) {
						tile.setInventorySlotContents(i, cont.copy());
						player.inventory.armorInventory[i] = null;
					}
				}

				List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, player.getBoundingBox().expand(16, 16, 16));
				Collections.sort(list, new Comparator<Entity>() {
					@Override
					public int compare(Entity o1, Entity o2) {
						return Double.compare(o1.getDistanceSqToEntity(player), o2.getDistanceSqToEntity(player));
					}
				});
				int index = 0;
				for (int i = player.inventory.mainInventory.length + 4; i < player.inventory.mainInventory.length + 50; i++) {
					if (index >= list.size())
						break;
					EntityItem entityitem = list.get(index++);
					if (entityitem != null) {
						ItemStack cont = entityitem.getEntityItem();
						if (cont != null) {
							tile.setInventorySlotContents(i, cont.copy());
							entityitem.setDead();
						}
					}
				}
				tile.setOwner("R.I.P. " + player.getCommandSenderEntity().getName());
			}
		}
	}
}