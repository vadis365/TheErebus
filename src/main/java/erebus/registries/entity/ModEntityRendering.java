package erebus.registries.entity;

import erebus.Erebus;
import erebus.client.render.entity.model.*;
import erebus.client.render.entity.renderer.*;
import erebus.entity.projectile.GooBall;
import erebus.entity.projectile.ThrownBlockAsItem;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModEntityRendering {

    public static ModelLayerLocation WASP = new ModelLayerLocation(Erebus.prefix("wasp"), "main");
    public static ModelLayerLocation ANIMATED_BLOCK = new ModelLayerLocation(Erebus.prefix("animated_block"), "main");
	public static ModelLayerLocation SCYTODES = new ModelLayerLocation(Erebus.prefix("scytodes"), "main");
	public static ModelLayerLocation MONEY_SPIDER = new ModelLayerLocation(Erebus.prefix("money_spider"), "main");
	public static ModelLayerLocation BLACK_WIDOW = new ModelLayerLocation(Erebus.prefix("black_widow"), "main");
	public static ModelLayerLocation LAVA_WEB_SPIDER = new ModelLayerLocation(Erebus.prefix("lava_web_spider"), "main");
	public static ModelLayerLocation LAVA_WEB_SPIDER_FLOW = new ModelLayerLocation(Erebus.prefix("lava_web_spider_flow"), "main");
	public static ModelLayerLocation MOTH = new ModelLayerLocation(Erebus.prefix("moth"), "main");
	public static ModelLayerLocation VELVET_WORM = new ModelLayerLocation(Erebus.prefix("velvet_worm"), "main");
	public static ModelLayerLocation ANTLION = new ModelLayerLocation(Erebus.prefix("antlion"), "main");
	public static ModelLayerLocation BOT_FLY = new ModelLayerLocation(Erebus.prefix("bot_fly"), "main");
	public static ModelLayerLocation BOT_FLY_LARVA = new ModelLayerLocation(Erebus.prefix("bot_fly_larva"), "main");
	public static ModelLayerLocation FLY = new ModelLayerLocation(Erebus.prefix("fly"), "main");
	public static ModelLayerLocation DRAGON_FLY = new ModelLayerLocation(Erebus.prefix("dragon_fly"), "main");
	public static ModelLayerLocation CENTIPEDE = new ModelLayerLocation(Erebus.prefix("centipede"), "main");
	public static ModelLayerLocation GRASSHOPPER = new ModelLayerLocation(Erebus.prefix("grasshopper"), "main");
	public static ModelLayerLocation LOCUST = new ModelLayerLocation(Erebus.prefix("locust"), "main");
	public static ModelLayerLocation BEETLE_LARVA = new ModelLayerLocation(Erebus.prefix("beetle_larva"), "main");
	public static ModelLayerLocation BOMBARDIER_BEETLE_LARVA = new ModelLayerLocation(Erebus.prefix("bombardier_beetle_larva"), "main");
	public static ModelLayerLocation BEETLE = new ModelLayerLocation(Erebus.prefix("beetle"), "main");

    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    	event.registerLayerDefinition(WASP, WaspModel::createBodyLayer);
    	event.registerLayerDefinition(ANIMATED_BLOCK, AnimatedBlockModel::createBodyLayer);
    	event.registerLayerDefinition(SCYTODES, ScytodesModel::createBodyLayer);
    	event.registerLayerDefinition(MONEY_SPIDER, ScytodesModel::createBodyLayer);
    	event.registerLayerDefinition(BLACK_WIDOW, BlackWidowModel::createBodyLayer);
    	event.registerLayerDefinition(LAVA_WEB_SPIDER, LavaWebSpiderModel::createBodyLayer);
    	event.registerLayerDefinition(LAVA_WEB_SPIDER_FLOW, LavaWebSpiderModel::createBodyLayer);
    	event.registerLayerDefinition(MOTH, MothModel::createBodyLayer);
    	event.registerLayerDefinition(VELVET_WORM, VelvetWormModel::createBodyLayer);
    	event.registerLayerDefinition(ANTLION, AntlionModel::createBodyLayer);
    	event.registerLayerDefinition(BOT_FLY, BotFlyModel::createBodyLayer);
    	event.registerLayerDefinition(BOT_FLY_LARVA, BotFlyLarvaModel::createBodyLayer);
    	event.registerLayerDefinition(FLY, FlyModel::createBodyLayer);
    	event.registerLayerDefinition(DRAGON_FLY, DragonflyModel::createBodyLayer);
    	event.registerLayerDefinition(CENTIPEDE, CentipedeModel::createBodyLayer);
    	event.registerLayerDefinition(GRASSHOPPER, GrasshopperModel::createBodyLayer);
    	event.registerLayerDefinition(LOCUST, LocustModel::createBodyLayer);
    	event.registerLayerDefinition(BEETLE_LARVA, BeetleLarvaModel::createBodyLayer);
    	event.registerLayerDefinition(BOMBARDIER_BEETLE_LARVA, BeetleLarvaModel::createBodyLayer);
    	event.registerLayerDefinition(BEETLE, BeetleModel::createBodyLayer);
    }

    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
    	event.registerEntityRenderer(ModEntities.WASP.get(), WaspRenderer::new);
    	event.registerEntityRenderer(ModEntities.ANIMATED_BLOCK.get(), AnimatedBlockRenderer::new);
    	event.registerEntityRenderer(ModEntities.SCYTODES.get(), ScytodesRenderer::new);
    	event.registerEntityRenderer(ModEntities.MONEY_SPIDER.get(), MoneySpiderRenderer::new);
    	event.registerEntityRenderer(ModEntities.BLACK_WIDOW.get(), BlackWidowRenderer::new);
    	event.registerEntityRenderer(ModEntities.LAVA_WEB_SPIDER.get(), LavaWebSpiderRenderer::new);
    	event.registerEntityRenderer(ModEntities.MOTH.get(), MothRenderer::new);
    	event.registerEntityRenderer(ModEntities.VELVET_WORM.get(), VelvetWormRenderer::new);
    	event.registerEntityRenderer(ModEntities.ANTLION.get(), AntlionRenderer::new);
    	event.registerEntityRenderer(ModEntities.BOT_FLY.get(), BotFlyRenderer::new);
    	event.registerEntityRenderer(ModEntities.BOT_FLY_LARVA.get(), BotFlyLarvaRenderer::new);
    	event.registerEntityRenderer(ModEntities.FLY.get(), FlyRenderer::new);
    	event.registerEntityRenderer(ModEntities.DRAGON_FLY.get(), DragonflyRenderer::new);
    	event.registerEntityRenderer(ModEntities.CENTIPEDE.get(), CentipedeRenderer::new);
    	event.registerEntityRenderer(ModEntities.GRASSHOPPER.get(), GrasshopperRenderer::new);
    	event.registerEntityRenderer(ModEntities.LOCUST.get(), LocustRenderer::new);
    	event.registerEntityRenderer(ModEntities.BEETLE_LARVA.get(), BeetleLarvaRenderer::new);
    	event.registerEntityRenderer(ModEntities.BOMBARDIER_BEETLE_LARVA.get(), BeetleLarvaRenderer::new);
    	event.registerEntityRenderer(ModEntities.BEETLE.get(), BeetleRenderer::new);

    	event.registerEntityRenderer(ModEntities.THROWN_BLOCK_AS_ITEM.get(), context -> new ThrownItemRenderer<ThrownBlockAsItem>(context, 3.0F, true));
    	event.registerEntityRenderer(ModEntities.GOO_BALL.get(), context -> new ThrownItemRenderer<GooBall>(context, 3.0F, true));
    }
}
