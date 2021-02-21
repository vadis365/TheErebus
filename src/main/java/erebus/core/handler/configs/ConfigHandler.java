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
	public boolean spawnPortalMobs, bombardierBlockDestroy, randomNames, playCustomSongs, graveMarker, bioluminescence, glowshrooms, generateVents, allowRespawning, netherWidows, biomeFogColours, falbackAllowHostiles, fallbackAllowPassives;
	public boolean mossGen, mouldGen, mossSpread, mouldSpread, dragonflyGrab, scorpionGrab, petrifiedQuartzGen;
	public static boolean smoothBedrock;
	public static String[] WOA_BLACKLISTED_BLOCKS;
	public float hangingWebHardness;
	public boolean disableThaumcraft = false, disableFMP = false;
	public float hammer_renderSize, getHammer_renderSizeChargedMultiplier, hammer_efficiency, hammer_damage;
	public int hammer_harvestLevel, hammer_maxUses, hammer_enchantability;

	public final String[] usedCategories = { Configuration.CATEGORY_GENERAL, "Biomes", "Ores", "Integration", "Mob Modifiers", "Wand of Animation", "Quake Hammer"  };

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
		generateVents = config.get(Configuration.CATEGORY_GENERAL, "Generate natural swap vents", true).getBoolean(true);
		netherWidows = config.get(Configuration.CATEGORY_GENERAL, "Spawn Black Widows in Nether", true).getBoolean(true);
		smoothBedrock = config.get(Configuration.CATEGORY_GENERAL, "Erebus Generates with Flat Bedrock Layers", false).getBoolean(false);
		mossGen = config.get(Configuration.CATEGORY_GENERAL, "Add Moss to World generation", true).getBoolean(true);
		mouldGen = config.get(Configuration.CATEGORY_GENERAL, "Add Mould to World generation", true).getBoolean(true);
		mossSpread = config.get(Configuration.CATEGORY_GENERAL, "Moss will naturally Spread over time", true).getBoolean(true);
		mouldSpread = config.get(Configuration.CATEGORY_GENERAL, "Mould will naturally Spread over time", true).getBoolean(true);
		petrifiedQuartzGen  = config.get(Configuration.CATEGORY_GENERAL, "Add Petrified Quartz to World generation", true).getBoolean(true);
		hangingWebHardness = config.getFloat("Hanging Web hardness", Configuration.CATEGORY_GENERAL, 4.0F, 0F, Float.MAX_VALUE, "");

		mobAttackDamageMultiplier = config.get("Mob Modifiers", "Mob Attack Damage Multipier", 1).getInt(1);
		mobHealthMultipier = config.get("Mob Modifiers", "Mob Health Multiplier", 1).getInt(1);
		dragonflyGrab = config.get("Mob Modifiers", "Dragonflies Grab Players", true).getBoolean(true);
		scorpionGrab = config.get("Mob Modifiers", "Scorpions Grab Players", true).getBoolean(true);

		WOA_BLACKLISTED_BLOCKS = config.getStringList("Blocks ignored by Wand", "Wand of Animation", new String[] { "minecraft:obsidian"}, "For Sanity");
		
		fallbackAllowHostiles = config.get(Configuration.CATEGORY_GENERAL, "Allow hostile spawning in Erebus dimension when world provider fails", false).getBoolean(false);
		fallbackAllowPassives = config.get(Configuration.CATEGORY_GENERAL, "Allow passive spawning in Erebus dimension when world provider fails", false).getBoolean(false);

		//QUAKE HAMMER
		hammer_renderSize = config.getFloat("Quake Hammer render size", "Quake Hammer", 1.75F, 0F, Float.MAX_VALUE, "");
		hammer_renderSize = config.getFloat("Quake Hammer charged render size", "Quake Hammer", 0.03F, 0F, Float.MAX_VALUE, "");
		hammer_harvestLevel = config.get("Quake Hammer", "Quake Hammer harvest level", 0,"", 0, 3).getInt();
		hammer_maxUses = config.get("Quake Hammer", "Quake Hammer max uses", 863,"", 0, Integer.MAX_VALUE).getInt();
		hammer_efficiency = config.getFloat("Quake Hammer efficiency", "Quake Hammer", 1.0F, 0F, Float.MAX_VALUE, "");
		hammer_damage = config.getFloat("Quake Hammer damage", "Quake Hammer", 10F, 0F, Float.MAX_VALUE, "");
		hammer_enchantability = config.get("Quake Hammer", "Quake Hammer enchantability", 18,"", 0, Integer.MAX_VALUE).getInt();


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
