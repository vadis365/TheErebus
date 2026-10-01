package erebus.events;

import erebus.Erebus;
import erebus.registries.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class MushroomHelmetTooltips {
    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        if (event.getItemStack().is(ModItems.MUSHROOM_HELMET))
            event.getToolTip().add(Component.translatable("tooltip.erebus.mush_helm").withStyle(ChatFormatting.YELLOW));
    }
}
