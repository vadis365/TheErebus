package erebus.events;

import erebus.Erebus;
import erebus.datagen.ModBlockStates;
import erebus.datagen.ModItemModels;
import erebus.datagen.ModLang;
import erebus.datagen.ModRegistries;
import erebus.datagen.providers.ModAdvancementProvider;
import erebus.datagen.providers.ModLootTableProvider;
import erebus.datagen.providers.ModRecipeProvider;
import erebus.datagen.tags.ModBiomeTags;
import erebus.datagen.tags.ModBlockTags;
import erebus.datagen.tags.ModEntityTags;
import erebus.datagen.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class GatherDataEventHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        DatapackBuiltinEntriesProvider datapackProvider = new ModRegistries(output, event.getLookupProvider());
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();

        generator.addProvider(event.includeClient(), new ModBlockStates(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModels(output, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBlockTags(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTags(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), new ModLang(output));
        generator.addProvider(event.includeServer(), new ModBiomeTags(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), new ModEntityTags(output, lookupProvider, Erebus.MODID, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModAdvancementProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), datapackProvider);
    }
}
