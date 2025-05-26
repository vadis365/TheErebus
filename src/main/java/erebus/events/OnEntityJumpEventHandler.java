package erebus.events;

import erebus.Erebus;
import erebus.block.QuicksandBlock;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public class OnEntityJumpEventHandler {

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();

        if(entity.level().getBlockState(entity.blockPosition().below()).is(OtherBlocks.QUICK_SAND)) {
            if(!QuicksandBlock.entityWillSink(entity)) {
                entity.setJumping(false);
            }
        }
    }
}
