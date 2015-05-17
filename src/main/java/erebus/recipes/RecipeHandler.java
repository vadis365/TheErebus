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
import erebus.item.DungeonIdols;
import erebus.item.DungeonIdols.IDOL;
import erebus.item.Food;
import erebus.item.Materials;
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

	private static void registerRecipes() {
		// Wood
		addShapelessRecipe(new ItemStack(ModBlocks.planks, 1, EnumWood.White.ordinal()), "plankWood", "dyeWhite");

		// Umber stuff
		addShapedRecipe(new ItemStack(ModBlocks.umberPaver, 4, 0), "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 1));
		addShapedRecipe(new ItemStack(ModBlocks.umberPaver, 4, 1), "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 2));
		addShapedRecipe(new ItemStack(ModBlocks.umberPaver, 4, 2), "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 3));
		addShapedRecipe(new ItemStack(ModBlocks.umberFurnace, 1), "###", "#$#", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1), '$', Items.bucket);
		addShapedRecipe(new ItemStack(ModBlocks.umberstone, 4, 4), "##", "##", '#', "stoneUmber");
		addShapelessRecipe(new ItemStack(ModBlocks.umberstoneButton, 1, 0), new ItemStack(ModBlocks.umberstone, 1, 1));
		addShapedRecipe(new ItemStack(ModBlocks.umberstone, 9, 5), "###", "###", "###", '#', "stoneUmber");
		addShapedRecipe(new ItemStack(ModBlocks.umberstone, 4, 6), "##", "##", '#', new ItemStack(ModBlocks.umberstone, 1, 5));
		addShapedRecipe(new ItemStack(ModBlocks.umberstonePillar, 2), "#", "#", '#', "stoneUmber");

		// Petrified Wood stuffs
		addShapedRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), "xx", "xx", 'x', Materials.DATA.petrifiedWood.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.petrifiedCraftingTable), "xx", "xx", 'x', ModBlocks.petrifiedWoodPlanks);
		addShapedRecipe(new ItemStack(ModBlocks.petrifiedWoodChest), "xxx", "xyx", "xxx", 'x', ModBlocks.petrifiedWoodPlanks, 'y', "ingotGold");

		// Stairs, slabs, walls
		for (int i = 0; i < ModBlocks.umbercobbleStairs.length; i++)
			addShapedRecipe(new ItemStack(ModBlocks.umbercobbleStairs[i], 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.umberstone, 1, i));
		addShapedRecipe(new ItemStack(ModBlocks.amberBrickStairs, 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.amber, 1, 2));
		addShapedRecipe(new ItemStack(ModBlocks.petrifiedWoodStairs, 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.petrifiedWoodPlanks, 1, 0));

		for (Block slab : ModBlocks.stoneSlabs)
			addShapedRecipe(new ItemStack(slab, 6), "xxx", 'x', new ItemStack(((BlockSlabStone) slab).base, 1, ((BlockSlabStone) slab).meta));
		for (int i = 0; i < ModBlocks.gneissStairs.length; i++)
			addShapedRecipe(new ItemStack(ModBlocks.gneissStairs[i], 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.gneiss, 1, i));

		addShapedRecipe(new ItemStack(ModBlocks.wall, 6), "###", "###", '#', "stoneUmber");
		addShapedRecipe(new ItemStack(ModBlocks.wall, 6, 1), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 1));
		addShapedRecipe(new ItemStack(ModBlocks.wall, 6, 2), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 2));
		addShapedRecipe(new ItemStack(ModBlocks.wall, 6, 3), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 3));
		addShapedRecipe(new ItemStack(ModBlocks.wall, 6, 4), "###", "###", '#', new ItemStack(ModBlocks.umberstone, 1, 4));
		addShapedRecipe(new ItemStack(ModBlocks.wall, 6, 5), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0));
		addShapedRecipe(new ItemStack(ModBlocks.wall, 6, 6), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1));
		addShapedRecipe(new ItemStack(ModBlocks.wall, 6, 7), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2));
		addShapedRecipe(new ItemStack(ModBlocks.wall, 6, 8), "###", "###", '#', new ItemStack(ModBlocks.amber, 1, 2));

		// Doors
		addShapedRecipe(new ItemStack(ModItems.doorAmber, 3), "##", "##", "##", '#', new ItemStack(ModBlocks.amber, 1, 2));
		addShapedRecipe(new ItemStack(ModItems.doorBaobab, 3), "##", "##", "##", '#', "plank" + EnumWood.Baobab);
		addShapedRecipe(new ItemStack(ModItems.doorEucalyptus, 3), "##", "##", "##", '#', "plank" + EnumWood.Eucalyptus);
		addShapedRecipe(new ItemStack(ModItems.doorMahogany, 3), "##", "##", "##", '#', "plank" + EnumWood.Mahogany);
		addShapedRecipe(new ItemStack(ModItems.doorMossbark, 3), "##", "##", "##", '#', "plank" + EnumWood.Mossbark);
		addShapedRecipe(new ItemStack(ModItems.doorAsper, 3), "##", "##", "##", '#', "plank" + EnumWood.Asper);
		addShapedRecipe(new ItemStack(ModItems.doorCypress, 3), "##", "##", "##", '#', "plank" + EnumWood.Cypress);
		addShapedRecipe(new ItemStack(ModItems.doorRotten, 3), "##", "##", "##", '#', "plank" + EnumWood.Rotten);
		addShapedRecipe(new ItemStack(ModItems.doorPetrified, 3), "##", "##", "##", '#', "plankPetrified");
		addShapedRecipe(new ItemStack(ModItems.doorScorched, 3), "##", "##", "##", '#', "plankScorched");

		// Jade tools
		addShapedRecipe(new ItemStack(ModItems.jadePickaxe, 1), "XXX", " # ", " # ", '#', "stickWood", 'X', "gemJade");
		addShapedRecipe(new ItemStack(ModItems.jadeShovel, 1), "X", "#", "#", '#', "stickWood", 'X', "gemJade");
		addShapedRecipe(new ItemStack(ModItems.jadeAxe, 1), "XX", "X#", " #", '#', "stickWood", 'X', "gemJade");
		addShapedRecipe(new ItemStack(ModItems.jadeHoe, 1), "XX", " #", " #", '#', "stickWood", 'X', "gemJade");
		addShapedRecipe(new ItemStack(ModItems.jadeSword, 1), "X", "X", "#", '#', "stickWood", 'X', "gemJade");
		addShapedRecipe(new ItemStack(ModItems.jadePaxel, 1), "XXX", "XSX", "XSX", 'X', "gemJade", 'S', "stickWood");
		GameRegistry.addRecipe(new RecipePaxel());

		// Jade armor
		addShapedRecipe(new ItemStack(ModItems.jadeHelmet, 1), "###", "# #", '#', "gemJade");
		addShapedRecipe(new ItemStack(ModItems.jadeBody, 1), "# #", "###", "###", '#', "gemJade");
		addShapedRecipe(new ItemStack(ModItems.jadeLegs, 1), "###", "# #", "# #", '#', "gemJade");
		addShapedRecipe(new ItemStack(ModItems.jadeBoots, 1), "# #", "# #", '#', "gemJade");

		// Exoskeleton armor
		addShapedRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), "sss", "s s", 's', Materials.DATA.plateExo.createStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonBody, 1), "s s", "sss", "sss", 's', Materials.DATA.plateExo.createStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), "sss", "s s", "s s", 's', Materials.DATA.plateExo.createStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), "s s", "s s", 's', Materials.DATA.plateExo.createStack());

		addShapedRecipe(Materials.DATA.reinforcedPlateExo.createStack(), "sss", "sss", "sss", 's', Materials.DATA.plateExo.createStack());

		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonHelmet, 1), "sss", "s s", 's', Materials.DATA.reinforcedPlateExo.createStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonBody, 1), "s s", "sss", "sss", 's', Materials.DATA.reinforcedPlateExo.createStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonLegs, 1), "sss", "s s", "s s", 's', Materials.DATA.reinforcedPlateExo.createStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonBoots, 1), "s s", "s s", 's', Materials.DATA.reinforcedPlateExo.createStack());

		// Special armor
		addShapedRecipe(Materials.DATA.compoundLens.createStack(), "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.amber, 1, 1), 'G', Materials.DATA.compoundEyes.createStack());
		addShapedRecipe(new ItemStack(ModItems.compoundGoggles, 1), "XXX", "OXO", 'O', Materials.DATA.compoundLens.createStack(), 'X', Materials.DATA.plateExo.createStack());
		addShapedRecipe(new ItemStack(ModItems.reinCompoundGoggles, 1), "XXX", "XOX", 'O', new ItemStack(ModItems.compoundGoggles, 1), 'X', Materials.DATA.reinforcedPlateExo.createStack());
		addShapedRecipe(new ItemStack(ModItems.jumpBoots), "F F", "BXB", "B B", 'F', Materials.DATA.flyWing.createStack(), 'B', Materials.DATA.elasticFibre.createStack(), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		addShapedRecipe(new ItemStack(ModItems.sprintLeggings), "BBB", "BXB", "BBB", 'B', Materials.DATA.bioVelocity.createStack(), 'X', new ItemStack(ModItems.reinExoskeletonLegs, 1));
		addShapedRecipe(new ItemStack(ModItems.armorGlider), "GXG", 'G', Materials.DATA.gliderWing.createStack(), 'X', new ItemStack(ModItems.reinExoskeletonBody, 1));
		addShapedRecipe(new ItemStack(ModItems.armorGliderPowered), "W W", "ECE", " V ", 'W', Materials.DATA.enhancedGliderWing.createStack(), 'E', Materials.DATA.elasticFibre.createStack(), 'C', new ItemStack(ModItems.armorGlider, 1), 'V', new ItemStack(ModBlocks.velocityBlock, 1));
		addShapedRecipe(new ItemStack(ModItems.waterStriders), "WWW", "WXW", "WWW", 'W', Materials.DATA.waterRepellent.createStack(), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonHelmet), "h h", "sss", "s s", 's', Materials.DATA.plateExoRhino.createStack(), 'h', Materials.DATA.rhinoBeetleHorn.createStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonBody), "s s", "sss", "sss", 's', Materials.DATA.plateExoRhino.createStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonLegs), "sss", "s s", "s s", 's', Materials.DATA.plateExoRhino.createStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonBoots), "s s", "s s", 's', Materials.DATA.plateExoRhino.createStack());

		addShapedRecipe(new ItemStack(ModItems.scorpionPincer), "I I", "XIX", "XPX", 'I', "ingotIron", 'X', Materials.DATA.reinforcedPlateExo.createStack(), 'P', Materials.DATA.scorpionPincer.createStack());
		addShapedRecipe(new ItemStack(ModItems.rolledNewspaper), "PWP", "PIP", "PWP", 'I', new ItemStack(Items.dye, 1, 0), 'P', Materials.DATA.papyrus.createStack(), 'W', Materials.DATA.whetstonePowder.createStack());
		addShapedRecipe(Materials.DATA.gliderWing.createStack(), "SSS", "FFF", "FFF", 'S', "stickWood", 'F', Materials.DATA.flyWing.createStack());
		addShapedRecipe(Materials.DATA.enhancedGliderWing.createStack(), "BBB", "WWW", "WWW", 'B', Materials.DATA.bamboo.createStack(), 'W', Materials.DATA.dragonflyWing.createStack());
		addShapelessRecipe(new ItemStack(ModItems.waspDagger), Materials.DATA.waspSting.createStack(), "stickWood");
		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());
		GameRegistry.addRecipe(new RecipeWhetstoneUpgrades());

		// Mushroom Helm & Mushroom Blocks
		addShapedRecipe(new ItemStack(ModItems.mushroomHelm, 1), "mmm", "mpm", 'm', Materials.DATA.hideShroom.createStack(), 'p', new ItemStack(Blocks.pumpkin));

		addShapedRecipe(new ItemStack(ModBlocks.bigBulbCappedMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.bulbCapped, 1));
		addShapedRecipe(new ItemStack(ModBlocks.bigGreenMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.kaizerfinger, 1));
		addShapedRecipe(new ItemStack(ModBlocks.bigBundleMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.bundleshroom, 1));
		addShapedRecipe(new ItemStack(ModBlocks.bigKaiserfingerMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.greenMushroom));
		addShapedRecipe(new ItemStack(ModBlocks.bigDutchCapMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.dutchCap, 1));
		addShapedRecipe(new ItemStack(Blocks.red_mushroom_block), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.red_mushroom, 1));
		addShapedRecipe(new ItemStack(Blocks.brown_mushroom_block), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.brown_mushroom, 1));

		// Red Gem
		addShapelessRecipe(new ItemStack(Items.redstone, 2, 0), Materials.DATA.redGem.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.redGem, 1, 0), "##", "##", '#', Materials.DATA.redGem.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.redGem, 1, 1), " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', "stickWood");
		addShapedRecipe(new ItemStack(ModBlocks.glowGemBlock, 3, 0), "BBB", "BGB", "BBB", 'B', Materials.DATA.bioLuminescence.createStack(), 'G', Materials.DATA.redGem.createStack());

		// Bamboo
		addShapedRecipe(new ItemStack(ModItems.bambucket, 1, 0), " S ", "B B", " B ", 'S', Items.string, 'B', Materials.DATA.bamboo.createStack());
		addShapedRecipe(new ItemStack(ModItems.bambucketHoney), "RRR", "RBR", "RRR", 'B', new ItemStack(ModItems.bambucket, 1, 0), 'R', Materials.DATA.honeyDrip.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), "##", "##", '#', Materials.DATA.bamboo.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooCrate), "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), 'b', Materials.DATA.bamboo.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooLadder, 1), "BBB", "S S", "BBB", 'B', Materials.DATA.bamboo.createStack(), 'S', Items.string);
		addShapedRecipe(new ItemStack(ModBlocks.bambooTorch, 4), "C", "B", "B", 'C', Items.coal, 'B', Materials.DATA.bamboo.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooBridge, 3), "SSS", "B B", "LLL", 'S', Items.string, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', Materials.DATA.bamboo.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooPole, 4), " S ", " B ", " B ", 'S', "slimeball", 'B', Materials.DATA.bamboo.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.extenderThingy, 1), "BSB", "PDP", "BRB", 'S', Items.string, 'R', "dustRedstone", 'D', Blocks.dispenser, 'B', Materials.DATA.bamboo.createStack(), 'P', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()));

		addShapelessRecipe(new ItemStack(ModItems.food, 1, Food.FoodType.bambooSoup.ordinal()), new ItemStack(Items.bowl), Materials.DATA.bamboo.createStack(), Materials.DATA.bambooShoot.createStack());
		addShapelessRecipe(new ItemStack(ModItems.food, 1, Food.FoodType.melonade.ordinal()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.melon));
		addShapelessRecipe(new ItemStack(ModItems.food, 1, Food.FoodType.melonadeSparkly.ordinal()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.speckled_melon));
		addShapelessRecipe(new ItemStack(ModItems.food, 1, Food.FoodType.larvaeOnStick.ordinal()), "stickWood", new ItemStack(ModItems.food, 1, Food.FoodType.larvaCooked.ordinal()), new ItemStack(ModItems.food, 1, Food.FoodType.larvaCooked.ordinal()), new ItemStack(ModItems.food, 1, Food.FoodType.larvaCooked.ordinal()));
		addShapedRecipe(new ItemStack(ModItems.food, 2, Food.FoodType.honeySandwich.ordinal()), " B ", "RRR", " B ", 'B', new ItemStack(Items.bread), 'R', Materials.DATA.honeyDrip.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.honeyTreat, 1), "SRS", "RBR", "SRS", 'S', new ItemStack(Items.sugar), 'B', new ItemStack(Items.bread), 'R', Materials.DATA.honeyDrip.createStack());

		// Miscellaneous
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'x', Items.clay_ball, 'y', Materials.DATA.mudBrick.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'y', Items.clay_ball, 'x', Materials.DATA.mudBrick.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'x', Blocks.clay, 'y', ModBlocks.mudBricks);
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'y', Blocks.clay, 'x', ModBlocks.mudBricks);
		addShapedRecipe(new ItemStack(ModBlocks.blockSilk, 1), "sss", "sss", "sss", 's', Items.string);
		addShapedRecipe(new ItemStack(ModBlocks.amber, 4, 2), "ss", "ss", 's', new ItemStack(ModBlocks.amber, 1, 0));
		addShapelessRecipe(new ItemStack(Items.string, 9), new ItemStack(ModBlocks.blockSilk));
		addShapelessRecipe(new ItemStack(Items.dye, 1, 15), Materials.DATA.shardBone.createStack());
		addShapelessRecipe(new ItemStack(Items.dye, 6, 15), new ItemStack(ModItems.fossilClub, 1, 0));
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', new ItemStack(Items.feather, 1, 0), 'S', "stickWood", 'T', Materials.DATA.shardBone.createStack());
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', Materials.DATA.flyWing.createStack(), 'S', "stickWood", 'T', Materials.DATA.shardBone.createStack());
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', Materials.DATA.flyWing.createStack(), 'S', "stickWood", 'T', Items.flint);
		addShapedRecipe(new ItemStack(ModItems.sprayCan, 9), " B ", "XRX", "XXX", 'X', "ingotIron", 'B', Blocks.wooden_button, 'R', Materials.DATA.repellent.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.altarBase, 1), "XXX", "XOX", "XXX", 'O', Blocks.obsidian, 'X', Materials.DATA.altarFragment.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.glowingJar, 1), "XXX", "GBG", "GGG", 'X', "ingotIron", 'G', new ItemStack(ModBlocks.amber, 1, 1), 'B', Materials.DATA.bioLuminescence.createStack());
		addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), Materials.DATA.reinforcedPlateExo.createStack(), Materials.DATA.reinforcedPlateExo.createStack(), Materials.DATA.reinforcedPlateExo.createStack(), Materials.DATA.reinforcedPlateExo.createStack());
		addShapelessRecipe(new ItemStack(Items.book, 1, 0), Materials.DATA.plateExo.createStack(), Items.paper, Items.paper, Items.paper);
		addShapelessRecipe(new ItemStack(Items.paper, 4), Materials.DATA.papyrus.createStack(), Materials.DATA.papyrus.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.velocityBlock), "xxx", "xxx", "xxx", 'x', Materials.DATA.bioVelocity.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.mudBricks), "xx", "xx", 'x', Materials.DATA.mudBrick.createStack());
		addShapedRecipe(new ItemStack(ModItems.homingBeecon), "GNG", "NCN", "GNG", 'N', Materials.DATA.nectar.createStack(), 'G', "ingotGold", 'C', Items.compass);
		addShapedRecipe(new ItemStack(ModItems.nectarCollector), "  B", " S ", "S  ", 'B', Items.bowl, 'S', "stickWood");
		ItemStack diamondPick = new ItemStack(Items.diamond_pickaxe);
		diamondPick.addEnchantment(Enchantment.silkTouch, 1);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.blockExtractor), "  P", " D ", "C  ", 'P', Materials.DATA.scorpionPincer.createStack(), 'D', diamondPick, 'C', "chestWood"));
		addShapedRecipe(new ItemStack(ModItems.bucketHoney), "RRR", "RBR", "RRR", 'R', Materials.DATA.honeyDrip.createStack(), 'B', Items.bucket);
		addShapedRecipe(new ItemStack(ModBlocks.jarOHoney), "%%%", "$0$", "$$$", '%', "ingotIron", '$', new ItemStack(ModBlocks.amber, 1, 1), '0', Materials.DATA.nectar.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.jadeBlock), "xxx", "xxx", "xxx", 'x', "gemJade");
		addShapelessRecipe(Materials.DATA.jade.createStack(9), "blockJade");
		addShapedRecipe(Materials.DATA.mucusCharge.createStack(), "SSS", "SRS", "SSS", 'S', "slimeball", 'R', Materials.DATA.repellent.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.mucusBomb, 1), "MMM", "MTM", "MMM", 'M', Materials.DATA.mucusCharge.createStack(), 'T', Blocks.tnt);
		addShapedRecipe(new ItemStack(ModBlocks.honeyCombBlock, 1), "NPN", "PCP", "NPN", 'P', Materials.DATA.papyrus.createStack(), 'C', "chestWood", 'N', Materials.DATA.nectar.createStack());
		addShapedRecipe(new ItemStack(Items.blaze_powder, 1), "FFF", "FFF", "FFF", 'F', new ItemStack(ModBlocks.fireBloom));
		addShapedRecipe(Materials.DATA.mossBall.createStack(), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.wallPlants, 1, 0));
		addShapelessRecipe(new ItemStack(ModBlocks.scorchedPlanks, 4), ModBlocks.scorchedWood);
		addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), Materials.DATA.yellowDottedFungus.createStack(), Blocks.torch);
		addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), Materials.DATA.yellowDottedFungus.createStack(), Materials.DATA.bioLuminescence.createStack());
		addShapedRecipe(new ItemStack(ModItems.portalActivator), "VSE", "VSS", "GVV", 'V', Blocks.vine, 'S', "stickWood", 'E', Materials.DATA.gaeanGem.createStack(), 'G', "ingotGold");
		addShapedRecipe(new ItemStack(ModBlocks.gaeanKeystone), "V V", "SOS", "SSS", 'V', Blocks.vine, 'S', Blocks.stonebrick, 'O', Blocks.obsidian);
		addShapedRecipe(new ItemStack(ModItems.antTamingAmulet), "pgp", "gog", "pgp", 'p', Materials.DATA.antPheromones.createStack(), 'g', "ingotGold", 'o', Blocks.obsidian);
		addShapelessRecipe(new ItemStack(ModItems.planticide, 2), Materials.DATA.poisonGland.createStack(), "slimeball", "dyeWhite");
		addShapelessRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", Materials.DATA.sapBall.createStack(), Materials.DATA.repellent.createStack(), Materials.DATA.camoPowder.createStack());
		addShapelessRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", "slimeball", Materials.DATA.repellent.createStack(), Materials.DATA.camoPowder.createStack());
		addShapedRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeGreen", 'z', "gemEmerald");
		addShapedRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeLime", 'z', "gemEmerald");
		addShapedRecipe(new ItemStack(ModBlocks.siloSupports), "xxx", "y y", "y y", 'x', "slabWood", 'y', Blocks.fence);
		addShapedRecipe(new ItemStack(ModBlocks.siloTank), "xzx", "ywy", "xzx", 'x', "ingotIron", 'y', "blockIron", 'z', ModBlocks.varnishedPlanks, 'w', ModBlocks.petrifiedWoodChest);
		addShapedRecipe(new ItemStack(ModBlocks.siloRoof), " x ", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', ModBlocks.petrifiedWoodPlanks);
		addShapedRecipe(new ItemStack(ModBlocks.offeringAltar), "xwx", "yzy", "xyx", 'x', "stone", 'y', Blocks.stonebrick, 'z', Blocks.obsidian, 'w', "ingotGold");
		addShapedRecipe(new ItemStack(ModBlocks.templeTile, 4), "xx", "xx", 'x', new ItemStack(ModBlocks.templeBrick));
		addShapedRecipe(new ItemStack(ModBlocks.templePillar, 2), "x", "x", 'x', new ItemStack(ModBlocks.templeTile));
		addShapedRecipe(new ItemStack(ModBlocks.gneiss), "xx", "xx", 'x', Materials.DATA.gneissRock.createStack());
		addShapelessRecipe(new ItemStack(Items.dye, 2, 9), ModBlocks.waterFlower);
		addShapedRecipe(new ItemStack(ModBlocks.craftingAltar), "xxx", "xxx", "xxx", 'x', ModBlocks.altarBase);
		addShapedRecipe(Materials.DATA.jade.createStack(), "xxx", "xxx", "xxx", 'x', Materials.DATA.jadeBerries.createStack());
		addShapedRecipe(Materials.DATA.waterRepellent.createStack(), "xxx", "xrx", "xxx", 'x', Materials.DATA.hydrofuge.createStack(), 'r', Materials.DATA.repellent.createStack());
		addShapelessRecipe(Materials.DATA.stewPot.createStack(), Items.cauldron, "stickWood");
		addShapelessRecipe(Materials.DATA.titanStew.createStack(), Materials.DATA.stewPot.createStack(), new ItemStack(ModItems.food, 1, Food.FoodType.titanChop.ordinal()), Items.potato, Items.carrot, new ItemStack(ModItems.food, 1, Food.FoodType.cabbage.ordinal()), "foodMushroom", "foodMushroom");
		addShapelessRecipe(Materials.DATA.titanStew.createStack(), Materials.DATA.stewPot.createStack(), Items.beef, Items.beef, Items.potato, Items.carrot, new ItemStack(ModItems.food, 1, Food.FoodType.cabbage.ordinal()), "foodMushroom", "foodMushroom");

		// Smoothies
		// Have to figure this out...
		addShapelessRecipe(Materials.DATA.smoothieGlass.createStack(), Items.glass_bottle, Items.glass_bottle, Items.glass_bottle);
		addShapedRecipe(new ItemStack(ModBlocks.smoothieMaker), "xrx", "xxx", "sss", 'x', Materials.DATA.smoothieGlass.createStack(), 'r', new ItemStack(ModBlocks.redGem), 's', new ItemStack(ModBlocks.umberstone));

		addShapelessRecipe(new ItemStack(ModItems.bucketAntiVenom), ModItems.bucketBeetleJuice, Materials.DATA.poisonGland.createStack(), Materials.DATA.nettleleaves.createStack(), Materials.DATA.nettleleaves.createStack());
		addShapelessRecipe(new ItemStack(ModItems.bambucketAntiVenom), ModItems.bambucketBeetleJuice, Materials.DATA.poisonGland.createStack(), Materials.DATA.nettleleaves.createStack(), Materials.DATA.nettleleaves.createStack());
		addShapelessRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), ModItems.bucketAntiVenom, Items.glass_bottle, Items.glass_bottle);
		addShapelessRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), ModItems.bambucketAntiVenom, Items.glass_bottle, Items.glass_bottle);

		// Sharp Swords
		for (Item aSwordType : swordType)
			for (int j = 0; j < 6; j++) {
				ItemStack swordSharp = new ItemStack(aSwordType);
				ItemStack stoneLevel = new ItemStack(ModItems.whetstone, 1, j);
				if (stoneLevel.getItemDamage() > 0) {
					swordSharp.addEnchantment(Enchantment.sharpness, stoneLevel.getItemDamage());
					addShapelessRecipe(swordSharp, new ItemStack(ModItems.whetstone, 1, stoneLevel.getItemDamage()), new ItemStack(aSwordType));
				}
			}

		// Sharp Axes
		for (Item anAxeType : axeType)
			for (int j = 0; j < 6; j++) {
				ItemStack axeSharp = new ItemStack(anAxeType);
				ItemStack stoneLevel = new ItemStack(ModItems.whetstone, 1, j);
				if (stoneLevel.getItemDamage() > 0) {
					axeSharp.addEnchantment(Enchantment.sharpness, stoneLevel.getItemDamage() + 1);
					addShapelessRecipe(axeSharp, new ItemStack(ModItems.whetstone, 1, stoneLevel.getItemDamage()), new ItemStack(anAxeType));
				}
			}

		// Special Items
		addShapedRecipe(Materials.DATA.rhinoRidingKit.createStack(), " SX", "CCC", "LLL", 'S', Items.string, 'X', Materials.DATA.plateExo.createStack(), 'C', new ItemStack(Blocks.carpet, 1, 0), 'L', new ItemStack(Items.dye, 1, 4));
		addShapedRecipe(Materials.DATA.beetleTamingAmulet.createStack(), " N ", "NJN", " F ", 'N', "nuggetGold", 'J', "gemJade", 'F', Materials.DATA.altarFragment.createStack());
		addShapedRecipe(new ItemStack(ModItems.beeTamingAmulet), " n ", "nJn", " N ", 'n', "nuggetGold", 'J', "gemJade", 'N', Materials.DATA.nectar.createStack());

		// Umbergolem parts
		addShapedRecipe(Materials.DATA.umberGolemCore.createStack(), "AAA", "ARA", "AAA", 'A', Materials.DATA.altarFragment.createStack(), 'R', Materials.DATA.redGem.createStack());
		addShapedRecipe(Materials.DATA.umberGolemHead.createStack(), "SSS", "SHS", "SSS", 'S', "stone", 'H', new ItemStack(ModItems.reinCompoundGoggles, 1));
		addShapedRecipe(Materials.DATA.umberGolemClaw.createStack(), "  P", "  S", " SS", 'S', "stone", 'P', Materials.DATA.scorpionPincer.createStack());
		addShapedRecipe(Materials.DATA.umberGolemClaw.createStack(), "SSP", "S  ", 'S', "stone", 'P', Materials.DATA.scorpionPincer.createStack());
		addShapedRecipe(Materials.DATA.umberGolemLegs.createStack(), "SSS", "S S", "R R", 'S', "stone", 'R', Materials.DATA.reinforcedPlateExo.createStack());
		addShapedRecipe(DungeonIdols.createStack(IDOL.MudUmbergolem), "XXX", "XUX", "XXX", 'X', ModBlocks.mudBricks, 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(DungeonIdols.createStack(IDOL.IronUmbergolem), "XXX", "XUX", "XXX", 'X', "blockIron", 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(DungeonIdols.createStack(IDOL.GoldUmbergolem), "XXX", "XUX", "XXX", 'X', "blockGold", 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(DungeonIdols.createStack(IDOL.JadeUmbergolem), "XXX", "XUX", "XXX", 'X', "blockjade", 'U', ModBlocks.umberGolemStatue);

		// Animation Magic
		addShapedRecipe(new ItemStack(ModItems.wandOfAnimation), " xy", " zx", "x  ", 'x', "ingotGold", 'y', ModItems.soulCrystal, 'z', "stickWood");
		addShapedRecipe(new ItemStack(ModItems.ritualDagger), "  x", " y ", "z  ", 'x', "ingotGold", 'y', "gemJade", 'z', "stickWood");
		addShapedRecipe(new ItemStack(ModItems.stoneHeart), " x ", "yzx", "xxy", 'x', ModBlocks.templeTile, 'y', Blocks.obsidian, 'z', Materials.DATA.crimsonHeart.createStack());
	}

	private static void registerSmelting() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.amber), new ItemStack(ModBlocks.amber, 1, 1), 0.3F);
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
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreJade), Materials.DATA.jade.createStack(), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreFossil), Materials.DATA.shardBone.createStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreGneiss), Materials.DATA.gneissRock.createStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.orePetrifiedWood), Materials.DATA.petrifiedWood.createStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreEncrustedDiamond), new ItemStack(ModItems.encrustedDiamond), 1.0F);
		GameRegistry.addSmelting(Materials.DATA.titanStew.createStack(), new ItemStack(ModItems.food, 1, Food.FoodType.titanStewCooked.ordinal()), 1.0F);

		GameRegistry.addSmelting(new ItemStack(ModBlocks.mud), Materials.DATA.mudBrick.createStack(), 0.2F);
		GameRegistry.addSmelting(Materials.DATA.nectar.createStack(), Materials.DATA.honeyDrip.createStack(), 0.2F);
		if (ConfigHandler.INSTANCE.lead)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreLead), Materials.DATA.ingotLead.createStack(), 1.0F);
		if (ConfigHandler.INSTANCE.silver)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreSilver), Materials.DATA.ingotSilver.createStack(), 1.0F);
		if (ConfigHandler.INSTANCE.copper)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreCopper), Materials.DATA.ingotCopper.createStack(), 1.0F);
		if (ConfigHandler.INSTANCE.tin)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreTin), Materials.DATA.ingotTin.createStack(), 1.0F);
		if (ConfigHandler.INSTANCE.aluminium)
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreAluminium), Materials.DATA.ingotAluminium.createStack(), 1.0F);
	}

	private static void registerOreDictionary() {
		OreDictionary.registerOre("chestWood", new ItemStack(Blocks.chest));

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
		OreDictionary.registerOre("oreGneiss", new ItemStack(ModBlocks.oreGneiss));

		OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("stone", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("stoneUmber", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.spawnEggs, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemJade", Materials.DATA.jade.createStack());
		OreDictionary.registerOre("blockJade", new ItemStack(ModBlocks.jadeBlock));
		OreDictionary.registerOre("blockSpawner", ModBlocks.spiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.jumpingSpiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.waspSpawner);
		OreDictionary.registerOre("gemDiamond", ModItems.encrustedDiamond);
		OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.amber, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.saplessLog));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.scorchedWood));

		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planks, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.varnishedPlanks));
		OreDictionary.registerOre("plankVarnished", new ItemStack(ModBlocks.varnishedPlanks));
		OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.scorchedPlanks));
		OreDictionary.registerOre("plankScorched", new ItemStack(ModBlocks.scorchedPlanks));
		OreDictionary.registerOre("plankPetrified", new ItemStack(ModBlocks.petrifiedWoodPlanks));
		for (EnumWood wood : EnumWood.values())
			OreDictionary.registerOre("plank" + wood, new ItemStack(ModBlocks.planks, 1, wood.ordinal()));

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

		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.bulbCapped));
		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.kaizerfinger));
		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.bundleshroom));
		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.greenMushroom));
		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.dutchCap));
		OreDictionary.registerOre("foodMushroom", new ItemStack(Blocks.red_mushroom));
		OreDictionary.registerOre("foodMushroom", new ItemStack(Blocks.brown_mushroom));

		if (ConfigHandler.INSTANCE.lead) {
			OreDictionary.registerOre("ingotLead", Materials.DATA.ingotLead.createStack());
			OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.oreLead));
		}
		if (ConfigHandler.INSTANCE.silver) {
			OreDictionary.registerOre("ingotSilver", Materials.DATA.ingotSilver.createStack());
			OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.oreSilver));
		}
		if (ConfigHandler.INSTANCE.copper) {
			OreDictionary.registerOre("ingotCopper", Materials.DATA.ingotCopper.createStack());
			OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.oreCopper));
		}
		if (ConfigHandler.INSTANCE.tin) {
			OreDictionary.registerOre("ingotTin", Materials.DATA.ingotTin.createStack());
			OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.oreTin));
		}
		if (ConfigHandler.INSTANCE.aluminium) {
			OreDictionary.registerOre("ingotAluminium", Materials.DATA.ingotAluminium.createStack());
			OreDictionary.registerOre("oreAluminum", new ItemStack(ModBlocks.oreAluminium));
		}
	}

	private static void addShapedRecipe(ItemStack output, Object... objects) {
		GameRegistry.addRecipe(new ShapedOreRecipe(output, objects));
	}

	private static void addShapelessRecipe(ItemStack output, Object... objects) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(output, objects));
	}
}