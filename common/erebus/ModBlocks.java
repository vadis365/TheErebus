package erebus;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOreStorage;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
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
import erebus.block.BlockDoorAmber;
import erebus.block.BlockDoubleHeightPlant;
import erebus.block.BlockErebusAltar;
import erebus.block.BlockErebusAltarHealing;
import erebus.block.BlockErebusAltarLightning;
import erebus.block.BlockErebusAltarRepair;
import erebus.block.BlockErebusAltarXP;
import erebus.block.BlockErebusFlower;
import erebus.block.BlockErebusGrass;
import erebus.block.BlockErebusHoney;
import erebus.block.BlockErebusMushroomCap;
import erebus.block.BlockErebusOre;
import erebus.block.BlockErebusOreExtras;
import erebus.block.BlockErebusStigma;
import erebus.block.BlockExtenderThingy;
import erebus.block.BlockFern;
import erebus.block.BlockFiddlehead;
import erebus.block.BlockGhostSand;
import erebus.block.BlockGlowingJar;
import erebus.block.BlockGneiss;
import erebus.block.BlockHollowLog;
import erebus.block.BlockHoneyComb;
import erebus.block.BlockHoneyTreat;
import erebus.block.BlockInsectRepellent;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;
import erebus.block.BlockMud;
import erebus.block.BlockOreFossil;
import erebus.block.BlockPetrifiedChest;
import erebus.block.BlockPetrifiedCraftingTable;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockPlantedGiantFlower;
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
import erebus.block.BlockUmberstonePillar;
import erebus.block.BlockVelocity;
import erebus.block.BlockWallErebus;
import erebus.block.BlockWaspNest;
import erebus.block.BlockWaspSpawner;
import erebus.block.BlockWitherWeb;
import erebus.block.ErebusMushroomSmall;
import erebus.block.JarOHoney;
import erebus.core.handler.ConfigHandler;
import erebus.item.block.ItemBlockAmber;
import erebus.item.block.ItemBlockColoredSingle;
import erebus.item.block.ItemBlockDoubleHeightPlant;
import erebus.item.block.ItemBlockErebusFlower;
import erebus.item.block.ItemBlockErebusMushroomSmall;
import erebus.item.block.ItemBlockErebusOreExtras;
import erebus.item.block.ItemBlockErebusStigma;
import erebus.item.block.ItemBlockFlowerPlanted;
import erebus.item.block.ItemBlockGneiss;
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
import erebus.item.block.ItemBlockWitherWeb;

public class ModBlocks {

	// FLUIDS
	public static Fluid erebusHoney;

	// PORTAL
	public static BlockPortalErebus portalErebus;

	// TERRAIN AND ORES
	public static Block umberstone, umberOreBlock, oreFossil, redGem, blockAmber, quickSand, ghostSand, erebusOreExtra, umberstoneButton;

	// WOOD
	public static Block logErebusGroup1, logErebusGroup2, planksErebus, erebusSapling, hollowLogAcacia, erebusFlower, erebusStigma;
	public static BlockLeavesErebus leavesErebus;

	// UNDERGROWTH
	public static Block erebusGrass, thorns, blockTurnip, fiddlehead, erebusMushroomSmall;
	public static BlockFern fern;
	public static Block doubleHeightPlant, erebusMushroomCap0, erebusMushroomCap1, erebusMushroomCap2, erebusMushroomCap3, erebusMushroomCap4;

	// DECORATIONS AND UTILITIES
	public static Block blockSilk, mirBrick, petrifiedWoodPlanks, petrifiedCraftingTable, bambooCrate, umberFurnace, umberFurnace_on;
	public static Block umberPaver, insectRepellent, bambooShoot, bambooCrop, bambooTorch, erebusAltar, erebusAltarLightning;
	public static Block erebusAltarHealing, erebusAltarXP, erebusAltarRepair, glowingJar, reinExo, bambooLadder, bambooBridge;
	public static Block umberGolemStatue, petrifiedWoodChest, blockBones, blockWitherWeb, extenderThingy, bambooPole, umberstonePillar;
	public static Block velocityBlock, honeyCombBlock, doorAmber, erebusHoneyBlock, honeyTreat, mud, mudBricks, flowerPlanted, jarOHoney;
	public static Block jadeBlock;

	// STAIRS, SLABS, WALLS
	public static Block[] umbercobbleStairs, plankStairs, stoneSlabs, plankSlabs, petrifiedWoodSlab, gneissStairs;
	public static Block wallErebus, petrifiedWoodStairs, amberBrickStairs, waspNestStairs;

	// DUNGEONS
	public static Block spiderSpawner, jumpingSpiderSpawner, waspSpawner, waspNestBlock, gneiss;

	public static void init() {
		registerFluids();
		initBlocks();
		initCreativeTabs();
		registerBlocks();
		registerProperties();
	}

	private static void initBlocks() {
		portalErebus = (BlockPortalErebus) new BlockPortalErebus(ConfigHandler.portalErebusID).setHardness(-1F).setLightValue(1.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("portalErebus");

		umberstone = new BlockUmberstone(ConfigHandler.umberstoneID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberstone");
		umberOreBlock = new BlockErebusOre(ConfigHandler.umberOreBlockID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreBlockU");
		oreFossil = new BlockOreFossil(ConfigHandler.oreFossilID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreFossilU").setTextureName("erebus:oreFossilU");
		redGem = new BlockRedGem(ConfigHandler.redGemID).setHardness(0.3F).setLightValue(1F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("redGem");
		blockAmber = new BlockAmber(ConfigHandler.blockAmberID).setHardness(1.5F).setResistance(10.0F).setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("blockAmber");
		quickSand = new BlockQuickSand(ConfigHandler.quickSandID).setHardness(28F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("quickSand").setTextureName("erebus:quickSand");
		ghostSand = new BlockGhostSand(ConfigHandler.ghostSandID).setHardness(0.42F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("ghostSand").setTextureName("erebus:ghostSand");
		erebusOreExtra = new BlockErebusOreExtras(ConfigHandler.erebusOreExtraID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("erebusOreExtras");
		umberstoneButton = new BlockButtonUmberstone(ConfigHandler.umberstoneButtonID).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberstoneButton");
		gneiss = new BlockGneiss(ConfigHandler.gneissID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("gneiss");

		logErebusGroup1 = new BlockLogErebus(ConfigHandler.logErebusGroup1ID, 0).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("logErebus1");
		logErebusGroup2 = new BlockLogErebus(ConfigHandler.logErebusGroup2ID, 1).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("logErebus2");
		planksErebus = new BlockPlanksErebus(ConfigHandler.planksErebusID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("planksErebus");
		leavesErebus = (BlockLeavesErebus) new BlockLeavesErebus(ConfigHandler.leavesErebusID).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("leavesErebus");
		erebusSapling = new BlockSaplingErebus(ConfigHandler.erebusSaplingID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("sapling_mahogany");
		hollowLogAcacia = new BlockHollowLog(ConfigHandler.hollowLogAcaciaID).setHardness(0.7F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("hollowLogAcacia");
		erebusFlower = new BlockErebusFlower(ConfigHandler.erebusFlowerID).setHardness(1.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusFlower");
		flowerPlanted = new BlockPlantedGiantFlower(ConfigHandler.flowerPlantedID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("flowerPlanted");
		erebusStigma = new BlockErebusStigma(ConfigHandler.erebusStigmaID).setHardness(1.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusStigma");	
		erebusMushroomSmall = new ErebusMushroomSmall(ConfigHandler.erebusMushroomSmallID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusMushroomSmall");
		erebusMushroomCap0 = new BlockErebusMushroomCap(ConfigHandler.erebusMushroomCap0ID, Material.wood, 0).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mushroomBulbCap");
		erebusMushroomCap1 = new BlockErebusMushroomCap(ConfigHandler.erebusMushroomCap1ID, Material.wood, 1).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mushroom1Cap");
		erebusMushroomCap2 = new BlockErebusMushroomCap(ConfigHandler.erebusMushroomCap2ID, Material.wood, 2).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mushroom2Cap");
		erebusMushroomCap3 = new BlockErebusMushroomCap(ConfigHandler.erebusMushroomCap3ID, Material.wood, 3).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mushroom3Cap");
		erebusMushroomCap4 = new BlockErebusMushroomCap(ConfigHandler.erebusMushroomCap4ID, Material.wood, 4).setHardness(0.2F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("mushroom4Cap");	
		doubleHeightPlant = new BlockDoubleHeightPlant(ConfigHandler.doubleHeightPlantID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("doubleHeightPlant");
		erebusGrass = new BlockErebusGrass(ConfigHandler.erebusGrassID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusTallGrass").setTextureName("erebus:tallgrass");
		thorns = new BlockThorns(ConfigHandler.thornsID).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("thorns").setTextureName("erebus:thorns");
		fern = (BlockFern) new BlockFern(ConfigHandler.fernID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusFern");
		blockTurnip = new BlockTurnip(ConfigHandler.blockTurnipID).setUnlocalizedName("turnips");
		fiddlehead = new BlockFiddlehead(ConfigHandler.fiddleheadID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("erebusFiddlehead");

		blockSilk = new Block(ConfigHandler.blockSilkID, Material.cloth).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("blockSilk").setTextureName("erebus:blockSilk");
		mirBrick = new Block(ConfigHandler.mirBrickID, Material.rock).setHardness(1.5F).setResistance(100.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("mirbrick").setTextureName("erebus:mirbrick");
		petrifiedWoodPlanks = new Block(ConfigHandler.petrifiedWoodPlanksID, Material.rock).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("petrifiedWoodPlanks").setTextureName("erebus:petrifiedWoodPlanks");
		petrifiedCraftingTable = new BlockPetrifiedCraftingTable(ConfigHandler.petrifiedCraftingTableID).setHardness(2.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("petrifiedCraftingTable");
		bambooCrate = new BlockBambooCrate(ConfigHandler.bambooCrateID).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooCrate");
		umberFurnace = new BlockUmberFurnace(ConfigHandler.umberFurnaceID, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberFurnaceOFF");
		umberFurnace_on = new BlockUmberFurnace(ConfigHandler.umberFurnace_onID, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberFurnaceON");
		umberPaver = new BlockUmberPaver(ConfigHandler.umberPaverID).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberPaver");
		insectRepellent = new BlockInsectRepellent(ConfigHandler.insectRepellentID).setUnlocalizedName("insectRepellent");
		bambooShoot = new BlockBambooShoot(ConfigHandler.bambooShootID).setCreativeTab(null).setUnlocalizedName("bambooShoot").setTextureName("erebus:bambooShoot");
		bambooCrop = new BlockBambooCrop(ConfigHandler.bambooCropID).setHardness(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("bambooCrop").setTextureName("erebus:bambooCropBase");
		bambooTorch = new BlockBambooTorch(ConfigHandler.bambooTorchID).setHardness(0.0F).setUnlocalizedName("bambooTorch");
		erebusAltar = new BlockErebusAltar(ConfigHandler.erebusAltarID).setHardness(20.0F).setUnlocalizedName("erebusAltar");
		erebusAltarLightning = new BlockErebusAltarLightning(ConfigHandler.erebusAltarLightningID).setHardness(20.0F).setUnlocalizedName("erebusAltarLightning");
		erebusAltarHealing = new BlockErebusAltarHealing(ConfigHandler.erebusAltarHealingID).setHardness(20.0F).setUnlocalizedName("erebusAltarHealing");
		erebusAltarXP = new BlockErebusAltarXP(ConfigHandler.erebusAltarXPID).setHardness(20.0F).setUnlocalizedName("erebusAltarXP");
		erebusAltarRepair = new BlockErebusAltarRepair(ConfigHandler.erebusAltarRepairID).setHardness(20.0F).setUnlocalizedName("erebusAltarRepair");
		glowingJar = new BlockGlowingJar(ConfigHandler.glowingJarID).setUnlocalizedName("glowingJar").setTextureName("erebus:glassAmber");
		reinExo = new BlockReinExo(ConfigHandler.reinExoID).setHardness(1.5F).setResistance(2000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("reinExo").setTextureName("erebus:blockReinExo");
		bambooLadder = new BlockBambooLadder(ConfigHandler.bambooLadderID).setHardness(0.4F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("bambooLadder").setTextureName("erebus:bambooLadder");
		umberGolemStatue = new BlockUmberGolemStatue(ConfigHandler.umberGolemStatueID).setUnlocalizedName("umberGolemStatue");
		waspNestBlock = new BlockWaspNest(ConfigHandler.waspNestBlockID).setHardness(50.0F).setResistance(2000.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("waspNestBlock").setTextureName("erebus:waspNestBlock");
		petrifiedWoodChest = new BlockPetrifiedChest(ConfigHandler.petrifiedWoodChestID).setHardness(2.0F).setUnlocalizedName("petrifiedWoodChest").setTextureName("erebus:petrifiedWoodPlanks");
		blockBones = new BlockBones(ConfigHandler.blockBonesID).setUnlocalizedName("blockBones");
		blockWitherWeb = new BlockWitherWeb(ConfigHandler.blockWitherWebID).setHardness(4.0F).setUnlocalizedName("witherWeb").setTextureName("web");
		bambooBridge = new BlockBambooBridge(ConfigHandler.bambooBridgeID).setHardness(0.4F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("bambooBridge").setTextureName("erebus:bambooBridge");
		extenderThingy = new BlockExtenderThingy(ConfigHandler.extenderThingyID).setHardness(0.4F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("extenderThingy").setTextureName("erebus:extenderThingy");
		bambooPole = new BlockBambooPole(ConfigHandler.bambooPoleID).setHardness(0.4F).setUnlocalizedName("bambooPole").setTextureName("erebus:blockBambooPole");
		umberstonePillar = new BlockUmberstonePillar(ConfigHandler.umberstonePillarID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umberstonePillar").setTextureName("erebus:umberstonePillarSides");
		velocityBlock = new BlockVelocity(ConfigHandler.velocityBlockID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("velocityBlock").setTextureName("erebus:blockSpeed0");
		honeyCombBlock = new BlockHoneyComb(ConfigHandler.honeyCombBlockID).setHardness(0.5F).setResistance(10.0F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("honeyCombBlock").setTextureName("erebus:honeyCombTop");
		doorAmber = new BlockDoorAmber(ConfigHandler.doorAmberID, Material.glass).setUnlocalizedName("doorAmber").setTextureName("erebus:doorAmber");
		erebusHoneyBlock = new BlockErebusHoney(ConfigHandler.erebusHoneyFluidID, erebusHoney).setUnlocalizedName("erebusHoney");
		mud = new BlockMud(ConfigHandler.mudID).setUnlocalizedName("erebusMud").setTextureName("erebus:mud");
		mudBricks = new Block(ConfigHandler.mudBricksID, Material.rock).setUnlocalizedName("erebus.mudBricks").setTextureName("erebus:mudBricks").setHardness(0.8F).setResistance(1.0F);
		honeyTreat = new BlockHoneyTreat(ConfigHandler.honeyTreatID).setHardness(0.5F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("honeyTreat").setTextureName("erebus:honeyTreat");
		jarOHoney = new JarOHoney(ConfigHandler.jarOHoneyID).setHardness(0.5F).setUnlocalizedName("erebus.jarOHoney").setTextureName("erebus:glassAmber");
		jadeBlock = new BlockOreStorage(ConfigHandler.jadeBlockID).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("erebus.blockJade").setTextureName("erebus:blockJade");

		umbercobbleStairs = new Block[BlockUmberstone.iconPaths.length];
		for (int i = 0; i < umbercobbleStairs.length; i++)
			umbercobbleStairs[i] = new BlockStairsErebus(ConfigHandler.umbercobbleStairsID[i], umberstone, i).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("umbercobbleStairs" + i);
		plankStairs = new Block[BlockPlanksErebus.plankTypes.length];
		for (int i = 0; i < BlockPlanksErebus.plankTypes.length; i++)
			plankStairs[i] = new BlockStairsErebus(ConfigHandler.plankStairsID[i], planksErebus, i).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("stairsPlanks" + i);
		petrifiedWoodStairs = new BlockStairsErebus(ConfigHandler.petrifiedWoodStairsID, petrifiedWoodPlanks, 0).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("petrifiedWoodStairs");
		stoneSlabs = new Block[2];
		for (int i = 0; i < 2; i++)
			stoneSlabs[i] = new BlockSlabStoneErebus(ConfigHandler.stoneSlabsID[i], i == 1).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("slabStoneErebus");
		plankSlabs = new Block[4];
		for (int i = 0; i < 4; i++)
			plankSlabs[i] = new BlockSlabPlanksErebus(ConfigHandler.plankSlabsID[i], (int) Math.floor(i / 2f), i % 2 == 1).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("slabPlanksErebus");
		wallErebus = new BlockWallErebus(ConfigHandler.wallErebusID).setUnlocalizedName("wallErebus");
		petrifiedWoodSlab = new Block[2];
		for (int i = 0; i < petrifiedWoodSlab.length; i++)
			petrifiedWoodSlab[i] = new BlockSlabPetrifiedWood(ConfigHandler.petrifiedWoodSlabID[i], i == 1).setUnlocalizedName("petrifiedWoodSlab");
		amberBrickStairs = new BlockStairsErebus(ConfigHandler.amberBrickStairsID, blockAmber, 2).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("amberBrickStairs");
		waspNestStairs = new BlockStairsErebus(ConfigHandler.waspNestStairsID, waspNestBlock, 2).setHardness(50.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("waspNestStairs");
		gneissStairs = new Block[BlockGneiss.iconPaths.length];
		for (int i = 0; i < gneissStairs.length; i++)
			gneissStairs[i] = new BlockStairsErebus(ConfigHandler.gneissStairsID[i], gneiss, i).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("gneissStairs" + i);
		//
		spiderSpawner = new BlockSpiderSpawner(ConfigHandler.spiderSpawnerID, "Scytodes - Erebus").setUnlocalizedName("spiderSpawner").setTextureName("erebus:spiderSpawner");
		jumpingSpiderSpawner = new BlockSpiderSpawner(ConfigHandler.jumpingSpiderSpawnerID, "JumpingSpider - Erebus").setUnlocalizedName("jumpingSpiderSpawner").setTextureName("erebus:spiderSpawner");
		waspSpawner = new BlockWaspSpawner(ConfigHandler.waspSpawnerID, "Wasp - Erebus").setUnlocalizedName("waspSpawner").setTextureName("erebus:waspNestSpawner");
	}

	private static void initCreativeTabs() {
		// Break down long lines
		Erebus.tabErebusBlock.add(umberstone, umberOreBlock, oreFossil, erebusOreExtra, redGem, blockAmber, quickSand, ghostSand);
		Erebus.tabErebusBlock.add(logErebusGroup1, logErebusGroup2, hollowLogAcacia, planksErebus, leavesErebus, erebusSapling);
		Erebus.tabErebusBlock.add(erebusGrass, fern, fiddlehead, thorns, erebusFlower, erebusStigma, doubleHeightPlant, erebusMushroomSmall);
		Erebus.tabErebusBlock.add(erebusMushroomCap0, erebusMushroomCap1, erebusMushroomCap2, erebusMushroomCap3, erebusMushroomCap4);
		Erebus.tabErebusBlock.add(blockSilk, mirBrick, petrifiedWoodPlanks, petrifiedCraftingTable, bambooCrop, bambooCrate, bambooLadder);
		Erebus.tabErebusBlock.add(bambooBridge, bambooPole, bambooTorch, glowingJar, umberstoneButton, umberFurnace, umberPaver, erebusAltar);
		Erebus.tabErebusBlock.add(reinExo, waspNestBlock, petrifiedWoodChest, blockBones, blockWitherWeb, extenderThingy);
		Erebus.tabErebusBlock.add(umberstonePillar, velocityBlock, honeyCombBlock, honeyTreat, erebusHoneyBlock, gneiss, mud, mudBricks, jarOHoney);
		Erebus.tabErebusBlock.add(jadeBlock);
		for (Block b : umbercobbleStairs)
			Erebus.tabErebusBlock.add(b);
		for (Block b : plankStairs)
			Erebus.tabErebusBlock.add(b);
		for (Block b : gneissStairs)
			Erebus.tabErebusBlock.add(b);
		Erebus.tabErebusBlock.add(petrifiedWoodStairs, amberBrickStairs, waspNestStairs, stoneSlabs[0], plankSlabs[0]);
		Erebus.tabErebusBlock.add(plankSlabs[2], petrifiedWoodSlab[0], wallErebus);
	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(portalErebus, "erebus.portal");

		GameRegistry.registerBlock(umberstone, ItemBlockUmberStone.class, "erebus.umberstone");
		GameRegistry.registerBlock(umberOreBlock, ItemBlockUmberOre.class, "erebus.oreBlockU");
		GameRegistry.registerBlock(oreFossil, "erebus.oreFossil");
		GameRegistry.registerBlock(redGem, ItemBlockRedGem.class, "erebus.redGem");
		GameRegistry.registerBlock(blockAmber, ItemBlockAmber.class, "erebus.blockAmber");
		GameRegistry.registerBlock(quickSand, "erebus.quickSand");
		GameRegistry.registerBlock(ghostSand, "erebus.ghostSand");
		if (ConfigHandler.lead || ConfigHandler.silver || ConfigHandler.copper || ConfigHandler.tin || ConfigHandler.aluminium)
			GameRegistry.registerBlock(erebusOreExtra, ItemBlockErebusOreExtras.class, "erebus.erebusOreExtras");
		GameRegistry.registerBlock(umberstoneButton, "erebus.umberstoneButton");

		GameRegistry.registerBlock(logErebusGroup1, ItemBlockLogErebus1.class, "erebus.logErebus1");
		GameRegistry.registerBlock(logErebusGroup2, ItemBlockLogErebus2.class, "erebus.logErebus2");
		GameRegistry.registerBlock(planksErebus, ItemBlockPlanksErebus.class, "erebus.planksErebus");
		GameRegistry.registerBlock(leavesErebus, ItemBlockLeavesErebus.class, "erebus.leavesErebus");
		GameRegistry.registerBlock(erebusSapling, ItemBlockSapling.class, "erebus.erebusSapling");
		GameRegistry.registerBlock(hollowLogAcacia, "erebus.hollowLogAcacia");
		GameRegistry.registerBlock(erebusFlower, ItemBlockErebusFlower.class, "erebus.erebusFlower");
		GameRegistry.registerBlock(erebusStigma, ItemBlockErebusStigma.class, "erebus.erebusStigma");

		GameRegistry.registerBlock(erebusGrass, "erebus.erebusGrass");
		GameRegistry.registerBlock(thorns, "erebus.thorns");
		GameRegistry.registerBlock(fern, ItemBlockColoredSingle.class, "erebus.fern");
		GameRegistry.registerBlock(blockTurnip, "erebus.blockTurnip");
		GameRegistry.registerBlock(fiddlehead, "erebus.fiddlehead");
		GameRegistry.registerBlock(flowerPlanted, ItemBlockFlowerPlanted.class, "erebus.flowerPlanted");
		GameRegistry.registerBlock(doubleHeightPlant, ItemBlockDoubleHeightPlant.class, "erebus.doubleHeightPlant");

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
		GameRegistry.registerBlock(blockWitherWeb, ItemBlockWitherWeb.class, "erebus.witherWeb");
		GameRegistry.registerBlock(bambooBridge, "erebus.bambooBridge");
		GameRegistry.registerBlock(extenderThingy, "erebus.extenderThingy");
		GameRegistry.registerBlock(bambooPole, "erebus.bambooPole");
		GameRegistry.registerBlock(umberstonePillar, "erebus.umberstonePillar");
		GameRegistry.registerBlock(velocityBlock, "erebus.velocityBlock");
		GameRegistry.registerBlock(honeyCombBlock, "erebus.honeyCombBlock");
		GameRegistry.registerBlock(doorAmber, "erebus.doorAmber");
		GameRegistry.registerBlock(gneiss, ItemBlockGneiss.class, "erebus.gneiss");
		GameRegistry.registerBlock(mud, "erebus.mud");
		GameRegistry.registerBlock(mudBricks, "erebus.mudBricks");
		GameRegistry.registerBlock(erebusMushroomCap0, "erebus.mushroomBulbCap");
		GameRegistry.registerBlock(erebusMushroomCap1, "erebus.mushroom1Cap");
		GameRegistry.registerBlock(erebusMushroomCap2, "erebus.mushroom2Cap");
		GameRegistry.registerBlock(erebusMushroomCap3, "erebus.mushroom3Cap");
		GameRegistry.registerBlock(erebusMushroomCap4, "erebus.mushroom4Cap");
		GameRegistry.registerBlock(erebusMushroomSmall, ItemBlockErebusMushroomSmall.class, "erebus.erebusMushroomSmall");
		GameRegistry.registerBlock(honeyTreat, "erebus.honeyTreat");
		GameRegistry.registerBlock(jarOHoney, "erebus.jarOHoney");
		GameRegistry.registerBlock(jadeBlock, "erebus.jadeBlock");

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
		for (int i = 0; i < gneissStairs.length; i++)
			GameRegistry.registerBlock(gneissStairs[i], "erebus.gneissStairs" + i);

		GameRegistry.registerBlock(wallErebus, ItemBlockWallErebus.class, "erebus.wallErebus");
		GameRegistry.registerBlock(insectRepellent, "erebus.blockInsectRepellent");

		GameRegistry.registerBlock(spiderSpawner, "erebus.spiderSpawner");
		GameRegistry.registerBlock(jumpingSpiderSpawner, "erebus.jumpingSpiderSpawner");
		GameRegistry.registerBlock(waspSpawner, "erebus.waspSpawner");

		GameRegistry.registerBlock(erebusHoneyBlock, "erebus.erebusHoney");
	}

	private static void registerProperties() {
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
		MinecraftForge.setBlockHarvestLevel(jadeBlock, "pickaxe", 2);

		if (ConfigHandler.aluminium)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 0, "pickaxe", 1);
		if (ConfigHandler.copper)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 1, "pickaxe", 1);
		if (ConfigHandler.lead)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 2, "pickaxe", 2);
		if (ConfigHandler.silver)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 3, "pickaxe", 2);
		if (ConfigHandler.tin)
			MinecraftForge.setBlockHarvestLevel(erebusOreExtra, 4, "pickaxe", 1);

		Block.setBurnProperties(logErebusGroup1.blockID, 5, 5);
		Block.setBurnProperties(logErebusGroup2.blockID, 5, 5);
		Block.setBurnProperties(planksErebus.blockID, 5, 20);
		Block.setBurnProperties(leavesErebus.blockID, 30, 60);
		Block.setBurnProperties(blockWitherWeb.blockID, 20, 50);
		Block.setBurnProperties(erebusGrass.blockID, 60, 100);
		Block.setBurnProperties(fern.blockID, 60, 100);
		Block.setBurnProperties(fiddlehead.blockID, 60, 100);
		Block.setBurnProperties(thorns.blockID, 15, 100);
	}

	private static void registerFluids() {
		erebusHoney = new Fluid("honey").setBlockID(ConfigHandler.erebusHoneyFluidID).setDensity(6000).setViscosity(6000).setUnlocalizedName("erebus.honey");
		FluidRegistry.registerFluid(erebusHoney);
	}
}