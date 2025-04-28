package erebus.events;

import com.google.common.collect.Lists;
import erebus.Erebus;
import erebus.block.BlockOfBonesBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.List;
import java.util.function.Consumer;

@EventBusSubscriber(modid = Erebus.MODID)
public class LivingDropsEventHandler {

    @SubscribeEvent
    public static void onPlayerDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof Player player && !event.getEntity().level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            BlockPos pos = player.getOnPos().above();
            Direction facing = player.getDirection();
            Level level = event.getEntity().level();
            level.setBlockAndUpdate(pos, ModBlocks.BLOCK_OF_BONES.get().defaultBlockState().setValue(BlockOfBonesBlock.FACING, facing));

            BlockOfBonesBlockEntity block = (BlockOfBonesBlockEntity) level.getBlockEntity(pos);
            if (block != null) {
                block.setDrops(event.getDrops());
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        ItemStack stack = new ItemStack(ModItems.DEATH_COMPASS.get());
        List<Component> list = Lists.newArrayList();
        list.add(Component.translatable("tooltip.jade.crop_mature", event.getEntity().getLastDeathLocation().get().dimension().toString()).withStyle(ChatFormatting.YELLOW));
        list.add(Component.translatable("tooltip.erebus.homeX", event.getEntity().getLastDeathLocation().get().pos().getX()).withStyle(ChatFormatting.YELLOW));
        list.add(Component.translatable("tooltip.erebus.homeZ", event.getEntity().getLastDeathLocation().get().pos().getZ()).withStyle(ChatFormatting.YELLOW));

        Consumer<Component> consumer = list::add;
        stack.addToTooltip(
                DataComponents.LORE,
                Item.TooltipContext.of(event.getEntity().level()),
                consumer,
                new TooltipFlag() {
                    @Override
                    public boolean isAdvanced() {
                        return false;
                    }

                    @Override
                    public boolean isCreative() {
                        return false;
                    }
                }
        );

        event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, stack);
    }
}
