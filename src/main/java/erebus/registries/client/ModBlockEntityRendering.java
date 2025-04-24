package erebus.registries.client;

import erebus.Erebus;
import erebus.client.render.block.model.OfferingAltarModel;
import erebus.client.render.block.renderer.GaeanKeystoneRenderer;
import erebus.client.render.block.renderer.OfferingAltarRenderer;
import erebus.registries.ModBlockEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModBlockEntityRendering {
	public static final ModelLayerLocation OFFERING_ALTAR = new ModelLayerLocation(Erebus.prefix("offering_altar"), "main");
	
	public static void registerBlockEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(OFFERING_ALTAR, OfferingAltarModel::createBodyLayer);
	}
	
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GAEAN_KEYSTONE.get(), GaeanKeystoneRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.OFFERING_ALTAR.get(), OfferingAltarRenderer::new);
    }
    
}
