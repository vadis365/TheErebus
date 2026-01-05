package erebus.item;

import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class BeeTamingAmulet extends Item {
	public BeeTamingAmulet() {
		super(new Item.Properties().stacksTo(1).durability(16));
	}

	@Override
	public @NonNull Component getHighlightTip(ItemStack item, @NonNull Component displayName) {
		if(item.has(ModDataComponents.BEE_TAMING_AMULET)) {
			BlockPos dataBlockPos = item.getComponents().get(ModDataComponents.ANT_TAMING_AMULET.get());
			return Component.empty()
					.append(Component.translatable("tooltip.erebus.honeycomb_x", dataBlockPos.getX()).withStyle(ChatFormatting.YELLOW))
					.append(Component.translatable("tooltip.erebus.honeycomb_y", dataBlockPos.getY()).withStyle(ChatFormatting.YELLOW))
					.append(Component.translatable("tooltip.erebus.honeycomb_z", dataBlockPos.getZ()).withStyle(ChatFormatting.YELLOW));
		} else {
			return Component.empty()
					.append(Component.translatable("tooltip.erebus.bee_taming_amulet_1").withStyle(ChatFormatting.YELLOW))
					.append(Component.translatable("tooltip.erebus.bee_taming_amulet_2").withStyle(ChatFormatting.YELLOW));
		}
	}

	@Override
	public @NonNull InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		ItemStack stack = context.getItemInHand();
		if (!level.isClientSide() && player != null) {
			BlockPos pos = context.getClickedPos();
			BlockState state = level.getBlockState(pos);
			if (state.getBlock() == ModBlocks.HONEY_COMB.get()) {
				stack.set(ModDataComponents.BEE_TAMING_AMULET, pos);
				stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
				return InteractionResult.SUCCESS;
			}
		}
		return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
	}
}
