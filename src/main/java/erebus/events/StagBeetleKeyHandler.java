package erebus.events;

import erebus.Erebus;
import erebus.entity.StagBeetle;
import erebus.network.server.BeetleDig;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public final class StagBeetleKeyHandler {
    private static final KeyMapping DIG = new KeyMapping("key.erebus.beetlemine", GLFW.GLFW_KEY_LEFT_ALT, KeyMapping.Category.GAMEPLAY);

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(DIG);
    }

    @SubscribeEvent
    public static void tick(ClientTickEvent.Post event) {
        var client = Minecraft.getInstance();
        while (DIG.consumeClick()) {
            if (client.screen == null && client.player != null && client.player.getVehicle() instanceof StagBeetle) {
                var hit = client.player.pick(5, 1, false);
                if (hit instanceof BlockHitResult block && hit.getType() == HitResult.Type.BLOCK)
                    client.getConnection().send(new BeetleDig(block.getBlockPos()));
            }
        }
    }
}
