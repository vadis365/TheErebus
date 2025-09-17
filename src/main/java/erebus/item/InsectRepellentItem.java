package erebus.item;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import erebus.registries.ModSounds;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class InsectRepellentItem extends Item {
    public InsectRepellentItem(Properties properties) {
        super(properties);
    }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("tooltip.erebus.spray_can").withStyle(ChatFormatting.YELLOW));
	}

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();

        if(context.getClickedFace() != Direction.UP) return InteractionResult.FAIL;

        if(player != null)
            if(!player.isCreative())
                stack.shrink(1);

        level.playSound(player, clickedPos, ModSounds.SPRAY_CAN_SOUND.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

        if(level.getBlockState(clickedPos.above()).isAir()) {
            level.setBlock(clickedPos.above(), OtherBlocks.INSECT_REPELLENT.get().defaultBlockState(), Block.UPDATE_ALL);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }
}
