package erebus.events;

import erebus.item.MaxSpeedBowItem;
import erebus.registries.item.ModItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ComputeFOVModifierEventHandler {

    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.MAX_SPEED_BOW.get()) {
            float fovModifier = 1;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = ticksUsingItem / MaxSpeedBowItem.DRAW_SPEED;

            if(deltaTicks > 1.0F) {
                deltaTicks = 1.0F;
            } else {
                deltaTicks *= deltaTicks;
            }

            fovModifier *= 1.0F - deltaTicks * 0.15F;
            event.setNewFovModifier(fovModifier);
        }
    }

}
