package erebus;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockAmber;
import erebus.block.BlockBambooBridge;
import erebus.block.BlockBambooCrate;
import erebus.block.BlockBambooCrop;
import erebus.block.BlockBambooLadder;
import erebus.block.BlockBambooPole;
import erebus.block.BlockBambooShoot;
import erebus.block.BlockBambooTorch;
import erebus.block.BlockBones;
import erebus.block.BlockButtonUmberstone;
import erebus.block.BlockErebusAltar;
import erebus.block.BlockErebusAltarHealing;
import erebus.block.BlockErebusAltarLightning;
import erebus.block.BlockErebusAltarRepair;
import erebus.block.BlockErebusAltarXP;
import erebus.block.BlockErebusGrass;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.block.BlockExtenderThingy;
import erebus.block.BlockFern;
import erebus.block.BlockFiddlehead;
import erebus.block.BlockGhostSand;
import erebus.block.BlockGlowingJar;
import erebus.block.BlockHollowLog;
import erebus.block.BlockInsectRepellent;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;
import erebus.block.BlockOreFossil;
import erebus.block.BlockPetrifiedChest;
import erebus.block.BlockPetrifiedCraftingTable;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockPortalErebus;
import erebus.block.BlockQuickSand;
import erebus.block.BlockRedGem;
import erebus.block.BlockReinExo;
import erebus.block.BlockSaplingErebus;
import erebus.block.BlockSlabPetrifiedWood;
import erebus.block.BlockSlabPlanksErebus;
import erebus.block.BlockSlabStoneErebus;
import erebus.block.BlockSpiderSpawner;
import erebus.block.BlockStairsErebus;
import erebus.block.BlockThorns;
import erebus.block.BlockTurnip;
import erebus.block.BlockUmberFurnace;
import erebus.block.BlockUmberGolemStatue;
import erebus.block.BlockUmberPaver;
import erebus.block.BlockUmberstone;
import erebus.block.BlockWallErebus;
import erebus.block.BlockWaspNest;
import erebus.block.BlockWaspSpawner;
import erebus.block.BlockWitherWeb;
import erebus.core.handler.ConfigurationHandler;
import erebus.item.block.ItemBlockAmber;
import erebus.item.block.ItemBlockColoredSingle;
import erebus.item.block.ItemBlockErebusOreExtras;
import erebus.item.block.ItemBlockLeavesErebus;
import erebus.item.block.ItemBlockLogErebus1;
import erebus.item.block.ItemBlockLogErebus2;
import erebus.item.block.ItemBlockPlanksErebus;
import erebus.item.block.ItemBlockRedGem;
import erebus.item.block.ItemBlockSapling;
import erebus.item.block.ItemBlockSlabPetrifiedWood;
import erebus.item.block.ItemBlockSlabPlanks0Erebus;
import erebus.item.block.ItemBlockSlabPlanks1Erebus;
import erebus.item.block.ItemBlockSlabStoneErebus;
import erebus.item.block.ItemBlockUmberOre;
import erebus.item.block.ItemBlockUmberStone;
import erebus.item.block.ItemBlockUmberpaver;
import erebus.item.block.ItemBlockWallErebus;

public class ModBlocks {

	//@formatter:off

	// PORTAL
	public static BlockPortalErebus portalErebus;		public static int portalErebusID;

	// TERRAIN AND ORES
	public static Block umberstone;         			public static int umberstoneID;
	public static Block umberOreBlock;     				public static int umberOreBlockID;
	public static Block oreFossil;      				public static int oreFossilID;
	public static Block redGem;       					public static int redGemID;
	public static Block blockAmber;      				public static int blockAmberID;
	public static Block quickSand;      				public static int quickSandID;
	public static Block ghostSand;						public static int ghostSandID;
	public static Block erebusOreExtra;      			public static int erebusOreExtraID;
	public static Block umberstoneButton;				public static int umberstoneButtonID;

	// WOOD
	public static Block logErebusGroup1;            	public static int logErebusGroup1ID;
	public static Block logErebusGroup2;             	public static int logErebusGroup2ID;
	public static Block planksErebus;             		public static int planksErebusID;
	public static BlockLeavesErebus leavesErebus;  		public static int leavesErebusID;
	public static Block erebusSapling;     				public static int erebusSaplingID;
	public static Block hollowLogAcacia;    			public static int hollowLogAcaciaID;

	// UNDERGROWTH
	public static Block erebusGrass;     				public static int erebusGrassID;
	public static Block thorns;       					public static int thornsID;
	public static BlockFern fern;      					public static int fernID;
	public static Block blockTurnip;     				public static int blockTurnipID;
	public static Block fiddlehead;      				public static int fiddleheadID;

	// DECORATIONS AND UTILITIES
	public static Block blockSilk;      				public static int blockSilkID;
	public static Block mirBrick;      					public static int mirBrickID;
	public static Block petrifiedWoodPlanks;   			public static int petrifiedWoodPlanksID;
	public static Block petrifiedCraftingTable;  		public static int petrifiedCraftingTableID;
	public static Block bambooCrate;          			public static int bambooCrateID;
	public static Block umberFurnace;					public static int umberFurnaceID;
	public static Block umberFurnace_on;				public static int umberFurnace_onID;
	public static Block umberPaver;						public static int umberPaverID;
	public static Block insectRepellent;				public static int insectRepellentID;
	public static Block bambooShoot;					public static int bambooShootID;
	public static Block bambooCrop;						public static int bambooCropID;
	public static Block bambooTorch;					public static int bambooTorchID;
	public static Block erebusAltar;					public static int erebusAltarID;
	public static Block erebusAltarLightning;			public static int erebusAltarLightningID;
	public static Block erebusAltarHealing;				public static int erebusAltarHealingID;
	public static Block erebusAltarXP;					public static int erebusAltarXPID;
	public static Block erebusAltarRepair;				public static int erebusAltarRepairID;
	public static Block glowingJar;						public static int glowingJarID;
	public static Block reinExo;						public static int reinExoID;
	public static Block bambooLadder;					public static int bambooLadderID;
	public static Block bambooBridge;					public static int bambooBridgeID;
	public static Block umberGolemStatue;				public static int umberGolemStatueID;
	public static Block petrifiedWoodChest;				public static int petrifiedWoodChestID;
	public static Block blockBones;						public static int blockBonesID;
	public static Block blockWitherWeb;					public static int blockWitherWebID;
	public static Block extenderThingy;					public static int extenderThingyID;
	public static Block bambooPole;						public static int bambooPoleID;

	// STAIRS, SLABS, WALLS
	public static Block[] umbercobbleStairs;			public static int[] umbercobbleStairsID;
	public static Block[] plankStairs;     				public static int[] plankStairsID;
	public static Block petrifiedWoodStairs;  		  	public static int petrifiedWoodStairsID;
	public static Block[] stoneSlabs;					public static int[] stoneSlabsID;
	public static Block[] plankSlabs;					public static int[] plankSlabsID;
	public static Block wallErebus;						public static int wallErebusID;
	public static Block[] petrifiedWoodSlab;			public static int[] petrifiedWoodSlabID;
	public static Block amberBrickStairs;				public static int amberBrickStairsID;
	public static Block waspNestStairs;    				public static int waspNestStairsID;

	// DUNGEONS
	public static Block spiderSpawner;     				public static int spiderSpawnerID;
	public static Block jumpingSpiderSpawner;    		public static int jumpingSpiderSpawnerID;
	public static Block waspSpawner;    				public static int waspSpawnerID;
	public static Block waspNestBlock;    				public static int waspNestBlockID;

	//@formatter:on

	public static void init() {
		// Block declaration: constructor, hardness, resistance, light value,
		// light opacity, step sound, creative tab (null), unlocalized name,
		// texture name
		portalErebus = (BlockPortalErebus) new BlockPortalErebus(portalErebusID).setHardness(-1F).setLightValue(1.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("portalErebus");

		umberstone = new BlockUmberstone(umberstoneID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberstone");
		umberOreBlock = new BlockErebusOre(umberOreBlockID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreBlockU");
		oreFossil = new BlockOreFossil(oreFossilID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreFossilU").setTextureName("erebus:oreFossil_U");
		redGem = new BlockRedGem(redGemID).setHardness(0.3F).setLightValue(1F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("redGem");
		blockAmber = new BlockAmber(blockAmberID).setHardness(1.5F).setResistance(10.0F).setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("blockAmber");
		quickSand = new BlockQuickSand(quickSandID).setHardness(28F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("quickSand").setTextureName("erebus:quickSand");
		ghostSand = new BlockGhostSand(ghostSandID).setHardness(0.42F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("ghostSand").setTextureName("erebus:ghostSand");
		erebusOreExtra = new BlockErebusOreExtras(erebusOreExtraID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("erebusOreExtras");
		umberstoneButton = new BlockButtonUmberstone(umberstoneButtonID).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberstoneButton");

		logErebusGroup1 = new BlockLogErebus(logErebusGroup1ID, 0).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("logErebus1");
		logErebusGroup2 = new BlockLogErebus(logErebusGroup2ID, 1).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("logErebus2");
		planksErebus = new BlockPlanksErebus(planksErebusID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("planksErebus");
		leavesErebus = (BlockLeavesErebus) new BlockLeavesErebus(leavesErebusID).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("leavesErebus");
		erebusSapling = new BlockSaplingErebus(erebusSaplingID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("sapling_mahogany");
		hollowLogAcacia = new BlockHollowLog(hollowLogAcaciaID).setHardness(0.7F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hollowLogAcacia");

		erebusGrass = new BlockErebusGrass(erebusGrassID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusTallGrass").setTextureName("erebus:tallgrass");
		thorns = new BlockThorns(thornsID).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("thorns").setTextureName("erebus:thorns");
		fern = (BlockFern) new BlockFern(fernID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusFern");
		blockTurnip = new BlockTurnip(blockTurnipID).setUnlocalizedName("turnips");
		fiddlehead = new BlockFiddlehead(fiddleheadID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusFiddlehead");

		blockSilk = new Block(blockSilkID, Material.cloth).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("blockSilk").setTextureName("erebus:blockSilk");
		mirBrick = new Block(mirBrickID, Material.rock).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mirbrick").setTextureName("erebus:mirbrick");
		petrifiedWoodPlanks = new Block(petrifiedWoodPlanksID, Material.rock).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("petrifiedWoodPlanks").setTextureName("erebus:petrifiedWoodPlanks");
		petrifiedCraftingTable = new BlockPetrifiedCraftingTable(petrifiedCraftingTableID).setHardness(2.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("petrifiedCraftingTable");
		bambooCrate = new BlockBambooCrate(bambooCrateID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooCrate");
		umberFurnace = new BlockUmberFurnace(umberFurnaceID, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberFurnaceOFF");
		umberFurnace_on = new BlockUmberFurnace(umberFurnace_onID, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberFurnaceON");
		umberPaver = new BlockUmberPaver(umberPaverID).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberPaver");
		insectRepellent = new BlockInsectRepellent(insectRepellentID).setUnlocalizedName("insectRepellent");
		bambooShoot = new BlockBambooShoot(bambooShootID).setCreativeTab(null).setUnlocalizedName("bambooShoot").setTextureName("erebus:bambooShoot");
		bambooCrop = new BlockBambooCrop(bambooCropID).setHardness(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooCrop").setTextureName("erebus:bambooCropBase");
		bambooTorch = new BlockBambooTorch(bambooTorchID).setHardness(0.0F).setUnlocalizedName("bambooTorch").setTextureName("erebus:blockBambooTorch");
		erebusAltar = new BlockErebusAltar(erebusAltarID).setUnlocalizedName("erebusAltar");
		erebusAltarLightning = new BlockErebusAltarLightning(erebusAltarLightningID).setUnlocalizedName("erebusAltarLightning");
		erebusAltarHealing = new BlockErebusAltarHealing(erebusAltarHealingID).setUnlocalizedName("erebusAltarHealing");
		erebusAltarXP = new BlockErebusAltarXP(erebusAltarXPID).setUnlocalizedName("erebusAltarXP");
		erebusAltarRepair = new BlockErebusAltarRepair(erebusAltarRepairID).setUnlocalizedName("erebusAltarRepair");
		glowingJar = new BlockGlowingJar(glowingJarID).setUnlocalizedName("glowingJar");
		reinExo = new BlockReinExo(reinExoID).setHardness(1.5F).setResistance(2000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("reinExo").setTextureName("erebus:reinExo");
		bambooLadder = new BlockBambooLadder(bambooLadderID).setHardness(0.4F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("bambooLadder").setTextureName("erebus:bamboo_ladder3");
		umberGolemStatue = new BlockUmberGolemStatue(umberGolemStatueID).setUnlocalizedName("umberGolemStatue");
		waspNestBlock = new BlockWaspNest(waspNestBlockID).setHardness(50.0F).setResistance(2000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("waspNestBlock").setTextureName("erebus:waspNestBlock");
		petrifiedWoodChest = new BlockPetrifiedChest(petrifiedWoodChestID).setHardness(2.0F).setUnlocalizedName("petrifiedWoodChest").setTextureName("erebus:petrifiedWoodPlanks");
		blockBones = new BlockBones(blockBonesID).setUnlocalizedName("blockBones");
		blockWitherWeb = new BlockWitherWeb(blockWitherWebID).setHardness(4.0F).setUnlocalizedName("witherWeb").setTextureName("erebus:witherWeb");
		bambooBridge = new BlockBambooBridge(bambooBridgeID).setHardness(0.4F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("bambooBridge").setTextureName("erebus:bambooBridge");
		extenderThingy = new BlockExtenderThingy(extenderThingyID).setHardness(0.4F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("extenderThingy").setTextureName("erebus:extenderThingy");
		bambooPole = new BlockBambooPole(bambooPoleID).setHardness(0.4F).setUnlocalizedName("bambooPole").setTextureName("erebus:blockBambooPole");

		umbercobbleStairs = new Block[BlockUmberstone.iconPaths.length];
		for (int i = 0; i < umbercobbleStairs.length; i++)
			umbercobbleStairs[i] = new BlockStairsErebus(umbercobbleStairsID[i], umberstone, i).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umbercobbleStairs" + i);
		plankStairs = new Block[BlockPlanksErebus.plankTypes.length];
		for (int i = 0; i < BlockPlanksErebus.plankTypes.length; i++)
			plankStairs[i] = new BlockStairsErebus(plankStairsID[i], planksErebus, i).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("stairsPlanks" + i);
		petrifiedWoodStairs = new BlockStairsErebus(petrifiedWoodStairsID, petrifiedWoodPlanks, 0).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("petrifiedWoodStairs");
		stoneSlabs = new Block[2];
		for (int i = 0; i < 2; i++)
			stoneSlabs[i] = new BlockSlabStoneErebus(stoneSlabsID[i], i == 1).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("slabStoneErebus");
		plankSlabs = new Block[4];
		for (int i = 0; i < 4; i++)
			plankSlabs[i] = new BlockSlabPlanksErebus(plankSlabsID[i], (int) Math.floor(i / 2f), i % 2 == 1).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("slabPlanksErebus");
		wallErebus = new BlockWallErebus(wallErebusID).setUnlocalizedName("wallErebus");
		petrifiedWoodSlab = new Block[2];
		for (int i = 0; i < petrifiedWoodSlab.length; i++)
			petrifiedWoodSlab[i] = new BlockSlabPetrifiedWood(petrifiedWoodSlabID[i], i == 1).setUnlocalizedName("petrifiedWoodSlab");
		amberBrickStairs = new BlockStairsErebus(amberBrickStairsID, blockAmber, 2).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("amberBrickStairs");
		waspNestStairs = new BlockStairsErebus(waspNestStairsID, waspNestBlock, 2).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("waspNestStairs");
		//
		spiderSpawner = new BlockSpiderSpawner(spiderSpawnerID, "Scytodes - Erebus").setUnlocalizedName("spiderSpawner").setTextureName("erebus:spiderSpawner");
		jumpingSpiderSpawner = new BlockSpiderSpawner(jumpingSpiderSpawnerID, "JumpingSpider - Erebus").setUnlocalizedName("jumpingSpiderSpawner").setTextureName("erebus:spiderSpawner");
		waspSpawner = new BlockWaspSpawner(waspSpawnerID, "Wasp - Erebus").setUnlocalizedName("waspSpawner").setTextureName("erebus:waspNestSpawner");

		// Creative tabs
		ErebusMod.tabErebusBlock.add(umberstone, umberOreBlock, oreFossil, erebusOreExtra, redGem, blockAmber, quickSand, ghostSand);
		ErebusMod.tabErebusBlock.add(logErebusGroup1, logErebusGroup2, hollowLogAcacia, planksErebus, leavesErebus, erebusSapling);
		ErebusMod.tabErebusBlock.add(erebusGrass, fern, fiddlehead, thorns);
		ErebusMod.tabErebusBlock.add(blockSilk, mirBrick, petrifiedWoodPlanks, petrifiedCraftingTable, bambooCrop, bambooCrate, bambooLadder, bambooBridge, bambooPole, bambooTorch, glowingJar, umberstoneButton, umberFurnace, umberPaver, erebusAltar, reinExo, waspNestBlock, petrifiedWoodChest,
		blockBones, blockWitherWeb, extenderThingy);
		for (Block b : umbercobbleStairs)
			ErebusMod.tabErebusBlock.add(b);
		for (Block b : plankStairs)
			ErebusMod.tabErebusBlock.add(b);
		ErebusMod.tabErebusBlock.add(petrifiedWoodStairs, amberBrickStairs, waspNestStairs, stoneSlabs[0], plankSlabs[0], plankSlabs[2], petrifiedWoodSlab[0], wallErebus);

		// Registering blocks
		GameRegistry.registerBlock(portalErebus, "erebus.portal");

		GameRegistry.registerBlock(umberstone, ItemBlockUmberStone.class, "erebus.umberstone");
		GameRegistry.registerBlock(umberOreBlock, ItemBlockUmberOre.class, "erebus.oreBlockU");
		GameRegistry.registerBlock(oreFossil, "erebus.oreFossil");
		GameRegistry.registerBlock(redGem, ItemBlockRedGem.class, "erebus.redGem");
		GameRegistry.registerBlock(blockAmber, ItemBlockAmber.class, "erebus.blockAmber");
		GameRegistry.registerBlock(quickSand, "erebus.quickSand");
		GameRegistry.registerBlock(ghostSand, "erebus.ghostSand");
		if (ConfigurationHandler.lead || ConfigurationHandler.silver || ConfigurationHandler.copper || ConfigurationHandler.tin || ConfigurationHandler.aluminium)
			GameRegistry.registerBlock(erebusOreExtra, ItemBlockErebusOreExtras.class, "erebus.erebusOreExtras");
		GameRegistry.registerBlock(umberstoneButton, "erebus.umberstoneButton");

		GameRegistry.registerBlock(logErebusGroup1, ItemBlockLogErebus1.class, "erebus.logErebus1");
		GameRegistry.registerBlock(logErebusGroup2, ItemBlockLogErebus2.class, "erebus.logErebus2");
		GameRegistry.registerBlock(planksErebus, ItemBlockPlanksErebus.class, "erebus.planksErebus");
		GameRegistry.registerBlock(leavesErebus, ItemBlockLeavesErebus.class, "erebus.leavesErebus");
		GameRegistry.registerBlock(erebusSapling, ItemBlockSapling.class, "erebus.erebusSapling");
		GameRegistry.registerBlock(hollowLogAcacia, "erebus.hollowLogAcacia");

		GameRegistry.registerBlock(erebusGrass, "erebus.erebusGrass");
		GameRegistry.registerBlock(thorns, "erebus.thorns");
		GameRegistry.registerBlock(fern, ItemBlockColoredSingle.class, "erebus.fern");
		GameRegistry.registerBlock(blockTurnip, "erebus.blockTurnip");
		GameRegistry.registerBlock(fiddlehead, "erebus.fiddlehead");

		GameRegistry.registerBlock(blockSilk, "erebus.blockSilk");
		GameRegistry.registerBlock(mirBrick, "erebus.mirBrick");
		GameRegistry.registerBlock(petrifiedWoodPlanks, "erebus.petrifiedWoodPlanks");
		GameRegistry.registerBlock(petrifiedCraftingTable, "erebus.petrifiedCraftingTable");
		GameRegistry.registerBlock(bambooCrate, "erebus.bambooCrate");
		GameRegistry.registerBlock(umberFurnace, "erebus.umberFurnaceOff");
		GameRegistry.registerBlock(umberFurnace_on, "erebus.umberFurnaceOn");
		GameRegistry.registerBlock(umberPaver, ItemBlockUmberpaver.class, "erebus.umberpaver");
		GameRegistry.registerBlock(bambooShoot, "erebus.bambooShoot");
		GameRegistry.registerBlock(bambooCrop, "erebus.bambooCrop");
		GameRegistry.registerBlock(bambooTorch, "erebus.bambooTorch");
		GameRegistry.registerBlock(erebusAltar, "erebus.erebusAltar");
		GameRegistry.registerBlock(erebusAltarLightning, "erebus.erebusAltarLightning");
		GameRegistry.registerBlock(erebusAltarHealing, "erebus.erebusAltarHealing");
		GameRegistry.registerBlock(erebusAltarXP, "erebus.erebusAltarXP");
		GameRegistry.registerBlock(erebusAltarRepair, "erebus.erebusAltarRepair");
		GameRegistry.registerBlock(glowingJar, "erebus.glowingJar");
		GameRegistry.registerBlock(reinExo, "erebus.reinExo");
		GameRegistry.registerBlock(bambooLadder, "erebus.bambooLadder");
		GameRegistry.registerBlock(umberGolemStatue, "erebus.umberGolemStatue");
		GameRegistry.registerBlock(waspNestBlock, "erebus.waspNestBlock");
		GameRegistry.registerBlock(petrifiedWoodChest, "erebus.petrifiedWoodChest");
		GameRegistry.registerBlock(blockBones, "erebus.blockBones");
		GameRegistry.registerBlock(blockWitherWeb, "erebus.witherWeb");
		GameRegistry.registerBlock(bambooBridge, "erebus.bambooBridge");
		GameRegistry.registerBlock(extenderThingy, "erebus.extenderThingy");
		GameRegistry.registerBlock(bambooPole, "erebus.bambooPole");

		for (int i = 0; i < umbercobbleStairs.length; i++)
			GameRegistry.registerBlock(umbercobbleStairs[i], "erebus.umbercobbleStairs" + i);
		for (int i = 0; i < plankStairs.length; i++)
			GameRegistry.registerBlock(plankStairs[i], "erebus.plankStairs" + i);
		for (int i = 0; i < stoneSlabs.length; i++)
			GameRegistry.registerBlock(stoneSlabs[i], ItemBlockSlabStoneErebus.class, "erebus.slabStone" + i);
		for (int i = 0; i < plankSlabs.length; i++)
			GameRegistry.registerBlock(plankSlabs[i], i <= 1 ? ItemBlockSlabPlanks0Erebus.class : ItemBlockSlabPlanks1Erebus.class, "erebus.slabPlanks" + i);
		GameRegistry.registerBlock(petrifiedWoodStairs, "erebus.petrifiedWoodStairs");
		for (int i = 0; i < petrifiedWoodSlab.length; i++)
			GameRegistry.registerBlock(petrifiedWoodSlab[i], ItemBlockSlabPetrifiedWood.class, "erebus.petrifiedWoodSlab" + i);
		GameRegistry.registerBlock(amberBrickStairs, "erebus.amberBrickStairs");
		GameRegistry.registerBlock(waspNestStairs, "erebus.waspNestStairs");

		GameRegistry.registerBlock(wallErebus, ItemBlockWallErebus.class, "erebus.wallErebus");
		GameRegistry.registerBlock(insectRepellent, "erebus.blockInsectRepellent");

		GameRegistry.registerBlock(spiderSpawner, "erebus.spiderSpawner");
		GameRegistry.registerBlock(jumpingSpiderSpawner, "erebus.jumpingSpiderSpawner");
		GameRegistry.registerBlock(waspSpawner, "erebus.waspSpawner");

		// Mining levels
		MinecraftForge.setBlockHarvestLevel(blockAmber, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(oreFossil, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(mirBrick, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(spiderSpawner, 0, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(jumpingSpiderSpawner, 0, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(waspSpawner, 0, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberstone, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberPaver, "pickaxe", 0);
		for (int i = 0; i < stoneSlabs.length; i++)
			MinecraftForge.setBlockHarvestLevel(stoneSlabs[i], "pickaxe", 0);
		for (int i = 0; i < plankSlabs.length; i++)
			MinecraftForge.setBlockHarvestLevel(plankSlabs[i], "axe", 0);
		MinecraftForge.setBlockHarvestLevel(wallErebus, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 0, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 2, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 3, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 4, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 5, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(umberOreBlock, 6, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(quickSand, "shovel", 2);
		MinecraftForge.setBlockHarvestLevel(ghostSand, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(reinExo, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(petrifiedWoodChest, "pickaxe", 0);

		if (ConfigurationHandler.aluminium)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 0, "pickaxe", 1);
		if (ConfigurationHandler.copper)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 1, "pickaxe", 1);
		if (ConfigurationHandler.lead)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 2, "pickaxe", 2);
		if (ConfigurationHandler.silver)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 3, "pickaxe", 2);
		if (ConfigurationHandler.tin)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 4, "pickaxe", 1);
	}
}