package erebus.events;

import erebus.Erebus;
import erebus.datagen.ModLang;
import erebus.datagen.ModModelProvider;
import erebus.datagen.advancement.AgricultureAdvancements;
import erebus.datagen.advancement.ExplorationAdvancements;
import erebus.datagen.advancement.PortalAdvancements;
import erebus.datagen.providers.ModLootTableProvider;
import erebus.datagen.providers.ModRecipeProvider;
import erebus.datagen.tags.ModBiomeTagsData;
import erebus.datagen.tags.ModBlockTagsData;
import erebus.datagen.tags.ModEntityTypeTagsData;
import erebus.datagen.tags.ModItemTagsData;
import net.minecraft.data.advancements.AdvancementProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;

@EventBusSubscriber(modid = Erebus.MODID)
public class GatherDataEventHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
        event.createProvider(ModLang::new);

        //Server

        event.createProvider((output, lookupProvider) -> new AdvancementProvider(
                output, lookupProvider,
                List.of(
                        new AgricultureAdvancements(),
                        new ExplorationAdvancements(),
                        new PortalAdvancements()
                )
        ));
        event.createProvider(ModLootTableProvider::new);
        event.createProvider(ModRecipeProvider.Runner::new);
        event.createProvider(ModBlockTagsData::new);
        event.createProvider(ModBiomeTagsData::new);
        event.createProvider(ModEntityTypeTagsData::new);
        event.createProvider(ModItemTagsData::new);
    }

    @SubscribeEvent
    public static void gatherDataServer(GatherDataEvent.Server event) {
    }
}
