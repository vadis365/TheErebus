package erebus.world.biome.util;

import erebus.registries.world.ModBiomes;
import erebus.world.chunk.terrain.TerrainColumn;
import it.unimi.dsi.fastutil.doubles.Double2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.doubles.Double2ObjectSortedMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;

import java.util.List;
import java.util.function.Consumer;

public class TerrainBuilder {

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
                biomeColumnToBedrock(-0.7D, 4.20D, 1.0D, biomeRegistry, ModBiomes.VOLCANIC_DESERT.getResourceKey())
        );
    }

    private static TerrainColumn biomeColumnWithUnderground(double noiseDepth, double noiseScale, double weight, HolderGetter<Biome> biomeRegistry, ResourceKey<Biome> key, Holder<Biome> undergroundBiome) {
        Holder.Reference<Biome> biomeHolder = biomeRegistry.getOrThrow(key);

        biomeHolder.bindKey(key);

        return makeColumn(DensityFunctions.constant(noiseDepth), DensityFunctions.constant(noiseScale), DensityFunctions.constant(weight), biomeHolder, treeMap -> {
            // This will put the transition boundary around Y-8
            treeMap.put(Math.min(noiseDepth - 1, -1), biomeHolder);
            treeMap.put(Math.min(noiseDepth - 3, -3), undergroundBiome);
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
