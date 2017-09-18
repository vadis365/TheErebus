package erebus;

import erebus.client.fx.ParticleTextureStitchEvent;
import erebus.client.sound.ErebusMusicHandler;
import erebus.core.handler.EntityShieldDamageEvent;
import erebus.core.handler.configs.ConfigHandler;
import erebus.lib.Reference;
import erebus.network.client.PacketParticle;
import erebus.network.server.ColossalCratePage;
import erebus.proxy.CommonProxy;
import erebus.world.SpawnerErebus;
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
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)//, dependencies = Reference.DEPENDENCIES)
public class Erebus {

	@SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_COMMON)
	public static CommonProxy PROXY;

	@Instance(Reference.MOD_ID)
	public static Erebus INSTANCE;

	public static DimensionType dimensionType;
	public static SimpleNetworkWrapper NETWORK_WRAPPER;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.INSTANCE.loadConfig(event);
		ModItems.init();
		ModBlocks.init();
		ModBiomes.init();
		ModSounds.init();
		ModEntities.init();
		
		if (event.getSide() == Side.CLIENT) {
			if (ConfigHandler.INSTANCE.playCustomSongs)
				MinecraftForge.EVENT_BUS.register(new ErebusMusicHandler());
			
			MinecraftForge.EVENT_BUS.register(new ParticleTextureStitchEvent());
		}

		dimensionType = DimensionType.register("EREBUS", "", ConfigHandler.INSTANCE.erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(ConfigHandler.INSTANCE.erebusDimensionID, dimensionType);
		ConfigHandler.INSTANCE.initOreConfigs();

		PROXY.registerTileEntities();
		PROXY.registerItemAndBlockRenderers();
		PROXY.registerEnitityRenderers();

		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, PROXY);
		NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		NETWORK_WRAPPER.registerMessage(PacketParticle.class, PacketParticle.class, 0, Side.CLIENT);
		NETWORK_WRAPPER.registerMessage(ColossalCratePage.class, ColossalCratePage.class, 1, Side.SERVER);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		TeleporterHandler.init();
		MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModItems.JUMP_BOOTS);
        MinecraftForge.EVENT_BUS.register(new EntityShieldDamageEvent());
		MinecraftForge.EVENT_BUS.register(SpawnerErebus.INSTANCE);
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