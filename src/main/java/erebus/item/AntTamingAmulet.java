package erebus.item;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class AntTamingAmulet extends Item {
    public AntTamingAmulet() {
        super(new Item.Properties().stacksTo(1).durability(16).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("ant_taming_amulet"))));
    }

    @Override
    public void appendHoverText(ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> lines, @NonNull TooltipFlag flag) {
        BlockPos target = stack.get(ModDataComponents.ANT_TAMING_AMULET);
        if (target != null) {
            lines.accept(Component.translatable("tooltip.erebus.silo_x", target.getX()).withStyle(ChatFormatting.YELLOW));
            lines.accept(Component.translatable("tooltip.erebus.silo_y", target.getY()).withStyle(ChatFormatting.YELLOW));
            lines.accept(Component.translatable("tooltip.erebus.silo_z", target.getZ()).withStyle(ChatFormatting.YELLOW));
        } else {
            lines.accept(Component.translatable("tooltip.erebus.ant_taming_amulet_1").withStyle(ChatFormatting.YELLOW));
            lines.accept(Component.translatable("tooltip.erebus.ant_taming_amulet_2").withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        if (player == null || !level.getBlockState(context.getClickedPos()).is(ModBlocks.SILO_TANK)) return InteractionResult.PASS;
        if (!level.isClientSide()) {
            stack.set(ModDataComponents.ANT_TAMING_AMULET, context.getClickedPos().immutable());
            stack.hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
        }
        return InteractionResult.SUCCESS;
    }
}
