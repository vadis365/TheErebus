package erebus.events;


import erebus.Erebus;
import erebus.client.layer.CustomHelmLayer;
import erebus.client.layer.GliderLayer;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Erebus.MODID)
public class EntityRenderersEventHandler {

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skin : event.getSkins()) {
            if (event.getSkin(skin) instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new GliderLayer<>(playerRenderer, event.getEntityModels()));
                playerRenderer.addLayer(new CustomHelmLayer<>(playerRenderer, event.getEntityModels(), skin.equals(PlayerSkin.Model.SLIM)));
            }

            for(EntityType<?> entityType : event.getEntityTypes()) {
                EntityRenderer<?> renderer = event.getRenderer(entityType);

                if(renderer instanceof ArmorStandRenderer armorStandRenderer) {
                    armorStandRenderer.addLayer(new CustomHelmLayer<>(armorStandRenderer, event.getEntityModels(), skin.equals(PlayerSkin.Model.SLIM)));
                }
            }
        }
    }
}
