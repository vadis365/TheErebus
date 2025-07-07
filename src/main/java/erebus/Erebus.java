package erebus;

import java.util.Locale;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import erebus.block.entity.BambooPipeBlockEntity;
import erebus.block.entity.BambooPipeExtractBlockEntity;
import erebus.block.entity.FluidJarBlockEntity;
import erebus.block.entity.LiquifierBlockEntity;
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
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@Mod(Erebus.MODID)
public class Erebus {

    public static final String MODID = "erebus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Erebus(IEventBus bus, ModContainer container, Dist dist) {
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

        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        bus.addListener(ModNetwork::register);

        NeoForgeMod.enableMilkFluid(); // TEMP - JUST FOR BEETLE MILKING TEST

        bus.addListener(this::registerCaps);

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
    }

	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}

	public void registerCaps(final RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.FLUID_JAR.get(), FluidJarBlockEntity::getTank);
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.BAMBOO_PIPE.get(), BambooPipeBlockEntity::getTank);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.BAMBOO_PIPE_EXTRACT.get(), BambooPipeExtractBlockEntity::getTank);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.LIQUIFIER.get(), LiquifierBlockEntity::getTank);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.LIQUIFIER.get(), (liquifier, side) -> new InvWrapper(liquifier));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.HONEY_COMB.get(), (honey_comb, side) -> new InvWrapper(honey_comb));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.BAMBOO_EXTENDER.get(), (extender, side) -> new InvWrapper(extender));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.BAMBOO_CRATE.get(), (crate, side) -> new InvWrapper(crate));
		event.registerItem(Capabilities.FluidHandler.ITEM, (stack, ctx) -> new FluidBucketWrapper(stack), ModItems.BEETLE_JUICE_BUCKET.get());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.COMPOSTER.get(), (composter, side) -> new SidedInvWrapper(composter, side));
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.SILO_TANK.get(), (silo, side) -> new SidedInvWrapper(silo, side));
	
	}
}
