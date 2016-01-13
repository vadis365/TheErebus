package erebus.core.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketAntiVenom;

public class AntiVenomDurationHandler {
	public final byte[] potionIds = new byte[] { 2, 4, 9, 15, 17, 18, 19, 20 };
	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void playerAntiVenomTick(PlayerTickEvent event) {

		World world = event.player.worldObj;
		if (world.isRemote)
			return;
		if (event.phase == event.phase.END) {
			if (event.player.getEntityData().hasKey("antivenomDuration")) {
				int duration = event.player.getEntityData().getInteger("antivenomDuration");
				if (duration > 0) {
					List<Potion> toRemove = new ArrayList<Potion>();
					for (PotionEffect effect : (Collection<PotionEffect>) event.player.getActivePotionEffects()) {
						Potion potion = Potion.potionTypes[effect.getPotionID()];
						for (int count = 0; count < potionIds.length; count++)
							if (potion.getId() == potionIds[count])
								toRemove.add(potion);
					}
					for (Potion potion : toRemove)
						event.player.removePotionEffect(potion.getId());

					if (world.getTotalWorldTime() % 20 == 0) {
						duration--;
						event.player.getEntityData().setInteger("antivenomDuration", duration);
						PacketPipeline.sendToPlayer(event.player, new PacketAntiVenom(duration));
					}
				} else
					return;
			}
		}
	}
}
