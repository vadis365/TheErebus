package erebus.core.handler.configs;

import erebus.lib.Reference;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();
	public final String[] usedCategories = {Configuration.CATEGORY_GENERAL, "Biomes", "Ores", "Integration", "Mob Modifiers"};
	public int erebusDimensionID;
	public int portalCooldown, antlionMazeFrequency, mobAttackDamageMultiplier, mobHealthMultipier;
	public byte beetleLarvaEating = 0;
	public boolean spawnPortalMobs, bombardierBlockDestroy, randomNames, playCustomSongs, alternativePlanks, graveMarker, bioluminescence, glowshrooms, generateVents, allowRespawning, netherWidows, biomeFogColours;
	public boolean disableThaumcraft = false, disableFMP = false;
	Configuration config;
	private File configFolder;

	public void loadConfig(FMLPreInitializationEvent event) {
		File configFile = event.getSuggestedConfigurationFile();
		configFolder = configFile.getParentFile();
		config = new Configuration(configFile);

		config.load();
		syncConfigs();
	}

	private void syncConfigs() {
	//	readEntityDimensionsFile();

		erebusDimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt(66);
		portalCooldown = config.get(Configuration.CATEGORY_GENERAL, "Number of seconds before the portal is usable again.", 5).getInt(5);
		antlionMazeFrequency = config.get(Configuration.CATEGORY_GENERAL, "Antlion Maze Frequency lower numbers increase rate. May Cause issues if too low.", 1024).getInt(1024);
		allowRespawning = config.get(Configuration.CATEGORY_GENERAL, "Should player re-spawn in dimension", false).getBoolean(false);
		spawnPortalMobs = config.get(Configuration.CATEGORY_GENERAL, "Should spawn beetles and larvae in the portal", true).getBoolean(true);
		beetleLarvaEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Beetle larva eating settings", 0, "0 = only wooden blocks except tile entities & logs, 1 = only wooden blocks except logs, 2 = anything", 0, 2).getInt(0);
		bombardierBlockDestroy = config.get(Configuration.CATEGORY_GENERAL, "Bombardier Beetle Block destruction", true, "This will not stop block destruction for player attacks only collided with blocks!").getBoolean(true);
		randomNames = config.get(Configuration.CATEGORY_GENERAL, "Random mob names", true).getBoolean(true);
		playCustomSongs = config.get(Configuration.CATEGORY_GENERAL, "Play erebus songs", true).getBoolean(true);
		alternativePlanks = config.get(Configuration.CATEGORY_GENERAL, "Alternative Planks Textures", false).getBoolean(false);
		graveMarker = config.get(Configuration.CATEGORY_GENERAL, "Block O' Bones Inventory Save", true).getBoolean(true);
		biomeFogColours = config.get(Configuration.CATEGORY_GENERAL, "Coloured Biome Fogs", true).getBoolean(true);
		bioluminescence = config.get(Configuration.CATEGORY_GENERAL, "Glowing bugs emit light", true).getBoolean(true);
		glowshrooms = config.get(Configuration.CATEGORY_GENERAL, "Add Glowshrooms to World generation", true).getBoolean(true);
		generateVents = config.get(Configuration.CATEGORY_GENERAL, "Generate natural swap vents", true).getBoolean(true);
		netherWidows = config.get(Configuration.CATEGORY_GENERAL, "Spawn Black Widows in Nether", true).getBoolean(true);
		
		mobAttackDamageMultiplier = config.get("Mob Modifiers", "Mob Attack Damage Multipier", 1).getInt(1);
		mobHealthMultipier = config.get("Mob Modifiers", "Mob Health Multiplier", 1).getInt(1);
		
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
/* TODO
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
*/
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (Reference.MOD_ID.equals(event.getModID())) {
			syncConfigs();
			initOreConfigs();
		}
	}
}