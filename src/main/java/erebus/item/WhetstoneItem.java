package erebus.item;

import erebus.Erebus;
import erebus.registries.data.ModDataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public final class WhetstoneItem extends Item {
    public WhetstoneItem() {
        super(new Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("whetstone"))));
    }

    public static int level(ItemStack stack) {
        return Math.clamp(stack.getOrDefault(ModDataComponents.WHETSTONE_LEVEL, 0), 0, 5);
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) {
        return level(stack) > 0 || super.isFoil(stack);
    }
}
