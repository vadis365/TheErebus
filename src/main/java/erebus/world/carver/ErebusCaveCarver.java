package erebus.world.carver;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
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

public class ErebusCaveCarver extends WorldCarver<ErebusCaveCarverConfiguration> {
    public ErebusCaveCarver(Codec<ErebusCaveCarverConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean isStartChunk(ErebusCaveCarverConfiguration config, RandomSource random) {
        return random.nextFloat() <= config.probability;
    }

    @Override
    public boolean carve(@NotNull CarvingContext context, @NotNull ErebusCaveCarverConfiguration config, @NotNull ChunkAccess chunk, @NotNull Function<BlockPos, Holder<Biome>> biomeAccessor, @NotNull RandomSource random, @NotNull Aquifer aquifer, @NotNull ChunkPos chunkPos, @NotNull CarvingMask carvingMask) {
        int maxRange = SectionPos.sectionToBlockCoord(this.getRange() * 2 - 1);
        int caveCount = random.nextInt(random.nextInt(random.nextInt(this.getCaveBound()) + 1) + 1);

        for(int caveIndex = 0; caveIndex < caveCount; ++caveIndex) {
            double posX = chunkPos.getBlockX(random.nextInt(16));
            double posY = config.y.sample(random, context);
            double posZ = chunkPos.getBlockZ(random.nextInt(16));
            double horizontalRadius = config.horizontalRadiusMultiplier.sample(random);
            double verticalRadius = config.verticalRadiusMultiplier.sample(random);
            double floorLevelValue = config.floorLevel.sample(random);
            WorldCarver.CarveSkipChecker skipChecker = (ctx, x, y, z, minY) -> shouldSkip(x, y, z, floorLevelValue);
            int tunnelCount = 1;
            if (random.nextInt(4) == 0) {
                double yScaleValue = config.yScale.sample(random);
                float roomRadius = 1.0F + random.nextFloat() * 6.0F;
                this.createRoom(context, config, chunk, biomeAccessor, aquifer, posX, posY, posZ, roomRadius, yScaleValue, carvingMask, skipChecker);
                tunnelCount += random.nextInt(4);
            }

            for(int tunnelIndex = 0; tunnelIndex < tunnelCount; ++tunnelIndex) {
                float yawAngle = random.nextFloat() * ((float)Math.PI * 2F);
                float pitchAngle = (random.nextFloat() - 0.5F) / 4.0F;
                float tunnelThickness = this.getThickness(random);
                int tunnelLength = maxRange - random.nextInt(maxRange / 4);
                this.createTunnel(context, config, chunk, biomeAccessor, random.nextLong(), aquifer, posX, posY, posZ, horizontalRadius, verticalRadius, tunnelThickness, yawAngle, pitchAngle, 0, tunnelLength, this.getYScale(), carvingMask, skipChecker);
            }
        }

        return true;
    }

    protected int getCaveBound() {
        return 15;
    }

    protected float getThickness(RandomSource random) {
        float thickness = random.nextFloat() * 2.0F + random.nextFloat();
        if (random.nextInt(10) == 0) {
            thickness *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
        }

        return thickness;
    }

    protected double getYScale() {
        return 1.0F;
    }

    protected void createRoom(CarvingContext context, ErebusCaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, Aquifer aquifer, double x, double y, double z, float radius, double horizontalVerticalRatio, CarvingMask carvingMask, WorldCarver.CarveSkipChecker skipChecker) {
        double roomWidth = (double)1.5F + (double)(Mth.sin(((float)Math.PI / 2F)) * radius);
        double roomHeight = roomWidth * horizontalVerticalRatio;
        this.carveEllipsoid(context, config, chunk, biomeAccessor, aquifer, x + (double)1.0F, y, z, roomWidth, roomHeight, carvingMask, skipChecker);
    }

    protected void createTunnel(CarvingContext context, ErebusCaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, long seed, Aquifer aquifer, double x, double y, double z, double horizontalRadiusMultiplier, double verticalRadiusMultiplier, float thickness, float yaw, float pitch, int branchIndex, int branchCount, double horizontalVerticalRatio, CarvingMask carvingMask, WorldCarver.CarveSkipChecker skipChecker) {
        RandomSource randomSource = RandomSource.create(seed);
        int branchPoint = randomSource.nextInt(branchCount / 2) + branchCount / 4;
        boolean isSteep = randomSource.nextInt(6) == 0;
        float yawOffset = 0.0F;
        float pitchOffset = 0.0F;

        for(int currentIndex = branchIndex; currentIndex < branchCount; ++currentIndex) {
            double tunnelWidth = (double)1.5F + (double)(Mth.sin((float)Math.PI * (float)currentIndex / (float)branchCount) * thickness);
            double tunnelHeight = tunnelWidth * horizontalVerticalRatio;
            float pitchCos = Mth.cos(pitch);
            x += Mth.cos(yaw) * pitchCos;
            y += Mth.sin(pitch);
            z += Mth.sin(yaw) * pitchCos;
            pitch *= isSteep ? 0.92F : 0.7F;
            pitch += pitchOffset * 0.1F;
            yaw += yawOffset * 0.1F;
            pitchOffset *= 0.9F;
            yawOffset *= 0.75F;
            pitchOffset += (randomSource.nextFloat() - randomSource.nextFloat()) * randomSource.nextFloat() * 2.0F;
            yawOffset += (randomSource.nextFloat() - randomSource.nextFloat()) * randomSource.nextFloat() * 4.0F;
            if (currentIndex == branchPoint && thickness > 1.0F) {
                this.createTunnel(context, config, chunk, biomeAccessor, randomSource.nextLong(), aquifer, x, y, z, horizontalRadiusMultiplier, verticalRadiusMultiplier, randomSource.nextFloat() * 0.5F + 0.5F, yaw - ((float)Math.PI / 2F), pitch / 3.0F, currentIndex, branchCount, 1.0F, carvingMask, skipChecker);
                this.createTunnel(context, config, chunk, biomeAccessor, randomSource.nextLong(), aquifer, x, y, z, horizontalRadiusMultiplier, verticalRadiusMultiplier, randomSource.nextFloat() * 0.5F + 0.5F, yaw + ((float)Math.PI / 2F), pitch / 3.0F, currentIndex, branchCount, 1.0F, carvingMask, skipChecker);
                return;
            }

            if (randomSource.nextInt(4) != 0) {
                if (!canReach(chunk.getPos(), x, z, currentIndex, branchCount, thickness)) {
                    return;
                }

                this.carveEllipsoid(context, config, chunk, biomeAccessor, aquifer, x, y, z, tunnelWidth * horizontalRadiusMultiplier, tunnelHeight * verticalRadiusMultiplier, carvingMask, skipChecker);
            }
        }

    }

    private static boolean shouldSkip(double relativeX, double relativeY, double relativeZ, double minRelativeY) {
        return relativeY <= minRelativeY || relativeX * relativeX + relativeY * relativeY + relativeZ * relativeZ >= (double) 1.0F;
    }
}
