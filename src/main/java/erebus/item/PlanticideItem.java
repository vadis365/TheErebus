package erebus.item;

import erebus.Erebus;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class PlanticideItem extends Item {
    public PlanticideItem() {
        super(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("planticide"))));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> lines, TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.planticide").withStyle(ChatFormatting.RED));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        if (player == null || !level.mayInteract(player, pos)
                || !player.mayUseItemAt(pos, context.getClickedFace(), stack)) return InteractionResult.FAIL;
        if (!level.isClientSide()) {
            for (int i = -2; i <= 2; i++)
                for (int j = -2; j <= 2; j++)
                    for (int k = -2; k <= 2; k++) {
                        BlockPos target = pos.offset(i, j, k);
                        if (!level.hasChunkAt(target) || !level.mayInteract(player, target)
                                || !player.mayUseItemAt(target, context.getClickedFace(), stack)) continue;
                        BlockState state = level.getBlockState(target);
                        if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.PODZOL)
                                || state.is(Blocks.MYCELIUM) || state.is(Tags.Blocks.VILLAGER_FARMLANDS)) {
                            level.levelEvent(2001, target, Block.getId(state));
                            level.setBlock(target, Blocks.COARSE_DIRT.defaultBlockState(), 3);
                        } else if (state.is(BlockTags.LEAVES) || state.is(BlockTags.FLOWERS)
                                || state.getBlock() instanceof VegetationBlock || state.getBlock() instanceof BonemealableBlock
                                || state.getBlock() instanceof CactusBlock || state.getBlock() instanceof SugarCaneBlock)
                            level.destroyBlock(target, false, player);
                    }

            if (!player.isCreative())
                stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

}
