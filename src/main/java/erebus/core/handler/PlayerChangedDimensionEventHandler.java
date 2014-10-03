package erebus.core.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import erebus.ModAchievements;
import erebus.core.handler.configs.ConfigHandler;

public class PlayerChangedDimensionEventHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void entityJoin(PlayerChangedDimensionEvent event){
		if(event.player.dimension == ConfigHandler.INSTANCE.erebusDimensionID){
			event.player.triggerAchievement(ModAchievements.welcome);
		}
	}
}
