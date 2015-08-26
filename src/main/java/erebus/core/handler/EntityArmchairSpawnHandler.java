package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayer;
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
			int x = player.getBedLocation(ConfigHandler.INSTANCE.erebusDimensionID).posX;
			int y = player.getBedLocation(ConfigHandler.INSTANCE.erebusDimensionID).posY - 1;
			int z = player.getBedLocation(ConfigHandler.INSTANCE.erebusDimensionID).posZ;
			if(!(world.getBlock (x, y, z) instanceof BlockArmchair)) {
				player.setSpawnChunk(null, true, ConfigHandler.INSTANCE.erebusDimensionID);
			}
		}
	}
}