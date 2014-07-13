package erebus.recipes;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockErebusFlower.FLOWER_TYPE;
import erebus.block.BlockErebusOre;
import erebus.block.BlockSlabStone;
import erebus.block.BlockSmallPlants;
import erebus.core.handler.ConfigHandler;
import erebus.item.ErebusFood;
import erebus.item.ErebusMaterial;
import erebus.item.ErebusMaterial.DATA;
import erebus.item.ErebusSpecial;
import erebus.lib.EnumWood;

public class RecipeHandler {

	public static Item[] swordType = new Item[] { Items.wooden_sword, Items.stone_sword, Items.iron_sword, Items.golden_sword, Items.diamond_sword, ModItems.jadeSword, ModItems.scorpionPincer, ModItems.waspSword };
	public static Item[] axeType = new Item[] { Items.wooden_axe, Items.stone_axe, Items.iron_axe, Items.golden_axe, Items.diamond_axe, ModItems.jadeAxe };

	public static void init() {
		EnumWood.initRecipes();

		registerOreDictionary();
		registerRecipes();
		registerSmelting();
	}

	private static void addSlabRecipe(BlockSlabStone block) {
		GameRegistry.addRecipe(new ItemStack(block, 6), new Object[] { "xxx", 'x', new ItemStack(block.base, 1, block.meta) });
	}

	private static void registerRecipes() {
		// Wood
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.planksErebus, 1, EnumWood.White.ordinal()), "plankWood", "dyeWhite"));

		// Umber stuff
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 0), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 1), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 2), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberFurnace, 1), new Object[] { "###", "#$#", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1), '$', Blocks.furnace });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 4, 4), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstoneButton, 1, 0), new Object[] { "#", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 9, 5), new Object[] { "###", "###", "###", '#', new ItemStack(ModBlocks.umberstone) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 4, 6), new Object[] { "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstonePillar, 2), new Object[] { "#", "#", '#', new ItemStack(ModBlocks.umberstone) });

		// Petrified Wood stuffs
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), new Object[] { "xx", "xx", 'x', new ItemStack(ModItems.erebusMaterials, 1, DATA.itemPetrifiedWood.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedCraftingTable), new Object[] { "xx", "xx", 'x', ModBlocks.petrifiedWoodPlanks });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodChest), new Object[] { "xxx", "xyx", "xxx", 'x', ModBlocks.petrifiedWoodPlanks, 'y', Items.gold_ingot });

		// Stairs, slabs, walls
		for (int i = 0; i < ModBlocks.umbercobbleStairs.length; i++)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.umbercobbleStairs[i], 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.umberstone, 1, i) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.amberBrickStairs, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodStairs, 4), new Object[] { "#  ", "## ", "###", '#', new ItemStack(ModBlocks.petrifiedWoodPlanks, 1, 0) });

		for (Block slab : ModBlocks.stoneSlabs)
			addSlabRecipe((BlockSlabStone) slab);

		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 0), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 1), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 2), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 3), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 4), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 5), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 6), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 7), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 8), new Object[] { "###", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ModItems.doorAmberItem, 1, 0), new Object[] { "##", "##", "##", '#', new ItemStack(ModBlocks.blockAmber, 1, 2) });

		// Jade tools
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePickaxe, 1), new Object[] { "XXX", " # ", " # ", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeShovel, 1), new Object[] { "X", "#", "#", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeAxe, 1), new Object[] { "XX", "X#", " #", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHoe, 1), new Object[] { "XX", " #", " #", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeSword, 1), new Object[] { "X", "X", "#", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePaxel, 1), new Object[] { "XXX", "XSX", "XSX", 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'S', Items.stick });
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
		GameRegistry.addRecipe(new ItemStack(ModItems.lightCrown, 1), new Object[] { "XXX", "XOX", "   ", 'O', new ItemStack(ModBlocks.glowGemBlock, 1), 'X', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(ModItems.scorpionPincer), new Object[] { "I I", "XIX", "XPX", 'I', Items.iron_ingot, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()), 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(ModItems.rolledNewspaper), new Object[] { "PWP", "PIP", "PWP", 'I', new ItemStack(Items.dye, 1, 0), 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()), 'W', new ItemStack(ModItems.erebusMaterials, 1, DATA.whetstonePowder.ordinal()) });

		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.gliderWing.ordinal()), new Object[] { "SSS", "FFF", "FFF", 'S', Items.stick, 'F', new ItemStack(ModItems.erebusMaterials, 1, DATA.flyWing.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.enhancedGliderWing.ordinal()), new Object[] { "BBB", "WWW", "WWW", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'W', new ItemStack(ModItems.erebusMaterials, 1, DATA.dragonflyWing.ordinal()) });

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.waspDagger), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, 10), new ItemStack(Items.stick) });

		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());

		GameRegistry.addRecipe(new RecipeWhetstoneUpgrades());

		// Red Gem
		GameRegistry.addShapelessRecipe(new ItemStack(Items.redstone, 2, 0), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 0), new Object[] { "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 1), new Object[] { " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', new ItemStack(Items.stick, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowGemBlock, 3, 0), new Object[] { "BBB", "BGB", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bioLuminescence.ordinal()), 'G', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()) });

		// Bamboo
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 0), new Object[] { "S", "B", 'S', Items.string, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 3), new Object[] { "RRR", "RBR", "RRR", 'B', new ItemStack(ModItems.bamBucket, 1, 0), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 1, EnumWood.Bamboo.ordinal()), new Object[] { "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooCrate), new Object[] { "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planksErebus, 1, EnumWood.Bamboo.ordinal()), 'b', new ItemStack(ModItems.erebusMaterials, 1, 3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooLadder, 1), new Object[] { "BBB", "S S", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'S', Items.string });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooTorch, 4), new Object[] { "C", "B", "B", 'C', Items.coal, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooBridge, 3), new Object[] { "SSS", "B B", "LLL", 'S', Items.string, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooPole, 4), new Object[] { " S ", " B ", " B ", 'S', Items.slime_ball, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.extenderThingy, 1), new Object[] { "BSB", "PDP", "BRB", 'S', Items.string, 'R', Items.redstone, 'D', Blocks.dispenser, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'P', new ItemStack(ModBlocks.planksErebus, 1, EnumWood.Bamboo.ordinal()) });

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.dataBambooSoup), new Object[] { new ItemStack(Items.bowl), new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.bambooShoot.ordinal()) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.dataMelonade), new Object[] { new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.melon) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.dataMelonadeSparkly), new Object[] { new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.speckled_melon) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.dataLarvaeOnStick), new Object[] { new ItemStack(Items.stick), new ItemStack(ModItems.erebusFood, 1, ErebusFood.dataLarvaCooked), new ItemStack(ModItems.erebusFood, 1, ErebusFood.dataLarvaCooked), new ItemStack(ModItems.erebusFood, 1, ErebusFood.dataLarvaCooked) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusFood, 2, ErebusFood.dataHoneySandwich), new Object[] { " B ", "RRR", " B ", 'B', new ItemStack(Items.bread), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyTreat, 1), new Object[] { "SRS", "RBR", "SRS", 'S', new ItemStack(Items.sugar), 'B', new ItemStack(Items.bread), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()) });

		// Miscellaneous
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'x', Items.clay_ball, 'y', new ItemStack(ModItems.erebusMaterials, 1, ErebusMaterial.DATA.mudBrick.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'y', Items.clay_ball, 'x', new ItemStack(ModItems.erebusMaterials, 1, ErebusMaterial.DATA.mudBrick.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'x', Blocks.clay, 'y', ModBlocks.mudBricks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'y', Blocks.clay, 'x', ModBlocks.mudBricks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSilk, 1), new Object[] { "sss", "sss", "sss", 's', Items.string });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockAmber, 4, 2), new Object[] { "ss", "ss", 's', new ItemStack(ModBlocks.blockAmber, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.portalActivator, 1), new Object[] { "  O", " / ", "/  ", 'O', new ItemStack(Items.spider_eye), '/', new ItemStack(Items.stick) });
		GameRegistry.addRecipe(new ItemStack(Items.string, 9), new Object[] { "#", '#', new ItemStack(ModBlocks.blockSilk) });
		GameRegistry.addRecipe(new ItemStack(Items.dye, 1, 15), new Object[] { "#", '#', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Items.dye, 6, 15), new Object[] { "#", '#', new ItemStack(ModItems.fossilClub, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(Items.feather, 1, 0), 'S', new ItemStack(Items.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Items.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), new Object[] { "T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Items.stick, 1, 0), 'T', new ItemStack(Items.flint, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ModItems.sprayCan, 9), new Object[] { " B ", "XRX", "XXX", 'X', Items.iron_ingot, 'B', Blocks.wooden_button, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.repellent.ordinal()) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wandOfAnimation, 1), new Object[] { "  N", " S ", "D  ", 'D', "gemDiamond", 'S', Items.stick, 'N', Items.nether_star }));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusAltar, 1), new Object[] { "XXX", "XOX", "XXX", 'O', Blocks.obsidian, 'X', new ItemStack(ModItems.erebusMaterials, 1, 15) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowingJar, 1), new Object[] { "XXX", "GBG", "GGG", 'X', Items.iron_ingot, 'G', new ItemStack(ModBlocks.blockAmber, 1, 1), 'B', new ItemStack(ModItems.erebusMaterials, 1, 13) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), new Object[] { new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.book, 1, 0), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 4), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.velocityBlock), "xxx", "xxx", "xxx", 'x', new ItemStack(ModItems.erebusMaterials, 1, DATA.bioVelocity.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mudBricks), "xx", "xx", 'x', new ItemStack(ModItems.erebusMaterials, 1, DATA.mudBrick.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.homingBeecon), "GNG", "NCN", "GNG", 'N', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()), 'G', Items.gold_ingot, 'C', Items.compass);
		GameRegistry.addRecipe(new ItemStack(ModItems.nectarCollector), "  B", " S ", "S  ", 'B', Items.bowl, 'S', Items.stick);
		ItemStack diamondPick = new ItemStack(Items.diamond_pickaxe);
		diamondPick.addEnchantment(Enchantment.silkTouch, 1);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.blockExtractor), "  P", " D ", "C  ", 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()), 'D', diamondPick, 'C', Blocks.chest));
		GameRegistry.addRecipe(new ItemStack(ModItems.bucketHoney), "RRR", "RBR", "RRR", 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()), 'B', Items.bucket);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.jarOHoney), "%%%", "$0$", "$$$", '%', Items.iron_ingot, '$', new ItemStack(ModBlocks.blockAmber, 1, 1), '0', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.jadeBlock), "xxx", "xxx", "xxx", 'x', "gemJade"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.erebusMaterials, 9, ErebusMaterial.DATA.jade.ordinal()), "blockJade"));
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.mucusCharge.ordinal()), new Object[] { "SSS", "SRS", "SSS", 'S', Items.slime_ball, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.repellent.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mucusBomb, 1), new Object[] { "MMM", "MTM", "MMM", 'M', new ItemStack(ModItems.erebusMaterials, 1, DATA.mucusCharge.ordinal()), 'T', Blocks.tnt });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyCombBlock, 1), new Object[] { "NPN", "PCP", "NPN", 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()), 'C', Blocks.chest, 'N', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(Items.blaze_powder, 1), new Object[] { "FFF", "FFF", "FFF", 'F', new ItemStack(ModBlocks.erebusPlantSmall, 1, 13) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.mossBall.ordinal()), new Object[] { "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.erebusWallPlants, 1, 0) });
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.scorchedPlanks, 4), ModBlocks.scorchedWood);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.rottenPlanks, 2), ModBlocks.rottenWood);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ritualDagger), "  x", " y ", "z  ", 'x', "ingotGold", 'y', "gemJade", 'z', "stickWood"));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.glowshroom, 1), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, DATA.yellowDottedFungus.ordinal()), Blocks.torch });
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.glowshroom, 1), new Object[] { new ItemStack(ModItems.erebusMaterials, 1, DATA.yellowDottedFungus.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.bioLuminescence.ordinal()) });
		
		// Whetstone Sharpening Enchanting Stuff
		GameRegistry.addRecipe(new ItemStack(ModItems.whetstone, 1, 0), "SSS", "PPP", "UUU", 'S', Blocks.sand, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.itemPetrifiedWood.ordinal()), 'U', new ItemStack(ModBlocks.umberstone, 1, 0));

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
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataRhinoRidingKit), new Object[] { " SX", "CCC", "LLL", 'S', Items.string, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()), 'C', new ItemStack(Blocks.carpet, 1, 0), 'L', new ItemStack(Items.dye, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataBeetleTamingAmulet), new Object[] { " N ", "NJN", " F ", 'N', Items.gold_nugget, 'J', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'F', new ItemStack(ModItems.erebusMaterials, 1, DATA.altarFragment.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.beeTamingAmulet, 1), new Object[] { " n ", "nJn", " N ", 'n', Items.gold_nugget, 'J', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'N', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()) });

		//Umbergolem parts recipe 1
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemCore), new Object[] { "AAA", "ARA", "AAA", 'A', new ItemStack(ModItems.erebusMaterials, 1, DATA.altarFragment.ordinal()), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemHead), new Object[] { "SSS", "SHS", "SSS", 'S', Blocks.stone, 'H', new ItemStack(ModItems.reinCompoundGoggles, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLClaw), new Object[] { "  P", "  S", " SS", 'S', Blocks.stone, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemRClaw), new Object[] { "SSP", "S  ", "   ", 'S', Blocks.stone, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLegs), new Object[] { "SSS", "S S", "R R", 'S', Blocks.stone, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()) });

		//Umbergolem parts recipe 2
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemHead), new Object[] { "SSS", "SHS", "SSS", 'S', ModBlocks.umberstone, 'H', new ItemStack(ModItems.reinCompoundGoggles, 1) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLClaw), new Object[] { "  P", "  S", " SS", 'S', ModBlocks.umberstone, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemRClaw), new Object[] { "SSP", "S  ", "   ", 'S', ModBlocks.umberstone, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()) });
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLegs), new Object[] { "SSS", "S S", "R R", 'S', ModBlocks.umberstone, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()) });

		// Umbergolem Statue
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberGolemStatue, 1), new Object[] { " H ", "LCR", " X ", 'H', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemHead), 'L', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLClaw), 'C', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemCore), 'R', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemRClaw), 'X',
		new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLegs) });

		// Mushroom Blocks
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusMushroomCap0, 1), new Object[] { "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.erebusPlantSmall, 1, BlockSmallPlants.dataBulbCappedShroom) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusMushroomCap1, 1), new Object[] { "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.erebusPlantSmall, 1, BlockSmallPlants.dataMushroom1) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusMushroomCap2, 1), new Object[] { "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.erebusPlantSmall, 1, BlockSmallPlants.dataMushroom2) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusMushroomCap3, 1), new Object[] { "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.erebusPlantSmall, 1, BlockSmallPlants.dataMushroom3) });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusMushroomCap4, 1), new Object[] { "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.erebusPlantSmall, 1, BlockSmallPlants.dataDutchCapShroom) });
		GameRegistry.addRecipe(new ItemStack(Blocks.red_mushroom_block, 1), new Object[] { "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.red_mushroom, 1) });
		GameRegistry.addRecipe(new ItemStack(Blocks.brown_mushroom_block, 1), new Object[] { "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.brown_mushroom, 1) });

		// Mushroom Helm
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.mushroomHelm, 1), new Object[] { new ItemStack(ModBlocks.erebusMushroomCap0, 1), new ItemStack(ModBlocks.erebusMushroomCap1, 1), new ItemStack(ModBlocks.erebusMushroomCap2, 1), new ItemStack(ModBlocks.erebusMushroomCap3, 1), new ItemStack(ModBlocks.erebusMushroomCap4, 1), new ItemStack(Blocks.red_mushroom_block, 1), new ItemStack(Blocks.brown_mushroom_block, 1), new ItemStack(ModItems.erebusMaterials, 1, DATA.camoPowder.ordinal()),
		new ItemStack(Blocks.pumpkin, 1) });

	}

	private static void registerSmelting() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockAmber, 1, 1), new ItemStack(ModBlocks.blockAmber), 0.3F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, 1), new ItemStack(ModItems.erebusFood, 1, 0), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, 3), new ItemStack(ModItems.erebusFood, 1, 2), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, 5), new ItemStack(ModItems.erebusFood, 1, 4), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberstone, 1), new ItemStack(ModBlocks.umberstone, 1, 1), 0.2F);
		GameRegistry.addSmelting(new ItemStack(Items.coal, 1), new ItemStack(ModBlocks.umberOreBlock, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(Items.iron_ingot, 1), new ItemStack(ModBlocks.umberOreBlock, 1, 1), 0.7F);
		GameRegistry.addSmelting(new ItemStack(Items.gold_ingot, 1), new ItemStack(ModBlocks.umberOreBlock, 1, 2), 1.0F);
		GameRegistry.addSmelting(new ItemStack(Items.dye, 1, 4), new ItemStack(ModBlocks.umberOreBlock, 1, 3), 0.2F);
		GameRegistry.addSmelting(new ItemStack(Items.diamond, 1), new ItemStack(ModBlocks.umberOreBlock, 1, 4), 1.0F);
		GameRegistry.addSmelting(new ItemStack(Items.emerald, 1), new ItemStack(ModBlocks.umberOreBlock, 1, 5), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusMaterials, 1, 1), new ItemStack(ModBlocks.umberOreBlock, 1, 6), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.mud), new ItemStack(ModItems.erebusMaterials, 1, DATA.mudBrick.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()), 0.2F);
		if (ConfigHandler.lead)
			GameRegistry.addSmelting(new ItemStack(ModItems.metalIngot, 1, 1), new ItemStack(ModBlocks.erebusOreExtra, 1, 2), 1.0F);
		if (ConfigHandler.silver)
			GameRegistry.addSmelting(new ItemStack(ModItems.metalIngot, 1, 2), new ItemStack(ModBlocks.erebusOreExtra, 1, 3), 1.0F);
		if (ConfigHandler.copper)
			GameRegistry.addSmelting(new ItemStack(ModItems.metalIngot, 1, 0), new ItemStack(ModBlocks.erebusOreExtra, 1, 1), 1.0F);
		if (ConfigHandler.tin)
			GameRegistry.addSmelting(new ItemStack(ModItems.metalIngot, 1, 3), new ItemStack(ModBlocks.erebusOreExtra, 1, 4), 1.0F);
	}

	private static void registerOreDictionary() {
		OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planksErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(ModBlocks.leavesErebus, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("oreCoal", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataCoal));
		OreDictionary.registerOre("oreIron", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataIron));
		OreDictionary.registerOre("oreGold", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataGold));
		OreDictionary.registerOre("oreLapis", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataLapis));
		OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataDiamond));
		OreDictionary.registerOre("oreEmerald", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataEmerald));
		OreDictionary.registerOre("oreJade", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataJade));
		OreDictionary.registerOre("orePetrifiedWood", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataPetrifiedWood));
		OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.umberOreBlock, 1, BlockErebusOre.dataEncrustedDiamond));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.spawnEggs, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemJade", new ItemStack(ModItems.erebusMaterials, 1, ErebusMaterial.DATA.jade.ordinal()));
		OreDictionary.registerOre("blockJade", new ItemStack(ModBlocks.jadeBlock));
		OreDictionary.registerOre("blockSpawner", ModBlocks.spiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.jumpingSpiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.waspSpawner);
		OreDictionary.registerOre("gemDiamond", ModItems.encrustedDiamond);
		OreDictionary.registerOre("gemDiamond", Items.diamond);
		OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.blockAmber, 1, OreDictionary.WILDCARD_VALUE));

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