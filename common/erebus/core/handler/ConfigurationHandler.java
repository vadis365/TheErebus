package erebus.core.handler;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockUmberstone;

public class ConfigurationHandler {

	public static Configuration config;

	public static int erebusDimensionID;
	public static boolean spawnPortalMobs = true;
	public static byte beetleLarvaEating = 0;
	public static boolean bombardierBlockDestroy = true;
	public static boolean randomNames = true;
	public static boolean playCustomSongs = true;

	public static boolean lead = false;
	public static boolean silver = false;
	public static boolean copper = false;
	public static boolean tin = false;
	public static boolean aluminium = false;

	public static void loadConfig(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();

			/*
			 * Blocks
			 */
			ModBlocks.portalErebusID = config.getBlock("Block ID of Erebus Portal", 2500).getInt();

			ModBlocks.umberstoneID = config.get(Configuration.CATEGORY_BLOCK, "Block ID of Umberstone", 255, "Umberstone Block ID must be below 256").getInt(255);
			ModBlocks.umberOreBlockID = config.getBlock("Block ID of the Umberstone Ores", 2501).getInt(2501);
			ModBlocks.oreFossilID = config.getBlock("Block ID of Fossil Ore", 2502).getInt(2502);
			ModBlocks.redGemID = config.getBlock("Block ID of Red Gem", 2503).getInt(2503);
			ModBlocks.blockAmberID = config.getBlock("Block ID of Amber", 2504).getInt(2504);
			ModBlocks.quickSandID = config.getBlock("Block ID of Quick Sand", 2505).getInt(2505);
			ModBlocks.ghostSandID = config.getBlock("Block ID of Ghost Sand", 2554).getInt(2554);
			ModBlocks.erebusOreExtraID = config.getBlock("Block ID of Extra Erebus Ores", 2506).getInt(2506);
			ModBlocks.umberstoneButtonID = config.getBlock("Block ID of Umberstone Button", 2561).getInt(2561);

			ModBlocks.logErebusGroup1ID = config.getBlock("Block ID of Log - group 1", 2507).getInt(2507);
			ModBlocks.logErebusGroup2ID = config.getBlock("Block ID of Log - group 2", 2508).getInt(2508);
			ModBlocks.planksErebusID = config.getBlock("Block ID of Planks", 2509).getInt(2509);
			ModBlocks.leavesErebusID = config.getBlock("Block ID of Leaves", 2510).getInt(2510);
			ModBlocks.erebusSaplingID = config.getBlock("Block ID of Erebus Saplings", 2511).getInt(2511);
			ModBlocks.hollowLogAcaciaID = config.getBlock("Block ID of Hollow Log", 2512).getInt(2512);

			ModBlocks.erebusGrassID = config.getBlock("Block ID of Erebus Grass", 2513).getInt(2513);
			ModBlocks.thornsID = config.getBlock("Block ID of Thorns", 2514).getInt(2514);
			ModBlocks.fernID = config.getBlock("Block ID of Ferns", 2515).getInt(2515);
			ModBlocks.fiddleheadID = config.getBlock("Block ID of Fiddlehead", 2516).getInt(2516);
			ModBlocks.blockTurnipID = config.getBlock("Block ID of Turnips", 2517).getInt(2517);

			ModBlocks.blockSilkID = config.getBlock("Block ID of Silk", 2518).getInt(2518);
			ModBlocks.mirBrickID = config.getBlock("Block ID of Mirbrick", 2519).getInt(2519);
			ModBlocks.petrifiedWoodPlanksID = config.getBlock("Block ID of Petrified Wood Planks", 2520).getInt(2520);
			ModBlocks.petrifiedCraftingTableID = config.getBlock("Block ID of Petrified Crafting Table", 2521).getInt(2521);
			ModBlocks.bambooCrateID = config.getBlock("Block ID of Bamboo Crate", 2522).getInt(2522);
			ModBlocks.umberFurnaceID = config.getBlock("Block ID of Umber Furnace ON", 2523).getInt(2523);
			ModBlocks.umberFurnace_onID = config.getBlock("Block ID of Umebr Furnace OFF", 2524).getInt(2524);
			ModBlocks.umberPaverID = config.getBlock("Block ID of Umebrpaver", 2525).getInt(2525);
			ModBlocks.insectRepellentID = config.getBlock("Block ID of Insect Repellent Block", 2526).getInt(2526);
			ModBlocks.bambooShootID = config.getBlock("Block ID of Bamboo Shoot Block", 2568).getInt(2568);
			ModBlocks.bambooCropID = config.getBlock("Block ID of Bamboo Crop Block", 2554).getInt(2554);
			ModBlocks.bambooTorchID = config.getBlock("Block ID of Bamboo Torch", 2559).getInt(2559);
			ModBlocks.bambooBridgeID = config.getBlock("Block ID of BambooBridge", 2577).getInt(2577);
			ModBlocks.erebusAltarID = config.getBlock("Block ID of Erebus Altar", 2560).getInt(2560);
			ModBlocks.erebusAltarLightningID = config.getBlock("Block ID of Erebus Altar of Lightning", 2562).getInt(2562);
			ModBlocks.erebusAltarHealingID = config.getBlock("Block ID of Erebus Altar of Healing", 2563).getInt(2563);
			ModBlocks.erebusAltarXPID = config.getBlock("Block ID of Erebus Altar of Experience", 2564).getInt(2564);
			ModBlocks.erebusAltarRepairID = config.getBlock("Block ID of Erebus Altar of Repair", 2565).getInt(2565);
			ModBlocks.glowingJarID = config.getBlock("Block ID of Glowing Jar", 2566).getInt(2566);
			ModBlocks.reinExoID = config.getBlock("Block ID of Reinforced Exoskeleton Block", 2567).getInt(2567);
			ModBlocks.bambooLadderID = config.getBlock("Block ID of Bamboo Ladder", 2569).getInt(2569);
			ModBlocks.umberGolemStatueID = config.getBlock("Block ID of Umber Golem Statue", 2570).getInt(2570);
			ModBlocks.waspNestBlockID = config.getBlock("Block ID of Wasp Nest Block", 2571).getInt(2571);
			ModBlocks.petrifiedWoodChestID = config.getBlock("Block ID of Petrified Wood Chest", 2574).getInt(2574);
			ModBlocks.blockBonesID = config.getBlock("Block ID of Block o' Bones", 2575).getInt(2575);
			ModBlocks.blockWitherWebID = config.getBlock("Block ID of Wither Web", 2576).getInt(2576);
			ModBlocks.extenderThingyID = config.getBlock("Block ID of Bamboo Extender", 2578).getInt(2578);
			ModBlocks.bambooPoleID = config.getBlock("Block ID of Bamboo Nerd Pole", 2579).getInt(2579);

			int id = 2527;
			ModBlocks.umbercobbleStairsID = new int[BlockUmberstone.iconPaths.length];
			for (int i = 0; i < ModBlocks.umbercobbleStairsID.length; i++)
				ModBlocks.umbercobbleStairsID[i] = config.getBlock("Block ID of Umbercobble Stairs " + i, id).getInt(id++);
			ModBlocks.plankStairsID = new int[BlockPlanksErebus.plankTypes.length];
			for (int i = 0; i < ModBlocks.plankStairsID.length; i++)
				ModBlocks.plankStairsID[i] = config.getBlock("Block ID of Plank Stairs " + i, id).getInt(id++);
			// 20 blocks for stairs
			ModBlocks.petrifiedWoodStairsID = config.getBlock("Block ID of Petrified Wood Stairs", 2547).getInt(2547);
			ModBlocks.stoneSlabsID = new int[2];
			for (int i = 0; i < ModBlocks.stoneSlabsID.length; i++)
				ModBlocks.stoneSlabsID[i] = config.getBlock("Block ID of Stone Slabs " + i, 2548 + i).getInt(2548 + i);
			ModBlocks.plankSlabsID = new int[4];
			for (int i = 0; i < ModBlocks.plankSlabsID.length; i++)
				ModBlocks.plankSlabsID[i] = config.getBlock("Block ID of Plank Slabs " + i, i < 2 ? 2550 + i : 2555 + i).getInt(i < 2 ? 2550 + i : 2555 + i);
			ModBlocks.wallErebusID = config.getBlock("Block ID of Wall", 2551).getInt(2551);
			ModBlocks.petrifiedWoodSlabID = new int[2];
			for (int i = 0; i < ModBlocks.petrifiedWoodSlabID.length; i++)
				ModBlocks.petrifiedWoodSlabID[i] = config.getBlock("Block ID of Petrified Wood Slab" + i, 2556 + i).getInt(2556 + i);
			ModBlocks.amberBrickStairsID = config.getBlock("Block ID of Amber Brick Stairs", 2558).getInt(2558);
			ModBlocks.waspNestStairsID = config.getBlock("Block ID of Wasp Nest Stairs", 2573).getInt(2573);

			ModBlocks.spiderSpawnerID = config.getBlock("Block ID of Scytodes Spawners", 2552).getInt(2552);
			ModBlocks.jumpingSpiderSpawnerID = config.getBlock("Block ID of Jumping Spider Spawners", 2553).getInt(2553);
			ModBlocks.waspSpawnerID = config.getBlock("Block ID of Wasp Spawners", 2572).getInt(2572);

			// latest ID used (please update after adding new blocks!) >>> 2578

			/*
			 * Items
			 */
			ModItems.portalActivatorID = config.getItem("Item ID of Portal Activator", 9706).getInt(9706);
			ModItems.erebusMaterialsID = config.getItem("Item ID of Erebus Materials", 9707).getInt(9707);
			ModItems.erebusFoodID = config.getItem("Item ID of Erebus Food", 9708).getInt(9708);
			ModItems.metalIngotID = config.getItem("Item ID of Metal Ingots", 9709).getInt(9709);
			ModItems.bamBucketID = config.getItem("Item ID of Bambucket", 9710).getInt(9710);
			ModItems.turnipID = config.getItem("Item ID of Turnips", 9711).getInt(9711);
			ModItems.sprayCanID = config.getItem("Item ID of Insect Repellent", 9712).getInt(9712);
			ModItems.wandOfAnimationID = config.getItem("ItemID of Wand of Animation", 9734).getInt(9734);
			ModItems.bucketOfBeetleJuiceID = config.getItem("ItemID of Bucket Of Beetle Juice", 9742).getInt(9742);
			ModItems.hornOfSummoningID = config.getItem("ItemID of Horn of The Swarm", 9744).getInt(9744);

			ModItems.jadeHelmetID = config.getItem("Item ID of Jade Helmet", 9713).getInt(9713);
			ModItems.jadeBodyID = config.getItem("Item ID of Jade Chestplate", 9714).getInt(9714);
			ModItems.jadeLegsID = config.getItem("Item ID of Jade Leggings", 9715).getInt(9715);
			ModItems.jadeBootsID = config.getItem("Item ID of Jade Boots", 9716).getInt(9716);
			ModItems.jadeSwordID = config.getItem("Item ID of Jade Sword", 9717).getInt(9717);
			ModItems.jadePickaxeID = config.getItem("Item ID of Jade Pickaxe", 9718).getInt(9718);
			ModItems.jadeAxeID = config.getItem("Item ID of Jade Axe", 9719).getInt(9719);
			ModItems.jadeShovelID = config.getItem("Item ID of Jade Shovel", 9720).getInt(9720);
			ModItems.jadePaxelID = config.getItem("Item ID of Jade Paxel", 9721).getInt(9721);
			ModItems.jadeHoeID = config.getItem("Item ID of Jade Hoe", 9722).getInt(9722);

			ModItems.exoskeletonHelmetID = config.getItem("Item ID of Exoskeleton Helmet", 9723).getInt(9723);
			ModItems.exoskeletonBodyID = config.getItem("Item ID of Exoskeleton Chestplate", 9724).getInt(9724);
			ModItems.exoskeletonLegsID = config.getItem("Item ID of Exoskeleton Leggings", 9725).getInt(9725);
			ModItems.exoskeletonBootsID = config.getItem("Item ID of Exoskeleton Boots", 9726).getInt(9726);

			ModItems.fossilClubID = config.getItem("Item ID of Caveman's Club", 9727).getInt(9727);
			ModItems.waspSwordID = config.getItem("Item ID of Wasp Sword", 9728).getInt(9728);
			ModItems.maxSpeedBowID = config.getItem("Item ID of MaxSpeed Bow", 9729).getInt(9729);
			ModItems.waspDaggerID = config.getItem("Item ID of Wasp Dagger", 9733).getInt(9733);
			ModItems.scorpionPincerID = config.getItem("Item ID of Scorpion Pincer", 9741).getInt(9741);
			ModItems.webSlingerID = config.getItem("Item ID of Web Slinger", 9745).getInt(9745);

			ModItems.compoundGogglesID = config.getItem("Item ID of Compound Goggles", 9730).getInt(9730);
			ModItems.reinCompoundGogglesID = config.getItem("Item ID of Reinforced Compound Goggles", 9740).getInt(9740);
			ModItems.sprintLeggingsID = config.getItem("Item ID of Sprint Leggings", 9731).getInt(9731);
			ModItems.jumpBootsID = config.getItem("Item ID of Jump Boots", 9732).getInt(9732);
			ModItems.armorGliderID = config.getItem("Item ID of Chest Armour Glider", 9735).getInt(9735);

			ModItems.reinExoskeletonHelmetID = config.getItem("Item ID of Reinforced Exoskeleton Helmet", 9736).getInt(9736);
			ModItems.reinExoskeletonBodyID = config.getItem("Item ID of Reinforced Exoskeleton Chestplate", 9737).getInt(9737);
			ModItems.reinExoskeletonLegsID = config.getItem("Item ID of Reinforced Exoskeleton Leggings", 9738).getInt(9738);
			ModItems.reinExoskeletonBootsID = config.getItem("Item ID of Reinforced Exoskeleton Boots", 9739).getInt(9739);

			ModItems.spawnEggsID = config.getItem("Item ID of Spawn Eggs", 9743).getInt(9743);

			// latest ID used (please update after adding new items!) >>> 9745

			/*
			 * Biomes & misc
			 */
			ModBiomes.jungleID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Underground Jungle", 151).getInt(151);
			ModBiomes.desertID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Volcanic Desert", 152).getInt(152);
			ModBiomes.savannahID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Subterranean Savannah", 153).getInt(153);
			ModBiomes.cavernID = config.get(Configuration.CATEGORY_GENERAL, "Biome ID of Cavern", 154, "Biome IDs must be below 256").getInt(154);

			ConfigurationHandler.erebusDimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID of The Erebus", 66, "There doesn't appear to be a limit on dimension IDs, but try to keep it low").getInt(66);
			ConfigurationHandler.spawnPortalMobs = config.get(Configuration.CATEGORY_GENERAL, "Should spawn beetles and larvae in the portal", true).getBoolean(true);
			ConfigurationHandler.beetleLarvaEating = (byte) config.get(Configuration.CATEGORY_GENERAL, "Beetle larva eating settings", 0, "0 = only wooden blocks except tile entities & logs, 1 = only wooden blocks except logs, 2 = anything").getInt(0);
			ConfigurationHandler.bombardierBlockDestroy = config.get(Configuration.CATEGORY_GENERAL, "Bombardier Beetle Block destruction", true, "This will not stop block destruction for player attacks only collided with blocks!").getBoolean(true);
			ConfigurationHandler.randomNames = config.get(Configuration.CATEGORY_GENERAL, "Random mob names", true).getBoolean(true);
			ConfigurationHandler.playCustomSongs = config.get(Configuration.CATEGORY_GENERAL, "Play erebus songs", true).getBoolean(true);

			ConfigurationHandler.lead = config.get(Configuration.CATEGORY_GENERAL, "Should generate lead?", false).getBoolean(false);
			ConfigurationHandler.silver = config.get(Configuration.CATEGORY_GENERAL, "Should generate silver?", false).getBoolean(false);
			ConfigurationHandler.copper = config.get(Configuration.CATEGORY_GENERAL, "Should generate copper?", false).getBoolean(false);
			ConfigurationHandler.tin = config.get(Configuration.CATEGORY_GENERAL, "Should generate tin?", false).getBoolean(false);
			ConfigurationHandler.aluminium = config.get(Configuration.CATEGORY_GENERAL, "Should generate aluminium?", false).getBoolean(false);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Erebus has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			config.save();
		}
	}
}
