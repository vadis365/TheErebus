package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.block.BlockArmchair;
import erebus.core.handler.configs.ConfigHandler;

public class EntityArmchairSpawnHandler {

	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void playerDeath(LivingDeathEvent event) {
		World world = event.entityLiving.worldObj;
		if (world.isRemote)
			return;

		if (event.entityLiving instanceof EntityPlayer) {
			final EntityPlayer player = (EntityPlayer) event.entityLiving;
	    	if(world.isRemote)
	    		return;
			if(player.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
				int x = player.getBedLocation(ConfigHandler.INSTANCE.erebusDimensionID).posX;
				int y = player.getBedLocation(ConfigHandler.INSTANCE.erebusDimensionID).posY - 1;
				int z = player.getBedLocation(ConfigHandler.INSTANCE.erebusDimensionID).posZ;
				if(!(world.getBlock(x, y, z) instanceof BlockArmchair)) {
					System.out.println("No Armchair");
					int xx = player.getEntityData().getInteger("erebusSpawnSetX");
					int yy = player.getEntityData().getInteger("erebusSpawnSetY");
					int zz = player.getEntityData().getInteger("erebusSpawnSetZ");
					System.out.println("Death event Bed Not Found Block is: "+ world.getBlock(xx, yy, zz));
					player.setSpawnChunk(new ChunkCoordinates(xx, yy, zz), true, ConfigHandler.INSTANCE.erebusDimensionID);
					player.getEntityData().setBoolean("hasSpawn", true);
				}
			}
		}
	}
}