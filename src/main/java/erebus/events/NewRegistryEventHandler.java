package erebus.events;

import erebus.Erebus;
import erebus.datagen.ModRegistries;
import erebus.world.biome.util.ErebusBiomeProvider;
import erebus.world.layer.biome.BiomeDensitySource;
import erebus.world.layer.biome.BiomeLayerStack;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class NewRegistryEventHandler {

    @SubscribeEvent
    public static void createNewRegistries(NewRegistryEvent event) {
        event.register(ModRegistries.BIOME_LAYER_TYPE);
    }

    @SubscribeEvent
    public static void setRegistriesForDataPack(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(ModRegistries.BIOME_STACK, BiomeLayerStack.DISPATCH_CODEC);
        event.dataPackRegistry(ModRegistries.BIOME_TERRAIN_DATA, BiomeDensitySource.CODEC);
    }

    @SubscribeEvent
    public static void registerBiomeSource(RegisterEvent event) {
        if(event.getRegistryKey().equals(Registries.BIOME_SOURCE)) {
            Registry.register(BuiltInRegistries.BIOME_SOURCE, Erebus.prefix("erebus_biomes"), ErebusBiomeProvider.CODEC);
        }
    }
}
