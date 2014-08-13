package erebus;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
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
import erebus.core.handler.BlockHighlightHandler;
import erebus.core.handler.BonemealHandler;
import erebus.core.handler.BucketHandler;
import erebus.core.handler.HomingBeeconTextureHandler;
import erebus.core.handler.configs.ConfigHandler;
import erebus.core.proxy.CommonProxy;
import erebus.debug.ErebusCommandDebug;
import erebus.entity.util.RandomMobNames;
import erebus.integration.FMBIntegration;
import erebus.integration.ModIntegrationHandler;
import erebus.integration.ThaumcraftIntegration;
import erebus.lib.Reference;
import erebus.network.PacketPipeline;
import erebus.recipes.AltarRecipe;
import erebus.recipes.ComposterRegistry;
import erebus.recipes.RecipeHandler;
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
		AltarRecipe.init();
		TeleporterHandler.init();

		MinecraftForge.EVENT_BUS.register(new BonemealHandler());
		MinecraftForge.EVENT_BUS.register(new BlockHighlightHandler());
		MinecraftForge.EVENT_BUS.register(ModBlocks.bambooShoot);
		MinecraftForge.EVENT_BUS.register(ModBlocks.flowerPlanted);
		MinecraftForge.EVENT_BUS.register(ModBlocks.plantSmall);
		MinecraftForge.EVENT_BUS.register(ModBlocks.quickSand);
		MinecraftForge.EVENT_BUS.register(ModBlocks.insectRepellent);
		MinecraftForge.EVENT_BUS.register(ModFluids.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModItems.armorGlider);
		MinecraftForge.EVENT_BUS.register(ModItems.jumpBoots);
		BucketHandler.INSTANCE.buckets.put(ModBlocks.honeyBlock, ModItems.bucketHoney);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);

		if (ConfigHandler.INSTANCE.randomNames)
		{
			MinecraftForge.EVENT_BUS.register(RandomMobNames.instance);
		}

		ModIntegrationHandler.addMod(ThaumcraftIntegration.class);
		ModIntegrationHandler.addMod(FMBIntegration.class);
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