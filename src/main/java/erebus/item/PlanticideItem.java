package erebus.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.Tags;

public class PlanticideItem extends Item {

	public PlanticideItem(Properties properties) {
		super(properties);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("tooltip.erebus.planticide").withStyle(ChatFormatting.RED));
	}

	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		Player player = context.getPlayer();
		ItemStack stack = context.getItemInHand();
		BlockPos pos = context.getClickedPos();
		if (!level.isClientSide()) {
			for (int i = -2; i <= 2; i++)
				for (int j = -2; j <= 2; j++)
					for (int k = -2; k <= 2; k++) {
						BlockState state = level.getBlockState(pos.offset(i, j, k));
						if (state.is(BlockTags.REPLACEABLE_BY_TREES) && !state.is(Blocks.WATER) || state.is(BlockTags.FLOWERS))
							level.destroyBlock(pos.offset(i, j, k), false, player);
						else if (state.is(BlockTags.DIRT) || state.is(Tags.Blocks.VILLAGER_FARMLANDS)) {
							level.setBlock(pos.offset(i, j, k), Blocks.COARSE_DIRT.defaultBlockState(), 3);
						} else if (state.getBlock() instanceof CropBlock || state.getBlock() instanceof BonemealableBlock)
							level.destroyBlock(pos.offset(i, j, k), false, player);
					}

			if (!player.isCreative())
				stack.shrink(1);
			return InteractionResult.SUCCESS;
		} else
			return InteractionResult.PASS;
	}

}