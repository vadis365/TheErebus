package erebus.events;

import de.cech12.bucketlib.api.BucketLibApi;
import erebus.Erebus;
import erebus.registries.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class RegisterCapabilitiesEventHandler {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        BucketLibApi.registerBucket(event, ModItems.BAMBUCKET.getId());
    }
}
