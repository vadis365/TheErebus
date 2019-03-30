package erebus;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import erebus.entity.EntityBogMaw;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBombardierBeetleLarva;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityBotFlyLarva;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityChameleonTick;
import erebus.entity.EntityCicada;
import erebus.entity.EntityCropWeevil;
import erebus.entity.EntityCrushroom;
import erebus.entity.EntityDragonfly;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFireAntSoldier;
import erebus.entity.EntityFireResistent;
import erebus.entity.EntityFly;
import erebus.entity.EntityFungalWeevil;
import erebus.entity.EntityGasVent;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGooBall;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityHoneyPotAnt;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityLavaWebSpider;
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
import erebus.entity.EntitySolifuge;
import erebus.entity.EntitySolifugeSmall;
import erebus.entity.EntitySporeBall;
import erebus.entity.EntityStagBeetle;
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
import erebus.entity.EntityWoodlouse;
import erebus.entity.EntityWoodlouseBall;
import erebus.entity.EntityWorkerBee;
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
	public final static List<Class<? extends EntityLiving>> MOB_LIST = new ArrayList<Class<? extends EntityLiving>>();
	public static void init() {
		// Entity registrations
/*		

		registerEntity(26, EntityBloodSnail.class, "blood_snail", 0x634430, 0xFF0000);

		registerEntity(46, EntityLeech.class, "leech", 0x804E3D, 0x3D6F80);

		registerEntity(83, EntityArmchairMount.class, "armchair_mount");
*/
		registerEntity(0, EntityBeetleLarva.class, "beetle_larva", 0xE5DEC4, 0x472A0F);
		registerEntity(1, EntityWasp.class, "wasp", 0xFECD09, 0x141414);
		registerEntity(2, EntityCentipede.class, "centipede", 0x3C0000, 0xEA0000);
		registerEntity(3, EntityBeetle.class, "beetle", 0x7B4026, 0xAB9A93);
		registerEntity(4, EntityFly.class, "fly", 0x381C22, 0x990000);
		registerEntity(5, EntityMosquito.class, "mosquito", 0x41432D, 0xDA5302);
		registerEntity(6, EntityTarantula.class, "tarantula", 0x0E0E0E, 0xF15A05);
		registerEntity(7, EntityBotFly.class, "bot_fly", 0xEFE2B9, 0x858B95);
		registerEntity(8, EntityScorpion.class, "scorpion", 0xC68C63, 0x4D1805);
		registerEntity(9, EntitySolifuge.class, "solifuge", 0xAD7045, 0x2F0000);
		registerEntity(10, EntityGrasshopper.class, "grasshopper", 0x63A02E, 0xE5D11B);
		registerEntity(11, EntityLocust.class, "locust", 0xA07E2E, 0xEC3200);
		registerEntity(12, EntityMoth.class, "moth", 0x83DB99, 0xACF4E0);
		registerEntity(13, EntityRhinoBeetle.class, "rhino_beetle", 0x252525, 0x4D5400);
		registerEntity(14, EntityAntlion.class, "antlion", 0x958A54, 0xBB4602);
		registerEntity(15, EntityBlackWidow.class, "black_widow", 0x101010, 0xFF0000);
		registerEntity(16, EntityGlowWorm.class, "glow_worm", 0x5A2C12, 0xFFFFBF);
		registerEntity(17, EntityBombardierBeetle.class, "bombardier_beetle", 0x232B98, 0xF15800);
		registerEntity(18, EntityScytodes.class, "scytodes", 0xC2833C, 0x520D06);
		registerEntity(19, EntityMoneySpider.class, "money_spider", 0xC2872F, 0xF9FF00);
		registerEntity(20, EntityPrayingMantis.class, "praying_mantis", 0x439600, 0x67FF10);
		registerEntity(21, EntityJumpingSpider.class, "jumping_spider", 0x0F3A45, 0xCC0000);
		registerEntity(22, EntityFireAnt.class, "fire_ant", 0xFF4900, 0xFFCC00);
		registerEntity(23, EntityWorkerBee.class, "worker_bee", 0xFAAE0E, 0x170F09);
		registerEntity(24, EntityVelvetWorm.class, "velvet_worm", 0x88444B, 0xFFDAC0);
		registerEntity(25, EntityDragonfly.class, "dragon_fly", 0x37A87C, 0xE9E9E9);

		registerEntity(27, EntityTitanBeetle.class, "titan_beetle", 0x915C4D, 0x2C1D19);
		registerEntity(28, EntityBotFlyLarva.class, "bot_fly_larva", 0xE7E0CA, 0xF8D552);
		registerEntity(29, EntityFungalWeevil.class, "fungal_weevil", 0x1E2F66, 0xCBCB00);
		registerEntity(30, EntityCropWeevil.class, "crop_weevil", 0x190E07, 0xAD0202);
		registerEntity(31, EntityWoodlouse.class, "woodlouse", 0x373737, 0x5A5A5A);
		registerEntity(32, EntityCicada.class, "cicada", 0x704E00, 0x88BA25);
		registerEntity(33, EntityFireAntSoldier.class, "fire_ant_soldier", 0xFF7F00, 0x9A0000);
		registerEntity(34, EntityLavaWebSpider.class, "lava_web_spider", 0xD36617, 0x342522);
		registerEntity(35, EntityAntlionMiniBoss.class, "antlion_mini_boss", 0xC8C082, 0xDACD02);
		registerEntity(36, EntityChameleonTick.class, "chameleon_tick", 0x7FFFC5, 0x459791);
		registerEntity(37, EntitySolifugeSmall.class, "solifuge_small");
		registerEntity(38, EntityMidgeSwarm.class, "midge_swarm", 0x333333, 0xFF0000);
		registerEntity(39, EntityPunchroom.class, "punchroom", 0xD6D3C8, 0x947058);
		registerEntity(40, EntityCrushroom.class, "crushroom", 0xD6D3C8, 0x431200);
		registerEntity(41, EntityBlackAnt.class, "black_ant", 0x1E1E1E, 0xFF6600);
		registerEntity(42, EntityZombieAnt.class, "zombie_ant", 0x19370E, 0x00A300);
		registerEntity(43, EntityTarantulaMiniboss.class, "tarantula_mini_boss", 0x0E0E0E, 0x7FBDFF);
		registerEntity(44, EntityTarantulaBaby.class, "tarantula_baby", 0x0E0E0E, 0xD4D407);
		registerEntity(45, EntityPondSkater.class, "pond_skater", 0x6D6F6C, 0xC20000);
		
		registerEntity(47, EntityBogMaw.class, "bog_maw", 0x688039, 0x688039);
		
		registerEntity(49, EntityMagmaCrawler.class, "magma_crawler", 0xD3540F, 0xFFC146);

		registerEntity(50, EntityAnimatedBlock.class, "animated_block");
		registerEntity(51, EntityAnimatedChest.class, "animated_chest");
		registerEntity(52, EntityAnimatedBambooCrate.class, "animated_bamboo_crate");
		registerEntity(53, EntityUmberGolem.class, "umber_golem");
		registerEntity(54, EntityFireResistent.class, "fire_resistent_item");
		registerEntity(55, EntityMucusBombPrimed.class, "mucus_bomb_primed");
		registerEntity(56, EntityUmberGolemDungeonTypes.class, "umber_golem_idol");
		registerEntity(57, EntityAntlionBoss.class, "antlion_boss", 0xDACD02, 0xC8C082);
		registerEntity(58, EntityHoneyPotAnt.class, "honey_pot_ant", 0x310000, 0xC1902E);
		registerEntity(59, EntityBombardierBeetleLarva.class, "bombardier_beetle_larva", 0xE5DEC4, 0x232B98);
		registerEntity(60, EntityZombieAntSoldier.class, "zombie_ant_soldier", 0x19370E, 0xC20000);
		registerEntity(61, EntityBedBug.class, "bed_bug");
		registerEntity(62, EntityStagBeetle.class, "stag_beetle", 0x00000F, 0x000054);

		registerEntity(70, EntityWaspDagger.class, "wasp_dagger");
		registerEntity(71, EntityWebSling.class, "web_sling");

		registerEntity(74, EntityGooBall.class, "goo_ball");
		registerEntity(75, EntityWoodlouseBall.class, "woodlouse_ball");
		registerEntity(76, EntitySporeBall.class, "spore_ball");
		registerEntity(77, EntityTarantulaEgg.class, "tarantula_egg"); 
		registerEntity(78, EntityPoisonJet.class, "poison_jet");
		registerEntity(79, EntityGasVent.class, "gas_vent");
		registerEntity(80, EntityThrownSand.class, "thrown_sand");
		registerEntity(81, EntityPreservedBlock.class, "preserved_block");

		// Spawn conditions
		if (ConfigHandler.INSTANCE.netherWidows)
			EntityRegistry.addSpawn(EntityBlackWidow.class, 100, 2, 5, EnumCreatureType.MONSTER, Biomes.HELL);
	}

	private static final void registerEntity(int id, Class<? extends Entity> entityClass, String name) {
		registerEntity(id, entityClass, "erebus."+ name, null);
	}

	private static final void registerEntity(int id, Class<? extends Entity> entityClass, String name, EntityDimensions dimensions) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), entityClass, name, id, Erebus.INSTANCE, 64, 3, true);
		if (dimensions != null)
			ErebusAPI.preservableEntityRegistry.registerEntity(entityClass, dimensions);
	}

	private static final void registerEntity(int id, Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor) {
		registerEntity(id, entityClass, "erebus."+ name, eggBackgroundColor, eggForegroundColor, null);
	}

	private static final void registerEntity(int id, Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor, EntityDimensions dimensions) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, name), entityClass, name, id, Erebus.INSTANCE, 64, 3, true, eggBackgroundColor, eggForegroundColor);
		MOB_LIST.add(entityClass);
		if (dimensions != null) {
			ErebusAPI.preservableEntityRegistry.registerEntity(entityClass, dimensions);
		}
	}

	public static Class<? extends EntityLiving> getRandomEntityClass() {
		Collections.shuffle(MOB_LIST);
		return MOB_LIST.get(0);
	}
}