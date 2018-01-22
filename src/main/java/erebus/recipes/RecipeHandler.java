package erebus.recipes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.blocks.BlockDoubleHeightPlant;
import erebus.blocks.BlockDoubleHeightPlant.EnumPlantType;
import erebus.blocks.BlockGiantFlower.EnumType;
import erebus.blocks.BlockSmallPlant.EnumSmallPlantType;
import erebus.blocks.BlockWallPlants.EnumWallPlantType;
import erebus.blocks.BlockWallPlantsCultivated.EnumWallPlantCultivatedType;
import erebus.blocks.EnumWood;
import erebus.items.ItemDungeonIdols;
import erebus.items.ItemErebusFood;
import erebus.items.ItemErebusFood.EnumFoodType;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.lib.Reference;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class RecipeHandler {
		public static final List<IRecipe> RECIPES = new ArrayList<IRecipe>();

		// Umber stuff
		public static final IRecipe UMBERPAVER = new ShapedOreRecipe(getResource("recipe_umberpaver"), new ItemStack(ModBlocks.UMBERPAVER, 4, 0), "##", "##", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 1));
		public static final IRecipe UMBERPAVER_MOSSY = new ShapedOreRecipe(getResource("recipe_umberpaver_mossy"), new ItemStack(ModBlocks.UMBERPAVER, 4, 1), "##", "##", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 2));
		public static final IRecipe UMBERPAVER_WEBBED = new ShapedOreRecipe(getResource("recipe__umberpaver_webbed"), new ItemStack(ModBlocks.UMBERPAVER, 4, 2), "##", "##", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 3));
		public static final IRecipe UMBER_FURNACE = new ShapedOreRecipe(getResource("recipe_umber_furnace"), new ItemStack(ModBlocks.UMBER_FURNACE, 1), "###", "#$#", "###", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 1), '$', Items.BUCKET);
		public static final IRecipe UMBERSTONE_BRICKS = new ShapedOreRecipe(getResource("recipe_umberstone_bricks"), new ItemStack(ModBlocks.UMBERSTONE, 4, 4), "##", "##", '#', "stoneUmber");
		public static final IRecipe UMBERSTONE_BUTTON = new ShapelessOreRecipe(getResource("recipe_umberstone_button"), new ItemStack(ModBlocks.UMBERSTONE_BUTTON, 1, 0), new ItemStack(ModBlocks.UMBERSTONE, 1, 1));
		public static final IRecipe UMBERTILE_SMOOTH = new ShapedOreRecipe(getResource("recipe_umbertile_smooth"), new ItemStack(ModBlocks.UMBERSTONE, 9, 5), "###", "###", "###", '#', "stoneUmber");
		public static final IRecipe UMBERTILE_SMOOTH_SMALL = new ShapedOreRecipe(getResource("recipe_umbertile_smooth_small"), new ItemStack(ModBlocks.UMBERSTONE, 4, 6), "##", "##", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 5));
		public static final IRecipe UMBERSTONE_PILLAR = new ShapedOreRecipe(getResource("recipe_umberstone_piller"), new ItemStack(ModBlocks.UMBERSTONE_PILLAR, 2), "#", "#", '#', "stoneUmber");

		// Petrified Wood stuffs
		public static final IRecipe PLANKS_PETRIFIED_WOOD = new ShapedOreRecipe(getResource("recipe_planks_petrified_wood"), new ItemStack(ModBlocks.PLANKS_PETRIFIED_WOOD), "xx", "xx", 'x', EnumErebusMaterialsType.PETRIFIED_WOOD.createStack());
		public static final IRecipe PETRIFIED_CRAFTING_TABLE = new ShapedOreRecipe(getResource("recipe_petrified_crafting_table"), new ItemStack(ModBlocks.PETRIFIED_CRAFTING_TABLE), "xx", "xx", 'x', ModBlocks.PLANKS_PETRIFIED_WOOD);
		public static final IRecipe PETRIFIED_WOOD_CHEST = new ShapedOreRecipe(getResource("recipe_petrified_wood_chest"), new ItemStack(ModBlocks.PETRIFIED_WOOD_CHEST), "xxx", "xyx", "xxx", 'x', ModBlocks.PLANKS_PETRIFIED_WOOD, 'y', "ingotGold");
/*
			// Stairs, slabs, walls
			for (int i = 0; i < ModBlocks.umbercobbleStairs.length; i++)
			public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.umbercobbleStairs[i], 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, i));
			X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.amberBrickStairs, 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.amber, 1, 2));
			X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.petrifiedWoodStairs, 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.petrifiedWoodPlanks, 1, 0));

			for (Block slab : ModBlocks.stoneSlabs)
				X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(slab, 6), "xxx", 'x', new ItemStack(((BlockSlabStone) slab).base, 1, ((BlockSlabStone) slab).meta));
			for (int i = 0; i < ModBlocks.gneissStairs.length; i++)
				X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.gneissStairs[i], 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.gneiss, 1, i));

		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6), "###", "###", '#', "stoneUmber");
		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6, 1), "###", "###", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 1));
		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6, 2), "###", "###", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 2));
		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6, 3), "###", "###", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 3));
		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6, 4), "###", "###", '#', new ItemStack(ModBlocks.UMBERSTONE, 1, 4));
		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6, 5), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 0));
		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6, 6), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 1));
		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6, 7), "###", "###", '#', new ItemStack(ModBlocks.umberPaver, 1, 2));
		public static final IRecipe X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.wall, 6, 8), "###", "###", '#', new ItemStack(ModBlocks.amber, 1, 2));

*/
		// Jade tools
		public static final IRecipe JADE_PICKAXE = new ShapedOreRecipe(getResource("recipe_jade_pickaxe"), new ItemStack(ModItems.JADE_PICKAXE, 1), "XXX", " # ", " # ", '#', "stickWood", 'X', "gemJade");
		public static final IRecipe JADE_SHOVEL = new ShapedOreRecipe(getResource("recipe_jade_shovel"), new ItemStack(ModItems.JADE_SHOVEL, 1), "X", "#", "#", '#', "stickWood", 'X', "gemJade");
		public static final IRecipe JADE_AXE = new ShapedOreRecipe(getResource("recipe_jade_axe"), new ItemStack(ModItems.JADE_AXE, 1), "XX", "X#", " #", '#', "stickWood", 'X', "gemJade");
		public static final IRecipe JADE_HOE = new ShapedOreRecipe(getResource("recipe_jade_hoe"), new ItemStack(ModItems.JADE_HOE, 1), "XX", " #", " #", '#', "stickWood", 'X', "gemJade");
		public static final IRecipe JADE_SWORD = new ShapedOreRecipe(getResource("recipe_jade_sword"), new ItemStack(ModItems.JADE_SWORD, 1), "X", "X", "#", '#', "stickWood", 'X', "gemJade");
		public static final IRecipe JADE_PAXEL = new ShapedOreRecipe(getResource("recipe_jade_paxel_basic"), new ItemStack(ModItems.JADE_PAXEL, 1), "XXX", "XSX", "XSX", 'X', "gemJade", 'S', "stickWood");
		public static final IRecipe RECIPE_PAXEL = new RecipePaxel();

		// Jade armor
		public static final IRecipe JADE_HELMET = new ShapedOreRecipe(getResource("recipe_jade_helmet"), new ItemStack(ModItems.JADE_HELMET, 1), "###", "# #", '#', "gemJade");
		public static final IRecipe JADE_CHESTPLATE = new ShapedOreRecipe(getResource("recipe_jade_chestplate"), new ItemStack(ModItems.JADE_CHESTPLATE, 1), "# #", "###", "###", '#', "gemJade");
		public static final IRecipe JADE_LEGGINGS = new ShapedOreRecipe(getResource("recipe_jade_leggings"), new ItemStack(ModItems.JADE_LEGGINGS, 1), "###", "# #", "# #", '#', "gemJade");
		public static final IRecipe JADE_BOOTS = new ShapedOreRecipe(getResource("recipe_jade_boots"), new ItemStack(ModItems.JADE_BOOTS, 1), "# #", "# #", '#', "gemJade");
		
		// Bamboo armor
		public static final IRecipe BAMBOO_HELMET = new ShapedOreRecipe(getResource("recipe_bamboo_helmet"), new ItemStack(ModItems.BAMBOO_HELMET, 1), "###", "# #", '#', "plankBamboo");
		public static final IRecipe BAMBOO_CHESTPLATE = new ShapedOreRecipe(getResource("recipe_bamboo_chestplate"), new ItemStack(ModItems.BAMBOO_CHESTPLATE, 1), "# #", "###", "###", '#', "plankBamboo");
		public static final IRecipe BAMBOO_LEGGINGS = new ShapedOreRecipe(getResource("recipe_bamboo_leggings"), new ItemStack(ModItems.BAMBOO_LEGGINGS, 1), "###", "# #", "# #", '#', "plankBamboo");
		public static final IRecipe BAMBOO_BOOTS = new ShapedOreRecipe(getResource("recipe_bamboo_boots"), new ItemStack(ModItems.BAMBOO_BOOTS, 1), "# #", "# #", '#', "plankBamboo");

		// Exoskeleton armor
		public static final IRecipe EXOSKELETON_HELMET = new ShapedOreRecipe(getResource("recipe_exo_helmet"), new ItemStack(ModItems.EXOSKELETON_HELMET, 1), "sss", "s s", 's', EnumErebusMaterialsType.PLATE_EXO.createStack());
		public static final IRecipe EXOSKELETON_CHESTPLATE = new ShapedOreRecipe(getResource("recipe_exo_chestplate"), new ItemStack(ModItems.EXOSKELETON_CHESTPLATE, 1), "s s", "sss", "sss", 's', EnumErebusMaterialsType.PLATE_EXO.createStack());
		public static final IRecipe EXOSKELETON_LEGGINGS = new ShapedOreRecipe(getResource("recipe_exo_leggings"), new ItemStack(ModItems.EXOSKELETON_LEGGINGS, 1), "sss", "s s", "s s", 's', EnumErebusMaterialsType.PLATE_EXO.createStack());
		public static final IRecipe EXOSKELETON_BOOTS = new ShapedOreRecipe(getResource("recipe_exo_boots"), new ItemStack(ModItems.EXOSKELETON_BOOTS, 1), "s s", "s s", 's', EnumErebusMaterialsType.PLATE_EXO.createStack());

		public static final IRecipe REINFORCED_PLATE_EXO = new ShapedOreRecipe(getResource("recipe_rein_plate_exo"), EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack(), "sss", "sss", "sss", 's', EnumErebusMaterialsType.PLATE_EXO.createStack());

		public static final IRecipe REIN_EXOSKELETON_HELMET = new ShapedOreRecipe(getResource("recipe_rein_exo_helmet"), new ItemStack(ModItems.REIN_EXOSKELETON_HELMET, 1), "sss", "s s", 's', EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack());
		public static final IRecipe REIN_EXOSKELETON_CHESTPLATE = new ShapedOreRecipe(getResource("recipe_rein_exo_chestplate"), new ItemStack(ModItems.REIN_EXOSKELETON_CHESTPLATE, 1), "s s", "sss", "sss", 's', EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack());
		public static final IRecipe REIN_EXOSKELETON_LEGGINGS = new ShapedOreRecipe(getResource("recipe_rein_exo_leggings"), new ItemStack(ModItems.REIN_EXOSKELETON_LEGGINGS, 1), "sss", "s s", "s s", 's', EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack());
		public static final IRecipe REIN_EXOSKELETON_BOOTS = new ShapedOreRecipe(getResource("recipe_rein_exo_boots"), new ItemStack(ModItems.REIN_EXOSKELETON_BOOTS, 1), "s s", "s s", 's', EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack());

		// Special armor & weapons
		public static final IRecipe COMPOUND_LENS = new ShapedOreRecipe(getResource("recipe_compound_lens"), EnumErebusMaterialsType.COMPOUND_LENS.createStack(), "GGG", "GEG", "GGG", 'E', new ItemStack(ModBlocks.AMBER_GLASS, 1, 0), 'G', EnumErebusMaterialsType.COMPOUND_EYES.createStack());
		public static final IRecipe COMPOUND_GOGGLES = new ShapedOreRecipe(getResource("recipe_compound_goggles"), new ItemStack(ModItems.COMPOUND_GOGGLES, 1), "XXX", "OXO", 'O', EnumErebusMaterialsType.COMPOUND_LENS.createStack(), 'X', EnumErebusMaterialsType.PLATE_EXO.createStack());
		public static final IRecipe REIN_COMPOUND_GOGGLES = new ShapedOreRecipe(getResource("recipe_rein_compound_goggles"), new ItemStack(ModItems.REIN_COMPOUND_GOGGLES, 1), "XXX", "XOX", 'O', new ItemStack(ModItems.COMPOUND_GOGGLES, 1), 'X', EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack());
		public static final IRecipe JUMP_BOOTS = new ShapedOreRecipe(getResource("recipe_jump_boots"), new ItemStack(ModItems.JUMP_BOOTS), "F F", "BXB", "B B", 'F', EnumErebusMaterialsType.FLY_WING.createStack(), 'B', EnumErebusMaterialsType.ELASTIC_FIBRE.createStack(), 'X', new ItemStack(ModItems.REIN_EXOSKELETON_BOOTS, 1));
		public static final IRecipe SPRINT_LEGGINGS = new ShapedOreRecipe(getResource("recipe_sprint_leggings"), new ItemStack(ModItems.SPRINT_LEGGINGS), "BBB", "BXB", "BBB", 'B', EnumErebusMaterialsType.BIO_VELOCITY.createStack(), 'X', new ItemStack(ModItems.REIN_EXOSKELETON_LEGGINGS, 1));
		public static final IRecipe GLIDER_CHESTPLATE = new ShapedOreRecipe(getResource("recipe_glider_chestplate"), new ItemStack(ModItems.GLIDER_CHESTPLATE), "GXG", 'G', EnumErebusMaterialsType.GLIDER_WING.createStack(), 'X', new ItemStack(ModItems.REIN_EXOSKELETON_CHESTPLATE, 1));
		public static final IRecipe GLIDER_CHESTPLATE_POWERED = new ShapedOreRecipe(getResource("recipe_glider_chestplate_powered"), new ItemStack(ModItems.GLIDER_CHESTPLATE_POWERED), "W W", "ECE", " V ", 'W', EnumErebusMaterialsType.ENHANCED_GLIDER_WING.createStack(), 'E', EnumErebusMaterialsType.ELASTIC_FIBRE.createStack(), 'C', new ItemStack(ModItems.GLIDER_CHESTPLATE, 1), 'V', new ItemStack(ModBlocks.VELOCITY_BLOCK, 1));
		public static final IRecipe GLIDER_DYE = new RecipeGliderDye();
		public static final IRecipe WATER_STRIDERS = new ShapedOreRecipe(getResource("recipe_water_striders"), new ItemStack(ModItems.WATER_STRIDERS), "WWW", "WXW", "WWW", 'W', EnumErebusMaterialsType.WATER_REPELLENT.createStack(), 'X', new ItemStack(ModItems.REIN_EXOSKELETON_BOOTS, 1));
		public static final IRecipe RHINO_EXOSKELETON_HELMET = new ShapedOreRecipe(getResource("recipe_rhino_exo_helmet"), new ItemStack(ModItems.RHINO_EXOSKELETON_HELMET), "h h", "sss", "s s", 's', EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack(), 'h', EnumErebusMaterialsType.RHINO_BEETLE_HORN.createStack());
		public static final IRecipe RHINO_EXOSKELETON_CHESTPLATE = new ShapedOreRecipe(getResource("recipe_rhino_exo_chestplate"), new ItemStack(ModItems.RHINO_EXOSKELETON_CHESTPLATE), "s s", "sss", "sss", 's', EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack());
		public static final IRecipe RHINO_EXOSKELETON_LEGGINGS = new ShapedOreRecipe(getResource("recipe_rhino_exo_leggings"), new ItemStack(ModItems.RHINO_EXOSKELETON_LEGGINGS), "sss", "s s", "s s", 's', EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack());
		public static final IRecipe RHINO_EXOSKELETON_BOOTS = new ShapedOreRecipe(getResource("recipe_rhino_exo_boots"), new ItemStack(ModItems.RHINO_EXOSKELETON_BOOTS), "s s", "s s", 's', EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack());
		public static final IRecipe ENHANCED_SCORPION_PINCER = new ShapedOreRecipe(getResource("recipe_enhanced_scorp_pincer"), new ItemStack(ModItems.ENHANCED_SCORPION_PINCER), "I I", "XIX", "XPX", 'I', "ingotIron", 'X', EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack(), 'P', EnumErebusMaterialsType.SCORPION_PINCER.createStack());
		public static final IRecipe ROLLED_NEWSPAPER = new ShapedOreRecipe(getResource("recipe_rolled_newspaper"), new ItemStack(ModItems.ROLLED_NEWSPAPER), "PWP", "PIP", "PWP", 'I', "dyeBlack", 'P', EnumErebusMaterialsType.PAPYRUS.createStack(), 'W', EnumErebusMaterialsType.WHETSTONE_POWDER.createStack());
		public static final IRecipe GLIDER_WING = new ShapedOreRecipe(getResource("recipe_glider_wing"), EnumErebusMaterialsType.GLIDER_WING.createStack(), "SSS", "FFF", "FFF", 'S', "stickWood", 'F', EnumErebusMaterialsType.FLY_WING.createStack());
		public static final IRecipe ENHANCED_GLIDER_WING = new ShapedOreRecipe(getResource("recipe_enhanced_glider_wing"), EnumErebusMaterialsType.ENHANCED_GLIDER_WING.createStack(), "BBB", "WWW", "WWW", 'B', EnumErebusMaterialsType.BAMBOO.createStack(), 'W', EnumErebusMaterialsType.DRAGONFLY_WING.createStack());
		public static final IRecipe WASP_DAGGER = new ShapelessOreRecipe(getResource("recipe_wasp_dagger"), new ItemStack(ModItems.WASP_DAGGER), EnumErebusMaterialsType.WASP_STING.createStack(), "stickWood");
		public static final IRecipe LEGGINGS_UPGRADES = new RecipeSprintLeggingsUpgrades();

		// Mushroom Helm & Mushroom Blocks
		public static final IRecipe MUSHROOM_HELMET = new ShapedOreRecipe(getResource("recipe_mush_helm"), new ItemStack(ModItems.MUSHROOM_HELMET, 1), "mmm", "mpm", 'm', EnumErebusMaterialsType.HIDE_SHROOM.createStack(), 'p', new ItemStack(Blocks.PUMPKIN));
		public static final IRecipe DARK_CAPPED_MUSHROOM_BLOCK = new ShapedOreRecipe(getResource("recipe_dark_capped_mush_block"), new ItemStack(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.DARK_CAPPED_MUSHROOM));
		public static final IRecipe GRANDMAS_SHOES_MUSHROOM_BLOCK = new ShapedOreRecipe(getResource("recipe_granmas_shoes_mush_block"), new ItemStack(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.GRANDMAS_SHOES_MUSHROOM));
		public static final IRecipe SARCASTIC_CZECH_MUSHROOM_BLOCK = new ShapedOreRecipe(getResource("recipe_sarcastic_czech_mush_block"), new ItemStack(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.SARCASTIC_CZECH_MUSHROOM));
		public static final IRecipe KAIZERS_FINGERS_MUSHROOM_BLOCK = new ShapedOreRecipe(getResource("recipe_kaisers_fingers_mush_block"), new ItemStack(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.KAIZERS_FINGERS_MUSHROOM));
		public static final IRecipe DUTCH_CAP_MUSHROOM_BLOCK = new ShapedOreRecipe(getResource("recipe_dutch_cap_mush_block"), new ItemStack(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.DUTCH_CAP_MUSHROOM));
		public static final IRecipe RED_MUSHROOM_BLOCK = new ShapedOreRecipe(getResource("recipe_red_mush_block"), new ItemStack(Blocks.RED_MUSHROOM_BLOCK), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.RED_MUSHROOM));
		public static final IRecipe BROWN_MUSHROOM_BLOCK = new ShapedOreRecipe(getResource("recipe_brown_mush_block"), new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK), "mmm", "mmm", "mmm", 'm', new ItemStack(Blocks.BROWN_MUSHROOM));

		// Shields
		public static final IRecipe REIN_EXO_SHIELD = new ShapedOreRecipe(getResource("recipe_rein_exo_shield"), new ItemStack(ModItems.REIN_EXOSKELETON_SHIELD, 1), "XIX", "XXX", " X ", 'I', "ingotIron", 'X', EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack());
		public static final IRecipe RHINO_EXO_SHIELD = new ShapedOreRecipe(getResource("recipe_rhino_exo_shield"), new ItemStack(ModItems.RHINO_EXOSKELETON_SHIELD, 1), "XIX", "XXX", " X ", 'I', "ingotIron", 'X', EnumErebusMaterialsType.PLATE_EXO_RHINO.createStack());
		public static final IRecipe JADE_SHIELD = new ShapedOreRecipe(getResource("recipe_jade_shield"), new ItemStack(ModItems.JADE_SHIELD, 1), "XIX", "XXX", " X ", 'I', "ingotIron", 'X', "gemJade");
		public static final IRecipe EXO_SHIELD = new ShapedOreRecipe(getResource("recipe_exo_shield"), new ItemStack(ModItems.EXOSKELETON_SHIELD, 1), "XIX", "XXX", " X ", 'I', "ingotIron", 'X', EnumErebusMaterialsType.PLATE_EXO.createStack());
		public static final IRecipe BAMBOO_SHIELD = new ShapedOreRecipe(getResource("recipe_bamboo_shield"), new ItemStack(ModItems.BAMBOO_SHIELD, 1), "XIX", "XXX", " X ", 'I', "ingotIron", 'X', EnumErebusMaterialsType.BAMBOO.createStack());

		// Red Gem
		public static final IRecipe REDSTONE = new ShapelessOreRecipe(getResource("recipe_redstone"), new ItemStack(Items.REDSTONE, 2, 0), EnumErebusMaterialsType.RED_GEM.createStack());
		public static final IRecipe RED_GEM = new ShapedOreRecipe(getResource("recipe_red_gem_block"), new ItemStack(ModBlocks.RED_GEM, 1, 0), "##", "##", '#', EnumErebusMaterialsType.RED_GEM.createStack());
		public static final IRecipe RED_GEM_LAMP = new ShapedOreRecipe(getResource("recipe_red_gem_lamp"), new ItemStack(ModBlocks.RED_GEM, 1, 1), " S ", "S#S", " S ", '#', new ItemStack(ModBlocks.RED_GEM, 1, 0), 'S', "stickWood");
		public static final IRecipe GLOW_GEM_ACTIVE = new ShapedOreRecipe(getResource("recipe_glow_gem"), new ItemStack(ModBlocks.GLOW_GEM_ACTIVE, 3, 0), "BBB", "BGB", "BBB", 'B', EnumErebusMaterialsType.BIO_LUMINESCENCE.createStack(), 'G', EnumErebusMaterialsType.RED_GEM.createStack());

		// Bamboo
		public static final IRecipe BAMBUCKET = new ShapedOreRecipe(getResource("recipe_bambucket"), new ItemStack(ModItems.BAMBUCKET, 1, 0), " S ", "B B", " B ", 'S', Items.STRING, 'B', EnumErebusMaterialsType.BAMBOO.createStack());
		public static final IRecipe BAMBOO_CRATE = new ShapedOreRecipe(getResource("recipe_bamboo_crate"), new ItemStack(ModBlocks.BAMBOO_CRATE), "bpb", "p p", "bpb", 'p', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAMBOO.ordinal()), 'b', EnumErebusMaterialsType.BAMBOO.createStack());
		public static final IRecipe BAMBOO_LADDER = new ShapedOreRecipe(getResource("recipe_bamboo_ladder"), new ItemStack(ModBlocks.BAMBOO_LADDER, 1), "BBB", "S S", "BBB", 'B', EnumErebusMaterialsType.BAMBOO.createStack(), 'S', Items.STRING);
		public static final IRecipe BAMBOO_TORCH = new ShapedOreRecipe(getResource("recipe_bamboo_torch"), new ItemStack(ModBlocks.BAMBOO_TORCH, 4), "C", "B", "B", 'C', new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE), 'B', EnumErebusMaterialsType.BAMBOO.createStack());
		public static final IRecipe BAMBOO_BRIDGE = new ShapedOreRecipe(getResource("recipe_bamboo_bridge"), new ItemStack(ModBlocks.BAMBOO_BRIDGE, 3), "SSS", "B B", "LLL", 'S', Items.STRING, 'L', new ItemStack(ModBlocks.BAMBOO_LADDER, 1), 'B', EnumErebusMaterialsType.BAMBOO.createStack());
		public static final IRecipe BAMBOO_NERD_POLE = new ShapedOreRecipe(getResource("recipe_bamboo_nerd_pole"), new ItemStack(ModBlocks.BAMBOO_NERD_POLE, 4), "S", "B", "B", 'S', "slimeball", 'B', EnumErebusMaterialsType.BAMBOO.createStack());
		public static final IRecipe BAMBOO_EXTENDER = new ShapedOreRecipe(getResource("recipe_bamboo_extender"), new ItemStack(ModBlocks.BAMBOO_EXTENDER, 1), "BSB", "PDP", "BRB", 'S', Items.STRING, 'R', "dustRedstone", 'D', Blocks.DISPENSER, 'B', EnumErebusMaterialsType.BAMBOO.createStack(), 'P', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAMBOO.ordinal()));

		public static final IRecipe BAMBOO_PIPE = new ShapedOreRecipe(getResource("recipe_bamboo_pipe"), new ItemStack(ModBlocks.BAMBOO_PIPE, 3), "  B", "HBS", "B  ", 'B', EnumErebusMaterialsType.BAMBOO.createStack(), 'S', Items.STRING, 'H', EnumErebusMaterialsType.HYDROFUGE.createStack());
		public static final IRecipe BAMBOO_PIPE_EXTRACT = new ShapelessOreRecipe(getResource("recipe_bamboo_pipe_extract"), new ItemStack(ModBlocks.BAMBOO_PIPE_EXTRACT, 1), new ItemStack(ModBlocks.BAMBOO_PIPE), new ItemStack(Blocks.LEVER));
		public static final IRecipe BAMBOO_PIPE_WRENCH = new ShapedOreRecipe(getResource("recipe_bamboo_pipe_wrench"), EnumErebusMaterialsType.BAMBOO_PIPE_WRENCH.createStack(), "B B", " P ", " B ", 'B', EnumErebusMaterialsType.BAMBOO.createStack(), 'P', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAMBOO.ordinal()));

		public static final IRecipe BAMBOO_SOUP = new ShapelessOreRecipe(getResource("recipe_bamboo_soup"), EnumFoodType.BAMBOO_SOUP.createStack(), new ItemStack(Items.BOWL), EnumErebusMaterialsType.BAMBOO.createStack(), new ItemStack(EnumWood.BAMBOO.getSapling()));
		public static final IRecipe LARVAE_ON_STICK = new ShapelessOreRecipe(getResource("recipe_larvae_on_a_stick"), EnumFoodType.LARVAE_ON_STICK.createStack(), "stickWood", EnumFoodType.BEETLE_LARVA_COOKED.createStack(), EnumFoodType.BEETLE_LARVA_COOKED.createStack(), EnumFoodType.BEETLE_LARVA_COOKED.createStack());
		public static final IRecipe HONEY_SANDWICH = new ShapedOreRecipe(getResource("recipe_honey_sandwich"), new ItemStack(ModItems.EREBUS_FOOD, 2, ItemErebusFood.EnumFoodType.HONEY_SANDWICH.ordinal()), " B ", "RRR", " B ", 'B', new ItemStack(Items.BREAD), 'R', "dropHoney");
		public static final IRecipe HONEY_TREAT = new ShapedOreRecipe(getResource("recipe_honey_treat"), new ItemStack(ModBlocks.HONEY_TREAT, 1), "SRS", "RBR", "SRS", 'S', new ItemStack(Items.SUGAR), 'B', new ItemStack(Items.BREAD), 'R', "dropHoney");

		// Miscellaneous
		public static final IRecipe MIR_BRICK_1 = new ShapedOreRecipe(getResource("recipe_mirbrick_1"), new ItemStack(ModBlocks.MIR_BRICK), "xy", "yx", 'x', Items.CLAY_BALL, 'y', EnumErebusMaterialsType.MUD_BRICK.createStack());
		public static final IRecipe MIR_BRICK_2 = new ShapedOreRecipe(getResource("recipe_mirbrick_2"), new ItemStack(ModBlocks.MIR_BRICK), "xy", "yx", 'y', Items.CLAY_BALL, 'x', EnumErebusMaterialsType.MUD_BRICK.createStack());
		public static final IRecipe MIR_BRICK_3 = new ShapedOreRecipe(getResource("recipe_mirbrick_3"), new ItemStack(ModBlocks.MIR_BRICK, 4), "xy", "yx", 'x', Blocks.CLAY, 'y', ModBlocks.MUD_BRICK);
		public static final IRecipe MIR_BRICK_4 = new ShapedOreRecipe(getResource("recipe_mirbrick_4"), new ItemStack(ModBlocks.MIR_BRICK, 4), "xy", "yx", 'y', Blocks.CLAY, 'x', ModBlocks.MUD_BRICK);
		public static final IRecipe SILK = new ShapedOreRecipe(getResource("recipe_silk"), new ItemStack(ModBlocks.SILK, 1), "sss", "sss", "sss", 's', Items.STRING);
		public static final IRecipe AMBER_BRICKS = new ShapedOreRecipe(getResource("recipe_amber_bricks"), new ItemStack(ModBlocks.AMBER_BRICKS, 4, 0), "ss", "ss", 's', new ItemStack(ModBlocks.AMBER, 1, 0));
		public static final IRecipe STRING = new ShapelessOreRecipe(getResource("recipe_string"),  new ItemStack(Items.STRING, 9), new ItemStack(ModBlocks.SILK));
		public static final IRecipe BONEMEAL = new ShapelessOreRecipe(getResource("recipe_bonemeal"), new ItemStack(Items.DYE, 1, 15), EnumErebusMaterialsType.SHARD_BONE.createStack());
		public static final IRecipe ARROW_1 = new ShapedOreRecipe(getResource("recipe_arrow_1"), new ItemStack(Items.ARROW, 4), "T", "S", "F", 'F', new ItemStack(Items.FEATHER, 1, 0), 'S', "stickWood", 'T', EnumErebusMaterialsType.SHARD_BONE.createStack());
		public static final IRecipe ARROW_2 = new ShapedOreRecipe(getResource("recipe_arrow_2"), new ItemStack(Items.ARROW, 4), "T", "S", "F", 'F', EnumErebusMaterialsType.FLY_WING.createStack(), 'S', "stickWood", 'T', EnumErebusMaterialsType.SHARD_BONE.createStack());
		public static final IRecipe ARROW_3 = new ShapedOreRecipe(getResource("recipe_arrow_3"), new ItemStack(Items.ARROW, 4), "T", "S", "F", 'F', EnumErebusMaterialsType.FLY_WING.createStack(), 'S', "stickWood", 'T', Items.FLINT);
		public static final IRecipe SPRAY_CAN = new ShapedOreRecipe(getResource("recipe_spray_can"), new ItemStack(ModItems.SPRAY_CAN, 9), " B ", "XRX", "XXX", 'X', "ingotIron", 'B', "buttonWood", 'R', EnumErebusMaterialsType.REPELLENT.createStack());
		public static final IRecipe ALTAR_BASE = new ShapedOreRecipe(getResource("recipe_altar_base"), new ItemStack(ModBlocks.ALTAR_BASE, 1), "XXX", "XOX", "XXX", 'O', Blocks.OBSIDIAN, 'X', EnumErebusMaterialsType.ALTAR_FRAGMENT.createStack());
		public static final IRecipe GLOWING_JAR = new ShapedOreRecipe(getResource("recipe_glowing_jar"), new ItemStack(ModBlocks.GLOWING_JAR, 1), "XXX", "GBG", "GGG", 'X', "ingotIron", 'G', new ItemStack(ModBlocks.AMBER_GLASS, 1, 0), 'B', EnumErebusMaterialsType.BIO_LUMINESCENCE.createStack());
		public static final IRecipe LIQUIFIER = new ShapedOreRecipe(getResource("recipe_liquifier"), new ItemStack(ModBlocks.LIQUIFIER, 1), "RBR", "SGS", "RJR", 'B', "plankBamboo", 'S', "stickWood", 'J', new ItemStack(ModBlocks.FLUID_JAR), 'G', new ItemStack(ModBlocks.AMBER_GLASS, 1, 0), 'R', "dustRedstone");

		public static final IRecipe REIN_EXO = new ShapelessOreRecipe(getResource("recipe_rein_exo"), new ItemStack(ModBlocks.REIN_EXO, 1), EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack(), EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack(), EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack(), EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack());
		public static final IRecipe BOOK = new ShapelessOreRecipe(getResource("recipe_book"), new ItemStack(Items.BOOK, 1, 0), EnumErebusMaterialsType.PLATE_EXO.createStack(), Items.PAPER, Items.PAPER, Items.PAPER);
		public static final IRecipe PAPER = new ShapelessOreRecipe(getResource("recipe_paper"), new ItemStack(Items.PAPER, 4), EnumErebusMaterialsType.PAPYRUS.createStack(), EnumErebusMaterialsType.PAPYRUS.createStack());
		public static final IRecipe VELOCITY_BLOCK = new ShapedOreRecipe(getResource("recipe_velocity_block"), new ItemStack(ModBlocks.VELOCITY_BLOCK), "xxx", "xxx", "xxx", 'x', EnumErebusMaterialsType.BIO_VELOCITY.createStack());
		public static final IRecipe MUD_BRICK = new ShapedOreRecipe(getResource("recipe_mud_brick"), new ItemStack(ModBlocks.MUD_BRICK), "xx", "xx", 'x', EnumErebusMaterialsType.MUD_BRICK.createStack());
		public static final IRecipe HOMING_BEECON = new ShapedOreRecipe(getResource("recipe_homing_beecon"), new ItemStack(ModItems.HOMING_BEECON), "GNG", "NCN", "GNG", 'N', EnumErebusMaterialsType.NECTAR.createStack(), 'G', "ingotGold", 'C', Items.COMPASS);
		public static final IRecipe NECTAR_COLLECTOR = new ShapedOreRecipe(getResource("recipe_nectar_collector"), new ItemStack(ModItems.NECTAR_COLLECTOR), "  B", " S ", "S  ", 'B', Items.BOWL, 'S', "stickWood");
		public static final IRecipe FLUID_JAR = new ShapedOreRecipe(getResource("recipe_fluid_jar"), new ItemStack(ModBlocks.FLUID_JAR), "JJJ", "$0$", "$$$", 'J', "gemJade", '$', new ItemStack(ModBlocks.AMBER_GLASS, 1, 0), '0', EnumErebusMaterialsType.NECTAR.createStack());
		public static final IRecipe JADE_BLOCK = new ShapedOreRecipe(getResource("recipe_jade_block"), new ItemStack(ModBlocks.JADE_BLOCK), "xxx", "xxx", "xxx", 'x', "gemJade");
		public static final IRecipe JADE = new ShapelessOreRecipe(getResource("recipe_jade"), EnumErebusMaterialsType.JADE.createStack(9), "blockJade");
		public static final IRecipe MUCUS_CHARGE = new ShapedOreRecipe(getResource("recipe_mucus_charge"), EnumErebusMaterialsType.MUCUS_CHARGE.createStack(), "SSS", "SRS", "SSS", 'S', "slimeball", 'R', EnumErebusMaterialsType.REPELLENT.createStack());
		public static final IRecipe MUCUS_BOMB = new ShapedOreRecipe(getResource("recipe_mucus_bomb"), new ItemStack(ModBlocks.MUCUS_BOMB, 1), "MMM", "MTM", "MMM", 'M', EnumErebusMaterialsType.MUCUS_CHARGE.createStack(), 'T', Blocks.TNT);
		public static final IRecipe HONEY_COMB = new ShapedOreRecipe(getResource("recipe_hony_comb"), new ItemStack(ModBlocks.HONEY_COMB, 1), "NPN", "PCP", "NPN", 'P', EnumErebusMaterialsType.PAPYRUS.createStack(), 'C', "chestWood", 'N', EnumErebusMaterialsType.NECTAR.createStack());
		public static final IRecipe BLAZE_POWDER = new ShapedOreRecipe(getResource("recipe_blaze_powder"), new ItemStack(Items.BLAZE_POWDER, 1), "FFF", "FFF", "FFF", 'F', new ItemStack(ModBlocks.SMALL_PLANT, 1, EnumSmallPlantType.FIRE_BLOOM.ordinal()));
		public static final IRecipe MOSS_BALL = new ShapedOreRecipe(getResource("recipe_moss_ball"), EnumErebusMaterialsType.MOSS_BALL.createStack(), "mmm", "mmm", "mmm", 'm', new ItemStack(ModBlocks.WALL_PLANTS, 1, 0));
		public static final IRecipe GLOWSHROOM_1 = new ShapelessOreRecipe(getResource("recipe_glowshroom_1"), new ItemStack(ModBlocks.GLOWSHROOM), EnumErebusMaterialsType.GLOWSHROOM.createStack(), Blocks.TORCH);
		public static final IRecipe GLOWSHROOM_2 = new ShapelessOreRecipe(getResource("recipe_glowshroom_2"), new ItemStack(ModBlocks.GLOWSHROOM), EnumErebusMaterialsType.GLOWSHROOM.createStack(), EnumErebusMaterialsType.BIO_LUMINESCENCE.createStack());
		public static final IRecipe PORTAL_ACTIVATOR = new ShapedOreRecipe(getResource("recipe_portal_activator"), new ItemStack(ModItems.PORTAL_ACTIVATOR), "VSE", "VSS", "GVV", 'V', Blocks.VINE, 'S', "stickWood", 'E', EnumErebusMaterialsType.GAEAN_GEM.createStack(), 'G', "ingotGold");
		public static final IRecipe GAEAN_KEYSTONE = new ShapedOreRecipe(getResource("recipe_gaean_keystone"), new ItemStack(ModBlocks.GAEAN_KEYSTONE), "V V", "SOS", "SSS", 'V', Blocks.VINE, 'S', Blocks.STONEBRICK, 'O', Blocks.OBSIDIAN);
		public static final IRecipe ANT_TAMING_AMULET = new ShapedOreRecipe(getResource("recipe_ant_amulet"), new ItemStack(ModItems.ANT_TAMING_AMULET), "pgp", "gog", "pgp", 'p', EnumErebusMaterialsType.ANT_PHEROMONES.createStack(), 'g', "ingotGold", 'o', Blocks.OBSIDIAN);
		public static final IRecipe PLANTICIDE = new ShapelessOreRecipe(getResource("recipe_planticide"), new ItemStack(ModItems.PLANTICIDE, 2), EnumErebusMaterialsType.POISON_GLAND.createStack(), "slimeball", "dyeWhite");
		public static final IRecipe COMPOSTER_1 = new ShapedOreRecipe(getResource("recipe_composter_1"), new ItemStack(ModBlocks.COMPOSTER), "xyx", "xzx", "xyx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()), 'y', "dyeGreen", 'z', "gemEmerald");
		public static final IRecipe COMPOSTER_2 = new ShapedOreRecipe(getResource("recipe_composter_2"), new ItemStack(ModBlocks.COMPOSTER), "xyx", "xzx", "xyx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()), 'y', "dyeLime", 'z', "gemEmerald");
		public static final IRecipe SILO_SUPPORTS = new ShapedOreRecipe(getResource("recipe_silo_supports"), new ItemStack(ModBlocks.SILO_SUPPORTS), "xxx", "y y", "y y", 'x', "slabWood", 'y', "fenceWood");
		public static final IRecipe SILO_TANK = new ShapedOreRecipe(getResource("recipe_silo_tank"), new ItemStack(ModBlocks.SILO_TANK), "xzx", "ywy", "xzx", 'x', "ingotIron", 'y', "blockIron", 'z', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()), 'w', ModBlocks.PETRIFIED_WOOD_CHEST);
		public static final IRecipe SILO_ROOF = new ShapedOreRecipe(getResource("recipe_silo_roof"), new ItemStack(ModBlocks.SILO_ROOF), " x ", "xyx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()), 'y', ModBlocks.PLANKS_PETRIFIED_WOOD);
		public static final IRecipe ALTAR_OFFERING = new ShapedOreRecipe(getResource("recipe_altar_offering"), new ItemStack(ModBlocks.ALTAR_OFFERING), "xwx", "yzy", "xyx", 'x', "stone", 'y', Blocks.STONEBRICK, 'z', Blocks.OBSIDIAN, 'w', "ingotGold");
		public static final IRecipe TEMPLE_TILE = new ShapedOreRecipe(getResource("recipe_temple_tile"), new ItemStack(ModBlocks.TEMPLE_TILE, 4), "xx", "xx", 'x', new ItemStack(ModBlocks.TEMPLE_BRICK));
		public static final IRecipe TEMPLE_PILLAR = new ShapedOreRecipe(getResource("recipe_temple_pillar"), new ItemStack(ModBlocks.TEMPLE_PILLAR, 2), "x", "x", 'x', new ItemStack(ModBlocks.TEMPLE_TILE));
		public static final IRecipe GNEISS = new ShapedOreRecipe(getResource("recipe_gneiss"), new ItemStack(ModBlocks.GNEISS), "xx", "xx", 'x', EnumErebusMaterialsType.GNEISS_ROCK.createStack());
		public static final IRecipe PINK_DYE = new ShapelessOreRecipe(getResource("recipe_pink_dye"), new ItemStack(Items.DYE, 2, 9), ModBlocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoubleHeightPlant.VARIANT, EnumPlantType.TALL_BLOOM).getBlock());
		public static final IRecipe BERRIES_TO_JADE = new ShapedOreRecipe(getResource("recipe_berries_to_jade"), EnumErebusMaterialsType.JADE.createStack(), "xxx", "xxx", "xxx", 'x', EnumErebusMaterialsType.JADE_BERRIES.createStack());
		public static final IRecipe WATER_REPELLENT = new ShapedOreRecipe(getResource("recipe_water_repellent"), EnumErebusMaterialsType.WATER_REPELLENT.createStack(), "xxx", "xrx", "xxx", 'x', EnumErebusMaterialsType.HYDROFUGE.createStack(), 'r', EnumErebusMaterialsType.REPELLENT.createStack());
		public static final IRecipe STEW_POT = new ShapelessOreRecipe(getResource("recipe_stew_pot"),  EnumErebusMaterialsType.STEW_POT.createStack(), Items.CAULDRON, "stickWood");
		public static final IRecipe TITAN_STEW_1 = new ShapelessOreRecipe(getResource("recipe_titan_stew_1"), EnumErebusMaterialsType.TITAN_STEW.createStack(), EnumErebusMaterialsType.STEW_POT.createStack(), EnumFoodType.TITAN_CHOP_RAW.createStack(), Items.POTATO, Items.CARROT, EnumFoodType.CABBAGE.createStack(), "foodMushroom", "foodMushroom");
		public static final IRecipe TITAN_STEW_2 = new ShapelessOreRecipe(getResource("recipe_titan_stew_2"), EnumErebusMaterialsType.TITAN_STEW.createStack(), EnumErebusMaterialsType.STEW_POT.createStack(), Items.BEEF, Items.BEEF, Items.POTATO, Items.CARROT, EnumFoodType.CABBAGE.createStack(), "foodMushroom", "foodMushroom");
		public static final IRecipe CULTIVATED_MOSS = new ShapedOreRecipe(getResource("recipe_cultivated_moss"), new ItemStack(ModBlocks.WALL_PLANTS_CULTIVATED, 1, EnumWallPlantCultivatedType.MOSS_DOWN_CULTIVATED.ordinal()), "dvd", "vpv", "dvd", 'd', new ItemStack(Items.DYE, 1, 2), 'v', EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), 'p', new ItemStack(ModBlocks.WALL_PLANTS, 1, EnumWallPlantType.MOSS_DOWN.ordinal()));
		public static final IRecipe CULTIVATED_MOULD = new ShapedOreRecipe(getResource("recipe_cultivated_mould"), new ItemStack(ModBlocks.WALL_PLANTS_CULTIVATED, 1, EnumWallPlantCultivatedType.MOULD_DOWN_CULTIVATED.ordinal()), "dvd", "vpv", "dvd", 'd', new ItemStack(Items.DYE, 1, 4), 'v', EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), 'p', new ItemStack(ModBlocks.WALL_PLANTS, 1, EnumWallPlantType.MOULD_DOWN.ordinal()));
		public static final IRecipe LEATHER = new ShapedOreRecipe(getResource("recipe_leather"), new ItemStack(Items.LEATHER, 8), "xx", "xx", 'x', EnumErebusMaterialsType.HIDE_SHROOM.createStack());
		public static final IRecipe DYE_GREEN_1 = new ShapedOreRecipe(getResource("recipe_green_dye_1"), new ItemStack(Items.DYE, 1, 2), "xx", "xx", 'x', new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.STEM.ordinal()));
		public static final IRecipe DYE_GREEN_2 = new ShapedOreRecipe(getResource("recipe_green_dye_2"), new ItemStack(Items.DYE, 1, 2), "xx", "xx", 'x', new ItemStack(ModBlocks.GIANT_LILY_PAD, 1));
		public static final IRecipe DYE_GREEN_3 = new ShapedOreRecipe(getResource("recipe_green_dye_3"), new ItemStack(Items.DYE, 1, 2), "xxx", "xxx", "xxx", 'x', EnumFoodType.PRICKLY_PEAR_RAW.createStack());
		public static final IRecipe AMBER_STAR = new ShapedOreRecipe(getResource("recipe_amber_star"), EnumErebusMaterialsType.AMBER_STAR.createStack(), " x ", "xyx", " x ", 'x', EnumErebusMaterialsType.RESIN.createStack(), 'y', new ItemStack(ModBlocks.AMBER_GLASS, 1, 0));
		//	public static final IRecipe ARMCHAIR = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.armchair), "  w", "www", "p p", 'w', Blocks.wool, 'p', "plankWood");
		public static final IRecipe ROTTEN_FLESH = new ShapedOreRecipe(getResource("recipe_rotten_flesh"), new ItemStack(Items.ROTTEN_FLESH), "xx", "xx", 'x', EnumErebusMaterialsType.PLATE_ZOMBIE_ANT.createStack());

		// Smoothies
		// Have to figure this out...
		public static final IRecipe SMOOTHIE_GLASS = new ShapelessOreRecipe(getResource("recipe_smoothie_glass"), EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
		public static final IRecipe SMOOTHIE_MAKER = new ShapedOreRecipe(getResource("recipe_smoothie_maker"), new ItemStack(ModBlocks.SMOOTHIE_MAKER), "xrx", "xxx", "sss", 'x', EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), 'r', new ItemStack(ModBlocks.RED_GEM), 's', new ItemStack(ModBlocks.UMBERSTONE));

		public static final IRecipe ANTI_VENOM_1 = new ShapelessOreRecipe(getResource("recipe_anti_venom_1"), new ItemStack(ModItems.ANTI_VENOM_BOTTLE, 2), FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid("anti_venom"), Fluid.BUCKET_VOLUME)), Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
		public static final IRecipe ANTI_VENOM_2 = new ShapelessOreRecipe(getResource("recipe_anti_venom_2"), new ItemStack(ModItems.ANTI_VENOM_BOTTLE, 2), ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("anti_venom"), Fluid.BUCKET_VOLUME)), Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);

		// Whetstone
		public static final IRecipe WHETSTONE_BASE = new ShapedOreRecipe(getResource("recipe_whetstone_base"), new ItemStack(ModItems.WHETSTONE, 1, 0), "SSS", "PPP", "UUU", 'S', Blocks.SAND, 'P', EnumErebusMaterialsType.PETRIFIED_WOOD.createStack(), 'U', new ItemStack(ModBlocks.UMBERSTONE, 1, 0));
		public static final IRecipe WHETSTONE_1 = new ShapedOreRecipe(getResource("recipe_whetstone_1"), new ItemStack(ModItems.WHETSTONE, 1, 1), "xxx", "xyx", "xxx", 'x', EnumErebusMaterialsType.WHETSTONE_POWDER.createStack(), 'y', new ItemStack(ModItems.WHETSTONE, 1, 0));
		public static final IRecipe WHETSTONE_2 = new ShapedOreRecipe(getResource("recipe_whetstone_2"), new ItemStack(ModItems.WHETSTONE, 1, 2), "xxx", "xyx", "xxx", 'x', EnumErebusMaterialsType.WHETSTONE_POWDER.createStack(), 'y', new ItemStack(ModItems.WHETSTONE, 1, 1));
		public static final IRecipe WHETSTONE_3 = new ShapedOreRecipe(getResource("recipe_whetstone_3"), new ItemStack(ModItems.WHETSTONE, 1, 3), "xxx", "xyx", "xxx", 'x', EnumErebusMaterialsType.WHETSTONE_POWDER.createStack(), 'y', new ItemStack(ModItems.WHETSTONE, 1, 2));
		public static final IRecipe WHETSTONE_4 = new ShapedOreRecipe(getResource("recipe_whetstone_4"), new ItemStack(ModItems.WHETSTONE, 1, 4), "xxx", "xyx", "xxx", 'x', EnumErebusMaterialsType.WHETSTONE_POWDER.createStack(), 'y', new ItemStack(ModItems.WHETSTONE, 1, 3));
		public static final IRecipe WHETSTONE_5 = new ShapedOreRecipe(getResource("recipe_whetstone_5"), new ItemStack(ModItems.WHETSTONE, 1, 5), "xxx", "xyx", "xxx", 'x', EnumErebusMaterialsType.WHETSTONE_POWDER.createStack(), 'y', new ItemStack(ModItems.WHETSTONE, 1, 4));

		// Special Items
		public static final IRecipe BEETLE_RIDING_KIT = new ShapedOreRecipe(getResource("recipe_beetle_riding_kit"), EnumErebusMaterialsType.BEETLE_RIDING_KIT.createStack(), " SX", "CCC", "LLL", 'S', Items.STRING, 'X', EnumErebusMaterialsType.PLATE_EXO.createStack(), 'C', new ItemStack(Blocks.CARPET, 1, 0), 'L', new ItemStack(Items.DYE, 1, 4));
		public static final IRecipe BEETLE_TAMING_AMULET = new ShapedOreRecipe(getResource("recipe_beetle_taming_amulet"), EnumErebusMaterialsType.BEETLE_TAMING_AMULET.createStack(), " N ", "NJN", " F ", 'N', "nuggetGold", 'J', "gemJade", 'F', EnumErebusMaterialsType.ALTAR_FRAGMENT.createStack());
		public static final IRecipe BEE_TAMING_AMULET = new ShapedOreRecipe(getResource("recipe_bee_taming_amulet"), new ItemStack(ModItems.BEE_TAMING_AMULET), " n ", "nJn", " N ", 'n', "nuggetGold", 'J', "gemJade", 'N', EnumErebusMaterialsType.NECTAR.createStack());

		// Umbergolem parts
		public static final IRecipe UMBERGOLEM_CORE = new ShapedOreRecipe(getResource("recipe_umbergolem_core"), EnumErebusMaterialsType.UMBERGOLEM_CORE.createStack(), "AAA", "ARA", "AAA", 'A', EnumErebusMaterialsType.ALTAR_FRAGMENT.createStack(), 'R', EnumErebusMaterialsType.RED_GEM.createStack());
		public static final IRecipe UMBERGOLEM_HEAD = new ShapedOreRecipe(getResource("recipe_umbergolem_head"), EnumErebusMaterialsType.UMBERGOLEM_HEAD.createStack(), "SSS", "SHS", "SMS", 'S', "stone", 'H', new ItemStack(ModItems.REIN_COMPOUND_GOGGLES, 1), 'M', EnumErebusMaterialsType.STAG_BEETLE_MANDIBLES.createStack());
		public static final IRecipe UMBERGOLEM_CLAW_1 = new ShapedOreRecipe(getResource("recipe_umbergolem_claw_1"), EnumErebusMaterialsType.UMBERGOLEM_CLAW.createStack(), "  P", "  S", " SS", 'S', "stone", 'P', EnumErebusMaterialsType.SCORPION_PINCER.createStack());
		public static final IRecipe UMBERGOLEM_CLAW_2 = new ShapedOreRecipe(getResource("recipe_umbergolem_claw_2"), EnumErebusMaterialsType.UMBERGOLEM_CLAW.createStack(), "SSP", "S  ", 'S', "stone", 'P', EnumErebusMaterialsType.SCORPION_PINCER.createStack());
		public static final IRecipe UMBERGOLEM_LEGS = new ShapedOreRecipe(getResource("recipe_umbergolem_legs"), EnumErebusMaterialsType.UMBERGOLEM_LEGS.createStack(), "SSS", "S S", "R R", 'S', "stone", 'R', EnumErebusMaterialsType.REINFORCED_PLATE_EXO.createStack());
		public static final IRecipe IDOL_MUD_UMBERGOLEM = new ShapedOreRecipe(getResource("recipe_umbergolem_mud"), ItemDungeonIdols.EnumIdolType.IDOL_MUD_UMBERGOLEM.createStack(), "XXX", "XUX", "XXX", 'X', ModBlocks.MUD_BRICK, 'U', new ItemStack(ModBlocks.UMBER_GOLEM_STATUE));
		public static final IRecipe IDOL_IRON_UMBERGOLEM = new ShapedOreRecipe(getResource("recipe_umbergolem_iron"), ItemDungeonIdols.EnumIdolType.IDOL_IRON_UMBERGOLEM.createStack(), "XXX", "XUX", "XXX", 'X', "blockIron", 'U', new ItemStack(ModBlocks.UMBER_GOLEM_STATUE));
		public static final IRecipe IDOL_GOLD_UMBERGOLEM = new ShapedOreRecipe(getResource("recipe_umbergolem_gold"), ItemDungeonIdols.EnumIdolType.IDOL_GOLD_UMBERGOLEM.createStack(), "XXX", "XUX", "XXX", 'X', "blockGold", 'U', new ItemStack(ModBlocks.UMBER_GOLEM_STATUE));
		public static final IRecipe IDOL_JADE_UMBERGOLEM = new ShapedOreRecipe(getResource("recipe_umbergolem_jade"), ItemDungeonIdols.EnumIdolType.IDOL_JADE_UMBERGOLEM.createStack(), "XXX", "XUX", "XXX", 'X', "blockJade", 'U', new ItemStack(ModBlocks.UMBER_GOLEM_STATUE));

		// Umbergolem Statue
		public static final IRecipe UMBER_GOLEM_STATUE = new ShapedOreRecipe(getResource("recipe_umbergolem_statue"), new ItemStack(ModBlocks.UMBER_GOLEM_STATUE), " H ", "LCL", " X ", 'H', EnumErebusMaterialsType.UMBERGOLEM_HEAD.createStack(), 'L', EnumErebusMaterialsType.UMBERGOLEM_CLAW.createStack(), 'C', EnumErebusMaterialsType.UMBERGOLEM_CORE.createStack(), 'X', EnumErebusMaterialsType.UMBERGOLEM_LEGS.createStack());

		// Animation Magic
		public static final IRecipe WAND_OF_ANIMATION = new ShapedOreRecipe(getResource("recipe_wans_of_animation"), new ItemStack(ModItems.WAND_OF_ANIMATION), " xy", " zx", "x  ", 'x', "ingotGold", 'y', EnumErebusMaterialsType.SOUL_CRYSTAL.createStack(), 'z', "stickWood");

		// Temporary Replacement Recipes
		public static final IRecipe WEB_SLINGER_WITHER = new ShapelessOreRecipe(getResource("recipe_web_slinger_wither"), new ItemStack(ModItems.WEB_SLINGER_WITHER), new ItemStack(ModItems.WEB_SLINGER), new ItemStack(Blocks.SOUL_SAND), EnumErebusMaterialsType.POISON_GLAND.createStack(), new ItemStack(ModBlocks.WITHER_WEB), new ItemStack(ModBlocks.WITHER_WEB), new ItemStack(ModBlocks.WITHER_WEB));
		public static final IRecipe LIGHTNING_SPEED_BLOCK = new ShapelessOreRecipe(getResource("recipe_lightning_speed_block"), new ItemStack(ModBlocks.LIGHTNING_SPEED_BLOCK), new ItemStack(ModBlocks.VELOCITY_BLOCK), EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack(), EnumErebusMaterialsType.SUPERNATURAL_VELOCITY.createStack());
		public static final IRecipe COMPASS_TO_BONEMEAL = new ShapelessOreRecipe(getResource("recipe_compass_to_bonemeal"), new ItemStack(Items.DYE, 1, 15), new ItemStack(ModItems.DEATH_COMPASS));

		// WOOD PITA GRRRR
		// Planks
		public static final IRecipe PLANKS_BAOBAB = new ShapelessOreRecipe(getResource("recipe_planks_baobab"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.BAOBAB.ordinal()), new ItemStack(EnumWood.BAOBAB.getLog()));
		public static final IRecipe PLANKS_EUCALYPTUS = new ShapelessOreRecipe(getResource("recipe_planks_eucalyptus"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.EUCALYPTUS.ordinal()), new ItemStack(EnumWood.EUCALYPTUS.getLog()));
		public static final IRecipe PLANKS_MAHOGANY = new ShapelessOreRecipe(getResource("recipe_planks_mahogany"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.MAHOGANY.ordinal()), new ItemStack(EnumWood.MAHOGANY.getLog()));
		public static final IRecipe PLANKS_MOSSBARK = new ShapelessOreRecipe(getResource("recipe_planks_mossbark"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.MOSSBARK.ordinal()), new ItemStack(EnumWood.MOSSBARK.getLog()));
		public static final IRecipe PLANKS_ASPER = new ShapelessOreRecipe(getResource("recipe_planks_asper"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.ASPER.ordinal()), new ItemStack(EnumWood.ASPER.getLog()));
		public static final IRecipe PLANKS_CYPRESS = new ShapelessOreRecipe(getResource("recipe_planks_cypress"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.CYPRESS.ordinal()), new ItemStack(EnumWood.CYPRESS.getLog()));
		public static final IRecipe PLANKS_BALSAM_1 = new ShapelessOreRecipe(getResource("recipe_planks_balsam_1"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.BALSAM.ordinal()), new ItemStack(EnumWood.BALSAM.getLog()));
		public static final IRecipe PLANKS_BALSAM_2 = new ShapelessOreRecipe(getResource("recipe_planks_balsam_2"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.BALSAM.ordinal()), new ItemStack(ModBlocks.LOG_BALSAM_RESINLESS));
		public static final IRecipe PLANKS_WHITE = new ShapelessOreRecipe(getResource("recipe_planks_white"), new ItemStack(ModBlocks.PLANKS, 1, EnumWood.WHITE.ordinal()), "plankWood", "dyeWhite");
		public static final IRecipe PLANKS_BAMBOO = new ShapedOreRecipe(getResource("recipe_planks_bamboo"), new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAMBOO.ordinal()), "##", "##", '#', EnumErebusMaterialsType.BAMBOO.createStack());
		public static final IRecipe PLANKS_ROTTEN = new ShapelessOreRecipe(getResource("recipe_planks_rotten"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.ROTTEN.ordinal()), new ItemStack(EnumWood.ROTTEN.getLog()));
		public static final IRecipe PLANKS_MARSHWOOD = new ShapelessOreRecipe(getResource("recipe_planks_marshwood"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.MARSHWOOD.ordinal()), new ItemStack(EnumWood.MARSHWOOD.getLog()));
		public static final IRecipe PLANKS_SCORCHED = new ShapelessOreRecipe(getResource("recipe_planks_scorched"), new ItemStack(ModBlocks.PLANKS, 4, EnumWood.SCORCHED.ordinal()),  new ItemStack(EnumWood.SCORCHED.getLog()));
		public static final IRecipe PLANKS_VARNISHED_1 = new ShapelessOreRecipe(getResource("recipe_planks_varnished_1"), new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()), "plankWood", EnumErebusMaterialsType.RESIN.createStack(), EnumErebusMaterialsType.REPELLENT.createStack(), EnumErebusMaterialsType.CAMO_POWDER.createStack());
		public static final IRecipe PLANKS_VARNISHED_2 = new ShapelessOreRecipe(getResource("recipe_planks_varnished_2"), new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()), "plankWood", "slimeball", EnumErebusMaterialsType.REPELLENT.createStack(), EnumErebusMaterialsType.CAMO_POWDER.createStack());

		// Stairs
		public static final IRecipe STAIRS_BAOBAB = new ShapedOreRecipe(getResource("recipe_stairs_baobab"), new ItemStack(EnumWood.BAOBAB.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAOBAB.ordinal()));
		public static final IRecipe STAIRS_EUCALYPTUS = new ShapedOreRecipe(getResource("recipe_stairs_eucalyptus"), new ItemStack(EnumWood.EUCALYPTUS.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.EUCALYPTUS.ordinal()));
		public static final IRecipe STAIRS_MAHOGANY = new ShapedOreRecipe(getResource("recipe_stairs_mahogany"), new ItemStack(EnumWood.MAHOGANY.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MAHOGANY.ordinal()));
		public static final IRecipe STAIRS_MOSSBARK = new ShapedOreRecipe(getResource("recipe_stairs_mossbark"), new ItemStack(EnumWood.MOSSBARK.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MOSSBARK.ordinal()));
		public static final IRecipe STAIRS_ASPER = new ShapedOreRecipe(getResource("recipe_stairs_asper"), new ItemStack(EnumWood.ASPER.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ASPER.ordinal()));
		public static final IRecipe STAIRS_CYPRESS = new ShapedOreRecipe(getResource("recipe_stairs_cypress"), new ItemStack(EnumWood.CYPRESS.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.CYPRESS.ordinal()));
		public static final IRecipe STAIRS_BALSAM = new ShapedOreRecipe(getResource("recipe_stairs_balsam"), new ItemStack(EnumWood.BALSAM.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BALSAM.ordinal()));
		public static final IRecipe STAIRS_WHITE = new ShapedOreRecipe(getResource("recipe_stairs_white"), new ItemStack(EnumWood.WHITE.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.WHITE.ordinal()));
		public static final IRecipe STAIRS_BAMBOO = new ShapedOreRecipe(getResource("recipe_stairs_bamboo"), new ItemStack(EnumWood.BAMBOO.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAMBOO.ordinal()));
		public static final IRecipe STAIRS_ROTTEN = new ShapedOreRecipe(getResource("recipe_stairs_rotten"), new ItemStack(EnumWood.ROTTEN.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ROTTEN.ordinal()));
		public static final IRecipe STAIRS_MARSHWOOD = new ShapedOreRecipe(getResource("recipe_stairs_marshwood"), new ItemStack(EnumWood.MARSHWOOD.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MARSHWOOD.ordinal()));
		public static final IRecipe STAIRS_SCORCHED = new ShapedOreRecipe(getResource("recipe_stairs_scorched"), new ItemStack(EnumWood.SCORCHED.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.SCORCHED.ordinal()));
		public static final IRecipe STAIRS_VARNISHED = new ShapedOreRecipe(getResource("recipe_stairs_varnished"), new ItemStack(EnumWood.VARNISHED.getStairs(), 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()));

		// Slabs
		public static final IRecipe SLABS_BAOBAB = new ShapedOreRecipe(getResource("recipe_slabs_baobab"), new ItemStack(EnumWood.BAOBAB.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAOBAB.ordinal()));
		public static final IRecipe SLABS_EUCALYPTUS = new ShapedOreRecipe(getResource("recipe_slabs_eucalyptus"), new ItemStack(EnumWood.EUCALYPTUS.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.EUCALYPTUS.ordinal()));
		public static final IRecipe SLABS_MAHOGANY = new ShapedOreRecipe(getResource("recipe_slabs_mahogany"), new ItemStack(EnumWood.MAHOGANY.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MAHOGANY.ordinal()));
		public static final IRecipe SLABS_MOSSBARK = new ShapedOreRecipe(getResource("recipe_slabs_mossbark"), new ItemStack(EnumWood.MOSSBARK.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MOSSBARK.ordinal()));
		public static final IRecipe SLABS_ASPER = new ShapedOreRecipe(getResource("recipe_slabs_asper"), new ItemStack(EnumWood.ASPER.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ASPER.ordinal()));
		public static final IRecipe SLABS_CYPRESS = new ShapedOreRecipe(getResource("recipe_slabs_cypress"), new ItemStack(EnumWood.CYPRESS.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.CYPRESS.ordinal()));
		public static final IRecipe SLABS_BALSAM = new ShapedOreRecipe(getResource("recipe_slabs_balsam"), new ItemStack(EnumWood.BALSAM.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BALSAM.ordinal()));
		public static final IRecipe SLABS_WHITE = new ShapedOreRecipe(getResource("recipe_slabs_white"), new ItemStack(EnumWood.WHITE.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.WHITE.ordinal()));
		public static final IRecipe SLABS_BAMBOO = new ShapedOreRecipe(getResource("recipe_slabs_bamboo"), new ItemStack(EnumWood.BAMBOO.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAMBOO.ordinal()));
		public static final IRecipe SLABS_ROTTEN = new ShapedOreRecipe(getResource("recipe_slabs_rotten"), new ItemStack(EnumWood.ROTTEN.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ROTTEN.ordinal()));
		public static final IRecipe SLABS_MARSHWOOD = new ShapedOreRecipe(getResource("recipe_slabs_marshwood"), new ItemStack(EnumWood.MARSHWOOD.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MARSHWOOD.ordinal()));
		public static final IRecipe SLABS_SCORCHED = new ShapedOreRecipe(getResource("recipe_slabs_scorched"), new ItemStack(EnumWood.SCORCHED.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.SCORCHED.ordinal()));
		public static final IRecipe SLABS_VARNISHED = new ShapedOreRecipe(getResource("recipe_slabs_varnished"), new ItemStack(EnumWood.VARNISHED.getSlab(), 6), "xxx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()));

		// Fences
		public static final IRecipe FENCE_BAOBAB = new ShapedOreRecipe(getResource("recipe_fence_baobab"), new ItemStack(EnumWood.BAOBAB.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAOBAB.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_EUCALYPTUS = new ShapedOreRecipe(getResource("recipe_fence_eucalyptus"), new ItemStack(EnumWood.EUCALYPTUS.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.EUCALYPTUS.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_MAHOGANY = new ShapedOreRecipe(getResource("recipe_fence_mahogany"), new ItemStack(EnumWood.MAHOGANY.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MAHOGANY.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_MOSSBARK = new ShapedOreRecipe(getResource("recipe_fence_mossbark"), new ItemStack(EnumWood.MOSSBARK.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MOSSBARK.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_ASPER = new ShapedOreRecipe(getResource("recipe_fence_asper"), new ItemStack(EnumWood.ASPER.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ASPER.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_CYPRESS = new ShapedOreRecipe(getResource("recipe_fence_cypress"), new ItemStack(EnumWood.CYPRESS.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.CYPRESS.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_BALSAM = new ShapedOreRecipe(getResource("recipe_fence_balsam"), new ItemStack(EnumWood.BALSAM.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BALSAM.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_WHITE = new ShapedOreRecipe(getResource("recipe_fence_white"), new ItemStack(EnumWood.WHITE.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.WHITE.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_BAMBOO = new ShapedOreRecipe(getResource("recipe_fence_bamboo"), new ItemStack(EnumWood.BAMBOO.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAMBOO.ordinal()), 's', EnumErebusMaterialsType.BAMBOO.createStack());
		public static final IRecipe FENCE_ROTTEN = new ShapedOreRecipe(getResource("recipe_fence_rotten"), new ItemStack(EnumWood.ROTTEN.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ROTTEN.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_MARSHWOOD = new ShapedOreRecipe(getResource("recipe_fence_marshwood"), new ItemStack(EnumWood.MARSHWOOD.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MARSHWOOD.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_SCORCHED = new ShapedOreRecipe(getResource("recipe_fence_scorched"), new ItemStack(EnumWood.SCORCHED.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.SCORCHED.ordinal()), 's', "stickWood");
		public static final IRecipe FENCE_VARNISHED = new ShapedOreRecipe(getResource("recipe_fence_varnished"), new ItemStack(EnumWood.VARNISHED.getFence(), 3), "xsx", "xsx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()), 's', "stickWood");

		// Fence Gates
		public static final IRecipe FENCE_GATE_BAOBAB = new ShapedOreRecipe(getResource("recipe_fence_gate_baobab"), new ItemStack(EnumWood.BAOBAB.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAOBAB.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_EUCALYPTUS = new ShapedOreRecipe(getResource("recipe_fence_gate_eucalyptus"), new ItemStack(EnumWood.EUCALYPTUS.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.EUCALYPTUS.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_MAHOGANY = new ShapedOreRecipe(getResource("recipe_fence_gate_mahogany"), new ItemStack(EnumWood.MAHOGANY.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MAHOGANY.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_MOSSBARK = new ShapedOreRecipe(getResource("recipe_fence_gate_mossbark"), new ItemStack(EnumWood.MOSSBARK.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MOSSBARK.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_ASPER = new ShapedOreRecipe(getResource("recipe_fence_gate_asper"), new ItemStack(EnumWood.ASPER.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ASPER.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_CYPRESS = new ShapedOreRecipe(getResource("recipe_fence_gate_cypress"), new ItemStack(EnumWood.CYPRESS.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.CYPRESS.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_BALSAM = new ShapedOreRecipe(getResource("recipe_fence_gate_balsam"), new ItemStack(EnumWood.BALSAM.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BALSAM.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_WHITE = new ShapedOreRecipe(getResource("recipe_fence_gate_white"), new ItemStack(EnumWood.WHITE.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.WHITE.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_BAMBOO = new ShapedOreRecipe(getResource("recipe_fence_gate_bamboo"), new ItemStack(EnumWood.BAMBOO.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAMBOO.ordinal()), 'x', EnumErebusMaterialsType.BAMBOO.createStack());
		public static final IRecipe FENCE_GATE_ROTTEN = new ShapedOreRecipe(getResource("recipe_fence_gate_rotten"), new ItemStack(EnumWood.ROTTEN.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ROTTEN.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_MARSHWOOD = new ShapedOreRecipe(getResource("recipe_fence_gate_marshwood"), new ItemStack(EnumWood.MARSHWOOD.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MARSHWOOD.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_SCORCHED = new ShapedOreRecipe(getResource("recipe_fence_gate_scorched"), new ItemStack(EnumWood.SCORCHED.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.SCORCHED.ordinal()), 'x', "stickWood");
		public static final IRecipe FENCE_GATE_VARNISHED = new ShapedOreRecipe(getResource("recipe_fence_gate_varnished"), new ItemStack(EnumWood.VARNISHED.getGate(), 1), "xsx", "xsx", 's', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.VARNISHED.ordinal()), 'x', "stickWood");

		// Doors
		public static final IRecipe DOOR_AMBER = new ShapedOreRecipe(getResource("recipe_door_amber"), new ItemStack(ModBlocks.DOOR_AMBER, 3), "##", "##", "##", '#', new ItemStack(ModBlocks.AMBER_BRICKS, 1, 0));
		public static final IRecipe DOOR_BAOBAB = new ShapedOreRecipe(getResource("recipe_door_baobab"), new ItemStack(EnumWood.BAOBAB.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.BAOBAB.ordinal()));
		public static final IRecipe DOOR_EUCALYPTUS = new ShapedOreRecipe(getResource("recipe_door_eucalyptus"), new ItemStack(EnumWood.EUCALYPTUS.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.EUCALYPTUS.ordinal()));
		public static final IRecipe DOOR_MAHOGANY = new ShapedOreRecipe(getResource("recipe_door_mahogany"), new ItemStack(EnumWood.MAHOGANY.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MAHOGANY.ordinal()));
		public static final IRecipe DOOR_MOSSBARK = new ShapedOreRecipe(getResource("recipe_door_mossbark"), new ItemStack(EnumWood.MOSSBARK.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MOSSBARK.ordinal()));
		public static final IRecipe DOOR_ASPER = new ShapedOreRecipe(getResource("recipe_door_asper"), new ItemStack(EnumWood.ASPER.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ASPER.ordinal()));
		public static final IRecipe DOOR_CYPRESS = new ShapedOreRecipe(getResource("recipe_door_cypress"), new ItemStack(EnumWood.CYPRESS.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.CYPRESS.ordinal()));
		public static final IRecipe DOOR_ROTTEN = new ShapedOreRecipe(getResource("recipe_door_rotten"), new ItemStack(EnumWood.ROTTEN.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.ROTTEN.ordinal()));
		public static final IRecipe DOOR_MARSHWOOD = new ShapedOreRecipe(getResource("recipe_door_marshwood"), new ItemStack(EnumWood.MARSHWOOD.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.MARSHWOOD.ordinal()));
		public static final IRecipe DOOR_SCORCHED = new ShapedOreRecipe(getResource("recipe_door_scorched"), new ItemStack(EnumWood.SCORCHED.getDoor(), 3), "xx", "xx", "xx", 'x', new ItemStack(ModBlocks.PLANKS, 1, EnumWood.SCORCHED.ordinal()));

	private static ResourceLocation getResource(String inName) {
		return new ResourceLocation(Reference.MOD_ID, inName);
	}

	public static void init() {
		try {
			for (Field field : RecipeHandler.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof IRecipe) {
					IRecipe recipe = (IRecipe) obj;
					String name = field.getName().toLowerCase(Locale.ENGLISH);
					registerRecipe(name, recipe);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void registerRecipe(String inName, IRecipe recipe) {
		RECIPES.add(recipe);
		recipe.setRegistryName(getResource(inName));
	}

	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandlerRecipes {

		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public static void registerRecipes(final RegistryEvent.Register<IRecipe> event) {
			RecipeHandler.init();
			final IForgeRegistry<IRecipe> registry = event.getRegistry();
			for (IRecipe recipes : RECIPES)
				registry.register(recipes);
		}
	}

	public static void registerSmelting() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.AMBER), new ItemStack(ModBlocks.AMBER_GLASS, 1, 0), 0.3F);
		GameRegistry.addSmelting(EnumFoodType.BEETLE_LARVA_RAW.createStack(), EnumFoodType.BEETLE_LARVA_COOKED.createStack(), 0.2F);
		GameRegistry.addSmelting(EnumFoodType.GRASSHOPPER_LEG_RAW.createStack(), EnumFoodType.GRASSHOPPER_LEG_COOKED.createStack(), 0.2F);
		GameRegistry.addSmelting(EnumFoodType.TARANTULA_LEG_RAW.createStack(), EnumFoodType.TARANTULA_LEG_COOKED.createStack(), 0.2F);
		GameRegistry.addSmelting(EnumFoodType.TITAN_CHOP_RAW.createStack(), EnumFoodType.TITAN_CHOP_COOKED.createStack(), 0.2F);
		GameRegistry.addSmelting(EnumFoodType.PRICKLY_PEAR_RAW.createStack(), EnumFoodType.PRICKLY_PEAR_COOKED.createStack(), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.UMBERSTONE, 1, 1), new ItemStack(ModBlocks.UMBERSTONE), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_COAL), new ItemStack(Items.COAL, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_IRON), new ItemStack(Items.IRON_INGOT), 0.7F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_GOLD), new ItemStack(Items.GOLD_INGOT), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_LAPIS), new ItemStack(Items.DYE, 1, 4), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_DIAMOND), new ItemStack(Items.DIAMOND), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_EMERALD), new ItemStack(Items.EMERALD), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_JADE), EnumErebusMaterialsType.JADE.createStack(), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_FOSSIL), EnumErebusMaterialsType.SHARD_BONE.createStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_GNEISS), EnumErebusMaterialsType.GNEISS_ROCK.createStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_PETRIFIED_WOOD), EnumErebusMaterialsType.PETRIFIED_WOOD.createStack(), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_ENCRUSTED_DIAMOND), new ItemStack(Items.DIAMOND), 1.0F); //should be encrusted diamond? O.o
		GameRegistry.addSmelting(EnumErebusMaterialsType.TITAN_STEW.createStack(), EnumFoodType.TITAN_STEW_COOKED.createStack(), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModItems.STAG_HEART_RAW), new ItemStack(ModItems.STAG_HEART_COOKED), 1.0F);

		GameRegistry.addSmelting(new ItemStack(ModBlocks.MUD), EnumErebusMaterialsType.MUD_BRICK.createStack(), 0.2F);
		GameRegistry.addSmelting(EnumErebusMaterialsType.NECTAR.createStack(), EnumErebusMaterialsType.HONEY_DRIP.createStack(), 0.2F);
		if (OreType.LEAD.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_LEAD), EnumErebusMaterialsType.INGOT_LEAD.createStack(), 1.0F);
		if (OreType.SILVER.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_SILVER), EnumErebusMaterialsType.INGOT_SILVER.createStack(), 1.0F);
		if (OreType.COPPER.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_COPPER), EnumErebusMaterialsType.INGOT_COPPER.createStack(), 1.0F);
		if (OreType.TIN.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_TIN), EnumErebusMaterialsType.INGOT_TIN.createStack(), 1.0F);
		if (OreType.ALUMINIUM.isEnabled())
			GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_ALUMINIUM), EnumErebusMaterialsType.INGOT_ALUMINIUM.createStack(), 1.0F);
		
		for (EnumWood wood : EnumWood.values()) {
			if (wood.hasLog()) {
				Block log = wood.getLog();
				GameRegistry.addSmelting(new ItemStack(log), new ItemStack(Items.COAL, 1, 1), 0.15F);
			}
		}
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandler {
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerOreDictEntries(final RegistryEvent.Register<Item> event) {
			OreDictionary.registerOre("chestWood", new ItemStack(Blocks.CHEST));
			OreDictionary.registerOre("buttonWood", new ItemStack(Blocks.WOODEN_BUTTON));
			OreDictionary.registerOre("fenceWood", new ItemStack(Blocks.OAK_FENCE));

			OreDictionary.registerOre("oreCoal", new ItemStack(ModBlocks.ORE_COAL));
			OreDictionary.registerOre("oreIron", new ItemStack(ModBlocks.ORE_IRON));
			OreDictionary.registerOre("oreGold", new ItemStack(ModBlocks.ORE_GOLD));
			OreDictionary.registerOre("oreLapis", new ItemStack(ModBlocks.ORE_LAPIS));
			OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.ORE_DIAMOND));
			OreDictionary.registerOre("oreEmerald", new ItemStack(ModBlocks.ORE_EMERALD));
			OreDictionary.registerOre("oreJade", new ItemStack(ModBlocks.ORE_JADE));
			OreDictionary.registerOre("orePetrifiedWood", new ItemStack(ModBlocks.ORE_PETRIFIED_WOOD));
			OreDictionary.registerOre("oreDiamond", new ItemStack(ModBlocks.ORE_ENCRUSTED_DIAMOND));
			OreDictionary.registerOre("oreFossil", new ItemStack(ModBlocks.ORE_FOSSIL));
			OreDictionary.registerOre("oreGneiss", new ItemStack(ModBlocks.ORE_GNEISS));
			OreDictionary.registerOre("oreQuartz", new ItemStack(ModBlocks.ORE_QUARTZ));

			OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.UMBERSTONE, 1, 1));
			OreDictionary.registerOre("stone", new ItemStack(ModBlocks.UMBERSTONE));
			OreDictionary.registerOre("stoneUmber", new ItemStack(ModBlocks.UMBERSTONE));
			OreDictionary.registerOre("gemJade", EnumErebusMaterialsType.JADE.createStack());
			OreDictionary.registerOre("blockJade", new ItemStack(ModBlocks.JADE_BLOCK));
			OreDictionary.registerOre("blockSpawner", ModBlocks.SPIDER_SPAWNER);
			OreDictionary.registerOre("blockSpawner", ModBlocks.JUMPING_SPIDER_SPAWNER);
			OreDictionary.registerOre("blockSpawner", ModBlocks.WASP_SPAWNER);
			//OreDictionary.registerOre("gemDiamond", ModItems.ENCRUSTED_DIAMOND);
			OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.AMBER_GLASS, 1, 0));
			OreDictionary.registerOre("logWood", new ItemStack(EnumWood.BALSAM.getLog()));
			OreDictionary.registerOre("logWood", new ItemStack(EnumWood.SCORCHED.getLog()));
			OreDictionary.registerOre("bamboo", EnumErebusMaterialsType.BAMBOO.createStack());

			OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.PLANKS));
			OreDictionary.registerOre("plankPetrified", new ItemStack(ModBlocks.PLANKS_PETRIFIED_WOOD));
			for (EnumWood wood : EnumWood.values()) // ewwwwww this is hax
				OreDictionary.registerOre("plank" + wood.getName().toUpperCase().charAt(0) + wood.getName().substring(1), new ItemStack(ModBlocks.PLANKS, 1, wood.ordinal()));

			OreDictionary.registerOre("dyeBlack", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_BLACK.ordinal()));
			OreDictionary.registerOre("dyeRed", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_RED.ordinal()));
			OreDictionary.registerOre("dyeBrown", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_BROWN.ordinal()));
			OreDictionary.registerOre("dyeBlue", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_BLUE.ordinal()));
			OreDictionary.registerOre("dyePurple", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_PURPLE.ordinal()));
			OreDictionary.registerOre("dyeCyan", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_CYAN.ordinal()));
			OreDictionary.registerOre("dyeLightGray", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_LIGHT_GRAY.ordinal()));
			OreDictionary.registerOre("dyeGray", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_GRAY.ordinal()));
			OreDictionary.registerOre("dyePink", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_PINK.ordinal()));
			OreDictionary.registerOre("dyeYellow", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_YELLOW.ordinal()));
			OreDictionary.registerOre("dyeLightBlue", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_LIGHT_BLUE.ordinal()));
			OreDictionary.registerOre("dyeMagenta", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_MAGENTA.ordinal()));
			OreDictionary.registerOre("dyeOrange", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_ORANGE.ordinal()));
			OreDictionary.registerOre("dyeWhite", new ItemStack(ModBlocks.GIANT_FLOWER, 1, EnumType.PETAL_WHITE.ordinal()));

			OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.DARK_CAPPED_MUSHROOM));
			OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.KAIZERS_FINGERS_MUSHROOM));
			OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.SARCASTIC_CZECH_MUSHROOM));
			OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.GRANDMAS_SHOES_MUSHROOM));
			OreDictionary.registerOre("foodMushroom", new ItemStack(ModBlocks.DUTCH_CAP_MUSHROOM));
			OreDictionary.registerOre("foodMushroom", new ItemStack(Blocks.RED_MUSHROOM));
			OreDictionary.registerOre("foodMushroom", new ItemStack(Blocks.BROWN_MUSHROOM));

			//Honey
			OreDictionary.registerOre("dropHoney", EnumErebusMaterialsType.HONEY_DRIP.createStack());
			OreDictionary.registerOre("bucketHoney", FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid("honey"), Fluid.BUCKET_VOLUME)));
			OreDictionary.registerOre("bucketHoney", ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("honey"), Fluid.BUCKET_VOLUME)));

			if (OreType.LEAD.isEnabled()) {
				OreDictionary.registerOre("ingotLead", EnumErebusMaterialsType.INGOT_LEAD.createStack());
				OreDictionary.registerOre("oreLead", new ItemStack(ModBlocks.ORE_LEAD));
			}
			if (OreType.SILVER.isEnabled()) {
				OreDictionary.registerOre("ingotSilver", EnumErebusMaterialsType.INGOT_SILVER.createStack());
				OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.ORE_SILVER));
			}
			if (OreType.COPPER.isEnabled()) {
				OreDictionary.registerOre("ingotCopper", EnumErebusMaterialsType.INGOT_COPPER.createStack());
				OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.ORE_COPPER));
			}
			if (OreType.TIN.isEnabled()) {
				OreDictionary.registerOre("ingotTin", EnumErebusMaterialsType.INGOT_TIN.createStack());
				OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.ORE_TIN));
			}
			if (OreType.ALUMINIUM.isEnabled()) {
				OreDictionary.registerOre("ingotAluminium", EnumErebusMaterialsType.INGOT_ALUMINIUM.createStack());
				OreDictionary.registerOre("oreAluminum", new ItemStack(ModBlocks.ORE_ALUMINIUM));
			}
			
			for (EnumWood wood : EnumWood.values()) {
				if (wood.hasLog()) {
					Block log = wood.getLog();
					OreDictionary.registerOre("logWood", log);
				}
				if (wood.hasSapling())
					OreDictionary.registerOre("treeSapling", wood.getSapling());
				if (wood.hasPlanks()) {
					Block stairs = wood.getStairs();
					OreDictionary.registerOre("stairWood", stairs);
					Block slab = wood.getSlab();
					OreDictionary.registerOre("slabWood", slab);
				}
				if (wood.hasLeaves())
					OreDictionary.registerOre("treeLeaves", wood.getLeaves());
			}
		}
	}
}
