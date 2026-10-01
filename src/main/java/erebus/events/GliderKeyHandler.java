package erebus.events;

import erebus.Erebus;
import erebus.item.armour.GliderItem;
import erebus.network.server.GliderControls;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public final class GliderKeyHandler {
    private static final KeyMapping GLIDE = new KeyMapping("key.erebus.glide", GLFW.GLFW_KEY_G, KeyMapping.Category.GAMEPLAY);
    private static final KeyMapping POWER = new KeyMapping("key.erebus.poweredglide", GLFW.GLFW_KEY_H, KeyMapping.Category.GAMEPLAY);
    private static Player previousPlayer;
    private static Item previousItem;
    private static boolean sentGlide;
    private static boolean sentPower;

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(GLIDE);
        event.register(POWER);
    }

    @SubscribeEvent
    public static void tick(ClientTickEvent.Pre event) {
        var client = Minecraft.getInstance();
        var player = client.player;
        Item item = player == null ? null : player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        boolean usable = player != null && client.screen == null && player.isAlive() && !player.isSpectator() && !player.isPassenger();
        boolean glide = usable && item instanceof GliderItem && GLIDE.isDown();
        boolean power = usable && item instanceof GliderItem glider && glider.powered() && POWER.isDown();
        if (player != null && client.getConnection() != null
                && (player != previousPlayer || item != previousItem || glide != sentGlide || power != sentPower)) {
            GliderFlightHandler.controls(player, glide, power);
            client.getConnection().send(new GliderControls(glide, power));
        }
        previousPlayer = player;
        previousItem = item;
        sentGlide = glide;
        sentPower = power;
    }

    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        if (!(event.getItemStack().getItem() instanceof GliderItem glider)) return;
        if (glider.powered()) {
            event.getToolTip().add(Component.translatable("tooltip.erebus.powered_glider"));
            event.getToolTip().add(Component.translatable("tooltip.erebus.glider_powered_key").append(": ").append(POWER.getTranslatedKeyMessage()));
        }
        event.getToolTip().add(Component.translatable("tooltip.erebus.glider_glide_key").append(": ").append(GLIDE.getTranslatedKeyMessage()));
    }
}
