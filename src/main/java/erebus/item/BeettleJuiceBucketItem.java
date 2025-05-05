package erebus.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

public class BeettleJuiceBucketItem  extends BucketItem {
	private static final int DRINK_DURATION = 32;
	public BeettleJuiceBucketItem(Fluid content, Properties properties) {
		super(content, properties);
	}
	
	 @Override
	    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
	        if (entityLiving instanceof ServerPlayer serverplayer) {
	            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
	            serverplayer.awardStat(Stats.ITEM_USED.get(this));
	        }

	        if (!level.isClientSide) {
	            entityLiving.removeEffectsCuredBy(net.neoforged.neoforge.common.EffectCures.MILK);
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
	        return DRINK_DURATION;
	    }

	    @Override
	    public UseAnim getUseAnimation(ItemStack stack) {
	        return UseAnim.DRINK;
	    }

	    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
	    	super.use(level, player, hand);
	        return ItemUtils.startUsingInstantly(level, player, hand);
	    }

}
