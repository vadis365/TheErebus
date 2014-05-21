package erebus.core.handler;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import erebus.ModBiomes;

public class ConfigHandler {

	public static Configuration config;
	public static int erebusDimensionID;
	public static byte beetleLarvaEating = 0;
	public static boolean spawnPortalMobs, bombardierBlockDestroy, randomNames, playCustomSongs, lead, silver, copper, tin, aluminium;


	public static void loadConfig(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());

		try {
			config.load();

			// Biomes & misc
			ModBiomes.undergroundJungleID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Underground Jungle", 151).getInt(151);
			ModBiomes.volcanicDesertID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Volcanic Desert", 152).getInt(152);
			ModBiomes.subterraneanSavannahID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Subterranean Savannah", 153).getInt(153);
			ModBiomes.elysianFieldsID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Elysian Fields", 154).getInt(154);
			ModBiomes.ulteriorOutbackID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Ulterior Outback", 155).getInt(155);
			/*ModBiomes.fungalForestID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Fungal Forest", 156).getInt(156);
			ModBiomes.betweenlandsID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Betweenlands", 157).getInt(157);*/

			
			/*ModBiomes.jungleSubLakeID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Underground Jungle - Lake", 161).getInt(161);
			ModBiomes.jungleSubAsperGroveID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Underground Jungle - Asper Grove", 162).getInt(162);
			ModBiomes.desertSubCharredForestID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Volcanic Desert - Charred Forest", 163).getInt(163);
			ModBiomes.savannahSubRockyWastelandID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Savannah - Rocky Wasteland", 164).getInt(164);
			ModBiomes.savannahSubAsperGroveID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Savannah - Asper Grove", 165).getInt(165);
			ModBiomes.savannahSubSteppeID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Savannah - Steppe", 166).getInt(166);*/
			ModBiomes.fieldsSubForestID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Fields - Forest", 167).getInt(167);

			erebusDimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt(66);
			spawnPortalMobs = config.get(Configuration.CATEGORY_GENERAL, "Should spawn beetles and larvae in the portal", true).getBoolean(true);
			beetleLarvaEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Beetle larva eating settings", 0, "0 = only wooden blocks except tile entities & logs, 1 = only wooden blocks except logs, 2 = anything").getInt(0);
			bombardierBlockDestroy = config.get(Configuration.CATEGORY_GENERAL, "Bombardier Beetle Block destruction", true, "This will not stop block destruction for player attacks only collided with blocks!").getBoolean(true);
			randomNames = config.get(Configuration.CATEGORY_GENERAL, "Random mob names", true).getBoolean(true);
			playCustomSongs = config.get(Configuration.CATEGORY_GENERAL, "Play erebus songs", true).getBoolean(true);

			lead = config.get(Configuration.CATEGORY_GENERAL, "Should generate lead?", false).getBoolean(false);
			silver = config.get(Configuration.CATEGORY_GENERAL, "Should generate silver?", false).getBoolean(false);
			copper = config.get(Configuration.CATEGORY_GENERAL, "Should generate copper?", false).getBoolean(false);
			tin = config.get(Configuration.CATEGORY_GENERAL, "Should generate tin?", false).getBoolean(false);
			aluminium = config.get(Configuration.CATEGORY_GENERAL, "Should generate aluminium?", false).getBoolean(false);

		} catch (Exception e) {
			FMLLog.severe("Erebus has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			config.save();
		}
	}
}