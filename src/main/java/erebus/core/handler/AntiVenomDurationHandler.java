package erebus.core.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import erebus.Erebus;
import erebus.network.server.PacketAntiVenom;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class AntiVenomDurationHandler {

	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void playerAntiVenomTick(PlayerTickEvent event) {

		World world = event.player.getEntityWorld();
		if (world.isRemote)
			return;
		if (event.phase == event.phase.END) {
			if (event.player.getEntityData().hasKey("anti_venom_duration")) {
				int duration = event.player.getEntityData().getInteger("anti_venom_duration");
				if (duration > 0) {
					List<Potion> toRemove = new ArrayList<Potion>();
					for (PotionEffect effect : (Collection<PotionEffect>) event.player.getActivePotionEffects()) {
						Potion potion = effect.getPotion();
						if (potion.isBadEffect())
							toRemove.add(potion);
					}
					for (Potion potion : toRemove)
						event.player.removePotionEffect(potion);

					if (world.getTotalWorldTime() % 20 == 0) {
						duration--;
						event.player.getEntityData().setInteger("anti_venom_duration", duration);
						Erebus.NETWORK_WRAPPER.sendTo(new PacketAntiVenom(duration), (EntityPlayerMP) event.player);
					}
				} else
					return;
			}
		}
	}
}
