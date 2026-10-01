package erebus.events;

import erebus.Erebus;
import erebus.item.armour.SprintLeggings;
import erebus.registries.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class SprintLeggingsHandler {
    private SprintLeggingsHandler() {
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        var player = event.getEntity();
        if (player.level().isClientSide() && !player.isLocalPlayer()) return;
        var leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        if (leggings.is(ModItems.SPRINT_LEGGINGS) && player.isSprinting() && player.onGround()) {
            double factor = 1 + (3 + SprintLeggings.upgrades(leggings)) * 0.0425;
            player.setDeltaMovement(player.getDeltaMovement().multiply(factor, 1, factor));
        }
    }
}
