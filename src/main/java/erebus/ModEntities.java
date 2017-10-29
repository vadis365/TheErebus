package erebus;


import erebus.api.ErebusAPI;
import erebus.core.handler.configs.ConfigHandler;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityAntlionBoss;
import erebus.entity.EntityAntlionMiniBoss;
import erebus.entity.EntityBedBug;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackAnt;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBombardierBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityBotFlyLarva;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityCicada;
import erebus.entity.EntityCropWeevil;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFireAntSoldier;
import erebus.entity.EntityFly;
import erebus.entity.EntityGasVent;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGooBall;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityHoneyPotAnt;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityLavaWebSpider;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMidgeSwarm;
import erebus.entity.EntityMoneySpider;
import erebus.entity.EntityMoth;
import erebus.entity.EntityPondSkater;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntitySolifugeSmall;
import erebus.entity.EntityStagBeetle;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityTarantulaBaby;
import erebus.entity.EntityThrownSand;
import erebus.entity.EntityTitanBeetle;
import erebus.entity.EntityUmberGolemDungeonTypes;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;
import erebus.entity.EntityWebSling;
import erebus.entity.EntityWoodlouse;
import erebus.entity.EntityWoodlouseBall;
import erebus.entity.EntityZombieAnt;
import erebus.entity.EntityZombieAntSoldier;
import erebus.lib.Reference;
import erebus.preserved.PreservableEntityRegistry.EntityDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void init() {
		// Entity registrations
/*		
		registerEntity(5, EntityMosquito.class, "mosquito", -13816034, -14803180);

		registerEntity(23, EntityWorkerBee.class, "worker_bee", 0xFFA200, 0x000000);

		registerEntity(26, EntityBloodSnail.class, "blood_snail", 0x634430, 0xFF0000);

		registerEntity(29, EntityCrushling.class, "crushling", 0xFFFFFF, 0xFF0000);

		registerEntity(39, EntityPunchroom.class, "punchroom", 0xFFFFFF, 0xFF0000);
		registerEntity(40, EntityCrushroom.class, "crushroom", 0xFFFFFF, 0xFF0000);

		registerEntity(43, EntityTarantulaMiniboss.class, "tarantula_mini_boss", 0x000000, 0xE82066);

		registerEntity(46, EntityLeech.class, "leech", 0x804E3D, 0x3D6F80);
		registerEntity(47, EntitySnapper.class, "snapper", 0x804E3D, 0x3D6F80);
		registerEntity(48, EntityWisp.class, "wisp", 0x804E3D, 0x3D6F80);
		registerEntity(49, EntityMagmaCrawler.class, "magma_crawler", 0xFF0000, 0xFFEE00);
	
		registerEntity(53, EntityUmberGolem.class, "umber_golem");
		registerEntity(54, EntityFireResistent.class, "fire_resistent_item");
		registerEntity(55, EntityMucusBombPrimed.class, "mucus_bomb_primed");

		registerEntity(70, EntityWaspDagger.class, "wasp_dagger");

		registerEntity(73, EntityExtractedBlock.class, "extracted_block");

		registerEntity(76, EntitySporeBall.class, "spore_ball");
		registerEntity(77, EntityTarantulaEgg.class, "tarantula_egg"); 
		registerEntity(78, EntityPoisonJet.class, "poison_jet");

		registerEntity(81, EntityPreservedBlock.class, "preserved_block");
		registerEntity(82, EntitySporeJet.class, "spore_jet");
		registerEntity(83, EntityArmchairMount.class, "armchair_mount");
*/
		registerEntity(0, EntityBeetleLarva.class, "beetle_larva", -1251634, -13032944);
		registerEntity(1, EntityWasp.class, "wasp", -256, -16382458);
		registerEntity(2, EntityCentipede.class, "centipede", -13565952, -92160);
		registerEntity(3, EntityBeetle.class, "beetle", -12116973, -5938366);
		registerEntity(4, EntityFly.class, "fly", -13165534, -6750208);

		registerEntity(6, EntityTarantula.class, "tarantula", 0x000000, 0xE82066);
		registerEntity(7, EntityBotFly.class, "bot_fly", -6750208, -13165534);
		registerEntity(8, EntityScorpion.class, "scorpion", 0xFFA200, 0xFFDB9C);
		registerEntity(9, EntitySolifuge.class, "solifuge", 0xFFDB9C, 0xFFA200);
		registerEntity(10, EntityGrasshopper.class, "grasshopper", 0x06B900, 0x5FFF5F);
		registerEntity(11, EntityLocust.class, "locust", 0x5FFF5F, 0x06B900);
		registerEntity(12, EntityMoth.class, "moth", 0x00FFDD, 0xFBFFA8);
		registerEntity(13, EntityRhinoBeetle.class, "rhinoBeetle", 0x222222, 0x747474);
		registerEntity(14, EntityAntlion.class, "antlion", 0x000000, 0xFFFFFF);
		registerEntity(15, EntityBlackWidow.class, "black_widow", 0x000000, 0xFF0000);
		registerEntity(16, EntityGlowWorm.class, "glow_worm", 0xFFFF00, 0xFFFFFF);
		registerEntity(17, EntityBombardierBeetle.class, "bombardier_beetle", 0xFFEEFF, 0x9E0E0E);
		registerEntity(18, EntityScytodes.class, "scytodes", 0x0B4D49, 0xFFFFFF);
		registerEntity(19, EntityMoneySpider.class, "money_spider", 0xF5C400, 0x0B4D49);
		registerEntity(20, EntityPrayingMantis.class, "praying_mantis", 0x06B900, 0x06B900);
		registerEntity(21, EntityJumpingSpider.class, "jumping_spider", 0xE82066, 0x06B900);
		registerEntity(22, EntityFireAnt.class, "fire_ant", 0xFF0000, 0xFFEE00);

		registerEntity(24, EntityVelvetWorm.class, "velvet_worm", 0x3449EB, 0xF22C5A);
		registerEntity(25, EntityDragonfly.class, "dragon_fly", 0x2CF2C4, 0x2CF2C4);

		registerEntity(27, EntityTitanBeetle.class, "titan_beetle", 0xF09071, 0x000000);
		registerEntity(28, EntityBotFlyLarva.class, "bot_fly_larva", -6750208, -13165534);

		registerEntity(30, EntityCropWeevil.class, "crop_weevil", 0x000000, 0xFFA200);
		registerEntity(31, EntityWoodlouse.class, "woodlouse", 0x747474, 0x222222);
		registerEntity(32, EntityCicada.class, "cicada", 0x747474, 0xFFA200);
		registerEntity(33, EntityFireAntSoldier.class, "fire_ant_soldier", 0xFF0000, 0xFFEE00);
		registerEntity(34, EntityLavaWebSpider.class, "lava_web_spider", 0xFF0000, 0x06B900);
		registerEntity(35, EntityAntlionMiniBoss.class, "antlion_mini_boss", 0x000000, 0xFFFFFF);
		registerEntity(36, EntityChameleonTick.class, "chameleon_tick", 0x804E3D, 0x3D6F80);
		registerEntity(37, EntitySolifugeSmall.class, "solifuge_small");
		registerEntity(38, EntityMidgeSwarm.class, "midge_swarm", -14803180, -13816034);

		registerEntity(41, EntityBlackAnt.class, "black_ant", 0x000000, 0xF09071);
		registerEntity(42, EntityZombieAnt.class, "zombie_ant", 0x00FE40, 0xF09071);

		registerEntity(44, EntityTarantulaBaby.class, "tarantula_baby", 0x000000, 0xE82066);
		registerEntity(45, EntityPondSkater.class, "pond_skater", -13816034, -14803180);
		
		registerEntity(50, EntityAnimatedBlock.class, "animated_block");
		registerEntity(51, EntityAnimatedChest.class, "animated_chest");
		registerEntity(52, EntityAnimatedBambooCrate.class, "animated_bamboo_crate");

		registerEntity(56, EntityUmberGolemDungeonTypes.class, "umber_golem_idol");
		registerEntity(57, EntityAntlionBoss.class, "antlion_boss", 0x000000, 0xFFFFFF);
		registerEntity(58, EntityHoneyPotAnt.class, "honey_pot_ant", 0xFFA400, 0x000000);
		registerEntity(59, EntityBombardierBeetleLarva.class, "bombardier_beetle_larva", 0xFFEEFF, 0x9E0E0E);
		registerEntity(60, EntityZombieAntSoldier.class, "zombie_ant_soldier", 0x00FE40, 0xF09071);
		registerEntity(61, EntityBedBug.class, "bed_bug");
		registerEntity(62, EntityStagBeetle.class, "stag_beetle", 0x222222, 0x747474);

		registerEntity(71, EntityWebSling.class, "web_sling");

		registerEntity(74, EntityGooBall.class, "goo_ball");
		registerEntity(75, EntityWoodlouseBall.class, "woodlouse_ball");

		registerEntity(79, EntityGasVent.class, "gas_vent");
		registerEntity(80, EntityThrownSand.class, "thrown_sand");

		// Spawn conditions
		if (ConfigHandler.INSTANCE.netherWidows)
			EntityRegistry.addSpawn(EntityBlackWidow.class, 100, 2, 5, EnumCreatureType.MONSTER, Biomes.HELL);
	}

	private static final void registerEntity(int id, Class<? extends Entity> entityClass, String name) {
		registerEntity(id, entityClass, name, null);
	}

	private static final void registerEntity(int id, Class<? extends Entity> entityClass, String name, EntityDimensions dimensions) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), entityClass, name, id, Erebus.INSTANCE, 256, 3, true);
		if (dimensions != null)
			ErebusAPI.preservableEntityRegistry.registerEntity(entityClass, dimensions);
	}

	private static final void registerEntity(int id, Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor) {
		registerEntity(id, entityClass, name, eggBackgroundColor, eggForegroundColor, null);
	}

	private static final void registerEntity(int id, Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor, EntityDimensions dimensions) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), entityClass, name, id, Erebus.INSTANCE, 256, 3, true, eggBackgroundColor, eggForegroundColor);
		if (dimensions != null)
			ErebusAPI.preservableEntityRegistry.registerEntity(entityClass, dimensions);
	}
}