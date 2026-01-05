package erebus.events;

import erebus.Erebus;
import erebus.block.BlockOfBonesBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.network.data.DeathCompassData;
import erebus.network.data.DeathCompassDataHolder;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

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
        BlockPos pos = event.getEntity().getLastDeathLocation().get().pos();

        stack.update(
                ModDataComponents.DEATH_COMPASS,
                DeathCompassDataHolder.DEFAULT,
                data -> new DeathCompassData(pos.getX(), pos.getY(), pos.getZ())
        );

       // event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, stack);
		if (!event.getEntity().getInventory().add(stack))
			event.getEntity().drop(stack, false);
    }
}
