package erebus.registries.entity;

import erebus.Erebus;
import erebus.entity.AnimatedBlock;
import erebus.entity.Antlion;
import erebus.entity.Beetle;
import erebus.entity.BeetleLarva;
import erebus.entity.BlackAnt;
import erebus.entity.BlackWidow;
import erebus.entity.BombardierBeetle;
import erebus.entity.BombardierBeetleLarva;
import erebus.entity.BotFly;
import erebus.entity.BotFlyLarva;
import erebus.entity.Centipede;
import erebus.entity.Dragonfly;
import erebus.entity.Fly;
import erebus.entity.Grasshopper;
import erebus.entity.LavaWebSpider;
import erebus.entity.Locust;
import erebus.entity.MoneySpider;
import erebus.entity.Moth;
import erebus.entity.Scytodes;
import erebus.entity.VelvetWorm;
import erebus.entity.Wasp;
import erebus.entity.WorkerBee;
import erebus.entity.projectile.AmberStar;
import erebus.entity.projectile.GooBall;
import erebus.entity.projectile.ThrownBlockAsItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Erebus.MODID);
    public static final DeferredRegister.Items SPAWN_EGGS = DeferredRegister.createItems(Erebus.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Grasshopper>> GRASSHOPPER = registerWithEgg("grasshopper", EntityType.Builder.of(Grasshopper::new, MobCategory.CREATURE).sized(1.3F, 0.75F), 0x63A02E, 0xE5D11B);
    public static final DeferredHolder<EntityType<?>, EntityType<Locust>> LOCUST = registerWithEgg("locust", EntityType.Builder.of(Locust::new, MobCategory.MONSTER).sized(2F, 1F), 0xA07E2E, 0xEC3200);
    public static final DeferredHolder<EntityType<?>, EntityType<Wasp>> WASP = registerWithEgg("wasp", EntityType.Builder.of(Wasp::new, MobCategory.MONSTER).sized(0.5F, 0.4F), 0xFECD09, 0x141414);
    public static final DeferredHolder<EntityType<?>, EntityType<AnimatedBlock>> ANIMATED_BLOCK = registerNoEgg("animated_block", EntityType.Builder.of(AnimatedBlock::new, MobCategory.MISC).fireImmune().sized(1F, 1.25F).clientTrackingRange(4).updateInterval(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Scytodes>> SCYTODES = registerWithEgg("scytodes", EntityType.Builder.of(Scytodes::new, MobCategory.MONSTER).sized(2F, 1F).fireImmune(), 0xC2833C, 0x520D06);
    public static final DeferredHolder<EntityType<?>, EntityType<MoneySpider>> MONEY_SPIDER = registerWithEgg("money_spider", EntityType.Builder.of(MoneySpider::new, MobCategory.MONSTER).sized(0.6F, 0.4F).fireImmune(), 0xC2872F, 0xF9FF00);
    public static final DeferredHolder<EntityType<?>, EntityType<BlackWidow>> BLACK_WIDOW = registerWithEgg("black_widow", EntityType.Builder.of(BlackWidow::new, MobCategory.MONSTER).sized(0.7F, 0.325F).fireImmune(), 0x101010, 0xFF0000);
    public static final DeferredHolder<EntityType<?>, EntityType<LavaWebSpider>> LAVA_WEB_SPIDER = registerWithEgg("lava_web_spider", EntityType.Builder.of(LavaWebSpider::new, MobCategory.MONSTER).sized(3F, 1.5F).fireImmune(), 0xD36617, 0x342522);
    public static final DeferredHolder<EntityType<?>, EntityType<Moth>> MOTH = registerWithEgg("moth", EntityType.Builder.of(Moth::new, MobCategory.MONSTER).sized(1.8F, 0.5F), 0x83DB99, 0xACF4E0);
    public static final DeferredHolder<EntityType<?>, EntityType<VelvetWorm>> VELVET_WORM = registerWithEgg("velvet_worm", EntityType.Builder.of(VelvetWorm::new, MobCategory.MONSTER).sized(0.3125F, 0.3125F), 0x88444B, 0xFFDAC0);
    public static final DeferredHolder<EntityType<?>, EntityType<Antlion>> ANTLION = registerWithEgg("antlion", EntityType.Builder.of(Antlion::new, MobCategory.MONSTER).sized(1.9F, 0.9F).fireImmune(), 0x958A54, 0xBB4602);
    public static final DeferredHolder<EntityType<?>, EntityType<BotFly>> BOT_FLY = registerWithEgg("bot_fly", EntityType.Builder.of(BotFly::new, MobCategory.MONSTER).sized(0.9F, 0.75F), 0xEFE2B9, 0x858B95);
    public static final DeferredHolder<EntityType<?>, EntityType<BotFlyLarva>> BOT_FLY_LARVA = registerNoEgg("bot_fly_larva", EntityType.Builder.of(BotFlyLarva::new, MobCategory.MONSTER).sized(0.5F, 0.2F).fireImmune());
    public static final DeferredHolder<EntityType<?>, EntityType<Fly>> FLY = registerWithEgg("fly", EntityType.Builder.of(Fly::new, MobCategory.MONSTER).sized(0.5F, 0.45F), 0x381C22, 0x990000);
    public static final DeferredHolder<EntityType<?>, EntityType<Dragonfly>> DRAGON_FLY = registerWithEgg("dragon_fly", EntityType.Builder.of(Dragonfly::new, MobCategory.MONSTER).sized(2.5F, 1.0F).fireImmune(), 0x37A87C, 0xE9E9E9);
    public static final DeferredHolder<EntityType<?>, EntityType<Centipede>> CENTIPEDE = registerWithEgg("centipede", EntityType.Builder.of(Centipede::new, MobCategory.MONSTER).sized(0.3125F, 0.3125F), 0x3C0000, 0xEA0000);

    public static final DeferredHolder<EntityType<?>, EntityType<BeetleLarva>> BEETLE_LARVA = registerWithEgg("beetle_larva", EntityType.Builder.of(BeetleLarva::new, MobCategory.CREATURE).sized(0.9F, 0.5F), 0xE5DEC4, 0x472A0F);
	public static final DeferredHolder<EntityType<?>, EntityType<BombardierBeetleLarva>> BOMBARDIER_BEETLE_LARVA = registerWithEgg("bombardier_beetle_larva", EntityType.Builder.of(BombardierBeetleLarva::new, MobCategory.MONSTER).sized(0.9F, 0.5F), 0xE5DEC4, 0x232B98);
	public static final DeferredHolder<EntityType<?>, EntityType<Beetle>> BEETLE = registerWithEgg("beetle", EntityType.Builder.of(Beetle::new, MobCategory.CREATURE).sized(1.6F, 0.9F), 0x7B4026, 0xAB9A93);
	public static final DeferredHolder<EntityType<?>, EntityType<BombardierBeetle>> BOMBARDIER_BEETLE = registerWithEgg("bombardier_beetle", EntityType.Builder.of(BombardierBeetle::new, MobCategory.MONSTER).sized(1.9F, 0.9F), 0x232B98, 0xF15800);
	public static final DeferredHolder<EntityType<?>, EntityType<WorkerBee>> WORKER_BEE = registerWithEgg("worker_bee", EntityType.Builder.of(WorkerBee::new, MobCategory.CREATURE).sized(0.5F, 0.5F), 0xFAAE0E, 0x170F09);
	public static final DeferredHolder<EntityType<?>, EntityType<BlackAnt>> BLACK_ANT = registerWithEgg("black_ant", EntityType.Builder.of(BlackAnt::new, MobCategory.CREATURE).sized(0.9F, 0.4F), 0x1E1E1E, 0xFF6600);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownBlockAsItem>> THROWN_BLOCK_AS_ITEM = registerNonMobEntity("thrown_block_as_item", EntityType.Builder.<ThrownBlockAsItem>of(ThrownBlockAsItem::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F));
    public static final DeferredHolder<EntityType<?>, EntityType<GooBall>> GOO_BALL = registerNonMobEntity("goo_ball", EntityType.Builder.<GooBall>of(GooBall::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F));
	public static final DeferredHolder<EntityType<?>, EntityType<AmberStar>> AMBER_STAR = registerNonMobEntity("amber_star", EntityType.Builder.<AmberStar>of(AmberStar::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F));

	// just calls a helper in the main mod because it'll be used all over probably
	private static String prefix(String name) {
		return Erebus.prefix(name).toString();
	}

	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(WASP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Wasp::canSpawnHere, null);
		event.register(SCYTODES.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Scytodes::canSpawnHere, null);
		event.register(BLACK_WIDOW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlackWidow::canSpawnHere, null);
		event.register(LAVA_WEB_SPIDER.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaWebSpider::canSpawnHere, null);
		event.register(MOTH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Moth::canSpawnHere, null);
		event.register(VELVET_WORM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, VelvetWorm::canSpawnHere, null);
		event.register(ANTLION.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Antlion::canSpawnHere, null);
		event.register(BOT_FLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BotFly::canSpawnHere, null);
		event.register(FLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Fly::canSpawnHere, null);
		event.register(DRAGON_FLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Dragonfly::canSpawnHere, null);
		event.register(CENTIPEDE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Centipede::canSpawnHere, null);
		event.register(GRASSHOPPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Grasshopper::canSpawnHere, null);
		event.register(LOCUST.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Locust::canSpawnHere, null);
		event.register(BEETLE_LARVA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BeetleLarva::canSpawnHere, null);
		event.register(BEETLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Beetle::canSpawnHere, null);
		event.register(BOMBARDIER_BEETLE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BombardierBeetle::canSpawnHere, null);
		event.register(WORKER_BEE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WorkerBee::canSpawnHere, null);
		event.register(BLACK_ANT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlackAnt::canSpawnHere, null);
	}

	public static void initializeAttributes(EntityAttributeCreationEvent event) {
		event.put(WASP.get(), Wasp.createAttributes().build());
		event.put(ANIMATED_BLOCK.get(), AnimatedBlock.createAttributes().build());
		event.put(SCYTODES.get(), Scytodes.createAttributes().build());
		event.put(MONEY_SPIDER.get(), MoneySpider.createAttributes().build());
		event.put(BLACK_WIDOW.get(), BlackWidow.createAttributes().build());
		event.put(LAVA_WEB_SPIDER.get(), LavaWebSpider.createAttributes().build());
		event.put(MOTH.get(), Moth.createAttributes().build());
		event.put(VELVET_WORM.get(), VelvetWorm.createAttributes().build());
		event.put(ANTLION.get(), Antlion.createAttributes().build());
		event.put(BOT_FLY.get(), BotFly.createAttributes().build());
		event.put(BOT_FLY_LARVA.get(), BotFlyLarva.createAttributes().build());
		event.put(FLY.get(), Fly.createAttributes().build());
		event.put(DRAGON_FLY.get(), Dragonfly.createAttributes().build());
		event.put(CENTIPEDE.get(), Centipede.createAttributes().build());
		event.put(GRASSHOPPER.get(), Grasshopper.createAttributes().build());
		event.put(LOCUST.get(), Locust.createAttributes().build());
		event.put(BEETLE_LARVA.get(), BeetleLarva.createAttributes().build());
		event.put(BOMBARDIER_BEETLE_LARVA.get(), BombardierBeetleLarva.createAttributes().build());
		event.put(BEETLE.get(), Beetle.createAttributes().build());
		event.put(WORKER_BEE.get(), WorkerBee.createAttributes().build());
		event.put(BOMBARDIER_BEETLE.get(), BombardierBeetle.createAttributes().build());
		event.put(BLACK_ANT.get(), BlackAnt.createAttributes().build());
	}

	public static DeferredRegister<EntityType<?>> getEntityTypes() {
		return ENTITY_TYPES;
	}

	public static <E extends Mob> DeferredHolder<EntityType<?>, EntityType<E>> registerWithEgg(String name, EntityType.Builder<E> builder, int primaryColor, int secondaryColor) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(prefix(name)));
		SPAWN_EGGS.register(name + "_spawn_egg", () -> new DeferredSpawnEggItem(ret, primaryColor, secondaryColor, new Item.Properties()));
		return ret;
	}

	public static <E extends Mob> DeferredHolder<EntityType<?>, EntityType<E>> registerNoEgg(String name, EntityType.Builder<E> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(prefix(name)));
	}

	private static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerNonMobEntity(String name, EntityType.Builder<E> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(prefix(name)));
	}

}
