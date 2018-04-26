package erebus.recipes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.blocks.BlockGiantFlower.EnumType;
import erebus.blocks.EnumWood;
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
import net.minecraftforge.registries.IForgeRegistry;

public class RecipeHandler {
		public static final List<IRecipe> RECIPES = new ArrayList<IRecipe>();

/*
	Stairs, slabs, walls for (int i = 0; i < ModBlocks.gneissStairs.length; i++)
		X = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.gneissStairs[i], 4), "#  ", "## ", "###", '#', new ItemStack(ModBlocks.gneiss, 1, i));
	 public static final IRecipe ARMCHAIR = new ShapedOreRecipe(getResource("recipe_"), new ItemStack(ModBlocks.armchair), "  w", "www", "p p", 'w', Blocks.wool, 'p', "plankWood");
*/
	// Jade tools
	public static final IRecipe RECIPE_PAXEL = new RecipePaxel();

	// Special armor & weapons
	public static final IRecipe GLIDER_DYE = new RecipeGliderDye();
	public static final IRecipe LEGGINGS_UPGRADES = new RecipeSprintLeggingsUpgrades();

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
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE_ENCRUSTED_DIAMOND), new ItemStack(Items.DIAMOND, 2), 1.0F); //should be encrusted diamond? O.o
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
			
			/* Temporary disabled as this looks like it's borked for people
			OreDictionary.registerOre("bucketHoney", FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid("honey"), Fluid.BUCKET_VOLUME)));
			OreDictionary.registerOre("bucketHoney", ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("honey"), Fluid.BUCKET_VOLUME)));
			 */
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