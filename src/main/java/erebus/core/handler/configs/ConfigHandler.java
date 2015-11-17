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
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();

	private File configFolder;

	public Configuration config;
	public int erebusDimensionID;
	public int portalCooldown;
	public byte beetleLarvaEating = 0;
	public boolean spawnPortalMobs, bombardierBlockDestroy, randomNames, playCustomSongs, alternativePlanks, graveMarker, bioluminescence, glowshrooms, generateVents, allowRespawning, netherWidows;

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

		ModBiomes.undergroundJungleID = config.get("Biomes", "Underground Jungle", ModBiomes.undergroundJungleID).getInt(ModBiomes.undergroundJungleID);
		ModBiomes.volcanicDesertID = config.get("Biomes", "Volcanic Desert", ModBiomes.volcanicDesertID).getInt(ModBiomes.volcanicDesertID);
		ModBiomes.subterraneanSavannahID = config.get("Biomes", "Subterranean Savannah", ModBiomes.subterraneanSavannahID).getInt(ModBiomes.subterraneanSavannahID);
		ModBiomes.elysianFieldsID = config.get("Biomes", "Elysian Fields", ModBiomes.elysianFieldsID).getInt(ModBiomes.elysianFieldsID);
		ModBiomes.ulteriorOutbackID = config.get("Biomes", "Ulterior Outback", ModBiomes.ulteriorOutbackID).getInt(ModBiomes.ulteriorOutbackID);
		ModBiomes.fungalForestID = config.get("Biomes", "Fungal Forest", ModBiomes.fungalForestID).getInt(ModBiomes.fungalForestID);
		ModBiomes.fieldsSubForestID = config.get("Biomes", "Fields - Forest", ModBiomes.fieldsSubForestID).getInt(ModBiomes.fieldsSubForestID);
		ModBiomes.submergedSwampID = config.get("Biomes", "Submerged Swamp", ModBiomes.submergedSwampID).getInt(ModBiomes.submergedSwampID);

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

		disableThaumcraft = config.get("Integration", "Disable Thaumcraft integration", false).getBoolean(false);
		disableFMP = config.get("Integration", "Disable Forge Multipart integration", false).getBoolean(false);

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
		if (Reference.MOD_ID.equals(event.modID)) {
			syncConfigs();
			initOreConfigs();
		}
	}
}