package erebus.events;


import erebus.Erebus;
import erebus.client.layer.CustomHelmLayer;
import erebus.client.layer.GliderLayer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.PlayerModelType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Erebus.MODID)
public class EntityRenderersEventHandler {

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        for(PlayerModelType type : event.getSkins()) {
            AvatarRenderer<AbstractClientPlayer> renderer = event.getPlayerRenderer(type);
            renderer.addLayer(new GliderLayer<>(renderer, event.getEntityModels(), event.getContext().getEquipmentRenderer()));
            renderer.addLayer(new CustomHelmLayer<>(renderer, event.getEntityModels(), event.getContext().getEquipmentRenderer()));
        }

        for(EntityType<?> type : event.getEntityTypes()) {
            var renderer = event.getRenderer(type);

            if(renderer instanceof ArmorStandRenderer armorStandRenderer) {
                armorStandRenderer.addLayer(new CustomHelmLayer<>(armorStandRenderer, event.getEntityModels(), event.getContext().getEquipmentRenderer()));
            }
        }
    }
}
