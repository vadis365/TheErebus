package erebus;

import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.*;
import erebus.block.altars.*;
import erebus.block.bamboo.*;
import erebus.block.glowshroom.*;
import erebus.block.plants.*;
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

import java.lang.reflect.Field;

public class ModBlocks {

	// PORTAL
	public static final BlockErebusPortal portal = (BlockErebusPortal) new BlockErebusPortal().setHardness(-1F).setLightLevel(1.0F).setStepSound(Block.soundTypeGlass).setBlockName("portalErebus");

	// TERRAIN AND ORES
	public static final Block umberstone = new BlockUmberstone().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("umberstone");
	public static final Block umberOreBlock = new BlockErebusOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockName("oreBlockU");
	public static final Block oreFossil = new BlockOreFossil().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("oreFossilU").setBlockTextureName("erebus:oreFossilU");
	public static final Block redGem = new BlockRedGem().setHardness(0.3F).setLightLevel(1F).setStepSound(Block.soundTypeGlass).setBlockName("redGem");
	public static final Block blockAmber = new BlockAmber().setHardness(1.5F).setResistance(10.0F).setLightOpacity(3).setStepSound(Block.soundTypeGlass).setBlockName("blockAmber");
	public static final Block quickSand = new BlockQuickSand().setHardness(28F).setStepSound(Block.soundTypeSand).setBlockName("quickSand").setBlockTextureName("erebus:quickSand");
	public static final Block ghostSand = new BlockGhostSand().setHardness(0.42F).setStepSound(Block.soundTypeSand).setBlockName("ghostSand").setBlockTextureName("erebus:ghostSand");
	public static final Block oreExtra = new BlockErebusOreExtras().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockName("erebusOreExtras");
	public static final Block umberstoneButton = new BlockButtonUmberstone().setHardness(0.5F).setStepSound(Block.soundTypeStone).setBlockName("umberstoneButton");

	// WOOD
	public static final Block planks = new BlockPlanksErebus().setHardness(2.0F).setStepSound(Block.soundTypeWood);
	public static final Block hollowLogAcacia = new BlockHollowLog().setHardness(0.7F).setStepSound(Block.soundTypeWood).setBlockName("hollowLogAcacia");
	public static final Block erebusFlower = new BlockErebusFlower().setHardness(1.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebusFlower");
	public static final Block erebusStigma = new BlockErebusStigma().setHardness(1.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebusStigma");
	public static final Block scorchedWood = new BlockSimple(Material.wood).setHardness(2.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("scorchedWood").setBlockTextureName("erebus:log_scorched");
	public static final Block rottenWood = new BlockSimple(Material.wood).setHardness(2.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("rottenWood").setBlockTextureName("erebus:log_rotten");
	public static final Block scorchedPlanks = new BlockSimple(Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("scorchedPlanks").setBlockTextureName("erebus:planks_scorched");
	public static final Block rottenPlanks = new BlockSimple(Material.wood).setHardness(0.5F).setResistance(1.0F).setStepSound(Block.soundTypeWood).setCreativeTab(ModTabs.blocks).setBlockName("rottenPlanks").setBlockTextureName("erebus:planks_rotten");
	public static final Block leaves = new BlockLeavesErebus().setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("leavesErebus");

	// UNDERGROWTH
	public static final Block thorns = new BlockThorns().setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockName("thorns").setBlockTextureName("erebus:thorns");
	public static final Block blockTurnip = new BlockTurnip().setBlockName("turnipsCrop");
	public static final Block fiddlehead = new BlockFiddlehead().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebusFiddlehead").setBlockTextureName("erebus:fiddlehead");
	public static final Block plantSmall = new BlockSmallPlants().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebusPlantSmall");
	public static final Block hanger = new BlockHangerPlants().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebusHanger");
	public static final Block wallPlants = new BlockWallPlants().setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockName("erebusWallPlants");
	public static final Block fern = new BlockFern().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("erebusFern");
	public static final Block doubleHeightPlant = new BlockDoubleHeightPlant().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("doubleHeightPlant");
	public static final Block mushroomCap0 = new BlockErebusMushroomCap(0).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("mushroomBulbCap");
	public static final Block mushroomCap1 = new BlockErebusMushroomCap(1).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("mushroom1Cap");
	public static final Block mushroomCap2 = new BlockErebusMushroomCap(2).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("mushroom2Cap");
	public static final Block mushroomCap3 = new BlockErebusMushroomCap(3).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("mushroom3Cap");
	public static final Block mushroomCap4 = new BlockErebusMushroomCap(4).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("mushroom4Cap");
	public static final Block glowshroom = new BlockGlowshroom().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroom").setBlockTextureName("erebus:mushroomYellow");
	public static final Block glowshroomStalkMain = new BlockGlowshroomStalkMain().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalk").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkDown1 = new BlockGlowshroomStalkDown1().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkDown1").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkDown2 = new BlockGlowshroomStalkDown2().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkDown2").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkDown3 = new BlockGlowshroomStalkDown3().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkDown3").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkN1 = new BlockGlowshroomStalkN1().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkN1").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkS1 = new BlockGlowshroomStalkS1().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkS1").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkNS2 = new BlockGlowshroomStalkNS2().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkNS2").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkW1 = new BlockGlowshroomStalkW1().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkW1").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkE1 = new BlockGlowshroomStalkE1().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkE1").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkWE2 = new BlockGlowshroomStalkWE2().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkWE2").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkN3 = new BlockGlowshroomStalkN3().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkN3").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkS3 = new BlockGlowshroomStalkS3().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkS3").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkW3 = new BlockGlowshroomStalkW3().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkW3").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block glowshroomStalkE3 = new BlockGlowshroomStalkE3().setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("glowshroomStalkE3").setBlockTextureName("erebus:glowshroomStalk");
	public static final Block flowerPlanted = new BlockPlantedGiantFlower().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("flowerPlanted");

	// DECORATIONS AND UTILITIES
	public static final Block blockSilk = new BlockSimple(Material.cloth).setHardness(0.2F).setStepSound(Block.soundTypeCloth).setBlockName("blockSilk").setBlockTextureName("erebus:blockSilk");
	public static final Block mirBrick = new BlockSimple(Material.rock).setHardness(1.5F).setStepSound(Block.soundTypeStone).setBlockName("mirbrick").setBlockTextureName("erebus:mirbrick");
	public static final Block petrifiedWoodPlanks = new BlockSimple(Material.rock).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("petrifiedWoodPlanks").setBlockTextureName("erebus:planks_petrifiedWood");
	public static final Block petrifiedCraftingTable = new BlockPetrifiedCraftingTable().setHardness(2.5F).setStepSound(Block.soundTypeStone).setBlockName("petrifiedCraftingTable");
	public static final Block bambooCrate = new BlockBambooCrate().setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("bambooCrate");
	public static final Block umberFurnace = new BlockUmberFurnace();
	public static final Block umberPaver = new BlockUmberPaver().setHardness(3.5F).setStepSound(Block.soundTypeStone).setBlockName("umberPaver");
	public static final Block insectRepellent = new BlockInsectRepellent().setBlockName("insectRepellent");
	public static final Block bambooShoot = new BlockBambooShoot().setCreativeTab(null).setBlockName("bambooShoot").setBlockTextureName("erebus:bambooShoot");
	public static final Block bambooCrop = new BlockBambooCrop().setHardness(1.0F).setStepSound(Block.soundTypeWood).setBlockName("bambooCrop").setBlockTextureName("erebus:bambooCropBase");
	public static final Block bambooTorch = new BlockBambooTorch().setHardness(0.0F).setBlockName("bambooTorch");
	public static final Block erebusAltar = new BlockErebusAltar().setHardness(20.0F).setBlockName("erebusAltar");
	public static final Block erebusAltarLightning = new BlockErebusAltarLightning().setHardness(20.0F).setBlockName("erebusAltarLightning");
	public static final Block erebusAltarHealing = new BlockErebusAltarHealing().setHardness(20.0F).setBlockName("erebusAltarHealing");
	public static final Block erebusAltarXP = new BlockErebusAltarXP().setHardness(20.0F).setBlockName("erebusAltarXP");
	public static final Block erebusAltarRepair = new BlockErebusAltarRepair().setHardness(20.0F).setBlockName("erebusAltarRepair");
	public static final Block glowingJar = new BlockGlowingJar().setBlockName("glowingJar").setBlockTextureName("erebus:glassAmber");
	public static final Block reinExo = new BlockSimple(Material.rock).setHardness(1.5F).setResistance(2000.0F).setStepSound(Block.soundTypeStone).setBlockName("reinExo").setBlockTextureName("erebus:blockReinExo");
	public static final Block bambooLadder = new BlockBambooLadder().setHardness(0.4F).setStepSound(Block.soundTypeLadder).setBlockName("bambooLadder").setBlockTextureName("erebus:bambooLadder");
	public static final Block bambooBridge = new BlockBambooBridge().setHardness(0.4F).setStepSound(Block.soundTypeLadder).setBlockName("bambooBridge").setBlockTextureName("erebus:planks_bamboo");
	public static final Block umberGolemStatue = new BlockUmberGolemStatue().setBlockName("umberGolemStatue");
	public static final Block petrifiedWoodChest = new BlockPetrifiedChest().setHardness(2.0F).setBlockName("petrifiedWoodChest").setBlockTextureName("erebus:planks_petrifiedWood");
	public static final Block blockBones = new BlockBones().setBlockName("blockBones");
	public static final Block blockWitherWeb = new BlockWitherWeb().setHardness(4.0F).setBlockName("witherWeb").setBlockTextureName("web");
	public static final Block extenderThingy = new BlockExtenderThingy().setHardness(0.4F).setStepSound(Block.soundTypeLadder).setBlockName("extenderThingy").setBlockTextureName("erebus:planks_bamboo");
	public static final Block bambooPole = new BlockBambooPole().setHardness(0.4F).setBlockName("bambooPole").setBlockTextureName("erebus:blockBambooPole");
	public static final Block umberstonePillar = new BlockUmberstonePillar().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("umberstonePillar").setBlockTextureName("erebus:umberstonePillarSides");
	public static final Block velocityBlock = new BlockVelocity().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("velocityBlock").setBlockTextureName("erebus:blockSpeed0");
	public static final Block honeyCombBlock = new BlockHoneyComb().setHardness(0.5F).setResistance(10.0F).setStepSound(Block.soundTypeCloth).setBlockName("honeyCombBlock").setBlockTextureName("erebus:honeyCombTop");
	public static final Block doorAmber = new BlockDoorAmber(Material.glass).setBlockName("doorAmber").setBlockTextureName("erebus:doorAmber");
	public static final Block honeyBlock = new BlockErebusHoney(ModFluids.honey).setBlockName("honeyBlock");
	public static final Block honeyTreat = new BlockHoneyTreat().setHardness(0.5F).setStepSound(Block.soundTypeCloth).setBlockName("honeyTreat").setBlockTextureName("erebus:honeyTreat");
	public static final Block mud = new BlockMud().setBlockName("erebusMud").setBlockTextureName("erebus:mud");
	public static final Block mudBricks = new BlockSimple(Material.rock).setBlockName("erebus.mudBricks").setBlockTextureName("erebus:mudBricks").setHardness(0.8F).setResistance(1.0F);
	public static final Block jarOHoney = new JarOHoney().setHardness(0.5F).setBlockName("erebus.jarOHoney").setBlockTextureName("erebus:glassAmber");
	public static final Block jadeBlock = new BlockCompressed(MapColor.greenColor).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeMetal).setBlockName("erebus.blockJade").setBlockTextureName("erebus:blockJade");
	public static final Block glowGemBlock = new BlockGlowGem().setBlockName("erebus.glowGemBlock").setBlockTextureName("erebus:glowGem");
	public static final Block mucusBomb = new BlockMucusBomb().setBlockName("erebus.mucusBomb");

	// DUNGEONS
	public static final Block spiderSpawner = new BlockSpiderSpawner("Scytodes - Erebus").setBlockName("spiderSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block jumpingSpiderSpawner = new BlockSpiderSpawner("JumpingSpider - Erebus").setBlockName("jumpingSpiderSpawner").setBlockTextureName("erebus:spiderSpawner");
	public static final Block waspSpawner = new BlockWaspSpawner("Wasp - Erebus").setBlockName("waspSpawner").setBlockTextureName("erebus:waspNestSpawner");
	public static final Block waspNestBlock = new BlockWaspNest().setHardness(50.0F).setResistance(2000.0F).setStepSound(Block.soundTypeStone).setBlockName("waspNestBlock").setBlockTextureName("erebus:waspNestBlock");
	public static final Block gneiss = new BlockGneiss().setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("gneiss");
	public static final Block siloSupports = new BlockSiloSupports(Material.wood).setHardness(2F).setStepSound(Block.soundTypeWood).setBlockName("siloSupports").setBlockTextureName("erebus:siloSupports");
	public static final Block siloTank = new BlockSiloTank(Material.iron).setHardness(3F).setStepSound(Block.soundTypeMetal).setBlockName("siloTank");
	public static final Block siloRoof = new BlockSiloRoof(Material.iron).setHardness(3F).setStepSound(Block.soundTypeMetal).setBlockName("siloRoof").setBlockTextureName("erebus:siloRoof");
	public static final Block composter = new BlockComposter().setHardness(3.5F).setStepSound(Block.soundTypeStone).setBlockName("composter");

	// STAIRS, SLABS, WALLS
	public static final Block[] umbercobbleStairs = new Block[BlockUmberstone.iconPaths.length];
	public static final Block[] stoneSlabs = new Block[8];
	public static final Block[] gneissStairs = new Block[BlockGneiss.iconPaths.length];
	public static final Block wall = new BlockWallErebus().setBlockName("wallErebus");
	public static final Block petrifiedWoodStairs = new BlockStairsBase(petrifiedWoodPlanks, 0).setStepSound(Block.soundTypeWood).setBlockName("petrifiedWoodStairs");
	public static final Block amberBrickStairs = new BlockStairsBase(blockAmber, 2).setStepSound(Block.soundTypeStone).setBlockName("amberBrickStairs");
	public static final Block waspNestStairs = new BlockStairsBase(waspNestBlock, 2).setHardness(50.0F).setStepSound(Block.soundTypeStone).setBlockName("waspNestStairs");

	// OTHER THINGS (AKA ILEXICONN'S STUFF. DELETE IF HE EVER QUITS)
	public static final Block gaeanKeystone = new BlockGaeanKeystone();
	public static final Block offeringAltar = new BlockOfferingAltar();

	public static void init() {
		initBlocks();
		EnumWood.initBlocks();
		initCreativeTabs();

		registerBlocks();
		registerProperties();
	}

	private static void initBlocks() {
		for (int i = 0; i < umbercobbleStairs.length; i++)
			umbercobbleStairs[i] = new BlockStairsBase(umberstone, i).setStepSound(Block.soundTypeStone).setBlockName("umbercobbleStairs" + i);
		for (int i = 0; i <= 4; i++)
			stoneSlabs[i] = new BlockSlabStone(ModBlocks.umberstone, i);
		for (int i = 0; i <= 2; i++)
			stoneSlabs[5 + i] = new BlockSlabStone(ModBlocks.umberPaver, i);
		stoneSlabs[7] = new BlockSlabStone(ModBlocks.petrifiedWoodPlanks);
		for (int i = 0; i < gneissStairs.length; i++)
			gneissStairs[i] = new BlockStairsBase(gneiss, i).setStepSound(Block.soundTypeStone).setBlockName("gneissStairs" + i);
	}

	private static void initCreativeTabs() {
		ModTabs.blocks.setTab(umberstone, umberOreBlock, oreFossil, oreExtra, redGem, blockAmber, quickSand, ghostSand);
		ModTabs.blocks.setTab(hollowLogAcacia, planks, leaves);
		ModTabs.blocks.setTab(fern, fiddlehead, thorns, erebusFlower, erebusStigma, doubleHeightPlant, plantSmall, wallPlants);
		ModTabs.blocks.setTab(mushroomCap0, mushroomCap1, mushroomCap2, mushroomCap3, mushroomCap4, glowshroomStalkMain, glowshroom);
		ModTabs.blocks.setTab(blockSilk, mirBrick, petrifiedWoodPlanks, petrifiedCraftingTable, bambooCrop, bambooCrate, bambooLadder);
		ModTabs.blocks.setTab(bambooBridge, bambooPole, bambooTorch, glowingJar, umberstoneButton, umberPaver, erebusAltar);
		ModTabs.blocks.setTab(reinExo, waspNestBlock, petrifiedWoodChest, blockBones, blockWitherWeb, extenderThingy);
		ModTabs.blocks.setTab(umberstonePillar, velocityBlock, honeyCombBlock, honeyTreat, gneiss, mud, mudBricks, jarOHoney);
		ModTabs.blocks.setTab(jadeBlock, glowGemBlock, mucusBomb, siloTank, siloSupports, siloRoof, composter);
		ModTabs.blocks.setTab(umbercobbleStairs);
		ModTabs.blocks.setTab(gneissStairs);
		ModTabs.blocks.setTab(petrifiedWoodStairs, amberBrickStairs, waspNestStairs, wall, gaeanKeystone, offeringAltar);
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
		String name = block.getUnlocalizedName();
		String[] strings = name.split("\\.");

		if (block instanceof ISubBlocksBlock)
			GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
		else
			GameRegistry.registerBlock(block, strings[strings.length - 1]);
	}

	private static void registerProperties() {
		oreFossil.setHarvestLevel("pickaxe", 1);
		mirBrick.setHarvestLevel("pickaxe", 1);
		spiderSpawner.setHarvestLevel("pickaxe", 0, 0);
		jumpingSpiderSpawner.setHarvestLevel("pickaxe", 0, 0);
		waspSpawner.setHarvestLevel("pickaxe", 0, 0);
		umberstone.setHarvestLevel("pickaxe", 0);
		umberPaver.setHarvestLevel("pickaxe", 0);
		wall.setHarvestLevel("pickaxe", 0);
		umberOreBlock.setHarvestLevel("pickaxe", 0, 0);
		umberOreBlock.setHarvestLevel("pickaxe", 1, 1);
		umberOreBlock.setHarvestLevel("pickaxe", 2, 2);
		umberOreBlock.setHarvestLevel("pickaxe", 1, 3);
		umberOreBlock.setHarvestLevel("pickaxe", 2, 4);
		umberOreBlock.setHarvestLevel("pickaxe", 2, 5);
		umberOreBlock.setHarvestLevel("pickaxe", 2, 6);
		umberOreBlock.setHarvestLevel("pickaxe", 2, 8);
		quickSand.setHarvestLevel("shovel", 2);
		ghostSand.setHarvestLevel("shovel", 0);
		reinExo.setHarvestLevel("pickaxe", 2);
		petrifiedWoodChest.setHarvestLevel("pickaxe", 0);
		jadeBlock.setHarvestLevel("pickaxe", 2);
		oreExtra.setHarvestLevel("pickaxe", 1, 0);
		oreExtra.setHarvestLevel("pickaxe", 1, 1);
		oreExtra.setHarvestLevel("pickaxe", 2, 2);
		oreExtra.setHarvestLevel("pickaxe", 2, 3);
		oreExtra.setHarvestLevel("pickaxe", 1, 4);

		Blocks.fire.setFireInfo(planks, 5, 20);
		Blocks.fire.setFireInfo(leaves, 30, 60);
		Blocks.fire.setFireInfo(blockWitherWeb, 20, 50);
		Blocks.fire.setFireInfo(fern, 60, 100);
		Blocks.fire.setFireInfo(fiddlehead, 60, 100);
		Blocks.fire.setFireInfo(thorns, 15, 100);
	}

	public static interface ISubBlocksBlock {

		Class<? extends ItemBlock> getItemBlockClass();
	}
}