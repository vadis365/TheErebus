package erebus.registries.entity;

import erebus.Erebus;
import erebus.client.render.entity.model.AnimatedBlockModel;
import erebus.client.render.entity.model.BlackWidowModel;
import erebus.client.render.entity.model.ScytodesModel;
import erebus.client.render.entity.model.WaspModel;
import erebus.client.render.entity.renderer.AnimatedBlockRenderer;
import erebus.client.render.entity.renderer.BlackWidowRenderer;
import erebus.client.render.entity.renderer.MoneySpiderRenderer;
import erebus.client.render.entity.renderer.ScytodesRenderer;
import erebus.client.render.entity.renderer.WaspRenderer;
import erebus.entity.projectile.WebSling;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModEntityRendering {

    public static ModelLayerLocation GRASHOPPER = new ModelLayerLocation(Erebus.prefix("grasshopper"), "main");
    public static ModelLayerLocation WASP = new ModelLayerLocation(Erebus.prefix("wasp"), "main");
    public static ModelLayerLocation ANIMATED_BLOCK = new ModelLayerLocation(Erebus.prefix("animated_block"), "main");
	public static ModelLayerLocation SCYTODES = new ModelLayerLocation(Erebus.prefix("scytodes"), "main");
	public static ModelLayerLocation MONEY_SPIDER = new ModelLayerLocation(Erebus.prefix("money_spider"), "main");
	public static ModelLayerLocation BLACK_WIDOW = new ModelLayerLocation(Erebus.prefix("black_widow"), "main");

    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    	//  event.registerLayerDefinition(GRASHOPPER, ModelGrasshopper::createBodyLayer);
    	event.registerLayerDefinition(WASP, WaspModel::createBodyLayer);
    	event.registerLayerDefinition(ANIMATED_BLOCK, AnimatedBlockModel::createBodyLayer);
    	event.registerLayerDefinition(SCYTODES, ScytodesModel::createBodyLayer);
    	event.registerLayerDefinition(MONEY_SPIDER, ScytodesModel::createBodyLayer);
    	event.registerLayerDefinition(BLACK_WIDOW, BlackWidowModel::createBodyLayer);
    }

    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
     //   event.registerEntityRenderer(ModEntities.GRASHOPPER.get(), RenderGrasshopper::new);
    	event.registerEntityRenderer(ModEntities.WASP.get(), WaspRenderer::new);
    	event.registerEntityRenderer(ModEntities.ANIMATED_BLOCK.get(), AnimatedBlockRenderer::new);
    	event.registerEntityRenderer(ModEntities.SCYTODES.get(), ScytodesRenderer::new);
    	event.registerEntityRenderer(ModEntities.MONEY_SPIDER.get(), MoneySpiderRenderer::new);
    	event.registerEntityRenderer(ModEntities.BLACK_WIDOW.get(), BlackWidowRenderer::new);
    	event.registerEntityRenderer(ModEntities.WEB_SLING.get(), context -> new ThrownItemRenderer<WebSling>(context, 3.0F, true));
    }
}
