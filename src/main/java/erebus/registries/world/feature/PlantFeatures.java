package erebus.registries.world.feature;

import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.helpers.ModFeatureHelpers;
import erebus.world.feature.bush.HeartBerryBushFeature;
import erebus.world.feature.bush.JadeBerryBushFeature;
import erebus.world.feature.bush.SwampBerryBushFeature;
import erebus.world.feature.plant.ErebusPlantFeature;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class PlantFeatures extends ModFeatureHelpers {

    public static ErebusPlantFeature NETTLE = new ErebusPlantFeature("nettle");
    public static ErebusPlantFeature SWAMP_PLANT = new ErebusPlantFeature("swamp_plant");
    public static ErebusPlantFeature FIRE_BLOOM = new ErebusPlantFeature("fire_bloom");
    public static ErebusPlantFeature FIDDLE_HEAD = new ErebusPlantFeature("fiddle_head");
    public static ErebusPlantFeature BULLRUSH = new ErebusPlantFeature("bullrush");
    public static ErebusPlantFeature WEEPING_BLUEBELL = new ErebusPlantFeature("weeping_bluebell");
    public static ErebusPlantFeature SUNDEW = new ErebusPlantFeature("sundew");
    public static ErebusPlantFeature TALL_BLOOM = new ErebusPlantFeature("tall_bloom");
    public static ErebusPlantFeature TANGLED_STALK = new ErebusPlantFeature("tangled_stalk");
    public static ErebusPlantFeature HIGH_CAPPED_MUSHROOM = new ErebusPlantFeature("high_capped_mushroom");
    public static ErebusPlantFeature FERN = new ErebusPlantFeature("fern");
    public static ErebusPlantFeature TALL_FERN = new ErebusPlantFeature("tall_fern");

    public static SwampBerryBushFeature SWAMP_BERRY_BUSH;
    public static HeartBerryBushFeature HEART_BERRY_BUSH;
    public static JadeBerryBushFeature JADE_BERRY_BUSH;

    public static void initConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        setConfiguredContext(context);
        SWAMP_BERRY_BUSH = new SwampBerryBushFeature();
        HEART_BERRY_BUSH = new HeartBerryBushFeature();
        JADE_BERRY_BUSH = new JadeBerryBushFeature();

        registerConfiguredBush(SWAMP_BERRY_BUSH);
        registerConfiguredBush(HEART_BERRY_BUSH);
        registerConfiguredBush(JADE_BERRY_BUSH);

        registerConfiguredFeature(
                NETTLE.getConfiguredResourceKey(),
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,
                        2,
                        0,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new NoiseThresholdProvider(
                                                2345L,
                                                new NormalNoise.NoiseParameters(0, 1.0),
                                                0.005F,
                                                -0.8F,
                                                0.33333334F,
                                                PlantBlocks.NETTLE.get().defaultBlockState(),
                                                List.of(PlantBlocks.NETTLE.get().defaultBlockState()),
                                                List.of(PlantBlocks.NETTLE_FLOWERED.get().defaultBlockState())
                                        )
                                )
                        )
                )
        );

        registerSimpleConfiguredPlant(context, SWAMP_PLANT, PlantBlocks.SWAMP_PLANT, 64);
        registerSimpleConfiguredPlant(context, FIRE_BLOOM, PlantBlocks.FIRE_BLOOM, 64);
        registerSimpleConfiguredPlant(context, FIDDLE_HEAD, PlantBlocks.FIDDLE_HEAD, 64);
        registerSimpleConfiguredPlant(context, BULLRUSH, PlantBlocks.BULLRUSH, 64);
        registerSimpleConfiguredPlant(context, WEEPING_BLUEBELL, PlantBlocks.WEEPING_BLUEBELL, 64);
        registerSimpleConfiguredPlant(context, SUNDEW, PlantBlocks.SUNDEW, 64);
        registerSimpleConfiguredPlant(context, TALL_BLOOM, PlantBlocks.TALL_BLOOM, 64);
        registerSimpleConfiguredPlant(context, TANGLED_STALK, PlantBlocks.TANGLED_STALK, 64);
        registerSimpleConfiguredPlant(context, HIGH_CAPPED_MUSHROOM, PlantBlocks.HIGH_CAPPED_MUSHROOM, 64);
        registerSimpleConfiguredPlant(context, FERN, PlantBlocks.FERN, 32);
        registerSimpleConfiguredPlant(context, TALL_FERN, PlantBlocks.TALL_FERN, 32);
    }

    public static void initPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        setPlacedContext(context);
        registerPlacedFeature(SWAMP_BERRY_BUSH);
        registerPlacedFeature(HEART_BERRY_BUSH);
        registerPlacedFeature(JADE_BERRY_BUSH);

        registerPlacedFeature(NETTLE);
        registerPlacedFeature(SWAMP_PLANT);
        registerPlacedFeature(FIRE_BLOOM);
        registerPlacedFeature(FIDDLE_HEAD);
        registerPlacedFeature(BULLRUSH);
        registerPlacedFeature(WEEPING_BLUEBELL);
        registerPlacedFeature(SUNDEW);
        registerPlacedFeature(TALL_BLOOM);
        registerPlacedFeature(TANGLED_STALK);
        registerPlacedFeature(HIGH_CAPPED_MUSHROOM);
        registerPlacedFeature(FERN);
        registerPlacedFeature(TALL_FERN);
    }
}
