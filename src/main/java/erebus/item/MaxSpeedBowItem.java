package erebus.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MaxSpeedBowItem extends BowItem {
    public MaxSpeedBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int timeLeft) {
        if(entity instanceof Player player) {
            ItemStack projectile = player.getProjectile(stack);
            if(!projectile.isEmpty()) {
                int duration = getUseDuration(stack, entity) - timeLeft;
                duration = EventHooks.onArrowLoose(stack, level, player, duration, !stack.isEmpty());

                if(duration < 0) return;

                List<ItemStack> ammo = draw(stack, projectile, player);
                if(level instanceof ServerLevel server && !ammo.isEmpty()) {
                    shoot(server, player, player.getUsedItemHand(), stack, ammo, 3.0F, 1.0F, true, null);
                }

                level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.ARROW_SHOOT,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 1 * 0.5F
                );

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return 5000;
    }
}
