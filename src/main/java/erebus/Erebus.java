package erebus;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.proxy.CommonProxy;
import erebus.lib.Reference;
import erebus.world.WorldProviderErebus;
import erebus.world.teleporter.TeleporterHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = "${version}", dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Erebus {

	@SidedProxy(clientSide = Reference.SP_CLIENT, serverSide = Reference.SP_SERVER)
	public static CommonProxy proxy;

	@Instance(Reference.MOD_ID)
	public static Erebus instance;
	
	public static DimensionType dimensionType;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.INSTANCE.loadConfig(event);
/*
		if (event.getSide() == Side.CLIENT) {
			MinecraftForge.EVENT_BUS.register(new RenderRhinoBeetleChargeBar());
			MinecraftForge.EVENT_BUS.register(new RenderWarHammerChargeBar());
			MinecraftForge.EVENT_BUS.register(new HomingBeeconTextureHandler());
			MinecraftForge.EVENT_BUS.register(new MobGrabbingHealthBarRemoval());
			if (ConfigHandler.INSTANCE.playCustomSongs)
				FMLCommonHandler.instance().bus().register(new ErebusMusicHandler());
		}
*/
	//	ModFluids.init();
		ModBlocks.init();
		//ModItems.init();
		//ModEntities.init();

//		AchievementPage.registerAchievementPage(new ModAchievements());

//		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		dimensionType = DimensionType.register("EREBUS", "", ConfigHandler.INSTANCE.erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(ConfigHandler.INSTANCE.erebusDimensionID, dimensionType);
	//	GameRegistry.registerWorldGenerator(new WorldGenAntlionMaze(), 0);
	}

	@EventHandler
	@SuppressWarnings("unchecked")
	public void init(FMLInitializationEvent event) {
		// Remove all the door recipes.
		// This is needed otherwise our doors will not be craftable due to the recipe ordering
	/*	List<IRecipe> doorRecipes = new ArrayList<IRecipe>();
		for (IRecipe recipe : (List<IRecipe>) CraftingManager.getInstance().getRecipeList())
			if (recipe != null) {
				ItemStack stack = recipe.getRecipeOutput();
				if (stack != null && stack.getItem() == Items.wooden_door)
					doorRecipes.add(recipe);
			}
		for (IRecipe recipe : doorRecipes)
			CraftingManager.getInstance().getRecipeList().remove(recipe);
*/
	//	proxy.registerKeyHandlers();
	//	proxy.registerTileEntities();
	//	proxy.registerRenderInformation();

	//	PacketPipeline.initializePipeline();
		ModBiomes.init();
	//	RecipeHandler.init();
	//	ErebusRecipesHandler.init();
		TeleporterHandler.init();

	/*	MinecraftForge.EVENT_BUS.register(new EntityDeathEventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerChangedDimensionEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityPickupEventHandler());
		MinecraftForge.EVENT_BUS.register(ModBlocks.quickSand);
		MinecraftForge.EVENT_BUS.register(ModFluids.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModItems.armorGlider);
		MinecraftForge.EVENT_BUS.register(ModItems.jumpBoots);
		BucketHandler.INSTANCE.buckets.put(ModBlocks.honeyBlock, ModItems.bucketHoney);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
	*/	MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);
	//	FMLCommonHandler.instance().bus().register(SpawnerErebus.INSTANCE);

		/*		if (ConfigHandler.INSTANCE.graveMarker)
			MinecraftForge.EVENT_BUS.register(new EntityDeathInventoryHandler());

		if (ConfigHandler.INSTANCE.randomNames)
			MinecraftForge.EVENT_BUS.register(RandomMobNames.instance);

		if (!ConfigHandler.INSTANCE.disableThaumcraft)
			ModIntegrationHandler.addMod(ThaumcraftIntegration.class);

		if (!ConfigHandler.INSTANCE.disableFMP)
			ModIntegrationHandler.addMod(FMPIntegration.class);

		ModIntegrationHandler.init();
		ComposterRegistry.init();

		// Add the other door recipes back
		CraftingManager.getInstance().getRecipeList().addAll(doorRecipes);
		*/
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	//	ModIntegrationHandler.postInit();
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
	//	event.registerServerCommand(new ErebusCommandDebug());
	}
}