package erebus.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.stream.Stream;

public class ModNoiseRouter {

    protected static NoiseRouter erebus(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noiseParams) {
        return createErebusRouter(densityFunctions, noiseParams, slideNetherLike(densityFunctions));
    }

    private static NoiseRouter createErebusRouter(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noiseParameters, DensityFunction slide) {
        DensityFunction shiftX = getFunction(densityFunctions, NoiseRouterData.SHIFT_X);
        DensityFunction shiftZ = getFunction(densityFunctions, NoiseRouterData.SHIFT_Z);

        DensityFunction aquafierBarrier = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_BARRIER), 0.5);
        DensityFunction aquafierFlooding = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67);
        DensityFunction aquafierSpread = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143);
        DensityFunction lavaPool = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_LAVA));
        DensityFunction temperature = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25F, noiseParameters.getOrThrow(Noises.TEMPERATURE));
        DensityFunction vegetation = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25F, noiseParameters.getOrThrow(Noises.VEGETATION));
        DensityFunction factor = getFunction(densityFunctions, NoiseRouterData.FACTOR);
        DensityFunction depth = getFunction(densityFunctions, NoiseRouterData.DEPTH);
        DensityFunction depthFactorGradient = noiseGradientDensity(DensityFunctions.cache2d(factor), depth);
        DensityFunction slideErebus = postProcess(slide);
        DensityFunction y = getFunction(densityFunctions, NoiseRouterData.Y);
        int minY = Stream.of(ModOreVeinifier.VeinType.values()).mapToInt(type -> type.minY).min().orElse(-DimensionType.MIN_Y * 2);
        int maxY = Stream.of(ModOreVeinifier.VeinType.values()).mapToInt(type -> type.maxY).min().orElse(-DimensionType.MIN_Y * 2);

        DensityFunction oreVeininess = NoiseRouterData.yLimitedInterpolatable(
                y,
                DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEIN_A), 1.5, 1.5),
                minY,
                maxY,
                0
        );

        DensityFunction oreVeinA = NoiseRouterData.yLimitedInterpolatable(
                y,
                DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEIN_A), 4.0F, 4.0F),
                minY,
                maxY,
                0
        ).abs();

        DensityFunction oreVeinB = NoiseRouterData.yLimitedInterpolatable(
                y,
                DensityFunctions.noise(noiseParameters.getOrThrow(Noises.ORE_VEIN_B), 4.0F, 4.0F),
                minY,
                maxY,
                0
        ).abs();

        return new NoiseRouter(
                aquafierBarrier,
                aquafierFlooding,
                aquafierSpread,
                lavaPool,
                temperature,
                vegetation,
                getFunction(densityFunctions, NoiseRouterData.CONTINENTS),
                getFunction(densityFunctions, NoiseRouterData.EROSION),
                depth,
                getFunction(densityFunctions, NoiseRouterData.RIDGES),
                slideOverworld(DensityFunctions.add(depthFactorGradient, DensityFunctions.constant(-0.703125))).clamp(-64, 384),
                slideErebus,
                oreVeininess,
                oreVeinA,
                oreVeinB
        );
    }

    private static DensityFunction slideOverworld(DensityFunction densityFunction) {
        return slide(densityFunction, -64, 384, 80, 64, -0.078125, 0, 24, 0.1171875);
    }

    private static DensityFunction slideNetherLike(HolderGetter<DensityFunction> densityFunctions) {
        return slide(getFunction(densityFunctions, NoiseRouterData.BASE_3D_NOISE_OVERWORLD), -64, 384, 80, 64, -0.078125F, 0, 24, 0.1171875F);
    }

    private static DensityFunction getFunction(HolderGetter<DensityFunction> densityFunctions, ResourceKey<DensityFunction> key) {
        return new DensityFunctions.HolderHolder(densityFunctions.getOrThrow(key));
    }

    private static DensityFunction postProcess(DensityFunction densityFunction) {
        DensityFunction densityfunction = DensityFunctions.blendDensity(densityFunction);
        return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction), DensityFunctions.constant(0.64)).squeeze();
    }

    private static DensityFunction slide(DensityFunction input, int minY, int maxY, int p_224447_, int p_224448_, double p_224449_, int p_224450_, int p_224451_, double p_224452_) {
        DensityFunction densityfunction1 = DensityFunctions.yClampedGradient(minY + maxY - p_224447_, minY + maxY - p_224448_, 1.0F, 0.0F);
        DensityFunction $$9 = DensityFunctions.lerp(densityfunction1, p_224449_, input);
        DensityFunction densityfunction2 = DensityFunctions.yClampedGradient(minY + p_224450_, minY + p_224451_, 0.0F, 1.0F);
        return DensityFunctions.lerp(densityfunction2, p_224452_, $$9);
    }

    private static DensityFunction noiseGradientDensity(DensityFunction minFunction, DensityFunction maxFunction) {
        DensityFunction densityfunction = DensityFunctions.mul(maxFunction, minFunction);
        return DensityFunctions.mul(DensityFunctions.constant(4.0), densityfunction.quarterNegative());
    }
}
