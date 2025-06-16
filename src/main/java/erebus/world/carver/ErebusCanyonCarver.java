package erebus.world.carver;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ErebusCanyonCarver extends WorldCarver<ErebusCanyonCarverConfiguration> {
    public ErebusCanyonCarver(Codec<ErebusCanyonCarverConfiguration> codec) {
        super(codec);
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
        int branchCount = (int)((float)rangeBlocks * config.shape.distanceFactor.sample(random));

        this.doCarve(context, config, chunk, biomeAccessor, random.nextLong(), aquifer, startX, startY, startZ, thickness, yaw, pitch, branchCount, yScale, carvingMask);
        return true;
    }

    private void doCarve(CarvingContext context, ErebusCanyonCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, long seed, Aquifer aquifer, double x, double y, double z, float thickness, float yaw, float pitch, int branchCount, double horizontalVerticalRatio, CarvingMask carvingMask) {
        RandomSource randomSource = RandomSource.create(seed);
        float[] widthFactors = this.initWidthFactors(context, config, randomSource);
        float yawOffset = 0.0F;
        float pitchOffset = 0.0F;

        for (int branchIndex = 0; branchIndex < branchCount; branchIndex++) {
            double horizontalRadius = 1.5 + (double)(Mth.sin((float)branchIndex * (float) Math.PI / (float)branchCount) * thickness);
            double verticalRadius = horizontalRadius * horizontalVerticalRatio;
            horizontalRadius *= config.shape.horizontalRadiusFactor.sample(randomSource);
            verticalRadius = this.updateVerticalRadius(config, randomSource, verticalRadius, (float)branchCount, (float)branchIndex);
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
        return false;
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
        return (double)radiusFactor * verticalRadius * (double)Mth.randomBetween(random, 0.75F, 1.0F);
    }

    private boolean shouldSkip(CarvingContext context, float[] widthFactors, double relativeX, double relativeY, double relativeZ, int y) {
        int depthIndex = y - context.getMinGenY();
        return (relativeX * relativeX + relativeZ * relativeZ) * (double)widthFactors[depthIndex - 1] + relativeY * relativeY / 6.0 >= 1.0;
    }
}
