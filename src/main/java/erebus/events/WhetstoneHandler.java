package erebus.events;

import erebus.Erebus;
import erebus.item.WhetstoneItem;
import erebus.registries.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class WhetstoneHandler {
    @SubscribeEvent
    public static void anvil(AnvilUpdateEvent event) {
        var stone = event.getRight();
        if (!stone.is(ModItems.WHETSTONE)) return;
        int level = WhetstoneItem.level(stone);
        if (level == 0) return;
        var input = event.getLeft();
        var sharpness = event.getPlayer().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS);
        if (!sharpness.value().canEnchant(input) || EnchantmentHelper.getItemEnchantmentLevel(sharpness, input) >= level) return;
        for (var existing : input.getEnchantments().keySet())
            if (!existing.equals(sharpness) && !Enchantment.areCompatible(existing, sharpness)) return;
        var result = input.copyWithCount(1);
        EnchantmentHelper.updateEnchantments(result, enchantments -> enchantments.set(sharpness, level));
        String name = event.getName();
        if (name != null) {
            if (name.isBlank()) result.remove(DataComponents.CUSTOM_NAME);
            else if (!name.equals(input.getHoverName().getString())) result.set(DataComponents.CUSTOM_NAME, Component.literal(name));
        }
        event.setOutput(result);
        event.setXpCost(5);
        event.setMaterialCost(1);
    }

    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        if (!event.getItemStack().is(ModItems.WHETSTONE)) return;
        int level = WhetstoneItem.level(event.getItemStack());
        event.getToolTip().add((level > 0 ? Component.translatable("tooltip.erebus.whetstone_sharpness", level)
                : Component.translatable("tooltip.erebus.whetstone_2")).withStyle(ChatFormatting.LIGHT_PURPLE));
        event.getToolTip().add(Component.translatable("tooltip.erebus.whetstone_" + (level > 0 ? "1" : "3")).withStyle(ChatFormatting.WHITE));
    }
}
