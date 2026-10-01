package erebus.events;

import erebus.Erebus;
import erebus.item.armour.GliderItem;
import erebus.network.data.GliderData;
import erebus.network.data.GliderInput;
import erebus.registries.data.ModAttachmentTypes;
import erebus.registries.data.ModDataComponents;
import erebus.registries.data.tags.ModItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public final class GliderFlightHandler {
    public static void controls(Player player, boolean gliding, boolean powered) {
        var stack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!(stack.getItem() instanceof GliderItem item) || !player.isAlive() || player.isSpectator() || player.isPassenger()) {
            player.setData(ModAttachmentTypes.GLIDER_INPUT, GliderInput.NONE);
            return;
        }
        var previous = player.getData(ModAttachmentTypes.GLIDER_INPUT);
        powered &= item.powered();
        if ((gliding || powered) && !previous.gliding() && !previous.powered() && player.onGround()) {
            player.jumpFromGround();
            player.setOnGround(false);
        }
        player.setData(ModAttachmentTypes.GLIDER_INPUT, new GliderInput(item, gliding, powered));
    }

    @SubscribeEvent
    public static void tick(PlayerTickEvent.Post event) {
        var player = event.getEntity();
        if (player.level().isClientSide() && !player.isLocalPlayer()) return;
        var stack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!(stack.getItem() instanceof GliderItem item)) {
            player.setData(ModAttachmentTypes.GLIDER_INPUT, GliderInput.NONE);
            return;
        }
        player.fallDistance = 0;
        var input = player.getData(ModAttachmentTypes.GLIDER_INPUT);
        var data = stack.getOrDefault(ModDataComponents.GLIDER, GliderData.EMPTY);
        boolean allowed = player.isAlive() && !player.isSpectator() && !player.isPassenger() && !player.onGround() && input.item() == item;
        if (!allowed) player.setData(ModAttachmentTypes.GLIDER_INPUT, GliderInput.NONE);
        boolean gliding = allowed && input.gliding();
        var fuel = allowed && input.powered() && item.powered() ? fuel(player) : ItemStack.EMPTY;
        boolean powered = allowed && input.powered() && item.powered() && (player.isCreative() || !fuel.isEmpty());
        var motion = player.getDeltaMovement();
        if (gliding) motion = motion.multiply(1.05, 0.5, 1.05);
        if (powered) motion = motion.multiply(1.05, 1, 1.05).add(0, 0.1, 0);
        if (gliding || powered) player.setDeltaMovement(motion);
        int ticks = Math.clamp(data.fuelTicks(), 0, 79);
        if (powered && !player.isCreative() && !player.level().isClientSide() && ++ticks >= 80) {
            ticks = 0;
            fuel.shrink(1);
        }
        var next = new GliderData(gliding, powered, ticks);
        if (!next.equals(data)) stack.set(ModDataComponents.GLIDER, next);
    }

    private static ItemStack fuel(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            var stack = player.getInventory().getItem(i);
            if (stack.is(ModItemTags.GLIDER_FUEL)) return stack;
        }
        return ItemStack.EMPTY;
    }

    @SubscribeEvent
    public static void fall(LivingFallEvent event) {
        if (event.getEntity() instanceof Player player && player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof GliderItem)
            event.setDistance(0);
    }

    @SubscribeEvent
    public static void dimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        event.getEntity().setData(ModAttachmentTypes.GLIDER_INPUT, GliderInput.NONE);
    }
}
