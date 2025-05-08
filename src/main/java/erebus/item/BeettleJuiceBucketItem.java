package erebus.item;

import javax.annotation.Nonnull;

import erebus.entity.BotFlyLarva;
import erebus.registries.ModFluids;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class BeettleJuiceBucketItem extends BucketItem {
	private static final int DRINK_DURATION = 32;
	public final Fluid content;
	public BeettleJuiceBucketItem(Fluid content, Properties properties) {
		super(content, properties);
		 this.content = content;
	}

	@Override
	@Nonnull
	public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		super.use(level, player, hand);
		if (containsBeetleJuice())
			return ItemUtils.startUsingInstantly(level, player, hand);
		return InteractionResultHolder.pass(stack);
	}

	public Entity getParasite(Entity entityIn) {
		for (Entity entity : entityIn.getPassengers())
			if (entity instanceof BotFlyLarva)
				return entity;
		return null;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
		super.finishUsingItem(stack, level, entityLiving);
		if (entityLiving instanceof ServerPlayer serverplayer) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
			serverplayer.awardStat(Stats.ITEM_USED.get(this));
		}
		if (!level.isClientSide) {
			entityLiving.removeEffectsCuredBy(net.neoforged.neoforge.common.EffectCures.MILK);
			if (entityLiving.isVehicle() && getParasite(entityLiving) != null)
				if (((BotFlyLarva) getParasite(entityLiving)).getParasiteCount() > 0) {
					((BotFlyLarva) getParasite(entityLiving)).setABitDead();
				}
		}

		if (entityLiving instanceof Player player) {
			return ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET), false);
		} else {
			stack.consume(1, entityLiving);
			return stack;
		}
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		if (containsBeetleJuice())
			return DRINK_DURATION;
		return super.getUseDuration(stack, entity);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		if (containsBeetleJuice())
			return UseAnim.DRINK;
		return super.getUseAnimation(stack);
	}

	public boolean containsBeetleJuice() {
		System.out.println("TRYING TO DRINK: " + this.content);
		if (content != Fluids.EMPTY && content == ModFluids.BEETLE_JUICE_STILL.get())
			return true;
		return false;
	}

}
