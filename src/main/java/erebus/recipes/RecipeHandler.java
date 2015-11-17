package erebus.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockSlabStone;
import erebus.block.plants.BlockErebusFlower.FLOWER_TYPE;
import erebus.item.ItemDungeonIdols;
import erebus.item.ItemDungeonIdols.IDOL;
import erebus.item.ItemErebusFood;
import erebus.item.ItemMaterials;
import erebus.lib.EnumWood;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHandler {

	public static void init() {
		EnumWood.initRecipes();

		registerOreDictionary();
		registerRecipes();
		registerSmelting();

		RecipeSorter.register("erebus.gliderdye", RecipeGliderDye.class, Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("erebus.paxelrecipe", RecipePaxel.class, Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("erebus.enchantmentsensitiverecipe", EnchantSensitiveRecipe.class, Category.SHAPED, "after:minecraft:shaped");
		RecipeSorter.register("erebus.sprintleggingsupgrades", RecipeSprintLeggingsUpgrades.class, Category.SHAPELESS, "after:minecraft:shapeless");
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
		addShapedRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), "xx", "xx", 'x', ItemMaterials.DATA.petrifiedWood.makeStack());
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
		addShapedRecipe(new ItemStack(ModItems.doorMarshwood, 3), "##", "##", "##", '#', "plank" + EnumWood.Marshwood);
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
		addShapedRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), "sss", "s s", 's', ItemMaterials.DATA.plateExo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonBody, 1), "s s", "sss", "sss", 's', ItemMaterials.DATA.plateExo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), "sss", "s s", "s s", 's', ItemMaterials.DATA.plateExo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), "s s", "s s", 's', ItemMaterials.DATA.plateExo.makeStack());

		addShapedRecipe(ItemMaterials.DATA.reinforcedPlateExo.makeStack(), "sss", "sss", "sss", 's', ItemMaterials.DATA.plateExo.makeStack());

		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonHelmet, 1), "sss", "s s", 's', ItemMaterials.DATA.reinforcedPlateExo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonBody, 1), "s s", "sss", "sss", 's', ItemMaterials.DATA.reinforcedPlateExo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonLegs, 1), "sss", "s s", "s s", 's', ItemMaterials.DATA.reinforcedPlateExo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonBoots, 1), "s s", "s s", 's', ItemMaterials.DATA.reinforcedPlateExo.makeStack());

		// Special armor
		addShapedRecipe(ItemMaterials.DATA.compoundLens.makeStack(), "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.amber, 1, 1), 'G', ItemMaterials.DATA.compoundEyes.makeStack());
		addShapedRecipe(new ItemStack(ModItems.compoundGoggles, 1), "XXX", "OXO", 'O', ItemMaterials.DATA.compoundLens.makeStack(), 'X', ItemMaterials.DATA.plateExo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.reinCompoundGoggles, 1), "XXX", "XOX", 'O', new ItemStack(ModItems.compoundGoggles, 1), 'X', ItemMaterials.DATA.reinforcedPlateExo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.jumpBoots), "F F", "BXB", "B B", 'F', ItemMaterials.DATA.flyWing.makeStack(), 'B', ItemMaterials.DATA.elasticFibre.makeStack(), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		addShapedRecipe(new ItemStack(ModItems.sprintLeggings), "BBB", "BXB", "BBB", 'B', ItemMaterials.DATA.bioVelocity.makeStack(), 'X', new ItemStack(ModItems.reinExoskeletonLegs, 1));
		addShapedRecipe(new ItemStack(ModItems.armorGlider), "GXG", 'G', ItemMaterials.DATA.gliderWing.makeStack(), 'X', new ItemStack(ModItems.reinExoskeletonBody, 1));
		addShapedRecipe(new ItemStack(ModItems.armorGliderPowered), "W W", "ECE", " V ", 'W', ItemMaterials.DATA.enhancedGliderWing.makeStack(), 'E', ItemMaterials.DATA.elasticFibre.makeStack(), 'C', new ItemStack(ModItems.armorGlider, 1), 'V', new ItemStack(ModBlocks.velocityBlock, 1));
		GameRegistry.addRecipe(new RecipeGliderDye());
		addShapedRecipe(new ItemStack(ModItems.waterStriders), "WWW", "WXW", "WWW", 'W', ItemMaterials.DATA.waterRepellent.makeStack(), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonHelmet), "h h", "sss", "s s", 's', ItemMaterials.DATA.plateExoRhino.makeStack(), 'h', ItemMaterials.DATA.rhinoBeetleHorn.makeStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonBody), "s s", "sss", "sss", 's', ItemMaterials.DATA.plateExoRhino.makeStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonLegs), "sss", "s s", "s s", 's', ItemMaterials.DATA.plateExoRhino.makeStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonBoots), "s s", "s s", 's', ItemMaterials.DATA.plateExoRhino.makeStack());

		addShapedRecipe(new ItemStack(ModItems.scorpionPincer), "I I", "XIX", "XPX", 'I', "ingotIron", 'X', ItemMaterials.DATA.reinforcedPlateExo.makeStack(), 'P', ItemMaterials.DATA.scorpionPincer.makeStack());
		addShapedRecipe(new ItemStack(ModItems.rolledNewspaper), "PWP", "PIP", "PWP", 'I', new ItemStack(Items.dye, 1, 0), 'P', ItemMaterials.DATA.papyrus.makeStack(), 'W', ItemMaterials.DATA.whetstonePowder.makeStack());
		addShapedRecipe(ItemMaterials.DATA.gliderWing.makeStack(), "SSS", "FFF", "FFF", 'S', "stickWood", 'F', ItemMaterials.DATA.flyWing.makeStack());
		addShapedRecipe(ItemMaterials.DATA.enhancedGliderWing.makeStack(), "BBB", "WWW", "WWW", 'B', ItemMaterials.DATA.bamboo.makeStack(), 'W', ItemMaterials.DATA.dragonflyWing.makeStack());
		addShapelessRecipe(new ItemStack(ModItems.waspDagger), ItemMaterials.DATA.waspSting.makeStack(), "stickWood");
		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());

		// Mushroom Helm & Mushroom Blocks
		addShapedRecipe(new ItemStack(ModItems.mushroomHelm, 1), "mmm", "mpm", 'm', ItemMaterials.DATA.hideShroom.makeStack(), 'p', new ItemStack(Blocks.pumpkin));

		addShapedRecipe(new ItemStack(ModBlocks.bigBulbCappedMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.bulbCapped));
		addShapedRecipe(new ItemStack(ModBlocks.bigGreenMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.greenMushroom));
		addShapedRecipe(new ItemStack(ModBlocks.bigBundleMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.bundleshroom));
		addShapedRecipe(new ItemStack(ModBlocks.bigKaiserfingerMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.kaizerfinger));
		addShapedRecipe(new ItemStack(ModBlocks.bigDutchCapMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.dutchCap));
		addShapedRecipe(new ItemStack(Blocks.red_mushroom_block), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.red_mushroom));
		addShapedRecipe(new ItemStack(Blocks.brown_mushroom_block), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.brown_mushroom));

		// Red Gem
		addShapelessRecipe(new ItemStack(Items.redstone, 2, 0), ItemMaterials.DATA.redGem.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.redGem, 1, 0), "##", "##", '#', ItemMaterials.DATA.redGem.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.redGem, 1, 1), " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', "stickWood");
		addShapedRecipe(new ItemStack(ModBlocks.glowGemBlock, 3, 0), "BBB", "BGB", "BBB", 'B', ItemMaterials.DATA.bioLuminescence.makeStack(), 'G', ItemMaterials.DATA.redGem.makeStack());

		// Bamboo
		addShapedRecipe(new ItemStack(ModItems.bambucket, 1, 0), " S ", "B B", " B ", 'S', Items.string, 'B', ItemMaterials.DATA.bamboo.makeStack());
		addShapedRecipe(new ItemStack(ModItems.bambucketHoney), "RRR", "RBR", "RRR", 'B', new ItemStack(ModItems.bambucket, 1, 0), 'R', "dropHoney");
		addShapedRecipe(new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), "##", "##", '#', ItemMaterials.DATA.bamboo.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooCrate), "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), 'b', ItemMaterials.DATA.bamboo.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooLadder, 1), "BBB", "S S", "BBB", 'B', ItemMaterials.DATA.bamboo.makeStack(), 'S', Items.string);
		addShapedRecipe(new ItemStack(ModBlocks.bambooTorch, 4), "C", "B", "B", 'C', new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE), 'B', ItemMaterials.DATA.bamboo.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooBridge, 3), "SSS", "B B", "LLL", 'S', Items.string, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', ItemMaterials.DATA.bamboo.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooPole, 4), "S", "B", "B", 'S', "slimeball", 'B', ItemMaterials.DATA.bamboo.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.extenderThingy, 1), "BSB", "PDP", "BRB", 'S', Items.string, 'R', "dustRedstone", 'D', Blocks.dispenser, 'B', ItemMaterials.DATA.bamboo.makeStack(), 'P', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()));

		addShapelessRecipe(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BAMBOO_SOUP.ordinal()), new ItemStack(Items.bowl), ItemMaterials.DATA.bamboo.makeStack(), ItemMaterials.DATA.bambooShoot.makeStack());
		addShapelessRecipe(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.LARVAE_ON_STICK.ordinal()), "stickWood", new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_COOKED.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_COOKED.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_COOKED.ordinal()));
		addShapedRecipe(new ItemStack(ModItems.food, 2, ItemErebusFood.FoodType.HONEY_SANDWICH.ordinal()), " B ", "RRR", " B ", 'B', new ItemStack(Items.bread), 'R', "dropHoney");
		addShapedRecipe(new ItemStack(ModBlocks.honeyTreat, 1), "SRS", "RBR", "SRS", 'S', new ItemStack(Items.sugar), 'B', new ItemStack(Items.bread), 'R', "dropHoney");

		// Miscellaneous
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'x', Items.clay_ball, 'y', ItemMaterials.DATA.mudBrick.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'y', Items.clay_ball, 'x', ItemMaterials.DATA.mudBrick.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'x', Blocks.clay, 'y', ModBlocks.mudBricks);
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'y', Blocks.clay, 'x', ModBlocks.mudBricks);
		addShapedRecipe(new ItemStack(ModBlocks.blockSilk, 1), "sss", "sss", "sss", 's', Items.string);
		addShapedRecipe(new ItemStack(ModBlocks.amber, 4, 2), "ss", "ss", 's', new ItemStack(ModBlocks.amber, 1, 0));
		addShapelessRecipe(new ItemStack(Items.string, 9), new ItemStack(ModBlocks.blockSilk));
		addShapelessRecipe(new ItemStack(Items.dye, 1, 15), ItemMaterials.DATA.shardBone.makeStack());
		addShapelessRecipe(new ItemStack(Items.dye, 6, 15), new ItemStack(ModItems.cavemanClub, 1, 0));
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', new ItemStack(Items.feather, 1, 0), 'S', "stickWood", 'T', ItemMaterials.DATA.shardBone.makeStack());
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', ItemMaterials.DATA.flyWing.makeStack(), 'S', "stickWood", 'T', ItemMaterials.DATA.shardBone.makeStack());
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', ItemMaterials.DATA.flyWing.makeStack(), 'S', "stickWood", 'T', Items.flint);
		addShapedRecipe(new ItemStack(ModItems.sprayCan, 9), " B ", "XRX", "XXX", 'X', "ingotIron", 'B', "buttonWood", 'R', ItemMaterials.DATA.repellent.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.altarBase, 1), "XXX", "XOX", "XXX", 'O', Blocks.obsidian, 'X', ItemMaterials.DATA.altarFragment.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.glowingJar, 1), "XXX", "GBG", "GGG", 'X', "ingotIron", 'G', new ItemStack(ModBlocks.amber, 1, 1), 'B', ItemMaterials.DATA.bioLuminescence.makeStack());
		addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), ItemMaterials.DATA.reinforcedPlateExo.makeStack(), ItemMaterials.DATA.reinforcedPlateExo.makeStack(), ItemMaterials.DATA.reinforcedPlateExo.makeStack(), ItemMaterials.DATA.reinforcedPlateExo.makeStack());
		addShapelessRecipe(new ItemStack(Items.book, 1, 0), ItemMaterials.DATA.plateExo.makeStack(), Items.paper, Items.paper, Items.paper);
		addShapelessRecipe(new ItemStack(Items.paper, 4), ItemMaterials.DATA.papyrus.makeStack(), ItemMaterials.DATA.papyrus.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.velocityBlock), "xxx", "xxx", "xxx", 'x', ItemMaterials.DATA.bioVelocity.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.mudBricks), "xx", "xx", 'x', ItemMaterials.DATA.mudBrick.makeStack());
		addShapedRecipe(new ItemStack(ModItems.homingBeecon), "GNG", "NCN", "GNG", 'N', ItemMaterials.DATA.nectar.makeStack(), 'G', "ingotGold", 'C', Items.compass);
		addShapedRecipe(new ItemStack(ModItems.nectarCollector), "  B", " S ", "S  ", 'B', Items.bowl, 'S', "stickWood");
		ItemStack diamondPick = new ItemStack(Items.diamond_pickaxe);
		diamondPick.addEnchantment(Enchantment.silkTouch, 1);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.blockExtractor), "  P", " D ", "C  ", 'P', ItemMaterials.DATA.scorpionPincer.makeStack(), 'D', diamondPick, 'C', "chestWood"));
		addShapedRecipe(new ItemStack(ModItems.bucketHoney), "RRR", "RBR", "RRR", 'R', "dropHoney", 'B', Items.bucket);
		addShapedRecipe(new ItemStack(ModBlocks.jarOHoney), "%%%", "$0$", "$$$", '%', "ingotIron", '$', new ItemStack(ModBlocks.amber, 1, 1), '0', ItemMaterials.DATA.nectar.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.jadeBlock), "xxx", "xxx", "xxx", 'x', "gemJade");
		addShapelessRecipe(ItemMaterials.DATA.jade.makeStack(9), "blockJade");
		addShapedRecipe(ItemMaterials.DATA.mucusCharge.makeStack(), "SSS", "SRS", "SSS", 'S', "slimeball", 'R', ItemMaterials.DATA.repellent.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.mucusBomb, 1), "MMM", "MTM", "MMM", 'M', ItemMaterials.DATA.mucusCharge.makeStack(), 'T', Blocks.tnt);
		addShapedRecipe(new ItemStack(ModBlocks.honeyCombBlock, 1), "NPN", "PCP", "NPN", 'P', ItemMaterials.DATA.papyrus.makeStack(), 'C', "chestWood", 'N', ItemMaterials.DATA.nectar.makeStack());
		addShapedRecipe(new ItemStack(Items.blaze_powder, 1), "FFF", "FFF", "FFF", 'F', new ItemStack(ModBlocks.fireBloom));
		addShapedRecipe(ItemMaterials.DATA.mossBall.makeStack(), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.wallPlants, 1, 0));
		addShapelessRecipe(new ItemStack(ModBlocks.scorchedPlanks, 4), ModBlocks.scorchedWood);
		addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), ItemMaterials.DATA.yellowDottedFungus.makeStack(), Blocks.torch);
		addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), ItemMaterials.DATA.yellowDottedFungus.makeStack(), ItemMaterials.DATA.bioLuminescence.makeStack());
		addShapedRecipe(new ItemStack(ModItems.portalActivator), "VSE", "VSS", "GVV", 'V', Blocks.vine, 'S', "stickWood", 'E', ItemMaterials.DATA.gaeanGem.makeStack(), 'G', "ingotGold");
		addShapedRecipe(new ItemStack(ModBlocks.gaeanKeystone), "V V", "SOS", "SSS", 'V', Blocks.vine, 'S', Blocks.stonebrick, 'O', Blocks.obsidian);
		addShapedRecipe(new ItemStack(ModItems.antTamingAmulet), "pgp", "gog", "pgp", 'p', ItemMaterials.DATA.antPheromones.makeStack(), 'g', "ingotGold", 'o', Blocks.obsidian);
		addShapelessRecipe(new ItemStack(ModItems.planticide, 2), ItemMaterials.DATA.poisonGland.makeStack(), "slimeball", "dyeWhite");
		addShapelessRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", ItemMaterials.DATA.sapBall.makeStack(), ItemMaterials.DATA.repellent.makeStack(), ItemMaterials.DATA.camoPowder.makeStack());
		addShapelessRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", "slimeball", ItemMaterials.DATA.repellent.makeStack(), ItemMaterials.DATA.camoPowder.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeGreen", 'z', "gemEmerald");
		addShapedRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeLime", 'z', "gemEmerald");
		addShapedRecipe(new ItemStack(ModBlocks.siloSupports), "xxx", "y y", "y y", 'x', "slabWood", 'y', "fenceWood");
		addShapedRecipe(new ItemStack(ModBlocks.siloTank), "xzx", "ywy", "xzx", 'x', "ingotIron", 'y', "blockIron", 'z', ModBlocks.varnishedPlanks, 'w', ModBlocks.petrifiedWoodChest);
		addShapedRecipe(new ItemStack(ModBlocks.siloRoof), " x ", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', ModBlocks.petrifiedWoodPlanks);
		addShapedRecipe(new ItemStack(ModBlocks.offeringAltar), "xwx", "yzy", "xyx", 'x', "stone", 'y', Blocks.stonebrick, 'z', Blocks.obsidian, 'w', "ingotGold");
		addShapedRecipe(new ItemStack(ModBlocks.templeTile, 4), "xx", "xx", 'x', new ItemStack(ModBlocks.templeBrick));
		addShapedRecipe(new ItemStack(ModBlocks.templePillar, 2), "x", "x", 'x', new ItemStack(ModBlocks.templeTile));
		addShapedRecipe(new ItemStack(ModBlocks.gneiss), "xx", "xx", 'x', ItemMaterials.DATA.gneissRock.makeStack());
		addShapelessRecipe(new ItemStack(Items.dye, 2, 9), ModBlocks.waterFlower);
		addShapedRecipe(ItemMaterials.DATA.jade.makeStack(), "xxx", "xxx", "xxx", 'x', ItemMaterials.DATA.jadeBerries.makeStack());
		addShapedRecipe(ItemMaterials.DATA.waterRepellent.makeStack(), "xxx", "xrx", "xxx", 'x', ItemMaterials.DATA.hydrofuge.makeStack(), 'r', ItemMaterials.DATA.repellent.makeStack());
		addShapelessRecipe(ItemMaterials.DATA.stewPot.makeStack(), Items.cauldron, "stickWood");
		addShapelessRecipe(ItemMaterials.DATA.titanStew.makeStack(), ItemMaterials.DATA.stewPot.makeStack(), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TITAN_CHOP_RAW.ordinal()), Items.potato, Items.carrot, new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.CABBAGE.ordinal()), "foodMushroom", "foodMushroom");
		addShapelessRecipe(ItemMaterials.DATA.titanStew.makeStack(), ItemMaterials.DATA.stewPot.makeStack(), Items.beef, Items.beef, Items.potato, Items.carrot, new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.CABBAGE.ordinal()), "foodMushroom", "foodMushroom");
		addShapedRecipe(new ItemStack(ModBlocks.wallPlantsCultivated, 1, 0), "dvd", "vpv", "dvd", 'd', new ItemStack(Items.dye, 1, 2), 'v', ItemMaterials.DATA.supernaturalvelocity.makeStack(), 'p', new ItemStack(ModBlocks.wallPlants, 1, 0));
		addShapedRecipe(new ItemStack(ModBlocks.wallPlantsCultivated, 1, 1), "dvd", "vpv", "dvd", 'd', new ItemStack(Items.dye, 1, 4), 'v', ItemMaterials.DATA.supernaturalvelocity.makeStack(), 'p', new ItemStack(ModBlocks.wallPlants, 1, 1));
		addShapedRecipe(new ItemStack(Items.leather, 8), "xx", "xx", 'x', ItemMaterials.DATA.hideShroom.makeStack());
		addShapedRecipe(new ItemStack(Items.dye, 1, 2), "xx", "xx", 'x', new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.STEM.ordinal()));
		addShapedRecipe(ItemMaterials.DATA.amberStar.makeStack(), " x ", "xyx", " x ", 'x', ItemMaterials.DATA.sapBall.makeStack(), 'y', new ItemStack(ModBlocks.amber, 1, 1));

		// Smoothies
		// Have to figure this out...
		addShapelessRecipe(ItemMaterials.DATA.smoothieGlass.makeStack(), Items.glass_bottle, Items.glass_bottle, Items.glass_bottle);
		addShapedRecipe(new ItemStack(ModBlocks.smoothieMaker), "xrx", "xxx", "sss", 'x', ItemMaterials.DATA.smoothieGlass.makeStack(), 'r', new ItemStack(ModBlocks.redGem), 's', new ItemStack(ModBlocks.umberstone));

		addShapelessRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), ModItems.bucketAntiVenom, Items.glass_bottle, Items.glass_bottle);
		addShapelessRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), ModItems.bambucketAntiVenom, Items.glass_bottle, Items.glass_bottle);

		// Whetstone
		addShapedRecipe(new ItemStack(ModItems.whetstone, 1, 0), "SSS", "PPP", "UUU", 'S', Blocks.sand, 'P', ItemMaterials.DATA.petrifiedWood.makeStack(), 'U', new ItemStack(ModBlocks.umberstone, 1, 0));
		for (int i = 1; i <= Enchantment.sharpness.getMaxLevel(); i++)
			addShapedRecipe(new ItemStack(ModItems.whetstone, 1, i), "xxx", "xyx", "xxx", 'x', ItemMaterials.DATA.whetstonePowder.makeStack(), 'y', new ItemStack(ModItems.whetstone, 1, i - 1));

		// Special Items
		addShapedRecipe(ItemMaterials.DATA.rhinoRidingKit.makeStack(), " SX", "CCC", "LLL", 'S', Items.string, 'X', ItemMaterials.DATA.plateExo.makeStack(), 'C', new ItemStack(Blocks.carpet, 1, 0), 'L', new ItemStack(Items.dye, 1, 4));
		addShapedRecipe(ItemMaterials.DATA.beetleTamingAmulet.makeStack(), " N ", "NJN", " F ", 'N', "nuggetGold", 'J', "gemJade", 'F', ItemMaterials.DATA.altarFragment.makeStack());
		addShapedRecipe(new ItemStack(ModItems.beeTamingAmulet), " n ", "nJn", " N ", 'n', "nuggetGold", 'J', "gemJade", 'N', ItemMaterials.DATA.nectar.makeStack());

		// Umbergolem parts
		addShapedRecipe(ItemMaterials.DATA.umberGolemCore.makeStack(), "AAA", "ARA", "AAA", 'A', ItemMaterials.DATA.altarFragment.makeStack(), 'R', ItemMaterials.DATA.redGem.makeStack());
		addShapedRecipe(ItemMaterials.DATA.umberGolemHead.makeStack(), "SSS", "SHS", "SSS", 'S', "stone", 'H', new ItemStack(ModItems.reinCompoundGoggles, 1));
		addShapedRecipe(ItemMaterials.DATA.umberGolemClaw.makeStack(), "  P", "  S", " SS", 'S', "stone", 'P', ItemMaterials.DATA.scorpionPincer.makeStack());
		addShapedRecipe(ItemMaterials.DATA.umberGolemClaw.makeStack(), "SSP", "S  ", 'S', "stone", 'P', ItemMaterials.DATA.scorpionPincer.makeStack());
		addShapedRecipe(ItemMaterials.DATA.umberGolemLegs.makeStack(), "SSS", "S S", "R R", 'S', "stone", 'R', ItemMaterials.DATA.reinforcedPlateExo.makeStack());
		addShapedRecipe(ItemDungeonIdols.createStack(IDOL.MudUmbergolem), "XXX", "XUX", "XXX", 'X', ModBlocks.mudBricks, 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(ItemDungeonIdols.createStack(IDOL.IronUmbergolem), "XXX", "XUX", "XXX", 'X', "blockIron", 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(ItemDungeonIdols.createStack(IDOL.GoldUmbergolem), "XXX", "XUX", "XXX", 'X', "blockGold", 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(ItemDungeonIdols.createStack(IDOL.JadeUmbergolem), "XXX", "XUX", "XXX", 'X', "blockJade", 'U', ModBlocks.umberGolemStatue);

		// Umbergolem Statue
		addShapedRecipe(new ItemStack(ModBlocks.umberGolemStatue), " H ", "LCL", " X ", 'H', ItemMaterials.DATA.umberGolemHead.makeStack(), 'L', ItemMaterials.DATA.umberGolemClaw.makeStack(), 'C', ItemMaterials.DATA.umberGolemCore.makeStack(), 'X', ItemMaterials.DATA.umberGolemLegs.makeStack());

		// Animation Magic
		addShapedRecipe(new ItemStack(ModItems.wandOfAnimation), " xy", " zx", "x  ", 'x', "ingotGold", 'y', ModItems.soulCrystal, 'z', "stickWood");

		// Temporary Replacement Recipes
		addShapelessRecipe(new ItemStack(ModItems.witherWebSlinger), new ItemStack(ModItems.webSlinger), new ItemStack(Blocks.soul_sand), ItemMaterials.DATA.poisonGland.makeStack(), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb));
		addShapelessRecipe(new ItemStack(ModBlocks.lightningSpeedBlock), new ItemStack(ModBlocks.velocityBlock), ItemMaterials.DATA.supernaturalvelocity.makeStack(), ItemMaterials.DATA.supernaturalvelocity.makeStack(), ItemMaterials.DATA.supernaturalvelocity.makeStack(), ItemMaterials.DATA.supernaturalvelocity.makeStack(), ItemMaterials.DATA.supernaturalvelocity.makeStack(), ItemMaterials.DATA.supernaturalvelocity.makeStack(), ItemMaterials.DATA.supernaturalvelocity.makeStack(), ItemMaterials.DATA.supernaturalvelocity.makeStack());

	}

	private static void registerSmelting() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.amber), new ItemStack(ModBlocks.amber, 1, 1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.GRASSHOPPER_LEG_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.GRASSHOPPER_LEG_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TARANTULA_LEG_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TARANTULA_LEG_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TITAN_CHOP_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TITAN_CHOP_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberstone, 1, 1), new ItemStack(ModBlocks.umberstone), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreCoal), new ItemStack(Items.coal, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreIron), new ItemStack(Items.iron_ingot), 0.7F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreGold), new ItemStack(Items.gold_ingot), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreLapis), new ItemStack(Items.dye, 1, 4), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreDiamond), new ItemStack(Items.diamond), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreEmerald), new ItemStack(Items.emerald), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreJade), ItemMaterials.DATA.jade.makeStack(), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreFossil), ItemMaterials.DATA.shardBone.makeStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreGneiss), ItemMaterials.DATA.gneissRock.makeStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.orePetrifiedWood), ItemMaterials.DATA.petrifiedWood.makeStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreEncrustedDiamond), new ItemStack(ModItems.encrustedDiamond), 1.0F);
		GameRegistry.addSmelting(ItemMaterials.DATA.titanStew.makeStack(), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TITAN_STEW_COOKED.ordinal()), 1.0F);

		GameRegistry.addSmelting(new ItemStack(ModBlocks.mud), ItemMaterials.DATA.mudBrick.makeStack(), 0.2F);
		GameRegistry.addSmelting(ItemMaterials.DATA.nectar.makeStack(), ItemMaterials.DATA.honeyDrip.makeStack(), 0.2F);
		if (OreType.LEAD.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreLead), ItemMaterials.DATA.ingotLead.makeStack(), 1.0F);
		if (OreType.SILVER.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreSilver), ItemMaterials.DATA.ingotSilver.makeStack(), 1.0F);
		if (OreType.COPPER.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreCopper), ItemMaterials.DATA.ingotCopper.makeStack(), 1.0F);
		if (OreType.TIN.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreTin), ItemMaterials.DATA.ingotTin.makeStack(), 1.0F);
		if (OreType.ALUMINIUM.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreAluminium), ItemMaterials.DATA.ingotAluminium.makeStack(), 1.0F);
	}

	private static void registerOreDictionary() {
		OreDictionary.registerOre("chestWood", new ItemStack(Blocks.chest));
		OreDictionary.registerOre("buttonWood", new ItemStack(Blocks.wooden_button));
		OreDictionary.registerOre("fenceWood", new ItemStack(Blocks.fence));

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
		OreDictionary.registerOre("gemJade", ItemMaterials.DATA.jade.makeStack());
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

		//Honey
		OreDictionary.registerOre("dropHoney", ItemMaterials.DATA.honeyDrip.makeStack());
		OreDictionary.registerOre("bucketHoney", new ItemStack(ModItems.bucketHoney));
		OreDictionary.registerOre("bucketHoney", new ItemStack(ModItems.bambucketHoney));

		if (OreType.LEAD.isEnabled()) {
			OreDictionary.registerOre("ingotLead", ItemMaterials.DATA.ingotLead.makeStack());
			OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.oreLead));
		}
		if (OreType.SILVER.isEnabled()) {
			OreDictionary.registerOre("ingotSilver", ItemMaterials.DATA.ingotSilver.makeStack());
			OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.oreSilver));
		}
		if (OreType.COPPER.isEnabled()) {
			OreDictionary.registerOre("ingotCopper", ItemMaterials.DATA.ingotCopper.makeStack());
			OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.oreCopper));
		}
		if (OreType.TIN.isEnabled()) {
			OreDictionary.registerOre("ingotTin", ItemMaterials.DATA.ingotTin.makeStack());
			OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.oreTin));
		}
		if (OreType.ALUMINIUM.isEnabled()) {
			OreDictionary.registerOre("ingotAluminium", ItemMaterials.DATA.ingotAluminium.makeStack());
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