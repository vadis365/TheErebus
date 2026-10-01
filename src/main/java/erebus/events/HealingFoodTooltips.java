package erebus.events;

import erebus.Erebus;
import erebus.registries.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class HealingFoodTooltips {
    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        var stack = event.getItemStack();
        String key = stack.is(ModItems.STAG_HEART_RAW) ? "tooltip.erebus.heals"
                : stack.is(ModItems.STAG_HEART_COOKED) ? "tooltip.erebus.feeds"
                : stack.is(ModItems.HEART_BERRIES) || stack.is(ModItems.LIFE_BLOOD) ? "tooltip.erebus.healinghearts" : null;
        if (key != null) event.getToolTip().add(Component.translatable(key).withStyle(ChatFormatting.YELLOW));
    }
}
