package erebus.events;

import erebus.Erebus;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Erebus.MODID)
public class BuildCreativeModeTabContentEventHandler {

    /*@SubscribeEvent
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            for (DeferredHolder<Item, ?> item : ModEntities.SPAWN_EGGS.getEntries()) {
                if (item.get() instanceof SpawnEggItem) {
                    event.accept(item.get());
                }
            }
        }
    }*/
}
