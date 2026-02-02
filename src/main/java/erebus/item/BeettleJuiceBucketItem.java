package erebus.item;

import erebus.entity.BotFlyLarva;
import erebus.registries.ModFluids;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class BeettleJuiceBucketItem extends BucketItem {
	private static final int DRINK_DURATION = 32;

	public BeettleJuiceBucketItem(Fluid content, Properties properties) {
		super(content, properties);
	}

	@Override
	@Nonnull
	public InteractionResult use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (super.use(level, player, hand) == InteractionResult.PASS) {
			if (containsBeetleJuice(stack))
				return ItemUtils.startUsingInstantly(level, player, hand);
		} else if (super.use(level, player, hand) == InteractionResult.CONSUME) {
            return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	public Entity getParasite(Entity entityIn) {
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
			if (entityLiving.isVehicle() && getParasite(entityLiving) != null)
				if (((BotFlyLarva) getParasite(entityLiving)).getParasiteCount() > 0)
					((BotFlyLarva) getParasite(entityLiving)).setABitDead();
		}

		if (entityLiving instanceof Player player) 
			return ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET), false);
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
