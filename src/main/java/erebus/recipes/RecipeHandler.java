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
import erebus.block.BlockSlabStone;
import erebus.block.plants.BlockErebusFlower.FLOWER_TYPE;
import erebus.core.handler.configs.ConfigHandler;
import erebus.item.Food;
import erebus.item.Materials;
import erebus.lib.EnumWood;

public class RecipeHandler
{

	public static Item[] swordType = new Item[] { Items.wooden_sword, Items.stone_sword, Items.iron_sword, Items.golden_sword, Items.diamond_sword, ModItems.jadeSword, ModItems.scorpionPincer, ModItems.waspSword };
	public static Item[] axeType = new Item[] { Items.wooden_axe, Items.stone_axe, Items.iron_axe, Items.golden_axe, Items.diamond_axe, ModItems.jadeAxe };

	public static void init()
	{
		EnumWood.initRecipes();

		registerOreDictionary();
		registerRecipes();
		registerSmelting();
	}

	private static void addSlabRecipe(BlockSlabStone block)
	{
		GameRegistry.addRecipe(new ItemStack(block, 6), "xxx", 'x', new ItemStack(block.base, 1, block.meta));
	}

	private static void registerRecipes()
	{
		// Wood
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.planks, 1, EnumWood.White.ordinal()), "plankWood", "dyeWhite"));

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
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), "xx", "xx", 'x', Materials.createStack(Materials.DATA.petrifiedWood));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedCraftingTable), "xx", "xx", 'x', ModBlocks.petrifiedWoodPlanks);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.petrifiedWoodChest), "xxx", "xyx", "xxx", 'x', ModBlocks.petrifiedWoodPlanks, 'y', "ingotGold"));

		// Stairs, slabs, walls
		for (int i = 0; i < ModBlocks.umbercobbleStairs.length; i++)
		{
			GameRegistry.addRecipe(new ItemStack(ModBlocks.umbercobbleStairs[i], 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.umberstone, 1, i));
		}
		GameRegistry.addRecipe(new ItemStack(ModBlocks.amberBrickStairs, 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodStairs, 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.petrifiedWoodPlanks, 1, 0));

		for (Block slab : ModBlocks.stoneSlabs)
		{
			addSlabRecipe((BlockSlabStone) slab);
		}
		for (int i = 0; i < ModBlocks.gneissStairs.length; i++)
		{
			GameRegistry.addRecipe(new ItemStack(ModBlocks.gneissStairs[i], 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.gneiss, 1, i));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.wall, 6), "###", "###", '#', "stoneUmber"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 1), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 2), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 3), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 4), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 5), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 6), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 7), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 8), "###", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2));

		// Doors
		GameRegistry.addRecipe(new ItemStack(ModItems.doorAmber, 3), "##", "##", "##", '#', new ItemStack(ModBlocks.blockAmber, 1, 2));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.doorBaobab, 3), "##", "##", "##", '#', "plank" + EnumWood.Baobab));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.doorEucalyptus, 3), "##", "##", "##", '#', "plank" + EnumWood.Eucalyptus));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.doorMahogany, 3), "##", "##", "##", '#', "plank" + EnumWood.Mahogany));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.doorMossbark, 3), "##", "##", "##", '#', "plank" + EnumWood.Mossbark));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.doorAsper, 3), "##", "##", "##", '#', "plank" + EnumWood.Asper));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.doorCypress, 3), "##", "##", "##", '#', "plank" + EnumWood.Cypress));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.doorPetrified, 3), "##", "##", "##", '#', "plankPetrified"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.doorScorched, 3), "##", "##", "##", '#', "plankScorched"));

		// Jade tools
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePickaxe, 1), "XXX", " # ", " # ", '#', Items.stick, 'X', Materials.createStack(Materials.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeShovel, 1), "X", "#", "#", '#', Items.stick, 'X', Materials.createStack(Materials.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeAxe, 1), "XX", "X#", " #", '#', Items.stick, 'X', Materials.createStack(Materials.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHoe, 1), "XX", " #", " #", '#', Items.stick, 'X', Materials.createStack(Materials.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeSword, 1), "X", "X", "#", '#', Items.stick, 'X', Materials.createStack(Materials.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePaxel, 1), "XXX", "XSX", "XSX", 'X', Materials.createStack(Materials.DATA.jade), 'S', Items.stick);
		GameRegistry.addRecipe(new RecipePaxel());

		// Jade armor
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHelmet, 1), "###", "# #", '#', Materials.createStack(Materials.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBody, 1), "# #", "###", "###", '#', Materials.createStack(Materials.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeLegs, 1), "###", "# #", "# #", '#', Materials.createStack(Materials.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBoots, 1), "# #", "# #", '#', Materials.createStack(Materials.DATA.jade));

		// Exoskeleton armor
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), "sss", "s s", "   ", 's', Materials.createStack(Materials.DATA.plateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBody, 1), "s s", "sss", "sss", 's', Materials.createStack(Materials.DATA.plateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), "sss", "s s", "s s", 's', Materials.createStack(Materials.DATA.plateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), "   ", "s s", "s s", 's', Materials.createStack(Materials.DATA.plateExo));

		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.reinforcedPlateExo), "sss", "sss", "sss", 's', Materials.createStack(Materials.DATA.plateExo));

		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonHelmet, 1), "sss", "s s", "   ", 's', Materials.createStack(Materials.DATA.reinforcedPlateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBody, 1), "s s", "sss", "sss", 's', Materials.createStack(Materials.DATA.reinforcedPlateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonLegs, 1), "sss", "s s", "s s", 's', Materials.createStack(Materials.DATA.reinforcedPlateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBoots, 1), "   ", "s s", "s s", 's', Materials.createStack(Materials.DATA.reinforcedPlateExo));

		// Special armor
		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.compoundLens), "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.blockAmber, 1, 1), 'G', Materials.createStack(Materials.DATA.compoundEyes));
		GameRegistry.addRecipe(new ItemStack(ModItems.compoundGoggles, 1), "XXX", "OXO", "   ", 'O', Materials.createStack(Materials.DATA.compoundLens), 'X', Materials.createStack(Materials.DATA.plateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinCompoundGoggles, 1), "XXX", "XOX", "   ", 'O', new ItemStack(ModItems.compoundGoggles, 1), 'X', Materials.createStack(Materials.DATA.reinforcedPlateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.jumpBoots), "F F", "BXB", "B B", 'F', Materials.createStack(Materials.DATA.flyWing), 'B', Materials.createStack(Materials.DATA.elasticFibre), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.sprintLeggings), "BBB", "BXB", "BBB", 'B', Materials.createStack(Materials.DATA.bioVelocity), 'X', new ItemStack(ModItems.reinExoskeletonLegs, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGlider), "   ", "GXG", "   ", 'G', Materials.createStack(Materials.DATA.gliderWing), 'X', new ItemStack(ModItems.reinExoskeletonBody, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGliderPowered), "W W", "ECE", " V ", 'W', Materials.createStack(Materials.DATA.enhancedGliderWing), 'E', Materials.createStack(Materials.DATA.elasticFibre), 'C', new ItemStack(ModItems.armorGlider, 1), 'V', new ItemStack(ModBlocks.velocityBlock, 1));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.scorpionPincer), "I I", "XIX", "XPX", 'I', "ingotIron", 'X', Materials.createStack(Materials.DATA.reinforcedPlateExo), 'P', Materials.createStack(Materials.DATA.scorpionPincer)));
		GameRegistry.addRecipe(new ItemStack(ModItems.rolledNewspaper), "PWP", "PIP", "PWP", 'I', new ItemStack(Items.dye, 1, 0), 'P', Materials.createStack(Materials.DATA.papyrus), 'W', Materials.createStack(Materials.DATA.whetstonePowder));
		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.gliderWing), "SSS", "FFF", "FFF", 'S', Items.stick, 'F', Materials.createStack(Materials.DATA.flyWing));
		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.enhancedGliderWing), "BBB", "WWW", "WWW", 'B', Materials.createStack(Materials.DATA.bamboo), 'W', Materials.createStack(Materials.DATA.dragonflyWing));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.waspDagger), Materials.createStack(Materials.DATA.waspSting), new ItemStack(Items.stick));
		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());
		GameRegistry.addRecipe(new RecipeWhetstoneUpgrades());

		// Mushroom Helm & Mushroom Blocks
		GameRegistry.addRecipe(new ItemStack(ModItems.mushroomHelm, 1), "mmm", "mpm", "   ", 'm', Materials.createStack(Materials.DATA.hideShroom), 'p', new ItemStack(Blocks.pumpkin));

		GameRegistry.addRecipe(new ItemStack(ModBlocks.mushroomCap0), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.bulbCappedShroom, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mushroomCap1), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.kaizerfinger, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mushroomCap2), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.bundleshroom, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mushroomCap3), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.greenMushroom));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mushroomCap4), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.dutchCap, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.red_mushroom_block), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.red_mushroom, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.brown_mushroom_block), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.brown_mushroom, 1));

		// Red Gem
		GameRegistry.addShapelessRecipe(new ItemStack(Items.redstone, 2, 0), Materials.createStack(Materials.DATA.redGem));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 0), "##", "##", '#', Materials.createStack(Materials.DATA.redGem));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 1), " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', new ItemStack(Items.stick, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowGemBlock, 3, 0), "BBB", "BGB", "BBB", 'B', Materials.createStack(Materials.DATA.bioLuminescence), 'G', Materials.createStack(Materials.DATA.redGem));

		// Bamboo
		GameRegistry.addRecipe(new ItemStack(ModItems.bambucket, 1, 0), " S ", "B B", " B ", 'S', Items.string, 'B', Materials.createStack(Materials.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModItems.bambucket, 1, 3), "RRR", "RBR", "RRR", 'B', new ItemStack(ModItems.bambucket, 1, 0), 'R', Materials.createStack(Materials.DATA.honeyDrip));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), "##", "##", '#', Materials.createStack(Materials.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooCrate), "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), 'b', Materials.createStack(Materials.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooLadder, 1), "BBB", "S S", "BBB", 'B', Materials.createStack(Materials.DATA.bamboo), 'S', Items.string);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooTorch, 4), "C", "B", "B", 'C', Items.coal, 'B', Materials.createStack(Materials.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooBridge, 3), "SSS", "B B", "LLL", 'S', Items.string, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', Materials.createStack(Materials.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooPole, 4), " S ", " B ", " B ", 'S', Items.slime_ball, 'B', Materials.createStack(Materials.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.extenderThingy, 1), "BSB", "PDP", "BRB", 'S', Items.string, 'R', Items.redstone, 'D', Blocks.dispenser, 'B', Materials.createStack(Materials.DATA.bamboo), 'P', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()));

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.food, 1, Food.FoodType.bambooSoup.ordinal()), new ItemStack(Items.bowl), Materials.createStack(Materials.DATA.bamboo), Materials.createStack(Materials.DATA.bambooShoot));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.food, 1, Food.FoodType.melonade.ordinal()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.melon));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.food, 1, Food.FoodType.melonadeSparkly.ordinal()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.speckled_melon));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.food, 1, Food.FoodType.larvaeOnStick.ordinal()), new ItemStack(Items.stick), new ItemStack(ModItems.food, 1, Food.FoodType.larvaCooked.ordinal()), new ItemStack(ModItems.food, 1, Food.FoodType.larvaCooked.ordinal()), new ItemStack(ModItems.food, 1, Food.FoodType.larvaCooked.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.food, 2, Food.FoodType.honeySandwich.ordinal()), " B ", "RRR", " B ", 'B', new ItemStack(Items.bread), 'R', Materials.createStack(Materials.DATA.honeyDrip));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyTreat, 1), "SRS", "RBR", "SRS", 'S', new ItemStack(Items.sugar), 'B', new ItemStack(Items.bread), 'R', Materials.createStack(Materials.DATA.honeyDrip));

		// Miscellaneous
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'x', Items.clay_ball, 'y', Materials.createStack(Materials.DATA.mudBrick));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'y', Items.clay_ball, 'x', Materials.createStack(Materials.DATA.mudBrick));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'x', Blocks.clay, 'y', ModBlocks.mudBricks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'y', Blocks.clay, 'x', ModBlocks.mudBricks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSilk, 1), "sss", "sss", "sss", 's', Items.string);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockAmber, 4, 2), "ss", "ss", 's', new ItemStack(ModBlocks.blockAmber, 1, 0));
		GameRegistry.addRecipe(new ItemStack(Items.string, 9), "#", '#', new ItemStack(ModBlocks.blockSilk));
		GameRegistry.addRecipe(new ItemStack(Items.dye, 1, 15), "#", '#', Materials.createStack(Materials.DATA.shardBone));
		GameRegistry.addRecipe(new ItemStack(Items.dye, 6, 15), "#", '#', new ItemStack(ModItems.fossilClub, 1, 0));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', new ItemStack(Items.feather, 1, 0), 'S', new ItemStack(Items.stick, 1, 0), 'T', Materials.createStack(Materials.DATA.shardBone));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', Materials.createStack(Materials.DATA.flyWing), 'S', new ItemStack(Items.stick, 1, 0), 'T', Materials.createStack(Materials.DATA.shardBone));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', Materials.createStack(Materials.DATA.flyWing), 'S', new ItemStack(Items.stick, 1, 0), 'T', new ItemStack(Items.flint, 1, 0));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sprayCan, 9), " B ", "XRX", "XXX", 'X', "ingotIron", 'B', Blocks.wooden_button, 'R', Materials.createStack(Materials.DATA.repellent)));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.altarBase, 1), "XXX", "XOX", "XXX", 'O', Blocks.obsidian, 'X', Materials.createStack(Materials.DATA.altarFragment));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.glowingJar, 1), "XXX", "GBG", "GGG", 'X', "ingotIron", 'G', new ItemStack(ModBlocks.blockAmber, 1, 1), 'B', Materials.createStack(Materials.DATA.bioLuminescence)));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), Materials.createStack(Materials.DATA.reinforcedPlateExo), Materials.createStack(Materials.DATA.reinforcedPlateExo), Materials.createStack(Materials.DATA.reinforcedPlateExo), Materials.createStack(Materials.DATA.reinforcedPlateExo));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.book, 1, 0), Materials.createStack(Materials.DATA.plateExo), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 4), Materials.createStack(Materials.DATA.papyrus), Materials.createStack(Materials.DATA.papyrus));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.velocityBlock), "xxx", "xxx", "xxx", 'x', Materials.createStack(Materials.DATA.bioVelocity));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mudBricks), "xx", "xx", 'x', Materials.createStack(Materials.DATA.mudBrick));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.homingBeecon), "GNG", "NCN", "GNG", 'N', Materials.createStack(Materials.DATA.nectar), 'G', "ingotGold", 'C', Items.compass));
		GameRegistry.addRecipe(new ItemStack(ModItems.nectarCollector), "  B", " S ", "S  ", 'B', Items.bowl, 'S', Items.stick);
		ItemStack diamondPick = new ItemStack(Items.diamond_pickaxe);
		diamondPick.addEnchantment(Enchantment.silkTouch, 1);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.blockExtractor), "  P", " D ", "C  ", 'P', Materials.createStack(Materials.DATA.scorpionPincer), 'D', diamondPick, 'C', Blocks.chest));
		GameRegistry.addRecipe(new ItemStack(ModItems.bucketHoney), "RRR", "RBR", "RRR", 'R', Materials.createStack(Materials.DATA.honeyDrip), 'B', Items.bucket);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.jarOHoney), "%%%", "$0$", "$$$", '%', "ingotIron", '$', new ItemStack(ModBlocks.blockAmber, 1, 1), '0', Materials.createStack(Materials.DATA.nectar)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.jadeBlock), "xxx", "xxx", "xxx", 'x', "gemJade"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(Materials.createStack(Materials.DATA.jade, 9), "blockJade"));
		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.mucusCharge), "SSS", "SRS", "SSS", 'S', Items.slime_ball, 'R', Materials.createStack(Materials.DATA.repellent));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mucusBomb, 1), "MMM", "MTM", "MMM", 'M', Materials.createStack(Materials.DATA.mucusCharge), 'T', Blocks.tnt);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyCombBlock, 1), "NPN", "PCP", "NPN", 'P', Materials.createStack(Materials.DATA.papyrus), 'C', Blocks.chest, 'N', Materials.createStack(Materials.DATA.nectar));
		GameRegistry.addRecipe(new ItemStack(Items.blaze_powder, 1), "FFF", "FFF", "FFF", 'F', new ItemStack(ModBlocks.fireBloom));
		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.mossBall), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.wallPlants, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.scorchedPlanks, 4), ModBlocks.scorchedWood);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), Materials.createStack(Materials.DATA.yellowDottedFungus), Blocks.torch);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), Materials.createStack(Materials.DATA.yellowDottedFungus), Materials.createStack(Materials.DATA.bioLuminescence));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.portalActivator), "VSE", "VSS", "GVV", 'V', Blocks.vine, 'S', Items.stick, 'E', Materials.createStack(Materials.DATA.gaeanGem), 'G', "ingotGold"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.gaeanKeystone), "V V", "SOS", "SSS", 'V', Blocks.vine, 'S', Blocks.stonebrick, 'O', Blocks.obsidian);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.antTamingAmulet), "pgp", "gog", "pgp", 'p', Materials.createStack(Materials.DATA.antPheromones), 'g', "ingotGold", 'o', Blocks.obsidian));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.planticide, 2), Materials.createStack(Materials.DATA.poisonGland), Items.slime_ball, new ItemStack(Items.dye, 1, 15));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", Materials.createStack(Materials.DATA.sapBall), Materials.createStack(Materials.DATA.repellent), Materials.createStack(Materials.DATA.camoPowder)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", "slimeball", Materials.createStack(Materials.DATA.repellent), Materials.createStack(Materials.DATA.camoPowder)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeGreen", 'z', "gemEmerald"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeLime", 'z', "gemEmerald"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.siloSupports), "xxx", "y y", "y y", 'x', "slabWood", 'y', Blocks.fence));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.siloTank), "xzx", "ywy", "xzx", 'x', "ingotIron", 'y', "blockIron", 'z', ModBlocks.varnishedPlanks, 'w', ModBlocks.petrifiedWoodChest));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.siloRoof), " x ", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', ModBlocks.petrifiedWoodPlanks);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.offeringAltar), "xwx", "yzy", "xyx", 'x', "stone", 'y', Blocks.stonebrick, 'z', Blocks.obsidian, 'w', "ingotGold"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.templeBrick), "xx", "xx", 'x', Materials.createStack(Materials.DATA.templeRock));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.templeTile, 4), "xx", "xx", 'x', new ItemStack(ModBlocks.templeBrick));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.templePillar, 2), "x", "x", 'x', new ItemStack(ModBlocks.templeTile));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.gneiss), "xx", "xx", 'x', Materials.createStack(Materials.DATA.gneissRock));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 2, 9), ModBlocks.waterFlower);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.craftingAltar), "xxx", "xxx", "xxx", 'x', ModBlocks.altarBase);

		// Whetstone Sharpening Enchanting Stuff
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bucketAntiVenom), ModItems.bucketBeetleJuice, Materials.createStack(Materials.DATA.poisonGland), Materials.createStack(Materials.DATA.nettleleaves), Materials.createStack(Materials.DATA.nettleleaves));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bambucketAntiVenom), ModItems.bambucketBeetleJuice, Materials.createStack(Materials.DATA.poisonGland), Materials.createStack(Materials.DATA.nettleleaves), Materials.createStack(Materials.DATA.nettleleaves));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), ModItems.bucketAntiVenom, Items.glass_bottle, Items.glass_bottle);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), ModItems.bambucketAntiVenom, Items.glass_bottle, Items.glass_bottle);

		// Sharp Swords
		for (Item aSwordType : swordType)
		{
			for (int j = 0; j < 6; j++)
			{
				ItemStack swordSharp = new ItemStack(aSwordType);
				ItemStack stoneLevel = new ItemStack(ModItems.whetstone, 1, j);
				if (stoneLevel.getItemDamage() > 0)
				{
					swordSharp.addEnchantment(Enchantment.sharpness, stoneLevel.getItemDamage());
					GameRegistry.addShapelessRecipe(swordSharp, new ItemStack(ModItems.whetstone, 1, stoneLevel.getItemDamage()), new ItemStack(aSwordType));
				}
			}
		}

		// Sharp Axes
		for (Item anAxeType : axeType)
		{
			for (int j = 0; j < 6; j++)
			{
				ItemStack axeSharp = new ItemStack(anAxeType);
				ItemStack stoneLevel = new ItemStack(ModItems.whetstone, 1, j);
				if (stoneLevel.getItemDamage() > 0)
				{
					axeSharp.addEnchantment(Enchantment.sharpness, stoneLevel.getItemDamage() + 1);
					GameRegistry.addShapelessRecipe(axeSharp, new ItemStack(ModItems.whetstone, 1, stoneLevel.getItemDamage()), new ItemStack(anAxeType));
				}
			}
		}

		// Special Items
		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.rhinoRidingKit), " SX", "CCC", "LLL", 'S', Items.string, 'X', Materials.createStack(Materials.DATA.plateExo), 'C', new ItemStack(Blocks.carpet, 1, 0), 'L', new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.beetleTamingAmulet), " N ", "NJN", " F ", 'N', Items.gold_nugget, 'J', Materials.createStack(Materials.DATA.jade), 'F', Materials.createStack(Materials.DATA.altarFragment));
		GameRegistry.addRecipe(new ItemStack(ModItems.beeTamingAmulet, 1), " n ", "nJn", " N ", 'n', Items.gold_nugget, 'J', Materials.createStack(Materials.DATA.jade), 'N', Materials.createStack(Materials.DATA.nectar));

		// Umbergolem parts
		GameRegistry.addRecipe(Materials.createStack(Materials.DATA.umberGolemCore), "AAA", "ARA", "AAA", 'A', Materials.createStack(Materials.DATA.altarFragment), 'R', Materials.createStack(Materials.DATA.redGem));
		GameRegistry.addRecipe(new ShapedOreRecipe(Materials.createStack(Materials.DATA.umberGolemHead), "SSS", "SHS", "SSS", 'S', "stone", 'H', new ItemStack(ModItems.reinCompoundGoggles, 1)));
		GameRegistry.addRecipe(new ShapedOreRecipe(Materials.createStack(Materials.DATA.umberGolemClaw), "  P", "  S", " SS", 'S', "stone", 'P', Materials.createStack(Materials.DATA.scorpionPincer)));
		GameRegistry.addRecipe(new ShapedOreRecipe(Materials.createStack(Materials.DATA.umberGolemClaw), "SSP", "S  ", "   ", 'S', "stone", 'P', Materials.createStack(Materials.DATA.scorpionPincer)));
		GameRegistry.addRecipe(new ShapedOreRecipe(Materials.createStack(Materials.DATA.umberGolemLegs), "SSS", "S S", "R R", 'S', "stone", 'R', Materials.createStack(Materials.DATA.reinforcedPlateExo)));

		// Animation Magic
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wandOfAnimation), " xy", " zx", "x  ", 'x', "ingotGold", 'y', ModItems.soulCrystal, 'z', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ritualDagger), "  x", " y ", "z  ", 'x', "ingotGold", 'y', "gemJade", 'z', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.stoneHeart), " x ", "yzx", "xxy", 'x', ModBlocks.templeTile, 'y', Blocks.obsidian, 'z', Materials.createStack(Materials.DATA.crimsonHeart)));
	}

	private static void registerSmelting()
	{
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockAmber, 1, 1), new ItemStack(ModBlocks.blockAmber), 0.3F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, Food.FoodType.larvaRaw.ordinal()), new ItemStack(ModItems.food, 1, Food.FoodType.larvaCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, Food.FoodType.grasshopperLegRaw.ordinal()), new ItemStack(ModItems.food, 1, Food.FoodType.grasshopperLegCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, Food.FoodType.tarantulaLegRaw.ordinal()), new ItemStack(ModItems.food, 1, Food.FoodType.tarantulaLegCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, Food.FoodType.titanChop.ordinal()), new ItemStack(ModItems.food, 1, Food.FoodType.titanChopCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberstone, 1, 1), new ItemStack(ModBlocks.umberstone), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreCoal), new ItemStack(Items.coal, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreIron), new ItemStack(Items.iron_ingot), 0.7F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreGold), new ItemStack(Items.gold_ingot), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreLapis), new ItemStack(Items.dye, 1, 4), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreDiamond), new ItemStack(Items.diamond), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreEmerald), new ItemStack(Items.emerald), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreJade), Materials.createStack(Materials.DATA.jade), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreFossil), Materials.createStack(Materials.DATA.shardBone), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreTemple), Materials.createStack(Materials.DATA.templeRock), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreGneiss), Materials.createStack(Materials.DATA.gneissRock), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.orePetrifiedWood), Materials.createStack(Materials.DATA.petrifiedWood), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreEncrustedDiamond), new ItemStack(ModItems.encrustedDiamond), 1.0F);

		GameRegistry.addSmelting(new ItemStack(ModBlocks.mud), Materials.createStack(Materials.DATA.mudBrick), 0.2F);
		GameRegistry.addSmelting(Materials.createStack(Materials.DATA.honeyDrip), Materials.createStack(Materials.DATA.nectar), 0.2F);
		if (ConfigHandler.INSTANCE.lead)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreLead), Materials.createStack(Materials.DATA.ingotLead), 1.0F);
		}
		if (ConfigHandler.INSTANCE.silver)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreSilver), Materials.createStack(Materials.DATA.ingotSilver), 1.0F);
		}
		if (ConfigHandler.INSTANCE.copper)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreCopper), Materials.createStack(Materials.DATA.ingotCopper), 1.0F);
		}
		if (ConfigHandler.INSTANCE.tin)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreTin), Materials.createStack(Materials.DATA.ingotTin), 1.0F);
		}
		if (ConfigHandler.INSTANCE.aluminium)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreAluminium), Materials.createStack(Materials.DATA.ingotAluminium), 1.0F);
		}
	}

	private static void registerOreDictionary()
	{
		OreDictionary.registerOre("oreCoal", new ItemStack(ModBlocks.oreCoal));
		OreDictionary.registerOre("oreIron", new ItemStack(ModBlocks.oreIron));
		OreDictionary.registerOre("oreGold", new ItemStack(ModBlocks.oreGold));
		OreDictionary.registerOre("oreLapis", new ItemStack(ModBlocks.oreLapis));
		OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.oreDiamond));
		OreDictionary.registerOre("oreEmerald", new ItemStack(ModBlocks.oreEmerald));
		OreDictionary.registerOre("oreJade", new ItemStack(ModBlocks.oreJade));
		OreDictionary.registerOre("orePetrifiedWood", new ItemStack(ModBlocks.orePetrifiedWood));
		OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.oreEncrustedDiamond));
		OreDictionary.registerOre("oreFossil", new ItemStack(ModBlocks.oreFossil));
		OreDictionary.registerOre("oreTemple", new ItemStack(ModBlocks.oreTemple));
		OreDictionary.registerOre("oreGneiss", new ItemStack(ModBlocks.oreGneiss));

		OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("stone", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("stoneUmber", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.spawnEggs, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemJade", Materials.createStack(Materials.DATA.jade));
		OreDictionary.registerOre("blockJade", new ItemStack(ModBlocks.jadeBlock));
		OreDictionary.registerOre("blockSpawner", ModBlocks.spiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.jumpingSpiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.waspSpawner);
		OreDictionary.registerOre("gemDiamond", ModItems.encrustedDiamond);
		OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.blockAmber, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.saplessLog));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.scorchedWood));

		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planks, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.varnishedPlanks));
		OreDictionary.registerOre("plankVarnished", new ItemStack(ModBlocks.varnishedPlanks));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.scorchedPlanks));
		OreDictionary.registerOre("plankScorched", new ItemStack(ModBlocks.scorchedPlanks));
		OreDictionary.registerOre("plankPetrified", new ItemStack(ModBlocks.petrifiedWoodPlanks));
		for (EnumWood wood : EnumWood.values())
		{
			OreDictionary.registerOre("plank" + wood, new ItemStack(ModBlocks.planks, 1, wood.ordinal()));
		}

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

		if (ConfigHandler.INSTANCE.lead)
		{
			OreDictionary.registerOre("ingotLead", Materials.createStack(Materials.DATA.ingotLead));
			OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.oreLead));
		}
		if (ConfigHandler.INSTANCE.silver)
		{
			OreDictionary.registerOre("ingotSilver", Materials.createStack(Materials.DATA.ingotSilver));
			OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.oreSilver));
		}
		if (ConfigHandler.INSTANCE.copper)
		{
			OreDictionary.registerOre("ingotCopper", Materials.createStack(Materials.DATA.ingotCopper));
			OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.oreCopper));
		}
		if (ConfigHandler.INSTANCE.tin)
		{
			OreDictionary.registerOre("ingotTin", Materials.createStack(Materials.DATA.ingotTin));
			OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.oreTin));
		}
		if (ConfigHandler.INSTANCE.aluminium)
		{
			OreDictionary.registerOre("ingotAluminium", Materials.createStack(Materials.DATA.ingotAluminium));
			OreDictionary.registerOre("oreAluminum", new ItemStack(ModBlocks.oreAluminium));
		}
	}
}