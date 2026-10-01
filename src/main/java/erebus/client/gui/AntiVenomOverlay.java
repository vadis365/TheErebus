package erebus.client.gui;

import erebus.Erebus;
import erebus.events.AntiVenomHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = Erebus.MODID, value = Dist.CLIENT)
public final class AntiVenomOverlay {
    private static final Identifier TEXTURE = Erebus.prefix("textures/gui/overlay/anti_venom_bar.png");

    @SubscribeEvent
    public static void register(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.HOTBAR, Erebus.prefix("anti_venom"), (graphics, delta) -> {
            var client = Minecraft.getInstance();
            var player = client.player;
            if (player == null || player.isCreative() || player.isSpectator() || client.options.hideGui) return;
            int amount = AntiVenomHandler.seconds(player) / 9;
            int x = graphics.guiWidth() / 2 + 82;
            int y = graphics.guiHeight() - 51 - (player.isUnderWater() ? 10 : 0);
            for (int i = 0; i < 10; i++) {
                if (i * 2 + 1 > amount) break;
                int offset = amount <= 3 && player.level().getGameTime() % 60 == 0 ? player.getRandom().nextInt(3) - 1 : 0;
                graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x - i * 8, y + offset,
                        i * 2 + 1 == amount ? 9 : 0, 0, 9, 11, 256, 256);
            }
        });
    }
}
