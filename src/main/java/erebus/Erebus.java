package erebus;

import erebus.core.handler.EntityShieldDamageEvent;
import erebus.core.handler.configs.ConfigHandler;
import erebus.lib.Reference;
import erebus.proxy.CommonProxy;
import erebus.world.WorldProviderErebus;
import erebus.world.teleporter.TeleporterHandler;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)//, dependencies = Reference.DEPENDENCIES)
public class Erebus {

	@SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_COMMON)
	public static CommonProxy PROXY;

	@Instance(Reference.MOD_ID)
	public static Erebus instance;

	public static DimensionType dimensionType;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.INSTANCE.loadConfig(event);
		ModItems.init();
		ModBlocks.init();
		ModBiomes.init();
		dimensionType = DimensionType.register("EREBUS", "", ConfigHandler.INSTANCE.erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(ConfigHandler.INSTANCE.erebusDimensionID, dimensionType);
		ConfigHandler.INSTANCE.initOreConfigs();

		PROXY.registerTileEntities();
		PROXY.registerItemAndBlockRenderers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		TeleporterHandler.init();
		MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModItems.JUMP_BOOTS);
		MinecraftForge.EVENT_BUS.register(new EntityShieldDamageEvent());
		PROXY.registerItemAndBlockColourRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		PROXY.postInit();
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
	}
}