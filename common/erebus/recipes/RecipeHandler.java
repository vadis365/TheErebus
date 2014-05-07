package erebus.recipes;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockErebusFlower.FLOWER_TYPE;
import erebus.block.BlockErebusOre;
import erebus.block.BlockLogErebus;
import erebus.block.BlockPlanksErebus;
import erebus.core.handler.ConfigHandler;
import erebus.item.ItemErebusFood;
import erebus.item.ItemErebusMaterial;
import erebus.item.ItemErebusMaterial.DATA;
import erebus.item.ItemErebusSpecial;

public class RecipeHandler {

	public static Item[] swordType = new Item[] { Item.swordWood, Item.swordStone, Item.swordIron, Item.swordGold, Item.swordDiamond, ModItems.jadeSword, ModItems.scorpionPincer, ModItems.waspSword };
	public static Item[] axeType = new Item[] { Item.axeWood, Item.axeStone, Item.axeIron, Item.axeGold, Item.axeDiamond, ModItems.jadeAxe };

	public static void init() {
		registerOreDictionary();
		registerRecipes();
		registerSmelting();
	}

	private static void registerRecipes() {
		// Wood
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataAcacia), new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataAcacia));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataEucalyptus), new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataEucalyptus));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataMahogany), new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataMahogany));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataBaobab), new ItemStack(ModBlocks.logErebusGroup1, 1, BlockLogErebus.dataBaobab));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataMossbark), new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataMossbark));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataPink), new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataPink));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataScorched), new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataScorched));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataAsper), new ItemStack(ModBlocks.logErebusGroup2, 1, BlockLogErebus.dataAsper));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planksErebus, 4, BlockPlanksErebus.dataCypress), new ItemStack(ModBlocks.logErebusGroup3, 1, BlockLogErebus.dataCypress));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataWhite), "plankWood", "dyeWhite"));

		// Umber stuff
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 0), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 1), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 2), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberFurnace, 1), new Object[] { "###", "#$#", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1), '$', Block.furnaceIdle });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 4, 4), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstoneButton, 1, 0), new Object[] { "#", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 9, 5), new Object[] { "###", "###", "###", '#', new ItemStack(ModBlocks.umberstone) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 4, 6), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstonePillar, 2), new Object[] { "#", "#", '#', new ItemStack(ModBlocks.umberstone) });

		// Stone tools made from umberstone
		GameRegistry.addRecipe(new ItemStack(Item.pickaxeStone, 1), new Object[] { "XXX", " # ", " # ", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.shovelStone, 1), new Object[] { "X", "#", "#", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.axeStone, 1), new Object[] { "XX", "X#", " #", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.hoeStone, 1), new Object[] { "XX", " #", " #", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(Item.swordStone, 1), new Object[] { "X", "X", "#", '#', Item.stick, 'X', new ItemStack(ModBlocks.umberstone, 1, 1) });

		// Petrified Wood stuffs
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), new Object[] { "xx", "xx", 'x', new ItemStack(ModItems.erebusMaterials, 1, DATA.itemPetrifiedWood.ordinal()) });
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
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePickaxe, 1), new Object[] { "XXX", " # ", " # ", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeShovel, 1), new Object[] { "X", "#", "#", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeAxe, 1), new Object[] { "XX", "X#", " #", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHoe, 1), new Object[] { "XX", " #", " #", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeSword, 1), new Object[] { "X", "X", "#", '#', Item.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePaxel, 1), new Object[] { "XXX", "XSX", "XSX", 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'S', Item.stick });
		GameRegistry.addRecipe(new RecipePaxel());

		// Jade armor
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHelmet, 1), new Object[] { "###", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBody, 1), new Object[] { "# #", "###", "###", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeLegs, 1), new Object[] { "###", "# #", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBoots, 1), new Object[] { "# #", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });

		// Exoskeleton armor
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), new Object[] { "sss", "s s", "   ", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBody, 1), new Object[] { "s s", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), new Object[] { "sss", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), new Object[] { "   ", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()), new Object[] { "sss", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonHelmet, 1), new Object[] { "sss", "s s", "   ", 's', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBody, 1), new Object[] { "s s", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonLegs, 1), new Object[] { "sss", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBoots, 1), new Object[] { "   ", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()) });

		// Special armor
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.compoundLens.ordinal()), new Object[] { "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.blockAmber, 1, 1), 'G', new ItemStack(ModItems.erebusMaterials, 1, DATA.compoundEyes.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.compoundGoggles, 1), new Object[] { "XXX", "OXO", "   ", 'O', new ItemStack(ModItems.erebusMaterials, 1, DATA.compoundLens.ordinal()), 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.reinCompoundGoggles, 1), new Object[] { "XXX", "XOX", "   ", 'O', new ItemStack(ModItems.compoundGoggles, 1), 'X', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jumpBoots), new Object[] { "F F", "BXB", "B B", 'F', new ItemStack(ModItems.erebusMaterials, 1, DATA.flyWing.ordinal()), 'B', new ItemStack(ModItems.erebusMaterials, 1, 9), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.sprintLeggings), new Object[] { "BBB", "BXB", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bioVelocity.ordinal()), 'X', new ItemStack(ModItems.reinExoskeletonLegs, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGlider), new Object[] { "   ", "GXG", "   ", 'G', new ItemStack(ModItems.erebusMaterials, 1, DATA.gliderWing.ordinal()), 'X', new ItemStack(ModItems.reinExoskeletonBody, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGliderPowered), new Object[] { "W W", "ECE", " V ", 'W', new ItemStack(ModItems.erebusMaterials, 1, DATA.enhancedGliderWing.ordinal()), 'E', new ItemStack(ModItems.erebusMaterials, 1, DATA.elasticFibre.ordinal()), 'C', new ItemStack(ModItems.armorGlider, 1), 'V', new ItemStack(ModBlocks.velocityBlock, 1) });

		GameRegistry.addRecipe(new ItemStack(ModItems.scorpionPincer), new Object[] { "I I", "XIX", "XPX", 'I', Item.ingotIron, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()), 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(ModItems.rolledNewspaper), new Object[] { "PWP", "PIP", "PWP", 'I', new ItemStack(Item.dyePowder, 1, 0), 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()), 'W', new ItemStack(ModItems.erebusMaterials, 1, DATA.whetstonePowder.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.gliderWing.ordinal()), new Object[] { "SSS", "FFF", "FFF", 'S', Item.stick, 'F', new ItemStack(ModItems.erebusMaterials, 1, DATA.flyWing.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.enhancedGliderWing.ordinal()), new Object[] { "BBB", "WWW", "WWW", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'W', new ItemStack(ModItems.erebusMaterials, 1, DATA.dragonflyWing.ordinal()) });

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.waspDagger), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, 10), new ItemStack(Item.stick) });

		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());

		GameRegistry.addRecipe(new RecipeWhetstoneUpgrades());

		// Red Gem
		GameRegistry.addShapelessRecipe(new ItemStack(Item.redstone, 2, 0), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 0), new Object[] { "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 1), new Object[] { " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', new ItemStack(Item.stick, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowGemBlock, 3, 0), new Object[] { "BBB", "BGB", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bioLuminescence.ordinal()), 'G', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()) });

		// Bamboo
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 0), new Object[] { "S", "B", 'S', Item.silk, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 3), new Object[] { "RRR", "RBR", "RRR", 'B', new ItemStack(ModItems.bamBucket, 1, 0), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataBamboo), new Object[] { "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooCrate), new Object[] { "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataBamboo), 'b', new ItemStack(ModItems.erebusMaterials, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooLadder, 1), new Object[] { "BBB", "S S", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'S', Item.silk });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooTorch, 4), new Object[] { "C", "B", "B", 'C', Item.coal, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooBridge, 3), new Object[] { "SSS", "B B", "LLL", 'S', Item.silk, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooPole, 4), new Object[] { " S ", " B ", " B ", 'S', Item.slimeBall, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.extenderThingy, 1), new Object[] { "BSB", "PDP", "BRB", 'S', Item.silk, 'R', Item.redstone, 'D', Block.dispenser, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'P', new ItemStack(ModBlocks.planksErebus, 1, BlockPlanksErebus.dataBamboo) });

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataBambooSoup), new Object[] { new ItemStack(Item.bowlEmpty), new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.bambooShoot.ordinal()) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataMelonade), new Object[] { new ItemStack(Item.potion, 1, 0), new ItemStack(Item.melon) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataMelonadeSparkly), new Object[] { new ItemStack(Item.potion, 1, 0), new ItemStack(Item.speckledMelon) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataLarvaeOnStick), new Object[] { new ItemStack(Item.stick), new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataLarvaCooked), new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataLarvaCooked), new ItemStack(ModItems.erebusFood, 1, ItemErebusFood.dataLarvaCooked) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusFood, 2, ItemErebusFood.dataHoneySandwich), new Object[] { " B ", "RRR", " B ", 'B', new ItemStack(Item.bread), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyTreat, 1), new Object[] { "SRS", "RBR", "SRS", 'S', new ItemStack(Item.sugar), 'B', new ItemStack(Item.bread), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()) });

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
		GameRegistry.addRecipe(new ItemStack(ModItems.sprayCan, 9), new Object[] { " B ", "XRX", "XXX", 'X', Item.ingotIron, 'B', Block.woodenButton, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.repellent.ordinal()) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wandOfAnimation, 1), new Object[] { "  N", " S ", "D  ", 'D', "gemDiamond", 'S', Item.stick, 'N', Item.netherStar }));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusAltar, 1), new Object[] { "XXX", "XOX", "XXX", 'O', Block.obsidian, 'X', new ItemStack(ModItems.erebusMaterials, 1, 15) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowingJar, 1), new Object[] { "XXX", "GBG", "GGG", 'X', Item.ingotIron, 'G', new ItemStack(ModBlocks.blockAmber, 1, 1), 'B', new ItemStack(ModItems.erebusMaterials, 1, 13) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), new Object[] { new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()) });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.book, 1, 0), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()), new ItemStack(Item.paper, 1, 0), new ItemStack(Item.paper, 1, 0), new ItemStack(Item.paper, 1, 0) });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.paper, 4), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.velocityBlock), "xxx", "xxx", "xxx", 'x', new ItemStack(ModItems.erebusMaterials, 1, DATA.bioVelocity.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mudBricks), "xx", "xx", 'x', new ItemStack(ModItems.erebusMaterials, 1, DATA.mudBrick.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.homingBeecon), "GNG", "NCN", "GNG", 'N', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()), 'G', Item.ingotGold, 'C', Item.compass);
		GameRegistry.addRecipe(new ItemStack(ModItems.nectarCollector), "  B", " S ", "S  ", 'B', Item.bowlEmpty, 'S', Item.stick);
		ItemStack diamondPick = new ItemStack(Item.pickaxeDiamond);
		diamondPick.addEnchantment(Enchantment.silkTouch, 1);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.blockExtractor), "  P", " D ", "C  ", 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()), 'D', diamondPick, 'C', Block.chest));
		GameRegistry.addRecipe(new ItemStack(ModItems.bucketHoney), "RRR", "RBR", "RRR", 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()), 'B', Item.bucketEmpty);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.jarOHoney), "%%%", "$0$", "$$$", '%', Item.ingotIron, '$', new ItemStack(ModBlocks.blockAmber, 1, 1), '0', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.jadeBlock), "xxx", "xxx", "xxx", 'x', "gemJade"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.erebusMaterials, 9, ItemErebusMaterial.DATA.jade.ordinal()), "blockJade"));
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.mucusCharge.ordinal()), new Object[] { "SSS", "SRS", "SSS", 'S', Item.slimeBall, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.repellent.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mucusBomb, 1), new Object[] { "MMM", "MTM", "MMM", 'M', new ItemStack(ModItems.erebusMaterials, 1, DATA.mucusCharge.ordinal()), 'T', Block.tnt });

		// Whetstone Sharpening Enchanting Stuff
		GameRegistry.addRecipe(new ItemStack(ModItems.whetstone, 1, 0), "SSS", "PPP", "UUU", 'S', Block.sand, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.itemPetrifiedWood.ordinal()), 'U', new ItemStack(ModBlocks.umberstone, 1, 0));

		// Sharp Swords
		for (int i = 0; i < swordType.length; i++)
			for (int j = 0; j < 6; j++) {
				ItemStack swordSharp = new ItemStack(swordType[i]);
				ItemStack stoneLevel = new ItemStack(ModItems.whetstone, 1, j);
				if (stoneLevel.getItemDamage() > 0) {
					swordSharp.addEnchantment(Enchantment.sharpness, stoneLevel.getItemDamage());
					GameRegistry.addShapelessRecipe(swordSharp, new ItemStack(ModItems.whetstone, 1, stoneLevel.getItemDamage()), new ItemStack(swordType[i]));
				}
			}

		// Sharp Axes
		for (int i = 0; i < axeType.length; i++)
			for (int j = 0; j < 6; j++) {
				ItemStack axeSharp = new ItemStack(axeType[i]);
				ItemStack stoneLevel = new ItemStack(ModItems.whetstone, 1, j);
				if (stoneLevel.getItemDamage() > 0) {
					axeSharp.addEnchantment(Enchantment.sharpness, stoneLevel.getItemDamage() + 1);
					GameRegistry.addShapelessRecipe(axeSharp, new ItemStack(ModItems.whetstone, 1, stoneLevel.getItemDamage()), new ItemStack(axeType[i]));
				}
			}

		// Special Items - for future expansion
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataRhinoRidingKit), new Object[] { " SX", "CCC", "LLL", 'S', Item.silk, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()), 'C', new ItemStack(Block.carpet, 1, 0), 'L', new ItemStack(Item.dyePowder, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataBeetleTamingAmulet), new Object[] { " N ", "NJN", " F ", 'N', Item.goldNugget, 'J', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'F', new ItemStack(ModItems.erebusMaterials, 1, DATA.altarFragment.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.beeTamingAmulet, 1), new Object[] { " n ", "nJn", " N ", 'n', Item.goldNugget, 'J', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'N', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()) });

		//Umbergolem parts recipe 1
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemCore), new Object[] { "AAA", "ARA", "AAA", 'A', new ItemStack(ModItems.erebusMaterials, 1, DATA.altarFragment.ordinal()), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemHead), new Object[] { "SSS", "SHS", "SSS", 'S', Block.stone, 'H', new ItemStack(ModItems.reinCompoundGoggles, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemLClaw), new Object[] { "  P", "  S", " SS", 'S', Block.stone, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemRClaw), new Object[] { "SSP", "S  ", "   ", 'S', Block.stone, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemLegs), new Object[] { "SSS", "S S", "R R", 'S', Block.stone, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()) });

		//Umbergolem parts recipe 2
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemHead), new Object[] { "SSS", "SHS", "SSS", 'S', ModBlocks.umberstone, 'H', new ItemStack(ModItems.reinCompoundGoggles, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemLClaw), new Object[] { "  P", "  S", " SS", 'S', ModBlocks.umberstone, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemRClaw), new Object[] { "SSP", "S  ", "   ", 'S', ModBlocks.umberstone, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemLegs), new Object[] { "SSS", "S S", "R R", 'S', ModBlocks.umberstone, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()) });

		// Umbergolem Statue
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberGolemStatue, 1), new Object[] { " H ", "LCR", " X ", 'H', new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemHead), 'L', new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemLClaw), 'C', new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemCore), 'R', new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemRClaw), 'X',
				new ItemStack(ModItems.erebusSpecialItem, 1, ItemErebusSpecial.dataGolemLegs) });
	}

	private static void registerSmelting() {
		addSmelting(new ItemStack(Item.coal, 1, 1), "logWood", 1.0F);
		addSmelting(new ItemStack(ModBlocks.blockAmber, 1, 1), ModBlocks.blockAmber, 0.3F);
		addSmelting(new ItemStack(ModItems.erebusFood, 1, 1), new ItemStack(ModItems.erebusFood.itemID, 1, 0), 0.2F);
		addSmelting(new ItemStack(ModItems.erebusFood, 1, 3), new ItemStack(ModItems.erebusFood.itemID, 1, 2), 0.2F);
		addSmelting(new ItemStack(ModItems.erebusFood, 1, 5), new ItemStack(ModItems.erebusFood.itemID, 1, 4), 0.2F);
		addSmelting(new ItemStack(ModBlocks.umberstone, 1), new ItemStack(ModBlocks.umberstone.blockID, 1, 1), 0.2F);
		addSmelting(new ItemStack(Item.coal, 1), new ItemStack(ModBlocks.umberOreBlock.blockID, 1, 0), 0.1F);
		addSmelting(new ItemStack(Item.ingotIron, 1), new ItemStack(ModBlocks.umberOreBlock.blockID, 1, 1), 0.7F);
		addSmelting(new ItemStack(Item.ingotGold, 1), new ItemStack(ModBlocks.umberOreBlock.blockID, 1, 2), 1.0F);
		addSmelting(new ItemStack(Item.dyePowder, 1, 4), new ItemStack(ModBlocks.umberOreBlock.blockID, 1, 3), 0.2F);
		addSmelting(new ItemStack(Item.diamond, 1), new ItemStack(ModBlocks.umberOreBlock.blockID, 1, 4), 1.0F);
		addSmelting(new ItemStack(Item.emerald, 1), new ItemStack(ModBlocks.umberOreBlock.blockID, 1, 5), 1.0F);
		addSmelting(new ItemStack(ModItems.erebusMaterials, 1, 1), new ItemStack(ModBlocks.umberOreBlock.blockID, 1, 6), 1.0F);
		addSmelting(new ItemStack(ModItems.erebusMaterials, 1, DATA.mudBrick.ordinal()), ModBlocks.mud, 0.2F);
		addSmelting(new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()), new ItemStack(ModItems.erebusMaterials.itemID, 1, DATA.nectar.ordinal()), 0.2F);
		if (ConfigHandler.lead)
			addSmelting(new ItemStack(ModItems.metalIngot, 1, 1), new ItemStack(ModBlocks.erebusOreExtra.blockID, 1, 2), 1.0F);
		if (ConfigHandler.silver)
			addSmelting(new ItemStack(ModItems.metalIngot, 1, 2), new ItemStack(ModBlocks.erebusOreExtra.blockID, 1, 3), 1.0F);
		if (ConfigHandler.copper)
			addSmelting(new ItemStack(ModItems.metalIngot, 1, 0), new ItemStack(ModBlocks.erebusOreExtra.blockID, 1, 1), 1.0F);
		if (ConfigHandler.tin)
			addSmelting(new ItemStack(ModItems.metalIngot, 1, 3), new ItemStack(ModBlocks.erebusOreExtra.blockID, 1, 4), 1.0F);
	}

	private static void addSmelting(ItemStack output, Object input, float xp) {
		if (input instanceof ItemStack) {
			if (((ItemStack) input).getItemDamage() == OreDictionary.WILDCARD_VALUE)
				GameRegistry.addSmelting(((ItemStack) input).itemID, output, xp);
			else
				FurnaceRecipes.smelting().addSmelting(((ItemStack) input).itemID, ((ItemStack) input).getItemDamage(), output, xp);
		} else if (input instanceof Item)
			addSmelting(output, new ItemStack((Item) input), xp);
		else if (input instanceof Block)
			addSmelting(output, new ItemStack((Block) input), xp);
		else if (input instanceof String)
			for (ItemStack stack : OreDictionary.getOres((String) input))
				addSmelting(output, stack, xp);
	}

	private static void registerOreDictionary() {
		OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup1, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup2, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.logErebusGroup3, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planksErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(ModBlocks.erebusSapling, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(ModBlocks.leavesErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreCoal", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataCoal));
		OreDictionary.registerOre("oreIron", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataIron));
		OreDictionary.registerOre("oreGold", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataGold));
		OreDictionary.registerOre("oreLapis", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataLapis));
		OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataDiamond));
		OreDictionary.registerOre("oreEmerald", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataEmerald));
		OreDictionary.registerOre("oreJade", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataJade));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.spawnEggs, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemJade", new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.DATA.jade.ordinal()));
		OreDictionary.registerOre("blockJade", new ItemStack(ModBlocks.jadeBlock));
		OreDictionary.registerOre("blockSpawner", ModBlocks.spiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.jumpingSpiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.waspSpawner);
		OreDictionary.registerOre("gemDiamond", ModItems.encrustedDiamond);
		OreDictionary.registerOre("gemDiamond", Item.diamond);

		OreDictionary.registerOre("dyeBlack", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.BLACK_PETAL.ordinal()));
		OreDictionary.registerOre("dyeRed", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.RED_PETAL.ordinal()));
		OreDictionary.registerOre("dyeBrown", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.BROWN_PETAL.ordinal()));
		OreDictionary.registerOre("dyeBlue", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.BLUE_PETAL.ordinal()));
		OreDictionary.registerOre("dyePurple", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.PURPLE_PETAL.ordinal()));
		OreDictionary.registerOre("dyeCyan", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.CYAN_PETAL.ordinal()));
		OreDictionary.registerOre("dyeLightGray", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.LIGHT_GRAY_PETAL.ordinal()));
		OreDictionary.registerOre("dyeGray", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.GRAY_PETAL.ordinal()));
		OreDictionary.registerOre("dyePink", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.PINK_PETAL.ordinal()));
		OreDictionary.registerOre("dyeYellow", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.YELLOW_PETAL.ordinal()));
		OreDictionary.registerOre("dyeLightBlue", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.LIGHT_BLUE_PETAL.ordinal()));
		OreDictionary.registerOre("dyeMagenta", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.MAGENTA_PETAL.ordinal()));
		OreDictionary.registerOre("dyeOrange", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.ORANGE_PETAL.ordinal()));
		OreDictionary.registerOre("dyeWhite", new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.WHITE_PETAL.ordinal()));

		for (Block stair : ModBlocks.plankStairs)
			OreDictionary.registerOre("stairWood", stair);
		for (Block slab : ModBlocks.plankSlabs)
			OreDictionary.registerOre("slabWood", new ItemStack(slab, 1, OreDictionary.WILDCARD_VALUE));

		if (ConfigHandler.lead) {
			OreDictionary.registerOre("ingotLead", new ItemStack(ModItems.metalIngot, 1, 1));
			OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.erebusOreExtra, 1, 2));
		}
		if (ConfigHandler.silver) {
			OreDictionary.registerOre("ingotSilver", new ItemStack(ModItems.metalIngot, 1, 2));
			OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.erebusOreExtra, 1, 3));
		}
		if (ConfigHandler.copper) {
			OreDictionary.registerOre("ingotCopper", new ItemStack(ModItems.metalIngot, 1, 0));
			OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.erebusOreExtra, 1, 1));
		}
		if (ConfigHandler.tin) {
			OreDictionary.registerOre("ingotTin", new ItemStack(ModItems.metalIngot, 1, 3));
			OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.erebusOreExtra, 1, 4));
		}
		if (ConfigHandler.aluminium)
			OreDictionary.registerOre("oreAluminum", new ItemStack(ModBlocks.erebusOreExtra, 1, 0));
	}
}