package erebus.events;

import erebus.Erebus;
import erebus.client.screen.PetrifiedCraftingTableScreen;
import erebus.registries.ModMenuTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class RegisterMenuScreensEventHandler {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.PETRIFIED_CRAFTING_MENU.get(), PetrifiedCraftingTableScreen::new);
    }
}
