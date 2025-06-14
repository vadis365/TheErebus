package erebus.world.chunk.noise;

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

public class NoiseDensityRouter implements DensityFunction.SimpleFunction {
    public static final MapCodec<NoiseDensityRouter> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            RegistryFileCodec.create(ModRegistries.BIOME_TERRAIN_DATA, BiomeDensitySource.CODEC, false).fieldOf("terrain_source").forGetter(NoiseDensityRouter::getBiomeDensitySource),
            Codec.doubleRange(-64, 0).fieldOf("lower_density_bound").forGetter(NoiseDensityRouter::getLowerDensityBound),
            Codec.doubleRange(0, 64).fieldOf("upper_density_bound").forGetter(NoiseDensityRouter::getUpperDensityBound),
            Codec.doubleRange(0, 32).orElse(8.0).fieldOf("depth_scalar").forGetter(NoiseDensityRouter::getDepthScalar)
    ).apply(inst, NoiseDensityRouter::new));
    public static final KeyDispatchDataCodec<NoiseDensityRouter> KEY_CODEC = KeyDispatchDataCodec.of(CODEC);

    private final Holder<BiomeDensitySource> biomeDensitySource;
    private final double lowerDensityBound;
    private final double upperDensityBound;
    private final double depthScalar;

    public NoiseDensityRouter(Holder<BiomeDensitySource> biomeDensitySource, double lowerDensityBound, double upperDensityBound, double depthScalar) {
        this.biomeDensitySource = biomeDensitySource;
        this.lowerDensityBound = lowerDensityBound;
        this.upperDensityBound = upperDensityBound;
        this.depthScalar = depthScalar;
    }
    @Override
    public double compute(@NotNull FunctionContext context) {
        return computeTerrain(context).scale;
    }

    @NotNull
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
        return visitor.apply(new ChunkCachedNoiseDensityRouter(biomeDensitySource, lowerDensityBound, upperDensityBound, depthScalar));
    }
}
