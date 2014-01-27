package erebus.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockLogErebus;
import erebus.block.BlockPlanksErebus;
import erebus.core.handler.ConfigurationHandler;
import erebus.item.ItemErebusFood;
import erebus.item.ItemErebusMaterial;

public class RecipeHandler {

	public static void init() {
		// Wood
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataAcacia), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataAcacia) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataEucalyptus), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataEucalyptus) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataMahogany), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataMahogany) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataBaobab), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataBaobab) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataMossbark), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataMossbark) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataPink), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataPink) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataScorched), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataScorched) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataAsper), new Object[] { "#", '#', new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataAsper) });
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataWhite), "plankWood", "dyeWhite"));

		// Umber stuff
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 0), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 1), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 2), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberFurnace, 1), new Object[] { "###", "# #", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 4, 4), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstoneButton, 1, 0), new Object[] { "#", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });

		// Stone tools made from umberstone
		GameRegistry.addRecipe(new ItemStack(Item.pickaxeStone, 1), new Object[] { "XXX", " # ", " # ", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.shovelStone, 1), new Object[] { "X", "#", "#", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.axeStone, 1), new Object[] { "XX", "X#", " #", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.hoeStone, 1), new Object[] { "XX", " #", " #", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.swordStone, 1), new Object[] { "X", "X", "#", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });

		// Petrified Wood stuffs
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), new Object[] { "xx", "xx", 'x', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataPetrifiedWood) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedCraftingTable), new Object[] { "xx", "xx", 'x', ModBlocks.petrifiedWoodPlanks });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodChest), new Object[] { "xxx", "xyx", "xxx", 'x', ModBlocks.petrifiedWoodPlanks, 'y', Item.ingotGold });

		// Stairs, slabs, walls
		for (int i = 0; i < ModBlocks.umbercobbleStairs.length; i++)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.umbercobbleStairs[i], 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.umberstone, 1, i) });
		for (int i = 0; i < ModBlocks.plankStairs.length; i++)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.plankStairs[i], 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.planksErebus, 1, i) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.amberBrickStairs, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodStairs, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.petrifiedWoodPlanks, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodSlab[0]), new Object[] { "xxx", 'x', ModBlocks.petrifiedWoodPlanks });

		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 0), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 1), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 2), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 3), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 4), new Object[] { "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 5), new Object[] { "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 6), new Object[] { "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stoneSlabs[0], 6, 7), new Object[] { "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2) });

		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 0), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 1), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 2), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 3), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 4), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 5), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 6), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 6) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[0], 6, 7), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 7) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[2], 6, 0), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 8) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.plankSlabs[2], 6, 1), new Object[] { "###", '#', new ItemStack(ModBlocks.planksErebus, 1, 9) });

		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 0), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 1), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 2), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 3), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 4), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 5), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 6), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 7), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 8), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2) });

		// Jade tools
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePickaxe, 1), new Object[] { "XXX", " # ", " # ", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeShovel, 1), new Object[] { "X", "#", "#", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeAxe, 1), new Object[] { "XX", "X#", " #", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHoe, 1), new Object[] { "XX", " #", " #", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeSword, 1), new Object[] { "X", "X", "#", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePaxel, 1), new Object[] { "XXX", "XSX", "XSX", 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade), 'S', Item.stick });
		GameRegistry.addRecipe(new RecipePaxel());

		// Jade armor
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHelmet, 1), new Object[] { "###", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBody, 1), new Object[] { "# #", "###", "###", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeLegs, 1), new Object[] { "###", "# #", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBoots, 1), new Object[] { "# #", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataJade) });

		// Exoskeleton armor
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), new Object[] { "sss", "s s", "   ", 's', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBody, 1), new Object[] { "s s", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), new Object[] { "sss", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), new Object[] { "   ", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate) });

		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataReinforcedPlateExo), new Object[] { "sss", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate) });

		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonHelmet, 1), new Object[] { "sss", "s s", "   ", 's', new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo) });
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBody, 1), new Object[] { "s s", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo) });
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonLegs, 1), new Object[] { "sss", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo) });
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBoots, 1), new Object[] { "   ", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo) });

		// Special armor
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataCompoundLens), new Object[] { "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.blockAmber, 1, 1), 'G', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataCompoundEyes) });
		GameRegistry.addRecipe(new ItemStack(ModItems.compoundGoggles, 1), new Object[] { "XXX", "OXO", "   ", 'O', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataCompoundLens), 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate) });
		GameRegistry.addRecipe(new ItemStack(ModItems.reinCompoundGoggles, 1), new Object[] { "XXX", "XOX", "   ", 'O', new ItemStack(ModItems.compoundGoggles, 1), 'X', new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jumpBoots), new Object[] { "F F", "BXB", "B B", 'F', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataFlyWing), 'B', new ItemStack(ModItems.erebusMaterials, 1, 9), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.sprintLeggings), new Object[] { "BBB", "BXB", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBioVelocity), 'X', new ItemStack(ModItems.reinExoskeletonLegs, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGlider), new Object[] { "   ", "GXG", "   ", 'G', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataGliderWing), 'X', new ItemStack(ModItems.reinExoskeletonBody, 1) });

		GameRegistry.addRecipe(new ItemStack(ModItems.scorpionPincer), new Object[] { "I I", "XIX", "XPX", 'I', Item.ingotIron, 'X', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataReinforcedPlateExo), 'P',
		new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataScorpPincer) });

		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataGliderWing), new Object[] { "SSS", "FFF", "FFF", 'S', Item.stick, 'F', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataFlyWing) });

		GameRegistry.addRecipe(new ItemStack(ModItems.waspDagger), new Object[] { "   ", " W ", " S ", 'W', new ItemStack(ModItems.erebusMaterials, 1, 10), 'S', new ItemStack(Item.stick) });

		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());

		// Red Gem
		GameRegistry.addShapelessRecipe(new ItemStack(Item.redstone, 2, 0), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataRedGem) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 0), new Object[] { "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataRedGem) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 1), new Object[] { " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', new ItemStack(Item.stick, 1, 0) });

		// Bamboo
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 0), new Object[] { "S", "B", 'S', Item.silk, 'B', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataBamboo), new Object[] { "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooCrate), new Object[] { "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataBamboo), 'b', new ItemStack(ModItems.erebusMaterials, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooLadder, 1), new Object[] { "BBB", "S S", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo), 'S', Item.silk });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooTorch, 4), new Object[] { " C ", " B ", " B ", 'C', Item.coal, 'B', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooBridge, 3), new Object[] { "SSS", "B B", "LLL", 'S', Item.silk, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooPole, 4), new Object[] { " S ", " B ", " B ", 'S', Item.slimeBall, 'B', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.extenderThingy, 1), new Object[] { "BSB", "PDP", "BRB", 'S', Item.silk, 'R', Item.redstone, 'D', Block.dispenser, 'B', new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo), 'P',
		new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataBamboo) });

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataBambooSoup), new Object[] { new ItemStack(Item.bowlEmpty), new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBamboo),
		new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBambooShoot) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataMelonade), new Object[] { new ItemStack(Item.potion, 1, 0), new ItemStack(Item.melon) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataMelonadeSparkly), new Object[] { new ItemStack(Item.potion, 1, 0), new ItemStack(Item.speckledMelon) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataLarvaeOnStick), new Object[] { new ItemStack(Item.stick), new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataLarvaCooked), new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataLarvaCooked),
		new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataLarvaCooked) });

		// Miscellaneous
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSilk, 1), new Object[] { "sss", "sss", "sss", 's', Item.silk });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockAmber, 4, 2), new Object[] { "ss", "ss", 's', new ItemStack(ModBlocks.blockAmber, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.portalActivator, 1), new Object[] { "  O", " / ", "/  ", 'O', new ItemStack(Item.spiderEye), '/', new ItemStack(Item.stick) });
		GameRegistry.addRecipe(new ItemStack(Item.silk, 9), new Object[] { "#", '#', new ItemStack(ModBlocks.blockSilk) });
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 1, 15), new Object[] { "#", '#', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 6, 15), new Object[] { "#", '#', new ItemStack(ModItems.fossilClub, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(Item.feather, 1, 0), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Item.stick, 1, 0), 'T', new ItemStack(Item.flint, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.sprayCan, 9), new Object[] { " B ", "XRX", "XXX", 'X', Item.ingotIron, 'B', Block.woodenButton, 'R', Item.redstone });
		GameRegistry.addRecipe(new ItemStack(ModItems.wandOfAnimation, 1), new Object[] { "  N", " S ", "D  ", 'D', Item.diamond, 'S', Item.stick, 'N', Item.netherStar });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusAltar, 1), new Object[] { "XXX", "XOX", "XXX", 'O', Block.obsidian, 'X', new ItemStack(ModItems.erebusMaterials, 1, 15) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowingJar, 1), new Object[] { "XXX", "GBG", "GGG", 'X', Item.ingotIron, 'G', Block.glass, 'B', new ItemStack(ModItems.erebusMaterials, 1, 13) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), new Object[] { new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo), new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo),
		new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo), new ItemStack(ModItems.erebusMaterials, 16, ItemErebusMaterial.dataReinforcedPlateExo) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberGolemStatue, 1), new Object[] { " s ", "sss", "s s", 's', Block.stone });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.book, 1, 0), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataExoPlate), new ItemStack(Item.paper, 1, 0), new ItemStack(Item.paper, 1, 0), new ItemStack(Item.paper, 1, 0) });

		// Furnace smelting
		FurnaceRecipes.smelting().addSmelting(ModBlocks.blockAmber.blockID, 0, new ItemStack(ModBlocks.blockAmber, 1, 1), 0.3F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 0, new ItemStack(ModItems.erebusFood, 1, 1), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 2, new ItemStack(ModItems.erebusFood, 1, 3), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModItems.erebusFood.itemID, 4, new ItemStack(ModItems.erebusFood, 1, 5), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberstone.blockID, 1, new ItemStack(ModBlocks.umberstone, 1), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 0, new ItemStack(Item.coal, 1), 0.1F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 1, new ItemStack(Item.ingotIron, 1), 0.7F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 2, new ItemStack(Item.ingotGold, 1), 1.0F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 3, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 4, new ItemStack(Item.diamond, 1), 1.0F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 5, new ItemStack(Item.emerald, 1), 1.0F);
		FurnaceRecipes.smelting().addSmelting(ModBlocks.umberOreBlock.blockID, 6, new ItemStack(ModItems.erebusMaterials, 1, 1), 1.0F);

		// Ore dictionary registrations
		OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup1, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup2, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planksErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(ModBlocks.erebusSapling, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(ModBlocks.leavesErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreCoal", new ItemStack(ModBlocks.umberOreBlock, 1, 0));
		OreDictionary.registerOre("oreIron", new ItemStack(ModBlocks.umberOreBlock, 1, 1));
		OreDictionary.registerOre("oreGold", new ItemStack(ModBlocks.umberOreBlock, 1, 2));
		OreDictionary.registerOre("oreLapis", new ItemStack(ModBlocks.umberOreBlock, 1, 3));
		OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.umberOreBlock, 1, 4));
		OreDictionary.registerOre("oreEmerald", new ItemStack(ModBlocks.umberOreBlock, 1, 5));
		OreDictionary.registerOre("craftingtable", new ItemStack(ModBlocks.petrifiedCraftingTable));

		if (ConfigurationHandler.lead) {
			OreDictionary.registerOre("ingotLead", new ItemStack(ModItems.metalIngot, 1, 1));
			OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.erebusOreExtra, 1, 2));
			FurnaceRecipes.smelting().addSmelting(ModBlocks.erebusOreExtra.blockID, 2, new ItemStack(ModItems.metalIngot, 1, 1), 1.0F);
		}
		if (ConfigurationHandler.silver) {
			OreDictionary.registerOre("ingotSilver", new ItemStack(ModItems.metalIngot, 1, 2));
			OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.erebusOreExtra, 1, 3));
			FurnaceRecipes.smelting().addSmelting(ModBlocks.erebusOreExtra.blockID, 3, new ItemStack(ModItems.metalIngot, 1, 2), 1.0F);
		}
		if (ConfigurationHandler.copper) {
			OreDictionary.registerOre("ingotCopper", new ItemStack(ModItems.metalIngot, 1, 0));
			OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.erebusOreExtra, 1, 1));
			FurnaceRecipes.smelting().addSmelting(ModBlocks.erebusOreExtra.blockID, 1, new ItemStack(ModItems.metalIngot, 1, 0), 1.0F);
		}
		if (ConfigurationHandler.tin) {
			OreDictionary.registerOre("ingotTin", new ItemStack(ModItems.metalIngot, 1, 3));
			OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.erebusOreExtra, 1, 4));
			FurnaceRecipes.smelting().addSmelting(ModBlocks.erebusOreExtra.blockID, 4, new ItemStack(ModItems.metalIngot, 1, 3), 1.0F);
		}
		if (ConfigurationHandler.aluminium)
			OreDictionary.registerOre("oreAluminum", new ItemStack(ModBlocks.erebusOreExtra, 1, 0));
	}
}