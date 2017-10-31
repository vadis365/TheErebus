package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityConstructingEvent {
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && ErebusExtendedPlayerProperties.get((EntityPlayer) event.entity) == null)
			ErebusExtendedPlayerProperties.register((EntityPlayer) event.entity);

		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ErebusExtendedPlayerProperties.EREBUS_EXT_PROP_NAME) == null)
			event.entity.registerExtendedProperties(ErebusExtendedPlayerProperties.EREBUS_EXT_PROP_NAME, new ErebusExtendedPlayerProperties((EntityPlayer) event.entity));
	}
}