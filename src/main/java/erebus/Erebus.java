package erebus;

import java.util.Locale;

import erebus.registries.*;
import erebus.registries.data.ModArmorMaterials;
import erebus.registries.data.ModTags;
import erebus.registries.data.ModToolMaterials;
import erebus.registries.entity.ModEntities;
import erebus.registries.entity.ModEntityRendering;
import erebus.registries.world.ModTrunkPlacers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Erebus.MODID)
public class Erebus {

    public static final String MODID = "erebus";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Erebus(IEventBus bus, ModContainer container, Dist dist) {
        bus.addListener(this::commonSetup);

        ModArmorMaterials.register(bus);
        ModBlocks.register(bus);
        ModItems.register(bus);
        ModTags.init();
        ModToolMaterials.init();
        ModTabs.register(bus);
        ModEntities.getEntityTypes().register(bus);
        bus.addListener(ModEntities::registerSpawnPlacements);
        bus.addListener(ModEntities::initializeAttributes);
        ModEntities.SPAWN_EGGS.register(bus);
        ModSounds.SOUNDS.register(bus);
        ModMenuTypes.register(bus);
        ModTrunkPlacers.register(bus);

        NeoForge.EVENT_BUS.register(this);

        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        
		if (dist.isClient()) {
			bus.addListener(ModEntityRendering::registerEntityLayers);
			bus.addListener(ModEntityRendering::registerEntityRender);
			bus.addListener(ModItemRendering::registerItemLayerDefinitions);
			bus.addListener(ModItemRendering::registerItemRender);
		}
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}
}
