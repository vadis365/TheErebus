package erebus.item;

import erebus.events.AntiVenomHandler;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import org.jspecify.annotations.NonNull;

public class AntiVenomBucketItem extends BucketItem {
    public AntiVenomBucketItem(Fluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity user) {
        if (user instanceof Player player) AntiVenomHandler.drink(player, AntiVenomHandler.MAX_SECONDS);
        return super.finishUsingItem(stack, level, user);
    }
}
