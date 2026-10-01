package erebus.events;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class BambooPipeTooltips {
    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        var stack = event.getItemStack();
        String key = stack.is(ModBlocks.BAMBOO_PIPE.asItem()) ? "tooltip.erebus.bamboo_pipe"
                : stack.is(ModBlocks.BAMBOO_PIPE_EXTRACT.asItem()) ? "tooltip.erebus.bamboo_pipe_extract" : null;
        if (key != null) event.getToolTip().add(Component.translatable(key).withStyle(ChatFormatting.YELLOW));
    }
}
