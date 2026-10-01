package erebus.world.carver;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;

final class CarverWaterBoundary {
    private CarverWaterBoundary() {
    }

    static boolean intersectsWater(ChunkAccess chunk, int minGenY, int maxGenY, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        var chunkPos = chunk.getPos();
        var pos = new BlockPos.MutableBlockPos();
        for (int x = minX; x < maxX; x++)
            for (int z = minZ; z < maxZ; z++) {
                for (int y = maxY + 1; y >= minY - 1; y--) {
                    if (y >= minGenY && y < maxGenY) {
                        pos.set(chunkPos.getBlockX(x), y, chunkPos.getBlockZ(z));
                        if (chunk.getBlockState(pos).is(Blocks.WATER)) return true;
                    }
                    if (y != minY - 1 && x != minX && x != maxX - 1 && z != minZ && z != maxZ - 1) y = minY;
                }
            }
        return false;
    }
}
