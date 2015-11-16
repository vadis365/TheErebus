package erebus.core.handler.configs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.Erebus;
import erebus.ModBiomes;
import erebus.lib.Reference;
import erebus.preserved.PreservableEntityRegistry;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();

	private File configFolder;

	public Configuration config;
	public int erebusDimensionID;
	public int portalCooldown;
	public byte beetleLarvaEating = 0;
	public boolean spawnPortalMobs, bombardierBlockDestroy, randomNames, playCustomSongs, coal, iron, gold, lapis, emerald, diamond, diamond_encrusted, jade, petrified_wood, fossil, gneiss, lead, silver, copper, tin, aluminium, alternativePlanks, graveMarker, bioluminescence, glowshrooms, generateVents, allowRespawning, netherWidows;

	public boolean disableThaumcraft = false, disableFMP = false;

	public final String[] usedCategories = { Configuration.CATEGORY_GENERAL, "Biomes", "Ores", "Integration" };

	public void loadConfig(FMLPreInitializationEvent event) {
		File configFile = event.getSuggestedConfigurationFile();
		configFolder = configFile.getParentFile();
		config = new Configuration(configFile);

		config.load();
		syncConfigs();
	}

	private void syncConfigs() {
		readEntityDimensionsFile();

		ModBiomes.undergroundJungleID = config.get("Biomes", "Underground Jungle", 100).getInt(100);
		ModBiomes.volcanicDesertID = config.get("Biomes", "Volcanic Desert", 101).getInt(101);
		ModBiomes.subterraneanSavannahID = config.get("Biomes", "Subterranean Savannah", 102).getInt(102);
		ModBiomes.elysianFieldsID = config.get("Biomes", "Elysian Fields", 103).getInt(103);
		ModBiomes.ulteriorOutbackID = config.get("Biomes", "Ulterior Outback", 104).getInt(104);
		ModBiomes.fungalForestID = config.get("Biomes", "Fungal Forest", 105).getInt(105);
		ModBiomes.fieldsSubForestID = config.get("Biomes", "Fields - Forest", 127).getInt(127);
		ModBiomes.submergedSwampID = config.get("Biomes", "Submerged Swamp", 106).getInt(106);
		/*
		 * ModBiomes.jungleSubLakeID = config.get("Biomes", "Biome ID of Underground Jungle - Lake", 161).getInt(161); ModBiomes.jungleSubAsperGroveID = config.get("Biomes", "Biome ID of Underground Jungle - Asper Grove", 162).getInt(162); ModBiomes.desertSubCharredForestID = config.get("Biomes", "Biome ID of Volcanic Desert - Charred Forest", 163).getInt(163); ModBiomes.savannahSubRockyWastelandID = config.get("Biomes", "Biome ID of Savannah - Rocky Wasteland", 164).getInt(164); ModBiomes.savannahSubAsperGroveID = config.get("Biomes", "Biome ID of Savannah - Asper Grove", 165).getInt(165); ModBiomes.savannahSubSteppeID = config.get("Biomes", "Biome ID of Savannah - Steppe", 166).getInt(166);
		 */

		erebusDimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt(66);
		portalCooldown = config.get(Configuration.CATEGORY_GENERAL, "Number of seconds before the portal is usable again.", 5).getInt(5);
		allowRespawning = config.get(Configuration.CATEGORY_GENERAL, "Should player re-spawn in dimension", false).getBoolean(false);
		spawnPortalMobs = config.get(Configuration.CATEGORY_GENERAL, "Should spawn beetles and larvae in the portal", true).getBoolean(true);
		beetleLarvaEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Beetle larva eating settings", 0, "0 = only wooden blocks except tile entities & logs, 1 = only wooden blocks except logs, 2 = anything", 0, 2).getInt(0);
		bombardierBlockDestroy = config.get(Configuration.CATEGORY_GENERAL, "Bombardier Beetle Block destruction", true, "This will not stop block destruction for player attacks only collided with blocks!").getBoolean(true);
		randomNames = config.get(Configuration.CATEGORY_GENERAL, "Random mob names", true).getBoolean(true);
		playCustomSongs = config.get(Configuration.CATEGORY_GENERAL, "Play erebus songs", true).getBoolean(true);
		alternativePlanks = config.get(Configuration.CATEGORY_GENERAL, "Alternative Planks Textures", false).getBoolean(false);
		graveMarker = config.get(Configuration.CATEGORY_GENERAL, "Block O' Bones Inventory Save", true).getBoolean(true);
		bioluminescence = config.get(Configuration.CATEGORY_GENERAL, "Glowing bugs emit light", true).getBoolean(true);
		glowshrooms = config.get(Configuration.CATEGORY_GENERAL, "Add Glowshrooms to World generation", true).getBoolean(true);
		generateVents = config.get(Configuration.CATEGORY_GENERAL, "Generate natural swap vents", true).getBoolean(true);
		netherWidows = config.get(Configuration.CATEGORY_GENERAL, "Spawn Black Widows in Nether", true).getBoolean(true);

		coal = config.get("Ores", "Generate coal", true).getBoolean(true);
		iron = config.get("Ores", "Generate iron", true).getBoolean(true);
		lapis = config.get("Ores", "Generate lapis lazuli", true).getBoolean(true);
		emerald = config.get("Ores", "Generate emerald", true).getBoolean(true);
		diamond = config.get("Ores", "Generate diamond", true).getBoolean(true);
//		diamond_encrusted = config.get("Ores", "Generate encrusted diamond(this option does nothing right now)", false).getBoolean(false);
		jade = config.get("Ores", "Generate jade", true).getBoolean(true);
		petrified_wood = config.get("Ores", "Generate petrified wood", true).getBoolean(true);
		fossil = config.get("Ores", "Generate fossil", true).getBoolean(true);
//		gneiss = config.get("Ores", "Generate gneiss(this option does nothing right now)", false).getBoolean(false);
		lead = config.get("Ores", "Generate lead", false).getBoolean(false);
		silver = config.get("Ores", "Generate silver", false).getBoolean(false);
		copper = config.get("Ores", "Generate copper", false).getBoolean(false);
		tin = config.get("Ores", "Generate tin", false).getBoolean(false);
		aluminium = config.get("Ores", "Generate aluminium", false).getBoolean(false);

		disableThaumcraft = config.get("Integration", "Disable Thaumcraft integration", false).getBoolean(false);
		disableFMP = config.get("Integration", "Disable Forge Multipart integration", false).getBoolean(false);

		if (config.hasChanged())
			config.save();
	}

	private void readEntityDimensionsFile() {
		File file = new File(configFolder, "ErebusEntityDimensions.cfg");
		BufferedReader br = new BufferedReader(new InputStreamReader(Erebus.class.getResourceAsStream("/assets/DefaultEntityDimensions.cfg")));
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
		if (Reference.MOD_ID.equals(event.modID))
			syncConfigs();
	}
}