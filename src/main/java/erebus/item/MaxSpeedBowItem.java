package erebus.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MaxSpeedBowItem extends BowItem {
    public MaxSpeedBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onUseTick(@NotNull Level level, @NotNull LivingEntity player, @NotNull ItemStack stack, int remainingUseDuration) {
        if(72000 -remainingUseDuration > 4) player.stopUsingItem();
    }

    @Override
    public void onStopUsing(@NotNull ItemStack stack, @NotNull LivingEntity entity, int count) {
        float pullSpeedModifier = 5;
        float speedModifier = 1.5F;
        int minRelease = 0;

        if(entity instanceof Player player) {


        }
    }
}
