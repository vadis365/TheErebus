package erebus.core.handler;

import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import erebus.core.handler.configs.ConfigHandler;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketArmchairClientMessages;

public class ErebusPlayerLoggedInEvent {

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		World world = event.player.worldObj;
		if (world.isRemote)
			return;
		if (event.player.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
			if (!event.player.getEntityData().hasKey("erebusPortalX")) {
				event.player.getEntityData().setInteger("erebusPortalX", (int) event.player.posX);
				event.player.getEntityData().setInteger("erebusPortalY", (int) event.player.posY);
				event.player.getEntityData().setInteger("erebusPortalZ", (int) event.player.posZ);
				PacketPipeline.sendToPlayer(event.player, new PacketArmchairClientMessages((byte)4));
			}
		}
	}
}
