package erebus.world.carver;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

public class ErebusCaveCarver extends WorldCarver<ErebusCaveCarverConfiguration> {
    public ErebusCaveCarver(Codec<ErebusCaveCarverConfiguration> codec) {
        super(codec);
    }

    private static boolean shouldSkip(double relativeX, double relativeY, double relativeZ, double minRelativeY) {
        return relativeY <= minRelativeY || relativeX * relativeX + relativeY * relativeY + relativeZ * relativeZ >= (double) 1.0F;
    }

    @Override
    public boolean isStartChunk(ErebusCaveCarverConfiguration config, @NonNull RandomSource random) {
        // Selection happens in carve: the reference draws the count before the probability gate.
        // Keeping both draws together avoids mutable state on this shared worldgen singleton.
        return config.probability > 0;
    }

    @Override
    public boolean carve(@NotNull CarvingContext context, @NotNull ErebusCaveCarverConfiguration config, @NotNull ChunkAccess chunk, @NotNull Function<BlockPos, Holder<Biome>> biomeAccessor, @NotNull RandomSource random, @NotNull Aquifer aquifer, @NotNull ChunkPos chunkPos, @NotNull CarvingMask carvingMask) {
        int caveCount = random.nextInt(random.nextInt(25) + 1);
        boolean selected = config.probability == 0.1F ? random.nextInt(10) == 0 : random.nextFloat() < config.probability;
        if (!selected || caveCount == 0) return false;

        for (int caveIndex = 0; caveIndex < caveCount; ++caveIndex) {
            double posX = chunkPos.getBlockX(random.nextInt(16));
            double posY = config.sampleHeight(random, context);
            double posZ = chunkPos.getBlockZ(random.nextInt(16));
            double horizontalRadius = config.horizontalRadiusMultiplier.sample(random);
            double verticalRadius = config.verticalRadiusMultiplier.sample(random);
            double floorLevelValue = config.floorLevel.sample(random);
            WorldCarver.CarveSkipChecker skipChecker = (ctx, x, y, z, minY) -> shouldSkip(x, y, z, floorLevelValue);
            int tunnelCount = random.nextBoolean() && random.nextBoolean() ? 2 : 1;
            if (random.nextInt(8) == 0) {
                long roomSeed = random.nextLong();
                float roomRadius = 1.0F + random.nextFloat() * 3.0F;
                double yScaleValue = config.yScale.sample(random);
                this.createRoom(context, config, chunk, biomeAccessor, roomSeed, aquifer, posX, posY, posZ, roomRadius, yScaleValue, carvingMask, skipChecker);
                tunnelCount += random.nextInt(3);
            }

            for (int tunnelIndex = 0; tunnelIndex < tunnelCount; ++tunnelIndex) {
                float yawAngle = random.nextFloat() * ((float) Math.PI * 2F);
                float pitchAngle = (random.nextFloat() - 0.5F) / 4.0F;
                float tunnelThickness = this.getThickness(random);
                this.createTunnel(context, config, chunk, biomeAccessor, random.nextLong(), aquifer, posX, posY, posZ, horizontalRadius, verticalRadius, tunnelThickness, yawAngle, pitchAngle, 0, 0, this.getYScale(), carvingMask, skipChecker);
            }
        }

        return true;
    }

    protected float getThickness(RandomSource random) {
        float thickness = random.nextFloat() * 2.5F + random.nextFloat();
        if (random.nextInt(10) == 0) {
            thickness *= random.nextFloat() * random.nextFloat() + 1.0F;
        }

        return thickness;
    }

    protected double getYScale() {
        return 1.0F;
    }

    protected void createRoom(CarvingContext context, ErebusCaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, long seed, Aquifer aquifer, double x, double y, double z, float radius, double horizontalVerticalRatio, CarvingMask carvingMask, WorldCarver.CarveSkipChecker skipChecker) {
        createTunnel(context, config, chunk, biomeAccessor, seed, aquifer, x, y, z, 1, 1, radius, 0, 0, -1, 0, horizontalVerticalRatio, carvingMask, skipChecker);
    }

    protected void createTunnel(CarvingContext context, ErebusCaveCarverConfiguration config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomeAccessor, long seed, Aquifer aquifer, double x, double y, double z, double horizontalRadiusMultiplier, double verticalRadiusMultiplier, float thickness, float yaw, float pitch, int branchIndex, int branchCount, double horizontalVerticalRatio, CarvingMask carvingMask, WorldCarver.CarveSkipChecker skipChecker) {
        RandomSource randomSource = RandomSource.create(seed);
        if (branchCount <= 0) {
            int range = (getRange() * 2 - 1) * 16;
            branchCount = range - randomSource.nextInt(range / 4);
        }
        boolean room = branchIndex == -1;
        if (room) branchIndex = branchCount / 2;
        int branchPoint = randomSource.nextInt(branchCount / 2) + branchCount / 4;
        boolean isSteep = randomSource.nextInt(6) == 0;
        float yawOffset = 0.0F;
        float pitchOffset = 0.0F;

        for (int currentIndex = branchIndex; currentIndex < branchCount; ++currentIndex) {
            double tunnelWidth = (double) 1.5F + (double) (Mth.sin((float) Math.PI * (float) currentIndex / (float) branchCount) * thickness);
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
            if (!room && currentIndex == branchPoint && thickness > 1.0F) {
                this.createTunnel(context, config, chunk, biomeAccessor, randomSource.nextLong(), aquifer, x, y, z, horizontalRadiusMultiplier, verticalRadiusMultiplier, randomSource.nextFloat() * 0.5F + 0.5F, yaw - ((float) Math.PI / 2F), pitch / 3.0F, currentIndex, branchCount, 1.0F, carvingMask, skipChecker);
                this.createTunnel(context, config, chunk, biomeAccessor, randomSource.nextLong(), aquifer, x, y, z, horizontalRadiusMultiplier, verticalRadiusMultiplier, randomSource.nextFloat() * 0.5F + 0.5F, yaw + ((float) Math.PI / 2F), pitch / 3.0F, currentIndex, branchCount, 1.0F, carvingMask, skipChecker);
                return;
            }

            if (room || randomSource.nextInt(4) != 0) {
                if (!canReach(chunk.getPos(), x, z, currentIndex, branchCount, thickness)) {
                    return;
                }

                this.carveEllipsoid(context, config, chunk, biomeAccessor, aquifer, x, y, z, tunnelWidth * horizontalRadiusMultiplier, tunnelHeight * verticalRadiusMultiplier, carvingMask, skipChecker);
                if (room) break;
            }
        }

    }

    @Override
    protected boolean carveEllipsoid(@NonNull CarvingContext context, @NonNull ErebusCaveCarverConfiguration config, ChunkAccess chunk, @NonNull Function<BlockPos, Holder<Biome>> biomes, @NonNull Aquifer aquifer, double x, double y, double z, double horizontalRadius, double verticalRadius, @NonNull CarvingMask mask, @NonNull CarveSkipChecker skip) {
        var chunkPos = chunk.getPos();
        double reach = 16.0 + horizontalRadius * 2.0;
        if (Math.abs(x - chunkPos.getMiddleBlockX()) > reach || Math.abs(z - chunkPos.getMiddleBlockZ()) > reach) return false;
        int minX = Math.max(Mth.floor(x - horizontalRadius) - chunkPos.getMinBlockX() - 1, 0);
        int maxX = Math.min(Mth.floor(x + horizontalRadius) - chunkPos.getMinBlockX() + 1, 16);
        int minZ = Math.max(Mth.floor(z - horizontalRadius) - chunkPos.getMinBlockZ() - 1, 0);
        int maxZ = Math.min(Mth.floor(z + horizontalRadius) - chunkPos.getMinBlockZ() + 1, 16);
        int minY = Math.max(Mth.floor(y - verticalRadius) - 1, context.getMinGenY() + 6);
        int maxY = Math.min(Mth.floor(y + verticalRadius) + 1, context.getMinGenY() + context.getGenDepth() - 4);
        if (minY >= maxY) return false;
        if (CarverWaterBoundary.intersectsWater(chunk, context.getMinGenY(), context.getMinGenY() + context.getGenDepth(),
                minX, maxX, minY, maxY, minZ, maxZ)) return false;
        boolean carved = false;
        var pos = new BlockPos.MutableBlockPos();
        for (int lx = minX; lx < maxX; lx++)
            for (int lz = minZ; lz < maxZ; lz++) {
                double dx = (chunkPos.getBlockX(lx) + 0.5 - x) / horizontalRadius;
                double dz = (chunkPos.getBlockZ(lz) + 0.5 - z) / horizontalRadius;
                if (dx * dx + dz * dz >= 1) continue;
                for (int py = maxY - 1; py >= minY; py--) {
                    double dy = (py + 0.5 - y) / verticalRadius;
                    if (skip.shouldSkip(context, dx, dy, dz, py) || mask.get(lx, py, lz)) continue;
                    pos.set(chunkPos.getBlockX(lx), py, chunkPos.getBlockZ(lz));
                    var existing = chunk.getBlockState(pos);
                    if (!existing.hasBlockEntity() && existing.getFluidState().isEmpty() && !existing.is(Blocks.BEDROCK)
                            && (canReplaceBlock(config, existing) || existing.is(Blocks.DIRT) || existing.is(Blocks.GRASS_BLOCK))) {
                        chunk.setBlockState(pos, Blocks.AIR.defaultBlockState());
                        mask.set(lx, py, lz);
                        carved = true;
                    }
                }
            }
        return carved;
    }
}
