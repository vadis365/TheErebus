package erebus.registries;

import erebus.Erebus;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModEntityRendering {

    public static ModelLayerLocation GRASHOPPER = new ModelLayerLocation(Erebus.prefix("grasshopper"), "main");


    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
      //  event.registerLayerDefinition(GRASHOPPER, ModelGrasshopper::createBodyLayer);
    }

    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
     //   event.registerEntityRenderer(ModEntities.GRASHOPPER.get(), RenderGrasshopper::new);
    }
}
