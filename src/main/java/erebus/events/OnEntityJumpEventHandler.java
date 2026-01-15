package erebus.events;

import erebus.registries.blocks.ModBlocks;
import erebus.block.QuicksandBlock;
import erebus.registries.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public class OnEntityJumpEventHandler {

    @SubscribeEvent
    public void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();

        if(entity.level().getBlockState(entity.blockPosition().below()).is(ModBlocks.QUICK_SAND)) {
            if(!QuicksandBlock.entityWillSink(entity)) {
                entity.setJumping(false);
            }
        }
        
        if (entity instanceof Player player) {
        	ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
			if (!stack.isEmpty() && stack.is(ModItems.JUMP_BOOTS.get()) && !player.isCrouching())
				player.setDeltaMovement(player.getDeltaMovement().add(0D, 0.4D, 0D));
		}
    }
}
