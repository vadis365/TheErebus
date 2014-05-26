package erebus.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import erebus.Erebus;
import erebus.world.TeleporterErebus;

public class CommonTickHandler {
	// wtf is this
	//public boolean genLairNextTick = false;
	//private int ticksToGo = 5;

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent e) {
		if (e.player != null)
			Erebus.teleportHandler.onTick(e.player);
	}

	@SubscribeEvent
	public void onWorldTick(WorldTickEvent e) {
		if (e.world.provider.dimensionId == ConfigHandler.erebusDimensionID) {
			TeleporterErebus.TELEPORTER_TO_EREBUS.removeStalePortalLocations(e.world.getTotalWorldTime());
			TeleporterErebus.TELEPORTER_TO_OVERWORLD.removeStalePortalLocations(e.world.getTotalWorldTime());
		}

		/*if (genLairNextTick) {
			ticksToGo--;
			if (ticksToGo <= 0) {

			}
		}*/
	}
}