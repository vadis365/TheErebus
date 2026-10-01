package erebus.item;

import erebus.entity.BotFlyLarva;
import erebus.events.BotFlyInfestationHandler;
import erebus.registries.ModFluids;
import erebus.registries.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class BeetleJuiceBucketItem extends BucketItem {
    private static final int DRINK_DURATION = 32;

    public BeetleJuiceBucketItem(Fluid content, Properties properties) {
        super(content, properties);
    }

    @Override
    public @Nonnull InteractionResult use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
        var result = super.use(level, player, hand);
        return result == InteractionResult.PASS ? ItemUtils.startUsingInstantly(level, player, hand) : result;
    }

    public Entity getParasite(Entity entityIn) {
        if (entityIn instanceof Player player) return BotFlyInfestationHandler.getLarva(player);
        for (Entity entity : entityIn.getPassengers())
            if (entity instanceof BotFlyLarva)
                return entity;
        return null;
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack stack, @NonNull Level level, @NonNull LivingEntity entityLiving) {
        super.finishUsingItem(stack, level, entityLiving);

        if (entityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide()) {
            entityLiving.removeAllEffects();
            if (entityLiving instanceof Player player && BotFlyInfestationHandler.count(player) > 0) {
                BotFlyInfestationHandler.ensureVisual(player);
                var larva = BotFlyInfestationHandler.getLarva(player);
                if (larva != null) larva.setABitDead();
                else BotFlyInfestationHandler.setCount(player, BotFlyInfestationHandler.count(player) - 1);
            } else if (entityLiving.isVehicle() && getParasite(entityLiving) != null)
                if (((BotFlyLarva) getParasite(entityLiving)).getParasiteCount() > 0)
                    ((BotFlyLarva) getParasite(entityLiving)).setABitDead();
        }

        if (entityLiving instanceof Player player)
            return player.hasInfiniteMaterials() ? stack : ItemUtils.createFilledResult(stack, player, new ItemStack(ModItems.BAMBUCKET.get()), false);
        else {
            stack.consume(1, entityLiving);
            return stack;
        }
    }

    @Override
    public int getUseDuration(@NonNull ItemStack stack, @NonNull LivingEntity entity) {
        if (containsBeetleJuice(stack))
            return DRINK_DURATION;
        return super.getUseDuration(stack, entity);
    }

    @Override
    public @NonNull ItemUseAnimation getUseAnimation(@NonNull ItemStack stack) {
        if (containsBeetleJuice(stack))
            return ItemUseAnimation.DRINK;
        return super.getUseAnimation(stack);
    }

    public boolean containsBeetleJuice(ItemStack stack) {
        return FluidUtil.getFirstStackContained(stack).is(ModFluids.BEETLE_JUICE_STILL.get());
    }

}
