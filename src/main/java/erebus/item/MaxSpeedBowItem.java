package erebus.item;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class MaxSpeedBowItem extends ProjectileWeaponItem {

    public static final float DRAW_SPEED = 20.0F;

    public MaxSpeedBowItem() {
        super(new Item.Properties()
                .durability(500)
                .rarity(Rarity.RARE)
                .setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("max_speed_bow"))));
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return ProjectileWeaponItem.ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    protected void shootProjectile(@NotNull LivingEntity shooter, @NotNull Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + angle, 0.0F, velocity, inaccuracy);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean hasProjectile = !player.getProjectile(itemstack).isEmpty();
        return EventHooks.onArrowNock(itemstack, level, player, hand, hasProjectile);
    }

    @Override
    public void onUseTick(@NotNull Level level, @NotNull LivingEntity shooter, @NotNull ItemStack stack, int remainingUseDuration) {
        int maxUsageTicks = 4;
        int elapsedTicks = 72000 - remainingUseDuration;
        if(elapsedTicks > maxUsageTicks) {
            shooter.stopUsingItem();
        }
    }

    @Override
    public void onStopUsing(@NotNull ItemStack bow, @NotNull LivingEntity shooter, int timeLeft) {
        if (shooter instanceof Player player) {
            Level level = player.level();
            ItemStack itemstack = player.getProjectile(bow);
            if (!itemstack.isEmpty()) {
                int chargeTicks = getUseDuration(bow, shooter) - timeLeft;
                chargeTicks = EventHooks.onArrowLoose(bow, level, player, chargeTicks, !itemstack.isEmpty());
                if (chargeTicks < 0) {
                    return;
                }

                float bowPower = getPowerForTime(chargeTicks);
                if (!((double)bowPower < 0.1)) {
                    List<ItemStack> list = draw(bow, itemstack, player);
                    if (level instanceof ServerLevel serverLevel) {
                        if (!list.isEmpty()) {
                            shoot(serverLevel, player, player.getUsedItemHand(), bow, list, bowPower * 3.0F, 1.0F, bowPower == 1.0F, null);
                        }
                    }

                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + bowPower * 0.5F);
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public static float getPowerForTime(int charge) {
        float powerFactor = (float)charge / (DRAW_SPEED / 2);
        powerFactor = (powerFactor * powerFactor + powerFactor * 2.0F) / 4.0F;
        if (powerFactor > 1.0F) {
            powerFactor = 1.0F;
        }

        return powerFactor;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return 72000;
    }

    @Override
    public @NotNull ItemUseAnimation getUseAnimation(@NotNull ItemStack stack) {
        return ItemUseAnimation.BOW;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
}
