package erebus.registries.entity;

import erebus.Erebus;
import erebus.entity.*;
import erebus.entity.projectile.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES;

    public static final DeferredHolder<EntityType<?>, EntityType<AmberStar>> AMBER_STAR;
    public static final DeferredHolder<EntityType<?>, EntityType<AnimatedBlock>> ANIMATED_BLOCK;
    public static final DeferredHolder<EntityType<?>, EntityType<Antlion>> ANTLION;
	public static final DeferredHolder<EntityType<?>, EntityType<AntlionBoss>> ANTLION_BOSS;
	public static final DeferredHolder<EntityType<?>, EntityType<AntlionMiniBoss>> ANTLION_MINI_BOSS;
	public static final DeferredHolder<EntityType<?>, EntityType<BabySolifuge>> BABY_SOLIFUGE;
	public static final DeferredHolder<EntityType<?>, EntityType<BabyTarantula>> BABY_TARANTULA;
    public static final DeferredHolder<EntityType<?>, EntityType<BedBug>> BED_BUG;
    public static final DeferredHolder<EntityType<?>, EntityType<Beetle>> BEETLE;
    public static final DeferredHolder<EntityType<?>, EntityType<BeetleLarva>> BEETLE_LARVA;
    public static final DeferredHolder<EntityType<?>, EntityType<BlackAnt>> BLACK_ANT;
    public static final DeferredHolder<EntityType<?>, EntityType<BlackWidow>> BLACK_WIDOW;
	public static final DeferredHolder<EntityType<?>, EntityType<BogMaw>> BOG_MAW;
    public static final DeferredHolder<EntityType<?>, EntityType<BombardierBeetle>> BOMBARDIER_BEETLE;
    public static final DeferredHolder<EntityType<?>, EntityType<BombardierBeetleLarva>> BOMBARDIER_BEETLE_LARVA;
    public static final DeferredHolder<EntityType<?>, EntityType<BotFly>> BOT_FLY;
    public static final DeferredHolder<EntityType<?>, EntityType<BotFlyLarva>> BOT_FLY_LARVA;
    public static final DeferredHolder<EntityType<?>, EntityType<Centipede>> CENTIPEDE;
    public static final DeferredHolder<EntityType<?>, EntityType<ChameleonTick>> CHAMELEON_TICK;
	public static final DeferredHolder<EntityType<?>, EntityType<Cicada>> CICADA;
    public static final DeferredHolder<EntityType<?>, EntityType<CropWeevil>> CROP_WEEVIL;
    public static final DeferredHolder<EntityType<?>, EntityType<Crushroom>> CRUSHROOM;
    public static final DeferredHolder<EntityType<?>, EntityType<Dragonfly>> DRAGON_FLY;
    public static final DeferredHolder<EntityType<?>, EntityType<FireAnt>> FIRE_ANT;
    public static final DeferredHolder<EntityType<?>, EntityType<FireAntSoldier>> FIRE_ANT_SOLDIER;
    public static final DeferredHolder<EntityType<?>, EntityType<Fly>> FLY;
    public static final DeferredHolder<EntityType<?>, EntityType<FungalWeevil>> FUNGAL_WEEVIL;
    public static final DeferredHolder<EntityType<?>, EntityType<GooBall>> GOO_BALL;
	public static final DeferredHolder<EntityType<?>, EntityType<GlowWorm>> GLOW_WORM;
    public static final DeferredHolder<EntityType<?>, EntityType<Grasshopper>> GRASSHOPPER;
    public static final DeferredHolder<EntityType<?>, EntityType<HoneyPotAnt>> HONEY_POT_ANT;
	public static final DeferredHolder<EntityType<?>, EntityType<JumpingSpider>> JUMPING_SPIDER;
    public static final DeferredHolder<EntityType<?>, EntityType<LavaWebSpider>> LAVA_WEB_SPIDER;
	public static final DeferredHolder<EntityType<?>, EntityType<Leech>> LEECH;
    public static final DeferredHolder<EntityType<?>, EntityType<Locust>> LOCUST;
    public static final DeferredHolder<EntityType<?>, EntityType<MagmaCrawler>> MAGMA_CRAWLER;
    public static final DeferredHolder<EntityType<?>, EntityType<MidgeSwarm>> MIDGE_SWARM;
    public static final DeferredHolder<EntityType<?>, EntityType<MoneySpider>> MONEY_SPIDER;
    public static final DeferredHolder<EntityType<?>, EntityType<Mosquito>> MOSQUITO;
    public static final DeferredHolder<EntityType<?>, EntityType<Moth>> MOTH;
	public static final DeferredHolder<EntityType<?>, EntityType<PoisonJet>> POISON_JET;
	public static final DeferredHolder<EntityType<?>, EntityType<PondSkater>> POND_SKATER;
	public static final DeferredHolder<EntityType<?>, EntityType<PrayingMantis>> PRAYING_MANTIS;
    public static final DeferredHolder<EntityType<?>, EntityType<Punchroom>> PUNCHROOM;
	public static final DeferredHolder<EntityType<?>, EntityType<RhinoBeetle>> RHINO_BEETLE;
	public static final DeferredHolder<EntityType<?>, EntityType<Scorpion>> SCORPION;
    public static final DeferredHolder<EntityType<?>, EntityType<Scytodes>> SCYTODES;
    public static final DeferredHolder<EntityType<?>, EntityType<Solifuge>> SOLIFUGE;
	public static final DeferredHolder<EntityType<?>, EntityType<StagBeetle>> STAG_BEETLE;
    public static final DeferredHolder<EntityType<?>, EntityType<Tarantula>> TARANTULA;
    public static final DeferredHolder<EntityType<?>, EntityType<TarantulaMiniBoss>> TARANTULA_MINI_BOSS;
	public static final DeferredHolder<EntityType<?>, EntityType<TarantulaEgg>> TARANTULA_EGG;
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownBlockAsItem>> THROWN_BLOCK_AS_ITEM;
	public static final DeferredHolder<EntityType<?>, EntityType<TitanBeetle>> TITAN_BEETLE;
	public static final DeferredHolder<EntityType<?>, EntityType<UmberGolem>> UMBER_GOLEM;
    public static final DeferredHolder<EntityType<?>, EntityType<VelvetWorm>> VELVET_WORM;
    public static final DeferredHolder<EntityType<?>, EntityType<Wasp>> WASP;
	public static final DeferredHolder<EntityType<?>, EntityType<Woodlouse>> WOODLOUSE;
    public static final DeferredHolder<EntityType<?>, EntityType<WorkerBee>> WORKER_BEE;
    public static final DeferredHolder<EntityType<?>, EntityType<ZombieAnt>> ZOMBIE_ANT;
    public static final DeferredHolder<EntityType<?>, EntityType<ZombieAntSoldier>> ZOMBIE_ANT_SOLDIER;

	static {
		ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Erebus.MODID);

		AMBER_STAR = register("amber_star", EntityType.Builder.<AmberStar>of(AmberStar::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F));
		ANIMATED_BLOCK = register("animated_block", EntityType.Builder.of(AnimatedBlock::new, MobCategory.MISC).fireImmune().sized(1F, 1.25F).clientTrackingRange(4).updateInterval(10));
		ANTLION = register("antlion", EntityType.Builder.of(Antlion::new, MobCategory.MONSTER).sized(1.9F, 0.9F).fireImmune());
		ANTLION_BOSS = register("antlion_boss", EntityType.Builder.of(AntlionBoss::new, MobCategory.MONSTER).sized(6.0F, 2.0F).fireImmune());
		ANTLION_MINI_BOSS = register("antlion_mini_boss", EntityType.Builder.of(AntlionMiniBoss::new, MobCategory.MONSTER).sized(2.75F, 1.2F).fireImmune());
		BABY_SOLIFUGE = register("baby_solifuge", EntityType.Builder.of(BabySolifuge::new, MobCategory.MONSTER).sized(1.0F, 0.5F).fireImmune());
		BABY_TARANTULA = register("baby_tarantula", EntityType.Builder.of(BabyTarantula::new, MobCategory.MONSTER).sized(0.75F, 0.25F));
		BED_BUG = register("bed_bug", EntityType.Builder.of(BedBug::new, MobCategory.MONSTER).sized(0.75F, 0.6F));
		BEETLE = register("beetle", EntityType.Builder.of(Beetle::new, MobCategory.CREATURE).sized(1.6F, 0.9F));
		BEETLE_LARVA = register("beetle_larva", EntityType.Builder.of(BeetleLarva::new, MobCategory.CREATURE).sized(0.9F, 0.5F));
		BLACK_ANT = register("black_ant", EntityType.Builder.of(BlackAnt::new, MobCategory.CREATURE).sized(0.9F, 0.4F));
		BLACK_WIDOW = register("black_widow", EntityType.Builder.of(BlackWidow::new, MobCategory.MONSTER).sized(0.7F, 0.325F).fireImmune());
		BOG_MAW = register("bog_maw", EntityType.Builder.of(BogMaw::new, MobCategory.MONSTER).sized(1.0F, 0.8F).fireImmune());
		BOMBARDIER_BEETLE = register("bombardier_beetle", EntityType.Builder.of(BombardierBeetle::new, MobCategory.MONSTER).sized(1.9F, 0.9F));
		BOMBARDIER_BEETLE_LARVA = register("bombardier_beetle_larva", EntityType.Builder.of(BombardierBeetleLarva::new, MobCategory.MONSTER).sized(0.9F, 0.5F));
		BOT_FLY = register("bot_fly", EntityType.Builder.of(BotFly::new, MobCategory.MONSTER).sized(0.9F, 0.75F));
		BOT_FLY_LARVA = register("bot_fly_larva", EntityType.Builder.of(BotFlyLarva::new, MobCategory.MONSTER).sized(0.5F, 0.2F).fireImmune());
		CENTIPEDE = register("centipede", EntityType.Builder.of(Centipede::new, MobCategory.MONSTER).sized(0.3125F, 0.3125F));
		CHAMELEON_TICK = register("chameleon_tick", EntityType.Builder.of(ChameleonTick::new, MobCategory.MONSTER).sized(1.0F, 1.0F));
		CICADA = register("cicada", EntityType.Builder.of(Cicada::new, MobCategory.CREATURE).sized(1.0F, 0.4F));
		CROP_WEEVIL = register("crop_weevil", EntityType.Builder.of(CropWeevil::new, MobCategory.CREATURE).sized(1F, 0.5F));
		CRUSHROOM = register("crushroom", EntityType.Builder.of(Crushroom::new, MobCategory.MONSTER).sized(2.5F, 3F));
		DRAGON_FLY = register("dragon_fly", EntityType.Builder.of(Dragonfly::new, MobCategory.MONSTER).sized(2.5F, 1.0F).fireImmune());
		FIRE_ANT = register("fire_ant", EntityType.Builder.of(FireAnt::new, MobCategory.MONSTER).sized(0.75F, 0.25F));
		FIRE_ANT_SOLDIER = register("fire_ant_soldier", EntityType.Builder.of(FireAntSoldier::new, MobCategory.MONSTER).sized(0.9F, 0.5F));
		FLY = register("fly", EntityType.Builder.of(Fly::new, MobCategory.MONSTER).sized(0.5F, 0.45F));
		FUNGAL_WEEVIL = register("fungal_weevil", EntityType.Builder.of(FungalWeevil::new, MobCategory.CREATURE).sized(1F, 0.5F));
		GOO_BALL = register("goo_ball", EntityType.Builder.<GooBall>of(GooBall::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F));
		GLOW_WORM = register("glow_worm", EntityType.Builder.of(GlowWorm::new, MobCategory.CREATURE).sized(1.5F, 0.5F));
		GRASSHOPPER = register("grasshopper", EntityType.Builder.of(Grasshopper::new, MobCategory.CREATURE).sized(1.3F, 0.75F));
		HONEY_POT_ANT = register("honey_pot_ant", EntityType.Builder.of(HoneyPotAnt::new, MobCategory.CREATURE).sized(0.9F, 0.4F));
		JUMPING_SPIDER = register("jumping_spider", EntityType.Builder.of(JumpingSpider::new, MobCategory.CREATURE).sized(0.7F, 0.5F));
		LAVA_WEB_SPIDER = register("lava_web_spider", EntityType.Builder.of(LavaWebSpider::new, MobCategory.MONSTER).sized(3F, 1.5F).fireImmune());
		LEECH = register("leech", EntityType.Builder.of(Leech::new, MobCategory.MONSTER).sized(0.5F, 0.25F));
		LOCUST = register("locust", EntityType.Builder.of(Locust::new, MobCategory.MONSTER).sized(2F, 1F));
		MAGMA_CRAWLER = register("magma_crawler", EntityType.Builder.of(MagmaCrawler::new, MobCategory.MONSTER).sized(0.9F, 0.9F));
		MIDGE_SWARM = register("midge_swarm", EntityType.Builder.of(MidgeSwarm::new, MobCategory.MONSTER).sized(0.9F, 0.9F));
		MONEY_SPIDER = register("money_spider", EntityType.Builder.of(MoneySpider::new, MobCategory.MONSTER).sized(0.6F, 0.4F).fireImmune());
		MOSQUITO = register("mosquito", EntityType.Builder.of(Mosquito::new, MobCategory.MONSTER).sized(0.9F, 1.3F));
		MOTH = register("moth", EntityType.Builder.of(Moth::new, MobCategory.MONSTER).sized(1.8F, 0.5F));
		POISON_JET = register("poison_jet", EntityType.Builder.of(PoisonJet::new, MobCategory.MONSTER).sized(0.7F, 0.7F));
		POND_SKATER = register("pond_skater", EntityType.Builder.of(PondSkater::new, MobCategory.CREATURE).sized(1.0F, 1.0F));
		PRAYING_MANTIS = register("praying_mantis", EntityType.Builder.of(PrayingMantis::new, MobCategory.CREATURE).sized(2.0F, 2.5F).fireImmune());
		PUNCHROOM = register("punchroom", EntityType.Builder.of(Punchroom::new, MobCategory.MONSTER).sized(1F, 1F).fireImmune());
		RHINO_BEETLE = register("rhino_beetle", EntityType.Builder.of(RhinoBeetle::new, MobCategory.CREATURE).sized(2.3F, 1.4F));
		SCORPION = register("scorpion", EntityType.Builder.of(Scorpion::new, MobCategory.MONSTER).sized(2.0F, 2.0F).fireImmune());
		SCYTODES = register("scytodes", EntityType.Builder.of(Scytodes::new, MobCategory.MONSTER).sized(2F, 1F).fireImmune());
		SOLIFUGE = register("solifuge", EntityType.Builder.of(Solifuge::new, MobCategory.MONSTER).sized(2.5F, 1.25F));
		STAG_BEETLE = register("stag_beetle", EntityType.Builder.of(StagBeetle::new, MobCategory.CREATURE).sized(2.5F, 1.2F));
		TARANTULA = register("tarantula", EntityType.Builder.of(Tarantula::new, MobCategory.MONSTER).sized(1.3F, 0.6F));
		TARANTULA_MINI_BOSS = register("tarantula_mini_boss", EntityType.Builder.of(TarantulaMiniBoss::new, MobCategory.MONSTER).sized(4.0F, 1.2F));
		TARANTULA_EGG = register("tarantula_egg", EntityType.Builder.of(TarantulaEgg::new, MobCategory.MISC).sized(0.7F, 0.7F));
		THROWN_BLOCK_AS_ITEM = register("thrown_block_as_item", EntityType.Builder.<ThrownBlockAsItem>of(ThrownBlockAsItem::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F));
		TITAN_BEETLE = register("titan_beetle", EntityType.Builder.of(TitanBeetle::new, MobCategory.CREATURE).sized(2.5F, 1.2F));
		UMBER_GOLEM = register("umber_golem", EntityType.Builder.of(UmberGolem::new, MobCategory.MONSTER).sized(1.0F, 1.0F).fireImmune());
		VELVET_WORM = register("velvet_worm", EntityType.Builder.of(VelvetWorm::new, MobCategory.MONSTER).sized(0.3125F, 0.3125F));
		WASP = register("wasp", EntityType.Builder.of(Wasp::new, MobCategory.MONSTER).sized(0.5F, 0.4F));
		WOODLOUSE = register("woodlouse", EntityType.Builder.of(Woodlouse::new, MobCategory.CREATURE).sized(1.0F, 0.3F));
		WORKER_BEE = register("worker_bee", EntityType.Builder.of(WorkerBee::new, MobCategory.CREATURE).sized(0.5F, 0.5F));
		ZOMBIE_ANT = register("zombie_ant", EntityType.Builder.of(ZombieAnt::new, MobCategory.MONSTER).sized(1.5F, 0.75F));
		ZOMBIE_ANT_SOLDIER = register("zombie_ant_soldier", EntityType.Builder.of(ZombieAntSoldier::new, MobCategory.MONSTER).sized(1.75F, 0.75F));
	}

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, EntityType.Builder<T> builder) {
		return ENTITY_TYPES.register(name, () -> builder.build(createKey(name)));
	}

	private static ResourceKey<EntityType<?>> createKey(String name) {
		return ResourceKey.create(Registries.ENTITY_TYPE, Erebus.prefix(name));
	}
}
