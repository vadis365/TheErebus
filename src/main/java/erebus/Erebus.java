package erebus;

import com.mojang.logging.LogUtils;
import erebus.network.data.DeathCompassData;
import erebus.recipes.ModCustomRecipes;
import erebus.registries.*;
import erebus.registries.client.ModBlockEntityRendering;
import erebus.registries.client.ModItemRendering;
import erebus.registries.client.ModMenuTypes;
import erebus.registries.client.ModParticles;
import erebus.registries.data.ModArmorMaterials;
import erebus.registries.data.ModDataComponents;
import erebus.registries.data.ModToolMaterials;
import erebus.registries.entity.ModEntities;
import erebus.registries.entity.ModEntityRendering;
import erebus.registries.network.ModNetwork;
import erebus.registries.world.*;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import java.util.Locale;

@Mod(Erebus.MODID)
public class Erebus {

    public static final String MODID = "erebus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Erebus(IEventBus bus, ModContainer container, Dist dist) {
        bus.addListener(this::commonSetup);

        ModArmorMaterials.ARMOR_MATERIALS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
		ModFluids.FLUIDS.register(bus);
		ModFluids.FLUID_TYPES.register(bus);
        ModToolMaterials.init();
        ModTabs.CREATIVE_MODE_TABS.register(bus);
        ModEntities.getEntityTypes().register(bus);
        bus.addListener(ModEntities::registerSpawnPlacements);
        bus.addListener(ModEntities::initializeAttributes);
        ModEntities.SPAWN_EGGS.register(bus);
        ModSounds.SOUNDS.register(bus);
        ModMenuTypes.MENU_TYPES.register(bus);
        ModTrunkPlacers.TRUNK_PLACERS.register(bus);
        ModFoliagePlacers.FOLIAGE_PLACERS.register(bus);
        ModTreeDecorators.TREE_DECORATORS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModPOIs.POI.register(bus);
        ModStructures.STRUCTURES.register(bus);
    	ModCustomRecipes.RECIPE_TYPES.register(bus);
    	ModCustomRecipes.RECIPE_SERIALIZERS.register(bus);
        ModParticles.PARTICLES.register(bus);
        ModDataComponents.DATA_COMPONENT_REGISTRY.register(bus);

        NeoForge.EVENT_BUS.register(this);

        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        
        bus.addListener(ModNetwork::register);

        NeoForgeMod.enableMilkFluid(); // TEMP - JUST FOR BEETLE MILKING TEST

		if (dist.isClient()) {
			bus.addListener(ModEntityRendering::registerEntityLayers);
			bus.addListener(ModEntityRendering::registerEntityRender);
			bus.addListener(ModItemRendering::registerItemLayerDefinitions);
			bus.addListener(ModItemRendering::registerItemRender);
			bus.addListener(ModBlockEntityRendering::registerBlockEntityLayerDefinitions);
			bus.addListener(ModBlockEntityRendering::registerBlockEntityRenderers);
            bus.addListener(ModParticles::registerParticleFactories);
		}
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ItemProperties.register(
                ModItems.DEATH_COMPASS.get(),
                ResourceLocation.withDefaultNamespace("angle"),
                new CompassItemPropertyFunction(
                        (level, stack, entity) -> {
                            DeathCompassData data = stack.get(ModDataComponents.DEATH_COMPASS);
                            if (data != null) {
                                if (entity instanceof Player player) {
                                    return player.getLastDeathLocation().orElse(null);
                                }
                            }

                            return null;
                        }
                )
        );

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
