package erebus.events;

import erebus.Config;
import erebus.Erebus;
import erebus.entity.Scorpion;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityMountEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class ScorpionCaptureEvents {
    private ScorpionCaptureEvents() {
    }

    @SubscribeEvent
    public static void onDismount(EntityMountEvent event) {
        // Block the sneak escape only. Death/removal and explicit non-sneak dismounts remain valid.
        if (Config.scorpionGrab && event.isDismounting()
                && event.getEntityBeingMounted() instanceof Scorpion scorpion && scorpion.isAlive()
                && event.getEntityMounting() instanceof Player player && player.isAlive()
                && !player.isCreative() && !player.isSpectator() && player.isShiftKeyDown()) {
            player.setShiftKeyDown(false);
            event.setCanceled(true);
        }
    }
}
