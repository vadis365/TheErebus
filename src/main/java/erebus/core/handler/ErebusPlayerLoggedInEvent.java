package erebus.core.handler;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import erebus.Erebus;
import erebus.core.handler.configs.ConfigHandler;

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
				Erebus.proxy.getClientPlayer().addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.unstable")));
			}
		}
	}
}
