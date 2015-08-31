package erebus.core.handler;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import erebus.ModAchievements;
import erebus.core.handler.configs.ConfigHandler;

public class PlayerChangedDimensionEventHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void entityJoin(PlayerChangedDimensionEvent event) {
		if (event.toDim == ConfigHandler.INSTANCE.erebusDimensionID)
			event.player.triggerAchievement(ModAchievements.welcome);
	}
}
