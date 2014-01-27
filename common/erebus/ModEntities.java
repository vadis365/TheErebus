package erebus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFireAnt;
import erebus.entity.EntityFirebrat;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityJumpingSpider;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMoneySpider;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityMoth;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityScorpion;
import erebus.entity.EntityScytodes;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityUmberGolem;
import erebus.entity.EntityWasp;
import erebus.entity.EntityWaspDagger;
import erebus.entity.EntityWebSling;
import erebus.entity.effect.EntityErebusLightningBolt;
import erebus.item.ItemSpawnEggs;

public class ModEntities {

	public static void init() {
		// Compatibility with old version
		EntityList.stringToClassMapping.put("BeetleLarva - Erebus", EntityBeetleLarva.class);
		EntityList.stringToClassMapping.put("Wasp - Erebus", EntityWasp.class);
		EntityList.stringToClassMapping.put("Centipede - Erebus", EntityCentipede.class);
		EntityList.stringToClassMapping.put("Beetle - Erebus", EntityBeetle.class);
		EntityList.stringToClassMapping.put("Fly - Erebus", EntityFly.class);
		EntityList.stringToClassMapping.put("Mosquito - Erebus", EntityMosquito.class);
		EntityList.stringToClassMapping.put("Tarantula - Erebus", EntityTarantula.class);
		EntityList.stringToClassMapping.put("BotFly - Erebus", EntityBotFly.class);
		EntityList.stringToClassMapping.put("Scorpion - Erebus", EntityScorpion.class);
		EntityList.stringToClassMapping.put("Solifuge - Erebus", EntitySolifuge.class);
		EntityList.stringToClassMapping.put("Grasshopper - Erebus", EntityGrasshopper.class);
		EntityList.stringToClassMapping.put("Locust - Erebus", EntityLocust.class);
		EntityList.stringToClassMapping.put("Moth - Erebus", EntityMoth.class);
		EntityList.stringToClassMapping.put("Firebrat - Erebus", EntityFirebrat.class);
		EntityList.stringToClassMapping.put("Antlion - Erebus", EntityAntlion.class);
		EntityList.stringToClassMapping.put("BlackWidow - Erebus", EntityBlackWidow.class);
		EntityList.stringToClassMapping.put("GlowWorm - Erebus", EntityGlowWorm.class);
		EntityList.stringToClassMapping.put("BombardierBeetle - Erebus", EntityBombardierBeetle.class);
		EntityList.stringToClassMapping.put("Scytodes - Erebus", EntityScytodes.class);
		EntityList.stringToClassMapping.put("MoneySpider - Erebus", EntityMoneySpider.class);
		EntityList.stringToClassMapping.put("PrayingMantis - Erebus", EntityPrayingMantis.class);
		EntityList.stringToClassMapping.put("JumpingSpider - Erebus", EntityJumpingSpider.class);
		EntityList.stringToClassMapping.put("AnimatedBlock", EntityAnimatedBlock.class);
		EntityList.stringToClassMapping.put("AnimatedChest", EntityAnimatedChest.class);
		EntityList.stringToClassMapping.put("AnimatedBambooCrate", EntityAnimatedBambooCrate.class);
		EntityList.stringToClassMapping.put("UmberGolem", EntityUmberGolem.class);
		EntityList.stringToClassMapping.put("WaspDagger", EntityWaspDagger.class);
		EntityList.stringToClassMapping.put("WebSling", EntityWebSling.class);
		EntityList.stringToClassMapping.put("ErebusLightning", EntityErebusLightningBolt.class);
		EntityList.stringToClassMapping.put("FireAnt - Erebus", EntityFireAnt.class);

		// Entity registrations
		registerEntity(0, EntityBeetleLarva.class, "BeetleLarva", -1251634, -13032944);
		registerEntity(1, EntityWasp.class, "Wasp", -256, -16382458);
		registerEntity(2, EntityCentipede.class, "Centipede", -13565952, -92160);
		registerEntity(3, EntityBeetle.class, "Beetle", -12116973, -5938366);
		registerEntity(4, EntityFly.class, "Fly", -13165534, -6750208);
		registerEntity(5, EntityMosquito.class, "Mosquito", -13816034, -14803180);
		registerEntity(6, EntityTarantula.class, "Tarantula", 0x000000, 0xE82066);
		registerEntity(7, EntityBotFly.class, "BotFly", -6750208, -13165534);
		registerEntity(8, EntityScorpion.class, "Scorpion", 0xFFA200, 0xFFDB9C);
		registerEntity(9, EntitySolifuge.class, "Solifuge", 0xFFDB9C, 0xFFA200);
		registerEntity(10, EntityGrasshopper.class, "Grasshopper", 0x06B900, 0x5FFF5F);
		registerEntity(11, EntityLocust.class, "Locust", 0x5FFF5F, 0x06B900);
		registerEntity(12, EntityMoth.class, "Moth", 0x00FFDD, 0xFBFFA8);
		registerEntity(13, EntityFirebrat.class, "Firebrat", 0xFF0000, 0xFF8800);
		registerEntity(14, EntityAntlion.class, "Antlion", 0x000000, 0xFFFFFF);
		registerEntity(15, EntityBlackWidow.class, "BlackWidow", 0x000000, 0xFF0000);
		registerEntity(16, EntityGlowWorm.class, "GlowWorm", 0xFFFF00, 0xFFFFFF);
		registerEntity(17, EntityBombardierBeetle.class, "BombardierBeetle", 0xFFEEFF, 0x9E0E0E);
		registerEntity(18, EntityScytodes.class, "Scytodes", 0x0B4D49, 0xFFFFFF);
		registerEntity(19, EntityMoneySpider.class, "MoneySpider", 0xF5C400, 0x0B4D49);
		registerEntity(20, EntityPrayingMantis.class, "PrayingMantis", 0x06B900, 0x06B900);
		registerEntity(21, EntityJumpingSpider.class, "JumpingSpider", 0xE82066, 0x06B900);
		registerEntity(22, EntityFireAnt.class, "FireAnt", 0xFF0000, 0xFFEE00);
		// registerEntity(EntityVelvetWorm.class, "VelvetWorm",
		// EntityRegistry.findGlobalUniqueEntityId(), 894731, 000000);

		registerEntity(50, EntityAnimatedBlock.class, "AnimatedBlock");
		registerEntity(51, EntityAnimatedChest.class, "AnimatedChest");
		registerEntity(52, EntityAnimatedBambooCrate.class, "AnimatedBambooCrate");
		registerEntity(53, EntityUmberGolem.class, "UmberGolem");

		registerEntity(70, EntityWaspDagger.class, "WaspDagger");
		registerEntity(71, EntityWebSling.class, "WebSling");
		registerEntity(72, EntityErebusLightningBolt.class, "ErebusLightning");
		// Spawn conditions
		EntityRegistry.addSpawn(EntityBlackWidow.class, 100, 5, 10, EnumCreatureType.monster, BiomeGenBase.hell);
	}

	private static final void registerEntity(int id, Class<? extends Entity> entityClass, String name) {
		EntityRegistry.registerModEntity(entityClass, name, id, ErebusMod.instance, 256, 1, true);
	}

	private static final void registerEntity(int id, Class<? extends EntityLiving> entityClass, String name, int eggBackgroundColor, int eggForegroundColor) {
		registerEntity(id, entityClass, name);
		ItemSpawnEggs.registerSpawnEgg(entityClass, name, id, eggBackgroundColor, eggForegroundColor);
	}
}