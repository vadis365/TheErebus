package erebus.world.chunk.noise;

import erebus.world.layer.biome.BiomeDensitySource;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
import org.jetbrains.annotations.NotNull;

public class ChunkCachedNoiseDensityRouter extends NoiseDensityRouter {
    private final BiomeDensitySource biomeDensitySource;

    private final BiomeDensitySource.DensityData[] horizontalCache = new BiomeDensitySource.DensityData[16 * 16];

    public ChunkCachedNoiseDensityRouter(Holder<BiomeDensitySource> biomeDensitySource, double lowerDensityBound, double upperDensityBound, double depthScalar) {
        super(biomeDensitySource, lowerDensityBound, upperDensityBound, depthScalar);
        this.biomeDensitySource = biomeDensitySource.value();
    }

    @NotNull
    @Override
    public BiomeDensitySource.DensityData computeTerrain(FunctionContext context) {
        int xInChunk = SectionPos.sectionRelative(context.blockX());
        int zInChunk = SectionPos.sectionRelative(context.blockZ());

        int arrayCoord = zInChunk + (xInChunk << 4);

        BiomeDensitySource.DensityData dataColumn = this.horizontalCache[arrayCoord];

        if (dataColumn == null) {
            dataColumn = this.biomeDensitySource.sampleTerrain(context.blockX(), context.blockZ(), context);
            this.horizontalCache[arrayCoord] = dataColumn;
        }

        return dataColumn;
    }
}
