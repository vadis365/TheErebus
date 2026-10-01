package erebus.mixin;

import erebus.world.BasinTerrain;
import erebus.world.ModNoiseGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseBasedChunkGenerator.class)
public abstract class BasinTerrainMixin {
    @Inject(method = "getBaseColumn", at = @At("RETURN"))
    private void erebus$predictBasin(int x, int z, LevelHeightAccessor heightAccessor, RandomState randomState, CallbackInfoReturnable<NoiseColumn> callback) {
        var generator = (NoiseBasedChunkGenerator) (Object) this;
        if (!generator.generatorSettings().is(ModNoiseGenerator.NOISE_GENERATOR)) return;
        var column = callback.getReturnValue();
        int minY = generator.generatorSettings().value().noiseSettings().minY();
        // Upper noise-cell slices cannot be changed by a basin, and cannot hold its lower writes.
        if (heightAccessor.getMinY() > minY + BasinTerrain.MAX_AFFECTED_Y_OFFSET) return;
        BasinTerrain.apply(x, z, minY, heightAccessor.getMaxY(), generator.getBiomeSource(), randomState, column::getBlock, column::setBlock);
    }

    @Inject(method = "buildSurface(Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/levelgen/WorldGenerationContext;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/biome/BiomeManager;Lnet/minecraft/core/Registry;Lnet/minecraft/world/level/levelgen/blending/Blender;)V", at = @At("RETURN"))
    private void erebus$buildBasin(ChunkAccess protoChunk, WorldGenerationContext context, RandomState randomState, StructureManager structureManager, BiomeManager biomeManager, Registry<Biome> biomeRegistry, Blender blender, CallbackInfo callback) {
        var generator = (NoiseBasedChunkGenerator) (Object) this;
        if (!generator.generatorSettings().is(ModNoiseGenerator.NOISE_GENERATOR)) return;
        int minY = generator.generatorSettings().value().noiseSettings().minY();
        var pos = new BlockPos.MutableBlockPos();
        for (int x = protoChunk.getPos().getMinBlockX(); x <= protoChunk.getPos().getMaxBlockX(); x++) {
            for (int z = protoChunk.getPos().getMinBlockZ(); z <= protoChunk.getPos().getMaxBlockZ(); z++) {
                pos.set(x, minY, z);
                BasinTerrain.apply(x, z, minY, protoChunk.getMaxY(), generator.getBiomeSource(), randomState, y -> protoChunk.getBlockState(pos.setY(y)), (y, state) -> protoChunk.setBlockState(pos.setY(y), state, 0));
            }
        }
    }
}
