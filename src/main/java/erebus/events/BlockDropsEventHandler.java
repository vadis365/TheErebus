package erebus.events;

import erebus.Erebus;
import erebus.block.entity.PreservedBlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public class BlockDropsEventHandler {

    @SubscribeEvent
    public static void onBlockDrops(BlockDropsEvent event) {
        if(event.getBlockEntity() instanceof PreservedBlockEntity preservedBlock) {
            event.getLevel().addFreshEntity(preservedBlock.getTrappedEntity());
        }
    }
}
