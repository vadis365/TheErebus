package erebus.registries;

import erebus.Erebus;
import erebus.client.entity.model.WaspModel;
import erebus.client.entity.render.WaspRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModEntityRendering {

    public static ModelLayerLocation GRASHOPPER = new ModelLayerLocation(Erebus.prefix("grasshopper"), "main");
    public static ModelLayerLocation WASP = new ModelLayerLocation(Erebus.prefix("wasp"), "main");

    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
      //  event.registerLayerDefinition(GRASHOPPER, ModelGrasshopper::createBodyLayer);
    	event.registerLayerDefinition(WASP, WaspModel::createBodyLayer);
    }

    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
     //   event.registerEntityRenderer(ModEntities.GRASHOPPER.get(), RenderGrasshopper::new);
    	event.registerEntityRenderer(ModEntities.WASP.get(), WaspRenderer::new);
    }
}
