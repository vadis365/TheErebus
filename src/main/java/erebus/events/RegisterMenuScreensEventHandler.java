package erebus.events;

import erebus.Erebus;
import erebus.inventory.client.*;
import erebus.registries.client.ModMenuTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class RegisterMenuScreensEventHandler {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.PETRIFIED_CRAFTING_MENU.get(), PetrifiedCraftingTableScreen::new);
        event.register(ModMenuTypes.UMBER_FURNACE_MENU.get(), UmberFurnaceScreen::new);
        event.register(ModMenuTypes.LIQUIFIER.get(), LiquifierScreen::new);
        event.register(ModMenuTypes.HONEY_COMB.get(), HoneyCombScreen::new);
        event.register(ModMenuTypes.BAMBOO_EXTENDER.get(), BambooExtenderScreen::new);
        event.register(ModMenuTypes.BAMBOO_CRATE.get(), BambooCrateScreen::new);
        event.register(ModMenuTypes.COLOSSAL_CRATE.get(), ColossalCrateScreen::new);
        event.register(ModMenuTypes.COMPOSTER.get(), ComposterScreen::new);
        event.register(ModMenuTypes.SILO_TANK.get(), SiloTankScreen::new);
        event.register(ModMenuTypes.BLENDER.get(), BlenderScreen::new);
    }
}
