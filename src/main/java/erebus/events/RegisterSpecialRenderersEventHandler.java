package erebus.events;

import erebus.Erebus;
import erebus.client.render.item.renderer.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterSpecialBlockModelRendererEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public class RegisterSpecialRenderersEventHandler {

    @SubscribeEvent
    public static void registerSpecialRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(Erebus.prefix("shield_special"), ErebusShieldSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("portal_activator_special"), PortalActivatorSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("quake_hammer_special"), QuakeHammerSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("scorpion_pincer_special"), ScorpionPincerSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("wand_of_animation_special"), WandOfAnimationItemSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("wand_of_preservation_special"), WandOfPreservationSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("wasp_dagger_special"), WaspDaggerSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("wasp_sword_special"), WaspSwordSpecialRenderer.Unbaked.MAP_CODEC);
        event.register(Erebus.prefix("web_slinger_special"), WebSlingerSpecialRenderer.Unbaked.MAP_CODEC);
    }

    @SubscribeEvent
    public static void registerSpecialBlockRenderers(RegisterSpecialBlockModelRendererEvent event) {

    }
}
