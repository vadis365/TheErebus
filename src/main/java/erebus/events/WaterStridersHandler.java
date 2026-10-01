package erebus.events;

import erebus.Erebus;
import erebus.registries.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class WaterStridersHandler {
    @SubscribeEvent
    public static void landing(LivingFallEvent event) {
        if (event.getEntity() instanceof Player player && player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.WATER_STRIDERS)
                && player.level().getFluidState(BlockPos.containing(player.getX(), player.getY() - 0.01, player.getZ())).is(FluidTags.WATER))
            event.setDistance(0);
    }

    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        if (event.getItemStack().is(ModItems.WATER_STRIDERS))
            event.getToolTip().add(Component.translatable("tooltip.erebus.water_striders").withStyle(ChatFormatting.YELLOW));
    }
}
