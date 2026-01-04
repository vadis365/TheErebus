package erebus.item;

import erebus.network.data.DeathCompassData;
import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class DeathCompass extends Item {
    public DeathCompass() {
        super(new Item.Properties());
    }

    @Override
    public @NonNull Component getHighlightTip(@NonNull ItemStack item, @NonNull Component displayName) {
        DeathCompassData data = item.getComponents().get(ModDataComponents.DEATH_COMPASS.get());
        if (data != null) {
            return Component
                    .translatable("tooltip.death_compass.pos")
                    .append("%d, %d, %d".formatted(data.x(), data.y(), data.z()))
                    .withStyle(ChatFormatting.YELLOW);
        }

        return Component.empty();
    }
}
