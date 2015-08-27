package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.Erebus;
import erebus.core.handler.configs.ConfigHandler;

public class EntityPlayerSpawnHandler {

	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void playerSpawn(EntityJoinWorldEvent event) {
		World world = event.entity.worldObj;
		if (world.isRemote)
			return;

		if (event.entity instanceof EntityPlayer) {
			final EntityPlayer player = (EntityPlayer) event.entity;
			if(player.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
				if(!player.getEntityData().hasKey("hasSpawn"))
					player.getEntityData().setBoolean("hasSpawn", false);
				if(!player.getEntityData().getBoolean("hasSpawn")) {
					int x = MathHelper.floor_double(player.posX);
					int y = MathHelper.floor_double(player.posY);
					int z = MathHelper.floor_double(player.posZ);
					player.getEntityData().setInteger("erebusSpawnSetX", x);
					player.getEntityData().setInteger("erebusSpawnSetY", y);
					player.getEntityData().setInteger("erebusSpawnSetZ", z);
					player.setSpawnChunk(new ChunkCoordinates(x, y, z), true, ConfigHandler.INSTANCE.erebusDimensionID);
					player.getEntityData().setBoolean("hasSpawn", true);
					Erebus.proxy.getClientPlayer().addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.unsafeSpawnSet")));    
				}
			}
		}
	}
}