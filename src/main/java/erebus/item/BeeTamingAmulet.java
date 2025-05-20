package erebus.item;

import java.util.List;

import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BeeTamingAmulet extends Item {
	public BeeTamingAmulet(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("tooltip.erebus.beetamingamulet_1").withStyle(ChatFormatting.YELLOW));
		tooltip.add(Component.translatable("tooltip.erebus.beetamingamulet_2").withStyle(ChatFormatting.YELLOW));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		if (!level.isClientSide() && player != null) {
			BlockPos pos = context.getClickedPos();
			BlockState state = level.getBlockState(pos);
			if (state != null && state.getBlock() == OtherBlocks.HONEY_COMB.get()) {
/*
 			TODO Apply ItemStackData components here 
				stack.getTagCompound().setInteger("homeX", pos.getX());
				stack.getTagCompound().setInteger("homeY", pos.getY());
				stack.getTagCompound().setInteger("homeZ", pos.getZ());
*/
				System.out.println("Clicky on Honeycomb");
				return InteractionResult.SUCCESS;
				
			}
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}