package erebus.events;

import erebus.Erebus;
import erebus.registries.ModBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class RegisterColorHandlersEventHandler {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        BlockColor color = (state, tint, pos, tintIndex) -> {
            if(tint != null && pos != null) {
                return BiomeColors.getAverageFoliageColor(tint, pos);
            } else {
                return GrassColor.getDefaultColor();
            }
        };

        event.register(color, ModBlocks.FERN.get(), ModBlocks.FIDDLE_HEAD.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {

    }
}
