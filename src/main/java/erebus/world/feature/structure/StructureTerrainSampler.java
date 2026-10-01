package erebus.world.feature.structure;

import erebus.world.BasinTerrain;
import erebus.world.ModNoiseGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;

import java.util.HashMap;
import java.util.Map;

public final class StructureTerrainSampler {
    private final ChunkGenerator generator;
    private final LevelHeightAccessor height;
    private final RandomState random;
    private final int cellHeight;
    private final int minY;
    private final int maxY;
    private final boolean predictsBasins;
    private final int basinMinY;
    private final Map<Column, NoiseColumn> columns = new HashMap<>();
    private final Map<Long, Boolean> basinColumns = new HashMap<>();
    private final Map<Column, NoiseColumn[]> cells = new HashMap<>();

    public StructureTerrainSampler(ChunkGenerator generator, LevelHeightAccessor height, RandomState random) {
        this.generator = generator;
        this.height = height;
        this.random = random;
        predictsBasins = generator instanceof NoiseBasedChunkGenerator noise && noise.generatorSettings().is(ModNoiseGenerator.NOISE_GENERATOR);
        basinMinY = predictsBasins ? ((NoiseBasedChunkGenerator) generator).generatorSettings().value().noiseSettings().minY() : 0;
        int cell = 0, minimum = height.getMinY(), maximum = height.getMaxY();
        if (generator instanceof NoiseBasedChunkGenerator noise && !noise.generatorSettings().value().isAquifersEnabled()) {
            var settings = noise.generatorSettings().value().noiseSettings().clampToHeightAccessor(height);
            int size = settings.getCellHeight();

            if (settings.height() > 0 && Math.floorMod(settings.minY(), size) == 0 && settings.height() % size == 0) {
                cell = size;
                minimum = settings.minY();
                maximum = minimum + settings.height() - 1;
            }
        }
        cellHeight = cell;
        minY = minimum;
        maxY = maximum;
    }

    public BlockState getBlock(BlockPos pos) {
        if (cellHeight != 0 && (pos.getY() < minY || pos.getY() > maxY)) return Blocks.AIR.defaultBlockState();
        int cellBottom = cellHeight == 0 ? height.getMinY() : Math.floorDiv(pos.getY(), cellHeight) * cellHeight;
        boolean sliced = cellHeight != 0 && (cellBottom > basinMinY + BasinTerrain.MAX_AFFECTED_Y_OFFSET
                || !needsBasinColumn(pos.getX(), pos.getZ()));
        int bottom = sliced ? cellBottom : height.getMinY();

        if (predictsBasins && cellHeight != 0
                && ((NoiseBasedChunkGenerator) generator).generatorSettings().value().noiseSettings().getCellWidth() == 4) {
            int x = Math.floorDiv(pos.getX(), 4) * 4, z = Math.floorDiv(pos.getZ(), 4) * 4;
            var full = cells.get(new Column(x, z, height.getMinY()));

            var sampled = full != null && needsBasinColumn(x, z) ? full : cells.computeIfAbsent(
                    new Column(x, z, bottom), _ -> StructureNoiseCell.sample(
                            (NoiseBasedChunkGenerator) generator, random, x, z,
                            sliced ? LevelHeightAccessor.create(bottom, cellHeight) : height, basinMinY));
            return sampled[Math.floorMod(pos.getX(), 4) * 4 + Math.floorMod(pos.getZ(), 4)].getBlock(pos.getY());
        }
        var key = new Column(pos.getX(), pos.getZ(), bottom);
        return columns.computeIfAbsent(key, column -> generator.getBaseColumn(column.x(), column.z(),
                sliced ? LevelHeightAccessor.create(column.minY(), cellHeight) : height, random)).getBlock(pos.getY());
    }

    private boolean needsBasinColumn(int x, int z) {
        if (!predictsBasins) return false;
        long column = ((long) x << 32) ^ (z & 0xffffffffL);
        // Basin prediction can inspect and reshape other heights in the same column.
        return basinColumns.computeIfAbsent(column, _ -> BasinTerrain.modifiesTerrain(
                generator.getBiomeSource().getNoiseBiome(x >> 2, (basinMinY + 24) >> 2, z >> 2, random.sampler())));
    }

    private record Column(int x, int z, int minY) {
    }
}
