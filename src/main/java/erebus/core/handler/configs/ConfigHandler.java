package erebus.core.handler.configs;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModBiomes;
import erebus.lib.Reference;

public class ConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();

	public Configuration config;
	public int erebusDimensionID;
	public byte beetleLarvaEating = 0;
	public boolean spawnPortalMobs, bombardierBlockDestroy, randomNames, playCustomSongs, lead, silver, copper, tin, aluminium;

	public final String[] usedCategories = { Configuration.CATEGORY_GENERAL, "Biomes", "Ores" };

	public void loadConfig(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());

		syncConfigs();
	}

	private void syncConfigs() {

		// Biomes & misc
		ModBiomes.undergroundJungleID = config.get("Biomes", "Biome ID of Underground Jungle", 100).getInt(100);
		ModBiomes.volcanicDesertID = config.get("Biomes", "Biome ID of Volcanic Desert", 101).getInt(101);
		ModBiomes.subterraneanSavannahID = config.get("Biomes", "Biome ID of Subterranean Savannah", 102).getInt(102);
		ModBiomes.elysianFieldsID = config.get("Biomes", "Biome ID of Elysian Fields", 103).getInt(103);
		ModBiomes.ulteriorOutbackID = config.get("Biomes", "Biome ID of Ulterior Outback", 104).getInt(104);
		/*ModBiomes.fungalForestID = config.get("Biomes", "Biome ID of Fungal Forest", 156).getInt(156);
		ModBiomes.betweenlandsID = config.get("Biomes", "Biome ID of Betweenlands", 157).getInt(157);*/

		ModBiomes.fieldsSubForestID = config.get("Biomes", "Biome ID of Fields - Forest", 127).getInt(127);
		/*ModBiomes.jungleSubLakeID = config.get("Biomes", "Biome ID of Underground Jungle - Lake", 161).getInt(161);
		ModBiomes.jungleSubAsperGroveID = config.get("Biomes", "Biome ID of Underground Jungle - Asper Grove", 162).getInt(162);
		ModBiomes.desertSubCharredForestID = config.get("Biomes", "Biome ID of Volcanic Desert - Charred Forest", 163).getInt(163);
		ModBiomes.savannahSubRockyWastelandID = config.get("Biomes", "Biome ID of Savannah - Rocky Wasteland", 164).getInt(164);
		ModBiomes.savannahSubAsperGroveID = config.get("Biomes", "Biome ID of Savannah - Asper Grove", 165).getInt(165);
		ModBiomes.savannahSubSteppeID = config.get("Biomes", "Biome ID of Savannah - Steppe", 166).getInt(166);*/

		erebusDimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt(66);
		spawnPortalMobs = config.get(Configuration.CATEGORY_GENERAL, "Should spawn beetles and larvae in the portal", true).getBoolean(true);
		beetleLarvaEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Beetle larva eating settings", 0, "0 = only wooden blocks except tile entities & logs, 1 = only wooden blocks except logs, 2 = anything").getInt(0);
		bombardierBlockDestroy = config.get(Configuration.CATEGORY_GENERAL, "Bombardier Beetle Block destruction", true, "This will not stop block destruction for player attacks only collided with blocks!").getBoolean(true);
		randomNames = config.get(Configuration.CATEGORY_GENERAL, "Random mob names", true).getBoolean(true);
		playCustomSongs = config.get(Configuration.CATEGORY_GENERAL, "Play erebus songs", true).getBoolean(true);

		lead = config.get("Ores", "Generate lead", false).getBoolean(false);
		silver = config.get("Ores", "Generate silver", false).getBoolean(false);
		copper = config.get("Ores", "Generate copper", false).getBoolean(false);
		tin = config.get("Ores", "Generate tin", false).getBoolean(false);
		aluminium = config.get("Ores", "Generate aluminium", false).getBoolean(false);

		if (config.hasChanged())
			config.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (Reference.MOD_ID.equals(eventArgs.modID))
			syncConfigs();
	}
}