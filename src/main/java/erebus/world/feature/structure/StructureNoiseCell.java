package erebus.world.feature.structure;

import erebus.mixin.NoiseGeneratorAccessor;
import erebus.world.BasinTerrain;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

final class StructureNoiseCell extends NoiseChunk {
    private StructureNoiseCell(NoiseBasedChunkGenerator generator, RandomState random,
                               int x, int z, LevelHeightAccessor height) {
        super(1, random, x, z, generator.generatorSettings().value().noiseSettings().clampToHeightAccessor(height),
                DensityFunctions.BeardifierMarker.INSTANCE, generator.generatorSettings().value(),
                ((NoiseGeneratorAccessor) generator).erebus$fluidPicker().get(), Blender.empty());
    }

    static NoiseColumn[] sample(NoiseBasedChunkGenerator generator, RandomState random, int x, int z, LevelHeightAccessor height, int basinMinY) {
        var settings = generator.generatorSettings().value();
        var noiseSettings = settings.noiseSettings().clampToHeightAccessor(height);
        int width = noiseSettings.getCellWidth(), cellHeight = noiseSettings.getCellHeight();
        int minimum = noiseSettings.minY(), count = noiseSettings.height();
        var states = new BlockState[width * width][count];
        var noise = new StructureNoiseCell(generator, random, x, z, height);
        noise.initializeForFirstCellX();
        noise.advanceCellX(0);

        try {
            for (int cellY = count / cellHeight - 1; cellY >= 0; cellY--) {
                noise.selectCellYZ(cellY, 0);
                for (int dy = cellHeight - 1; dy >= 0; dy--) {
                    int offset = cellY * cellHeight + dy;
                    noise.updateForY(minimum + offset, (double) dy / cellHeight);
                    for (int dx = 0; dx < width; dx++) {
                        noise.updateForX(x + dx, (double) dx / width);
                        for (int dz = 0; dz < width; dz++) {
                            noise.updateForZ(z + dz, (double) dz / width);
                            var state = noise.getInterpolatedState();
                            states[dx * width + dz][offset] = state == null ? settings.defaultBlock() : state;
                        }
                    }
                }
            }
        } finally {
            noise.stopInterpolation();
        }

        var columns = new NoiseColumn[width * width];
        for (int dx = 0; dx < width; dx++) {
            for (int dz = 0; dz < width; dz++) {
                var column = new NoiseColumn(minimum, states[dx * width + dz]);
                if (height.getMinY() <= basinMinY + BasinTerrain.MAX_AFFECTED_Y_OFFSET) {
                    BasinTerrain.apply(x + dx, z + dz, basinMinY, height.getMaxY(),
                            generator.getBiomeSource(), random, column::getBlock, column::setBlock);
                }
                columns[dx * width + dz] = column;
            }
        }
        return columns;
    }
}
