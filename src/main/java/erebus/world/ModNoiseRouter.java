package erebus.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import static net.minecraft.world.level.levelgen.DensityFunctions.*;

public class ModNoiseRouter {

    protected static NoiseRouter erebus(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise) {
        DensityFunction shiftX = getFunction(densityFunctions, NoiseRouterData.SHIFT_X);
        DensityFunction shiftZ = getFunction(densityFunctions, NoiseRouterData.SHIFT_Z);

        return new NoiseRouter(
                getBarrier(),
                getFluidLevelFloodedness(),
                getFluidLevelSpread(),
                getLavaPools(),
                getTemperature(shiftX, shiftZ, noise),
                getVegetation(shiftX, shiftZ, noise),
                getContinents(densityFunctions),
                getErosion(densityFunctions),
                getDepth(densityFunctions),
                getRidges(densityFunctions),
                getInitialDensity(),
                getFinalDensity(),
                getVeinToggle(),
                getVeinRidged(),
                getVeinGap()
        );
    }

    private static DensityFunction getBarrier() {
        return zero();
    }

    private static DensityFunction getFluidLevelFloodedness() {
        return zero();
    }

    private static DensityFunction getFluidLevelSpread() {
        return zero();
    }

    private static DensityFunction getLavaPools() {
        return zero();
    }

    private static DensityFunction getTemperature(DensityFunction shiftX, DensityFunction shiftZ, HolderGetter<NormalNoise.NoiseParameters> noise) {
        return shiftedNoise2d(shiftX, shiftZ, 0.25D, noise.getOrThrow(Noises.TEMPERATURE));
    }

    private static DensityFunction getVegetation(DensityFunction shiftX, DensityFunction shiftZ, HolderGetter<NormalNoise.NoiseParameters> noise) {
        return shiftedNoise2d(shiftX, shiftZ, 0.25D, noise.getOrThrow(Noises.VEGETATION));
    }

    private static DensityFunction getContinents(HolderGetter<DensityFunction> functions) {
        return getFunction(functions, NoiseRouterData.CONTINENTS);
    }

    private static DensityFunction getErosion(HolderGetter<DensityFunction> functions) {
        return getFunction(functions, NoiseRouterData.EROSION);
    }

    private static DensityFunction getDepth(HolderGetter<DensityFunction> functions) {
        return rangeChoice(
                getFunction(functions, NoiseRouterData.Y),
                0.0D,
                32.0D,
                constant(2.0D),
                constant(-2.0D)
        );
    }

    private static DensityFunction getRidges(HolderGetter<DensityFunction> functions) {
        return getFunction(functions, NoiseRouterData.RIDGES);
    }

    private static DensityFunction getInitialDensity() {
        return zero();
    }

    private static DensityFunction getFinalDensity() {
        return mul(
                constant(0.64D),
                interpolated(
                        blendDensity(
                                add(
                                        constant(2.5D),
                                        mul(
                                                yClampedGradient(-8, 24, 0.0D, 1.0D),
                                                add(
                                                        constant(-2.5D),
                                                        add(
                                                                constant(0.5D),
                                                                mul(
                                                                        yClampedGradient(110, 128, 1.0D, 0.0D),
                                                                        add(
                                                                                constant(-0.5F),
                                                                                BlendedNoise.createUnseeded(0.1D, 0.3D, 80D, 60D, 1D)
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        ).squeeze();
    }

    private static DensityFunction getVeinToggle() {
        return zero();
    }

    private static DensityFunction getVeinRidged() {
        return zero();
    }

    private static DensityFunction getVeinGap() {
        return zero();
    }

    private static DensityFunction getFunction(HolderGetter<DensityFunction> functions, ResourceKey<DensityFunction> key) {
        return new HolderHolder(functions.getOrThrow(key));
    }
}
