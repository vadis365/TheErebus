package erebus.registries.world;

import erebus.world.biome.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {

    // Biomes
    public static final ElysianFieldsBiome ELYSIAN_FIELDS = new ElysianFieldsBiome(0xC6FF54, 0xD5E47F);
    public static final ElysianForestBiome ELYSIAN_FOREST = new ElysianForestBiome(0x74BF26, 0xD5E47F);
    public static final FungalForestBiome FUNGAL_FOREST = new FungalForestBiome(0x4E8833, 0xCBD1DA);
    public static final PetrifiedForestBiome PETRIFIED_FOREST = new PetrifiedForestBiome(0xC1B668, 0xEAC272);
    public static final SubmergedSwampBiome SUBMERGED_SWAMP = new SubmergedSwampBiome(0x314D31, 0x088008);
    public static final SubterraneanSavannahBiome SUBTERRANEAN_SAVANNAH = new SubterraneanSavannahBiome(0xB6B957, 0x8C7409);
    public static final UlteriorOutbackBiome ULTERIOR_OUTBACK = new UlteriorOutbackBiome(0xC1B668, 0xEAC272);
    public static final UndergroundJungleBiome UNDERGROUND_JUNGLE = new UndergroundJungleBiome(0x53CA37, 0x088008);
    public static final VolcanicDessertBiome VOLCANIC_DESERT = new VolcanicDessertBiome(0xA6BB4E, 0xFFE70A);

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> featureGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(ELYSIAN_FIELDS.getResourceKey(), ELYSIAN_FIELDS.getBiome());
        context.register(ELYSIAN_FOREST.getResourceKey(), ELYSIAN_FOREST.getBiome());
        context.register(FUNGAL_FOREST.getResourceKey(), FUNGAL_FOREST.getBiome());
        context.register(PETRIFIED_FOREST.getResourceKey(), PETRIFIED_FOREST.getBiome());
        context.register(SUBMERGED_SWAMP.getResourceKey(), SUBMERGED_SWAMP.getBiome());
        context.register(SUBTERRANEAN_SAVANNAH.getResourceKey(), SUBTERRANEAN_SAVANNAH.getBiome());
        context.register(ULTERIOR_OUTBACK.getResourceKey(), ULTERIOR_OUTBACK.getBiome());
        context.register(UNDERGROUND_JUNGLE.getResourceKey(), UNDERGROUND_JUNGLE.getBiome());
        context.register(VOLCANIC_DESERT.getResourceKey(), VOLCANIC_DESERT.getBiome());
    }
}
