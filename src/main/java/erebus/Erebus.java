package erebus;

import erebus.api.ErebusAPI;
import erebus.client.fx.ParticleTextureStitchEvent;
import erebus.client.gui.GuiAntiVenomBar;
import erebus.client.render.entity.MobGrabbingHealthBarRemoval;
import erebus.client.render.entity.RenderRhinoBeetleChargeBar;
import erebus.client.sound.ErebusMusicHandler;
import erebus.core.capabilities.base.EntityCapabilityHandler;
import erebus.core.capabilities.player.PlayerDeathLocationCapability;
import erebus.core.handler.AntiVenomDurationHandler;
import erebus.core.handler.DeathCompassRespawnEvent;
import erebus.core.handler.EntityDeathInventoryHandler;
import erebus.core.handler.EntityShieldDamageEvent;
import erebus.core.handler.configs.ConfigHandler;
import erebus.lib.Reference;
import erebus.network.client.MessageSyncEntityCapabilities;
import erebus.network.client.PacketAntiVenom;
import erebus.network.client.PacketBones;
import erebus.network.client.PacketOfferingAltar;
import erebus.network.client.PacketOfferingAltarTimer;
import erebus.network.client.PacketParticle;
import erebus.network.client.PacketSmoothieMakerGUI;
import erebus.network.server.ColossalCratePage;
import erebus.network.server.PacketBeetleDig;
import erebus.network.server.PacketBeetleRamAttack;
import erebus.network.server.PacketGlider;
import erebus.network.server.PacketGliderPowered;
import erebus.preserved.PreservableEntityRegistry;
import erebus.proxy.CommonProxy;
import erebus.recipes.ComposterRegistry;
import erebus.recipes.ErebusRecipesHandler;
import erebus.world.SpawnerErebus;
import erebus.world.WorldProviderErebus;
import erebus.world.teleporter.TeleporterHandler;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
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

	static { 
		FluidRegistry.enableUniversalBucket();
	} 

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ErebusAPI.preservableEntityRegistry = PreservableEntityRegistry.INSTANCE;
		ConfigHandler.INSTANCE.loadConfig(event);
		ModFluids.init();
		ModItems.init();
		ModBlocks.init();
		ModBiomes.init();
		ModSounds.init();
		ModEntities.init();
		ComposterRegistry.init();
		
		if (event.getSide() == Side.CLIENT) {
			if (ConfigHandler.INSTANCE.playCustomSongs)
				MinecraftForge.EVENT_BUS.register(new ErebusMusicHandler());
			
			MinecraftForge.EVENT_BUS.register(new ParticleTextureStitchEvent());
			MinecraftForge.EVENT_BUS.register(new RenderRhinoBeetleChargeBar());
			MinecraftForge.EVENT_BUS.register(new MobGrabbingHealthBarRemoval());
			MinecraftForge.EVENT_BUS.register(new GuiAntiVenomBar());
			MinecraftForge.EVENT_BUS.register(ModFluids.INSTANCE);
		}

		dimensionType = DimensionType.register("EREBUS", "", ConfigHandler.INSTANCE.erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(ConfigHandler.INSTANCE.erebusDimensionID, dimensionType);
		ConfigHandler.INSTANCE.initOreConfigs();

		PROXY.registerKeyHandlers();
		PROXY.registerTileEntities();
		PROXY.registerItemAndBlockRenderers();
		PROXY.registerEnitityRenderers();

		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, PROXY);
		NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		NETWORK_WRAPPER.registerMessage(PacketParticle.class, PacketParticle.class, 0, Side.CLIENT);
		NETWORK_WRAPPER.registerMessage(ColossalCratePage.class, ColossalCratePage.class, 1, Side.SERVER);
		NETWORK_WRAPPER.registerMessage(PacketBeetleDig.class, PacketBeetleDig.class, 2, Side.SERVER);
		NETWORK_WRAPPER.registerMessage(PacketBeetleRamAttack.class, PacketBeetleRamAttack.class, 3, Side.SERVER);
		NETWORK_WRAPPER.registerMessage(PacketAntiVenom.class, PacketAntiVenom.class, 4, Side.CLIENT);
		NETWORK_WRAPPER.registerMessage(PacketGlider.class, PacketGlider.class, 5, Side.SERVER);
		NETWORK_WRAPPER.registerMessage(PacketGliderPowered.class, PacketGliderPowered.class, 6, Side.SERVER);
		NETWORK_WRAPPER.registerMessage(PacketBones.class, PacketBones.class, 7, Side.CLIENT);
		NETWORK_WRAPPER.registerMessage(MessageSyncEntityCapabilities.class, MessageSyncEntityCapabilities.class, 8, Side.CLIENT);
		NETWORK_WRAPPER.registerMessage(PacketOfferingAltar.class, PacketOfferingAltar.class, 9, Side.CLIENT);
		NETWORK_WRAPPER.registerMessage(PacketOfferingAltarTimer.class, PacketOfferingAltarTimer.class, 10, Side.CLIENT);
		NETWORK_WRAPPER.registerMessage(PacketSmoothieMakerGUI.class, PacketSmoothieMakerGUI.class, 11, Side.CLIENT);

		MinecraftForge.EVENT_BUS.register(EntityCapabilityHandler.class);
		EntityCapabilityHandler.registerEntityCapability(new PlayerDeathLocationCapability());
		EntityCapabilityHandler.registerCapabilities();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		TeleporterHandler.init();
		MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModItems.JUMP_BOOTS);
		MinecraftForge.EVENT_BUS.register(ModItems.GLIDER_CHESTPLATE);
        MinecraftForge.EVENT_BUS.register(new EntityShieldDamageEvent());
		MinecraftForge.EVENT_BUS.register(SpawnerErebus.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new AntiVenomDurationHandler());
		if (ConfigHandler.INSTANCE.graveMarker) {
			MinecraftForge.EVENT_BUS.register(new EntityDeathInventoryHandler());
			MinecraftForge.EVENT_BUS.register(new DeathCompassRespawnEvent());
		}
		PROXY.registerItemAndBlockColourRenderers();
		ErebusRecipesHandler.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		PROXY.postInit();
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
	}
}