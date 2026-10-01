package erebus.item;

import erebus.events.AntiVenomHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class AntiVenomBottleItem extends Item {
    public AntiVenomBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity user) {
        if (user instanceof Player player) AntiVenomHandler.drink(player, 60);
        return super.finishUsingItem(stack, level, user);
    }
}
