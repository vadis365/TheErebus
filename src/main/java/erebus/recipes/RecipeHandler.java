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
import erebus.block.BlockErebusOre;
import erebus.block.BlockSlabStone;
import erebus.block.plants.BlockErebusFlower.FLOWER_TYPE;
import erebus.block.plants.BlockSmallPlants;
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
		GameRegistry.addRecipe(new ItemStack(block, 6), "xxx", 'x', new ItemStack(block.base, 1, block.meta));
	}

	private static void registerRecipes() {
		// Wood
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.planksErebus, 1, EnumWood.White.ordinal()), "plankWood", "dyeWhite"));

		// Umber stuff
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 0), "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 1), "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberPaver, 4, 2), "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 3));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberFurnace, 1), "###", "#$#", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1), '$', Items.bucket);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.umberstone, 4, 4), "##", "##", '#', "stoneUmber"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.umberstoneButton, 1, 0), "stoneUmber"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.umberstone, 9, 5), "###", "###", "###", '#', "stoneUmber"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberstone, 4, 6), "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 5));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.umberstonePillar, 2), "#", "#", '#', "stoneUmber"));

		// Petrified Wood stuffs
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), "xx", "xx", 'x', new ItemStack(ModItems.erebusMaterials, 1, DATA.itemPetrifiedWood.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedCraftingTable), "xx", "xx", 'x', ModBlocks.petrifiedWoodPlanks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodChest), "xxx", "xyx", "xxx", 'x', ModBlocks.petrifiedWoodPlanks, 'y', Items.gold_ingot);

		// Stairs, slabs, walls
		for (int i = 0; i < ModBlocks.umbercobbleStairs.length; i++)
			GameRegistry.addRecipe(new ItemStack(ModBlocks.umbercobbleStairs[i], 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.umberstone, 1, i));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.amberBrickStairs, 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodStairs, 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.petrifiedWoodPlanks, 1, 0));

		for (Block slab : ModBlocks.stoneSlabs)
			addSlabRecipe((BlockSlabStone) slab);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.wallErebus, 6, 0), "###", "###", '#', "stoneUmber"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 1), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 2), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 3), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 4), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 5), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 6), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 7), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wallErebus, 6, 8), "###", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModItems.doorAmberItem, 1, 0), "##", "##", "##", '#', new ItemStack(ModBlocks.blockAmber, 1, 2));

		// Jade tools
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePickaxe, 1), "XXX", " # ", " # ", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeShovel, 1), "X", "#", "#", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeAxe, 1), "XX", "X#", " #", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHoe, 1), "XX", " #", " #", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeSword, 1), "X", "X", "#", '#', Items.stick, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePaxel, 1), "XXX", "XSX", "XSX", 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'S', Items.stick);
		GameRegistry.addRecipe(new RecipePaxel());

		// Jade armor
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHelmet, 1), "###", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBody, 1), "# #", "###", "###", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeLegs, 1), "###", "# #", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBoots, 1), "# #", "# #", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()));

		// Exoskeleton armor
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), "sss", "s s", "   ", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBody, 1), "s s", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), "sss", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), "   ", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()));

		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()), "sss", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()));

		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonHelmet, 1), "sss", "s s", "   ", 's', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBody, 1), "s s", "sss", "sss", 's', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonLegs, 1), "sss", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBoots, 1), "   ", "s s", "s s", 's', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()));

		// Special armor
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.compoundLens.ordinal()), "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.blockAmber, 1, 1), 'G', new ItemStack(ModItems.erebusMaterials, 1, DATA.compoundEyes.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.compoundGoggles, 1), "XXX", "OXO", "   ", 'O', new ItemStack(ModItems.erebusMaterials, 1, DATA.compoundLens.ordinal()), 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinCompoundGoggles, 1), "XXX", "XOX", "   ", 'O', new ItemStack(ModItems.compoundGoggles, 1), 'X', new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.jumpBoots), "F F", "BXB", "B B", 'F', new ItemStack(ModItems.erebusMaterials, 1, DATA.flyWing.ordinal()), 'B', new ItemStack(ModItems.erebusMaterials, 1, 9), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.sprintLeggings), "BBB", "BXB", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bioVelocity.ordinal()), 'X', new ItemStack(ModItems.reinExoskeletonLegs, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGlider), "   ", "GXG", "   ", 'G', new ItemStack(ModItems.erebusMaterials, 1, DATA.gliderWing.ordinal()), 'X', new ItemStack(ModItems.reinExoskeletonBody, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGliderPowered), "W W", "ECE", " V ", 'W', new ItemStack(ModItems.erebusMaterials, 1, DATA.enhancedGliderWing.ordinal()), 'E', new ItemStack(ModItems.erebusMaterials, 1, DATA.elasticFibre.ordinal()), 'C', new ItemStack(ModItems.armorGlider, 1), 'V', new ItemStack(ModBlocks.velocityBlock, 1));

		GameRegistry.addRecipe(new ItemStack(ModItems.scorpionPincer), "I I", "XIX", "XPX", 'I', Items.iron_ingot, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal()), 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal()));

		GameRegistry.addRecipe(new ItemStack(ModItems.rolledNewspaper), "PWP", "PIP", "PWP", 'I', new ItemStack(Items.dye, 1, 0), 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()), 'W', new ItemStack(ModItems.erebusMaterials, 1, DATA.whetstonePowder.ordinal()));

		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.gliderWing.ordinal()), "SSS", "FFF", "FFF", 'S', Items.stick, 'F', new ItemStack(ModItems.erebusMaterials, 1, DATA.flyWing.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.enhancedGliderWing.ordinal()), "BBB", "WWW", "WWW", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'W', new ItemStack(ModItems.erebusMaterials, 1, DATA.dragonflyWing.ordinal()));

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.waspDagger), new ItemStack(ModItems.erebusMaterials, 1, 10), new ItemStack(Items.stick));

		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());

		GameRegistry.addRecipe(new RecipeWhetstoneUpgrades());

		// Red Gem
		GameRegistry.addShapelessRecipe(new ItemStack(Items.redstone, 2, 0), new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 0), "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 1), " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', new ItemStack(Items.stick, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowGemBlock, 3, 0), "BBB", "BGB", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bioLuminescence.ordinal()), 'G', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()));

		// Bamboo
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 0), "S", "B", 'S', Items.string, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.bamBucket, 1, 3), "RRR", "RBR", "RRR", 'B', new ItemStack(ModItems.bamBucket, 1, 0), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planksErebus, 1, EnumWood.Bamboo.ordinal()), "##", "##", '#', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooCrate), "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planksErebus, 1, EnumWood.Bamboo.ordinal()), 'b', new ItemStack(ModItems.erebusMaterials, 1, 3));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooLadder, 1), "BBB", "S S", "BBB", 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'S', Items.string);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooTorch, 4), "C", "B", "B", 'C', Items.coal, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooBridge, 3), "SSS", "B B", "LLL", 'S', Items.string, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooPole, 4), " S ", " B ", " B ", 'S', Items.slime_ball, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.extenderThingy, 1), "BSB", "PDP", "BRB", 'S', Items.string, 'R', Items.redstone, 'D', Blocks.dispenser, 'B', new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), 'P', new ItemStack(ModBlocks.planksErebus, 1, EnumWood.Bamboo.ordinal()));

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.bambooSoup.ordinal()), new ItemStack(Items.bowl), new ItemStack(ModItems.erebusMaterials, 1, DATA.bamboo.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.bambooShoot.ordinal()));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.melonade.ordinal()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.melon));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.melonadeSparkly.ordinal()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.speckled_melon));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaeOnStick.ordinal()), new ItemStack(Items.stick), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaCooked.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaCooked.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaCooked.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusFood, 2, ErebusFood.FoodType.honeySandwich.ordinal()), " B ", "RRR", " B ", 'B', new ItemStack(Items.bread), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyTreat, 1), "SRS", "RBR", "SRS", 'S', new ItemStack(Items.sugar), 'B', new ItemStack(Items.bread), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()));

		// Miscellaneous
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'x', Items.clay_ball, 'y', new ItemStack(ModItems.erebusMaterials, 1, ErebusMaterial.DATA.mudBrick.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'y', Items.clay_ball, 'x', new ItemStack(ModItems.erebusMaterials, 1, ErebusMaterial.DATA.mudBrick.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'x', Blocks.clay, 'y', ModBlocks.mudBricks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'y', Blocks.clay, 'x', ModBlocks.mudBricks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSilk, 1), "sss", "sss", "sss", 's', Items.string);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockAmber, 4, 2), "ss", "ss", 's', new ItemStack(ModBlocks.blockAmber, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModItems.portalActivator, 1), "  O", " / ", "/  ", 'O', new ItemStack(Items.spider_eye), '/', new ItemStack(Items.stick));
		GameRegistry.addRecipe(new ItemStack(Items.string, 9), "#", '#', new ItemStack(ModBlocks.blockSilk));
		GameRegistry.addRecipe(new ItemStack(Items.dye, 1, 15), "#", '#', new ItemStack(ModItems.erebusMaterials, 1, 2));
		GameRegistry.addRecipe(new ItemStack(Items.dye, 6, 15), "#", '#', new ItemStack(ModItems.fossilClub, 1, 0));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', new ItemStack(Items.feather, 1, 0), 'S', new ItemStack(Items.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Items.stick, 1, 0), 'T', new ItemStack(ModItems.erebusMaterials, 1, 2));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', new ItemStack(ModItems.erebusMaterials, 1, 6), 'S', new ItemStack(Items.stick, 1, 0), 'T', new ItemStack(Items.flint, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModItems.sprayCan, 9), " B ", "XRX", "XXX", 'X', Items.iron_ingot, 'B', Blocks.wooden_button, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.repellent.ordinal()));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wandOfAnimation, 1), "  N", " S ", "D  ", 'D', "gemDiamond", 'S', Items.stick, 'N', Items.nether_star));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusAltar, 1), "XXX", "XOX", "XXX", 'O', Blocks.obsidian, 'X', new ItemStack(ModItems.erebusMaterials, 1, 15));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowingJar, 1), "XXX", "GBG", "GGG", 'X', Items.iron_ingot, 'G', new ItemStack(ModBlocks.blockAmber, 1, 1), 'B', new ItemStack(ModItems.erebusMaterials, 1, 13));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()), new ItemStack(ModItems.erebusMaterials, 16, DATA.reinforcedPlateExo.ordinal()));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.book, 1, 0), new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 4), new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()));
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
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.mucusCharge.ordinal()), "SSS", "SRS", "SSS", 'S', Items.slime_ball, 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.repellent.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mucusBomb, 1), "MMM", "MTM", "MMM", 'M', new ItemStack(ModItems.erebusMaterials, 1, DATA.mucusCharge.ordinal()), 'T', Blocks.tnt);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyCombBlock, 1), "NPN", "PCP", "NPN", 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.papyrus.ordinal()), 'C', Blocks.chest, 'N', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()));
		GameRegistry.addRecipe(new ItemStack(Items.blaze_powder, 1), "FFF", "FFF", "FFF", 'F', new ItemStack(ModBlocks.erebusPlantSmall, 1, 13));
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusMaterials, 1, DATA.mossBall.ordinal()), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.erebusWallPlants, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.scorchedPlanks, 4), ModBlocks.scorchedWood);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.rottenPlanks, 2), ModBlocks.rottenWood);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ritualDagger), "  x", " y ", "z  ", 'x', "ingotGold", 'y', "gemJade", 'z', "stickWood"));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.glowshroom, 1), new ItemStack(ModItems.erebusMaterials, 1, DATA.yellowDottedFungus.ordinal()), Blocks.torch);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.glowshroom, 1), new ItemStack(ModItems.erebusMaterials, 1, DATA.yellowDottedFungus.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.bioLuminescence.ordinal()));
        GameRegistry.addRecipe(new ItemStack(ModItems.gaeanGem, 1), "VSE", "VSS", "GVV", 'V', Blocks.vine, 'S', Items.stick, 'E', ModItems.gaeanGem, 'G', Items.gold_ingot);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.gaeanKeystone, 1), "V V", "SOS", "SSS", 'V', Blocks.vine, 'S', Blocks.stonebrick, 'O', Blocks.obsidian);

		// Whetstone Sharpening Enchanting Stuff
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.whetstone, 1, 0), "SSS", "PPP", "UUU", 'S', Blocks.sand, 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.itemPetrifiedWood.ordinal()), 'U', "stoneUmber"));

		// Sharp Swords
        for (Item aSwordType : swordType) {
            for (int j = 0; j < 6; j++) {
                ItemStack swordSharp = new ItemStack(aSwordType);
                ItemStack stoneLevel = new ItemStack(ModItems.whetstone, 1, j);
                if (stoneLevel.getItemDamage() > 0) {
                    swordSharp.addEnchantment(Enchantment.sharpness, stoneLevel.getItemDamage());
                    GameRegistry.addShapelessRecipe(swordSharp, new ItemStack(ModItems.whetstone, 1, stoneLevel.getItemDamage()), new ItemStack(aSwordType));
                }
            }
        }

		// Sharp Axes
        for (Item anAxeType : axeType) {
            for (int j = 0; j < 6; j++) {
                ItemStack axeSharp = new ItemStack(anAxeType);
                ItemStack stoneLevel = new ItemStack(ModItems.whetstone, 1, j);
                if (stoneLevel.getItemDamage() > 0) {
                    axeSharp.addEnchantment(Enchantment.sharpness, stoneLevel.getItemDamage() + 1);
                    GameRegistry.addShapelessRecipe(axeSharp, new ItemStack(ModItems.whetstone, 1, stoneLevel.getItemDamage()), new ItemStack(anAxeType));
                }
            }
        }

		// Special Items - for future expansion
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataRhinoRidingKit), " SX", "CCC", "LLL", 'S', Items.string, 'X', new ItemStack(ModItems.erebusMaterials, 1, DATA.plateExo.ordinal()), 'C', new ItemStack(Blocks.carpet, 1, 0), 'L', new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataBeetleTamingAmulet), " N ", "NJN", " F ", 'N', Items.gold_nugget, 'J', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'F', new ItemStack(ModItems.erebusMaterials, 1, DATA.altarFragment.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.beeTamingAmulet, 1), " n ", "nJn", " N ", 'n', Items.gold_nugget, 'J', new ItemStack(ModItems.erebusMaterials, 1, DATA.jade.ordinal()), 'N', new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()));

		//Umbergolem parts
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemCore), "AAA", "ARA", "AAA", 'A', new ItemStack(ModItems.erebusMaterials, 1, DATA.altarFragment.ordinal()), 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.redGem.ordinal()));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemHead), "SSS", "SHS", "SSS", 'S', "stone", 'H', new ItemStack(ModItems.reinCompoundGoggles, 1)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemClaw), "  P", "  S", " SS", 'S', "stone", 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal())));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemClaw), "SSP", "S  ", "   ", 'S', "stone", 'P', new ItemStack(ModItems.erebusMaterials, 1, DATA.scorpionPincer.ordinal())));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLegs), "SSS", "S S", "R R", 'S', "stone", 'R', new ItemStack(ModItems.erebusMaterials, 1, DATA.reinforcedPlateExo.ordinal())));

		// Umbergolem Statue
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberGolemStatue, 1), " H ", "LCR", " X ", 'H', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemHead), 'L', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemClaw), 'C', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemCore), 'R', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemClaw), 'X', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLegs));

	}

	private static void registerSmelting() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockAmber, 1, 1), new ItemStack(ModBlocks.blockAmber), 0.3F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaRaw.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.grasshopperLegRaw.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.grasshopperLegCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.tarantulaLegRaw.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.tarantulaLegCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberstone, 1, 1), new ItemStack(ModBlocks.umberstone), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 0), new ItemStack(Items.coal, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 1), new ItemStack(Items.iron_ingot), 0.7F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 2), new ItemStack(Items.gold_ingot), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 3), new ItemStack(Items.dye, 1, 4), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 4), new ItemStack(Items.diamond), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 5), new ItemStack(Items.emerald), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusMaterials, 1, 1), new ItemStack(ModBlocks.umberOreBlock, 1, 6), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.mud), new ItemStack(ModItems.erebusMaterials, 1, DATA.mudBrick.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusMaterials, 1, DATA.honeyDrip.ordinal()), new ItemStack(ModItems.erebusMaterials, 1, DATA.nectar.ordinal()), 0.2F);
		if (ConfigHandler.lead)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.erebusOreExtra, 1, 2), new ItemStack(ModItems.metalIngot, 1, 1), 1.0F);
		if (ConfigHandler.silver)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.erebusOreExtra, 1, 3), new ItemStack(ModItems.metalIngot, 1, 2), 1.0F);
		if (ConfigHandler.copper)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.erebusOreExtra, 1, 1), new ItemStack(ModItems.metalIngot, 1, 0), 1.0F);
		if (ConfigHandler.tin)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.erebusOreExtra, 1, 4), new ItemStack(ModItems.metalIngot, 1, 3), 1.0F);
	}

	private static void registerOreDictionary() {
		OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("stone", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("stoneUmber", new ItemStack(ModBlocks.umberstone));
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