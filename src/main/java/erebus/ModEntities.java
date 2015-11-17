package erebus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import erebus.api.ErebusAPI;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityAntlionBoss;
import erebus.entity.EntityAntlionMiniBoss;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackAnt;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBloodSnail;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBombardierBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityBotFlyLarva;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityCicada;
import erebus.entity.EntityCrushling;
import erebus.entity.EntityCrushroom;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityExtractedBlock;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFireAntSoldier;
import erebus.entity.EntityFireResistent;
import erebus.entity.EntityFly;
import erebus.entity.EntityGasVent;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGooBall;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityHoneyPotAnt;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityLavaWebSpider;
import erebus.entity.EntityLeech;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMagmaCrawler;
import erebus.entity.EntityMidgeSwarm;
import erebus.entity.EntityMoneySpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityMoth;
import erebus.entity.EntityMucusBombPrimed;
import erebus.entity.EntityPoisonJet;
import erebus.entity.EntityPondSkater;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityPreservedBlock;
import erebus.entity.EntityPunchroom;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySnapper;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntitySolifugeSmall;
import erebus.entity.EntitySporeBall;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityTarantulaBaby;
import erebus.entity.EntityTarantulaEgg;
import erebus.entity.EntityTarantulaMiniboss;
import erebus.entity.EntityThrownSand;
import erebus.entity.EntityTitanBeetle;
import erebus.entity.EntityUmberGolem;
import erebus.entity.EntityUmberGolemDungeonTypes;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;
import erebus.entity.EntityWaspDagger;
import erebus.entity.EntityWebSling;
import erebus.entity.EntityWheatWeevil;
import erebus.entity.EntityWisp;
import erebus.entity.EntityWoodlouse;
import erebus.entity.EntityWoodlouseBall;
import erebus.entity.EntityWorkerBee;
import erebus.entity.EntityZombieAnt;
import erebus.entity.effect.EntityErebusLightningBolt;
import erebus.item.ItemSpawnEggs;
import erebus.preserved.PreservableEntityRegistry.EntityDimensions;

public class ModEntities {
	public static void init() {
		// Entity registrations
		registerEntity(0, EntityBeetleLarva.class, "beetleLarva", -1251634, -13032944);
		registerEntity(1, EntityWasp.class, "wasp", -256, -16382458);
		registerEntity(2, EntityCentipede.class, "centipede", -13565952, -92160);
		registerEntity(3, EntityBeetle.class, "beetle", -12116973, -5938366);
		registerEntity(4, EntityFly.class, "fly", -13165534, -6750208);
		registerEntity(5, EntityMosquito.class, "mosquito", -13816034, -14803180);
		registerEntity(6, EntityTarantula.class, "tarantula", 0x000000, 0xE82066);
		registerEntity(7, EntityBotFly.class, "botFly", -6750208, -13165534);
		registerEntity(8, EntityScorpion.class, "scorpion", 0xFFA200, 0xFFDB9C);
		registerEntity(9, EntitySolifuge.class, "solifuge", 0xFFDB9C, 0xFFA200);
		registerEntity(10, EntityGrasshopper.class, "grasshopper", 0x06B900, 0x5FFF5F);
		registerEntity(11, EntityLocust.class, "locust", 0x5FFF5F, 0x06B900);
		registerEntity(12, EntityMoth.class, "moth", 0x00FFDD, 0xFBFFA8);
		registerEntity(13, EntityRhinoBeetle.class, "rhinoBeetle", 0x222222, 0x747474);
		registerEntity(14, EntityAntlion.class, "antlion", 0x000000, 0xFFFFFF);
		registerEntity(15, EntityBlackWidow.class, "blackWidow", 0x000000, 0xFF0000);
		registerEntity(16, EntityGlowWorm.class, "glowWorm", 0xFFFF00, 0xFFFFFF);
		registerEntity(17, EntityBombardierBeetle.class, "bombardierBeetle", 0xFFEEFF, 0x9E0E0E);
		registerEntity(18, EntityScytodes.class, "scytodes", 0x0B4D49, 0xFFFFFF);
		registerEntity(19, EntityMoneySpider.class, "moneySpider", 0xF5C400, 0x0B4D49);
		registerEntity(20, EntityPrayingMantis.class, "prayingMantis", 0x06B900, 0x06B900);
		registerEntity(21, EntityJumpingSpider.class, "jumpingSpider", 0xE82066, 0x06B900);
		registerEntity(22, EntityFireAnt.class, "fireAnt", 0xFF0000, 0xFFEE00);
		registerEntity(23, EntityWorkerBee.class, "workerBee", 0xFFA200, 0x000000);
		registerEntity(24, EntityVelvetWorm.class, "velvetWorm", 0x3449EB, 0xF22C5A);
		registerEntity(25, EntityDragonfly.class, "dragonfly", 0x2CF2C4, 0x2CF2C4);
		registerEntity(26, EntityBloodSnail.class, "bloodSnail", 0x634430, 0xFF0000);
		registerEntity(27, EntityTitanBeetle.class, "titanBeetle", 0xF09071, 0x000000);
		registerEntity(28, EntityBotFlyLarva.class, "botFlyLarva");
		registerEntity(29, EntityCrushling.class, "crushling", 0xFFFFFF, 0xFF0000);
		registerEntity(30, EntityWheatWeevil.class, "wheatWeevil", 0x000000, 0xFFA200);
		registerEntity(31, EntityWoodlouse.class, "woodlouse", 0x747474, 0x222222);
		registerEntity(32, EntityCicada.class, "cicada", 0x747474, 0xFFA200);
		registerEntity(33, EntityFireAntSoldier.class, "fireAntSoldier", 0xFF0000, 0xFFEE00);
		registerEntity(34, EntityLavaWebSpider.class, "lavaWebSpider", 0xFF0000, 0x06B900);
		registerEntity(35, EntityAntlionMiniBoss.class, "antlionMiniBoss", 0x000000, 0xFFFFFF);
		registerEntity(36, EntityChameleonTick.class, "chameleonTick", 0x804E3D, 0x3D6F80);
		registerEntity(37, EntitySolifugeSmall.class, "solifugeSmall");
		registerEntity(38, EntityMidgeSwarm.class, "midgeSwarm", -14803180, -13816034);
		registerEntity(39, EntityPunchroom.class, "punchroom", 0xFFFFFF, 0xFF0000);
		registerEntity(40, EntityCrushroom.class, "crushroom", 0xFFFFFF, 0xFF0000);
		registerEntity(41, EntityBlackAnt.class, "blackAnt", 0x000000, 0xF09071);
		registerEntity(42, EntityZombieAnt.class, "zombieAnt", 0x00FE40, 0xF09071);
		registerEntity(43, EntityTarantulaMiniboss.class, "tarantulaminiboss", 0x000000, 0xE82066);
		registerEntity(44, EntityTarantulaBaby.class, "tarantulababy", 0x000000, 0xE82066);
		registerEntity(45, EntityPondSkater.class, "pondskater", -13816034, -14803180);
		registerEntity(46, EntityLeech.class, "leech", 0x804E3D, 0x3D6F80);
		registerEntity(47, EntitySnapper.class, "snapper", 0x804E3D, 0x3D6F80);
		registerEntity(48, EntityWisp.class, "wisp", 0x804E3D, 0x3D6F80);
		registerEntity(49, EntityMagmaCrawler.class, "magmaCrawler", 0xFF0000, 0xFFEE00);
		registerEntity(50, EntityAnimatedBlock.class, "animatedBlock");
		registerEntity(51, EntityAnimatedChest.class, "animatedChest");
		registerEntity(52, EntityAnimatedBambooCrate.class, "animatedBambooCrate");
		registerEntity(53, EntityUmberGolem.class, "umberGolem");
		registerEntity(54, EntityFireResistent.class, "fireResistentItem");
		registerEntity(55, EntityMucusBombPrimed.class, "mucusBombPrimed");
		registerEntity(56, EntityUmberGolemDungeonTypes.class, "umberGolemIdol");
		registerEntity(57, EntityAntlionBoss.class, "antlionBoss", 0x000000, 0xFFFFFF);
		registerEntity(58, EntityHoneyPotAnt.class, "honeyPotAnt", 0xFFA400, 0x000000);
		registerEntity(59, EntityBombardierBeetleLarva.class, "bombardierBeetleLarva", 0xFFEEFF, 0x9E0E0E);

		registerEntity(70, EntityWaspDagger.class, "waspDagger");
		registerEntity(71, EntityWebSling.class, "webSling");
		registerEntity(72, EntityErebusLightningBolt.class, "erebusLightning");
		registerEntity(73, EntityExtractedBlock.class, "extractedBlock");
		registerEntity(74, EntityGooBall.class, "gooBall");
		registerEntity(75, EntityWoodlouseBall.class, "woodlouseBall");
		registerEntity(76, EntitySporeBall.class, "sporeBall");
		registerEntity(77, EntityTarantulaEgg.class, "tarantulaegg");
		registerEntity(78, EntityPoisonJet.class, "poisonjet");
		registerEntity(79, EntityGasVent.class, "gasVent");
		registerEntity(80, EntityThrownSand.class, "thrownSand");
		registerEntity(81, EntityPreservedBlock.class, "preservedBlock");

		// Spawn conditions
		if (ConfigHandler.INSTANCE.netherWidows)
			EntityRegistry.addSpawn(EntityBlackWidow.class, 100, 2, 5, EnumCreatureType.monster, BiomeGenBase.hell);
	}

	private static final void registerEntity(int id, Class<? extends Entity> entityClass, String name) {
		registerEntity(id, entityClass, name, null);
	}

	private static final void registerEntity(int id, Class<? extends Entity> entityClass, String name, EntityDimensions dimensions) {
		EntityRegistry.registerModEntity(entityClass, name, id, Erebus.instance, 256, 3, true);
		if (dimensions != null)
			ErebusAPI.preservableEntityRegistry.registerEntity(entityClass, dimensions);
	}

	private static final void registerEntity(int id, Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor) {
		registerEntity(id, entityClass, name, eggBackgroundColor, eggForegroundColor, null);
	}

	private static final void registerEntity(int id, Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor, EntityDimensions dimensions) {
		registerEntity(id, entityClass, name, dimensions);
		ItemSpawnEggs.registerSpawnEgg(entityClass, name, id, eggBackgroundColor, eggForegroundColor);
	}
}