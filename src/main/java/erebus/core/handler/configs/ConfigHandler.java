package erebus.core.handler.configs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import erebus.Erebus;
import erebus.lib.Reference;
import erebus.preserved.PreservableEntityRegistry;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();

	private File configFolder;

	public Configuration config;
	public int erebusDimensionID;
	public int portalCooldown, mobAttackDamageMultiplier, mobHealthMultipier;
	public byte beetleLarvaEating = 0;
	public boolean spawnPortalMobs, bombardierBlockDestroy, randomNames, playCustomSongs, graveMarker, bioluminescence, glowshrooms, moss, lichen, generateVents, allowRespawning, netherWidows, biomeFogColours;
	public static boolean smoothBedrock;
	public static String[] WOA_BLACKLISTED_BLOCKS;

	public boolean disableThaumcraft = false, disableFMP = false;


	/////////
	//MOBS
	////////

	//DRAGONFLY
	public boolean dragonfly_pickupPlayer, dragonfly_attackTogether;
	public double dragonfly_baseHealth, dragonfly_baseDamage, dragonfly_movementSpeed, dragonfly_followRange, dragonfly_armor, dragonfly_armorToughness, dragonfly_knockbackResistance, dragonfly_luck, dragonfly_wanderingSpeed, dragonfly_attackMovementSpeed, dragonfly_speedWater;
	public float dragonfly_width, dragonfly_height, dragonfly_soundVolume, dragonfly_soundPitch;
	public int dragonfly_dropTime, dragonfly_particleCount, dragonfly_spawnMinLightLevel, dragonfly_spawnMinHeight, getDragonfly_spawnMaxHeight, dragonfly_maxInChunk;

	//SCORPION
	public boolean scorpion_pickupPlayer, scorpion_normalPoison, scorpion_hardPoison, scorpion_normalSlowness, scorpion_hardSlowness;
	public int scorpion_normalPoisonDuration, scorpion_hardPoisonDuration, scorpion_normalSlownessDuration, scorpion_hardSlownessDuration;


	/////////
	//ITEMS
	////////

	//QUAKE HAMMER
	public float hammer_renderSize, getHammer_renderSizeChargedMultiplier, hammer_efficiency, hammer_damage;
	public int hammer_harvestLevel, hammer_maxUses, hammer_enchantability;


	/////////
	//BLOCKS
	////////

	//PETRIFIED QUARTZ
	public boolean genPetrifiedQuartz;

	//HANGING WEB
	public float webHardness;

	public final String[] usedCategories = { Configuration.CATEGORY_GENERAL, "Biomes", "Ores", "Integration", "Mob Modifiers", "Wand of Animation", "Dragonfly"};

	public void loadConfig(FMLPreInitializationEvent event) {
		File configFile = event.getSuggestedConfigurationFile();
		configFolder = configFile.getParentFile();
		config = new Configuration(configFile);

		config.load();
		syncConfigs();
	}

	private void syncConfigs() {
		readEntityDimensionsFile();

		erebusDimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt(66);
		portalCooldown = config.get(Configuration.CATEGORY_GENERAL, "Number of seconds before the portal is usable again.", 5).getInt(5);
		allowRespawning = config.get(Configuration.CATEGORY_GENERAL, "Should player re-spawn in dimension", false).getBoolean(false);
	//	spawnPortalMobs = config.get(Configuration.CATEGORY_GENERAL, "Should spawn beetles and larvae in the portal", true).getBoolean(true);
		beetleLarvaEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Beetle larva eating settings", 0, "0 = only wooden blocks except tile entities & logs, 1 = only wooden blocks except logs, 2 = anything", 0, 2).getInt(0);
		bombardierBlockDestroy = config.get(Configuration.CATEGORY_GENERAL, "Bombardier Beetle Block destruction", true, "This will not stop block destruction for player attacks only collided with blocks!").getBoolean(true);
		randomNames = config.get(Configuration.CATEGORY_GENERAL, "Random mob names", true).getBoolean(true);
		playCustomSongs = config.get(Configuration.CATEGORY_GENERAL, "Play erebus songs", true).getBoolean(true);
		graveMarker = config.get(Configuration.CATEGORY_GENERAL, "Block O' Bones Inventory Save", true).getBoolean(true);
		biomeFogColours = config.get(Configuration.CATEGORY_GENERAL, "Coloured Biome Fogs", true).getBoolean(true);
		bioluminescence = config.get(Configuration.CATEGORY_GENERAL, "Glowing bugs emit light", true).getBoolean(true);
		glowshrooms = config.get(Configuration.CATEGORY_GENERAL, "Add Glowshrooms to World generation", true).getBoolean(true);
		moss = config.get(Configuration.CATEGORY_GENERAL, "Add Moss to World generation", true).getBoolean(true);
		lichen = config.get(Configuration.CATEGORY_GENERAL, "Add Lichen to World generation", true).getBoolean(true);
		generateVents = config.get(Configuration.CATEGORY_GENERAL, "Generate natural swap vents", true).getBoolean(true);
		netherWidows = config.get(Configuration.CATEGORY_GENERAL, "Spawn Black Widows in Nether", true).getBoolean(true);
		smoothBedrock = config.get(Configuration.CATEGORY_GENERAL, "Erebus Generates with Flat Bedrock Layers", false).getBoolean(false);

		mobAttackDamageMultiplier = config.get("Mob Modifiers", "Mob Attack Damage Multipier", 1).getInt(1);
		mobHealthMultipier = config.get("Mob Modifiers", "Mob Health Multiplier", 1).getInt(1);

		WOA_BLACKLISTED_BLOCKS = config.getStringList("Blocks ignored by Wand", "Wand of Animation", new String[] { "minecraft:obsidian"}, "For Sanity");


		/////////
		//MOBS
		////////

		//DRAGONFLY
		dragonfly_pickupPlayer = config.get("dragonfly", "Can pickup player", true).getBoolean();
		dragonfly_attackTogether = config.get("dragonfly", "Group together if one is attacked", true).getBoolean();
		dragonfly_baseHealth = config.get("dragonfly", "Base health", 15D, "", 0D, Double.MAX_VALUE).getDouble();
		dragonfly_baseDamage = config.get("dragonfly", "Base damage", 1D, "", 0D, Double.MAX_VALUE).getDouble();
		dragonfly_movementSpeed = config.get("dragonfly", "Movement speed", 1D, "FOR SOME REASON THIS CAN ONLY BE CHANGED UPON NEW WORLD CREATION",0D, Double.MAX_VALUE).getDouble();
		dragonfly_followRange = config.get("dragonfly", "Following range", 32D, "", 0D, Double.MAX_VALUE).getDouble();
		dragonfly_armor = config.get("dragonfly", "Armor (-1: mc default)", -1D, "", -1D, Double.MAX_VALUE).getDouble();
		dragonfly_armorToughness = config.get("dragonfly", "Armor toughness (-1: mc default)", -1D, "", -1D, Double.MAX_VALUE).getDouble();
		dragonfly_knockbackResistance = config.get("dragonfly", "Knockback resistance (-1: mc default)", -1D, "", -1D, Double.MAX_VALUE).getDouble();
		dragonfly_luck = config.get("dragonfly", "Luck (-1: mc default)", -1D, "", -1D, Double.MAX_VALUE).getDouble();
		dragonfly_wanderingSpeed = config.get("dragonfly", "Wandering speed", 1D, "", 0D, Double.MAX_VALUE).getDouble();
		dragonfly_attackMovementSpeed = config.get("dragonfly", "Attack movement speed", 0.5D, "", 0D, Double.MAX_VALUE).getDouble();
		dragonfly_speedWater = config.get("dragonfly", "Movement speed in water", 0.32D, "", 0D, Double.MAX_VALUE).getDouble();
		dragonfly_width = config.getFloat("Dragonfly hitbox width", "dragonfly", 2.5F, 0F, Float.MAX_VALUE, "");
		dragonfly_height = config.getFloat("Dragonfly hitbox height", "dragonfly", 1.0F, 0F, Float.MAX_VALUE, "");
		dragonfly_soundVolume = config.getFloat("Dragonfly sound volume", "dragonfly", 0.3F, 0F, Float.MAX_VALUE, "");
		dragonfly_soundPitch = config.getFloat("Dragonfly sound pitch multiplier", "dragonfly", 0.5F, 0F, Float.MAX_VALUE, "");
		dragonfly_dropTime = config.get("dragonfly", "Player drop time", 20,"", 0, Integer.MAX_VALUE).getInt();
		dragonfly_particleCount = config.get("dragonfly", "Particle count", 20,"", 0, Integer.MAX_VALUE).getInt();
		dragonfly_spawnMinLightLevel = config.get("dragonfly", "Minimum spawn light level", 7,"", 0, Integer.MAX_VALUE).getInt();
		dragonfly_spawnMinHeight = config.get("dragonfly", "Minimum spawn height", 0,"", 0, Integer.MAX_VALUE).getInt();
		getDragonfly_spawnMaxHeight = config.get("dragonfly", "Maximum spawn height", 100,"", 0, Integer.MAX_VALUE).getInt();
		dragonfly_maxInChunk = config.get("dragonfly", "Max dragonflies in one chunk", 6,"", 0, Integer.MAX_VALUE).getInt();

		//SCORPION
		scorpion_pickupPlayer = config.get("scorpion", "Can pickup player", true).getBoolean();
		scorpion_normalPoison = config.get("scorpion", "Poison on normal difficulty", true).getBoolean();
		scorpion_hardPoison = config.get("scorpion", "Poison on hard difficulty", true).getBoolean();
		scorpion_normalSlowness = config.get("scorpion", "Slowness on normal difficulty", false).getBoolean();
		scorpion_hardSlowness = config.get("scorpion", "Slowness on hard difficulty", false).getBoolean();
		scorpion_normalPoisonDuration = config.get("scorpion", "Poison duration on normal difficulty", 100, "", 0, Integer.MAX_VALUE).getInt();
		scorpion_hardPoisonDuration = config.get("scorpion", "Poison duration on hard difficulty", 200, "", 0, Integer.MAX_VALUE).getInt();
		scorpion_normalSlownessDuration = config.get("scorpion", "Slowness duration on normal difficulty", 100, "", 0, Integer.MAX_VALUE).getInt();
		scorpion_hardSlownessDuration = config.get("scorpion", "Slowness duration on hard difficulty", 200, "", 0, Integer.MAX_VALUE).getInt();

		/////////
		//ITEMS
		////////

		//QUAKE HAMMER
		hammer_renderSize = config.getFloat("Quake Hammer render size", "Items", 1.75F, 0F, Float.MAX_VALUE, "");
		hammer_renderSize = config.getFloat("Quake Hammer charged render size", "Items", 0.03F, 0F, Float.MAX_VALUE, "");
		hammer_harvestLevel = config.get("Items", "Quake Hammer harvest level", 0,"", 0, 3).getInt();
		hammer_maxUses = config.get("Items", "Quake Hammer max uses", 863,"", 0, Integer.MAX_VALUE).getInt();
		hammer_efficiency = config.getFloat("Quake Hammer efficiency", "Items", 1.0F, 0F, Float.MAX_VALUE, "");
		hammer_damage = config.getFloat("Quake Hammer damage", "Items", 10F, 0F, Float.MAX_VALUE, "");
		hammer_enchantability = config.get("Items", "Quake Hammer enchantability", 18,"", 0, Integer.MAX_VALUE).getInt();

		/////////
		//BLOCKS
		////////

		//PETRIFIED QUARTZ
		allowRespawning = config.get(Configuration.CATEGORY_GENERAL, "Should Petrified Quartz generate", true).getBoolean(true);

		//HANGING WEB
		webHardness = config.getFloat("Hanging Web hardness", Configuration.CATEGORY_GENERAL, 4.0F, 0F, Float.MAX_VALUE, "");

	//	disableThaumcraft = config.get("Integration", "Disable Thaumcraft integration", false).getBoolean(false);
	//	disableFMP = config.get("Integration", "Disable Forge Multipart integration", false).getBoolean(false);

		if (config.hasChanged())
			config.save();
	}

	public void initOreConfigs() {
		for (OreType oretype : OreType.values())
			oretype.setEnabled(config.get("Ores", "Generate " + oretype.toString().toLowerCase(), oretype.isEnabled()).getBoolean(oretype.isEnabled()));

		if (config.hasChanged())
			config.save();
	}

	private void readEntityDimensionsFile() {
		File file = new File(configFolder, "ErebusEntityDimensions.cfg");
		BufferedReader br = new BufferedReader(new InputStreamReader(Erebus.class.getResourceAsStream("/assets/erebus/default_entity_sizes.cfg")));
		if (!file.exists()) {
			PreservableEntityRegistry.INSTANCE.readFile(br, true);
			PreservableEntityRegistry.INSTANCE.writeConfigFile(file);
		} else
			try {
				PreservableEntityRegistry.INSTANCE.readFile(br, true);
				PreservableEntityRegistry.INSTANCE.readFile(new BufferedReader(new FileReader(file)), false);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (Reference.MOD_ID.equals(event.getModID())) {
			syncConfigs();
			initOreConfigs();
		}
	}
}