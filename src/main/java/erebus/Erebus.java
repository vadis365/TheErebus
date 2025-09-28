package erebus;

import java.util.Locale;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import erebus.client.ModAtlases;
import erebus.events.BedPlaceEventHandler;
import erebus.events.GogglesClientTickHandler;
import erebus.events.OnEntityJumpEventHandler;
import erebus.network.data.DeathCompassData;
import erebus.registries.ModCustomRecipes;
import erebus.registries.ModFluids;
import erebus.registries.ModItems;
import erebus.registries.ModSounds;
import erebus.registries.ModTabs;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModBlockEntityRendering;
import erebus.registries.client.ModItemRendering;
import erebus.registries.client.ModMenuTypes;
import erebus.registries.client.ModParticles;
import erebus.registries.data.ModArmorMaterials;
import erebus.registries.data.ModDataComponents;
import erebus.registries.data.ModPredicates;
import erebus.registries.data.ModToolMaterials;
import erebus.registries.entity.ModEntities;
import erebus.registries.entity.ModEntityRendering;
import erebus.registries.network.ModNetwork;
import erebus.registries.world.ModBiomeLayerTypes;
import erebus.registries.world.ModPOIs;
import erebus.registries.world.carver.ModWorldCarvers;
import erebus.registries.world.feature.ModFeatures;
import erebus.registries.world.feature.config.DecorationFeatureConfigs;
import erebus.registries.world.feature.config.PlantFeatureConfigs;
import erebus.registries.world.feature.config.StructureFeatureConfigs;
import erebus.registries.world.structure.ModStructurePieces;
import erebus.registries.world.structure.ModStructureProcessors;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.registries.world.tree.ModFoliagePlacers;
import erebus.registries.world.tree.ModTreeDecorators;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;

@Mod(Erebus.MODID)
public class Erebus {

    public static final String MODID = "erebus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Erebus(IEventBus bus, ModContainer container, Dist dist) {
    	
    	IEventBus neoBus = NeoForge.EVENT_BUS;
        bus.addListener(this::commonSetup);
        DecorationFeatureConfigs.init();
        PlantFeatureConfigs.init();
        StructureFeatureConfigs.init();

        ModArmorMaterials.ARMOR_MATERIALS.register(bus);
        ModBlocks.register(bus);
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
    	ModCustomRecipes.RECIPE_TYPES.register(bus);
    	ModCustomRecipes.RECIPE_SERIALIZERS.register(bus);
        ModParticles.PARTICLES.register(bus);
        ModDataComponents.DATA_COMPONENT_REGISTRY.register(bus);
        ModFeatures.CONFIGS.register(bus);
        ModWorldCarvers.CARVERS.register(bus);
        ModStructurePieces.STRUCTURE_PIECES.register(bus);
        ModStructureProcessors.STRUCTURE_PROCESSORS.register(bus);
        ModStructureTypes.STRUCTURE_TYPES.register(bus);
        ModBiomeLayerTypes.BIOME_LAYER_TYPES.register(bus);
        ModPredicates.PREDICATES.register(bus);
        ModAtlases.registerPetrifiedChestAtlases();

        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        bus.addListener(ModNetwork::register);

        neoBus.register(new BedPlaceEventHandler());
        neoBus.register(new OnEntityJumpEventHandler());
        

        NeoForgeMod.enableMilkFluid(); // TEMP - JUST FOR BEETLE MILKING TEST

		if (dist.isClient()) {
			bus.addListener(this::setFluidRenderTypes);
			bus.addListener(ModEntityRendering::registerEntityLayers);
			bus.addListener(ModEntityRendering::registerEntityRender);
			bus.addListener(ModItemRendering::registerItemLayerDefinitions);
			bus.addListener(ModItemRendering::registerItemRender);
			bus.addListener(ModItemRendering::registerItemColors);
			bus.addListener(ModBlockEntityRendering::registerBlockEntityLayerDefinitions);
			bus.addListener(ModBlockEntityRendering::registerBlockEntityRenderers);
            bus.addListener(ModParticles::registerParticleFactories);
            neoBus.register(new GogglesClientTickHandler());
		}
    }

    private void setFluidRenderTypes(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModFluids.BEETLE_JUICE_FLOW.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.BEETLE_JUICE_STILL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.HONEY_FLOW.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.HONEY_STILL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.ANTI_VENOM_FLOW.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.ANTI_VENOM_STILL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FORMIC_ACID_FLOW.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FORMIC_ACID_STILL.get(), RenderType.translucent());
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

        ItemProperties.register(ModItems.MAX_SPEED_BOW.get(), ResourceLocation.withDefaultNamespace("pull"), (stack, level, entity, p_344166_) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });

        ItemProperties.register(
                ModItems.MAX_SPEED_BOW.get(),
                ResourceLocation.withDefaultNamespace("pulling"),
                (stack, level, entity, p_174633_) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
        );
    }

	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}
}
