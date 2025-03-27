package erebus.world.gen.warp;

import erebus.world.gen.ModBiomeSource;
import net.minecraft.Util;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;

public class TerrainWarper {
    protected static final float[] BIOME_WEIGHTS = Util.make(new float[25], (floats) -> {
        for (int x = -2; x <= 2; ++x) {
            for (int z = -2; z <= 2; ++z) {
                float weight = 10.0F / Mth.sqrt((float) (x * x + z * z) + 0.2F);
                floats[x + 2 + (z + 2) * 5] = weight;
            }
        }
    });
    public final NoiseModifier caveNoiseModifier;
    private final int cellWidth;
    private final int cellHeight;
    private final int cellCountY;
    private final BiomeSource source;
    private final NoiseSettings settings;
    private final NoiseSlider topSlide;
    private final NoiseSlider bottomSlider;
    private final BlendedNoise blendedNoise;
    private final double dimensionDensityFactor;
    private final double dimensionDensityOffset;

    public TerrainWarper(int width, int height, int y, BiomeSource source, NoiseSettings settings, NoiseSlider topSlide, NoiseSlider bottomSlider, BlendedNoise blendedNoise, NoiseModifier modifier) {
        cellWidth = width;
        cellHeight = height;
        cellCountY = y;
        this.source = source;
        this.settings = settings;
        this.topSlide = topSlide;
        this.bottomSlider = bottomSlider;
        this.blendedNoise = blendedNoise;
        this.dimensionDensityFactor = source instanceof ModBiomeSource biomeSource ? biomeSource.getBaseFactor() : 1.0F;
        this.dimensionDensityOffset = source instanceof ModBiomeSource biomeSource ? biomeSource.getBaseOffset() : 1.0F;
        this.caveNoiseModifier = modifier;
    }

    public void fillNoiseColumn(double[] doubles, int x, int z, Climate.Sampler sampler, int seaLevel, int min, int max) {
        if (source instanceof ModBiomeSource source) {
            double d0;
            double d1;
            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            float depth = source.getBiomeDepth(x, seaLevel, z, sampler);

            for (int offX = -2; offX <= 2; ++offX) {
                for (int offZ = -2; offZ <= 2; ++offZ) {
                    Biome biome = source.getNoiseBiome(x + offX, seaLevel, z + offZ, sampler).value();
                    float offD = source.getBiomeDepth(biome);
                    float offS = source.getBiomeScale(biome);
                    float f6;
                    float f7;
                    f6 = offD;
                    f7 = offS;

                    float f8 = offD > depth ? 0.5F : 1.0F;
                    float f9 = f8 * BIOME_WEIGHTS[offX + 2 + (offZ + 2) * 5] / (f6 + 2.0F);
                    f += f7 * f9;
                    f1 += f6 * f9;
                    f2 += f9;
                }
            }

            float f10 = f1 / f2;
            float f11 = f / f2;
            double d6 = f10 * 0.5F - 0.125F;
            double d8 = f11 * 0.9F + 0.1F;
            d0 = d6 * 0.265625D;
            d1 = 96.0D / d8;
            double density = -0.46875;

            for (int index = 0; index <= max; ++index) {
                int y = index + min;
                DensityFunction.FunctionContext context = new DensityFunction.SinglePointContext(x, y, z);
                double noise = blendedNoise.compute(context) * 128.0D;
                double totaldensity = computeInitialDensity(y, d0, d1, density) + noise;
                totaldensity = caveNoiseModifier.modifyNoise(totaldensity, y * cellHeight, z * cellWidth, x * cellWidth);
                totaldensity = applySlide(totaldensity, y);
                doubles[index] = totaldensity;
            }
        }

        throw new IllegalArgumentException("BiomeSource is not an instance of ModBiomeSource");
    }

    protected double computeInitialDensity(int y, double offset, double factor, double density) {
        double base = 1.0D - (double) y * 2.0D / 32.0D + density;
        double factored = base * dimensionDensityFactor + dimensionDensityOffset;
        double total = (factored + offset) * factor;
        return total * (double) (total > 0.0D ? 4 : 1);
    }

    protected double applySlide(double density, int height) {
        int i = Math.floorDiv(settings.minY(), cellHeight);
        int j = height - i;
        density = topSlide.applySlide(density, cellCountY - j);
        density = bottomSlider.applySlide(density, j);
        return density;
    }
}
