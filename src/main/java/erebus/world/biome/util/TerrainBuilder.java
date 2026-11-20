package erebus.world.biome.util;

import erebus.registries.world.ModBiomes;
import erebus.world.chunk.terrain.TerrainColumn;
import it.unimi.dsi.fastutil.doubles.Double2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.doubles.Double2ObjectSortedMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;

import java.util.List;
import java.util.function.Consumer;

public class TerrainBuilder {

    public static final int GLOWROT_UPPER_START_Y = 32;

    public static List<TerrainColumn> getBiomeColumns(HolderGetter<Biome> biomeRegistry) {
        return List.of(
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.ELYSIAN_FIELDS.getResourceKey()),
                biomeColumnToBedrock(-0.7D, 4.20D, 0.5D, biomeRegistry, ModBiomes.ELYSIAN_FOREST.getResourceKey()),
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.FUNGAL_FOREST.getResourceKey()),
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.PETRIFIED_FOREST.getResourceKey()),
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.SUBMERGED_SWAMP.getResourceKey()),
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey()),
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.ULTERIOR_OUTBACK.getResourceKey()),
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.UNDERGROUND_JUNGLE.getResourceKey()),
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.VOLCANIC_DESERT.getResourceKey()),
                biomeColumnToBedrock(-0.8D, 3.95D, 1D, biomeRegistry, ModBiomes.ROOTWATER_MARSH_KEY),
                biomeColumnToBedrock(-0.7D, 4.1D, 1D, biomeRegistry, ModBiomes.CINDER_SCRUB_KEY),
                layeredBiomeColumn(-0.7D, 4.2D, 0.75D, biomeRegistry, ModBiomes.GLOWROT_GROVES_KEY,
                        ModBiomes.FUNGAL_FOREST_KEY, GLOWROT_UPPER_START_Y),
                biomeColumnToBedrock(-0.75D, 4.1D, 1D, biomeRegistry, ModBiomes.AMBER_THICKETS_KEY),
                biomeColumnToBedrock(-0.7D, 4.05D, 1D, biomeRegistry, ModBiomes.FOSSIL_BARRENS_KEY)
        );
    }

    private static TerrainColumn layeredBiomeColumn(double noiseDepth, double noiseScale, double weight,
                                                     HolderGetter<Biome> biomeRegistry, ResourceKey<Biome> upper,
                                                     ResourceKey<Biome> lower, int upperStartBlockY) {
        if (Math.floorMod(upperStartBlockY, QuartPos.SIZE) != 0) {
            throw new IllegalArgumentException("Biome boundary must align with a quart: " + upperStartBlockY);
        }

        int upperQuart = QuartPos.fromBlock(upperStartBlockY);
        var upperBiome = biomeRegistry.getOrThrow(upper);
        upperBiome.bindKey(upper);
        return makeColumn(DensityFunctions.constant(noiseDepth), DensityFunctions.constant(noiseScale),
                DensityFunctions.constant(weight), upperBiome, layers -> {
                    // Adjacent anchors put the midpoint between samples, avoiding the lower-anchor tie.
                    layers.put(upperQuart - 1, biomeRegistry.getOrThrow(lower));
                    layers.put(upperQuart, upperBiome);
                });
    }

    private static TerrainColumn biomeColumnToBedrock(double noiseDepth, double noiseScale, double weight, HolderGetter<Biome> biomeRegistry, ResourceKey<Biome> key) {
        Holder.Reference<Biome> biomeHolder = biomeRegistry.getOrThrow(key);

        biomeHolder.bindKey(key);

        return makeColumn(DensityFunctions.constant(noiseDepth), DensityFunctions.constant(noiseScale), DensityFunctions.constant(weight), biomeHolder, treeMap -> treeMap.put(0, biomeHolder));
    }

    private static TerrainColumn makeColumn(DensityFunction noiseDepth, DensityFunction noiseScale, DensityFunction noiseWeight, Holder<Biome> biomeHolder, Consumer<Double2ObjectSortedMap<Holder<Biome>>> layerBuilder) {
        return new TerrainColumn(biomeHolder, Util.make(new Double2ObjectAVLTreeMap<>(), layerBuilder), noiseDepth, noiseScale, noiseWeight);
    }
}
