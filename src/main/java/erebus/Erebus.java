package erebus;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import erebus.client.render.entity.MobGrabbingHealthBarRemoval;
import erebus.client.render.entity.RenderRhinoBeetleChargeBar;
import erebus.client.sound.AmbientMusicManager;
import erebus.core.handler.BucketHandler;
import erebus.core.handler.EntityDeathEventHandler;
import erebus.core.handler.HomingBeeconTextureHandler;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.proxy.CommonProxy;
import erebus.debug.ErebusCommandDebug;
import erebus.entity.util.RandomMobNames;
import erebus.integration.FMPIntegration;
import erebus.integration.ModIntegrationHandler;
import erebus.integration.ThaumcraftIntegration;
import erebus.lib.Reference;
import erebus.network.PacketPipeline;
import erebus.recipes.ComposterRegistry;
import erebus.recipes.ErebusRecipesHandler;
import erebus.recipes.RecipeHandler;
import erebus.world.SpawnerErebus;
import erebus.world.WorldProviderErebus;
import erebus.world.teleporter.TeleporterHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Erebus
{

	@SidedProxy(clientSide = Reference.SP_CLIENT, serverSide = Reference.SP_SERVER)
	public static CommonProxy proxy;

	@Instance(Reference.MOD_ID)
	public static Erebus instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.INSTANCE.loadConfig(event);

		if (event.getSide() == Side.CLIENT)
		{
			MinecraftForge.EVENT_BUS.register(new RenderRhinoBeetleChargeBar());
			MinecraftForge.EVENT_BUS.register(new HomingBeeconTextureHandler());
			MinecraftForge.EVENT_BUS.register(new MobGrabbingHealthBarRemoval());
			AmbientMusicManager.register();
		}

		ModFluids.init();
		ModBlocks.init();
		ModItems.init();
		ModEntities.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		DimensionManager.registerProviderType(ConfigHandler.INSTANCE.erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(ConfigHandler.INSTANCE.erebusDimensionID, ConfigHandler.INSTANCE.erebusDimensionID);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerKeyHandlers();
		proxy.registerTileEntities();
		proxy.registerRenderInformation();

		PacketPipeline.initializePipeline();
		ModBiomes.init();
		RecipeHandler.init();
		ErebusRecipesHandler.init();
		TeleporterHandler.init();

		MinecraftForge.EVENT_BUS.register(new EntityDeathEventHandler());
		MinecraftForge.EVENT_BUS.register(ModBlocks.quickSand);
		MinecraftForge.EVENT_BUS.register(ModBlocks.insectRepellent);
		MinecraftForge.EVENT_BUS.register(ModFluids.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModItems.armorGlider);
		MinecraftForge.EVENT_BUS.register(ModItems.jumpBoots);
		BucketHandler.INSTANCE.buckets.put(ModBlocks.honeyBlock, ModItems.bucketHoney);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(ConfigHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(new SpawnerErebus());

		if (ConfigHandler.INSTANCE.randomNames)
		{
			MinecraftForge.EVENT_BUS.register(RandomMobNames.instance);
		}

		if (!ConfigHandler.INSTANCE.disableThaumcraft)
		{
			ModIntegrationHandler.addMod(ThaumcraftIntegration.class);
		}

		if (!ConfigHandler.INSTANCE.disableFMP)
		{
			ModIntegrationHandler.addMod(FMPIntegration.class);
		}

		ModIntegrationHandler.init();

		ComposterRegistry.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		ModIntegrationHandler.postInit();
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new ErebusCommandDebug());
	}
}