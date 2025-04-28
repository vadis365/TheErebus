package erebus.registries.client;

import erebus.Erebus;
import erebus.client.render.block.model.BlockOfBonesModel;
import erebus.client.render.block.model.HealingAltarModel;
import erebus.client.render.block.model.OfferingAltarModel;
import erebus.client.render.block.renderer.BlockOfBonesRenderer;
import erebus.client.render.block.renderer.GaeanKeystoneRenderer;
import erebus.client.render.block.renderer.HealingAltarRenderer;
import erebus.client.render.block.renderer.OfferingAltarRenderer;
import erebus.registries.ModBlockEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModBlockEntityRendering {
	public static final ModelLayerLocation OFFERING_ALTAR = new ModelLayerLocation(Erebus.prefix("offering_altar"), "main");
    public static final ModelLayerLocation BLOCK_OF_BONES = new ModelLayerLocation(Erebus.prefix("block_of_bones"), "main");
    public static final ModelLayerLocation ALTAR_HEALING = new ModelLayerLocation(Erebus.prefix("altar_healing"), "main");
    
	public static void registerBlockEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(OFFERING_ALTAR, OfferingAltarModel::createBodyLayer);
        event.registerLayerDefinition(BLOCK_OF_BONES, BlockOfBonesModel::createBodyLayer);
        event.registerLayerDefinition(ALTAR_HEALING, HealingAltarModel::createBodyLayer);
	}
	
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GAEAN_KEYSTONE.get(), GaeanKeystoneRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.OFFERING_ALTAR.get(), OfferingAltarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLOCK_OF_BONES.get(), BlockOfBonesRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_HEALING.get(), HealingAltarRenderer::new);
    }
    
}
