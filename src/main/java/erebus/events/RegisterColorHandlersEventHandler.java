package erebus.events;
    
import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.client.color.block.BlockTintSources;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.List;

@EventBusSubscriber(modid = Erebus.MODID)
public class RegisterColorHandlersEventHandler {
    
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BlockTintSources.foliage()),
                ModBlocks.FERN.get(),
                ModBlocks.FIDDLE_HEAD.get(),
                ModBlocks.TALL_FERN.get()
        );
    
        event.register(List.of(BlockTintSources.grassBlock()), ModBlocks.SWAMP_VENT.get());
    }
}
