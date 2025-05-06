package erebus.item;

import javax.annotation.Nonnull;

import erebus.Erebus;
import erebus.entity.BotFlyLarva;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
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

public class BeettleJuiceBucketItem extends BucketItem {
	public static final ResourceLocation BEETLE_JUICE_LOCATION = Erebus.prefix("beetle_juice_still");
	private static final int DRINK_DURATION = 32;

	public BeettleJuiceBucketItem(Fluid content, Properties properties) {
		super(content, properties);
	}

	@Override
	@Nonnull
	public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		//if (containsBeetleJuice(itemstack))
			return ItemUtils.startUsingInstantly(level, player, hand);
	//	return super.use(level, player, hand);
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
		//if (containsBeetleJuice(stack))
			return DRINK_DURATION;
		//return super.getUseDuration(stack, entity);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		//if (containsBeetleJuice(stack))
			return UseAnim.DRINK;
	//	return super.getUseAnimation(stack);
	}
/*
	public static boolean containsBeetleJuice(ItemStack itemStack) {
		ResourceLocation bucketContent = BucketLibUtil.getContent(itemStack);
		System.out.println("Contents: " + bucketContent.toString());
		if (bucketContent != null && bucketContent.equals(BEETLE_JUICE_LOCATION))
			return true;
		return false;
	}
*/
}
