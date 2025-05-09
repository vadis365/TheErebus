package erebus.events;

import erebus.Erebus;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.PlantBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class RegisterColorHandlersEventHandler {

    private static final BlockColor foliageColor = (state, tint, pos, tintIndex) -> {
        if(tint != null && pos != null) {
            return BiomeColors.getAverageFoliageColor(tint, pos);
        } else {
            return GrassColor.getDefaultColor();
        }
    };

    private static final BlockColor grassColor = (state, tint, pos, tintIndex) -> {
        if (tint != null && pos != null) {
            return BiomeColors.getAverageGrassColor(tint, pos);
        } else {
            return GrassColor.getDefaultColor();
        }
    };

    private static final ItemColor itemFoliageColor = (itemStack, i) -> GrassColor.getDefaultColor();

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(foliageColor,
                PlantBlocks.FERN.get(),
                PlantBlocks.FIDDLE_HEAD.get(),
                PlantBlocks.TALL_FERN.get()
        );

        event.register(grassColor, OtherBlocks.SWAMP_VENT.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(itemFoliageColor,
                PlantBlocks.FERN.get(),
                PlantBlocks.FIDDLE_HEAD.get(),
                PlantBlocks.TALL_FERN.get(),
                OtherBlocks.SWAMP_VENT.get()
        );
    }
}
