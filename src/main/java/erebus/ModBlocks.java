package erebus;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockAmber;
import erebus.block.BlockAntlionEgg;
import erebus.block.BlockAntlionSpawner;
import erebus.block.BlockBones;
import erebus.block.BlockButtonUmberstone;
import erebus.block.BlockCapstone;
import erebus.block.BlockComposter;
import erebus.block.BlockDoorErebus;
import erebus.block.BlockDragonflySpawner;
import erebus.block.BlockErebusHoney;
import erebus.block.BlockErebusStigma;
import erebus.block.BlockForceField;
import erebus.block.BlockGhostSand;
import erebus.block.BlockGlowGem;
import erebus.block.BlockGlowingJar;
import erebus.block.BlockGneiss;
import erebus.block.BlockGneissVent;
import erebus.block.BlockHollowLog;
import erebus.block.BlockHoneyComb;
import erebus.block.BlockHoneyTreat;
import erebus.block.BlockMagmaCrawlerSpawner;
import erebus.block.BlockMucusBomb;
import erebus.block.BlockMud;
import erebus.block.BlockPetrifiedChest;
import erebus.block.BlockPetrifiedCraftingTable;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockPlantedGiantFlower;
import erebus.block.BlockPreservedBlock;
import erebus.block.BlockQuickSand;
import erebus.block.BlockRedGem;
import erebus.block.BlockSimple;
import erebus.block.BlockSlabStone;
import erebus.block.BlockSpiderSpawner;
import erebus.block.BlockStairsBase;
import erebus.block.BlockSwampVent;
import erebus.block.BlockTarantulaEgg;
import erebus.block.BlockTempleBrickUnbreaking;
import erebus.block.BlockTempleTeleporter;
import erebus.block.BlockUmberFurnace;
import erebus.block.BlockUmberGolemStatue;
import erebus.block.BlockUmberGravel;
import erebus.block.BlockUmberPaver;
import erebus.block.BlockUmberstone;
import erebus.block.BlockUmberstonePillar;
import erebus.block.BlockWallErebus;
import erebus.block.BlockWaspNest;
import erebus.block.BlockWaspSpawner;
import erebus.block.BlockWitherWeb;
import erebus.block.ErebusPortal;
import erebus.block.GaeanKeystone;
import erebus.block.InsectRepellent;
import erebus.block.JarOHoney;
import erebus.block.LightningSpeedBlock;
import erebus.block.SaplessLog;
import erebus.block.VelocityBlock;
import erebus.block.altars.AltarBase;
import erebus.block.altars.HealingAltar;
import erebus.block.altars.LightningAltar;
import erebus.block.altars.OfferingAltar;
import erebus.block.altars.RepairAltar;
import erebus.block.altars.XPAltar;
import erebus.block.bamboo.BlockBambooBridge;
import erebus.block.bamboo.BlockBambooCrate;
import erebus.block.bamboo.BlockBambooCrop;
import erebus.block.bamboo.BlockBambooLadder;
import erebus.block.bamboo.BlockBambooPole;
import erebus.block.bamboo.BlockBambooShoot;
import erebus.block.bamboo.BlockBambooTorch;
import erebus.block.bamboo.BlockExtenderThingy;
import erebus.block.cooking.BlockSmoothieMaker;
import erebus.block.glowshroom.BlockGlowshroom;
import erebus.block.glowshroom.BlockGlowshroomStalkDown1;
import erebus.block.glowshroom.BlockGlowshroomStalkDown2;
import erebus.block.glowshroom.BlockGlowshroomStalkDown3;
import erebus.block.glowshroom.BlockGlowshroomStalkE1;
import erebus.block.glowshroom.BlockGlowshroomStalkE3;
import erebus.block.glowshroom.BlockGlowshroomStalkMain;
import erebus.block.glowshroom.BlockGlowshroomStalkN1;
import erebus.block.glowshroom.BlockGlowshroomStalkN3;
import erebus.block.glowshroom.BlockGlowshroomStalkNS2;
import erebus.block.glowshroom.BlockGlowshroomStalkS1;
import erebus.block.glowshroom.BlockGlowshroomStalkS3;
import erebus.block.glowshroom.BlockGlowshroomStalkW1;
import erebus.block.glowshroom.BlockGlowshroomStalkW3;
import erebus.block.glowshroom.BlockGlowshroomStalkWE2;
import erebus.block.ores.UmberOre;
import erebus.block.ores.UmberOreEncrustedDiamond;
import erebus.block.ores.UmberOreExtra;
import erebus.block.ores.UmberOreFossil;
import erebus.block.ores.UmberOreGneiss;
import erebus.block.ores.UmberOreJade;
import erebus.block.ores.UmberOrePetrifiedWood;
import erebus.block.plants.BlockAlgae;
import erebus.block.plants.BlockBerryBush;
import erebus.block.plants.BlockCabbage;
import erebus.block.plants.BlockErebusFlower;
import erebus.block.plants.BlockHangerPlants;
import erebus.block.plants.BlockMireCoral;
import erebus.block.plants.BlockThorns;
import erebus.block.plants.BlockTurnip;
import erebus.block.plants.BlockWallPlants;
import erebus.block.plants.BlockWallPlantsCultivated;
import erebus.block.plants.DoubleHeightPlant;
import erebus.block.plants.ErebusHugeMushroom;
import erebus.block.plants.SmallMushroom;
import erebus.block.plants.SmallPlant;
import erebus.block.silo.BlockSiloRoof;
import erebus.block.silo.BlockSiloSupports;
import erebus.block.silo.BlockSiloTank;
import erebus.lib.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;

public class ModBlocks {

	// LIST WITH ALL BLOCKS IN THIS CLASS
	public static final List<Block> BLOCKS = new LinkedList<Block>();

	// PORTAL
	public static final Block portal = new ErebusPortal();

	// TERRAIN
	public static final Block umberstone = new BlockUmberstone();
	public static final Block redGem = new BlockRedGem();
	public static final Block amber = new BlockAmber();
	public static final Block quickSand = new BlockQuickSand();
	public static final Block ghostSand = new BlockGhostSand();
	public static final Block umberstoneButton = new BlockButtonUmberstone();
	public static final Block volcanicRock = new BlockSimple(Material.rock).setHardness(5.0F).setResistance(20.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.volcanicRock").setBlockTextureName("erebus:volcanicRock");
	public static final Block swampVent = new BlockSwampVent();
	public static final Block umbergravel = new BlockUmberGravel();

	// ORES
	public static final Block oreCoal = new UmberOre(Blocks.coal_ore, "Coal", 0);
	public static final Block oreIron = new UmberOre(Blocks.iron_ore, "Iron", 1);
	public static final Block oreGold = new UmberOre(Blocks.gold_ore, "Gold", 2);
	public static final Block oreLapis = new UmberOre(Blocks.lapis_ore, "Lapis", 1);
	public static final Block oreDiamond = new UmberOre(Blocks.diamond_ore, "Diamond", 2);
	public static final Block oreEmerald = new UmberOre(Blocks.emerald_ore, "Emerald", 2);
	public static final Block oreJade = new UmberOreJade();
	public static final Block oreEncrustedDiamond = new UmberOreEncrustedDiamond();
	public static final Block orePetrifiedWood = new UmberOrePetrifiedWood();
	public static final Block oreFossil = new UmberOreFossil();
	public static final Block oreGneiss = new UmberOreGneiss();
	public static final Block oreAluminium = new UmberOreExtra("Aluminium", 1);
	public static final Block oreCopper = new UmberOreExtra("Copper", 1);
	public static final Block oreLead = new UmberOreExtra("Lead", 2);
	public static final Block oreSilver = new UmberOreExtra("Silver", 2);
	public static final Block oreTin = new UmberOreExtra("Tin", 1);

	// WOOD
	public static final Block planks = new BlockPlanksErebus();
	public static final Block hollowLogAcacia = new BlockHollowLog();
	public static final Block erebusFlower = new BlockErebusFlower().setHardness(1.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebus.flower");
	public static final Block stiga = new BlockErebusStigma().setHardness(1.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebus.stigma");
	public static final Block scorchedWood = new BlockSimple(Material.wood).setHardness(2.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("erebus.log_scorched").setBlockTextureName("erebus:log_scorched");
	public static final Block scorchedPlanks = new BlockSimple(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("erebus.planks_scorched").setBlockTextureName("erebus:planks_scorched");
	public static final Block varnishedPlanks = new BlockSimple(Material.wood).setHardness(2.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("erebus.planks_varnished").setBlockTextureName("erebus:planks_varnished");
	public static final Block saplessLog = new SaplessLog();

	// DOUBLE PLANTS
	public static final Block sundew = new DoubleHeightPlant("Sundew", 0.8F);
	public static final Block weepingBlue = new DoubleHeightPlant("WeepingBlue", 0.5F);
	public static final Block bullrush = new DoubleHeightPlant("Bullrush");
	public static final Block droughtedShrub = new DoubleHeightPlant("DroughtedShrub");
	public static final Block tangledStalk = new DoubleHeightPlant("TangledStalk");
	public static final Block highCapped = new DoubleHeightPlant("HighCapped", 0.6F);
	public static final Block waterFlower = new DoubleHeightPlant("WaterFlower", 0.5F);

	// SMALL PLANTS
	public static final Block fiddlehead = new SmallPlant("fiddlehead", true);
	public static final Block fern = new SmallPlant("fern", true);
	public static final Block fireBloom = new SmallPlant("fireBloom", false);
	public static final Block swampPlant = new SmallPlant("swampPlant", false);
	public static final Block nettleFlowered = new SmallPlant("nettleFlowered", false);
	public static final Block nettle = new SmallPlant("nettle", false);
	public static final Block desertShrub = new SmallPlant("desertShrub", false);
	public static final Block bulbCapped = new SmallMushroom("bulbCappedShroom");
	public static final Block kaizerfinger = new SmallMushroom("kaizerfinger");
	public static final Block bundleshroom = new SmallMushroom("bundleshroom");
	public static final Block greenMushroom = new SmallMushroom("greenMushroom");
	public static final Block dutchCap = new SmallMushroom("dutchCap");

	// UNDERGROWTH
	public static final Block thorns = new BlockThorns();
	public static final Block blockTurnip = new BlockTurnip();
	public static final Block hanger = new BlockHangerPlants();
	public static final Block wallPlants = new BlockWallPlants();
	public static final Block wallPlantsCultivated = new BlockWallPlantsCultivated();
	public static final Block bigBulbCappedMushroom = new ErebusHugeMushroom(0, "bulbcapped", bulbCapped);
	public static final Block bigGreenMushroom = new ErebusHugeMushroom(1, "green", greenMushroom);
	public static final Block bigBundleMushroom = new ErebusHugeMushroom(2, "bundleshroom", bundleshroom);
	public static final Block bigKaiserfingerMushroom = new ErebusHugeMushroom(3, "kaizerfinger", kaizerfinger);
	public static final Block bigDutchCapMushroom = new ErebusHugeMushroom(4, "dutchcap", dutchCap);
	public static final Block glowshroom = new BlockGlowshroom();
	public static final Block glowshroomStalkMain = new BlockGlowshroomStalkMain();
	public static final Block glowshroomStalkDown1 = new BlockGlowshroomStalkDown1();
	public static final Block glowshroomStalkDown2 = new BlockGlowshroomStalkDown2();
	public static final Block glowshroomStalkDown3 = new BlockGlowshroomStalkDown3();
	public static final Block glowshroomStalkN1 = new BlockGlowshroomStalkN1();
	public static final Block glowshroomStalkS1 = new BlockGlowshroomStalkS1();
	public static final Block glowshroomStalkNS2 = new BlockGlowshroomStalkNS2();
	public static final Block glowshroomStalkW1 = new BlockGlowshroomStalkW1();
	public static final Block glowshroomStalkE1 = new BlockGlowshroomStalkE1();
	public static final Block glowshroomStalkWE2 = new BlockGlowshroomStalkWE2();
	public static final Block glowshroomStalkN3 = new BlockGlowshroomStalkN3();
	public static final Block glowshroomStalkS3 = new BlockGlowshroomStalkS3();
	public static final Block glowshroomStalkW3 = new BlockGlowshroomStalkW3();
	public static final Block glowshroomStalkE3 = new BlockGlowshroomStalkE3();
	public static final Block flowerPlanted = new BlockPlantedGiantFlower();
	public static final Block jadeBerryBush = new BlockBerryBush("jade");
	public static final Block heartBerryBush = new BlockBerryBush("heart");
	public static final Block swampBerryBush = new BlockBerryBush("swamp");
	public static final Block mireCoral = new BlockMireCoral().setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockName("erebus.mireCoral").setBlockTextureName("erebus:swampGlowWeed").setLightLevel(0.8F);
	public static final Block algae = new BlockAlgae().setBlockName("erebus.algae").setBlockTextureName("erebus:algae");
	public static final Block blockCabbage = new BlockCabbage();
	public static final Block giantLilyPad = new BlockSimple(Material.wood, "axe", 1).setHardness(5.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebus.giantLilyPad").setBlockTextureName("erebus:giantLilyPad").setCreativeTab(ModTabs.blocks);

	// DECORATIONS AND UTILITIES
	public static final Block blockSilk = new BlockSimple(Material.cloth).setHardness(0.2F).setStepSound(Block.soundTypeCloth).setBlockName("erebus.blockSilk").setBlockTextureName("erebus:blockSilk").setCreativeTab(ModTabs.blocks);
	public static final Block mirBrick = new BlockSimple(Material.rock, "pickaxe", 1).setHardness(1.5F).setStepSound(Block.soundTypeStone).setBlockName("erebus.mirbrick").setBlockTextureName("erebus:mirbrick").setCreativeTab(ModTabs.blocks);
	public static final Block petrifiedWoodPlanks = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("erebus.petrifiedWoodPlanks").setBlockTextureName("erebus:planks_petrifiedWood").setCreativeTab(ModTabs.blocks);
	public static final Block petrifiedCraftingTable = new BlockPetrifiedCraftingTable();
	public static final Block bambooCrate = new BlockBambooCrate();
	public static final Block umberFurnace = new BlockUmberFurnace();
	public static final Block umberPaver = new BlockUmberPaver();
	public static final Block insectRepellent = new InsectRepellent();
	public static final Block bambooShoot = new BlockBambooShoot();
	public static final Block bambooCrop = new BlockBambooCrop().setHardness(1.0F).setStepSound(Block.soundTypeWood).setBlockName("erebus.bambooCrop").setBlockTextureName("erebus:bambooCropBase");
	public static final Block bambooTorch = new BlockBambooTorch();
	public static final Block glowingJar = new BlockGlowingJar();
	public static final Block reinExo = new BlockSimple(Material.rock).setHardness(1.5F).setResistance(2000.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.reinExo").setBlockTextureName("erebus:blockReinExo").setCreativeTab(ModTabs.blocks);
	public static final Block bambooLadder = new BlockBambooLadder();
	public static final Block bambooBridge = new BlockBambooBridge();
	public static final Block umberGolemStatue = new BlockUmberGolemStatue();
	public static final Block petrifiedWoodChest = new BlockPetrifiedChest();
	public static final Block bones = new BlockBones();
	public static final Block witherWeb = new BlockWitherWeb();
	public static final Block extenderThingy = new BlockExtenderThingy();
	public static final Block bambooPole = new BlockBambooPole();
	public static final Block umberstonePillar = new BlockUmberstonePillar();
	public static final Block honeyCombBlock = new BlockHoneyComb();
	public static final Block honeyBlock = new BlockErebusHoney();
	public static final Block honeyTreat = new BlockHoneyTreat();
	public static final Block mud = new BlockMud();
	public static final Block mudBricks = new BlockSimple(Material.rock).setBlockName("erebus.mudBricks").setBlockTextureName("erebus:mudBricks").setHardness(0.8F).setResistance(1.0F).setCreativeTab(ModTabs.blocks);
	public static final Block jarOHoney = new JarOHoney();
	public static final Block jadeBlock = new BlockCompressed(MapColor.greenColor).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("erebus.blockJade").setBlockTextureName("erebus:blockJade").setCreativeTab(ModTabs.blocks);
	public static final Block glowGemBlock = new BlockGlowGem();
	public static final Block mucusBomb = new BlockMucusBomb();
	public static final Block siloSupports = new BlockSiloSupports(Material.wood).setHardness(2F).setStepSound(Block.soundTypeWood).setBlockName("erebus.siloSupports").setBlockTextureName("erebus:siloSupports");
	public static final Block siloTank = new BlockSiloTank(Material.iron).setHardness(3F).setStepSound(Block.soundTypeMetal).setBlockName("erebus.siloTank");
	public static final Block siloRoof = new BlockSiloRoof(Material.iron).setHardness(3F).setStepSound(Block.soundTypeMetal).setBlockName("erebus.siloRoof").setBlockTextureName("erebus:siloRoof");
	public static final Block composter = new BlockComposter().setHardness(3.5F).setStepSound(Block.soundTypeStone).setBlockName("erebus.composter");

	// DOORS
	public static final Block doorAmber = new BlockDoorErebus("amber", Material.glass).setRenderPass(1);
	public static final Block doorBaobab = new BlockDoorErebus("baobab");
	public static final Block doorEucalyptus = new BlockDoorErebus("eucalyptus");
	public static final Block doorMahogany = new BlockDoorErebus("mahogany");
	public static final Block doorMossbark = new BlockDoorErebus("mossbark");
	public static final Block doorAsper = new BlockDoorErebus("asper");
	public static final Block doorCypress = new BlockDoorErebus("cypress");
	public static final Block doorRotten = new BlockDoorErebus("rotten");
	public static final Block doorPetrified = new BlockDoorErebus("petrified");
	public static final Block doorScorched = new BlockDoorErebus("scorched");
	public static final Block doorMarshwood = new BlockDoorErebus("marshwood");

	// VELOCITY BLOCKS
	public static final Block velocityBlock = new VelocityBlock();
	public static final Block lightningSpeedBlock = new LightningSpeedBlock();

	// ALTARS
	public static final Block altarBase = new AltarBase().setBlockName("erebus.altarBase");
	public static final Block altarLightning = new LightningAltar().setBlockName("erebus.altarLightning");
	public static final Block altarHealing = new HealingAltar().setBlockName("erebus.altarHealing");
	public static final Block altarXP = new XPAltar().setBlockName("erebus.altarXP");
	public static final Block altarRepair = new RepairAltar().setBlockName("erebus.altarRepair");
	public static final Block offeringAltar = new OfferingAltar();

	// DUNGEONS
	public static final Block spiderSpawner = new BlockSpiderSpawner("scytodes").setBlockName("erebus.spiderSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block jumpingSpiderSpawner = new BlockSpiderSpawner("jumpingSpider").setBlockName("erebus.jumpingSpiderSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block tarantulaSpawner = new BlockSpiderSpawner("tarantula").setBlockName("erebus.tarantulaSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block waspSpawner = new BlockWaspSpawner("wasp").setBlockName("erebus.waspSpawner").setBlockTextureName("erebus:waspNestSpawner");
	public static final Block antlionSpawner = new BlockAntlionSpawner("antlion").setBlockName("erebus.antlionSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block magmaCrawlerSpawner = new BlockMagmaCrawlerSpawner("magmaCrawler").setBlockName("erebus.magmaCrawlerSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block dragonflySpawner = new BlockDragonflySpawner("dragonfly").setBlockName("erebus.dragonflySpawner").setBlockTextureName("erebus:spiderSpawner");

	public static final Block capstone = new BlockCapstone();
	public static final Block waspNestBlock = new BlockWaspNest();
	public static final Block gneiss = new BlockGneiss();
	public static final Block templeBrick = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.templeBrick").setBlockTextureName("erebus:templeBrick");
	public static final Block templePillar = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.templePillar").setBlockTextureName("erebus:templePillar");
	public static final Block templeTile = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.templeTile").setBlockTextureName("erebus:templeTile");
	public static final Block gneissVent = new BlockGneissVent();
	public static final Block templeBrickUnbreaking = new BlockTempleBrickUnbreaking();
	public static final Block templeTeleporter = new BlockTempleTeleporter().setStepSound(Block.soundTypeStone).setBlockTextureName("erebus:templeBrick");
	public static final Block forceField = new BlockForceField().setBlockTextureName("erebus:forceField");

	// STAIRS, SLABS, WALLS
	public static final Block[] umbercobbleStairs = new Block[BlockUmberstone.iconPaths.length];
	public static final Block[] stoneSlabs = new Block[8];
	public static final Block[] gneissStairs = new Block[BlockGneiss.iconPaths.length];
	public static final Block wall = new BlockWallErebus();
	public static final Block petrifiedWoodStairs = new BlockStairsBase(petrifiedWoodPlanks, 0).setStepSound(Block.soundTypeWood).setBlockName("erebus.petrifiedWoodStairs");
	public static final Block amberBrickStairs = new BlockStairsBase(amber, 2).setStepSound(Block.soundTypeStone).setBlockName("erebus.amberBrickStairs");
	public static final Block waspNestStairs = new BlockStairsBase(waspNestBlock, 2).setHardness(50.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.waspNestStairs");

	// COOKING
	public static final Block smoothieMaker = new BlockSmoothieMaker();

	// OTHER THINGS
	public static final Block gaeanKeystone = new GaeanKeystone();
	public static final Block tarantulaEgg = new BlockTarantulaEgg();
	public static final Block antlionEgg = new BlockAntlionEgg();
	public static final Block preservedBlock = new BlockPreservedBlock();

	public static void init() {
		initBlocks();
		EnumWood.initBlocks();

		registerBlocks();
		registerProperties();
	}

	private static void initBlocks() {
		for (int i = 0; i < umbercobbleStairs.length; i++)
			umbercobbleStairs[i] = new BlockStairsBase(umberstone, i).setStepSound(Block.soundTypeStone).setBlockName("erebus.umbercobbleStairs" + i);
		for (int i = 0; i <= 4; i++)
			stoneSlabs[i] = new BlockSlabStone(ModBlocks.umberstone, i, BlockUmberstone.iconPaths[i]);
		for (int i = 0; i <= 2; i++)
			stoneSlabs[5 + i] = new BlockSlabStone(ModBlocks.umberPaver, i, "umberpaver" + BlockUmberPaver.types[i]);
		stoneSlabs[7] = new BlockSlabStone(ModBlocks.petrifiedWoodPlanks, "petrifiedWood");
		for (int i = 0; i < gneissStairs.length; i++)
			gneissStairs[i] = new BlockStairsBase(gneiss, i).setStepSound(Block.soundTypeStone).setBlockName("erebus.gneissStairs" + i);
	}

	private static void registerBlocks() {
		try {
			for (Field f : ModBlocks.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Block)
					registerBlock((Block) obj);
				else if (obj instanceof Block[])
					for (Block block : (Block[]) obj)
						registerBlock(block);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void registerBlock(Block block) {
		BLOCKS.add(block);
		String name = block.getUnlocalizedName();
		String[] strings = name.split("\\.");

		if (block instanceof ISubBlocksBlock)
			GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
		else
			GameRegistry.registerBlock(block, strings[strings.length - 1]);
	}

	private static void registerProperties() {
		Blocks.fire.setFireInfo(planks, 5, 20);
		Blocks.fire.setFireInfo(witherWeb, 20, 50);
		Blocks.fire.setFireInfo(fern, 60, 100);
		Blocks.fire.setFireInfo(fiddlehead, 60, 100);
		Blocks.fire.setFireInfo(thorns, 15, 100);
	}

	public static interface ISubBlocksBlock {

		Class<? extends ItemBlock> getItemBlockClass();
	}
}