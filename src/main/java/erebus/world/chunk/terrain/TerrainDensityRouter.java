package erebus.world.chunk.terrain;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.datagen.ModRegistries;
import erebus.world.layer.biome.BiomeDensitySource;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.jetbrains.annotations.NotNull;

public class TerrainDensityRouter implements DensityFunction.SimpleFunction {
    public static final MapCodec<TerrainDensityRouter> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RegistryFileCodec.create(ModRegistries.BIOME_TERRAIN_DATA, BiomeDensitySource.CODEC, false).fieldOf("terrain_source").forGetter(TerrainDensityRouter::getBiomeDensitySource),
            Codec.doubleRange(-64, 0).fieldOf("lower_density_bound").forGetter(TerrainDensityRouter::getLowerDensityBound),
            Codec.doubleRange(0, 64).fieldOf("upper_density_bound").forGetter(TerrainDensityRouter::getUpperDensityBound),
            Codec.doubleRange(0, 32).orElse(8.0).fieldOf("depth_scalar").forGetter(TerrainDensityRouter::getDepthScalar),
            DensityFunction.HOLDER_HELPER_CODEC.fieldOf("base_factor").forGetter(TerrainDensityRouter::getBaseFactor),
            DensityFunction.HOLDER_HELPER_CODEC.fieldOf("base_offset").forGetter(TerrainDensityRouter::getBaseOffset)
    ).apply(instance, TerrainDensityRouter::new));

    public static final KeyDispatchDataCodec<TerrainDensityRouter> KEY_CODEC = KeyDispatchDataCodec.of(CODEC);

    private final Holder<BiomeDensitySource> biomeDensitySource;
    private final double lowerDensityBound;
    private final double upperDensityBound;
    private final double depthScalar;
    private final DensityFunction baseFactor;
    private final DensityFunction baseOffset;

    public TerrainDensityRouter(Holder<BiomeDensitySource> biomeDensitySource, double lowerDensityBound, double upperDensityBound, double depthScalar, DensityFunction baseFactor, DensityFunction baseOffset) {
        this.biomeDensitySource = biomeDensitySource;
        this.lowerDensityBound = lowerDensityBound;
        this.upperDensityBound = upperDensityBound;
        this.depthScalar = depthScalar;
        this.baseFactor = baseFactor;
        this.baseOffset = baseOffset;
    }

    @Override
    public double compute(@NotNull FunctionContext context) {
        BiomeDensitySource.DensityData data = computeTerrain(context);
        double depth = baseOffset.compute(context) + data.depth() * baseFactor.compute(context);
        return depth + data.depth();
    }

    public BiomeDensitySource.DensityData computeTerrain(FunctionContext context) {
        return biomeDensitySource.value().sampleTerrain(context.blockX(), context.blockZ(), context);
    }

    @Override
    public double minValue() {
        return lowerDensityBound;
    }

    @Override
    public double maxValue() {
        return upperDensityBound;
    }

    @Override
    public @NotNull KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return KEY_CODEC;
    }

    public DensityFunction getBaseOffset() {
        return baseOffset;
    }

    public DensityFunction getBaseFactor() {
        return baseFactor;
    }

    public double getDepthScalar() {
        return depthScalar;
    }

    public double getUpperDensityBound() {
        return upperDensityBound;
    }

    public double getLowerDensityBound() {
        return lowerDensityBound;
    }

    public Holder<BiomeDensitySource> getBiomeDensitySource() {
        return biomeDensitySource;
    }

    @Override
    @NotNull
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(new ChunkCachedDensityRouter(biomeDensitySource, lowerDensityBound, upperDensityBound, depthScalar, baseFactor, baseOffset));
    }
}
