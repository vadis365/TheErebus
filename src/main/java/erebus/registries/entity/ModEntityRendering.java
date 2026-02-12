package erebus.registries.entity;

import erebus.Erebus;
import erebus.client.render.entity.model.*;
import erebus.client.render.entity.model.layer.*;
import erebus.client.render.entity.renderer.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModEntityRendering {

    public static ModelLayerLocation WASP = register("wasp");
    public static ModelLayerLocation WASP_WINGS = registerLayer("wasp", "wings");
    public static ModelLayerLocation ANIMATED_BLOCK = register("animated_block");
	public static ModelLayerLocation SCYTODES = register("scytodes");
	public static ModelLayerLocation MONEY_SPIDER = register("money_spider");
	public static ModelLayerLocation BLACK_WIDOW = register("black_widow");
	public static ModelLayerLocation LAVA_WEB_SPIDER = register("lava_web_spider");
	public static ModelLayerLocation LAVA_WEB_SPIDER_FLOW = register("lava_web_spider_flow");
	public static ModelLayerLocation MOTH = register("moth");
	public static ModelLayerLocation VELVET_WORM = register("velvet_worm");
	public static ModelLayerLocation ANTLION = register("antlion");
	public static ModelLayerLocation BOT_FLY = register("bot_fly");
	public static ModelLayerLocation BOT_FLY_LARVA = register("bot_fly_larva");
	public static ModelLayerLocation FLY = register("fly");
	public static ModelLayerLocation DRAGON_FLY = register("dragon_fly");
	public static ModelLayerLocation CENTIPEDE = register("centipede");
	public static ModelLayerLocation GRASSHOPPER = register("grasshopper");
	public static ModelLayerLocation LOCUST = register("locust");
	public static ModelLayerLocation BEETLE_LARVA = register("beetle_larva");
	public static ModelLayerLocation BOMBARDIER_BEETLE_LARVA = register("bombardier_beetle_larva");
	public static ModelLayerLocation BEETLE = register("beetle");
	public static ModelLayerLocation WORKER_BEE = register("worker_bee");
	public static ModelLayerLocation BOMBARDIER_BEETLE = register("bombardier_beetle");
	public static ModelLayerLocation BLACK_ANT = register("black_ant");
	public static ModelLayerLocation BLACK_ANT_COLLECTOR = register("black_ant_collector");
	public static ModelLayerLocation BLACK_ANT_FERTILIZER = register("black_ant_fertilizer");
	public static ModelLayerLocation BLACK_ANT_HARVESTER = register("black_ant_harvester");
	public static ModelLayerLocation BLACK_ANT_PLANTER = register("black_ant_planter");
	public static ModelLayerLocation PUNCHROOM = register("punchroom");
	public static ModelLayerLocation CROP_WEEVIL = register("crop_weevil");
	public static ModelLayerLocation FUNGAL_WEEVIL = register("fungal_weevil");
	public static ModelLayerLocation BED_BUG = register("bed_bug");
	public static ModelLayerLocation HONEY_POT_ANT = register("honey_pot_ant");
	public static ModelLayerLocation ZOMBIE_ANT = register("zombie_ant");
	public static ModelLayerLocation ZOMBIE_ANT_SOLDIER = register("zombie_ant_soldier");

	private static ModelLayerLocation register(String name) {
		return new ModelLayerLocation(Erebus.prefix(name), "main");
	}

	private static ModelLayerLocation registerLayer(String name, String layer) {
		return new ModelLayerLocation(Erebus.prefix(name), layer);
	}

    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    	event.registerLayerDefinition(WASP, WaspModel::createBodyLayer);
		event.registerLayerDefinition(WASP_WINGS, WaspWingsModel::createBodyLayer);
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
    	event.registerLayerDefinition(WORKER_BEE, WorkerBeeModel::createBodyLayer);
    	event.registerLayerDefinition(BOMBARDIER_BEETLE, BombardierBeetleModel::createBodyLayer);
    	event.registerLayerDefinition(BLACK_ANT, BlackAntModel::createBodyLayer);
		event.registerLayerDefinition(BLACK_ANT_COLLECTOR, BlackAntCollectorModel::createBodyLayer);
		event.registerLayerDefinition(BLACK_ANT_FERTILIZER, BlackAntFertilizerModel::createBodyLayer);
		event.registerLayerDefinition(BLACK_ANT_HARVESTER, BlackAntHarvesterModel::createBodyLayer);
		event.registerLayerDefinition(BLACK_ANT_PLANTER, BlackAntPlanterModel::createBodyLayer);
    	event.registerLayerDefinition(PUNCHROOM, PunchroomModel::createBodyLayer);
    	event.registerLayerDefinition(CROP_WEEVIL, WeevilModel::createBodyLayer);
    	event.registerLayerDefinition(FUNGAL_WEEVIL, WeevilModel::createBodyLayer);
    	event.registerLayerDefinition(BED_BUG, BedBugModel::createBodyLayer);
    	event.registerLayerDefinition(HONEY_POT_ANT, HoneyPotAntModel::createBodyLayer);
    	event.registerLayerDefinition(ZOMBIE_ANT, AntModel::createBodyLayer);
    	event.registerLayerDefinition(ZOMBIE_ANT_SOLDIER, SoldierAntModel::createBodyLayer);
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
    	event.registerEntityRenderer(ModEntities.WORKER_BEE.get(), WorkerBeeRenderer::new);
    	event.registerEntityRenderer(ModEntities.BOMBARDIER_BEETLE.get(), BombardierBeetleRenderer::new);
    	event.registerEntityRenderer(ModEntities.BLACK_ANT.get(), BlackAntRenderer::new);
    	event.registerEntityRenderer(ModEntities.PUNCHROOM.get(), PunchroomRenderer::new);
    	event.registerEntityRenderer(ModEntities.CROP_WEEVIL.get(), CropWeevilRenderer::new);
    	event.registerEntityRenderer(ModEntities.FUNGAL_WEEVIL.get(), FungalWeevilRenderer::new);
    	event.registerEntityRenderer(ModEntities.BED_BUG.get(), BedBugRenderer::new);
    	event.registerEntityRenderer(ModEntities.HONEY_POT_ANT.get(), HoneyPotAntRenderer::new);
    	event.registerEntityRenderer(ModEntities.ZOMBIE_ANT.get(), ZombieAntRenderer::new);
    	event.registerEntityRenderer(ModEntities.ZOMBIE_ANT_SOLDIER.get(), ZombieAntSoldierRenderer::new);

    	event.registerEntityRenderer(ModEntities.THROWN_BLOCK_AS_ITEM.get(), context -> new ThrownItemRenderer<>(context, 3.0F, true));
    	event.registerEntityRenderer(ModEntities.GOO_BALL.get(), context -> new ThrownItemRenderer<>(context, 3.0F, true));
		event.registerEntityRenderer(ModEntities.AMBER_STAR.get(), context -> new ThrownItemRenderer<>(context, 3.0F, true));
    }
}
