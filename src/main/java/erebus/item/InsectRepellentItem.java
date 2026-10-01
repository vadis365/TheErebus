package erebus.item;

import erebus.Erebus;
import erebus.registries.ModSounds;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class InsectRepellentItem extends Item {
    public InsectRepellentItem() {
        super(new Item.Properties().stacksTo(9).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("spray_can"))));
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> lines, TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.spray_can").withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        BlockPos support = context.getClickedPos();
        BlockPos pos = support.above();
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        if (player == null || context.getClickedFace() != Direction.UP
                || !level.mayInteract(player, support) || !level.mayInteract(player, pos)
                || !player.mayUseItemAt(support, Direction.UP, stack) || !player.mayUseItemAt(pos, Direction.UP, stack)
                || level.isOutsideBuildHeight(pos) || !level.getWorldBorder().isWithinBounds(pos)
                || !level.getBlockState(support).isFaceSturdy(level, support, Direction.UP)
                || level.getBlockState(support).is(ModBlocks.INSECT_REPELLENT) || !level.getBlockState(pos).isAir())
            return InteractionResult.FAIL;
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (!level.setBlock(pos, ModBlocks.INSECT_REPELLENT.get().defaultBlockState(), Block.UPDATE_ALL)) return InteractionResult.FAIL;
        stack.consume(1, player);
        player.awardStat(Stats.ITEM_USED.get(this));
        level.playSound(null, pos, ModSounds.SPRAY_CAN_SOUND.get(), SoundSource.BLOCKS, 1, 1);
        return InteractionResult.SUCCESS_SERVER;
    }
}
