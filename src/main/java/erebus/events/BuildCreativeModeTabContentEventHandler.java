package erebus.events;

import erebus.Erebus;
import erebus.registries.ModEntities;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class BuildCreativeModeTabContentEventHandler {

    @SubscribeEvent
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            for (DeferredHolder<Item, ?> item : ModEntities.SPAWN_EGGS.getEntries()) {
                if (item.get() instanceof SpawnEggItem) {
                    event.accept(item.get());
                }
            }
        }
    }
}
