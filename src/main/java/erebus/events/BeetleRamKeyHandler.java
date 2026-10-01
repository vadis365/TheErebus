package erebus.events;

import erebus.Erebus;
import erebus.entity.RhinoBeetle;
import erebus.network.server.BeetleRamAttack;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public final class BeetleRamKeyHandler {
    private static final KeyMapping RAM = new KeyMapping("key.erebus.beetleram", GLFW.GLFW_KEY_R, KeyMapping.Category.GAMEPLAY);
    private static boolean sentActive;
    private static RhinoBeetle previousMount;

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(RAM);
    }

    @SubscribeEvent
    public static void tick(ClientTickEvent.Post event) {
        var client = Minecraft.getInstance();
        var mount = client.player != null && client.player.getVehicle() instanceof RhinoBeetle beetle ? beetle : null;
        boolean active = mount != null && client.screen == null && RAM.isDown();
        if (client.player != null && (active != sentActive || mount != previousMount))
            client.getConnection().send(new BeetleRamAttack(active));
        sentActive = active;
        previousMount = mount;
    }
}
