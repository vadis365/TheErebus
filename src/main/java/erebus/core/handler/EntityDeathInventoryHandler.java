package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityBones;

public class EntityDeathInventoryHandler {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void playerDeath(LivingDeathEvent event) {
		World world = event.entityLiving.worldObj;
		if (world.isRemote)
			return;

		if (event.entityLiving instanceof EntityPlayer && !world.getGameRules().getGameRuleBooleanValue("keepInventory")) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY - 1);
			int z = MathHelper.floor_double(player.posZ);
			int playerFacing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			byte directionMeta = 0;

			if (!world.isAirBlock(x, y, z))
				y++;
			if (playerFacing == 0)
				directionMeta = 2;
			if (playerFacing == 1)
				directionMeta = 5;
			if (playerFacing == 2)
				directionMeta = 3;
			if (playerFacing == 3)
				directionMeta = 4;
			world.setBlock(x, y, z, ModBlocks.bones, directionMeta, 3);
			TileEntityBones tile = Utils.getTileEntity(world, x, y, z, TileEntityBones.class);
			if (tile != null) {
				for (int i = 0; i < player.inventory.mainInventory.length; i++) {
					ItemStack cont = player.inventory.mainInventory[i];
					if (cont != null) {
						tile.setInventorySlotContents(i, cont.copy());
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
				tile.setOwner("R.I.P. " + player.getCommandSenderName());
			}
		}
	}
}