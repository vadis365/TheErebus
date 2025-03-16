package erebus.registries;

import erebus.Erebus;
import erebus.client.entity.model.ModelAnimatedBlock;
import erebus.client.entity.model.WaspModel;
import erebus.client.entity.render.RenderAnimatedBlock;
import erebus.client.entity.render.WaspRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModEntityRendering {

    public static ModelLayerLocation GRASHOPPER = new ModelLayerLocation(Erebus.prefix("grasshopper"), "main");
    public static ModelLayerLocation WASP = new ModelLayerLocation(Erebus.prefix("wasp"), "main");
    public static ModelLayerLocation ANIMATED_BLOCK = new ModelLayerLocation(Erebus.prefix("animated_block"), "main");

    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
      //  event.registerLayerDefinition(GRASHOPPER, ModelGrasshopper::createBodyLayer);
    	event.registerLayerDefinition(WASP, WaspModel::createBodyLayer);
    	 event.registerLayerDefinition(ANIMATED_BLOCK, ModelAnimatedBlock::createBodyLayer);
    }

    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
     //   event.registerEntityRenderer(ModEntities.GRASHOPPER.get(), RenderGrasshopper::new);
    	event.registerEntityRenderer(ModEntities.WASP.get(), WaspRenderer::new);
    	event.registerEntityRenderer(ModEntities.ANIMATED_BLOCK.get(), RenderAnimatedBlock::new);
    }
}
