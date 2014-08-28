package erebus;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockAmber;
import erebus.block.BlockBones;
import erebus.block.BlockButtonUmberstone;
import erebus.block.BlockComposter;
import erebus.block.BlockDoorAmber;
import erebus.block.BlockErebusHoney;
import erebus.block.BlockErebusMushroomCap;
import erebus.block.BlockErebusStigma;
import erebus.block.BlockGhostSand;
import erebus.block.BlockGlowGem;
import erebus.block.BlockGlowingJar;
import erebus.block.BlockGneiss;
import erebus.block.BlockHollowLog;
import erebus.block.BlockHoneyComb;
import erebus.block.BlockHoneyTreat;
import erebus.block.BlockInsectRepellent;
import erebus.block.BlockMucusBomb;
import erebus.block.BlockMud;
import erebus.block.BlockPetrifiedChest;
import erebus.block.BlockPetrifiedCraftingTable;
import erebus.block.BlockPlanksErebus;
import erebus.block.BlockPlantedGiantFlower;
import erebus.block.BlockQuickSand;
import erebus.block.BlockRedGem;
import erebus.block.BlockSimple;
import erebus.block.BlockSlabStone;
import erebus.block.BlockSpiderSpawner;
import erebus.block.BlockStairsBase;
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
import erebus.block.ErebusPortal;
import erebus.block.GaeanKeystone;
import erebus.block.JarOHoney;
import erebus.block.SaplessLog;
import erebus.block.altars.BlockAltar;
import erebus.block.altars.BlockErebusAltar;
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
import erebus.block.ores.UmberOreTemple;
import erebus.block.plants.BlockErebusFlower;
import erebus.block.plants.BlockHangerPlants;
import erebus.block.plants.BlockThorns;
import erebus.block.plants.BlockTurnip;
import erebus.block.plants.BlockWallPlants;
import erebus.block.plants.DoubleHeightPlant;
import erebus.block.plants.SmallMushroom;
import erebus.block.plants.SmallPlant;
import erebus.block.silo.BlockSiloRoof;
import erebus.block.silo.BlockSiloSupports;
import erebus.block.silo.BlockSiloTank;
import erebus.lib.EnumWood;

public class ModBlocks
{

	// PORTAL
	public static final Block portal = new ErebusPortal();

	// TERRAIN
	public static final Block umberstone = new BlockUmberstone().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.umberstone");
	public static final Block redGem = new BlockRedGem().setHardness(0.3F).setLightLevel(1F).setStepSound(Block.soundTypeGlass).setBlockName("erebus.redGem");
	public static final Block blockAmber = new BlockAmber().setHardness(1.5F).setResistance(10.0F).setLightOpacity(3).setStepSound(Block.soundTypeGlass).setBlockName("erebus.amber");
	public static final Block quickSand = new BlockQuickSand().setHardness(28F).setStepSound(Block.soundTypeSand).setBlockName("erebus.quickSand").setBlockTextureName("erebus:quickSand");
	public static final Block ghostSand = new BlockGhostSand().setHardness(0.42F).setStepSound(Block.soundTypeSand).setBlockName("erebus.ghostSand").setBlockTextureName("erebus:ghostSand");
	public static final Block umberstoneButton = new BlockButtonUmberstone().setHardness(0.5F).setStepSound(Block.soundTypeStone).setBlockName("erebus.umberstoneButton");
	public static final Block volcanicRock = new BlockSimple(Material.rock).setHardness(5.0F).setResistance(20.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.volcanicRock").setBlockTextureName("erebus:volcanicRock");

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
	public static final Block oreTemple = new UmberOreTemple();
	public static final Block oreGneiss = new UmberOreGneiss();
	public static final Block oreAluminium = new UmberOreExtra("Aluminium", 1);
	public static final Block oreCopper = new UmberOreExtra("Copper", 1);
	public static final Block oreLead = new UmberOreExtra("Lead", 2);
	public static final Block oreSilver = new UmberOreExtra("Silver", 2);
	public static final Block oreTin = new UmberOreExtra("Tin", 1);

	// WOOD
	public static final Block planks = new BlockPlanksErebus().setHardness(2.0F).setStepSound(Block.soundTypeWood);
	public static final Block hollowLogAcacia = new BlockHollowLog().setHardness(0.7F).setStepSound(Block.soundTypeWood).setBlockName("erebus.log_hollow_acacia");
	public static final Block erebusFlower = new BlockErebusFlower().setHardness(1.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebus.flower");
	public static final Block stiga = new BlockErebusStigma().setHardness(1.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebus.stigma");
	public static final Block scorchedWood = new BlockSimple(Material.wood).setHardness(2.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("erebus.log_scorched").setBlockTextureName("erebus:log_scorched");
	public static final Block rottenWood = new BlockSimple(Material.wood).setHardness(2.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("erebus.log_rotten").setBlockTextureName("erebus:log_rotten");
	public static final Block scorchedPlanks = new BlockSimple(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("erebus.planks_scorched").setBlockTextureName("erebus:planks_scorched");
	public static final Block rottenPlanks = new BlockSimple(Material.wood).setHardness(0.5F).setResistance(1.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("erebus.planks_rotten").setBlockTextureName("erebus:planks_rotten");
	public static final Block varnishedPlanks = new BlockSimple(Material.wood).setHardness(2.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("erebus.planks_varnished").setBlockTextureName("erebus:planks_varnished");
	public static final Block saplessLog = new SaplessLog();

	// DOUBLE PLANTS
	public static final Block sundew = new DoubleHeightPlant("Sundew");
	public static final Block weepingBlue = new DoubleHeightPlant("WeepingBlue");
	public static final Block bullrush = new DoubleHeightPlant("Bullrush");
	public static final Block droughtedShrub = new DoubleHeightPlant("DroughtedShrub");
	public static final Block tangledStalk = new DoubleHeightPlant("TangledStalk");
	public static final Block highCapped = new DoubleHeightPlant("HighCapped");

	// SMALL PLANTS
	public static final Block fiddlehead = new SmallPlant("fiddlehead", true);
	public static final Block fern = new SmallPlant("fern", true);
	public static final Block fireBloom = new SmallPlant("fireBloom", false);
	public static final Block swampPlant = new SmallPlant("swampPlant", false);
	public static final Block nettleFlowered = new SmallPlant("nettleFlowered", false);
	public static final Block nettle = new SmallPlant("nettle", false);
	public static final Block mireCoral = new SmallPlant("mireCoral", false);
	public static final Block desertShrub = new SmallPlant("desertShrub", false);
	public static final Block bulbCappedShroom = new SmallMushroom("bulbCappedShroom");
	public static final Block kaizerfinger = new SmallMushroom("kaizerfinger");
	public static final Block bundleshroom = new SmallMushroom("bundleshroom");
	public static final Block greenMushroom = new SmallMushroom("greenMushroom");
	public static final Block dutchCap = new SmallMushroom("dutchCap");

	// UNDERGROWTH
	public static final Block thorns = new BlockThorns();
	public static final Block blockTurnip = new BlockTurnip();
	public static final Block hanger = new BlockHangerPlants();
	public static final Block wallPlants = new BlockWallPlants();
	public static final Block mushroomCap0 = new BlockErebusMushroomCap(0);
	public static final Block mushroomCap1 = new BlockErebusMushroomCap(1);
	public static final Block mushroomCap2 = new BlockErebusMushroomCap(2);
	public static final Block mushroomCap3 = new BlockErebusMushroomCap(3);
	public static final Block mushroomCap4 = new BlockErebusMushroomCap(4);
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

	// DECORATIONS AND UTILITIES
	public static final Block blockSilk = new BlockSimple(Material.cloth).setHardness(0.2F).setStepSound(Block.soundTypeCloth).setBlockName("erebus.blockSilk").setBlockTextureName("erebus:blockSilk");
	public static final Block mirBrick = new BlockSimple(Material.rock).setHardness(1.5F).setStepSound(Block.soundTypeStone).setBlockName("erebus.mirbrick").setBlockTextureName("erebus:mirbrick");
	public static final Block petrifiedWoodPlanks = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("erebus.petrifiedWoodPlanks").setBlockTextureName("erebus:planks_petrifiedWood");
	public static final Block petrifiedCraftingTable = new BlockPetrifiedCraftingTable().setHardness(2.5F).setStepSound(Block.soundTypeStone).setBlockName("erebus.petrifiedCraftingTable");
	public static final Block bambooCrate = new BlockBambooCrate().setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("erebus.bambooCrate");
	public static final Block umberFurnace = new BlockUmberFurnace();
	public static final Block umberPaver = new BlockUmberPaver().setHardness(3.5F).setStepSound(Block.soundTypeStone).setBlockName("erebus.umberPaver");
	public static final Block insectRepellent = new BlockInsectRepellent().setBlockName("erebus.insectRepellent");
	public static final Block bambooShoot = new BlockBambooShoot().setCreativeTab(null).setBlockName("erebus.bambooShoot").setBlockTextureName("erebus:bambooShoot");
	public static final Block bambooCrop = new BlockBambooCrop().setHardness(1.0F).setStepSound(Block.soundTypeWood).setBlockName("erebus.bambooCrop").setBlockTextureName("erebus:bambooCropBase");
	public static final Block bambooTorch = new BlockBambooTorch().setHardness(0.0F).setBlockName("erebus.bambooTorch");
	public static final Block glowingJar = new BlockGlowingJar().setBlockName("erebus.glowingJar").setBlockTextureName("erebus:glassAmber");
	public static final Block reinExo = new BlockSimple(Material.rock).setHardness(1.5F).setResistance(2000.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.reinExo").setBlockTextureName("erebus:blockReinExo");
	public static final Block bambooLadder = new BlockBambooLadder().setHardness(0.4F).setStepSound(Block.soundTypeLadder).setBlockName("erebus.bambooLadder").setBlockTextureName("erebus:bambooLadder");
	public static final Block bambooBridge = new BlockBambooBridge().setHardness(0.4F).setStepSound(Block.soundTypeLadder).setBlockName("erebus.bambooBridge").setBlockTextureName("erebus:planks_bamboo");
	public static final Block umberGolemStatue = new BlockUmberGolemStatue().setBlockName("erebus.umberGolemStatue");
	public static final Block petrifiedWoodChest = new BlockPetrifiedChest().setHardness(2.0F).setBlockName("erebus.petrifiedWoodChest").setBlockTextureName("erebus:planks_petrifiedWood");
	public static final Block blockBones = new BlockBones().setBlockName("erebus.blockBones");
	public static final Block blockWitherWeb = new BlockWitherWeb().setHardness(4.0F).setBlockName("erebus.witherWeb").setBlockTextureName("web");
	public static final Block extenderThingy = new BlockExtenderThingy().setHardness(0.4F).setStepSound(Block.soundTypeLadder).setBlockName("erebus.extenderThingy").setBlockTextureName("erebus:planks_bamboo");
	public static final Block bambooPole = new BlockBambooPole().setHardness(0.4F).setBlockName("erebus.bambooPole").setBlockTextureName("erebus:blockBambooPole");
	public static final Block umberstonePillar = new BlockUmberstonePillar().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.umberstonePillar").setBlockTextureName("erebus:umberstonePillarSides");
	public static final Block velocityBlock = new BlockVelocity().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.velocityBlock").setBlockTextureName("erebus:blockSpeed0");
	public static final Block honeyCombBlock = new BlockHoneyComb().setHardness(0.5F).setResistance(10.0F).setStepSound(Block.soundTypeCloth).setBlockName("erebus.honeyCombBlock").setBlockTextureName("erebus:honeyCombTop");
	public static final Block doorAmber = new BlockDoorAmber();
	public static final Block honeyBlock = new BlockErebusHoney(ModFluids.honey).setBlockName("erebus.honeyBlock");
	public static final Block honeyTreat = new BlockHoneyTreat().setHardness(0.5F).setStepSound(Block.soundTypeCloth).setBlockName("erebus.honeyTreat").setBlockTextureName("erebus:honeyTreat");
	public static final Block mud = new BlockMud().setBlockName("erebus.mud").setBlockTextureName("erebus:mud");
	public static final Block mudBricks = new BlockSimple(Material.rock).setBlockName("erebus.mudBricks").setBlockTextureName("erebus:mudBricks").setHardness(0.8F).setResistance(1.0F);
	public static final Block jarOHoney = new JarOHoney().setHardness(0.5F).setBlockName("erebus.jarOHoney").setBlockTextureName("erebus:glassAmber");
	public static final Block jadeBlock = new BlockCompressed(MapColor.greenColor).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("erebus.blockJade").setBlockTextureName("erebus:blockJade");
	public static final Block altar = new BlockAltar().setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.altar").setBlockTextureName("stone");
	public static final Block glowGemBlock = new BlockGlowGem().setBlockName("erebus.glowGemBlock").setBlockTextureName("erebus:glowGem");
	public static final Block mucusBomb = new BlockMucusBomb().setBlockName("erebus.mucusBomb");

	// ALTARS
	public static final Block altarBase = new BlockErebusAltar().setHardness(20.0F).setBlockName("erebus.altarBase");
	public static final Block altarLightning = new LightningAltar().setHardness(20.0F).setBlockName("erebus.altarLightning");
	public static final Block altarHealing = new HealingAltar().setHardness(20.0F).setBlockName("erebus.altarHealing");
	public static final Block altarXP = new XPAltar().setHardness(20.0F).setBlockName("erebus.altarXP");
	public static final Block altarRepair = new RepairAltar().setHardness(20.0F).setBlockName("erebus.altarRepair");

	// DUNGEONS
	public static final Block spiderSpawner = new BlockSpiderSpawner("Scytodes - Erebus").setBlockName("erebus.spiderSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block jumpingSpiderSpawner = new BlockSpiderSpawner("JumpingSpider - Erebus").setBlockName("erebus.jumpingSpiderSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block waspSpawner = new BlockWaspSpawner("Wasp - Erebus").setBlockName("erebus.waspSpawner").setBlockTextureName("erebus:waspNestSpawner");
	public static final Block waspNestBlock = new BlockWaspNest().setHardness(50.0F).setResistance(2000.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.waspNestBlock").setBlockTextureName("erebus:waspNestBlock");
	public static final Block gneiss = new BlockGneiss().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.gneiss");
	public static final Block siloSupports = new BlockSiloSupports(Material.wood).setHardness(2F).setStepSound(Block.soundTypeWood).setBlockName("erebus.siloSupports").setBlockTextureName("erebus:siloSupports");
	public static final Block siloTank = new BlockSiloTank(Material.iron).setHardness(3F).setStepSound(Block.soundTypeMetal).setBlockName("erebus.siloTank");
	public static final Block siloRoof = new BlockSiloRoof(Material.iron).setHardness(3F).setStepSound(Block.soundTypeMetal).setBlockName("erebus.siloRoof").setBlockTextureName("erebus:siloRoof");
	public static final Block composter = new BlockComposter().setHardness(3.5F).setStepSound(Block.soundTypeStone).setBlockName("erebus.composter");
	public static final Block templeBrick = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.templeBrick").setBlockTextureName("erebus:templeBrick");
	public static final Block templePillar = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.templePillar").setBlockTextureName("erebus:templePillar");
	public static final Block templeTile = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.templeTile").setBlockTextureName("erebus:templeTile");

	// STAIRS, SLABS, WALLS
	public static final Block[] umbercobbleStairs = new Block[BlockUmberstone.iconPaths.length];
	public static final Block[] stoneSlabs = new Block[8];
	public static final Block[] gneissStairs = new Block[BlockGneiss.iconPaths.length];
	public static final Block wall = new BlockWallErebus().setBlockName("erebus.wallErebus");
	public static final Block petrifiedWoodStairs = new BlockStairsBase(petrifiedWoodPlanks, 0).setStepSound(Block.soundTypeWood).setBlockName("erebus.petrifiedWoodStairs");
	public static final Block amberBrickStairs = new BlockStairsBase(blockAmber, 2).setStepSound(Block.soundTypeStone).setBlockName("erebus.amberBrickStairs");
	public static final Block waspNestStairs = new BlockStairsBase(waspNestBlock, 2).setHardness(50.0F).setStepSound(Block.soundTypeStone).setBlockName("erebus.waspNestStairs");

	// OTHER THINGS (AKA LEXICONN'S STUFF. DELETE IF HE EVER QUITS)
	public static final Block gaeanKeystone = new GaeanKeystone();
	public static final Block offeringAltar = new OfferingAltar();

	public static void init()
	{
		initBlocks();
		EnumWood.initBlocks();
		initCreativeTabs();

		registerBlocks();
		registerProperties();
	}

	private static void initBlocks()
	{
		for (int i = 0; i < umbercobbleStairs.length; i++)
		{
			umbercobbleStairs[i] = new BlockStairsBase(umberstone, i).setStepSound(Block.soundTypeStone).setBlockName("erebus.umbercobbleStairs" + i);
		}
		for (int i = 0; i <= 4; i++)
		{
			stoneSlabs[i] = new BlockSlabStone(ModBlocks.umberstone, i);
		}
		for (int i = 0; i <= 2; i++)
		{
			stoneSlabs[5 + i] = new BlockSlabStone(ModBlocks.umberPaver, i);
		}
		stoneSlabs[7] = new BlockSlabStone(ModBlocks.petrifiedWoodPlanks);
		for (int i = 0; i < gneissStairs.length; i++)
		{
			gneissStairs[i] = new BlockStairsBase(gneiss, i).setStepSound(Block.soundTypeStone).setBlockName("erebus.gneissStairs" + i);
		}
	}

	private static void initCreativeTabs()
	{
		ModTabs.blocks.setTab(umberstone, redGem, blockAmber, quickSand, ghostSand);
		ModTabs.blocks.setTab(hollowLogAcacia, planks);
		ModTabs.blocks.setTab(blockSilk, mirBrick, petrifiedWoodPlanks, petrifiedCraftingTable, bambooCrate, bambooLadder);
		ModTabs.blocks.setTab(bambooBridge, bambooPole, bambooTorch, glowingJar, umberstoneButton, umberPaver, altarBase);
		ModTabs.blocks.setTab(reinExo, waspNestBlock, petrifiedWoodChest, blockBones, blockWitherWeb, extenderThingy);
		ModTabs.blocks.setTab(umberstonePillar, velocityBlock, honeyCombBlock, honeyTreat, gneiss, mud, mudBricks, jarOHoney);
		ModTabs.blocks.setTab(jadeBlock, glowGemBlock, mucusBomb, siloTank, siloSupports, siloRoof, composter);
		ModTabs.blocks.setTab(umbercobbleStairs);
		ModTabs.blocks.setTab(gneissStairs);
		ModTabs.blocks.setTab(petrifiedWoodStairs, amberBrickStairs, waspNestStairs, wall, gaeanKeystone);
	}

	private static void registerBlocks()
	{
		try
		{
			for (Field f : ModBlocks.class.getDeclaredFields())
			{
				Object obj = f.get(null);
				if (obj instanceof Block)
				{
					registerBlock((Block) obj);
				} else if (obj instanceof Block[])
				{
					for (Block block : (Block[]) obj)
					{
						registerBlock(block);
					}
				}
			}
		} catch (IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
	}

	private static void registerBlock(Block block)
	{
		String name = block.getUnlocalizedName();
		String[] strings = name.split("\\.");

		if (block instanceof ISubBlocksBlock)
		{
			GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
		} else
		{
			GameRegistry.registerBlock(block, strings[strings.length - 1]);
		}
	}

	private static void registerProperties()
	{
		oreFossil.setHarvestLevel("pickaxe", 1);
		mirBrick.setHarvestLevel("pickaxe", 1);
		spiderSpawner.setHarvestLevel("pickaxe", 0, 0);
		jumpingSpiderSpawner.setHarvestLevel("pickaxe", 0, 0);
		waspSpawner.setHarvestLevel("pickaxe", 0, 0);
		umberstone.setHarvestLevel("pickaxe", 0);
		umberPaver.setHarvestLevel("pickaxe", 0);
		wall.setHarvestLevel("pickaxe", 0);
		quickSand.setHarvestLevel("shovel", 2);
		ghostSand.setHarvestLevel("shovel", 0);
		reinExo.setHarvestLevel("pickaxe", 2);
		petrifiedWoodChest.setHarvestLevel("pickaxe", 0);
		jadeBlock.setHarvestLevel("pickaxe", 2);

		Blocks.fire.setFireInfo(planks, 5, 20);
		Blocks.fire.setFireInfo(blockWitherWeb, 20, 50);
		Blocks.fire.setFireInfo(fern, 60, 100);
		Blocks.fire.setFireInfo(fiddlehead, 60, 100);
		Blocks.fire.setFireInfo(thorns, 15, 100);
	}

	public static interface ISubBlocksBlock
	{

		Class<? extends ItemBlock> getItemBlockClass();
	}
}