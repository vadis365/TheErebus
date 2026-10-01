package erebus;

import com.mojang.logging.LogUtils;
import erebus.events.BedPlaceEventHandler;
import erebus.events.OnEntityJumpEventHandler;
import erebus.item.consume.HealConsumeEffect;
import erebus.loot.*;
import erebus.registries.ModCustomRecipes;
import erebus.registries.ModFluids;
import erebus.registries.ModSounds;
import erebus.registries.ModTabs;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModBlockEntityRendering;
import erebus.registries.client.ModItemRendering;
import erebus.registries.client.ModMenuTypes;
import erebus.registries.client.ModParticles;
import erebus.registries.data.ModAttachmentTypes;
import erebus.registries.data.ModDataComponents;
import erebus.registries.data.ModPredicates;
import erebus.registries.entity.ModEntities;
import erebus.registries.entity.ModEntityRendering;
import erebus.registries.item.ModItems;
import erebus.registries.network.ModNetwork;
import erebus.registries.world.ModBiomeLayerTypes;
import erebus.registries.world.ModOrePlacements;
import erebus.registries.world.ModPOIs;
import erebus.registries.world.carver.ModWorldCarvers;
import erebus.registries.world.feature.ModFeatures;
import erebus.registries.world.feature.config.DecorationFeatureConfigs;
import erebus.registries.world.feature.config.PlantFeatureConfigs;
import erebus.registries.world.structure.ModStructurePieces;
import erebus.registries.world.structure.ModStructureProcessors;
import erebus.registries.world.structure.ModStructureTypes;
import erebus.registries.world.tree.ModFoliagePlacers;
import erebus.registries.world.tree.ModTreeDecorators;
import erebus.registries.world.tree.ModTrunkPlacers;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.slf4j.Logger;

import java.util.Locale;

@Mod(Erebus.MODID)
public class Erebus {

    public static final String MODID = "erebus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Erebus(IEventBus bus, ModContainer container, Dist dist) {
        var neoBus = NeoForge.EVENT_BUS;
        DecorationFeatureConfigs.init();
        PlantFeatureConfigs.init();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModFluids.FLUIDS.register(bus);
        ModFluids.FLUID_TYPES.register(bus);
        ModTabs.CREATIVE_MODE_TABS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModSounds.SOUNDS.register(bus);
        ModMenuTypes.MENU_TYPES.register(bus);
        ModTrunkPlacers.TRUNK_PLACERS.register(bus);
        ModFoliagePlacers.FOLIAGE_PLACERS.register(bus);
        ModTreeDecorators.TREE_DECORATORS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModPOIs.POI.register(bus);
        ModCustomRecipes.RECIPE_TYPES.register(bus);
        ModCustomRecipes.RECIPE_SERIALIZERS.register(bus);
        ModCustomRecipes.RECIPE_BOOK_CATEGORIES.register(bus);
        ModParticles.PARTICLES.register(bus);
        ModDataComponents.DATA_COMPONENT_REGISTRY.register(bus);
        ModAttachmentTypes.TYPES.register(bus);
        HealConsumeEffect.TYPES.register(bus);
        ModFeatures.CONFIGS.register(bus);
        BalsamResinCount.TYPES.register(bus);
        BeetlePlateCount.TYPES.register(bus);
        ScytodesEyeCount.TYPES.register(bus);
        MoneySpiderIngotCount.TYPES.register(bus);
        HoneyPotNectarCount.TYPES.register(bus);
        TarantulaDropCount.TYPES.register(bus);
        JumpingSpiderDropCount.TYPES.register(bus);
        CropWeevilSeedCount.TYPES.register(bus);
        LegacyArthropodRolls.TYPES.register(bus);
        StagPlateCount.TYPES.register(bus);
        RedGemDropCount.TYPES.register(bus);
        ModOrePlacements.TYPES.register(bus);
        ModWorldCarvers.CARVERS.register(bus);
        ModStructurePieces.STRUCTURE_PIECES.register(bus);
        ModStructureProcessors.STRUCTURE_PROCESSORS.register(bus);
        ModStructureTypes.STRUCTURE_TYPES.register(bus);
        ModBiomeLayerTypes.BIOME_LAYER_TYPES.register(bus);
        ModPredicates.PREDICATES.register(bus);

        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        bus.addListener(ModNetwork::register);

        neoBus.register(new BedPlaceEventHandler());
        neoBus.register(new OnEntityJumpEventHandler());

        NeoForgeMod.enableMilkFluid();

        if (dist == Dist.CLIENT) {
            bus.addListener(ModEntityRendering::registerEntityLayers);
            bus.addListener(ModEntityRendering::registerEntityRender);
            bus.addListener(ModItemRendering::registerItemLayerDefinitions);
            bus.addListener(ModItemRendering::registerItemRender);
            bus.addListener(ModBlockEntityRendering::registerBlockEntityLayerDefinitions);
            bus.addListener(ModBlockEntityRendering::registerBlockEntityRenderers);
            bus.addListener(ModParticles::registerParticleFactories);
        }
    }

    public static Identifier prefix(String name) {
        return Identifier.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
    }
}
