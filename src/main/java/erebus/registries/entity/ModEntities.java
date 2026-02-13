package erebus.registries.entity;

import erebus.Erebus;
import erebus.entity.*;
import erebus.entity.projectile.AmberStar;
import erebus.entity.projectile.GooBall;
import erebus.entity.projectile.ThrownBlockAsItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Erebus.MODID);

	public static ResourceKey<EntityType<?>> AMBER_STAR_KEY = createKey("amber_star");
	public static ResourceKey<EntityType<?>> ANIMATED_BLOCK_KEY = createKey("animated_block");
	public static ResourceKey<EntityType<?>> ANTLION_KEY = createKey("antlion");
	public static ResourceKey<EntityType<?>> ANTLION_BOSS_KEY = createKey("antlion_boss");
	public static ResourceKey<EntityType<?>> BED_BUG_KEY = createKey("bed_bug");
	public static ResourceKey<EntityType<?>> BEETLE_KEY = createKey("beetle");
	public static ResourceKey<EntityType<?>> BEETLE_LARVA_KEY = createKey("beetle_larva");
	public static ResourceKey<EntityType<?>> BLACK_ANT_KEY = createKey("black_ant");
	public static ResourceKey<EntityType<?>> BLACK_WIDOW_KEY = createKey("black_widow");
	public static ResourceKey<EntityType<?>> BOMBARDIER_BEETLE_KEY = createKey("bombardier_beetle");
	public static ResourceKey<EntityType<?>> BOMBARDIER_BEETLE_LARVA_KEY = createKey("bombardier_beetle_larva");
	public static ResourceKey<EntityType<?>> BOT_FLY_KEY = createKey("bot_fly");
	public static ResourceKey<EntityType<?>> BOT_FLY_LARVA_KEY = createKey("bot_fly_larva");
	public static ResourceKey<EntityType<?>> CENTIPEDE_KEY = createKey("centipede");
	public static ResourceKey<EntityType<?>> CROP_WEEVIL_KEY = createKey("crop_weevil");
	public static ResourceKey<EntityType<?>> DRAGON_FLY_KEY = createKey("dragon_fly");
	public static ResourceKey<EntityType<?>> FLY_KEY = createKey("fly");
	public static ResourceKey<EntityType<?>> FUNGAL_WEEVIL_KEY = createKey("fungal_weevil");
	public static ResourceKey<EntityType<?>> GOO_BALL_KEY = createKey("goo_ball");
	public static ResourceKey<EntityType<?>> GRASSHOPPER_KEY = createKey("grasshopper");
	public static ResourceKey<EntityType<?>> HONEY_POT_ANT_KEY = createKey("honey_pot_ant");
	public static ResourceKey<EntityType<?>> LAVA_WEB_SPIDER_KEY = createKey("lava_web_spider");
	public static ResourceKey<EntityType<?>> LOCUST_KEY = createKey("locust");
	public static ResourceKey<EntityType<?>> MONEY_SPIDER_KEY = createKey("money_spider");
	public static ResourceKey<EntityType<?>> MOTH_KEY = createKey("moth");
	public static ResourceKey<EntityType<?>> PUNCHROOM_KEY = createKey("punchroom");
	public static ResourceKey<EntityType<?>> SCYTODES_KEY = createKey("scytodes");
	public static ResourceKey<EntityType<?>> THROWN_BLOCK_AS_ITEM_KEY = createKey("thrown_block_as_item");
	public static ResourceKey<EntityType<?>> VELVET_WORM_KEY = createKey("velvet_worm");
	public static ResourceKey<EntityType<?>> WASP_KEY = createKey("wasp");
	public static ResourceKey<EntityType<?>> WORKER_BEE_KEY = createKey("worker_bee");
	public static ResourceKey<EntityType<?>> ZOMBIE_ANT_KEY = createKey("zombie_ant");
	public static ResourceKey<EntityType<?>> ZOMBIE_ANT_SOLDIER_KEY = createKey("zombie_ant_soldier");

    public static final DeferredHolder<EntityType<?>, EntityType<AmberStar>> AMBER_STAR = register("amber_star", EntityType.Builder.<AmberStar>of(AmberStar::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F), AMBER_STAR_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<AnimatedBlock>> ANIMATED_BLOCK = register("animated_block", EntityType.Builder.of(AnimatedBlock::new, MobCategory.MISC).fireImmune().sized(1F, 1.25F).clientTrackingRange(4).updateInterval(10), ANIMATED_BLOCK_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Antlion>> ANTLION = register("antlion", EntityType.Builder.of(Antlion::new, MobCategory.MONSTER).sized(1.9F, 0.9F).fireImmune(), ANTLION_KEY);
	public static final DeferredHolder<EntityType<?>, EntityType<AntlionBoss>> ANTLION_BOSS = register("antlion_boss", EntityType.Builder.of(AntlionBoss::new, MobCategory.MONSTER).sized(6.0F, 2.0F).fireImmune(), ANTLION_BOSS_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<BedBug>> BED_BUG = register("bed_bug", EntityType.Builder.of(BedBug::new, MobCategory.MONSTER).sized(0.75F, 0.6F), BED_BUG_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Beetle>> BEETLE = register("beetle", EntityType.Builder.of(Beetle::new, MobCategory.CREATURE).sized(1.6F, 0.9F), BEETLE_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<BeetleLarva>> BEETLE_LARVA = register("beetle_larva", EntityType.Builder.of(BeetleLarva::new, MobCategory.CREATURE).sized(0.9F, 0.5F), BEETLE_LARVA_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<BlackAnt>> BLACK_ANT = register("black_ant", EntityType.Builder.of(BlackAnt::new, MobCategory.CREATURE).sized(0.9F, 0.4F), BLACK_ANT_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<BlackWidow>> BLACK_WIDOW = register("black_widow", EntityType.Builder.of(BlackWidow::new, MobCategory.MONSTER).sized(0.7F, 0.325F).fireImmune(), BLACK_WIDOW_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<BombardierBeetle>> BOMBARDIER_BEETLE = register("bombardier_beetle", EntityType.Builder.of(BombardierBeetle::new, MobCategory.MONSTER).sized(1.9F, 0.9F), BOMBARDIER_BEETLE_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<BombardierBeetleLarva>> BOMBARDIER_BEETLE_LARVA = register("bombardier_beetle_larva", EntityType.Builder.of(BombardierBeetleLarva::new, MobCategory.MONSTER).sized(0.9F, 0.5F), BOMBARDIER_BEETLE_LARVA_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<BotFly>> BOT_FLY = register("bot_fly", EntityType.Builder.of(BotFly::new, MobCategory.MONSTER).sized(0.9F, 0.75F), BOT_FLY_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<BotFlyLarva>> BOT_FLY_LARVA = register("bot_fly_larva", EntityType.Builder.of(BotFlyLarva::new, MobCategory.MONSTER).sized(0.5F, 0.2F).fireImmune(), BOT_FLY_LARVA_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Centipede>> CENTIPEDE = register("centipede", EntityType.Builder.of(Centipede::new, MobCategory.MONSTER).sized(0.3125F, 0.3125F), CENTIPEDE_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<CropWeevil>> CROP_WEEVIL = register("crop_weevil", EntityType.Builder.of(CropWeevil::new, MobCategory.CREATURE).sized(1F, 0.5F), CROP_WEEVIL_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Dragonfly>> DRAGON_FLY = register("dragon_fly", EntityType.Builder.of(Dragonfly::new, MobCategory.MONSTER).sized(2.5F, 1.0F).fireImmune(), DRAGON_FLY_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Fly>> FLY = register("fly", EntityType.Builder.of(Fly::new, MobCategory.MONSTER).sized(0.5F, 0.45F), FLY_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<FungalWeevil>> FUNGAL_WEEVIL = register("fungal_weevil", EntityType.Builder.of(FungalWeevil::new, MobCategory.CREATURE).sized(1F, 0.5F), FUNGAL_WEEVIL_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<GooBall>> GOO_BALL = register("goo_ball", EntityType.Builder.<GooBall>of(GooBall::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F), GOO_BALL_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Grasshopper>> GRASSHOPPER = register("grasshopper", EntityType.Builder.of(Grasshopper::new, MobCategory.CREATURE).sized(1.3F, 0.75F), GRASSHOPPER_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<HoneyPotAnt>> HONEY_POT_ANT = register("honey_pot_ant", EntityType.Builder.of(HoneyPotAnt::new, MobCategory.CREATURE).sized(0.9F, 0.4F), HONEY_POT_ANT_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<LavaWebSpider>> LAVA_WEB_SPIDER = register("lava_web_spider", EntityType.Builder.of(LavaWebSpider::new, MobCategory.MONSTER).sized(3F, 1.5F).fireImmune(), LAVA_WEB_SPIDER_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Locust>> LOCUST = register("locust", EntityType.Builder.of(Locust::new, MobCategory.MONSTER).sized(2F, 1F), LOCUST_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<MoneySpider>> MONEY_SPIDER = register("money_spider", EntityType.Builder.of(MoneySpider::new, MobCategory.MONSTER).sized(0.6F, 0.4F).fireImmune(), MONEY_SPIDER_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Moth>> MOTH = register("moth", EntityType.Builder.of(Moth::new, MobCategory.MONSTER).sized(1.8F, 0.5F), MOTH_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Punchroom>> PUNCHROOM = register("punchroom", EntityType.Builder.of(Punchroom::new, MobCategory.MONSTER).sized(1F, 1F).fireImmune(), PUNCHROOM_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Scytodes>> SCYTODES = register("scytodes", EntityType.Builder.of(Scytodes::new, MobCategory.MONSTER).sized(2F, 1F).fireImmune(), SCYTODES_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownBlockAsItem>> THROWN_BLOCK_AS_ITEM = register("thrown_block_as_item", EntityType.Builder.<ThrownBlockAsItem>of(ThrownBlockAsItem::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F), THROWN_BLOCK_AS_ITEM_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<VelvetWorm>> VELVET_WORM = register("velvet_worm", EntityType.Builder.of(VelvetWorm::new, MobCategory.MONSTER).sized(0.3125F, 0.3125F), VELVET_WORM_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<Wasp>> WASP = register("wasp", EntityType.Builder.of(Wasp::new, MobCategory.MONSTER).sized(0.5F, 0.4F), WASP_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<WorkerBee>> WORKER_BEE = register("worker_bee", EntityType.Builder.of(WorkerBee::new, MobCategory.CREATURE).sized(0.5F, 0.5F), WORKER_BEE_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<ZombieAnt>> ZOMBIE_ANT = register("zombie_ant", EntityType.Builder.of(ZombieAnt::new, MobCategory.MONSTER).sized(1.5F, 0.75F), ZOMBIE_ANT_KEY);
    public static final DeferredHolder<EntityType<?>, EntityType<ZombieAntSoldier>> ZOMBIE_ANT_SOLDIER = register("zombie_ant_soldier", EntityType.Builder.of(ZombieAntSoldier::new, MobCategory.MONSTER).sized(1.75F, 0.75F), ZOMBIE_ANT_SOLDIER_KEY);

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, EntityType.Builder<T> builder, ResourceKey<EntityType<?>> key) {
		return ENTITY_TYPES.register(name, () -> builder.build(key));
	}

	private static ResourceKey<EntityType<?>> createKey(String name) {
		return ResourceKey.create(Registries.ENTITY_TYPE, Erebus.prefix(name));
	}
}
