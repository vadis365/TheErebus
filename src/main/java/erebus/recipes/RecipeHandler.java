package erebus.recipes;

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
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.BlockSlabStone;
import erebus.block.plants.BlockErebusFlower.FLOWER_TYPE;
import erebus.item.ItemDungeonIdols;
import erebus.item.ItemErebusFood;
import erebus.item.ItemMaterials;
import erebus.lib.EnumWood;
import erebus.world.biomes.decorators.data.OreSettings.OreType;

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
		addShapedRecipe(new ItemStack(ModBlocks.petrifiedWoodPlanks), "xx", "xx", 'x', ItemMaterials.DATA.PETRIFIED_WOOD.makeStack());
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
		addShapedRecipe(new ItemStack(ModItems.exoskeletonHelmet, 1), "sss", "s s", 's', ItemMaterials.DATA.PLATE_EXO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonBody, 1), "s s", "sss", "sss", 's', ItemMaterials.DATA.PLATE_EXO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonLegs, 1), "sss", "s s", "s s", 's', ItemMaterials.DATA.PLATE_EXO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.exoskeletonBoots, 1), "s s", "s s", 's', ItemMaterials.DATA.PLATE_EXO.makeStack());

		addShapedRecipe(ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack(), "sss", "sss", "sss", 's', ItemMaterials.DATA.PLATE_EXO.makeStack());

		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonHelmet, 1), "sss", "s s", 's', ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonBody, 1), "s s", "sss", "sss", 's', ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonLegs, 1), "sss", "s s", "s s", 's', ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.reinExoskeletonBoots, 1), "s s", "s s", 's', ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack());

		// Special armor
		addShapedRecipe(ItemMaterials.DATA.COMPOUND_LENS.makeStack(), "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.amber, 1, 1), 'G', ItemMaterials.DATA.COMPOUND_EYES.makeStack());
		addShapedRecipe(new ItemStack(ModItems.compoundGoggles, 1), "XXX", "OXO", 'O', ItemMaterials.DATA.COMPOUND_LENS.makeStack(), 'X', ItemMaterials.DATA.PLATE_EXO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.reinCompoundGoggles, 1), "XXX", "XOX", 'O', new ItemStack(ModItems.compoundGoggles, 1), 'X', ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.jumpBoots), "F F", "BXB", "B B", 'F', ItemMaterials.DATA.FLY_WING.makeStack(), 'B', ItemMaterials.DATA.ELASTIC_FIBRE.makeStack(), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		addShapedRecipe(new ItemStack(ModItems.sprintLeggings), "BBB", "BXB", "BBB", 'B', ItemMaterials.DATA.BIO_VELOCITY.makeStack(), 'X', new ItemStack(ModItems.reinExoskeletonLegs, 1));
		addShapedRecipe(new ItemStack(ModItems.armorGlider), "GXG", 'G', ItemMaterials.DATA.GLIDER_WING.makeStack(), 'X', new ItemStack(ModItems.reinExoskeletonBody, 1));
		addShapedRecipe(new ItemStack(ModItems.armorGliderPowered), "W W", "ECE", " V ", 'W', ItemMaterials.DATA.ENHANCED_GLIDER_WING.makeStack(), 'E', ItemMaterials.DATA.ELASTIC_FIBRE.makeStack(), 'C', new ItemStack(ModItems.armorGlider, 1), 'V', new ItemStack(ModBlocks.velocityBlock, 1));
		GameRegistry.addRecipe(new RecipeGliderDye());
		addShapedRecipe(new ItemStack(ModItems.waterStriders), "WWW", "WXW", "WWW", 'W', ItemMaterials.DATA.WATER_REPELLENT.makeStack(), 'X', new ItemStack(ModItems.reinExoskeletonBoots, 1));
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonHelmet), "h h", "sss", "s s", 's', ItemMaterials.DATA.PLATE_EXO_RHINO.makeStack(), 'h', ItemMaterials.DATA.RHINO_BEETLE_HORN.makeStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonBody), "s s", "sss", "sss", 's', ItemMaterials.DATA.PLATE_EXO_RHINO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonLegs), "sss", "s s", "s s", 's', ItemMaterials.DATA.PLATE_EXO_RHINO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.rhinoExoskeletonBoots), "s s", "s s", 's', ItemMaterials.DATA.PLATE_EXO_RHINO.makeStack());

		addShapedRecipe(new ItemStack(ModItems.scorpionPincer), "I I", "XIX", "XPX", 'I', "ingotIron", 'X', ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack(), 'P', ItemMaterials.DATA.SCORPION_PINCER.makeStack());
		addShapedRecipe(new ItemStack(ModItems.rolledNewspaper), "PWP", "PIP", "PWP", 'I', new ItemStack(Items.dye, 1, 0), 'P', ItemMaterials.DATA.PAPYRUS.makeStack(), 'W', ItemMaterials.DATA.WHETSTONE_POWDER.makeStack());
		addShapedRecipe(ItemMaterials.DATA.GLIDER_WING.makeStack(), "SSS", "FFF", "FFF", 'S', "stickWood", 'F', ItemMaterials.DATA.FLY_WING.makeStack());
		addShapedRecipe(ItemMaterials.DATA.ENHANCED_GLIDER_WING.makeStack(), "BBB", "WWW", "WWW", 'B', ItemMaterials.DATA.BAMBOO.makeStack(), 'W', ItemMaterials.DATA.DRAGONFLY_WING.makeStack());
		addShapelessRecipe(new ItemStack(ModItems.waspDagger), ItemMaterials.DATA.WASP_STING.makeStack(), "stickWood");
		GameRegistry.addRecipe(new RecipeSprintLeggingsUpgrades());

		// Mushroom Helm & Mushroom Blocks
		addShapedRecipe(new ItemStack(ModItems.mushroomHelm, 1), "mmm", "mpm", 'm', ItemMaterials.DATA.HIDE_SHROOM.makeStack(), 'p', new ItemStack(Blocks.pumpkin));

		addShapedRecipe(new ItemStack(ModBlocks.bigBulbCappedMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.darkCapped));
		addShapedRecipe(new ItemStack(ModBlocks.bigGreenMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.grandmasShoes));
		addShapedRecipe(new ItemStack(ModBlocks.bigBundleMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.sarcasticCzech));
		addShapedRecipe(new ItemStack(ModBlocks.bigKaiserfingerMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.kaizersFinger));
		addShapedRecipe(new ItemStack(ModBlocks.bigDutchCapMushroom), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.dutchCap));
		addShapedRecipe(new ItemStack(Blocks.red_mushroom_block), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.red_mushroom));
		addShapedRecipe(new ItemStack(Blocks.brown_mushroom_block), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.brown_mushroom));

		// Red Gem
		addShapelessRecipe(new ItemStack(Items.redstone, 2, 0), ItemMaterials.DATA.RED_GEM.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.redGem, 1, 0), "##", "##", '#', ItemMaterials.DATA.RED_GEM.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.redGem, 1, 1), " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.redGem, 1, 0), 'S', "stickWood");
		addShapedRecipe(new ItemStack(ModBlocks.glowGemBlock, 3, 0), "BBB", "BGB", "BBB", 'B', ItemMaterials.DATA.BIO_LUMINESCENCE.makeStack(), 'G', ItemMaterials.DATA.RED_GEM.makeStack());

		// Bamboo
		addShapedRecipe(new ItemStack(ModItems.bambucket, 1, 0), " S ", "B B", " B ", 'S', Items.string, 'B', ItemMaterials.DATA.BAMBOO.makeStack());
		addShapedRecipe(new ItemStack(ModItems.bambucketHoney), "RRR", "RBR", "RRR", 'B', new ItemStack(ModItems.bambucket, 1, 0), 'R', "dropHoney");
		addShapedRecipe(new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), "##", "##", '#', ItemMaterials.DATA.BAMBOO.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooCrate), "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()), 'b', ItemMaterials.DATA.BAMBOO.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooLadder, 1), "BBB", "S S", "BBB", 'B', ItemMaterials.DATA.BAMBOO.makeStack(), 'S', Items.string);
		addShapedRecipe(new ItemStack(ModBlocks.bambooTorch, 4), "C", "B", "B", 'C', new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE), 'B', ItemMaterials.DATA.BAMBOO.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooBridge, 3), "SSS", "B B", "LLL", 'S', Items.string, 'L', new ItemStack(ModBlocks.bambooLadder, 1), 'B', ItemMaterials.DATA.BAMBOO.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.bambooPole, 4), "S", "B", "B", 'S', "slimeball", 'B', ItemMaterials.DATA.BAMBOO.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.extenderThingy, 1), "BSB", "PDP", "BRB", 'S', Items.string, 'R', "dustRedstone", 'D', Blocks.dispenser, 'B', ItemMaterials.DATA.BAMBOO.makeStack(), 'P', new ItemStack(ModBlocks.planks, 1, EnumWood.Bamboo.ordinal()));

		addShapelessRecipe(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BAMBOO_SOUP.ordinal()), new ItemStack(Items.bowl), ItemMaterials.DATA.BAMBOO.makeStack(), ItemMaterials.DATA.BAMBOO_SHOOT.makeStack());
		addShapelessRecipe(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.LARVAE_ON_STICK.ordinal()), "stickWood", new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_COOKED.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_COOKED.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_COOKED.ordinal()));
		addShapedRecipe(new ItemStack(ModItems.food, 2, ItemErebusFood.FoodType.HONEY_SANDWICH.ordinal()), " B ", "RRR", " B ", 'B', new ItemStack(Items.bread), 'R', "dropHoney");
		addShapedRecipe(new ItemStack(ModBlocks.honeyTreat, 1), "SRS", "RBR", "SRS", 'S', new ItemStack(Items.sugar), 'B', new ItemStack(Items.bread), 'R', "dropHoney");

		// Miscellaneous
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'x', Items.clay_ball, 'y', ItemMaterials.DATA.MUD_BRICK.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick), "xy", "yx", 'y', Items.clay_ball, 'x', ItemMaterials.DATA.MUD_BRICK.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'x', Blocks.clay, 'y', ModBlocks.mudBricks);
		addShapedRecipe(new ItemStack(ModBlocks.mirBrick, 4), "xy", "yx", 'y', Blocks.clay, 'x', ModBlocks.mudBricks);
		addShapedRecipe(new ItemStack(ModBlocks.blockSilk, 1), "sss", "sss", "sss", 's', Items.string);
		addShapedRecipe(new ItemStack(ModBlocks.amber, 4, 2), "ss", "ss", 's', new ItemStack(ModBlocks.amber, 1, 0));
		addShapelessRecipe(new ItemStack(Items.string, 9), new ItemStack(ModBlocks.blockSilk));
		addShapelessRecipe(new ItemStack(Items.dye, 1, 15), ItemMaterials.DATA.SHARD_BONE.makeStack());
		addShapelessRecipe(new ItemStack(Items.dye, 6, 15), new ItemStack(ModItems.cavemanClub, 1, 0));
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', new ItemStack(Items.feather, 1, 0), 'S', "stickWood", 'T', ItemMaterials.DATA.SHARD_BONE.makeStack());
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', ItemMaterials.DATA.FLY_WING.makeStack(), 'S', "stickWood", 'T', ItemMaterials.DATA.SHARD_BONE.makeStack());
		addShapedRecipe(new ItemStack(Items.arrow, 4), "T", "S", "F", 'F', ItemMaterials.DATA.FLY_WING.makeStack(), 'S', "stickWood", 'T', Items.flint);
		addShapedRecipe(new ItemStack(ModItems.sprayCan, 9), " B ", "XRX", "XXX", 'X', "ingotIron", 'B', "buttonWood", 'R', ItemMaterials.DATA.REPELLENT.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.altarBase, 1), "XXX", "XOX", "XXX", 'O', Blocks.obsidian, 'X', ItemMaterials.DATA.ALTAR_FRAGMENT.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.glowingJar, 1), "XXX", "GBG", "GGG", 'X', "ingotIron", 'G', new ItemStack(ModBlocks.amber, 1, 1), 'B', ItemMaterials.DATA.BIO_LUMINESCENCE.makeStack());
		addShapelessRecipe(new ItemStack(ModBlocks.reinExo, 1), ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack(), ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack(), ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack(), ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack());
		addShapelessRecipe(new ItemStack(Items.book, 1, 0), ItemMaterials.DATA.PLATE_EXO.makeStack(), Items.paper, Items.paper, Items.paper);
		addShapelessRecipe(new ItemStack(Items.paper, 4), ItemMaterials.DATA.PAPYRUS.makeStack(), ItemMaterials.DATA.PAPYRUS.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.velocityBlock), "xxx", "xxx", "xxx", 'x', ItemMaterials.DATA.BIO_VELOCITY.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.mudBricks), "xx", "xx", 'x', ItemMaterials.DATA.MUD_BRICK.makeStack());
		addShapedRecipe(new ItemStack(ModItems.homingBeecon), "GNG", "NCN", "GNG", 'N', ItemMaterials.DATA.NECTAR.makeStack(), 'G', "ingotGold", 'C', Items.compass);
		addShapedRecipe(new ItemStack(ModItems.nectarCollector), "  B", " S ", "S  ", 'B', Items.bowl, 'S', "stickWood");
		ItemStack diamondPick = new ItemStack(Items.diamond_pickaxe);
		diamondPick.addEnchantment(Enchantment.silkTouch, 1);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.blockExtractor), "  M", " D ", "C  ", 'M', ItemMaterials.DATA.STAG_BEETLE_MANDIBLES.makeStack(), 'D', diamondPick, 'C', "chestWood"));
		addShapedRecipe(new ItemStack(ModItems.bucketHoney), "RRR", "RBR", "RRR", 'R', "dropHoney", 'B', Items.bucket);
		addShapedRecipe(new ItemStack(ModBlocks.jarOHoney), "%%%", "$0$", "$$$", '%', "ingotIron", '$', new ItemStack(ModBlocks.amber, 1, 1), '0', ItemMaterials.DATA.NECTAR.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.jadeBlock), "xxx", "xxx", "xxx", 'x', "gemJade");
		addShapelessRecipe(ItemMaterials.DATA.JADE.makeStack(9), "blockJade");
		addShapedRecipe(ItemMaterials.DATA.MUCUS_CHARGE.makeStack(), "SSS", "SRS", "SSS", 'S', "slimeball", 'R', ItemMaterials.DATA.REPELLENT.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.mucusBomb, 1), "MMM", "MTM", "MMM", 'M', ItemMaterials.DATA.MUCUS_CHARGE.makeStack(), 'T', Blocks.tnt);
		addShapedRecipe(new ItemStack(ModBlocks.honeyCombBlock, 1), "NPN", "PCP", "NPN", 'P', ItemMaterials.DATA.PAPYRUS.makeStack(), 'C', "chestWood", 'N', ItemMaterials.DATA.NECTAR.makeStack());
		addShapedRecipe(new ItemStack(Items.blaze_powder, 1), "FFF", "FFF", "FFF", 'F', new ItemStack(ModBlocks.fireBloom));
		addShapedRecipe(ItemMaterials.DATA.MOSS_BALL.makeStack(), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.wallPlants, 1, 0));
		addShapelessRecipe(new ItemStack(ModBlocks.scorchedPlanks, 4), ModBlocks.scorchedWood);
		addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), ItemMaterials.DATA.YELLOW_DOTTED_FUNGUS.makeStack(), Blocks.torch);
		addShapelessRecipe(new ItemStack(ModBlocks.glowshroom), ItemMaterials.DATA.YELLOW_DOTTED_FUNGUS.makeStack(), ItemMaterials.DATA.BIO_LUMINESCENCE.makeStack());
		addShapedRecipe(new ItemStack(ModItems.portalActivator), "VSE", "VSS", "GVV", 'V', Blocks.vine, 'S', "stickWood", 'E', ItemMaterials.DATA.GAEAN_GEM.makeStack(), 'G', "ingotGold");
		addShapedRecipe(new ItemStack(ModBlocks.gaeanKeystone), "V V", "SOS", "SSS", 'V', Blocks.vine, 'S', Blocks.stonebrick, 'O', Blocks.obsidian);
		addShapedRecipe(new ItemStack(ModItems.antTamingAmulet), "pgp", "gog", "pgp", 'p', ItemMaterials.DATA.ANT_PHEROMONES.makeStack(), 'g', "ingotGold", 'o', Blocks.obsidian);
		addShapelessRecipe(new ItemStack(ModItems.planticide, 2), ItemMaterials.DATA.POISON_GLAND.makeStack(), "slimeball", "dyeWhite");
		addShapelessRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", ItemMaterials.DATA.SAP_BALL.makeStack(), ItemMaterials.DATA.REPELLENT.makeStack(), ItemMaterials.DATA.CAMO_POWDER.makeStack());
		addShapelessRecipe(new ItemStack(ModBlocks.varnishedPlanks), "plankWood", "slimeball", ItemMaterials.DATA.REPELLENT.makeStack(), ItemMaterials.DATA.CAMO_POWDER.makeStack());
		addShapedRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeGreen", 'z', "gemEmerald");
		addShapedRecipe(new ItemStack(ModBlocks.composter), "xyx", "xzx", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', "dyeLime", 'z', "gemEmerald");
		addShapedRecipe(new ItemStack(ModBlocks.siloSupports), "xxx", "y y", "y y", 'x', "slabWood", 'y', "fenceWood");
		addShapedRecipe(new ItemStack(ModBlocks.siloTank), "xzx", "ywy", "xzx", 'x', "ingotIron", 'y', "blockIron", 'z', ModBlocks.varnishedPlanks, 'w', ModBlocks.petrifiedWoodChest);
		addShapedRecipe(new ItemStack(ModBlocks.siloRoof), " x ", "xyx", 'x', ModBlocks.varnishedPlanks, 'y', ModBlocks.petrifiedWoodPlanks);
		addShapedRecipe(new ItemStack(ModBlocks.offeringAltar), "xwx", "yzy", "xyx", 'x', "stone", 'y', Blocks.stonebrick, 'z', Blocks.obsidian, 'w', "ingotGold");
		addShapedRecipe(new ItemStack(ModBlocks.templeTile, 4), "xx", "xx", 'x', new ItemStack(ModBlocks.templeBrick));
		addShapedRecipe(new ItemStack(ModBlocks.templePillar, 2), "x", "x", 'x', new ItemStack(ModBlocks.templeTile));
		addShapedRecipe(new ItemStack(ModBlocks.gneiss), "xx", "xx", 'x', ItemMaterials.DATA.GNEISS_ROCK.makeStack());
		addShapelessRecipe(new ItemStack(Items.dye, 2, 9), ModBlocks.waterFlower);
		addShapedRecipe(ItemMaterials.DATA.JADE.makeStack(), "xxx", "xxx", "xxx", 'x', ItemMaterials.DATA.JADE_BERRIES.makeStack());
		addShapedRecipe(ItemMaterials.DATA.WATER_REPELLENT.makeStack(), "xxx", "xrx", "xxx", 'x', ItemMaterials.DATA.HYDROFUGE.makeStack(), 'r', ItemMaterials.DATA.REPELLENT.makeStack());
		addShapelessRecipe(ItemMaterials.DATA.STEW_POT.makeStack(), Items.cauldron, "stickWood");
		addShapelessRecipe(ItemMaterials.DATA.TITAN_STEW.makeStack(), ItemMaterials.DATA.STEW_POT.makeStack(), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TITAN_CHOP_RAW.ordinal()), Items.potato, Items.carrot, new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.CABBAGE.ordinal()), "foodMushroom", "foodMushroom");
		addShapelessRecipe(ItemMaterials.DATA.TITAN_STEW.makeStack(), ItemMaterials.DATA.STEW_POT.makeStack(), Items.beef, Items.beef, Items.potato, Items.carrot, new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.CABBAGE.ordinal()), "foodMushroom", "foodMushroom");
		addShapedRecipe(new ItemStack(ModBlocks.wallPlantsCultivated, 1, 0), "dvd", "vpv", "dvd", 'd', new ItemStack(Items.dye, 1, 2), 'v', ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), 'p', new ItemStack(ModBlocks.wallPlants, 1, 0));
		addShapedRecipe(new ItemStack(ModBlocks.wallPlantsCultivated, 1, 1), "dvd", "vpv", "dvd", 'd', new ItemStack(Items.dye, 1, 4), 'v', ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), 'p', new ItemStack(ModBlocks.wallPlants, 1, 1));
		addShapedRecipe(new ItemStack(Items.leather, 8), "xx", "xx", 'x', ItemMaterials.DATA.HIDE_SHROOM.makeStack());
		addShapedRecipe(new ItemStack(Items.dye, 1, 2), "xx", "xx", 'x', new ItemStack(ModBlocks.erebusFlower, 1, FLOWER_TYPE.STEM.ordinal()));
		addShapedRecipe(ItemMaterials.DATA.AMBER_STAR.makeStack(), " x ", "xyx", " x ", 'x', ItemMaterials.DATA.SAP_BALL.makeStack(), 'y', new ItemStack(ModBlocks.amber, 1, 1));
		addShapedRecipe(new ItemStack(ModBlocks.armchair), "  w", "www", "p p", 'w', Blocks.wool, 'p', "plankWood");
		addShapedRecipe(new ItemStack(Items.rotten_flesh), "xx", "xx", 'x', ItemMaterials.DATA.PLATE_ZOMBIE_ANT.makeStack());

		// Smoothies
		// Have to figure this out...
		addShapelessRecipe(ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), Items.glass_bottle, Items.glass_bottle, Items.glass_bottle);
		addShapedRecipe(new ItemStack(ModBlocks.smoothieMaker), "xrx", "xxx", "sss", 'x', ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), 'r', new ItemStack(ModBlocks.redGem), 's', new ItemStack(ModBlocks.umberstone));

		addShapelessRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), ModItems.bucketAntiVenom, Items.glass_bottle, Items.glass_bottle);
		addShapelessRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), ModItems.bambucketAntiVenom, Items.glass_bottle, Items.glass_bottle);

		// Whetstone
		addShapedRecipe(new ItemStack(ModItems.whetstone, 1, 0), "SSS", "PPP", "UUU", 'S', Blocks.sand, 'P', ItemMaterials.DATA.PETRIFIED_WOOD.makeStack(), 'U', new ItemStack(ModBlocks.umberstone, 1, 0));
		for (int i = 1; i <= Enchantment.sharpness.getMaxLevel(); i++)
			addShapedRecipe(new ItemStack(ModItems.whetstone, 1, i), "xxx", "xyx", "xxx", 'x', ItemMaterials.DATA.WHETSTONE_POWDER.makeStack(), 'y', new ItemStack(ModItems.whetstone, 1, i - 1));

		// Special Items
		addShapedRecipe(ItemMaterials.DATA.RHINO_RIDING_KIT.makeStack(), " SX", "CCC", "LLL", 'S', Items.string, 'X', ItemMaterials.DATA.PLATE_EXO.makeStack(), 'C', new ItemStack(Blocks.carpet, 1, 0), 'L', new ItemStack(Items.dye, 1, 4));
		addShapedRecipe(ItemMaterials.DATA.BEETLE_TAMING_AMULET.makeStack(), " N ", "NJN", " F ", 'N', "nuggetGold", 'J', "gemJade", 'F', ItemMaterials.DATA.ALTAR_FRAGMENT.makeStack());
		addShapedRecipe(new ItemStack(ModItems.beeTamingAmulet), " n ", "nJn", " N ", 'n', "nuggetGold", 'J', "gemJade", 'N', ItemMaterials.DATA.NECTAR.makeStack());

		// Umbergolem parts
		addShapedRecipe(ItemMaterials.DATA.UMBERGOLEM_CORE.makeStack(), "AAA", "ARA", "AAA", 'A', ItemMaterials.DATA.ALTAR_FRAGMENT.makeStack(), 'R', ItemMaterials.DATA.RED_GEM.makeStack());
		addShapedRecipe(ItemMaterials.DATA.UMBERGOLEM_HEAD.makeStack(), "SSS", "SHS", "SMS", 'S', "stone", 'H', new ItemStack(ModItems.reinCompoundGoggles, 1), 'M', ItemMaterials.DATA.STAG_BEETLE_MANDIBLES.makeStack());
		addShapedRecipe(ItemMaterials.DATA.UMBERGOLEM_CLAW.makeStack(), "  P", "  S", " SS", 'S', "stone", 'P', ItemMaterials.DATA.SCORPION_PINCER.makeStack());
		addShapedRecipe(ItemMaterials.DATA.UMBERGOLEM_CLAW.makeStack(), "SSP", "S  ", 'S', "stone", 'P', ItemMaterials.DATA.SCORPION_PINCER.makeStack());
		addShapedRecipe(ItemMaterials.DATA.UMBERGOLEM_LEGS.makeStack(), "SSS", "S S", "R R", 'S', "stone", 'R', ItemMaterials.DATA.REINFORCED_PLATE_EXO.makeStack());
		addShapedRecipe(ItemDungeonIdols.IDOL.MUD_UMBERGOLEM.makeStack(), "XXX", "XUX", "XXX", 'X', ModBlocks.mudBricks, 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(ItemDungeonIdols.IDOL.IRON_UMBERGOLEM.makeStack(), "XXX", "XUX", "XXX", 'X', "blockIron", 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(ItemDungeonIdols.IDOL.GOLD_UMBERGOLEM.makeStack(), "XXX", "XUX", "XXX", 'X', "blockGold", 'U', ModBlocks.umberGolemStatue);
		addShapedRecipe(ItemDungeonIdols.IDOL.JADE_UMBERGOLEM.makeStack(), "XXX", "XUX", "XXX", 'X', "blockJade", 'U', ModBlocks.umberGolemStatue);

		// Umbergolem Statue
		addShapedRecipe(new ItemStack(ModBlocks.umberGolemStatue), " H ", "LCL", " X ", 'H', ItemMaterials.DATA.UMBERGOLEM_HEAD.makeStack(), 'L', ItemMaterials.DATA.UMBERGOLEM_CLAW.makeStack(), 'C', ItemMaterials.DATA.UMBERGOLEM_CORE.makeStack(), 'X', ItemMaterials.DATA.UMBERGOLEM_LEGS.makeStack());

		// Animation Magic
		addShapedRecipe(new ItemStack(ModItems.wandOfAnimation), " xy", " zx", "x  ", 'x', "ingotGold", 'y', ItemMaterials.DATA.SOUL_CRYSTAL.makeStack(), 'z', "stickWood");

		// Temporary Replacement Recipes
		addShapelessRecipe(new ItemStack(ModItems.witherWebSlinger), new ItemStack(ModItems.webSlinger), new ItemStack(Blocks.soul_sand), ItemMaterials.DATA.POISON_GLAND.makeStack(), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb));
		addShapelessRecipe(new ItemStack(ModBlocks.lightningSpeedBlock), new ItemStack(ModBlocks.velocityBlock), ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack(), ItemMaterials.DATA.SUPERNATURAL_VELOCITY.makeStack());
		addShapelessRecipe(new ItemStack(Items.dye, 1, 15), new ItemStack(ModItems.deathCompass));

	}

	private static void registerSmelting() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.amber), new ItemStack(ModBlocks.amber, 1, 1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.BEETLE_LARVA_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.GRASSHOPPER_LEG_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.GRASSHOPPER_LEG_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TARANTULA_LEG_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TARANTULA_LEG_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TITAN_CHOP_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TITAN_CHOP_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.PRICKLY_PAIR_RAW.ordinal()), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.PRICKLY_PAIR_COOKED.ordinal()), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.umberstone, 1, 1), new ItemStack(ModBlocks.umberstone), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreCoal), new ItemStack(Items.coal, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreIron), new ItemStack(Items.iron_ingot), 0.7F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreGold), new ItemStack(Items.gold_ingot), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreLapis), new ItemStack(Items.dye, 1, 4), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreDiamond), new ItemStack(Items.diamond), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreEmerald), new ItemStack(Items.emerald), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreJade), ItemMaterials.DATA.JADE.makeStack(), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreFossil), ItemMaterials.DATA.SHARD_BONE.makeStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreGneiss), ItemMaterials.DATA.GNEISS_ROCK.makeStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.orePetrifiedWood), ItemMaterials.DATA.PETRIFIED_WOOD.makeStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.oreEncrustedDiamond), new ItemStack(ModItems.encrustedDiamond), 1.0F);
		GameRegistry.addSmelting(ItemMaterials.DATA.TITAN_STEW.makeStack(), new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.TITAN_STEW_COOKED.ordinal()), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModItems.stagHeartRaw), new ItemStack(ModItems.stagHeartCooked), 1.0F);

		GameRegistry.addSmelting(new ItemStack(ModBlocks.mud), ItemMaterials.DATA.MUD_BRICK.makeStack(), 0.2F);
		GameRegistry.addSmelting(ItemMaterials.DATA.NECTAR.makeStack(), ItemMaterials.DATA.HONEY_DRIP.makeStack(), 0.2F);
		if (OreType.LEAD.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreLead), ItemMaterials.DATA.INGOT_LEAD.makeStack(), 1.0F);
		if (OreType.SILVER.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreSilver), ItemMaterials.DATA.INGOT_SILVER.makeStack(), 1.0F);
		if (OreType.COPPER.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreCopper), ItemMaterials.DATA.INGOT_COPPER.makeStack(), 1.0F);
		if (OreType.TIN.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreTin), ItemMaterials.DATA.INGOT_TIN.makeStack(), 1.0F);
		if (OreType.ALUMINIUM.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.oreAluminium), ItemMaterials.DATA.INGOT_ALUMINIUM.makeStack(), 1.0F);
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
		OreDictionary.registerOre("oreQuartz", new ItemStack(ModBlocks.oreQuartz));

		OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.umberstone, 1, 1));
		OreDictionary.registerOre("stone", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("stoneUmber", new ItemStack(ModBlocks.umberstone));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.spawnEggs, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemJade", ItemMaterials.DATA.JADE.makeStack());
		OreDictionary.registerOre("blockJade", new ItemStack(ModBlocks.jadeBlock));
		OreDictionary.registerOre("blockSpawner", ModBlocks.spiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.jumpingSpiderSpawner);
		OreDictionary.registerOre("blockSpawner", ModBlocks.waspSpawner);
		OreDictionary.registerOre("gemDiamond", ModItems.encrustedDiamond);
		OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.amber, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.saplessLog));
		OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.scorchedWood));
		OreDictionary.registerOre("bamboo", ItemMaterials.DATA.BAMBOO.makeStack());

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

		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.darkCapped));
		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.kaizersFinger));
		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.sarcasticCzech));
		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.grandmasShoes));
		OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.dutchCap));
		OreDictionary.registerOre("foodMushroom", new ItemStack(Blocks.red_mushroom));
		OreDictionary.registerOre("foodMushroom", new ItemStack(Blocks.brown_mushroom));

		//Honey
		OreDictionary.registerOre("dropHoney", ItemMaterials.DATA.HONEY_DRIP.makeStack());
		OreDictionary.registerOre("bucketHoney", new ItemStack(ModItems.bucketHoney));
		OreDictionary.registerOre("bucketHoney", new ItemStack(ModItems.bambucketHoney));

		if (OreType.LEAD.isEnabled()) {
			OreDictionary.registerOre("ingotLead", ItemMaterials.DATA.INGOT_LEAD.makeStack());
			OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.oreLead));
		}
		if (OreType.SILVER.isEnabled()) {
			OreDictionary.registerOre("ingotSilver", ItemMaterials.DATA.INGOT_SILVER.makeStack());
			OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.oreSilver));
		}
		if (OreType.COPPER.isEnabled()) {
			OreDictionary.registerOre("ingotCopper", ItemMaterials.DATA.INGOT_COPPER.makeStack());
			OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.oreCopper));
		}
		if (OreType.TIN.isEnabled()) {
			OreDictionary.registerOre("ingotTin", ItemMaterials.DATA.INGOT_TIN.makeStack());
			OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.oreTin));
		}
		if (OreType.ALUMINIUM.isEnabled()) {
			OreDictionary.registerOre("ingotAluminium", ItemMaterials.DATA.INGOT_ALUMINIUM.makeStack());
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
