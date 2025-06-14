package erebus.world.chunk.terrain;

import erebus.world.layer.biome.BiomeDensitySource;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.levelgen.DensityFunction;

public class ChunkCachedDensityRouter extends TerrainDensityRouter {
    private final BiomeDensitySource biomeDensitySource;
    private final BiomeDensitySource.DensityData[] horizontalCache = new BiomeDensitySource.DensityData[16 * 16];

    public ChunkCachedDensityRouter(Holder<BiomeDensitySource> biomeDensitySource, double lowerDensityBound, double upperDensityBound, double depthScalar, DensityFunction baseFactor, DensityFunction baseOffset) {
        super(biomeDensitySource, lowerDensityBound, upperDensityBound, depthScalar, baseFactor, baseOffset);
        this.biomeDensitySource = biomeDensitySource.value();
    }

    @Override
    public BiomeDensitySource.DensityData computeTerrain(FunctionContext context) {
        int xInChunk = SectionPos.sectionRelative(context.blockX());
        int zInChunk = SectionPos.sectionRelative(context.blockZ());

        int arrayCoord = zInChunk + (xInChunk << 4);

        BiomeDensitySource.DensityData dataColumn = horizontalCache[arrayCoord];

        if(dataColumn == null) {
            dataColumn = biomeDensitySource.sampleTerrain(context.blockX(), context.blockZ(), context);
            horizontalCache[arrayCoord] = dataColumn;
        }

        return dataColumn;
    }
}
