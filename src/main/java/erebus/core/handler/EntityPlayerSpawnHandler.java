package erebus.core.handler;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.core.handler.configs.ConfigHandler;

public class EntityPlayerSpawnHandler {

	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onClonePlayer(PlayerEvent.Clone event) {
		
		World world = event.entity.worldObj;
		if (world.isRemote)
			return;

		if (event.wasDeath) {
			if(event.entityPlayer.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
				if(!event.original.getEntityData().hasKey("hasSpawn"))
					event.entityPlayer.getEntityData().setBoolean("hasSpawn", false);
				if(event.original.getEntityData().getBoolean("hasSpawn")) {
					int x = event.original.getEntityData().getInteger("erebusSpawnSetX");
					int y = event.original.getEntityData().getInteger("erebusSpawnSetY");
					int z = event.original.getEntityData().getInteger("erebusSpawnSetZ");
					event.entityPlayer.getEntityData().setInteger("erebusSpawnSetX", x);
					event.entityPlayer.getEntityData().setInteger("erebusSpawnSetY", y);
					event.entityPlayer.getEntityData().setInteger("erebusSpawnSetZ", z);
					event.entityPlayer.setSpawnChunk(new ChunkCoordinates(x, y, z), true, ConfigHandler.INSTANCE.erebusDimensionID);
					event.entityPlayer.getEntityData().setBoolean("hasSpawn", true);

					System.out.println("Clone event Block below is: "+ world.getBlock(x, y, z));
					//if(!(world.getBlock(x, y, z) instanceof BlockArmchair))
					//	Erebus.proxy.getClientPlayer().addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.unsafeSpawnSet")));    
				}
			}
		}
	}
}

