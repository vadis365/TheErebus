package erebus.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoiseRouter {

    protected static NoiseRouter erebus(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noiseParams) {
        return noNewCaves(densityFunctions, noiseParams, slideNetherLike(densityFunctions, -64, 384));
    }

    private static NoiseRouter noNewCaves(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noiseParameters, DensityFunction slide) {
        DensityFunction shiftX = getFunction(densityFunctions, NoiseRouterData.SHIFT_X);
        DensityFunction shiftZ = getFunction(densityFunctions, NoiseRouterData.SHIFT_Z);
        DensityFunction temperature = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25F, noiseParameters.getOrThrow(Noises.TEMPERATURE));
        DensityFunction vegetation = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25F, noiseParameters.getOrThrow(Noises.VEGETATION));
        DensityFunction processedSlide = postProcess(slide);
        return new NoiseRouter(
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                temperature,
                vegetation,
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                processedSlide,
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero()
        );
    }

    private static DensityFunction slideNetherLike(HolderGetter<DensityFunction> densityFunctions, int minY, int maxY) {
        return slide(getFunction(densityFunctions, NoiseRouterData.BASE_3D_NOISE_NETHER), minY, maxY, 24, 0, 0.9375F, -8, 24, 2.5F);
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
}
