package erebus.item;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jspecify.annotations.NonNull;

public class MaxSpeedBowItem extends BowItem {
    public static final float DRAW_SPEED = 5.0F;

    public MaxSpeedBowItem() {
        super(new Properties().durability(500).rarity(Rarity.RARE)
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("max_speed_bow"))));
    }

    public static float getPowerForTime(int charge) {
        float power = Math.max(0, charge) / DRAW_SPEED;
        return Math.min(1, (power * power + power * 2) / 4) * 1.5F;
    }

    @Override
    public void onUseTick(@NonNull Level level, @NonNull LivingEntity shooter, @NonNull ItemStack stack, int remaining) {
        if (getUseDuration(stack, shooter) - remaining >= DRAW_SPEED) {
            // Native release owns firing; stopUsingItem is also called on cancellation and must not fire.
            shooter.releaseUsingItem();
        }
    }

    @Override
    public boolean releaseUsing(@NonNull ItemStack bow, @NonNull Level level, @NonNull LivingEntity shooter, int remaining) {
        if (!(shooter instanceof Player player)) return false;
        var ammo = player.getProjectile(bow);
        int charge = EventHooks.onArrowLoose(bow, level, player, getUseDuration(bow, player) - remaining, !ammo.isEmpty());
        if (charge < 0 || ammo.isEmpty()) return false;
        float power = getPowerForTime(charge);
        if (power < 0.1F) return false;
        if (level instanceof ServerLevel server) {
            var projectiles = draw(bow, ammo, player);
            if (projectiles.isEmpty()) return false;
            shoot(server, player, player.getUsedItemHand(), bow, projectiles, power * 3, 1, power == 1, null);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS,
                    1, 1 / (player.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return true;
    }

    @Override
    public boolean isFoil(@NonNull ItemStack stack) {
        return true;
    }
}
