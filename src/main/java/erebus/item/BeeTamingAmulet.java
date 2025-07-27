package erebus.item;

import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class BeeTamingAmulet extends Item {
	public BeeTamingAmulet(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		if (stack.has(ModDataComponents.BEE_TAMING_AMULET)) {
			BlockPos dataBlockPos = stack.getComponents().get(ModDataComponents.BEE_TAMING_AMULET.get());
			tooltip.add(Component.translatable("tooltip.erebus.honeycomb_x", dataBlockPos.getX()).withStyle(ChatFormatting.YELLOW));
			tooltip.add(Component.translatable("tooltip.erebus.honeycomb_y", dataBlockPos.getY()).withStyle(ChatFormatting.YELLOW));
			tooltip.add(Component.translatable("tooltip.erebus.honeycomb_z", dataBlockPos.getZ()).withStyle(ChatFormatting.YELLOW));
		} else {
			tooltip.add(Component.translatable("tooltip.erebus.bee_taming_amulet_1").withStyle(ChatFormatting.YELLOW));
			tooltip.add(Component.translatable("tooltip.erebus.bee_taming_amulet_2").withStyle(ChatFormatting.YELLOW));
		}
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		ItemStack stack = context.getItemInHand();
		if (!level.isClientSide() && player != null) {
			BlockPos pos = context.getClickedPos();
			BlockState state = level.getBlockState(pos);
			if (state != null && state.getBlock() == OtherBlocks.HONEY_COMB.get()) {
				stack.set(ModDataComponents.BEE_TAMING_AMULET, pos);
				stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}