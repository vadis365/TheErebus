package erebus.events;

import erebus.Erebus;
import erebus.registries.ModBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class RegisterColorHandlersEventHandler {

    private static BlockColor foliageColor = (state, tint, pos, tintIndex) -> {
        if(tint != null && pos != null) {
            return BiomeColors.getAverageFoliageColor(tint, pos);
        } else {
            return GrassColor.getDefaultColor();
        }
    };

    private static ItemColor itemFoliageColor = (itemStack, i) -> GrassColor.getDefaultColor();

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(foliageColor,
                ModBlocks.FERN.get(),
                ModBlocks.FIDDLE_HEAD.get(),
                ModBlocks.TALL_FERN.get()
        );
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(itemFoliageColor,
                ModBlocks.FERN.get(),
                ModBlocks.FIDDLE_HEAD.get(),
                ModBlocks.TALL_FERN.get()
        );
    }
}
