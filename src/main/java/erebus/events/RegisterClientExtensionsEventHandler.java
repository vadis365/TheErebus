package erebus.events;

import erebus.Erebus;
import erebus.client.MaxSpeedBowExtensions;
import erebus.registries.item.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public class RegisterClientExtensionsEventHandler {

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new MaxSpeedBowExtensions(), ModItems.MAX_SPEED_BOW);
    }
}
