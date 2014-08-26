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
import erebus.core.handler.configs.ConfigHandler;
import erebus.item.ErebusFood;
import erebus.item.ErebusMaterial;
import erebus.item.ErebusSpecial;
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
		GameRegistry.addRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), "xx", "xx", 'x', ErebusMaterial.createStack(ErebusMaterial.DATA.itemPetrifiedWood));
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

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.wall, 6), "###", "###", '#', "stoneUmber"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 1), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 2), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 3), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 4), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 5), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 6), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 7), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wall, 6, 8), "###", "###", '#', new ItemStack(ModBlocks.blockAmber, 1, 2));
		GameRegistry.addRecipe(new ItemStack(ModItems.doorAmberItem, 3), "##", "##", "##", '#', new ItemStack(ModBlocks.blockAmber, 1, 2));

		// Jade tools
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePickaxe, 1), "XXX", " # ", " # ", '#', Items.stick, 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeShovel, 1), "X", "#", "#", '#', Items.stick, 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeAxe, 1), "XX", "X#", " #", '#', Items.stick, 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHoe, 1), "XX", " #", " #", '#', Items.stick, 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeSword, 1), "X", "X", "#", '#', Items.stick, 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadePaxel, 1), "XXX", "XSX", "XSX", 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.jade), 'S', Items.stick);
		GameRegistry.addRecipe(new RecipePaxel());

		// Jade armor
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeHelmet, 1), "###", "# #", '#', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBody, 1), "# #", "###", "###", '#', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeLegs, 1), "###", "# #", "# #", '#', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		GameRegistry.addRecipe(new ItemStack(ModItems.jadeBoots, 1), "# #", "# #", '#', ErebusMaterial.createStack(ErebusMaterial.DATA.jade));

		// Exoskeleton armor
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), "sss", "s s", "   ", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.plateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBody, 1), "s s", "sss", "sss", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.plateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), "sss", "s s", "s s", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.plateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), "   ", "s s", "s s", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.plateExo));

		GameRegistry.addRecipe(ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo), "sss", "sss", "sss", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.plateExo));

		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonHelmet, 1), "sss", "s s", "   ", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBody, 1), "s s", "sss", "sss", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonLegs, 1), "sss", "s s", "s s", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinExoskeletonBoots, 1), "   ", "s s", "s s", 's', ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo));

		// Special armor
		GameRegistry.addRecipe(ErebusMaterial.createStack(ErebusMaterial.DATA.compoundLens), "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.blockAmber, 1, 1), 'G', ErebusMaterial.createStack(ErebusMaterial.DATA.compoundEyes));
		GameRegistry.addRecipe(new ItemStack(ModItems.compoundGoggles, 1), "XXX", "OXO", "   ", 'O', ErebusMaterial.createStack(ErebusMaterial.DATA.compoundLens), 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.plateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.reinCompoundGoggles, 1), "XXX", "XOX", "   ", 'O', new ItemStack(ModItems.compoundGoggles, 1), 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo));
		GameRegistry.addRecipe(new ItemStack(ModItems.jumpBoots), "F F", "BXB", "B B", 'F', ErebusMaterial.createStack(ErebusMaterial.DATA.flyWing), 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.elasticFibre), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.sprintLeggings), "BBB", "BXB", "BBB", 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bioVelocity), 'X', new ItemStack(ModItems.reinExoskeletonLegs, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGlider), "   ", "GXG", "   ", 'G', ErebusMaterial.createStack(ErebusMaterial.DATA.gliderWing), 'X', new ItemStack(ModItems.reinExoskeletonBody, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.armorGliderPowered), "W W", "ECE", " V ", 'W', ErebusMaterial.createStack(ErebusMaterial.DATA.enhancedGliderWing), 'E', ErebusMaterial.createStack(ErebusMaterial.DATA.elasticFibre), 'C', new ItemStack(ModItems.armorGlider, 1), 'V', new ItemStack(ModBlocks.velocityBlock, 1));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.scorpionPincer), "I I", "XIX", "XPX", 'I', "ingotIron", 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo), 'P', ErebusMaterial.createStack(ErebusMaterial.DATA.scorpionPincer)));
		GameRegistry.addRecipe(new ItemStack(ModItems.rolledNewspaper), "PWP", "PIP", "PWP", 'I', new ItemStack(Items.dye, 1, 0), 'P', ErebusMaterial.createStack(ErebusMaterial.DATA.papyrus), 'W', ErebusMaterial.createStack(ErebusMaterial.DATA.whetstonePowder));
		GameRegistry.addRecipe(ErebusMaterial.createStack(ErebusMaterial.DATA.gliderWing), "SSS", "FFF", "FFF", 'S', Items.stick, 'F', ErebusMaterial.createStack(ErebusMaterial.DATA.flyWing));
		GameRegistry.addRecipe(ErebusMaterial.createStack(ErebusMaterial.DATA.enhancedGliderWing), "BBB", "WWW", "WWW", 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo), 'W', ErebusMaterial.createStack(ErebusMaterial.DATA.dragonflyWing));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.waspDagger), ErebusMaterial.createStack(ErebusMaterial.DATA.waspSting), new ItemStack(Items.stick));
		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());
		GameRegistry.addRecipe(new RecipeWhetstoneUpgrades());

		// Red Gem
		GameRegistry.addShapelessRecipe(new ItemStack(Items.redstone, 2, 0), ErebusMaterial.createStack(ErebusMaterial.DATA.redGem));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 0), "##", "##", '#', ErebusMaterial.createStack(ErebusMaterial.DATA.redGem));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.redGem, 1, 1), " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', new ItemStack(Items.stick, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowGemBlock, 3, 0), "BBB", "BGB", "BBB", 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bioLuminescence), 'G', ErebusMaterial.createStack(ErebusMaterial.DATA.redGem));

		// Bamboo
		GameRegistry.addRecipe(new ItemStack(ModItems.bambucket, 1, 0), " S ", "B B", " B ", 'S', Items.string, 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModItems.bambucket, 1, 3), "RRR", "RBR", "RRR", 'B', new ItemStack(ModItems.bambucket, 1, 0), 'R', ErebusMaterial.createStack(ErebusMaterial.DATA.honeyDrip));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), "##", "##", '#', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooCrate), "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), 'b', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooLadder, 1), "BBB", "S S", "BBB", 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo), 'S', Items.string);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooTorch, 4), "C", "B", "B", 'C', Items.coal, 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooBridge, 3), "SSS", "B B", "LLL", 'S', Items.string, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.bambooPole, 4), " S ", " B ", " B ", 'S', Items.slime_ball, 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.extenderThingy, 1), "BSB", "PDP", "BRB", 'S', Items.string, 'R', Items.redstone, 'D', Blocks.dispenser, 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo), 'P', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()));

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.bambooSoup.ordinal()), new ItemStack(Items.bowl), ErebusMaterial.createStack(ErebusMaterial.DATA.bamboo), ErebusMaterial.createStack(ErebusMaterial.DATA.bambooShoot));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.melonade.ordinal()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.melon));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.melonadeSparkly.ordinal()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.speckled_melon));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaeOnStick.ordinal()), new ItemStack(Items.stick), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaCooked.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaCooked.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaCooked.ordinal()));
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusFood, 2, ErebusFood.FoodType.honeySandwich.ordinal()), " B ", "RRR", " B ", 'B', new ItemStack(Items.bread), 'R', ErebusMaterial.createStack(ErebusMaterial.DATA.honeyDrip));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyTreat, 1), "SRS", "RBR", "SRS", 'S', new ItemStack(Items.sugar), 'B', new ItemStack(Items.bread), 'R', ErebusMaterial.createStack(ErebusMaterial.DATA.honeyDrip));

		// Miscellaneous
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'x', Items.clay_ball, 'y', ErebusMaterial.createStack(ErebusMaterial.DATA.mudBrick));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'y', Items.clay_ball, 'x', ErebusMaterial.createStack(ErebusMaterial.DATA.mudBrick));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'x', Blocks.clay, 'y', ModBlocks.mudBricks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'y', Blocks.clay, 'x', ModBlocks.mudBricks);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSilk, 1), "sss", "sss", "sss", 's', Items.string);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockAmber, 4, 2), "ss", "ss", 's', new ItemStack(ModBlocks.blockAmber, 1, 0));
		GameRegistry.addRecipe(new ItemStack(Items.string, 9), "#", '#', new ItemStack(ModBlocks.blockSilk));
		GameRegistry.addRecipe(new ItemStack(Items.dye, 1, 15), "#", '#', ErebusMaterial.createStack(ErebusMaterial.DATA.shardBone));
		GameRegistry.addRecipe(new ItemStack(Items.dye, 6, 15), "#", '#', new ItemStack(ModItems.fossilClub, 1, 0));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', new ItemStack(Items.feather, 1, 0), 'S', new ItemStack(Items.stick, 1, 0), 'T', ErebusMaterial.createStack(ErebusMaterial.DATA.shardBone));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', ErebusMaterial.createStack(ErebusMaterial.DATA.flyWing), 'S', new ItemStack(Items.stick, 1, 0), 'T', ErebusMaterial.createStack(ErebusMaterial.DATA.shardBone));
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', ErebusMaterial.createStack(ErebusMaterial.DATA.flyWing), 'S', new ItemStack(Items.stick, 1, 0), 'T', new ItemStack(Items.flint, 1, 0));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sprayCan, 9), " B ", "XRX", "XXX", 'X', "ingotIron", 'B', Blocks.wooden_button, 'R', ErebusMaterial.createStack(ErebusMaterial.DATA.repellent)));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.erebusAltar, 1), "XXX", "XOX", "XXX", 'O', Blocks.obsidian, 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.altarFragment));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.glowingJar, 1), "XXX", "GBG", "GGG", 'X', "ingotIron", 'G', new ItemStack(ModBlocks.blockAmber, 1, 1), 'B', ErebusMaterial.createStack(ErebusMaterial.DATA.bioLuminescence)));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo), ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo), ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo), ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.book, 1, 0), ErebusMaterial.createStack(ErebusMaterial.DATA.plateExo), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 4), ErebusMaterial.createStack(ErebusMaterial.DATA.papyrus), ErebusMaterial.createStack(ErebusMaterial.DATA.papyrus));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.velocityBlock), "xxx", "xxx", "xxx", 'x', ErebusMaterial.createStack(ErebusMaterial.DATA.bioVelocity));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mudBricks), "xx", "xx", 'x', ErebusMaterial.createStack(ErebusMaterial.DATA.mudBrick));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.homingBeecon), "GNG", "NCN", "GNG", 'N', ErebusMaterial.createStack(ErebusMaterial.DATA.nectar), 'G', "ingotGold", 'C', Items.compass));
		GameRegistry.addRecipe(new ItemStack(ModItems.nectarCollector), "  B", " S ", "S  ", 'B', Items.bowl, 'S', Items.stick);
		ItemStack diamondPick = new ItemStack(Items.diamond_pickaxe);
		diamondPick.addEnchantment(Enchantment.silkTouch, 1);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.blockExtractor), "  P", " D ", "C  ", 'P', ErebusMaterial.createStack(ErebusMaterial.DATA.scorpionPincer), 'D', diamondPick, 'C', Blocks.chest));
		GameRegistry.addRecipe(new ItemStack(ModItems.bucketHoney), "RRR", "RBR", "RRR", 'R', ErebusMaterial.createStack(ErebusMaterial.DATA.honeyDrip), 'B', Items.bucket);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.jarOHoney), "%%%", "$0$", "$$$", '%', "ingotIron", '$', new ItemStack(ModBlocks.blockAmber, 1, 1), '0', ErebusMaterial.createStack(ErebusMaterial.DATA.nectar)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.jadeBlock), "xxx", "xxx", "xxx", 'x', "gemJade"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ErebusMaterial.createStack(ErebusMaterial.DATA.jade, 9), "blockJade"));
		GameRegistry.addRecipe(ErebusMaterial.createStack(ErebusMaterial.DATA.mucusCharge), "SSS", "SRS", "SSS", 'S', Items.slime_ball, 'R', ErebusMaterial.createStack(ErebusMaterial.DATA.repellent));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.mucusBomb, 1), "MMM", "MTM", "MMM", 'M', ErebusMaterial.createStack(ErebusMaterial.DATA.mucusCharge), 'T', Blocks.tnt);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.honeyCombBlock, 1), "NPN", "PCP", "NPN", 'P', ErebusMaterial.createStack(ErebusMaterial.DATA.papyrus), 'C', Blocks.chest, 'N', ErebusMaterial.createStack(ErebusMaterial.DATA.nectar));
		GameRegistry.addRecipe(new ItemStack(Items.blaze_powder, 1), "FFF", "FFF", "FFF", 'F', new ItemStack(ModBlocks.plantSmall, 1, 13));
		GameRegistry.addRecipe(ErebusMaterial.createStack(ErebusMaterial.DATA.mossBall), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.wallPlants, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.scorchedPlanks, 4), ModBlocks.scorchedWood);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.rottenPlanks, 2), ModBlocks.rottenWood);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), ErebusMaterial.createStack(ErebusMaterial.DATA.yellowDottedFungus), Blocks.torch);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), ErebusMaterial.createStack(ErebusMaterial.DATA.yellowDottedFungus), ErebusMaterial.createStack(ErebusMaterial.DATA.bioLuminescence));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.portalActivator), "VSE", "VSS", "GVV", 'V', Blocks.vine, 'S', Items.stick, 'E', ErebusMaterial.createStack(ErebusMaterial.DATA.gaeanGem), 'G', "ingotGold"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.gaeanKeystone), "V V", "SOS", "SSS", 'V', Blocks.vine, 'S', Blocks.stonebrick, 'O', Blocks.obsidian);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.antTamingAmulet), "pgp", "gog", "pgp", 'p', ErebusMaterial.createStack(ErebusMaterial.DATA.antPheromones), 'g', "ingotGold", 'o', Blocks.obsidian));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.planticide, 2), ErebusMaterial.createStack(ErebusMaterial.DATA.poisonGland), Items.slime_ball, new ItemStack(Items.dye, 1, 15));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", ErebusMaterial.createStack(ErebusMaterial.DATA.sapBall), ErebusMaterial.createStack(ErebusMaterial.DATA.repellent), ErebusMaterial.createStack(ErebusMaterial.DATA.camoPowder)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", "slimeball", ErebusMaterial.createStack(ErebusMaterial.DATA.repellent), ErebusMaterial.createStack(ErebusMaterial.DATA.camoPowder)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeGreen", 'z', "gemEmerald"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeLime", 'z', "gemEmerald"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.siloSupports), "xxx", "y y", "y y", 'x', "slabWood", 'y', Blocks.fence));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.siloTank), "xzx", "ywy", "xzx", 'x', "ingotIron", 'y', "blockIron", 'z', ModBlocks.varnishedPlanks, 'w', ModBlocks.petrifiedWoodChest));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.siloRoof), " x ", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', ModBlocks.petrifiedWoodPlanks);

		// Whetstone Sharpening Enchanting Stuff
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bucketAntiVenom), ModItems.bucketBeetleJuice, ErebusMaterial.createStack(ErebusMaterial.DATA.poisonGland), ErebusMaterial.createStack(ErebusMaterial.DATA.nettleleaves), ErebusMaterial.createStack(ErebusMaterial.DATA.nettleleaves));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bambucketAntiVenom), ModItems.bambucketBeetleJuice, ErebusMaterial.createStack(ErebusMaterial.DATA.poisonGland), ErebusMaterial.createStack(ErebusMaterial.DATA.nettleleaves), ErebusMaterial.createStack(ErebusMaterial.DATA.nettleleaves));
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
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataRhinoRidingKit), " SX", "CCC", "LLL", 'S', Items.string, 'X', ErebusMaterial.createStack(ErebusMaterial.DATA.plateExo), 'C', new ItemStack(Blocks.carpet, 1, 0), 'L', new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataBeetleTamingAmulet), " N ", "NJN", " F ", 'N', Items.gold_nugget, 'J', ErebusMaterial.createStack(ErebusMaterial.DATA.jade), 'F', ErebusMaterial.createStack(ErebusMaterial.DATA.altarFragment));
		GameRegistry.addRecipe(new ItemStack(ModItems.beeTamingAmulet, 1), " n ", "nJn", " N ", 'n', Items.gold_nugget, 'J', ErebusMaterial.createStack(ErebusMaterial.DATA.jade), 'N', ErebusMaterial.createStack(ErebusMaterial.DATA.nectar));

		// Umbergolem parts
		GameRegistry.addRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemCore), "AAA", "ARA", "AAA", 'A', ErebusMaterial.createStack(ErebusMaterial.DATA.altarFragment), 'R', ErebusMaterial.createStack(ErebusMaterial.DATA.redGem));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemHead), "SSS", "SHS", "SSS", 'S', "stone", 'H', new ItemStack(ModItems.reinCompoundGoggles, 1)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemClaw), "  P", "  S", " SS", 'S', "stone", 'P', ErebusMaterial.createStack(ErebusMaterial.DATA.scorpionPincer)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemClaw), "SSP", "S  ", "   ", 'S', "stone", 'P', ErebusMaterial.createStack(ErebusMaterial.DATA.scorpionPincer)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLegs), "SSS", "S S", "R R", 'S', "stone", 'R', ErebusMaterial.createStack(ErebusMaterial.DATA.reinforcedPlateExo)));

		// Umbergolem Statue
		GameRegistry.addRecipe(new ItemStack(ModBlocks.umberGolemStatue, 1), " H ", "LCR", " X ", 'H', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemHead), 'L', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemClaw), 'C', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemCore), 'R', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemClaw), 'X', new ItemStack(ModItems.erebusSpecialItem, 1, ErebusSpecial.dataGolemLegs));

		// Animation Magic
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wandOfAnimation), " xy", " zx", "x  ", 'x', "ingotGold", 'y', ModItems.soulCrystal, 'z', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ritualDagger), "  x", " y ", "z  ", 'x', "ingotGold", 'y', "gemJade", 'z', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.stoneHeart), " x ", "yzx", "xxy", 'x', "stone", 'y', Blocks.obsidian, 'z', ErebusMaterial.createStack(ErebusMaterial.DATA.crimsonHeart)));
	}

	private static void registerSmelting()
	{
		GameRegistry.addSmelting(new ItemStack(ModBlocks.blockAmber, 1, 1), new ItemStack(ModBlocks.blockAmber), 0.3F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaRaw.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.larvaCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.grasshopperLegRaw.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.grasshopperLegCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.tarantulaLegRaw.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.tarantulaLegCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.titanChop.ordinal()), new ItemStack(ModItems.erebusFood, 1, ErebusFood.FoodType.titanChopCooked.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberstone, 1, 1), new ItemStack(ModBlocks.umberstone), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 0), new ItemStack(Items.coal, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 1), new ItemStack(Items.iron_ingot), 0.7F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 2), new ItemStack(Items.gold_ingot), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 3), new ItemStack(Items.dye, 1, 4), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 4), new ItemStack(Items.diamond), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 5), new ItemStack(Items.emerald), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberOreBlock, 1, 6), ErebusMaterial.createStack(ErebusMaterial.DATA.jade), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.mud), ErebusMaterial.createStack(ErebusMaterial.DATA.mudBrick), 0.2F);
		GameRegistry.addSmelting(ErebusMaterial.createStack(ErebusMaterial.DATA.honeyDrip), ErebusMaterial.createStack(ErebusMaterial.DATA.nectar), 0.2F);
		if (ConfigHandler.INSTANCE.lead)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreExtra, 1, 2), new ItemStack(ModItems.metalIngot, 1, 1), 1.0F);
		}
		if (ConfigHandler.INSTANCE.silver)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreExtra, 1, 3), new ItemStack(ModItems.metalIngot, 1, 2), 1.0F);
		}
		if (ConfigHandler.INSTANCE.copper)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreExtra, 1, 1), new ItemStack(ModItems.metalIngot, 1, 0), 1.0F);
		}
		if (ConfigHandler.INSTANCE.tin)
		{
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreExtra, 1, 4), new ItemStack(ModItems.metalIngot, 1, 3), 1.0F);
		}
	}

	private static void registerOreDictionary()
	{
		OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("stone", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("stoneUmber", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planks, 1, OreDictionary.WILDCARD_VALUE));
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
		OreDictionary.registerOre("gemJade", ErebusMaterial.createStack(ErebusMaterial.DATA.jade));
		OreDictionary.registerOre("blockJade", new ItemStack(ModBlocks.jadeBlock));
		OreDictionary.registerOre("blockSpawner", ModBlocks.spiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.jumpingSpiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.waspSpawner);
		OreDictionary.registerOre("gemDiamond", ModItems.encrustedDiamond);
		OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.blockAmber, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.saplessLog));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.rottenWood));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.scorchedWood));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.varnishedPlanks));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.scorchedPlanks));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.rottenPlanks));

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
			OreDictionary.registerOre("ingotLead", new ItemStack(ModItems.metalIngot, 1, 1));
			OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.oreExtra, 1, 2));
		}
		if (ConfigHandler.INSTANCE.silver)
		{
			OreDictionary.registerOre("ingotSilver", new ItemStack(ModItems.metalIngot, 1, 2));
			OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.oreExtra, 1, 3));
		}
		if (ConfigHandler.INSTANCE.copper)
		{
			OreDictionary.registerOre("ingotCopper", new ItemStack(ModItems.metalIngot, 1, 0));
			OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.oreExtra, 1, 1));
		}
		if (ConfigHandler.INSTANCE.tin)
		{
			OreDictionary.registerOre("ingotTin", new ItemStack(ModItems.metalIngot, 1, 3));
			OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.oreExtra, 1, 4));
		}
		if (ConfigHandler.INSTANCE.aluminium)
		{
			OreDictionary.registerOre("oreAluminum", new ItemStack(ModBlocks.oreExtra, 1, 0));
		}
	}
}