package erebus.world.carver;

import com.mojang.serialization.Codec;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

public class ErebusCanyonCarver extends WorldCarver<ErebusCanyonCarverConfiguration> {
    public ErebusCanyonCarver(Codec<ErebusCanyonCarverConfiguration> codec) {
        super(codec);
    }

    private static BlockState topMaterial(Holder<Biome> biome) {
        if (biome.is(ModBiomes.PETRIFIED_FOREST_KEY)) return ModBlocks.VOLCANIC_ROCK.get().defaultBlockState();
        if (biome.is(ModBiomes.VOLCANIC_DESERT_KEY)) return Blocks.SAND.defaultBlockState();
        if (biome.is(ModBiomes.ULTERIOR_OUTBACK_KEY)) return Blocks.RED_SAND.defaultBlockState();
        return Blocks.GRASS_BLOCK.defaultBlockState();
    }

    private static BlockState fillerMaterial(Holder<Biome> biome) {
        if (biome.is(ModBiomes.PETRIFIED_FOREST_KEY)) return ModBlocks.VOLCANIC_ROCK.get().defaultBlockState();
        if (biome.is(ModBiomes.VOLCANIC_DESERT_KEY)) return Blocks.SANDSTONE.defaultBlockState();
        if (biome.is(ModBiomes.ULTERIOR_OUTBACK_KEY)) return Blocks.RED_SANDSTONE.defaultBlockState();
        return Blocks.DIRT.defaultBlockState();
    }

    @Override
    public boolean carve(@NotNull CarvingContext context, ErebusCanyonCarverConfiguration config, @NotNull ChunkAccess chunk, @NotNull Function<BlockPos, Holder<Biome>> biomeAccessor, RandomSource random, @NotNull Aquifer aquifer, ChunkPos chunkPos, @NotNull CarvingMask carvingMask) {
        int rangeBlocks = (this.getRange() * 2 - 1) * 16;
        double startX = chunkPos.getBlockX(random.nextInt(16));
        int startY = config.y.sample(random, context);
        double startZ = chunkPos.getBlockZ(random.nextInt(16));
        float yaw = random.nextFloat() * (float) (Math.PI * 2);
        float pitch = config.verticalRotation.sample(random);
        double yScale = config.yScale.sample(random);
        float thickness = config.shape.thickness.sample(random);
        int branchCount = (int) ((float) rangeBlocks * config.shape.distanceFactor.sample(random));

        this.doCarve(context, config, chunk, biomeAccessor, random.nextLong(), aquifer, startX, startY, startZ, thickness, yaw, pitch, branchCount, yScale, carvingMask);
        return true;
    }

    private void doCarve(CarvingContext context, ErebusCanyonCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, long seed, Aquifer aquifer, double x, double y, double z, float thickness, float yaw, float pitch, int branchCount, double horizontalVerticalRatio, CarvingMask carvingMask) {
        RandomSource randomSource = RandomSource.create(seed);
        branchCount -= randomSource.nextInt(Math.max(1, branchCount / 4));
        float[] widthFactors = this.initWidthFactors(context, config, randomSource);
        float yawOffset = 0.0F;
        float pitchOffset = 0.0F;

        for (int branchIndex = 0; branchIndex < branchCount; branchIndex++) {
            double horizontalRadius = 1.5 + (double) (Mth.sin((float) branchIndex * (float) Math.PI / (float) branchCount) * thickness);
            double verticalRadius = horizontalRadius * horizontalVerticalRatio;
            horizontalRadius *= config.shape.horizontalRadiusFactor.sample(randomSource);
            verticalRadius = this.updateVerticalRadius(config, randomSource, verticalRadius, (float) branchCount, (float) branchIndex);
            float cosOfPitch = Mth.cos(pitch);
            float sinOfPitch = Mth.sin(pitch);
            x += Mth.cos(yaw) * cosOfPitch;
            y += sinOfPitch;
            z += Mth.sin(yaw) * cosOfPitch;
            pitch *= 0.7F;
            pitch += pitchOffset * 0.05F;
            yaw += yawOffset * 0.05F;
            pitchOffset *= 0.8F;
            yawOffset *= 0.5F;
            pitchOffset += (randomSource.nextFloat() - randomSource.nextFloat()) * randomSource.nextFloat() * 2.0F;
            yawOffset += (randomSource.nextFloat() - randomSource.nextFloat()) * randomSource.nextFloat() * 4.0F;
            if (randomSource.nextInt(4) != 0) {
                if (!canReach(chunk.getPos(), x, z, branchIndex, branchCount, thickness)) {
                    return;
                }

                this.carveEllipsoid(context, config, chunk, biomeAccessor, aquifer, x, y, z, horizontalRadius, verticalRadius, carvingMask, (context1, relativeX, relativeY, relativeZ, y1) -> this.shouldSkip(context1, widthFactors, relativeX, relativeY, relativeZ, y1));
            }
        }
    }

    @Override
    public boolean isStartChunk(@NotNull ErebusCanyonCarverConfiguration context, @NotNull RandomSource random) {
        return context.probability == 0.02F ? random.nextInt(50) == 0 : random.nextFloat() < context.probability;
    }

    @Override
    protected boolean carveEllipsoid(@NonNull CarvingContext context, @NonNull ErebusCanyonCarverConfiguration config, ChunkAccess chunk, @NonNull Function<BlockPos, Holder<Biome>> biomes, @NonNull Aquifer aquifer, double x, double y, double z, double horizontalRadius, double verticalRadius, @NonNull CarvingMask mask, @NonNull CarveSkipChecker skip) {
        var chunkPos = chunk.getPos();
        double reach = 16.0 + horizontalRadius * 2.0;
        if (Math.abs(x - chunkPos.getMiddleBlockX()) > reach || Math.abs(z - chunkPos.getMiddleBlockZ()) > reach) return false;
        int minX = Math.max(Mth.floor(x - horizontalRadius) - chunkPos.getMinBlockX() - 1, 0);
        int maxX = Math.min(Mth.floor(x + horizontalRadius) - chunkPos.getMinBlockX() + 1, 16);
        int minZ = Math.max(Mth.floor(z - horizontalRadius) - chunkPos.getMinBlockZ() - 1, 0);
        int maxZ = Math.min(Mth.floor(z + horizontalRadius) - chunkPos.getMinBlockZ() + 1, 16);
        int minY = Math.max(Mth.floor(y - verticalRadius) - 1, context.getMinGenY() + 1);
        int maxY = Math.min(Mth.floor(y + verticalRadius) + 1, context.getMinGenY() + context.getGenDepth() - 8);
        if (minY >= maxY) return false;
        var pos = new BlockPos.MutableBlockPos();
        if (CarverWaterBoundary.intersectsWater(chunk, context.getMinGenY(), context.getMinGenY() + context.getGenDepth(),
                minX, maxX, minY, maxY, minZ, maxZ)) return false;
        boolean carved = false;
        var helper = new BlockPos.MutableBlockPos();
        for (int lx = minX; lx < maxX; lx++)
            for (int lz = minZ; lz < maxZ; lz++) {
                double dx = (chunkPos.getBlockX(lx) + 0.5 - x) / horizontalRadius;
                double dz = (chunkPos.getBlockZ(lz) + 0.5 - z) / horizontalRadius;
                if (dx * dx + dz * dz >= 1) continue;
                var foundTop = new MutableBoolean(false);
                for (int py = maxY - 1; py >= minY; py--) {
                    double dy = (py + 0.5 - y) / verticalRadius;
                    if (skip.shouldSkip(context, dx, dy, dz, py) || mask.get(lx, py, lz)) continue;
                    pos.set(chunkPos.getBlockX(lx), py, chunkPos.getBlockZ(lz));
                    if (carveBlock(context, config, chunk, biomes, mask, pos, helper, aquifer, foundTop)) {
                        mask.set(lx, py, lz);
                        carved = true;
                    }
                }
            }
        return carved;
    }

    @Override
    protected boolean carveBlock(CarvingContext context, @NonNull ErebusCanyonCarverConfiguration config, @NonNull ChunkAccess chunk, @NonNull Function<BlockPos, Holder<Biome>> biomes, @NonNull CarvingMask mask, BlockPos.MutableBlockPos pos, BlockPos.@NonNull MutableBlockPos helper, @NonNull Aquifer aquifer, @NonNull MutableBoolean foundTop) {
        int relativeY = pos.getY() - context.getMinGenY();
        if (relativeY < 0 || relativeY >= context.getGenDepth()) return false;
        var biome = biomes.apply(pos);
        var top = topMaterial(biome);
        var filler = fillerMaterial(biome);
        var existing = chunk.getBlockState(pos);
        if (existing.hasBlockEntity() || !existing.getFluidState().isEmpty() || existing.is(Blocks.BEDROCK)) return false;
        if (!canReplaceBlock(config, existing) && !existing.is(top.getBlock()) && !existing.is(filler.getBlock())) return false;
        if (existing.is(top.getBlock())) foundTop.setTrue();
        BlockState result;
        if (relativeY < 3) result = Blocks.BEDROCK.defaultBlockState();
        else if (relativeY < 4) result = ModBlocks.UMBERSTONE.get().defaultBlockState();
        else if (relativeY < 10 && biome.is(ModBiomes.VOLCANIC_DESERT_KEY)) result = Blocks.LAVA.defaultBlockState();
        else if (relativeY < 23 && biome.is(ModBiomes.SUBMERGED_SWAMP_KEY)) result = Blocks.WATER.defaultBlockState();
        else result = Blocks.AIR.defaultBlockState();
        chunk.setBlockState(pos, result);
        if (!result.getFluidState().isEmpty()) chunk.markPosForPostprocessing(pos);
        if (result.isAir() && foundTop.isTrue() && relativeY > 0) {
            helper.set(pos).move(Direction.DOWN);
            if (chunk.getBlockState(helper).is(filler.getBlock())) chunk.setBlockState(helper, top);
        }
        return true;
    }

    private float[] initWidthFactors(CarvingContext context, ErebusCanyonCarverConfiguration config, RandomSource random) {
        int genDepth = context.getGenDepth();
        float[] widthFactors = new float[genDepth];
        float widthFactor = 1.0F;

        for (int depthIndex = 0; depthIndex < genDepth; depthIndex++) {
            if (depthIndex == 0 || random.nextInt(config.shape.widthSmoothness) == 0) {
                widthFactor = 1.0F + random.nextFloat() * random.nextFloat();
            }

            widthFactors[depthIndex] = widthFactor * widthFactor;
        }

        return widthFactors;
    }

    private double updateVerticalRadius(ErebusCanyonCarverConfiguration config, RandomSource random, double verticalRadius, float branchCount, float currentBranch) {
        float centerFactor = 1.0F - Mth.abs(0.5F - currentBranch / branchCount) * 2.0F;
        float radiusFactor = config.shape.verticalRadiusDefaultFactor + config.shape.verticalRadiusCenterFactor * centerFactor;
        return (double) radiusFactor * verticalRadius * (double) Mth.randomBetween(random, 0.75F, 1.0F);
    }

    private boolean shouldSkip(CarvingContext context, float[] widthFactors, double relativeX, double relativeY, double relativeZ, int y) {
        int depthIndex = y - context.getMinGenY();
        return (relativeX * relativeX + relativeZ * relativeZ) * (double) widthFactors[depthIndex] + relativeY * relativeY / 6.0 >= 1.0;
    }
}
